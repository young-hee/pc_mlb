package com.plgrim.ncp.biz.callcenter.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by user on 2015-06-12.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CounselGridDTO extends AbstractDTO{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String cnsltSn;
    private String cnsltDetailTurn;
    private String inqTpNm;
    private String cstmrnm;
    private String ordNo;
    private String godNo;
    private String cnsltStatNm;
    private String transStatNm;
    private String transTgtNm;
    private String promsclDt;
    private String regDt;
    private String regtrId;
    private String cnsltPrcsComptDt;
    private String transUdterDt;
    private String cstmrClmNm;
    private String cnsltKndNm;
    private String cnsltJobTpNm;
    private String cnsltTgtNm;
    private String dvcNm;
    private String langCd;
    private String mallId;

    private String deleteYn;
}
