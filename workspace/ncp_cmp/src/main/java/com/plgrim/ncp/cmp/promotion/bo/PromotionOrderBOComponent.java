package com.plgrim.ncp.cmp.promotion.bo;

import com.plgrim.ncp.biz.promotion.data.PromotionApplyGoodBoDTO;

public interface PromotionOrderBOComponent extends PromotionBOComponent{

	/**
     * 적용상품 추가 insert - 적용대상상품 - 교차할인 적용상품
     *
     * @param promotionBoDTO
     * @return
     * @throws Exception
     */
    public int insertPromotionApplyGoods(PromotionApplyGoodBoDTO promotionApplyGoodBoDTO) throws Exception;
    
    /**
     * 교차할인 적용상품 삭제 delete
     *
     * @param promotionBoDTO
     * @return
     * @throws Exception
     */
    public int deletePrmCrsDcAplGod(PromotionApplyGoodBoDTO promotionApplyGoodBoDTO) throws Exception;
    
    /**
     * 적용상품 삭제 delete - 적용대상상품 - 교차할인 적용상품
     *
     * @param promotionBoDTO
     * @return
     * @throws Exception
     */
    public int deletePromotionTargetGoods(PromotionApplyGoodBoDTO promotionApplyGoodBoDTO) throws Exception;
    
}
