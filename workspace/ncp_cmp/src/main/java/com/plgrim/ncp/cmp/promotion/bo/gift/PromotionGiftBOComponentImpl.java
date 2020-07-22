package com.plgrim.ncp.cmp.promotion.bo.gift;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmOfferGft;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionSeparator;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionType;
import com.plgrim.ncp.biz.goods.service.GoodsService;
import com.plgrim.ncp.biz.promotion.data.PrmOfferGftExtend;
import com.plgrim.ncp.biz.promotion.data.PromotionApplyGoodBoDTO;
import com.plgrim.ncp.biz.promotion.exception.PromotionAlreadyExistApplyTargetException;
import com.plgrim.ncp.biz.promotion.exception.PromotionNotGiftException;
import com.plgrim.ncp.biz.promotion.service.PromotionDetailService;
import com.plgrim.ncp.cmp.promotion.bo.PromotionGiftBOComponent;
import com.plgrim.ncp.cmp.promotion.bo.PromotionBOComponentImpl;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;

@Transactional(value = "transactionManager")
@Component
public class PromotionGiftBOComponentImpl extends PromotionBOComponentImpl implements PromotionGiftBOComponent {

	/*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    @Autowired
    private PromotionDetailService promotionDetailService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    @Qualifier("sessionTemplate1")
    protected SqlSession sqlSession1;

    @Autowired
    private IdGenService idGenService;
    
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.GiftPromotionComponent#insertPrmOfferGft(com.plgrim.ncp.base.entities.datasource1.prm.PrmOfferGft)
	 */
    @Override
    public int insertPrmOfferGft(PrmOfferGft prmOfferGft) throws Exception {

        String lang = "KOR";

        int cnt = 0;
        String prmNo = prmOfferGft.getPrmNo();
        for (String godNo : prmOfferGft.getGodNo().split(PromotionSeparator.DELIMITER.toString())) {

            God godEt = goodsService.getGoods(godNo);

            String godTpCd = godEt.getGodTpCd();

            if (!godTpCd.contains(PromotionType.GFT.toString())) {
                throw new PromotionNotGiftException(null);
            }

            PrmOfferGft pog = new PrmOfferGft();
            pog.setGodNo(godNo);

            // validation 추가
            PrmOfferGftExtend validResult = promotionDetailService.selectValidPrmOfferGft(prmOfferGft);

            if (validResult != null && validResult.getOfferGiftCount() > 0) {
                String gftCodeNm = CodeUtil.getCode(lang, PromotionType.GFT.toString()).getCdNm();
                String[] param = { gftCodeNm };
                throw new PromotionAlreadyExistApplyTargetException(param);
            }

            Map<String, Object> conditions = Maps.newHashMap();
            conditions.put("prm_no", prmNo);
            int turn = idGenService.generateDBOrder(sqlSession1, "PRM_OFFER_GFT", "OFFER_GFT_TURN", conditions, DatabaseType.ORACLE);

            pog.setPrmNo(prmNo);
            pog.setOfferGftTurn(turn);
            pog.setRegtrId(prmOfferGft.getRegtrId());
            pog.setUdterId(prmOfferGft.getUdterId());

            cnt += promotionDetailService.insertPrmOfferGft(pog);
        }

        return cnt;
    }

    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.GiftPromotionComponent#deleteOfferGift(com.plgrim.ncp.biz.promotion.data.PromotionApplyGoodBoDTO)
     */
    @Override
    public int deleteOfferGift(PromotionApplyGoodBoDTO promotionApplyGoodBoDTO) throws Exception {

        int result = 0;
        String prmNo = promotionApplyGoodBoDTO.getPrmOfferGft().getPrmNo();
        for (String offerGftTurn : promotionApplyGoodBoDTO.getOfferGftTurns().split(PromotionSeparator.DELIMITER.toString())) {
            PrmOfferGft pog = new PrmOfferGft();

            pog.setPrmNo(prmNo);
            pog.setOfferGftTurn(Integer.parseInt(offerGftTurn));

            result += promotionDetailService.deletePrmOfferGft(pog);
        }
        return result;
    }

}
