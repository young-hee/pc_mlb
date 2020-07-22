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
 * @since       2015. 4. 17       
 */
package com.plgrim.ncp.biz.display.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspBrndCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRevCpst;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Instantiates a new dsp ctgry result.
 */
@Data

/**
 * [메서드 설명].
 * 
 * <p/>
 * 
 * [사용 방법 설명].
 *
 * @return Int [설명]
 * @since 2015. 4. 3
 */

@EqualsAndHashCode(callSuper = false)
public class DspCtgryBoResult extends AbstractResult {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** The dsp ctgry. */
	DspCtgry dspCtgry;
	
	/** The dsp ctgry lang. */
	DspCtgryLang dspCtgryLang;
	
	String langCdNm;
	
	String langCdI;
	
	String langCdMeta;

	String strDvcSectCd;
	
	String strTmplatSn;
	
	String strTmplatNm;
	
	String strTmplatCnncUrl;
	
	String[] arLangCdNm;
	
	String[] arLangCdI;
	
	String[] arLangCdMeta;

	String[] arDspCtgryNm;
	
	String[] arRprstImgFileNm;
	
	String[] arRprstImgAltrtvCont;
	
	String[] arRprstImgFileUrl;
	
	String[] arDvcSectCd;
	
	String[] arTmplatSn;
	
	String[] arTmplatNm;
	
	String[] arTmplatCnncUrl;

	String[] arMetaSjNm;

	String[] arMetaDscrCont;

	String[] arMetaKwd;

	String upperDspCtgryNm;
	
	String outptSectCdNm;
	
	String regtrNm;
	
	String udterNm;
	
	String dspCtgryPath;
	
	String chiDspCtgryPath;
	
	String dvcSectCdPc;
	
	String tmplatNmPc;
	
	String tmplatCnncUrlPc;
	
	String dvcSectCdMb;

	String tmplatNmMb;
	
	String tmplatCnncUrlMb;
	
	String dspBrndNm;
	
	String newIcon;
	
	String abYn;

	Integer childCnt;

	Integer saleGodCnt;

	Integer totalGodCnt;
	
	/** 전시브랜드 카테고리 */
	DspBrndCtgry dspBrndCtgry;
	String brUdterNm;
	
	/** 전시개정구성  */
	DspRevCpst dspRevCpst;

	/** 전시개정구성 브랜드 */
	DspRevCpst dspRevCpstBrnd;

	String brDvcSectCdPc;

	String brTmplatNmPc;

	String brTmplatCnncUrlPc;

	String brDvcSectCdMb;

	String brTmplatNmMb;

	String brTmplatCnncUrlMb;

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
	public String[] getArDspCtgryNm (){
		String s = dspCtgry.getDspCtgryNm();
		this.arDspCtgryNm = s.split(",");
		
		return this.arDspCtgryNm;
	}
	
	public String[] getArLangCdNm (){
		if(this.langCdNm != null) {
			this.arLangCdNm = this.langCdNm.split(",");
		}
		
		return this.arLangCdNm;
	}
	
	public String[] getArLangCdI (){
		if(this.langCdI != null) {
			this.arLangCdI = this.langCdI.split(",");
		}
		
		return this.arLangCdI;
	}
	
	public String[] getArLangCdMeta (){
		if(this.langCdMeta != null) {
			this.arLangCdMeta = this.langCdMeta.split(",");
		}

		return this.arLangCdMeta;
	}

	public String[] getArRprstImgFileNm (){
		String s = dspCtgry.getRprstImgFileNm();
		if(s != null) {
			this.arRprstImgFileNm = s.split(",");
		}
		
		return this.arRprstImgFileNm;
	}
	
	public String[] getArRprstImgAltrtvCont (){
		String s = dspCtgry.getRprstImgAltrtvCont();
		if(s != null) {
			this.arRprstImgAltrtvCont = s.split(",");
		}
		
		return this.arRprstImgAltrtvCont;
	}
	
	public String[] getArRprstImgFileUrl (){
		String s = dspCtgry.getRprstImgFileUrl();
		if(s != null) {
			this.arRprstImgFileUrl = s.split(",");
		}
		
		return this.arRprstImgFileUrl;
	}
	
	public String[] getArDvcSectCd (){
		String s = this.strDvcSectCd;
		if(s!=null) {
			this.arDvcSectCd = s.split(",");
		}
		
		return this.arDvcSectCd;
	}
	
	public String[] getArTmplatSn (){
		String s = this.strTmplatSn;
		if(s!=null) {
			this.arTmplatSn = s.split(",");
		}
		
		return this.arTmplatSn;
	}
	
	public String[] getArTmplatNm (){
		String s = this.strTmplatNm;
		if(s!=null) {
			this.arTmplatNm = s.split(",");
		}
		
		return this.arTmplatNm;
	}
	
	public String[] getArTmplatCnncUrl (){
		String s = this.strTmplatCnncUrl;
		if(s!=null) {
			this.arTmplatCnncUrl = s.split(",");
		}
		
		return this.arTmplatCnncUrl;
	}

	public String[] getArMetaSjNm (){
		String s = this.dspCtgry.getMetaSjNm();
		if(s!=null){
			this.arMetaSjNm = s.split("[$]");
		}
		return this.arMetaSjNm;
	}

	public String[] getArMetaDscrCont (){
		String s = this.dspCtgry.getMetaDscrCont();
		if(s!=null){
			this.arMetaDscrCont = s.split("[$]");
		}
		return this.arMetaDscrCont;
	}

	public String[] getArMetaKwd (){
		String s = this.dspCtgry.getMetaKwd();
		if(s!=null){
			this.arMetaKwd = s.split("[$]");
		}
		return this.arMetaKwd;
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
