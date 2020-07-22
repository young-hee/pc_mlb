package com.plgrim.ncp.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import lombok.extern.slf4j.Slf4j;

/**
 * system 처리 관련 controller
 * 
 * @author Chulhui Park <charles@plgrim.com>
 *
 */
@Controller
@RequestMapping("/common")
@Slf4j
public class CommonController {

    /**
     * change locale : user's browser cookie
     * REMIND 현재로선 쓰일 일이 없음
     *
     * @param user injected CurrentUser
     * @param referer injected Referer from HttpHeader
     * @param strLocale injected _locale from Request Param
     * @return redirect to referer url
     */
    @RequestMapping(method=RequestMethod.GET, value="/changeLocale")
    public RedirectView changeLocale(
    		@RequestHeader(value="Referer", required=false, defaultValue="/") String referer,
    		@RequestParam("_locale") String strLocale) throws Exception{
    	
        if (log.isDebugEnabled()) {
            log.debug("referer = {}, locale = {}", referer, strLocale);
        }
        
        // TODO cookie setting 필요

        return new RedirectView(referer);
        
    }
}
