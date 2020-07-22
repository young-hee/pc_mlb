package com.plgrim.ncp.cmp.display.fo;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspUseGrp;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPartcptnTgt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtSnsReply;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmResult;
import com.plgrim.ncp.base.enums.EventEnum;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionAplTarget;
import com.plgrim.ncp.base.repository.evt.EvtApplcnRepository;
import com.plgrim.ncp.base.repository.evt.EvtRepository;
import com.plgrim.ncp.base.repository.evt.EvtSnsReplyRepository;
import com.plgrim.ncp.biz.display.data.DspCnrConttExt;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.data.DspPromtScFrDTO;
import com.plgrim.ncp.biz.display.result.DspCnrFrResult;
import com.plgrim.ncp.biz.display.result.DspPlanFoResult;
import com.plgrim.ncp.biz.display.service.DxmDisplayService;
import com.plgrim.ncp.biz.display.service.MbmDisplayService;
import com.plgrim.ncp.biz.display.service.PlanService;
import com.plgrim.ncp.biz.display.service.SamDisplayService;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.promotion.data.EventBoDTO;
import com.plgrim.ncp.biz.promotion.data.EventFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventSearchFoDTO;
import com.plgrim.ncp.biz.promotion.result.EventBoResult;
import com.plgrim.ncp.biz.promotion.service.EventService;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.page.PageService;

@Component
public class PlanComponentImpl extends AbstractComponent implements PlanComponent {

	@Autowired
	private PlanService planService;

	@Autowired
	private EventService eventService;

	@Autowired
	private EvtApplcnRepository evtApplcnRepository;

	@Autowired
	private EvtSnsReplyRepository evtSnsReplyRepository;

	@Autowired
	private EvtRepository evtRepository;

	@Autowired
	private DxmDisplayService dxmDisplayService;
	
	@Autowired
	private MbmDisplayService mbmDisplayService;
	
	@Autowired
	private SamDisplayService samDisplayService;
	
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;

	@Override
	public DspPlanFoResult selectDxmPlan(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		String device = dspPromtScFrDTO.getDevice();
		DspPlanFoResult dspPlanFoResult = planService.selectDxmPlan(dspPromtScFrDTO);

		dspPlanFoResult.setPlanCornerList(planService.selectPlanCornerList(dspPromtScFrDTO));

		if ("STYLE_PROMT".equals(dspPromtScFrDTO.getPromtTpCd())) {
			dspPromtScFrDTO.setDevice(device);
			dspPlanFoResult.setStyleList(planService.selectDxmStyleList(dspPromtScFrDTO));
		}
		else if("DX_PROMT".equals(dspPromtScFrDTO.getPromtTpCd())) {
			if(dspPromtScFrDTO.getPromtGrpSn() != null) {
				dspPlanFoResult.setDiscovererPlanListByGroup(planService.selectDxmDiscovererPlanListByGroupWithoutSelf(dspPromtScFrDTO));
			}
			else {
				DspPromt dspPromt = planService.selectDxmDiscovererPlanGroupInfo(dspPromtScFrDTO);
				if(dspPromt != null) {
					dspPromtScFrDTO.setDevice(device);
					dspPromtScFrDTO.setPromtGrpSn(dspPromt.getPromtGrpSn());
					dspPlanFoResult.setDiscovererPlanListByGroup(planService.selectDxmDiscovererPlanListByGroupWithoutSelf(dspPromtScFrDTO));
				}
			}
		}
		return dspPlanFoResult;
	}
	
	@Override
	public DspPlanFoResult selectMallPlan(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {

		DspPlanFoResult dspPlanFoResult = planService.selectMallPlan(dspPromtScFrDTO);

		dspPlanFoResult.setPlanCornerList(planService.selectMallPlanCornerList(dspPromtScFrDTO));

		return dspPlanFoResult;
	}

	@Override
	public EventFoDTO selectDxmEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		EventFoDTO eventFoDTO = planService.selectDxmEvt(eventSearchFoDTO);
		return eventFoDTO;
	}

