package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.entities.datasource1.prm.PrmOfferGft;

@Data
@EqualsAndHashCode(callSuper = false)
public class PrmOfferGftExtend extends PrmOfferGft {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    String godNm;

    String godTpCd;
    
    String godTpNm;
    
    /**
     * 총 가용 재고 수량(사은품 총재고)
     */
    Long totUsefulInvQty;
    
    /**
     * 가용 재고 수량(실재고수량 / 사은품 가용재고)
     *   실재고수량 = 총 가용 재고 수량 - 판매 예정 수 - IF(안전 재고 사용여부=Y,안전 재고 수,0)
     */
    Long usefulInvQty;
    
    /**
     * 제공사은품 count
     */
    private int offerGiftCount = 0;

    /*
     * ---------------------------------------------------------------------
     * Constructors.
     * ---------------------------------------------------------------------
     */

    /*
     * ---------------------------------------------------------------------
     * public & interface method.
     * ---------------------------------------------------------------------
     */

    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}

