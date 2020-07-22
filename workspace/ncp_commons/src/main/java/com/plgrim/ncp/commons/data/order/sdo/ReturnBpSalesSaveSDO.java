package com.plgrim.ncp.commons.data.order.sdo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ReturnBpSalesSaveSDO {

	private String	type; //	메시지 유형
	
	private String	id; //	ID
	
	private String	number; //	ERROR NUMBER
	
	private String	message; //	메시지 텍스트
	
	private String	logNo; //	어플리케이션 로그: 로그 번호
	
	private String	logMsgNo; //	어플리케이션 로그: 내부 메시지 일련 번호
	
	private String	messageV1; //
	
	private String	messageV2; //
	
	private String	messageV3; //
	
	private String	messageV4; //
	
	private String	parameter; //	매개변수 이름
	
	private String	row; //	매개변수라인
	
	private String	field; //	매개변수 필드
	
	private String	system; //	메시지를 발생시킨 논리 시스템 

		
}
