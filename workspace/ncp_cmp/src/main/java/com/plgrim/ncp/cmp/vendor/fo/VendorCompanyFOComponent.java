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
 * @since       2015. 5. 8       
 */
package com.plgrim.ncp.cmp.vendor.fo;

import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.com.ComChrger;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopEvt;
import com.plgrim.ncp.biz.vendor.data.*;
import com.plgrim.ncp.biz.vendor.result.*;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

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
public interface VendorCompanyFOComponent {

	/**
	 * 업체유형에 따른 업체 목록 조회.(페이징처리되지않은)
	 *
	 * @param systemPK [설명]
	 * @param vendorSearchDTO [설명]
	 * @return the vendor list
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public List<VendorListResult> getVendorList(SystemPK systemPK, VendorSearchDTO vendorSearchDTO) throws Exception ;

	/**
	 * 업체 목록 수 조회
	 * @param vendorSearchDTO
	 * @return
	 * @throws Exception
	 */
	public Long selectVendorListCount(VendorSearchDTO vendorSearchDTO) throws Exception ;


}
