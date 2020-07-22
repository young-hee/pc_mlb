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
package com.plgrim.ncp.biz.member.result;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 회원 혜택 API Result 
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MemberBenefitApiResult extends AbstractResult {
	
	private static final long serialVersionUID = -185503650864134403L;

	/** 결과 코드 */
    private String resultCd;
    
    /** 결과 메세지 */
    private String resultMsg;
    
    /** 회원혜택 상세결과 리스트 */
    private List<MemberBenefitResult> memberBenefitResultList;
}
