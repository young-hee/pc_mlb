package com.plgrim.ncp.commons.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysExchgRt;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)

public class SysExchgResult extends AbstractResult {


	/**
	 * 
	 */
    private static final long serialVersionUID = -4541116837790748487L;
	SysExchgRt sysExchgRt;
	String exchgRtCrncyAmtNm; // 적용환율
	
}
