package com.plgrim.ncp.biz.vendor.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

/**
 * VendorBrndHoldyResult
 * @author lss
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class VendorBrndHoldyResult extends AbstractResult {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 시스템 일자
	 */
	private String sysDate;
	
	/**
	 * 매장 ID
	 */
	private String shopId;
	
	/**
	 * 브랜드 ID
	 */
	private String brndId;
	
	/**
	 * 사용 여부
	 */
	private String useYn;
	
	/**
	 * 설명
	 */
	private String dscr;
	
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
	
	/**
	 * 등록자명
	 */
	private String regtrNm;
	
	/**
	 * 수정자명
	 */
	private String udterNm;
	
	/**
	 * 등록일(YYYY-MM-DD HH24:MI)
	 */
	private String viewRegDt;
	
	/**
	 * 수정일(YYYY-MM-DD HH24:MI)
	 */
	private String viewUdtDt;
	
	/**
	 * 구분(일별:DAY, 요일별:WEEK)
	 */
	private String gubun;

	/**
	 * 요일코드
	 */
	private String dowCd;
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
