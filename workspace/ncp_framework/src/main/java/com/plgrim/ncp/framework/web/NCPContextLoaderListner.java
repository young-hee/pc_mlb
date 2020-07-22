package com.plgrim.ncp.framework.web;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;

import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * NCP framework에 등록되는 자원 해제
 * 
 * @author Chulhui Park <charles@plgrim.com>
 *
 */
@Slf4j
public class NCPContextLoaderListner extends ContextLoaderListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		
		try {
	        // This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks wrto this class
	        Enumeration<Driver> drivers = DriverManager.getDrivers();
	        while (drivers.hasMoreElements()) {
	            Driver driver = drivers.nextElement();
	            try {
	                DriverManager.deregisterDriver(driver);
	                log.info("deregistering jdbc driver: {}", driver);
	            } catch (SQLException e) {
	                log.warn("Error deregistering driver {}", driver, e);
	            }

	        }
	        
			LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
			log.info("call logback.stop() by contextDestroyed event");
			loggerContext.stop();

		} catch (Exception e) {
			log.warn("error occured during destroy resource:{}", e.getMessage(), e);
		}
	}

}
