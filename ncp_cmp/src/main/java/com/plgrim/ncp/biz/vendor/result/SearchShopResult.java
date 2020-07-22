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

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;

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
public class SearchShopResult extends AbstractResult {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

    /**
     *
     */
    private static final long serialVersionUID = 2097822591560688187L;


    private SysBrnd sysBrnd;
    private String sido;
    private String gugun;
    private String sidocd;
    private String guguncd;
    private String shopId;
    private String shopNm;
    private String brndId;
    private String brndNm;
    private String shopTpCd;
    private String otltShopYn;
    private String eventYn;
    private String baseAddr;
    private String detailAddr;
    private String shopTelAreaNo;
    private String shopTelTlofNo;
    private String shopTelTlofWthnNo;
    private String wkdyOperBegHhmm;
    private String wkdyOperEndHhmm;
    private String lttd;
    private String lgtd;
    private List<String> shopImgUrl;
    private String repairShopYn;
    // #51297 매장찾기/상품상세 셀링 포인트 노출 기준 추가 변경 START
    private String rtgodShopYn; // 반품매장 여부
    private String exchgShopYn; // 교환매장 여부
    // #51297 매장찾기/상품상세 셀링 포인트 노출 기준 추가 변경 END

    private String otltCnt;
    private String gnlrCnt;
    private String flgshpCnt;
    private String drtstorCnt;
    private String stretCnt;
    private String eventCnt;
    private String totCnt;
    private String pickupCnt;
    private String repairCnt;


    private String evtNm;
    private String evtCont;
    private String evtBegDate;
    private String evtEndDate;

    private String distance;
    private String mainShopImgUrl;
    private List<String> brndIds;
    private List<String> brndNms;

    private String parkngPsbYn;
    private String parkngUpodnlInfoCont;
    private String trnsportInfoCont;
    private String pkupShopYn;

    private long emenge01;
    private long emenge02;

    private String itmNm;
    private String itmQtyStr;
    
    
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
