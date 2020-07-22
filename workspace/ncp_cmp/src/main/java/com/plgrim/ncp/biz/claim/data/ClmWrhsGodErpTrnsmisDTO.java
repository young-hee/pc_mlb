package com.plgrim.ncp.biz.claim.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractDTO;


/**
 * 클레임 상품별 ERP 연동 정보
 * @author Seed
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("ClmWrhsGodErpTrnsmisDTO")
public class ClmWrhsGodErpTrnsmisDTO extends AbstractDTO{
	
	private String ordNo;
	
	private String clmNo;
	
	private String clmErpTrnsmisTpCd;
	
	private String erpTrnsmisCd;
	
	private String regtrId;
 
}
