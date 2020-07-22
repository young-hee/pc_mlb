package com.plgrim.ncp.biz.display.data;

import java.util.Date;
import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.RecoPick;
import com.plgrim.ncp.base.entities.datasource1.dsp.RecoPickSDO;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.biz.display.result.MallDspFoCtgryResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspCtgryScFrDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = -3322855233470757931L;

	String tgtMbrTpCd;
	
	/** 전시카테고리 정보 */
	DspCtgry dspCtgry;
	String upperCategory;
	/** 상위전시카테고리 번호 */
	String[] upperCategoryNos;

	/** 하부 전시카테고리번호 목록 */
	String[] dspCtgryNos;
	/** 전시카테고리 깊이 코드 */
	String dspCtgryTreeRootDspCtgryNo;
	/** DpthCategory No */
	String[] ctgryDpths = new String[5];
	/** ctgryDpthCd */
	String ctgryDpthCd;

	/** 검색필터 : 브랜드목록 Best */
	String[] searchConditionBrandBests;
	/** 검색필터 : 브랜드목록 Best */
	String[] searchConditionBrandAtoZs;
	/** 검색필터 : 색상목록 */
	String[] searchConditionColors;
	/** 검색필터 : 색상패턴목록 */
	String[] searchConditionColors2;
	/** 검색필터 : 사이즈 */
	String[] searchConditionSizes;
	/** 검색필터 : 재질 */
	String[] searchConditionMaterials;
	/** 검색필터 : 스타일 */
	String[] searchConditionStyles;

	String[] searchConditionBrandIds;
	
	String[] searchConditionTeamCds;
	/** 검색필터 : 팀코드 */
	String searchConditionBrandId;
	/** 검색피러 : 하위카테고리별 사이즈 */
	String[] searchConditionDspCtgrySizes;
	/** 상품 정렬 */
	String sortColumn;
	String sortValue;

	/** 신상품 기준 조건 */
	Date newGodCondition;

	String useNew;

	/** 특설매장 (OUTLET/BRAND) */
	String special;
	/** 브랜드샵 카테고리 유형 */
	String dspCtgryOutputType;
	/** 특설매장의 카테고리 타입 배열 */
	String[] specialCtgryTypes;
	/** 해당 검색조건에 의한 상품 번호 전체 */
	String godNos;
	/** Brand Id */
	String brndId;
	/** 전시상품 가격 - 가격구분코드 */
	String prcSectCd;

	/** 판매집계코드 */
	String sttSectCd;
	/** 판매랭크 */
	int sttRank;

	/** 판매지수 유형 */
	String saleIdxTp;

	/** 일모카드 사용 유무 */
	String empYn;

	String dspCtgryType;

	String mbCtgryListYn;

	private String cnrSn;// 코너 번호
	private List<String> godNoList;// 상품 번호 목록
	private String aplMbrTp;// 적용회원유형
	private String aplMbrAtrb;// 적용회원속성
	private String grpcoId;// 적용회원그룹사ID

	String imgSize;

	List<String> brandIds;

	String currentPage;

	String NewBest;

	String fLocation;

	String[] size;
	String[] color;
	String[] color2;
	String[] meterial;
	String[] style;
	String[] brand;

	String[] tempSize;
	String[] tempColor;
	String[] tempColor2;
	String[] tempMeterial;
	String[] tempStyle;
	String[] tempBrand;

	private String searchAllBrandNM = ""; // 브랜드 이름
	private String searchSizeNM = ""; // 사이즈 이름
	private String searchColorNM = ""; // 색이름
	private String searchMaterNM = ""; // 소재이름
	private String searchCateNM = ""; // 카테고리

	String thmCtgryNo;

	String dspCtgryNo;

	String dspCtgryNm;
	
	String[] dpthsByExprTp;

	String dspBrndId;

	String beaker1dpthCtgryNo;

	List<String> cateList;

	String turn;

	String brandShopNo;

	String indexKey;

	String mallGubun;
	
	Integer topNum;

	String ctgrySectCd;

	String mbrNo;

	String filterCtgryNo;

	String ovseaBrndYn;

	String filterType;

	// 검색으로 하는 경우 filter를 상품 기준으로 조회하여 세션에 저장할지 여부.
	boolean searchFilterInitFlag = false;
	
	String pageNo;

	int pageSize = 40; // 40 80 120
	
	int mallPageSize = 36; // 36 72 96

	String currentCtgryDpthCd;

	String ctgryNoDpth1;

	String ctgryNoDpth2;

	String ctgryNoDpth3;
	
	String ctgryNoDpthNm1;

	String ctgryNoDpthNm2;

	String ctgryNoDpthNm3;
	
	// 대카테고리
	String women = DisplayEnum.WOMEN.toString();

	String men = DisplayEnum.MEN.toString();

	String kids = DisplayEnum.KIDS.toString();

	int prcStart;

	int prcEnd;
	
	Long cnrTpGrpSn;
	
	Long cnrSetSn;
	
	String ctgryType;
	
	private java.lang.Integer cnrSetBaseDspCnt;
	
	private java.lang.Integer cnrSetBaseDspCntEnd;
	
	String searchText;
	
	String godBadgeYn;
	
	/**
	 * 전시 사용 그룹 유형 코드
	 * ㅁ. 템플릿 유형 : TMPLAT_TP
	 *    >. 메인 템플릿 : MAIN_TMPLAT
	 *    >. 메인 PC 상단 배너 : MAIN_PC_UPEND_TMPLAT
	 *    >. 메인 MO 상단 배너 : MAIN_MOBILE_UPEND_BANNER
	 *    >. 스타일 목록 템플릿 : STYLE_LIST_TMPLAT
	 *    >. 디스커버러 목록 템플릿 : DX_LIST_TMPLAT
	 *    >. 프로모션 목록 템플릿 : PRM_LIST_TMPLAT
	 *    >. 카테고리 변경 값 : CTGRY_MOD_VAL	 
	 */
	private String dspUseGrpTpCd;  

	private String bstGodEvlYn;
	
	private String[] recoPickGodNos;
	
	private List<RecoPick> recoPicks;
	
	private List<RecoPickSDO> recoPickSDOs;
	/**
	 * 메타 제목 명
	 * 메타 정보에 사용되는 "Title"	 
	 */
	private String metaSjNm;


	/**
	 * 메타 설명 내용
	 * 메타 정보에 사용되는 "Description"	 
	 */
	private String metaDscrCont;


	/**
	 * 메타 키워드
	 * 메타 정보에 사용되는 "Keywords"	 
	 */
	private String metaKwd;
	
	/**
	 * 하위 카테고리 목록
	 */
	private List<MallDspFoCtgryResult> lowerCtgryList;
	
	private List<DspCtgryScFrDTO> dspCtgryScFrDTOList;
	
	/**
	 * mallId, mallGubun setter
	 */
	@Override
	public void setMallId(String mallId){
		
		super.setMallId(mallId);
		this.setMallGubun(mallId);
	
	}
	
	/**상품팀코드 */
	private String teamCd;
	
	
	private String sortSeq;
	
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * --------------------------------------------------------------------- public
	 * & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * --------------------------------------------------------------------- private
	 * method. ---------------------------------------------------------------------
	 */

}
