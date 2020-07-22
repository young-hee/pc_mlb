package com.plgrim.ncp.biz.display.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.biz.display.result.DspCnrFoResult;
import com.plgrim.ncp.biz.display.result.DspPlanFoResult;
import com.plgrim.ncp.biz.display.result.DspPlanGodFoResult;
import com.plgrim.ncp.biz.display.result.DspPlanSprtrFoResult;
import com.plgrim.ncp.biz.display.result.DspPlanTmplatFoResult;

@Data
@EqualsAndHashCode(callSuper = false)
public class DspPlanDetailResultFoDTO extends AbstractDTO{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**템플릿 정보*/
	private DspPlanTmplatFoResult dspPlanDetailTmplat;
	
	/***/
	private DspPlanFoResult dspPlanDetail;
	
	/**구분자 정보 리스트*/
	private List<DspPlanSprtrFoResult> dspPlanSprtrList;
	
	/**상품 정보 리스트*/
	private List<DspPlanSprtrFoResult> dspPlanDetailGodList;
	
	private List<DspPlanFoResult> dspBrndList;
	
	private List<DspPlanFoResult> dspPlanList;
	
	private List<DspPlanFoResult> dspCtgryList;
	
	private List<DspCnrFoResult> cornerList;
	
	private List<List<DspPlanGodFoResult>> detailMbGodList;
	
	private List<DspPlanSprtrFoResult> detailMbSprtrGodList;
	
	private long totalRow;
	
	private String tmplatSnTp;
	
	/**
	 * 유효성 체크결과
	 */
    private boolean checkResult = false;
    
    /**
     * 유효성 체크 결과코드
     */
    private String resultCode;
    
    /**
     * 결과 메시지
     */
    private String resultMessage;
    
    /**
     * 대상회원속성코드 TGT_MBR_ATRB_CD
     */
    private String tgtMbrAtrbCd;
    /**
     * 대상회원타입코드 TGT_MBR_TP_CD
     */
    private String tgtMbrTpCd;
    /**
     * 그룹사아이디 GRPCO_ID
     */
    private String grpcoId;
    /**
     * 대상회원속성코드명 DSP_TGT_TP_CD_NM
     */
    private String tgtMbrAtrbCdNm;
    /**
     * 대상회원타입코드명 TGT_MBR_TP_CD_NM
     */
    private String tgtMbrTpCdNm;
    /**
     * 그룹사명 GRPCO_NM
     */    
    private String grpcoNm;
    /**
     * 실패시 호출할 jsp
     */    
    private String failJsp;    
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
