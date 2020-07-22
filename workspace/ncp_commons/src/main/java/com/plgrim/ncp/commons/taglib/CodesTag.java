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

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.commons.util.CodeUtil.Code;
import com.plgrim.ncp.framework.commons.LocaleService;
import com.plgrim.ncp.framework.commons.StringService;

/**
 * JSP Page Option Custom Tag Lib
 * @author Park
 */

public class CodesTag extends RequestContextAwareTag {

    /**
     * UID
     */
    private static final long serialVersionUID = 1;
    private String type;
    private String id;
    private String style;
    private String name;
    private String etc;
    private String var;
    private String prefix;
    private String postfix;
    private boolean includeNotUse;

	private boolean isCalUse;
	private boolean isCdAdd;
    private String orderBy;
    private String depth = "1";
    /**
     * Code Group Key
     */
    private String group;

    /**
     * Default Value
     */
    private String def;
    /**
     * Checkbox Option
     */
    private String chkAll = "N";

    /**
     * filtering
     */
    private String filterAsstnCd1;

    private String filterAsstnCd2;

    private static final String SPACE = "&nbsp;&nbsp;&nbsp;";
    void makeOptions(StringBuilder sb, int dataDepth, int displayDepth, String group, List<Code> list){
        String[] defs = (def==null)?new String[]{}:def.split(",");
        for(Code code : list){
            if((dataDepth < displayDepth) && code.getSub() != null && !code.isLeaf() && code.getSub().size() > 0){
                sb.append("<optgroup label='");
                for(int i = 1 ; i < dataDepth ; i++){
                    sb.append(SPACE);
                }
                if(isCdAdd){
                	sb.append("["+code.getCd()+"] ").append(code.getCdNm()).append("</optgroup>");
                }else{
                	sb.append(code.getCdNm()).append("'></optgroup>");
                }
                
                makeOptions(sb, dataDepth + 1, displayDepth, (group== null?"":(group+':'))+code.getCd(), code.getSub());
            }
            else{

                if (StringService.isNotEmpty(filterAsstnCd1) && !filterAsstnCd1.equals(code.getAsstnCd1())) {
                    continue;
                }

                if (StringService.isNotEmpty(filterAsstnCd2) && !filterAsstnCd2.equals(code.getAsstnCd2())) {
                    continue;
                }

                sb.append("<option value='");
                if(group != null){
                    sb.append(group).append(':');
                }
                sb.append(code.getCd()).append('\'');
                if("Y".equals(chkAll)){
                    sb.append(" selected");
                }
                else{
                    for(String d : defs){
                        if(code.getCd().equals(d)){
                            sb.append(" selected");
                        }
                    }
                }
                sb.append('>');
                for(int i = 1 ; i < dataDepth ; i++){
                    sb.append(SPACE);
                }
                if(isCdAdd){
                	sb.append("["+code.getCd()+"] ").append(code.getCdNm()).append("</option>");
                }else{
                	sb.append(code.getCdNm()).append("</option>");
                }
                
            }
        }

    }
    void makeJsonString(StringBuilder sb, int dataDepth, int displayDepth, List<Code> list){
        for(Code code : list){

            if (dataDepth == 1 && StringService.isNotEmpty(filterAsstnCd1) && !filterAsstnCd1.equals(code.getAsstnCd1())) {
                continue;
            }

            if (dataDepth == 1 && StringService.isNotEmpty(filterAsstnCd2) && !filterAsstnCd2.equals(code.getAsstnCd2())) {
                continue;
            }

            sb.append('{');
            sb.append("'parent':'").append(code.getUpperCd()).append("', ");
            sb.append("'cd':'").append(code.getCd()).append("', ");
            if(isCdAdd){
            	sb.append("'cdNm':'").append("["+code.getCd()+"] ").append(code.getCdNm()).append("', ");
            }else{
            	sb.append("'cdNm':'").append(code.getCdNm()).append("', ");
            }
            
            sb.append("'cdDscr':");
            if(code.getCdDscr() == null){
                sb.append("null, ");
            }
            else{
                sb.append('\'').append(code.getCdDscr()).append("', ");
            }
            sb.append("'asstnCd1':");
            if(code.getAsstnCd1() == null){
                sb.append("null, ");
            }
            else{
                sb.append('\'').append(code.getAsstnCd1()).append("', ");
            }
            sb.append("'asstnCd2':");
            if(code.getAsstnCd2() == null){
                sb.append("null, ");
            }
            else{
                sb.append('\'').append(code.getAsstnCd2()).append("', ");
            }
            sb.append("'sub':");
            sb.append('[');
            if(code.getSub()!=null && code.getSub().size() > 0 && dataDepth < displayDepth){
                makeJsonString(sb, dataDepth+1, displayDepth, code.getSub());
            }
            sb.append(']');
            sb.append('}').append(',');
        }
        sb.deleteCharAt(sb.length()-1);
    }
    void makeJson(StringBuilder sb, List<Code> list){
        for(Code code : list){

            if (StringService.isNotEmpty(filterAsstnCd1) && !filterAsstnCd1.equals(code.getAsstnCd1())) {
                continue;
            }

            if (StringService.isNotEmpty(filterAsstnCd2) && !filterAsstnCd2.equals(code.getAsstnCd2())) {
                continue;
            }

            sb.append('{');
            sb.append("'parent':'").append(code.getUpperCd()).append("', ");
            sb.append("'cd':'").append(code.getCd()).append("', ");
            sb.append("'cdNm':'").append(code.getCdNm()).append("', ");
            sb.append("'cdDscr':");
            if(code.getCdDscr() == null){
                sb.append("null, ");
            }
            else{
                sb.append('\'').append(code.getCdDscr()).append("', ");
            }
            sb.append("'asstnCd1':");
            if(code.getAsstnCd1() == null){
                sb.append("null, ");
            }
            else{
                sb.append('\'').append(code.getAsstnCd1()).append("', ");
            }
            sb.append("'asstnCd2':");
            if(code.getAsstnCd2() == null){
                sb.append("null ");
            }
            else{
                sb.append('\'').append(code.getAsstnCd2()).append("' ");
            }
            sb.append("'sub':");
            if(code.getSub()!=null){

            }
            else{
                sb.append("[]");
            }
            sb.append('}').append(',');
        }
        sb.deleteCharAt(sb.length()-1);
    }
    void makeSuggestString(StringBuilder sb, int dataDepth, int displayDepth, String groupCode, String groupName, List<Code> list){
        if(dataDepth >= displayDepth){
            return;
        }
        for(Code code : list){
            if(code.isLeaf() || code.getSub() == null || code.getSub().size() == 0){
                sb.append('"');
                if(groupName != null){
                    sb.append(groupName).append(" > ");
                }
                sb.append(code.getCdNm());
                sb.append('\t');
                if(groupName != null){
                    sb.append(group).append(':');
                }
                sb.append(code.getCd());
                sb.append('"');
                sb.append(',');
            }
            else{
                //				makeSuggestString(sb)
            }
        }
    }
    @Override
    public int doStartTagInternal() throws Exception{
        StringBuilder sb = new StringBuilder();
        String lang = LocaleService.getCurrentLang((HttpServletRequest)pageContext.getRequest()).toString();
        if(type == null){
            if(var != null && var.trim().length() > 0){
                pageContext.setAttribute(var, CodeUtil.getCodes(group, lang, includeNotUse,isCalUse, orderBy, filterAsstnCd1, filterAsstnCd2));
            }
        }
        else{
            if("option".equals(type)){
                makeOptions(sb, 1, Integer.parseInt(depth), null, CodeUtil.getCodes(group, lang, includeNotUse,isCalUse, orderBy));
            }
            else if("json".equals(type)){
                sb.append("[");
                makeJsonString(sb, 1, Integer.parseInt(depth), CodeUtil.getCodes(group, lang, includeNotUse,isCalUse, orderBy));
                //				sb.deleteCharAt(sb.length()-1);
                sb.append("]");
            }
            else if("radio".equals(type) || "checkbox".equals(type)){
                if(id == null){
                    id = group.toLowerCase();
                }
                String[] defs = (def==null)?new String[]{}:def.split(",");
                for(Code code : CodeUtil.getCodes(group, lang, includeNotUse,isCalUse, orderBy)){

                    if (StringService.isNotEmpty(filterAsstnCd1) && !filterAsstnCd1.equals(code.getAsstnCd1())) {
                        continue;
                    }

                    if (StringService.isNotEmpty(filterAsstnCd2) && !filterAsstnCd2.equals(code.getAsstnCd2())) {
                        continue;
                    }

                    if(prefix != null){
                        sb.append(prefix);
                    }
                    sb.append("<input type='").append(type).append('\'');
                    sb.append(" value='").append(code.getCd()).append('\'');
                    makeAttrs(sb, code.getCd());
                    if("Y".equals(chkAll)){
                        sb.append(" checked='checked'");
                    }
                    else{
                        for(String d : defs){
                            if(code.getCd().equals(d)){
                                sb.append(" checked='checked'");
                            }
                        }
                    }

                    sb.append('>');
                    sb.append("<label for='").append(id+code.getCd()).append("'>");
                    sb.append(code.getCdNm()).append("</label>");
                    if(postfix != null){
                        sb.append(postfix);
                    }
                }
            }
            else if("grid".equals(type)){

                boolean beginObject = true;

                for(Code code : CodeUtil.getCodes(group, lang, includeNotUse,isCalUse, orderBy)){
                    if (StringService.isNotEmpty(filterAsstnCd1) && !filterAsstnCd1.equals(code.getAsstnCd1())) {
                        continue;
                    }

                    if (StringService.isNotEmpty(filterAsstnCd2) && !filterAsstnCd2.equals(code.getAsstnCd2())) {
                        continue;
                    }                	
                    if(beginObject) {
                        sb.append("{\'");
                        beginObject = false;
                    }else {
                        sb.append(",{\'");
                    }

                    sb.append(code.getCdNm()).append("\':\'");
                    sb.append(code.getCd()).append("\'}");
                }
            }
            else{
                return EVAL_PAGE;
            }
            if(var != null && var.trim().length() > 0){
                pageContext.setAttribute(var, sb.toString());
            }
            else{
                pageContext.getOut().println(sb.toString());
            }
        }

        return EVAL_PAGE;
    }
    void makeAttrs(StringBuilder sb, String postId){
        makeAttr(sb, "id", id+postId);
        makeAttr(sb, "style", style);
        makeAttr(sb, "name", name);
        if(etc != null){
            sb.append(' ').append(etc);
        }
    }
    void makeAttr(StringBuilder sb, String key, String value){
        if(key == null || value == null){
            return;
        }
        sb.append(' ').append(key).append("='").append(value).append('\'');
    }
    /**
     * @param group the optionDataKey to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * @param defaultValue the defaultValue to set
     */
    public void setDef(String defaultValue) {
        this.def = defaultValue;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public void setVar(String var){
        this.var = var;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }
    public void setDepth(String depth) {
        this.depth = depth;
    }
    public void setIncludeNotUse(boolean includeNotUse) {
        this.includeNotUse = includeNotUse;
    }

	public void setIsCalUse(boolean isCalUse) {
		this.isCalUse = isCalUse;
	}    
	public void setIsCdAdd(boolean isCdAdd) {
		this.isCdAdd = isCdAdd;
	}  	
    public void setFilterAsstnCd1(String filterAsstnCd1) {
        this.filterAsstnCd1 = filterAsstnCd1;
    }
    public void setFilterAsstnCd2(String filterAsstnCd2) {
        this.filterAsstnCd2 = filterAsstnCd2;
    }
    public String getOrderBy() {
        return orderBy;
    }
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
