package com.plgrim.ncp.biz.display.data;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrContt;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("dspCnrConttExt")
public class DspCnrConttExt extends DspCnrContt {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = 1L;
	private String errMsg;
	private String godNo;

	private String godNm;

	private String godSaleSectCd;
	/**
	 * 정소가
	 */
	private java.math.BigDecimal rtlPrc;
	
	private java.math.BigDecimal lastSalePrc;
	
	private java.math.BigDecimal csmrPrc;

	private String imgFrontUrl;

	private String imgBackUrl;

	private String imgBcrnColorCd;

	private String optValCd1;
	
	private String optValCd2;
	
	private String optValCd3;

	private String dspCnrNm;

	private String godUrl;
	private String scDspTgtLang;

	private int wishListCount;

	private String wishListYn;
	
	private String tagNm;
	private String colorTagNm;
	
	private String brndId;
	
	private String dspCtgryNo;
	
	private String dspCtgryNm;
	
	private List<DspCnrConttExt> dspCnrConttExtList;
	
	private String detailImgFrontUrl;
	
	
	private String sprtrTurn;
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * --------------------------------------------------------------------- public
	 * & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * --------------------------------------------------------------------- PRIVATE
	 * method. ---------------------------------------------------------------------
	 */

}
