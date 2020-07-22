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
 * 전시 컨텐츠 이미지
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("dspConttImg")
public class DspConttImg extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 컨텐츠 일련번호	 
	 */
	private java.lang.Long conttSn;


	/**
	 * 컨텐츠 이미지 순번
	 * ㅁ.컨텐츠에 대한 순번으로 컨텐츠별 순번을 발급하도록 한다.
	 *    >. "전시 카테고리 컨텐츠.컨텐츠 순번"의 MAX값 + 1로 등록 되도록 한다.
	 *    >. 숫자로 최대 5자리 " 99999" 로 관리 한다.
	 *       (ORACLE 기준 Format)	 
	 */
	private java.lang.Integer conttImgTurn;


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
	 * 이미지 제목 명
	 * ㅁ. 템플릿을 식별 하기 위한 실질 식별자	 
	 */
	private String imgSjNm;


	/**
	 * 이미지 파일 URL	 
	 */
	private String imgFileUrl;


	/**
	 * 이미지 파일 명
	 * ㅁ. 저장된 이미지 파일의 명칭	 
	 */
	private String imgFileNm;


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