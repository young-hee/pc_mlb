package com.plgrim.ncp.base.entities.datasource1.ord;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.entities.datasource1.inf.InfAffOrd;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrdExtend extends Ord {

    /**
     *
     */
    private static final long serialVersionUID = 93515666567316420L;

    private String[] ordNos;

    private List<InfAffOrd> infAffOrdList;
	/**
	 * 제휴 주문 일련번호	 
	 */
	private java.lang.Long affOrdSn;

	private String saleShopCd;
}
