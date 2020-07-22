package com.plgrim.ncp.base.entities.datasource1.god;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godCnncGrpGodExtend")
public class GodCnncGrpGodExtend extends GodCnncGrpGod {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8773693404421763086L;

	private String brndId;	
}
