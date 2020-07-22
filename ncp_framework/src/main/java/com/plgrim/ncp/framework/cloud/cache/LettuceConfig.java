package com.plgrim.ncp.framework.cloud.cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.resource.ClientResources;
import com.lambdaworks.redis.resource.DefaultClientResources;
import com.plgrim.ncp.framework.config.PropertyCondition;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@PropertyCondition(value = "ncp_base.cache.external.enable.lettuce")
@Configuration
@EnableCaching
@Slf4j
public class LettuceConfig {
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
	
	@Value("${ncp_base.cloud.redis.cluster.max.redirect:2}")
	private int clusterMaxRedirect;
	
    @Bean(destroyMethod = "shutdown")
    ClientResources clientResources() {
        return DefaultClientResources.create();
    }

    @Bean(destroyMethod = "shutdown")
    RedisClient redisClient(ClientResources clientResources) {
    	RedisClient client =  RedisClient.create(clientResources, RedisURI.create(redisHost, redisPort));
    	
//    	setDefaultTimeout(Duration.ofSeconds(defaultTimeout));
    	client.setDefaultTimeout(defaultTimeout, TimeUnit.SECONDS);
    	
    	return client;
    }

    @Bean(destroyMethod = "close")
    StatefulRedisConnection<String, String> connection(RedisClient redisClient) {
        return redisClient.connect();
    }
    
    // lettuce connection pool은 single connection이 resource 사용 대비 perforamnce에서 유리하다고 하여, 일단 구성하지 않는다.
    // https://github.com/lettuce-io/lettuce-core/issues/264
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
		LettuceCustomConnectionFactory connectionFactory = null;
    	if (clusterMode) {
			List<String> nodeList = new LinkedList<>();

			String[] hosts = StringUtils.split(redisHost, '|');
			for (String host : hosts) {
				nodeList.add(host + ":" + redisPort);
			}
			
    		RedisClusterConfiguration config = new RedisClusterConfiguration(nodeList);
    		config.setMaxRedirects(clusterMaxRedirect);
    		connectionFactory = new LettuceCustomConnectionFactory(config);
    		log.info("lettuce RedisConnectionFactory initialized for cluster mode:{}", nodeList);
    	} else {
    		connectionFactory = new LettuceCustomConnectionFactory(redisHost, redisPort);
    		log.info("lettuce RedisConnectionFactory initialized for master/slave mode");
    	}
    	connectionFactory.setTimeout(defaultTimeout*1000L);
    	
    	log.info("lettuce RedisConnectionFactory created:{}", connectionFactory);
    	return connectionFactory;
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
	public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory)
			throws ParserConfigurationException, SAXException, IOException {
		Map<String, Long> cacheConfigMap = createCacheConfigMap();

		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());

		/**
		 * cache data setting 과정에 필드 매핑이 안되는 문제로 인해
		 * JsonAutoDetect.Visibility.ANY 를 통해 모든 접근 지시자 가능하도록 설정
		 * 참조 : https://www.baeldung.com/jackson-jsonmappingexception
		 */
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
		jackson2JsonRedisSerializer.setObjectMapper(new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY));
		redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
		redisTemplate.afterPropertiesSet();

		RedisCacheManager manager = new RedisCacheManager(redisTemplate);
		manager.setExpires(cacheConfigMap);
		manager.setDefaultExpiration(defaultTimeout);
		manager.setUsePrefix(true);

		log.info("redisCacheManager initialized with config:{}", cacheConfigMap);

		return manager;
	}
}
