package com.plgrim.ncp.commons.data.order.sdo;

import java.util.List;



import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * YRSD_FP_RETURN_STORE_CHECK
 * 패션피아에서 매장반품건 입고여부 체크
 * @author user
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReturnStoreCheckSDO {
	
	private String r3nameR;	//sapJcoMode
	
	private String callerId;		//호출자 ID

	private String result;		//결과 코드
	
	private List<TOrderSDO> tOrderSDOList;	//T_ORDER

	private String eReturn;	//sap 통신 후 리턴 코드
	
	private String eMessage;	//sap 통신 후 리턴 메세지



}
