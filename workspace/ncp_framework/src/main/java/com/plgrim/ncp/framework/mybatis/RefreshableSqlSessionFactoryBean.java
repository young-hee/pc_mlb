package com.plgrim.ncp.framework.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class RefreshableSqlSessionFactoryBean extends TypedSqlSessionFactoryBean {
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock readLock = rwl.readLock();
	private final Lock writeLock = rwl.writeLock();
	private SqlSessionFactory proxy;
	private int interval = 500;
	private Timer timer;
	private TimerTask task;
	private Resource[] mapperLocations;

	/**
	 * 파일 감시 쓰레드가 실행중인지 여부.
	 */
	private boolean running = false;

	public void setMapperLocations(Resource[] mapperLocations) {
		super.setMapperLocations(mapperLocations);
		this.mapperLocations = Arrays.copyOf(mapperLocations, mapperLocations.length);
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	/**
	 * @throws Exception
	 */

	public void refresh() throws Exception {
		log.info("Refreshing sqlMapClient.");
		writeLock.lock();

		try {
			super.afterPropertiesSet();
		} finally {
			writeLock.unlock();
		}

	}

	/**
	 * 싱글톤 멤버로 SqlMapClient 원본 대신 프록시로 설정하도록 오버라이드.
	 */

	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		setRefreshable();
	}

	private void setRefreshable() {
		proxy = (SqlSessionFactory) Proxy.newProxyInstance(SqlSessionFactory.class.getClassLoader(),
				new Class[] { SqlSessionFactory.class }, new InvocationHandler() {
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						return method.invoke(getParentObject(), args);
					}
				});

		task = new TimerTask() {
			private Map<Resource, Long> map = new ConcurrentHashMap<Resource, Long>();

			public void run() {
				if (isModified()) {
					log.info("SQL is modifeid. Try to realod");
					try {
						refresh();
						log.info("SQL Reloading is completed");
					} catch (Exception e) {
						log.error("caught exception", e);
					}
				}
			}

			private boolean isModified() {
				if (mapperLocations == null) {
					return false;
				}
				for (int i = 0; i < mapperLocations.length; i++) {
					Resource mappingLocation = mapperLocations[i];
					if (mappingLocation == null) {
						continue;
					}
					if (findModifiedResource(mappingLocation)) {
						return true;
					}
				}
				return false;

			}

			private boolean findModifiedResource(Resource resource) {
				boolean retVal = false;
				List<String> modifiedResources = new ArrayList<String>();
				try {
					long modified = resource.lastModified();
					if (map.containsKey(resource)) {
						long lastModified = map.get(resource).longValue();
						if (lastModified != modified) {
							map.put(resource, new Long(modified));
							modifiedResources.add(resource.getDescription());
							retVal = true;
						}
					} else {
						map.put(resource, new Long(modified));
					}

				} catch (IOException e) {
					log.error("caught exception", e);
				}

				if (retVal) {
					if (log.isInfoEnabled()) {
						log.info("modified files : " + modifiedResources);
					}
				}

				return retVal;
			}
		};

		timer = new Timer(true);
		resetInterval();

	}

	private Object getParentObject() throws Exception {
		readLock.lock();
		try {
			return super.getObject();
		} finally {
			readLock.unlock();

		}

	}

	public SqlSessionFactory getObject() {

		return this.proxy;

	}

	public Class<? extends SqlSessionFactory> getObjectType() {

		return (this.proxy != null ? this.proxy.getClass() : SqlSessionFactory.class);

	}

	public boolean isSingleton() {

		return true;

	}

	public void setCheckInterval(int ms) {
		interval = ms;
		if (timer != null) {
			resetInterval();
		}

	}

	private void resetInterval() {
		if (running) {
			timer.cancel();
			running = false;
		}

		if (interval > 0) {
			timer.schedule(task, 0, interval);
			running = true;
		}
	}

	public void destroy() throws Exception {
		timer.cancel();
	}
}