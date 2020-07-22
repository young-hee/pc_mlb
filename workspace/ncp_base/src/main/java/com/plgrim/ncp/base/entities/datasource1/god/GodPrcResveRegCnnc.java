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

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 상품 가격 예약 등록 연결
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godPrcResveRegCnnc")
public class GodPrcResveRegCnnc extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 가격 예약 등록 일련번호	 
	 */
	private java.lang.Long prcResveRegSn;
	/**
	 * 상품 번호
ㅁ. 상품 유형 1자리 || 업체 코드 3자리 || YYMMDD || 5자리 Cycle Sequence	 
	 */
	private String godNo;
	/**
	 * 예약 정소가	 
	 */
	private java.math.BigDecimal resveRtlPrc;
	/**
	 * 예약 실소가	 
	 */
	private java.math.BigDecimal resveCsmrPrc;
	/**
	 * 예약 임직원가	 
	 */
	private java.math.BigDecimal resveEmpPrc;
	/**
	 * 등록자 ID
등록한 관리자 번호	 
	 */
	private String regtrId;
	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;
	/**
	 * 수정자 ID
수정한 관리자 번호	 
	 */
	private String udterId;
	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;

}
