/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 상품 태그 예약
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godTagResve")
public class GodTagResve extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 태그 예약 일련번호	 
	 */
	private java.lang.Long tagResveSn;


	/**
	 * 예약 시작 일시	 
	 */
	private java.util.Date resveBegDt;


	/**
	 * 예약 종료 일시	 
	 */
	private java.util.Date resveEndDt;


	/**
	 * 태그 명	 
	 */
	private String tagNm;


	/**
	 * 사용 여부	 
	 */
	private String useYn;


	/**
	 * 등록자 ID
	 * 등록한 관리자 번호	 
	 */
	private String regtrId;


	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;


	/**
	 * 수정자 ID
	 * 수정한 관리자 번호	 
	 */
	private String udterId;


	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;

	
}