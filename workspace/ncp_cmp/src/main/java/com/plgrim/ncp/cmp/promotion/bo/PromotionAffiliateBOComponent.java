package com.plgrim.ncp.cmp.promotion.bo;

import java.util.List;

import com.plgrim.ncp.biz.promotion.data.CardPartnershipBoDTO;
import com.plgrim.ncp.biz.promotion.result.CardPartnershipBoResult;

public interface PromotionAffiliateBOComponent extends PromotionBOComponent{

	/**
	 * 카드 제휴사 등록
	 * 
	 * @param cardPartnershipBoDTO
	 * @return
	 * @throws Exception
	 */
    public String insertCardPartnership(CardPartnershipBoDTO cardPartnershipBoDTO) throws Exception;

    /**
     * 카드 제휴사 수정
     * 
     * @param cardPartnershipBoDTO
     * @return
     * @throws Exception
     */
    public int updateCardPartnership(CardPartnershipBoDTO cardPartnershipBoDTO) throws Exception;
    
    /**
     * 카드 제휴사 상태 수정
     * 
     * @param cardPartnershipBoDTO
     * @return
     * @throws Exception
     */
    public int updateCardPartnershipStatus(CardPartnershipBoDTO cardPartnershipBoDTO) throws Exception;

    /**
	 * 카드 제휴사 목록 조회
	 *  
	 * @param cardPartnershipBoDTO
	 * @return
	 * @throws Exception
	 */
	public List<CardPartnershipBoResult> selectCardPartnershipList(CardPartnershipBoDTO cardPartnershipBoDTO) throws Exception;
}
