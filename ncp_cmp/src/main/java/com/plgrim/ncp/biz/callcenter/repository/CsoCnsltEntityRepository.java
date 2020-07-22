package com.plgrim.ncp.biz.callcenter.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnslt;
import com.plgrim.ncp.base.repository.cso.CsoCnsltRepository;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;

@Repository
public class CsoCnsltEntityRepository extends CsoCnsltRepository {
	
	@Autowired
	IdGenService idGenService;


	
	public Long insertCsoCnsltWithGenSn(CsoCnslt csoCnslt) throws Exception {
		Long maxSn = Long.parseLong(idGenService.generateDBSequence(getSession1(), "SQ_CSO_CNSLT", DatabaseType.ORACLE)+"");
		csoCnslt.setCnsltSn(maxSn);
		insertCsoCnslt(csoCnslt);
		return maxSn;
	}

	public CsoCnslt getInqryCnslt(Long mtmInqSn) {
		CsoCnslt result = getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.getInqryCnslt", mtmInqSn);
	    return result;
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
