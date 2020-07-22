package com.plgrim.ncp.commons.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPlcExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPlcValExtend;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysPolicyDTO extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = -198565344395270773L;
	
	/**
	 * 정책
	 */
	SysPlcExtend sysPlc;
	
	/**
	 * 정책값
	 */
	SysPlcValExtend sysPlcVal;
}
