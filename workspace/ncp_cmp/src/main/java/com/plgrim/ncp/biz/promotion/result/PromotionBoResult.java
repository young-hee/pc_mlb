package com.plgrim.ncp.biz.promotion.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.biz.promotion.data.PrmAplTgtExtend;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionBoResult extends AbstractResult {
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
     * check message
     */
    private String chkMsg;

    /**
     * 회원 쿠폰 사용여부 체크
     */
    private String mbrCpnUseChk;

    /**
     * 프로모션 번호
     */
    private String prmNo;

    private Prm prm;

    private PrmCpn prmCpn;
    
    private PrmAplTgtExtend prmAplTgtEx; 

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
