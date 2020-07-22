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
 * @since       2015. 10. 13       
 */
package com.plgrim.ncp.biz.display.result;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrContt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSet;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSetLang;
import com.plgrim.ncp.biz.display.data.DspCnrConttExt;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class DspCnrSetFrResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = 5489690913358055230L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    Long cnrSn;
    
    Long cnrSetSn;

    Long cnrTpGrpSn;
    
    Integer sortSeq;

    String cnrConttTpGrpNm;

    /** 전시코너세트 기본정보 */
    DspCnrSet dspCnrSet;
    /** 전시코너세트언어 정보 */
    DspCnrSetLang dspCnrSetLang;
     
    List<DspCnrContt> cnrConttList;
    
    List<DspCnrConttExt> cnrConttExList;
    
    List<DspCnrConttExt> rightShapeList;
    
    List<DspCtgryGodFoResult> cnrGodConttList;
    
    List<DspCnrConttFrResult> dspCnrConttList;
    
    /** 컨텐츠 HTML 목록. */
    List<DspCnrConttFrResult> dspCnrConttHtmlList;
    
    /** 컨텐츠 이미지 배너목록. */
    List<DspCnrConttFrResult> dspCnrConttImgList;
    
    /** 컨텐츠 동영상 목록. */
    List<DspCnrConttFrResult> dspCnrConttMoviList;
    
    /** 컨텐츠 텍스트 목록. */
    List<DspCnrConttFrResult> dspCnrConttTextList;
    
    /** 컨텐츠 상품 목록. */
    List<DspCnrConttFrResult> dspCnrConttGodList;
    
    /** 컨텐츠 기획전 목록. */
    List<DspCnrConttFrResult> dspCnrConttPromtList;
    
    /** 컨텐츠 STrend 목록. */
    List<DspCnrConttFrResult> dspCnrConttStrndList;
    
    
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
