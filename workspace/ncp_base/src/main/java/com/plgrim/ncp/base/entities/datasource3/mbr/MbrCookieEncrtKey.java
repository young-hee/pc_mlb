/**
 * @author : Generator(Generator)
 * @date : 2018-04-13
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource3.mbr;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 회원 쿠키 암호화 키
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mbrCookieEncrtKey")
public class MbrCookieEncrtKey extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 암호화 개인 키	 
	 */
	private String encrtPsnlKey;

	
}