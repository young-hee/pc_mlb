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
package com.plgrim.ncp.biz.display.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.biz.display.data.DspPromtScFrDTO;
import com.plgrim.ncp.biz.display.repository.DisplayPromtFrRepository;
import com.plgrim.ncp.biz.display.result.DspPromtFrResult;

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
 * @author shsunhee.kim
 * @since 2015. 5. 28
 */
@Service
public class DisplayPromtFrService extends AbstractService{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DisplayPromtFrRepository displayPromtFrRepository;
	

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
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspPromtScFrDTO [설명]
	 * @return List [설명]
	 * @since 2015. 5. 28
	 */
	public DspPromtFrResult selectPromtGodList(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {	    
		DspPromtFrResult lists = displayPromtFrRepository.selectPromtGodList(dspPromtScFrDTO);	
		
		return lists;
    }
	
	
}
