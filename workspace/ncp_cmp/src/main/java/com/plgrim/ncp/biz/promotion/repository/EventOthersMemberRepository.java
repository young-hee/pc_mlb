/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 5. 21       
 */
package com.plgrim.ncp.biz.promotion.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;
import com.plgrim.ncp.biz.promotion.result.EventOthersMemberResult;

/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author sy59.gim
 * @since 2015. 5. 21
 */
@Repository
public class EventOthersMemberRepository extends AbstractRepository {

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
     * 회원 이벤트 응모현황 건수 조회.
     *
     * @param ea [설명]
     * @return Long [설명]
     * @since 2015. 5. 15
     */
    public long selectEventApplyListCountForMember(EvtApplcn ea) {
		return getSession1().selectOne("com.plgrim.ncp.biz.event.others.member.selectEventApplyListCountForMember", ea);
	}
	
	/**
	 * 회원 이벤트 응모현황 목록 조회.
	 *
	 * @param ea [설명]
	 * @return List [설명]
	 * @since 2015. 5. 15
	 */
    public List<EventOthersMemberResult> selectEventApplyListForMember(EvtApplcn ea) {
		return getSession1().selectList("com.plgrim.ncp.biz.event.others.member.selectEventApplyListForMember", ea);
	}
    
	/**
	 * 회원 이벤트 응모현황 엑셀 조회.
	 *
	 * @param ea [설명]
	 * @return List [설명]
	 * @since 2015. 5. 15
	 */
    public List<Map<String, Object>> selectEventApplyExcelForMember(EvtApplcn ea) {
		return getSession1().selectList("com.plgrim.ncp.biz.event.others.member.selectEventApplyExcelForMember", ea);
	}

    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}
