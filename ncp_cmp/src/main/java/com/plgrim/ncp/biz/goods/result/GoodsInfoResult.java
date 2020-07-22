package com.plgrim.ncp.biz.goods.result;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.com.ComChrger;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcLang;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspGodPrc;
import com.plgrim.ncp.base.entities.datasource1.god.GodAditDetail;
import com.plgrim.ncp.base.entities.datasource1.god.GodColor;
import com.plgrim.ncp.base.entities.datasource1.god.GodCpstGodCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodDsgnGrp;
import com.plgrim.ncp.base.entities.datasource1.god.GodDspCtgryExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyCpstGodNmExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNmExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndrDscr;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndrImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodMovi;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodNtfcDetail;
import com.plgrim.ncp.base.entities.datasource1.god.GodOpt;
import com.plgrim.ncp.base.entities.datasource1.god.GodOptVal;
import com.plgrim.ncp.base.entities.datasource1.god.GodRelateExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodSaleMall;
import com.plgrim.ncp.base.entities.datasource1.god.GodStdCtgryExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodFitLktbExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;

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
 * Description	:	상품 정보 RESULT
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("goodsInfoResult")
public class GoodsInfoResult extends GoodsResult{ 	
	private static final long serialVersionUID = -2760735906564861790L;
	
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 상품 엔티티. */
    private GodExtend godEx;
    
    /** 상품 단품 엔티티. */
    private GodItmExtend godItmEx;
            
    /** 시스템브랜드 엔티티.*/
    private SysBrnd sysBrnd;
    
    /** 업체 엔티티.*/
    private Com com;
    
    /** 업체 담당자 엔티티.*/
    private ComChrger comChrger;
                
    /** 상품 디자인 그룹 정보 엔티티. */
    private GodDsgnGrp godDsgnGrp;
    
    /** 상품 표준카테고리 정보 엔티티. */
    private GodStdCtgryExtend godStdCtgryEx;

    /** 고객센터 메모 정보 엔티티 */
    private CsoCnsltMemo csoCnsltMemo;
        
    /** 전시 상품 가격 정보 엔티티 */
	private DspGodPrc dspGodPrc;
	
	/** 상품 컬러 엔티티 */
	private GodColor godColor;

	/** 상품 이미지 엔티티*/
	private GodImg godImg;
	
	/** 상품 동영상 엔티티*/
	private GodMovi godMovi;
	
	/** 상품 언어별 상품명 확장 엔티티 */
	private GodLangbyGodNmExtend godLangbyGodNmEx;
	
	/** 상품 언어별 상품명 확장 엔티티 */
	private GodLangbyGodNmExtend godLangbyGodNmExChi;
	
	/** 상품 언어별 상품명 영문 엔티티 */
	private GodLangbyGodNm godNmEng;
	
	/** 상품 언어별 상품명 중문 엔티티 */
	private GodLangbyGodNm godNmChi;
		
    /** 상품 언어별 상품명 리스트 */
	private List<GodLangbyGodNm> godLangbyGodNmList;
	
    /** 상품단품정보 엔티티 확장 리스트*/
    private List<GodItmExtend> godItmExList;

    /** 상품 고시 상세 엔티티. */
    private GodNtfcDetail godNtfcDetail;
    
    /** 상품고시상세정보 영문 엔티티*/
    private GodNtfcDetail godNtfcDetailEng;

    /** 상품고시상세정보 중문 엔티티*/
    private GodNtfcDetail godNtfcDetailChi;
    
    /** 상품고시상세정보 엔티티 리스트*/
    private List<GodNtfcDetail> godNtfcDetailList;
    
    /** 상품세탁 설명 엔티티 리스트*/
    private List<GodLndrDscr> godLndrDscrList;
    
    /** 상품세탁 이미지 엔티티 리스트*/
    private List<GodLndrImg> godLndrImgList;
    
    /** 상품 전시카테고리 정보 리스트. */
    private List<GodDspCtgryExtend> godDspCtgryExList;
    
    /** 상품 이미지 정보 리스트. */
    private List<GodImg> godImgList;
    
    /** 세트/패키지 구성품 정보 확장 리스트. */
    private List<GodCpstGodCnncExtend> godCpstGodCnncExList;
    
    /** 세트/패키지 구성품 언어별 상품명 확장 리스트. */
    private List<GodLangbyCpstGodNmExtend> godLangByCpstGodNmExList;
        
    /** 상품 판매 몰 리스트. */
    private List<GodSaleMall> godSaleMallList;
    
    /** 상품 옵션 리스트. */
    private List<GodOpt> godOptList;
    
    /** 상품 옵션 값 리스트. */
    private List<GodOptVal> godOptValList;
    
    /** 업체  국내 배송비 정책. */
    private ComDmstcDlvCstPlc comDmstcDlvCstPlc;
    
	/** 업체  국내 배송비 정책 리스트. */
	private List<ComDmstcDlvCstPlc> comDmstcDlvCstPlcList;
	
	/** 업체  국내 배송비 정책 언어별 리스트. */
	private List<ComDmstcDlvCstPlcLang> comDmstcDlvCstPlcLangList;
	
	/** 상품 추가정보 리스트. */
    private List<GodAditDetail> godAditDetailList;

    /** 상품 연관 정보 리스트 */
    private List<GodRelateExtend> godRelateExList;
    
    /** 상품 공지사항 정보 리스트 */
    private List<GodNotiExtend> godNotiExList;
    
    /** 상품 색상 정보 리스트 */
    private List<GodExtend> designColorList;
    
    /** 세트상품단품정보 엔티티 확장 리스트*/
    private List<GodItmExtend> cpstGodCnncItmExList;    
    
    /** 상품 이미지 유무. */
    private String imgExistYn;
    
    /** 상품 이미지 전체 경로. */
    private String imgFullUrl;
    
    /** 상품 옵션 품절여부 */
    private String optSoldYn;
    
    /** 인스타유입구분1 */
    private String utm_source;
    
    /** 인스타유입구분2 */
    private String utm_medium;
    
    /** 상품 핏 가이드 확장 엔티티 */
    private InfGodFitLktbExtend godFitLktbEx;

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
