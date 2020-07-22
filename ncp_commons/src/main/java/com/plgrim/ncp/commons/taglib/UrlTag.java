/* Copyright (c) 2013 plgrim, Inc.
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
package com.plgrim.ncp.commons.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**
 * JSP Page Option Custom Tag Lib
 * @author Park
 */

abstract class UrlTag extends RequestContextAwareTag {
	/**
	 * UID
	 */
    private static final long serialVersionUID = 6909370419303809488L;
    private String var;
    String path;
    String system;
    String device;
    
    String opt;
    String width;
    String height;
    String imgQ;
	@Override
	protected int doStartTagInternal() throws Exception {
		int count = pageContext.getAttribute("urlTagCount") == null ? 0 : (int) pageContext.getAttribute("urlTagCount");
		count ++;
		pageContext.setAttribute("urlTagCount", count);
		String root = null;
		if(root == null){
			root = getDefaultRootURI();
		}
		if(root == null){
			root = "";
		}
		if(path == null){
			path = "";
		}

		if(path.length() > 0){
			if(!path.startsWith("/")){
				path = '/'+path;
			}
		}
		if(opt != null && !opt.trim().equals("")){
			if(imgQ == null || imgQ.equals("")){
				imgQ = "Y";
			}
			
			if(imgQ.toUpperCase().equals("Y")){
				if(width == null){
					width = "";
				}
				if(height == null){
					height = "";
				}
				if(width.equals("") && height.equals("")){
					throw new JspException("urlTagCount : imgOnDemand [width or height] is required");
				}
			
				if(var != null && var.length() > 0){
					pageContext.setAttribute(var, root+"/cmd/"+opt.toUpperCase().trim()+"_"+width.trim()+"x"+height.trim()+"/src/"+root + path);
				}
				else{
					try {
			            pageContext.getOut().print(root+"/cmd/"+opt.toUpperCase().trim()+"_"+width.trim()+"x"+height.trim()+"/src/"+root + path);
		            }
		            catch (IOException e) {
			            throw new JspException("urlTagCount:"+count+" message: "+e.getMessage(), e);
		            }
				}
			}else{
				if(var != null && var.length() > 0){
					pageContext.setAttribute(var, root + path);
				}
				else{
					try {
			            pageContext.getOut().print(root + path);
		            }
		            catch (IOException e) {
			            throw new JspException("urlTagCount:"+count+" message: "+e.getMessage(), e);
		            }
				}
			}
		}else{
			if(var != null && var.length() > 0){
				pageContext.setAttribute(var, root + path);
			}
			else{
				try {
		            pageContext.getOut().print(root + path);
	            }
	            catch (IOException e) {
		            throw new JspException("urlTagCount:"+count+" message: "+e.getMessage(), e);
	            }
			}
		}

		return SKIP_BODY;
	}
	abstract String getDefaultRootURI();
	public void setPath(String path) {
		this.path = path;
	}

	public void setVar(String var) {
		this.var = var;
	}
	
	public void setSystem(String system) {
		if(system == null){
			return;
		}
		this.system = system.toUpperCase();
	}
	public void setDevice(String device){
		if(device == null){
			return;
		}
		this.device = device.toUpperCase();
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setHeight(String height) {
		this.height = height;
	}
	
	public void setImgQ(String imgQ) {
		this.imgQ = imgQ;
	}
}
