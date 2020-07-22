
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtSnsReply;

/**
 * The Class EvtSnsReplyRepository.
 */
@Repository
public class EvtSnsReplyRepository extends AbstractRepository {
	

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
	 * 이벤트 SNS 댓글 상세 조회.
	 *
	 * @param evtSnsReply the EvtSnsReply
	 * @return the EvtSnsReply
	 * @throws SQLException the SQL exception
	 */
	public EvtSnsReply selectEvtSnsReply(EvtSnsReply evtSnsReply) {
		return getSession1().selectOne("com.plgrim.ncp.base.selectEvtSnsReply", evtSnsReply);
	}
	
	/**
	 * 이벤트 SNS 댓글 등록.
	 *
	 * @param evtSnsReply the EvtSnsReply
	 * @throws SQLException the SQL exception
	 */
	public void insertEvtSnsReply(EvtSnsReply evtSnsReply) {
		getSession1().insert("com.plgrim.ncp.base.insertEvtSnsReply", evtSnsReply);
	}
	
	/**
	 * 이벤트 SNS 전시여부 수정
	 * @param evtSnsReply
	 */
	public void updateEvtSnsReplyDspYn(EvtSnsReply evtSnsReply) {
		getSession1().update("com.plgrim.ncp.base.updateEvtSnsReplyDspYn", evtSnsReply);
	}
	
	/**
	 * 이벤트 SNS 댓글 수정.
	 *
	 * @param evtSnsReply the EvtSnsReply
	 * @throws SQLException the SQL exception
	 */
	public int updateEvtSnsReply(EvtSnsReply evtSnsReply) {
		return getSession1().update("com.plgrim.ncp.base.updateEvtSnsReply", evtSnsReply);
	}
	
	/**
	 * 이벤트 SNS 댓글 삭제.
	 *
	 * @param evtSnsReply the EvtSnsReply
	 * @return the EvtSnsReply
	 * @throws SQLException the SQL exception
	 */
	public int deleteEvtSnsReply(EvtSnsReply evtSnsReply) {
		return getSession1().delete("com.plgrim.ncp.base.deleteEvtSnsReply", evtSnsReply);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}

