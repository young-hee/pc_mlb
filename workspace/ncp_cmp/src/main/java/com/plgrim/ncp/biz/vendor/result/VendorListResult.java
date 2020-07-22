/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 4. 28       
 */
package com.plgrim.ncp.biz.vendor.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.com.ComAffComBrndHist;
import com.plgrim.ncp.base.entities.datasource1.com.ComAffComDcExcpGod;
import com.plgrim.ncp.base.entities.datasource1.com.ComAffVrscComBrnd;
import com.plgrim.ncp.base.entities.datasource1.com.ComAffVrscComCnnc;
import com.plgrim.ncp.base.entities.datasource1.com.ComBrnd;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author tktaeki.kim
 * @since 2015. 4. 14
 */

/**
 * Instantiates a new vendor list result.
 */

/**
 * Instantiates a new vendor list result.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class VendorListResult extends AbstractResult {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
    /**
	 * 
	 */
    private static final long serialVersionUID = 2097822591560688187L;

	/**
     * 업체 Entity
     */
    private Com com;
    /** The com brnd resve. */
    private ComBrnd comBrnd;
    
	/** The com aff com dc excp god. */
	private ComAffComDcExcpGod comAffComDcExcpGod;    
    
	/**
	 * comAffVrscComBrnd
	 */
    private ComAffVrscComBrnd comAffVrscComBrnd;
    
    /**
     * comAffVrscComCnnc
     */
    private ComAffVrscComCnnc comAffVrscComCnnc;
    
    /**
     * comAffComBrndHist
     */
    private ComAffComBrndHist comAffComBrndHist;
    
    /**
     * telNo
     */
    private String telNo;
    
    /**
     * cellNo
     */
    private String cellNo;
    
    /**
     * brndPath
     */
    private String brndPath;
    
    
    /** The brnd nm. */
    private String brndNm;
    
    /** The base fee rt. */
    private String baseFeeRt;

    /** The resv fee rt. */
    private String resvFeeRt;
    
    /** The dummy text. */
    private String dummyText;
    
    /** The god nm. */
    private String godNm;
    
    /** The resve tp cd. */
    private String resveTpCd;
    
    
    /**
     * erp 상품번호
     */
    private String erpGodNo;
    
    /**
	 * 등록자명
	 */
	private String regterNm;
	
	/**
	 * 등록일(YYYY-MM-DD HH24:MI)
	 */
	private String viewRegDt;
	
	/**
	 * 수정자명
	 */
	private String udterNm;
	
	/**
	 * 수정일(YYYY-MM-DD HH24:MI)
	 */
	private String viewUdtDt;
	
	/**
	 * 업체 유형 명
	 */
	private String comTpNm;
	
	/**
	 * 업체 유형 코드
	 */
	private String comTpCd;
	
	/**
	 * 제휴사명
	 */
	private String affComNm;
	
	
	/**
	 * 업체 ID
	 */
	private String comId;
	
	/**
	 * 업체명 
	 */
	private String comNm;
	
	/**
	 * 변경이전이력 율
	 */
	private java.math.BigDecimal modBfHistRt;
	
	/**
	 * 변경이후이력 율
	 */
	private java.math.BigDecimal modAfHistRt;

	/**
	 * 업체 상세 유형 명
	 */
	private String comDetailTpNm;
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

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
