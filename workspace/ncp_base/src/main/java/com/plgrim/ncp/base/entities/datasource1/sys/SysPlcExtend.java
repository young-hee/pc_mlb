package com.plgrim.ncp.base.entities.datasource1.sys;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysPlcExtend")
public class SysPlcExtend extends SysPlc {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String mallNm;	//몰명
	private String plcNm;	//정책명
	/**
	 * 수정자명
	 */
	private String udterNm;
}
