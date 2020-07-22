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
 * @since       2015. 11. 12       
 */
package com.plgrim.ncp.cmp.promotion.fo;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import com.plgrim.ncp.base.entities.datasource1.evt.EvtSnsReply;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSns;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.biz.promotion.data.EventBoDTO;
import com.plgrim.ncp.biz.promotion.data.EventExtendsFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventFreeGiftTurnExtendsFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventSearchFoDTO;
import com.plgrim.ncp.biz.promotion.data.EvtApplcnBoDTO;
import com.plgrim.ncp.biz.promotion.data.GoodsListFoDTO;
import com.plgrim.ncp.biz.promotion.data.SingleEventResultDto;
import com.plgrim.ncp.biz.promotion.result.EventAtendResult;
import com.plgrim.ncp.biz.promotion.result.EventBoResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

/**
 * 이벤트 Command Component
 * @author js
 *
 */
public interface PromotionEventFOComponent {

	/**
	 * [FO]전시 이벤트 리스트
	 * 
	 * @param searchDTO
	 * @return
	 * @throws Exception
	 */
	public List<EventExtendsFoDTO> selectEventList(EventSearchFoDTO searchDTO) throws Exception;
	
	/**
	 * [FO]전시 종료 이벤트 리스트
	 * 
	 * @param searchDTO
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<EventExtendsFoDTO> selectEventEndList(EventSearchFoDTO searchDTO, PageParam pageParam) throws Exception;
	
	/**
	 * 구분 타이틀 단위의 연관상품 목록 조회
	 * @param searchDTO
	 * @return
	 */
	public  List<GoodsListFoDTO> selectEventGodList(EventSearchFoDTO searchDTO);
	
	/**
	 * 이벤트 카운트
	 * @param searchDTO
	 * @return
	 */
	public long selectEventCount(EventSearchFoDTO searchDTO);
	
	/**
	 * [FO]전시 이벤트 상세보기
	 * 
	 * @param searchDTO
	 * @return
	 * @throws Exception
	 */
	public EventFoDTO selectEventView(EventSearchFoDTO searchDTO) throws Exception;
	
	/**
	 * [FO-MO]전시 이벤트 상세보기
	 * @param searchDTO
	 * @return
	 * @throws Exception
	 */
	public EventFoDTO selectEventViewMobile(EventSearchFoDTO searchDTO) throws Exception;
	
	/**
	 * [FO]SNS 댓글 목록
	 * 
	 * @param searchDTO
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<EvtSnsReply>  selectSnsReplyList(EventSearchFoDTO searchDTO, PageParam pageParam) throws Exception;
	
	/**
	 * 이벤트 목록
	 * 
	 * @param eventBoDTO
	 * @return
	 * @throws Exception
	 */
	public List<EventBoResult> selectEventList(EventBoDTO eventBoDTO) throws Exception;

	/**
	 * 이벤트 목록 조회(페이징 없음)
	 * 
	 * <p/>
	 * 
	 * 모바일에서 사용
	 *
	 * @param searchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 1
	 */
	public List<EventExtendsFoDTO> selectEventListNoPaging(EventSearchFoDTO searchDTO) throws Exception;

    /**
     * [FO]전시 이벤트 기본 정보
     * 
     * @param model
     * @param request
     * @param searchDTO
     * @since 2015. 8. 28
     */
    public EventFoDTO selectEventBaseInfo(EventSearchFoDTO searchDTO) throws Exception;
    
    /**
     * 이벤트 경품 목록 가져오기
     * 
     * @param model
     * @param request
     * @param searchDTO
     * @since 2015. 8. 28
     */
    public List<EventFreeGiftTurnExtendsFoDTO> selecEvtFreeGiftList(EventSearchFoDTO searchDTO) throws Exception;

    /**
     * 경품(쿠폰) 재고 잔여 수량
     * 
     * @param model
     * @param request
     * @param searchDTO
     * @since 2015. 8. 28
     */
    public String seleceventCouponDownIssueCheck(EventSearchFoDTO searchDTO) throws Exception;
    
    /**
     * 이벤트 참여 유효성 체크
     * 
     * @param eventBoDTO
     * @return
     * @throws Exception
     */
    public EventBoResult validEventPartcptn(EventBoDTO eventBoDTO, SystemPK systemPK) throws Exception;
    
    /**
     * 이벤트 총 당첨정보 조회
     * 
     * @param eventBoDTO
     * @return
     * @throws Exception
     */
    public EventBoResult selectEventTotalPrize(EventBoDTO eventBoDTO) throws Exception;
    
    /**
     * 이벤트 참여 로그인 여부 체크
     * 
     * @return String
     *   - NOT_LOGIN        : 비로그인
     *   - SUCCESS          : 성공
     * 
     * @throws Exception
     */
    public String validLogin() throws Exception;
    
    /**
     * 이벤트 결과코드 메시지 설정
     * 
     * @param request
     * @param systemPK
     * @param result
     * @throws Exception
     */
    public void extractResultMessageSett(HttpServletRequest request, EventBoResult result) throws Exception;
    
    /**
     * 이벤트 결과코드 메시지 설정(한번에 받기 기능에서 사용)
     * 
     * @param request
     * @param resultList
     * @param returnResult
     * @throws Exception
     */
    public EventBoResult extractResultMessageSettMulti(HttpServletRequest request, List<EventBoResult> resultList, EventBoResult returnResult) throws Exception;
    
