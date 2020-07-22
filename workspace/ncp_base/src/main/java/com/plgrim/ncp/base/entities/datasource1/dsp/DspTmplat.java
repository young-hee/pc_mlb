/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.dsp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 전시 템플릿
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("dspTmplat")
public class DspTmplat extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 템플릿 일련번호
	 * ㅁ. 템플릿을 관리하는 실질 식별자	 
	 */
	private java.lang.Long tmplatSn;


	/**
	 * 템플릿 유형 코드
	 * ㅁ. 템플릿 유형 : TMPLAT_TP
	 *    >. 전시 카테고리(정형) : DSP_CTGRY
	 *    >. 테마 페이지(비정형) : THEMA_PGE
	 *    >. 기획전 : PROMT
	 *    >. S-TREND : STRND	 
	 */
	private String tmplatTpCd;


	/**
	 * 디바이스 구분 코드
	 * ㅁ. 디바이스 구분 : DVC_SECT
	 *    >. PC : PC
	 *    >. 모바일 : MOBILE
	 *    >. PC + 모바일 : PC_MOBILE	 
	 */
	private String dvcSectCd;


	/**
	 * 템플릿 명
	 * ㅁ. 템플릿을 식별 하기 위한 실질 식별자	 
	 */
	private String tmplatNm;


	/**
	 * 템플릿 설명
	 * ㅁ. 템플릿에 대한 간략한 설명	 
	 */
	private String tmplatDscr;


	/**
	 * 템플릿 연결 URL
	 * ㅁ. 템플릿에 대한 모바일용URL을 관리 함.	 
	 */
	private String tmplatCnncUrl;


	/**
	 * 컨텐츠 등록 가이드 이미지 URL	 
	 */
	private String conttRegGdImgUrl;


	/**
	 * 템플릿 사용 여부
	 * ㅁ. 템플릿 사용 여부
	 *    >. 사용 : Y
	 *    >. 미사용 : N	 
	 */
	private String tmplatUseYn;


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