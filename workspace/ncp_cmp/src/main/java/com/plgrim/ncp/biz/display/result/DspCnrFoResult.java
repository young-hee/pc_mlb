package com.plgrim.ncp.biz.display.result;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnr;
@Data
@EqualsAndHashCode(callSuper=false)
public class DspCnrFoResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = -6314214717137198463L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 전시코너 기본정보 */
    DspCnr dspCnr;
    /** 전시코너세트 목록 */
    List<DspCnrSetFoResult> dspCnrSetList;
    
    boolean hasDspContt;
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
    public boolean isHasContents(){
    	boolean returnValue = false;
    	if(dspCnrSetList != null && dspCnrSetList.size() > 0){
        	for(DspCnrSetFoResult cnrSetResult:dspCnrSetList){
        		if(cnrSetResult != null && cnrSetResult.getDspCnrConttMap() != null && cnrSetResult.getDspCnrConttMap().size() > 0){
            		Map<String,List<DspCnrConttFoResult>> map = cnrSetResult.getDspCnrConttMap();
					for(String key : map.keySet()){
						if(map.get(key) != null && map.get(key).size() > 0){
                            returnValue = true;
                            break;
            		    }
                    }
                }
            }
    	}
    	return returnValue;
    }
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
