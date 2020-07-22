/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 4. 16       
 */
package com.plgrim.ncp.biz.vendor.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcExcp;

/**
 * VendorSearchDTO
 * @author lss
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class VendorSearchDTO extends AbstractDTO {

	/**
	 * serialVersionUID
	 */
    private static final long serialVersionUID = 1L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    
    

    /**
     * 업체 Entity
     */
    private Com com = new Com();
    
    /**
     * comDmstcDlvCstPlcExcp
     */
    private ComDmstcDlvCstPlcExcp comDmstcDlvCstPlcExcp;
    /**
     * comDmstcDlvCstPlc
     */
    private ComDmstcDlvCstPlc comDmstcDlvCstPlc;
	/**
	 * 대표자 명	 
	 */
	private String rprstivNm;
	/**
	 * 사업자등록번호	 
	 */
	private String bmanRegNo;
	/**
	 * 업체유형코드	 
	 */
	private String comTpCd;	
    
    /**
     * 페이지번호
     */
    private String pageNo;
    
    /**
    * 제휴몰구분
    */
   private List<String> searchComTpCd;
   
   /**
    * 조회구분
    */
   private String searchGubun;
   
   /**
    * 조회txt
    */
   private String searchTxt;
   
   /**
    * 업체 상태 코드
    * COM_STAT    업체 상태
    * APRV_WAIT    승인대기
    * APRV_COMPT    승인완료
    * APRV_REJECT    승인거부
    * DELETE    삭제
    */
   private List<String> searchComStatCd;
   
   
   /**
    * 브랜드아이디
    */
   private String brndId;
   
   /**
    * 브랜드명
    */
   private String brndNm;
   
   /**
    * 이력일시 조회시작일자
    */
   private String searchStartDate;
   
   /**
    * 이력일시 조회끝일자
    */
   private String searchEndDate;
   
   /** 매장별 수수료율 **/
   
   /**
    * searchShop
    */
   private String searchShop;
   
   /**
    * searchShopValue
    */
   private String searchShopValue;
   
   /**
    * searchBrand
    */
   private String searchBrand;
   
   /**
    * searchBrandValue
    */
   private String searchBrandValue;
   
   /**
    * dlvShopYn
    */
   private String dlvShopYn;
   
   /**
    * pkupShopYn
    */
   private String pkupShopYn;
   
   /**
    * viewRegDt
    */
   private String viewRegDt;
   
   /**
    *  제휴대행사코드 
    */
   private String searchAffVrscComId;
   
   /**
    * 업체등록건수 (ERP참조입력용)
    */
   private java.lang.Integer comCnt;

   /**
    * 업체ID (제휴대행사조회용)
    */
   private String searchComId;

   private String ovseaDlvdmstcDlvCstPlcYn;


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
