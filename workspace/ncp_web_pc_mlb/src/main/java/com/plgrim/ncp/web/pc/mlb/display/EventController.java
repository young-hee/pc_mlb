package com.plgrim.ncp.web.pc.mlb.display;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.plgrim.ncp.base.entities.datasource1.evt.EvtSnsReply;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSnsCnrsHist;
import com.plgrim.ncp.base.enums.EventEnum;
import com.plgrim.ncp.base.enums.MemberBenefitEnum;
import com.plgrim.ncp.base.repository.mbr.MbrSnsCnrsHistRepository;
import com.plgrim.ncp.biz.display.data.DspPromtScFrDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.promotion.data.EventBoDTO;
import com.plgrim.ncp.biz.promotion.data.EventFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventSearchFoDTO;
import com.plgrim.ncp.biz.promotion.repository.EventRepository;
import com.plgrim.ncp.biz.promotion.result.EventBoResult;
import com.plgrim.ncp.cmp.display.fo.PlanComponent;
import com.plgrim.ncp.cmp.member.fo.MemberBenefitFOComponent;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.page.PageService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/event")
@Slf4j
public class EventController extends DisplayCommonController {
	
	@Autowired
	private PlanComponent planComponent;
	@Autowired
    private MbrSnsCnrsHistRepository mbrSnsCnrsHistRepository;
	
	@Autowired
	private MemberBenefitFOComponent memberBenefitFOComponent;
 
	@Autowired
	private EventRepository eventRepository;
	
