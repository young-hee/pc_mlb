package com.plgrim.ncp.biz.promotion.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
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
import com.plgrim.ncp.base.entities.datasource1.evt.EvtStplatAgr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSns;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPlcValExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.enums.EventEnum;
import com.plgrim.ncp.base.enums.EventEnum.EventApplcnMbrSect;
import com.plgrim.ncp.base.enums.EventEnum.EventFreeGiftKind;
import com.plgrim.ncp.base.enums.EventEnum.EventResultCode;
import com.plgrim.ncp.base.enums.EventEnum.EventType;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionAplTarget;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionSeparator;
import com.plgrim.ncp.base.repository.evt.EvtAplGodRepository;
import com.plgrim.ncp.base.repository.evt.EvtSprtrRepository;
import com.plgrim.ncp.base.repository.evt.EvtStplatAgrRepository;
import com.plgrim.ncp.biz.display.data.DspPromtBoDTO;
import com.plgrim.ncp.biz.promotion.data.EventApplyGoodBoDTO;
import com.plgrim.ncp.biz.promotion.data.EventBoDTO;
import com.plgrim.ncp.biz.promotion.data.EventExtendsFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventFreeGiftTurnExtendsFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventSearchFoDTO;
import com.plgrim.ncp.biz.promotion.data.EvtAplGodExtend;
import com.plgrim.ncp.biz.promotion.data.EvtApplcnBoDTO;
import com.plgrim.ncp.biz.promotion.data.EvtApplcnExtend;
import com.plgrim.ncp.biz.promotion.data.EvtPrizeExtend;
import com.plgrim.ncp.biz.promotion.data.EvtRelateGodBoDTO;
import com.plgrim.ncp.biz.promotion.data.EvtRelateGodExtend;
import com.plgrim.ncp.biz.promotion.data.EvtSprtrBoDTO;
import com.plgrim.ncp.biz.promotion.data.GoodsListFoDTO;
import com.plgrim.ncp.biz.promotion.exception.EventFreeGiftExistIssuGftException;
import com.plgrim.ncp.biz.promotion.repository.EventRepository;
import com.plgrim.ncp.biz.promotion.result.EventApplcnBoResult;
import com.plgrim.ncp.biz.promotion.result.EventApplyGoodBoResult;
import com.plgrim.ncp.biz.promotion.result.EventBoResult;
import com.plgrim.ncp.biz.promotion.result.EventPrizeBoResult;
import com.plgrim.ncp.biz.promotion.result.PrizeFreeGiftResult;
import com.plgrim.ncp.commons.repository.SysPolicyRepository;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.framework.cloud.CloudFileSystem;
import com.plgrim.ncp.framework.cloud.aws.S3FileSystem;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;

/**
 * 이벤트 Service
 * @author js
 *
 */
@Service
public class EventService extends AbstractService {

	@Autowired
	S3FileSystem s3FileSystem;
    @Value("${ncp_base.cloud.bucketName}")
    private String bucketName;
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private EvtSprtrRepository evtSprtrRepository;
	
    @Autowired
    private EvtAplGodRepository evtAplGodRepository;

    @Autowired
	private EvtStplatAgrRepository evtStplatAgrRepository;
    
    @Autowired
    private SysPolicyRepository sysPolicyRepository;
    
	@Value("${ncp_web_bo.image.promotion.event.http.path}")
	String eventHttpPath;
	
	@Value("${ncp_web_bo.image.promotion.event.upload.path}")
	String eventUploadPath;
 
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
	
    String getUploadImageUrlPath() {
        return getConfigService().getProperty( "ncp_web_bo.cloud.bucket.pec.temp.folder.path" )+ getDateService().getStringCurrentToday() + "/";
    }	
    String getSaveImagePath(String mallId) {
    	mallId = BOSecurityUtil.getAuthMall().get("mallId").toString();
    	return getConfigService().getProperty( "ncp_web_bo.cloud."+mallId+".bucket.image.folder.path" );
    }
	/**
	 * 이벤트 목록 조회
	 * @param searchDTO
	 * @param pageParam
	 * @return
	 */
	public List<EventExtendsFoDTO> selectEventList(EventSearchFoDTO searchDTO) {
	    return eventRepository.selectEventList(searchDTO);
    }
	/**
	 * 이벤트 카운트
	 * @param searchDTO
	 * @return
	 */
	public long selectEventCount(EventSearchFoDTO searchDTO) {
		return eventRepository.selectEventCount(searchDTO);
    }
	/**
	 * 종료된 이벤트 목록 조회
	 * @param searchDTO
	 * @param pageParam
	 * @return
	 */
	public Page<EventExtendsFoDTO> selectEventEndList(EventSearchFoDTO searchDTO, PageParam pageParam) {
	    return eventRepository.selectEventEndList(searchDTO, pageParam);
    }
	
	/**
	 * 이벤트 목록 조회 ( 페이징 없음)
	 * @param searchDTO
	 * @return
	 */
	public List<EventExtendsFoDTO> selectEventListNoPaging(EventSearchFoDTO searchDTO) {
		return  eventRepository.selectEventListNoPaging(searchDTO);
	}
	
	/**
	 * 이벤트 상세 조회
	 * @param searchDTO
	 * @return
	 */
	public EventExtendsFoDTO selectEventView(EventSearchFoDTO searchDTO) {
	    return eventRepository.selectEvent(searchDTO);
    }

	/**
	 *  출석이벤트 참여 목록 조회
	 * @param searchDTO
	 * @return
	 */
	public List<Integer> selectEventAtend(EventSearchFoDTO searchDTO) {
		return eventRepository.selectEventAtend(searchDTO);
    }
	/**
	 * 출석체크 이벤트 경품 목록 조회
	 * @param searchDTO
	 * @return
	 */
	public List<EventFreeGiftTurnExtendsFoDTO> selectFreeGiftTurnList(EventSearchFoDTO searchDTO) {
		return eventRepository.selectFreeGiftTurnList(searchDTO);
    }
	
	/**
	 * 이벤트 경품 적용기간별 목록 조회
	 * @param searchDTO
	 * @return
	 */
	public List<EventFreeGiftTurnExtendsFoDTO> selectFreeGiftTimeList(EventSearchFoDTO searchDTO) {
		return eventRepository.selectFreeGiftTimeList(searchDTO);
	}
	
	/**
	 * 출석 이벤트 참여
	 * @param evtNo
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public void insertEventAtend(EvtAtend evtAtend) throws Exception {
		eventRepository.insertEventAtend(evtAtend);
	}
	
	/**
	 * 출석 이벤트 중복 체크
	 * @param evtNo
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public boolean checkEventAtend(String evtNo, String mbrNo) throws Exception {
		return eventRepository.checkEventAtend(evtNo, mbrNo);
	}
	
	/**
	 * 이벤트 응모 자격 조건 조회
	* <pre>
	*
	* </pre>
	* @param evtNo
	* @since 2015. 6. 26.
	 */
	public List<EvtPartcptnTgt> selectEvtPartcptnTgtList(String evtNo){
		return eventRepository.selectEvtPartcptnTgtList(evtNo);
	}
	
	/**
	 * 타임쿠폰] 응모 중복 체크
	 * @param evtNo
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public boolean checkEvtApplcnToday(EventSearchFoDTO searchDTO) throws Exception {
		return eventRepository.checkEvtApplcnToday(searchDTO);
	}
	/**
	 * 타임쿠폰] 경품 남은 수량 조회
	 * @param evtNo
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public int selectFreeGiftInvQty(String evtNo, int freeGiftTurn) throws Exception {
		return eventRepository.selectFreeGiftInvQty(evtNo, freeGiftTurn);
	}
	/**
	 * 이벤트 연관 상품 목록 조회
	 * @param searchDTO
	 * @return
	 */
	public List<GoodsListFoDTO> selectEventGoodsList(EventSearchFoDTO searchDTO) {
		return eventRepository.selectEventGoodsList(searchDTO);
	}
	/**
	 * 이벤트 구분 타이틀 목록 조회
	 * @param searchDTO
	 * @return
	 */
	public List<EvtSprtr> selectEvtSprtrList(EventSearchFoDTO searchDTO) {
		return  eventRepository.selectEvtSprtrList(searchDTO);
	}
	
	/**
	 * SNS 댓글 목록 조회
	 * @param evtNo
	 * @return
	 * @throws Exception
	 */
	public Page<EvtSnsReply> selectSnsReplyList(EventSearchFoDTO searchDTO, PageParam pageParam) {
		return eventRepository.selectSnsReplyList(searchDTO, pageParam);
	}		
	
	/**
	 * 경품 재고 감소
	 * @param info
	 * @throws Exception 
	 */
	public void updateFreeGiftInvQty(String evtNo, int freeGiftTurn) throws Exception {
		eventRepository.updateFreeGiftInvQty(evtNo, freeGiftTurn);
	}

	/**
	 * 이벤트 경품 적용기간 경품수량 감소
	 * @param info
	 * @throws Exception 
	 */
	public int updateEvtFreeGiftAplPd(String evtNo, int freeGiftTurn) throws Exception {
		return eventRepository.updateEvtFreeGiftAplPd(evtNo, freeGiftTurn);
	}
	
	/**
	 * 이벤트 응모 가능여부 체크
	 * @param info
	 */
	public boolean getEvtApplcnEnable(String evtNo){
		return eventRepository.getEvtApplcnEnable(evtNo);
	}
	
