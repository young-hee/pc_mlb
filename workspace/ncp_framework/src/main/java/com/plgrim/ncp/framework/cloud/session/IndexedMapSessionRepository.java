package com.plgrim.ncp.framework.cloud.session;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Map;

import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.MapSessionRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * in memory map implementation for spring session repository
 *
 * @author charles <charles@plgrim.com>
 */
@Slf4j
public class IndexedMapSessionRepository extends MapSessionRepository implements FindByIndexNameSessionRepository<ExpiringSession> {
	/**
     * @see org.springframework.session.FindByIndexNameSessionRepository#findByIndexNameAndIndexValue(java.lang.String, java.lang.String)
     */
    @Override
    public Map<String, ExpiringSession> findByIndexNameAndIndexValue(String indexName, String indexValue) {
        if (log.isDebugEnabled()) {
            log.debug("findByIndexNameAndIndexValue: indexName[{}], indexValue[{}]", indexName, indexValue);
        }
        
        log.info("called findByIndexNameAndIndexValue: indexName[{}], indexValue[{}]", indexName, indexValue);
        return Collections.emptyMap();
    }

    /**
     * After test session object serialized, call super.save(session)
     * 
     * @see org.springframework.session.MapSessionRepository#save(org.springframework.session.ExpiringSession)
     */
    @Override
    public void save(ExpiringSession session) {
        try {
            new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(session);
        } catch (IOException e) {
            throw new RuntimeException("Error occured during serialization test of session object:"+e.getMessage(), e);
        }
        
        super.save(session);
    }
}
