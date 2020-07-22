package com.plgrim.ncp.cmp.promotion.bo;

import com.plgrim.ncp.base.entities.datasource1.prm.PrmOfferGft;
import com.plgrim.ncp.biz.promotion.data.PromotionApplyGoodBoDTO;

public interface PromotionGiftBOComponent extends PromotionBOComponent{

	/**
     * 프로모션 제공 사은품 등록 insert
     *
     * @param prmOfferGft
     * @return
     * @throws Exception
     */
    public int insertPrmOfferGft(PrmOfferGft prmOfferGft) throws Exception;
    
    /**
     * 프로모션 제공 사은품 삭제 delete
     *
     * @param prmOfferGft
     * @return
     * @throws Exception
     */
    public int deleteOfferGift(PromotionApplyGoodBoDTO promotionApplyGoodBoDTO) throws Exception;
    
}
