/**
 * @author : Generator(Generator)
 * @date : 2018-05-29
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.com;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 업체 국내 배송비 정책 언어
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("comDmstcDlvCstPlcLang")
public class ComDmstcDlvCstPlcLang extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 국내 배송비 정책 일련번호	 
	 */
	private java.lang.Long dmstcDlvCstPlcSn;


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
	 * 배송 방법 설명	 
	 */
	private String dlvMthdDscr;


	/**
	 * 모바일 배송 방법 설명	 
	 */
	private String mobileDlvMthdDscr;


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