package com.plgrim.ncp.biz.claim.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClmDetailGodInfoDTO extends AbstractDTO {
	/**
	 * 
	 */
    private static final long serialVersionUID = -4328770253008464408L;

    /**
     * 순번
     */
    private String clmWrhsGodTurn;
    
    /**
     * 접수일시
     */
    private String rceptDt;
    
    /**
     * 상품명
     */
    private String godNm;
    
    /**
     * 옵션
     */
    private String itmNm;
    
    /**
     * 주문수량
     */
    private String ordQty;
    
    /**
     * 취소수량
     */
    private String cnclQty;
    
    /**
     * 판매금액
     */
    private String saleAmt;
    
    /**
     * 주결제금액
     */
    private String payExchgRtCrncyAmt;
    
    /**
     * 주결제금액
     */
    private String stdrCrncyAmt;
    
    /**
     * 할인금액
     */
    private String dcAmt;
    
    /**
     * 맴버쉽포인트결제
     */
    private String unityPntUseAmt;
    
    /**
     * 이벤트포인트결제
     */
    private String evtPntUseAmt;
    
    /**
     * P포인트 결제
     */
    private String webpntUseAmt;
    
    /**
     * 결품
     */
    private String shtgDcsnYn;
    
    /**
     * 사유 코드
     */
    private String clmResnCd;
    
    /**
     * 사유 내용
     */
    private String clmResnCdNm;
    
    
    
    

}
