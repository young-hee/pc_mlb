/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.prm;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 프로모션 묶음 할인 조건
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("prmBundleDcCnd")
public class PrmBundleDcCnd extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 프로모션 번호
	 * ㅁ. PR || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String prmNo;


	/**
	 * 묶음 할인 조건 순번	 
	 */
	private java.lang.Integer bundleDcCndTurn;


	/**
	 * 묶음 할인 조건 최소 수량
	 * 묶음 할인 상품 중 조건에 해당하는 최소 주문수량	 
	 */
	private java.lang.Long bundleDcCndMinQty;


	/**
	 * 묶음 할인 조건 최대 수량
	 * 묶음 할인 상품 중 조건에 해당하는 최대 주문수량	 
	 */
	private java.lang.Long bundleDcCndMaxQty;


	/**
	 * 묶음 할인 구분 코드
	 * ㅁ. 묶음 할인 구분 : BUNDLE_DC_SECT
	 *    >. 정액 : FIXAMT
	 *    >. 정률 : FIXRT	 
	 */
	private String bundleDcSectCd;


	/**
	 * 묶음 할인 금액
	 * 조건에 맞는 주문 수량별 할인되는 금액	 
	 */
	private java.math.BigDecimal bundleDcAmt;


	/**
	 * 묶음 할인 율
	 * 조건에 맞는 주문 수량별 할인되는 비율	 
	 */
	private java.math.BigDecimal bundleDcRt;


	/**
	 * 등록자 ID	 
	 */
	private String regtrId;


	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;


	/**
	 * 수정자 ID	 
	 */
	private String udterId;


	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;

	
}