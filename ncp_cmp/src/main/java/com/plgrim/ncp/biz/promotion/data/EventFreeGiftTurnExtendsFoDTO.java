package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftInfo;
import com.plgrim.ncp.framework.commons.StringService;

@Data
@EqualsAndHashCode(callSuper = false)
public class EventFreeGiftTurnExtendsFoDTO extends EvtFreeGiftInfo {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int dcAmt;

	private int dcRt;

	private String dcSectCd;
	
	private String maxDcPsbAmt;
	
	private String cpnMaxDcPsbAmt;

	private String evtNm;

	private String evtStatCd;

	private Date begDt;

	private Date endDt;

	private Long freeGiftQty;
	
	private String baseFreeGiftQty;

	private String freeGiftQtyStr;
	
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

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
	
	public String getFreeGiftQtyStr() {
		String bfGq = getBaseFreeGiftQty();
		if (StringService.isNotEmpty(bfGq)) {
			int length = bfGq.length();
			freeGiftQtyStr = String.format("%0"+length+"d", getFreeGiftQty());
		}
		return freeGiftQtyStr;
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
