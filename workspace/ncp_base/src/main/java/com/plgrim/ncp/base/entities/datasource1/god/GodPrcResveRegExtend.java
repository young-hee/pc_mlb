/**
 * @package : com.plgrim.ncp.base.entities..god
 * @author : jackie(jackie)
 * @date : 20150616
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 상품 가격 예약 등록
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godPrcResveRegExtend")
public class GodPrcResveRegExtend extends GodPrcResveReg {
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 6461784682514321403L;

	/**
	 * 예약 시작 일시	 
	 */
	private String resveBegDtStr;
	
	/**
	 * 예약 종료 일시	 
	 */
	private String resveEndDtStr;
	
	/**
	 * 승인 일시	 
	 */
	private String aprvDtStr;
	
	/**
	 * 등록 일시	 
	 */
	private String regDtStr;

	/**
	 * 수정 일시	 
	 */
	private String udtDtStr;
	
	/**
	 * 정률정액여부	 
	 */
	private String prcTyp;
	
	/**
	 * 인하율	 
	 */
	private String prcDcRt;
	
	/**
	 * 인하액	 
	 */
	private String prcDcAmt;
	
	/**
	 * 현재 실소가	 
	 */
	private java.math.BigDecimal currCsmrPrc;
	
	/**
	 * 현재 임직원가	 
	 */
	private java.math.BigDecimal currEmpPrc;
	
	/**
	 * 가격 예약구분코드
	 */
	private String prcResveSectCd;
}
