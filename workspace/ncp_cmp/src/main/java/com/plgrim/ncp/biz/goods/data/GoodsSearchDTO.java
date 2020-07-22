package com.plgrim.ncp.biz.goods.data;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlExtend;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** Copyright (c) 2018 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 *
 * Description	:	상품 검색 DTO
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsSearchDTO")
public class GoodsSearchDTO extends AbstractDTO {
    private static final long serialVersionUID = 3822218401060855922L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /**
     * 고도몰 상품번호
     */
    private String goodsno;
    
    /** 정렬 flag. */
    private String sortFlag;
    
    /** 리뷰보기 flag. */
    private String reviewFlag;
    
    /** 상품번호. */
    private String godNo;
    
    /** 단품번호. */
    private String itmNo;
    
    /** ERP상품번호. */
    private String erpGodNo;
    
    /** 상품번호 멀티 */
    private String[] godNos;
    
    /** 정렬 상품번호 배열 */
    private String[] ordrGodNos;

    /** ERP상품번호 멀티. */
    private String[] erpGodNos;
    
    /** 단품번호 멀티 */
    private String[] itmNos;
    
    /** 전시카테고리 멀티 */
    private String[] dspCtgryNos;
    
    /** 브랜드ID 멀티. */
    private String[] brndIds;
    
    /** 브랜드 그룹ID. */
    private String brndGrpId;
        
    /** 브랜드ID. */
    private String brndId;
    
    /** 상품유형코드. */
    private String godTpCd;
    
    /** 상품유형코드 멀티. */
    private String[] godTpCds;
    
    /** 승인코드. */
    private String godAprvSectCd;    
    
    /** 판매구분코드. */
    private String godSaleSectCd;

    /** 컬러코드. */
    private String colorCd;
    
	/** 다중검색 상품번호 구분. */	
	private String godNosGbn;
			
	/** 검색기간. */
	private String term;
	
	/** 검색기간 시작일. */
	private String startTermDt;
	
	/** 검색기간 시작시간 */
	private String startTermHh;
	
	/** 검색기간 시작분 */
	private String startTermMm;
	
	/** 검색기간 종료일. */
	private String endTermDt;
	
	/** 검색기간 종료시간 */
	private String endTermHh;
	
	/** 검색기간 종료분 */
	private String endTermMm;
	
	/** 상품명 */
	private String godNm;
	    
    /** 등록자 */
    private String regtrId;
    
    /** 수정자 */
    private String udterId;
    
    /** 사용여부 */
    private String useYn;
    
    /** 디자인 그룹번호 */
    private String dsgnGrpNo;
        
    /** 언어코드 */
    private String langCd;

    /** 페이징 여부 */
    private String pagingYn;

    /** 등록자 */
    private String regtrNm;

    /** 수정자 */
    private String udterNm;
    
    /** 몰구분 */
    private String mallTpCd;
    
    /** 입점구분코드. */
    private String partmalSectCd;
    
    /** 입점구분코드 멀티 */
    private String[] partmalSectCds;

    /** 관리자 유형 코드 */
    private String adminTpCd;
    
    /** 전시여부. */
    private String dspYn;    
    
    /** 디자인 그룹번호 */
    private String dsgnGrpNoLike;
    
    /** 단품정보 조회선택값 */
    private String godItmShowGbn;    
    
    /** 상품승인거부 Y/N */
    private String aprvRejYn;
    
    /** 최소가격 */
    private String prcMin;
    
    /** 최대가격 */
    private String prcMax;
    
    /** 업체ID */
    private String comId;
    
    /** 옵션값 코드 */
    private String optValCd;
    
    /** 세탁 코드 */
    private String lndrCd;
    
    /** 가격반영 프로시져 결과코드 */
    private String oc_result_cd;

    /** 가격반영 프로시져 결과메세지 */
    private String oc_result_cont;    

    /** BO 미리보기 여부 */
    private String isBoPreview;
    
    /** 성별명 */
    private String sexNm;
    
    /** 단품명 */
    private String itemNm;
    
    /** 매장픽업 여부 */
    private String shopPkupPsbYn;
        
    /** 해외전시 상태 영어*/
    private String ovseaDspStatCd;
    
    /** 해외전시 상태 중국어*/
    private String ovseaDspStatCdChi;
    
    /** 판매구분 */
    private String resveGodYn;
    
    /** 인스타유입구분1 */
    private String utm_source;
    
    /** 인스타유입구분2 */
    private String utm_medium;
    
	/** 상품 상품평 확장 엔티티. */
	private GodGodEvlExtend godGodEvlEx;
	
	
    private String language;
    
    private String key;
    
    private String token;
	
    private String[] dsgnGrpNos;
    
	/**
	 * ERP  상품 연결 그룹 일련번호	 
	 */
	private String erpGodCnncGrpNo;
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
