/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 * beyondj2ee			2015.02.09         
 */
package com.plgrim.ncp.biz.callcenter.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MainCounselOrdResult extends AbstractResult {

    // ~ Instance fields. ~~~~~~~~~~~~~~

    // ~ Constructors. ~~~~~~~~~~~~~~~~~

    // ~ Getter and Setter. ~~~~~~~~~~~~


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String mbrTpCd;
    private String mbrNo;
    private String ordNo;
    private String cstmrMobilNationNo;
    private String cstmrMobilAreaNo;
    private String cstmrMobilTlofNo;
    private String cstmrMobilTlofWthnNo;
    private String cstmrTelNationNo;
    private String cstmrTelAreaNo;
    private String cstmrTelTlofNo;
    private String cstmrTelTlofWthnNo;
    private String mbrId;
    private String erpCstmrNo;


}
