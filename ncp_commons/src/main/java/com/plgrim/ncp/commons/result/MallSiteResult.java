package com.plgrim.ncp.commons.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMallCnvrsUrl;

@Data
@EqualsAndHashCode(callSuper = false)
public class MallSiteResult extends AbstractResult {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * UID
	 */
    private static final long serialVersionUID = -1228499078819181695L;
	SysMallCnvrsUrl sysMallCnvrsUrl;
	String userMallId; // 몰 ID

}
