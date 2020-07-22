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
 * @since       2015. 3. 24       
 */
package com.plgrim.ncp.framework.mvc;

import java.io.File;

import lombok.Data;

/**
 * 다운로드 전용 Value Object.
 * 
 * <p>
 * 
 *
 * @author tktaeki.kim
 * @since 2015. 3. 24
 */
@Data
public class DownloadObject {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/* 다운로드 파일 이름 (확장자 포함) 예) a.txt*/
	String displayFileName;
	
	/* 다운로드 대상 파일*/
	File downloadFile;
	
	/**
	 * cloud filesystem을 사용하는 경우 full path
	 */
	String cloudFilePath;

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
