package com.plgrim.ncp.biz.promotion.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.biz.promotion.data.CardPartnershipBoDTO;
import com.plgrim.ncp.biz.promotion.result.CardPartnershipBoResult;

/**
 * 카드 제휴사 상세 Repository
 * @author js
 *
 */
@Repository
public class CardPartnershipDetailRepository extends AbstractRepository {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 카드 제휴사 목록
	 * @param cardPartnershipBoDTO
	 * @return
	 * @throws Exception
	 */
	public List<CardPartnershipBoResult> selectCardPartnershipList(CardPartnershipBoDTO cardPartnershipBoDTO) throws Exception {
		List<CardPartnershipBoResult> resultList = getSession1().selectList("com.plgrim.ncp.biz.promotion.selectCardPartnershipList", cardPartnershipBoDTO);
		return resultList;
	}

	/**
	 * 카드 제휴사 등록
	 * @param prm
	 * @throws Exception
	 */
	public void insertCardPartnership(Prm prm) throws Exception {
		getSession1().insert("com.plgrim.ncp.base.insertCardPartnership", prm);
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
