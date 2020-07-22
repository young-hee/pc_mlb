package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysGrpCdCdCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysGrpCdExtend;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysCdDTO  extends AbstractDTO {

	
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	/**
	 * 공통그룹코드
	 */
    SysGrpCdExtend sysGrpCd;

    /**
     * 공통그룹 상세코드
     */
	SysGrpCdCdCnncExtend sysGrpCdCdCnnc;

}
