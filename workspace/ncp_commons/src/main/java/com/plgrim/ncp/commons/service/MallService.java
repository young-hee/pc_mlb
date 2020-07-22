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
package com.plgrim.ncp.commons.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMall;
import com.plgrim.ncp.commons.repository.MallRepository;

/**
 * MallService Implementation.
 *
 * @author Jessi
 */
@Service
public class MallService extends AbstractService {

	/** 공통코드 조회 Repository. */
	@Autowired
	private MallRepository mallRepository;
	
	/**
	 * 몰 코드 조회.
	 *
	 * @return List<MenuViewResult>
	 * @since 2015. 4. 24
	 */
	public List<SysMall> getMallCodeList() {
		return mallRepository.getMallCodeList();
	}

	/**
	 * 이벤트 응모가능여부
	 * 
	 */
	public String selectCampaginEvtInfo(String evtNo) {
	    return mallRepository.selectCampaginEvtInfo(evtNo);
    }
	
	/**
	 * 수신동의 유도 캠페인 쿠폰 발급 카운트
	 * 
	 */
	public int selectMyCampaginEvtCount(String mbrNo, String evtNo) {
	    return mallRepository.selectMyCampaginEvtCount(mbrNo, evtNo);
    }
}
