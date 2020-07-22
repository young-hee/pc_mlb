package com.plgrim.ncp.biz.display.data;

import java.util.Date;
import java.util.List;

import com.plgrim.ncp.biz.display.result.GoodsFinderFoResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;

@Data
@EqualsAndHashCode(callSuper = true)
public class DspCtgrySearchFoDTO extends AbstractDTO {
    
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = -3322855233470757931L;
	
	/** 전시카테고리 정보 */
	DspCtgry dspCtgry;
	
	/** 상위전시카테고리 번호 */
	String[] upperCategoryNos;
	
	/** 카테고리정보 로딩 여부 */
	String hasCtgryInfo = "Y";
	/** 하부 전시카테고리 로딩 여부 */
	String hasSubCtgry = "Y";
	/** 상품리스트를 가져올지 여부 */
	String hasGodList = "Y";
	/** 로케이션정보를 가져올지 여부 */
	String hasLocation = "Y";
	/** 로케이션정보를 가져올지 여부 */
	String hasAllCtgry = "Y";
	

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

	String searchConditionBrandId;
	
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
	/** Recommendation 상품번호 */
	String[] recoGodNoList;
	/** Brand Id */
	String brndId;
	/** 전시상품 가격 - 가격구분코드 */
	String prcSectCd;

	/** 판매집계코드*/
	String sttSectCd;
	/** 판매랭크 */
	int sttRank;
	
	/** 판매지수 유형*/
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

	private List<String> dsgnGrpNoList;// 디자인그룹 번호 목록
	
	String notIncBrndNo;

	String grpBrndId;

	String mallGubun;

	String menCtgryNo;	//라이프스타일 MEN 카테고리 번호

	String womenCtgryNo;	//라이프스타일 MEN 카테고리 번호..

	int defaultImgTurn;	// 상품리스트 상품기본이미지 순번

	private String imgSizeCd;	// 이미지 사이즈 코드
	private String selectSectCd; // 조회 구분
	/**
	 * 브랜드 카테고리 번호
	 *   - [2016.06.04] 상품상세 브랜드 카테고리
	 *     정보 전달시 사용하기 위해 추가
	 */
	private String brndCtgryNo;

	// 모바일 대카메인 - what's new View All에서 특정 카테고리만 가져오기 위함 'DXMA01','DXMA02','DXMA03','DXMA04','DXMA05','DXMA06'
	List<String> bigCateList;

	/** 수트파인더 습득자 번호 */
	String finderNo;

	/** 수트파인더 질문 및 예문 리스트 */
	List<GoodsFinderFoDTO> goodsFinderFoDTOList;

	/** 수트파인더 예문 순번 리스트 */
	List<String> smpleSntnTurnList;

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
