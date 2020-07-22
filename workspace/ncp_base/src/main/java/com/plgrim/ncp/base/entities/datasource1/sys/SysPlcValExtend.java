package com.plgrim.ncp.base.entities.datasource1.sys;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysPlcValExtend")
public class SysPlcValExtend extends SysPlcVal {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	private int maxPlcValTurn;
	/**
	 * 수정자명
	 */
	private String udterNm;
	private String plcValBegDtStr;
	private String plcValEndDtStr;
	private String aplBegDtStr;
}
