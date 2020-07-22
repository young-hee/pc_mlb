package com.plgrim.ncp.commons.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysGrpCdCdCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysGrpCdExtend;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysCodeResult extends AbstractResult {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * UID
	 */

    private static final long serialVersionUID = -6655325365564354058L;
    
	/**
	 * 공통그룹코드
	 */
    SysGrpCdExtend sysGrpCd;

    /**
     * 공통그룹 상세코드
     */
	SysGrpCdCdCnncExtend sysGrpCdCdCnnc;
	
}
