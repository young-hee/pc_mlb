package com.plgrim.ncp.biz.helpdesk.result;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoFaq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoFaqAtchmnfl;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqAns;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqAtchFile;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqOrdGod;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class HelpdeskEmailFoResult extends AbstractResult{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	CsoMtmInq csoMtmInq;
	
	CsoMtmInqAtchFile csoMtmInqAtchFile;
	
	CsoMtmInqOrdGod csoMtmInqOrdGod;
	
	CsoMtmInqAns csoMtmInqAns;
	
	Mbr mbr;
	
	private String inqDt;
	
	private String regDt;
	
	private String ansDate;
	
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
