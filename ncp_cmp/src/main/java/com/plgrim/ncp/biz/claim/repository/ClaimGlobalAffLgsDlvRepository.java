package com.plgrim.ncp.biz.claim.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plgrim.ncp.biz.claim.data.InfAffClmDTO;
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
import com.plgrim.ncp.base.repository.lgs.LgsDlvRepository;
import com.plgrim.ncp.framework.commons.IdGenService;

/**
 * @author aether
 *
 */

@Repository
@Slf4j
public class ClaimGlobalAffLgsDlvRepository extends LgsDlvRepository {
	
	@Autowired
	IdGenService idGenService;


}
