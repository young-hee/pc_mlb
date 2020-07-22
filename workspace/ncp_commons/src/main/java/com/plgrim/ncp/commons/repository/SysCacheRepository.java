///* Copyright (c) 2013 plgrim, Inc.
// * All right reserved.
// * http://plgrim.com
// * This software is the confidential and proprietary information of plgrim
// * , Inc. You shall not disclose such Confidential Information and
// * shall use it only in accordance with the terms of the license agreement
// * you entered into with plgrim.
// *
// * Revision History
// * Author              Date                         Description
// * ------------------   --------------                  ------------------
// *
// */
//package com.plgrim.ncp.commons.repository;
//
//import org.apache.poi.ss.formula.functions.T;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.stereotype.Repository;
//
///**
// * 권한그룹 Repository
// * @author tam
// *
// */
//@Repository
//public class SysCacheRepository{
//
//	@Autowired
//	CacheManager cacheManager;
//
//
//	private Cache getCache(String cacheName) throws Exception{
//		return cacheManager.getCache(cacheName);
//	}
//
//	/**
//	 * cache 정보를 조회한다.
//	 * @param cacheName
//	 * @param selectKey
//	 * @param type
//	 * @return value
//	 * @throws Exception
//	 */
//	public Object selectSysCache ( String cacheName, Object selectKey,Class<T> type) throws Exception {
//		return getCache(cacheName).get(selectKey, type);
//	}
//
//	/**
//	 * cache Merge
//	 * @param cacheName
//	 * @param selectKey
//	 * @param value
//	 * @throws Exception
//	 */
//	public void mergeSysCache ( String cacheName, Object selectKey,Object value) throws Exception {
//		getCache(cacheName).put(selectKey, value);
//	}
//
//	/**
//	 * cache Delete
//	 * @param cacheName
//	 * @param selectKey
//	 * @throws Exception
//	 */
//	public void deleteSysCache ( String cacheName, Object selectKey) throws Exception {
//		getCache(cacheName).evict(selectKey);
//	}
//
//	/**
//	 * cache init
//	 * @param cacheName
//	 * @throws Exception
//	 */
//	public void initSysCache ( String cacheName) throws Exception {
//		getCache(cacheName).clear();
//	}
//}
//
//
//
//
//
