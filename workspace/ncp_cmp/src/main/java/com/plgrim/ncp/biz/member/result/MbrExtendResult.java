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
 * @since       2015. 6. 22       
 */
package com.plgrim.ncp.biz.member.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;

/**
 * 회원
 * 
 * @author sy59.gim
 * @since 2015. 6. 22
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("MbrExtendResult")
public class MbrExtendResult extends Mbr {

	/** The Constant serialVersionUID. */
    private static final long serialVersionUID = 266556246105397073L;
    
    /** 휴대폰 번호. */
    private String mobilNo;

    /** 전화 번호. */
    private String telNo;

    /** 휴대폰 번호. */
    private String hiddenMobilNo;

    /** 회원 아이디. */
    private String hiddenMbrId;

    /** 쿠폰 개수. */
    private long cpnCnt;

    /** 사용가능한 쿠폰 개수. */
    private long usableCpnCnt;

    /** 주문 개수. */
    private long ordCnt;

    /** 클레임 개수. */
    private long clmCnt;

    /** 위시리스트 개수. */
    private long wishCnt;

    /** 멤버십포인트. */
    private long reserve;

    /** 이벤트포인트. */
    private long eventReserve;

    /** P포인트. */
    private java.math.BigDecimal webPntReserve;

    /** 회원 유형 코드 이름 */
    private String mbrTpNm;

    /** 회원 속성 코드 이름 */
    private String mbrAtrbNm;

    /** 회원 상태 코드 이름 */
    private String mbrStatNm;

    /** 회원 성별 코드 이름 */
    private String mbrSexSectNm;

    /** 환불 계좌 은행 이름 */
    private String rfdBnkAcctNm;

    /** 환불 계좌 번호 */
    private String rfdBnkAcctNo;

    /** ERP 고객번호 */
    private String erpCstmrNo;
    
    /** mbr 이메일 */
    private String mbrEmail;
    
    //20160503_주동민_sr#18801 [CSO내 제휴사 고객정보 마스킹 처리요청]
    private String orgTelNo;
	private String orgMobilNo;
	private String orgMbrNm;
	private String orgMbrEmail;
	private String mbrTpCd;
	
	//2016-06-27
	private String reJoinYn;
	
	/* #31806  2016.12.06 eunsik10.kim  멤버십 제도 개선관련  */
	private String erpGrdCd;

    /**  회원 등급. */
    private String onlneGrdNm;

    /** 탈퇴 일자 */
    private String secsnDateStr;
}
