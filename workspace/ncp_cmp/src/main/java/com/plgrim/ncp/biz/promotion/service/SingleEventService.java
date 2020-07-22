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
 * @since       2015. 11. 13       
 */
package com.plgrim.ncp.biz.promotion.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftInfo;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPrize;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPrizeFreeGift;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtStmp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrGrd;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.base.enums.EventEnum;
import com.plgrim.ncp.base.enums.EventEnum.EventApplcnMbrSect;
import com.plgrim.ncp.base.enums.EventEnum.EventResultCode;
import com.plgrim.ncp.base.repository.evt.EvtApplcnRepository;
import com.plgrim.ncp.biz.promotion.data.EventBoDTO;
import com.plgrim.ncp.biz.promotion.data.EventExtendsFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventFreeGiftTurnExtendsFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventSearchFoDTO;
import com.plgrim.ncp.biz.promotion.repository.EventRepository;
import com.plgrim.ncp.biz.promotion.repository.SingleEventRepository;
import com.plgrim.ncp.biz.promotion.result.EventBoResult;
import com.plgrim.ncp.framework.enums.DatabaseType;

/**
 * 이벤트 Service
 * 
 * @author js
 *
 */

@Service
public class SingleEventService extends AbstractService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private SingleEventRepository singleEventRepository;

	@Autowired
	private EvtApplcnRepository evtApplcnRepository;

	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;
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
	 * 이벤트 응모하기
	 *
	 * @param eventBoDTO
	 * @return
	 * @throws Exception
	 */
	public int insertEvtApplcn(EventBoDTO eventBoDTO) throws Exception {

		// Step 1. 응모정보 체크
		String mbrNo = eventBoDTO.getMbrNo();
		EventBoResult ebr = singleEventRepository.validEventApplcn(eventBoDTO);
		eventBoDTO.setEventBoResult(ebr);

		Integer freeGiftTurn = ebr.getEvtFreeGiftInfo() != null ? ebr.getEvtFreeGiftInfo().getFreeGiftTurn() : null;
		String freeGiftKndCd = ebr.getEvtFreeGiftInfo() != null ? ebr.getEvtFreeGiftInfo().getFreeGiftKndCd() : null;

		String evtNo = ebr.getEvt().getEvtNo();
		String evtTpCd = ebr.getEvt().getEvtTpCd();

		int applcnCnt = 0;
		boolean procCheck = true; // 가능여부 체크

		if (EventEnum.YES.toString().equals(ebr.getApplcnCheck())) {

			Integer stmpCount = null;
			Integer atendDaycnt = null;

			// 스탬프
			if (EventEnum.EventType.STMP.toString().equals(evtTpCd) && ebr.getEvtFreeGiftInfo() != null) {
				stmpCount = ebr.getApplcnCount() + 1;
			} // 출석체크
			else if (EventEnum.EventType.ATEND_CHK.toString().equals(evtTpCd) && ebr.getEvtFreeGiftInfo() != null) {
				atendDaycnt = ebr.getApplcnCount() + 1;
			} // 전체추첨
			else if (EventEnum.EventType.ALL_DRWT.toString().equals(evtTpCd)) {
				freeGiftTurn = null;
			} else if (EventEnum.EventType.ALL_PRIZE.toString().equals(evtTpCd)) {
				if (freeGiftTurn == null) {
					procCheck = false;
				}
			} else if (EventEnum.EventType.TM_CPN.toString().equals(evtTpCd)) {
				if (freeGiftTurn == null || (EventEnum.EventFreeGiftKind.COUPON.toString().equals(freeGiftKndCd)
						&& EventEnum.NO.toString().equals(ebr.getPrmAplPdCheck()))) {
					procCheck = false;
				}
			} else {
				procCheck = false;
			}

			if (procCheck) {
				// 1. 응모
				EvtApplcn evtApplcn = new EvtApplcn();
				evtApplcn.setEvtNo(evtNo);
				evtApplcn.setApplcnMbrSectCd(EventApplcnMbrSect.MBR.toString());
				evtApplcn.setMbrNo(ebr.getMbrNo());
				evtApplcn.setApplcnDt(new Date()); // 응모일시

				evtApplcn.setStmpCount(stmpCount);
				evtApplcn.setAtendDaycnt(atendDaycnt);
				evtApplcn.setFreeGiftTurn(freeGiftTurn);
				evtApplcn.setRegtrId(mbrNo);
				evtApplcn.setUdterId(mbrNo);

				eventBoDTO.setEvtApplcn(evtApplcn);

				applcnCnt = singleEventRepository.insertEventApplcn(eventBoDTO.getEvtApplcn());
			}
			// 이벤트 참여 방법 insert
			if (applcnCnt > 0) {

				EvtApplcn evtApplcn = eventBoDTO.getEvtApplcn();

				// 스탬프
				if (EventEnum.EventType.STMP.toString().equals(evtTpCd)) {

					EvtStmp evtStmp = eventBoDTO.getEvtStmp();

					evtStmp.setEvtNo(evtApplcn.getEvtNo());
					evtStmp.setFreeGiftTurn(evtApplcn.getFreeGiftTurn());
					evtStmp.setMbrNo(evtApplcn.getMbrNo());
					evtStmp.setStmpDt(evtApplcn.getApplcnDt());
					evtStmp.setEvtPartcptnSn(evtApplcn.getEvtPartcptnSn());

					evtStmp.setRegtrId(mbrNo);
					evtStmp.setUdterId(mbrNo);
					singleEventRepository.insertEvtStmp(evtStmp);
				}
			}
		} else {
			String applcnCountSectCd = ebr.getEvt().getApplcnCountSectCd();
			ebr.setResultCode(EventEnum.EventResultCode.NO_MORE_CHANCE.toString());
			ebr.setResultAdtnCode(applcnCountSectCd);
		}
		eventBoDTO.setEventBoResult(ebr);

		return applcnCnt;
	}

	/**
	 * 이벤트 참여 유효성 체크
	 *
	 * @param eventBoDTO
	 * @return
	 * @throws Exception
	 */
	public EventBoResult validEventPartcptn(EventBoDTO eventBoDTO) throws Exception {

		EventBoResult result = eventRepository.validEventPartcptn(eventBoDTO);

		boolean checkResult = false;
		if (result != null) {
			if (EventEnum.YES.toString().equals(result.getApplcnDateCheck())
					&& EventEnum.YES.toString().equals(result.getDeviceCheck())
					&& EventEnum.YES.toString().equals(result.getMemberTypeCheck())) {
				checkResult = true;
				eventBoDTO.setEvtTpCd(result.getEvt().getEvtTpCd());
			}
		} else {
			result = new EventBoResult();
		}
		result.setCheckResult(checkResult);

		return result;
	}

	/**
	 * 당첨자 등록
	 * 
	 * @param eventBoDTO
	 * @throws Exception
	 */
	public int insertEventPrize(EventBoDTO eventBoDTO) throws Exception {

		String evtNo = eventBoDTO.getEvtApplcn().getEvtNo();
		String mbrNo = eventBoDTO.getMbrNo();

		long evtPartcptnSn = eventBoDTO.getEvtApplcn().getEvtPartcptnSn();

		EvtPrize evtPrize = new EvtPrize();
		evtPrize.setEvtPartcptnSn(evtPartcptnSn);
		evtPrize.setEvtNo(evtNo);
		evtPrize.setPrizeDt(new Date());
		evtPrize.setRegtrId(mbrNo);
		evtPrize.setUdterId(mbrNo);
		int cnt = singleEventRepository.insertEventPrize(evtPrize);

		if (cnt > 0) {
			EvtPrizeFreeGift evtPrizeFreeGift = new EvtPrizeFreeGift();
			evtPrizeFreeGift.setEvtPartcptnSn(evtPartcptnSn);
			evtPrizeFreeGift.setPrizeTurn(evtPrize.getPrizeTurn());
			evtPrizeFreeGift.setEvtNo(evtNo);
			evtPrizeFreeGift.setFreeGiftTurn(eventBoDTO.getEvtApplcn().getFreeGiftTurn());
			evtPrizeFreeGift.setRegtrId(mbrNo);
			evtPrizeFreeGift.setUdterId(mbrNo);
			singleEventRepository.insertEventPrizeFreeGift(evtPrizeFreeGift);
		}

		return cnt;
	}

	public Map<String, String> insertEventfcfs(EventSearchFoDTO eventSearchFoDTO) {
		Map<String, String> result = new HashMap<String, String>();
		String lastDay = "N";
		try {
			// 이벤트 기준 정보 조회
			EventExtendsFoDTO eventExtendsFoDTO = eventRepository.selectfcfsEvent(eventSearchFoDTO);

			if (eventExtendsFoDTO == null) {
				result.put("resultCode", "N");
				result.put("resultMsg", "이벤트 기간이 아닙니다.");
				return result;
			}

			if (eventExtendsFoDTO.getAtendDaycnt() == eventExtendsFoDTO.getLastDaycnt()) {
				lastDay = "Y";
			}

			// 해당일 이벤트 마감체크
			eventSearchFoDTO.setAtendDaycnt(eventExtendsFoDTO.getAtendDaycnt());
			
			int prizeRank = eventRepository.selectfcfsRankingInformation(eventSearchFoDTO);
			if (prizeRank > 100) {	
				eventRepository.deletefcfsWinner(eventSearchFoDTO);
				if ("Y".equals(lastDay)) {
					result.put("resultCode", "CLOSE");
					result.put("resultMsg", "선착순 마감 되었습니다.");
				} else {
					result.put("resultCode", "ENTRY_AGAIN");
					result.put("resultMsg", "선착순 마감 되었습니다. 오전 10시부터 다시 응모해주세요.");
				}
				return result;
			} else {
			
					int count = eventRepository.selectfcfsWinnerCountCheck(eventSearchFoDTO);
					if (count > 99) {
						if ( "Y".equals(lastDay)) {
							result.put("resultCode", "CLOSE");
							result.put("resultMsg", "선착순 마감 되었습니다.");
						} else {
							result.put("resultCode", "ENTRY_AGAIN");
							result.put("resultMsg", "선착순 마감 되었습니다. 오전 10시부터 다시 해주세요.");
						}
						return result;
					}
					if (eventRepository.selectfcfsDoubleWinningCheck(eventSearchFoDTO) > 1) {
						result.put("resultCode", "N");
						result.put("resultMsg", "이벤트 기간 동안 1회만 참여 가능 합니다.");
						return result;
					}
					// 1. 응모 테이블 insert
					long evtApplcnSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
					EvtApplcn evtApplcn = new EvtApplcn();
					evtApplcn.setEvtPartcptnSn(evtApplcnSn);
					evtApplcn.setEvtNo(eventSearchFoDTO.getEvtNo());
					evtApplcn.setMbrNo(eventSearchFoDTO.getMbrNo());
					evtApplcn.setAtendDaycnt(eventExtendsFoDTO.getAtendDaycnt());
					long time = System.currentTimeMillis();
					Date regDt = new Date(time);
					evtApplcn.setApplcnDt(regDt);
					evtApplcn.setUdterId(eventSearchFoDTO.getMbrNo());
					evtApplcn.setRegtrId(eventSearchFoDTO.getMbrNo());
					evtApplcnRepository.insertEvtApplcn(evtApplcn);
					eventSearchFoDTO.setEvtPartcptnSn(evtApplcnSn);
					eventSearchFoDTO.setLastDay(lastDay);
					//result.put("resultCode", "Y");
					
					EvtPrize evtPrize = new EvtPrize();
					evtPrize.setEvtPartcptnSn(eventSearchFoDTO.getEvtPartcptnSn());
					evtPrize.setEvtNo(eventSearchFoDTO.getEvtNo());
					evtPrize.setPrizeRank(prizeRank);
					evtPrize.setPrizeDt(new Date());
					evtPrize.setRegtrId(eventSearchFoDTO.getMbrNo());
					evtPrize.setUdterId(eventSearchFoDTO.getMbrNo());
					int cnt = eventRepository.insertEventPrize(evtPrize);

					if (cnt > 0) {
						EvtFreeGiftInfo evtFreeGiftInfo = new EvtFreeGiftInfo();
						evtFreeGiftInfo.setEvtNo(eventSearchFoDTO.getEvtNo());
						
						evtFreeGiftInfo.setPrizeRank(2); //연장이벤트

						evtFreeGiftInfo = eventRepository.getfcfsFreeGift(evtFreeGiftInfo);

						EvtPrizeFreeGift evtPrizeFreeGift = new EvtPrizeFreeGift();
						evtPrizeFreeGift.setEvtPartcptnSn(eventSearchFoDTO.getEvtPartcptnSn());
						evtPrizeFreeGift.setPrizeTurn(evtPrize.getPrizeTurn());
						evtPrizeFreeGift.setEvtNo(eventSearchFoDTO.getEvtNo());
						evtPrizeFreeGift.setFreeGiftTurn(evtFreeGiftInfo.getFreeGiftTurn());
						evtPrizeFreeGift.setRegtrId(eventSearchFoDTO.getMbrNo());
						evtPrizeFreeGift.setUdterId(eventSearchFoDTO.getMbrNo());
						singleEventRepository.insertEventPrizeFreeGift(evtPrizeFreeGift);

						result.put("resultCode", "Y");
						result.put("resultMsg", evtFreeGiftInfo.getFreeGiftNm() + "\n 축하 드립니다!");
						return result;

					}
			}

		} catch (Exception e) {
			if ("Y".equals(lastDay)) {
				result.put("resultCode", "CLOSE");
				result.put("resultMsg", "선착순 마감 되었습니다.");
			} else {
				result.put("resultCode", "ENTRY_AGAIN");
				result.put("resultMsg", "선착순 마감 되었습니다. 오전 10시부터 다시 응모해주세요.");
			}

			return result;
		}

		return result;
	}

	public Map<String, String> addEventfcfs(EventSearchFoDTO eventSearchFoDTO )  {

		Map<String, String> result = new HashMap<String, String>();
		String lastDay = eventSearchFoDTO.getLastDay();
		try {

			int prizeRank = eventRepository.selectfcfsRankingInformation(eventSearchFoDTO);
			if (prizeRank > 200) {
				eventRepository.deletefcfsWinner(eventSearchFoDTO);
				if ("Y".equals(lastDay)) {
					result.put("resultCode", "CLOSE");
					result.put("resultMsg", "선착순 마감 되었습니다.");
				} else {
					result.put("resultCode", "ENTRY_AGAIN");
					result.put("resultMsg", "선착순 마감 되었습니다. 오전 10시부터 다시 응모해주세요.");
				}
				return result;
			} else {
				EvtPrize evtPrize = new EvtPrize();
				evtPrize.setEvtPartcptnSn(eventSearchFoDTO.getEvtPartcptnSn());
				evtPrize.setEvtNo(eventSearchFoDTO.getEvtNo());
				evtPrize.setPrizeRank(prizeRank);
				evtPrize.setPrizeDt(new Date());
				evtPrize.setRegtrId(eventSearchFoDTO.getMbrNo());
				evtPrize.setUdterId(eventSearchFoDTO.getMbrNo());
				int cnt = eventRepository.insertEventPrize(evtPrize);

				if (cnt > 0) {
					EvtFreeGiftInfo evtFreeGiftInfo = new EvtFreeGiftInfo();
					evtFreeGiftInfo.setEvtNo(eventSearchFoDTO.getEvtNo());
					/*if (prizeRank == 1 || prizeRank == 50 || prizeRank == 100 || prizeRank == 200) {
						evtFreeGiftInfo.setPrizeRank(prizeRank);
					} else {
						evtFreeGiftInfo.setPrizeRank(999);
					}*/
					
					evtFreeGiftInfo.setPrizeRank(1);

					evtFreeGiftInfo = eventRepository.getfcfsFreeGift(evtFreeGiftInfo);

					EvtPrizeFreeGift evtPrizeFreeGift = new EvtPrizeFreeGift();
					evtPrizeFreeGift.setEvtPartcptnSn(eventSearchFoDTO.getEvtPartcptnSn());
					evtPrizeFreeGift.setPrizeTurn(evtPrize.getPrizeTurn());
					evtPrizeFreeGift.setEvtNo(eventSearchFoDTO.getEvtNo());
					evtPrizeFreeGift.setFreeGiftTurn(evtFreeGiftInfo.getFreeGiftTurn());
					evtPrizeFreeGift.setRegtrId(eventSearchFoDTO.getMbrNo());
					evtPrizeFreeGift.setUdterId(eventSearchFoDTO.getMbrNo());
					singleEventRepository.insertEventPrizeFreeGift(evtPrizeFreeGift);

					result.put("resultCode", "Y");
					result.put("resultMsg", evtFreeGiftInfo.getFreeGiftNm() + "\n 축하 드립니다!");
					return result;

				}
			}
		} catch (Exception e) { 
			result.put("resultCode", "CLOSE");
			
			if ("Y".equals(lastDay)) {
				result.put("resultCode", "CLOSE");
				result.put("resultMsg", "선착순 마감 되었습니다.");
			} else {
				result.put("resultCode", "ENTRY_AGAIN");
				result.put("resultMsg", "선착순 마감 되었습니다. 오전 10시부터 다시 응모해주세요.");
			}

			return result;
		}
		return result;
	}

	public int selectfcfsDoubleWinningCheck(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return eventRepository.selectfcfsDoubleWinningCheck(eventSearchFoDTO);
	}

	public int selectEventPeriodNewMemberInquiry(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return eventRepository.selectEventPeriodNewMemberInquiry(eventSearchFoDTO);
	}
	public int selectfcfsWinnerCountCheck(EventSearchFoDTO eventSearchFoDTO) throws Exception   {
		return eventRepository.selectfcfsWinnerCountCheck(eventSearchFoDTO);
	}
	public EventExtendsFoDTO selectEventLwprt(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return eventRepository.selectEventLwprt(eventSearchFoDTO);
	}
	
	public int selectEventFirstPurchase(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return eventRepository.selectEventFirstPurchase(eventSearchFoDTO);
	}
	
	public int selectEventRepurchase(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return eventRepository.selectEventFirstPurchase(eventSearchFoDTO);
	}
	
	
	public Map<String, String> insertEventRenewal(EventSearchFoDTO eventSearchFoDTO) {
		Map<String, String> result = new HashMap<String, String>();
 
		try {
			// 이벤트 기준 정보 조회
			EventExtendsFoDTO eventExtendsFoDTO = eventRepository.selectEventRenewal(eventSearchFoDTO);

			if (eventExtendsFoDTO == null) {
				result.put("resultCode", "N");
				result.put("resultMsg", "이벤트 기간이 아닙니다.");
				return result;
			}

			int count = eventRepository.selectfcfsWinnerCountCheck(eventSearchFoDTO);
			if (count > 499) {
				result.put("resultCode", "N");
				
				if(eventSearchFoDTO.getSortSeq() == 1){
					result.put("resultMsg", "첫구매 이벤트 선착순이 마감 되었습니다.");	
				}else{
					result.put("resultMsg", "재구매 이벤트 선착순이 마감 되었습니다.");	
				}
				
				
				return result;
			}
			if (eventRepository.selectfcfsDoubleWinningCheck(eventSearchFoDTO) > 1) {
				result.put("resultCode", "N");
				result.put("resultMsg", "이벤트 기간 동안 1회만 참여 가능 합니다.");
				return result;
			}
			// 1. 응모 테이블 insert
			long evtApplcnSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
			EvtApplcn evtApplcn = new EvtApplcn();
			evtApplcn.setEvtPartcptnSn(evtApplcnSn);
			evtApplcn.setEvtNo(eventSearchFoDTO.getEvtNo());
			evtApplcn.setMbrNo(eventSearchFoDTO.getMbrNo());
			long time = System.currentTimeMillis();
			Date regDt = new Date(time);
			evtApplcn.setApplcnDt(regDt);
			evtApplcn.setUdterId(eventSearchFoDTO.getMbrNo());
			evtApplcn.setRegtrId(eventSearchFoDTO.getMbrNo());
			evtApplcnRepository.insertEvtApplcn(evtApplcn);
			eventSearchFoDTO.setEvtPartcptnSn(evtApplcnSn);
			result.put("resultCode", "Y");

		} catch (Exception e) {
			result.put("resultCode", "N");
			if(eventSearchFoDTO.getSortSeq() == 1){
				result.put("resultMsg", "첫구매 이벤트 선착순이 마감 되었습니다.");	
			}else{
				result.put("resultMsg", "재구매 이벤트 선착순이 마감 되었습니다.");	
			}
			return result;
		}

		return result;
	}
	
 
	
	public Map<String, String> addEventRenewal(EventSearchFoDTO eventSearchFoDTO )  {

		Map<String, String> result = new HashMap<String, String>();
 
		try {

			int prizeRank = eventRepository.selectfcfsRankingInformation(eventSearchFoDTO);
			if (prizeRank > 500) {
				eventRepository.deletefcfsWinner(eventSearchFoDTO);
				result.put("resultCode", "N");
				if(eventSearchFoDTO.getSortSeq() == 1){
					result.put("resultMsg", "첫구매 이벤트 선착순이 마감 되었습니다.");	
				}else{
					result.put("resultMsg", "재구매 이벤트 선착순이 마감 되었습니다.");	
				}
				return result;
			} else {
				EvtPrize evtPrize = new EvtPrize();
				evtPrize.setEvtPartcptnSn(eventSearchFoDTO.getEvtPartcptnSn());
				evtPrize.setEvtNo(eventSearchFoDTO.getEvtNo());
				evtPrize.setPrizeRank(prizeRank);
				evtPrize.setPrizeDt(new Date());
				evtPrize.setRegtrId(eventSearchFoDTO.getMbrNo());
				evtPrize.setUdterId(eventSearchFoDTO.getMbrNo());
				int cnt = eventRepository.insertEventPrize(evtPrize);

				if (cnt > 0) {
					EvtFreeGiftInfo evtFreeGiftInfo = new EvtFreeGiftInfo();
					evtFreeGiftInfo.setEvtNo(eventSearchFoDTO.getEvtNo());
					if (prizeRank == 10|| prizeRank == 100 || prizeRank == 500) {
						evtFreeGiftInfo.setPrizeRank(prizeRank);
					} else {
						evtFreeGiftInfo.setPrizeRank(999);
					}

					evtFreeGiftInfo = eventRepository.getfcfsFreeGift(evtFreeGiftInfo);

					EvtPrizeFreeGift evtPrizeFreeGift = new EvtPrizeFreeGift();
					evtPrizeFreeGift.setEvtPartcptnSn(eventSearchFoDTO.getEvtPartcptnSn());
					evtPrizeFreeGift.setPrizeTurn(evtPrize.getPrizeTurn());
					evtPrizeFreeGift.setEvtNo(eventSearchFoDTO.getEvtNo());
					evtPrizeFreeGift.setFreeGiftTurn(evtFreeGiftInfo.getFreeGiftTurn());
					evtPrizeFreeGift.setRegtrId(eventSearchFoDTO.getMbrNo());
					evtPrizeFreeGift.setUdterId(eventSearchFoDTO.getMbrNo());
					singleEventRepository.insertEventPrizeFreeGift(evtPrizeFreeGift);

					result.put("resultCode", "Y");
					result.put("resultMsg", evtFreeGiftInfo.getFreeGiftNm());
					return result;

				}
			}
		} catch (Exception e) { 
			result.put("resultCode", "N");
			if(eventSearchFoDTO.getSortSeq() == 1){
				result.put("resultMsg", "첫구매 이벤트 선착순이 마감 되었습니다.");	
			}else{
				result.put("resultMsg", "재구매 이벤트 선착순이 마감 되었습니다.");	
			}
			return result;
		}
		return result;
	}
	
	/**
	 * 화면에 랜덤 노출 이벤트 조회
	 * - PV 이벤트
	 * 
	 * @param eventSearchFoDTO
	 * @return String
	 */
	public String selectSpecialEventForPvEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return singleEventRepository.selectSpecialEventForPvEvent(eventSearchFoDTO);
	}
	
	/**
	 * PV 이벤트 당첨 경품 조회
	 * 
	 * @param eventSearchFoDTO
	 * @return EventFreeGiftTurnExtendsFoDTO
	 */
	public EventFreeGiftTurnExtendsFoDTO selectPrizeInfoForPvEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return singleEventRepository.selectPrizeInfoForPvEvent(eventSearchFoDTO);
	}
	
	/**
	 * PV 이벤트 경품 목록 조회
	 * 
	 * @param eventSearchFoDTO
	 * @return List<EventFreeGiftTurnExtendsFoDTO>
	 */
	public List<EventFreeGiftTurnExtendsFoDTO> selectFreeGiftTurnListForPvEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return singleEventRepository.selectFreeGiftTurnListForPvEvent(eventSearchFoDTO);
	}
	
	/**
	 * PV 이벤트 당첨 순번 조회
	 * - 중복 방지를 위한 처리
	 * 
	 * @param eventSearchFoDTO
	 * @return String
	 */
	public String selectNextvalForPvEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return singleEventRepository.selectNextvalForPvEvent(eventSearchFoDTO);
	}
	
	/**
	 * 연말정산 Happy 포인트 이벤트 포인트 적립 여부 조회
	 * 
	 * @param eventSearchFoDTO
	 * @return int
	 */
	public int selectValidForHappyPoint(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return singleEventRepository.selectValidForHappyPoint(eventSearchFoDTO);
	}
	
	/**
	 * 룰렛 이벤트 구매확정 개수 조회
	 * 
	 * @param eventBoDTO
	 * @return int
	 */
	public int selectPchDcsnCnt(EventBoDTO eventBoDTO) throws Exception {
		return singleEventRepository.selectPchDcsnCnt(eventBoDTO);
	}
	
	/**
	 *룰렛 이벤트 포토리뷰작성 개수 조회
	 * 
	 * @param eventBoDTO
	 * @return int
	 */
	public int selectAtchFileCnt(EventBoDTO eventBoDTO) throws Exception {
		return singleEventRepository.selectAtchFileCnt(eventBoDTO);
	}
	
	/**
	 * 룰렛 이벤트 경품 목록 조회
	 * 
	 * @param eventSearchFoDTO
	 * @return List<EventFreeGiftTurnExtendsFoDTO>
	 */
	public List<EventFreeGiftTurnExtendsFoDTO> selectFreeGiftTurnListForRouletteEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return singleEventRepository.selectFreeGiftTurnListForRouletteEvent(eventSearchFoDTO);
	}
	
	/**
	 *룰렛 이벤트 해당일 선착순 마감 조회
	 * 
	 * @param eventSearchFoDTO
	 */
	public EventBoResult validRouletteApplcn(EventSearchFoDTO eventSearchFoDTO) {
		
		EventBoResult result = new EventBoResult();
		boolean flag = true;
		String lastDay = "N";
		try {
			// 이벤트 기준 정보 조회
			EventExtendsFoDTO eventExtendsFoDTO = eventRepository.selectfcfsEvent(eventSearchFoDTO);
			result.setAtendDaycnt(eventExtendsFoDTO.getAtendDaycnt());

			if (eventExtendsFoDTO == null) {
				result.setResultCode(EventResultCode.INVALID_APPLCN_DATE.toString());
				result.setResultMessage("이벤트 기간이 아닙니다.");	
				flag = false;
				return result;
			}

			if (eventExtendsFoDTO.getAtendDaycnt() == eventExtendsFoDTO.getLastDaycnt()) {
				lastDay = "Y";
			}

			// 해당일 이벤트 마감체크
			eventSearchFoDTO.setAtendDaycnt(eventExtendsFoDTO.getAtendDaycnt());
			int count = eventRepository.selectfcfsWinnerCountCheck(eventSearchFoDTO);
			
			if (count > 199) {
				if ( "Y".equals(lastDay)) {
					result.setResultCode("CLOSE");
					result.setResultMessage("선착순이 종료 되었습니다.");	
					flag = false;
				} else {
					result.setResultCode("ENTRY_AGAIN");
					result.setResultMessage("오늘 선착순이 종료 되었습니다. 내일 오전 10시에 다시 응모해주세요.");	
					flag = false;
				}
				return result;
			}
			
			// 해당일 이벤트 마감 시퀀스로 한번 더 중복체크
			eventSearchFoDTO.setAtendDaycnt(eventExtendsFoDTO.getAtendDaycnt()+ 8); //시퀀스 중복 확인으로, 날짜 시퀀스는 1~8개 경품 시퀀스 이후부터 사용 
			int prizeRank = eventRepository.selectfcfsRankingInformation(eventSearchFoDTO);
			eventSearchFoDTO.setPrizeRank(prizeRank);
			
			if(prizeRank > 200){
				if ( "Y".equals(lastDay)) {
					result.setResultCode("CLOSE");
					result.setResultMessage("선착순이 종료 되었습니다.");	
					flag = false;
				} else {
					result.setResultCode("ENTRY_AGAIN");
					result.setResultMessage("오늘 선착순이 종료 되었습니다. 내일 오전 10시에 다시 응모해주세요.");	
					flag = false;
				}
				return result;
			}

		 } catch (Exception e) {
			if ("Y".equals(lastDay)) {
				result.setResultCode("CLOSE");
				result.setResultMessage("선착순이 종료 되었습니다.");	
				flag = false;
			} else {
				result.setResultCode("ENTRY_AGAIN");
				result.setResultMessage("오늘 선착순이 종료 되었습니다. 내일 오전 10시에 다시 응모해주세요.");	
				flag = false;
			}

			return result;
		}
		
		
		result.setCheckResult(flag);

		return result;
	}
	
	/**
	 *룰렛 이벤트 이벤트 응모
	 * 
	 * @param eventSearchFoDTO
	 */
	public int insertEvtRouletteApplcn(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		int applnCnt = 0;
		
		// 이벤트 기준 정보 조회
		//EventExtendsFoDTO eventExtendsFoDTO = eventRepository.selectfcfsEvent(eventSearchFoDTO);
		
		// 1. 응모
		long evtApplcnSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
		EvtApplcn evtApplcn = new EvtApplcn();
		evtApplcn.setEvtPartcptnSn(evtApplcnSn);
		evtApplcn.setEvtNo(eventSearchFoDTO.getEvtNo());
		evtApplcn.setApplcnMbrSectCd(EventApplcnMbrSect.MBR.toString());
		evtApplcn.setMbrNo(eventSearchFoDTO.getMbrNo());
		evtApplcn.setApplcnDt(new Date()); // 응모일시

		evtApplcn.setAtendDaycnt(eventSearchFoDTO.getAtendDaycnt());
		evtApplcn.setFreeGiftTurn(eventSearchFoDTO.getFreeGiftTurn());
		evtApplcn.setRegtrId(eventSearchFoDTO.getMbrNo());
		evtApplcn.setUdterId(eventSearchFoDTO.getMbrNo());
		
		evtApplcnRepository.insertEvtApplcn(evtApplcn);
		eventSearchFoDTO.setEvtPartcptnSn(evtApplcnSn);
		eventSearchFoDTO.setAtendDaycnt(eventSearchFoDTO.getAtendDaycnt());
		applnCnt = 1;
		
		return applnCnt;
	}
	
	
	/**
	 *룰렛 이벤트 이벤트 당첨
	 * 
	 * @param eventSearchFoDTO
	 */
	public EventBoResult insertEvtRoulettePrize(EventSearchFoDTO eventSearchFoDTO) throws Exception {	
	
		EventBoResult result = new EventBoResult();
		boolean flag = true;
		
		try {
			eventSearchFoDTO.setAtendDaycnt(eventSearchFoDTO.getAtendDaycnt()+ 8); 
			
			EvtPrize evtPrize = new EvtPrize();
			evtPrize.setEvtPartcptnSn(eventSearchFoDTO.getEvtPartcptnSn()); 
			evtPrize.setEvtNo(eventSearchFoDTO.getEvtNo());
			evtPrize.setPrizeRank(eventSearchFoDTO.getPrizeRank());
			evtPrize.setPrizeDt(new Date());
			evtPrize.setRegtrId(eventSearchFoDTO.getMbrNo());
			evtPrize.setUdterId(eventSearchFoDTO.getMbrNo());
			int cnt = eventRepository.insertEventPrize(evtPrize);
		
			if (cnt > 0) {
				EvtPrizeFreeGift evtPrizeFreeGift = new EvtPrizeFreeGift();
				evtPrizeFreeGift.setEvtPartcptnSn(eventSearchFoDTO.getEvtPartcptnSn());
				evtPrizeFreeGift.setPrizeTurn(evtPrize.getPrizeTurn());
				evtPrizeFreeGift.setEvtNo(eventSearchFoDTO.getEvtNo());
				evtPrizeFreeGift.setFreeGiftTurn(eventSearchFoDTO.getFreeGiftTurn());
				evtPrizeFreeGift.setRegtrId(eventSearchFoDTO.getMbrNo());
				evtPrizeFreeGift.setUdterId(eventSearchFoDTO.getMbrNo());
				singleEventRepository.insertEventPrizeFreeGift(evtPrizeFreeGift);
			}
		} catch (Exception e) {
			result.setResultCode(EventResultCode.ERROR.toString());
			result.setResultMessage("이벤트 참여 처리 중 오류가 발생하였습니다. 다시 시도하시길 바랍니다. ");	
			flag = false;
			return result;
		}
		
		result.setCheckResult(flag);

		return result;
	}
	
	
	
	/**
	 *상품입고알림신청 응모 및 입고 알림 처리
	 * 
	 * @param eventSearchFoDTO
	 */
	public int insertStockInformRequest(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		int applnCnt = 0;
		
		// 이벤트 기준 정보 조회
		//EventExtendsFoDTO eventExtendsFoDTO = eventRepository.selectfcfsEvent(eventSearchFoDTO);
		
		// 1. 응모
		long evtApplcnSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
		EvtApplcn evtApplcn = new EvtApplcn();
		evtApplcn.setEvtPartcptnSn(evtApplcnSn);
		evtApplcn.setEvtNo(eventSearchFoDTO.getEvtNo());
		evtApplcn.setApplcnMbrSectCd(EventApplcnMbrSect.MBR.toString());
		evtApplcn.setMbrNo(eventSearchFoDTO.getMbrNo());
		evtApplcn.setApplcnDt(new Date()); // 응모일시

		evtApplcn.setAtendDaycnt(eventSearchFoDTO.getAtendDaycnt());
		evtApplcn.setFreeGiftTurn(null);
		evtApplcn.setRegtrId(eventSearchFoDTO.getMbrNo());
		evtApplcn.setUdterId(eventSearchFoDTO.getMbrNo());
		
		evtApplcnRepository.insertEvtApplcn(evtApplcn);
		eventSearchFoDTO.setEvtPartcptnSn(evtApplcnSn);
		eventSearchFoDTO.setAtendDaycnt(eventSearchFoDTO.getAtendDaycnt());
		applnCnt = 1;
		
		return applnCnt;
	}
	
	
	/**
	 *선판매 오픈 알림 신청 처리
	 * 
	 * @param eventSearchFoDTO
	 */
	public EventBoResult insertEarlyBirdEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		//int applnCnt = 0;
		
		
		EventBoResult result = new EventBoResult();
		
		try {
			
			long evtApplcnSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
			EvtApplcn evtApplcn = new EvtApplcn();
			evtApplcn.setEvtPartcptnSn(evtApplcnSn);
			evtApplcn.setEvtNo(eventSearchFoDTO.getEvtNo());
			evtApplcn.setApplcnMbrSectCd(EventApplcnMbrSect.MBR.toString());
			evtApplcn.setMbrNo(eventSearchFoDTO.getMbrNo());
			evtApplcn.setApplcnDt(new Date()); // 응모일시

			evtApplcn.setAtendDaycnt(1);
			evtApplcn.setFreeGiftTurn(null);
			evtApplcn.setCont(eventSearchFoDTO.getPhoneMobile());
			evtApplcn.setRegtrId(eventSearchFoDTO.getMbrNo());
			evtApplcn.setUdterId(eventSearchFoDTO.getMbrNo());

			if (eventRepository.selectEarlyBirdDoubleWinningCheck(evtApplcn) > 0) {
				result.setResultCode(EventResultCode.NO_MORE_CHANCE.toString());
				result.setResultMessage("이미 신청 완료되었습니다.");	
				return result;
			}else{
				evtApplcnRepository.insertEvtApplcn(evtApplcn);
				eventSearchFoDTO.setEvtPartcptnSn(evtApplcnSn);
				eventSearchFoDTO.setAtendDaycnt(eventSearchFoDTO.getAtendDaycnt());
				result.setResultCode(EventResultCode.SUCCESS.toString());
				result.setResultMessage("신청 완료!행사 오픈일 SMS가 발송됩니다");
				return result;
			}
		
		} catch (Exception e) {
			result.setResultCode(EventResultCode.ERROR.toString());
			result.setResultMessage("이벤트 참여 처리 중 오류가 발생하였습니다. 다시 시도하시길 바랍니다. ");	
			return result;
		}
		
		
	}
	
	
	/**
	 *선판매 오픈 알림 신청 처리
	 * 
	 * @param eventSearchFoDTO
	 */
	public EventBoResult insertSnsShareEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		//int applnCnt = 0;
		
		
		EventBoResult result = new EventBoResult();
		
		try {
			
			long evtApplcnSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
			EvtApplcn evtApplcn = new EvtApplcn();
			evtApplcn.setEvtPartcptnSn(evtApplcnSn);
			evtApplcn.setEvtNo(eventSearchFoDTO.getEvtNo());
			evtApplcn.setApplcnMbrSectCd(EventApplcnMbrSect.MBR.toString());
			evtApplcn.setMbrNo(eventSearchFoDTO.getMbrNo());
			evtApplcn.setApplcnDt(new Date()); // 응모일시

			//evtApplcn.setAtendDaycnt(1);
			evtApplcn.setFreeGiftTurn(null);
			evtApplcn.setCont(eventSearchFoDTO.getPhoneMobile());
			evtApplcn.setRegtrId(eventSearchFoDTO.getMbrNo());
			evtApplcn.setUdterId(eventSearchFoDTO.getMbrNo());

			evtApplcnRepository.insertEvtApplcn(evtApplcn);
			eventSearchFoDTO.setEvtPartcptnSn(evtApplcnSn);
			eventSearchFoDTO.setAtendDaycnt(eventSearchFoDTO.getAtendDaycnt());
			result.setResultCode(EventResultCode.SUCCESS.toString());
			result.setResultMessage("신청 완료!행사 오픈일 SMS가 발송됩니다");
			return result;
		
		} catch (Exception e) {
			result.setResultCode(EventResultCode.ERROR.toString());
			result.setResultMessage("이벤트 참여 처리 중 오류가 발생하였습니다. 다시 시도하시길 바랍니다. ");	
			return result;
		}
		
		
	}
	
	
	/**
	 *릴레이 선물 응모처리
	 * 
	 * @param eventSearchFoDTO
	 */
	public EventBoResult insertRelayGiftEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		
		EventBoResult result = new EventBoResult();
		
		try {
			
			long evtApplcnSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
			EvtApplcn evtApplcn = new EvtApplcn();
			evtApplcn.setEvtPartcptnSn(evtApplcnSn);
			evtApplcn.setEvtNo(eventSearchFoDTO.getEvtNo());
			evtApplcn.setApplcnMbrSectCd(EventApplcnMbrSect.MBR.toString());
			evtApplcn.setMbrNo(eventSearchFoDTO.getMbrNo());
			evtApplcn.setApplcnDt(new Date()); // 응모일시

			evtApplcn.setAtendDaycnt(null);
			evtApplcn.setFreeGiftTurn(null);
			evtApplcn.setCont(eventSearchFoDTO.getPhoneMobile());
			evtApplcn.setRegtrId(eventSearchFoDTO.getMbrNo());
			evtApplcn.setUdterId(eventSearchFoDTO.getMbrNo());
			evtApplcn.setBegDt(eventSearchFoDTO.getBegDt());
			evtApplcn.setEndDt(eventSearchFoDTO.getEndDt());

			if (singleEventRepository.selectRelayGiftDoubleWinningCheck(evtApplcn) > 0) {
				result.setResultCode(EventResultCode.NO_MORE_CHANCE.toString());
				result.setResultMessage("이미 신청 완료되었습니다.");	
				return result;
			}else{
				evtApplcnRepository.insertEvtApplcn(evtApplcn);
				eventSearchFoDTO.setEvtPartcptnSn(evtApplcnSn);
				eventSearchFoDTO.setAtendDaycnt(eventSearchFoDTO.getAtendDaycnt());
				result.setResultCode(EventResultCode.SUCCESS.toString());
				result.setResultMessage("신청 완료!행사 오픈일 SMS가 발송됩니다");
				return result;
			}
		
		} catch (Exception e) {
			result.setResultCode(EventResultCode.ERROR.toString());
			result.setResultMessage("이벤트 참여 처리 중 오류가 발생하였습니다. 다시 시도하시길 바랍니다. ");	
			return result;
		}
		
		
	}
	
	
	/**
	 *이벤트 응모처리
	 * 
	 * @param eventSearchFoDTO
	 */
	public EventBoResult insertEventApplcn(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		
		EventBoResult result = new EventBoResult();
		
		try {
			
			long evtApplcnSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
			EvtApplcn evtApplcn = new EvtApplcn();
			evtApplcn.setEvtPartcptnSn(evtApplcnSn);
			evtApplcn.setEvtNo(eventSearchFoDTO.getEvtNo());
			evtApplcn.setApplcnMbrSectCd(EventApplcnMbrSect.MBR.toString());
			evtApplcn.setMbrNo(eventSearchFoDTO.getMbrNo());
			evtApplcn.setApplcnDt(new Date()); // 응모일시

			evtApplcn.setAtendDaycnt(1);
			evtApplcn.setFreeGiftTurn(null);
			//evtApplcn.setCont(eventSearchFoDTO.getPhoneMobile());
			evtApplcn.setCont("");
			evtApplcn.setRegtrId(eventSearchFoDTO.getMbrNo());
			evtApplcn.setUdterId(eventSearchFoDTO.getMbrNo());
			
			if (singleEventRepository.selectDoubleWinningCheck(evtApplcn) > 0 && eventSearchFoDTO.getEvtDuplctYn()!='Y') {
				result.setResultCode(EventResultCode.NO_MORE_CHANCE.toString());
				result.setResultMessage("이미 신청 완료되었습니다.");	
				return result;
			}else{
				evtApplcnRepository.insertEvtApplcn(evtApplcn);
				eventSearchFoDTO.setEvtPartcptnSn(evtApplcnSn);
				eventSearchFoDTO.setAtendDaycnt(eventSearchFoDTO.getAtendDaycnt());
				result.setResultCode(EventResultCode.SUCCESS.toString());
				result.setResultMessage("신청 완료되었습니다.");
				return result;
			}
		
		} catch (Exception e) {
			result.setResultCode(EventResultCode.ERROR.toString());
			result.setResultMessage("이벤트 참여 처리 중 오류가 발생하였습니다. 다시 시도하시길 바랍니다. ");	
			return result;
		}
	}
	
	
	/**
	 *쿠폰 유효기간 
	 * 
	 * @param eventBoDTO
	 */
	public PrmCpn selectPrmCpn(EventBoDTO eventBoDTO) throws Exception {
		return singleEventRepository.selectPrmCpn(eventBoDTO);
	}
		
		
		
}
