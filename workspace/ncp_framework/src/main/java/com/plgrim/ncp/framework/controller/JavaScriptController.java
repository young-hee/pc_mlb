package com.plgrim.ncp.framework.controller;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.messages.NCPReloadableMessageSource;

import lombok.extern.slf4j.Slf4j;

/**
 * javascript에서 사용하는 global message를 제공하기 위한 controller
 * 
 * @author Chulhui Park <charles@plgrim.com>
 *
 */
@Controller
@Lazy
@RequestMapping("/javascript")
@Slf4j
public class JavaScriptController {
	private static final String SUFFIX_JS = ".js";
	
    private NCPReloadableMessageSource messageSource;
	
    @PostConstruct
    public void init() {
    	this.messageSource = ContextService.getBean(NCPReloadableMessageSource.class);
    	log.info("setup messageSource[{}] in JavaScriptController", this.messageSource);
    }
    
    /**
     * messageFile에 해당하는 keyPrefix를 가지는 메세지를 response에 바로 출력
     * 
     * @param webRequest native web request
     * @return return forward path
     */
    @RequestMapping(method = RequestMethod.GET, value = { "/message/{messageFile:.+}" })
    public void messageCommon(HttpServletRequest request, HttpServletResponse response, @PathVariable("messageFile") String messageFile) throws Exception {
    	if (!messageFile.endsWith(SUFFIX_JS)) {
    		log.warn("invalid messageFile suffix[{}]: only support .js", messageFile);
    		response.setStatus(HttpStatus.SC_NOT_FOUND);
    		return;
    	}
    	
    	String filename = messageFile.substring(0, messageFile.length()-SUFFIX_JS.length());
    	String locale = "";
    	String _filename = filename;
    	int idx = filename.lastIndexOf('_');
    	if (idx > 0) {
    		int length = filename.length();
    		_filename = filename.substring(0, idx);
    		locale = filename.substring(idx+1, length);
    	}
    	
    	String messageKeyPrefix = _filename+".js";
    	Map<String, String> messages = messageSource.getAllMessages(messageKeyPrefix, request);    	
    	if (log.isDebugEnabled()) {
        	log.debug("filename[{}], locale[{}], messageKeyPrefix[{}]", _filename, locale, messageKeyPrefix);
        }
        
    	response.setStatus(HttpStatus.SC_OK);
    	response.setContentType("text/javascript;charset=UTF-8");
    	
    	// local profile이 아닌 경우 1주일간 cache를 하도록 한다.
    	if (!ContextService.isLocalProfile()) {
        	Calendar inOneMonth = Calendar.getInstance();
        	inOneMonth.add(Calendar.DATE, 7);
        	response.setDateHeader("Expires", inOneMonth.getTimeInMillis());    		
    	}
    	
    	try {
    		PrintWriter out = response.getWriter();
    		
    		for(Map.Entry<String, String> entry : messages.entrySet()) {
        		String key = entry.getKey();
        		String value = entry.getValue();
        		
        		out.println("MESSAGES['"+key+"'] = \""+value+"\"");
        	}
    	} finally {
    		response.flushBuffer();
    	}
    }
}
