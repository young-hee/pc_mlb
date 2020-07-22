package com.plgrim.ncp.biz.display.result;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSet;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("dspAbTestSetResult")
public class DspAbTestSetResult extends AbstractResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2213475628339238901L;
	
	/** The dsp ab test set. */
	DspAbTestSet dspAbTestSet;
	
	
}
