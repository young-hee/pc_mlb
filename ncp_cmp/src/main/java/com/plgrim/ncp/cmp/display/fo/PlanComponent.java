package com.plgrim.ncp.cmp.display.fo;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import com.plgrim.ncp.base.entities.datasource1.evt.EvtSnsReply;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmResult;
import com.plgrim.ncp.biz.display.data.DspCnrConttExt;
import com.plgrim.ncp.biz.display.data.DspPromtScFrDTO;
import com.plgrim.ncp.biz.display.result.DspCnrFrResult;
import com.plgrim.ncp.biz.display.result.DspPlanFoResult;
import com.plgrim.ncp.biz.promotion.data.EventBoDTO;
import com.plgrim.ncp.biz.promotion.data.EventFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventSearchFoDTO;
import com.plgrim.ncp.biz.promotion.result.EventBoResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

public interface PlanComponent {

	public DspPlanFoResult selectDxmPlan(DspPromtScFrDTO dspPromtScFrDTO) throws Exception;
	
	public DspPlanFoResult selectMallPlan(DspPromtScFrDTO dspPromtScFrDTO) throws Exception;

	public EventFoDTO selectDxmEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception;
	
	public EventFoDTO selectMallEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception;
	
	public Page<EvtSnsReply>  selectSnsReplyList(EventSearchFoDTO eventSearchFoDTO, PageParam pageParam) throws Exception;
	
	public void insertReply(EvtSnsReply reply, String mbrNo) throws Exception;
	
	public void updateEvtSnsReplyDspYn(EvtSnsReply reply, String mbrNo) throws Exception;

	public void selectDxmEventPlanList(Model model, DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam) throws Exception;

	public Page<DspPlanFoResult> selectDxmEventPlanList(DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam) throws Exception;

	public Map<String, String> checkEventTarget(String evtNo, String mbrNo, SystemPK pk) throws Exception;
	
	public PrmResult selectWebPointInfo(String mbrNo);
	
	public PrmResult selectWebPointInfoNew(MbrWebpntHist mbrWebpntHist);
	
	public boolean checkEventEnable(EventBoDTO eventBoDTO) throws Exception;
	
	public EventBoResult checkEventEnableResult(EventBoDTO eventBoDTO) throws Exception;
	
	public void selectMallEventPlanList(Model model, DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam) throws Exception;
	
	public Page<DspPlanFoResult> selectMallEventPlanPage(Model model, DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam) throws Exception;
	
	public List<?> selectMallEventPlanCnncList(Model model, DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam) throws Exception;
	
	/**
	 * Discoverer 기획전 조회
	 * 
	 * @param model
	 * @param dspPromtScFrDTO
	 * @param pageParam
	 * @throws Exception
	 */
	public void selectDxmDiscovererPlanList(Model model, DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam) throws Exception;
	
	/**
	 * Discoverer 기획전 조회(MB)
	 * 
	 * @param dspPromtScFrDTO
	 * @param pageParam
	 * @returtn Page<DspPlanFoResult>
	 * @throws Exception
	 */
	public Page<DspPlanFoResult> selectDxmDiscovererPlanList(DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam) throws Exception;
	
	/**
	 * Discoverer 기획전 비디오 목록 조회
	 * 
	 * @param model
	 * @param dspPromtScFrDTO
	 * @param pageParam
	 * @throws Exception
	 */
	public void selectDxmDiscovererPlanVideoList(Model model, DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam) throws Exception;
	
	/**
	 * Discoverer 기획전 비디오 조회(MB)
	 * 
	 * @param dspPromtScFrDTO
	 * @param pageParam
	 * @returtn DspCnrFrResult
	 * @throws Exception
	 */
	public DspCnrFrResult selectDxmDiscovererPlanVideoList(DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam) throws Exception;

	public DspPlanFoResult selectMallPlanAdvance(DspPromtScFrDTO dspPromtScFrDTO) throws Exception;

	public Page<DspCnrConttExt> searchDisplayGodPageList(DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam) throws Exception;

	public EventFoDTO selectMallEventAdvance(EventSearchFoDTO eventSearchFoDTO) throws Exception;

	public Page<DspCnrConttExt> searchEvtGodPageList(EventSearchFoDTO eventSearchFoDTO, PageParam pageParam) throws Exception;
}
