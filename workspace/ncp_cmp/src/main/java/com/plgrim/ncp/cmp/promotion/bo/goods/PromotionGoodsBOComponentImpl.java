package com.plgrim.ncp.cmp.promotion.bo.goods;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.cmp.promotion.bo.PromotionBOComponentImpl;
import com.plgrim.ncp.cmp.promotion.bo.PromotionGoodsBOComponent;

@Transactional(value = "transactionManager")
@Component
public class PromotionGoodsBOComponentImpl extends PromotionBOComponentImpl implements PromotionGoodsBOComponent {

}
