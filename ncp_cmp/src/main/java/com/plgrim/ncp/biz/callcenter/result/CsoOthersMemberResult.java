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
 * @since       2015. 6. 17       
 */
package com.plgrim.ncp.biz.callcenter.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGodInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGodInqAns;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqAns;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqOrdGod;
import com.plgrim.ncp.base.entities.datasource1.pay.PayRfd;

/**
 * [회원에서 사용하는 CS 정보 조회 결과]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [환불]
 *   <li> [상품QNA]
 *   <li> [1:1문의]
 * </ul>.
 *
 * @author sy59.gim
 * @since 2015. 5. 15
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CsoOthersMemberResult extends AbstractResult {

	/** UID */
    private static final long serialVersionUID = 3559926488934861590L;
    
    /** 결제 환불 Entity. */
    private PayRfd payRfd; 
    
    /** 고객서비스 상품 문의 Entity */
    private CsoGodInq csoGodInq;
    
    /** 고객서비스 상품 문의 답변 Entity */
    private CsoGodInqAns csoGodInqAns; 
    
    /** 고객서비스 일대일 문의 Entity. */
    private CsoMtmInq csoMtmInq; 
    
    /** 고객서비스 일대일 문의 답변 Entity. */
    private CsoMtmInqAns csoMtmInqAns; 
    
    /** 고객서비스 일대일 문의 주문 상품 Entity. */
    private CsoMtmInqOrdGod csoMtmInqOrdGod; 
    
    /** 회원 번호. */
    private String mbrNo;
    
    /** 환불 상태 코드명. */
    private String rfdStatNm;
    
    /** 은행코드명. */
    private String rfdBnkNm; 
    
    /** 주문번호. */
    private String ordNo;
    
    /** 클레임 번호. */
    private String clmNo;
    
    /** 상품번호. */
    private String godNo;    
    
    /** 상품명. */
    private String godNm;
	
	/** 답변 상태 코드명. */
	private String ansStatNm;
	
	/** 문의 유형 코드명. */
	private String inqTpNm;
	
	/** 관리자명. */
	private String adminNm;

    /**  회원 등급. */
    private String onlneGrdNm;
}
