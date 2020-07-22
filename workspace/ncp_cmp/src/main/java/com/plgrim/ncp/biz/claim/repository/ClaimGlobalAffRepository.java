package com.plgrim.ncp.biz.claim.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plgrim.ncp.biz.claim.data.InfAffClmDTO;
import com.plgrim.ncp.biz.claim.data.LgsDlvExtendForClm;
import com.plgrim.ncp.biz.claim.result.*;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdClmGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrm;
import com.plgrim.ncp.framework.commons.IdGenService;

/**
 * @author aether
 *
 */

@Repository
@Slf4j
public class ClaimGlobalAffRepository extends AbstractRepository {
	
	@Autowired
	IdGenService idGenService;

	public List<InfAffClmDTO> selectInfAffClm(InfAffClmDTO infAffClmSearch) {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.infAffClm.selectInfAffClm", infAffClmSearch);
    }

	public List<InfOrdGodErpDstbExtend> selectOrdGodErpBaseList(
            InfAffClmDTO infAff) {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.infAffClm.selectOrdGodErpBaseList", infAff);
    }

	public List<OrdGodAplPrm> selectOrdGodAplPrm(OrdGodAplPrm searchOrdGodAplPrm) {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.infAffClm.selectOrdGodAplPrm", searchOrdGodAplPrm);
    }

	public void insertOrdClmGodCnnc(OrdClmGodCnnc ordClmGodCnnc) {
		getSession1().insert("com.plgrim.ncp.biz.claim.infAffClm.insertOrdClmGodCnnc", ordClmGodCnnc);
    }

	public void updateInfOrdGodErpDstbByClmUnit(InfOrdGodErpDstb infOrdGodErpDstb) {
		getSession1().update("com.plgrim.ncp.biz.claim.infAffClm.updateInfOrdGodErpDstbByClmUnit", infOrdGodErpDstb);
    }

	public List<LgsDlvsp> selectOrdLgsDlvsp(LgsDlvsp ordLgsDlvSpSearch) {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.infAffClm.selectOrdLgsDlvsp", ordLgsDlvSpSearch);
    }

	public void insertLgsDlvByOrdLgsDlv(LgsDlvExtendForClm lgsDlvSearch) {
		getSession1().insert("com.plgrim.ncp.biz.claim.infAffClm.insertLgsDlvByOrdLgsDlv", lgsDlvSearch);
    }

	public void updateInfAffClmForRegClmNo(InfAffClmDTO infAffClmDTO) {	    
		getSession1().update("com.plgrim.ncp.biz.claim.infAffClm.updateInfAffClmForRegClmNo", infAffClmDTO);
    }

	

}
