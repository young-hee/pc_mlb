package com.plgrim.ncp.biz.promotion.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.biz.promotion.data.CardPartnershipBoDTO;
import com.plgrim.ncp.biz.promotion.repository.CardPartnershipDetailRepository;

/**
 * 카드 제휴사 상세 Service
 * @author js
 *
 */
@Service
@Slf4j
public class CardPartnershipDetailService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	@Autowired
	private CardPartnershipDetailRepository cardPartnershipDetailRepository;

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
	 * 카드 제휴사 등록
	 * @param cardPartnershipBoDTO
	 * @throws Exception
	 */
	public void insertCardPartnershipDetail(CardPartnershipBoDTO cardPartnershipBoDTO) throws Exception {
		cardPartnershipDetailRepository.insertCardPartnership(cardPartnershipBoDTO.getPrm());
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
