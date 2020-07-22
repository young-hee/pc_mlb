package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GodExtend extends God {
	private static final long serialVersionUID = -8556525224456849372L;

    /** 상품 판매 여부 코드명. */
    private String godSaleSectNm;

    /** 상품 승인 코드명. */
    private String godAprvSectNm;
    
    /** 상품 구분 코드명. */
    private String godTpNm;
    
    /** 전시여부 */
    private String dspYnNm;
    
    /** 예약 판매 여부 명 */
    private String resveSaleGodYnNm;
        
    /** 추천 성별명 */
    private String recomendSexNm;
    
    /** 입점구분 명 */
    private String partmalSectNm;
	
	/** 상품 총가용재고. */
	private long totUsefulInvQty;

	/** 상품 판매예정수량. */
	private long salePrearngeQty;
	
	/** 상품 안전재고. */
	private long safeInvQty;

    /** 상품 안전재고 사용 여부. */
	private String safeInvUseYn;
	
	/** 상품 온라인 한정재고. */
	private long onlneLmttInvQty;

	/** 상품 제휴몰 한정재고. */
	private long affComLmttInvQty;

	/** 상품 예약재고. */
	private long resveInvQty;
	
	/** 수량. */
	private long qty;

    /** 상품 SKU No. */
	private String skuNo;
	
	/** 단품명. */
	private String itmNm;

	/** 단품번호. */
	private String itmNo;
	
	/** 단품 상태 코드 */
	private String ItmStatCd;
	
	/** 상품 이력 순번. */
	private java.lang.Integer godHistTurn;
	
	/** 단품 이력 순번. */
	private java.lang.Integer itmHistTurn;
	
    /** 상품 이미지 유무. */
    private String imgExistYn;
    
    /** 상품 이미지 전체 경로. */
    private String imgFullUrl;	
    
    /** 상품 이미지 경로. */
    private String imgUrl;
    
    /** 상품 상세 경로. */
    private String godDetailUrl;
    
    /** 포인트(마일리지) 적립금. */
    private java.math.BigDecimal pntSavMny;
    
    /** 웹포인트 적립금. */
    private java.math.BigDecimal webpntSavMny;
    
    /** 영문상품명. */
    private String godEngNm;
    
    /** 중문상품명. */
    private String godChiNm;
    
    /** 전시 카테고리 번호 리스트*/
    private String dspCtgryNoList;
    
    /** 옵션명 목록 */
    private String optValNmList;
}
