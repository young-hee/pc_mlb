package com.plgrim.ncp.biz.callcenter.repository;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTransOrdGod;
import com.plgrim.ncp.base.repository.cso.CsoCnsltTransOrdGodRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 2015-04-13.
 */
@Repository
public class CsoCnsltTransOrdGodEntityRepository extends CsoCnsltTransOrdGodRepository {

    @Override
    public CsoCnsltTransOrdGod selectCsoCnsltTransOrdGod(CsoCnsltTransOrdGod csoCnsltTransOrdGod) {
        return super.selectCsoCnsltTransOrdGod(csoCnsltTransOrdGod);
    }

    @Override
    public void insertCsoCnsltTransOrdGod(CsoCnsltTransOrdGod csoCnsltTransOrdGod) {
        super.insertCsoCnsltTransOrdGod(csoCnsltTransOrdGod);
    }

    @Override
    public int updateCsoCnsltTransOrdGod(CsoCnsltTransOrdGod csoCnsltTransOrdGod) {
        return super.updateCsoCnsltTransOrdGod(csoCnsltTransOrdGod);
    }

    @Override
    public int deleteCsoCnsltTransOrdGod(CsoCnsltTransOrdGod csoCnsltTransOrdGod) {
        return super.deleteCsoCnsltTransOrdGod(csoCnsltTransOrdGod);
    }
}
