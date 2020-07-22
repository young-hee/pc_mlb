/**
 * @package : com.plgrim.ncp.base.entities..sys
 * @author : jackie(jackie)
 * @date : 20150601
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 시스템 약관 이력
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysStplatHistExtends")
public class SysStplatHistExtends extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
등록한 관리자 번호	 
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

	private String histStrDt;
}
