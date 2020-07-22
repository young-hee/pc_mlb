package com.plgrim.ncp.biz.callcenter.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by user on 2015-05-11.
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class RefundBankAccountResult extends AbstractResult {
    private String mbrNo;
    private String rfdBnkAcctTurn;
    private String rfdBnkAcctNm;
    private String rfdBnkAcctNo;
    private String rfdBnkAcctBnkCd;
    private String rfdBnkAcctAcnthldrNm;
    private String rprstRfdBnkAcctYn;
    private String regtrId;
    private String regDt;
    private String udterId;
    private String udtDt;
}
