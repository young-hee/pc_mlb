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
 * @since       2015. 4. 17       
 */
package com.plgrim.ncp.biz.vendor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.biz.vendor.data.VendorSearchDTO;
import com.plgrim.ncp.biz.vendor.repository.VendorBrndHistRepository;
import com.plgrim.ncp.biz.vendor.result.ComBrndHistResult;
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
 * @since 2015. 3. 24
 */
@Service
public class ComBrndHistService  extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 업체 Repository
	 */
	@Autowired
	private VendorBrndHistRepository vendorBrndHistRepository;

	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;	
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
	 * 수수료율 목록 건수 조회.
	 *
	 * @param searchDTO [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public long selectBrndHistListCount(VendorSearchDTO searchDTO) throws Exception {
		return vendorBrndHistRepository.selectBrndHistListCount(searchDTO);
	}
	
	/**
	 * 수수료율 목록 조회.
	 *
	 * @param searchDTO [설명]
	 * @return Page [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public Page<ComBrndHistResult> selectBrndHistList(VendorSearchDTO searchDTO) throws Exception {
		
		RepositoryHelper.makePageEntityIndex(searchDTO, searchDTO.getPageParam());  // 페이지 설정

		// 목록 건수 조회
		long totalRow = vendorBrndHistRepository.selectBrndHistListCount(searchDTO);
		
		// 목록 조회
		List<ComBrndHistResult> results = new ArrayList<ComBrndHistResult>();
		if(totalRow > 0){
			results = vendorBrndHistRepository.selectBrndHistList(searchDTO);
		}
		
		return new PageImpl<ComBrndHistResult>(results, searchDTO.getPageParam().getPageable(), totalRow);
	}
	
    /**
     * 수수료율 목록 조회 엑셀.
     * 
     * <p/>
     * 
     * [사용 방법 설명].
     *
     * @param searchDTO [설명]
     * @return List [설명]
     * @throws Exception the exception
     * @since 2015. 5. 8
     */
    public List<Map<String, Object>> selectBrndHistListExcel(VendorSearchDTO searchDTO) throws Exception {
		return vendorBrndHistRepository.selectBrndHistListExcel(searchDTO);
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
