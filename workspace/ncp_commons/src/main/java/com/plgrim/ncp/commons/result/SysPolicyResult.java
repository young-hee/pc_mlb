package com.plgrim.ncp.commons.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPlcExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPlcValExtend;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysPolicyResult extends AbstractResult {
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = -811561956003771511L;

	/**
	 * 정책
	 */
	SysPlcExtend sysPlc;
	
	/**
	 * 정책값
	 */
	SysPlcValExtend sysPlcVal;
}
