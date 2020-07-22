package com.plgrim.ncp.cmp.promotion.bo.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtAplGod;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftAplPd;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtImg;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPartcptnTgtMbr;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPrize;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPrizeFreeGift;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtRelateGod;
import com.plgrim.ncp.base.enums.PromotionEnum;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionAplGoods;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionSeparator;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionStat;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefJobCd;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefJobDetail;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefSectCd;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoCommandService;
import com.plgrim.ncp.biz.promotion.data.EventApplyGoodBoDTO;
import com.plgrim.ncp.biz.promotion.data.EventBoDTO;
import com.plgrim.ncp.biz.promotion.data.EvtApplcnBoDTO;
import com.plgrim.ncp.biz.promotion.data.EvtRelateGodBoDTO;
import com.plgrim.ncp.biz.promotion.data.EvtSprtrBoDTO;
import com.plgrim.ncp.biz.promotion.result.EventApplcnBoResult;
import com.plgrim.ncp.biz.promotion.result.EventApplyGoodBoResult;
import com.plgrim.ncp.biz.promotion.result.EventPrizeBoResult;
import com.plgrim.ncp.biz.promotion.result.PrizeFreeGiftResult;
import com.plgrim.ncp.biz.promotion.service.EventService;
import com.plgrim.ncp.cmp.promotion.bo.PromotionBOComponentImpl;
import com.plgrim.ncp.cmp.promotion.bo.PromotionEventBOComponent;
import com.plgrim.ncp.commons.service.EditorService;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;

@Transactional(value = "transactionManager")
@Component
public class PromotionEventBOComponentImpl extends PromotionBOComponentImpl implements PromotionEventBOComponent {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	MemberPersonalInfoCommandService memberPersonalInfoCommandService;
	
	@Autowired
	private EditorService editorService;
	
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#insertEvent(com.plgrim.ncp.biz.promotion.data.EventBoDTO, java.util.List)
	 */
	@Override
    public String insertEvent(EventBoDTO eventBoDTO, List<String> rprstImgFile) throws Exception {

        String evtNoGen = getIdGenService().generateDBNumber(sqlSession1, "SQ_EVT", "EV", DatabaseType.ORACLE);
        String evtNo = String.valueOf(evtNoGen);

        eventBoDTO.getEvt().setEvtNo(evtNo);

    	// 에디터로 등록된 이미지의 경로 변경
    	this.moveUncommitedImg(eventBoDTO);
        
        //이벤트 등록
        eventService.insertEvent(eventBoDTO);

        //이미지 등록
        List<EvtImg> imgList = setEvtImg(eventBoDTO.getEvt(), rprstImgFile);
        eventService.insertEvtImg(imgList, eventBoDTO.getEvt());
        
        //적용대상 등록
    	eventService.insertEvtPartcptnTgt(sqlSession1, eventBoDTO);
       
    	 //전시대상 등록
    	eventService.insertEvtDspTgt(sqlSession1, eventBoDTO);
    	
    	
        EvtAplGod pagAdd = new EvtAplGod();
        
        pagAdd.setEvtNo(evtNo);
        pagAdd.setGodAplYn(PromotionEnum.YES.toString());
        pagAdd.setAplGodSectCd(PromotionAplGoods.ALL.toString());
        pagAdd.setRegtrId(eventBoDTO.getEvt().getRegtrId());
        pagAdd.setUdterId(eventBoDTO.getEvt().getRegtrId());
        
        eventService.insertEventApplyGoods(sqlSession1, pagAdd);    	
    	
        return evtNo;
    }
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#deleteEvtFreeGiftAplPd(com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftAplPd)
	 */
    @Override
	public int deleteEvtFreeGiftAplPd(EvtFreeGiftAplPd evtFreeGiftAplPd) throws Exception {
		return eventService.deleteEvtFreeGiftAplPd(evtFreeGiftAplPd);
	}
    
