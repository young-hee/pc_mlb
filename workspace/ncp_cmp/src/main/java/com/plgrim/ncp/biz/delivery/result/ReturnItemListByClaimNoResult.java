package com.plgrim.ncp.biz.delivery.result;

import lombok.Data;

@Data
public class ReturnItemListByClaimNoResult {
    private String ordNo;
    /**
     * 배송 수거지 순번     
     */
    private Integer dlvPcupspTurn;
    /**
     * 배송 순번     
     */
    private Integer dlvTurn; 
    
    /**
     * SKU
     */
    private String skuNo;
    
    /**
     * S/N
     */
    private String erpGodSn;
    
    /**
     * Claim No
     */
    private String clmNo;
    
    /**
     * 회수검수여부
     */
    private String wrhsAcptYn;
    
    /**
     * 출고지시상품번호
     */
    private String dlivyDrctGodNo;
    
    /**
     * 클레임 입고 상품 순번
     */
    private Integer clmWrhsGodTurn;
}
