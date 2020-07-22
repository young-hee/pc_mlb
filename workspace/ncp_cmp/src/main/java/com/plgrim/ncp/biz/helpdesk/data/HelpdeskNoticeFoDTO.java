package com.plgrim.ncp.biz.helpdesk.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNoti;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNotiAtchFile;

@Data
@EqualsAndHashCode(callSuper=false)
public class HelpdeskNoticeFoDTO extends AbstractDTO{

	/**
	 *
	 */
	private static final long serialVersionUID = -23054304978305937L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	SysNoti sysNoti;

	private String searchNotice;

	private int rNum;

	/*언어코드*/
	private String langCd;

	private String noticeRegDt;

	private long searchNotiSn;

	private String pageNo;

	private String evtNm;

	private String eventPrizeRank;

	private String mbrNo;

	private String evtNo;

	private String eventPrizeYn;

	private String evtApplcnYn;

	private String searchNoticeCd;

	private String searchExpsrScrinSectCd;

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
