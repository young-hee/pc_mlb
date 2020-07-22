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
 * @since       2015. 4. 24       
 */
package com.plgrim.ncp.commons.repository;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMallCnvrsUrl;
import com.plgrim.ncp.commons.data.FormSysMallDTO;
import com.plgrim.ncp.commons.data.SysMallDTO;
import com.plgrim.ncp.commons.result.MallSiteResult;

/**
 * 몰 Repository.
 *
 * @author Dennis
 */

@Slf4j
@Repository
public class MallSiteRepository extends AbstractRepository {
	
	/**
	 * 몰 Site Redirect list 조회.
	 * @param sysAuthGrpDTO
	 * @return
	 */
	public List<MallSiteResult> selectSysMallList( FormSysMallDTO  paramData) throws Exception {
		log.info(paramData.toString());
		
		return getSession1().selectList("com.plgrim.ncp.commons.siter.selectSysMallList", paramData);
	}
	
	/**
	 * Mall Site 저장 처리
	 * @param paramData
	 */
	
	public void mergeSiter ( SysMallDTO paramData) throws Exception {

		log.info(paramData.toString());
		
		getSession1().update("com.plgrim.ncp.commons.siter.mergeSiter", paramData);
		
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
	public long selectSysMallListCount(FormSysMallDTO paramData) throws Exception {
	    return getSession1().selectOne("com.plgrim.ncp.commons.siter.selectSysMallListCount", paramData);
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
	public List<Map<String, Object>> selectSysMallListExcel(FormSysMallDTO paramData) throws Exception {
	    return getSession1().selectList("com.plgrim.ncp.commons.siter.selectSysMallListExcel", paramData);
    }

}
