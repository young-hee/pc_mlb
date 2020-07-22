package com.plgrim.ncp.biz.promotion.repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtAplGod;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtAtend;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtDspTgt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftAplPd;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftInfo;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtImg;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPartcptnTgt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPartcptnTgtMbr;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPrize;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPrizeFreeGift;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtRelateGod;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtSnsReply;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtSprtr;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtSprtrLang;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtStmp;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtStmpExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSns;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.repository.evt.EvtRepository;
import com.plgrim.ncp.biz.promotion.data.EventApplyGoodBoDTO;
import com.plgrim.ncp.biz.promotion.data.EventBoDTO;
import com.plgrim.ncp.biz.promotion.data.EventExtendsFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventFreeGiftTurnExtendsFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventSearchFoDTO;
import com.plgrim.ncp.biz.promotion.data.EvtApplcnBoDTO;
import com.plgrim.ncp.biz.promotion.data.GoodsListFoDTO;
import com.plgrim.ncp.biz.promotion.result.EventApplcnBoResult;
import com.plgrim.ncp.biz.promotion.result.EventApplyGoodBoResult;
import com.plgrim.ncp.biz.promotion.result.EventBoResult;
import com.plgrim.ncp.biz.promotion.result.EventPrizeBoResult;
import com.plgrim.ncp.biz.promotion.result.PrizeFreeGiftResult;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;

/**
 * 이벤트 Repository
 * @author js
 *
 */
@Repository
public class EventRepository extends EvtRepository{

	

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
    private IdGenService idGenService;
	
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
	 * 이벤트 응모 자격 조건 조회
	* <pre>
	*
	* </pre>
	* @param evtNo
	* @since 2015. 6. 26.
	 */
	public List<EvtPartcptnTgt> selectEvtPartcptnTgtList(String evtNo){
		return getSession1().selectList("com.plgrim.ncp.biz.promotion.selectEvtPartcptnTgtList", evtNo);
	}
	/**
	 * 이벤트 목록 조회
	 * @param searchDTO
	 * @param pageParam
	 * @return
	 */
	public List<EventExtendsFoDTO> selectEventList(EventSearchFoDTO searchDTO) {
		List<EventExtendsFoDTO> list = getSession1().selectList("com.plgrim.ncp.biz.promotion.selectEventList", searchDTO);
		return list;

	}
	
	/**
	 * 이벤트 목록 조회 V2
	 * @param searchDTO
	 * @param pageParam
	 * @return
	 */
	public Page<EventExtendsFoDTO> selectV2EventList(EventSearchFoDTO searchDTO, PageParam pageParam) {
		RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
		List<EventExtendsFoDTO> list = getSession1().selectList("com.plgrim.ncp.biz.promotion.selectEventList", searchDTO);
		
		long totalRow = selectEventCount(searchDTO);
		
		return new PageImpl<EventExtendsFoDTO>(list, pageParam.getPageable(), totalRow);
		
	}
	
	/**
	 * 이벤트 목록 조회
	 * @param searchDTO
	 * @param pageParam
	 * @return
	 */
	public Page<EventExtendsFoDTO> selectEventEndList(EventSearchFoDTO searchDTO, PageParam pageParam) {
		RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
		List<EventExtendsFoDTO> list = getSession1().selectList("com.plgrim.ncp.biz.promotion.selectEventEndList", searchDTO);

		long totalRow = selectEventCount(searchDTO);

		return new PageImpl<EventExtendsFoDTO>(list, pageParam.getPageable(), totalRow);

	}

	/**
	 * 이벤트 목록 조회 ( 페이징 없음)
	 * @param searchDTO
	 * @return
	 */
	public List<EventExtendsFoDTO> selectEventListNoPaging(EventSearchFoDTO searchDTO) {
		return  getSession1().selectList("com.plgrim.ncp.biz.promotion.selectEventListNoPaging", searchDTO);
	}
	/**
	 * 이벤트 목록 카운트
	 * @param searchDTO
	 * @return
	 */
	public long selectEventCount(
            EventSearchFoDTO searchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectEventCount",searchDTO);
    }
	
	/**
	 * 이벤트 상세 조회
	 * @param searchDTO
	 * @return
	 */
	public EventExtendsFoDTO selectEvent(EventSearchFoDTO searchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectEvent",searchDTO);
    }
	/**
	 * [출석체크]참여 목록 조회
	 * @param searchDTO
	 * @return
	 */
	public List<Integer> selectEventAtend(EventSearchFoDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.promotion.selectEventAtend",searchDTO);
    }
	/**
	 * 경품 목록 조회
	 * @param searchDTO
	 * @return
	 */
	public List<EventFreeGiftTurnExtendsFoDTO> selectFreeGiftTurnList(EventSearchFoDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.promotion.selectFreeGiftTurnList",searchDTO);
    }

	/**
	 * 이벤트 경품 적용기간별 목록 조회
	 * @param searchDTO
	 * @return
	 */
	public List<EventFreeGiftTurnExtendsFoDTO> selectFreeGiftTimeList(EventSearchFoDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.promotion.selectFreeGiftTimeList", searchDTO);
	}

	/**
	 * 이벤트 연관 상품 목록 조회
	 * @param searchDTO
	 * @return
	 */
	public List<GoodsListFoDTO> selectEventGoodsList(EventSearchFoDTO searchDTO) {
		return  getSession1().selectList("com.plgrim.ncp.biz.promotion.selectEventGoodsList", searchDTO);
	}
	/**
	 * 이벤트 구분 타이틀 목록 조회
	 * @param searchDTO
	 * @return
	 */
	public List<EvtSprtr> selectEvtSprtrList(EventSearchFoDTO searchDTO) {
		return  getSession1().selectList("com.plgrim.ncp.biz.promotion.selectEvtSprtrList", searchDTO);
	}
	/**
	 * [출석체크]출석 중복 체크
	 * @param evtNo
	 * @param mbrNo
	 * @return true 이면 출석 가능, false이면 출석 중복
	 */
	public boolean checkEventAtend(String evtNo, String mbrNo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("evtNo", evtNo);
		map.put("mbrNo", mbrNo);
		String result = getSession1().selectOne(
		        "com.plgrim.ncp.biz.promotion.checkEventAtend", map);

		return StringUtils.isNotEmpty(result) ? false : true;
	}
	
