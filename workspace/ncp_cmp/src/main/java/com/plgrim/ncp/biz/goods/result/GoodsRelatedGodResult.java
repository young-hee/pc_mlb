package com.plgrim.ncp.biz.goods.result;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrTodayGod;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("goodsRelatedGodResult ")
public class GoodsRelatedGodResult extends GoodsResult{

	/**
	 * 
	 */
    private static final long serialVersionUID = 4535601869157598906L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    private String godNo;
    
    private String godNm;
    
    private String mobileGodNm;
	
    private String rtlPrc;

    private String csmrPrc;
    
	private String lastSalePrc;
	
	private String godSaleSectCd;
	
	private String imgUrl;
	
	private String totalCount;
	
	private String godDetailUrl;
	
	private String rNum;

	
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
