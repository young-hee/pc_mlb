package com.plgrim.ncp.biz.display.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnr;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrContt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSet;
@Data
@EqualsAndHashCode(callSuper=false)
public class DspCnrSearchFoDTO extends AbstractDTO {
	/**
	 * 
	 */
    private static final long serialVersionUID = -9149203599362383736L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    
    String parentType;
    String parentStringCd;
    int parentIntCd;
    
    DspCnr dspCnr;
    
    DspCnrSet dspCnrSet;
    
    DspCnrContt dspCnrContt;
    
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
	
	/** 임직원여부 */
	String empYn;
	
	String grpcoId;
	
	String soldDspYn;
	
	String pcId;
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
