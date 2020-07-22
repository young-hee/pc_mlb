package com.plgrim.ncp.biz.display.result;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryCnncGod;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspGodPrc;
import com.plgrim.ncp.base.entities.datasource1.dsp.RecoPick;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspCtgryGodFoResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = -8946004039738762688L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    
    List<DspCtgryGodFoResult> sizeFilters;
    List<DspCtgryGodFoResult> colorFilters;
    List<DspCtgryGodFoResult> teamFilters;
    /** 전시카테고리 정보 */
    DspCtgryCnncGod dspCtgryCnncGod;
    /** 전시카테고리 상품 일일 판매량 */
//    SttDatelyCtgryGodSale sttDatelyCtgryGodSale;
    /** 전시상품 기본 정보 */
    God god;
    /** 전시상품가격 */
    DspGodPrc dspGodPrc;
    
    RecoPick recoPick;
    /** 브랜드 정보 */
    SysBrnd sysBrnd;
    /** 상품 언어별 상품 명 */
    GodLangbyGodNm godLangbyGodNm;
    
    List<GodItm> godItmList;
    
    /** 상품 이미지 정보 */
    List<GodImg> godImgList;

    /** 상품 아이콘 목록 */
    List<GodIconFoResult> godIconList;
    /** 상품옵션 목록 (사이즈/재질/스타일) - 검색엔진과 협의 필요함 - BO 상품정리된 이후 진행 */


    /**상품할인율 */
    String godDcRt;
    /**코폰 할인률 */
    String cpnDcRt;
    
    /**상품목록에 사용할 이미지URL (앞면)*/
    String imgFrontUrl;
    /**상품목록에 사용할 이미지URL (뒷면)*/
    String imgBackUrl;
    /**상품목록에 사용할 상세이미지URL (앞면)*/
    String detailImgFrontUrl;
    
    /**상품제조일자 (yyyyMM)*/
    String mnfcturDate;
    
    /**모바일 포커스온 위클리뉴 상품 ImgUrl 90X120 */
    String focusOnGodImgUrl; 
    /**모바일 위클리뉴 상품 ImgUrl 280X370 */
    String weeklyNewGodImgUrl;
    
    String colorStyleCd;

    String[] colorStyleCds;

    String sesonGrpNm;

	public String[] getColorStyleCds() {
		
		if(getColorStyleCd() != null){
			
			return getColorStyleCd().split(",");
			
		}else{
		
			return colorStyleCds;
		}			
	}

    /** 질문 및 예문 리스트 */
    List<GoodsFinderFoResult> goodsFinderFoList;
    
    String godNo;
    
    String optValCd1;
	
    String dspCtgryNo;
    
    String filterType;
    
    String godUrl;
    
    String method;
    
    String tagNm;
	String colorTagNm;
	
	String brndId;
	
	String godBadgeYn;
	
	/**상품팀코드 */
	private String teamCd;
	
	/**상품팀명 */
	private String teamNm;
	
	/**언어별 상품팀명 */
	private String teamAltNm;
	
	Integer godRank;
	
	String nationCd;
	
	
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
