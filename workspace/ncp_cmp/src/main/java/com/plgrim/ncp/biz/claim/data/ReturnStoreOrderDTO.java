package com.plgrim.ncp.biz.claim.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReturnStoreOrderDTO extends AbstractDTO {


    private String rtrvlDrctGodNo;

    private String erpTrnsmisYn;

    private String erpCnfirmYn;

    private String erpTransDiv;

}
