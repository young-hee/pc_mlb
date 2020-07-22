package com.plgrim.ncp.base.entities.datasource1.inf;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("infGodItmExtend")
public class InfGodItmExtend extends InfGodItm {
	private static final long serialVersionUID = 8906190217140717935L;
	
	/**
	 * 혼용율 
	 */	
	private String matrDscr;

	/**
	 * 혼용율(영문) 
	 */	
	private String engMatrDscr;
	
	/**
	 * 혼용율(중문) 
	 */	
	private String chiMatrDscr;
	
	/**
	 * MLB 팀코드
	 */	
	private String teamCd;

}