	@RequestMapping(value = {"/{evtNo}/view", "/{evtNo}/{evtTp}"})
	public String view(Model model, HttpServletRequest request, HttpServletResponse response,
			EventSearchFoDTO eventSearchFoDTO) throws Exception {
 
		defaultSetting(eventSearchFoDTO, request);
		String lang = eventRepository.selectEventLang(eventSearchFoDTO);
		eventSearchFoDTO.setLang(lang);
		EventFoDTO eventFoDTO  = planComponent.selectMallEvent(eventSearchFoDTO) ;
		model.addAttribute("event", eventFoDTO);
		if("SNS_REPLY".equals(eventFoDTO.getEventExt().getEvtTpCd())) {
			model.addAttribute("kakaoApiKey", getConfigService().getProperty("ncp_web_mb_MLB.event.kakao.scriptkey"));
			model.addAttribute("fbAppId", getConfigService().getProperty("ncp_web_mb_MLB.event.fb.appid"));
		}
		String domain = getConfigService().getProperty("ncp.url.pc_MLB.root");
		model.addAttribute("eventEndYn", eventFoDTO.getEventExt().getEventEndYn());
		model.addAttribute("og_title", eventFoDTO.getEventExt().getEvtNm());
		model.addAttribute("og_desc", eventFoDTO.getEventExt().getEvtDscr());
		model.addAttribute("og_image", eventFoDTO.getEventExt().getImgFileUrl());
		model.addAttribute("og_url", domain + "/event/" + eventFoDTO.getEventExt().getEvtNo() + "/view");
		
		model.addAttribute("seo_title", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		model.addAttribute("seo_desc", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		
		String mbrNo = "";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mbrNo = userDetail.getMbr().getMbrNo();
        }
		model.addAttribute("mbrNo", mbrNo);
		
		return "tiles:event/event.view";
	}
 
	@RequestMapping(value = "/snsReplyList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ModelAndView snsReplyList(Model model, HttpServletRequest request, HttpServletResponse response,
			EventSearchFoDTO eventSearchFoDTO) throws Exception {
		defaultSetting(eventSearchFoDTO, request);
		if(eventSearchFoDTO.getEndIndex() == 0){
			eventSearchFoDTO.setEndIndex(30);
		}
		
		PageParam pageParam = getPageService().buildPageParam(Integer.toString(eventSearchFoDTO.getBeginIndex()), eventSearchFoDTO.getEndIndex());
		Page<EvtSnsReply> result = planComponent.selectSnsReplyList(eventSearchFoDTO, pageParam);
		PageService.makePageResult(result, model);
		model.addAttribute("list", result.getContent());
		
		String mbrNo = "";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mbrNo = userDetail.getMbr().getMbrNo();
        }
		model.addAttribute("mbrNo", mbrNo);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("tiles:event/include/snsReplyList");
		return mav;
	}
	@RequestMapping(value = "/checkEventTarget.json", method = RequestMethod.GET, produces = { "application/json" })
	public void checkEventTarget(@RequestParam(value = "evtNo", required = true) String evtNo, Model model, HttpServletRequest request) throws Exception {
		
		String mbrNo = getLoginMemberNo();
//		String reuslt = null;
		Map<String,String> result = new HashMap<String,String>();
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		
        if(StringUtils.isNotEmpty(mbrNo)){
        	result = planComponent.checkEventTarget(evtNo,  mbrNo, pk);
		}else{
			result.put("resultCode","NOT_LOGIN");
		}
        
		model.addAttribute("resultCode",result.get("resultCode"));
		model.addAttribute("targetDevice",result.get("targetDevice")); // 대상 채널
		model.addAttribute("targetMember",result.get("targetMember")); // 대상 회원 유형
	}
	@RequestMapping(value = "/checkEventEnable.json", method = RequestMethod.GET, produces = { "application/json" })
	public void checkEventEnable(@RequestParam(value = "evtNo", required = true) String evtNo, Model model, HttpServletRequest request) throws Exception {

		String mbrNo = getLoginMemberNo();
		Map<String, String> result = new HashMap<String, String>();

		if (StringUtils.isNotEmpty(mbrNo)) {
			EventBoDTO eventBoDTO = new EventBoDTO();
			eventBoDTO.setEvtNo(evtNo);
			eventBoDTO.setMbrNo(mbrNo);
			EventBoResult enable = planComponent.checkEventEnableResult(eventBoDTO);
			result.put("resultCode", EventEnum.YES.toString().equals(enable.getApplcnCheck()) ? "Y" : "N");
			result.put("resultDateCode", EventEnum.YES.toString().equals(enable.getApplcnDateCheck()) ? "Y" : "N");
		} else {
			result.put("resultCode", "NOT_LOGIN");
		}
		model.addAttribute("resultCode",result.get("resultCode"));
		model.addAttribute("resultDateCode",result.get("resultDateCode"));
	}
	private String getLoginMemberNo(){
		String userId = null;
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
        	userId = userDetail.getMbr().getMbrNo();
        }
        return userId;
	}
	
	@RequestMapping(value = "/insertReply.json", method = RequestMethod.POST, produces = { "application/json" })
	public void insertReply(@RequestParam(value = "evtNo", required = true) String evtNo, EvtSnsReply reply,
			Model model, HttpServletRequest request) throws Exception {
		String mbrNo = "";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mbrNo = userDetail.getMbr().getMbrNo();
        }
 
		String reuslt = null;
		if (StringUtils.isNotEmpty(mbrNo)) {
			planComponent.insertReply(reply, mbrNo);
			reuslt = "SUCCESS";
		} else {
			reuslt = "NOT_LOGIN";
		}

		model.addAttribute("result", reuslt);
	}
	
	@RequestMapping(value = "/updateReplyDspYn.json", method = RequestMethod.POST, produces = { "application/json" })
	public void deleteReply(EvtSnsReply reply, Model model, HttpServletRequest request) throws Exception {
	
		String mbrNo = "";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mbrNo = userDetail.getMbr().getMbrNo();
        }
 
		String reuslt = null;
		
		if (StringUtils.isNotEmpty(mbrNo)) {
			planComponent.updateEvtSnsReplyDspYn(reply, mbrNo);
			reuslt = "SUCCESS";
		} else {
			reuslt = "NOT_LOGIN";
		}

		model.addAttribute("result", reuslt);
	}
	
	@RequestMapping(value = "/promotionList", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(Model model, HttpServletRequest request, HttpServletResponse response,
			 DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
	
		defaultSetting(dspPromtScFrDTO, request);
		
		if(StringUtils.isEmpty(dspPromtScFrDTO.getSearchCategory())) {
			dspPromtScFrDTO.setSearchCategory("ALL");
		}
		dspPromtScFrDTO.setDspUseGrpTpCd("MLB_PRM_LIST_TMPLAT");
		PageParam pageParam = getPageService().buildPageParam(dspPromtScFrDTO.getPageNo(),
				dspPromtScFrDTO.getPageSize());
		planComponent.selectMallEventPlanList(model,dspPromtScFrDTO, pageParam);

		model.addAttribute("seo_title", "프로모션 / 이벤트 | MLB");
		model.addAttribute("seo_desc", "프로모션 / 이벤트 | MLB");
		
		return "tiles:event/promotion.list";
	}
	
	@RequestMapping(value = "/promotionList.json", method = { RequestMethod.GET, RequestMethod.POST }, produces = { "application/json" })
	public String searchList(Model model, HttpServletRequest request, HttpServletResponse response,
			 DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
	
		defaultSetting(dspPromtScFrDTO, request);
		
		if(StringUtils.isEmpty(dspPromtScFrDTO.getSearchCategory())) {
			dspPromtScFrDTO.setSearchCategory("ALL");
		}

		PageParam pageParam = getPageService().buildPageParam(dspPromtScFrDTO.getPageNo(),
				dspPromtScFrDTO.getPageSize());
		
		model.addAttribute("evt", planComponent.selectMallEventPlanCnncList(model, dspPromtScFrDTO, pageParam));
 
		return "tiles:event/promotion.list";
	}
	
    @RequestMapping(value = "/snscnrshist.json", method = RequestMethod.GET,  produces = { "application/json" })
    public void  snscnrshist(Model model, HttpServletRequest request,MbrSnsCnrsHist mbrSnsCnrsHist) throws Exception {

    	if (ContextService.hasRole()) {
            
    		try {
        		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
                SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
                String mbrNo = userDetail.getMbr().getMbrNo();

                mbrSnsCnrsHist.setMbrNo(mbrNo);
                mbrSnsCnrsHist.setSnsCnrsDate(new Date());
                mbrSnsCnrsHist.setMallId(pk.getMall());
                mbrSnsCnrsHist.setRegtrId(mbrNo);
                mbrSnsCnrsHist.setUdterId(mbrNo);
                mbrSnsCnrsHistRepository.insertMbrSnsCnrsHist(mbrSnsCnrsHist);
                
                memberBenefitFOComponent.callMemberBenefit(pk, MemberBenefitEnum.BnefSectCd.SNS_CNRS,null);	
			} catch (Exception e) {
				StringWriter error = new StringWriter();
				e.printStackTrace(new PrintWriter(error));
				log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
			}
    		
        }
    }
    
    @RequestMapping(value = {"/kakao/down/view"})
	public String kakaoDownView(Model model, HttpServletRequest request, HttpServletResponse response,
			EventSearchFoDTO eventSearchFoDTO) throws Exception {
 
		defaultSetting(eventSearchFoDTO, request);
		String mbrNo = "";
		
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mbrNo = userDetail.getMbr().getMbrNo();
        }
		model.addAttribute("mbrNo", mbrNo);
		
		return "tiles:event/event.kakao.down";
	}
}