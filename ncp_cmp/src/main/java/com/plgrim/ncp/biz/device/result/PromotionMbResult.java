package com.plgrim.ncp.biz.device.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftInfo;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;

@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionMbResult extends AbstractResult {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /* 
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    private Prm prm;

    private PrmCpn prmCpn;
    
    private SysShopBrnd sysShopBrnd;
    
    private Evt evt;
    
    private EvtFreeGiftInfo evtFreeGiftInfo;
    

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
