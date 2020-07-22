package com.plgrim.ncp.biz.display.result;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestRev;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSet;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSetMod;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRev;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRevCpst;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("dspAbTestRevResult")
public class DspAbTestRevResult extends AbstractResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2213475628339238901L;
	
	String pcTmplatNm;
	
	String mobileTmplatNm;
	
	String abTestGrpNm;
	
	/** The dsp rev cpst. */
	DspRevCpst dspRevCpst;
	
	/** The dsp rev. */
	DspRev dspRev;
	
	/** The dsp ab test rev. */
	DspAbTestRev dspAbTestRev;
	
	/** The dsp ab test set. */
	DspAbTestSet dspAbTestSet;
	
	/** The dsp ab test set mod. */
	DspAbTestSetMod dspAbTestSetMod;
	
	Integer engConttCnt;
	
	Integer chiConttCnt;
	
}
