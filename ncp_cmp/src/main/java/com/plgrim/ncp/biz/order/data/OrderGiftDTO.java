package com.plgrim.ncp.biz.order.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderGiftDTO extends AbstractDTO {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 5086618094751346520L;

	private String prmNo = null;

	private Integer giftDelvSeq;
	
	private List<God> godList = null;

	private List<OrdGod> giftList = null;

}
