package com.plgrim.ncp.biz.callcenter.repository;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetailJobTp;
import com.plgrim.ncp.base.repository.cso.CsoCnsltDetailJobTpRepository;

@Repository
public class CsoCnsltDetailJobTpEntityRepository extends
        CsoCnsltDetailJobTpRepository {

	@Override
    public CsoCnsltDetailJobTp selectCsoCnsltDetailJobTp(
            CsoCnsltDetailJobTp csoCnsltDetailJobTp) {
	    // TODO Auto-generated method stub
	    return super.selectCsoCnsltDetailJobTp(csoCnsltDetailJobTp);
    }

	@Override
    public void insertCsoCnsltDetailJobTp(
            CsoCnsltDetailJobTp csoCnsltDetailJobTp) {
	    // TODO Auto-generated method stub
	    super.insertCsoCnsltDetailJobTp(csoCnsltDetailJobTp);
    }

	@Override
    public int updateCsoCnsltDetailJobTp(CsoCnsltDetailJobTp csoCnsltDetailJobTp)
            {
	    // TODO Auto-generated method stub
	    return super.updateCsoCnsltDetailJobTp(csoCnsltDetailJobTp);
    }

	@Override
    public int deleteCsoCnsltDetailJobTp(CsoCnsltDetailJobTp csoCnsltDetailJobTp)
            {
	    // TODO Auto-generated method stub
	    return super.deleteCsoCnsltDetailJobTp(csoCnsltDetailJobTp);
    }
	
	public int getMaxJobTpTurn(CsoCnsltDetailJobTp csoCnsltDetailJobTp){
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mtm.getMaxJobTpTurn", csoCnsltDetailJobTp);
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
