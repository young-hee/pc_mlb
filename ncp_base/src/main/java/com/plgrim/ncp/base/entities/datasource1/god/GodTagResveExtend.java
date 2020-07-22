package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("godTagResveExtend")
public class GodTagResveExtend extends GodTagResve {	
	private static final long serialVersionUID = 7360802308675950451L;
	
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * 예약 시작 일시	 
	 */
	private String resveBegDtStr;
	
	/**
	 * 예약 종료 일시	 
	 */
	private String resveEndDtStr;
	
	/** 등록일시 */
	private String regDtStr;
	
	/** 검색기간 시작시간 */
	private String startTermHh;
	
	/** 검색기간 시작분 */
	private String startTermMm;
	
	/** 검색기간 종료시간 */
	private String endTermHh;
	
	/** 검색기간 종료분 */
	private String endTermMm;
	
	/** 영문 태그명 */
    private String tagNmEng;
    
    /** 중문 태그명 */
    private String tagNmChi;    
	
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
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}
