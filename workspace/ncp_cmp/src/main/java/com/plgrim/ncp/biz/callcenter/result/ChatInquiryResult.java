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
package com.plgrim.ncp.biz.callcenter.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * PLGRIM톡 채팅상담 리스트 조회
 *
 * @author 
 * @since 2017. 4. 12
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ChatInquiryResult extends AbstractResult {
	/**
	 *
	 */
    private static final long serialVersionUID = 1L;


    private String chatInqSn; //상담번호
    private String chatInqTpCdNm; //문의유형

    private String chatInqSj; //제목

    private String mbrId; //회원 ID
    private String mbrNm; //회원명
    private String mbrNo; //회원번호
    private String inqCstmrNm; //고객이름

    private String chatTicketId; //채팅 티켓아이디
    private String chatTransYn; //채팅 이관여부

    private String ordNo; //주문번호
    private String godNo; //상품번호(온라인 품번)

    private String cnsltDt; //상담일시
    private String cstmrClmCd; //컴플레인 여부(고객 클레임코드)
    private String cstmrClmCdNm; //고객 클레임코드이름

    private String ansAdminNm; //상담사
    private String cnsltStatCdNm; //처리상태
    private String prcsComptDt; //처리완료 일시

    private String ansEvlCdNm;//만족도 이름
    private String ansEvlCd;//만족도


    private String inPathSectCdNm;//인입경로
    private String inMediaTpCdNm;//인입매체
    private String inScrinSectCdNm;//인입화면

    private String mallNm;//몰구분
    private String langCd; //언어코드
    private String langCdNm;//언어구분

    private String regDt; //등록일시
    private String loginId;  //채팅상세보기 agentId

    private String chatTpCdNm; // 시나리오 채팅 구분

    /**  회원 등급. */
    private String onlneGrdNm;
}
