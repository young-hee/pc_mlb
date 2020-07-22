package com.plgrim.ncp.commons.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPrdlstWt;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysPrudWtResult extends AbstractResult {

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

	/**
	 * 
	 */
    private static final long serialVersionUID = 2891203045485644739L;
    
    SysPrdlstWt sysPrdlstWt;
    //private Long godWt; // 무게
    //private java.math.BigDecimal godWt;
    //private String sesonGrpCd;//시즌정보
    //private String prdlstCd;
    private String prdlstBrndNm;
    private String prdlstBrndId;
    private String useYnNm;

}
