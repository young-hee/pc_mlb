/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shhenry.choi
 * @since       2015. 3. 18       
 */
package com.plgrim.ncp.biz.callcenter.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGlan;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class FastUrlGridDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * 바로가기 분류 코드
	 ㅁ. 바로가기 분류 : GLAN_CLFC
	 >. 몰/브랜드그룹 : MALL_BRND_GRP
	 >. 국내PG사 : OVSEA_PG_COM
	 >. 해외PG사 : DMSTC_PG_COM
	 >. 기타 : ETC

	 */
	private String glanClfcCd;

	/**
	 * 변경 전 바로가기 분류 코드
	 */
	private String glanClfcCdKey;
	/**
	 * 바로가기 명
	 */
	private String glanNm;
	/**
	 * 변경 전 바로가기 명
	 */
	private String glanNmKey;

	/**
	 * 바로가기 URL
	 */
	private String glanUrl;
	/**
	 * 정렬 순서
	 */
	private java.lang.Integer sortSeq;
	/**
	 * 사용 여부
	 */
	private String useYn;
	/**
	 * 등록자 ID
	 */
	private String regtrId;
	/**
	 * 등록 일시
	 */
	private java.util.Date regDt;
	/**
	 * 수정자 ID
	 수정한 관리자 번호
	 */
	private String udterId;
	/**
	 * 수정 일시
	 */
	private java.util.Date udtDt;



	
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
