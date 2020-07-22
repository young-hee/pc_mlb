package com.plgrim.ncp.cmp.promotion.fo.event;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.poi.ss.util.SSCellRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrGrd;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.base.enums.EventEnum.EventFreeGiftKind;
import com.plgrim.ncp.base.enums.EventEnum.EventResultCode;
import com.plgrim.ncp.base.enums.PromotionEnum.CouponIssueLimitReason;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.SysInfoEnum;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.service.MemberBaseSelectService;
import com.plgrim.ncp.biz.member.service.MemberBenefitCommandService;
import com.plgrim.ncp.biz.promotion.data.EventBoDTO;
import com.plgrim.ncp.biz.promotion.data.EventExtendsFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventFreeGiftTurnExtendsFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventSearchFoDTO;
import com.plgrim.ncp.biz.promotion.exception.EventFreeGiftNotJoinTimeException;
import com.plgrim.ncp.biz.promotion.exception.EventOnlineMemberOnlyException;
import com.plgrim.ncp.biz.promotion.result.EventBoResult;
import com.plgrim.ncp.biz.promotion.service.EventService;
import com.plgrim.ncp.biz.promotion.service.PromotionService;
import com.plgrim.ncp.biz.promotion.service.SingleEventService;
import com.plgrim.ncp.cmp.promotion.fo.PromotionSingleEventFOComponent;
import com.plgrim.ncp.framework.commons.ContextService;

@Component
public class PromotionSingleEventFOComponentImpl extends AbstractComponent implements PromotionSingleEventFOComponent {

	@Autowired
	private SingleEventService singleEventService;

	@Autowired
	private EventService eventService;
	
	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	MemberBenefitCommandService memberBenefitCommandService;
	
	@Autowired
	MemberBaseSelectService memberBaseSelectService;
	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
	public Map<String, String> insertEventfcfs(EventSearchFoDTO searchDTO) {

		return singleEventService.insertEventfcfs(searchDTO);
	}

	@Override
	public Map<String, String> addEventfcfs(EventSearchFoDTO searchDTO) {

		return singleEventService.addEventfcfs(searchDTO);
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW)
	public Map<String, String> insertEventRenewal(EventSearchFoDTO searchDTO) {

		return singleEventService.insertEventRenewal(searchDTO);
	}

	@Override
	public Map<String, String> addEventRenewal(EventSearchFoDTO searchDTO) {

		return singleEventService.addEventRenewal(searchDTO);
	}

	@Override
	public int selectfcfsDoubleWinningCheck(EventSearchFoDTO searchDTO) throws Exception {

		return singleEventService.selectfcfsDoubleWinningCheck(searchDTO);
	}

	@Override
	public int selectEventPeriodNewMemberInquiry(EventSearchFoDTO searchDTO) throws Exception {

		return singleEventService.selectEventPeriodNewMemberInquiry(searchDTO);
	}

	@Override
	public int selectfcfsWinnerCountCheck(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return singleEventService.selectfcfsWinnerCountCheck(eventSearchFoDTO);
	}

	@Override
	public EventExtendsFoDTO selectEventLwprt(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return singleEventService.selectEventLwprt(eventSearchFoDTO);
	}

	@Override
	public int selectEventFirstPurchase(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		// TODO Auto-generated method stub
		return singleEventService.selectEventFirstPurchase(eventSearchFoDTO);
	}

	@Override
	public int selectEventRepurchase(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		// TODO Auto-generated method stub
		return singleEventService.selectEventRepurchase(eventSearchFoDTO);
	}
	
	@Override
	public int selectEvtStmpCount(EventBoDTO eventBoDTO, String type) throws Exception {
		return eventService.selectEvtStmpCount(eventBoDTO, type);
	}
	
