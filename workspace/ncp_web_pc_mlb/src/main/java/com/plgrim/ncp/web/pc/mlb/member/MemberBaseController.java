/* Copyright (c) 2018 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      id
 * @since       2018.04.30   
 */
package com.plgrim.ncp.web.pc.mlb.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.plgrim.ncp.base.abstracts.AbstractController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/base")
public class MemberBaseController extends AbstractController {

	/**
	 * Home location set 생성
	 * 	Home
	 * 
	 * @return
	 */
	protected List<Map<String, String>> makeHomeLocationSet() {
		
        Map<String, String> location = null;
        
        List<Map<String, String>> locationSet = new ArrayList<Map<String, String>>();
        
        location = new HashMap<String, String>();
        location.put("url", "/main/mall/view");
        location.put("msgKey", "common.location.home");
        locationSet.add(location);

        return locationSet;
	}
	
	/**
	 * 로그아웃 후 gate 페이지
	 */
	@RequestMapping(value="/logoutGate" ,method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpServletRequest request) throws Exception{
		
		return "redirect:/";
	}
	
}
