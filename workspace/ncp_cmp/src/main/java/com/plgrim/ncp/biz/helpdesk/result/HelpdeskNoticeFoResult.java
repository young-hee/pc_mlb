package com.plgrim.ncp.biz.helpdesk.result;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNoti;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNotiAtchFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class HelpdeskNoticeFoResult extends AbstractResult{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	SysNoti sysNoti;
	
	private String searchNotice; 
	
	private int rowNo;
	
	private String noticeRegDt;
	
	private long searchNotiSn;
	
	private String pageNo;
	
	private String evtNm;
	
	private String eventPrizeRank;

	private String mbrNo;
	
	private String evtNo;
	
	private String eventPrizeYn;
	
	private String evtApplcnYn;
	
	private List<SysNotiAtchFile> sysNotiAtchFiles;
	
	private int rnum;
	
	private String searchNoticeCd; 
	
	
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
