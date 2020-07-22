package com.plgrim.ncp.biz.callcenter.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstAnsAtchmnfl;
import com.plgrim.ncp.base.repository.cso.CsoJobRequstAnsAtchmnflRepository;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.google.common.collect.Maps;

@Repository
public class CsoJobRequstAnsAtchmnflEntityRepository extends CsoJobRequstAnsAtchmnflRepository {

	@Autowired
	IdGenService idGenService;
	
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
	
	public void insertCsoJobRequstAtchmnflWithGenTurn(CsoJobRequstAnsAtchmnfl csoJobRequstAnsAtchmnfl) throws Exception {
		// TURN 증가
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("REQUST_SN", csoJobRequstAnsAtchmnfl.getRequstSn());

		int getMaxTurn = idGenService.generateDBOrder(getSession1(), "CSO_JOB_REQUST_ATCHMNFL", "REQUST_ATCHMNFL_TURN", conditions, DatabaseType.ORACLE);
		csoJobRequstAnsAtchmnfl.setRequstAnsAtchmnflTurn(getMaxTurn);
		
		insertCsoJobRequstAnsAtchmnfl(csoJobRequstAnsAtchmnfl);
		
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
