package com.plgrim.ncp.cmp.vendor.bo.company;

import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.biz.vendor.result.VendorListResult;
import com.plgrim.ncp.biz.vendor.service.VendorService;
import com.plgrim.ncp.cmp.vendor.bo.VendorCompanyBOComponent;
import com.plgrim.ncp.cmp.vendor.bo.common.VendorBOComponentImpl;
import com.plgrim.ncp.framework.data.SystemPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
 * @author yoon.eun
 * @since 2015. 11. 10
 */
@Component
@Transactional(value = "transactionManager")
public class VendorCompanyBOComponentImpl extends VendorBOComponentImpl implements VendorCompanyBOComponent {

	@Autowired
	private VendorService vendorService;

	/**
	 * 업체등록
	 *
	 * @param com
	 * @throws Exception
	 */
	@Override
	public String insertVendor(SystemPK systemPK, Com com) throws Exception {
		return vendorService.insertVendor(com);
	}

	@Override
	public void updateVendorList(List<VendorListResult> updateList) throws Exception {
		vendorService.updateVendorList(updateList);
	}

	@Override
	public Com selectVendor(SystemPK systemPK, Com com) throws Exception {
		return vendorService.selectVendor(com);
	}

}
