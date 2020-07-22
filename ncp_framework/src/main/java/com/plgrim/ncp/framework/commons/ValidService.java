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
 * @since       2015. 3. 25       
 */

package com.plgrim.ncp.framework.commons;

import java.util.UUID;

import org.apache.commons.validator.routines.IntegerValidator;
import org.springframework.stereotype.Service;

/**
 * 스트링 오브젝트 처리를 위한 유틸리티 서비스
 * 
 * <p>
 *
 * @author tktaeki.kim
 * @since 2015. 2. 25
 */
@Service
public class ValidService {

	public static boolean isEmptyOrWhitespace(String value){
		if(value==null || value.trim().length() == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isEmptyObject(Object value){
		if(value==null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isUUID(String str){
		
		boolean isValue = false;
		
		try{
			UUID.fromString(str);
			isValue = true;
		}catch(IllegalArgumentException e){}
		
		return isValue;
	}
	
	public static boolean isRange(int value,int min , int max){
		
		boolean isValue = false;
		
		try{
			isValue = IntegerValidator.getInstance().isInRange(value, min, max);
			
		}catch(IllegalArgumentException e){}
		
		return isValue;
	}
	
}
