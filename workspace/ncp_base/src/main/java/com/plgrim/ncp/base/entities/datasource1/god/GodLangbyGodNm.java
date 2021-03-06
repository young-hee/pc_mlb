/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 상품 언어별 상품 명
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godLangbyGodNm")
public class GodLangbyGodNm extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 상품 번호
	 * ㅁ. 상품 유형 1자리 || 업체 코드 3자리 || YYMMDD || 5자리 Cycle Sequence	 
	 */
	private String godNo;


	/**
	 * 언어 코드	 
	 */
	private String langCd;


	/**
	 * 번역 상태 코드
	 * ㅁ. 번역 상태 : TRSLT_STAT
	 *    >. 번역 대기 : TRSLT_WAIT
	 *    >. 번역 요청 : TRSLT_REQUST
	 *    >. 번역 완료 : TRSLT_COMPT	 
	 */
	private String trsltStatCd;


	/**
	 * 해외 전시 상태 코드
	 * ㅁ. 해외 전시 상태 : OVSEA_DSP_STAT
	 *    >. 전시 대기 : DSP_WAIT
	 *    >. 전시 승인 : DSP_APRV
	 *    >. 전시 거부 : DSP_REJECT	 
	 */
	private String ovseaDspStatCd;


	/**
	 * 상품 명	 
	 */
	private String godNm;


	/**
	 * 모바일 상품 명	 
	 */
	private String mobileGodNm;


	/**
	 * 태그 명	 
	 */
	private String tagNm;


	/**
	 * 색상 태그 명	 
	 */
	private String colorTagNm;


	/**
	 * 테마 태그 명	 
	 */
	private String themaTagNm;


	/**
	 * 색상 명	 
	 */
	private String colorNm;


	/**
	 * 상품 검색 유의어	 
	 */
	private String godSrchSnm;


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
