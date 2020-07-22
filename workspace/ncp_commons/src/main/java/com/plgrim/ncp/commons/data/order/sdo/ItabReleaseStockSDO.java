package com.plgrim.ncp.commons.data.order.sdo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.commons.data.order.sdo.FpReleaseStockSDO;

@Data
@EqualsAndHashCode(callSuper=false)
public class ItabReleaseStockSDO {

	private String	ebeln; //	STO번호	//호출시 필수
	
	private String	ebelp; //	일련번호	//호출시 필수
	
	private String	status; //	처리결과 : S, E
	
	private String	message; //	결과메시지



}
