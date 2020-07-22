/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 시스템 약관 언어 이력
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysStplatLangHist")
public class SysStplatLangHist extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 약관 코드
	 * ㅁ. 약관 : STPLAT
	 *    >. 온라인사이트 이용약관 : ONLNE_SITE_USEF_STPLAT
	 *    >. 개인정보 보호를 위한 이용자 동의사항 : PSNL_INFO_PRTC_USEF_AGR
	 *    >. 개인정보의 취급위탁 동의 : PSNL_INFO_TRTMNT_CNSGN_AGR
	 *    >. 홍보 마케팅 목적 개인정보 수집 및 이용 동의 (선택) : MARKT_PSNL_INFO_COLCT_USEF_AGR
	 *    >. 개인정보 제 3자 제공 안내 (선택) : PSNL_INFO_THPR_OFFER_DETL
	 *    >. 멤버십 약관 : MBSH_STPLAT
	 *    >. 개인정보 보호를 위한 이용자 동의사항 : PSNL_INFO_PRTC_USEF_AGR_B2E
	 *    >. 비회원 개인정보 수집 이용 동의  : NMBR_PSNL_INFO_COLCT_USEF_AGR
	 *    >. 개인정보 보호를 위한 이용자 동의사항 : PSNL_INFO_PRTC_USEF_AGR_RE_WHSG
	 *    >. 개인정보수집동의 : PSNL_INFO_COLCT_AGR
	 *    >. 개인정보 수집 이용에 대한 동의    PSNL_INFO_COLCT_USEF_AGR
	 *    >. 이메일 무단 수집 거부    EMAIL_WONTICE_COLCT_REJECT
	 *    >. 개인정보취급방침 : PSNL_INFO_TRTMNT_POLCY
	 *    >. 개인정보 취급자 책임과 의무 : PSNL_INFO_TRTR_RSPNSBL_DUTY	 
	 */
	private String stplatCd;


	/**
	 * 언어 코드
	 * ㅁ. 코드 테이블 규칙에 따라 대문자를 사용한 ISO 639 표준에 따른다
	 * 
	 * ㅁ. 언어 : LANG
	 *    >. 한국어 : KOR
	 *    >. 중국어 : CHI
	 *    >. 영어 : ENG	 
	 */
	private String langCd;


	/**
	 * 이력 일시	 
	 */
	private java.util.Date histDt;


	/**
	 * 약관 설명	 
	 */
	private String stplatDscr;


	/**
	 * 약관 내용	 
	 */
	private String stplatCont;


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
