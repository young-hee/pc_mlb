package com.plgrim.ncp.biz.promotion.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.biz.promotion.data.PrmAplGodExtend;

@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionApplyGoodBoResult extends AbstractResult {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    private String prmNo;

    private String aplTurn;

    private String aplTgtGodGrpCd;
    
    private String godNo;
    
    private String aplGodSectCd;
    
    private PrmAplGodExtend prmAplGodEx;
    

    /*
     * ************************************************************************
     * validate 용도
     * 
     * 주문할인 프로모션
     *   - 묶음할인, 교차할인 간에 중복 등록 불가
     *   - 묶음할인에서 그룹간 중복 등록 불가
     *   
     * : ordDcGodCount, aplTgtGodGrpCd, godAplYn;
     * 
     * ************************************************************************
     */
    
    /**
     * 주문할인 (적용)상품 count
     */
    private int ordDcGodCount = 0;
    
    /**
     * 상품적용여부
     */
    private String godAplYn;
    
    /**
     * 적용상품 count
     */
    private int prmAplGodCount = 0;

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
