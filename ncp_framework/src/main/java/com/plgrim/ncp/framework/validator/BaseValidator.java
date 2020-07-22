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
 * beyondj2ee			2015.02.09        
 */
package com.plgrim.ncp.framework.validator;

import org.springframework.validation.Errors;

/**
 * [클래스 설명]
 * <pre>
 * 	모든 validation 은 BaseValidator
 *  을 implements 받는다.
 *  
 * </pre>
 * 
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author sewoon1.choi
 * @since 2015. 8. 12.
 */
public interface BaseValidator{

	public boolean supports(Class<?> clazz);

	public void validate(Object target, Errors errors);
	
	public void validate(Object target,Object param, Errors errors);
}
