package com.plgrim.ncp.commons.data.order.sdo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FpHoldStockSDO {

	private String r3nameR;	//sapJcoMode
	
	private String callerId;		//호출자 ID

	private String result;		//결과 코드
	

	private String	werks; //	물류센터		//호출시 필수
	
	private String	material; //	품번		//호출시 필수
	
	private String	quantity; //	수량		//호출시 필수
	
	private String	reason; //	사유		//호출시 필수
	
	private String	fpkey; //	패션피아 접수번호		//호출시 필수
	
	private String	status; //	처리결과 : S, E
	
	private String	message; //	결과메시지
	
	private String	ebeln; //	STO번호
	
	private String	ebelp; //	일련번호
	
	
}
