package com.plgrim.ncp.biz.callcenter.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by user on 2015-06-09.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FaqGridDTO extends AbstractDTO {

    private java.lang.Long faqSn;
    private String mallId;
    private String langCd;
    private String faqSectCd;
    private String faqDetailSectCd;
    private String faqSj;
    private String faqCont;
    private java.lang.Integer sortSeq;
    private java.lang.Integer inqireCount;
    private String dspYn;
    private String mainExpsrYn;
    private String regtrId;
    private java.util.Date regDt;
    private String udterId;
    private java.util.Date udtDt;

}
