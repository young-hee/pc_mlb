/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.mbr;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 회원 개인정보 이용
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mbrPsnlInfoUsef")
public class MbrPsnlInfoUsef extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 개인정보 이용 일련번호
	 * 개인정보 이용 일련번호	 
	 */
	private java.lang.Long psnlInfoUsefSn;


	/**
	 * 이용 일시
	 * ㅁ. 개인정보를 이용한 일자를 의미하며, 이용한 시점의 초단위까지 관리 한다.
	 *    >. YYYYMMDD HH24:MI:SS
	 *       (ORACLE 기준 Format)	 
	 */
	private java.util.Date usefDt;


	/**
	 * 이용 구분 코드
	 * ㅁ. 개인정보 이용 구분 코드 : PSNL_INFO_USE_TP
	 *    >.개인정보이용현황 : PSNL_INFO_STTUS
	 *    >.개인정보 제3자 제공 내역 : PSNL_INFO_THPR_OFFER_DETL
	 *    >.개인정보 취급위탁 내역 : PSNL_INFO_TRTMNT_CNSGN_DETL	 
	 */
	private String usefSectCd;


	/**
	 * 이용 업무 코드
	 * ㅁ. 어떠한 업무를 위해 개인정보를 활용했는지 관리 함.
	 * 
	 * ㅁ. 이용 업무 코드
	 *    >.개인정보이용현황 : PSNL_INFO_STTUS
	 *       >>. 1:1문의등록 :  MTM_INQ_REG
	 *       >>. 배송정보등록 : DLVSP_INFO_REG
	 *       >>. 배송정보수정 : DLVSP_INFO_UDT
	 *       >>. 결제정보 : PAY_INFO
	 *       >>. 조회 : INQIRE
	 *       >>. 삭제 : DELETE
	 *       >>. 저장 : SAVE
	 *    >.개인정보 제3자 제공 내역 : PSNL_INFO_THPR_OFFER_DETL
	 *       >>. 고객관리 : CSTMR_MANAGE
	 *    >.개인정보 취급위탁 내역 : PSNL_INFO_TRTMNT_CNSGN_DETL
	 *       >>. 고객상담, 수선 : CSTMR_CNSL_REPAIR
	 *       >>. 실명인증, I-pin발급 : RLNM_CRTFC_IPIN
	 *       >>. DM발송 : DM_SND
	 *       >>. 물류서비스 : LGIST_SRVC
	 *       >>. 시스템 구축 및 유지보수 : SYS_MANAGE
	 *       >>. 제품판매 및 응대서비스 : CSTMR_SALE_SVC
	 *       >>. SMS, MMS발송 : SND_SMS
	 *       >>. 모바일 쿠폰 발송 : MOBILE_CNP_SND
	 *       >>. 이메일발송 : SND_EMAIL
	 *       >>. 카카오알림톡/문자발송 : SND_KKO_NTCN_TAK	 
	 */
	private String usefJobCd;


	/**
	 * 몰 ID	 
	 */
	private String mallId;


	/**
	 * 언어 코드
	 * ㅁ. 코드 테이블 규칙에 따라 대문자를 사용한 ISO 639 표준에 따른다
	 * 
	 * ㅁ. 언어 : LANG
	 *    >. 한국어 : KOR
	 *    >. 중국어 : CHI
	 *    >. 영어 : ENG
	 *    >. 일본어 : JPN	 
	 */
	private String langCd;


	/**
	 * 디바이스 코드
	 * ㅁ. 디바이스를 정의
	 * 
	 * ㅁ. 디바이스 : DVC
	 *    >. PC : PC
	 *    >. 모바일 웹 : MOBILE_WEB
	 *    >. 모바일 앱 : MOBILE_APP	 
	 */
	private String dvcCd;


	/**
	 * 유입 일련번호	 
	 */
	private java.lang.Long inflowSn;


	/**
	 * BO 사이트 ID
	 * BO, PO, CS를 정의	 
	 */
	private String boSiteId;


	/**
	 * 메뉴 일련번호	 
	 */
	private java.lang.Long menuSn;


	/**
	 * 등록자 ID
	 * 등록한 관리자 번호	 
	 */
	private String regtrId;


	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;


	/**
	 * 수정자 ID
	 * 수정한 관리자 번호	 
	 */
	private String udterId;


	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;

	
}