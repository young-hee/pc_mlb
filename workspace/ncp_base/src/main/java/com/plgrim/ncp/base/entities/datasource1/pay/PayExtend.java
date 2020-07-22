package com.plgrim.ncp.base.entities.datasource1.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PayExtend extends Pay {
    /**
     *
     */
    private static final long serialVersionUID = 328465322987751416L;

    private String[] ordNos;

    private String ordNo;
	/**
	 * 가상계좌 유효 일시	 
	 */
	private String virtlBnkAcctValidDtStr;
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
    
    /***********claim start**************/
    
    private String newPayNo;
    
    private String cpnCrtfcCd;
    private String erpCmpgId;
    
    private String cpnKndCd;
    
    private String refundYn;
    
    private String dcSectCd;
    /***********claim end****************/

    private String lastClm;
    
    private String rfdStatCdNm;
    
    private String rfdStatClmNo;
    
    private String partCnclPsbYn;
    
	private java.util.Date exchgRtAplBegDt;
	
	private java.math.BigDecimal stdrCrncyAmt;
}
