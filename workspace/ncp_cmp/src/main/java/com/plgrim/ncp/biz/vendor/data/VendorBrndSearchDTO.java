/* Copyright (c) 2013 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 *
 */
package com.plgrim.ncp.biz.vendor.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndPrdlst;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndPrdlstHist;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPrdlstCd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShop;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopEvt;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopHoldy;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * VendorBrndSearchDTO
 * @author lss
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class VendorBrndSearchDTO extends AbstractDTO {

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
	 * 시스템 매장 브랜드 Entity
	 */
	private SysShopBrnd sysShopBrnd;

	/**
	 * 시스템 브랜드 Entity
	 */
	private SysBrnd sysBrnd;

	/**
	 * 시스템 매장 Entity
	 */
	private SysShop sysShop;

	/**
	 * 시스템 품목 Entity
	 */
	private SysPrdlstCd sysPrdlstCd;

	/**
	 * 시스템 브랜드 품목이력 Entity
	 */
	private SysBrndPrdlstHist sysBrndPrdlstHist;

	/**
	 * 시스템 브랜드 품목 Entity
	 */
	private SysBrndPrdlst sysBrndPrdlst;

	/**
	 * 시스템 매장 이벤트 Entity
	 */
	private SysShopEvt sysShopEvt;

	/**
	 * 시스템 매장 휴일 Entity
	 */
	private SysShopHoldy sysShopHoldy;

	/**
	 *매장명, 매장코드
	 */
	private String searchGubunTxt;

	/**
	 * 	조회구분
	 */
	private String searchGubun;

   /**
	 * 	브랜드매장타입
	 */
	private List<String> searchBrndShopType;

	/**
	 * 	유통구분
	 */
	private List<String> searchDistbSectCd;

	/**
    * 페이지번호
    */
	private String pageNo;

	/**
	 * 브랜드 매장 이벤트 상태
	 */
	private String evtStatus;

	/**
	 * 브랜드 매장 이벤트 상태 리스트 검색
	 */
	private List<String> evtStatusList;

	/**
	 * 브랜드 매장 이벤트 사용여부 리스트 검색
	 */
	private List<String> evtUseYnList;

	/**
	 * 브랜드 매장 휴일 검색 시작일
	 */
	private String searchHoldyBegDate;

	/**
	 * 브랜드 매장 휴일 검색 종료일
	 */
	private String searchHoldyEndDate;

	/**
	 * 브랜드&품목 수수료
	 */
	private java.math.BigDecimal shopRepairFee;

	/**
	 * 브랜드&품목 다건 검색
	 */
	private String searchBrndIds;

	/**
	 * 브랜드&품목 다건 검색 Arr
	 */
	private String[] searchBrndIdsArr;

	/**
	 * 브랜드&품목 엑셀여부 (초기값 N)
	 */
	private String brndPldsExcelYn = "N";

	/**
	 * 수선정보관리 조회시 브랜드값이 전체일때
	 */
	private String sysBrndBrndIdEmptyValue;
	/**
	 * 수선정보관리 조회시 브랜드값이 전체일때
	 */
	private String[] sysBrndBrndIdEmptyValueArr;
	
    /** 브랜드ID 멀티. */
    private String[] brndIds;	

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
