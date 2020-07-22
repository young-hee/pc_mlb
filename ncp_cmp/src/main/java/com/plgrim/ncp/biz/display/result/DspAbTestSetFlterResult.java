package com.plgrim.ncp.biz.display.result;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSetFlter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("dspAbTestSetFlterResult")
public class DspAbTestSetFlterResult extends DspAbTestSetFlter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2213475628339238901L;
	
	
	BigDecimal expsrRt; 
	
	String strOrdBegDt;
	
	String strOrdEndDt;
	
	String flterGrpCdNm;
	
	String flterCdNm;
	
	Integer flterGrpSortSeq;
	
	Integer flterSortSeq;
	
	
}
