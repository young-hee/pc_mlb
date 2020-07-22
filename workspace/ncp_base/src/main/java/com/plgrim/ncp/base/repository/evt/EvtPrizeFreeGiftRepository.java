
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPrizeFreeGift;

/**
 * The Class EvtPrizeFreeGiftRepository.
 */
@Repository
public class EvtPrizeFreeGiftRepository extends AbstractRepository {
	

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
	 * 이벤트 당첨 경품 상세 조회.
	 *
	 * @param evtPrizeFreeGift the EvtPrizeFreeGift
	 * @return the EvtPrizeFreeGift
	 * @throws SQLException the SQL exception
	 */
	public EvtPrizeFreeGift selectEvtPrizeFreeGift(EvtPrizeFreeGift evtPrizeFreeGift) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtPrizeFreeGift", evtPrizeFreeGift);
	}
	
	/**
	 * 이벤트 당첨 경품 등록.
	 *
	 * @param evtPrizeFreeGift the EvtPrizeFreeGift
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtPrizeFreeGift(EvtPrizeFreeGift evtPrizeFreeGift) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtPrizeFreeGift", evtPrizeFreeGift);
	}
	
	/**
	 * 이벤트 당첨 경품 수정.
	 *
	 * @param evtPrizeFreeGift the EvtPrizeFreeGift
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtPrizeFreeGift(EvtPrizeFreeGift evtPrizeFreeGift) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtPrizeFreeGift", evtPrizeFreeGift);
	}
	
	/**
	 * 이벤트 당첨 경품 삭제.
	 *
	 * @param evtPrizeFreeGift the EvtPrizeFreeGift
	 * @return the EvtPrizeFreeGift
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtPrizeFreeGift(EvtPrizeFreeGift evtPrizeFreeGift) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtPrizeFreeGift", evtPrizeFreeGift);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

