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
 * @since       2015. 7. 2       
 */
package com.plgrim.ncp.cmp.common.bo.system.shorturl;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.cmp.common.bo.system.SystemShorturlBOComponent;
import com.plgrim.ncp.commons.data.SysMallDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMallCnvrsUrl;
import com.plgrim.ncp.commons.data.FormSysMallDTO;
import com.plgrim.ncp.commons.result.MallSiteResult;
import com.plgrim.ncp.commons.service.SysMallService;
import com.plgrim.ncp.framework.data.SystemPK;


//@Slf4j
@Component
public class SystemShorturlBOComponentImpl extends AbstractComponent implements SystemShorturlBOComponent {

	@Autowired
	SysMallService sysMallService;


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


	/**
	 * Site Redirct 목록 조회.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	@Override
	public List<MallSiteResult> selectSysMallList(SystemPK systemPK, FormSysMallDTO paramData) throws Exception {

		return sysMallService.selectSysMallList(paramData);
	}

	/**
	 * Site Redirct 목록 조회 개수.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	@Override
	public long selectSysMallListCount(SystemPK systemPK, FormSysMallDTO paramData) throws Exception {
		return sysMallService.selectSysMallListCount(paramData);
	}

	/**
	 * Site Redirct 목록 조회 엑셀.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	@Override
	public List<Map<String, Object>> selectSysMallListExcel(SystemPK systemPK, FormSysMallDTO paramData) throws Exception {
		return sysMallService.selectSysMallListExcel(paramData);
	}


	@Override
	public void updateSiter(List<SysMallDTO> paramList) throws Exception {
		sysMallService.mergeSiter(paramList);
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