	@Override
	public Page<EvtSnsReply> selectSnsReplyList(EventSearchFoDTO eventSearchFoDTO, PageParam pageParam)
			throws Exception {
		return eventService.selectSnsReplyList(eventSearchFoDTO, pageParam);
	}

	@Override
	public void insertReply(EvtSnsReply reply, String mbrNo) throws Exception {
		// 1. 응모 테이블 insert
		long evtApplcnSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
		EvtApplcn evtApplcn = new EvtApplcn();
		evtApplcn.setEvtPartcptnSn(evtApplcnSn);
		evtApplcn.setEvtNo(reply.getEvtNo());
		evtApplcn.setMbrNo(mbrNo);
		long time = System.currentTimeMillis();
		Date regDt = new Date(time);
		evtApplcn.setApplcnDt(regDt);
		evtApplcn.setUdterId(mbrNo);
		evtApplcn.setRegtrId(mbrNo);
		evtApplcnRepository.insertEvtApplcn(evtApplcn);

		Evt evt = new Evt();
		evt.setEvtNo(reply.getEvtNo());
		evt = evtRepository.selectEvt(evt);
		if ("SNS_REPLY".equals(evt.getEvtTpCd())) {
			// 2. 댓글 테이블 insert
			long snsReplySn = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_REPLY", DatabaseType.ORACLE);
			reply.setReplySn(snsReplySn);
			reply.setEvtPartcptnSn(evtApplcnSn);
			reply.setUdterId(mbrNo);
			reply.setRegtrId(mbrNo);
			reply.setDspYn("Y");
			evtSnsReplyRepository.insertEvtSnsReply(reply);
		}

	}
	
	@Override
	public void updateEvtSnsReplyDspYn(EvtSnsReply reply, String mbrNo) throws Exception {
		
		reply.setUdterId(mbrNo);
		reply.setDspYn("N");
		evtSnsReplyRepository.updateEvtSnsReplyDspYn(reply);
	}

	@Override
	public void selectDxmEventPlanList(Model model, DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam)
			throws Exception {

		Page<DspPlanFoResult> evtList = planService.selectDxmEventPlanList(dspPromtScFrDTO, pageParam);
		PageService.makePageResult(evtList, model);
		model.addAttribute("evt", evtList.getContent());
		model.addAttribute("totalRow", evtList.getTotalElements());
		if (StringUtils.isNotEmpty(dspPromtScFrDTO.getDspUseGrpTpCd())) {
			DspCtgryScFrDTO dspCtgryScFrDTO = new DspCtgryScFrDTO();
			dspCtgryScFrDTO.setDspUseGrpTpCd(dspPromtScFrDTO.getDspUseGrpTpCd());
			DspUseGrp dspUseGrp = mbmDisplayService.selectDspUseGrp(dspCtgryScFrDTO); // THMA02A01
			dspCtgryScFrDTO.setDspCtgryNo(dspUseGrp.getDspCtgryNo());
			dspCtgryScFrDTO.setMallId(dspPromtScFrDTO.getMallId());
			dspCtgryScFrDTO.setDevice(dspPromtScFrDTO.getDevice());
			dspCtgryScFrDTO.setLang(dspPromtScFrDTO.getLang());
			dspCtgryScFrDTO.setPrcSectCd(dspPromtScFrDTO.getPrcSectCd());

			model.addAttribute("contt", planService.selectDxmEventPlanContt(dspCtgryScFrDTO));
		}
		model.addAttribute("dspPromtScFrDTO", dspPromtScFrDTO);
	}

	@Override
	public Map<String, String> checkEventTarget(String evtNo, String mbrNo, SystemPK pk) throws Exception {
		String mbrTpCd = "";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbrTpCd = userDetail.getMbr().getMbrTpCd();
		}