	/**
	 * 타임쿠폰 다운가능 여부 체크 : 당첨 수량 체크
	 * @param evtNo
	 * @param freeGiftTurn
	 * @param freeGiftQty 일자별/경품별 당첨 수량
	 * @return false 불가, true 가능
	 */
	public boolean checkTimeCouponPrizeCount(String evtNo, int freeGiftTurn, long freeGiftQty ){
		// 일자별 경품 당첨 수 조회
		int prizeCount = eventRepository.selectTimeCouponPrizeCountByDay(evtNo, freeGiftTurn);
		// 당첨 가능 수량을 초과했는지 비교
		if (prizeCount >= freeGiftQty) {
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 이벤트 목록
	 * @param eventBoDTO
	 * @return
	 * @throws Exception
	 */
	public List<EventBoResult> selectEventList(EventBoDTO eventBoDTO) throws Exception {
		List<EventBoResult> resultList = eventRepository.selectEventList(eventBoDTO);
		return resultList;
	}

	/**
	 * 이벤트 입력
	 * @param eventBoDTO
	 * @throws Exception
	 */
	public void insertEvent(EventBoDTO eventBoDTO) throws Exception {
		eventRepository.insertEvent(eventBoDTO.getEvt());
	}

	/**
	 * 이벤트 적용대상 입력
	 * @param sqlSession
	 * @param eventBoDTO
	 * @throws Exception
	 */
	public void insertEvtPartcptnTgt(SqlSession sqlSession,EventBoDTO eventBoDTO) throws Exception{
		final String evtNo = eventBoDTO.getEvt().getEvtNo();
		final String strMallId = eventBoDTO.getEvt().getMallId();
		final String usrId = eventBoDTO.getEvt().getRegtrId();
		EvtPartcptnTgt evtPartcptnTgt = eventBoDTO.getEvtPartcptnTgt();
		
		List<String> mallList = new ArrayList<String>();
		List<String> dvcList = new ArrayList<String>();		
		List<String> tgtMbrTpList = new ArrayList<String>();
		List<String> langList = new ArrayList<String>();

		//몰ID 추가
        //적용대상 등록(상위 이벤트는 몰ID만 등록 한다)
		//evtPartcptnTgt.setMallId("DXM");
		evtPartcptnTgt.setMallId(strMallId);
        if (StringService.isNotEmpty(evtPartcptnTgt.getMallId())) {
        	mallList = Arrays.asList(evtPartcptnTgt.getMallId().split(PromotionSeparator.COMMA.toString()));
        }
        for(String mallId : mallList){
        	EvtPartcptnTgt tgt = new EvtPartcptnTgt();
        	tgt.setMallId(mallId);
        	tgt.setPartcptnTgtTpCd(PromotionAplTarget.MALL_ID.toString());
        	tgt.setEvtNo(evtNo);
        	tgt.setRegtrId(usrId);
        	tgt.setUdterId(usrId);
        	
        	eventRepository.insertEvtPartcptnTgt(sqlSession, tgt);
        }
        
        if(eventBoDTO.getEvt().getRprstLwprtSectCd().equals("LWPRT_EVT")){
            if (StringService.isNotEmpty(evtPartcptnTgt.getDvcCd())) {
                dvcList = Arrays.asList(evtPartcptnTgt.getDvcCd().split(PromotionSeparator.COMMA.toString()));
            }
            for(String dvc : dvcList){
            	EvtPartcptnTgt tgt = new EvtPartcptnTgt();
            	tgt.setDvcCd(dvc);
            	tgt.setPartcptnTgtTpCd(PromotionAplTarget.DVC.toString());
            	tgt.setEvtNo(evtNo);
            	tgt.setRegtrId(usrId);
            	tgt.setUdterId(usrId);
            	
            	eventRepository.insertEvtPartcptnTgt(sqlSession, tgt);
            }
            if (StringService.isNotEmpty(evtPartcptnTgt.getTgtMbrTpCd())) {
            	tgtMbrTpList = Arrays.asList(evtPartcptnTgt.getTgtMbrTpCd().split(PromotionSeparator.COMMA.toString()));
            }
            for(String tgtMbrTp : tgtMbrTpList){
            	EvtPartcptnTgt tgt = new EvtPartcptnTgt();
            	tgt.setTgtMbrTpCd(tgtMbrTp);
            	tgt.setPartcptnTgtTpCd(PromotionAplTarget.TGT_MBR_TP.toString());
            	tgt.setEvtNo(evtNo);
            	tgt.setRegtrId(usrId);
            	tgt.setUdterId(usrId);
            	
            	eventRepository.insertEvtPartcptnTgt(sqlSession, tgt);
            }
        }
        
        // 다국어 추가
        if(StringService.isNotEmpty(evtPartcptnTgt.getLangCd())) {
        	langList = Arrays.asList(evtPartcptnTgt.getLangCd().split(PromotionSeparator.COMMA.toString()));
        	
            for(String langCd : langList){
            	EvtPartcptnTgt tgt = new EvtPartcptnTgt();
            	tgt.setLangCd(langCd);
            	tgt.setPartcptnTgtTpCd(PromotionAplTarget.LANG.toString());
            	tgt.setEvtNo(evtNo);
            	tgt.setRegtrId(usrId);
            	tgt.setUdterId(usrId);
            	
            	eventRepository.insertEvtPartcptnTgt(sqlSession, tgt);
            }
        }
	}
	
	/**
	 * @param eventBoDTO
	 * @throws Exception
	 */
	public void updateEvtPartcptnTgt(EventBoDTO eventBoDTO) throws Exception{
		final String evtNo = eventBoDTO.getEvt().getEvtNo();
		final String strMallId = eventBoDTO.getEvt().getMallId();
		final String usrId = eventBoDTO.getEvt().getRegtrId();
		EvtPartcptnTgt evtPartcptnTgt = eventBoDTO.getEvtPartcptnTgt();
		
		EvtPartcptnTgt tgtParam = new EvtPartcptnTgt();
		tgtParam.setEvtNo(evtNo);
		
		eventRepository.deleteEvtPartcptnTgt(tgtParam);
		List<String> mallList = new ArrayList<String>();
		List<String> dvcList = new ArrayList<String>();		
		List<String> tgtMbrTpList = new ArrayList<String>();
		List<String> langList = new ArrayList<String>();

        int i = 0;
        
		//몰ID 추가
		//임의 셋팅
		//evtPartcptnTgt.setMallId("DXM");
		evtPartcptnTgt.setMallId(strMallId);
        if (StringService.isNotEmpty(evtPartcptnTgt.getMallId())) {
        	mallList = Arrays.asList(evtPartcptnTgt.getMallId().split(PromotionSeparator.COMMA.toString()));
        }
        for(String mallId : mallList){
        	i++;
        	EvtPartcptnTgt tgt = new EvtPartcptnTgt();
        	tgt.setAplTurn(i);
        	tgt.setMallId(mallId);
        	tgt.setPartcptnTgtTpCd(PromotionAplTarget.MALL_ID.toString());
        	tgt.setEvtNo(evtNo);
        	tgt.setRegtrId(usrId);
        	tgt.setUdterId(usrId);
        	
        	eventRepository.updateEvtPartcptnTgt(tgt);
        }
        
        if (StringService.isNotEmpty(evtPartcptnTgt.getDvcCd())) {
            dvcList = Arrays.asList(evtPartcptnTgt.getDvcCd().split(PromotionSeparator.COMMA.toString()));
        }
        for(String dvc : dvcList){
        	i++;
        	EvtPartcptnTgt tgt = new EvtPartcptnTgt();
        	tgt.setAplTurn(i);
        	tgt.setDvcCd(dvc);
        	tgt.setPartcptnTgtTpCd(PromotionAplTarget.DVC.toString());
        	tgt.setEvtNo(evtNo);
        	tgt.setRegtrId(usrId);
        	tgt.setUdterId(usrId);
        	
        	eventRepository.updateEvtPartcptnTgt(tgt);
        }
        if (StringService.isNotEmpty(evtPartcptnTgt.getTgtMbrTpCd())) {
        	tgtMbrTpList = Arrays.asList(evtPartcptnTgt.getTgtMbrTpCd().split(PromotionSeparator.COMMA.toString()));
        }
        for(String tgtMbrTp : tgtMbrTpList){
        	i++;
        	EvtPartcptnTgt tgt = new EvtPartcptnTgt();
        	tgt.setAplTurn(i);
        	tgt.setTgtMbrTpCd(tgtMbrTp);
        	tgt.setPartcptnTgtTpCd(PromotionAplTarget.TGT_MBR_TP.toString());
        	tgt.setEvtNo(evtNo);
        	tgt.setRegtrId(usrId);
        	tgt.setUdterId(usrId);
        	
        	eventRepository.updateEvtPartcptnTgt(tgt);
        }
        
        // 다국어 추가
        if(StringService.isNotEmpty(evtPartcptnTgt.getLangCd())) {
        	langList = Arrays.asList(evtPartcptnTgt.getLangCd().split(PromotionSeparator.COMMA.toString()));
        }
        for(String langCd : langList){
        	i++;
        	EvtPartcptnTgt tgt = new EvtPartcptnTgt();
        	tgt.setAplTurn(i);
        	tgt.setLangCd(langCd);
        	tgt.setPartcptnTgtTpCd(PromotionAplTarget.LANG.toString());
        	tgt.setEvtNo(evtNo);
        	tgt.setRegtrId(usrId);
        	tgt.setUdterId(usrId);
        	
        	eventRepository.updateEvtPartcptnTgt(tgt);
        }
	}
	
	/**
	 * 이벤트 전시대상 입력
	 * @param sqlSession
	 * @param eventBoDTO
	 * @throws Exception
	 */
	public void insertEvtDspTgt(SqlSession sqlSession,EventBoDTO eventBoDTO) throws Exception{
		// 이벤트 적용대상과 동일하게 저장한다.
		final String evtNo = eventBoDTO.getEvt().getEvtNo();
		final String strMallId = eventBoDTO.getEvt().getMallId();
		final String usrId = eventBoDTO.getEvt().getRegtrId();
		EvtPartcptnTgt evtPartcptnTgt = eventBoDTO.getEvtPartcptnTgt();
		EvtDspTgt evtDspTgt = eventBoDTO.getEvtDspTgt();
		
		List<String> mallList = new ArrayList<String>();
		List<String> dvcList = new ArrayList<String>();		
//		List<String> tgtMbrTpList = new ArrayList<String>();
		List<String> langList = new ArrayList<String>();

		//몰ID 추가
        //전시대상 등록(상위 이벤트는 몰ID만 등록 한다)
		evtPartcptnTgt.setMallId(strMallId);
        if (StringService.isNotEmpty(evtPartcptnTgt.getMallId())) {
        	mallList = Arrays.asList(evtPartcptnTgt.getMallId().split(PromotionSeparator.COMMA.toString()));
        }
        for(String mallId : mallList){
        	EvtDspTgt tgt = new EvtDspTgt();
        	tgt.setMallId(mallId);
        	tgt.setEvtDspTgtTpCd(PromotionAplTarget.MALL_ID.toString());
        	tgt.setEvtNo(evtNo);
        	tgt.setRegtrId(usrId);
        	tgt.setUdterId(usrId);
        	
        	eventRepository.insertEvtDspTgt(sqlSession, tgt);
        }

        if (StringService.isNotEmpty(evtPartcptnTgt.getDvcCd())) {
            dvcList = Arrays.asList(evtPartcptnTgt.getDvcCd().split(PromotionSeparator.COMMA.toString()));
        }
        for(String dvc : dvcList){
        	EvtDspTgt tgt = new EvtDspTgt();
        	tgt.setDvcCd(dvc);
        	tgt.setEvtDspTgtTpCd(PromotionAplTarget.DVC.toString());
        	tgt.setEvtNo(evtNo);
        	tgt.setRegtrId(usrId);
        	tgt.setUdterId(usrId);
        	
        	eventRepository.insertEvtDspTgt(sqlSession, tgt);
        }
        
        // 다국어 추가
        if(StringService.isNotEmpty(evtDspTgt.getLangCd())) {
        	langList = Arrays.asList(evtDspTgt.getLangCd().split(PromotionSeparator.COMMA.toString()));
        	
            for(String langCd : langList){
            	EvtDspTgt tgt = new EvtDspTgt();
            	tgt.setLangCd(langCd);
            	tgt.setEvtDspTgtTpCd(PromotionAplTarget.LANG.toString());
            	tgt.setEvtNo(evtNo);
            	tgt.setRegtrId(usrId);
            	tgt.setUdterId(usrId);
            	
            	eventRepository.insertEvtDspTgt(sqlSession, tgt);
            }
        }

		//회원유형 추가
		List<CodeUtil.Code> tgtMbrTpList = CodeUtil.getCodes("MBR_TP","KOR");
		if(!tgtMbrTpList.isEmpty()){
			for(CodeUtil.Code tgtMbrTp : tgtMbrTpList){
				EvtDspTgt tgt = new EvtDspTgt();
				tgt.setTgtMbrTpCd(tgtMbrTp.getCd());
				tgt.setEvtDspTgtTpCd(PromotionAplTarget.TGT_MBR_TP.toString());
				tgt.setEvtNo(evtNo);
				tgt.setRegtrId(usrId);
				tgt.setUdterId(usrId);

				eventRepository.insertEvtDspTgt(sqlSession, tgt);
			}
		}

		//회원속성 추가
		List<CodeUtil.Code>  mbrAtrbCdList = CodeUtil.getCodes("MBR_ATRB","KOR");
		if(!mbrAtrbCdList.isEmpty()){
			for(CodeUtil.Code mbrAtrbCd : mbrAtrbCdList){
				EvtDspTgt tgt = new EvtDspTgt();
				tgt.setTgtMbrAtrbCd(mbrAtrbCd.getCd());
				tgt.setEvtDspTgtTpCd(PromotionAplTarget.TGT_MBR_ATRB.toString());
				tgt.setEvtNo(evtNo);
				tgt.setRegtrId(usrId);
				tgt.setUdterId(usrId);

				eventRepository.insertEvtDspTgt(sqlSession, tgt);
			}
		}
	}
	
	/**
	 * 이벤트 전시대상 수정
	 * @param sqlSession
	 * @param eventBoDTO
	 * @throws Exception
	 */
	public void updateEvtDspTgt(SqlSession sqlSession,EventBoDTO eventBoDTO) throws Exception{
		// 이벤트 적용대상과 동일하게 저장한다.
		final String evtNo = eventBoDTO.getEvt().getEvtNo();
		final String strMallId = eventBoDTO.getEvt().getMallId();
		final String usrId = eventBoDTO.getEvt().getRegtrId();
		EvtPartcptnTgt evtPartcptnTgt = eventBoDTO.getEvtPartcptnTgt();
		EvtDspTgt evtDspTgt = eventBoDTO.getEvtDspTgt();
		
		EvtDspTgt tgtParam = new EvtDspTgt();
		tgtParam.setEvtNo(evtNo);
		
		eventRepository.deleteEvtDspTgt(tgtParam);
		
		List<String> mallList = new ArrayList<String>();
		List<String> dvcList = new ArrayList<String>();		
		List<String> langList = new ArrayList<String>();

		//몰ID 추가
        //전시대상 등록(상위 이벤트는 몰ID만 등록 한다)
		evtPartcptnTgt.setMallId(strMallId);
        if (StringService.isNotEmpty(evtPartcptnTgt.getMallId())) {
        	mallList = Arrays.asList(evtPartcptnTgt.getMallId().split(PromotionSeparator.COMMA.toString()));
        }
        for(String mallId : mallList){
        	EvtDspTgt tgt = new EvtDspTgt();
        	tgt.setMallId(mallId);
        	tgt.setEvtDspTgtTpCd(PromotionAplTarget.MALL_ID.toString());
        	tgt.setEvtNo(evtNo);
        	tgt.setRegtrId(usrId);
        	tgt.setUdterId(usrId);
        	
        	eventRepository.insertEvtDspTgt(sqlSession, tgt);
        }
        
        if (StringService.isNotEmpty(evtPartcptnTgt.getDvcCd())) {
            dvcList = Arrays.asList(evtPartcptnTgt.getDvcCd().split(PromotionSeparator.COMMA.toString()));
        }
        for(String dvc : dvcList){
        	EvtDspTgt tgt = new EvtDspTgt();
        	tgt.setDvcCd(dvc);
        	tgt.setEvtDspTgtTpCd(PromotionAplTarget.DVC.toString());
        	tgt.setEvtNo(evtNo);
        	tgt.setRegtrId(usrId);
        	tgt.setUdterId(usrId);
        	
        	eventRepository.insertEvtDspTgt(sqlSession, tgt);
        }
        
        // 다국어 추가
        if(StringService.isNotEmpty(evtDspTgt.getLangCd())) {
        	langList = Arrays.asList(evtDspTgt.getLangCd().split(PromotionSeparator.COMMA.toString()));
        	
            for(String langCd : langList){
            	EvtDspTgt tgt = new EvtDspTgt();
            	tgt.setLangCd(langCd);
            	tgt.setEvtDspTgtTpCd(PromotionAplTarget.LANG.toString());
            	tgt.setEvtNo(evtNo);
            	tgt.setRegtrId(usrId);
            	tgt.setUdterId(usrId);
            	
            	eventRepository.insertEvtDspTgt(sqlSession, tgt);
            }
        }
        
        //회원유형 추가
  		List<CodeUtil.Code> tgtMbrTpList = CodeUtil.getCodes("MBR_TP","KOR");
  		if(!tgtMbrTpList.isEmpty()){
  			for(CodeUtil.Code tgtMbrTp : tgtMbrTpList){
  				EvtDspTgt tgt = new EvtDspTgt();
  				tgt.setTgtMbrTpCd(tgtMbrTp.getCd());
  				tgt.setEvtDspTgtTpCd(PromotionAplTarget.TGT_MBR_TP.toString());
  				tgt.setEvtNo(evtNo);
  				tgt.setRegtrId(usrId);
  				tgt.setUdterId(usrId);

  				eventRepository.insertEvtDspTgt(sqlSession, tgt);
  			}
  		}

  		//회원속성 추가
  		List<CodeUtil.Code>  mbrAtrbCdList = CodeUtil.getCodes("MBR_ATRB","KOR");
  		if(!mbrAtrbCdList.isEmpty()){
  			for(CodeUtil.Code mbrAtrbCd : mbrAtrbCdList){
  				EvtDspTgt tgt = new EvtDspTgt();
  				tgt.setTgtMbrAtrbCd(mbrAtrbCd.getCd());
  				tgt.setEvtDspTgtTpCd(PromotionAplTarget.TGT_MBR_ATRB.toString());
  				tgt.setEvtNo(evtNo);
  				tgt.setRegtrId(usrId);
  				tgt.setUdterId(usrId);

  				eventRepository.insertEvtDspTgt(sqlSession, tgt);
  			}
  		}
	}
	
	/**
	 * @param list
	 * @param evtNo
	 * @throws Exception
	 */
	public void insertFreeGiftInfo(List<EventBoDTO> list) throws Exception {
		final String loginId = BOSecurityUtil.getLoginId();
		EvtFreeGiftInfo freeGiftInfo = null;
		if(list != null && !list.isEmpty()) {
			String evtNo = list.get(0).getFreeGiftInfo().getEvtNo();

			Evt evt = new Evt();
			evt.setEvtNo(evtNo);
			Evt validEvt = eventRepository.selectEvt(evt);

			Map<String,Object>conditions = new HashMap<String,Object>();
			conditions.put("EVT_NO", evtNo);
			int turn = getIdGenService().generateDBOrder(sqlSession1, "EVT_FREE_GIFT_INFO", "FREE_GIFT_TURN", conditions,  DatabaseType.ORACLE);
			
			//EventBoDTO eventBo = new EventBoDTO();
			//int freeGiftCnt = eventRepository.selectMaxFreeGift(evtNo);
			for(EventBoDTO eventBoDTO : list){
				//freeGiftCnt ++;
				freeGiftInfo = eventBoDTO.getFreeGiftInfo();
				freeGiftInfo.setEvtNo(evtNo);
				freeGiftInfo.setFreeGiftTurn(turn);
				freeGiftInfo.setRegtrId(loginId);
				freeGiftInfo.setUdterId(loginId);
				int result = eventRepository.insertFreeGiftInfo(freeGiftInfo);
				turn++;

				if (result > 0 && EventType.TM_CPN.toString().equals(validEvt.getEvtTpCd())) {
					this.extractEvtFreeGiftAplPdProecss(freeGiftInfo, validEvt);
				}
			}
		}		
	}

	/**
	 * 이벤트 경품정보 적용기간 insert
	 * 
	 * @param freeGiftInfo
	 * @param validEvt
	 * @return
	 * @throws Exception
	 */
	private int extractEvtFreeGiftAplPdProecss(EvtFreeGiftInfo freeGiftInfo, Evt validEvt) throws Exception {

		final String loginId = BOSecurityUtil.getLoginId();

		// 이벤트 경품정보 적용기간
		int resultCnt = 0;

		DateTime applcnBegDt = new DateTime(validEvt.getApplcnBegDt());
		DateTime applcnEndDt = new DateTime(validEvt.getApplcnEndDt());
		String datebyApplcnBegHour = validEvt.getDatebyApplcnBegHour();
		String datebyApplcnEndHour = validEvt.getDatebyApplcnEndHour();

		String defBegHour = "0000";
		String defEndHour = "2359";

		int daysBetweenCnt = Days.daysBetween(applcnBegDt, applcnEndDt).getDays();

		String hmPattern = "HHmm";
		DateTime applcnBegHour = DateTimeFormat.forPattern(hmPattern).parseDateTime(defBegHour);
		DateTime applcnEndHour = DateTimeFormat.forPattern(hmPattern).parseDateTime(defEndHour);

		// 0은 당일
		for (int i = 0; i <= daysBetweenCnt; i++) {

			DateTime begDt = applcnBegDt.plusDays(i);
			DateTime endDt = applcnBegDt.plusDays(i);

			if (StringService.isNotEmpty(datebyApplcnBegHour) && StringService.isNotEmpty(datebyApplcnEndHour)) {

				applcnBegHour = DateTimeFormat.forPattern(hmPattern).parseDateTime(datebyApplcnBegHour);
				applcnEndHour = DateTimeFormat.forPattern(hmPattern).parseDateTime(datebyApplcnEndHour);

				// 시작시간이 더 큰 경우
				if (applcnBegHour.compareTo(applcnEndHour) >= Days.ZERO.getDays()) {

					int j = i + 1;
					if (j <= daysBetweenCnt) {
						endDt = applcnBegDt.plusDays(j);
					}
					else {
						break;
					}

					if (applcnBegHour.compareTo(applcnEndHour) == Hours.ZERO.getHours()) {
						applcnEndHour = applcnEndHour.minusMinutes(Minutes.ONE.getMinutes());
						applcnEndHour = applcnEndHour.plusSeconds(59);
					}
				}
			}
			begDt = begDt.plusHours(applcnBegHour.getHourOfDay());
			begDt = begDt.plusMinutes(applcnBegHour.getMinuteOfHour());
			endDt = endDt.plusHours(applcnEndHour.getHourOfDay());
			endDt = endDt.plusMinutes(applcnEndHour.getMinuteOfHour());

			// 등록
			EvtFreeGiftAplPd evtFreeGiftAplPd = new EvtFreeGiftAplPd();
			evtFreeGiftAplPd.setEvtNo(validEvt.getEvtNo());
			evtFreeGiftAplPd.setFreeGiftTurn(freeGiftInfo.getFreeGiftTurn());
			evtFreeGiftAplPd.setBegDt(begDt.toDate());
			evtFreeGiftAplPd.setEndDt(endDt.toDate());
			evtFreeGiftAplPd.setFreeGiftQty(freeGiftInfo.getFreeGiftInvQty());
			evtFreeGiftAplPd.setRegtrId(loginId);
			evtFreeGiftAplPd.setUdterId(loginId);

			resultCnt += eventRepository.insertEvtFreeGiftAplPd(evtFreeGiftAplPd);
		}

		if (resultCnt > 0) {
			// 이벤트경품정보 update
			eventRepository.updateFreeGiftInvQtyAplPd(freeGiftInfo);
		}

		return resultCnt;
	}
	
	/**
	 * @param list
	 * @param evtNo
	 * @throws Exception
	 */
	public void updateFreeGiftInfo(List<EventBoDTO> list) throws Exception {
		final String loginId = BOSecurityUtil.getLoginId();
		EvtFreeGiftInfo freeGiftInfo = null;
		if(list != null && !list.isEmpty()) {
			String evtNo = list.get(0).getFreeGiftInfo().getEvtNo();

			Evt evt = new Evt();
			evt.setEvtNo(evtNo);
			Evt validEvt = eventRepository.selectEvt(evt);

			for(EventBoDTO eventBoDTO : list){
				freeGiftInfo = eventBoDTO.getFreeGiftInfo();
				freeGiftInfo.setUdterId(loginId);
				if (EventType.TM_CPN.toString().equals(validEvt.getEvtTpCd())) {
					this.extractEvtFreeGiftAplPdChangeProecss(freeGiftInfo, validEvt);
				}
			}
		}		
	}

	/**
	 * 이벤트 경품정보 적용기간 change
	 * 
	 * @param validEvt 
	 * @param freeGiftInfo 
	 * @throws Exception
	 */
	private void extractEvtFreeGiftAplPdChangeProecss(EvtFreeGiftInfo freeGiftInfo, Evt validEvt) throws Exception {
		// 삭제 후, 재등록
		
		String evtNo = freeGiftInfo.getEvtNo();
		int freeGiftTurn = freeGiftInfo.getFreeGiftTurn();

		int checkResult = eventRepository.getFreeGiftIssuCount(evtNo, freeGiftTurn);
		
		if (checkResult > 0) {
			throw new EventFreeGiftExistIssuGftException(null);
		} else {
			EvtFreeGiftAplPd evtFreeGiftAplPd = new EvtFreeGiftAplPd();
			evtFreeGiftAplPd.setEvtNo(evtNo);
			evtFreeGiftAplPd.setFreeGiftTurn(freeGiftTurn);
			int result = eventRepository.updateFreeGiftInfo(freeGiftInfo);

			if (result > 0) {
				eventRepository.deleteEvtFreeGiftAplPd(evtFreeGiftAplPd);
				this.extractEvtFreeGiftAplPdProecss(freeGiftInfo, validEvt);
			}
		}
	}
	
	/**
	 * @param list
	 * @param evtNo
	 * @throws Exception
	 */
	public void deleteFreeGiftInfo(List<EventBoDTO> list) throws Exception {

		EvtFreeGiftInfo freeGiftInfo = null;
		if(list != null && !list.isEmpty()) {

			String evtNo = list.get(0).getFreeGiftInfo().getEvtNo();

			Evt evt = new Evt();
			evt.setEvtNo(evtNo);
			Evt validEvt = eventRepository.selectEvt(evt);

			for(EventBoDTO eventBoDTO : list){
				freeGiftInfo = eventBoDTO.getFreeGiftInfo();
				int freeGiftTurn = freeGiftInfo.getFreeGiftTurn();

				// 타임쿠폰인 경우 자식테이블 데이터 삭제(이벤트경품적용기간)
				if (EventType.TM_CPN.toString().equals(validEvt.getEvtTpCd())) {
					EvtFreeGiftAplPd evtFreeGiftAplPd = new EvtFreeGiftAplPd();
					evtFreeGiftAplPd.setEvtNo(freeGiftInfo.getEvtNo());
					evtFreeGiftAplPd.setFreeGiftTurn(freeGiftInfo.getFreeGiftTurn());
					
					int checkResult = eventRepository.getFreeGiftIssuCount(evtNo, freeGiftTurn);
					
					if (checkResult > 0) {
						throw new EventFreeGiftExistIssuGftException(null);
					} else {
						eventRepository.deleteEvtFreeGiftAplPd(evtFreeGiftAplPd);
					}
				}

				eventRepository.deleteFreeGiftInfo(freeGiftInfo);
			}
		}		
	}
	
	/**
	 * 
	 * @param evt
	 * @return
	 * @throws Exception
	 */
	public String getEvtImgPath(Evt evt) throws Exception {
		String imgPath = getUploadImagePath(evt.getEvtNo());		
		String fileUrl = eventHttpPath + imgPath + evt.getEvtNo();
		String commitResourcePath = this.bucketName + ":"+getSaveImagePath(BOSecurityUtil.getAuthMall().get("mallId").toString()) + fileUrl+"/";
		
		return commitResourcePath;
	}
	
	/**
	 * @param list
	 * @param evt
	 * @throws Exception
	 */
	public void insertEvtImg(List<EvtImg> list, Evt evt) throws Exception {
		String imgTempPath = getTempUploadImagePath("base");
		//String imgRealPath = getConfigService().getProperty("ncp_base.image.base.upload.path");
		//String fileUrl = getConfigService().getProperty("ncp_web_bo.image.promotion.event.path");
		String imgPath = getUploadImagePath(evt.getEvtNo());		
		String fileUrl = eventHttpPath + imgPath + evt.getEvtNo();
		
		String suffix = DateService.getStringCurrentToday() + DateService.getStringCurrentHourMinuteSecond();
		
		if(list != null){
			for(EvtImg evtImg : list){
				evtImg.setImgFileUrl(fileUrl);
				String tempFileNm = evtImg.getImgFileNm();
				
				if(StringUtils.isNotEmpty(tempFileNm)){
					
					String fileExt = tempFileNm.substring(tempFileNm.lastIndexOf(".")+1);
					String fileNm = evtImg.getEvtNo() + "_" + evtImg.getEvtImgSectCd() + "_" + evtImg.getDvcSectCd() + "_" + "KOR" + "_" + suffix + "." + fileExt;
					evtImg.setImgFileNm(fileNm);
			    	eventRepository.insertEvtImg(evtImg);
	 
					String tempResourcePath = this.bucketName + ":"+ getUploadImageUrlPath() + tempFileNm;
					String commitResourcePath = this.bucketName + ":"+getSaveImagePath("DXM") + fileUrl+"/" + fileNm;			
	 
					s3FileSystem.move(tempResourcePath, commitResourcePath, CloudFileSystem.Permission.PUBLIC);						
				}

 
			}
		}
	}
	

	/**
	 * @param newImgList
	 * @param oldImgList
	 * @param evt
	 * @throws Exception
	 */
	public void updateEvtImg(List<EvtImg> newImgList, List<EvtImg> oldImgList, Evt evt) throws Exception {
		String imgTempPath = getTempUploadImagePath("base");
		String imgPath = getUploadImagePath(evt.getEvtNo());
		String fileUrl = eventHttpPath + imgPath + evt.getEvtNo();
		String uploadUrl = eventUploadPath + imgPath + evt.getEvtNo();
		String tempFileNm = "";
		String fileExt = "";
		String fileNm ="";
		
		String suffix = DateService.getStringCurrentToday() + DateService.getStringCurrentHourMinuteSecond();

		List<EvtImg> insList = new ArrayList<EvtImg>();
		List<EvtImg> updList = new ArrayList<EvtImg>();
		List<EvtImg> delList = new ArrayList<EvtImg>();
		
		List<EvtImg> newRprstImgList = new ArrayList<EvtImg>();
		List<EvtImg> newBcrnImgList = new ArrayList<EvtImg>();
		List<EvtImg> oldRprstImgList = new ArrayList<EvtImg>();
		List<EvtImg> oldBcrnImgList = new ArrayList<EvtImg>();

		//대표이벤트
		if(evt.getRprstLwprtSectCd().equals("RPRST_EVT")||
				(!evt.getEvtTpCd().equals("TM_CPN") && !evt.getEvtTpCd().equals("SNS_REPLY") && !evt.getEvtTpCd().equals("ATEND_CHK"))){
		//if(!evt.getEvtTpCd().equals("TM_CPN") && !evt.getEvtTpCd().equals("SNS_REPLY") && !evt.getEvtTpCd().equals("ATEND_CHK")){
			//newRprstImgList = newImgList;
			//oldRprstImgList = oldImgList;
			newRprstImgList.addAll(newImgList);
			oldRprstImgList.addAll(oldImgList);
			if(newRprstImgList.size() == oldRprstImgList.size()){
				for(EvtImg evtImg : newRprstImgList){
				    updList.add(evtImg);
					//eventRepository.updateEvtImg(evtImg);
				}
			}else{
				for(EvtImg evtImg : oldRprstImgList){
					delList.add(evtImg);
					//eventRepository.deleteEvtImg(evtImg);
				}
				for(EvtImg evtImg : newRprstImgList){
					insList.add(evtImg);
					//eventRepository.insertEvtImg(evtImg);
				}
			}
		}else{

			//대표이미지
			for(EvtImg newImg :newImgList){
				if(newImg.getEvtImgSectCd().equals("RPRST")){
					newRprstImgList.add(newImg);
				}else{
					newBcrnImgList.add(newImg);
				}
			}
			//배경이미지
			for(EvtImg oldImg :oldImgList){
				if(oldImg.getEvtImgSectCd().equals("RPRST")){
					oldRprstImgList.add(oldImg);
				}else{
					oldBcrnImgList.add(oldImg);
				}
			}
			
			if(newRprstImgList.size() == oldRprstImgList.size()){
				for(EvtImg evtImg : newRprstImgList){
				    updList.add(evtImg);
					//eventRepository.updateEvtImg(evtImg);
				}
			}else{
				for(EvtImg evtImg : oldRprstImgList){
					delList.add(evtImg);
					//eventRepository.deleteEvtImg(evtImg);
				}
				for(EvtImg evtImg : newRprstImgList){
					insList.add(evtImg);
					//eventRepository.insertEvtImg(evtImg);
				}
			}
			
			if(newBcrnImgList.size() == oldBcrnImgList.size()){
				for(EvtImg evtImg : newBcrnImgList){
				    updList.add(evtImg);
					//eventRepository.updateEvtImg(evtImg);
				}
			}else{
				for(EvtImg evtImg : oldBcrnImgList){
					delList.add(evtImg);
					//eventRepository.deleteEvtImg(evtImg);
				}
				for(EvtImg evtImg : newBcrnImgList){
					insList.add(evtImg);
					//eventRepository.insertEvtImg(evtImg);
				}
			}
		}
				
		//이미지 파일 처리
		for(EvtImg evtImg : newImgList){
			evtImg.setImgFileUrl(fileUrl);
			tempFileNm = evtImg.getImgFileNm();
			fileExt = tempFileNm.substring(tempFileNm.lastIndexOf(".")+1);
			fileNm = evtImg.getEvtNo() + "_" + evtImg.getEvtImgSectCd() + "_" + evtImg.getDvcSectCd() + "_" + "KOR" + "_" + suffix + "." + fileExt;
			//String fileNm = evtImg.getEvtNo() + "." + fileExt;
			
			if(tempFileNm.indexOf(evtImg.getEvtNo()) < 0) {
				if(StringUtils.isNotEmpty(tempFileNm)){
					String tempResourcePath = this.bucketName + ":"+ getUploadImageUrlPath() + tempFileNm;
					String commitResourcePath = this.bucketName + ":"+getSaveImagePath("DXM") + fileUrl+"/" +  fileNm;
					s3FileSystem.move(tempResourcePath, commitResourcePath, CloudFileSystem.Permission.PUBLIC);						
				}

			}
	
 
		}
		
		if(delList != null){
			for(EvtImg evtImg : delList){
				//evtImg.setImgFileUrl(fileUrl);
				//tempFileNm = evtImg.getImgFileNm();
				//fileExt = tempFileNm.substring(tempFileNm.lastIndexOf(".")+1);
				//fileNm = evtImg.getEvtNo() + "_" + evtImg.getEvtImgSectCd() + "_" + evtImg.getDvcSectCd() + "_" + "KOR" + "." + fileExt;
				eventRepository.deleteEvtImg(evtImg);
			}
		}
		
		if(updList != null){
			for(EvtImg evtImg : updList){
				
				tempFileNm = evtImg.getImgFileNm();
				fileExt = tempFileNm.substring(tempFileNm.lastIndexOf(".")+1);
				fileNm = evtImg.getEvtNo() + "_" + evtImg.getEvtImgSectCd() + "_" + evtImg.getDvcSectCd() + "_" + "KOR" + "_" + suffix + "." + fileExt;
                if(tempFileNm.indexOf(evtImg.getEvtNo()) == -1 ){
                    evtImg.setImgFileUrl(fileUrl);
                    evtImg.setImgFileNm(fileNm);
                  
               
                }
				
				eventRepository.updateEvtImg(evtImg);
			}
		}
		
		if(insList != null){
			for(EvtImg evtImg : insList){
				evtImg.setImgFileUrl(fileUrl);
				tempFileNm = evtImg.getImgFileNm();
				fileExt = tempFileNm.substring(tempFileNm.lastIndexOf(".")+1);
				fileNm = evtImg.getEvtNo() + "_" + evtImg.getEvtImgSectCd() + "_" + evtImg.getDvcSectCd() + "_" + "KOR" + "_" + suffix + "." + fileExt;
              
				if(tempFileNm.indexOf(evtImg.getEvtNo()) == -1 ){
                    evtImg.setImgFileNm(fileNm);                                 
                }
				eventRepository.insertEvtImg(evtImg);
			}
		}			
	}

	/**
	 * @param eventBoDTO
	 * @return
	 * @throws Exception
	 */
	public int updateEvent(EventBoDTO eventBoDTO) throws Exception {
		int result = eventRepository.updateEvent(eventBoDTO.getEvt());
		return result;
	}

	/**
	 * 이벤트 수정(승인시)
	 * 
	 * @param eventBoDTO
	 * @return
	 * @throws Exception
	 */
	public int updateEventAprv(EventBoDTO eventBoDTO) throws Exception {
	    int result = eventRepository.updateEventAprv(eventBoDTO.getEvt());
	    return result;
	}
	
	/**
	 * @param eventBoDTO
	 * @return
	 * @throws Exception
	 */
	public int updateEventStatus(EventBoDTO eventBoDTO) throws Exception {
		int result = eventRepository.updateEventStatus(eventBoDTO);
		return result;
	}

	/**
	 * @param eventBoDTO
	 * @return
	 * @throws Exception
	 */
	public List<EvtFreeGiftInfo> selectFreeGiftList(EventBoDTO eventBoDTO) throws Exception {
		List<EvtFreeGiftInfo> resultList = eventRepository.selectFreeGiftList(eventBoDTO);
		return resultList;
	}
	
	/**
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public String[] insertEvtRelateGod(List<EvtRelateGod> list) throws Exception{
		String[] result =  new String[2];
		 
		if(list != null && list.size() > 0) {
		
			String loginId = BOSecurityUtil.getLoginId(); 
			EvtRelateGod  relateGod = list.get(0);
			List<String> godNoList = eventRepository.selectRelateGodBeforeList(relateGod);			
			String godNo = null;
			String dupGod = "";
			int insCnt = 0;

			int seq = godNoList.size()+1; // 20191213
		
			for(EvtRelateGod evtRelateGod : list){
				godNo = evtRelateGod.getGodNo();
				if(StringService.isNotEmpty(godNo)&&godNoList.contains(godNo)) {
					dupGod += godNo + ",";
				} else {
					evtRelateGod.setRegtrId(loginId);
					evtRelateGod.setUdterId(loginId);
					evtRelateGod.setSortSeq(seq+insCnt); // 처음 등록시 999로 세팅 -> 순서대로 세팅 20191213
					eventRepository.insertEvtRelateGod(evtRelateGod);
					insCnt++;
				}
			}
			result[0] = dupGod ;
			result[1] = ""+insCnt ; 
		}
		return result;
	}
	
	/**
	 * @param list
	 * @throws Exception
	 */
	public void updateEvtRelateGod(List<EvtRelateGodBoDTO> list) throws Exception{

		String loginId = BOSecurityUtil.getLoginId(); 
		
		if(list != null && list.size() > 0) {
			for( EvtRelateGodBoDTO evtRelateGodBoDTO : list){
				EvtRelateGod evtRelateGod = evtRelateGodBoDTO.getEvtRelateGod();
				evtRelateGod.setUdterId(loginId);
				eventRepository.updateEvtRelateGod(evtRelateGod);
			}
		}
	}
	
	/**
	 * @param list
	 * @throws Exception
	 */
	public void deleteEvtRelateGod(List<EvtRelateGodBoDTO> list) throws Exception{
		 
		String loginId = BOSecurityUtil.getLoginId(); 

		if(list != null && list.size() > 0) {
			for( EvtRelateGodBoDTO evtRelateGodBoDTO : list){
				EvtRelateGod evtRelateGod = evtRelateGodBoDTO.getEvtRelateGod();
				eventRepository.deleteEvtRelateGod(evtRelateGod);
			}
		}
	}
	
	/**
	 * @param relateGodBoDTO
	 * @throws Exception
	 */
	public void updateRelateGodSortSeq(EvtRelateGodBoDTO relateGodBoDTO) throws Exception{

		String loginId = BOSecurityUtil.getLoginId(); 
		
		relateGodBoDTO.getEvtRelateGod().setUdterId(loginId);
		eventRepository.updateRelateGodSortSeq(relateGodBoDTO.getEvtRelateGod());
	}
	
	/**
	 * @param evtSprtrBoDTO
	 * @param rprstImgFile
	 * @throws Exception
	 */
	public void insertEvtSprtr(EvtSprtrBoDTO evtSprtrBoDTO, List<String> rprstImgFile) throws Exception {		
		String imgTempPath = getTempUploadImagePath("base");
		String imgPath = getUploadImagePath(evtSprtrBoDTO.getEvtSprtr().getEvtNo());
		String fileUrl = eventHttpPath + imgPath + evtSprtrBoDTO.getEvtSprtr().getEvtNo() + "/sprtr";
		String uploadUrl = eventUploadPath + imgPath + evtSprtrBoDTO.getEvtSprtr().getEvtNo() + "/sprtr";
		
		String suffix = DateService.getStringCurrentToday() + DateService.getStringCurrentHourMinuteSecond();
				
		String loginId = BOSecurityUtil.getLoginId();
		
		List<String[]> tmpImgName = new ArrayList<String[]>();
		
		Map<String,Object>conditions = new HashMap<String,Object>();
		conditions.put("EVT_NO", evtSprtrBoDTO.getEvtSprtr().getEvtNo());
		int turn = getIdGenService().generateDBOrder(sqlSession1, "EVT_SPRTR", "SPRTR_TURN", conditions,  DatabaseType.ORACLE);
		String fileName = evtSprtrBoDTO.getEvtSprtr().getEvtNo() + "_" + turn + "_";
		String tempFileNm = "";
		String fileExt = "";
		//기본 구분 타이틀(한국어) 세팅
		evtSprtrBoDTO.getEvtSprtr().setSprtrTurn(turn);
    	if(evtSprtrBoDTO.getEvtSprtr().getSprtrImgFileNm() != "" && evtSprtrBoDTO.getEvtSprtr().getSprtrImgFileNm() != null){   		
    		tempFileNm = evtSprtrBoDTO.getEvtSprtr().getSprtrImgFileNm();
    		fileExt = tempFileNm.substring(tempFileNm.lastIndexOf(".")+1);
    		String[] tmpStr = {evtSprtrBoDTO.getEvtSprtr().getSprtrImgFileNm(), fileName + "KOR" + "_" + suffix + "." + fileExt};
    		tmpImgName.add(tmpStr);
    		evtSprtrBoDTO.getEvtSprtr().setSprtrImgFileUrl(fileUrl);
    		evtSprtrBoDTO.getEvtSprtr().setSprtrImgFileNm(fileName + "KOR" + "_" + suffix + "." + fileExt);
    	}
    	evtSprtrBoDTO.getEvtSprtr().setRegtrId(loginId);
    	evtSprtrBoDTO.getEvtSprtr().setUdterId(loginId);
		
		eventRepository.insertEvtSprtr(evtSprtrBoDTO.getEvtSprtr());
		
		//언어별 구분 타이틀 세팅
		EvtSprtrLang sprtrLang = new EvtSprtrLang();
		sprtrLang.setEvtNo(evtSprtrBoDTO.getEvtSprtr().getEvtNo());
		sprtrLang.setSprtrTurn(turn);
		sprtrLang.setRegtrId(loginId);
		sprtrLang.setUdterId(loginId);
		sprtrLang.setLangCd("CHI");
		if(evtSprtrBoDTO.getSprtrImgFileNmChi() != "" && evtSprtrBoDTO.getSprtrImgFileNmChi() != null){	
			tempFileNm = evtSprtrBoDTO.getSprtrImgFileNmChi();
    		fileExt = tempFileNm.substring(tempFileNm.lastIndexOf(".")+1);
			String[] tmpStr = {evtSprtrBoDTO.getSprtrImgFileNmChi(), fileName + "CHI" + suffix + "." + fileExt};
    		tmpImgName.add(tmpStr);
			sprtrLang.setSprtrImgFileUrl(fileUrl);
			sprtrLang.setSprtrImgFileNm(fileName + "CHI" + suffix + "." + fileExt);
		}
		sprtrLang.setSprtrNm(evtSprtrBoDTO.getSprtrNmChi());
		sprtrLang.setSprtrImgAltrtvCont(evtSprtrBoDTO.getSprtrImgAltrtvContChi());
		eventRepository.insertEvtSprtrLang(sprtrLang);

		sprtrLang.setLangCd("ENG");
		if(evtSprtrBoDTO.getSprtrImgFileNmEng() != "" && evtSprtrBoDTO.getSprtrImgFileNmEng() != null){	
			tempFileNm = evtSprtrBoDTO.getSprtrImgFileNmEng();
    		fileExt = tempFileNm.substring(tempFileNm.lastIndexOf(".")+1);
			String[] tmpStr = {evtSprtrBoDTO.getSprtrImgFileNmEng(), fileName + "ENG" + suffix + "." + fileExt};
    		tmpImgName.add(tmpStr);
			sprtrLang.setSprtrImgFileUrl(fileUrl);
			sprtrLang.setSprtrImgFileNm(fileName + "ENG" + suffix + "." + fileExt);
		}
		sprtrLang.setSprtrNm(evtSprtrBoDTO.getSprtrNmEng());
		sprtrLang.setSprtrImgAltrtvCont(evtSprtrBoDTO.getSprtrImgAltrtvContEng());
		eventRepository.insertEvtSprtrLang(sprtrLang);
		
		if(rprstImgFile != null){
			for(String img : rprstImgFile){
				for(String[] tmpImgNm : tmpImgName){
					if(img.equals(tmpImgNm[0])){								
						
						String tempResourcePath = this.bucketName + ":"+ getUploadImageUrlPath() + img;
						String commitResourcePath = this.bucketName + ":"+getSaveImagePath("DXM") + fileUrl+"/" +  tmpImgNm[1];			
		 
						s3FileSystem.move(tempResourcePath, commitResourcePath, CloudFileSystem.Permission.PUBLIC);	
						
						//log.info( " imageFile.getAbsolutePath=>"+imageFile.getAbsolutePath()  );
 
						//moveTempImage(getConfigService().getProperty("ncp_base.image.base.upload.path")+fileUrl, tempFileNm, imgTempPath, tempFileNm);
					}
				}
			}
		}
		
	}
	
	/**
	 * @param evtSprtrBoDTO
	 * @param rprstImgFile
	 * @throws Exception
	 */
	public void updateEvtSprtr(EvtSprtrBoDTO evtSprtrBoDTO, List<String> rprstImgFile) throws Exception {
		String imgTempPath = getTempUploadImagePath("base");
		String imgPath = getUploadImagePath(evtSprtrBoDTO.getEvtSprtr().getEvtNo());
		String fileUrl = eventHttpPath + imgPath + evtSprtrBoDTO.getEvtSprtr().getEvtNo() + "/sprtr";
		String uploadUrl = eventUploadPath + imgPath + evtSprtrBoDTO.getEvtSprtr().getEvtNo() + "/sprtr";
		
        String suffix = DateService.getStringCurrentToday() + DateService.getStringCurrentHourMinuteSecond();

		List<String[]> tmpImgName = new ArrayList<String[]>();
		
		String loginId = BOSecurityUtil.getLoginId();
		EvtSprtr oldSprtr = evtSprtrRepository.selectEvtSprtr(evtSprtrBoDTO.getEvtSprtr());		
		String fileName = evtSprtrBoDTO.getEvtSprtr().getEvtNo() + "_" + oldSprtr.getSprtrTurn() + "_";		
		String tempFileNm = "";
		String fileExt = "";

		//기본 구분타이틀 세팅(한국어)
		oldSprtr.setSprtrNm(evtSprtrBoDTO.getEvtSprtr().getSprtrNm());
		if(oldSprtr.getSprtrImgFileNm() != "" && oldSprtr.getSprtrImgFileNm() != null ){
			if(evtSprtrBoDTO.getEvtSprtr().getSprtrImgFileNm() == "" || evtSprtrBoDTO.getEvtSprtr().getSprtrImgFileNm() == null){
				oldSprtr.setSprtrImgFileNm("");
				oldSprtr.setSprtrImgFileUrl("");
			}else{
				tempFileNm = evtSprtrBoDTO.getEvtSprtr().getSprtrImgFileNm();
				if (!oldSprtr.getSprtrImgFileNm().equals(tempFileNm)) {
				    fileExt = tempFileNm.substring(tempFileNm.lastIndexOf(".")+1);
				    String[] tmpStr = {evtSprtrBoDTO.getEvtSprtr().getSprtrImgFileNm(), fileName + "KOR" + "_" + suffix + "." + fileExt};
				    tmpImgName.add(tmpStr);
				    oldSprtr.setSprtrImgFileNm(fileName + "KOR" + "_" + suffix + "." + fileExt);
				    oldSprtr.setSprtrImgFileUrl(fileUrl);
                }
			}
		}else{
			if(evtSprtrBoDTO.getEvtSprtr().getSprtrImgFileNm() == "" || evtSprtrBoDTO.getEvtSprtr().getSprtrImgFileNm() == null){
				//oldSprtr.setSprtrImgFileUrl("");
			}else{
				tempFileNm = evtSprtrBoDTO.getEvtSprtr().getSprtrImgFileNm();
	    		fileExt = tempFileNm.substring(tempFileNm.lastIndexOf(".")+1);
				String[] tmpStr = {evtSprtrBoDTO.getEvtSprtr().getSprtrImgFileNm(), fileName + "KOR" + "_" + suffix + "." + fileExt};
	    		tmpImgName.add(tmpStr);
				oldSprtr.setSprtrImgFileNm(fileName + "KOR" + "_" + suffix + "." + fileExt);
				oldSprtr.setSprtrImgFileUrl(fileUrl);
			}
		}
		oldSprtr.setSprtrImgAltrtvCont(evtSprtrBoDTO.getEvtSprtr().getSprtrImgAltrtvCont());
		oldSprtr.setUdterId(loginId);		
		eventRepository.updateEvtSprtr(oldSprtr);
				
		//언어별 구분타이틀 세팅
		EvtSprtrLang sprtrLang = new EvtSprtrLang();
		sprtrLang.setEvtNo(evtSprtrBoDTO.getEvtSprtr().getEvtNo());
		sprtrLang.setSprtrTurn(evtSprtrBoDTO.getEvtSprtr().getSprtrTurn());
		
		List<EvtSprtrLang> oldSprtrLangList = eventRepository.selectEvtSprtrLang(sprtrLang);
		if(oldSprtrLangList != null){
			EvtSprtrLang oldSprtrLangChi = null;
			EvtSprtrLang oldSprtrLangEng = null;
			for(EvtSprtrLang oldSprtrLang : oldSprtrLangList){
				if(oldSprtrLang.getLangCd().equals("CHI")){
					oldSprtrLangChi = oldSprtrLang;
				}else if(oldSprtrLang.getLangCd().equals("ENG")){
					oldSprtrLangEng = oldSprtrLang;
				}
			}
			if(oldSprtrLangChi != null){
				oldSprtrLangChi.setSprtrNm(evtSprtrBoDTO.getSprtrNmChi());		
				
				if(oldSprtrLangChi.getSprtrImgFileNm() == "" || oldSprtrLangChi.getSprtrImgFileNm() == null){
					if(evtSprtrBoDTO.getSprtrImgFileNmChi() == "" || evtSprtrBoDTO.getSprtrImgFileNmChi() == null){
						//oldSprtrLangChi.setSprtrImgFileUrl("");
					}else{
						tempFileNm = evtSprtrBoDTO.getSprtrImgFileNmChi();
			    		fileExt = tempFileNm.substring(tempFileNm.lastIndexOf(".")+1);
						String[] tmpStr = {evtSprtrBoDTO.getSprtrImgFileNmChi(), fileName + "CHI" + suffix + "." + fileExt};
			    		tmpImgName.add(tmpStr);
			    		oldSprtrLangChi.setSprtrImgFileNm(fileName + "CHI" + suffix + "." + fileExt);
						oldSprtrLangChi.setSprtrImgFileUrl(fileUrl);
					}
				}else{
					if(evtSprtrBoDTO.getSprtrImgFileNmChi() == "" || evtSprtrBoDTO.getSprtrImgFileNmChi() == null){
						oldSprtrLangChi.setSprtrImgFileNm("");
						oldSprtrLangChi.setSprtrImgFileUrl("");
					}else{
						tempFileNm = evtSprtrBoDTO.getSprtrImgFileNmChi();
						
						if (!oldSprtrLangChi.getSprtrImgFileNm().equals(tempFileNm)) {
						    fileExt = tempFileNm.substring(tempFileNm.lastIndexOf(".")+1);
						    String[] tmpStr = {evtSprtrBoDTO.getSprtrImgFileNmChi(), fileName + "CHI" + suffix + "." + fileExt};
						    tmpImgName.add(tmpStr);
						    oldSprtrLangChi.setSprtrImgFileNm(fileName + "CHI" + suffix + "." + fileExt);
						    oldSprtrLangChi.setSprtrImgFileUrl(fileUrl);
						}
						
					}
				}
				oldSprtrLangChi.setSprtrImgAltrtvCont(evtSprtrBoDTO.getSprtrImgAltrtvContChi());
				oldSprtrLangChi.setUdterId(loginId);
				
				eventRepository.updateEvtSprtrLang(oldSprtrLangChi);			
			}
			if(oldSprtrLangEng != null){
				oldSprtrLangEng.setSprtrNm(evtSprtrBoDTO.getSprtrNmEng());		
				
				if(oldSprtrLangEng.getSprtrImgFileNm() == "" || oldSprtrLangEng.getSprtrImgFileNm() == null){
					if(evtSprtrBoDTO.getSprtrImgFileNmEng() == "" || evtSprtrBoDTO.getSprtrImgFileNmEng() == null){
						//oldSprtrLangChi.setSprtrImgFileUrl("");
					}else{
						tempFileNm = evtSprtrBoDTO.getSprtrImgFileNmEng();
			    		fileExt = tempFileNm.substring(tempFileNm.lastIndexOf(".")+1);
						String[] tmpStr = {evtSprtrBoDTO.getSprtrImgFileNmEng(), fileName + "ENG" + suffix + "." + fileExt};
			    		tmpImgName.add(tmpStr);
			    		oldSprtrLangEng.setSprtrImgFileNm(fileName + "ENG" + suffix + "." + fileExt);
			    		oldSprtrLangEng.setSprtrImgFileUrl(fileUrl);
					}
				}else{
					if(evtSprtrBoDTO.getSprtrImgFileNmEng() == "" || evtSprtrBoDTO.getSprtrImgFileNmEng() == null){
						oldSprtrLangEng.setSprtrImgFileNm("");
						oldSprtrLangEng.setSprtrImgFileUrl("");
					}else{
					    tempFileNm = evtSprtrBoDTO.getSprtrImgFileNmEng();
					    
					    if (!oldSprtrLangEng.getSprtrImgFileNm().equals(tempFileNm)) {
					        
					        fileExt = tempFileNm.substring(tempFileNm.lastIndexOf(".")+1);
					        String[] tmpStr = {evtSprtrBoDTO.getSprtrImgFileNmEng(), fileName + "ENG" + suffix + "." + fileExt};
					        tmpImgName.add(tmpStr);
					        oldSprtrLangEng.setSprtrImgFileNm(fileName + "ENG" + suffix + "." + fileExt);
					        oldSprtrLangEng.setSprtrImgFileUrl(fileUrl);
					    }
					}
				}
				oldSprtrLangEng.setSprtrImgAltrtvCont(evtSprtrBoDTO.getSprtrImgAltrtvContEng());
				oldSprtrLangEng.setUdterId(loginId);
				
				eventRepository.updateEvtSprtrLang(oldSprtrLangEng);			
			}
		}
		
		if(rprstImgFile != null){
			for(String img : rprstImgFile){				
				for(String[] tmpImgNm : tmpImgName){
					if(img.equals(tmpImgNm[0])){						
 
						String tempResourcePath = this.bucketName + ":"+ getUploadImageUrlPath() + img;
						String commitResourcePath = this.bucketName + ":"+getSaveImagePath("DXM") + fileUrl+"/" +  tmpImgNm[1];			
		 
						s3FileSystem.move(tempResourcePath, commitResourcePath, CloudFileSystem.Permission.PUBLIC);	
						
						//moveTempImage(getConfigService().getProperty("ncp_base.image.base.upload.path")+fileUrl, tempFileNm, imgTempPath, tempFileNm);
					}
				}
			}
		}
	}
	
	/**
	 * @param list
	 * @throws Exception
	 */
	public void updateEvtSprtrTurn(List<EvtSprtrBoDTO> list) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); 
		
		if(list != null && list.size() > 0) {
			for( EvtSprtrBoDTO evtSprtrBoDTO : list){
				EvtSprtr evtSprtr = evtSprtrBoDTO.getEvtSprtr();
				evtSprtr.setUdterId(loginId);
				eventRepository.updateEvtSprtrTurn(evtSprtr);
			}
		}
	}
	
