package com.plgrim.ncp.biz.delivery.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class RePickupHistResult {
	
	int rtrvlDrctTurn;
	String dlvComTrnsmisDtStr;
	String dlvComNm;
	String dmstcWaybilNo;
	String rePickupAdminNm;
}
