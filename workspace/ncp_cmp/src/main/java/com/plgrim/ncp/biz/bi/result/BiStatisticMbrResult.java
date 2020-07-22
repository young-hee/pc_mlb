package com.plgrim.ncp.biz.bi.result;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("biStatisticMbrResult")
public class BiStatisticMbrResult extends AbstractResult {
	
	private static final long serialVersionUID = -1038122103918233937L;
	
	private Mbr mbr;
	private String logOccurDt;
	private String mobile;
	private String ordCnt;
	private String ordGodCnt;
	private String ordSaleAmt;
	private String bskGodCnt;
}
