package com.plgrim.ncp.commons.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopEvt;

@Data
@EqualsAndHashCode(callSuper=false)
public class SysShopEvtResult extends AbstractResult{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 시스템 매장 브랜드 이벤트 Entity
	 */
	private SysShopEvt sysShopEvt;
	
	/**
	 * 시스템 매장 이벤트 상태
	 */
	private String evtStatus;
	
	/**
	 * 시스템 매장 이벤트 상태명
	 */
	private String evtStatusNm;
	
	/**
	 * 등록자명
	 */
	private String regterNm;
	
	/**
	 * 진행기간
	 */
	private String termDate;
	
	/**
	 * 사용여부명
	 */
	private String useYnNm;
	
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
