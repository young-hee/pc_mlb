package com.plgrim.ncp.biz.interfaces.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class InfOrdGodErpDstbClmSearchDTO extends AbstractDTO {

	/**
	 * 
	 */
    private static final long serialVersionUID = -5692444487113933456L;

    private String ordNo;
    
    private String clmNo;
    
    /* 
     * 클레임입고상품순번
     * 반품부분철회시 사용 
     */
    private String clmWrhsGodTurn;
    
    /**
     * 클레임번호가 null 인 데이터를 조회하기 위해 추가
     */
    private String clmNoNullYn;
    
    /**
     * 배송비쿠폰번호 무료배송 쿠폰 복원위해 추가
     */
    private String dlvCstCpnNo;
    
    private List<String>ordGodTurn;
        
    private String codeOrRe;	
    
    /**
     * 교환 출고상품 여부
     */
    private String exchangeDlivyGodYn;

    /**
     * 교환 철회 제외 여부
     */
    private String exceptExchangeWithrawYn;
}
