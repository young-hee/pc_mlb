package com.plgrim.ncp.biz.claim.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.God;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClmCouponResult extends AbstractResult {

	private static final long serialVersionUID = 4148373537262330926L;

	private List<ClmGoodsCouponResult> clmGoodsCouponResultList;

	private ClmWrhsGodExtend clmWrhGodExtend;

	private God god;

}
