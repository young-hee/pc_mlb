package com.plgrim.ncp.base.enums.goods;

public enum GoodsExcelEnum {

	// CONSTANTS
	GOODSLIST("상품목록")
	,GOODSLIST_01("온라인품번 ")
	,GOODSLIST_02("ERP품번 ")
	,GOODSLIST_03("디자인코드 ")
	,GOODSLIST_04("상품명 ")
	,GOODSLIST_05("이미지URL ")
	,GOODSLIST_06("브랜드 ")
	,GOODSLIST_07("브랜드코드 ")
	,GOODSLIST_08("상위브랜드코드 ")
	,GOODSLIST_09("품목 ")
	,GOODSLIST_10("표준카테고리 ")
	,GOODSLIST_11("추천성별 ")
	,GOODSLIST_12("원산지 ")
	,GOODSLIST_13("시즌 ")
	,GOODSLIST_14("상품구분 ")
	,GOODSLIST_15("온라인 전용상품 ")
	,GOODSLIST_16("퀵배송 ")
	,GOODSLIST_17("매장픽업 ")
	,GOODSLIST_18("수선대상 ")	
	,GOODSLIST_19("제조국변경여부 ")	
	,GOODSLIST_20("해외판매여부 ")
	,GOODSLIST_21("상품무게 ")
	,GOODSLIST_22("승인구분 ")
	,GOODSLIST_23("전시구분 ")
	,GOODSLIST_24("판매구분 ")
	,GOODSLIST_25("판매상태 ")
	,GOODSLIST_26("정소가 ")
	,GOODSLIST_27("실소가 ")
	,GOODSLIST_28("현재실소가 ")
	,GOODSLIST_29("임직원가 ")
	,GOODSLIST_30("현재임직원가 ")
	,GOODSLIST_31("SFC가 ")
	,GOODSLIST_32("현재SFC가 ")
	,GOODSLIST_33("블루베리가 ")
	,GOODSLIST_34("현재블루베리가 ")
	,GOODSLIST_35("이지웰가 ")
	,GOODSLIST_36("현재이지웰가 ")
	,GOODSLIST_37("웰스토리가 ")
	,GOODSLIST_38("현재웰스토리가 ")
	,GOODSLIST_39("제조사 ")
	,GOODSLIST_40("수입사 ")
	,GOODSLIST_41("제조국가 ")
	,GOODSLIST_42("제조일자 ")
	,GOODSLIST_43("소재 ")
	,GOODSLIST_44("취급주의사항 ")
	,GOODSLIST_45("세탁코드 ")
	,GOODSLIST_46("세탁방법 ")
	,GOODSLIST_47("품질보증기준설명 ")
	,GOODSLIST_48("상품종류설명 ")
	,GOODSLIST_49("상품특징설명 ")
	,GOODSLIST_50("모델정보설명 ")
	,GOODSLIST_51("실측사이즈설명 ")
	,GOODSLIST_52("입고일 ")
	,GOODSLIST_53("온라인재고 ")
	,GOODSLIST_54("센터FC01재고 ")
	,GOODSLIST_55("센터FC08재고 ")
	,GOODSLIST_56("배송매장재고 ")
	,GOODSLIST_57("안전재고 ")
	,GOODSLIST_58("주문대기수량 ")
	,GOODSLIST_59("판매시작일 ")
	,GOODSLIST_60("판매종료일 ")
	;

	/**
	 * 상품목록 엑셀
	 */
	public enum GoodsListExcel{
		GOODSLIST_01
		,GOODSLIST_02
		,GOODSLIST_03
		,GOODSLIST_04
		,GOODSLIST_05
		,GOODSLIST_06
		,GOODSLIST_07
		,GOODSLIST_08
		,GOODSLIST_09
		,GOODSLIST_10
		,GOODSLIST_11
		,GOODSLIST_12
		,GOODSLIST_13
		,GOODSLIST_14
		,GOODSLIST_15
		,GOODSLIST_16
		,GOODSLIST_17
		,GOODSLIST_18
		,GOODSLIST_19
		,GOODSLIST_20
		,GOODSLIST_21
		,GOODSLIST_22
		,GOODSLIST_23
		,GOODSLIST_24
		,GOODSLIST_25
		,GOODSLIST_26
		,GOODSLIST_27
		,GOODSLIST_28
		,GOODSLIST_29
		,GOODSLIST_30
		,GOODSLIST_31
		,GOODSLIST_32
		,GOODSLIST_33
		,GOODSLIST_34
		,GOODSLIST_35
		,GOODSLIST_36
		,GOODSLIST_37
		,GOODSLIST_38
		,GOODSLIST_39
		,GOODSLIST_40
		,GOODSLIST_41
		,GOODSLIST_42
		,GOODSLIST_43
		,GOODSLIST_44
		,GOODSLIST_45
		,GOODSLIST_46
		,GOODSLIST_47
		,GOODSLIST_48
		,GOODSLIST_49
		,GOODSLIST_50
		,GOODSLIST_51
		,GOODSLIST_52
		,GOODSLIST_53
		,GOODSLIST_54
		,GOODSLIST_55
		,GOODSLIST_56
		,GOODSLIST_57
		,GOODSLIST_58
		,GOODSLIST_59
		,GOODSLIST_60		
	}

	private final String value;

	private GoodsExcelEnum(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}