    /**
     * 에디터로 등록된 이미지의 경로 변경
     * 
     * @param eventBoDTO
     * @throws Exception
     */
    private void moveUncommitedImg(EventBoDTO eventBoDTO) throws Exception {
    	String commitResourcePath = eventService.getEvtImgPath(eventBoDTO.getEvt());
    	String pcHtml = eventBoDTO.getEvt().getPcHtml();
    	String mobileHtml = eventBoDTO.getEvt().getMobileHtml();
		//	이미지 무브
		if(StringService.contains(pcHtml, "uncommited")) {
			pcHtml = editorService.commitContentsImagesFromTemp(pcHtml, commitResourcePath, true);
			eventBoDTO.getEvt().setPcHtml(pcHtml);
		}
		if(StringService.contains(mobileHtml, "uncommited")) {
			mobileHtml = editorService.commitContentsImagesFromTemp(mobileHtml, commitResourcePath, true);
			eventBoDTO.getEvt().setMobileHtml(mobileHtml);
		}
    }
    
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#updateEvent(com.plgrim.ncp.biz.promotion.data.EventBoDTO, java.util.List)
     */
    @Override
    public int updateEvent(EventBoDTO eventBoDTO, List<String> rprstImgFile) throws Exception {
        
    	// 에디터로 등록된 이미지의 경로 변경
    	this.moveUncommitedImg(eventBoDTO);

        int result = 0;
        if (PromotionStat.APRV.toString().equals(eventBoDTO.getEvt().getEvtStatCd())) {
            result = eventService.updateEventAprv(eventBoDTO);
        }
        else {
            result = eventService.updateEvent(eventBoDTO);
        }
        
        List<EvtImg> imgList = new ArrayList<EvtImg>();

        if(rprstImgFile != null){
        	imgList = setEvtImg(eventBoDTO.getEvt(), rprstImgFile);
        	eventService.updateEvtImg(imgList, eventBoDTO.getEvtImgList(), eventBoDTO.getEvt());
        }
        if(eventBoDTO.getEvt().getRprstLwprtSectCd().equals("LWPRT_EVT") && eventBoDTO.getEvt().getEvtStatCd().equals("APRV_WAIT")){ 
        	eventService.updateEvtPartcptnTgt(eventBoDTO);	// 이벤트 적용대상 수정
        	eventService.updateEvtDspTgt(sqlSession1, eventBoDTO);	// 이벤트 전시대상 수정
        }
        return result;
    }
    
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#updateEventStatus(com.plgrim.ncp.biz.promotion.data.EventBoDTO)
     */
    @Override
    public int updateEventStatus(EventBoDTO eventBoDTO) throws Exception {
        int result = eventService.updateEventStatus(eventBoDTO);
        return result;
    }
    
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#insertEventApplyGoods(com.plgrim.ncp.biz.promotion.data.EventBoDTO)
     */
	@Override
    public int insertEventApplyGoods(EventBoDTO eventBoDTO) throws Exception {

        String evtNo = eventBoDTO.getEvtAplGod().getEvtNo();
        String godAplYn = eventBoDTO.getEvtAplGod().getGodAplYn();
        String aplGodSectCd = eventBoDTO.getEvtAplGod().getAplGodSectCd();
        
        // 이전 적용상품구분코드
        String beforeAplGodSectCd = eventBoDTO.getBeforeAplGodSectCd();
        // 이전 제외상품구분코드
        String beforeExcGodSectCd = eventBoDTO.getBeforeExcGodSectCd();
        
        // 선택된 적용상품구분코드
        String checkedAplGodSectCd = eventBoDTO.getCheckedAplGodSectCd();
        // 선택된 제외상품구분코드
        String checkedExcGodSectCd = eventBoDTO.getCheckedExcGodSectCd();

        String regtrId = eventBoDTO.getEvtAplGod().getRegtrId();
        String udterId = eventBoDTO.getEvtAplGod().getUdterId();
        
        
        // 적용대상 상품이 변경된 경우
        if (!checkedAplGodSectCd.equals(beforeAplGodSectCd)) {
            
            EvtAplGod pagDel = new EvtAplGod();
            
            pagDel.setEvtNo(evtNo);
            pagDel.setAplGodSectCd(beforeAplGodSectCd);
            pagDel.setGodAplYn(PromotionEnum.YES.toString());
            
            // 이전 적용상품값 제거
            eventService.deleteEventApplyGoods(pagDel);
            
            // 전체대상일 경우 구분값 insrt
            if (PromotionAplGoods.ALL.toString().equals(checkedAplGodSectCd)) {
                
                EvtAplGod pagAdd = new EvtAplGod();
                
                pagAdd.setEvtNo(evtNo);
                pagAdd.setGodAplYn(PromotionEnum.YES.toString());
                pagAdd.setAplGodSectCd(checkedAplGodSectCd);
                pagAdd.setRegtrId(regtrId);
                pagAdd.setUdterId(udterId);
                
                eventService.insertEventApplyGoods(sqlSession1, pagAdd);
            // } else if (EventAplGoods.GOD.toString().equals(checkedAplGodSectCd)) {
            //     
            //     pagDel.setEvtNo(evtNo);
            //     pagDel.setAplGodSectCd(null);
            //     pagDel.setGodAplYn(PromotionEnum.NO.toString());
            //     
            //     // 이전 적용상품값 제거
            //     eventApplyService.deleteEventApplyGoods(pagDel);
            } else {
                pagDel.setEvtNo(evtNo);
                pagDel.setAplGodSectCd(null);
                pagDel.setGodAplYn(PromotionEnum.NO.toString());
                
                // 이전 적용상품값 제거
                eventService.deleteEventApplyGoods(pagDel);
            }
        }
        
        // 제외대상 상품이 변경된 경우
        if (StringService.isNotEmpty(beforeExcGodSectCd) && !checkedExcGodSectCd.equals(beforeExcGodSectCd)) {
            
            EvtAplGod pagDel = new EvtAplGod();
            
            pagDel.setEvtNo(evtNo);
            pagDel.setAplGodSectCd(beforeExcGodSectCd);
            pagDel.setGodAplYn(PromotionEnum.NO.toString());
            
            // 이전 제외상품값 제거
            eventService.deleteEventApplyGoods(pagDel);
        }

        if (PromotionAplGoods.BRND.toString().equals(aplGodSectCd)) {

            for (String brndId : eventBoDTO.getEvtAplGod().getBrndId().split(PromotionSeparator.DELIMITER.toString())) {

                EvtAplGod pag = new EvtAplGod();
                pag.setBrndId(brndId);

                pag.setEvtNo(evtNo);
                pag.setGodAplYn(godAplYn);
                pag.setAplGodSectCd(aplGodSectCd);
                pag.setRegtrId(regtrId);
                pag.setUdterId(udterId);

                eventService.insertEventApplyGoods(sqlSession1, pag);
            }
        }
        else if (PromotionAplGoods.DSP_CTGRY.toString().equals(aplGodSectCd)) {

            for (String dspCtgryNo : eventBoDTO.getEvtAplGod().getDspCtgryNo().split(PromotionSeparator.DELIMITER.toString())) {

                EvtAplGod pag = new EvtAplGod();
                pag.setDspCtgryNo(dspCtgryNo);

                pag.setEvtNo(evtNo);
                pag.setGodAplYn(godAplYn);
                pag.setAplGodSectCd(aplGodSectCd);
                pag.setRegtrId(regtrId);
                pag.setUdterId(udterId);

                eventService.insertEventApplyGoods(sqlSession1, pag);
            }
        }
        else if (PromotionAplGoods.GOD.toString().equals(aplGodSectCd)) {

            for (String godNo : eventBoDTO.getEvtAplGod().getGodNo().split(PromotionSeparator.DELIMITER.toString())) {

                EvtAplGod pag = new EvtAplGod();
                pag.setGodNo(godNo);

                pag.setEvtNo(evtNo);
                pag.setGodAplYn(godAplYn);
                pag.setAplGodSectCd(aplGodSectCd);
                pag.setRegtrId(regtrId);
                pag.setUdterId(udterId);

                eventService.insertEventApplyGoods(sqlSession1, pag);
            }
        }
        else {
            // 값이 없습니다.
        }

        return 0;
    }
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#deleteEventApplyGoods(com.plgrim.ncp.biz.promotion.data.EventBoDTO)
	 */
    @Override
    public int deleteEventApplyGoods(EventBoDTO eventBoDTO) throws Exception {

        int result = 0;
        String evtNo = eventBoDTO.getEvtAplGod().getEvtNo();
        String aplTurns = eventBoDTO.getAplTurns();

        if (StringService.isNotEmpty(aplTurns)) {
            
            for (String aplTurn : aplTurns.split(PromotionSeparator.DELIMITER.toString())) {
                
                EvtAplGod pag = new EvtAplGod();
                
                pag.setEvtNo(evtNo);
                pag.setAplTurn(Integer.parseInt(aplTurn));
                
                result += eventService.deleteEventApplyGoods(pag);
            }
        }

        return result;
    }
    
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#insertEvtImgGodApl(com.plgrim.ncp.base.entities.datasource1.evt.EvtAplGod, java.util.List)
     */
    @Override
    public void insertEvtImgGodApl(EvtAplGod evtAplGod, List<String> rprstImgFile) throws Exception {
        eventService.insertEvtImgGodApl(evtAplGod, rprstImgFile);
    }

    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#insertFreeGift(java.util.List, java.util.List, java.util.List)
     */
    @Override
    public void insertFreeGift(List<EventBoDTO> insList, List<EventBoDTO> updList, List<EventBoDTO> delList) throws Exception {
    	eventService.insertFreeGiftInfo(insList);
    	eventService.updateFreeGiftInfo(updList);
    	eventService.deleteFreeGiftInfo(delList);

    }
 
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#insertEvtSprtr(com.plgrim.ncp.biz.promotion.data.EvtSprtrBoDTO, java.util.List)
     */
	@Override
	public void insertEvtSprtr(EvtSprtrBoDTO evtSprtrBoDTO, List<String> imgFile) throws Exception {
		eventService.insertEvtSprtr(evtSprtrBoDTO, imgFile);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#updateEvtSprtr(com.plgrim.ncp.biz.promotion.data.EvtSprtrBoDTO, java.util.List)
	 */
	@Override
	public void updateEvtSprtr(EvtSprtrBoDTO evtSprtrBoDTO, List<String> imgFile) throws Exception {
		eventService.updateEvtSprtr(evtSprtrBoDTO, imgFile);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#updateEvtSprtrTurn(java.util.List)
	 */
	@Override
	public void updateEvtSprtrTurn(List<EvtSprtrBoDTO> list) throws Exception{
		eventService.updateEvtSprtrTurn(list);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#deleteEvtSprtr(java.util.List)
	 */
	@Override
	public void deleteEvtSprtr(List<EvtSprtrBoDTO> list) throws Exception{
		eventService.deleteEvtSprtr(list);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#insertEvtRelateGod(java.util.List)
	 */
	@Override
	public String[] insertEvtRelateGod(List<EvtRelateGod> list) throws Exception{
		
		return eventService.insertEvtRelateGod(list);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#updateEvtRelateGod(java.util.List)
	 */
	@Override
	public void updateEvtRelateGod(List<EvtRelateGodBoDTO> list) throws Exception{
		eventService.updateEvtRelateGod(list);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#updateRelateGodSortSeq(com.plgrim.ncp.biz.promotion.data.EvtRelateGodBoDTO)
	 */
	@Override
	public void updateRelateGodSortSeq(EvtRelateGodBoDTO relateGodBoDTO) throws Exception{
		eventService.updateRelateGodSortSeq(relateGodBoDTO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#deleteEvtRelateGod(java.util.List)
	 */
	@Override
	public void deleteEvtRelateGod(List<EvtRelateGodBoDTO> list) throws Exception{
		eventService.deleteEvtRelateGod(list);
	}

	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#updatePrizeWinner(com.plgrim.ncp.biz.promotion.data.EvtApplcnBoDTO)
	 */
	@Override
	public void updatePrizeWinner(EvtApplcnBoDTO applcnBoDTO) throws Exception{
		String loginId = BOSecurityUtil.getLoginId();
		List<PrizeFreeGiftResult> freeGiftList = eventService.selectPrizeFreeGiftInfo(applcnBoDTO.getEvt());
		String histCd = applcnBoDTO.getEvt().getPrizeHistCd();
		if(!histCd.equals("PRIZE_HIST_UNLMIT")){
			applcnBoDTO.setHistory(histCd.replace("PRIZE_HIST_", ""));
		}
		List<EvtApplcn> resultList = eventService.selectPrizeWinnerChoose(applcnBoDTO);
		
		long cntSum = 0;
		long cntMinus = 0;
		String evtNo;
		if(resultList != null){
			for(PrizeFreeGiftResult freeGift : freeGiftList){
				evtNo = freeGift.getFreeGiftInfo().getEvtNo();
				
				long prizeQty = freeGift.getFreeGiftInfo().getPrizeQty(); //당첨인원
				long chooseQty = freeGift.getChoseQty(); //선정인원
				//long cnt = (prepareQty*prizeQty) - chooseQty; //등수별 남은 경품갯수
				long cnt = prizeQty - chooseQty; //등수별 남은 경품갯수
				cntSum += cnt;
				cntMinus = cntSum - cnt;
				for(long i = cntMinus ; i < cntSum ; i++){
					if(i < resultList.size()){
						Map<String,Object>conditions = new HashMap<String,Object>();
						conditions.put("EVT_PARTCPTN_SN", resultList.get((int) i).getEvtPartcptnSn());
						int turn = getIdGenService().generateDBOrder(sqlSession1, "EVT_PRIZE", "PRIZE_TURN", conditions,  DatabaseType.ORACLE);
						
						EvtPrize prize = new EvtPrize();
						prize.setEvtNo(evtNo);
						prize.setEvtPartcptnSn(resultList.get((int) i).getEvtPartcptnSn());
						prize.setPrizeRank(freeGift.getFreeGiftInfo().getPrizeRank());
						prize.setPrizeTurn(turn);
						prize.setRegtrId(loginId);
						prize.setUdterId(loginId);
						
						EvtPrizeFreeGift prizeFreeGift = new EvtPrizeFreeGift();
						prizeFreeGift.setEvtNo(evtNo);
						prizeFreeGift.setEvtPartcptnSn(resultList.get((int) i).getEvtPartcptnSn());
						prizeFreeGift.setFreeGiftTurn(freeGift.getFreeGiftInfo().getFreeGiftTurn());
						prizeFreeGift.setPrizeTurn(turn);
						prizeFreeGift.setRegtrId(loginId);
						prizeFreeGift.setUdterId(loginId);
						
						eventService.insertEvtPrize(prize);
						eventService.insertEvtPrizeFreeGift(prizeFreeGift);
						
					}
				}
			}
			applcnBoDTO.getEvt().setUdterId(loginId);
			eventService.updateEventPrizeChoose(applcnBoDTO.getEvt());
		}
		
		//return resultList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#deleteApplicant(java.util.List)
	 */
	@Override
	public void deleteApplicant(List<EvtApplcnBoDTO> list) throws Exception{
		eventService.deleteApplicant(list);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#deletePrizeWinner(java.util.List)
	 */
	@Override
	public void deletePrizeWinner(List<EvtApplcnBoDTO> list) throws Exception{
		eventService.deletePrizeWinner(list);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#updatePrizeWinnerRank(java.util.List)
	 */
	@Override
	public void updatePrizeWinnerRank(List<EvtApplcnBoDTO> list) throws Exception{
		eventService.updatePrizeWinnerRank(list);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#insertEvtApplcn(java.util.List)
	 */
	@Override
	public String insertEvtApplcn(List<EvtApplcn> list) throws Exception{
		return eventService.insertEvtApplcn(list);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#insertPrizeWinner(java.util.List)
	 */
	@Override
	public String insertPrizeWinner(List<EvtApplcn> list) throws Exception{
		return eventService.insertPrizeWinner(list);
	}
    
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#updateRelateGodSortSeqExcel(java.util.List, com.plgrim.ncp.base.entities.datasource1.evt.EvtRelateGod, java.lang.String)
	 */
	@Override
    public HashMap<String, Object> updateRelateGodSortSeqExcel(List<EvtRelateGod> evtRelateGods, EvtRelateGod relateGod, String loginId) throws Exception {
        return eventService.updateRelateGodSortSeqExcel(evtRelateGods, relateGod, loginId);

    }
    
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#insertAplGodUploadExcel(java.util.List, com.plgrim.ncp.base.entities.datasource1.evt.EvtAplGod, java.lang.String)
	 */
	@Override
    public HashMap<String, Object> insertAplGodUploadExcel(List<EvtAplGod> evtAplGods, EvtAplGod aplGod, String loginId) throws Exception {
        return eventService.insertAplGodUploadExcel(evtAplGods, aplGod, loginId);

    }
    
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#insertApplicantUploadExcel(java.util.List, com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn, java.lang.String)
	 */
	@Override
    public HashMap<String, Object> insertApplicantUploadExcel(List<EvtApplcn> applcns, EvtApplcn applcn, String loginId) throws Exception {
        return eventService.insertApplicantUploadExcel(applcns, applcn, loginId);

    }
    
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#insertPrizeWinnerUploadExcel(java.util.List, com.plgrim.ncp.base.entities.datasource1.evt.EvtPrize, java.lang.String)
	 */
	@Override
    public HashMap<String, Object> insertPrizeWinnerUploadExcel(List<EvtPrize> prizes, EvtPrize prize, String loginId) throws Exception {
        return eventService.insertPrizeWinnerUploadExcel(prizes, prize, loginId);

    }
 
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#selectApplcnList(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.promotion.data.EvtApplcnBoDTO, com.plgrim.ncp.framework.page.PageParam)
	 */
	@Override
    public Page<EventApplcnBoResult> selectApplcnList(SystemPK systemPK, EvtApplcnBoDTO applcnBoDTO, PageParam pageParam) throws Exception {
    	String loginId = BOSecurityUtil.getLoginId();
    	applcnBoDTO.setLang(systemPK.getLang());
    	long listCount = eventService.selectApplcnListCount(applcnBoDTO);
    	List<EventApplcnBoResult> list = new ArrayList<EventApplcnBoResult>();
    	if(listCount > 0){
    		list = eventService.selectApplcnList(applcnBoDTO);
    	}
    	//개인 정보 이용 이력 등록
		if(listCount > 0){
			String[][] usefCdInfo = { // 구분, 업무, 업무상세
					{UsefSectCd.PSNL_INFO_STTUS.toString(), UsefJobCd.INQIRE.toString(), UsefJobDetail.LIST.toString()}
			};
			String[] target = {};
			memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
					, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
					, target					// 이용대상 : 회원
					, null						// 유입 일련번호
					, applcnBoDTO.getAccessMenuSn()			// 메뉴 일련번호
					, loginId					// 로그인 ID
					);
		}
    	return new PageImpl<EventApplcnBoResult>(list, pageParam.getPageable(), listCount);
	}
    
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#selectPrizeWinnerList(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.promotion.data.EvtApplcnBoDTO, com.plgrim.ncp.framework.page.PageParam)
	 */
	@Override
    public Page<EventApplcnBoResult> selectPrizeWinnerList(SystemPK systemPK, EvtApplcnBoDTO applcnBoDTO, PageParam pageParam) throws Exception {
    	String loginId = BOSecurityUtil.getLoginId();
    	applcnBoDTO.setLang(systemPK.getLang());
    	long listCount = eventService.selectPrizeWinnerListCount(applcnBoDTO);
    	List<EventApplcnBoResult> list = new ArrayList<EventApplcnBoResult>();
    	if(listCount > 0){
    		list = eventService.selectPrizeWinnerList(applcnBoDTO);
    	}
    	//개인 정보 이용 이력 등록
		if(listCount > 0){
			String[][] usefCdInfo = { // 구분, 업무, 업무상세
					{UsefSectCd.PSNL_INFO_STTUS.toString(), UsefJobCd.INQIRE.toString(), UsefJobDetail.LIST.toString()}
			};
			String[] target = {};
			memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
					, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
					, target					// 이용대상 : 회원
					, null						// 유입 일련번호
					, applcnBoDTO.getAccessMenuSn()			// 메뉴 일련번호
					, loginId					// 로그인 ID
					);
		}
    	return new PageImpl<EventApplcnBoResult>(list, pageParam.getPageable(), listCount);
	}
    
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#selectApplicantListExcel(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.promotion.data.EvtApplcnBoDTO)
	 */
	@Override
    public List<Map<String, Object>> selectApplicantListExcel(SystemPK systemPK, EvtApplcnBoDTO applcnBoDTO) throws Exception {
    	String loginId = BOSecurityUtil.getLoginId();
    	applcnBoDTO.setLang(systemPK.getLang());

    	List<Map<String, Object>> list = eventService.selectApplicantListExcel(applcnBoDTO);
    	//개인 정보 이용 이력 등록
    	if(list != null && list.size() > 0){
			String[][] usefCdInfo = { // 구분, 업무, 업무상세
					{UsefSectCd.PSNL_INFO_STTUS.toString(), UsefJobCd.INQIRE.toString(), UsefJobDetail.LIST.toString()}
			};
			String[] target = {};
			memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
					, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
					, target					// 이용대상 : 회원
					, null						// 유입 일련번호
					, applcnBoDTO.getAccessMenuSn()			// 메뉴 일련번호
					, loginId					// 로그인 ID
					);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#selectPrizeWinnerListExcel(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.promotion.data.EvtApplcnBoDTO)
	 */
	@Override
	public List<Map<String, Object>> selectPrizeWinnerListExcel(SystemPK systemPK, EvtApplcnBoDTO applcnBoDTO) throws Exception {
		String loginId = BOSecurityUtil.getLoginId();
    	applcnBoDTO.setLang(systemPK.getLang());

    	List<Map<String, Object>> list = eventService.selectPrizeWinnerListExcel(applcnBoDTO);
    	//개인 정보 이용 이력 등록
    	if(list != null && list.size() > 0){
			String[][] usefCdInfo = { // 구분, 업무, 업무상세
					{UsefSectCd.PSNL_INFO_STTUS.toString(), UsefJobCd.INQIRE.toString(), UsefJobDetail.LIST.toString()}
			};
			String[] target = {};
			memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
					, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
					, target					// 이용대상 : 회원
					, null						// 유입 일련번호
					, applcnBoDTO.getAccessMenuSn()			// 메뉴 일련번호
					, loginId					// 로그인 ID
					);
		}
		return list;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#saveEvtTgtMbrExcel(java.util.List, java.lang.String)
	 */
	@Override
	public HashMap<String, Object> saveEvtTgtMbrExcel(List<EvtPartcptnTgtMbr> evtTgtMbrList, String loginId) throws Exception{
		return eventService.saveEvtTgtMbrExcel(evtTgtMbrList, loginId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#updateEventReply(java.util.List)
	 */
	@Override
	public int updateEventReply(List<EvtApplcnBoDTO> list) throws Exception{
		return eventService.updateEventReply(list);
	}

	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#selectEvtSnsReplyList(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.promotion.data.EvtApplcnBoDTO, com.plgrim.ncp.framework.page.PageParam)
	 */
	@Override
	public Page<EventApplcnBoResult> selectEvtSnsReplyList(SystemPK systemPK, EvtApplcnBoDTO applcnBoDTO, PageParam pageParam) throws Exception {
		String loginId = BOSecurityUtil.getLoginId();
		applcnBoDTO.setLang(systemPK.getLang());
		long listCount = eventService.selectEvtSnsReplyListCount(applcnBoDTO);
		List<EventApplcnBoResult> list = new ArrayList<EventApplcnBoResult>();
		if(listCount > 0){
			list = eventService.selectEvtSnsReplyList(applcnBoDTO);
		}
		//개인 정보 이용 이력 등록
		if(listCount > 0){
			String[][] usefCdInfo = { // 구분, 업무, 업무상세
					{UsefSectCd.PSNL_INFO_STTUS.toString(), UsefJobCd.INQIRE.toString(), UsefJobDetail.LIST.toString()}
			};
			String[] target = {};
			memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
					, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
					, target					// 이용대상 : 회원
					, null						// 유입 일련번호
					, applcnBoDTO.getAccessMenuSn()			// 메뉴 일련번호
					, loginId					// 로그인 ID
			);
		}
		return new PageImpl<EventApplcnBoResult>(list, pageParam.getPageable(), listCount);
	}

	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#selectEvtSnsReplyListExcel(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.promotion.data.EvtApplcnBoDTO)
	 */
	@Override
	public List<Map<String, Object>> selectEvtSnsReplyListExcel(SystemPK systemPK, EvtApplcnBoDTO applcnBoDTO) throws Exception {
		String loginId = BOSecurityUtil.getLoginId();
		applcnBoDTO.setLang(systemPK.getLang());

		List<Map<String, Object>> list = eventService.selectEvtSnsReplyListExcel(applcnBoDTO);
		//개인 정보 이용 이력 등록
		if(list != null && list.size() > 0){
			String[][] usefCdInfo = { // 구분, 업무, 업무상세
					{UsefSectCd.PSNL_INFO_STTUS.toString(), UsefJobCd.INQIRE.toString(), UsefJobDetail.LIST.toString()}
			};
			String[] target = {};
			memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
					, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
					, target					// 이용대상 : 회원
					, null						// 유입 일련번호
					, applcnBoDTO.getAccessMenuSn()			// 메뉴 일련번호
					, loginId					// 로그인 ID
			);
		}
		return list;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#selectEventApplyGoodList(com.plgrim.ncp.biz.promotion.data.EventApplyGoodBoDTO, com.plgrim.ncp.framework.page.PageParam)
	 */
	@Override
    public Page<EventApplyGoodBoResult> selectEventApplyGoodList(EventApplyGoodBoDTO searchDTO, PageParam pageParam)
            throws Exception {

        Page<EventApplyGoodBoResult> resultList = eventService.selectEventApplyGoodList(searchDTO, pageParam);

        return resultList;
    }
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#selectPrizeWinnerListNotice(com.plgrim.ncp.base.entities.datasource1.evt.Evt)
	 */
	@Override
	public List<EventPrizeBoResult> selectPrizeWinnerListNotice(Evt evt) throws Exception {
		return eventService.selectPrizeWinnerListNotice(evt);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#getFreeGiftIssuCount(java.lang.String)
	 */
    @Override
    public int getFreeGiftIssuCount(String evtNo) throws Exception {
    	return eventService.getFreeGiftIssuCount(evtNo);
    }
    
    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.promotion.bo.EventComponent#selectEvtReplyDspCount(com.plgrim.ncp.biz.promotion.data.EvtApplcnBoDTO)
     */
    @Override
    public Map<String, String> selectEvtReplyDspCount(EvtApplcnBoDTO evtApplcnBoDTO) throws Exception{
        return eventService.selectEvtReplyDspCount(evtApplcnBoDTO);
    }
    
	private List<EvtImg> setEvtImg(Evt evt, List<String> rprstImgFile){
	   List<EvtImg> imgList = new ArrayList<EvtImg>();
	   for(int i = 0 ; i < rprstImgFile.size() ; i++){
		   String fn = rprstImgFile.get(i);
		   EvtImg evtImg = new EvtImg();
		   String[] arrText = fn.split(":");
	 
		   evtImg.setEvtNo(evt.getEvtNo());
		   evtImg.setImgFileNm(arrText[0]);
		   evtImg.setImgAltrtvCont(arrText[1]);
		   evtImg.setEvtImgSectCd(arrText[2]);
		   evtImg.setDvcSectCd(arrText[3]);

		   if("RPRST1".equals(arrText[2])){
			   evtImg.setImgTurn(1);
			   evtImg.setEvtImgSectCd("RPRST_IMG_1PCE");
			   
			   if(arrText.length >4){
				   evtImg.setImgExpsrTxt1Cont(arrText[4]);
			   }
			   if(arrText.length >5){
				   evtImg.setImgExpsrTxt2Cont(arrText[5]);
			   }
 
		   }else if("RPRST2".equals(arrText[2])){
			   evtImg.setImgTurn(2);
			   evtImg.setEvtImgSectCd("RPRST_IMG_2PCE");
			   if(arrText.length >4){
				   evtImg.setImgExpsrTxt1Cont(arrText[4]);
			   }
			   if(arrText.length >5){
				   evtImg.setImgExpsrTxt2Cont(arrText[5]);
			   }
		   }else if ("BCRN".equals(arrText[2])){
			   if("MOBILE".equals(arrText[3])){
				   evtImg.setImgTurn(4);
			   }else{
				   evtImg.setImgTurn(3);
			   }
		   }else{
			   if("MOBILE".equals(arrText[3])){
				   evtImg.setImgTurn(6);
			   }else{
				   evtImg.setImgTurn(5);
			   }
		   }
		   
		   evtImg.setRegtrId(evt.getRegtrId());
		   evtImg.setUdterId(evt.getRegtrId());
		   imgList.add(evtImg);
	   }
	   return imgList;
	}

	@Override
	public void updateEvtList(List<EventBoDTO> gridList) {
		try {
			eventService.updateEvtList(gridList);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
