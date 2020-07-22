package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInqAns;


@Data
@EqualsAndHashCode(callSuper = false)
public class CsoOrdAffDataDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private CsoOrgztOrdAffInq csoOrgztOrdAffInq; // 단체주문 / 제휴문의
    private CsoOrgztOrdAffInqAns csoOrgztOrdAffInqAns; // 단체주문 / 제휴문의 답변
}
