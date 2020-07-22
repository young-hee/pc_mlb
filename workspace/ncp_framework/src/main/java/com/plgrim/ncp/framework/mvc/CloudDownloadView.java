/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      Chulhui Park
 * @since       2018.05.03       
 */
package com.plgrim.ncp.framework.mvc;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.view.AbstractView;
import org.apache.commons.io.IOUtils;

import com.plgrim.ncp.framework.cloud.CloudFileMetadata;
import com.plgrim.ncp.framework.cloud.CloudFileSystem;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CloudDownloadView extends AbstractView {
	@Autowired
	CloudFileSystem cloudFileSystem;
	
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
        
		DownloadObject downloadObject = (DownloadObject)model.get("file");
		String displayFileName = downloadObject.getDisplayFileName();
		String cloudFilePath = downloadObject.getCloudFilePath();
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
 
        try (OutputStream out = response.getOutputStream(); InputStream in = cloudFileSystem.getInputStream(cloudFilePath);) {
        	CloudFileMetadata fileMeta = cloudFileSystem.getFileInformation(cloudFilePath);
    		response.setContentType(fileMeta.getContentType());
    		response.setContentLength((int)fileMeta.getLength());
    		response.setDateHeader(HttpHeaders.LAST_MODIFIED, fileMeta.getLastModified());
    		
        	IOUtils.copy(in, out);
        	out.flush();
        } catch (Exception e) {
        	log.error("Error occured during executing downloadObject[{}]", downloadObject);
        }
    }
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
