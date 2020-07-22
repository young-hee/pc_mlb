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
package com.plgrim.ncp.biz.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.repository.OrderOthersMemberRepository;
import com.plgrim.ncp.biz.order.result.OrderBoResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

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
 * @since 2015. 5. 29
 */
@Service
public class OrderOthersMemberService extends AbstractService {

	@Autowired
	OrderOthersMemberRepository orderOthersMemberRepository;
	
	/**
	 * 회원 총주문건수/총주문금액
	 * @param systemPK
	 * @param orderDTO
	 * @return
	 */
	public OrderBoResult selectOrderCountAndAmtForMember(SystemPK systemPK, OrderBoDTO orderDTO) {
    	orderDTO.setLang(systemPK.getLang());
    	
		return orderOthersMemberRepository.selectOrderCountAndAmtForMember(orderDTO);
	}

	/**
	 * 회원 주문 목록 조회.
	 *
	 * @param systemPK [설명]
	 * @param orderDTO [설명]
	 * @param pageParam [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 4
	 */
    public Page<OrderBoResult> selectOrderListForMember(SystemPK systemPK, OrderBoDTO orderDTO, PageParam pageParam) throws Exception {
    	// 페이지 인덱스 셋팅
    	orderDTO.setLang(systemPK.getLang());
    	RepositoryHelper.makePageEntityIndex(orderDTO, pageParam);
    	
    	// step 2. 목록 건수 조회
    	long listCount = orderOthersMemberRepository.selectOrderListCountForMember(orderDTO);
    	
    	// 목록 조회
		List<OrderBoResult> lists = new ArrayList<OrderBoResult>();
		if(listCount > 0){
			lists = orderOthersMemberRepository.selectOrderListForMember(orderDTO);
		}
    	
    	return new PageImpl<OrderBoResult>(lists, pageParam.getPageable(), listCount);
	}
    
	/**
	 * 회원 주문 엑셀 조회.
	 *
	 * @param systemPK [설명]
	 * @param orderDTO [설명]
	 * @return List [설명]
	 * @since 2015. 5. 15
	 */
    public List<Map<String, Object>> selectOrderExcelForMember(SystemPK systemPK, OrderBoDTO orderDTO) {
    	orderDTO.setLang(systemPK.getLang());
    	
		return orderOthersMemberRepository.selectOrderExcelForMember(orderDTO);
	}

}
