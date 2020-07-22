package com.plgrim.ncp.biz.goods.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShop;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;

@Data
@EqualsAndHashCode(callSuper=false)
public class GoodsSearchFoResult extends AbstractResult {
    /**
     *
     */
    private static final long serialVersionUID = 8712313852603790822L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

    /** 시스템 매장 브랜드 엔티티. */
    private SysShopBrnd sysShopBrnd;

    /** 시스템 매장 엔티티. */
    private SysShop sysShop;

    /** 시스템 브랜드 엔티티. */
    private SysBrnd sysBrnd;

    private String day;

    private String invQty;

    /** 요일 코드. */
    private String dowCd;

    /** 공휴일 여부. */
    private String hldyYn;
    /** 픽업 가능일 : today, nextday */
    private String pickupDay;

    private String distance;

    private String otltCnt;

    private String gnlrCnt;

    private String flgshpCnt;

    private String drtstorCnt;

    private String stretCnt;

    private String eventCnt;

    private String totCnt;

    private String eventYn;

    private String repairShopYn;

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
