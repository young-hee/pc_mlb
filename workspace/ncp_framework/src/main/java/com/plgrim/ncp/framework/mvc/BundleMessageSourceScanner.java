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
 * @since       2015. 3. 13       
 */
package com.plgrim.ncp.framework.mvc;

import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.context.support.ServletContextResourcePatternResolver;

import com.plgrim.ncp.framework.commons.CollectionService;
import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.StringService;
import com.google.common.collect.Sets;

/**
 * WEB-INF 밑에 리소스를 검색 후 리스트 형태로 리턴하는 팩토리 빈
 * 
 * <p>
 * 
 * @author tktaeki.kim
 * @since 2015. 3. 4
 */
@Data
@Slf4j
public class BundleMessageSourceScanner {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/* 메세지 파일 확장자 명*/
	final static String INCLUDE_EXTENSION = ".properties";
	
	/* 삭제할 시작 문자열*/
	final static String REMOVE_START_CHAR = "[";
	
	/* 삭제할 끝 문자열*/
	final static String REMOVE_END_CHAR = "]";
	
	/* 메세지 타입 검사 문자열*/
	final static String LAST_INDEX_CHAR = "_";
	
	final static String REMOVE_PATH_CHAR = "/";

	static String messageRootDirectory = "/META-INF";

	@Autowired
	private ApplicationContext applicationContext;

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */
	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * 지정된 Ant 스타일의 파일패턴을 분석 해서 해당 파일들을 리스트 형태로 리턴 한다. <br/>
	 * getResources는 팩토리빈 생성 메서드.
	 * 
	 * <p/>
	 * 
	 * getResources(/WEB-INF/messages/**);
	 * 
	 * 
	 *  WEB-INF/messages/Message
        WEB-INF/messages/order/message_a
		WEB-INF/messages/product/message"
	 * 
	 *
	 * @param locations 리소스 경로 배열
	 * @return List 모든 리소스 경로
	 * @since 2015. 3. 13
	 */
	public List<String> getResources(String wildcardBase) throws Exception {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		Resource[] resources = resolver.getResources(wildcardBase);

		String includeExtension = INCLUDE_EXTENSION;
		Set<String> locationSet = Sets.newHashSet();

		for (Resource resource : resources) {

			String location = resource.getDescription();

			if (StringUtils.contains(location, INCLUDE_EXTENSION)) {

				location = StringUtils.replace(location,"\\" , "/" );
				int index =  StringUtils.indexOf(location, messageRootDirectory);

				if (index > 0) {

					//"_{언어셋}.properties를 삭제 한다."
					location = location.substring(index);
					int subIndex = 0;
					location = StringUtils.substringBefore(location, LAST_INDEX_CHAR);
					location = location.substring(1);
					location = location.trim();
					locationSet.add(location);
				}
			}
		}

		StringBuffer message = new StringBuffer();
		message.append("\n\n######################################################\n")
				.append("# Message Resource Information \n")
				.append("######################################################\n");
		log.info(message.toString());
		for (String path : locationSet) {
			log.info(path);
		}

		message.setLength(0);
		message.append("\n\n######################################################\n")
				.append("# URL Mapping Information \n")
				.append("######################################################\n");

		log.info(message.toString());

		return CollectionService.setToList(locationSet);
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	public static void main(String[] vars) throws Exception {


		String[] locations = {
				"file [C:\\iworkspaces\\ncp_web_tutorial\\target\\ncp_web_tutorial\\WEB-INF\\classes\\META-INF\\messages\\member\\MemberController_en.properties]",
				"file [C:\\iworkspaces\\ncp_web_tutorial\\target\\ncp_web_tutorial\\WEB-INF\\classes\\META-INF\\messages\\member\\MemberController_ko.properties]",
				"file [C:\\iworkspaces\\ncp_web_tutorial\\target\\ncp_web_tutorial\\WEB-INF\\classes\\META-INF\\messages\\member\\MemberController_zh.properties]",
				"file [C:\\iworkspaces\\ncp_web_tutorial\\target\\ncp_web_tutorial\\WEB-INF\\classes\\META-INF\\messages\\member]",
				"URL [jar:file:/C:/iworkspaces/ncp_web_tutorial/target/ncp_web_tutorial/WEB-INF/lib/ncp_cmp-0.0.1-SNAPSHOT.jar!/META-INF/messages/]",
				"URL [jar:file:/C:/iworkspaces/ncp_web_tutorial/target/ncp_web_tutorial/WEB-INF/lib/ncp_cmp-0.0.1-SNAPSHOT.jar!/META-INF/messages/exception_en.properties]",
				"URL [jar:file:/C:/iworkspaces/ncp_web_tutorial/target/ncp_web_tutorial/WEB-INF/lib/ncp_cmp-0.0.1-SNAPSHOT.jar!/META-INF/messages/exception_ko.properties]",
				"URL [jar:file:/C:/iworkspaces/ncp_web_tutorial/target/ncp_web_tutorial/WEB-INF/lib/ncp_cmp-0.0.1-SNAPSHOT.jar!/META-INF/messages/exception_zh.properties]",
			 };



		Set<String> locationSet = Sets.newHashSet();
		for(String location : locations) {

			if (StringUtils.contains(location, INCLUDE_EXTENSION)) {

				location = StringUtils.replace(location,"\\" , "/" );
				int index =  StringUtils.indexOf(location, messageRootDirectory);

				if (index > 0) {

					//"_{언어셋}.properties를 삭제 한다."
					location = location.substring(index);
					int subIndex = 0;
					location = StringUtils.substringBefore(location, LAST_INDEX_CHAR);
					location = location.substring(1);
					location = location.trim();
					locationSet.add(location);
				}
			}
		}

		log.info(JsonService.marshallingJsonWithPretty(locationSet));

	}

}
