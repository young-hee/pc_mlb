package com.plgrim.ncp.biz.display.result;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspGodPrc;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtGod;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtSprtr;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspPlanGodFoResult  extends AbstractResult{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** 전시 기획전 엔티티 */
	DspPromt dspPromt;
	
	/** 전시 기획전 언어 엔티티 */
	DspPromtLang dspPromtLang;
	
	/** 전시 기획전 구분자정보*/
	private DspPromtSprtr dspPromtSprtr;
	
	/** 전시 기획전 상품 정보*/
	private DspPromtGod dspPromtGod;
	
	/** 상품정보*/
	private God god;
	
	/** 언어별 상품 정보*/
	private GodLangbyGodNm godLangbyGodNm;
	
	/**상품 이미지 정보*/
	private GodImg godImg;
	
	/** 전시 상품 가격 정보*/
	private DspGodPrc dspGodPrc;
	
	private List<GodImg> godImgList;
	
	private List<GodIconFoResult> godIconList;
	
	private SysBrnd sysBrnd;
	
	private int rowNo;
	
	private String godTotalRow;

	private List<GodImg> godThmbImgList;// 상품 대표 이미지
	
	String frontImage3;
	String backImage3;
	String frontImage4;
	String backImage4;
	String mnfcturDate;
	String sesonGrpNm;

    /* 상품 아이콘 관련 변수 - s */
	private String seasonInfo;
    /* 상품 아이콘 관련 변수 - e */
	
	
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

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
