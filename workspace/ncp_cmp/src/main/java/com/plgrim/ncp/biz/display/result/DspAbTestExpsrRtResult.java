package com.plgrim.ncp.biz.display.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * DspAbTestExpsrRtResult
 * @author eunseo1.park
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class DspAbTestExpsrRtResult extends AbstractResult {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	String abTestNm;
	int abTestSn;
	String progrsPsbYn;
	String setUseYn;
	String setExpsrMenmthdCd;
	String abTestDscr;
	String begDt;
	String endDt;
	int setTurn;
	float setExpsrRt;
	int revSn;
	int revCpstTurn;
	String abTestGrpCd;
	String abTestRevDscr;
	int modTurn;
	float revExpsrRt;
	float expsrRt;
	String modBegDt;
	String modEndDt;
	String modRegDt;
	int lastSetTurn;
	int lastModTurn;
	String modResnCont;
	String modRegtrID;
}
