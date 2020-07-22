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
 * @since       2015. 3. 4       
 */
package com.plgrim.ncp.base.entities;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.abstracts.AbstractEntity;
import com.plgrim.ncp.framework.page.PageParam;


/**
 * 레파지토리 지원을 위한 헬퍼 클래스.
 */
public class RepositoryHelper {
	// ~ Instance fields. ~~~~~~~~~~~~~~
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	// ~ Implementation Method. ~~~~~~~~
	// ~ Public Methods. ~~~~~~~~~~~~~~~
	
	/**
	 * 페이징을 위한  시작 row 번호, 마지막 row를 설정 한다.<br/>
	 * 엔티티일 경우
	 *
	 * @param <T> the generic type
	 * @param entity the entity
	 * @param pageParam the page param
	 * @return the t
	 * @since 2015. 2. 26
	 */
	public static <T extends AbstractEntity> void makePageEntityIndex(T entity, PageParam pageParam) {
		//페이지 인덱스 셋팅
		entity.setBeginIndex(pageParam.getBeginIndex());
		entity.setEndIndex(pageParam.getEndIndex());
	}
	
	/**
	 * 페이징을 위한  시작 row 번호, 마지막 row를 설정 한다.<br/>
	 * dto일 경우
	 * @param <T> the generic type
	 * @param entity the entity
	 * @param pageParam the page param
	 * @return the t
	 * @since 2015. 2. 26
	 */
	public static <T extends AbstractDTO> void makePageEntityIndex(T dto, PageParam pageParam) {
		//페이지 인덱스 셋팅
		dto.setBeginIndex(pageParam.getBeginIndex());
		dto.setEndIndex(pageParam.getEndIndex());
	}
	
	
	// ~ Private Methods. ~~~~~~~~~~~~~~
	// ~ Getter and Setter. ~~~~~~~~~~~~
}
