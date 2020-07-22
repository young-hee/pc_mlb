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
 * 회원 개인정보 이용 업무 상세
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mbrPsnlInfoUsefJobDetail")
public class MbrPsnlInfoUsefJobDetail extends AbstractEntity {
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
	 * 이용 업무 상세 코드
	 * ㅁ. 이용 업무 상세 코드 : USE_JOB_DETAIL
	 * 
	 *    >.개인정보이용현황 : PSNL_INFO_STTUS
	 *       >>. 1:1문의등록 :  MTM_INQ_REG
	 *          >>>. 휴대전화번호 : MOBIL_NO
	 *          >>>. E-mail 수신동의여부 : EMAIL_RCV_YN
	 *          >>>. E-mail 주소 : EMAIL
	 *          >>>. SMS 수신여부 : SMS_RCV_YN
	 *       >>. 배송정보등록 : DLVSP_INFO_REG
	 *          >>>. 주소 : ADDR
	 *          >>>. 휴대전화번호 : MOBIL_NO
	 *          >>>. 유선전화번호 : CBLE_PHON_NO
	 *       >>. 배송정보수정 : DLVSP_INFO_UDT
	 *          >>>. 이름(받는사람) : RCVER_NM
	 *          >>>. 영문이름 : ENG_NM
	 *          >>>. 주소 : ADDR
	 *          >>>. 휴대전화번호 : MOBIL_NO
	 *          >>>. 유선전화번호 : CBLE_PHON_NO
	 *       >>. 결제정보 : PAY_INFO
	 *          >>>. 예금주 : ACNTHLDR
	 *          >>>. 은행명 : BNK_NM
	 *          >>>. 계좌번호 : BNK_ACCT_NO
	 *       >>. 조회 : INQIRE
	 *          >>>. 목록 : LIST
	 *          >>>. 회원ID : MBR_ID
	 *          >>>. 휴대전화번호 : MOBIL_NO
	 *          >>>. 주문상세 : ORD_DETAIL
	 *          >>>. 회원상세 : MBR_DETAIL
	 *          >>>. 클레임상세 : CLM_DETAIL
	 *          >>>. 업체상세 : COM_DETAIL
	 *          >>>. 1:1문의 상세 : MTM_INQ_DETAIL
	 *          >>>. 상품평 상세 : GOD_EVL_DETAIL
	 *       >>. 삭제 : DELETE
	 *          >>>. 탈퇴 : MBR_SECSN
	 *       >>. 저장 : SAVE
	 *          >>>. 엑셀다운로드 : EXCEL_DOWN
	 *    >.개인정보 제3자 제공 내역 : PSNL_INFO_THPR_OFFER_DETL
	 *       >>. 고객관리 : CSTMR_MANAGE
	 *          >>>. 회원정보 전체 : MBR_INFO_ALL
	 *    >.개인정보 취급위탁 내역 : PSNL_INFO_TRTMNT_CNSGN_DETL
	 *       >>. 고객상담, 수선 : CSTMR_CNSL_REPAIR
	 *          >>>. 클레임접수 : CLM_RCEPT
	 *          >>>. 1:1문의 답변 : MTM_INQ_ANS
	 *          >>>. 배송 정보 수정 : DLVSP_INFO_UDT
	 *       >>. 실명인증, I-pin발급 : RLNM_CRTFC_IPIN
	 *          >>>. 약관에 따름 : STPLAT_APPLC
	 *       >>. DM발송 : DM_SND
	 *          >>>. 약관에 따름 : STPLAT_APPLC
	 *       >>. 물류서비스 : LGIST_SRVC
	 *          >>>. 약관에 따름 : STPLAT_APPLC
	 *       >>. 시스템 구축 및 유지보수 : SYS_MANAGE
	 *          >>>. 약관에 따름 : STPLAT_APPLC
	 *       >>. 제품판매 및 응대서비스 : CSTMR_SALE_SVC
	 *          >>>. 약관에 따름 : STPLAT_APPLC
	 *       >>. SMS, MMS발송 : SND_SMS
	 *          >>>. 약관에 따름 : STPLAT_APPLC
	 *       >>. 모바일 쿠폰 발송 : MOBILE_CNP_SND
	 *          >>>. 약관에 따름 : STPLAT_APPLC	 
	 */
	private String usefJobDetailCd;


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
