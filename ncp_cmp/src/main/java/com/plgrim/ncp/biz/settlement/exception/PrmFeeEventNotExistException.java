package com.plgrim.ncp.biz.settlement.exception;

import com.plgrim.ncp.framework.exception.NCPException;

public class PrmFeeEventNotExistException extends NCPException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public PrmFeeEventNotExistException(String params){
        super.directMessage = params;
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
