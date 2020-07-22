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
 * @since       2015. 6. 17       
 */
package com.plgrim.ncp.biz.promotion.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;
import com.plgrim.ncp.biz.promotion.repository.EventOthersMemberRepository;
import com.plgrim.ncp.biz.promotion.result.EventOthersMemberResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

/**
 * [회원 이벤트 응모내역 조회]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [회원 이벤트 응모내역 조회]
 *   <li> [회원 이벤트 응모내역 엑셀 조회]
 * </ul>.
 *
 * @author sy59.gim
 * @since 2015. 5. 21
 */
@Service
public class EventOthersMemberService extends AbstractService {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    /** 회원 이벤트 응모내역 repository. */
    @Autowired
    private EventOthersMemberRepository promotionOthersMemberRepository;

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
	 * 회원 이벤트 응모내역 조회.
	 *
	 * @param mbrNo [설명]
	 * @param pageParam [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 4
	 */
    public Page<EventOthersMemberResult> selectEventApplyListForMember(SystemPK systemPK, EvtApplcn ea, PageParam pageParam) throws Exception {
    	// 페이지 인덱스 셋팅
    	ea.setLang(systemPK.getLang());
    	RepositoryHelper.makePageEntityIndex(ea, pageParam);
    	
    	// step 2. 목록 건수 조회
    	long listCount = promotionOthersMemberRepository.selectEventApplyListCountForMember(ea);
    	
    	// 목록 조회
		List<EventOthersMemberResult> lists = new ArrayList<EventOthersMemberResult>();
		if(listCount > 0){
			lists = promotionOthersMemberRepository.selectEventApplyListForMember(ea);
		}
    	
    	return new PageImpl<EventOthersMemberResult>(lists, pageParam.getPageable(), listCount);
	}
    
	/**
	 * 회원 이벤트 응모내역 엑셀 조회.
	 *
	 * @param mbrNo [설명]
	 * @return List [설명]
	 * @since 2015. 5. 15
	 */
    public List<Map<String, Object>> selectEventApplyExcelForMember(SystemPK systemPK, EvtApplcn ea) {
    	ea.setLang(systemPK.getLang());
    	
		return promotionOthersMemberRepository.selectEventApplyExcelForMember(ea);
	}

    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}