	/**
	 * @param list
	 * @throws Exception
	 */
	public void deleteEvtSprtr(List<EvtSprtrBoDTO> list) throws Exception {

		if(list != null && list.size() > 0) {
			for( EvtSprtrBoDTO evtSprtrBoDTO : list){
				EvtSprtr evtSprtr = evtSprtrBoDTO.getEvtSprtr();
				eventRepository.deleteEvtSprtrLang(evtSprtr);
				eventRepository.deleteEvtSprtr(evtSprtr);
			}
		}
	}

	/**
	 * 당첨자 등록
	 * @param prize
	 * @throws Exception
	 */
	public void insertEvtPrize(EvtPrize prize) throws Exception {
		eventRepository.insertEvtPrize(prize);
	}
	
	/**
	 * 당첨자 경품 등록
	 * @param prizeFreeGift
	 * @throws Exception
	 */
	public void insertEvtPrizeFreeGift(EvtPrizeFreeGift prizeFreeGift) throws Exception {
		eventRepository.insertEvtPrizeFreeGift(prizeFreeGift);
	}
	
    /**
     * 당첨자 등록
     * @param prize
     * @throws Exception
     */
    public int insertEventPrize(EventBoDTO eventBoDTO) throws Exception {

        String evtNo = eventBoDTO.getEvtApplcn().getEvtNo();
        String mbrNo = eventBoDTO.getMbrNo();

        long evtPartcptnSn = eventBoDTO.getEvtApplcn().getEvtPartcptnSn();

        EvtPrize evtPrize = new EvtPrize();
        evtPrize.setEvtPartcptnSn(evtPartcptnSn);
        evtPrize.setEvtNo(evtNo);
        if(eventBoDTO.getPrizeRank() != 0) {
        	evtPrize.setPrizeRank(eventBoDTO.getPrizeRank());
        }
        evtPrize.setPrizeDt(new Date());
        evtPrize.setRegtrId(mbrNo);
        evtPrize.setUdterId(mbrNo);
        int cnt = eventRepository.insertEventPrize(evtPrize);

        if (cnt > 0) {
            EvtPrizeFreeGift evtPrizeFreeGift = new EvtPrizeFreeGift();
            evtPrizeFreeGift.setEvtPartcptnSn(evtPartcptnSn);
            evtPrizeFreeGift.setPrizeTurn(evtPrize.getPrizeTurn());
            evtPrizeFreeGift.setEvtNo(evtNo);
            evtPrizeFreeGift.setFreeGiftTurn(eventBoDTO.getEvtApplcn().getFreeGiftTurn());
            evtPrizeFreeGift.setRegtrId(mbrNo);
            evtPrizeFreeGift.setUdterId(mbrNo);
            eventRepository.insertEventPrizeFreeGift(evtPrizeFreeGift);
        }

        return cnt;
    }