	/**
	 * [타임쿠폰] 응모 중복 체크
	 * @param evtNo
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public boolean checkEvtApplcnToday(EventSearchFoDTO searchDTO) throws Exception {
		String result = getSession1().selectOne(
		        "com.plgrim.ncp.biz.promotion.checkEvtApplcnToday", searchDTO);
		return StringUtils.isNotEmpty(result) ? false : true;
	}
	/**
	 * 경품 남은 수량 조회
	 * @param evtNo
	 * @param freeGiftTurn
	 * @return
	 * @throws Exception
	 */
	public int selectFreeGiftInvQty(String evtNo, int freeGiftTurn) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("evtNo", evtNo);
		map.put("freeGiftTurn", freeGiftTurn);		
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectFreeGiftInvQty", map);
	}
	
	/**
	 * SNS 댓글 목록 조회
	 * @param evtNo
	 * @return
	 * @throws Exception
	 */
	public Page<EvtSnsReply> selectSnsReplyList(EventSearchFoDTO searchDTO, PageParam pageParam) {
		
		RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
		List<EvtSnsReply> list = getSession1().selectList("com.plgrim.ncp.biz.promotion.selectSnsReplyList", searchDTO);

		long totalRow = selectSnsReplyListCount(searchDTO);

		return new PageImpl<EvtSnsReply>(list, pageParam.getPageable(), totalRow);
		
	}	
	
	/**
	 * SNS 댓글 목록 카운트
	 * @param evtNo
	 * @return
	 * @throws Exception
	 */
	private long selectSnsReplyListCount(EventSearchFoDTO searchDTO) {
		
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectSnsReplyListCount", searchDTO);

	}	
	/**
	 * [출석체크]출석 등록
	 * @param evtAtend
	 */
	public void insertEventAtend(EvtAtend evtAtend){
		getSession1().insert( "com.plgrim.ncp.biz.promotion.insertEvtAtend", evtAtend);
	}
	/**
	 * 경품 재고 감소
	 * @param info
	 */
	public void updateFreeGiftInvQty(String evtNo, int freeGiftTurn) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("evtNo", evtNo);
		map.put("freeGiftTurn", freeGiftTurn);
		getSession1().update("com.plgrim.ncp.biz.promotion.updateFreeGiftInvQty",map);
	}

	/**
	 * 이벤트 경품 적용기간 경품수량 감소
	 * @param evtFreeGiftInfo
	 * @return
	 */
	public int updateEvtFreeGiftAplPd(String evtNo, int freeGiftTurn) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("evtNo", evtNo);
		map.put("freeGiftTurn", freeGiftTurn);
		int resultCnt = getSession1().update("com.plgrim.ncp.biz.promotion.updateEvtFreeGiftAplPd", map);
		return resultCnt;
	}

	/**
	 * 경품재고수량 적용기간 반영 update
	 * @param evtFreeGiftInfo
	 * @return
	 */
	public int updateFreeGiftInvQtyAplPd(EvtFreeGiftInfo evtFreeGiftInfo) throws Exception {
		int resultCnt = getSession1().update("com.plgrim.ncp.biz.promotion.updateFreeGiftInvQtyAplPd", evtFreeGiftInfo);
		return resultCnt;
	}

	/**
	 * 타임쿠폰 경품지급 건 COUNT
	 * @param evtNo
	 * @return
	 */
	public int getFreeGiftIssuCount(String evtNo, Integer freeGiftTurn) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("evtNo", evtNo);
		map.put("freeGiftTurn", freeGiftTurn);
		int resultCnt = getSession1().selectOne("com.plgrim.ncp.biz.promotion.getFreeGiftIssuCount", map);
		return resultCnt;
	}

	/**
	 * 이벤트경품적용기간 삭제
	 * @param evtFreeGiftAplPd
	 * @return
	 * @throws Exception
	 */
	public int deleteEvtFreeGiftAplPd(EvtFreeGiftAplPd evtFreeGiftAplPd) throws Exception {
		int resultCnt = getSession1().delete("com.plgrim.ncp.biz.promotion.deleteEvtFreeGiftAplPd", evtFreeGiftAplPd);
		return resultCnt;
	}

	/**
	 * 이벤트 응모 가능여부 체크
	 * @param info
	 */
	public boolean getEvtApplcnEnable(String evtNo){
		String result = getSession1().selectOne("com.plgrim.ncp.biz.promotion.getEvtApplcnEnable", evtNo);
		return StringUtils.isNotEmpty(result)? true : false;
	}
	/**
	 * 타임쿠폰 경품별 당일 당첨수 조회
	 * @param evtNo
	 * @param freeGiftTurn
	 * @return
	 */
	public int selectTimeCouponPrizeCountByDay(String evtNo, int freeGiftTurn){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("evtNo", evtNo);
		map.put("freeGiftTurn", freeGiftTurn);	
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectTimeCouponPrizeCountByDay",map);
	}

	/**
	 * 이벤트 목록 조회
	 * @param eventBoDTO
	 * @return
	 * @throws Exception
	 */
	public List<EventBoResult> selectEventList(EventBoDTO eventBoDTO) throws Exception {
		List<EventBoResult> resultList = getSession1().selectList("com.plgrim.ncp.biz.promotion.selectEventList", eventBoDTO);
		return resultList;
	}

	/**
	 * 이벤트 등록
	 * @param evt
	 * @throws Exception
	 */
	public void insertEvent(Evt evt) throws Exception {
		getSession1().insert("com.plgrim.ncp.base.insertEvt", evt);
	}

	/**
	 * 적용대상 등록
	 * @param sqlSession
	 * @param evtPartcptnTgt
	 * @throws Exception
	 */
	public void insertEvtPartcptnTgt(SqlSession sqlSession, EvtPartcptnTgt evtPartcptnTgt) throws Exception {
		
		Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("evt_no", evtPartcptnTgt.getEvtNo());
        int cnt = idGenService.generateDBOrder(sqlSession, "EVT_PARTCPTN_TGT", "APL_TURN", conditions, DatabaseType.ORACLE);
        
        evtPartcptnTgt.setAplTurn(cnt);
		getSession1().insert("com.plgrim.ncp.base.insertEvtPartcptnTgt", evtPartcptnTgt);
	}
	
	/**
	 * 적용 대상 목록 조회
	 * @param evtPartcptnTgt
	 * @return
	 * @throws Exception
	 */
	public List<EvtPartcptnTgt> selectPartcptnTgtList(EvtPartcptnTgt evtPartcptnTgt) throws Exception {
		List<EvtPartcptnTgt> resultList = getSession1().selectList("com.plgrim.ncp.biz.promotion.selectEvtPartcptnTgt", evtPartcptnTgt);
		return resultList;
	}
	
	/**
	 * 적용 대상 수정
	 * @param evtPartcptnTgt
	 * @throws Exception
	 */
	public void updateEvtPartcptnTgt(EvtPartcptnTgt evtPartcptnTgt) throws Exception {
		getSession1().insert("com.plgrim.ncp.base.insertEvtPartcptnTgt", evtPartcptnTgt);
	}
	
	/**
	 * 적용 대상 삭제
	 * @param evtPartcptnTgt
	 * @throws Exception
	 */
	public void deleteEvtPartcptnTgt(EvtPartcptnTgt evtPartcptnTgt) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.promotion.deleteEvtPartcptnTgt", evtPartcptnTgt);
	}
	
	/**
	 * 전시대상 등록
	 * @param sqlSession
	 * @param evtDspTgt
	 * @throws Exception
	 */
	public void insertEvtDspTgt(SqlSession sqlSession, EvtDspTgt evtDspTgt) throws Exception {
		
		Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("evt_no", evtDspTgt.getEvtNo());
        int cnt = idGenService.generateDBOrder(sqlSession, "EVT_DSP_TGT", "APL_TURN", conditions, DatabaseType.ORACLE);
        
        evtDspTgt.setAplTurn(cnt);
		getSession1().insert("com.plgrim.ncp.base.insertEvtDspTgt", evtDspTgt);
	}

	/**
	 * 전시대상 목록 조회
	 * @param evtDspTgt
	 * @return
	 * @throws Exception
	 */
	public List<EvtPartcptnTgt> selectEvtDspTgtList(EvtDspTgt evtDspTgt) throws Exception {
		List<EvtPartcptnTgt> resultList = getSession1().selectList("com.plgrim.ncp.base.selectEvtDspTgt", evtDspTgt);
		return resultList;
	}
	
	/**
	 * 전시대상 수정
	 * @param evtDspTgt
	 * @throws Exception
	 */
	public void updateEvtDspTgt(EvtDspTgt evtDspTgt) throws Exception {
		getSession1().insert("com.plgrim.ncp.base.updateEvtDspTgt", evtDspTgt);
	}
	
	/**
	 * 전시대상 삭제
	 * @param evtDspTgt
	 * @throws Exception
	 */
	public void deleteEvtDspTgt(EvtDspTgt evtDspTgt) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.promotion.deleteEvtDspTgtDxm", evtDspTgt);
	}
	
	/**
	 * 경품 등록
	 * @param freeGiftInfo
	 * @throws Exception
	 */
	public int insertFreeGiftInfo(EvtFreeGiftInfo freeGiftInfo) throws Exception {
		int resultCnt = getSession1().insert("com.plgrim.ncp.base.insertEvtFreeGiftInfo", freeGiftInfo);
		return resultCnt;
	}
	
	/**
	 * 이벤트 경품 적용 기간 등록.
	 *
	 * @param evtFreeGiftAplPd the EvtFreeGiftAplPd
	 * @throws SQLException the SQL exception
	 */
	public int insertEvtFreeGiftAplPd(EvtFreeGiftAplPd evtFreeGiftAplPd) throws Exception {
		int resultCnt = getSession1().insert("com.plgrim.ncp.base.insertEvtFreeGiftAplPd", evtFreeGiftAplPd);
		return resultCnt;
	}
	
	/**
	 * 이미지 등록
	 * @param evtImg
	 * @throws Exception
	 */
	public void insertEvtImg(EvtImg evtImg) throws Exception {
		getSession1().insert("com.plgrim.ncp.base.insertEvtImg", evtImg);
	}
	
	/**
	 * 이미지 삭제
	 * @param evtImg
	 * @throws Exception
	 */
	public void deleteEvtImg(EvtImg evtImg) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.promotion.deleteEvtImg", evtImg);
	}
	
	/**
	 * 이미지 수정
	 * @param evtImg
	 * @throws Exception
	 */
	public void updateEvtImg(EvtImg evtImg) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.promotion.updateEvtImg", evtImg);
	}

	/**
	 * 이벤트 수정
	 * @param evt
	 * @return
	 * @throws Exception
	 */
	public int updateEvent(Evt evt) throws Exception {
		int result = getSession1().update("com.plgrim.ncp.base.updateEvt", evt);
		return result;
	}

	/**
	 * 이벤트 수정(승인시)
	 * @param evt
	 * @return
	 * @throws Exception
	 */
	public int updateEventAprv(Evt evt) throws Exception {
	    int result = getSession1().update("com.plgrim.ncp.biz.promotion.updateEventAprv", evt);
	    return result;
	}
	
	/**
	 * 이벤트 상태 수정
	 * @param eventBoDTO
	 * @return
	 * @throws Exception
	 */
	public int updateEventStatus(EventBoDTO eventBoDTO) throws Exception {
		int result = getSession1().update("com.plgrim.ncp.biz.promotion.updateEvent", eventBoDTO);
		return result;
	}
	
	/**
	 * 경품 수정
	 * @param freeGiftInfo
	 * @return
	 * @throws Exception
	 */
	public int updateFreeGiftInfo(EvtFreeGiftInfo freeGiftInfo) throws Exception {
		int result = getSession1().update("com.plgrim.ncp.biz.promotion.updateFreeGiftInfo", freeGiftInfo);
		return result;
	}
	
	/**
	 * 경품 삭제
	 * @param freeGiftInfo
	 * @throws Exception
	 */
	public void deleteFreeGiftInfo(EvtFreeGiftInfo freeGiftInfo) throws Exception {
		getSession1().delete("com.plgrim.ncp.base.deleteEvtFreeGiftInfo", freeGiftInfo);
	}
	
	/**
	 * 경품 목록 조회
	 * @param eventBoDTO
	 * @return
	 * @throws Exception
	 */
	public List<EvtFreeGiftInfo> selectFreeGiftList(EventBoDTO eventBoDTO) throws Exception {
		List<EvtFreeGiftInfo> resultList = getSession1().selectList("com.plgrim.ncp.biz.promotion.selectFreeGiftList", eventBoDTO.getEvt());
		return resultList;
	}
	
	/**
	 * 연관 상품 등록
	 * @param evtRelateGod
	 * @throws Exception
	 */
	public void insertEvtRelateGod(EvtRelateGod evtRelateGod) throws Exception {
		getSession1().insert("com.plgrim.ncp.base.insertEvtRelateGod", evtRelateGod);
	}
	
	/**
	 * 연관 상품 수정
	 * @param evtRelateGod
	 * @throws Exception
	 */
	public void updateEvtRelateGod(EvtRelateGod evtRelateGod) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.promotion.updateEvtRelateGod", evtRelateGod);
	}
	
	/**
	 * 연관 상품 전시순서 수정
	 * @param evtRelateGod
	 * @throws Exception
	 */
	public void updateRelateGodSortSeq(EvtRelateGod evtRelateGod) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.promotion.updateRelateGodSortSeq", evtRelateGod);
	}
	
	/**
	 * 연관 상품 전시순서 일괄업로드
	 * @param evtRelateGod
	 * @return
	 * @throws Exception
	 */
	public int updateRelateGodSortSeqExcel(EvtRelateGod evtRelateGod) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.promotion.updateRelateGodSortSeqExcel", evtRelateGod);
	}
	
	/**
	 * 연관 상품 삭제
	 * @param evtRelateGod
	 * @throws Exception
	 */
	public void deleteEvtRelateGod(EvtRelateGod evtRelateGod) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.promotion.deleteEvtRelateGod", evtRelateGod);
	}
	
	/**
	 * 연관상품 등록전 목록 조회
	 * @param evtRelateGod
	 * @return
	 * @throws Exception
	 */
	public List<String> selectRelateGodBeforeList(EvtRelateGod evtRelateGod)  throws Exception {
		List<String> list = getSession1().selectList("com.plgrim.ncp.biz.promotion.selectRelateGodBeforeList", evtRelateGod);		
		return list;
	}
	
	/**
	 * 구분 타이틀 등록
	 * @param evtSprtr
	 * @throws Exception
	 */
	public void insertEvtSprtr(EvtSprtr evtSprtr) throws Exception {
		evtSprtr.setRprstSprtrYn("N");
		getSession1().insert("com.plgrim.ncp.base.insertEvtSprtr", evtSprtr);
	}
	
	/**
	 * 구분 타이틀 수정
	 * @param evtSprtr
	 * @throws Exception
	 */
	public void updateEvtSprtr(EvtSprtr evtSprtr) throws Exception {
		getSession1().update("com.plgrim.ncp.base.updateEvtSprtr", evtSprtr);
	}
	
	/**
	 * 구분 타이틀 전시순서 수정
	 * @param evtSprtr
	 * @throws Exception
	 */
	public void updateEvtSprtrTurn(EvtSprtr evtSprtr) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.promotion.updateEvtSprtrTurn", evtSprtr);
	}
	
	/**
	 * 구분 타이틀 삭제
	 * @param evtSprtr
	 * @throws Exception
	 */
	public void deleteEvtSprtr(EvtSprtr evtSprtr) throws Exception {
		getSession1().delete("com.plgrim.ncp.base.deleteEvtSprtr", evtSprtr);
	}
	
	
	/**
	 * 구분 타이틀 목록 조회(언어별)
	 * @param evtSprtrLang
	 * @return
	 * @throws Exception
	 */
	public List<EvtSprtrLang> selectEvtSprtrLang(EvtSprtrLang evtSprtrLang) throws Exception {
		List<EvtSprtrLang> result = getSession1().selectList("com.plgrim.ncp.biz.promotion.selectEvtSprtrLangList", evtSprtrLang);
		return result;
	}
	
	/**
	 * 구분 타이틀 등록(언어별)
	 * @param evtSprtrLang
	 * @throws Exception
	 */
	public void insertEvtSprtrLang(EvtSprtrLang evtSprtrLang) throws Exception {
		getSession1().insert("com.plgrim.ncp.base.insertEvtSprtrLang", evtSprtrLang);
	}
	
	/**
	 * 구분 타이틀 수정(언어별)
	 * @param evtSprtrLang
	 * @throws Exception
	 */
	public void updateEvtSprtrLang(EvtSprtrLang evtSprtrLang) throws Exception {
		getSession1().update("com.plgrim.ncp.base.updateEvtSprtrLang", evtSprtrLang);
	}
	
	/**
	 * 구분 타이틀 전시순서 수정
	 * @param evtSprtrLang
	 * @throws Exception
	 */
	public void updateEvtSprtrLnagTurn(EvtSprtrLang evtSprtrLang) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.promotion.updateEvtSprtrLang", evtSprtrLang);
	}
	
	/**
	 * 구분 타이틀 삭제(언어별)
	 * @param evtSprtr
	 * @throws Exception
	 */
	public void deleteEvtSprtrLang(EvtSprtr evtSprtr) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.promotion.deleteEvtSprtrLang", evtSprtr);
	}
	
	/**
	 * 당첨자 경품 목록 조회
	 * @param evt
	 * @return
	 * @throws Exception
	 */
	public List<PrizeFreeGiftResult> selectPrizeFreeGiftInfo(Evt evt) throws Exception {
		List<PrizeFreeGiftResult> resultList = getSession1().selectList("com.plgrim.ncp.web.bo.promotion.selectPrizeFreeGiftInfo", evt);
		return resultList;
	}
	
	/**
	 * 당첨자 선정위한 응모자 목록 조회
	 * @param applcnBoDTO
	 * @return
	 */
	public List<EvtApplcn> selectPrizeWinnerChoose(EvtApplcnBoDTO applcnBoDTO){
		List<EvtApplcn> resultList = getSession1().selectList("com.plgrim.ncp.web.bo.promotion.selectPrizeWinnerChoose", applcnBoDTO );
		return resultList;
	}
	
	/**
	 * 당첨자 등록
	 * @param evtPrize
	 * @throws Exception
	 */
	public void insertEvtPrize(EvtPrize evtPrize) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.promotion.insertEvtPrize", evtPrize);
	}
	
	/**
	 * 당첨자 경품 등록
	 * @param evtPrizeFreeGift
	 * @throws Exception
	 */
	public void insertEvtPrizeFreeGift(EvtPrizeFreeGift evtPrizeFreeGift) throws Exception {
		getSession1().insert("com.plgrim.ncp.base.insertEvtPrizeFreeGift", evtPrizeFreeGift);
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
	    
	    int cnt = getSession1().insert("com.plgrim.ncp.biz.promotion.insertEvtPrize", evtPrize);
	    return cnt;
	}
	
	/**
	 * 당첨자 경품 등록
	 * @param evtPrizeFreeGift
	 * @throws Exception
	 */
	public int insertEventPrizeFreeGift(EvtPrizeFreeGift evtPrizeFreeGift) throws Exception {
	    int cnt = getSession1().insert("com.plgrim.ncp.base.insertEvtPrizeFreeGift", evtPrizeFreeGift);
	    return cnt;
	}
	
	/**
	 * 당첨자 선정 수정
	 * @param evt
	 * @throws Exception
	 */
	public void updateEventPrizeChoose(Evt evt) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.promotion.updateEventPrizeChoose", evt);
	}
	
	/**
	 * 응모자 삭제
	 * @param evtApplcn
	 * @throws Exception
	 */
	public void deleteApplicant(EvtApplcn evtApplcn) throws Exception {
		getSession1().delete("com.plgrim.ncp.base.deleteEvtApplcn", evtApplcn);
	}
	
	/**
	 * @param evtPrize
	 * @throws Exception
	 */
	public void deleteEvtPrize(EvtPrize evtPrize) throws Exception {
		getSession1().delete("com.plgrim.ncp.base.deleteEvtPrize", evtPrize);
	}
	
	/**
	 * 당첨자 경품 삭제
	 * @param evtPrizeFreeGift
	 * @throws Exception
	 */
	public void deleteEvtPrizeFreeGift(EvtPrizeFreeGift evtPrizeFreeGift) throws Exception {
		getSession1().delete("com.plgrim.ncp.base.deleteEvtPrizeFreeGift", evtPrizeFreeGift);
	}
	
	/**
	 * 당첨자 등수 수정
	 * @param evtPrize
	 * @throws Exception
	 */
	public void updatePrizeWinnerRank(EvtPrize evtPrize) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.promotion.updatePrizeWinnerRank", evtPrize);
	}
	
	/**
	 * 당첨자 경품 수정
	 * @param evtPrizeFreeGift
	 * @throws Exception
	 */
	public void updatePrizeFreeGift(EvtPrizeFreeGift evtPrizeFreeGift) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.promotion.updateEvtPrizeFreeGift", evtPrizeFreeGift);
	}
	
	/**
	 * 응모자 등록
	 * @param evtApplcn
	 * @throws Exception
	 */
	public int insertEvtApplcn(EvtApplcn evtApplcn) throws Exception {		
		long evtNoGen = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
		evtApplcn.setEvtPartcptnSn(evtNoGen);
		int insCnt = getSession1().insert("com.plgrim.ncp.biz.promotion.insertEvtApplcn", evtApplcn);		
		return insCnt;
	}
	
	/**
	 * 응모자 등록 insert base
	 * @param evtApplcn
	 * @throws Exception
	 */
	public int insertEventApplcn(EvtApplcn evtApplcn) throws Exception {		
	    long evtNoGen = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
	    evtApplcn.setEvtPartcptnSn(evtNoGen);
	    int insCnt = getSession1().insert("com.plgrim.ncp.base.insertEvtApplcn", evtApplcn);		
	    return insCnt;
	}
	
	/**
	 * 응모자 상세
	 * @param evtApplcn
	 * @return
	 * @throws Exception
	 */
	public EvtApplcn selectEvtApplcn(EvtApplcn evtApplcn) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectEvtApplcn",evtApplcn);
		
	}
	
	/**
	 * @param evtNo
	 * @return
	 * @throws Exception
	 */
	public int selectMaxPrizeTurn(String evtNo) throws Exception {
		int result = getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectMaxPrizeTurn", evtNo);
		return result;
	}
	
	/**
	 * 적용상품 등록
	 * @param evtNo
	 * @return
	 * @throws Exception
	 */
	public int insertEventApplyGoods(SqlSession sqlSession, EvtAplGod evtAplGod) throws Exception {

        Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("evt_no", evtAplGod.getEvtNo());
        int cnt = idGenService.generateDBOrder(sqlSession, "EVT_APL_GOD", "APL_TURN", conditions, DatabaseType.ORACLE);

        evtAplGod.setAplTurn(cnt);

        getSession1().insert("com.plgrim.ncp.base.insertEvtAplGod", evtAplGod);

        return cnt;
    }

    /**
     * 적용상품 삭제
     * @param evtAplGod
     * @return
     * @throws Exception
     */
    public int deleteEventApplyGoods(EvtAplGod evtAplGod) throws Exception {

        int cnt = getSession1().delete("com.plgrim.ncp.biz.promotion.deleteEventApplyGoods", evtAplGod);
        return cnt;
    }

    /**
     * 적용상품 목록
     * @param searchDTO
     * @param pageParam
     * @return
     * @throws Exception
     */
    public Page<EventApplyGoodBoResult> selectEventApplyGoodList(EventApplyGoodBoDTO searchDTO, PageParam pageParam)
            throws Exception {

        // 페이지 인덱스 셋팅
        RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
        List<EventApplyGoodBoResult> resultList = getSession1()
                .selectList("com.plgrim.ncp.biz.promotion.selectEvtAplGodList", searchDTO);

//        for (EventApplyGoodBoResult result : resultList) {
//
//            if (CodeUtil.getCode(lang, result.getEvtAplGodEx().getGodSaleSectCd()) != null) {
//                result.getEvtAplGodEx().setGodSaleSectNm(CodeUtil.getCode(lang, result.getEvtAplGodEx().getGodSaleSectCd()).getCdNm());
//            }
//        }

        long totalRow = getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectEvtAplGodListCount", searchDTO);

        return new PageImpl<EventApplyGoodBoResult>(resultList, pageParam.getPageable(), totalRow);
    }
	
    /**
     * 적용상품 일괄업로드
     * @param evtAplGod
     * @return
     * @throws Exception
     */
    public int insertAplGodUploadExcel(EvtAplGod evtAplGod) throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.promotion.insertAplGodUploadExcel", evtAplGod);
	}
    
    /**
     * 응모자 일괄업로드
     * @param evtApplcn
     * @return
     * @throws Exception
     */
    public int insertApplicantUploadExcel(EvtApplcn evtApplcn) throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.promotion.insertApplicantUploadExcel", evtApplcn);
	}
    
    /**
     * 당첨자 일괄업로드
     * @param evtPrize
     * @return
     * @throws Exception
     */
    public int insertPrizeWinnerUploadExcel(EvtPrize evtPrize) throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.promotion.insertPrizeWinnerUploadExcel", evtPrize);
	}
    
    /**
     * 적용상품 목록
     * @param evtAplGod
     * @return
     * @throws Exception
     */
    public List<EvtAplGod> selectEvtAplGod(EvtAplGod evtAplGod) throws Exception {
    	return getSession1().selectList("com.plgrim.ncp.biz.promotion.selectEvtAplGod", evtAplGod);
	}
    
    /**
     * 이벤트 당첨자 공지위한 당첨자 목록
     * @param evt
     * @return
     * @throws Exception
     */
    public List<EventPrizeBoResult> selectPrizeWinnerListNotice(Evt evt) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.web.bo.promotion.selectPrizeWinnerListNotice", evt);
	}
    
    /**
     * 이벤트 당첨자 확인
     * @param evt
     * @return
     * @throws Exception
     */
