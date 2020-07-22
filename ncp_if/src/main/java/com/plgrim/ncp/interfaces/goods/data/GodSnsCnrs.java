package com.plgrim.ncp.interfaces.goods.data;

import lombok.Data;

/*
 * 전송여부요
 */

@Data
public class GodSnsCnrs {
	private String godNo;
	private String snsSectCd;
	private String cnrsTrnsmisSectCd;
	private String snsCnrsDt;
	private String cnrsCont;
	private String regtrId;
	private String regDt;
	private String udterId;
	private String udtDt;
	
}
