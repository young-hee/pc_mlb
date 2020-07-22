/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 *  beyondj2ee			2015.02.10                     
 */
package com.plgrim.ncp.framework.mvc;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

import com.plgrim.ncp.framework.utils.XSSUtil;

/**
 * The Class StringTrimConvert.
 */
public class StringTrimConvert implements Converter<String, String> {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public String convert(String source) {
		String str=null;
		if(source != null){
			str = XSSUtil.sanitize(source);
		}
		return StringUtils.trimToEmpty(str);
	}
	
}