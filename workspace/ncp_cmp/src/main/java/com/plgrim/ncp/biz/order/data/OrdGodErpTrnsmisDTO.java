package com.plgrim.ncp.biz.order.data;

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
@Alias("OrdGodErpTrnsmisDTO")
public class OrdGodErpTrnsmisDTO extends AbstractDTO{
	
	private String ordNo;
	
	private Integer ordGodTurn;
	
	private String ordGodErpTrnsmisTpCd;
	
	private Integer qtyTurn;
	
	private String erpTrnsmisCd;
	
	private String regtrId;
 
}
