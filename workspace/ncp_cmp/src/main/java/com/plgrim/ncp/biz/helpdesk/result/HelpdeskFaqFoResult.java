package com.plgrim.ncp.biz.helpdesk.result;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoFaq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoFaqAtchmnfl;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class HelpdeskFaqFoResult extends AbstractResult{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	CsoFaq csoFaq;
	
	private int rowNo;
	
	private String searchFaq;
	
	private String searchCd;
	
	private String searchAllCd;
	/*코드*/
	private String cd;
	/*상위코드*/
	private String upperCd;
	/*언어코드*/
	private String langCd;
	/*코드명*/
	private String cdNm;
	/*상위코드명*/
	private String upperCdNm;
	
	private String faqSchwdNm;
	
	private String pageNo;
	
	private long faqSn;
	
	private List<CsoFaqAtchmnfl> csoFaqAtchmnfls;
	
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
