package com.plgrim.ncp.commons.data.order.sdo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class IsPrHeaderSDO {

	private String	rctNo;//	영수증번호 
	
	private String	fspVbenr;//	패션피아 주문번호
	
	private String	werks;//	매장코드
	
	private String	bpNo;//	고객
	
	private String	purDat;//	구매일자
	
	private String	purTime;//	구매시간
	
	private String	reaPrc;//	실소매금액
	
	private String	disAmt;//	할인금액
	
	private String	cbBalAmt;//	영수증캐쉬백잔액
	
	private String	waers;//	통화 키
	
	private String	classBp;//	고객등급-매장
	
	private String	orderType;//	패션피아 구매유형
	
	private String	mlBalAmt;//	잔여마일리지(영수증)
	
}
