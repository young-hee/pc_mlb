package com.plgrim.ncp.biz.display.data;

import com.plgrim.ncp.biz.promotion.data.EventSearchFoDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspPromtScFrDTO extends EventSearchFoDTO{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 기획전 일련번호
	"1"부터 오라클 시퀀스(MPD_SPDP_BASE_SQ01)를 사용해서 발행	 
	 */
	private java.lang.Long promtSn;
	
	/**
	 * 기획전 그룹 일련번호
	 */
	private java.lang.Long promtGrpSn;

	private String sortValue;
	
	
	private String sprtr;
	
 
	
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
	private String grpcoId;
	
	/**
	 * 임직원 여부
	 * 
	 * */
	private String empYn;
	
	/**
	 * 미리보기 여부
	 */
	private String prev = "N";
	
	/**
	 * 선택한 카테고리 및 브랜드 코드
	 * 
	 * */
	private String sectCd;
	
	private String mode;
	
	private Long cnrSn;
	  
	private Long cnrTpGrpSn;
	
	private String searchCategory;

	private String promtTpCd;
	
	private String dspUseGrpTpCd;
	
	private String promtExpsrSectCd;
	
	/**
	 * ORDER BY 사용시 DESC 또는 ASC
	 */
	private String sortDiv;
	
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
