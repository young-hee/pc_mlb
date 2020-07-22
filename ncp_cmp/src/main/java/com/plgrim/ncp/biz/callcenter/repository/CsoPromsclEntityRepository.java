package com.plgrim.ncp.biz.callcenter.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoPromscl;
import com.plgrim.ncp.base.repository.cso.CsoPromsclRepository;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;

@Repository
public class CsoPromsclEntityRepository extends CsoPromsclRepository {

	@Autowired
	IdGenService idGenService;
	
	@Override
    public CsoPromscl selectCsoPromscl(CsoPromscl csoPromscl) {
	    // TODO Auto-generated method stub
	    return super.selectCsoPromscl(csoPromscl);
    }

	@Override
    public void insertCsoPromscl(CsoPromscl csoPromscl) {
	    // TODO Auto-generated method stub		
		//csoPromscl.setPromsclSn(Long.parseLong(idGenService.generateDBSequence(getSession1(), "SQ_CSO_PROMSCL", DatabaseType.ORACLE)+""));
		csoPromscl.setPromsclSn(idGenService.generateDBSequence(getSession1(), "SQ_CSO_PROMSCL", DatabaseType.ORACLE));


	    super.insertCsoPromscl(csoPromscl);
    }

	@Override
    public int updateCsoPromscl(CsoPromscl csoPromscl) {
	    // TODO Auto-generated method stub
	    return super.updateCsoPromscl(csoPromscl);
    }

	@Override
    public int deleteCsoPromscl(CsoPromscl csoPromscl) {
	    // TODO Auto-generated method stub
	    return super.deleteCsoPromscl(csoPromscl);
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
