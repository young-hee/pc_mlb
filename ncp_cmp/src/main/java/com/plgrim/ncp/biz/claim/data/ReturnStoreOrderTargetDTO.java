package com.plgrim.ncp.biz.claim.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReturnStoreOrderTargetDTO extends AbstractDTO {/**
	 * 
	 */
    private static final long serialVersionUID = -281291317778007170L;

    /**
     * LDDG.DLIVY_DRCT_TP_CD
     *     >. 주문 : ORD
		   >. 교환 : EXCHG
		   >. 맞교환 : DRT_EXCHG
		   >. 매장 픽업 : SHOP_PKUP
     */
    private String deliOrderDivCd;
    
    /**
     * iogsd.ord_no
     */
    private String ordNo;

    /**
     * lrdg.rtrvl_drct_god_no
     */
    private String rtrvlDrctGodNo;
    
    /**
     * OG.ITM_NO
     */
    private String itemNo;
    
    /**
     * iogsd.qty_turn
     */
    private String qtySeq;
    
    /**
     * iogsd.clm_no
     */
    private String clmNo;
    
    /**
     * LRDG.rtrvl_god_stat_cd
		ㅁ. 회수상품상태 : RTRVL_GOD_STAT
		   >. 정상입고 : NORM_WRHS
		   >. 불량판정 : BADN_JDGMNT
		---삭제   >. 수선의뢰 : REPAIR_REQEST
     */
    private String restoreStatusCd;
    
    /**
     * iogsd.ord_no
     */
    private String fpiaOr;
    
    /**
     * iogsd.qty_turn
     */
    private String seqNo;
    
    /**
     * to_char(sysdate,'YYYYMMDD')
     */
    private String wadatIst;
    
    /**
     * OG.SKU_NO
     */
    private String matnr;
    
    /**
     * 1
     */
    private String menge;
    
    /**
     * 'PC'
     */
    private String meins;
    
    /**
     *  'X'
     */
    private String ztype;
    
    /**
     * IOGSD.UNITY_PNT_USE_UNT_PRC
     */
    private String cbAmt;
    
    /**
     * 'KWR'
     */
    private String waers;
    
    /**
     * IOGSD.S_STO_NO
     */
    private String ebeln;
    
    /**
     * iogsd.s_doc_no
     */
    private String vbeln;
    
    /**
     * IOGSD.PAY_EXCHG_RT_CRNCY_UNT_PRC + IOGSD.UNITY_PNT_USE_UNT_PRC
     */
    private String payPrice;
    
    /**
     * lrdg.rtrvl_drct_grp_dgre
     */
    private String iChasu;
    
    /**
     * LRDG.BADN_REQEST_CONT
     */
    private String iOtgrpt;
    
    /**
     * nvl(lrdg.REPAIR_RCEPT_SN,'')
     */
    private String repareNo;
    
    /**
     * nvl(iogsd.clm_erp_intrlck_yn,'N')
     */
    private String claimErpYn;
    
    
    /**
     * nvl(iogsd.RESVE_RCPTFR_DCSN_YN,'N')
     */
    private String sendDlvCfmYn;
    
    /**
     * lrdg.ERP_INV_TRNSMIS_SECT_CD
     */
    private String erpTransDiv;
    
    /**
     * nvl(lrdg.rtrvl_god_prcs_compt_yn,'N') 
     */
    private String restoreEndYn;
    
    /**
     * nvl(IOGSD.DRT_EXCHG_QTY_TURN,9999)
     */
    private String orgQtySeq;
    
    /**
     * O.MBR_EMPL_NO
     */
    private String empNo;
    
    /**
     * 배송 매장
     */
    private String dlvShopId;
    
    /**
     * 출고 매장
     */
    private String dlivyShopId;
    
    /**
     * 주문유형코드
     */
    private String ordTpCd;
    
    /**
     * 회수매장ID (매장출고 이면서 X30004이 아니면 자기매장)
     */
    private String rtrvlShopId;
    
   /**
     * ERP 연동 대상여부
     */
	private String intrlckTgtYn;

    /**
     * 판매매장ID (A345,B04E..) K2
     */
    
     private String saleShopCd;  
    

     /**
      * 
		ㅁ. 회수 상품 상태 상세
		   >. 정상 : NRMLT
		   >. 소각 : INCNR
		   >. 자가 소비 : OOWN_CSM
      */
     private String rtrvlGodStatDetailCd;
     
     
     /* 회수완료날짜,클레임구분 
 	 * K2 
 	 */
      
  	 private String rtrvlComptDt;
  	 
  	 private String clmTpCd;
  	 
    /**
     * P포인트 사용금액
     */
    private String namt;


    /**
     *	회수처리자 (UDTER_ID)
     */
    private String rtrvlProcrId;

    /**
     * 센터반품즉시처리여부
     */
    private String cntrRtrvlImdtlProcYn;

    /**
     * 가반품여부
     */
    private String virtlRtgodYn;
    
    
    /**
     * 분실 보상금
     */
    private String zamnt;
    

}
