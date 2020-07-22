package com.plgrim.ncp.biz.order.data;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderStokcTransferDTO {
	
	private String saleDate;
	
	private String godNo;
	private String itmNo;
	
	private Long hoffInvQty;
	private Date trnsmisDt;
	private String trnsmisYn;
	
	private String season;
	
	private String siz;
	private String color;
	
	private String partCode;
	
	private String teamCd;
	private String brndId;
}
