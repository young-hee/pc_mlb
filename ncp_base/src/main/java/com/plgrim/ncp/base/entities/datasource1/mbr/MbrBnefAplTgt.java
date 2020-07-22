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
 * 회원 혜택 적용 대상
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mbrBnefAplTgt")
public class MbrBnefAplTgt extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 혜택 일련번호	 
	 */
	private java.lang.Long bnefSn;


	/**
	 * 적용 순번	 
	 */
	private java.lang.Integer aplTurn;


	/**
	 * 혜택 대상 유형 코드
	 * ㅁ. 혜택 대상 유형 : BNEF_TGT_TP
	 *    >. 몰 ID : MALL_ID
	 *    >. 언어 : LANG
	 *    >. 디바이스 : DVC
	 *    >. 모바일 어플리케이션 구분 : MOBILE_APP_SECT
	 *    >. 대상 회원 유형 : TGT_MBR_TP
	 *    >. 대상 회원 속성 : TGT_MBR_ATRB
	 *    >. 제휴 업체 ID : AFF_COM_ID
	 *    >. 채널 수신 동의 구분 : CHNNL_RECPTN_AGR_SECT	 
	 */
	private String bnefTgtTpCd;


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
	 * 모바일 어플리케이션 구분 코드
	 * ㅁ. 모바일 어플리케이션 구분 : MOBILE_APP_SECT
	 *    >. 디스커버리 앱 : dx_app	 
	 */
	private String mobileAppSectCd;


	/**
	 * 대상 회원 유형 코드
	 * ㅁ. 대상 회원 유형 : TGT_MBR_TP
	 *    >. 비회원 : NMBR
	 *    >. 온라인 회원 : ONLINE_MBR
	 *    >. 멤버쉽 회원 : UNITY_MBR	 
	 */
	private String tgtMbrTpCd;


	/**
	 * 대상 회원 속성 코드
	 * ㅁ. 대상 회원 속성 : TGT_MBR_ATRB
	 *    >. 일반 회원 : GNRL_MBR
	 *    >. 임직원 : EMP	 
	 */
	private String tgtMbrAtrbCd;


	/**
	 * 그룹사 ID	 
	 */
	private String grpcoId;


	/**
	 * 제휴 업체 ID	 
	 */
	private String affComId;


	/**
	 * 채널 수신 동의 구분 코드
	 * ㅁ. 채널 수신 동의 구분 : CHNNL_RECPTN_AGR_SECT
	 *    >. SMS 수신 동의 : SMS_RECPTN_AGR
	 *    >. 이메일 수신 동의 : EMAIL_RECPTN_AGR
	 *    >. DM 수신 동의 : DM_RECPTN_AGR
	 *    >. TM 수신 동의 : TM_RECPTN_AGR	 
	 */
	private String chnnlRecptnAgrSectCd;


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
