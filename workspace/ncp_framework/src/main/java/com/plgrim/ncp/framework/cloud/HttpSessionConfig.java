package com.plgrim.ncp.framework.cloud;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import com.plgrim.ncp.framework.cloud.session.IndexedMapSessionRepository;
import com.plgrim.ncp.framework.cloud.session.RedisSessionRegistry;
import com.plgrim.ncp.framework.config.PropertyCondition;

import lombok.extern.slf4j.Slf4j;

/**
 * WAS의 HttpSession을 Spring의 HttpSession을 사용하도록 변경하는 configuration
 * 이 configuration과 함께 web.xml에 filter도 등록해줘야 한다.
 */
@Configuration
@EnableSpringHttpSession
@Slf4j
public class HttpSessionConfig {
	public static final int TIME_UNIT_MINUTE = 60;
    
//    @Value("${ncp_base.http.session.external.enabled:true}")
//    private boolean enabledExternalHttpSession;
	
	@Value("${ncp_base.was.session.timeout:30}")
    private int baseSessionTimeout;
	
    /**
     * create FindByIndexNameSessionRepository bean for Spring Session
     * 
     * @return return created instance
     */
    @PropertyCondition(value="ncp_base.cache.external.enable", expectCondition=false)
    @Bean
    @Qualifier("sessionRepository")
    public FindByIndexNameSessionRepository<ExpiringSession> mapSessionRepository() {
    	log.info("create sessionRepository using in-memory");
    	
        IndexedMapSessionRepository indexedMapSessionRepository = new IndexedMapSessionRepository();
        indexedMapSessionRepository.setDefaultMaxInactiveInterval(baseSessionTimeout*TIME_UNIT_MINUTE);
        return indexedMapSessionRepository;
    }

    /**
     * create FindByIndexNameSessionRepository bean for Spring Session
     * 
     * @param sessionRepository injected FindByIndexNameSessionRepository bean
     * @return return created instance
     */
    @PropertyCondition(value="ncp_base.cache.external.enable", expectCondition=false)
    @Bean
    @Qualifier("sessionRegistry")
    public SpringSessionBackedSessionRegistry sessionRegistryForLocal(
            FindByIndexNameSessionRepository<ExpiringSession> sessionRepository) {
    	log.info("create sessionRegistry using in-memory");
    	
        return new SpringSessionBackedSessionRegistry(sessionRepository);
    }

    /**
     * create RedisOperationsSessionRepository bean for Spring Session
     * 
     * @param connectionFactory injected RedisConnectionFactory bean
     * @return return created instance
     * @throws NamingException 
     */
    @PropertyCondition("ncp_base.cache.external.enable")
    @Bean
    @Qualifier("sessionRepository")
    public SessionRepository<?> redisSessionRepository(RedisConnectionFactory connectionFactory) throws NamingException {
//    	if (!enabledExternalHttpSession) {
//            return mapSessionRepository();
//        }
    
    	log.info("create sessionRepository using external redis");
		Context defCtx = (Context) new InitialContext().lookup("java:comp/env");
		String systemId = (String) defCtx.lookup("system.id");
		
        RedisOperationsSessionRepository repository = new RedisOperationsSessionRepository(connectionFactory);
        repository.setRedisKeyNamespace(systemId);
        repository.setDefaultMaxInactiveInterval(baseSessionTimeout*TIME_UNIT_MINUTE);
        return repository;
    }
    
    /**
     * create RedisSessionRegistry bean for Spring Session
     * 
     * @param connectionFactory injected RedisConnectionFactory bean
     * @return return created instance
     */
    @PropertyCondition("ncp_base.cache.external.enable")
    @Bean
    @Qualifier("sessionRegistry")
    public SessionRegistry sessionRegistry(RedisConnectionFactory connectionFactory) {
//        if (!enabledExternalHttpSession) {
//            return sessionRegistryForLocal(mapSessionRepository());
//        }
        
        log.info("create sessionRegistry using external redis");
        return new RedisSessionRegistry(connectionFactory);
    }
}
