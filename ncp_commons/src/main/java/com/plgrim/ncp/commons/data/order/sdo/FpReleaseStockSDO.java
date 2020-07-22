package com.plgrim.ncp.commons.data.order.sdo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FpReleaseStockSDO {

	private String r3nameR;	//sapJcoMode
	
	private String callerId;		//호출자 ID

	private String result;		//결과 코드
	
	//private List<ItabReleaseStockSDO> itabReleaseStockSDO;
	
	private ItabReleaseStockSDO itabReleaseStockSDO;
	
	private String	status; //	처리결과 : S, E
	
	private String	message; //	결과메시지
	

	
	
}
