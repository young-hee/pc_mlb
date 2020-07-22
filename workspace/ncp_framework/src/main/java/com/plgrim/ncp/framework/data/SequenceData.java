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
 * @since       2015. 3. 5       
 */
package com.plgrim.ncp.framework.data;

import java.util.Map;

import lombok.Data;

/**
 * 시퀀스 조회를 위한 전용 데이터 오브젝트
 * 
 * <p>
 * 
 *
 * @author tktaeki.kim
 * @since 2015. 3. 5
 */
@Data
public class SequenceData {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/* 시퀀스 명 */
	private String sequenceName;
	
	/* 번호 코드를 위한 prefix */
	private String prefix;
	
	/* 순번 테이블 */
	private String orderTable;
	
	/* 순번 컬럼 */
	private String orderColumn;
	
	/* 순번 조건들 (where)*/
	private Map<String, ?> condition;
	

}
