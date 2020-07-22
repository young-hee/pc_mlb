package com.plgrim.ncp.biz.callcenter.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstOrdGod;
import com.plgrim.ncp.base.repository.cso.CsoJobRequstOrdGodRepository;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.google.common.collect.Maps;

@Repository
public class CsoJobRequstOrdGodEntityRepository extends CsoJobRequstOrdGodRepository {
	
	@Autowired
	IdGenService idGenService;
	
	public void insertCsoJobRequstOrdGodWithGenTurn(CsoJobRequstOrdGod csoJobRequstOrdGod) throws Exception {
		
		// TURN 증가
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("REQUST_SN", csoJobRequstOrdGod.getRequstSn());
		
		int getMaxTurn = idGenService.generateDBOrder(getSession1(), "CSO_JOB_REQUST_ORD_GOD", "REQUST_ORD_GOD_TURN", conditions, DatabaseType.ORACLE);
		csoJobRequstOrdGod.setRequstOrdGodTurn(getMaxTurn);

		insertCsoJobRequstOrdGod(csoJobRequstOrdGod);
	}

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

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
