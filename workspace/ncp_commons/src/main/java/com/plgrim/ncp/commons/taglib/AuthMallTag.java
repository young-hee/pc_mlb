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

import com.plgrim.ncp.commons.util.BOSecurityUtil;
import lombok.Data;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import java.util.List;
import java.util.Map;

/**
 * JSP Page Option Custom Tag Lib
 *
 * @author Park
 */

@Data
public class AuthMallTag extends RequestContextAwareTag {

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

    /**
     * Default Value
     */
    private String def;
    /**
     * Checkbox Option
     */
    private String chkAll = "N";

    void makeOptions(StringBuilder sb, List<Map<String, String>> list) {
        String[] defs = (def == null) ? new String[]{} : def.split(",");
        for (Map<String, String> map : list) {
            sb.append("<option value='");
            String mallId = map.get("mallId");
            sb.append(mallId).append('\'');
            if ("Y".equals(chkAll)) {
                sb.append(" selected");
            } else {
                for (String d : defs) {
                    if (mallId.equals(d)) {
                        sb.append(" selected");
                    }
                }
            }
            sb.append('>');

            sb.append(map.get("mallNm")).append("</option>");
        }
    }

    @Override
    public int doStartTagInternal() throws Exception {
        List<Map<String, String>> list = BOSecurityUtil.getAuthMallList();

        StringBuilder sb = new StringBuilder();
        if (type == null) {
            if (var != null && var.trim().length() > 0) {
                pageContext.setAttribute(var, list);
            }
        } else {
            if ("option".equals(type)) {
                makeOptions(sb, list);
            } else if ("radio".equals(type) || "checkbox".equals(type)) {
                String[] defs = (def == null) ? new String[]{} : def.split(",");
                for (Map<String, String> map : list) {
                    if (prefix != null) {
                        sb.append(prefix);
                    }

                    String mallId = map.get("mallId");
                    sb.append("<input type='").append(type).append('\'');
                    sb.append(" value='").append(mallId).append('\'');
                    makeAttrs(sb, mallId);

                    if ("Y".equals(chkAll)) {
                        sb.append(" checked='checked'");
                    } else {
                        for (String d : defs) {
                            if (mallId.equals(d)) {
                                sb.append(" checked='checked'");
                            }
                        }
                    }

                    sb.append('>');
                    sb.append("<label for='").append(id + mallId).append("'>");
                    sb.append(map.get("mallNm")).append("</label>");
                    if (postfix != null) {
                        sb.append(postfix);
                    }
                }
            } else {
                return EVAL_PAGE;
            }

            if (var != null && var.trim().length() > 0) {
                pageContext.setAttribute(var, sb.toString());
            } else {
                pageContext.getOut().println(sb.toString());
            }
        }

        return EVAL_PAGE;
    }

    void makeAttrs(StringBuilder sb, String postId) {
        makeAttr(sb, "id", id + postId);
        makeAttr(sb, "style", style);
        makeAttr(sb, "name", name);
        if (etc != null) {
            sb.append(' ').append(etc);
        }
    }

    void makeAttr(StringBuilder sb, String key, String value) {
        if (key == null || value == null) {
            return;
        }
        sb.append(' ').append(key).append("='").append(value).append('\'');
    }
}
