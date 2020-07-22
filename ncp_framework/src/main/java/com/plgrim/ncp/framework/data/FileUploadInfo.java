/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tam.kwon
 * @since       2015. 4. 6       
 */
package com.plgrim.ncp.framework.data;

import lombok.Data;


/**
 * FileUpload 전용 Result.
 */
@Data
public class FileUploadInfo {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/* system filename 정보 */
	String fileName;
	
	
	/* system file path 정보 */
	String filePath;
	
	/* file 용량 정보 */
	long fileSize;
	
	/* 사용자 등록 파일명 (확장자 포함) */
	String orgFileName;
	
	/* 파일확장자 */
	String extension;

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
