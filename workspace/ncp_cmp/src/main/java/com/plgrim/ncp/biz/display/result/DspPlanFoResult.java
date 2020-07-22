package com.plgrim.ncp.biz.display.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnr;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtBrnd;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtExtend;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtImg;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtImgLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspTmplat;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.biz.display.data.DspPromtSprtrExt;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspPlanFoResult extends AbstractResult{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/** 전시 기획전 엔티티 */
	private DspPromt dspPromt;
	
	private DspPromtExtend dspPromtExtend;
	
	/** 전시 기획전 언어 엔티티 */
	private DspPromtLang dspPromtLang;

	/** 전시 기획전 엔티티 V2 */
	private DspPromtImg dspPromtImg;
	/** 전시 기획전 언어 엔티티 V2 */
	private DspPromtImgLang dspPromtImgLang;
	
	/**기획전 브랜드 정보*/
	private DspPromtBrnd dspPromtBrnd;
	
	/** 전시 카테고리 엔티티 */
	private DspCtgry dspCtgry;
	/** 전시 카테고리 언어 엔티티 */
	private DspCtgryLang dspCtgryLang;
	
	/** 전시 템플릿 정보*/
	private DspTmplat dspTmplat;
	
	/** 전시 코너 정보*/
	private DspCnr dspCnr;
	
	/** 시스템 브랜드 엔티티*/
	private SysBrnd sysBrnd;
	
	/**카테고리별 카운트 */
	private int dspPlanCtgryCnt;
	
	/**브랜드별 카운트 */
	private int dspPlanBrndCnt;
	
	/** 상위 브랜드 명*/
	private String upperBrndNm;
	
	private String promtSectNm;
	
	private long totalRow;
	
	private int rowNo;
	
	private String beginDate;
	
	private String endDate;
	
	private java.lang.Long promtSn;
	
	private Long cnrSn;
	  
	private Long cnrTpGrpSn;
	
	private String dspCnrNm;
	
	private String dspCnrSectCd;
	 
	private List<DspPromtSprtrExt> sprtrList;
	 
	private List<DspPromtSprtrExt> textList;
	
	private List<DspPromtSprtrExt> imgList;
	
	private List<DspCnrSetFrResult> dspCnrSetList;   
	
	private List<DspPlanFoResult> planCornerList;   
	private List<DspPlanFoResult> styleList;   
	private String evtNo;
	private String promtGrpSn;
	private String evtNm;
	private String evtTycd;
	private java.util.Date evtBegDt;
	private java.util.Date evtEndDt;
	private String pcImgFileUrl;
	private String pcImgExpsrTxt1Cont;
	private String pcImgExpsrTxt2Cont;
	private String pcImgAltrtvCont;
	private String moImgFileUrl;
	private String moImgExpsrTxt1Cont;
	private String moImgExpsrTxt2Cont;
	private String moImgAltrtvCont;
	private String pcUrl;
	private String mobileUrl;
	private String evtTp;
	
	private List<DspPlanFoResult> discovererPlanListByGroup;
	
	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;
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
