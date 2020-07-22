package com.plgrim.ncp.biz.claim.result;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.biz.claim.result.ClmDetailListResult;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClmDetailResult extends AbstractResult {
	
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 4205067120338184194L;
    
    /*
     * 클레임 배송지순번
     */
    private String dlvPcupspTurn;
    private String dlvPcupspTurnDlivy;
    private String addrseAddr;
    private String addrseAddrDlivy;
    private String addrseMobil;
    private String addrseNm;
    private String totalDlvCst;
    private String cnclDlvCst;
    private String cnclDlvCstEx;
    private String rtgodDlvCst;
    private String rtgodDlvCstEx;
    private String dlvMnNm;
    private String cvnstorHdryAprvNo;
    private String exchgDlvCst;
    private String partmalSectCd;
    private String dlvMemo;

    /*
     * 클레임 상품리스트 정보
     */
    List<ClmDetailListResult> clmDetailList;    
	
	
}
