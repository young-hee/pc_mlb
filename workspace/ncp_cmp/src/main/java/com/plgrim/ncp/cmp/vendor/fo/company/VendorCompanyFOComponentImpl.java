/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 5. 8       
 */
package com.plgrim.ncp.cmp.vendor.fo.company;

import com.plgrim.ncp.biz.vendor.data.*;
import com.plgrim.ncp.biz.vendor.result.*;
import com.plgrim.ncp.biz.vendor.service.*;
import com.plgrim.ncp.cmp.vendor.fo.VendorCompanyFOComponent;
import com.plgrim.ncp.framework.data.SystemPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


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
 * @since 2015. 4. 7
 */

/**
 * @author user
 *
 */
@Component
public class VendorCompanyFOComponentImpl implements VendorCompanyFOComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** 업체 서비스. */
	@Autowired
	VendorService vendorService;

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
	@Override
	public List<VendorListResult> getVendorList(SystemPK systemPK,
												VendorSearchDTO vendorSearchDTO) throws Exception {
		// TODO Auto-generated method stub
		return vendorService.selectVendorList(vendorSearchDTO);
	}
	@Override
	public Long selectVendorListCount(VendorSearchDTO vendorSearchDTO) throws Exception {
		return vendorService.selectVendorListCount(vendorSearchDTO);
	}



}
