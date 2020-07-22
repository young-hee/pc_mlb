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

import com.plgrim.ncp.framework.commons.StringService;
import org.springframework.web.servlet.tags.RequestContextAwareTag;


/**
 * JSP Page Option Custom Tag Lib
 * @author Park
 */

public class Utag2Tag extends RequestContextAwareTag {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -485207675401784141L;
	
	/** ref_tpl */
	private String tmplt;
	
	/** ref_cnr. */
	private String cnr;
	
	/** ref_sect. */
	private String sect;
	
	/** ref_cat. */
	private String cate;
	
	/** ref_etc. */
	private String etc;
	
	/** ref_brn. */
	private String brn;
	
	/** ref_br. */
	private String br;
	
	/** ref_type. */
	private String type;
	
	/** set. */
	private String set;
	
	/** dpos */
	private String contt;
	
	
	/** The var. */
	private String var;
    
	private String abTestSn;
	private String abRevSn;
	private String abRevTrnSn;

	public void setCate(String cate) {
		this.cate = cate;
	}
	public void setTmplt(String tmplt) {
		this.tmplt = tmplt;
	}
	public void setCnr(String cnr) {
		this.cnr = cnr;
	}
	public void setSet(String set) {
		this.set = set;
	}
	public void setContt(String contt) {
		this.contt = contt;
	}
	public void setSect(String sect) {
		this.sect = sect;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public void setBrn(String brn) {
		this.brn = brn;
	}
	public void setBr(String br) {
		this.br = br;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setVar(String var){
		this.var = var;
	}
    public void setAbTestSn(String abTestSn) {
        this.abTestSn = abTestSn;
    }
    public void setAbRevSn(String abRevSn) {
        this.abRevSn = abRevSn;
    }
    public void setAbRevTrnSn(String abRevTrnSn) {
        this.abRevTrnSn = abRevTrnSn;
    }

	@Override
    protected int doStartTagInternal() throws Exception {
		StringBuilder sb = new StringBuilder();
		String spr = "$";
		
		sb.append("utag=");
		if(StringService.isNotEmpty(tmplt)) {
			sb.append("ref_tpl:");
			sb.append(tmplt);
			sb.append(spr);
		}
		
		if(StringService.isNotEmpty(cnr)) {
			sb.append("ref_cnr:");
			sb.append(cnr);
			sb.append(spr);
		}
		
		if(StringService.isNotEmpty(sect)) {
			sb.append("ref_sect:");
			sb.append(sect);
			sb.append(spr);
		}
		
		if(StringService.isNotEmpty(cate)) {
			sb.append("ref_cat:");
			sb.append(cate);
			sb.append(spr);
		}
				
		if(StringService.isNotEmpty(etc)) {
			sb.append("ref_etc:");
			sb.append(etc);
			sb.append(spr);
		}
		
		if(StringService.isNotEmpty(brn)) {
			sb.append("ref_brn:");
			sb.append(brn);
			sb.append(spr);
		}
		
		if(StringService.isNotEmpty(br)) {
			sb.append("ref_br:");
			sb.append(br);
			sb.append(spr);
		}
		
		if(StringService.isNotEmpty(type)) {
			sb.append("ref_type:");
			sb.append(type);
			sb.append(spr);
		}
		
		if(StringService.isNotEmpty(set)) {
			sb.append("set:");
			sb.append(set);
			sb.append(spr);
		}
				
		if(StringService.isNotEmpty(contt)) {
			sb.append("dpos:");
			sb.append(contt);
			sb.append(spr);
		}

        if(StringService.isNotEmpty(abTestSn)) {
            sb.append("ab_test_sn:");
            sb.append(abTestSn);
            sb.append(spr);
        }

        if(StringService.isNotEmpty(abRevSn)) {
            sb.append("ab_rev_sn:");
            sb.append(abRevSn);
            sb.append(spr);
        }

        if(StringService.isNotEmpty(abRevTrnSn)) {
            sb.append("ab_rev_trn_sn:");
            sb.append(abRevTrnSn);
            sb.append(spr);
        }

		if(sb.toString().endsWith(spr)) {
			sb.delete(sb.lastIndexOf(spr), sb.length());
		}
		
		if(var != null){
			pageContext.setAttribute(var, sb.toString());
		}
		else{
			pageContext.getOut().print(sb.toString());
		}
		return SKIP_BODY;
    }

	
}
