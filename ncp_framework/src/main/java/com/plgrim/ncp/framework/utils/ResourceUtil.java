/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 3. 9       
 */
package com.plgrim.ncp.framework.utils;

import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import com.google.common.collect.Lists;

/**
 * 리소스 파일에 대한 정보를 조회(classpath, WEB-INF) 하는 유틸리티
 * 
 * <p>
 * 
 *
 * @author tktaeki.kim
 * @since 2015. 3. 9
 */
@Slf4j
public class ResourceUtil {

	public static List<String> getResource(String... basenames)  {

		try {
			List<String> paths = Lists.newArrayList();
			
			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
			
			
			for (String basename : basenames) {
				
				Resource[] resources = resourcePatternResolver.getResources(basename);
				
				log.info("#########" + JsonUtil.marshallingJsonWithPretty(resources));
			}
			
			return null;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
