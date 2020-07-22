package com.plgrim.ncp.biz.display.result;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSet;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSetMod;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspConttAbTestSetResult extends AbstractResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2213475628339238901L;

	String abTestSn;
	
	Integer setTurn;
	
	DspAbTestSet dspAbTestSet;
	
	List<DspAbTestRevResult> dspAbTestRevList;
	List<DspAbTestSetFlterResult> dspAbTestSetFlterList;
	List<DspAbTestSetModResult> dspAbTestSetModList;
}
