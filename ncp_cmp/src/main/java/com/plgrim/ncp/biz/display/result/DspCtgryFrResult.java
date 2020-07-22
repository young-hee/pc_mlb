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
 * @since       2015. 11. 5       
 */
package com.plgrim.ncp.biz.display.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspBrndCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryLang;
import com.plgrim.ncp.framework.commons.StringService;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspCtgryFrResult extends AbstractResult {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = -4012441265850877117L;
	
	/** 전시카테고리 엔티티 */
	DspCtgry dspCtgry;
	/** 전시카테고리 언어 엔티티 */
	DspCtgryLang dspCtgryLang;
	
	DspBrndCtgry dspBrndCtgry;
	
	/** Location Information */
	String locationUrl;
	String locationName;
	
	String brandUpperDspCtgryNo;
	
	Integer godCnt;
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
	public DspCtgry getDspCtgry() {
		return changeDspCtgryLang(dspCtgry, dspCtgryLang);
	}
	
	private DspCtgry changeDspCtgryLang(DspCtgry ctgry, DspCtgryLang ctgryLang){
		if(ctgry != null && ctgryLang != null){
			if(StringService.isNotEmpty(ctgryLang.getDspCtgryNm())) {
				ctgry.setDspCtgryNm(ctgryLang.getDspCtgryNm());
			}
			if(StringService.isNotEmpty(ctgryLang.getRprstImgFileNm())) {
				ctgry.setRprstImgFileNm(ctgryLang.getRprstImgFileNm());
				ctgry.setRprstImgFileUrl(ctgryLang.getRprstImgFileUrl());
				ctgry.setRprstImgAltrtvCont(ctgryLang.getRprstImgAltrtvCont());
			}
		}
		
		return ctgry;
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
