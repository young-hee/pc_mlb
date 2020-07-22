package com.plgrim.ncp.biz.helpdesk.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrLoginLog;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;

@Data
@EqualsAndHashCode(callSuper=false)
public class HistoryInfoFoDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9085367007917825651L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	MbrLoginLog mbrLoginLog;
	
	MbrPsnlInfoModHist mbrPsnlInfoModHist;
	
	private String mbrNo;
	
	private String mbrNm;
	
	private String loginDt; //로그인 일시
	
	private String langCd;
	
	private String mallId;

	private String dvcCd;
	
	private String psnlInfoModDt; //개인정보 변경일시
	
	private String erpTrnsmisDt; //erp 전송일시
	
	private String regtrId;

	private String regDt;

	private String udterId;

	private String udtDt;
	
	private String joinDt;
	
	private String dateStart;    							    	
	
	private String dateEnd;    										
	
	private String srchMonth;    	
	
	private String usefJobCd;

	private String gubun;
	

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