//    public List<EvtPrize> selectEvtPrize(EvtApplcn evtApplcn) throws Exception {
//		return getSession1().selectList("com.plgrim.ncp.web.bo.promotion.selectEvtPrize", evtApplcn);
//	}
    
    /**
     * 이벤트 응모자 목록 Count
     * @param applcnBoDTO
     * @return
     * @throws Exception
     */
    public long selectApplcnListCount(EvtApplcnBoDTO applcnBoDTO) throws Exception {
    	return getSession1().selectOne("com.plgrim.ncp.web.bo.promotion.selectEvtApplcnListCount", applcnBoDTO);
    }
    
    /**
     * 이벤트 응모자 목록
     * @param applcnBoDTO
     * @return
     * @throws Exception
     */
    public List<EventApplcnBoResult> selectApplcnList(EvtApplcnBoDTO applcnBoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.web.bo.promotion.selectEvtApplcnList", applcnBoDTO);
	}
    
    /**
     * 이벤트 당첨자 목록 Count
     * @param applcnBoDTO
     * @return
     * @throws Exception
     */
    public long selectPrizeWinnerListCount(EvtApplcnBoDTO applcnBoDTO) throws Exception {
    	return getSession1().selectOne("com.plgrim.ncp.web.bo.promotion.selectPrizeWinnerListCount", applcnBoDTO);
    }
    
    /**
     * 이벤트 당첨자 목록
     * @param applcnBoDTO
     * @return
     * @throws Exception
     */
    public List<EventApplcnBoResult> selectPrizeWinnerList(EvtApplcnBoDTO applcnBoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.web.bo.promotion.selectPrizeWinnerList", applcnBoDTO);
	}
    
	/**
	 * 이벤트 응모자 엑셀
	 * @param applcnBoDTO
	 * @return
	 */
	public List<Map<String, Object>> selectApplicantListExcel(EvtApplcnBoDTO applcnBoDTO) {		
		List<Map<String, Object>> results = getSession1().selectList(
		        "com.plgrim.ncp.web.bo.promotion.selectApplicantListExcel", applcnBoDTO);		
		return results;
	}

	/**
	 * 이벤트 당첨자 엑셀
	 * @param applcnBoDTO
	 * @return
	 */
	public List<Map<String, Object>> selectPrizeWinnerListExcel(EvtApplcnBoDTO applcnBoDTO) {		
		List<Map<String, Object>> results = getSession1().selectList(
		        "com.plgrim.ncp.web.bo.promotion.selectPrizeWinnerListExcel", applcnBoDTO);		
		return results;
	}
	
	public List<String> selectEvtApplMemList(String evtNo) {
		List<String> result = getSession1().selectList("com.plgrim.ncp.biz.promotion.selectEvtApplMemList", evtNo);
		return result;
	}
	
	
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
	 * @return true : 응모가능 , false : 응모불가능
	 * @throws Exception
	 * @since 2015. 6. 26.
	 */
	public boolean checkEventEnable(String evtNo) throws Exception {
		String result = getSession1().selectOne("com.plgrim.ncp.biz.promotion.checkEventEnable", evtNo);
		return StringUtils.isNotEmpty(result) ? true : false;
	}

    /**
     * 이벤트 경품 목록 가져오기
     * 
     * @param model
     * @param request
     * @param searchDTO
     * @since 2015. 8. 28
     */
    public List<EventFreeGiftTurnExtendsFoDTO> selecEvtFreeGiftList(EventSearchFoDTO searchDTO) throws Exception {
        return getSession1().selectList("com.plgrim.ncp.biz.promotion.selecEvtFreeGiftList", searchDTO);
    }

    /**
     * 경품(쿠폰) 재고 잔여 수량
     * 
     * @param model
     * @param request
     * @param searchDTO
     * @since 2015. 8. 28
     */
    public String seleceventCouponDownIssueCheck(EventSearchFoDTO searchDTO) throws Exception {
        return getSession1().selectOne("com.plgrim.ncp.biz.promotion.seleceventCouponDownIssueCheck", searchDTO);
    }
    
	/**
	 * 이벤트(클레임 보지 않음) 응모자 구매 리스트 조회 
	 * @param searchDTO
	 * @return
	 */
	public List<Ord> selectNoClaimEvtApplCnOrdList(EventSearchFoDTO searchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.promotion.selectNoClaimEvtApplCnOrdList", searchDTO);
	}

	/**
	 * 이벤트 응모여부 체크 
	 * @param searchDTO
	 * @return
	 */	
	public String checkEventApplCn(EventSearchFoDTO searchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.isExsistEvtApplCn", searchDTO);
	}
	
	/**
	 * 이벤트 참여 유효성 체크
	 * 
	 * @param eventBoDTO
	 * @return
	 * @throws Exception
	 */
	public EventBoResult validEventPartcptn(EventBoDTO eventBoDTO) throws Exception {
	    EventBoResult result = getSession1().selectOne("com.plgrim.ncp.biz.promotion.validEventPartcptn", eventBoDTO);
        return result;
    }
	
	/**
	 * 이벤트 응모 유효성 체크
	 * 
	 * @param eventBoDTO
	 * @return
	 * @throws Exception
	 */
	public EventBoResult validEventApplcn(EventBoDTO eventBoDTO) throws Exception {
	    EventBoResult result = getSession1().selectOne("com.plgrim.ncp.biz.promotion.validEventApplcn", eventBoDTO);
	    return result;
	}
	
	/**
	 * 이벤트 총 당첨정보
	 * 
	 * @param eventBoDTO
	 * @return
	 * @throws Exception
	 */
	public EventBoResult selectEventTotalPrize(EventBoDTO eventBoDTO) throws Exception {
	    EventBoResult result = getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectEventTotalPrize", eventBoDTO);
	    return result;
	}
	
	/**
	 * 이벤트 스탬프 등록
	 * 
	 * @return
	 */
	public int insertEvtStmp(EvtStmp evtStmp) throws Exception {
	    int result = getSession1().insert("com.plgrim.ncp.base.insertEvtStmp", evtStmp);
	    return result;
	}
	
	/**
	 * 이벤트 스탬프 건수 조회
	 * 
	 * @param evtStmpExtend
	 * @return int
	 */
	public int selectEvtStmpCount(EvtStmpExtend evtStmpExtend) throws Exception {
	    return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectEvtStmpCount", evtStmpExtend); 
	}
	
	/**
	 * 이벤트 응모 유효성 체크 List
	 */
	public List<EventBoResult> validEventApplcnList(EventBoDTO eventBoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.promotion.validEventApplcnList", eventBoDTO);
	}

	/**
	 * 이벤트 대상자 등록
	 * @param evtPartcptnTgtMbr
	 * @return
	 * @throws Exception
	 */
	public int saveEvtTgtMbrExcel(EvtPartcptnTgtMbr evtPartcptnTgtMbr) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.promotion.saveEvtPartcptnTgtMbr", evtPartcptnTgtMbr);
	}

	/**
	 * 이벤트 대상자 전체 삭제
	 * @param evtPartcptnTgtMbr
	 * @return
	 * @throws Exception
	 */
	public int deleteEvtTgtMbrExcel(EvtPartcptnTgtMbr evtPartcptnTgtMbr) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.promotion.deleteEvtPartcptnTgtMbr", evtPartcptnTgtMbr);
	}



	/**
     * SNS 인증정보 리스트 조회
     */
    public List<MbrSns> selectMbrSnsList(MbrSns mbrSns) throws Exception {
        return getSession1().selectList("com.plgrim.ncp.biz.promotion.selectMbrSnsList", mbrSns);
    }

    /**
	 * SNS 인증정보 등록
	 */
	public int insertMbrSnsMerge(MbrSns mbrSns) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.promotion.insertMbrSnsMerge", mbrSns);
	}

	/**
	 * 최종사용 SNS 여부 update
	 */
	public int updateMbrSnsLastUseYn(MbrSns mbrSns) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.promotion.updateMbrSnsLastUseYn", mbrSns);
	}

	/**
	 * 이벤트 응모 유효성 체크_1
	 * 
	 * @param eventBoDTO
	 * @return
	 * @throws Exception
	 */
	public EventBoResult validEventApplcn_1(EventBoDTO eventBoDTO) throws Exception {
	    EventBoResult result = getSession1().selectOne("com.plgrim.ncp.biz.promotion.validEventApplcn_1", eventBoDTO);
	    return result;
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * 이벤트 약관 조회
	 * @param sysStplat
	 * @return
	 * @throws Exception
	 */
	public List<SysStplat> selectStplatList(String sysStplat) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.promotion.selectStplatList", sysStplat);
	}
	
	/**
	 * 이벤트 응모가능여부
	 * 
	 */
	public String selectCampaginEvtInfo(String evtNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectCampaginEvtInfo",evtNo);
    }

	/**
	 * 이벤트댓글 전시여부 수정
	 */
	public int updateEventReply(EvtSnsReply evtSnsReply){
		return getSession1().update("com.plgrim.ncp.web.bo.promotion.updateEvtSnsReply", evtSnsReply);
	}

	/**
	 * 이벤트댓글 전시여부별 건수
	 */
	public Map<String, String> selectEvtReplyDspCount(EvtApplcnBoDTO evtApplcnBoDTO){
		return getSession1().selectOne("com.plgrim.ncp.web.bo.promotion.selectEvtReplyDspCount", evtApplcnBoDTO);
	}

	/**
	 * 이벤트 댓글 목록 Count
	 * @param applcnBoDTO
	 * @return
	 * @throws Exception
	 */
	public long selectEvtSnsReplyListCount(EvtApplcnBoDTO applcnBoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.web.bo.promotion.selectEvtSnsReplyListCount", applcnBoDTO);
	}

	/**
	 * 이벤트 댓글 목록
	 * @param applcnBoDTO
	 * @return
	 * @throws Exception
	 */
	public List<EventApplcnBoResult> selectEvtSnsReplyList(EvtApplcnBoDTO applcnBoDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.web.bo.promotion.selectEvtSnsReplyList", applcnBoDTO);
	}

	/**
	 * 이벤트 댓글 엑셀
	 * @param applcnBoDTO
	 * @return
	 */
	public List<Map<String, Object>> selectEvtSnsReplyListExcel(EvtApplcnBoDTO applcnBoDTO) {
		List<Map<String, Object>> results = getSession1().selectList(
				"com.plgrim.ncp.web.bo.promotion.selectEvtSnsReplyListExcel", applcnBoDTO);
		return results;
	}

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
	public String selectFnMakingData(Map<String,String> paramMap) {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectFnMakingData", paramMap);
    }
	
	public EventExtendsFoDTO selectfcfsEvent(Map<String,String> paramMap) {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectFnMakingData", paramMap);
    }
	
	public EventExtendsFoDTO selectfcfsEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectfcfsEvent", eventSearchFoDTO);
	}
	public int selectfcfsRankingInformation(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectfcfsRankingInformation", eventSearchFoDTO);
	}
	public int selectfcfsWinnerCountCheck(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectfcfsWinnerCountCheck", eventSearchFoDTO);
	}
	public int deletefcfsWinner(EventSearchFoDTO eventSearchFoDTO) throws Exception  {
		return getSession1().delete("com.plgrim.ncp.biz.promotion.deletefcfsWinner", eventSearchFoDTO);
	}
	public int updatefcfsWinner(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return getSession1().delete("com.plgrim.ncp.biz.promotion.updatefcfsWinner", eventSearchFoDTO);
	}
	
	public EvtFreeGiftInfo getfcfsFreeGift(EvtFreeGiftInfo evtFreeGiftInfo){
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.getfcfsFreeGift", evtFreeGiftInfo);
	}
	public int selectfcfsDoubleWinningCheck(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectfcfsDoubleWinningCheck", eventSearchFoDTO);
	}
	public int selectEventPeriodNewMemberInquiry(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectEventPeriodNewMemberInquiry", eventSearchFoDTO);
	}
	
	public EventExtendsFoDTO selectEventLwprt(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectEventLwprt", eventSearchFoDTO);
	}
	
	public int selectEventFirstPurchase(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectEventFirstPurchase", eventSearchFoDTO);
	}
	
	public int selectEventRepurchase(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectEventRepurchase", eventSearchFoDTO);
	}
	
	public EventExtendsFoDTO selectEventRenewal(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectEventRenewal", eventSearchFoDTO);
	}
	public int selectEarlyBirdDoubleWinningCheck(EvtApplcn evtApplcn) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectEarlyBirdDoubleWinningCheck", evtApplcn);
	}
	public int updateEvtForGrid(Evt evt) {
		return getSession1().update("com.plgrim.ncp.biz.promotion.updateEvtForGrid", evt);

	}
	
	public String selectEventLang(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectEventLang", eventSearchFoDTO);
	}
	
}
