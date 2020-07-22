/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 6. 18       
 */
package com.plgrim.ncp.biz.member.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Instantiates a new member bo result. 
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MemberBenefitPymntHistResult extends AbstractResult {

    /** UID. */ 
	private static final long serialVersionUID = 1L;
	
	/**
	 * 혜택 일련번호	 
	 */
	private java.lang.Long bnefSn;	
	
	/**
	 * 혜택 구분 코드
	 */
	private String bnefSectCd;
	
	/**
	 * 혜택 상세 구분 코드	 
	 */
	private String bnefDetailSectCd;	
	
	private String mbrNo;
		
	private String bnefPymntTurn;
	
	private java.util.Date pymntDt;
	
	private String bnefPymntSuccesYn;
	
	private String bnefPymntFailrResnCont;
	
	private String mbrId;
	
	private String mbrNm;
	
	private String erpCstmrNo;
	
	private String bnefTpCd;
	
	private String bnefTpNm;
	
	/**
	 * 혜택 상세 순번	 
	 */
	private java.lang.Integer bnefDetailTurn;	
	
    
}

