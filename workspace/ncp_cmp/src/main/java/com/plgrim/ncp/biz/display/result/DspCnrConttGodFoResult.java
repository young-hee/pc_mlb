package com.plgrim.ncp.biz.display.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspGodPrc;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
@Data
@EqualsAndHashCode(callSuper=false)
public class DspCnrConttGodFoResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = 7048830595256758887L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    God god;
    /** 전시상품가격 */
    DspGodPrc dspGodPrc;
    /** 브랜드 정보 */
    SysBrnd sysBrnd;
    /** 상품 언어별 상품 명 */
    GodLangbyGodNm godLangbyGodNm;
    /** 상품 이미지 정보 */
    List<GodImg> godImgList;
    /** 상품 아이콘 목록 */
    List<GodIconFoResult> godIconList;
    
    String imgFrontUrl;
    String imgBackUrl;
    String imgChoiceUrl;
    String mnfcturDate;
    
    /** 상품 Color 목록 */
    List<GodColorFoResult> godColorList;
    /** 상품 주문수량  */
    Integer ordCnt;
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
