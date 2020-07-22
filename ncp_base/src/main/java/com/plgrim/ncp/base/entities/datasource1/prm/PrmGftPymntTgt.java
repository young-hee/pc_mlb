/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.prm;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 프로모션 사은품 지급 대상
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("prmGftPymntTgt")
public class PrmGftPymntTgt extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 주문 번호	 
	 */
	private String ordNo;


	/**
	 * 프로모션 번호	 
	 */
	private String prmNo;


	/**
	 * 제휴 주문 여부	 
	 */
	private String affOrdYn;

	
}