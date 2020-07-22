package com.plgrim.ncp.commons.data.order.sdo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FpReturnStoreOrderSDO {

	private String r3nameR;	//sapJcoMode
	
	private String callerId;		//호출자 ID

	private String result;		//결과 코드

	private String	iFlag; //	구분 (A:출고, B:출고취소)
	
	private TransDlvSDO transDlvSDO;	//파라메터SDO  > T_TRANS_DLV
	
	private List<TransDlvSDO> transDlvSDOList;	//T_TRANS_DLV
	
	
	private String	eMblnr; //	자재문서번호
	
	private String	eRctno; //	영수증 번호
	
	private String	eReturn; //	처리 결과
	
	private String	eMessage; //	메시지

}
