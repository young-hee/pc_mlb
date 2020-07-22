package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GodImgExtend extends GodImg{	
	/**
	 * 
	 */
    private static final long serialVersionUID = -4186675738679379677L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * 새로운 이미지 순번	 
	 */
	private java.lang.Integer newImgTurn;
	
    /**
     * 언어코드
     */
    private String langCd;
    
    /**
     * 임시 이미지 파일명
     */
    private String tempFileName;

    /**
     * 임시 이미지 파일 위치
     */
    private String tempResourcePath;
    
    /**
     * 브랜드 코드
     */
    private String brndId;
    
    /**
     * ERP 상품번호	 
     */
    private String erpGodNo;
    
    /**
     * cloud front path
     */
    private String cloudFrontPath;

    /**
     * cloud front Type
     * 
     * Discovery : DXM
     * 
     */
    private String cloudFrontType;
    
    /**
     * 이미지 저장 위치
     */
    private String saveImagePath;
    
    /**
     * 이미지 등록 유형
     * 
     * ONE : 개별
     * MASS : zip 파일 대량 등록
     * 
     */
    private String imageUploadType;
    
    /**
     * 파일 확장자
     */
    private String fileExt;
    
    
    private String imgModYn;
    
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

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
}
