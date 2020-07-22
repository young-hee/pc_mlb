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
 * @since       2015. 3. 11       
 */
package com.plgrim.ncp.framework.data;

import java.net.URLEncoder;

import com.plgrim.ncp.framework.data.mobile.MobileDevice;

import lombok.Data;

/**
 * 시스템의 PK 정보 오브젝트.
 * 모든 Component, Repository, Service, Controller에서
 * 해당 오브젝트를 필수 오브젝트로 전달 한다.
 * 
 * <p>
 * 
 *
 * @author tktaeki.kim
 * @since 2015. 3. 11
 */
@Data
public class SystemPK {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/*몰 아이디*/
	String mall;
	
	/*언어 코드*/
	String lang;
	
	/*디바이스 코드*/
	String device;

	String deviceType; // NORMAL, TABLET, MOBILE

	String devicePlatform; // IOS, ANDROID, UNKNOWN


	/* 사이트 */
	String site;
	
	/*앱*/
	String app;

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
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
