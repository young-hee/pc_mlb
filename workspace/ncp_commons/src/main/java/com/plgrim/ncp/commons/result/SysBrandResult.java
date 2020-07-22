package com.plgrim.ncp.commons.result;

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndImg;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndExtend;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysBrandResult extends AbstractResult {

	/**
	 * UID
	 */

    private static final long serialVersionUID = -6655325365564354058L;
    
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
    /**
     * 시스템 브랜드 entity
     */
    SysBrndExtend  sysBrnd;

    /**
     * 선택된 브랜드코드 이미지
     * */
    List<SysBrndImg> sysBrndImgList;
	
    /**
     * 브랜드 깊이
     */
    int brndDepth;
    
    /**
     * brnd tree id 
     */
    String id;
    
    /**
     * brnd tree parent id
     */
    String upperId;
    
    /**
     * brnd name 
     */
    String text;
    
    /**
     * 하위브랜드 갯수
     */
    int childCount;
    
 	/**
 	 * 최상위브랜드 정보
 	 * */
    String topBrndId;
    /**
     * 하위 브랜드
     */
    private List<SysBrandResult> item;
        
    java.util.Date batchEndDt;
}
