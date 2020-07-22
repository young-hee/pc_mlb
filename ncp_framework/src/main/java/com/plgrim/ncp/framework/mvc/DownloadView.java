/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 3. 24       
 */
package com.plgrim.ncp.framework.mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;


@Slf4j
public class DownloadView extends AbstractView {

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

	public void DownloadView(){
        super.setContentType("application/octet-stream");
    }

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
    protected void renderMergedOutputModel(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
//		File file = (File)model.get("file");
		
		DownloadObject downloadObject = (DownloadObject)model.get("file");
		if (downloadObject == null) {
			return;
		}

		String displayFileName = downloadObject.getDisplayFileName();
		File file = (File)downloadObject.getDownloadFile();
  
        response.setContentType(getContentType());
        response.setContentLength((int)file.length());
 
        String userAgent = request.getHeader("User-Agent");
 
        if (userAgent.contains("Trident")||(userAgent.indexOf("MSIE")>-1)) {
            log.info("user-agent:IE under 10 : {}", userAgent);
            displayFileName=URLEncoder.encode(displayFileName, "UTF-8").replaceAll("\\+", "%20");
        } else if(userAgent.contains("Chrome")) {
        	log.info("user-agent:Chrome : {}", userAgent);
        	displayFileName = new String(displayFileName.getBytes("UTF-8"), "ISO-8859-1");
        } else if(userAgent.contains("Opera")) {
        	log.info("user-agent:Opera : {}", userAgent);
        	displayFileName = new String(displayFileName.getBytes("UTF-8"), "ISO-8859-1");
        } else if(userAgent.contains("Firefox")) {
        	log.info("user-agent:Firefox : {}", userAgent);
        	displayFileName = new String(displayFileName.getBytes("UTF-8"), "ISO-8859-1");
        }
         
        response.setHeader("Content-Disposition", "attachment; filename=\"" + displayFileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
 
        OutputStream out = response.getOutputStream();
        FileInputStream fis = null;
  
        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } catch(Exception e){
            log.error("",e);
        }finally{
            if(fis != null){
                try{
                    fis.close();
                }catch(Exception e){}
            }
        }
        out.flush();
    }
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}