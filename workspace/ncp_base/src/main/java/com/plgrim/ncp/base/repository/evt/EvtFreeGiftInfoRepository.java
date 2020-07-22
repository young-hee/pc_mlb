
/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://www.plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              			Date                         	Description
 * ------------------   		--------------                  ------------------
 * Generator(Generator)		2018-05-23                      
 */
package com.plgrim.ncp.base.repository.evt;

import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftInfo;

/**
 * The Class EvtFreeGiftInfoRepository.
 */
@Repository
public class EvtFreeGiftInfoRepository extends AbstractRepository {
	

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
	 * 이벤트 경품 정보 상세 조회.
	 *
	 * @param evtFreeGiftInfo the EvtFreeGiftInfo
	 * @return the EvtFreeGiftInfo
	 * @throws SQLException the SQL exception
	 */
	public EvtFreeGiftInfo selectEvtFreeGiftInfo(EvtFreeGiftInfo evtFreeGiftInfo) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtFreeGiftInfo", evtFreeGiftInfo);
	}
	
	/**
	 * 이벤트 경품 정보 등록.
	 *
	 * @param evtFreeGiftInfo the EvtFreeGiftInfo
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtFreeGiftInfo(EvtFreeGiftInfo evtFreeGiftInfo) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtFreeGiftInfo", evtFreeGiftInfo);
	}
	
	/**
	 * 이벤트 경품 정보 수정.
	 *
	 * @param evtFreeGiftInfo the EvtFreeGiftInfo
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtFreeGiftInfo(EvtFreeGiftInfo evtFreeGiftInfo) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtFreeGiftInfo", evtFreeGiftInfo);
	}
	
	/**
	 * 이벤트 경품 정보 삭제.
	 *
	 * @param evtFreeGiftInfo the EvtFreeGiftInfo
	 * @return the EvtFreeGiftInfo
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtFreeGiftInfo(EvtFreeGiftInfo evtFreeGiftInfo) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtFreeGiftInfo", evtFreeGiftInfo);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

