package com.plgrim.ncp.biz.member.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInqAns;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BundleOrderFoResult extends AbstractResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8278802629339800103L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	CsoOrgztOrdAffInq csoOrgztOrdAffInq;
	
	CsoOrgztOrdAffInqAns csoOrgztOrdAffInqAns;
	
	private String mobilNo;
	
	private String chkEmail;
	
	private String chkSms;
	
	private String mbrId;
	
	private String mobilNationNo;
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
