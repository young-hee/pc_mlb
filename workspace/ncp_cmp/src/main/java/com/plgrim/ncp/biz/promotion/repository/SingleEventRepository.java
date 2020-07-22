/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      th86.yang
 * @since       2015. 11. 16       
 */
package com.plgrim.ncp.biz.promotion.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPrize;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPrizeFreeGift;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtStmp;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.base.repository.evt.EvtRepository;
import com.plgrim.ncp.biz.promotion.data.EventBoDTO;
import com.plgrim.ncp.biz.promotion.data.EventFreeGiftTurnExtendsFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventSearchFoDTO;
import com.plgrim.ncp.biz.promotion.result.EventBoResult;
import com.plgrim.ncp.framework.enums.DatabaseType;

/**
 * 이벤트 Repository
 * @author js
 *
 */
@Repository
public class SingleEventRepository extends EvtRepository{

	

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	@Autowired
    @Qualifier("sessionTemplate1")
    private SqlSession sqlSession1;
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
	 * 이벤트 응모 유효성 체크
	 *
	 * @param eventBoDTO
	 * @return
	 * @throws Exception
	 */
	public EventBoResult validEventApplcn(EventBoDTO eventBoDTO) throws Exception {
		EventBoResult result = getSession1().selectOne("com.plgrim.ncp.biz.promotion.single.validEventApplcn", eventBoDTO);
		return result;
	}

	/**
	 * 응모자 등록
	 * @param evtApplcn
	 * @throws Exception
	 */
	public int insertEventApplcn(EvtApplcn evtApplcn) throws Exception {
		long evtNoGen = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
		evtApplcn.setEvtPartcptnSn(evtNoGen);
		int insCnt = getSession1().insert("com.plgrim.ncp.biz.promotion.single.insertEvtApplcn", evtApplcn);
		return insCnt;
	}

	/**
	 * 이벤트 스탬프 등록
	 *
	 * @return
	 */
	public int insertEvtStmp(EvtStmp evtStmp) throws Exception {
		int result = getSession1().insert("com.plgrim.ncp.biz.promotion.single.insertEvtStmp", evtStmp);
		return result;
	}

	/**
	 * 당첨자 등록
	 * @param evtPrize
	 * @throws Exception
	 */
	public int insertEventPrize(EvtPrize evtPrize) throws Exception {

		Map<String,Object>conditions = new HashMap<String,Object>();
		conditions.put("EVT_PARTCPTN_SN", evtPrize.getEvtPartcptnSn());
		int turn = getIdGenService().generateDBOrder(sqlSession1, "EVT_PRIZE", "PRIZE_TURN", conditions,  DatabaseType.ORACLE);
		evtPrize.setPrizeTurn(turn);

		int cnt = getSession1().insert("com.plgrim.ncp.biz.promotion.single.insertEvtPrize", evtPrize);
		return cnt;
	}

	/**
	 * 당첨자 경품 등록
	 * @param evtPrizeFreeGift
	 * @throws Exception
	 */
	public int insertEventPrizeFreeGift(EvtPrizeFreeGift evtPrizeFreeGift) throws Exception {
		int cnt = getSession1().insert("com.plgrim.ncp.biz.promotion.single.insertEvtPrizeFreeGift", evtPrizeFreeGift);
		return cnt;
	}

	/**
	 * 화면에 랜덤 노출 이벤트 조회
	 * - PV 이벤트
	 * 
	 * @param eventSearchFoDTO
	 * @return
	 */
	public String selectSpecialEventForPvEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.single.selectSpecialEventForPvEvent", eventSearchFoDTO);
	}
	
	/**
	 * PV 이벤트 당첨 경품 조회
	 * 
	 * @param eventSearchFoDTO
	 * @return
	 */
	public EventFreeGiftTurnExtendsFoDTO selectPrizeInfoForPvEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.single.selectPrizeInfoForPvEvent", eventSearchFoDTO);
	}
	
	/**
	 * PV 이벤트 경품 목록 조회
	 * 
	 * @param eventSearchFoDTO
	 * @return
	 */
	public List<EventFreeGiftTurnExtendsFoDTO> selectFreeGiftTurnListForPvEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.promotion.single.selectFreeGiftTurnListForPvEvent", eventSearchFoDTO);
	}
	
	/**
	 * PV 이벤트 당첨 순번 조회
	 * - 중복 방지를 위한 처리
	 * 
	 * @param eventSearchFoDTO
	 * @return String
	 */
	public String selectNextvalForPvEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.single.selectNextvalForPvEvent", eventSearchFoDTO);
	}

	/**
	 * 연말정산 Happy 포인트 이벤트 포인트 적립 여부 조회
	 * 
	 * @param eventSearchFoDTO
	 * @return int
	 */
	public int selectValidForHappyPoint(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.single.selectValidForHappyPoint", eventSearchFoDTO);
	}
	
	/**
	 * 룰렛이벤트 구매확정 응모권 조회
	 * 
	 * @param eventBoDTO
	 * @return int
	 */
	public int selectPchDcsnCnt(EventBoDTO eventBoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.single.selectPchDcsnCnt", eventBoDTO);
	}
	
	/**
	 * 룰렛이벤트 포토리뷰 작성 응모권 조회
	 * 
	 * @param eventBoDTO
	 * @return int
	 */
	public int selectAtchFileCnt(EventBoDTO eventBoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.single.selectAtchFileCnt", eventBoDTO);
	}
	
	/**
	 * 룰렛이벤트 이벤트 경품 목록 조회
	 * 
	 * @param eventSearchFoDTO
	 * @return
	 */
	public List<EventFreeGiftTurnExtendsFoDTO> selectFreeGiftTurnListForRouletteEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.promotion.single.selectFreeGiftTurnListForRouletteEvent", eventSearchFoDTO);
	}
	
	/**
	 * 릴레이기프트 
	 * 
	 * @param eventSearchFoDTO
	 * @return
	 */
	public int selectRelayGiftDoubleWinningCheck(EvtApplcn evtApplcn) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectRelayGiftDoubleWinningCheck", evtApplcn);
	}
	
	/**
	 * 이벤트 참여 조회
	 * 
	 * @param eventSearchFoDTO
	 * @return
	 */
	public int selectDoubleWinningCheck(EvtApplcn evtApplcn) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.single.selectDoubleWinningCheck", evtApplcn);
	}
	
	/**
	 * 쿠폰 발급일 조회
	 * 
	 * @param eventSearchFoDTO
	 * @return
	 */
	public PrmCpn selectPrmCpn(EventBoDTO eventBoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.single.selectPrmCpn", eventBoDTO);
	}
}
