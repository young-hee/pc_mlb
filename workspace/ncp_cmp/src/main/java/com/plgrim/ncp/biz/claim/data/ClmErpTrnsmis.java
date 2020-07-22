package com.plgrim.ncp.biz.claim.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;


/**
 * 수선배치에서 erp 통신 이후 DB update 를 위한 파라메터DTO
 * @author user
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("ClmErpTrnsmis")
public class ClmErpTrnsmis {
	
	
	private String clmNo;
	
	private String ordNo;
	
	private String erpTrnsmisCd;
	
	private String clmErpTrnsmisTpCd;
	
	private String regtrId;
	
	private String batchYn;
	

}
