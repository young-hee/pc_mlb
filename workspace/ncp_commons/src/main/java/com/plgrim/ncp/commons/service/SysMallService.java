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
package com.plgrim.ncp.commons.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMallCnvrsUrl;
import com.plgrim.ncp.base.repository.sys.SysMallCnvrsUrlRepository;
import com.plgrim.ncp.commons.data.FormSysMallDTO;
import com.plgrim.ncp.commons.data.SysMallDTO;
import com.plgrim.ncp.commons.repository.MallSiteRepository;
import com.plgrim.ncp.commons.result.MallSiteResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

@Service
public class SysMallService extends AbstractService{

	@Autowired
	MallSiteRepository mallSiteRepository;
	
	@Autowired
	SysMallCnvrsUrlRepository sysMallCnvrsUrlRepository;
	
	
	public List<MallSiteResult> selectSysMallList( FormSysMallDTO paramData) throws Exception {
		return mallSiteRepository.selectSysMallList(paramData);
	}
	
	/**
	 * Mall Site 저장 처리
	 * @param paramData
	 */
	
	public void mergeSiter (List<SysMallDTO> paramList) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		if(paramList != null) {
			for(SysMallDTO item : paramList) {
				item.getSysMallCnvrsUrl().setRegtrId(loginId); // 등록자
				item.getSysMallCnvrsUrl().setUdterId(loginId); // 등록자
				
				mallSiteRepository.mergeSiter(item);
			}
		}
	}
	
	/**
	 * Site Redirct 목록 조회 개수.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysMallListCount(FormSysMallDTO paramData) throws Exception {
	    return mallSiteRepository.selectSysMallListCount(paramData);
    }
	
	/**
	 * Site Redirct 목록 조회 엑셀.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectSysMallListExcel(FormSysMallDTO paramData) throws Exception {
	    return mallSiteRepository.selectSysMallListExcel(paramData);
    }


}
