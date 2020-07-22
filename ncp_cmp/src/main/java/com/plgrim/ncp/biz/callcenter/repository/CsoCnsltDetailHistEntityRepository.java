package com.plgrim.ncp.biz.callcenter.repository;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltDetailHist;
import com.plgrim.ncp.base.repository.cso.CsoCnsltDetailHistRepository;

@Repository
public class CsoCnsltDetailHistEntityRepository extends
        CsoCnsltDetailHistRepository {

	@Override
    public CsoCnsltDetailHist selectCsoCnsltDetailHist(
            CsoCnsltDetailHist csoCnsltDetailHist) {
	    // TODO Auto-generated method stub
	    return super.selectCsoCnsltDetailHist(csoCnsltDetailHist);
    }

	@Override
    public void insertCsoCnsltDetailHist(CsoCnsltDetailHist csoCnsltDetailHist)
            {
	    // TODO Auto-generated method stub
	    super.insertCsoCnsltDetailHist(csoCnsltDetailHist);
    }

	@Override
    public int updateCsoCnsltDetailHist(CsoCnsltDetailHist csoCnsltDetailHist)
            {
	    // TODO Auto-generated method stub
	    return super.updateCsoCnsltDetailHist(csoCnsltDetailHist);
    }

	@Override
    public int deleteCsoCnsltDetailHist(CsoCnsltDetailHist csoCnsltDetailHist)
            {
	    // TODO Auto-generated method stub
	    return super.deleteCsoCnsltDetailHist(csoCnsltDetailHist);
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
