/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shsunhee.kim
 * @since       2015. 11. 27       
 */
package com.plgrim.ncp.biz.display.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspBrndCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnr;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrTmplatInfoCnnc;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtImg;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtImgLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrnd;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrndLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspTmplat;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class DspTmplatFrResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = -6314214717137198463L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** The dsp cnr. */
	DspCnr dspCnr;
    
    /** 템플릿 정보 */
	DspTmplat dspTmplat;
	
	/** 템플릿 연결 코너정보 */
	DspCnrTmplatInfoCnnc dspCnrTmplatInfoCnnc;
	
	/** 카테고리/테마페이지 기본정보 */
	DspCtgry dspCtgry;
	DspCtgryLang dspCtgryLang;
	DspBrndCtgry dspBrndCtgry;
	
	/** 기획전 기본정보 */
	DspPromt dspPromt;
	DspPromtLang dspPromtLang;

	DspPromtImg dspPromtImg;
	DspPromtImgLang dspPromtImgLang;
	
	/** S-Trnd 기본정보 */
	DspStrnd dspStrnd;
	DspStrndLang dspStrndLang;
	String brndNm;
	String sesonNm;
	
	/** 이벤트 기본정보 */
	Evt evt;
	
	//기획전
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
