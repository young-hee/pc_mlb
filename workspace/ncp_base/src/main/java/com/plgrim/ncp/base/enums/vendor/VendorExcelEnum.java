package com.plgrim.ncp.base.enums.vendor;

/**
 * 업체 엑셀
 * @author lss
 *
 */
public enum VendorExcelEnum {
	
	// CONSTANTS
	  VENDOR("업체목록")
	, VENDOR_01("업체구분")
	, VENDOR_02("업체ID")	
	, VENDOR_03("업체명")	
	, VENDOR_04("사업자번호")
	, VENDOR_05("대표자명")
	, VENDOR_06("업체상태")
	, VENDOR_07("등록자")
	, VENDOR_08("등록일시")
	, VENDOR_09("전화번호")
	, VENDOR_10("입점업체구분")
	, VENDOR_11("업체 상태 코드")

	, AFFLIATION("브랜드별 수수료율 관리")
	, AFFLIATION_01("제휴사")	
	, AFFLIATION_02("제휴사코드")	
	, AFFLIATION_03("판매점코드")
	, AFFLIATION_04("브랜드명")	
	, AFFLIATION_05("브랜드코드")
	, AFFLIATION_06("제휴사수수료율")	
	, AFFLIATION_07("대행사수수료율")	
	, AFFLIATION_08("직접연계여부")
	, AFFLIATION_09("재고적용여부")
	, AFFLIATION_10("재고분배율")
	, AFFLIATION_11("등록자")
	, AFFLIATION_12("등록일시")	
	, AFFLIATION_13("최종수정자")
	, AFFLIATION_14("최종수정일시")		
	
	, COMMISSION("브랜드별 수수료율")
	, COMMISSION_01("브랜드경로")	
	, COMMISSION_02("브랜드명")	
	, COMMISSION_03("브랜드코드")
	, COMMISSION_04("브랜드수수료율")	
	, COMMISSION_05("등록자")
	, COMMISSION_06("등록일시")	
	, COMMISSION_07("최종수정자")
	, COMMISSION_08("최종수정일시")	
	
	, EXCPGOD("가격인하율 예외상품관리")
	, EXCPGOD_01("브랜드경로")	
	, EXCPGOD_02("상품명")	
	, EXCPGOD_03("ERP 품번코드")
	, EXCPGOD_04("온라인 품번코드")	
	, EXCPGOD_05("등록자")
	, EXCPGOD_06("등록일시")	
	, EXCPGOD_07("최종수정자")
	, EXCPGOD_08("최종수정일시")
	
	, SHOP_BRAND_FEE("매장별 수수료율 내역")
	, SHOP_BRAND_FEE_01("매장코드")
	, SHOP_BRAND_FEE_02("매장명")
	, SHOP_BRAND_FEE_03("브랜드경로")
	, SHOP_BRAND_FEE_04("브랜드명")
	, SHOP_BRAND_FEE_05("브랜드코드")
	, SHOP_BRAND_FEE_06("배송매장수수료율-구분")
	, SHOP_BRAND_FEE_07("배송매장수수료율-수수료율")
	, SHOP_BRAND_FEE_08("픽업매장수수료율(자기재고)-구분")
	, SHOP_BRAND_FEE_09("픽업매장수수료율(자기재고)-수수료율")
	, SHOP_BRAND_FEE_10("픽업매장수수료율(CDC재고)-구분")
	, SHOP_BRAND_FEE_11("픽업매장수수료율(CDC재고)-수수료율")
	, SHOP_BRAND_FEE_12("배송매장사용여부")
	, SHOP_BRAND_FEE_13("픽업매장사용여부")
	, SHOP_BRAND_FEE_14("등록자")
	, SHOP_BRAND_FEE_15("등록일시")
	, SHOP_BRAND_FEE_16("최종수정자")
	, SHOP_BRAND_FEE_17("최종수정일시")
	
	, SHOP_BRAND("브랜드 매장 내역")
	, SHOP_BRAND_01("매장코드")
	, SHOP_BRAND_02("매장명")
	, SHOP_BRAND_03("지점명")
	, SHOP_BRAND_04("브랜드코드")
	, SHOP_BRAND_05("브랜드")
	, SHOP_BRAND_06("배송매장여부")
	, SHOP_BRAND_07("픽업매장여부")
	, SHOP_BRAND_08("프론트 노출여부")
	, SHOP_BRAND_09("사용여부")
	, SHOP_BRAND_10("유통구분")
	, SHOP_BRAND_11("매장타입")
	, SHOP_BRAND_12("등록자")
	, SHOP_BRAND_13("등록일시")
	, SHOP_BRAND_14("최종수정자")
	, SHOP_BRAND_15("최종수정일시")
	, SHOP_BRAND_16("수선여부")
	, SHOP_BRAND_17("매장교환여부")
	, SHOP_BRAND_18("매장반품여부")
	, SHOP_BRAND_19("퀵배송매장여부")
	
	, SHOP_BRAND_EVT("브랜드 매장 이벤트 내역")
	, SHOP_BRAND_EVT_01("이벤트 명")
	, SHOP_BRAND_EVT_02("이벤트 상태")
	, SHOP_BRAND_EVT_03("진행기간")
	, SHOP_BRAND_EVT_04("사용여부")
	, SHOP_BRAND_EVT_05("등록자")
	, SHOP_BRAND_EVT_06("등록일시")
	
	, SHOP_BRAND_HOLDY("브랜드 매장 휴일 내역")
	, SHOP_BRAND_HOLDY_01("기간")
	, SHOP_BRAND_HOLDY_02("휴일명")
	, SHOP_BRAND_HOLDY_03("등록자")
	, SHOP_BRAND_HOLDY_04("등록일시")
	, SHOP_BRAND_HOLDY_05("최종수정자")
	, SHOP_BRAND_HOLDY_06("최종수정일시")
	
