package com.plgrim.ncp.web.pc.mlb.inflow;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.plgrim.ncp.base.abstracts.AbstractController;
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.cmp.common.fo.CommonInflowFOComponent;
import com.plgrim.ncp.cmp.product.fo.GoodsFOComponent;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;

import lombok.extern.slf4j.Slf4j;

/** Copyright (c) 2018 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 *
 * Description	:	유입 채널 컨트롤러
 *
 * @Author 	:	neps
 * @Date   	:	2018. 7. 30.
 * @Version	:	
 *
 */
@Slf4j
@Controller
@RequestMapping("/inflow")
public class InflowController extends AbstractController{
    
    @Value("${ncp_web_pc_mlb.main.url}")
    String mainURL;

    @Value("${ncp.url.pc_MLB.root.secure}")
    String pcURL;    

    @Value("${ncp.url.mb_MLB.root.secure}")
    String mobileURL;    
    
    @Autowired
    CommonInflowFOComponent commonInflowFOComponent;
    
    @Autowired
    GoodsFOComponent goodsFOComponent; 
    
    
    @RequestMapping(value = {"/enginepage/{serviceId}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String inflowEnginePage(
	    		HttpServletRequest request
	    		, HttpServletResponse response
	    		, @PathVariable String serviceId
	    		, Model model
	    		, @RequestParam(value="godNo", required=false) String godNo
	    		, @RequestParam(value="utm_source", required=false) String utmSource
	    		, @RequestParam(value="utm_medium", required=false) String utmMedium
	    		, @RequestParam(value="ref", required=false) String ref
    		) throws Exception {    	
        if (log.isDebugEnabled()) {
            log.debug("welcome inflowEnginePage page:{}", request.getRequestURI());
        }
    	String l_NaPm = ServletRequestUtils.getStringParameter(request, "NaPm", ""); 
    		
        /**
         * 유입 번호 설정
         */
        try {			
        	SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request); // System PK
        	
        	SysInflow inflowDTO = new SysInflow();
        	inflowDTO.setMallId(systemPK.getMall());
        	inflowDTO.setAdvtAffComId(serviceId); 
        	SysInflow sysInflow = commonInflowFOComponent.selectSysInflow(inflowDTO);        
        	
        	HttpSession session = request.getSession();
        	session.setAttribute(String.valueOf(SessionEnum.INFLOW_SN), sysInflow.getInflowSn().toString());
        	session.setAttribute(String.valueOf(SessionEnum.ADVT_AFF_COM_ID), serviceId);
		} catch (Exception e) {
			log.debug("inflow session setting error : {}", e.getMessage());
		}
        
        StringBuffer redirectURL = new StringBuffer();
        boolean hasParam = false;
        redirectURL.append("redirect:");

        SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);               

        if(StringService.isNotEmpty(godNo)) {        	
            if ("PC".equals(systemPK.getDevice())) {
            	redirectURL.append(pcURL);
            } else {
            	redirectURL.append(mobileURL);
            }        	        	
        	String nextUrl = goodsFOComponent.getViewGoodsUrl(godNo, true);        	
        	redirectURL.append(nextUrl);
        }else {
        	if(StringService.isNotEmpty(ref)) {
        		redirectURL.append(URLDecoder.decode(ref, "UTF-8"));
        	}else{
        		redirectURL.append(mainURL);
        	}
        }

        if(!StringService.isEmpty(l_NaPm)){
        	hasParam = true;
        	redirectURL.append("?")
        	.append("NaPm=")
        	.append(URLEncoder.encode(l_NaPm,"UTF-8"));
        }
        
        if(!StringService.isEmpty(utmSource)){
        	if(!hasParam) {
        		hasParam = true;
        		redirectURL.append("?");
        	}else{
        		redirectURL.append("&");
        	}
        	
        	redirectURL.append("utm_source=")
        	.append(utmSource);
        }
        
        if(!StringService.isEmpty(utmMedium)){
        	if(!hasParam) {
        		hasParam = true;
        		redirectURL.append("?");
        	}else{
        		redirectURL.append("&");
        	}
        	
        	redirectURL.append("utm_medium=")
        	.append(utmMedium);
        }
        
        return redirectURL.toString();
    }
	
}
