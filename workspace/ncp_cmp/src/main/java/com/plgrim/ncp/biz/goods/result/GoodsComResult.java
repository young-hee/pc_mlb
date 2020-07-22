package com.plgrim.ncp.biz.goods.result;

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.com.ComBrnd;
import com.plgrim.ncp.base.entities.datasource1.com.ComChrger;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComGodOpt;
import com.plgrim.ncp.base.entities.datasource1.com.ComGodOptVal;
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsComResult extends GoodsResult{
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 6070669218286880859L;
	
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    
    /** 업체 엔티티. */
    private Com com;
    
    /** 업체 브랜드 엔티티. */
    private ComBrnd comBrnd;
	
    /** 업체  담당자 엔티티. */
	private ComChrger comChrger;
	
	/** 업체  국내 배송비 정책 엔티티. */
	private ComDmstcDlvCstPlc comDmstcDlvCstPlc;
	
	/** 업체  해외 배송비 정책 엔티티. */
	private ComOvseaDlvCstPlc comOvseaDlvCstPlc;
	
	/** 시스템 브랜드 엔티티. */
	private SysBrnd sysBrnd;
	
	/** 업체  국내 배송비 정책 리스트. */
	private List<ComDmstcDlvCstPlc> comDmstcDlvCstPlcList;
	
	/** 업체  해외 배송비 정책 리스트. */
	private List<ComOvseaDlvCstPlc> comOvseaDlvCstPlcList;
	
    /** 업체  해외 국내 배송비 정책 리스트. */
    private List<ComDmstcDlvCstPlc> comOvseaDlvDmstcDlvCstPlcList;

	private ComGodOpt comGodOpt;
	
	
	private ComGodOptVal comGodOptVal;
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
