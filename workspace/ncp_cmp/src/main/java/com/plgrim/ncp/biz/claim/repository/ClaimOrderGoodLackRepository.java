package com.plgrim.ncp.biz.claim.repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.biz.claim.data.ClaimListSearchDTO;
import com.plgrim.ncp.biz.claim.result.ClaimListResult;
import com.plgrim.ncp.biz.claim.result.DeliveryOrderGoodResult;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chakhan
 *
 */
@Repository
@Slf4j
public class ClaimOrderGoodLackRepository extends AbstractRepository {
	
	public List<DeliveryOrderGoodResult> selectOrderGoodLackList(ClaimListSearchDTO claimListSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.ordergoodlack.selectOrderGoodLackList", claimListSearchDTO);
	}

	public long selectOrderGoodLackListTotal(ClaimListSearchDTO claimListSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.ordergoodlack.selectOrderGoodLackListTotal", claimListSearchDTO);
	}
	
	public List<ClaimListResult> selectOrderGoodLackListForExcel(ClaimListSearchDTO claimListSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.ordergoodlack.selectOrderGoodLackListForExcel", claimListSearchDTO);
	}
}
