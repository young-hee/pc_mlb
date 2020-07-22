package com.plgrim.ncp.commons.data.order;

import java.util.Date;

import com.plgrim.ncp.commons.data.AbstractOrderDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class OrdGodInfDTO extends AbstractOrderDTO{

	String dlivyDrctGodNo;
	String ordNo;
	Long ordGodTurn;
	Long pckageGodTurn;
	String gftYn;
	String dlvShopId;
	Long dlivyDrctQty;
	Long dlivyDrctCnclQty;
	Date rtTrnsmisDt;
	Long rtTrnsmisSeq;
	String clmNo;

	
}