    /**
     * 당첨자 경품 등록
     * @param prizeFreeGift
     * @throws Exception
     */
    public int insertEventPrizeFreeGift(EvtPrizeFreeGift evtPrizeFreeGift) throws Exception {
        int cnt = eventRepository.insertEventPrizeFreeGift(evtPrizeFreeGift);
        return cnt;
    }

	/**
	 * 당첨자 경품 목록
	 * @param evt
	 * @return
	 * @throws Exception
	 */
	public List<PrizeFreeGiftResult> selectPrizeFreeGiftInfo(Evt evt) throws Exception {
		return eventRepository.selectPrizeFreeGiftInfo(evt);
	}
	
	/**
	 * @param applcnBoDTO
	 * @return
	 * @throws Exception
	 */
	public List<EvtApplcn> selectPrizeWinnerChoose(EvtApplcnBoDTO applcnBoDTO) throws Exception {
		return eventRepository.selectPrizeWinnerChoose(applcnBoDTO);
	}
	
//	public List<EvtApplcn> selectPrizeWinnerChoose(EvtApplcnBoDTO applcnBoDTO) throws Exception{
//		String loginId = BOSecurityUtil.getLoginId();
//		List<PrizeFreeGiftResult> freeGiftList = eventRepository.selectPrizeFreeGiftInfo(applcnBoDTO.getEvt());
//		List<EvtApplcn> resultList = eventRepository.selectPrizeWinnerChoose(applcnBoDTO);
//		
//		long cntSum = 0;
//		long cntMinus = 0;
//		String evtNo;
//		if(resultList != null){
//			for(PrizeFreeGiftResult freeGift : freeGiftList){
//				evtNo = freeGift.getFreeGiftInfo().getEvtNo();
//				long prepareQty = freeGift.getFreeGiftInfo().getPrpareFreeGiftQty(); //수량
//				long prizeQty = freeGift.getFreeGiftInfo().getPrizeQty(); //당첨인원
//				long chooseQty = freeGift.getChoseQty(); //선정인원
//				//long cnt = (prepareQty*prizeQty) - chooseQty; //등수별 남은 경품갯수
//				long cnt = prizeQty - chooseQty; //등수별 남은 경품갯수
//				cntSum += cnt;
//				cntMinus = cntSum - cnt;
//				for(long i = cntMinus ; i < cntSum ; i++){
//					if(i < resultList.size()){
//						EvtPrize prize = new EvtPrize();
//						prize.setEvtNo(evtNo);
//						prize.setEvtPartcptnSn(resultList.get((int) i).getEvtPartcptnSn());
//						prize.setPrizeRank(freeGift.getFreeGiftInfo().getPrizeRank());
//						prize.setPrizeTurn((int) i++);
//						prize.setRegtrId(loginId);
//						prize.setUdterId(loginId);
//						
//						EvtPrizeFreeGift prizeFreeGift = new EvtPrizeFreeGift();
//						prizeFreeGift.setEvtNo(evtNo);
//						prizeFreeGift.setEvtPartcptnSn(resultList.get((int) i).getEvtPartcptnSn());
//						prizeFreeGift.setFreeGiftTurn(freeGift.getFreeGiftInfo().getFreeGiftTurn());
//						prizeFreeGift.setPrizeTurn((int) i++);
//						prizeFreeGift.setRegtrId(loginId);
//						prizeFreeGift.setUdterId(loginId);
//						
//						eventRepository.insertEvtPrize(prize);
//						eventRepository.insertEvtPrizeFreeGift(prizeFreeGift);
//						
//					}
//				}
//			}
//		}
//		
//		return resultList;
//	}
	
