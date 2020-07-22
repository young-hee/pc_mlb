/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 4. 24       
 */
package com.plgrim.ncp.biz.vendor.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcHist;

/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author tktaeki.kim
 * @since 2015. 4. 14
 */

/**
 * Instantiates a new vendor list result.
 */

/**
 * Instantiates a new vendor list result.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ComDmstcDlvCstPlcResult extends AbstractResult {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
    /**
	 * serialVersionUID
	 */
    private static final long serialVersionUID = 2097822591560688187L;

	/**
     * 업체 Entity
     */
    private Com com;
    
    /**
     * comDmstcDlvCstPlc
     */
    private ComDmstcDlvCstPlc comDmstcDlvCstPlc;
    /**
     * comDmstcDlvCstPlcHist
     */
    private ComDmstcDlvCstPlcHist comDmstcDlvCstPlcHist;
    
    /* 배송담당자 정보 */
    /**
     * chrgerTurn
     */
    private String chrgerTurn               ;
    /**
     * chrgerNm
     */
    private String chrgerNm                 ;
    /**
     * chrgerEmail
     */
    private String chrgerEmail              ;
    /**
     * chrgerTelNationNo
     */
    private String chrgerTelNationNo        ;
    /**
     * chrgerTelAreaNo
     */
    private String chrgerTelAreaNo          ;
    /**
     * chrgerTelTlofNo
     */
    private String chrgerTelTlofNo          ;
    /**
     * chrgerTelTlofWthnNo
     */
    private String chrgerTelTlofWthnNo      ;
    /**
     * chrgerMobilNationNo
     */
    private String chrgerMobilNationNo      ;
    /**
     * chrgerMobilAreaNo
     */
    private String chrgerMobilAreaNo        ;
    /**
     * chrgerMobilTlofNo
     */
    private String chrgerMobilTlofNo        ;
    /**
     * chrgerMobilTlofWthnNo
     */
    private String chrgerMobilTlofWthnNo    ;
    /**
     * chrgerTelNo
     */
    private String chrgerTelNo;
    /**
     * chrgerMobilNo
     */
    private String chrgerMobilNo;
    
    
    /* 반품담당자 정보 */
    /**
     * retChrgerTurn
     */
    private String retChrgerTurn            ;
    /**
     * retChrgerNm
     */
    private String retChrgerNm              ;
    /**
     * retChrgerEmail
     */
    private String retChrgerEmail           ;
    /**
     * retChrgerTelNationNo
     */
    private String retChrgerTelNationNo     ;
    /**
     * retChrgerTelAreaNo
     */
    private String retChrgerTelAreaNo       ;
    /**
     * retChrgerTelTlofNo
     */
    private String retChrgerTelTlofNo       ;
    /**
     * retChrgerTelTlofWthnNo
     */
    private String retChrgerTelTlofWthnNo   ;
    /**
     * retChrgerMobilNationNo
     */
    private String retChrgerMobilNationNo   ;
    /**
     * retChrgerMobilAreaNo
     */
    private String retChrgerMobilAreaNo     ;
    /**
     * retChrgerMobilTlofNo
     */
    private String retChrgerMobilTlofNo     ;
    /**
     * retChrgerMobilTlofWthnNo
     */
    private String retChrgerMobilTlofWthnNo ;
    /**
     * retChrgerTelNo
     */
    private String retChrgerTelNo;
    /**
     * retChrgerMobilNo
     */
    private String retChrgerMobilNo;
    /**
	 * 등록자명
	 */
	private String regterNm;
	
	/**
	 * 수정자명
	 */
	private String udterNm;
	
	/**
	 * 등록일(YYYY-MM-DD HH24:MI)
	 */
	private String viewRegDt;
	
	/**
	 * 수정일(YYYY-MM-DD HH24:MI)
	 */
	private String viewUdtDt;
	/**
	 * 배송비 컬럼 - 무료배송은 '무료배송' 노출
	 */
	private String dmstcDlvCstList;
	/**
	 * 배송수단명
	 */
	private String dlvMnNm;
	/**
	 * 배송사명
	 */
	private String dlvComNm;

    private String ovseaDlvdmstcDlvCstPlcYn;
    
	/**
	 * 배송사 주소
	 */
	private String dlvComUrl;
	/**
	 * 글로벌 배송 방법 설명
	 */
	private String dlvMthdDscrEng;
	/**
	 * 중국 배송 방법 설명
	 */
	private String dlvMthdDscrChi;
	/**
	 * 모바일 글로벌 배송 방법 설명
	 */
	private String mobileDlvMthdDscrEng;
	/**
	 * 모바일 중국 배송 방법 설명
	 */
	private String mobileDlvMthdDscrChi;	
	
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
