package com.plgrim.ncp.biz.display.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtLang;
@Data
@EqualsAndHashCode(callSuper=false)
public class DspCnrConttPromtFoResult extends AbstractResult {

	/**
	 * 
	 */
    private static final long serialVersionUID = -1619854242901818701L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    DspPromt dspPromt;
    DspPromtLang dspPromtLang;
    
    //1단PC이미지
    String pcMobileImgIndUseYn;
	String pcImgFileNm;
	String pcImgFileUrl;
	String pcImgAltrtvCont;
	String pcImgExpsrTxt1Cont;
	String pcImgExpsrTxt2Cont;
	//1단PC이미지-언어
	String pPcImgFileNm;
	String pPcImgFileUrl;
	String pPcImgAltrtvCont;
	String pPcImgExpsrTxt1Cont;
	String pPcImgExpsrTxt2Cont;
	//2단PC이미지
	String pcMobileImgIndUseYn2pce;
	String pcImgFileNm2pce;
	String pcImgFileUrl2pce;
	String pcImgAltrtvCont2pce;
	String pcImgExpsrTxt1Cont2pce;
	String pcImgExpsrTxt2Cont2pce;
	//2단PC이미지-언어
	String pPcImgFileNm2pce;
	String pPcImgFileUrl2pce;
	String pPcImgAltrtvCont2pce;
	String pPcImgExpsrTxt1Cont2pce;
	String pPcImgExpsrTxt2Cont2pce;
	
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
