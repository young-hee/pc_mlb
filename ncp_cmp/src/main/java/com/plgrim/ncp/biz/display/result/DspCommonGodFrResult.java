/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shsunhee.kim
 * @since       2015. 11. 6       
 */
package com.plgrim.ncp.biz.display.result;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspGodPrc;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyTagResve;
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResve;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.framework.commons.StringService;

/**
 * Instantiates a new dsp cnr contt god fr result.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class DspCommonGodFrResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = 7048830595256758887L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 상품정보 */
	God god;
	
    /** 전시상품가격 */
    DspGodPrc dspGodPrc;
    
    /** 브랜드 정보 */
    SysBrnd sysBrnd;
    
    /** 상품 언어별 상품 명 */
    GodLangbyGodNm godLangbyGodNm;
    
    /** The god tag resve. */
    GodTagResve godTagResve;
    
    
    /** The god langby tag resve. */
    GodLangbyTagResve godLangbyTagResve; 
    
    /** 아이콘명. */
    String iconNm;
    
    String mnfcturDate;
    
    /** 판매순위 */
    Integer sttRank;
	
    /** 상품평 수. */
    Integer godas;
    
    String imgFrontUrl;
    String imgBackUrl;
	
	
    /** The icon1. : 시즌아이콘 */
    String icon1;
    
    /** The icon2. : 상품아이콘(NEW,BEST,SEASONOFF)*/
    List<String> icon2;
    
    /** The icon3. : 할인율 */
    String icon3;
    
    String tagNm;
    
    String colorStyleCd;

    String[] colorStyleCds;
    
	public String[] getColorStyleCds() {
		
		if(getColorStyleCd() != null){
			
			return getColorStyleCd().split(",");
			
		}else{
		
			return colorStyleCds;
		}			
	}
	
	//브랜드 상품 자동페이징처리 2016.06.02 seozzu
	String ctgNm;
    String image;
    
    String sesonGrpNm;
    
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
    /**
	 * 시즌 아이콘 : 현재년도 상품에 대해 seson_grp_cd 적용.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @return String [설명]
	 * @since 2015. 10. 21
	 */
	public String getIcon1() {
    	icon1 = "";
    	if(god!=null) {
    		String sesonGrpCd = god.getSesonGrpCd();
    		if(StringService.isNotEmpty(sesonGrpCd)) {
		    	Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
				String cDate = simpleDateFormat.format(date);
						
				
				//입점사여부
				String partmalSectCd = god.getPartmalSectCd();
				
				if(partmalSectCd.equals("PARTMAL")) {  //입점사
					if(StringService.isNotEmpty(this.mnfcturDate)) {
						if(cDate.equals(this.mnfcturDate.substring(0,4))) {
							icon1 = cDate.substring(2,4) + sesonGrpCd;
						}
					}
				} else {  //자사				
					String cNo = cDate.substring(3,4);
					String erpNo = god.getErpGodNo();
					String sNo = erpNo.substring(2,3);
					
					if(cNo.equals(sNo)){
						icon1 = cDate.substring(2,4) + sesonGrpCd;
					}
				}
    		}
    	}
    	return icon1;
    }
    
    /**
     * 상품 아이콘 목록.
     * 
     * <p/>
     * 
     * [사용 방법 설명].
     *
     * @return List [설명]
     * @since 2015. 10. 21
     */
    public List<String> getIcon2() {
    	icon2 = new ArrayList<String>();
    	if(StringService.isNotEmpty(this.iconNm)) {
    		String s[] = this.iconNm.split(",");
    		for(String icon: s) {
    			icon2.add(icon);
    		}
    	}
    	
    	return icon2;
    }
    
    /**
     * 상품할인율, 쿠폰할인율 적용
     * (2개 모두 존재할 경우 "+" ).
     * 
     * <p/>
     * 
     * [사용 방법 설명].
     *
     * @return String [설명]
     * @since 2015. 10. 21
     */
    public String getIcon3() {
    	icon3 = "";
    	if(dspGodPrc != null) {
			BigDecimal godDcRt = dspGodPrc.getGodDcRt();
			BigDecimal cpnDcRt = dspGodPrc.getCpnDcRt();
			if((godDcRt != null && godDcRt.intValue() > 0) || (cpnDcRt != null && cpnDcRt.intValue() > 0 )) {
				if(godDcRt != null && godDcRt.intValue() > 0) {
					icon3 = godDcRt.toString() +  "%";
				}
				if((godDcRt != null && godDcRt.intValue() > 0) && (cpnDcRt != null && cpnDcRt.intValue() > 0 )) {
					icon3 += " + ";
				}
				if(cpnDcRt != null && cpnDcRt.intValue() > 0) {
					icon3 += cpnDcRt + "%";
				}
			}
    	}
	
    	return icon3;
    }
    
    /**
     * Gets the tag nm.
     *
     * @return the tag nm
     */
    public String getTagNm() {
    	if(god != null){
			this.tagNm = god.getTagNm();
		}
    	if(godTagResve != null){
			if(godLangbyTagResve != null && godLangbyTagResve.getTagNm() != null){
				this.tagNm = godLangbyTagResve.getTagNm();
			}else if(godTagResve != null && godTagResve.getTagNm() != null){
				this.tagNm = godTagResve.getTagNm();
			}
		}
    	
    	return this.tagNm;    	
    }

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
