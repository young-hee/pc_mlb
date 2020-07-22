package com.plgrim.ncp.framework.cloud.cache;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.plgrim.ncp.framework.config.PropertyCondition;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPoolConfig;

/**
 * spring cache implementation으로 redis 사용 (WAS의 in-memory cache)<br>
 * dev/stg/prd envrionments에서 사용한다.
 * 
 * @author charles <charles@plgrim.com>
 *
 */
@PropertyCondition(value = "ncp_base.cache.external.enable.jedis")
@Configuration
@EnableCaching
@Slf4j
public class RedisConfig {
	@Autowired
	ApplicationContext context;

	@Value("${ncp_base.cloud.redis.clusterMode:false}")
	private boolean clusterMode;

	@Value("${ncp_base.cloud.ehcache.config.location}")
	private String configLocation;

	@Value("${ncp_base.cloud.redis.host}")
	private String redisHost;

	@Value("${ncp_base.cloud.redis.port}")
	private int redisPort;

	@Value("${ncp_base.cloud.redis.pool.min}")
	private int redisPoolMin;

	@Value("${ncp_base.cloud.redis.pool.max}")
	private int redisPoolMax;

	@Value("${ncp_base.cloud.redis.default.timeout}")
	private int defaultTimeout;

	/**
	 * create JedisConnectionFactory bean
	 * 
	 * @return return created instance
	 */
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		log.info("create JedisConnectionFactory with host[{}], port[{}]", redisHost, redisPort);
		if (clusterMode) {
			List<String> nodeList = new LinkedList<>();

			String[] hosts = StringUtils.split(redisHost, '|');
			for (String host : hosts) {
				nodeList.add(host + ":" + redisPort);
			}

			return new JedisConnectionFactory(new RedisClusterConfiguration(nodeList));
		} else {

			JedisConnectionFactory factory = new JedisConnectionFactory();
			factory.setHostName(redisHost);
			factory.setPort(redisPort);
			factory.setUsePool(true);

			// jedis pool config
			if (this.redisPoolMin != 0 && this.redisPoolMax != 0) {
				log.info("create JedisConnectionPool with min[{}], max[{}]", redisPoolMin, redisPoolMax);

				JedisPoolConfig poolConfig = new JedisPoolConfig();
				poolConfig.setMaxIdle(this.redisPoolMin);
				poolConfig.setMinIdle(this.redisPoolMin);
				poolConfig.setMaxTotal(this.redisPoolMax);
				poolConfig.setMaxIdle(this.redisPoolMax);
				poolConfig.setTestOnBorrow(true);
				factory.setPoolConfig(poolConfig);
			}
			return factory;
		}
	}

	private Map<String, Long> createCacheConfigMap() throws ParserConfigurationException, SAXException, IOException {
		Resource resource = context.getResource(configLocation);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(resource.getFile());

		doc.getDocumentElement().normalize();

		Map<String, Long> configMap = new HashMap<>();
		NodeList nodeList = doc.getElementsByTagName("cache");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			NamedNodeMap nodeMap = node.getAttributes();
			String cacheName = null;
			String cacheSecond = null;
			for (int j = 0; j < nodeMap.getLength(); j++) {
				Node attributeNode = nodeMap.item(j);
				if ("name".equals(attributeNode.getNodeName())) {
					cacheName = attributeNode.getNodeValue();
				} else if ("timeToLiveSeconds".equals(attributeNode.getNodeName())) {
					cacheSecond = attributeNode.getNodeValue();
				}
				if (log.isDebugEnabled()) {
					log.debug("cache-config: name: {}, value: {}", cacheName, cacheSecond);
				}
			}
			configMap.put(cacheName, Long.parseLong(cacheSecond));

		}

		return configMap;
	}

	@Bean
	public CacheManager redisCacheManager(JedisConnectionFactory jedisConnectionFactory)
			throws ParserConfigurationException, SAXException, IOException {
		Map<String, Long> cacheConfigMap = createCacheConfigMap();

		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		redisTemplate.afterPropertiesSet();

		RedisCacheManager manager = new RedisCacheManager(redisTemplate);
		manager.setExpires(cacheConfigMap);
		manager.setDefaultExpiration(defaultTimeout);
		manager.setUsePrefix(true);

		log.info("redisCacheManager initialized with config:{}", cacheConfigMap);

		return manager;
	}
}
