package com.plgrim.ncp.cmp.promotion.fo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.plgrim.ncp.biz.promotion.data.EventBoDTO;
import com.plgrim.ncp.biz.promotion.data.EventExtendsFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventFreeGiftTurnExtendsFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventSearchFoDTO;
import com.plgrim.ncp.biz.promotion.result.EventBoResult;

/**
 * 1회성 이벤트들을 위한 컴포넌트로 참고용으로 미삭제.
 * 참고만 하고 필요시 PromotionEventFOComponent에 추가 생성하여 사용하여야 함.
 * 
 * @author Seed
 *
 */
@Component
public interface PromotionSingleEventFOComponent {
	
	public Map<String,String> insertEventfcfs(EventSearchFoDTO searchDTO)  ;

	public Map<String,String> addEventfcfs(EventSearchFoDTO searchDTO)  ;
	
	public int selectfcfsDoubleWinningCheck(EventSearchFoDTO searchDTO) throws Exception;

	public int selectEventPeriodNewMemberInquiry(EventSearchFoDTO searchDTO) throws Exception;

	public int selectfcfsWinnerCountCheck(EventSearchFoDTO eventSearchFoDTO) throws Exception;

	public EventExtendsFoDTO selectEventLwprt(EventSearchFoDTO eventSearchFoDTO) throws Exception;
	
	
	public int selectEventFirstPurchase(EventSearchFoDTO eventSearchFoDTO) throws Exception;
	
	public int selectEventRepurchase(EventSearchFoDTO eventSearchFoDTO) throws Exception;
	
	public Map<String,String> insertEventRenewal(EventSearchFoDTO eventSearchFoDTO)  ;
 
	public Map<String,String> addEventRenewal(EventSearchFoDTO eventSearchFoDTO)  ;
	
	/**
	 * 스탬프 건수 조회
	 * 
	 * @param eventBoDTO
	 * @param total : TOTAL OR MBR(TOTAL : 금일 전체 응모 건수 조회, MBR : 회원의 응모 건수 조회)
	 * @return int
	 * @throws Exception
	 */
	public int selectEvtStmpCount(EventBoDTO eventBoDTO, String type) throws Exception;
	
	/**
	 * 화면에 랜덤 노출 이벤트 조회
	 * - PV 이벤트
	 * 
	 * @param eventSearchFoDTO
	 * @return String
	 */
	public String selectSpecialEventForPvEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception;
	
	/**
	 * PV 이벤트 당첨 경품 조회
	 * 
	 * @param eventSearchFoDTO
	 * @return EventFreeGiftTurnExtendsFoDTO
	 */
	public EventFreeGiftTurnExtendsFoDTO selectPrizeInfoForPvEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception;
	
	/**
	 * PV 이벤트 경품 목록 조회
	 * 
	 * @param eventSearchFoDTO
	 * @return List<EventFreeGiftTurnExtendsFoDTO>
	 */
	public List<EventFreeGiftTurnExtendsFoDTO> selectFreeGiftTurnListForPvEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception;
	
	/**
	 * 공유 PV 이벤트 당첨 처리 검증
	 * - 포인트
	 * 
	 * @param eventBoDTO
	 * @return EventBoResult
	 * @throws Exception
	 */
	public EventBoResult validIconClickJoinEvent(EventBoDTO eventBoDTO) throws Exception;
	
	/**
	 * 공유 PV 이벤트 당첨 처리
	 * - 포인트
	 * 
	 * @param eventBoDTO
	 * @return EventBoResult
	 * @throws Exception
	 */
	public EventBoResult iconClickJoinEvent(EventBoDTO eventBoDTO) throws Exception;
	
	/**
	 * 공유 PV 이벤트 당첨 처리
	 * - 기프트박스
	 * 
	 * @param eventBoDTO
	 * @return EventBoResult
	 * @throws Exception
	 */
	public EventBoResult validIconClickRandomJoinEvent(EventBoDTO eventBoDTO) throws Exception;
	
	/**
	 * 공유 PV 이벤트 당첨 처리
	 * - 기프트콘
	 * 
	 * @param eventBoDTO
	 * @return EventBoResult
	 * @throws Exception
	 */
	public EventBoResult iconClickRandomJoinEvent(EventBoDTO eventBoDTO) throws Exception;
	
	/**
	 * 연말연시 이벤트 당첨 처리 검증
	 * - 기프트콘
	 * 
	 * @param eventBoDTO
	 * @return EventBoResult
	 * @throws Exception
	 */
	public EventBoResult validYearEndVipGiftJoinEvent(EventBoDTO eventBoDTO) throws Exception;
	
	/**
	 * 연말정산 Happy 포인트 이벤트 알림 노출 여부 조회
	 * 
	 * @param eventSearchFoDTO
	 * @return boolean
	 * @throws Exception
	 */
	public boolean validForHappyPoint(EventSearchFoDTO eventSearchFoDTO) throws Exception;
	
	/**
	 * 온라인 Family sale 이벤트 임직원 검증
	 * 
	 * @param EventBoDTO
	 * @return boolean
	 * @throws Exception
	 */
	public EventBoResult validFnfEmployee(EventBoDTO eventBoDTO) throws Exception;
	
	/**
	 * 룰렛이벤트 응모 가능 횟수 조회
	 * 
	 * @param EventBoDTO
	 * @return boolean
	 * @throws Exception
	 */
	public int selectEventCpnCnt(EventBoDTO eventBoDTO) throws Exception;
	
	/**
	 * 룰렛이벤트 해당일 선착순 마감 조회
	 * 
	 * @param eventBoDTO
	 * @return EventBoResult
	 * @throws Exception
	 */
	public EventBoResult validRouletteApplcn(EventSearchFoDTO eventSearchFoDTO) throws Exception;
	
	/**
	 * 룰렛이벤트 당첨 처리
	 * 
	 * @param eventBoDTO
	 * @return EventBoResult
	 * @throws Exception
	 */
	public EventBoResult spinRouletteEvent(EventBoDTO eventBoDTO) throws Exception;
	
	/**
	 * 헤리티지 모자 이벤트 신청 처리
	 * 
	 * @param eventBoDTO
	 * @return EventBoResult
	 * @throws Exception
	 */
	public EventBoResult reqStockInformEvent(EventBoDTO eventBoDTO) throws Exception;
	
	/**
	 * 디커 선판매 알림 이벤트 신청 처리
	 * 
	 * @param eventBoDTO
	 * @return EventBoResult
	 * @throws Exception
	 */
	public EventBoResult earlyBirdEvent(EventBoDTO eventBoDTO) throws Exception;
	
	/**
	 * 디커 SNS 공유 이벤트 신청 처리
	 * 
	 * @param eventBoDTO
	 * @return EventBoResult
	 * @throws Exception
	 */
	public EventBoResult snsShareEvent(EventBoDTO eventBoDTO) throws Exception;
	
	/**
	 * 릴레이 이벤트 응모 처리
	 * 
	 * @param eventBoDTO
	 * @return EventBoResult
	 * @throws Exception
	 */
	public EventBoResult relayGiftEvent(EventBoDTO eventBoDTO) throws Exception;
	
	/**
	 * 쿠폰 다운로드 처리
	 * 
	 * @param eventBoDTO
	 * @return EventBoResult
	 * @throws Exception
	 */
	public EventBoResult CouponIssueByCpnPrmNo(EventBoDTO eventBoDTO) throws Exception;
		
}