	/**
	 * 이벤트 약관 조회
	 * @param sysStplat
	 * @return
	 * @throws Exception
	 */
	public List<SysStplat> selectStplatList(String sysStplat) throws Exception;

	/**
	 * 이벤트 응모가능여부
	 * 
	 */
	public String selectCampaginEvtInfo(String evtNo) throws Exception;
	
	/**
	 * 이벤트 경품 목록 조회
	 */
	public List<EventFreeGiftTurnExtendsFoDTO> selectFreeGiftTurnList(EventSearchFoDTO eventSearchFoDTO) throws Exception;
	
	/**
	 * 성명, ID 등 마스킹 처리 후 반환(FN_MASKING 사용)
	 * 
	 * @param paramMap maskingType, sourceString, maskingYn
	 * paramMap.maskingType		- 'FLNM':성명, 'ID':ID
	 * paramMap.sourceString	- 마스킹할 string
	 * paramMap.maskingYn		- 마스킹 처리 여부 'Y', 'N'
	 * @return
	 * @throws Exception
	 */
	public String selectFnMakingData(Map<String,String> paramMap) throws Exception;
	
	/**
	 * 이벤트 응모여부 체크 
	 * 
	 * @param searchDTO
	 * @return
	 */
	public String checkEventApplCn(EventSearchFoDTO searchDTO) throws Exception;
	
	/**
	 * 출석 등록 
	 * @param evtNo
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public EventAtendResult insertEventAtend(String evtNo, String mbrNo) throws Exception;
	
	/**
	 * 이벤트 응모 가능 여부 체크
	* <pre>
	* 유입경로, 회원유형으로 이벤트 응모 가능 한지 체크
	* </pre>
	* @param evtNo
	* @param mbrNo
	* @param pk
	* @return
	* @throws Exception
	* @since 2015. 6. 26.
	 */
	public Map<String,String> checkEventTarget(String evtNo, String mbrNo, SystemPK pk) throws Exception;
	
	/**
	 * 이벤트 응모 가능 여부 체크
	 * 
	 * <pre>
	 * 이벤트 상태, 응모기간이 유효한지 체크
	 * </pre>
	 * 
	 * @param evtNo
	 * @param mbrNo
	 * @param pk
	 * @return
	 * @throws Exception
	 * @since 2015. 6. 26.
	 */
	public boolean checkEventEnable(String evtNo) throws Exception;
	
	/**
	 * 쿠폰 다운로드
	 * @param evtNo
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public String downloadCpn(String evtNo, String mbrNo, int freeGiftTurn, int prizeRank) throws Exception;
	
	/**
	 * SNS 댓글 쓰기
	 * @param reply
	 */
	public void insertReply(EvtSnsReply reply, String mbrNo) throws Exception;
	
	/**
     * 이벤트 당첨  insert
     * 
     * @param EvtApplcnBoDTO
     * @return
     * @throws Exception
     */
	public int insertEventPrize(SystemPK systemPK, EventBoDTO eventBoDTO) throws Exception;
    
    /**
     * 응모자 등록
     * 
     * @param systemPK
     * @param applcnBoDTO
     * @return
     * @throws Exception
     */   
    public int insertEvtApplCn(SystemPK systemPK, EvtApplcnBoDTO applcnBoDTO) throws Exception;

    /**
     * 스탬프 이벤트 응모
     * @return 
     * 
     * @throws Exception
     */
    public EventBoResult insertEventStampApplcn(EventBoDTO eventBoDTO, SystemPK systemPK) throws Exception;
    
    /**
     * 이벤트 참여
     * 
     * @param systemPK
     * @param paramDTO
     * @return
     * @throws Exception
     */
    public EventBoResult insertPartcptnEvent(EventBoDTO eventBoDTO) throws Exception;
    
    /**
     * SNS 인증정보 token 값 세팅
     * 
     * @param mbrNo
     * @param model
     * @param request
     * @throws Exception
     */
	public void setModelMbrSnsInfo(String mbrNo, Model model, HttpServletRequest request) throws Exception;

	/**
	 * SNS 인증정보 등록
	 * 
	 * @param mbrSns
	 * @return
	 * @throws Exception
	 */
	public int insertMbrSnsMerge(MbrSns mbrSns) throws Exception;

	/**
	 * SNS 인증정보 리스트 조회
	 * 
	 * @param mbrSns
	 * @return
	 * @throws Exception
	 */
    public List<MbrSns> selectMbrSnsList(MbrSns mbrSns) throws Exception;

    /**
     * 최종사용 SNS 여부 update
     * 
     * @param mbrSns
     * @return
     * @throws Exception
     */
	public int updateMbrSnsLastUseYn(MbrSns mbrSns) throws Exception;

	/**
	 * 여러장의 쿠폰다운로드
	 * 
	 * @param systemPK
	 * @param eventBoDTO
	 * @param mbr
	 * @return
	 * @throws Exception
	 */
	public SingleEventResultDto insertMultipleCouponIssued(SystemPK systemPK, EventBoDTO eventBoDTO, Mbr mbr) throws Exception;

} 
