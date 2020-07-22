package com.plgrim.ncp.commons.data.order.sdo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TagExchangeSDO {

	private String fpiaOr;		// 주문번호
	private String seqNo;		// 품목 번호, 상품수량순번
	private String tagNew;		// 신규 SAP_STYLE_NO_SN
	private String tagOld;		// 기존 SAP_STYLE_NO_SN
	
	private String r3nameR = "RET";		// 기본값 "SAP/RETAIL"
	private String shopNo;
	private String callerId;
	
	private String result;		// 처리 결과 코드
	private String message;	// 처리 결과 메시지
}
