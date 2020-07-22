package com.plgrim.ncp.biz.display.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtSprtr;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtSprtrLang;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspPromtSprtrFrResult extends AbstractResult{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** 전시 기획전 구분자정보*/
	private DspPromtSprtr dspPromtSprtr;
	
	/** 전시 기획전 구분자 언어 정보*/
	private DspPromtSprtrLang dspPromtSprtrLang;
	
	
	List<DspPromtGodFrResult> dspPromtGodList;
	
	Long promtSn;
	
	Integer sprtrTurn;
	private String colorStyleCd;

	private String[] colorStyleCds;
    
	public String[] getColorStyleCds() {
		
		if(getColorStyleCd() != null){
			
			return getColorStyleCd().split(",");
			
		}else{
		
			return colorStyleCds;
		}			
	}
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
	public DspPromtSprtr getDspPromtSprtr() {
		return changeDspPromtSprtrLnag(dspPromtSprtr, dspPromtSprtrLang);
	}
	
	private DspPromtSprtr changeDspPromtSprtrLnag(DspPromtSprtr dspPromtSprtr, DspPromtSprtrLang dspPromtSprtrLang){
		if(dspPromtSprtrLang != null && dspPromtSprtrLang.getSprtrNm() != null){
			dspPromtSprtr.setSprtrNm(dspPromtSprtrLang.getSprtrNm());
			dspPromtSprtr.setSprtrImgFileNm(dspPromtSprtrLang.getSprtrImgFileNm());
			dspPromtSprtr.setSprtrImgFileUrl(dspPromtSprtrLang.getSprtrImgFileUrl());
			dspPromtSprtr.setSprtrImgAltrtvCont(dspPromtSprtrLang.getSprtrImgAltrtvCont());
		}
		
		return dspPromtSprtr;
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
