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
 * 시스템 품목 코드
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysPrdlstCd")
public class SysPrdlstCd extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 품목 코드
	 * ㅁ. ERP와 연동하여 처리 된건은 2자리	 
	 */
	private String prdlstCd;


	/**
	 * 사용 여부	 
	 */
	private String useYn;


	/**
	 * 상품 무게
	 * ㅁ. ㎏ 단위	 
	 */
	private java.math.BigDecimal godWt;


	/**
	 * 품목 구분 코드
	 * ㅁ. 품목 구분 : PRDLST_SECT
	 *    >. 의류 : CLTHS
	 *    >. 잡화 : ACSR	 
	 */
	private String prdlstSectCd;


	/**
	 * 정렬 순서	 
	 */
	private java.lang.Integer sortSeq;


	/**
	 * 품목 설명	 
	 */
	private String prdlstDscr;


	/**
	 * 표준 카테고리 번호
	 * ㅁ. 표준 카테고리 번호
	 *    >. 대카(3) + 중카(3) + 소카(3) + 세카(3) + 세세카(3)
	 * ㅁ. 사용 안함	 
	 */
	private String stdCtgryNo;


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