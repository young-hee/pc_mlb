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
import com.plgrim.ncp.base.entities.datasource1.cso.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberGoodsQnaGridDTO extends AbstractDTO {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 상품 문의 일련번호
     */
    private java.lang.Long godInqSn;
    /**
     * 디자인 그룹 번호
     ㅁ. 자사의 경우 <상품.ERP 상품 번호>에서  마지막 색상 부분을 제외한 번호

     ㅁ. 입점사는 <상품.ERP 상품 번호>와 동일하게 처리
     */
    private String dsgnGrpNo;
    /**
     * 상품 번호
     상품 유형 1자리 || 업체 코드 3자리 || YYMMDD || 5자리 Cycle Sequence
     */
    private String godNo;
    /**
     * 문의 유형 코드
     ㅁ. 고객 상담 문의에 대한 형태를 관리함

     ㅁ. 문의 유형 : INQ_TP
     >. 상품문의 : GOD_INQ
     >. 취소/교환/반품 : CNCL_EXCHG_RTGOD
     >. 배송문의 : DLV_INQ
     >. 아웃바운드 : OUTBND
     >. 주문/결제 : ORD_SANCTN
     >. 회원 : MBR
     >. 포인트/이벤트/쿠폰/마일리지 : PNT_EVT_CPN_MILE
     >. 모바일앱이용 : MOBILE_APP_USE
     >. 매장구매후문의/매장문의 : SHOP_PCH_SHOP_INQ
     >. A/S : AS
     >. 이용불편/기타 : USE_INCONV_ETC
     */
    private String inqTpCd;
    /**
     * 회원 번호
     ㅁ. 회원 가입시 부여되는 고유한 관리 번호
     >. MB || YYYYMMDD || 7자리 Cycle
     */
    private String mbrNo;
    /**
     * 문의 고객 명
     ㅁ. 삼담을 요청한 고객의 성명
     */
    private String inqCstmrNm;
    /**
     * 문의 내용
     ㅁ. 고객의 질문의 주요 내용을 기술한다.

     */
    private String inqCont;
    /**
     * 문의 일시
     ㅁ. 상담 고객이 상담을 등록한 시점을 의미하며, 등록된 시점의 시점의 초단위까지 관리 한다.
     >. YYYYMMDD HH24:MI:SS
     (ORACLE 기준 Format)

     */
    private java.util.Date inqDt;
    /**
     * 비밀글 여부
     ㅁ. 고객 상담의 내용에 대해 외부에 노출은 되나, 그 내용은 고객 당사자와 상담 직원만이 조회 할 수 있음

     ㅁ. 비밀글 여부
     >. 비밀글 : Y
     >. 일반글 : N
     */
    private String secretsntncYn;
    /**
     * 답변 상태 코드
     ㅁ. 고객의 상담에 대한 답변 상태를 의미 함.

     ㅁ. 답변 상태 : ANS_STAT
     >. 답변 전 : ANS_BF
     >. 답변 대기 : ANS_WAIT
     >. 답변 완료 : ANS_COMPT
     */
    private String ansStatCd;
    /**
     * 조회 횟수
     ㅁ. 고객의 상담건에 대해 조회한 횟수를 의미 함.

     */
    private java.lang.Integer inqireCount;
    /**
     * 처리 완료 일시
     ㅁ. 문의에 대해 완료한 일시를 의미 함
     >. YYYYMMDD HH24:MI:SS
     (ORACLE 기준 Format)
     */
    private java.util.Date prcsComptDt;
    /**
     * 삭제 여부
     */
    private String deleteYn;
    /**
     * 고객 이메일
     ㅁ. 상담을 요청한 고객의 이메일 주소를 의미 함.
     */
    private String cstmrEmail;
    /**
     * 고객 이메일 수신 여부
     ㅁ. 상담을 신청한 고객의 이메일 수신 여부를 의미 함.

     ㅁ. 고객 이메일 수신 여부
     >. 수신 : Y
     >. 수신거절 : N

     */
    private String cstmrEmailRecptnYn;
    /**
     * 언어 코드
     ㅁ. 코드 테이블 규칙에 따라 대문자를 사용한 ISO 639 표준에 따른다

     ㅁ. 언어 : LANG
     >. 한국어 : KOR
     >. 중국어 : CHI
     >. 영어 : ENG
     >. 일본어 : JPN
     */
    private String langCd;
    /**
     * 몰 ID
     */
    private String mallId;
    /**
     * 디바이스 코드
     ㅁ. 디바이스를 정의

     ㅁ. 디바이스 : DVC
     >. PC : PC
     >. 모바일 웹 : MOBILE_WEB
     >. 모바일 앱 : MOBILE_APP
     */
    private String dvcCd;
    /**
     * 약관 항목 동의 여부
     ㅁ. 개인정보 이용 관련 약관 항목별 동의여부를 관리
     >. 이용 동의 : Y
     >. 이용 동의 안함 : N

     ㅁ. 개인정보 이용 동의는 법적 문제가 야기될수 있는 사안이므로 기본 값은 N로 설정 되도록 한다.
     */
    private String stplatIemAgrYn;
    /**
     * 약관 코드
     ㅁ. 약관 : STPLAT
     >. 온라인사이트 이용약관 : ONLINE_SITE_USEF_STPLAT
     >. 개인정보 보호를 위한 이용자 동의사항 : PSNL_INFO_PRTC_USEF_AGR_MATTER
     >. 개인정보의 취급위탁 동의 : PSNL_INFO_TRTMNT_CNSGN_AGR
     >. 홍보 마케팅 목적 개인정보 수집 및 이용 동의 : MARKT_PSNL_INFO_COLCT_USEF_AGR
     >. 개인정보 제 3자 제공 안내 : PSNL_INFO_THPR_OFFER_DETL
     >. 멤버쉽이용약관 : MBSH_USEF_STPLAT

     */
    private String stplatCd;
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


}
