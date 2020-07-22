package com.plgrim.ncp.commons.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysExchgRt;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SysExchgDTO extends AbstractDTO {/**
	 * 
	 */
    private static final long serialVersionUID = 1L;

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
    private SysExchgRt sysExchgRt;
    private String exchgRtAplBegDtDay; // 적용일
    private String exchgRtAplBegDtHour; // 적용시간
    private String exchgRtAplBegDtMinute; // 적용분
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
