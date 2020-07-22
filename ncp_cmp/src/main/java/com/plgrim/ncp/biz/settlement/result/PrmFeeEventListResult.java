package com.plgrim.ncp.biz.settlement.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;

@Data
@EqualsAndHashCode(callSuper=false)
public class PrmFeeEventListResult extends AbstractResult {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String feeEventSn;
	private String comTpNm;
	private String comId;
	private String comNm;
	private String eventAplTgtCd;
	private String feeEventNm;
	private String eventBegDt;
	private String eventEndDt;
	private String aditFeeRt;
	private String useYn;
	private SysBrnd sysBrnd;
	private God god;
	private String feeEventTpCd;
	private String feeEventTpNm;
	private String regtrId;
	private String udterId;
	private String regDt;
	private String udtDt;
	private String regtrNm;
	private String udterNm;
	
	private String affVrscComId;
	private String affVrscComNm;
	
	private String brndList;
	
	/*Grid 변수*/
	private String godNm;
	private String godNo;
	private String erpGodNo;
	private java.math.BigDecimal rtlPrc;
	private java.math.BigDecimal csmrPrc;
	private String brndId;
	private String brndNm;
	
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
