package com.plgrim.ncp.base.enums;

public enum GoodsHistoryEnum {

	/************************************
	 * 상품정보변경히스토리 변경항목 ENUM
	 ************************************/

	YES("Y"), NO("N"),
	
	// 상품정보 변경이력 관리 항목정의
	MODITM_GOD_HISTORY("GOD"),
	MODITM_GOD_HISTORY_SECT("승인여부,전시여부,판매여부,정소가,실소가"),
	MODITM_GOD_HISTORY_APRV_SECT_CD("승인여부"),
	MODITM_GOD_HISTORY_DSP_YN("전시여부"),
	MODITM_GOD_HISTORY_GOD_SALE_SECT_CD("판매여부"),
	MODITM_GOD_HISTORY_RTL_PRC("정소가"),
	MODITM_GOD_HISTORY_CSMR_PRC("실소가"),
	MODITM_GOD_HISTORY_OVSEA_DLV_PSB_YN("해외판매여부"),
	MODITM_GOD_HISTORY_EN_DSP_YN("영문전시상태"),
	MODITM_GOD_HISTORY_CN_DSP_YN("중문전시상태"),
	MODITM_GOD_HISTORY_ONLNE_ONLY_GOD_YN("온라인전용상품여부"),

	// 상품재고배분 변경이력 관리 항목정의
	MODITM_STOCK_HISTORY("STOCK"),
	MODITM_STOCK_HISTORY_SAFE_INV_RT("브랜드안전재고정량"),
	MODITM_STOCK_HISTORY_SAFE_INV_QTY("상품안전재고정량"),
	MODITM_STOCK_HISTORY_SAFE_INV_YN("안전재고사용여부"),
	MODITM_STOCK_HISTORY_WEB_SALE_PSB_YN("웹판매가능여부"),
	MODITM_STOCK_HISTORY_SECT("브랜드안전재고정률,상품안전재고정량,웹판매가능여부"),
	
	// 사은품재고이력 처리용 구분코드값
	MODITM_GIFTGOD_HISTORY_CD_ORDER("ORD"),
	MODITM_GIFTGOD_HISTORY_CD_CANCEL("CNL"),
	MODITM_GIFTGOD_HISTORY_CD_RETURN("RET"),
	MODITM_GIFTGOD_HISTORY_CD_EXCHG("EXG"),
	
	// 사은품재고이력 처리용 구분설명값
	MODITM_GIFTGOD_HISTORY_TXT_ORDER("정상주문"),
	MODITM_GIFTGOD_HISTORY_TXT_CANCEL("취소주문"),
	MODITM_GIFTGOD_HISTORY_TXT_RETURN("반품처리"),
	MODITM_GIFTGOD_HISTORY_TXT_EXCHG("교환처리"),
	
	// 사은품재고처리 매장
	MODITM_GIFTGOD_SHOP_ID("X30004");
	
	private final String text;

	private GoodsHistoryEnum(final String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
}
