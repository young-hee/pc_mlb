package com.plgrim.ncp.biz.promotion.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplGod;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplPd;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplTgt;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCrsDcAplGod;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmOfferGft;

/**
 * @author Vito
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionMbSearchDTO extends AbstractDTO {

    /**
     *
     */ 
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    private String mbrNo;

    /**
     * 사용자 array
     */
    private List<String> listMbrNo;
    
    /**
     *  브랜드 번호
     */
    private String brndId;
    
    /**
     *  shop번호
     */
    private String shopId;
    
    /**
     * 프로모션
     */
    private Prm prm;

    /**
     * 프로모션 쿠폰
     */
    private PrmCpn prmCpn;

    /**
     * 프로모션 적용대상
     */
    private PrmAplTgt prmAplTgt;

    /**
     * 프로모션 적용상품
     */
    private PrmAplGod prmAplGod;

    private PrmCrsDcAplGod prmCrsDcAplGod;

    private PrmOfferGft prmOfferGft;

    /**
     * 쿠폰 발급/사용 일자 정보
     */
    private PrmAplPd prmAplPd;
    
    /**
     * 회원 발급 쿠폰
     */
    private MbrIssuCpn mbrIssuCpn;
    
    /**
     * 브랜드리스트
     */
    private List<String> brndIds;
    
    /**
     * shop 리스트
     */
    private List<String> shopIds;

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
