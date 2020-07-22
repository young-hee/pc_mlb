package com.plgrim.ncp.biz.display.result;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.enums.DisplayEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class DxmDspFoCtgryResult extends AbstractResult{

	List<DxmDspFoCtgryResult> dxmDspFoCtgryResults;
	//카테고리 정보
	DspCtgry ctgryInfo;

	//브랜드 리스트
	List<DspCtgry> brndList;

	//전체 전시 카테고리 트리 리스트
	List<DspCtgry> allCtgryList;

	//전시 카테고리 트리 리스트
	List<DspCtgry> ctgryList;

	//전략 카테고리 리스트
	List<DspCtgry> strtgyCtgryList;

	//Outlet 카테고리 리스트
	List<DspCtgry> outletCtgryList;

	//모바일 전시 카테고리 트리 리스트
	List<DspCtgryLNBResult> mbCtgryList;

	//전략 카테고리 리스트
	List<DspCtgryLNBResult> mbStrtgyCtgryList;

	//Outlet 카테고리 리스트
	List<DspCtgryLNBResult> mbOutletCtgryList;

	//기타 카테고리 리스트
	List<DspCtgryLNBResult> etcCtgryList;

	//신상품, 인기상품, 세일상품 카테고리 리스트
	List<DspCtgry> newTopOnCtgryList;

	List<DspCtgry> locationList;

	//상품상세여부
	String godDetailYN;

	String dspCtgryNo;
   
	String upperDspCtgryNo;
	
	String brandShopNo;

	String brndShopId;

	String mallGubun;

	String locationTp;
	
	String dspCtgryNm;
	//전략카테고리, Outlet카테고리 번호
	String etcCtgryNo;

	CategoryChackResult ctgyChack;


	//대카테고리
	String women = DisplayEnum.WOMEN.toString();

	String men = DisplayEnum.MEN.toString();

	String kids = DisplayEnum.KIDS.toString();

	String otlt= DisplayEnum.OTLTCTG.toString();

	String brandShopNm;
	
	List<DspCnrFrResult> lnbCnr;
	
	/** 상품 정렬 */
	String sortColumn;
	
	/**
	 * 리프 카테고리 여부
	 * ㅁ. 최하위 카테고리 여부 : Y이면 상품연결 가능함.	 
	 */
	private String leafCtgryYn;

	/**
	 * 카테고리 구분 코드
	 * ㅁ. 카테고리 구분 : CTGRY_SECT
	 *    >. 일반 카테고리 : GNRL_CTGRY
	 *    >. 전략 카테고리 : STRTGY_CTGRY
	 *    >. 신상품 카테고리 : NEW_GOD_CTGRY
	 *    >. 베스트 카테고리 : BST_CTGRY
	 *    >. ON SALE 카테고리 : ON_SALE_CTGRY
	 *    >. 아울렛 카테고리 : OTLT_CTGRY	 
	 */
	private String ctgrySectCd;
	
	/**
	 * 출력 구분 코드
	 * ㅁ. 출력 구분 : OUTPT_SECT
	 *    >. 일반 : GNRL
	 *    >. 링크 : LINK
	 */
	String outptSectCd;
	
	/**
	 * 출력 링크 URL
	 * ㅁ. 출력이 링크일때 이동항 링크의 URL을 관리 함
	 */
	String outptLinkUrl;
	

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
}
