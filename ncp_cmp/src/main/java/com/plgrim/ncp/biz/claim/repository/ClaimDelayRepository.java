package com.plgrim.ncp.biz.claim.repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGod;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdClmGodCnnc;
import com.plgrim.ncp.biz.claim.data.*;
import com.plgrim.ncp.biz.claim.result.*;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author chakhan
 *
 */
@Repository
@Slf4j
public class ClaimDelayRepository extends AbstractRepository {
	
	@Autowired
	IdGenService idGenService;


	public List<ClaimListResult> selectClaimDelayList(ClaimListSearchDTO claimListSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.delay.selectClaimDelayList", claimListSearchDTO);
	}

	public long selectClaimDelayListTotal(ClaimListSearchDTO claimListSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.delay.selectClaimDelayListTotal", claimListSearchDTO);
	}
}
