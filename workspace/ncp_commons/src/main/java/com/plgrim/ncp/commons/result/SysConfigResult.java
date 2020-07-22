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
package com.plgrim.ncp.commons.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBoSite;
import com.plgrim.ncp.base.entities.datasource1.sys.SysDynmcConfig;
import com.plgrim.ncp.base.entities.datasource1.sys.SysDynmcConfigDetail;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMenu;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysConfigResult extends AbstractResult {
    private static final long serialVersionUID = 591797712065396263L;
    
    SysDynmcConfig sysDynmcConfig; //설정 master
	SysDynmcConfigDetail sysDynmcConfigDetail; //설정 detail
	String mallNm;
	String configKey;
	String configVal;
	String useYn;
	String strBegDt;
	String strEndDt;
}

