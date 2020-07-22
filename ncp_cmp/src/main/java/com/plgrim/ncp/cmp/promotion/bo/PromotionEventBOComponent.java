package com.plgrim.ncp.cmp.promotion.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtAplGod;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftAplPd;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPartcptnTgtMbr;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPrize;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtRelateGod;
import com.plgrim.ncp.biz.promotion.data.EventApplyGoodBoDTO;
import com.plgrim.ncp.biz.promotion.data.EventBoDTO;
import com.plgrim.ncp.biz.promotion.data.EvtApplcnBoDTO;
import com.plgrim.ncp.biz.promotion.data.EvtRelateGodBoDTO;
import com.plgrim.ncp.biz.promotion.data.EvtSprtrBoDTO;
import com.plgrim.ncp.biz.promotion.result.EventApplcnBoResult;
import com.plgrim.ncp.biz.promotion.result.EventApplyGoodBoResult;
import com.plgrim.ncp.biz.promotion.result.EventPrizeBoResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

public interface PromotionEventBOComponent extends PromotionBOComponent{
	
	/**
	 * 이벤트 등록
	 * 
	 * @param eventBoDTO
	 * @param rprstImgFile
	 * @return
	 * @throws Exception
	 */
	public String insertEvent(EventBoDTO eventBoDTO, List<String> rprstImgFile) throws Exception;
	
	/**
	 * 경품 등록/수정/삭제
	 * 
	 * @param insList
	 * @param updList
	 * @param delList
	 * @throws Exception
	 */
	public void insertFreeGift(List<EventBoDTO> insList, List<EventBoDTO> updList, List<EventBoDTO> delList) throws Exception;
    
    /**
     * 이벤트경품적용기간 삭제
     * 
     * @param evtFreeGiftAplPd
     * @return
     * @throws Exception
     */
    public int deleteEvtFreeGiftAplPd(EvtFreeGiftAplPd evtFreeGiftAplPd) throws Exception;
	
	/**
	 * 이벤트 수정
	 * 
	 * @param eventBoDTO
	 * @param rprstImgFile
	 * @return
	 * @throws Exception
	 */
	public int updateEvent(EventBoDTO eventBoDTO, List<String> rprstImgFile) throws Exception;

    /**
     * 이벤트 상태 변경
     * 
     * @param eventBoDTO
     * @return
     * @throws Exception
     */
    public int updateEventStatus(EventBoDTO eventBoDTO) throws Exception;
    
    /**
     * 이벤트 구분 타이틀 등록
     * 
     * @param evtSprtrBoDTO
     * @param rprstImgFile
     * @throws Exception
     */
    public void insertEvtSprtr(EvtSprtrBoDTO evtSprtrBoDTO, List<String> rprstImgFile) throws Exception;
    
	/**
	 * 이벤트 구분 타이틀 수정
	 * 
	 * @param evtSprtrBoDTO
	 * @param rprstImgFile
	 * @throws Exception
	 */
	public void updateEvtSprtr(EvtSprtrBoDTO evtSprtrBoDTO, List<String> rprstImgFile) throws Exception;
	
	/**
	 * 이벤트 구분 타이틀 전시순서 수정
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void updateEvtSprtrTurn(List<EvtSprtrBoDTO> list) throws Exception;
	
	/**
	 * 이벤트 구분 타이틀 삭제
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void deleteEvtSprtr(List<EvtSprtrBoDTO> list) throws Exception;
	
    /**
     * 이벤트 연관 상품 등록
     * 
     * @param list
     * @return
     * @throws Exception
     */
    public String[] insertEvtRelateGod(List<EvtRelateGod> list) throws Exception;
    
    
    /**
     * 이벤트 연관 상품 변경
     * 
     * @param list
     * @throws Exception
     */
    public void updateEvtRelateGod(List<EvtRelateGodBoDTO> list) throws Exception;
    
    /**
     * 이벤트 연관 상품 전시순서 수정
     * 
     * @param relateGodBoDTO
     * @throws Exception
     */
    public void updateRelateGodSortSeq(EvtRelateGodBoDTO relateGodBoDTO) throws Exception;
    
    /**
     * 이벤트 연관 상품 삭제
     * 
     * @param list
     * @throws Exception
     */
    public void deleteEvtRelateGod(List<EvtRelateGodBoDTO> list) throws Exception;
    
    /**
     * 이벤트 응모자 목록 조회
     * 
     * @param systemPK
     * @param applcnBoDTO
     * @param pageParam
     * @return
     * @throws Exception
     */
    public Page<EventApplcnBoResult> selectApplcnList(SystemPK systemPK, EvtApplcnBoDTO applcnBoDTO, PageParam pageParam) throws Exception;
    
	/**
	 * 당첨자 수정
	 * 
	 * @param applcnBoDTO
	 * @throws Exception
	 */
	public void updatePrizeWinner(EvtApplcnBoDTO applcnBoDTO) throws Exception;
	
	/**
     * 이벤트 당첨자 목록 조회
     * 
     * @param systemPK
     * @param applcnBoDTO
     * @param pageParam
     * @return
     * @throws Exception
     */
    public Page<EventApplcnBoResult> selectPrizeWinnerList(SystemPK systemPK, EvtApplcnBoDTO applcnBoDTO, PageParam pageParam) throws Exception;
    
