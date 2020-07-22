package com.plgrim.ncp.cmp.callcenter.fo.helpdesk.bundle;


import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.cmp.callcenter.fo.helpdesk.CallcenterBundleFOComponent;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.biz.member.data.BundleOrderFoDTO;
import com.plgrim.ncp.biz.helpdesk.service.HelpdeskService;
import com.plgrim.ncp.framework.data.SystemPK;

import java.util.List;

@Slf4j
@Transactional(value="transactionManager")
@Component
public class CallcenterBundleFOComponentImpl extends AbstractComponent implements CallcenterBundleFOComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	HelpdeskService helpdeskService;
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


	/**
	 * 단체주문/제휴문의 등록
	 */
	@Override
	public void insertBundleOrder(SystemPK pk, BundleOrderFoDTO bundleOrderFoDTO) throws Exception {
		 helpdeskService.insertBundleOrder(bundleOrderFoDTO);
	}

	/**
	 * 단체주문/제휴문의 개인정보 수집이용에 대한 동의
	 */
	@Override
	public List<SysStplat> selectUserAgr(SysStplat sysStplat) throws Exception {
		//return helpdeskService.selectUserAgr(sysStplat);
		return null;
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
