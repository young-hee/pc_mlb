package com.plgrim.ncp.biz.bi.result;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("biStatisticGodQtyShopResult")
public class BiStatisticGodQtyShopResult extends AbstractResult{
	
	private static final long serialVersionUID = 2775585764799099913L;

	private String itmNm;
	private String shopNm;
	private String invQty;
}
