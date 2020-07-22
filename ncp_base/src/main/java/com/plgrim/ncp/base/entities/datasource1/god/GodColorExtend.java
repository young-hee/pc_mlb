package com.plgrim.ncp.base.entities.datasource1.god;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godColorExtend")
public class GodColorExtend extends GodColor {
	private static final long serialVersionUID = 3170454601215723606L;
	
	
	/**
	 * 색상 그룹 명	 
	 */
	private String colorGrpNm;
	
	
	/**
	 * 색상 그룹 스타일 내용 1	 
	 */
	private String colorGrpStyleCont1;


	/**
	 * 색상 그룹 스타일 내용 2	 
	 */
	private String colorGrpStyleCont2;	

}
