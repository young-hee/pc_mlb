package com.plgrim.ncp.biz.promotion.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class PromotionKeyMessageExceiptioin extends NCPException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public PromotionKeyMessageExceiptioin(String[] params) {
        init(params);
    }
    
    public PromotionKeyMessageExceiptioin(String param) {
        directMessage = param;
    }
    
    
    
    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

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
