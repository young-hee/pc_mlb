package com.plgrim.ncp.biz.claim.repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.biz.claim.data.ClaimListSearchDTO;
import com.plgrim.ncp.biz.claim.data.ClaimRefundListSearchDTO;
import com.plgrim.ncp.biz.claim.result.ClaimListResult;
import com.plgrim.ncp.biz.claim.result.ClaimRefundListResult;
import com.plgrim.ncp.framework.commons.IdGenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chakhan
 *
 */
@Repository
@Slf4j
public class ClaimRefundRepository extends AbstractRepository {


	public List<ClaimRefundListResult> selectClaimRefundList(ClaimRefundListSearchDTO claimRefundListSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.refund.selectClaimRefundList", claimRefundListSearchDTO);
	}

	public long selectClaimRefundListTotal(ClaimRefundListSearchDTO claimRefundListSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.refund.selectClaimRefundListTotal", claimRefundListSearchDTO);
	}
}
