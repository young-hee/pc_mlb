/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      mc009.park
 * @since       2015. 6. 19       
 */
package com.plgrim.ncp.biz.cart.data;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.bsk.Bsk;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.biz.cart.result.CartPrmResult;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 [장바구니 조회용 DTO]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author mc009.park
 * @since 2015. 6. 19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Slf4j
@JsonInclude(value=Include.NON_EMPTY)
public class CartSearchDTO extends AbstractDTO {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 7644269984548697154L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** 장바구니 */
	Bsk bsk = new Bsk();
	
	/** 장바구니 병합용 대상 장바구니 */
	Bsk targetBsk;
	
	/** 회원 개체 */
	Mbr mbr;

	/** 장바구니 상품 목록 */
	List<BskGod> bskGodList;
	
	/** 프로모션 번호 목록 */
	List<String> prmNoList;
	
	/** 상품 번호 목록 */
	List<String> godNoList;
	
	/** 주문 프로모션 제외 대상 상품 */
	List<String> exclsList;
	
	/** The bskgod extend list. */
	List<BskGodExtend> bskgodExtendList;
	
	/** 주문 사은품. */
	List<CartOrderGiftDTO> ordGiftList;
	
	/** 상품 순번 최대 값 */
	private int maxGodTurn; //장바구니 병합에서 사용
	
	/**통화코드*/
	private String crncyCd;
	
	/** 장바구니 그룹 순번 */
	private String grpSeq;
	
	/** 프로모션 번호 */
	private String prmNo;
	
	/** 상품 번호 */
	private String godNo;
	
	/** 상품 가격 */
	private String price;
	
	/** 상품 수량 */
	private String itmQty;
	
	/** 싱글 EPID */
	private String epUserid;
	
	/** B2E인증키 */
	private String b2eCrtfcKey;
	
	/** 배송지 구분 코드 */
	private String dlvSectCd;
	
	/** 픽업 매장 ID */
	private String pkupShopSn;
	
	/** 브랜드 ID */
	private String brndId;
	
	/** 매장Id */
	private String shopId;
	
	/** 임직원 전용 여부 */
	private String empYn = "N";
	
	/** 이미지 사이즈 */
	private String imgSizeCd = "100X132";
	
	/** 수정자 Id */
	private String udterId;
	
	
	private String bskNo;
	private int parentGodTurn;
	private String pckageGodYn;
	
	private long wishSn;

	private List<CartPrmResult> cartPrmResultList;



	/*
	 * ncp 3차
	 * 장바구니 선택 국가코드
	 */
	private String cartNationCd;

	/*
	 * ncp 3차
	 * 장바구니 선택 국가존
	 */
	private String cartZoneTurn;

	/*
	 * ncp 3차
	 * 장바구니 선택 국가 해외 배송비 정책 일련번호
	 */
	private String cartOvseaDlvCstPlcSn;
	
	// 예약장바구니 기능 추가  : by cannon (2016.04.18)
	private String resveSaleGodYn;

	private String langCd;
	
	private java.lang.Long wishlstSn;

	private String godTurn;		   // 상품순번
	
	private String naverPayYn = "N";
	
	private String virtlDlvComptYn = "N";
	
	private String virtlDlvOrdNo;
	
	private String virtlDlvClmNo;
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
	
	public static <T> T fromJSON(String jsonString, Class<T> type){
        ObjectMapper objectMapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
        	return objectMapper.readValue(jsonString, type);
        }catch(IOException ie) {
        	throw new RuntimeException(ie.getMessage());
        }
    }
	
	public String toJSON() {
		ObjectMapper objectMapper = new ObjectMapper();
        String jsonInString;
        try {
            jsonInString = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.warn("error occured during parse to json:" + e.getMessage(), e);
            return "{"+ToStringBuilder.reflectionToString(this)+"}";
        }

        return jsonInString;
	}

}

