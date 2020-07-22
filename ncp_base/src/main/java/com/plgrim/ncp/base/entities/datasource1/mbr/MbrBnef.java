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
 * 회원 혜택
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mbrBnef")
public class MbrBnef extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 혜택 일련번호	 
	 */
	private java.lang.Long bnefSn;


	/**
	 * 혜택 구분 코드
	 * ㅁ. BNEF_SECT : 혜택 구분
	 *    >. LOGIN : 로그인
	 *    >. ONLNE_NEW_JOIN : 온라인 신규 가입
	 *    >. UNITY_NEW_JOIN : 멤버십 신규 가입	 
	 */
	private String bnefSectCd;


	/**
	 * 혜택 상세 구분 코드
	 * ㅁ. BNEF_SECT : 혜택 구분
	 *    >. LOGIN : 로그인
	 *       >>. MOBILE_APP_DWLD_BNEF : 모바일 앱 다운로드 혜택
	 *    >. ONLNE_NEW_JOIN : 온라인 신규 가입
	 *       >>. NEW_MBR_JOIN : 신규 회원 가입
	 *       >>. MARKT_RECPTN_AGR : 마케팅 수신 동의
	 *       >>. MBR_JOIN_RECOMENDR_BNEF : 회원가입 추천인 혜택
	 *       >>. MBR_JOIN_RECOMENDE_BNEF : 회원가입 피추천인 혜택
	 *    >. UNITY_NEW_JOIN : 멤버십 신규 가입
	 *       >>. NEW_UNITY_MBR_JOIN : 신규 멤버십 회원 가입	 
	 */
	private String bnefDetailSectCd;


	/**
	 * 혜택 명	 
	 */
	private String bnefNm;


	/**
	 * 혜택 시작 일시	 
	 */
	private java.util.Date bnefBegDt;


	/**
	 * 혜택 종료 일시	 
	 */
	private java.util.Date bnefEndDt;


	/**
	 * 승인 구분 코드
	 * ㅁ. 승인 구분 : APRV_SECT
	 *    >. 승인대기 : APRV_WAIT
	 *    >. 승인 : APRV
	 *    >. 중지 : STPGE	 
	 */
	private String aprvSectCd;


	/**
	 * 승인 관리자 ID	 
	 */
	private String aprvAdminId;


	/**
	 * 승인 일시	 
	 */
	private java.util.Date aprvDt;


	/**
	 * 중지 관리자 ID	 
	 */
	private String stpgeAdminId;


	/**
	 * 중지 일시	 
	 */
	private java.util.Date stpgeDt;


	/**
	 * 중지 사유 내용	 
	 */
	private String stpgeResnCont;


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
