package com.plgrim.ncp.commons.data.order.sdo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BpSalesSaveSDO {

	private String r3nameR;	//sapJcoMode
	
	private String callerId;		//호출자 ID
	
	private String result;		//결과 코드
	
	private IsPrHeaderSDO isPrHeaderSDO;	//IS_PR_HEADER
	
	private List<ItPrItemSDO> itPrItemSDOList;	//IT_PR_ITEM
	
	private List<ItPrPaymentSDO> ItPrPaymentSDOList;	//IT_PR_PAYMENT
	
	private List<ItGiftSDO> itGiftSDOList;	//IT_GIFT
	
	private List<ReturnBpSalesSaveSDO> returnBpSalesSaveList;	//RETURN
		
}
