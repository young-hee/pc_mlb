package com.plgrim.ncp.biz.goods.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrTodayGod;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class GoodsTodayGodFoResult extends AbstractResult{

	/**
	 * 
	 */
    private static final long serialVersionUID = 4535601869157598906L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 상품 이미지 엔티티. */
	private GodImg godImg;
	
	/** 회원 오늘본상품 엔티티. */
	private MbrTodayGod mbrTodayGod;
	
	private String sbj;
	
	private String godNm;
	
	private String lastSalePrc;
	
	private String imgUrl;
	
	private String todayGodSn;
	
	private String todayGodSectCd;
	
	private String godNo;
	
	private String evtNo;
	
	private String promtSn;
	
	private String totalCount;
	
	private String progressCd;
	
	private String brndNm;
	
	private String pcUrl;

	private String cookieKey;

	private String godSaleSectCd;

	private String rtlPrc;

    private String brndId;
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
