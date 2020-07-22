package com.plgrim.ncp.framework.filter.cache;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Data
public class ContentsCache {
	public static final String CACHE_KEY = "anonymousContentsCache";

	@CachePut(value = CACHE_KEY, key = "#key")
	public CacheItem putCache(String key, CacheItem contents) {
		return contents;
	}

	/**
	 * 캐시된 컨텐츠가 있다면 Spring Cache Abstraction 에 의해 반환된다
	 * 
	 * @param key
	 * @return
	 */
	@Cacheable(CACHE_KEY)
	public CacheItem getCache(String key) {
		return null;
	}
}
