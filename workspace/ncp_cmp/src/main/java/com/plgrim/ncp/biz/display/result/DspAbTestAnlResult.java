package com.plgrim.ncp.biz.display.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * DspAbTestAnlResult
 * @author eunseo1.park
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class DspAbTestAnlResult extends AbstractResult {

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
	int setTurn;
	int revSn;
	int revCpstTurn;
	int modTurn;
	int pv;
	int uv;
	int buyerCnt;
	int salesAmt;		
	float cr;
	float ctr;
	String setUseYn;
	String abTestGrpCd;
	String abTestRevDscr;
	String tmplatSn;
	String tmplatNm;
	String revSectCd;
	String regDt;
	String dspCnrNm;
	int clickQty;
	String deviceType;
	String searchStartDate;
	String searchEndDate;
	BigDecimal expsrRt;
	int engConttCnt;
	int chiConttCnt;
	String useYn;
	String sttDate;
	String langCd;
	
}
