package com.plgrim.ncp.biz.promotion.service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.biz.promotion.data.CardPartnershipBoDTO;
import com.plgrim.ncp.biz.promotion.repository.CardPartnershipRepository;
import com.plgrim.ncp.biz.promotion.result.CardPartnershipBoResult;

/**
 * 카드 제휴사 Service
 * @author js
 *
 */
@Slf4j
@Service
public class CardPartnershipService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	@Autowired
	private CardPartnershipRepository cardPartnershipRepository;

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
	 * 카드 제휴사 목록 조회
	 * @param cardPartnershipBoDTO
	 * @return
	 * @throws Exception
	 */
	public List<CardPartnershipBoResult> selectCardPartnershipList(CardPartnershipBoDTO cardPartnershipBoDTO) throws Exception {
		List<CardPartnershipBoResult> resultList = cardPartnershipRepository.selectCardPartnershipList(cardPartnershipBoDTO);
		return resultList;
	}

	/**
	 * 카드 제휴사 등록
	 * @param cardPartnershipBoDTO
	 * @throws Exception
	 */
	public void insertCardPartnership(CardPartnershipBoDTO cardPartnershipBoDTO) throws Exception {
		cardPartnershipRepository.insertCardPartnership(cardPartnershipBoDTO.getPrm());
	}

	/**
	 * 카드 제휴사 수정
	 * @param cardPartnershipBoDTO
	 * @return
	 * @throws Exception
	 */
	public int updateCardPartnership(CardPartnershipBoDTO cardPartnershipBoDTO) throws Exception {
		int result = cardPartnershipRepository.updateCardPartnership(cardPartnershipBoDTO.getPrm());
		return result;
	}
	
	/**
	 * 카드 제휴사 상태 수정
	 * @param cardPartnershipBoDTO
	 * @return
	 * @throws Exception
	 */
	public int updateCardPartnershipStatus(CardPartnershipBoDTO cardPartnershipBoDTO) throws Exception {
		int result = cardPartnershipRepository.updateCardPartnershipStatus(cardPartnershipBoDTO);
		return result;
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
