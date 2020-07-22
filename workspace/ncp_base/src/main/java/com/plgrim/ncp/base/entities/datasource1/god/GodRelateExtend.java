package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GodRelateExtend extends GodRelate{

	/**
	 * 
	 */
    private static final long serialVersionUID = 338122766755115841L;
    
    /*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

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
    
    /**
	 * ERP 품번
	 */
    private String erpGodNo;

    /**
	 * 연관 ERP 품번
	 */
    private String relateErpGodNo;

    /**
     * 상품명
     */
    private String godNm;
    
	/**
	 * 상품 판매 구분 코드
	 * ㅁ. 상품 판매 구분 : GOD_SALE_SECT
	 *    >. 판매중 : SALE_PROGRS
	 *    >. 일시품절 : SMTM_SLDOUT
	 *    >. 품절 : SLDOUT
	 *    >. 판매 종료 : SALE_END	 
	 */
	private String godSaleSectCd;
	private String godSaleSectNm;
    
	/**
	 * 상품 유형 코드
	 * ㅁ. 상품구분 : GOD_TP
	 *    >. 일반상품 : GNRL_GOD
	 *    >. 사입 사은품 : PCHS_GFT
	 *    >. 전환 사은품 : CNVRS_GFT
	 *    >. 세트상품 : SET_GOD
	 *    >. 패키지 상품 : PCKAGE_GOD
	 *    >. 상품권 : GFCT
	 *    >. 마일리지 상품 : MILE_GOD	 
	 */
	private String godTpCd;
	private String godTpNm;
	
    /**
	 * 전시 여부	 
	 */
	private String dspYn;
	private String dspYnNm;
	
	/**
	 * 브랜드 ID
	 * ㅁ. 온라인에서 사용하는 브랜드 ID	 
	 */
	private String brndId;
	private String brndNm;
}
