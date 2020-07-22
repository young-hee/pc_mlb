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
 * @since       2015. 6. 15       
 */
package com.plgrim.ncp.commons.result;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShop;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopPhoto;

/**
 * @author ed
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysShopResult extends AbstractResult {

	/**
	 * UID
	 */
    private static final long serialVersionUID = 591797712065396263L;
    
    private SysShop sysShop; // 매장
    private SysBrnd sysBrnd; // 브랜드
    private String regtrIdNm; // 등록자명
    private String regDtNm; // 등록일
    private String udterIdNm; // 수정자명
    private String udtDtNm; // 수정일    
	private String cpnPrmNm; // 쿠폰명	
	
	private List<SysShopPhoto> sysShopPhotoList; //시스템 매장 브랜드 이미지 Entity
	
	private List<SysShopPhoto> sysShopPhotoDeleteList = new ArrayList<SysShopPhoto>(); // 매장 이미지 삭제
}

