package com.plgrim.ncp.biz.display.repository;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplatHistExtends;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DisplayOldPolicyRepository extends AbstractRepository{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

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
	
	public SysStplat selectOldSysStplat(SysStplatHistExtends sysStplat) throws Exception {
		SysStplat result = getSession1().selectOne(
		        "com.plgrim.ncp.biz.display.selectOldSysStplat", sysStplat);
		return result;
	}

}
