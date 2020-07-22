package com.plgrim.ncp.commons.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;

@Data
@EqualsAndHashCode(callSuper = false)
public class ErpBrndIdResult extends AbstractResult {

	/**
	 * UID
	 */

    private static final long serialVersionUID = -6655325365564354058L;
    
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    private String brndGrp;
    private String brndCd;
	private String brndNm;
	
}
