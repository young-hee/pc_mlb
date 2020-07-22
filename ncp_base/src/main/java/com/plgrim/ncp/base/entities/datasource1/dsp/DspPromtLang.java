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
 * 전시 기획전 언어
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("dspPromtLang")
public class DspPromtLang extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 기획전 일련번호
	 * "1"부터 오라클 시퀀스(MPD_SPDP_BASE_SQ01)를 사용해서 발행	 
	 */
	private java.lang.Long promtSn;


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
	 * 기획전 명
	 * ㅁ. 카테고리에 적용된 언어에 따라서 카테고리명, 이미지를 적용하도록 함.	 
	 */
	private String promtNm;


	/**
	 * 기획전 설명	 
	 */
	private String promtDscr;


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
