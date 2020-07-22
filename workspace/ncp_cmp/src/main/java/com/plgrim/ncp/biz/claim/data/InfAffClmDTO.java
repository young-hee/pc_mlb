package com.plgrim.ncp.biz.claim.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class InfAffClmDTO extends AbstractDTO {


	/**
	 * 
	 */
    private static final long serialVersionUID = 8280880481689218670L;
    
    
	private String affClmTurn;
	private String affClmDt;
	private String regDt;
	private String udtDt;
	private String qty;
	private String rtlPrc;
	private String csmrPrc;
	private String payExchgRtCrncyAmt;
	private String affComSalePrc;
	private String dlvCst;
	private String comDlvCst;
	private String affOrdSn;
	private String affOrdTurn;
	private String clmWrhsGodTurn;
	private String cstmrPostNo;
	private String mallId;
	private String affClmNo;
	private String affClmItmNo;
	private String cstmrNm;
	private String dlvMemo;
	private String godMemo;
	private String rtrvlShopNo;
	private String clmNo;
	private String godNo;
	private String cstmrDetailAddr;
	private String cstmrBaseAddr;
	private String mobilNationNo;
	private String mobilAreaNo;
	private String mobilTlofNo;
	private String mobilTlofWthnNo;
	private String telNationNo;
	private String telAreaNo;
	private String telTlofNo;
	private String telTlofWthnNo;
	private String itmNo;
	private String pckageshapeGodNo;
	private String regtrId;
	private String udterId;
	private String rtrvlNationCd;
	private String erpGodNo;
	private String crncyCd;
	private String ordNo;
	private String ordGodTurn;

	
}
