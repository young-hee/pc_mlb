package com.plgrim.ncp.biz.claim.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClaimBatchDrtExchgRcptfrDTO extends AbstractDTO {

	/**
	 * 
	 */
    private static final long serialVersionUID = -7554779772178443719L;

    /**
     *  맞교환시 주문당시 오리지널 주문 번호
     */
    private String ordNoOri;
    
    /**
     * 맞교환시 주문당시 오리지널 수량 번호
     */
    private String qtyTurnOri;
    
    /**
     * 주문 번호
     */
    private String ordNo;
    
    /**
     * 맞교환시 클레임으로 생성된 수량 번호
     */
    private String qtyTurn;

}
