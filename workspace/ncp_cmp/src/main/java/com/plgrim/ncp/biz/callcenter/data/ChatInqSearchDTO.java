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
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  PLGRIM톡 채팅상담 리스트(검색) 조회
 *
 * @author 
 * @since 2017. 4. 12
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ChatInqSearchDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */


	/**
	 * 
	 */
    //private static final long serialVersionUID = -4425784568552289540L;

    // 우선검색
    private String chatInqSn;           // 상담번호

    private String chatTicketId;        // 티켓번호

    private String ordNo;               // 주문번호

    private String mbrNo;               // 회원번호

    // 우선검색 종료

    private String mobilNo;             // 전화번호

    private String godNo;               // 상품품번

    private String langCd;              // 언어

    private String mallId;              // 몰

    private String cnsltStatCd;         // 처리상태

    private String searchDateType;		// 날짜 검색 타입

    private String chatStartDt;		    // 날짜 검색 시작일

    private String chatEndDt;		    // 날짜 검색 종료일

    private String inqTpCd;			    // 문의유형

    private String inqDetailTpCd;		// 문의 상세 유형

    private String inPathSectCd;        // 인입경로

    private String inMediaTpCd;         // 인입매체

    private String inScrinSectCd;       // 인입 화면 메뉴

    private String ansEvlCd;		    // 만족도

    private String cnsltChrgerId;	    // 상담사id

    private String cnsltChrgerNm;	    // 상담사 이름

    private String inqSj;	            // 문의 제목

    private String senarioChatYn;       // 시나리오 채팅여부

    private String onlneGrdCd;          // 회원 등급
}