	/**
	 * @param list
	 * @throws Exception
	 */
	public void deleteApplicant(List<EvtApplcnBoDTO> list) throws Exception{
		if(list != null && list.size() > 0) {
			//List<EvtPrize> prizeList = eventRepository.selectEvtPrize(list.get(0).getApplcn());
			for( EvtApplcnBoDTO evtApplcnBoDTO : list){
				EvtApplcn applcn = evtApplcnBoDTO.getApplcn();
				//applcn.setEvtPartcptnSn(evtApplcnBoDTO.getPrize().getEvtPartcptnSn());
				eventRepository.deleteApplicant(applcn);
			}
		}
		//리턴값 필요
	}
	
	/**
	 * @param list
	 * @throws Exception
	 */
	public void deletePrizeWinner(List<EvtApplcnBoDTO> list) throws Exception{
		if(list != null && list.size() > 0) {
			for( EvtApplcnBoDTO evtApplcnBoDTO : list){
				EvtPrize evtPrize = evtApplcnBoDTO.getPrize();
				evtPrize.setEvtNo(evtApplcnBoDTO.getApplcn().getEvtNo());
				evtPrize.setEvtPartcptnSn(evtApplcnBoDTO.getApplcn().getEvtPartcptnSn());
				if(evtApplcnBoDTO.getPrizeFreeGift().getFreeGiftTurn() != null){					
					EvtPrizeFreeGift prizeFreeGift = evtApplcnBoDTO.getPrizeFreeGift();
					prizeFreeGift.setEvtPartcptnSn(evtApplcnBoDTO.getApplcn().getEvtPartcptnSn());
					prizeFreeGift.setEvtNo(evtApplcnBoDTO.getApplcn().getEvtNo());
					prizeFreeGift.setPrizeTurn(evtApplcnBoDTO.getPrize().getPrizeTurn());
					//prizeFreeGift.setFreeGiftTurn(evtApplcnBoDTO.getPrizeFreeGift().getFreeGiftTurn());
					eventRepository.deleteEvtPrizeFreeGift(prizeFreeGift);
				}
				eventRepository.deleteEvtPrize(evtPrize);
			}
		}
	}
	
