package com.plgrim.ncp.biz.helpdesk.data;


import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoFaq;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class HelpdeskFaqFoDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3271929153970109530L;

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
