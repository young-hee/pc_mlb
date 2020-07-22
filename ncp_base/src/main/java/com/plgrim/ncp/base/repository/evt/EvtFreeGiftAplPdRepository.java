
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftAplPd;

/**
 * The Class EvtFreeGiftAplPdRepository.
 */
@Repository
public class EvtFreeGiftAplPdRepository extends AbstractRepository {
	

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
	 * 이벤트 경품 적용 기간 상세 조회.
	 *
	 * @param evtFreeGiftAplPd the EvtFreeGiftAplPd
	 * @return the EvtFreeGiftAplPd
	 * @throws SQLException the SQL exception
	 */
	public EvtFreeGiftAplPd selectEvtFreeGiftAplPd(EvtFreeGiftAplPd evtFreeGiftAplPd) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtFreeGiftAplPd", evtFreeGiftAplPd);
	}
	
	/**
	 * 이벤트 경품 적용 기간 등록.
	 *
	 * @param evtFreeGiftAplPd the EvtFreeGiftAplPd
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtFreeGiftAplPd(EvtFreeGiftAplPd evtFreeGiftAplPd) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtFreeGiftAplPd", evtFreeGiftAplPd);
	}
	
	/**
	 * 이벤트 경품 적용 기간 수정.
	 *
	 * @param evtFreeGiftAplPd the EvtFreeGiftAplPd
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtFreeGiftAplPd(EvtFreeGiftAplPd evtFreeGiftAplPd) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtFreeGiftAplPd", evtFreeGiftAplPd);
	}
	
	/**
	 * 이벤트 경품 적용 기간 삭제.
	 *
	 * @param evtFreeGiftAplPd the EvtFreeGiftAplPd
	 * @return the EvtFreeGiftAplPd
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtFreeGiftAplPd(EvtFreeGiftAplPd evtFreeGiftAplPd) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtFreeGiftAplPd", evtFreeGiftAplPd);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