	/**
	 * @param list
	 * @throws Exception
	 */
	public void updatePrizeWinnerRank(List<EvtApplcnBoDTO> list) throws Exception{
		String loginId = BOSecurityUtil.getLoginId(); 
		Evt evt = new Evt();
		evt.setEvtNo(list.get(0).getApplcn().getEvtNo());
		List<PrizeFreeGiftResult> freeGiftList = eventRepository.selectPrizeFreeGiftInfo(evt); //준비된 상품
		
		List<EvtApplcnBoDTO> updList = new ArrayList<EvtApplcnBoDTO>();
		List<EvtApplcnBoDTO> insList = new ArrayList<EvtApplcnBoDTO>();
		
		EvtPrize ep = null;
		EvtPrizeFreeGift fg = null;
		
		for(EvtApplcnBoDTO dto : list){
			if(dto.getPrizeFreeGift().getFreeGiftTurn() == null || dto.getPrizeFreeGift().getFreeGiftTurn() == 0){
				//log.info("********************* insList add " + insList);
				insList.add(dto);
			}else{
				//log.info("********************* updList add " + updList);
				updList.add(dto);
			}
		}
		if(updList != null && updList.size() > 0){
			for(EvtApplcnBoDTO dto : updList){
				for(PrizeFreeGiftResult freeGift : freeGiftList){
					int i = 0;
					if(dto.getPrize().getPrizeRank() == freeGift.getFreeGiftInfo().getPrizeRank()){
						long prizeQty = freeGift.getFreeGiftInfo().getPrizeQty(); //당첨인원
						long chooseQty = freeGift.getChoseQty(); //선정인원
						if(prizeQty > chooseQty){
							ep = new EvtPrize();
							ep = dto.getPrize();
							ep.setEvtNo(dto.getApplcn().getEvtNo());
							ep.setEvtPartcptnSn(dto.getApplcn().getEvtPartcptnSn());
							ep.setUdterId(loginId);
							eventRepository.updatePrizeWinnerRank(dto.getPrize());
							
							fg = new EvtPrizeFreeGift();
							fg.setEvtNo(dto.getApplcn().getEvtNo());
							fg.setEvtPartcptnSn(dto.getApplcn().getEvtPartcptnSn());
							fg.setPrizeTurn(dto.getPrize().getPrizeTurn());
							fg.setFreeGiftTurn(freeGift.getFreeGiftInfo().getFreeGiftTurn());
							fg.setUdterId(loginId);
							eventRepository.updatePrizeFreeGift(fg);
							
							freeGift.setChoseQty(chooseQty+1);
							continue;
							//freeGiftList.set(this, freeGift);
						}
					}
				}
			}
		}
		if(insList != null && insList.size() > 0){
			for(EvtApplcnBoDTO dto : insList){
				for(PrizeFreeGiftResult freeGift : freeGiftList){
					if(dto.getPrize().getPrizeRank() == freeGift.getFreeGiftInfo().getPrizeRank()){
						long prizeQty = freeGift.getFreeGiftInfo().getPrizeQty(); //당첨인원
						long chooseQty = freeGift.getChoseQty(); //선정인원
						if(prizeQty > chooseQty){
							ep = new EvtPrize();
							ep = dto.getPrize();
							ep.setEvtNo(dto.getApplcn().getEvtNo());
							ep.setEvtPartcptnSn(dto.getApplcn().getEvtPartcptnSn());
							ep.setUdterId(loginId);
							eventRepository.updatePrizeWinnerRank(dto.getPrize());
							
							fg = new EvtPrizeFreeGift();
							fg.setEvtNo(dto.getApplcn().getEvtNo());
							fg.setEvtPartcptnSn(dto.getApplcn().getEvtPartcptnSn());
							fg.setPrizeTurn(dto.getPrize().getPrizeTurn());
							fg.setFreeGiftTurn(freeGift.getFreeGiftInfo().getFreeGiftTurn());
							fg.setRegtrId(loginId);
							fg.setUdterId(loginId);
							eventRepository.insertEvtPrizeFreeGift(fg);
							
							freeGift.setChoseQty(chooseQty+1);
							continue;
						}
					}
				}
			}
		}
	}
	
	/**
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public String insertEvtApplcn(List<EvtApplcn> list) throws Exception{
		//String[] result =  new String[2];
		
		String loginId = BOSecurityUtil.getLoginId(); 

		int insCnt = 0;
		if(list != null && list.size() > 0) {
			for(EvtApplcn evtApplcn : list){
				evtApplcn.setRegtrId(loginId);
				evtApplcn.setUdterId(loginId);
				eventRepository.insertEvtApplcn(evtApplcn);
				insCnt++;
			}
		}
		return ""+insCnt;
	}
	
	/**
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public String insertPrizeWinner(List<EvtApplcn> list) throws Exception{
		//String[] result =  new String[2];
		
		String loginId = BOSecurityUtil.getLoginId(); 
		Map<String,Object>conditions = new HashMap<String,Object>();
		//응모자 목록에 있는지 확인 필요
		int insCnt = 0;
		//long prizeTurn = 0;
		if(list != null && list.size() > 0) {
			for(EvtApplcn evtApplcn : list){
				EvtApplcn applcn = eventRepository.selectEvtApplcn(evtApplcn);
				//log.info("************************ applcn : " + applcn);
				if(applcn != null && applcn.getEvtPartcptnSn() != null){	
					conditions.put("EVT_PARTCPTN_SN", applcn.getEvtPartcptnSn());
		            int turn = getIdGenService().generateDBOrder(sqlSession1, "EVT_PRIZE", "PRIZE_TURN", conditions,  DatabaseType.ORACLE);
					//prizeTurn = eventRepository.selectMaxPrizeTurn(evtApplcn.getEvtNo());
					EvtPrize prize = new EvtPrize();
					prize.setEvtNo(applcn.getEvtNo());
					prize.setEvtPartcptnSn(applcn.getEvtPartcptnSn());
					//.setPrizeRank(freeGift.getFreeGiftInfo().getPrizeRank());
					prize.setPrizeTurn(turn);
					prize.setRegtrId(loginId);
					prize.setUdterId(loginId);
					
					eventRepository.insertEvtPrize(prize);
					insCnt++;
				}
			}
		}
		return ""+insCnt;
	}
	
	/**
	 * @param evt
	 * @throws Exception
	 */
	public void updateEventPrizeChoose(Evt evt) throws Exception {
		eventRepository.updateEventPrizeChoose(evt);
	}
	
	/**
	 * @param evtSprtrLang
	 * @throws Exception
	 */
	public void insertEvtSprtrLang(EvtSprtrLang evtSprtrLang) throws Exception {

	}
	
	/**
	 * @param evtSprtrLang
	 * @throws Exception
	 */
	public void updateEvtSprtrLnag(EvtSprtrLang evtSprtrLang) throws Exception {

	}
	
	/**
	 * @param evtSprtrLang
	 * @throws Exception
	 */
	public void deleteEvtSprtrLnag(EvtSprtrLang evtSprtrLang) throws Exception {
		
	}
	
	/**
     * 이벤트 적용상품.
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param sqlSession [설명]
     * @param promotionBoDTO [설명]
     * @throws Exception the exception
     * @since 2015. 5. 6
     */
    public int insertEventApplyGoods(SqlSession sqlSession, EvtAplGod evtAplGod) throws Exception {
        int result = eventRepository.insertEventApplyGoods(sqlSession, evtAplGod);
        return result;
    }
    
    /**
     * @param evtAplGod
     * @return
     * @throws Exception
     */
    public int deleteEventApplyGoods(EvtAplGod evtAplGod) throws Exception {
        int result = eventRepository.deleteEventApplyGoods(evtAplGod);
        return result;
    }

    /**
     * @param searchDTO
     * @param pageParam
     * @return
     * @throws Exception
     */
    public Page<EventApplyGoodBoResult> selectEventApplyGoodList(EventApplyGoodBoDTO searchDTO, PageParam pageParam)
            throws Exception {

        Page<EventApplyGoodBoResult> resultList = eventRepository.selectEventApplyGoodList(searchDTO, pageParam);

        return resultList;
    }
	
