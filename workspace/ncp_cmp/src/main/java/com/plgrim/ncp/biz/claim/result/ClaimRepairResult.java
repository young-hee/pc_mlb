package com.plgrim.ncp.biz.claim.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrmExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.biz.order.result.OrdGodForRtnClmResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClaimRepairResult extends AbstractResult {

	/**
	 *
	 */
	private static final long serialVersionUID = -5064752112864163329L;

	private String clmNo;
	
	private List<ClaimListResult> claimListResult;

	
}
