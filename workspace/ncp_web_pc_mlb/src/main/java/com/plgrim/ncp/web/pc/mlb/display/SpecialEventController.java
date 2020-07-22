package com.plgrim.ncp.web.pc.mlb.display;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.plgrim.ncp.base.entities.datasource1.god.GodReWhsgNtcn;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.enums.EventEnum.EventResultCode;
import com.plgrim.ncp.base.repository.mbr.MbrRepository;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.promotion.data.EventBoDTO;
import com.plgrim.ncp.biz.promotion.data.EventExtendsFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventSearchFoDTO;
import com.plgrim.ncp.biz.promotion.result.EventBoResult;
import com.plgrim.ncp.cmp.display.fo.PlanComponent;
import com.plgrim.ncp.cmp.product.fo.GoodsFOComponent;
import com.plgrim.ncp.cmp.promotion.fo.PromotionEventFOComponent;
import com.plgrim.ncp.cmp.promotion.fo.PromotionSingleEventFOComponent;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.data.SystemPK;

import groovy.util.logging.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/special/event")
public class SpecialEventController extends DisplayCommonController {

	@Autowired
	private PlanComponent planComponent;

	@Autowired
	private PromotionSingleEventFOComponent promotionSingleEventFOComponent;

	@Autowired
	private MbrRepository mbrRepository;
	
	@Autowired
	private PromotionEventFOComponent promotionEventFOComponent;
	
	@Autowired
	GoodsFOComponent goodsFOComponent;

	@RequestMapping(value = { "/{evtNo}/RenewalEvent01" })
	public String view(Model model, HttpServletRequest request, HttpServletResponse response,
			EventSearchFoDTO eventSearchFoDTO) throws Exception {

		defaultSetting(eventSearchFoDTO, request);
		EventFoDTO eventFoDTO = planComponent.selectDxmEvent(eventSearchFoDTO);
		model.addAttribute("event", eventFoDTO);

		String domain = getConfigService().getProperty("ncp.url.pc_DX.root");
		model.addAttribute("eventEndYn", eventFoDTO.getEventExt().getEventEndYn());
		model.addAttribute("og_title", eventFoDTO.getEventExt().getEvtNm());
		model.addAttribute("og_desc", eventFoDTO.getEventExt().getEvtDscr());
		model.addAttribute("og_image", eventFoDTO.getEventExt().getImgFileUrl());
		model.addAttribute("og_url", domain + "/special/event/"+ eventFoDTO.getEventExt().getEvtNo() +"RenewalEvent01");
		
		model.addAttribute("seo_title", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		model.addAttribute("seo_desc", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		String mbrNo = "";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mbrNo = userDetail.getMbr().getMbrNo();
			Mbr mbr = new Mbr();
			mbr.setMbrNo(mbrNo);
			mbr = mbrRepository.selectMbr(mbr);
			if ("Y".equals(mbr.getEmailRecptnAgrYn()) && "Y".equals(mbr.getSmsRecptnAgrYn())) {
				model.addAttribute("emailSmsYn", "Y");
			} else {
				model.addAttribute("emailSmsYn", "N");
			}

			eventSearchFoDTO.setMbrNo(mbrNo);
			if (promotionSingleEventFOComponent.selectfcfsDoubleWinningCheck(eventSearchFoDTO) > 1) {
				model.addAttribute("joinEventYn", "N");
			}

		}
		model.addAttribute("mbrNo", mbrNo);
		return "tiles:event/special.fcfs.view";
	}

	@RequestMapping(value = "/joinEvent.json", method = RequestMethod.GET, produces = { "application/json" })
	public void checkEventEnable(@RequestParam(value = "evtNo", required = true) String evtNo, Model model,
			HttpServletRequest request) throws Exception {

		String mbrNo = getLoginMemberNo();
		Map<String, String> result = new HashMap<String, String>();

		if (StringUtils.isEmpty(mbrNo)) {
			result.put("resultCode", "NOT_LOGIN");

		} else {
			Mbr mbr = new Mbr();
			mbr.setMbrNo(mbrNo);
			mbr = mbrRepository.selectMbr(mbr);

			EventSearchFoDTO eventSearchFoDTO = new EventSearchFoDTO();
			eventSearchFoDTO.setEvtNo(evtNo);
			eventSearchFoDTO.setMbrNo(mbrNo);

			if (promotionSingleEventFOComponent.selectEventPeriodNewMemberInquiry(eventSearchFoDTO) < 1) {
				result.put("resultCode", "N");
				result.put("resultMsg", "이벤트 대상자가 아닙니다.");

			} else {
				if ("Y".equals(mbr.getEmailRecptnAgrYn()) && "Y".equals(mbr.getSmsRecptnAgrYn())) {

					if (promotionSingleEventFOComponent.selectfcfsDoubleWinningCheck(eventSearchFoDTO) > 0) {

						result.put("resultCode", "N");
						result.put("resultMsg", "이벤트 기간 동안 1회만 참여 가능 합니다.");

					} else {

						result = promotionSingleEventFOComponent.insertEventfcfs(eventSearchFoDTO);

						if ("Y".equals(result.get("resultCode"))) {
							result = promotionSingleEventFOComponent.addEventfcfs(eventSearchFoDTO);
						}

					}
				} else if ("Y".equals(mbr.getEmailRecptnAgrYn()) && "N".equals(mbr.getSmsRecptnAgrYn())) {

					result.put("resultCode", "N");
					result.put("resultMsg", "SMS 수신동의 후 응모 가능합니다.");

				} else if ("N".equals(mbr.getEmailRecptnAgrYn()) && "Y".equals(mbr.getSmsRecptnAgrYn())) {

					result.put("resultCode", "N");
					result.put("resultMsg", "이메일 수신동의 후 응모 가능합니다.");

				} else {
					result.put("resultCode", "N");
					result.put("resultMsg", "SMS/이메일 수신동의 후 응모 가능합니다.");
				}

			}

		}
		model.addAttribute("result", result);
	}

	private String getLoginMemberNo() {
		String userId = null;
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			userId = userDetail.getMbr().getMbrNo();
		}
		return userId;
	}

