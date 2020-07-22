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
public class UriAuthData {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/* grp */
	private String grp;
	
	/* site id */
	private String siteId;
	
	/* url */
	private String url;
	
	/* uri */
	private String uri;
	
	/* 접근가능url인지 판단(기존메소드) */
	private String accessMenuYn;
	
	/* 접근 ip */
	private String ip;
	
	/* Login Id */
	private String loginId;
}
