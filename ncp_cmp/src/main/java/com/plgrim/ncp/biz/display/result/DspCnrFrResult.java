package com.plgrim.ncp.biz.display.result;

import java.util.List;

import org.springframework.data.domain.Page;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnr;
import com.plgrim.ncp.biz.display.data.DspCnrConttExt;
import com.plgrim.ncp.biz.display.data.DspCnrConttExtResult;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class DspCnrFrResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = -6314214717137198463L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    String dspCtgryNo;
    
    Long cnrSn;
    
    Integer cnrSortSeq;
    
    Long cnrTpGrpSn;
    
    String dspCnrNm;
    
    /** 전시코너 기본정보 */
    DspCnr dspCnr;
    /** 전시코너세트 목록 */
    List<DspCnrSetFrResult> dspCnrSetList;   
    
    List<DspCnrConttExt> cnrBnrConttList;
    
    List<DspCnrConttExt> cnrGodConttList;
    
    List<DspCnrConttExt> cnrConttList;
    
    Page<DspCnrConttExtResult> cnrConttListByPaging;
    
    List<DspCnrFrResult> cnrMainConttList;
    
    List<DspCnrFrResult> newArrivalList;
    
    List<DspCnrFrResult> dspCnrList;

    private java.lang.Integer cnrSetBaseDspCnt;
 
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
