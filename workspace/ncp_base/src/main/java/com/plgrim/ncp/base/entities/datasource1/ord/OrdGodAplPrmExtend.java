package com.plgrim.ncp.base.entities.datasource1.ord;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrdGodAplPrmExtend extends OrdGodAplPrm {

    /**
     *
     */
    private static final long serialVersionUID = -7066581657194230206L;

    private String godNo;

    private String godNm;

    private String gftGodNo;

    private String itmNo;

    private String prmNm;

    private String godAplYn;

    private List<OrdGodAplPrmExtend> ordGodList;

    private String ordGftPchStdrCd;

    private String ordGftAplCndQty;

    private String ordGftAplCndAmt;

    private String gftChoisePsbQty;

    private String prmDtlTpCdNm;

    private int ordQty;
    
	/**
	 * 주문 상품 순번 - original	 
	 */
	private java.lang.Integer newOrdGodTurn;
    
	private long dmstcDlvCstPlcSn;
	
	private String saleShopCd;

	/**
	 * 업체 코드
	 */
	private String comId;

    private String aplAmtEx;
}
