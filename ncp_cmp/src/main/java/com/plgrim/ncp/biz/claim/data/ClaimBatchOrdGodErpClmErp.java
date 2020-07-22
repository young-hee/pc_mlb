package com.plgrim.ncp.biz.claim.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;


/**
 * 클레임 배치 ord_god_erp clm_erp.. 컬럼 update 하기 위한 DTO
 * @author user
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClaimBatchOrdGodErpClmErp extends AbstractDTO {/**
	 * 
	 */
    private static final long serialVersionUID = 1067107658907965735L;

    /**
     * clm_erp_intrlck_yn
     */
    private String clmErpYn;
    
    /**
     * clm_prcs_err_cont
     */
    private String clmPrcsErrCont;
    
    /**
     * ord_no
     */
    private String ordNo;

    
    /**
     * qty_turn
     */
    private String qtyTurn;
    
    /**
     * rtgod_rcptfr_no
     */
    private String rtgodRcptfrNo;
    
    /**
     * 2017-05-29 스플릿 3차로 추가 반품 납품 문서번호
     * 매장반품시 사용
     */
    private String dfgDocNo;
}
