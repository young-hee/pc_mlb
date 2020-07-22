package com.plgrim.ncp.biz.display.data;

import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspPlanSearchFoDTO extends AbstractDTO{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 이벤트 rownum
	 */
	private int rowNo;
	
	/**
	 * 적용 브랜드 ID
	ㅁ. 기획전이 노출 된 스토어로써 기준을 적용하기 위한 브랜트 ID 임	 
	 */
	private String aplBrndId;
	
	
	/**
	 * 기획전 일련번호
	"1"부터 오라클 시퀀스(MPD_SPDP_BASE_SQ01)를 사용해서 발행	 
	 */
	private java.lang.Long promtSn;
	
	/**
	 * 전시 카테고리 번호
	전시매장번호
	전시매장코드(3)+대카(3)+중카(3)+소카(3)+ 세카(3) +세세카(3)
	전시매장유형:Select Item, 브랜드매장	 
	 */
	private String categoryNo;
	
	private String sortValue;
	
	private String groupValue;
	
	private String mode;
	
	private String b2eCode;
	
	private String singleExist;
	
	private String sprtr;
	
	private String godNo;
	
	private String listExCnt;
	
	private String pageNo;
	
	private int PageRow;
	
	/** 전시대상 설정 */
	/** 적용회원유형 
	 * (APL_MBR_TP: NMBR 비회원, 
	 * ONLINE_MBR 온라인회원, UNTY_MBR 통합회원) 
	 */
	private String aplMbrTp;
	/** 적용회원속성
	 * (APL_MBR_ATRB: GNRL_MBR 일반회원, PLGRIM_FSHN 플그림패션,
	 * plgrim 플그림, GRPCO_ALL 그룹사전체, GRPCO_INDVDLZ 그룹사개별)
	 */
	private String aplMbrAtrb;
	
	/**
	 * 소속 그룹사 ID	 
	 */
	private String psitnGrpcoId;
	
	/**
	 * 미리보기 여부
	 */
	private String prev = "N";
	
	/**
	 * 선택한 카테고리 및 브랜드 코드
	 * 
	 * */
	private String sectCd;
	
	/**
	 * 임직원 여부
	 * 
	 * */
	private String empYn;

    private String soldDspYn;

	// 이미지 사이즈 코드
	private String imgSizeCd;
	
	/**
     * AB Test 통계분석을 위한 utag 변수선언
     * - 개정순번(revSn), 개정구성순번(revCpstTurn), AB테스트순번(abTestSn)
	 */
	private Long revSn;
    private Integer revCpstTurn;
    private Long abTestSn;

	/**
	 * imgQ 사용 여부
	 * difault.Y
	 */
	private String ondemandActive;

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
