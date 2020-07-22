package com.plgrim.ncp.biz.order.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysExchgRt;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysExchgRtDTO extends SysExchgRt {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7587476459303204771L;
	
	//환율적용 요청 금액 파라메터
	private String oriAmt;
	
	//환율적용 후 응답 파라메터
	private String exAmt;
	
	//결제환율적용시작일 조회용임 YYYYMMDDHH24MISS
	private String exchgRtAplBegDtStr;
	
	
	
}
