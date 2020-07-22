package com.plgrim.ncp.biz.member.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoNmbrStplatAgr;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInqAns;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInqAtchmnfl;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BundleOrderFoDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4004713041258152373L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	Mbr mbr;
	
	CsoOrgztOrdAffInq csoOrgztOrdAffInq;
	
	CsoOrgztOrdAffInqAns csoOrgztOrdAffInqAns;
	/**고객서비스 비회원 약관 동의 Entity	 */
	CsoNmbrStplatAgr csoNmbrStplatAgr;
	/**단체제휴문의 첨부파일*/
	CsoOrgztOrdAffInqAtchmnfl csoOrgztOrdAffInqAtchmnfl;
	
	private String mobilNo;
	
	private String chkEmail;
	
	private String chkSms;
	
	private String mbrId;
	
	private String agreeCheck;
	
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
