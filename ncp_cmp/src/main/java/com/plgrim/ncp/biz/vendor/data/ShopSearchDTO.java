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

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ShopSearchDTO extends AbstractDTO {

	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
      
   
   /** 브랜드별 이력 **/
   private String brndId; //브랜드아이디
   private String brndNm; //브랜드명
   private String sido;   //시도
   private String gugun;  //구군
   private String sidocd;   //시도
   private String guguncd;  //구군
   private String srchBrnd;  //구군
   private String srchKeyword;  //구군
   private String srchType; 
   private String shopId;
   private String shopNm;
   private String shopType;
   private String cntryCd;
   
   private String godNo; //상품 번호
   private String[] cpstGodNoArr; // 구성상품 번호 배열
   private String godTpCd;
   
   private String lttd;
   private String lgtd;
   private String mobileSrchKeyword;  // 모바일 검색 키워드
   private String mobileYn;
   
   private List<String> brndIds;
   private List<String> shopIds;
   private String upperBrndId;

   private String[] shopIdArray;	// shop_id 배열
   
   private String erpGodNo;
   private String skuNo;
   private String itmNo;			//	단품번호
   
   private String goodsDetailYn; //상품상세에서 호출 여부
   
   private String closeRYn;

   private String talkYn;          /* 시나리오 채팅 호출 여부*/


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
