package com.plgrim.ncp.base.entities.datasource1.god;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("godItmExtend")
public class GodItmExtend extends GodItm{
    private static final long serialVersionUID = 338122766755115841L;
    
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /**
	 * 단품 상태 코드명
	 */
    private String itmStatNm;
	/**
	 * 예약수량 변경 비교
	 */
	private java.lang.Long compareResveQty;
	
	/**
	 * 가용 재고 수량 매장명
	 */
	private String usefulInvQtyShopNm;

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
}
