package com.plgrim.ncp.commons.data.order.sdo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ItPrPaymentSDO {

	private String	rctNo;//	영수증번호
	
	private String	seqNo;//	순번
	
	private String	zlsch;//	지불방법
	
	private String	recNo;//	인식번호(쿠폰)
	
	private String	crdCmp;//	카드회사
	
	private String	dmbtr;//	현지 통화 금액
	
	private String	waers;//	통화 키
	
	private String	seqnoItem;//	품번참조순번
	
	private String	campaignId;//	캠페인
		
}
