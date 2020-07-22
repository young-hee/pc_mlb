package com.plgrim.ncp.biz.display.result;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTest;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSetMod;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspConttAbTestResult extends AbstractResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2213475628339238901L;

	String abTestSn;
	
	/** The dsp ab test. */
	DspAbTest dspAbTest;
	
	DspAbTestSetMod dspAbTestSetMod;
	
	String setExpsrMenmthdNm;
	
	String progrsStatusCd;

	String progrsMenu;

	String progrsNm;

	String term;

	List<DspConttAbTestSetResult> dspAbTestSetList;
	
}
