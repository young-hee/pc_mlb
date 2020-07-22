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
 * @since       2015. 4. 16       
 */
package com.plgrim.ncp.biz.display.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspBrndCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRevCpst;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspCtgryBoDTO extends AbstractDTO {
    private static final long serialVersionUID = -47385681539238369L;
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

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
	/** The dsp ctgry. */
	DspCtgry dspCtgry;
	
	DspCtgryLang dspCtgryLang;
	
	String scAplMallId;
	
	String scDspCtgryNo;
	
	String scDspYn;
	
	String scSpcifyUrlDspYn;
	
	String scCtgryNo;
	String[] arDspCtgryNo;
	
	String scDspCtgryNm;
	
	String scLeafYn;
	
	String scCtgryDivLneYn;
	
	String scMoviTurn;
	
	String upperDspCtgryNo;
	
	String dspCtgryNo;
	
	String[] arDspCtgryNm;
	
	String[] arLangCdNm;
	
	String[] arLangCdI;
	
	String[] arRprstImgAltrtvCont;
	
	String[] arRprstImgFileNm;
	
	String[] arRprstImgFileUrl;

	String[] arMetaSjNm;

	String[] arMetaDscrCont;

	String[] arMetaKwd;


	String[] arTmplatSn;
	String[] arTmplatNm;
	String[] arTmplatCnncUrl;
	
	String orderBy;
	
	String scLangCd;
	
	
	String scDspBrndId;
	
	String selBrndId;
	
	DspBrndCtgry dspBrndCtgry;
	
	String scErpGodNo;
	String scGodNm;
	String scGodNo;
	String scSesonCd;
	String scPoYn;
	
	String scExclSort;
	String ctgSectCd;
	String leafYn;

	String scRevSn;

	DspRevCpst dspRevCpst;

	DspRevCpst dspRevCpstBrnd;
	
	
	String endpBrndId;
	
	String scLeafDspCtgryNo;
	
	/** 전시여부. */
    private String godDspYn;
    
    /** 카테고리전시여부. */
    private String dspCtgryCnncGodDspYn;
	
	/** 승인코드. */
    private String godAprvSectCd;
	
	/** 상품승인거부 Y/N */
    private String aprvRejYn;
	
	public String[] getArDspCtgryNo() {
		
		if(scCtgryNo==null||"".equals(scCtgryNo)) {
			this.arDspCtgryNo = null;
		}
		else {
			this.arDspCtgryNo = scCtgryNo.split("\r\n");			
		}
		return this.arDspCtgryNo;
	}
	
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}

