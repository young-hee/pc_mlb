package com.plgrim.ncp.biz.display.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtLang;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspPromtFrResult extends AbstractResult{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** 전시 기획전 엔티티 */
	DspPromt dspPromt;
	
	/** 전시 기획전 언어 엔티티 */
	DspPromtLang dspPromtLang;
	
	List<DspPromtSprtrFrResult> dspPromtSprtrList;
	
	Long promtSn;
	
	
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
	public DspPromt getDspPromt() {
		return changeDspPromtLnag(dspPromt, dspPromtLang);
	}
	
	private DspPromt changeDspPromtLnag(DspPromt dspPromt, DspPromtLang dspPromtLang){
		if(dspPromtLang != null && dspPromtLang.getPromtNm() != null){
			dspPromt.setPromtNm(dspPromtLang.getPromtNm());
//			dspPromt.setPcImgFileUrl(dspPromtLang.getPcImgFileUrl());
//			dspPromt.setPcImgAltrtvCont(dspPromtLang.getPcImgAltrtvCont());
//			dspPromt.setPcImgFileNm(dspPromtLang.getPcImgFileNm());
//			dspPromt.setMobileImgFileUrl(dspPromtLang.getMobileImgFileUrl());
//			dspPromt.setMobileImgAltrtvCont(dspPromtLang.getMobileImgAltrtvCont());
//			dspPromt.setMobileImgFileNm(dspPromtLang.getMobileImgFileNm());
//			dspPromt.setPcGodImgFileUrl(dspPromtLang.getPcGodImgFileUrl());
//			dspPromt.setPcGodImgAltrtvCont(dspPromtLang.getPcGodImgAltrtvCont());
//			dspPromt.setPcGodImgFileNm(dspPromtLang.getPcGodImgFileNm());
//			dspPromt.setMobileGodImgFileUrl(dspPromtLang.getMobileGodImgFileUrl());
//			dspPromt.setMobileGodImgAltrtvCont(dspPromtLang.getMobileGodImgAltrtvCont());
//			dspPromt.setMobileGodImgFileNm(dspPromtLang.getMobileGodImgFileNm());
		}
		
		return dspPromt;
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