	, COM_AFF_VRSC_COM_CNNC("제휴사별 상품속성관리")
	, COM_AFF_VRSC_COM_CNNC_01("제휴사")
	, COM_AFF_VRSC_COM_CNNC_02("제휴사코드")
	, COM_AFF_VRSC_COM_CNNC_03("가격인하율")
	, COM_AFF_VRSC_COM_CNNC_04("등록자")
	, COM_AFF_VRSC_COM_CNNC_05("등록일시")
	, COM_AFF_VRSC_COM_CNNC_06("최종수정자")
	, COM_AFF_VRSC_COM_CNNC_07("최종수정일시")
	
	, COUNSEL_HOLDY("상담 휴일 내역")
	;

	  
	  
	/**
	 * 업체 목록 엑셀
	 */
	public enum VendorExcel{
		  //VENDOR_01 
		  VENDOR_02 
		  ,VENDOR_03 
		  ,VENDOR_04 
		  ,VENDOR_05
		  ,VENDOR_09
		  //,VENDOR_06
		  ,VENDOR_07
		  ,VENDOR_08
	}

	/**
	 * 제휴사 브랜드 목록
	 */
	public enum AffliationBrndExcel{
		AFFLIATION_01, AFFLIATION_02, AFFLIATION_03, AFFLIATION_04, AFFLIATION_05, AFFLIATION_06, AFFLIATION_07,
		AFFLIATION_08, AFFLIATION_09, AFFLIATION_10, AFFLIATION_11, AFFLIATION_12, AFFLIATION_13, AFFLIATION_14
	}
	
	/**
	 * 브랜드별 수수료율 목록
	 */
	public enum BrndCommissionExcel{
		COMMISSION_01, COMMISSION_02, COMMISSION_03, COMMISSION_04, COMMISSION_05, COMMISSION_06, COMMISSION_07, COMMISSION_08
	}	

	/**
	 * 가격인하율 예외상품관리
	 */
	public enum ExcpGodExcel{
		EXCPGOD_01, EXCPGOD_02, EXCPGOD_03, EXCPGOD_04, EXCPGOD_05, EXCPGOD_06, EXCPGOD_07, EXCPGOD_08
	}
	
	/**
	 * 매장별 수수료율 관리
	 */
	public enum ShopBrandFeeExcel{
		  SHOP_BRAND_FEE_01	, SHOP_BRAND_FEE_02	, SHOP_BRAND_FEE_03	, SHOP_BRAND_FEE_04	, SHOP_BRAND_FEE_05
		, SHOP_BRAND_FEE_06	, SHOP_BRAND_FEE_07	, SHOP_BRAND_FEE_08	, SHOP_BRAND_FEE_09	, SHOP_BRAND_FEE_10
		, SHOP_BRAND_FEE_11	, SHOP_BRAND_FEE_12	, SHOP_BRAND_FEE_13 , SHOP_BRAND_FEE_14 , SHOP_BRAND_FEE_15
		, SHOP_BRAND_FEE_16 , SHOP_BRAND_FEE_17

	}
	
	/**
	 * 브랜드 매장내역 목록
	 */
	public enum ShopBrandExcel{
		SHOP_BRAND_01, SHOP_BRAND_02, SHOP_BRAND_03, SHOP_BRAND_04, SHOP_BRAND_05,
		SHOP_BRAND_06, SHOP_BRAND_07 ,SHOP_BRAND_19, SHOP_BRAND_17, SHOP_BRAND_18  , SHOP_BRAND_09, SHOP_BRAND_16, //SHOP_BRAND_10,
		SHOP_BRAND_11, SHOP_BRAND_12, SHOP_BRAND_13, SHOP_BRAND_14, SHOP_BRAND_15
	}
	
	/**
	 * 브랜드 매장 이벤트 목록
	 */
	public enum ShopBrandEvtExcel{
		SHOP_BRAND_EVT_01, SHOP_BRAND_EVT_02, SHOP_BRAND_EVT_03, SHOP_BRAND_EVT_04 , SHOP_BRAND_EVT_05 , SHOP_BRAND_EVT_06
	}
	
	/**
	 * 브랜드 매장 휴일 목록
	 */
	public enum ShopBrandHoldyExcel{
		SHOP_BRAND_HOLDY_01, SHOP_BRAND_HOLDY_02, SHOP_BRAND_HOLDY_03, SHOP_BRAND_HOLDY_04//, SHOP_BRAND_HOLDY_05, SHOP_BRAND_HOLDY_06
	}
	
	/**
	 * 제휴사별 상품속성관리
	 * @author lss
	 *
	 */
	public enum ComAffVrscComCnnc{
		COM_AFF_VRSC_COM_CNNC_01, COM_AFF_VRSC_COM_CNNC_02, COM_AFF_VRSC_COM_CNNC_03
		,COM_AFF_VRSC_COM_CNNC_04, COM_AFF_VRSC_COM_CNNC_05, COM_AFF_VRSC_COM_CNNC_06
		,COM_AFF_VRSC_COM_CNNC_07
	}

	/**
	 * 입점업체 목록 엑셀
	 */
	public enum PartmalComExcel{
		VENDOR_10
		,VENDOR_02
		,VENDOR_03
		,VENDOR_04
		,VENDOR_05
		,VENDOR_09
		,VENDOR_11
		,VENDOR_07
		,VENDOR_08
	}


	/**
	 * 
	 * @author lss
	 */
	private final String value;

	private VendorExcelEnum(final String value) {
		this.value = value;
	}

	/**
	 * 
	 * @author lss
	 */
	@Override
	public String toString() {
		return value;
	}
}
