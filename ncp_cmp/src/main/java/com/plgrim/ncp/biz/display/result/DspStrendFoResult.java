package com.plgrim.ncp.biz.display.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrnd;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrndLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspTmplat;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspStrendFoResult extends AbstractResult {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**strend 정보*/
 	private DspStrnd dspStrnd;
 	
 	private String nm;
 	
 	/**strend 언어 정보*/
 	private DspStrndLang dspStrndLang;
	
 	/**전시 템플릿 정보*/
 	private DspTmplat dspTmplat;
 	
 	/**브랜드 정보*/
	private SysBrnd sysBrnd;
	
	/**카테코리 정보*/
	private DspCtgry dspCtgry;
	
	/**상품 정보(추천)*/
	private DspCommonGodFrResult recommendGoods;
	
	private int rowNo;
	
	private String trndNm;
	
	private String sesonNm;
	
	private String title1;
	
	private String title2;
	
	private String registDt;
 	
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
