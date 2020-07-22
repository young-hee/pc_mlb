package com.plgrim.ncp.framework.cloud.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.MapSession;
import org.springframework.session.Session;

import java.util.Collections;
import java.util.Map;

/**
 * spring cache implementation for spring session repository
 *
 * @author charles <charles@plgrim.com>
 */
@Slf4j
public class IndexedCacheSessionRepository implements FindByIndexNameSessionRepository<ExpiringSession> {

    /**
     * If non-null, this value is used to override
     * {@link ExpiringSession#setMaxInactiveIntervalInSeconds(int)}.
     */
    private Integer defaultMaxInactiveInterval;
    
    @Autowired
    private Cache sessionCache;
    
    /**
     * If non-null, this value is used to override
     * {@link ExpiringSession#setMaxInactiveIntervalInSeconds(int)}.
     * @param defaultMaxInactiveInterval the number of seconds that the {@link Session}
     * should be kept alive between client requests.
     */
    public void setDefaultMaxInactiveInterval(int defaultMaxInactiveInterval) {
        this.defaultMaxInactiveInterval = Integer.valueOf(defaultMaxInactiveInterval);
    }
    
    /**
     * @see org.springframework.session.SessionRepository#createSession()
     */
    @Override
    public ExpiringSession createSession() {
        ExpiringSession result = new MapSession();
        if (this.defaultMaxInactiveInterval != null) {
            result.setMaxInactiveIntervalInSeconds(this.defaultMaxInactiveInterval);
        }
        return result;
    }

    /**
     * @see org.springframework.session.SessionRepository#save(org.springframework.session.Session)
     */
    @Override
    public void save(ExpiringSession session) {
        sessionCache.put(session.getId(), session);
    }

    /**
     * @see org.springframework.session.SessionRepository#getSession(java.lang.String)
     */
    @Override
    public ExpiringSession getSession(String id) {
        ExpiringSession session = sessionCache.get(id, ExpiringSession.class);
        if (session == null) {
            return null;
        }
        if (session.isExpired()) {
            delete(session.getId());
            return null;
        }
        return session;
    }

    /**
     * @see org.springframework.session.SessionRepository#delete(java.lang.String)
     */
    @Override
    public void delete(String id) {
        sessionCache.evict(id);        
    }

    /**
     * @see org.springframework.session.FindByIndexNameSessionRepository#findByIndexNameAndIndexValue(java.lang.String, java.lang.String)
     */
    @Override
    public Map<String, ExpiringSession> findByIndexNameAndIndexValue(String indexName, String indexValue) {
        if (log.isDebugEnabled()) {
            log.debug("findByIndexNameAndIndexValue: indexName[{}], indexValue[{}]", indexName, indexValue);
        }
        
        return Collections.emptyMap();
    }
}
