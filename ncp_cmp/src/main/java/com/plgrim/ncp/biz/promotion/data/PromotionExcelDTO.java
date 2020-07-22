package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.entities.datasource1.prm.Prm;

@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionExcelDTO extends Prm {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    /**
     * 프로모션 번호
     */
    private String prmNo;

    /**
     * 프로모션상태코드
     */
    private String prmStatCd;

    /**
     * 상품번호
     */
    private String godNo;

    private String errMsg;
    
    /**
     * 그리드 컬럼라벨
     */
    private String columnLabel;

    /**
     * 그리리 컬럼ID
     */
    private String columnId;

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
