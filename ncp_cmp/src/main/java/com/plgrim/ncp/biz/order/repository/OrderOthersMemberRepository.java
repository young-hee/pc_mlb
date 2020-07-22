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
 * @since       2015. 5. 29       
 */
package com.plgrim.ncp.biz.order.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.result.OrderBoResult;

/**
 * [회원 주문 조회]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author sy59.gim
 * @since 2015. 5. 29
 */
@Repository
public class OrderOthersMemberRepository extends AbstractRepository {
	
	/**
	 * 회원 총주문건수/총주문금액
	 * @param orderDTO
	 * @return
	 */
	public OrderBoResult selectOrderCountAndAmtForMember(OrderBoDTO orderDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.other.member.selectOrderCountAndAmtForMember", orderDTO);
	}

	/**
	 * 회원 주문 건수 조회.
	 *
	 * @param orderDTO [설명]
	 * @return Long [설명]
	 * @since 2015. 5. 15
	 */
    public long selectOrderListCountForMember(OrderBoDTO orderDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.order.other.member.selectOrderListCountForMember", orderDTO);
	}
	
	/**
	 * 회원 주문 목록 조회.
	 *
	 * @param orderDTO [설명]
	 * @return List [설명]
	 * @since 2015. 5. 15
	 */
    public List<OrderBoResult> selectOrderListForMember(OrderBoDTO orderDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.other.member.selectOrderListForMember", orderDTO);
	}
    
	/**
	 * 회원 주문 엑셀 조회.
	 *
	 * @param orderDTO [설명]
	 * @return List [설명]
	 * @since 2015. 5. 15
	 */
    public List<Map<String, Object>> selectOrderExcelForMember(OrderBoDTO orderDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.order.other.member.selectOrderExcelForMember", orderDTO);
	}
}
