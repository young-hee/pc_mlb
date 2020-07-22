/**
 * @package : com.plgrim.ncp.base.entities..god
 * @author : jackie(jackie)
 * @date : 20150518
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 상품 가격 예약
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godPrcResveExtend")
public class GodPrcResveExtend extends GodPrcResve {
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 4632457246561506189L;

	/**
	 * 예약시작일시	 
	 */
	private String resveBegDtStr;
	
	/**
	 * 예약종료일시	 
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
	 * 업체 수수료율
	 */
	private java.math.BigDecimal partmalComFeeRt;

	/**
	 * 일괄등록처리 결과메세지
	 */
	private String validText;

}