    /**
     * @param evtRelateGods
     * @param relateGod
     * @param loginId
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> updateRelateGodSortSeqExcel(List<EvtRelateGod> evtRelateGods, EvtRelateGod relateGod, String loginId) throws Exception {
    	int count = evtRelateGods.size();
        int std = 500;
        boolean flag = false;
        HashMap<String, Object> map = Maps.newHashMap();
        
        List<EvtRelateGodExtend> errList = new ArrayList<EvtRelateGodExtend>();
    	
        int sucsCount = 0;
		// 온라인품번 먼저 update
        for(EvtRelateGod evtRelateGod : evtRelateGods){
			if(StringService.isNotEmpty(evtRelateGod.getGodNo())){
				evtRelateGod.setEvtNo(relateGod.getEvtNo());
				evtRelateGod.setSprtrTurn(relateGod.getSprtrTurn());
				evtRelateGod.setUdterId(loginId);
				int i = eventRepository.updateRelateGodSortSeqExcel(evtRelateGod);
				//log.info("updateRelateGodSortSeqExcel =================== : " + i);
				if(i != 1){
					EvtRelateGodExtend extend = new EvtRelateGodExtend();
					extend.setGodNo(evtRelateGod.getGodNo());
					//extend.setErpGodNo(evtRelateGod.getErpGodNo());
					extend.setSortSeq(evtRelateGod.getSortSeq());
					extend.setErrMsg("상품이 존재하지 않음");
					errList.add(extend);
					sucsCount++;
				}
			}
        }

		// ERP 품번 update
		for(EvtRelateGod evtRelateGod : evtRelateGods){
			/*
			if(StringService.isNotEmpty(evtRelateGod.getErpGodNo())){
				evtRelateGod.setEvtNo(relateGod.getEvtNo());
				evtRelateGod.setSprtrTurn(relateGod.getSprtrTurn());
				evtRelateGod.setUdterId(loginId);
				int i = eventRepository.updateRelateGodSortSeqExcel(evtRelateGod);
				//log.info("updateRelateGodSortSeqExcel =================== : " + i);
				if(i != 1){
					EvtRelateGodExtend extend = new EvtRelateGodExtend();
					extend.setGodNo(evtRelateGod.getGodNo());
					//extend.setErpGodNo(evtRelateGod.getErpGodNo());
					extend.setSortSeq(evtRelateGod.getSortSeq());
					extend.setErrMsg("상품이 존재하지 않음");
					errList.add(extend);
					sucsCount++;
				}
			}
			*/
		}

        map.put("sucCount", sucsCount);
        map.put("list", errList);
        return map;
        //return eventRepository.updateRelateGodSortSeqExcel(evtRelateGods, loginId);

    }
    
    /**
     * @param evtAplGods
     * @param aplGod
     * @param loginId
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> insertAplGodUploadExcel(List<EvtAplGod> evtAplGods, EvtAplGod aplGod, String loginId) throws Exception {
    	int count = evtAplGods.size();
        int std = 500;
        boolean flag = false;
        HashMap<String, Object> map = Maps.newHashMap();
        
        Map<String,Object>conditions = new HashMap<String,Object>();
		conditions.put("EVT_NO", aplGod.getEvtNo());
        int turn = getIdGenService().generateDBOrder(sqlSession1, "EVT_APL_GOD", "APL_TURN", conditions,  DatabaseType.ORACLE);
        
        List<EvtAplGodExtend> errList = new ArrayList<EvtAplGodExtend>();
        
        List<EvtAplGod> godList = eventRepository.selectEvtAplGod(aplGod);
        //int errCount = 0;
        int suscCount = 0;
        for(EvtAplGod evtAplGod : evtAplGods){
        	boolean fl = false;
        	for(EvtAplGod god : godList){
        		if(god.getGodNo().equals(evtAplGod.getGodNo())){
        			EvtAplGodExtend extend = new EvtAplGodExtend();
        			extend.setGodNo(evtAplGod.getGodNo());
        			extend.setErrMsg("이미 등록되어 있는 상품");
        			errList.add(extend);
        			
        			fl = true;
        			break;
        		}
        	}
        	if(!fl){        		
        		evtAplGod.setEvtNo(aplGod.getEvtNo());
        		evtAplGod.setAplTurn(turn);
        		evtAplGod.setAplGodSectCd(aplGod.getAplGodSectCd());
        		evtAplGod.setGodAplYn(aplGod.getGodAplYn());
        		evtAplGod.setRegtrId(loginId);
        		evtAplGod.setUdterId(loginId);
        		turn++;
        		int i = eventRepository.insertAplGodUploadExcel(evtAplGod);        		
        		if(i != 1){
        			EvtAplGodExtend extend = new EvtAplGodExtend();
        			extend.setGodNo(evtAplGod.getGodNo());
        			extend.setErrMsg("상품이 존재하지 않음");
        			errList.add(extend);
        			//log.info("insertAplGodUploadExcel =================== : " + i);
        		}
        	}
        		//suscCount++;
        }
        //map.put("errCount", errCount);
        map.put("suscCount", suscCount);
        map.put("list", errList);
        return map;
    }
    
    /**
     * @param evtApplcns
     * @param applcn
     * @param loginId
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> insertApplicantUploadExcel(List<EvtApplcn> evtApplcns, EvtApplcn applcn, String loginId) throws Exception {
    	int count = evtApplcns.size();
        int std = 500;
        boolean flag = false;
        HashMap<String, Object> map = Maps.newHashMap();
        
        List<EvtApplcnExtend> errList = new ArrayList<EvtApplcnExtend>();
    	
        int sucsCount = 0;
        for(EvtApplcn evtApplcn : evtApplcns){
            
            long evtApplcnSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
            
        	evtApplcn.setEvtNo(applcn.getEvtNo());
        	evtApplcn.setEvtPartcptnSn(evtApplcnSn);
        	evtApplcn.setRegtrId(loginId);
        	evtApplcn.setUdterId(loginId);
        	evtApplcnSn++;
        	int i = eventRepository.insertApplicantUploadExcel(evtApplcn);
        	//log.info("insertApplicantUploadExcel =================== : " + i);
        	if(i != 1){
        		EvtApplcnExtend extend = new EvtApplcnExtend();
        		extend.setMbrNo(evtApplcn.getMbrNo());
        		extend.setErrMsg("회원이 존재하지 않음");
        		errList.add(extend);
        		sucsCount++;
        	}
        }
        map.put("sucCount", sucsCount);
        map.put("list", errList);
        return map;
        //return eventRepository.updateRelateGodSortSeqExcel(evtRelateGods, loginId);

    }
    
    /**
     * @param evtPrizes
     * @param prize
     * @param loginId
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> insertPrizeWinnerUploadExcel(List<EvtPrize> evtPrizes, EvtPrize prize, String loginId) throws Exception {
    	int count = evtPrizes.size();
        int std = 500;
        boolean flag = false;
        HashMap<String, Object> map = Maps.newHashMap();        
        List<EvtPrizeExtend> errList = new ArrayList<EvtPrizeExtend>();
        
        Map<String,Object> conditions = new HashMap<String,Object>();
        
        Evt evt = new Evt();
        evt.setEvtNo(prize.getEvtNo());
        List<PrizeFreeGiftResult> freeGiftList = eventRepository.selectPrizeFreeGiftInfo(evt);
        //EvtPrizeExtend extend = new EvtPrizeExtend();
        int sucsCount = 0;
        List<EvtPrize> tmpList = new ArrayList<EvtPrize>(evtPrizes);
//        Integer[] arrInt = {};
//        for(EvtPrize tmpList1 : evtPrizes){
//        	tmpList.add((EvtPrize) Arrays.asList(evtPrizes));
//        }
        Collections.copy(tmpList, evtPrizes);
        //tmpList = evtPrizes;
        //log.info("====================== evtPrizes.size() " + evtPrizes.size());
        if(evtPrizes != null && freeGiftList != null){
//        	for(EvtPrize tmpPrize : evtPrizes){
//        		arrInt[0] = tmpPrize.getPrizeRank();
//        		if(arrInt != null){
//        			
//        		}
//        	}
        	for(EvtPrize tmpPrize : evtPrizes){
        		for(PrizeFreeGiftResult freeGift : freeGiftList){
        			if(tmpPrize.getPrizeRank() == null || tmpPrize.getPrizeRank() == 0){
        				tmpList.remove(tmpPrize);
        				break;
        			}else{
	        			if(freeGift.getFreeGiftInfo().getPrizeRank() == tmpPrize.getPrizeRank()){
	        				tmpList.remove(tmpPrize);
	        				break;
	        			}
        			}
        		}
        	}
        	for(PrizeFreeGiftResult freeGift : freeGiftList){
        		long prepareQty = freeGift.getFreeGiftInfo().getPrpareFreeGiftQty(); //수량
				long prizeQty = freeGift.getFreeGiftInfo().getPrizeQty(); //당첨인원
				long chooseQty = freeGift.getChoseQty(); //선정인원
				long cnt = (prepareQty*prizeQty) - chooseQty; //등수별 남은 경품갯수
				//log.info("====================== rank " + freeGift.getFreeGiftInfo().getPrizeRank());
				//log.info("====================== cnt " + cnt);
		        for(EvtPrize evtPrize : evtPrizes){
		        	conditions.put("EVT_PARTCPTN_SN", evtPrize.getEvtPartcptnSn());
		            int turn = getIdGenService().generateDBOrder(sqlSession1, "EVT_PRIZE", "PRIZE_TURN", conditions,  DatabaseType.ORACLE);
		        	if(evtPrize.getEvtPartcptnSn() != null || evtPrize.getEvtPartcptnSn() != 0){
		        		if(freeGift.getFreeGiftInfo().getPrizeRank() == evtPrize.getPrizeRank()){
		        			if(cnt > 0){
			        			evtPrize.setEvtNo(prize.getEvtNo());
			        			evtPrize.setPrizeRank(freeGift.getFreeGiftInfo().getPrizeRank());
			        			evtPrize.setPrizeTurn(turn);
			        			evtPrize.setRegtrId(loginId);
			        			evtPrize.setUdterId(loginId);
			        			
			        			
			        			EvtPrizeFreeGift prizeFreeGift = new EvtPrizeFreeGift();
								prizeFreeGift.setEvtNo(evtPrize.getEvtNo());
								prizeFreeGift.setEvtPartcptnSn(evtPrize.getEvtPartcptnSn());
								prizeFreeGift.setFreeGiftTurn(freeGift.getFreeGiftInfo().getFreeGiftTurn());
								prizeFreeGift.setPrizeTurn(turn);
								prizeFreeGift.setRegtrId(loginId);
								prizeFreeGift.setUdterId(loginId);
								
								//turn++;
								
			        			int i = eventRepository.insertPrizeWinnerUploadExcel(evtPrize);
			        			
			        			//log.info("insertPrizeWinnerUploadExcel =================== : " + i);
			        			if(i != 1){
			        				EvtPrizeExtend extend = new EvtPrizeExtend();
			        				//extend.setMbrNo(evtPrize.getMbrNo());
			        				extend.setPrizeRank(evtPrize.getPrizeRank());
			        				extend.setEvtPartcptnSn(evtPrize.getEvtPartcptnSn());
			        				extend.setErrMsg("응모자가 아님");
			        				errList.add(extend);
			        				//sucsCount++;
			        			}else{
			        				eventRepository.insertEvtPrizeFreeGift(prizeFreeGift);	
			        				sucsCount++;
			        			}
			        			cnt--;
			        		}else{
			        			EvtPrizeExtend extend = new EvtPrizeExtend();
		        				//extend.setMbrNo(evtPrize.getMbrNo());
		        				extend.setPrizeRank(evtPrize.getPrizeRank());
		        				extend.setEvtPartcptnSn(evtPrize.getEvtPartcptnSn());
		        				extend.setErrMsg("선정 완료된 등수임");
		        				errList.add(extend);
		        				//sucsCount++;
			        		}
		        		}else{
		        			//등수가 없음(등수 없이 insert)
		        			if(evtPrize.getPrizeRank() == null || evtPrize.getPrizeRank() == 0){
		        				evtPrize.setEvtNo(prize.getEvtNo());
			        			evtPrize.setPrizeRank(freeGift.getFreeGiftInfo().getPrizeRank());
			        			evtPrize.setPrizeTurn(turn);
			        			evtPrize.setRegtrId(loginId);
			        			evtPrize.setUdterId(loginId);
			        			turn++;
			        			int i = eventRepository.insertPrizeWinnerUploadExcel(evtPrize);
			        			if(i != 1){
			        				EvtPrizeExtend extend = new EvtPrizeExtend();
			        				//extend.setMbrNo(evtPrize.getMbrNo());
			        				extend.setPrizeRank(evtPrize.getPrizeRank());
			        				extend.setEvtPartcptnSn(evtPrize.getEvtPartcptnSn());
			        				extend.setErrMsg("응모자가 아님");
			        				errList.add(extend);
			        				//sucsCount++;
			        			}else{
			        				sucsCount++;
			        			}
		        			}
		        		}
		        	}else{
		        		EvtPrizeExtend extend = new EvtPrizeExtend();
        				//extend.setMbrNo(evtPrize.getMbrNo());
        				extend.setPrizeRank(evtPrize.getPrizeRank());
        				extend.setEvtPartcptnSn(evtPrize.getEvtPartcptnSn());
        				extend.setErrMsg("이벤트 참여 일련번호가 없음");
        				errList.add(extend);
		        	}		        	
		        }
        	}
        	if(tmpList != null || tmpList.size() > 0){
        		for(EvtPrize evtPrize : tmpList){
        			EvtPrizeExtend extend = new EvtPrizeExtend();
        			extend.setPrizeRank(evtPrize.getPrizeRank());
    				extend.setEvtPartcptnSn(evtPrize.getEvtPartcptnSn());
    				extend.setErrMsg("등수 없음");
    				errList.add(extend);
        		}
        	}
        }
        //log.info("sucCount ===================================== " + sucsCount);
        map.put("sucCount", sucsCount);
        map.put("list", errList);
        return map;
        //return eventRepository.updateRelateGodSortSeqExcel(evtRelateGods, loginId);

    }
    
    /**
     * 당첨자 공지 위한 당첨자 목록 조회
     * @param evt
     * @return
     * @throws Exception
     */
    public List<EventPrizeBoResult> selectPrizeWinnerListNotice(Evt evt) throws Exception {
		return eventRepository.selectPrizeWinnerListNotice(evt);
	}
    
 
    /**
     * @param applcnBoDTO
     * @return
     * @throws Exception
     */
    public long selectApplcnListCount(EvtApplcnBoDTO applcnBoDTO) throws Exception {   	
    	return eventRepository.selectApplcnListCount(applcnBoDTO);
    }
    
    /**
     * @param applcnBoDTO
     * @return
     * @throws Exception
     */
    public List<EventApplcnBoResult> selectApplcnList(EvtApplcnBoDTO applcnBoDTO) throws Exception {
		return eventRepository.selectApplcnList(applcnBoDTO);
	}
    
    /**
     * @param applcnBoDTO
     * @return
     * @throws Exception
     */
    public long selectPrizeWinnerListCount(EvtApplcnBoDTO applcnBoDTO) throws Exception {
    	return eventRepository.selectPrizeWinnerListCount(applcnBoDTO);
    }
    
    /**
     * @param applcnBoDTO
     * @return
     * @throws Exception
     */
    public List<EventApplcnBoResult> selectPrizeWinnerList(EvtApplcnBoDTO applcnBoDTO) throws Exception {
		return eventRepository.selectPrizeWinnerList(applcnBoDTO);
	}
    
    /**
     * @param applcnBoDTO
     * @return
     */
    public List<Map<String, Object>> selectApplicantListExcel(EvtApplcnBoDTO applcnBoDTO) {		
		return eventRepository.selectApplicantListExcel(applcnBoDTO);
	}

	/**
	 * @param applcnBoDTO
	 * @return
	 */
	public List<Map<String, Object>> selectPrizeWinnerListExcel(EvtApplcnBoDTO applcnBoDTO) {		
		return eventRepository.selectPrizeWinnerListExcel(applcnBoDTO);
	}

	/**
	 * @param searchDTO
	 * @return
	 */
	public EventFoDTO selectPayBagEvtApplcnOrdList(EventSearchFoDTO searchDTO) throws Exception {
		EventExtendsFoDTO evt = this.selectEventView(searchDTO);
		EventFoDTO evtDetail = new EventFoDTO();

		evtDetail.setEventExt(evt);
		//	주문내역 조회
		if(StringService.isNotEmpty(searchDTO.getMbrNo())){
			//searchDTO.setBegDt("20150101");	//	테스트용 기간
			searchDTO.setBegDt(DateService.parseString(evt.getApplcnBegDt(), "yyyyMMdd"));
			searchDTO.setEndDt(DateService.parseString(evt.getApplcnEndDt(), "yyyyMMdd"));
			searchDTO.setPchAmt(new BigDecimal("100000"));
			evtDetail.setOrdList(eventRepository.selectNoClaimEvtApplCnOrdList(searchDTO));
			
			
			//	응모 여부 확인
			String applyYn = this.checkEventApplCn(searchDTO);
			if(StringService.isEmpty(applyYn)){
				evtDetail.setEvtApplYn("N");				
			}else{
				evtDetail.setEvtApplYn("Y");
			}
		}
		
		return evtDetail;
	}

	/**
	 * @param applcnBoDTO
	 * @return
	 */
	public int insertEvtApplCn(EvtApplcnBoDTO applcnBoDTO) throws Exception{
		
		int insCnt = 0;
		EventSearchFoDTO searchDTO = new EventSearchFoDTO();
		searchDTO.setEvtNo(applcnBoDTO.getApplcn().getEvtNo());
		searchDTO.setMbrNo(applcnBoDTO.getApplcn().getMbrNo());
				
		//	응모 여부 확인
		String applyYn = this.checkEventApplCn(searchDTO);
		if(StringService.isEmpty(applyYn)){			
			insCnt = eventRepository.insertEvtApplcn(applcnBoDTO.getApplcn());					
		}
		
		return insCnt;		
	}
	
	/**
	 * @param searchDTO
	 * @return
	 */
	public String checkEventApplCn(EventSearchFoDTO searchDTO) throws Exception{
		return eventRepository.checkEventApplCn(searchDTO);
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
        return eventRepository.selecEvtFreeGiftList(searchDTO);
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
        return eventRepository.seleceventCouponDownIssueCheck(searchDTO);
    }

    /**
     * 타임쿠폰 경품지급 건 COUNT
     * @param evtNo
     * @return
     * @throws Exception
     */
    public int getFreeGiftIssuCount(String evtNo) throws Exception {
    	return this.getFreeGiftIssuCount(evtNo, null);
    }
    public int getFreeGiftIssuCount(String evtNo, Integer freeGiftTurn) throws Exception {
    	return eventRepository.getFreeGiftIssuCount(evtNo, freeGiftTurn);
    }

	/**
	 * 이벤트경품적용기간 삭제
	 * @param evtFreeGiftAplPd
	 * @return
	 * @throws Exception
	 */
	public int deleteEvtFreeGiftAplPd(EvtFreeGiftAplPd evtFreeGiftAplPd) throws Exception {
		return eventRepository.deleteEvtFreeGiftAplPd(evtFreeGiftAplPd);
	}

    
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * 이미지 temp 경로 설정
	 * @param uploadImageType
	 * @return
	 * @throws Exception
	 */
	private String getTempUploadImagePath( String uploadImageType  ) throws Exception{		
		return getConfigService().getProperty( "ncp_base.image."+uploadImageType+".upload.temp.path" );	
	}
	
	/**
	 * 이미지 경로 설정
	 * @param uploadImageType
	 * @return
	 * @throws Exception
	 */
	private String getUploadImagePath( String evtNo  ) throws Exception{
		String imgPath = "";
		imgPath = "/"+evtNo.substring(2, 6)+"/"+evtNo.substring(6, 8)+"/"+evtNo.substring(8, 10)+"/";
		return imgPath;	
	}
	public List<String> selectEvtApplMemList(String evtNo) {
		return eventRepository.selectEvtApplMemList(evtNo);
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
	 * @return
	 * @throws Exception
	 * @since 2015. 6. 26.
	 */
	public boolean checkEventEnable(String evtNo) throws Exception {
		return eventRepository.checkEventEnable(evtNo);
	}
	
	
	
    public void insertEvtImgGodApl(EvtAplGod evtAplGod, List<String> rprstImgFile) throws Exception {
        String imgTempPath = getTempUploadImagePath("base");
        String imgPath = getUploadImagePath(evtAplGod.getEvtNo());
        String fileUrl = eventHttpPath + imgPath + evtAplGod.getEvtNo() + "/sprtr";
        String uploadUrl = eventUploadPath + imgPath + evtAplGod.getEvtNo() + "/sprtr";

        String suffix = DateService.getStringCurrentToday() + DateService.getStringCurrentHourMinuteSecond();

        String loginId = BOSecurityUtil.getLoginId();

        List<String[]> tmpImgName = new ArrayList<String[]>();

        EvtAplGod update = evtAplGodRepository.selectEvtAplGod(evtAplGod);

        String fileName = evtAplGod.getEvtNo() + "_" + update.getAplTurn() + "_";
        String tempFileNm = "";
        String fileExt = "";
        //기본 구분 타이틀(한국어) 세팅
        if (evtAplGod.getPcGodImgUrl() != "" && evtAplGod.getPcGodImgUrl() != null) {
            tempFileNm = evtAplGod.getPcGodImgUrl();
            fileExt = tempFileNm.substring(tempFileNm.lastIndexOf(".") + 1);
            String[] tmpStr = { evtAplGod.getPcGodImgUrl(), fileName + "PC" + "_" + suffix + "." + fileExt };
            if (tempFileNm.indexOf("promotion") == -1) {
                update.setPcGodImgUrl(fileUrl + "/" + fileName + "PC" + "_" + suffix + "." + fileExt);
                tmpImgName.add(tmpStr);

            }

            update.setPcGodImgDscr(evtAplGod.getPcGodImgDscr());
        }
        if (evtAplGod.getMobileGodImgUrl() != "" && evtAplGod.getMobileGodImgUrl() != null) {
            tempFileNm = evtAplGod.getMobileGodImgUrl();
            fileExt = tempFileNm.substring(tempFileNm.lastIndexOf(".") + 1);
            String[] tmpStr = { evtAplGod.getMobileGodImgUrl(), fileName + "MB" + "_" + suffix + "." + fileExt };
            if (tempFileNm.indexOf("promotion") == -1) {
                update.setMobileGodImgUrl(fileUrl + "/" + fileName + "MB" + "_" + suffix + "." + fileExt);
                tmpImgName.add(tmpStr);
            }

            update.setMobileGodImgDscr(evtAplGod.getPcGodImgDscr());
        }

        update.setUdterId(loginId);

        evtAplGodRepository.updateEvtAplGod(update);

        if (rprstImgFile != null) {
            for (String img : rprstImgFile) {
                for (String[] tmpImgNm : tmpImgName) {
                    if (img.equals(tmpImgNm[0])) {
                    	
                    	
    					String tempResourcePath = this.bucketName + ":"+ getUploadImageUrlPath() + img;
						String commitResourcePath = this.bucketName + ":"+getSaveImagePath("DXM") + fileUrl+"/" +  tmpImgNm[1];			
		 
						s3FileSystem.move(tempResourcePath, commitResourcePath, CloudFileSystem.Permission.PUBLIC);	
 
                        //log.info( " imageFile.getAbsolutePath=>"+imageFile.getAbsolutePath()  );
 
                        //moveTempImage(getConfigService().getProperty("ncp_base.image.base.upload.path")+fileUrl, tempFileNm, imgTempPath, tempFileNm);
                    }
                }
            }
        }
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
        	if (EventEnum.YES.toString().equals(result.getApplcnDateCheck()) && EventEnum.YES.toString().equals(result.getDeviceCheck())
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
     * 이벤트 응모 유효성 체크
     * 
     * @param eventBoDTO
     * @return
     * @throws Exception
     */
    public EventBoResult validEventApplcn(EventBoDTO eventBoDTO) throws Exception {
        EventBoResult result = eventRepository.validEventApplcn(eventBoDTO);
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
        EventBoResult result = eventRepository.selectEventTotalPrize(eventBoDTO);
        return result;
    }
    
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
        EventBoResult ebr = eventRepository.validEventApplcn(eventBoDTO);
        eventBoDTO.setEventBoResult(ebr);
        
        Integer freeGiftTurn = ebr.getEvtFreeGiftInfo() != null ? ebr.getEvtFreeGiftInfo().getFreeGiftTurn() : null;
        String freeGiftKndCd = ebr.getEvtFreeGiftInfo() != null ? ebr.getEvtFreeGiftInfo().getFreeGiftKndCd() : null;
        
        String evtNo = ebr.getEvt().getEvtNo();
        String evtTpCd = ebr.getEvt().getEvtTpCd();
        
        int applcnCnt = 0;
        boolean procCheck = true;  // 가능여부 체크
        if (EventEnum.YES.toString().equals(ebr.getApplcnCheck())) {
            
            Integer stmpCount = null;
            Integer atendDaycnt = null;
            
            // 스탬프
			if (EventType.STMP.toString().equals(evtTpCd) && ebr.getEvtFreeGiftInfo() != null) {
				stmpCount = ebr.getApplcnCount() + 1;
			} // 출석체크
			else if (EventType.ATEND_CHK.toString().equals(evtTpCd) && ebr.getEvtFreeGiftInfo() != null) {
				atendDaycnt = ebr.getApplcnCount() + 1;
			} // 전체추첨
			else if (EventType.ALL_DRWT.toString().equals(evtTpCd)) {
				//freeGiftTurn = null;
			}
			else if (EventType.ALL_PRIZE.toString().equals(evtTpCd)) {
				if (freeGiftTurn == null) {
					procCheck = false;
				}
			}
			else if (EventType.TM_CPN.toString().equals(evtTpCd)) {
				if (freeGiftTurn == null || (EventFreeGiftKind.COUPON.toString().equals(freeGiftKndCd)
				        && EventEnum.NO.toString().equals(ebr.getPrmAplPdCheck()))) {
					procCheck = false;
				}
			}
			else {
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
                
                applcnCnt = eventRepository.insertEventApplcn(eventBoDTO.getEvtApplcn());
            }
            // 이벤트 참여 방법 insert
            if (applcnCnt > 0) {
                
                EvtApplcn evtApplcn = eventBoDTO.getEvtApplcn();
                
                // 스탬프
                if (EventType.STMP.toString().equals(evtTpCd)) {
                    
                    EvtStmp evtStmp = eventBoDTO.getEvtStmp();
                    
                    evtStmp.setEvtNo(evtApplcn.getEvtNo());
                    evtStmp.setFreeGiftTurn(evtApplcn.getFreeGiftTurn());
                    evtStmp.setMbrNo(evtApplcn.getMbrNo());
                    evtStmp.setStmpDt(evtApplcn.getApplcnDt());
                    evtStmp.setEvtPartcptnSn(evtApplcn.getEvtPartcptnSn());
                    
                    evtStmp.setRegtrId(mbrNo);
                    evtStmp.setUdterId(mbrNo);
                    eventRepository.insertEvtStmp(evtStmp);
                }
            }
        } else {
            String applcnCountSectCd = ebr.getEvt().getApplcnCountSectCd();
            ebr.setResultCode(EventResultCode.NO_MORE_CHANCE.toString());
            ebr.setResultAdtnCode(applcnCountSectCd);
        }
        eventBoDTO.setEventBoResult(ebr);
        
        return applcnCnt;
    }
    
    /**
     * 스탬프 등록
     * 
     * @param eventBoDTO
     * @return
     * @throws Exception
     */
    public int insertEvtStmp(EventBoDTO eventBoDTO) throws Exception {
    	String mbrNo = eventBoDTO.getMbrNo();
    	EvtApplcn evtApplcn = eventBoDTO.getEvtApplcn();
		
        EvtStmp evtStmp = new EvtStmp();
        if(eventBoDTO.getEvtStmp() != null) {
        	evtStmp = eventBoDTO.getEvtStmp();
        }
        
        evtStmp.setEvtNo(evtApplcn.getEvtNo());
        evtStmp.setFreeGiftTurn(evtApplcn.getFreeGiftTurn());
        evtStmp.setMbrNo(evtApplcn.getMbrNo());
        evtStmp.setStmpDt(evtApplcn.getApplcnDt());
        evtStmp.setEvtPartcptnSn(evtApplcn.getEvtPartcptnSn());
        
        evtStmp.setRegtrId(mbrNo);
        evtStmp.setUdterId(mbrNo);
        
    	return eventRepository.insertEvtStmp(evtStmp);
    }
    
    /**
     * 스탬프 건수 조회
     * 
     * @param eventBoDTO
     * @param total : TOTAL OR MBR(TOTAL : 금일 전체 응모 건수 조회, MBR : 회원의 응모 건수 조회)
     * @return
     * @throws Exception
     */
    public int selectEvtStmpCount(EventBoDTO eventBoDTO, String type) throws Exception {
    	EvtStmpExtend evtStmpExtend = new EvtStmpExtend();
        
    	evtStmpExtend.setEvtNo(eventBoDTO.getEvtNo());
    	evtStmpExtend.setFreeGiftTurn(eventBoDTO.getFreeGiftTurn());
        if(type == null || "MBR".equals(type)) {
        	evtStmpExtend.setMbrNo(eventBoDTO.getMbrNo());	
        }
        evtStmpExtend.setSelectType(type);
        
    	return eventRepository.selectEvtStmpCount(evtStmpExtend);
    }
    
    /**
     * 이벤트 응모 유효성 체크
     */
    public List<EventBoResult> validEventApplcnList(EventBoDTO eventBoDTO) throws Exception {
    	return eventRepository.validEventApplcnList(eventBoDTO);
    }

	/**
	 * 이벤트 대상자 등록
	 * @param evtTgtMbrList
	 * @param loginId
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveEvtTgtMbrExcel(List<EvtPartcptnTgtMbr> evtTgtMbrList, String loginId) throws Exception {
		HashMap<String, Object> map = Maps.newHashMap();

		List<EvtPartcptnTgtMbr> errList = new ArrayList<EvtPartcptnTgtMbr>();

		int sucsCount = 0;
		for(EvtPartcptnTgtMbr evtTgtMbr : evtTgtMbrList){
			if((evtTgtMbrList != null && evtTgtMbrList.size() == 1) && StringService.isEmpty(evtTgtMbr.getMbrNo())){
				eventRepository.deleteEvtTgtMbrExcel(evtTgtMbr);
			}else{
				evtTgtMbr.setUdterId(loginId);
				int i = eventRepository.saveEvtTgtMbrExcel(evtTgtMbr);
				if(i != 1){
					EvtPartcptnTgtMbr extend = new EvtPartcptnTgtMbr();
					extend.setEvtNo(evtTgtMbr.getEvtNo());
					extend.setMbrNo(evtTgtMbr.getMbrNo());
					errList.add(extend);
					sucsCount++;
				}
			}
		}
		map.put("sucCount", sucsCount);
		map.put("list", errList);
		return map;
	}


	/**
     * SNS 인증정보 리스트 조회
     */
    public List<MbrSns> selectMbrSnsList(MbrSns mbrSns) throws Exception {
    	return eventRepository.selectMbrSnsList(mbrSns);
    }

    /**
     * SNS 인증정보 등록
     */
    public int insertMbrSnsMerge(MbrSns mbrSns) throws Exception {
    	return eventRepository.insertMbrSnsMerge(mbrSns);
    }

    /**
     * 최종사용 SNS 여부 update
     */
    public int updateMbrSnsLastUseYn(MbrSns mbrSns) throws Exception {
    	return eventRepository.updateMbrSnsLastUseYn(mbrSns);
    }
    
    /**
     * 이벤트 응모 유효성 체크_1
     * 
     * @param eventBoDTO
     * @return
     * @throws Exception
     */
    public EventBoResult validEventApplcn_1(EventBoDTO eventBoDTO) throws Exception {
        EventBoResult result = eventRepository.validEventApplcn_1(eventBoDTO);
        return result;
    }


	/**
	 * 이벤트 약관 동의 등록.
	 * @param evtStplatAgr
	 * @throws Exception
	 */
	public void insertEvtStplatAgr(EvtStplatAgr evtStplatAgr) throws Exception {
		evtStplatAgrRepository.insertEvtStplatAgr(evtStplatAgr);
	}


	/**
	 * 이벤트 약관 조회
	 * @param sysStplat
	 * @return
	 * @throws Exception
	 */
	public List<SysStplat> selectStplatList(String sysStplat) throws Exception {
		return eventRepository.selectStplatList(sysStplat);
	}

	/**
	 * 이벤트 응모가능여부
	 * 
	 */
	public String selectCampaginEvtInfo(String evtNo) {
	    return eventRepository.selectCampaginEvtInfo(evtNo);
    }

	/**
	 * 이벤트댓글 전시여부 수정
	 */
	public int updateEventReply(List<EvtApplcnBoDTO> list){

		String loginId = BOSecurityUtil.getLoginId();

		int cnt = 0;
		for(int i=0; i<list.size(); i++){
			EvtSnsReply evtSnsReply = new EvtSnsReply();
			evtSnsReply = list.get(i).getSnsReply();
			evtSnsReply.setUdterId(loginId);
			eventRepository.updateEventReply(evtSnsReply);
			cnt++;
		}
		return cnt;
	}

	/**
	 * 이벤트댓글 전시여부별 건수
	 */
	public Map<String, String> selectEvtReplyDspCount(EvtApplcnBoDTO evtApplcnBoDTO){
		return eventRepository.selectEvtReplyDspCount(evtApplcnBoDTO);
	}

	/**
	 * 이벤트 댓글 목록 Count
	 * @param applcnBoDTO
	 * @return
	 * @throws Exception
	 */
	public long selectEvtSnsReplyListCount(EvtApplcnBoDTO applcnBoDTO) throws Exception {
		return eventRepository.selectEvtSnsReplyListCount(applcnBoDTO);
	}

	/**
	 * 이벤트 댓글 목록
	 * @param applcnBoDTO
	 * @return
	 * @throws Exception
	 */
	public List<EventApplcnBoResult> selectEvtSnsReplyList(EvtApplcnBoDTO applcnBoDTO) throws Exception {
		return eventRepository.selectEvtSnsReplyList(applcnBoDTO);
	}

	/**
	 * 이벤트 댓글 엑셀
	 * @param applcnBoDTO
	 * @return
	 */
	public List<Map<String, Object>> selectEvtSnsReplyListExcel(EvtApplcnBoDTO applcnBoDTO) throws Exception {
		return eventRepository.selectEvtSnsReplyListExcel(applcnBoDTO);
	}
	
	/**
	 * 이벤트 시스템 정책 데이터
	 * @param sysPlcVal
	 * @param containKey
	 * @return
	 * @throws Exception
	 */
	public String getEventSysPlcVal(SysPlcValExtend sysPlcValExtend, String containKey) throws Exception{
		
		String plcVal = "";
		
		List<String> sysPlcValList = sysPolicyRepository.getMvSysPlcVal(sysPlcValExtend);
		if(sysPlcValList != null && sysPlcValList.size() > 0){
			if(StringService.isEmpty(containKey)){
				plcVal = sysPlcValList.get(0);
				
			}else{
				Iterator<String> it = sysPlcValList.iterator();
				String it_val = it.next();
				
				if(it_val.contains(containKey)){
					plcVal = it_val;
				}
			}
		}
		
		return plcVal;
	}
	
	/**
	 * 성명, ID 등 마스킹 처리 후 반환(FN_MASKING 사용)
	 * 
	 * @param paramMap maskingType, sourceString, maskingYn
	 * maskingType	- 'FLNM':성명, 'ID':ID
	 * sourceString	- 마스킹할 string
	 * maskingYn	- 마스킹 처리 여부 'Y', 'N'
	 * @return
	 * @throws Exception
	 */
	public String selectFnMakingData(Map<String,String> paramMap) throws Exception {
		return eventRepository.selectFnMakingData(paramMap);
	}
	public void updateEvtList(List<EventBoDTO> gridList) {
		Evt evt = null;
		String loginId = BOSecurityUtil.getLoginId();
		
		for(EventBoDTO eventBoDTO: gridList) {
			evt = eventBoDTO.getEvt();
			evt.setRegtrId(loginId);
			evt.setUdterId(loginId);
			eventRepository.updateEvtForGrid(evt);
		}
		
	}
}
