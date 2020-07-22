package com.plgrim.ncp.framework.cloud.cache;


import com.plgrim.ncp.framework.cloud.ApiClientFactory;
import com.plgrim.ncp.framework.config.PropertyCondition;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.nio.charset.Charset;
import java.util.Set;

@PropertyCondition(value = "ncp.cache.external.flush.enable")
@Configuration
@Slf4j
public class RedisCacheFlush implements InitializingBean {
    private static final String PREFIX_SPRING_SESSION = "spring:session";

    @Autowired(required = false)
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    ApiClientFactory clientFactory;

    @Override
    public void afterPropertiesSet() {
        try {
            /**
             * RedisConnectionFactory 가 null 일경우 Redis를 사용하지 않으므로 Path
             */
            if (redisConnectionFactory == null) {
                log.info("{} Redis Disabled", this.getClass());
                return;
            }

            flushAllDisplayCacheData();
        } catch (Exception e) {
            log.warn("Redis DATA flush fail {}", e);
        }
    }

    /**
     * Cache를 사용중인 Display Object 변경 후 역직렬화 과정에서
     * Object 불일치를 최소화 하기 위해 서버 기동시 초기화 진행
     *
     * @throws Exception
     */
    private void flushAllDisplayCacheData() throws Exception {
        Context env = (Context) new InitialContext().lookup("java:comp/env");
        String mallId = StringUtils.lowerCase((String) env.lookup("mall.id"));

        RedisConnection conn = redisConnectionFactory.getConnection();

        byte[] keyPrefix = (mallId + "DisplayRepository.*").getBytes(Charset.defaultCharset());
        Set<byte[]> keys = conn.keys(keyPrefix);
        for (byte[] key : keys) {
            String strkey = new String(key);
            if (strkey.startsWith(PREFIX_SPRING_SESSION)) {
                continue;
            }
            conn.del(key);
        }
    }
}