    /**
	 * 응모자 삭제
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void deleteApplicant(List<EvtApplcnBoDTO> list) throws Exception;
	
	/**
	 * 응모자 등록
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public String insertEvtApplcn(List<EvtApplcn> list) throws Exception;
	
	/**
	 * 당첨자 삭제
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void deletePrizeWinner(List<EvtApplcnBoDTO> list) throws Exception;
	
	/**
	 * 당첨자 등수 수정
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void updatePrizeWinnerRank(List<EvtApplcnBoDTO> list) throws Exception;
	
	/**
	 * 이벤트 당첨자 등록
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public String insertPrizeWinner(List<EvtApplcn> list) throws Exception;
	
	/**
     * 적용대상상품 추가 insert
     * 
     * @param eventBoDTO
     * @return
     * @throws Exception
     */
    public int insertEventApplyGoods(EventBoDTO eventBoDTO) throws Exception;
    
    /**
     * 적용대상상품 삭제 delete
     * 
     * @param eventBoDTO
     * @return
     * @throws Exception
     */
    public int deleteEventApplyGoods(EventBoDTO eventBoDTO) throws Exception;
    
    /**
     * 이벤트 응모자 목록 조회 (excel)
     * 
     * @param systemPK
     * @param applcnBoDTO
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> selectApplicantListExcel(SystemPK systemPK, EvtApplcnBoDTO applcnBoDTO) throws Exception;
    
    /**
     * 이벤트 당첨자 목록 조회 (excel)
     * 
     * @param systemPK
     * @param applcnBoDTO
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> selectPrizeWinnerListExcel(SystemPK systemPK, EvtApplcnBoDTO applcnBoDTO) throws Exception;
    
    /**
     * 연관 상품 전시순서 일괄업로드
     * 
     * @param evtRelateGods
     * @param relateGod
     * @param loginId
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> updateRelateGodSortSeqExcel(List<EvtRelateGod> evtRelateGods, EvtRelateGod relateGod, String loginId) throws Exception;
    
    /**
     * 적용상품 일괄업로드
     * 
     * @param evtAplGods
     * @param aplGod
     * @param loginId
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> insertAplGodUploadExcel(List<EvtAplGod> evtAplGods, EvtAplGod aplGod, String loginId) throws Exception;
    
    /**
     * 응모자 일괄업로드
     * 
     * @param applcns
     * @param applcn
     * @param loginId
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> insertApplicantUploadExcel(List<EvtApplcn> applcns, EvtApplcn applcn, String loginId) throws Exception;
    
    /**
     * 당첨자 일괄업로드
     * 
     * @param prizes
     * @param prize
     * @param loginId
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> insertPrizeWinnerUploadExcel(List<EvtPrize> prizes, EvtPrize prize, String loginId) throws Exception;
 
    /**
     * 이벤트 적용상품 이미지 등록
     * 
     * @param evtAplGod
     * @param rprstImgFile
     * @throws Exception
     */
    public void insertEvtImgGodApl(EvtAplGod evtAplGod, List<String> rprstImgFile) throws Exception;
    
    /**
     * 이벤트 대상자 등록
     * @param evtTgtMbrList
     * @param loginId
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> saveEvtTgtMbrExcel(List<EvtPartcptnTgtMbr> evtTgtMbrList, String loginId) throws Exception;
	
	/**
	 * 이벤트댓글 전시여부 수정
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int updateEventReply(List<EvtApplcnBoDTO> list) throws Exception;
	
	/**
	 * 이벤트 댓글 목록 조회
	 * 
	 * @param systemPK
	 * @param applcnBoDTO
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<EventApplcnBoResult> selectEvtSnsReplyList(SystemPK systemPK, EvtApplcnBoDTO applcnBoDTO, PageParam pageParam) throws Exception;
	
	/**
	 * 이벤트 댓글 엑셀
	 * 
	 * @param systemPK
	 * @param applcnBoDTO
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectEvtSnsReplyListExcel(SystemPK systemPK, EvtApplcnBoDTO applcnBoDTO) throws Exception;
	
	/**
	 * 타임쿠폰 경품지급 건 COUNT
	 * 
	 * @param evtNo
	 * @return
	 * @throws Exception
	 */
	 public int getFreeGiftIssuCount(String evtNo) throws Exception;
	 
	/**
	 * 이벤트댓글 전시여부별 건수
	 * 
	 * @param evtApplcnBoDTO
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> selectEvtReplyDspCount(EvtApplcnBoDTO evtApplcnBoDTO) throws Exception;
	
	/**
	 * 적용 상품 목록
	 * 
	 * @param searchDTO
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<EventApplyGoodBoResult> selectEventApplyGoodList(EventApplyGoodBoDTO searchDTO, PageParam pageParam)throws Exception;
	
	/**
	 * 당첨자 공지 당첨자 목록
	 * 
	 * @param evt
	 * @return
	 * @throws Exception
	 */
	public List<EventPrizeBoResult> selectPrizeWinnerListNotice(Evt evt) throws Exception;

	public void updateEvtList(List<EventBoDTO> gridList);
	
}
