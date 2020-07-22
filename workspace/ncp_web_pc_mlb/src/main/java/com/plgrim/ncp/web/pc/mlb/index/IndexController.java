/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 *                       
 */
package com.plgrim.ncp.web.pc.mlb.index;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.plgrim.ncp.base.abstracts.AbstractController;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.cmp.display.fo.MbmDisplaySelectComponent;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("")
public class IndexController extends AbstractController {

    @Value("${ncp_web_pc_mlb.main.url}")
    String pcURL;

    @Value("${ncp.url.mb.root}")
    String mobileURL;

    @Autowired
    SessionRegistry sessionRegistry;
    
    @Autowired
    MbmDisplaySelectComponent mbmDisplaySelectComponent;


    /**
     * favicon 전용 컨트롤러.
     *
     * @return the json
     * @throws Exception the exception
     */

    /**
     * / 루트 URL.
     * 예) http://localhost/
     *
     * @return the json
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/index", "/"}, method = {RequestMethod.GET,RequestMethod.POST})
    public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("welcome index page:{}", request.getRequestURI());
        }

        StringBuffer redirectURL = new StringBuffer();
        redirectURL.append("redirect:");

        /**
         * --------------------------------------
         * PC 와 모바일(PC 보기) 구분 로직 추가 해야함
         * --------------------------------------
         */
        SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
//        if ("PC".equals(systemPK.getDevice())) {
            redirectURL.append(pcURL);
//        } else {
//            redirectURL.append(mobileURL);
//        }

        String quertString = request.getQueryString();
        if (StringService.isNotEmpty(quertString)) {
            redirectURL.append("?");
            redirectURL.append(quertString);
        }

