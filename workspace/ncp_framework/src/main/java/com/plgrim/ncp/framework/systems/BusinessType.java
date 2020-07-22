package com.plgrim.ncp.framework.systems;

import lombok.Getter;

/**
 * 내부에서 취급하고 있는 비지니스 도메인에 대한 구분을 나타내기 위한 enum.
 * <p>
 * 
 * @author narusas
 *
 */
public enum BusinessType {
	  AFFILIATE 		("AF",  	"goods/affiliate",				"제휴"),
	  BRAND 			("BR",  	"brand",						"브랜드"),
	  CALLCENTER 		("CC",  	"callcenter",					"고객센터"),
	  CART 				("CT",  	"cart",							"장바구니"),
	  CLAIM 			("CL",  	"claim",						"클레임"),
	  COMMONS 			("SC",  	"commons",						"시스템공통"),
	  COUPON 			("CP",  	"promotion/coupon",				"쿠폰"),
	  CUSTOMERSERVICE 	("CS",  	"callcenter/customerservice",	"상담관리"),
	  DELIVERY 			("DL",  	"delivery",						"배송"),
	  DISPLAY 			("DP",  	"display",						"전시"),
	  EVENTS 			("ET",  	"promotion/events",				"이벤트"),
	  FAQ 				("FQ",  	"callcenter/faq",				"FAQ"),
	  GOODS 			("GD",  	"goods",						"상품"),
	  GUEST 			("GT",  	"guest",						"비회원"),
	  INTRO 			("MI",  	"intro",						"메인"),
	  MAGAZINE 			("MZ",  	"promotion/magazine",			"매거진"),
	  MEMBER 			("MB",  	"member",						"회원"),
	  MYPAGE 			("MP",  	"mypage",						"마이페이지"),
	  ORDER 			("OD",  	"order",						"주문"),
	  PAYMENT 			("PY",  	"payment",						"결제"),
	  PROMOTION 		("PM",  	"promotion",					"프로모션"),
	  SEARCH 			("SH",  	"search",						"검색"),
	  SETTLEMENT 		("SM",  	"settlement",					"매출및정산"),
	  STAFF 			("ST",  	"mmber/staff",					"임직원"),
	  VENDOR 			("VD",  	"vendor",						"업체"),
	
	; 

	@Getter
	private String code;
	
	@Getter
	private String directory;

	@Getter
	private String description;

	private BusinessType(String code, String directory, String desc) {
		this.code = code;
		this.description = desc;
	}
	
	public static boolean contains(String code) {
		for(BusinessType type: values()){
			if (type.code.equals(code)){
				return true;
			}
		}
		return false;
	}
	
	public static BusinessType codeOf(String code) {
		for(BusinessType type: values()){
			if (type.code.equals(code)){
				return type;
			}
		}
		return null;
	}
}
