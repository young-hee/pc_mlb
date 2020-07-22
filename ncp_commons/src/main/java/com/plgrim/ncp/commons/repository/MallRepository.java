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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMall;

/**
 * 몰 Repository.
 *
 * @author Jessi
 */
@Repository
public class MallRepository extends AbstractRepository {

	/**
	 * 몰 코드 조회.
	 *
	 * @return List<MenuViewResult>
	 * @since 2015. 4. 24
	 */
	public List<SysMall> getMallCodeList() {
		return getSession1().selectList("com.plgrim.ncp.commons.mall.getMallCodeList");
	}
	
	/**
	 * 이벤트 응모가능여부
	 * 
	 */
	public String selectCampaginEvtInfo(String evtNo) {
		return getSession1().selectOne("com.plgrim.ncp.commons.mall.selectCampaginEvtInfo",evtNo);
    }
	
	/**
	 * 수신동의 유도 캠페인 쿠폰 발급 카운트
	 * 
	 */
	public int selectMyCampaginEvtCount(String mbrNo, String evtNo) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("mbrNo", mbrNo);
		param.put("evtNo", evtNo);
		return getSession1().selectOne("com.plgrim.ncp.commons.mall.selectMyCampaginEvtCount",param);
    }
}
