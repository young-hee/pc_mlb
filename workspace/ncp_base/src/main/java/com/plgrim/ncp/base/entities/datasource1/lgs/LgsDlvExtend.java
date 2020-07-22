package com.plgrim.ncp.base.entities.datasource1.lgs;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("lgsDlvExtend")
public class LgsDlvExtend extends LgsDlv {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4937351594554452009L;
	
	/**
	 * 반품 요청 주문 상품 순번	 
	 */
	private Integer reqRtOrdGodTurn;	
}
