package com.plgrim.ncp.cmp.orderfulfilment.bo.claim;

import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.biz.claim.data.RefundPayDTO;

public interface RefundPayComponent {
	
	/**
	 * 결제 환불 처리
	 * @param refundPayDTO
	 * @param clm
	 * @param isReRefund 환불재처리여부
	 * @return 정상환불여부
	 */
	public boolean refundPayTransaction(RefundPayDTO refundPayDTO, Clm clm, boolean isRefundReprocess);

	/**
	 * PG사별 환불처리
	 * 
	 * @param refundPayDTO
	 * @param clm
	 * @param payMnCd
	 * @param dealTpCd
	 */
	public void refundPG(RefundPayDTO refundPayDTO);
	
	/**
	 * 에스크로 정산 보류 처리
	 * 
	 * @param refundPayDTO
	 */
	public void reserveCalculate(RefundPayDTO refundPayDTO);
}
