package com.plgrim.ncp.biz.bi.result;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("biStatisticOnlineInventoryResult")
public class BiStatisticOnlineInventoryResult extends AbstractResult{	
	
	private static final long serialVersionUID = 1622991421602567990L;
	
	private String mallNm;
	private String brndNm;
	private String godNo;
	private String erpGodNo;
	private String godNm;
	private String colorCd;
	private String optValCd1;
	private String mnfYearSeaGrpCd;
	private String rtlPrc;
	private String csmrPrc;
	private String allInv;
	private String onlineComInv;
	private String salePrearngeQty;
	private String onlineInv;
	private String comInv;
	private String shopInv;
	private String godSaleSectCd;
	
}
