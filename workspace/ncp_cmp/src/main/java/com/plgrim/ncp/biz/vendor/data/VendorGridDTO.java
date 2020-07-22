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
 * @since       2015. 4. 29       
 */
package com.plgrim.ncp.biz.vendor.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.com.ComAffComDcExcpGod;
import com.plgrim.ncp.base.entities.datasource1.com.ComAffVrscComBrnd;
import com.plgrim.ncp.base.entities.datasource1.com.ComAffVrscComCnnc;
import com.plgrim.ncp.base.entities.datasource1.com.ComBrnd;
import com.plgrim.ncp.base.entities.datasource1.com.ComChrger;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcExcp;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdmin;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShop;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * VendorGridDTO
 * @author lss
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class VendorGridDTO extends AbstractDTO {

	/**
	 * serialVersionUID
	 */
    private static final long serialVersionUID = 1L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    
	/**
	 * 조회 DTO
	 */
	VendorSearchDTO search = new VendorSearchDTO();
	
	/**
	 * comId
	 */
	private String comId;
	
	/**
	 * comDmstcDlvCstPlcExcp
	 */
	private ComDmstcDlvCstPlcExcp comDmstcDlvCstPlcExcp;
	
    /**
     * comBrnd
     */
    private ComBrnd comBrnd;    
    
    /**
     * comAffComDcExcpGod
     */
    private ComAffComDcExcpGod comAffComDcExcpGod;
    
    /**
     * sysAdmin
     */
    private SysAdmin sysAdmin;
    
    /**
     * comDmstcDlvCstPlc
     */
    public ComDmstcDlvCstPlc comDmstcDlvCstPlc;
    
    public java.lang.Long dmstcDlvCstPlcSn;
    
    /**
     * sysShop
     */
    public SysShop sysShop;
    
    /**
     * sysBrnd
     */
    public SysBrnd sysBrnd;
    
    /**
     * sysShopBrnd
     */
    public SysShopBrnd sysShopBrnd;
    
    /**
     * comAffVrscComBrnd
     */
    public ComAffVrscComBrnd comAffVrscComBrnd;
    
    /**
     * comAffVrscComCnnc
     */
    public ComAffVrscComCnnc comAffVrscComCnnc;
    
    
    /**
     * 테이블이 아닌 개별변수
     */
    private String brndPath;
    
    /**
     * brndNm
     */
    private String brndNm;
    
    /**
     * baseFeeRt
     */
    private String baseFeeRt;

    /**
     * resvFeeRt
     */
    private String resvFeeRt;
    
    /**
     * dummyText
     */
    private String dummyText;
    
    /**
     * comTpCd
     */
    private String comTpCd;
    
    /**
     * erpGodNo
     */
    private String erpGodNo;
    
    /**
     * godNm
     */
    private String godNm;

    /**
     * cnChrgerNm
     */
    private String cnChrgerNm;

    /**
     * enChrgerNm
     */
    private String enChrgerNm;

    /**
     * 배송담당자 정보
     */
    ComChrger comChrger;
    
    /**
     * 반품담당자 정보
     */
    ComChrger retComChrger;
    
    /**
	 * 배송 매장 수수료 사용 금액 또는 율
	 */
	private java.math.BigDecimal dlvShopFeeUse;
	
	/**
	 * 픽업 매장 수수료 사용 금액 또는 율
	 */
	private java.math.BigDecimal pkupShopFeeUse;
	
	/**
	 * 픽업 매장 물류 수수료 사용 금액 또는 율
	 */
	private java.math.BigDecimal pkupShopLgsFeeUse;
	
	/**
	 * 글로벌 배송 방법 설명
	 */
	private String dlvMthdDscrEng;
	/**
	 * 중국 배송 방법 설명
	 */
	private String dlvMthdDscrChi;
	/**
	 * 모바일 글로벌 배송 방법 설명
	 */
	private String mobileDlvMthdDscrEng;
	/**
	 * 모바일 중국 배송 방법 설명
	 */
	private String mobileDlvMthdDscrChi;	
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