	@Override
	public String selectSpecialEventForPvEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return singleEventService.selectSpecialEventForPvEvent(eventSearchFoDTO);
	}
	
	@Override
	public EventFreeGiftTurnExtendsFoDTO selectPrizeInfoForPvEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return singleEventService.selectPrizeInfoForPvEvent(eventSearchFoDTO);
	}
	
	@Override
	public List<EventFreeGiftTurnExtendsFoDTO> selectFreeGiftTurnListForPvEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return singleEventService.selectFreeGiftTurnListForPvEvent(eventSearchFoDTO);
	}
	
	@Override
	public EventBoResult validIconClickJoinEvent(EventBoDTO eventBoDTO) throws Exception {
		EventBoResult result = eventBoDTO.getEventBoResult();
		boolean flag = true;
		// 금일 하루 stamp 누적 횟수 조회
		int totalCount = 0;
		try {
			totalCount = this.selectEvtStmpCount(eventBoDTO, "TOTAL");
		}
		catch(Exception e) {
			result.setResultCode(EventResultCode.ERROR.toString());
			result.setResultMessage("이벤트 참여 처리 중 오류가 발생하였습니다. 다시 시도하시길 바랍니다.");
			flag = false;
		}
		
		// 금일 하루 stamp 누적 횟수 검증
		// 2000회 이상이면 기회 없음.
		if(flag && totalCount >= 2000) {
			result.setResultCode(EventResultCode.NO_MORE_CHANCE.toString());
			result.setResultMessage("오늘 선착순 마감! 내일 다시 공유를 찾아주세요.");
			flag = false;
		}
		
		// 회원의 stamp 누적 횟수 조회
		int mbrCount = 0;
		if(flag) {
			try {
				mbrCount = this.selectEvtStmpCount(eventBoDTO, "MBR");
			}
			catch(Exception e) {
				result.setResultCode(EventResultCode.ERROR.toString());
				result.setResultMessage("이벤트 참여 처리 중 오류가 발생하였습니다. 다시 시도하시길 바랍니다.");
				flag = false;
			}
		}
		
		// 회원의 stamp 누적 횟수 검증
		// 10회 이상이면 기회 없음.
		if(flag && mbrCount >= 10) {
			result.setResultCode(EventResultCode.NO_MORE_CHANCE.toString());
			result.setResultMessage("1000 포인트 모두 적립 받으셨습니다.");
			flag = false;
		}
		
		result.setCheckResult(flag);
		
		return result;
	}
	
	@Override
	public EventBoResult iconClickJoinEvent(EventBoDTO eventBoDTO) throws Exception {
		
		// 이벤트 응모
		int applcnCnt = eventService.insertEvtApplcn(eventBoDTO);
		EventBoResult validResult = eventBoDTO.getEventBoResult();
		
		// 이벤트 당첨
		if (applcnCnt > 0) {
			int prizeCnt = eventService.insertEventPrize(eventBoDTO);
			
			if (prizeCnt > 0) {
				// 스탬프 등록
                eventService.insertEvtStmp(eventBoDTO);
                
				// 포인트 지급
				this.insertWebpnt(validResult);
				
				validResult.setResultCode(EventResultCode.SUCCESS.toString());
				validResult.setResultMessage("100 포인트 적립 되었습니다.");
			}
		}
		return validResult;
	}

	private void insertWebpnt(EventBoResult validResult) throws Exception {

		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
		Mbr mbr = userDetail.getMbr();

		// 온라인회원만 P포인트 지급 대상
		if (mbr.getMbrTpCd().equals(MemberEnum.MemberTpCd.UNITY_MBR.toString())) {

			// P포인트적립
			MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
			mbrWebpntHist.setMbrNo(mbr.getMbrNo());
			mbrWebpntHist.setWebpntTpCd("WEBPNT");
			mbrWebpntHist.setWebpntResnCd("EVT");
			mbrWebpntHist.setWebpntDetailResnCd(validResult.getEvt().getEvtTpCd());
			mbrWebpntHist.setWebpntAmt(validResult.getEvtFreeGiftInfo().getWebpntAmt());
			mbrWebpntHist.setWebpntStatCd("ACCML_DCSN");
			mbrWebpntHist.setUseBegDt(validResult.getEvtFreeGiftInfo().getWebpntUseBegDt());
			mbrWebpntHist.setUseEndDt(validResult.getEvtFreeGiftInfo().getWebpntUseEndDt());
			mbrWebpntHist.setEvtNo(validResult.getEvt().getEvtNo());
			mbrWebpntHist.setResnDscr(validResult.getEvt().getEvtNm());
			mbrWebpntHist.setMallId(getIdGenService().getEnvValue(SysInfoEnum.MALL_ID_SYSTEM_VARIABLE_ID.toString()));

			memberBenefitCommandService.insertWebPoint(mbrWebpntHist);
		}
		else {
			throw new EventOnlineMemberOnlyException(null);
		}
	}
	
	@Override
	public EventBoResult validIconClickRandomJoinEvent(EventBoDTO eventBoDTO) throws Exception {
		EventBoResult result = eventBoDTO.getEventBoResult();
		boolean flag = true;
		
		// 회원의 stamp 누적 횟수 조회
		int count = 0;
		try {
			// stamp 응모 이벤트 번호를 세팅.
			eventBoDTO.setEvtNo(eventBoDTO.getRelateEvtNo());
			count = this.selectEvtStmpCount(eventBoDTO, "MBR");
		}
		catch(Exception e) {
			result.setResultCode(EventResultCode.ERROR.toString());
			result.setResultMessage("이벤트 참여 처리 중 오류가 발생하였습니다. 다시 시도하시길 바랍니다.");
			flag = false;
		}
		
		// 회원의 stamp 누적 횟수 검증
		// 10회 미만이면 참여 대상자가 아님.
		if(flag && count < 10) {
			if(EventResultCode.ERROR.toString().equals(result.getResultCode())) {
				result.setResultCode(EventResultCode.NOT_PARTCPTN_TGT.toString());
				result.setResultMessage("아직 선물 박스를 확인할 수 없습니다.");	
			}
		}
		
		result.setCheckResult(flag);
		
		return result;
	}
	
	@Override
	public EventBoResult iconClickRandomJoinEvent(EventBoDTO eventBoDTO) throws Exception {

		// 이벤트 경품 목록 조회
		EventSearchFoDTO eventSearchFoDTO = new EventSearchFoDTO();
		eventSearchFoDTO.setEvtNo(eventBoDTO.getEvtNo());
		List<EventFreeGiftTurnExtendsFoDTO> freeGiftTurnList = singleEventService.selectFreeGiftTurnListForPvEvent(eventSearchFoDTO);
		
		int totalCount = 0;
		int randomInt = 0;
		int randomIntCompare = 0;
		int freeGiftTurn = 0;
		
		// 경품 랜덤 설정
		if(freeGiftTurnList != null && !freeGiftTurnList.isEmpty()) {
			Random random = new Random();
			// 전체 남은 경품 갯수
			for(EventFreeGiftTurnExtendsFoDTO eventFreeGiftTurnExtendsFoDTO : freeGiftTurnList) {
				totalCount = (int) (totalCount + eventFreeGiftTurnExtendsFoDTO.getFreeGiftInvQty());
			}
			
			if(totalCount == 0) {
				EventBoResult validResult = new EventBoResult();
				validResult.setResultCode(EventResultCode.NO_MORE_WINNER.toString());
				validResult.setResultMessage("모든 경품이 소진되었습니다. 참여해 주셔서 감사합니다.");
				return validResult;
			}
			
			// 랜덤 숫자 생성
			randomInt = random.nextInt((int) totalCount)+1;
			
			// 랜덤 숫자와 비교하여 당첨할 경품 순번 선택
			for(EventFreeGiftTurnExtendsFoDTO eventFreeGiftTurnExtendsFoDTO : freeGiftTurnList) {
				randomIntCompare = (int) (randomIntCompare + eventFreeGiftTurnExtendsFoDTO.getFreeGiftInvQty());
				if(randomInt < randomIntCompare) {
					eventBoDTO.setFreeGiftInfo(eventFreeGiftTurnExtendsFoDTO);
					eventBoDTO.setPrizeRank(eventFreeGiftTurnExtendsFoDTO.getPrizeRank());
					freeGiftTurn = eventFreeGiftTurnExtendsFoDTO.getFreeGiftTurn();
					break;
				}
			}
			
			// 경품이 선택되지 않으면 오류로 처리.
			if(freeGiftTurn == 0) {
				EventBoResult validResult = new EventBoResult();
				validResult.setResultCode(EventResultCode.ERROR.toString());
				validResult.setResultMessage("이벤트 참여 처리 중 오류가 발생하였습니다. 다시 시도하시길 바랍니다.");
				return validResult;
			}
			
			// 중복 방지를 위한 처리로 Exception 발생시에는 해당 경품의 당첨은 다 된 것으로 처리.
			// 시퀀스는 1~4 까지 등록되어 있음.
			// 1: 750, 2: 375, 3: 75, 4: 1800
			try {
				eventSearchFoDTO.setFreeGiftTurn(freeGiftTurn);
				singleEventService.selectNextvalForPvEvent(eventSearchFoDTO);
			}
			catch(Exception e) {
				EventBoResult validResult = new EventBoResult();
				validResult.setResultCode(EventResultCode.NO_MORE_WINNER.toString());
				validResult.setResultMessage("접속자가 많아 당첨 처리가 되지 않았습니다. 다시 시도하시길 바랍니다.");
				return validResult;
			}

		}
		
		// 이벤트 응모
		int applcnCnt = eventService.insertEvtApplcn(eventBoDTO);
		EventBoResult validResult = eventBoDTO.getEventBoResult();
		validResult.setEvtFreeGiftInfo(eventBoDTO.getEventBoResult().getEvtFreeGiftInfo());
		// 이벤트 당첨
		if (applcnCnt > 0) {
			eventBoDTO.getEvtApplcn().setEvtNo(eventBoDTO.getEvtNo());
			eventService.insertEventPrize(eventBoDTO);
		}
		
		if(EventResultCode.NO_MORE_CHANCE.toString().equals(validResult.getResultCode())) {
			validResult.setResultMessage("이미 응모하셨습니다.");
		}
		else {
			validResult.setResultCode(EventResultCode.SUCCESS.toString());
			validResult.setResultMessage(eventBoDTO.getFreeGiftInfo().getFreeGiftNm() + " 경품에 당첨되셨습니다.");
		}
		return validResult;
	}
	
	@Override //온라인 VIP회원 검증 및 이벤트 신청 확인 
	public EventBoResult validYearEndVipGiftJoinEvent(EventBoDTO eventBoDTO) throws Exception {
		EventBoResult result = eventBoDTO.getEventBoResult();
		boolean flag = true;
		
		int count = 0;
		try {
			
			
			MbrGrd mbrGrd = new MbrGrd();
			mbrGrd.setMbrNo(eventBoDTO.getMbrNo());
			mbrGrd.setMallId(eventBoDTO.getMallId());
			
			mbrGrd = memberBaseSelectService.selectMbrGrd(mbrGrd);
			
			String[] evtMbrGrdArr = eventBoDTO.getEvtMbrGrd().split(",");
			
			//등급확인
			for (String evtMbrGrd : evtMbrGrdArr) {
				if (mbrGrd.getOnlneGrdCd().equals(evtMbrGrd)) { //DISCOVERER & EXPLORER 인지 확인
					count = count + 1;
				}else{
					count = count + 0;
				}
            }
			
			// 등급별 이벤트 대상자 확인 검증
			if(flag && count == 0) {
					result.setResultCode(EventResultCode.NOT_PARTCPTN_TGT.toString());
					result.setResultMessage("아쉽지만 회원님은 이벤트 대상자가 아닙니다.");	
					flag = false;
			}else if(flag && count > 0){ 
				
				EventBoResult validResult = eventBoDTO.getEventBoResult();
				validResult.setEvtFreeGiftInfo(eventBoDTO.getEventBoResult().getEvtFreeGiftInfo());
				
				if(EventResultCode.NO_MORE_CHANCE.toString().equals(validResult.getResultCode())) {
					result.setResultCode(EventResultCode.NO_MORE_CHANCE.toString());
					result.setResultMessage("이미 응모하셨습니다.");
					flag = false;
				}else{
					result.setResultCode(EventResultCode.SUCCESS.toString());
					flag = true;
				}
				
			}

		}
		catch(Exception e) {
			result.setResultCode(EventResultCode.ERROR.toString());
			result.setResultMessage("이벤트 참여 처리 중 오류가 발생하였습니다. 다시 시도하시길 바랍니다.");
			flag = false;
		}
		
		result.setCheckResult(flag);
		
		return result;
	}

	@Override
	public boolean validForHappyPoint(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		boolean flag = false;
		
		// 회원의 이벤트 포인트 적립 여부 검증.
		int count = 0;
		try {
			count = singleEventService.selectValidForHappyPoint(eventSearchFoDTO);
			if(count > 0) {
				flag = true;
			}
		}
		catch(Exception e) {
			flag = false;
		}
		
		return flag;
	}
	
	@Override
	public EventBoResult validFnfEmployee(EventBoDTO eventBoDTO) throws Exception {
		
		EventBoResult validResult = new EventBoResult();
		
		String employeeChk = eventBoDTO.getMbr().getMbrAtrbCd();
		
		if(employeeChk.equals("EMP")){
			validResult.setResultCode(EventResultCode.SUCCESS.toString());
			validResult.setResultMessage("입장 가능합니다.");
		}else{
			validResult.setResultCode(EventResultCode.NOT_PARTCPTN_TGT.toString());
			validResult.setResultMessage("인증 받은 임직원 ID만 로그인이 가능합니다.");
		}
		return validResult;
	}
	
	@Override
	public int selectEventCpnCnt(EventBoDTO eventBoDTO) throws Exception {
		
		int result = 0;
		
		try {
			// 회원의 룰렛이벤트 응모권 조회 
			int entryCpnCnt = 1; //기본 이벤트 응모권(로그인시)
			int cstmrPchDcsnCnt = 0; //구매확정 응모권
			int atchFileCnt = 0; //포토리뷰작성 응모권
			int eventPartcptnCnt = 0; //이벤트 참여수
			
			//구매확정 응모권
			cstmrPchDcsnCnt = singleEventService.selectPchDcsnCnt(eventBoDTO); 
			
			//포토리뷰작성 응모권
			atchFileCnt = singleEventService.selectAtchFileCnt(eventBoDTO); 
			
			//이벤트 참여수
			EventBoResult eventBoResult = eventService.selectEventTotalPrize(eventBoDTO);
			eventPartcptnCnt = 	eventBoResult.getApplcnCount();
			
			//남은이벤트응모권 : 기본이벤트응모권 + 구매확정응모권 + 포토리뷰작성수 - 이벤트참여수 
			result = entryCpnCnt + cstmrPchDcsnCnt + atchFileCnt - eventPartcptnCnt;
			
			if(result < 0){
				result = 0;
			}
		}
		catch(Exception e) {
			result = 0;
		}
		
		return result;
	}
	
	
	@Override
	public EventBoResult validRouletteApplcn(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return singleEventService.validRouletteApplcn(eventSearchFoDTO);
	}
	
	@Override
	public EventBoResult spinRouletteEvent(EventBoDTO eventBoDTO) throws Exception {
		
		// 이벤트 경품 목록 조회
		EventSearchFoDTO eventSearchFoDTO = new EventSearchFoDTO();
		eventSearchFoDTO.setEvtNo(eventBoDTO.getEvtNo());
		eventSearchFoDTO.setPrizeRank(eventBoDTO.getPrizeRank());
		List<EventFreeGiftTurnExtendsFoDTO> freeGiftTurnList = singleEventService.selectFreeGiftTurnListForRouletteEvent(eventSearchFoDTO);
		
		int totalCount = 0;
		int randomInt = 0;
		int randomIntCompare = 0;
		int freeGiftTurn = 0;
		int applcnCnt = 0;
		String freeGiftKndCd = "";
		
		// 경품 랜덤 설정
		if(freeGiftTurnList != null && !freeGiftTurnList.isEmpty()) {
			Random random = new Random();
			// 전체 남은 경품 갯수
			for(EventFreeGiftTurnExtendsFoDTO eventFreeGiftTurnExtendsFoDTO : freeGiftTurnList) {
				totalCount = (int) (totalCount + eventFreeGiftTurnExtendsFoDTO.getFreeGiftInvQty());
			}
			
			if(totalCount == 0) {
				EventBoResult validResult = new EventBoResult();
				validResult.setResultCode(EventResultCode.NO_MORE_WINNER.toString());
				validResult.setResultMessage("모든 경품이 소진되었습니다. 참여해 주셔서 감사합니다.");
				return validResult;
			}
			
			// 랜덤 숫자 생성
			randomInt = random.nextInt((int) totalCount)+1;
			
			Collections.shuffle(freeGiftTurnList);

			// 랜덤 숫자와 비교하여 당첨할 경품 순번 선택
			for(EventFreeGiftTurnExtendsFoDTO eventFreeGiftTurnExtendsFoDTO : freeGiftTurnList) {
				randomIntCompare = (int) (randomIntCompare + eventFreeGiftTurnExtendsFoDTO.getFreeGiftInvQty());
				if(randomInt <= randomIntCompare) {
					
					freeGiftTurn = eventFreeGiftTurnExtendsFoDTO.getFreeGiftTurn();
					freeGiftKndCd = eventFreeGiftTurnExtendsFoDTO.getFreeGiftKndCd();
					eventBoDTO.setFreeGiftInfo(eventFreeGiftTurnExtendsFoDTO);
					eventBoDTO.setPrizeRank(eventFreeGiftTurnExtendsFoDTO.getPrizeRank());
					
					break;
				}
			}			
			
			// 경품이 선택되지 않으면 오류로 처리.
			if(freeGiftTurn == 0) {
				EventBoResult validResult = new EventBoResult();
				validResult.setResultCode(EventResultCode.ERROR.toString());
				validResult.setResultMessage("이벤트 참여 처리 중 오류가 발생하였습니다. 다시 시도하시길 바랍니다.");
				return validResult;
			}
			
			// 중복 방지를 위한 처리로 Exception 발생시에는 해당 경품의 당첨은 다 된 것으로 처리.
			// 시퀀스는 1~8 까지 등록되어 있음.
			// 1: 7, 2: 14, 3: 56, 4: 84, 5:140, 6:210, 7:350, 8:1939
			try {
				eventSearchFoDTO.setFreeGiftTurn(freeGiftTurn);
				singleEventService.selectNextvalForPvEvent(eventSearchFoDTO);
			}
			catch(Exception e) {
				EventBoResult validResult = new EventBoResult();
				validResult.setResultCode(EventResultCode.NO_MORE_WINNER.toString());
				validResult.setResultMessage("접속자가 많아 당첨 처리가 되지 않았습니다. 다시 시도하시길 바랍니다.");
				return validResult;
			}

		}
		
		// 이벤트 응모
		eventSearchFoDTO.setMbrNo(eventBoDTO.getMbrNo());
		eventSearchFoDTO.setAtendDaycnt(eventBoDTO.getAtendDaycnt());
		applcnCnt = singleEventService.insertEvtRouletteApplcn(eventSearchFoDTO);
	
		EventBoResult validResult =  eventService.validEventApplcn(eventBoDTO);
		
		// 이벤트 당첨
		if (applcnCnt > 0) {
			EventBoResult prizeResult = singleEventService.insertEvtRoulettePrize(eventSearchFoDTO);
			
			if(prizeResult.isCheckResult()){
				//경품이 웹포인트일 경우 포인트 지급
				if(EventFreeGiftKind.WEB_POINT.toString().equals(freeGiftKndCd)){
					this.insertWebpnt(validResult);
				}
				validResult.setResultCode(EventResultCode.SUCCESS.toString());
				validResult.setResultMessage(eventBoDTO.getFreeGiftInfo().getFreeGiftNm() + " 경품에 당첨되셨습니다.");
			}
			
		}
		
		return validResult;
	}
	
	
	
	@Override
	public EventBoResult reqStockInformEvent(EventBoDTO eventBoDTO) throws Exception {
		
		int applcnCnt = 0;
		
		// 이벤트 응모 및 입고알림 신청처리
		EventSearchFoDTO eventSearchFoDTO = new EventSearchFoDTO();
		eventSearchFoDTO.setMbrNo(eventBoDTO.getMbrNo());
		eventSearchFoDTO.setAtendDaycnt(eventBoDTO.getAtendDaycnt());
		eventSearchFoDTO.setEvtNo(eventBoDTO.getEvtNo());
		applcnCnt = singleEventService.insertStockInformRequest(eventSearchFoDTO);
	
		EventBoResult validResult =  eventService.validEventApplcn(eventBoDTO);
		
		validResult.setResultCode(EventResultCode.SUCCESS.toString());
		validResult.setResultMessage("상품입고알림 신청이 완료되었습니다.");

		
		return validResult;
	}
	
	
	@Override
	public EventBoResult earlyBirdEvent(EventBoDTO eventBoDTO) throws Exception {
		
		
		// 이벤트 응모
		EventSearchFoDTO eventSearchFoDTO = new EventSearchFoDTO();
		eventSearchFoDTO.setMbrNo(eventBoDTO.getMbrNo());
		eventSearchFoDTO.setAtendDaycnt(1);
		eventSearchFoDTO.setEvtNo(eventBoDTO.getEvtNo());
		eventSearchFoDTO.setPhoneMobile(eventBoDTO.getMbr().getMbrNm() +'/'+eventBoDTO.getPhoneMobile());
		
		EventBoResult validResult = new EventBoResult();
		validResult = singleEventService.insertEarlyBirdEvent(eventSearchFoDTO);
	
		
		return validResult;
	}

	@Override
	public EventBoResult snsShareEvent(EventBoDTO eventBoDTO) throws Exception {
		
		EventSearchFoDTO eventSearchFoDTO = new EventSearchFoDTO();
		eventSearchFoDTO.setMbrNo(eventBoDTO.getMbrNo());
		eventSearchFoDTO.setEvtNo(eventBoDTO.getEvtNo());
		eventSearchFoDTO.setPhoneMobile(eventBoDTO.getPhoneMobile());
		
		EventBoResult validResult = new EventBoResult();
		validResult = singleEventService.insertSnsShareEvent(eventSearchFoDTO);
	
		
		return validResult;
	}
	
	@Override
	public EventBoResult relayGiftEvent(EventBoDTO eventBoDTO) throws Exception {
		
		EventSearchFoDTO eventSearchFoDTO = new EventSearchFoDTO();
		eventSearchFoDTO.setMbrNo(eventBoDTO.getMbrNo());
		eventSearchFoDTO.setEvtNo(eventBoDTO.getEvtNo());
		eventSearchFoDTO.setPhoneMobile(eventBoDTO.getPhoneMobile());
		eventSearchFoDTO.setBegDt(eventBoDTO.getBegDt());
		eventSearchFoDTO.setEndDt(eventBoDTO.getEndDt());
		
		EventBoResult validResult = new EventBoResult();
		validResult = singleEventService.insertRelayGiftEvent(eventSearchFoDTO);
	
		
		return validResult;
	}
	
	@Override
	public EventBoResult CouponIssueByCpnPrmNo(EventBoDTO eventBoDTO) throws Exception {

		// 이벤트 응모
		EventSearchFoDTO eventSearchFoDTO = new EventSearchFoDTO();
		eventSearchFoDTO.setMbrNo(eventBoDTO.getMbrNo());
		eventSearchFoDTO.setAtendDaycnt(1);
		eventSearchFoDTO.setEvtNo(eventBoDTO.getEvtNo());
		eventSearchFoDTO.setEvtDuplctYn(eventBoDTO.getEvtDuplctYn());
		eventSearchFoDTO.setPhoneMobile(eventBoDTO.getMbr().getMbrNm() +'/'+eventBoDTO.getPhoneMobile());
		
		EventBoResult validResult = new EventBoResult();
		validResult = singleEventService.insertEventApplcn(eventSearchFoDTO);
		
		if(EventResultCode.SUCCESS.toString().equals(validResult.getResultCode())) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			String mbrNo = userDetail.getMbr().getMbrNo();
			
			
			
			String[] cpnPrmNmArr = eventBoDTO.getCpnPrmNm().split(",");
			
			
			for (String cpnPrmNm : cpnPrmNmArr) {
				
				//쿠폰 정보 
				eventBoDTO.setCpnPrmNm(cpnPrmNm);
				PrmCpn prmCpn= singleEventService.selectPrmCpn(eventBoDTO);
				
				MbrIssuCpn cpn = new MbrIssuCpn();
				cpn.setPrmNo(cpnPrmNm); 
				cpn.setMbrNo(mbrNo);
				cpn.setUdterId(mbrNo);
				cpn.setRegtrId(mbrNo);
				cpn.setValidBegDate(prmCpn.getCpnUsePdBegDate());
				cpn.setValidEndDate(prmCpn.getCpnUsePdEndDate());
				promotionService.insertCouponIssue(cpn);
				
				// 쿠폰 발급 재고 수량 update
				//prmCpn.setPrmNo(cpnPrmNm); 
				promotionService.updateIssueCouponInvQty(prmCpn);
				
			}
			
		}
		
		return validResult;
	}
	
	
}
