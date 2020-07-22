package com.plgrim.ncp.commons.data.order.sdo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class SusunResultIFSDO {

	private String r3nameR;	//sapJcoMode	//호출시 필수
	
	private String callerId;		//호출자 ID	//호출시 필수
	
	private String result;		//결과 코드
	
	private String iKunnr;		// SAP 고객 아이디
	
	private String iQmnum;//수선번호

	
	/**
	 * ㅁ. 수선 완료 코드 : SAP에서 연계 되는 코드
	   >. A13 : 처리안되고고객한테발송
	   >. A14 : 수선하고고객한테발송
	   >. A15 : 그냥회수반품
	 */
	private String asTxt;//결과코드

	
	private String status;//통신결과 (sap 결과아님)
	
	
	private String message;//통신결과 메세지 (sap 결과아님)

}