		// 응모 자격 체크
		List<EvtPartcptnTgt> targets = eventService.selectEvtPartcptnTgtList(evtNo);
		boolean isAvailableDevice = false;
		boolean isAvailableMember = false;
		String targetDevice = "";
		String targetMember = "";
		for (EvtPartcptnTgt target : targets) {
			if (PromotionAplTarget.DVC.toString().equals(target.getPartcptnTgtTpCd())) {
				if ("MOBILE_WEB".equals(target.getDvcCd())) {
					if (StringUtils.isEmpty(targetDevice)) {
						targetDevice += "모바일웹";
					} else {
						targetDevice += ", 모바일웹";
					}
				} else if ("MOBILE_APP".equals(target.getDvcCd())) {
					if (StringUtils.isEmpty(targetDevice)) {
						targetDevice += "모바일앱";
					} else {
						targetDevice += ", 모바일앱";
					}
				} else if ("PC".equals(target.getDvcCd())) {
					if (StringUtils.isEmpty(targetDevice)) {
						targetDevice += "PC";
					} else {
						targetDevice += ", PC";
					}
				}

				if (pk.getDevice().equals(target.getDvcCd())) {
					isAvailableDevice = true;
				}
			}

			if (PromotionAplTarget.TGT_MBR_TP.toString().equals(target.getPartcptnTgtTpCd())) {
				if ("ONLINE_MBR".equals(target.getTgtMbrTpCd())) {
					targetMember = "온라인 회원";
				} else if ("UNITY_MBR".equals(target.getTgtMbrTpCd())) {
					targetMember = "통합 회원";
				}
				if (mbrTpCd.equals(target.getTgtMbrTpCd())) {
					isAvailableMember = true;
				}
			}
		}
		String resultCode = "OK";
		if (!isAvailableDevice) {
			resultCode = "INVALID_DEVICE";
		}
		if (!isAvailableMember) {
			resultCode = "INVALID_MEMBER";
		}

