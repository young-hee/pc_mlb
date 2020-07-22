package com.plgrim.ncp.biz.callcenter.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstAns;
import com.plgrim.ncp.base.repository.cso.CsoJobRequstAnsRepository;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.google.common.collect.Maps;

@Repository
public class CsoJobRequstAnsEntityRepository extends CsoJobRequstAnsRepository {

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
	
	/*public void insertCsoJobRequstAnsWithGenTurn(CsoJobRequstAns csoJobRequstAns) throws Exception {
		// TURN 증가
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("REQUST_SN", csoJobRequstAns.getRequstSn());
		
		int getMaxTurn = idGenService.generateDBOrder(getSession1(), "CSO_JOB_REQUST_ANS", "JOB_REQUST_ANS_TURN", conditions, DatabaseType.ORACLE);
		csoJobRequstAns.setJobRequstAnsTurn(getMaxTurn);
		
		//insertCsoJobRequstAns(csoJobRequstAns);
		getSession1().insert()

		
	}*/
	

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
