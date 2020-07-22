package com.plgrim.ncp.biz.claim.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReturnStoreExchgOriDTO extends AbstractDTO {

	/**
	 * 
	 */
    private static final long serialVersionUID = -8132309252846255858L;

    
    private String qtySeq;
    private String sSto;
    private String sDocno;
	

}
