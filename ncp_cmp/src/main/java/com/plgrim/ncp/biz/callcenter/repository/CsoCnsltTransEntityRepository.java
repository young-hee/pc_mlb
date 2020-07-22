package com.plgrim.ncp.biz.callcenter.repository;

import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTrans;
import com.plgrim.ncp.base.repository.cso.CsoCnsltTransRepository;

import java.util.Map;

@Repository
public class CsoCnsltTransEntityRepository extends CsoCnsltTransRepository {

    @Autowired
    IdGenService idGenService;

    public void insertCsoCnsltTransWithGenTurn(CsoCnsltTrans csoCnsltTrans) throws Exception {

        Map<String, Object> conditions = Maps.newHashMap();

        csoCnsltTrans.getCnsltSn();
        conditions.put("CNSLT_SN", csoCnsltTrans.getCnsltSn());

        int maxTurn = idGenService.generateDBOrder(getSession1(), "CSO_CNSLT_TRANS", "TRANS_REQUST_TURN", conditions, DatabaseType.ORACLE);
        csoCnsltTrans.setTransRequstTurn(maxTurn);

        insertCsoCnsltTrans(csoCnsltTrans);

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
