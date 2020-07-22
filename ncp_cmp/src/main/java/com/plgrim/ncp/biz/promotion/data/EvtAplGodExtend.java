package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.entities.datasource1.evt.EvtAplGod;

/**
 * 이벤트 적용상품 확장
 * @author js
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EvtAplGodExtend extends EvtAplGod {

    /**
     * UID
     */
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    /**
     * 이벤트 번호
     */
    private String evtNo;
    
    /**
     * 적용상품 선택 코드
     */
    private String aplGodSectCd;

    /**
     * 브랜드명
     */
    private String brndNm;

    /**
     * 카테고리명
     */
    private String dspCtgryNm;

    /**
     * 상품판매명
     */
    private String godSaleSectNm;

    /**
     * ERP상품번호
     */
    private String erpGodNo;

    /**
     * 상품번호
     */
    private String godNo;

    /**
     * 상품명
     */
    private String godNm;

    private String mobileGodNm;
    /**
     * 상품판매코드
     */
    private String godSaleSectCd;
    
    /**
     * 에러메시지
     */
    private String errMsg;

    private int dgprcGodDcRt;
    
    private String dcIcon;
    
    private String rtlPrc;
    
    private String lastSalePrc;
    
    private String dcYn;
    
    private int cpnDcRt;
    
    private int voteCount;
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
