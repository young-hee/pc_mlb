
package com.plgrim.ncp.biz.callcenter.repository;
import java.util.Map;

import com.plgrim.ncp.commons.util.BOSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoJobRequstAtchmnfl;
import com.plgrim.ncp.base.repository.cso.CsoJobRequstAtchmnflRepository;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.google.common.collect.Maps;

@Repository
public class CsoJobRequstAtchmnflEntityRepository extends CsoJobRequstAtchmnflRepository {

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
	
	public void insertCsoJobRequstAtchmnflWithGenTurn(CsoJobRequstAtchmnfl csoJobRequstAtchmnfl) throws Exception {
		// TURN 증가
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("REQUST_SN", csoJobRequstAtchmnfl.getRequstSn());
		
		int getMaxTurn = idGenService.generateDBOrder(getSession1(), "CSO_JOB_REQUST_ATCHMNFL", "REQUST_ATCHMNFL_TURN", conditions, DatabaseType.ORACLE);
		csoJobRequstAtchmnfl.setRequstAtchmnflTurn(getMaxTurn);
		csoJobRequstAtchmnfl.setRegtrId(BOSecurityUtil.getLoginId());
		csoJobRequstAtchmnfl.setUdterId(BOSecurityUtil.getLoginId());

		insertCsoJobRequstAtchmnfl(csoJobRequstAtchmnfl);
		
	}


	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
