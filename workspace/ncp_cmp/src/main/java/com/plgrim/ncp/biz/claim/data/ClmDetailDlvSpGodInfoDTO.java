package com.plgrim.ncp.biz.claim.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClmDetailDlvSpGodInfoDTO extends AbstractDTO {

	
	/**
	 * 
	 */
    private static final long serialVersionUID = 2968540271400320059L;

	/**
	 * 배송지
	 */
	private String addrs;
	
	/**
	 * 배송비 환불금액
	 */
	private String dlvRefundAmt;
	
	/**
	 * 상품정보
	 */
	private List<ClmDetailGodInfoDTO> clmDetailGodInfoList;

}