	@RequestMapping(value = { "/{evtNo}/RenewalEvent02" })
	public String renewalEvent02(Model model, HttpServletRequest request, HttpServletResponse response,
			EventSearchFoDTO eventSearchFoDTO) throws Exception {

		defaultSetting(eventSearchFoDTO, request);
		EventFoDTO eventFoDTO = planComponent.selectDxmEvent(eventSearchFoDTO);
		model.addAttribute("event", eventFoDTO);

		eventSearchFoDTO.setSortSeq(1);
		EventExtendsFoDTO evt = promotionSingleEventFOComponent.selectEventLwprt(eventSearchFoDTO);

		EventSearchFoDTO foDto = new EventSearchFoDTO();
		
		foDto.setEvtNo(evt.getEvtNo());
		int count1 = promotionSingleEventFOComponent.selectfcfsWinnerCountCheck(foDto);

		if(count1 >=500){
			model.addAttribute("joinEventYn1", "N");	
		}

		eventSearchFoDTO.setSortSeq(2);
		evt = promotionSingleEventFOComponent.selectEventLwprt(eventSearchFoDTO);

		foDto.setEvtNo(evt.getEvtNo());
		int count2 =promotionSingleEventFOComponent.selectfcfsWinnerCountCheck(foDto);

		if(count2 >=500){
			model.addAttribute("joinEventYn2", "N");	
		}
 
		String domain = getConfigService().getProperty("ncp.url.pc_DX.root");
		model.addAttribute("eventEndYn", eventFoDTO.getEventExt().getEventEndYn());
		model.addAttribute("og_title", eventFoDTO.getEventExt().getEvtNm());
		model.addAttribute("og_desc", eventFoDTO.getEventExt().getEvtDscr());
		model.addAttribute("og_image", eventFoDTO.getEventExt().getImgFileUrl());
		model.addAttribute("og_url", domain + "/special/event/"+ eventFoDTO.getEventExt().getEvtNo() + "RenewalEvent02");
		
		model.addAttribute("seo_title", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		model.addAttribute("seo_desc", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		
		String mbrNo = "";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mbrNo = userDetail.getMbr().getMbrNo();

		}
		model.addAttribute("mbrNo", mbrNo);
		return "tiles:event/special.RenewalEvent02";
	}

	@RequestMapping(value = "/joinEventRenewal01.json", method = RequestMethod.GET, produces = { "application/json" })
	public void joinEventRenewal01(@RequestParam(value = "evtNo", required = true) String evtNo, Model model,
			HttpServletRequest request) throws Exception {

		String mbrNo = getLoginMemberNo();
		Map<String, String> result = new HashMap<String, String>();

		if (StringUtils.isEmpty(mbrNo)) {
			result.put("resultCode", "NOT_LOGIN");

		} else {
			Mbr mbr = new Mbr();
			mbr.setMbrNo(mbrNo);
			mbr = mbrRepository.selectMbr(mbr);

			EventSearchFoDTO eventSearchFoDTO = new EventSearchFoDTO();
			eventSearchFoDTO.setEvtNo(evtNo);
			eventSearchFoDTO.setSortSeq(1);
			EventExtendsFoDTO evt = promotionSingleEventFOComponent.selectEventLwprt(eventSearchFoDTO);
			eventSearchFoDTO.setEvtNo(evt.getEvtNo());
			eventSearchFoDTO.setMbrNo(mbrNo);

			if (promotionSingleEventFOComponent.selectEventFirstPurchase(eventSearchFoDTO) < 1) {
				result.put("resultCode", "N");
				result.put("resultMsg", "첫구매 이벤트 대상자가 아닙니다.");

			} else {
 
				if (promotionSingleEventFOComponent.selectfcfsDoubleWinningCheck(eventSearchFoDTO) > 0) {

					result.put("resultCode", "N");
					result.put("resultMsg", "이벤트 기간 동안 1회만 참여 가능 합니다.");

				} else {

					result = promotionSingleEventFOComponent.insertEventRenewal(eventSearchFoDTO);

					if ("Y".equals(result.get("resultCode"))) {
						result = promotionSingleEventFOComponent.addEventRenewal(eventSearchFoDTO);
					}

				}
			}

		}
		model.addAttribute("result", result);
	}
	
	@RequestMapping(value = "/joinEventRenewal02.json", method = RequestMethod.GET, produces = { "application/json" })
	public void joinEventRenewal02(@RequestParam(value = "evtNo", required = true) String evtNo, Model model,
			HttpServletRequest request) throws Exception {

		String mbrNo = getLoginMemberNo();
		Map<String, String> result = new HashMap<String, String>();

		if (StringUtils.isEmpty(mbrNo)) {
			result.put("resultCode", "NOT_LOGIN");

		} else {
			Mbr mbr = new Mbr();
			mbr.setMbrNo(mbrNo);
			mbr = mbrRepository.selectMbr(mbr);

			EventSearchFoDTO eventSearchFoDTO = new EventSearchFoDTO();
			eventSearchFoDTO.setEvtNo(evtNo);
			eventSearchFoDTO.setSortSeq(2);
			EventExtendsFoDTO evt = promotionSingleEventFOComponent.selectEventLwprt(eventSearchFoDTO);
			eventSearchFoDTO.setEvtNo(evt.getEvtNo());
			eventSearchFoDTO.setMbrNo(mbrNo);

			if (promotionSingleEventFOComponent.selectEventRepurchase(eventSearchFoDTO) < 2) {
				result.put("resultCode", "N");
				result.put("resultMsg", "재구매 이벤트 대상자가 아닙니다.");

			} else {
 
				if (promotionSingleEventFOComponent.selectfcfsDoubleWinningCheck(eventSearchFoDTO) > 0) {

					result.put("resultCode", "N");
					result.put("resultMsg", "이벤트 기간 동안 1회만 참여 가능 합니다.");

				} else {

					result = promotionSingleEventFOComponent.insertEventRenewal(eventSearchFoDTO);

					if ("Y".equals(result.get("resultCode"))) {
						result = promotionSingleEventFOComponent.addEventRenewal(eventSearchFoDTO);
					}

				}
			}

		}
		model.addAttribute("result", result);
	}
	
	
		
	/**
	 * 헤리티지 모자 알림 이벤트
	 * 
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param eventSearchFoDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/{evtNo}/Heritage161/view"})
	public String InformStock(Model model, HttpServletRequest request, HttpServletResponse response,
			EventSearchFoDTO eventSearchFoDTO) throws Exception {

		defaultSetting(eventSearchFoDTO, request);
		EventFoDTO eventFoDTO = planComponent.selectDxmEvent(eventSearchFoDTO);
		model.addAttribute("event", eventFoDTO);

		String domain = getConfigService().getProperty("ncp.url.pc_DX.root");
		model.addAttribute("eventEndYn", eventFoDTO.getEventExt().getEventEndYn());
		model.addAttribute("og_title", eventFoDTO.getEventExt().getEvtNm());
		model.addAttribute("og_desc", eventFoDTO.getEventExt().getEvtDscr());
		model.addAttribute("og_image", eventFoDTO.getEventExt().getImgFileUrl());
		model.addAttribute("og_url", domain + "/special/event/"+ eventFoDTO.getEventExt().getEvtNo() +"/InformStock/view");
		
		model.addAttribute("seo_title", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		model.addAttribute("seo_desc", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		
		String mbrNo = "";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mbrNo = userDetail.getMbr().getMbrNo();
			Mbr mbr = new Mbr();
			mbr.setMbrNo(mbrNo);
			mbr = mbrRepository.selectMbr(mbr);
			model.addAttribute("mbrNm", mbr.getMbrNm());

		}
		model.addAttribute("mbrNo", mbrNo);
		
		return "tiles:event/special.InformStock";
	}
	
	/**
	 * 입고알림 서비스 신청
	 * 
	 * @param prmNo
	 * @param request
	 * @param model
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/stockInformRequest.json", method = { RequestMethod.POST }, produces = { "application/json" })
	public void stockInformRequest(Model model, HttpServletRequest request, EventBoDTO eventBoDTO)
					throws Exception {

		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		EventBoResult result = new EventBoResult();
		
		String evtNo = eventBoDTO.getEvtNo();
		String mbrNo = this.getLoginMemberNo();
		String phoneMobile = eventBoDTO.getPhoneMobile();
		
		eventBoDTO.setMbrNo(mbrNo);
		eventBoDTO.setMallId(systemPK.getMall());
		eventBoDTO.setEvtNo(evtNo);
		
		
		// 이벤트 참여 유효성 체크
		result = promotionEventFOComponent.validEventPartcptn(eventBoDTO, systemPK);

		if (result.isCheckResult()) {

			try {
				EventSearchFoDTO eventSearchFoDTO = new EventSearchFoDTO();
				eventSearchFoDTO.setEvtNo(evtNo);
				
				defaultSetting(eventSearchFoDTO, request);
				
				// 입고알림 신청
				result = promotionSingleEventFOComponent.reqStockInformEvent(eventBoDTO);
				
				
				if(EventResultCode.SUCCESS.toString().equals(result.getResultCode())) {
					GodReWhsgNtcn godReWhsgNtcn = new GodReWhsgNtcn();
					
					godReWhsgNtcn.setMobilAreaNo(phoneMobile.substring(0, 3));
					godReWhsgNtcn.setMobilTlofNo(phoneMobile.substring(3, 7));
					godReWhsgNtcn.setMobilTlofWthnNo(phoneMobile.substring(7,11));
					
					godReWhsgNtcn.setMallId(systemPK.getMall());
					godReWhsgNtcn.setNtcnComptYn("Y");
					godReWhsgNtcn.setGodNo("GM0019050713788"); //TODO 변경 GM0019050713788
					godReWhsgNtcn.setItmNo("IT201905070076125"); //TODO 변경 IT201905070076125
					godReWhsgNtcn.setMbrNo(mbrNo);
					godReWhsgNtcn.setNtcnSectCd("MOBIL_NTCN");
					godReWhsgNtcn.setDeleteYn("N");
					godReWhsgNtcn.setStplatIemAgrYn("N");
					
					
					String message = goodsFOComponent.addGoodsInform(godReWhsgNtcn);
					
					if("SUCCES".equals(message)){
						model.addAttribute("msg", "success");
					}else{
						model.addAttribute("msg", "false");
					}
				}
				
			}
			catch (Exception e) {
				StringWriter error = new StringWriter();
				e.printStackTrace(new PrintWriter(error));
				throw e;
			}
		}

		model.addAttribute("result", result);
	}
	
	
	/**
	 * 선판매 이벤트
	 * 
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param eventSearchFoDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/{evtNo}/earlyBird/view"})
	public String earlyBird(Model model, HttpServletRequest request, HttpServletResponse response,
			EventSearchFoDTO eventSearchFoDTO) throws Exception {

		defaultSetting(eventSearchFoDTO, request);
		EventFoDTO eventFoDTO = planComponent.selectDxmEvent(eventSearchFoDTO);
		model.addAttribute("event", eventFoDTO);

		String domain = getConfigService().getProperty("ncp.url.pc_DX.root");
		model.addAttribute("eventEndYn", eventFoDTO.getEventExt().getEventEndYn());
		model.addAttribute("og_title", eventFoDTO.getEventExt().getEvtNm());
		model.addAttribute("og_desc", eventFoDTO.getEventExt().getEvtDscr());
		model.addAttribute("og_image", eventFoDTO.getEventExt().getImgFileUrl());
		model.addAttribute("og_url", domain + "/special/event/"+ eventFoDTO.getEventExt().getEvtNo() +"/InformStock/view");
		
		model.addAttribute("seo_title", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		model.addAttribute("seo_desc", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		
		String mbrNo = "";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mbrNo = userDetail.getMbr().getMbrNo();
			Mbr mbr = new Mbr();
			mbr.setMbrNo(mbrNo);
			mbr = mbrRepository.selectMbr(mbr);
			model.addAttribute("mbrNm", mbr.getMbrNm());

		}
		model.addAttribute("mbrNo", mbrNo);
		
		return "tiles:event/special.earlybird.view";
	}
	
	/**
	 * 입고알림 서비스 신청
	 * 
	 * @param prmNo
	 * @param request
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/earlyBirdRequest.json", method = { RequestMethod.POST }, produces = { "application/json" })
	public void earlyBirdRequest(Model model, HttpServletRequest request, EventBoDTO eventBoDTO)
					throws Exception {

		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		EventBoResult result = new EventBoResult();
		
		String evtNo = eventBoDTO.getEvtNo();
		String mbrNo = this.getLoginMemberNo();
		String phoneMobile = eventBoDTO.getPhoneMobile();
		String mrbNm = eventBoDTO.getMbr().getMbrNm();
		
		eventBoDTO.setMbrNo(mbrNo);
		eventBoDTO.setMallId(systemPK.getMall());
		eventBoDTO.setEvtNo(evtNo);
		eventBoDTO.setPhoneMobile(phoneMobile);
		
		
		// 이벤트 참여 유효성 체크
		result = promotionEventFOComponent.validEventPartcptn(eventBoDTO, systemPK);

		if (result.isCheckResult() || result.getResultCode()=="NOT_LOGIN") { //비회원도 가능하도록 

			try {
				EventSearchFoDTO eventSearchFoDTO = new EventSearchFoDTO();
				eventSearchFoDTO.setEvtNo(evtNo);
				
				defaultSetting(eventSearchFoDTO, request);
				
				// 선판매 알림 신청
				result = promotionSingleEventFOComponent.earlyBirdEvent(eventBoDTO);
				
				model.addAttribute("result", result.getResultCode());
				model.addAttribute("msg", result.getResultMessage());
				
			}
			catch (Exception e) {
				StringWriter error = new StringWriter();
				e.printStackTrace(new PrintWriter(error));
				throw e;
			}
		}

		model.addAttribute("result", result);
	}
	
	
	/**
	 * MLB 다운 SNS 공유 이벤트 
	 * 
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param eventSearchFoDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/{evtNo}/MLB_MEGA" , "/{evtNo}/MLBMEGA"})
	public String snsShare(Model model, HttpServletRequest request, HttpServletResponse response,
			EventSearchFoDTO eventSearchFoDTO) throws Exception {

		defaultSetting(eventSearchFoDTO, request);
		EventFoDTO eventFoDTO = planComponent.selectDxmEvent(eventSearchFoDTO);
		model.addAttribute("event", eventFoDTO);

		String domain = getConfigService().getProperty("ncp.url.pc_DX.root");
		model.addAttribute("eventEndYn", eventFoDTO.getEventExt().getEventEndYn());
		model.addAttribute("og_title", eventFoDTO.getEventExt().getEvtNm());
		model.addAttribute("og_desc", eventFoDTO.getEventExt().getEvtDscr());
		model.addAttribute("og_image", eventFoDTO.getEventExt().getImgFileUrl());
		model.addAttribute("og_url", domain + "/special/event/"+ eventFoDTO.getEventExt().getEvtNo() +"/InformStock/view");
		
		model.addAttribute("seo_title", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		model.addAttribute("seo_desc", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		
		String mbrNo = "";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mbrNo = userDetail.getMbr().getMbrNo();
			Mbr mbr = new Mbr();
			mbr.setMbrNo(mbrNo);
			mbr = mbrRepository.selectMbr(mbr);
			model.addAttribute("mbrNm", mbr.getMbrNm());

		}
		model.addAttribute("mbrNo", mbrNo);
		
		return "tiles:event/special.mlb.snsshare.view";
	}
	
	/**
	 * MLBKIDS 다운 SNS 공유 이벤트 
	 * 
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param eventSearchFoDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/{evtNo}/MLBKIDS_MEGA","/{evtNo}/MLBKIDSMEGA"})
	public String snsShare2(Model model, HttpServletRequest request, HttpServletResponse response,
			EventSearchFoDTO eventSearchFoDTO) throws Exception {

		defaultSetting(eventSearchFoDTO, request);
		EventFoDTO eventFoDTO = planComponent.selectDxmEvent(eventSearchFoDTO);
		model.addAttribute("event", eventFoDTO);

		String domain = getConfigService().getProperty("ncp.url.pc_DX.root");
		model.addAttribute("eventEndYn", eventFoDTO.getEventExt().getEventEndYn());
		model.addAttribute("og_title", eventFoDTO.getEventExt().getEvtNm());
		model.addAttribute("og_desc", eventFoDTO.getEventExt().getEvtDscr());
		model.addAttribute("og_image", eventFoDTO.getEventExt().getImgFileUrl());
		model.addAttribute("og_url", domain + "/special/event/"+ eventFoDTO.getEventExt().getEvtNo() +"/InformStock/view");
		
		model.addAttribute("seo_title", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		model.addAttribute("seo_desc", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		
		String mbrNo = "";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mbrNo = userDetail.getMbr().getMbrNo();
			Mbr mbr = new Mbr();
			mbr.setMbrNo(mbrNo);
			mbr = mbrRepository.selectMbr(mbr);
			model.addAttribute("mbrNm", mbr.getMbrNm());

		}
		model.addAttribute("mbrNo", mbrNo);
		
		return "tiles:event/special.mlbkids.snsshare.view";
	}
	
	/**
	 * MLB 쿠폰 다운로드 이벤트 
	 * 
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param eventSearchFoDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/{evtNo}/2019FWDown"})
	public String DownCpn(Model model, HttpServletRequest request, HttpServletResponse response,
			EventSearchFoDTO eventSearchFoDTO) throws Exception {

		defaultSetting(eventSearchFoDTO, request);
		
		eventSearchFoDTO.setBoAccess("BODSPYN");
		eventSearchFoDTO.setLang("KOR");
		EventFoDTO eventFoDTO = planComponent.selectDxmEvent(eventSearchFoDTO);
		model.addAttribute("event", eventFoDTO);

		String domain = getConfigService().getProperty("ncp.url.pc_MLB.root");
		model.addAttribute("eventEndYn", eventFoDTO.getEventExt().getEventEndYn());
		model.addAttribute("og_title", eventFoDTO.getEventExt().getEvtNm());
		model.addAttribute("og_desc", eventFoDTO.getEventExt().getEvtDscr());
		model.addAttribute("og_image", eventFoDTO.getEventExt().getImgFileUrl());
		model.addAttribute("og_url", domain + "/special/event/"+ eventFoDTO.getEventExt().getEvtNo() +"/DownCpn/view");
		
		model.addAttribute("seo_title", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		model.addAttribute("seo_desc", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		
		String mbrNo = "";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mbrNo = userDetail.getMbr().getMbrNo();
			Mbr mbr = new Mbr();
			mbr.setMbrNo(mbrNo);
			mbr = mbrRepository.selectMbr(mbr);
			model.addAttribute("mbrNm", mbr.getMbrNm());

		}
		model.addAttribute("mbrNo", mbrNo);
		
		return "tiles:event/special.downcpn.view";
	}
	

	/**
	 * 쿠폰 지급 처리
	 * 
	 * @param model
	 * @param request
	 * @param eventBoDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/downCoupon.json", method = { RequestMethod.POST }, produces = { "application/json" })
	public void downCoupon(Model model, HttpServletRequest request, EventBoDTO eventBoDTO)
					throws Exception {

		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		EventBoResult result = new EventBoResult();
		
		String evtNo = eventBoDTO.getEvtNo();
		String mbrNo = this.getLoginMemberNo();
		String phoneMobile = eventBoDTO.getPhoneMobile();
		String mrbNm = eventBoDTO.getMbr().getMbrNm();
		
		eventBoDTO.setMbrNo(mbrNo);
		eventBoDTO.setMallId(systemPK.getMall());
		eventBoDTO.setEvtNo(evtNo);
		eventBoDTO.setPhoneMobile(phoneMobile);
		
		
		// 이벤트 참여 유효성 체크
		result = promotionEventFOComponent.validEventPartcptn(eventBoDTO, systemPK);

		if (result.isCheckResult()) { 

			try {
				EventSearchFoDTO eventSearchFoDTO = new EventSearchFoDTO();
				eventSearchFoDTO.setEvtNo(evtNo);
				
				defaultSetting(eventSearchFoDTO, request);
				
				// 쿠폰 다운
				result = promotionSingleEventFOComponent.CouponIssueByCpnPrmNo(eventBoDTO);
				
				model.addAttribute("result", result.getResultCode());
				model.addAttribute("msg", result.getResultMessage());
				
			}
			catch (Exception e) {
				StringWriter error = new StringWriter();
				e.printStackTrace(new PrintWriter(error));
				throw e;
			}
		}

		model.addAttribute("result", result);
	}
	
	/**
	 * MLB 블랙프라이데이 이벤트 (선입장 페이지)
	 * 
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param eventSearchFoDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/{evtNo}/blackFriday"})
	public String blackFriday(Model model, HttpServletRequest request, HttpServletResponse response,
			EventSearchFoDTO eventSearchFoDTO) throws Exception {

		defaultSetting(eventSearchFoDTO, request);
		
		eventSearchFoDTO.setLang("KOR");
		EventFoDTO eventFoDTO = planComponent.selectDxmEvent(eventSearchFoDTO);
		model.addAttribute("event", eventFoDTO);

		String domain = getConfigService().getProperty("ncp.url.pc_MLB.root");
		model.addAttribute("eventEndYn", eventFoDTO.getEventExt().getEventEndYn());
		model.addAttribute("og_title", eventFoDTO.getEventExt().getEvtNm());
		model.addAttribute("og_desc", eventFoDTO.getEventExt().getEvtDscr());
		model.addAttribute("og_image", eventFoDTO.getEventExt().getImgFileUrl());
		model.addAttribute("og_url", domain + "/special/event/"+ eventFoDTO.getEventExt().getEvtNo() +"/blackFriday");
		
		model.addAttribute("seo_title", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		model.addAttribute("seo_desc", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		
		String mbrNo = "";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mbrNo = userDetail.getMbr().getMbrNo();
			Mbr mbr = new Mbr();
			mbr.setMbrNo(mbrNo);
			mbr = mbrRepository.selectMbr(mbr);
			model.addAttribute("mbrNm", mbr.getMbrNm());

		}
		model.addAttribute("mbrNo", mbrNo);
		
		return "tiles:event/special.blackfriday.view";
	}
	
	/**
	 * MLB 맨투맨 시즌오프 이벤트 
	 * 
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param eventSearchFoDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/{evtNo}/onlinecpn"})
	public String onlinecpn(Model model, HttpServletRequest request, HttpServletResponse response,
			EventSearchFoDTO eventSearchFoDTO) throws Exception {

		defaultSetting(eventSearchFoDTO, request);
		
		eventSearchFoDTO.setBoAccess("BODSPYN");
		eventSearchFoDTO.setLang("KOR");
		EventFoDTO eventFoDTO  = planComponent.selectMallEvent(eventSearchFoDTO) ;
		model.addAttribute("event", eventFoDTO);

		String domain = getConfigService().getProperty("ncp.url.pc_MLB.root");
		model.addAttribute("eventEndYn", eventFoDTO.getEventExt().getEventEndYn());
		model.addAttribute("og_title", eventFoDTO.getEventExt().getEvtNm());
		model.addAttribute("og_desc", eventFoDTO.getEventExt().getEvtDscr());
		model.addAttribute("og_image", eventFoDTO.getEventExt().getImgFileUrl());
		model.addAttribute("og_url", domain + "/special/event/"+ eventFoDTO.getEventExt().getEvtNo() +"/onlineEvt");
		
		model.addAttribute("seo_title", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		model.addAttribute("seo_desc", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		
		String mbrNo = "";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mbrNo = userDetail.getMbr().getMbrNo();
			Mbr mbr = new Mbr();
			mbr.setMbrNo(mbrNo);
			mbr = mbrRepository.selectMbr(mbr);
			model.addAttribute("mbrNm", mbr.getMbrNm());

		}
		model.addAttribute("mbrNo", mbrNo);
		
		return "tiles:event/special.onlinecpn.view";
	}
	
	/**
	 * MLB 맨투맨 시즌오프 이벤트 - 플러스친구 
	 * 
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param eventSearchFoDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/{evtNo}/onlinepluscpn"})
	public String onlinepluscpn(Model model, HttpServletRequest request, HttpServletResponse response,
			EventSearchFoDTO eventSearchFoDTO) throws Exception {

		defaultSetting(eventSearchFoDTO, request);
		
		eventSearchFoDTO.setBoAccess("BODSPYN");
		eventSearchFoDTO.setLang("KOR");
		EventFoDTO eventFoDTO  = planComponent.selectMallEvent(eventSearchFoDTO) ;
		model.addAttribute("event", eventFoDTO);

		String domain = getConfigService().getProperty("ncp.url.pc_MLB.root");
		model.addAttribute("eventEndYn", eventFoDTO.getEventExt().getEventEndYn());
		model.addAttribute("og_title", eventFoDTO.getEventExt().getEvtNm());
		model.addAttribute("og_desc", eventFoDTO.getEventExt().getEvtDscr());
		model.addAttribute("og_image", eventFoDTO.getEventExt().getImgFileUrl());
		model.addAttribute("og_url", domain + "/special/event/"+ eventFoDTO.getEventExt().getEvtNo() +"/onlineEvt");
		
		model.addAttribute("seo_title", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		model.addAttribute("seo_desc", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		
		String mbrNo = "";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mbrNo = userDetail.getMbr().getMbrNo();
			Mbr mbr = new Mbr();
			mbr.setMbrNo(mbrNo);
			mbr = mbrRepository.selectMbr(mbr);
			model.addAttribute("mbrNm", mbr.getMbrNm());

		}
		model.addAttribute("mbrNo", mbrNo);
		
		return "tiles:event/special.onlinepluscpn.view";
	}
	
	/**
	 * MLB 이벤트
	 * 
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param eventSearchFoDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/{evtNo}/onlinesecretcpn"})
	public String onlinesecretcpn(Model model, HttpServletRequest request, HttpServletResponse response,
			EventSearchFoDTO eventSearchFoDTO) throws Exception {

		defaultSetting(eventSearchFoDTO, request);
		
		eventSearchFoDTO.setBoAccess("BODSPYN");
		eventSearchFoDTO.setLang("KOR");
		EventFoDTO eventFoDTO  = planComponent.selectMallEvent(eventSearchFoDTO) ;
		model.addAttribute("event", eventFoDTO);

		String domain = getConfigService().getProperty("ncp.url.pc_MLB.root");
		model.addAttribute("eventEndYn", eventFoDTO.getEventExt().getEventEndYn());
		model.addAttribute("og_title", eventFoDTO.getEventExt().getEvtNm());
		model.addAttribute("og_desc", eventFoDTO.getEventExt().getEvtDscr());
		model.addAttribute("og_image", eventFoDTO.getEventExt().getImgFileUrl());
		model.addAttribute("og_url", domain + "/special/event/"+ eventFoDTO.getEventExt().getEvtNo() +"/onlineEvt");
		
		model.addAttribute("seo_title", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		model.addAttribute("seo_desc", eventFoDTO.getEventExt().getEvtNm() + " | MLB");
		
		String mbrNo = "";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mbrNo = userDetail.getMbr().getMbrNo();
			Mbr mbr = new Mbr();
			mbr.setMbrNo(mbrNo);
			mbr = mbrRepository.selectMbr(mbr);
			model.addAttribute("mbrNm", mbr.getMbrNm());

		}
		model.addAttribute("mbrNo", mbrNo);
		
		return "tiles:event/special.onlinesecretcpn.view";
	}
	
}