		Map<String, String> result = new HashMap<String, String>();
		result.put("resultCode", resultCode);
		result.put("targetDevice", targetDevice);
		result.put("targetMember", targetMember);
		return result;
	}
	
	@Override
	public boolean checkEventEnable(EventBoDTO eventBoDTO) throws Exception {
		EventBoResult validResult = eventService.validEventApplcn_1(eventBoDTO);
		return EventEnum.YES.toString().equals(validResult.getApplcnCheck());
	}

	@Override
	public EventBoResult checkEventEnableResult(EventBoDTO eventBoDTO) throws Exception {
		EventBoResult validResult = eventService.validEventApplcn_1(eventBoDTO);
		return validResult;
	}

	@Override
	public PrmResult selectWebPointInfo(String mbrNo) {
		return planService.selectWebPointInfo(mbrNo);
	}
	
	@Override
	public PrmResult selectWebPointInfoNew(MbrWebpntHist mbrWebpntHist) {
		return planService.selectWebPointInfoNew(mbrWebpntHist);
	}

	@Override
	public Page<DspPlanFoResult> selectDxmEventPlanList(DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam)
			throws Exception {
 
		return planService.selectDxmEventPlanList(dspPromtScFrDTO, pageParam);
	}

	@Override
	public void selectMallEventPlanList(Model model, DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam)
			throws Exception {
		
		Page<DspPlanFoResult> evtList = planService.selectMallEventPlanList(dspPromtScFrDTO, pageParam);
		PageService.makePageResult(evtList, model);
		model.addAttribute("evt", evtList.getContent());
		model.addAttribute("totalRow", evtList.getTotalElements());
		if (StringUtils.isNotEmpty(dspPromtScFrDTO.getDspUseGrpTpCd())) {
			DspCtgryScFrDTO dspCtgryScFrDTO = new DspCtgryScFrDTO();
			dspCtgryScFrDTO.setDspUseGrpTpCd(dspPromtScFrDTO.getDspUseGrpTpCd());
			DspUseGrp dspUseGrp = new DspUseGrp();
			if("MBM".equals(dspPromtScFrDTO.getMallId())){
				dspUseGrp = mbmDisplayService.selectDspUseGrp(dspCtgryScFrDTO); // THMA02A01
			}else{
				dspUseGrp = samDisplayService.selectDspUseGrp(dspCtgryScFrDTO); // THMA02A01
			}
			dspCtgryScFrDTO.setDspCtgryNo(dspUseGrp.getDspCtgryNo());
			dspCtgryScFrDTO.setMallId(dspPromtScFrDTO.getMallId());
			dspCtgryScFrDTO.setDevice(dspPromtScFrDTO.getDevice());
			dspCtgryScFrDTO.setLang(dspPromtScFrDTO.getLang());
			dspCtgryScFrDTO.setPrcSectCd(dspPromtScFrDTO.getPrcSectCd());
			
			if("MBM".equals(dspPromtScFrDTO.getMallId())){
				model.addAttribute("contt", planService.selectMbmEventPlanContt(dspCtgryScFrDTO));
			}else {
				model.addAttribute("contt", planService.selectSamEventPlanContt(dspCtgryScFrDTO));
			}
		}
		model.addAttribute("dspPromtScFrDTO", dspPromtScFrDTO);
	}
	
	@Override
	public Page<DspPlanFoResult> selectMallEventPlanPage(Model model, DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam)
			throws Exception {
		return planService.selectMallEventPlanList(dspPromtScFrDTO, pageParam);
	
	}
	@Override
	public List<?> selectMallEventPlanCnncList(Model model, DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam)
			throws Exception {
		
		Page<DspPlanFoResult> evtList = planService.selectMallEventPlanList(dspPromtScFrDTO, pageParam);
		PageService.makePageResult(evtList, model);
		
		return evtList.getContent();
		
	}
	
	@Override
	public EventFoDTO selectMallEvent(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		EventFoDTO eventFoDTO = planService.selectMallEvt(eventSearchFoDTO);
		return eventFoDTO;
	}
	
	@Override
	public void selectDxmDiscovererPlanList(Model model, DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam)
			throws Exception {

		Page<DspPlanFoResult> evtList = planService.selectDxmDiscovererPlanList(dspPromtScFrDTO, pageParam);
		PageService.makePageResult(evtList, model);
		model.addAttribute("evt", evtList.getContent());
		model.addAttribute("totalRow", evtList.getTotalElements());
		if (StringUtils.isNotEmpty(dspPromtScFrDTO.getDspUseGrpTpCd())) {
			DspCtgryScFrDTO dspCtgryScFrDTO = new DspCtgryScFrDTO();
			dspCtgryScFrDTO.setDspUseGrpTpCd(dspPromtScFrDTO.getDspUseGrpTpCd());
			DspUseGrp dspUseGrp = dxmDisplayService.selectDspUseGrp(dspCtgryScFrDTO); // THMA04A01
			dspCtgryScFrDTO.setDspCtgryNo(dspUseGrp.getDspCtgryNo());
			dspCtgryScFrDTO.setMallId(dspPromtScFrDTO.getMallId());
			dspCtgryScFrDTO.setDevice(dspPromtScFrDTO.getDevice());
			dspCtgryScFrDTO.setLang(dspPromtScFrDTO.getLang());
			dspCtgryScFrDTO.setPrcSectCd(dspPromtScFrDTO.getPrcSectCd());

			model.addAttribute("contt", planService.selectDxmEventPlanContt(dspCtgryScFrDTO));
		}
		model.addAttribute("dspPromtScFrDTO", dspPromtScFrDTO);
	}
	
	@Override
	public Page<DspPlanFoResult> selectDxmDiscovererPlanList(DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam)
			throws Exception {
		return planService.selectDxmDiscovererPlanList(dspPromtScFrDTO, pageParam);
	}
	
	@Override
	public void selectDxmDiscovererPlanVideoList(Model model, DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam)
			throws Exception {

		DspCtgryScFrDTO dspCtgryScFrDTO = new DspCtgryScFrDTO();
		dspCtgryScFrDTO.setDspUseGrpTpCd(dspPromtScFrDTO.getDspUseGrpTpCd());
		DspUseGrp dspUseGrp = dxmDisplayService.selectDspUseGrp(dspCtgryScFrDTO); // THMA02402
		dspCtgryScFrDTO.setDspCtgryNo(dspUseGrp.getDspCtgryNo());
		dspCtgryScFrDTO.setMallId(dspPromtScFrDTO.getMallId());
		dspCtgryScFrDTO.setDevice(dspPromtScFrDTO.getDevice());
		dspCtgryScFrDTO.setLang(dspPromtScFrDTO.getLang());
		dspCtgryScFrDTO.setPrcSectCd(dspPromtScFrDTO.getPrcSectCd());

		DspCnrFrResult result = planService.selectDxmEventPlanConttByPaging(dspCtgryScFrDTO, pageParam);
		PageService.makePageResult(result.getCnrConttListByPaging(), model);
		model.addAttribute("contt", result);
		model.addAttribute("cnrConttListByPaging", result.getCnrConttListByPaging().getContent());
		model.addAttribute("totalRow", result.getCnrConttListByPaging().getTotalElements());
		model.addAttribute("dspPromtScFrDTO", dspPromtScFrDTO);
	}
	
	@Override
	public DspCnrFrResult selectDxmDiscovererPlanVideoList(DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam)
			throws Exception {
		DspCtgryScFrDTO dspCtgryScFrDTO = new DspCtgryScFrDTO();
		dspCtgryScFrDTO.setDspUseGrpTpCd(dspPromtScFrDTO.getDspUseGrpTpCd());
		DspUseGrp dspUseGrp = dxmDisplayService.selectDspUseGrp(dspCtgryScFrDTO); // THMA02402
		dspCtgryScFrDTO.setDspCtgryNo(dspUseGrp.getDspCtgryNo());
		dspCtgryScFrDTO.setMallId(dspPromtScFrDTO.getMallId());
		dspCtgryScFrDTO.setDevice(dspPromtScFrDTO.getDevice());
		dspCtgryScFrDTO.setLang(dspPromtScFrDTO.getLang());
		dspCtgryScFrDTO.setPrcSectCd(dspPromtScFrDTO.getPrcSectCd());

		return planService.selectDxmEventPlanConttByPaging(dspCtgryScFrDTO, pageParam);
	}
	
	@Override
	public DspPlanFoResult selectMallPlanAdvance(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {

		DspPlanFoResult dspPlanFoResult = planService.selectMallPlanAdvance(dspPromtScFrDTO);

		dspPlanFoResult.setPlanCornerList(planService.selectMallPlanCornerList(dspPromtScFrDTO));

		return dspPlanFoResult;
	}

	@Override
	public Page<DspCnrConttExt> searchDisplayGodPageList(DspPromtScFrDTO dspPromtScFrDTO, PageParam pageParam) throws Exception {
		// TODO Auto-generated method stub
		return planService.searchDisplayGodPageList(dspPromtScFrDTO, pageParam);
	}

	@Override
	public EventFoDTO selectMallEventAdvance(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		EventFoDTO eventFoDTO = planService.selectMallEventAdvance(eventSearchFoDTO);
		return eventFoDTO;
	}

	@Override
	public Page<DspCnrConttExt> searchEvtGodPageList(EventSearchFoDTO eventSearchFoDTO, PageParam pageParam) throws Exception {
		// TODO Auto-generated method stub
		return planService.searchEvtGodPageList(eventSearchFoDTO, pageParam);
	}
}
