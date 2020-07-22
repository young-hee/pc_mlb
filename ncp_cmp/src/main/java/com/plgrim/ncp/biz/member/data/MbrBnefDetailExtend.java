/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.biz.member.data;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefDetail;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 회원 혜택 상세 확장
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mbrBnefDetailExtend")
public class MbrBnefDetailExtend extends MbrBnefDetail {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	
}
