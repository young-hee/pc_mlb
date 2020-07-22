package com.plgrim.ncp.commons.data.order.sdo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 규격서가 없어 as-is 소스 기준으로 작성
 * @author user
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class TransStockSDO {


	private String r3nameR;	//sapJcoMode
	
	private String callerId;		//호출자 ID

	private String result;		//결과 코드
	
	
	private String	fpiaOr; //	패션피아주문번호
	
	private String	seqno; //	품목 번호
	
	private String	werksF; //	?
	
	private String	werksT; //	?
	
	private String	matnr; //	상품 번호
	
	private String	menge; //	수량
	
	private String eReturn;	//sap 통신 후 리턴 코드
	
	private String eMessage;	//sap 통신 후 리턴 메세지
	
			
}
