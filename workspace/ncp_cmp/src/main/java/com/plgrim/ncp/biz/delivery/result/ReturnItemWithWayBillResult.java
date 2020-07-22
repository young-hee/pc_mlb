package com.plgrim.ncp.biz.delivery.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReturnItemWithWayBillResult extends AbstractResult{
    
    /**
     * 
     */
    private static final long serialVersionUID = 3618158811297627624L;
    /**
     * 주문 번호
     */
    private String ordNo;
    /**
     * 배송 수거지 순번     
     */
    private java.lang.Integer dlvPcupspTurn;
    /**
     * 배송 순번     
     */
    private java.lang.Integer dlvTurn; 
    /**
     * 클레임 번호
     */
    private String clmNo;
    /**
     * 국내 운송장 번호     
     */
    private String dmstcWaybilNo;
    /**
     * 회수지시상품번호
     */
    private String rtrvlDrctGodNo;
}
