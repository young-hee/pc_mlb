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
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrExtend;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Instantiates a new member bo result.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class PurpleCoinMemberBoResult extends AbstractResult {
    /** UID. */
    private static final long serialVersionUID = -3150974890518029171L;

    /**  회원 Entity. */
    private MbrExtend mbr;
    
    /**  회원 상태 코드명. */
    private String mbrStatNm;             

    /**  회원 유형 코드명. */
    private String mbrTpNm;               

    /**  회원 속성 코드명. */
    private String mbrAtrbNm;             

    /**  가입 언어 코드명. */
    private String joinLangNm;

    /**  로그 발생 일자. */
    private Date logOccurDate;

    /**  회원 인증 일자(통합회원 가입일). */
    private Date mbrCrtfcDate;

    /** 총 적립코인 */
    private java.math.BigDecimal totalAccmlAmt;

    /** 총 적립코인 */
    private String totalAccmlAmtStr;

    /** 총 사용 코인 */
    private java.math.BigDecimal totalUseAmt;

    /** 총 소멸 코인 */
    private java.math.BigDecimal totalExtshAmt;

    /** 총 소멸 코인 */
    private String totalExtshAmtStr;

    /** 당월 적립코인 */
    private java.math.BigDecimal curMonthAccmlAmt;

    /** 당월 소멸코인 */
    private java.math.BigDecimal curMonthExtshAmt;

    /** 최초 적립일 */
    private java.util.Date firstAccmlDt;

    /** 가용 코인 */
    private java.math.BigDecimal usefulAmt;

    /** 총사용코인 */
    private String totalUseAmtStr;

}
