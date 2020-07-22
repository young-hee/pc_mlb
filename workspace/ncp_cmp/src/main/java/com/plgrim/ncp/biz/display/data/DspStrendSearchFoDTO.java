package com.plgrim.ncp.biz.display.data;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspStrendSearchFoDTO extends AbstractDTO{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/**strend rownum */
	private int rowNo;
	
	/**strend 시리얼 번호*/
	private int strndSn;
	
	/**strend 유형*/
	private String strndTpCd;
	
	private String brndId;
	
	private String sesonCd;

	/**템플릿 일련번호*/
	private java.lang.Long tmplatSn;
	
	/**코너 일련번호*/
	private java.lang.Long cnrSn;
	
	/**코너 세트 일련번호*/
	private java.lang.Long cnrSetSn;
	/**이미지 사이즈*/
	private String imgSizeCd;
	
	/** 개정 순번 */
	private Long revSn;
	
	/** 전시대상 설정 */
	/** 적용회원유형 
	 * (APL_MBR_TP: NMBR 비회원, 
	 * ONLINE_MBR 온라인회원, UNTY_MBR 통합회원) 
	 */
	String aplMbrTp;
	/** 적용회원속성
	 * (APL_MBR_ATRB: GNRL_MBR 일반회원, PLGRIM_FSHN 플그림패션,
	 * plgrim 플그림, GRPCO_ALL 그룹사전체, GRPCO_INDVDLZ 그룹사개별)
	 */
	String aplMbrAtrb;
	/** 전시상품 가격 - 가격구분코드 */
	String prcSectCd;
	
	/** 임직원 여부 */
	String empYn;
	
	String grpcoId;


	String upBrndId;
	String brndNotInYn;
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
