
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtImg;

/**
 * The Class EvtImgRepository.
 */
@Repository
public class EvtImgRepository extends AbstractRepository {
	

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
	 * 이벤트 이미지 상세 조회.
	 *
	 * @param evtImg the EvtImg
	 * @return the EvtImg
	 * @throws SQLException the SQL exception
	 */
	public EvtImg selectEvtImg(EvtImg evtImg) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtImg", evtImg);
	}
	
	/**
	 * 이벤트 이미지 등록.
	 *
	 * @param evtImg the EvtImg
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtImg(EvtImg evtImg) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtImg", evtImg);
	}
	
	/**
	 * 이벤트 이미지 수정.
	 *
	 * @param evtImg the EvtImg
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtImg(EvtImg evtImg) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtImg", evtImg);
	}
	
	/**
	 * 이벤트 이미지 삭제.
	 *
	 * @param evtImg the EvtImg
	 * @return the EvtImg
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtImg(EvtImg evtImg) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtImg", evtImg);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

