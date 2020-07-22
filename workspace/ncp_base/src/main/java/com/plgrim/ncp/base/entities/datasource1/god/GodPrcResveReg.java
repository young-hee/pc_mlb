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
 * 상품 가격 예약 등록
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godPrcResveReg")
public class GodPrcResveReg extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 예약 등록 일련번호	 
	 */
	private java.lang.Long resveRegSn;


	/**
	 * 상품 번호
	 * ㅁ. 상품 유형 1자리 || 업체 코드 3자리 || YYMMDD || 5자리 Cycle Sequence	 
	 */
	private String godNo;


	/**
	 * 가격 예약 구분 코드
	 * ㅁ. 가격 예약 구분 : PRC_RESVE_SECT
	 *    >. 정소가 : RTL_PRC
	 *    >. 실소가 : CSMR_PRC
	 *    >. 임직원가 : EMP_PRC	 
	 */
	private String prcResveSectCd;


	/**
	 * 예약 시작 일시	 
	 */
	private java.util.Date resveBegDt;


	/**
	 * 예약 종료 일시	 
	 */
	private java.util.Date resveEndDt;


	/**
	 * 등록 관리자 ID	 
	 */
	private String regAdminId;


	/**
	 * 승인 여부	 
	 */
	private String aprvYn;


	/**
	 * 승인 일시	 
	 */
	private java.util.Date aprvDt;


	/**
	 * 승인 관리자 ID	 
	 */
	private String aprvAdminId;


	/**
	 * 적용 여부	 
	 */
	private String aplYn;


	/**
	 * 적용 일시	 
	 */
	private java.util.Date aplDt;


	/**
	 * 적용 관리자 ID	 
	 */
	private String aplAdminId;


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