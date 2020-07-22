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

@Data
@EqualsAndHashCode(callSuper=false)
public class DspCtgryBrndFrResult extends AbstractResult {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = -4012441265850877117L;
	
	String dspCtgryNo;
	
	String brndId;
	
	String brndNm;
	
	String pcBrndImgFileNm;
    String pcBrndImgFileUrl;
    String pcBrndImgAltrtvCont;
    String mobileBrndImgFileNm;
    String mobileBrndImgFileUrl;
    String mobileBrndImgAltrtvCont;
    String pcixeBrndImgFileNm;
    String pcixeBrndImgFileUrl;
    String pcixeBrndImgAltrtvCont;
    String mbixeBrndImgFileNm;
    String mbixeBrndImgFileUrl;
    String mbixeBrndImgAltrtvCont;
    
    String myBrnd;
	
    String odrIdx;

    /* 카테고리 출력 유형 코드 */
    String ctgryOutptTpCd;

    /* 출력 구분 코드 */
    String outptSectCd;

    /* 출력 링크 URL */
    String outptLinkUrl;

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
