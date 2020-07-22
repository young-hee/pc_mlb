/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jw.vito.kim
 * @since       2015. 4. 30
 */
package com.plgrim.ncp.cmp.promotion.bo.affiliate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplTgt;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionAplTarget;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionSeparator;
import com.plgrim.ncp.biz.promotion.data.CardPartnershipBoDTO;
import com.plgrim.ncp.biz.promotion.repository.PromotionTargetRepository;
import com.plgrim.ncp.biz.promotion.result.CardPartnershipBoResult;
import com.plgrim.ncp.biz.promotion.service.CardPartnershipService;
import com.plgrim.ncp.cmp.promotion.bo.PromotionAffiliateBOComponent;
import com.plgrim.ncp.cmp.promotion.bo.PromotionBOComponentImpl;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.enums.DatabaseType;

@Transactional(value = "transactionManager")
@Component
public class PromotionAffiliateBOComponentImpl extends PromotionBOComponentImpl implements PromotionAffiliateBOComponent {

    /*
     * --------------------------------------------------------------------- Instance fields.
     * ---------------------------------------------------------------------
     */

    @Autowired
    private CardPartnershipService cardPartnershipService;

    @Autowired
    private PromotionTargetRepository promotionTargetRepository;
    
    @Autowired
    @Qualifier("sessionTemplate1")
    private SqlSession sqlSession1;

    /*
     * --------------------------------------------------------------------- Constructors.
     * ---------------------------------------------------------------------
     */

    /*
     * --------------------------------------------------------------------- public & interface method.
     * ---------------------------------------------------------------------
     */

    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.CardPartnershipCommandComponent#insertCardPartnership(com.plgrim.ncp.biz.promotion.data.CardPartnershipBoDTO)
     */
    @Override
    public String insertCardPartnership(CardPartnershipBoDTO cardPartnershipBoDTO) throws Exception {

        // idGenService.generateDBSequence(session, "SQ_PRM", dbType)

        String prmNoGen = getIdGenService().generateDBNumber(sqlSession1, "SQ_PRM", "PR", DatabaseType.ORACLE);
        String prmNo = String.valueOf(prmNoGen);
        
        cardPartnershipBoDTO.getPrm().setPrmNo(prmNo);

        cardPartnershipService.insertCardPartnership(cardPartnershipBoDTO);

        // 프로모션 적용대상 언어 코드 저장
        String prmLangCd = cardPartnershipBoDTO.getPrmLangCd();
        
        List<String> langList = new ArrayList<String>();

        if ( StringService.isNotEmpty(prmLangCd) ) {
            langList = Arrays.asList(prmLangCd.split(PromotionSeparator.DELIMITER.toString()));
        }

        for (String langCd : langList) {
            PrmAplTgt pat = new PrmAplTgt();

            pat.setLangCd(langCd);
            pat.setPrmTgtTpCd(PromotionAplTarget.LANG.toString());

            pat.setPrmNo(prmNo);
            pat.setRegtrId(cardPartnershipBoDTO.getPrm().getRegtrId());
            pat.setUdterId(cardPartnershipBoDTO.getPrm().getUdterId());
            
            promotionTargetRepository.insertPromotionApplyTarget(sqlSession1, pat);
        }
        
        return prmNo;
    }

    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.CardPartnershipCommandComponent#updateCardPartnership(com.plgrim.ncp.biz.promotion.data.CardPartnershipBoDTO)
     */
    @Override
    public int updateCardPartnership(CardPartnershipBoDTO cardPartnershipBoDTO) throws Exception {
        int result = cardPartnershipService.updateCardPartnership(cardPartnershipBoDTO);
        
        String prmNo = cardPartnershipBoDTO.getPrm().getPrmNo();

        // 프로모션 적용대상 언어 코드 저장(삭제 후 저장)
        PrmAplTgt prmAplTgt = new PrmAplTgt();
        prmAplTgt.setPrmNo(prmNo);
        promotionTargetRepository.deletePromotionApplyTarget(prmAplTgt);

        String prmLangCd = cardPartnershipBoDTO.getPrmLangCd();
        
        List<String> langList = new ArrayList<String>();

        if ( StringService.isNotEmpty(prmLangCd) ) {
            langList = Arrays.asList(prmLangCd.split(PromotionSeparator.DELIMITER.toString()));
        }

        for (String langCd : langList) {
            PrmAplTgt pat = new PrmAplTgt();

            pat.setLangCd(langCd);
            pat.setPrmTgtTpCd(PromotionAplTarget.LANG.toString());

            pat.setPrmNo(prmNo);
            pat.setRegtrId(cardPartnershipBoDTO.getPrm().getRegtrId());
            pat.setUdterId(cardPartnershipBoDTO.getPrm().getUdterId());
            
            promotionTargetRepository.insertPromotionApplyTarget(sqlSession1, pat);
        }
        
        return result;
    }
    
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.CardPartnershipCommandComponent#updateCardPartnershipStatus(com.plgrim.ncp.biz.promotion.data.CardPartnershipBoDTO)
     */
    @Override
    public int updateCardPartnershipStatus(CardPartnershipBoDTO cardPartnershipBoDTO) throws Exception {
        int result = cardPartnershipService.updateCardPartnershipStatus(cardPartnershipBoDTO);
        return result;
    }

    @Override
	public List<CardPartnershipBoResult> selectCardPartnershipList(CardPartnershipBoDTO cardPartnershipBoDTO) throws Exception {
		List<CardPartnershipBoResult> resultList = cardPartnershipService.selectCardPartnershipList(cardPartnershipBoDTO);
		return resultList;
	}
    
     /*
     * --------------------------------------------------------------------- private method.
     * ---------------------------------------------------------------------
     */

}