        return redirectURL.toString();

    }

    @RequestMapping(value = {"/tiles"}, method = {RequestMethod.GET,RequestMethod.POST})
    public String tiles() throws Exception {    	
    	return "tiles:test/contents";
    }
    
    @RequestMapping(value = {"/commonCtgList.json"}, produces = { "application/json" })
    public void commonCtgList(DspCtgry dspCtgry, Model model) throws Exception {
    	Object ctgList = mbmDisplaySelectComponent.selectGNBCategotyList(dspCtgry);
    	model.addAttribute("ctgList", ctgList);
    }
    
    /**
     * 메인 페이지.
     *
     * @return the json
     * @throws Exception the exception
     */
    @RequestMapping(value = "/public/main/old")
    public void main(HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        if(log.isInfoEnabled()){
        	log.info("-[begin]----------------------------");
        	log.info(session.getId());
        }

        session.setAttribute("xxx","xxx");
        if(log.isInfoEnabled()){
        	log.info( "session[xxx]{}", (String)session.getAttribute("xxx"));
        }

        session.setAttribute("yyy","yyy");
        if(log.isInfoEnabled()){
        	log.info("session[yyy]{}", (String)session.getAttribute("yyy"));
        	log.info("-[end]----------------------------");
        }

    }

    /**
     * 로그인 페이지.
     *
     * @return the json
     * @throws Exception the exception
     */
    @RequestMapping(value = "/public/login")
    public void login(@RequestParam(required = false) String loginTarget, HttpServletRequest request) throws Exception {
        if(log.isInfoEnabled()){
        	log.info("#loginTarget### {}", loginTarget);
        	log.info("#### {}", request.getRequestURI());
        }

        HttpSession session = request.getSession();
        session.setAttribute("aaaa", "bbbbb");

    }

    /**
     * 팝업 로그인 페이지.
     *
     * @return the json
     * @throws Exception the exception
     */
    @RequestMapping(value = "/public/popLogin")
    public String popLogin(@RequestParam(required = false) String loginTarget, HttpServletRequest request) throws Exception {
        return "popupLogin";
    }

    /**
     * 팝업 성공 페이지.
     *
     * @return the json
     * @throws Exception the exception
     */
    @RequestMapping(value = "/public/popLoginSuccess")
    public String popLoginSuccess(@RequestParam(required = false) String loginTarget, HttpServletRequest request) throws Exception {
        return "popLoginSuccess";
    }

    /**
     * 인증이 없는 테스트 페이지.
     *
     * @return the json
     * @throws Exception the exception
     */
    @RequestMapping(value = "/public/temp", method = RequestMethod.GET)
    public void publicTemp(@RequestParam(required = false) String foo, @RequestParam(required = false) String bar, @RequestParam(required = false) String name, HttpServletRequest request) throws Exception {

        if(log.isInfoEnabled()){
        	log.info("#foo### {}", foo);
        	log.info("#bar### {}", bar);
        	log.info("#name### {}", name);
        	log.info("##############> /public/temp");
        }

        HttpSession session = request.getSession();
        if(log.isInfoEnabled()){
        	log.info("-[begin]----------------------------");
        	log.info(session.getId());
        	log.info( "session[xxx]{}", (String)session.getAttribute("xxx"));
        	log.info("session[yyy]{}", (String)session.getAttribute("yyy"));
        	log.info("-[end]----------------------------");
        }

    }

    /**
     * 인증이 필요한 테스트 페이지.
     *
     * @return the json
     * @throws Exception the exception
     */
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @RequestMapping(value = "/secured/temp", method = RequestMethod.GET)
    public void securedTemp(HttpServletRequest request) throws Exception {
        if(log.isInfoEnabled()){
        	log.info("#### {}", request.getRequestURI());
        }
    }

    /**
     * 세션 정보를 가져 온다.
     *
     * @return the json
     * @throws Exception the exception
     */
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @RequestMapping(value = "/secured/sessionInfo")
    public void sessionInfo(Model model, HttpServletRequest request) throws Exception {
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			
			model.addAttribute("userId", userDetail.getMbr().getMbrId() );
			model.addAttribute("email", userDetail.getMbr().getMbrEmail() );
			
		}
        if(log.isInfoEnabled()){
        	log.info("#### {}", request.getRequestURI());
        }
    }

    @RequestMapping(value = "/public/csrf", method = RequestMethod.GET)
    public void csrfForm(HttpServletRequest request) throws Exception {
        if(log.isInfoEnabled()){
        	log.info("#### {}", request.getRequestURI());
        }
    }

    @RequestMapping(value = "/public/csrf", method = RequestMethod.POST)
    public String csrf(HttpServletRequest request, @RequestParam("id") String id) throws Exception {
        if(log.isInfoEnabled()){
        	log.info("#### {}", request.getRequestURI());
        	log.info("#### {}", id);
        }
        return "redirect:/public/csrf";
    }

    @RequestMapping(value = "/public/csrfAjax", method = RequestMethod.GET)
    public void csrfAjaxForm(HttpServletRequest request) throws Exception {
        if(log.isInfoEnabled()){
        	log.info("#### {}", request.getRequestURI());
        }
    }

    @RequestMapping(value = "/public/csrfAjax", method = RequestMethod.POST,  produces = {"application/json"})
    public void csrfAjax(HttpServletRequest request) throws Exception {
        if(log.isInfoEnabled()){
        	log.info("#### {}", request.getRequestURI());
        }
    }

    @RequestMapping(value = "/public/csrfUpload", method = RequestMethod.GET)
    public void uploadForm(Model model) throws Exception {
    }

    @RequestMapping(value = "/public/csrfUpload", method = RequestMethod.POST)
    public String upload( @RequestParam("file") MultipartFile[]  files ) throws Exception {

        for(MultipartFile file : files) {
            if(log.isInfoEnabled()){
            	log.info("###{}", file.getOriginalFilename());
            }

            java.io.File path = new  java.io.File("c:/upload/a.jpg");

            file.transferTo(path);
            if(log.isInfoEnabled()){
            	log.info("###{}", file.getSize());
            }
        }

        return "redirect:/public/csrfUpload";
    }
}