/**
 * @author : Generator(Generator)
 * @date : 2018-06-26
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.inf;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 인터페이스 상품 가격
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("infGodPrc")
public class InfGodPrc extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 브랜드 ID
	 * ㅁ. BRAND : 브랜드코드
	 *    >. 디스커버리 : X
	 *    >. MLB : M
	 *    >. MLB Kids : I	 
	 */
	private String brndId;


	/**
	 * 시즌 코드
	 * ㅁ. SEASON : 시즌 코드
	 * ㅁ. ERP에서 연계 받는 코드	 
	 */
	private String sesonCd;


	/**
	 * 디자인 그룹 번호
	 * ㅁ. PARTCODE : 품번 코드	 
	 */
	private String dsgnGrpNo;


	/**
	 * 실소가
	 * ㅁ. PRICE : 판매가(실소가)	 
	 */
	private java.math.BigDecimal csmrPrc;


	/**
	 * 실소가 유형 코드
	 * ㅁ. SALETRTYPE : 판매가유형	 
	 */
	private String csmrPrcTpCd;


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
