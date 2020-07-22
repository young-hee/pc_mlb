package com.plgrim.ncp.biz.callcenter.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequst;
import com.plgrim.ncp.base.repository.cso.CsoJobRequstRepository;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;

@Repository
public class CsoJobRequstEntityRepository extends CsoJobRequstRepository {

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

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
	
	@Autowired
	IdGenService idGenService;
	
	public void insertCsoJobRequstWithGenSn(CsoJobRequst csoJobRequst) throws Exception {
		// SN 증가
		Long getMaxSn = Long.parseLong(idGenService.generateDBSequence(getSession1(), "SQ_CSO_JOB_REQUST", DatabaseType.ORACLE)+"");
		csoJobRequst.setRequstSn(getMaxSn);
		
		insertCsoJobRequst(csoJobRequst);
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
