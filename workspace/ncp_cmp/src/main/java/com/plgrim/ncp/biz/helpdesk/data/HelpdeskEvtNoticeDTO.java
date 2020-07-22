package com.plgrim.ncp.biz.helpdesk.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPrize;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNoti;

@Data
@EqualsAndHashCode(callSuper=false)
public class HelpdeskEvtNoticeDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5445740133361452933L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	Mbr mbr;
	
	SysNoti sysNoti;
	
	EvtApplcn evtApplcn;
	
	EvtPrize evtPrize;
	
	Evt evt;
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
