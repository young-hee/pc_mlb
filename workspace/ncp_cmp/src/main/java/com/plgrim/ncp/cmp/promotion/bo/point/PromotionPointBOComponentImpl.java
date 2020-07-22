package com.plgrim.ncp.cmp.promotion.bo.point;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.biz.promotion.service.PromotionService;
import com.plgrim.ncp.cmp.promotion.bo.PromotionBOComponentImpl;
import com.plgrim.ncp.cmp.promotion.bo.PromotionPointBOComponent;

@Transactional(value = "transactionManager")
@Component
public class PromotionPointBOComponentImpl extends PromotionBOComponentImpl implements PromotionPointBOComponent {

	@Autowired
    private PromotionService promotionService;
	
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.PromotionComponent#updateGodImdtlDcProcedure(com.plgrim.ncp.biz.promotion.data.PromotionBoDTO)
     */
    @Override
    public void updateGodImdtlDcProcedure(PromotionBoDTO promotionBoDTO) throws Exception {
        promotionService.updateGodImdtlDcProcedure(promotionBoDTO);
    }
    
}
