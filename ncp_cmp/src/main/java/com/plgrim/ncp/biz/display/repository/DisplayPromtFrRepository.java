/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shsunhee.kim
 * @since       2015. 11. 25       
 */
package com.plgrim.ncp.biz.display.repository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.repository.dsp.DspCnrRepository;
import com.plgrim.ncp.biz.display.data.DspPromtScFrDTO;
import com.plgrim.ncp.biz.display.result.DspPromtFrResult;


/**
 * 기획전 Repository.
 *
 * @author shsunhee.kim
 * @since 2015. 3. 13
 */
@Repository
@Slf4j
public class DisplayPromtFrRepository extends DspCnrRepository {

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
	 * 전시브랜드카테고리 전체 브랜드 목록 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 11. 11
	 */
	public DspPromtFrResult selectPromtGodList(DspPromtScFrDTO dto) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectPromtGodList", dto);
	}

	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	
}
