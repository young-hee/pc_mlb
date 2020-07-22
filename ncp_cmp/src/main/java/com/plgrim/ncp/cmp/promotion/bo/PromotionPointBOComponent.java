package com.plgrim.ncp.cmp.promotion.bo;

import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;

public interface PromotionPointBOComponent extends PromotionBOComponent{
	
	/**
     * 프로모션 즉시포인트 사용여부 적용 프로시져 실행
     * 
     * @param promotionBoDTO
     * @throws Exception
     */
    public void updateGodImdtlDcProcedure(PromotionBoDTO promotionBoDTO) throws Exception;
    
}
