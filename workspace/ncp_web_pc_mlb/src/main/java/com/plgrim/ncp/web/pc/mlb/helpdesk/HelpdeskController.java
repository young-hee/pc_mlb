package com.plgrim.ncp.web.pc.mlb.helpdesk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ImmutableMap;
import com.plgrim.ncp.base.abstracts.AbstractController;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoFaq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInq;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.sys.SysNoti;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.biz.helpdesk.data.HelpdeskFaqFoDTO;
import com.plgrim.ncp.biz.helpdesk.data.HelpdeskNoticeFoDTO;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskFaqFoResult;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskNoticeFoResult;
import com.plgrim.ncp.biz.member.data.BundleOrderFoDTO;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MypageMtmFoResult;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.cmp.callcenter.fo.helpdesk.CallcenterBundleFOComponent;
import com.plgrim.ncp.cmp.callcenter.fo.helpdesk.CallcenterFaqFOComponent;
import com.plgrim.ncp.cmp.callcenter.fo.helpdesk.CallcenterNoticeFOComponent;
import com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent;
import com.plgrim.ncp.cmp.display.fo.DxmDisplaySelectComponent;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.commons.CommonSelectComponentImpl;
import com.plgrim.ncp.commons.result.CodeViewResult;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.FileUploadResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.page.PageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/helpdesk")
public class HelpdeskController extends AbstractController {

	@Autowired
	CallcenterNoticeFOComponent callcenterNoticeFOComponent;
	@Autowired
	CallcenterFaqFOComponent callcenterFaqFOComponent;
	@Autowired
	CommonSelectComponent commonSelectComponent;
	@Autowired
	MemberActivityFOComponent memberActivityFOComponent;
	@Autowired
	CommonSelectComponentImpl commonSelectComponentImpl;
	@Autowired
	CallcenterBundleFOComponent callcenterBundleFOComponent;
	@Autowired
	DisplaySelectComponent displaySelectComponent;
	@Autowired
	IdGenService idGenService;

	/**
	 * 고객센터 서브메인
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/","" })
	public String viewHelpdesk(HttpServletRequest request, Model model) throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		HelpdeskFaqFoDTO helpdeskFaqFoDTO = new HelpdeskFaqFoDTO();
		HelpdeskNoticeFoDTO helpdeskNoticeFoDTO = new HelpdeskNoticeFoDTO();
		helpdeskFaqFoDTO.setCsoFaq(new CsoFaq());
		helpdeskNoticeFoDTO.setSysNoti(new SysNoti());
		helpdeskNoticeFoDTO.setMallId(pk.getMall());
		helpdeskNoticeFoDTO.setLang(pk.getLang());

		helpdeskFaqFoDTO.setMallId(pk.getMall());

		try {
			helpdeskFaqFoDTO.setLangCd(pk.getLang());
			helpdeskFaqFoDTO.setMallId(pk.getMall());
			helpdeskNoticeFoDTO.setLang(pk.getLang());
			/* 이용안내 TOP10 */

			model.addAttribute("faqTop10List", callcenterFaqFOComponent.selectFaqTop10List(helpdeskFaqFoDTO));
			/* 공지사항 */
			model.addAttribute("noticeNew5List", callcenterNoticeFOComponent.selectNoticeNew5List(helpdeskNoticeFoDTO));

		} catch (Exception e) {
			log.error("", e);
		}

		return "tiles:helpdesk/helpdesk.view";
	}

	/**
	 * 공지사항 리스트
	 * @param request
	 * @param helpdeskNoticeFoDTO
	 * @param pageNo
	 * @param searchNotice
	 * @param searchNoticeCd
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/notice/list" }, method = { RequestMethod.GET })
	public String listNotice(HttpServletRequest request, HelpdeskNoticeFoDTO helpdeskNoticeFoDTO,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			@RequestParam(value = "searchNotice", required = false) String searchNotice,
			@RequestParam(value = "searchNoticeCd", required = false) String searchNoticeCd, Model model)
			throws Exception {

		if (StringService.isEmpty(pageNo)) {
			pageNo = "1";
		}
		PageParam pageParam = getPageService().buildPageParam(pageNo, 10);

		try {
			SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
			helpdeskNoticeFoDTO.setMallId(pk.getMall());
			helpdeskNoticeFoDTO.setLang(pk.getLang());
			if (StringService.isNotEmpty(helpdeskNoticeFoDTO.getSearchNotice())) {
				helpdeskNoticeFoDTO
						.setSearchNotice(java.net.URLDecoder.decode(helpdeskNoticeFoDTO.getSearchNotice(), "UTF-8"));
				searchNotice = java.net.URLDecoder.decode(searchNotice, "UTF-8");
			}


			Page<HelpdeskNoticeFoResult> lists = callcenterNoticeFOComponent.selectNoticeList(helpdeskNoticeFoDTO,pageParam);
			List<CodeViewResult> notiTitlelist = callcenterNoticeFOComponent.selectNotiTitleList(helpdeskNoticeFoDTO);

			model.addAttribute("noticeTitles", notiTitlelist);
			model.addAttribute("noticeList", lists.getContent());
			model.addAttribute("searchNotice", searchNotice);
			model.addAttribute("searchNoticeCd", searchNoticeCd);
			PageService.makePageResult(lists, model);

		} catch (Exception e) {
			log.error("", e);
		}

		return "tiles:helpdesk/notice.list";
	}

	/**
	 * 공지사항 상세
	 * @param helpdeskNoticeFoDTO
	 * @param searchNotiSn
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/notice/view/{searchNotiSn}", method = { RequestMethod.POST , RequestMethod.GET })
	public String selectNoticeDetail(@ModelAttribute HelpdeskNoticeFoDTO helpdeskNoticeFoDTO,
			@PathVariable String searchNotiSn, Model model, HttpServletRequest request) throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		helpdeskNoticeFoDTO.setMallId(pk.getMall());
		helpdeskNoticeFoDTO.setLang(pk.getLang());

		model.addAttribute("noticeDetail", callcenterNoticeFOComponent.selectNoticeDetail(pk, helpdeskNoticeFoDTO));
		model.addAttribute("searchNotiSn", helpdeskNoticeFoDTO.getSearchNotiSn());
		model.addAttribute("searchNoticeCd", helpdeskNoticeFoDTO.getSearchNoticeCd());
		model.addAttribute("searchNotice", helpdeskNoticeFoDTO.getSearchNotice());

		model.addAttribute("preNoticeDetail", callcenterNoticeFOComponent.selectPreNotiSn(pk, helpdeskNoticeFoDTO));
		model.addAttribute("nextNoticeDetail", callcenterNoticeFOComponent.selectNextNotiSn(pk, helpdeskNoticeFoDTO));

		return "tiles:helpdesk/notice.view";
	}

	@ResponseBody
	@RequestMapping(value = "/notice/popupList.ajax", method = { RequestMethod.GET }, produces = { "application/json" })
	public Map<String, Object> getNoticePopupList(HelpdeskNoticeFoDTO helpdeskNoticeFoDTO,
			HttpServletRequest request) throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		helpdeskNoticeFoDTO.setDevice(pk.getDevice());
		helpdeskNoticeFoDTO.setMallId(pk.getMall());
		helpdeskNoticeFoDTO.setLang(pk.getLang());
		//helpdeskNoticeFoDTO
		//EXPSR_SCRIN_SECT
		//helpdeskNoticeFoDTO.setSearchExpsrScrinSectCd("BSK");

		return ImmutableMap.<String, Object>builder()
				.put("noticeList", callcenterNoticeFOComponent.selectPopupNoticeList(helpdeskNoticeFoDTO).stream()
						.map(o -> o.getSysNoti())
						.collect(Collectors.toList()))
				.build();
	}

	/**
	 * FAQ 리스트
	 * @param helpdeskFaqFoDTO
	 * @param pageNo
	 * @param searchFaq
	 * @param searchCd
	 * @param searchAllCd
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/faq/list", method = { RequestMethod.POST, RequestMethod.GET })
	public String selectFaqList(@ModelAttribute HelpdeskFaqFoDTO helpdeskFaqFoDTO,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			@RequestParam(value = "searchFaq", required = false) String searchFaq,
			@RequestParam(value = "searchCd", required = false) String searchCd,
			@RequestParam(value = "searchAllCd", required = false) String searchAllCd, Model model,
			HttpServletRequest request) throws Exception {


		PageParam pageParam = getPageService().buildPageParam(pageNo, 10);
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		helpdeskFaqFoDTO.setMallId(pk.getMall());
		helpdeskFaqFoDTO.setLangCd(pk.getLang());
		try {
			List<CodeViewResult> faqTitlelist = callcenterFaqFOComponent.selectFaqTitleList(helpdeskFaqFoDTO);
			Page<HelpdeskFaqFoResult> lists = callcenterFaqFOComponent.selectFaqList(helpdeskFaqFoDTO, pageParam);
			model.addAttribute("faqTitles", faqTitlelist);
			model.addAttribute("totCnt", callcenterFaqFOComponent.selectCountFaq(helpdeskFaqFoDTO));
			model.addAttribute("faqList", lists.getContent());
			model.addAttribute("searchCd", searchCd);
			model.addAttribute("searchFaq", searchFaq);
			model.addAttribute("searchAllCd", searchAllCd);
			PageService.makePageResult(lists, model);

		} catch (Exception e) {
			log.error("", e);
		}

		return "tiles:helpdesk/faq.list";
	}

	/**
	 * FAQ 리스트 조회수 증가
	 * @param helpdeskFaqFoDTO
	 * @param faqSn
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateFaqListInquiry.json", produces = { "application/json" })
	public void updateFaqListInquiry(@ModelAttribute HelpdeskFaqFoDTO helpdeskFaqFoDTO,
			@RequestParam(value = "faqSn", required = false) String faqSn, Model model) throws Exception {

		helpdeskFaqFoDTO.setFaqSn(Long.parseLong(faqSn));
		callcenterFaqFOComponent.updateFaqInqireCount(helpdeskFaqFoDTO);
	}

	/**
	 * 1:1문의 등록 화면
	 * @param mypageOrderInfoDTO
	 * @param mypageMtmFoDTO
	 * @param model
	 * @param erpGodNo
	 * @param godNo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/csInquiry/new", method = { RequestMethod.POST, RequestMethod.GET })
	public String newCsInquiry(MypageOrderInfoDTO mypageOrderInfoDTO,MypageMtmFoDTO mypageMtmFoDTO, Model model,@RequestParam(value = "erpGodNo", required = false) String erpGodNo
			,@RequestParam(value = "godNo", required = false) String godNo,HttpServletRequest request)
			throws Exception {
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		mypageMtmFoDTO.setChkDay("60");
		mypageMtmFoDTO.setMallId(pk.getMall());
		mypageMtmFoDTO.setLangCd(pk.getLang());

		if (ContextService.hasRole()) {

			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mypageMtmFoDTO.setMbrNo(userDetail.getMbr().getMbrNo());
			if(userDetail.getMbr().getMbrNm()!=null && userDetail.getMbr().getMbrNm()!= ""){
			mypageMtmFoDTO.setMbrNm(userDetail.getMbr().getMbrNm());
			}
			if(userDetail.getMbr().getMbrId()!=null && userDetail.getMbr().getMbrId() != ""){
			mypageMtmFoDTO.setMbrId(userDetail.getMbr().getMbrId());
			}
			if(mypageMtmFoDTO.getMbrCheck()!=null){
			model.addAttribute("mbrCheck", mypageMtmFoDTO.getMbrCheck());
			}
			model.addAttribute("userDetail", userDetail);

			if(userDetail.getMbr().getMbrEmail()!=null && userDetail.getMbr().getMbrEmail()!=""){
				String[] email = userDetail.getMbr().getMbrEmail().split("@");
				if(email !=null&& email.length >0) {
					if(email.length >1){
						model.addAttribute("mbrEmail1", email[0]);
						model.addAttribute("mbrEmail2", email[1]);
					}else{
						model.addAttribute("mbrEmail1", email[0]);
					}
				}
			}
			if(!StringUtils.isEmpty(erpGodNo)){
				model.addAttribute("erpGodNo",erpGodNo);
			}
			if(!StringUtils.isEmpty(godNo)){
				model.addAttribute("godNo",godNo);
			}


			return "tiles:helpdesk/inquiry.new";
		}else{
			SysStplat nmbPpsnlInfoColctUsefAgr = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.NMBR_PSNL_INFO_COLCT_USEF_AGR.toString());
			model.addAttribute("nmbPpsnlInfoColctUsefAgr", nmbPpsnlInfoColctUsefAgr);
			if(!StringUtils.isEmpty(erpGodNo)){
				model.addAttribute("erpGodNo",erpGodNo);
			}
			if(!StringUtils.isEmpty(godNo)){
				model.addAttribute("godNo",godNo);
			}
			SecurityContext context = SecurityContextHolder.getContext();
			if(context.getAuthentication().getPrincipal().equals("anonymousUser") || !"Y".equals(mypageMtmFoDTO.getOrderYn())){
				return "tiles:helpdesk/inquiryNl.new";
			}else{
				SecurityUserDetail userDetail = (SecurityUserDetail)ContextService.getCurrentUserDetail();
				if(!"".equals(userDetail.getParameterMap().get("cstmrNm"))
						&&!"".equals(userDetail.getParameterMap().get("cstmrMobilNo"))
						&&!"".equals(userDetail.getParameterMap().get("ordNo"))){
					// 비회원 정보
					mypageOrderInfoDTO.setCstmrNm(userDetail.getParameterMap().get("cstmrNm"));
					mypageOrderInfoDTO.setCstmrMobilNo(userDetail.getParameterMap().get("cstmrMobilNo"));
					mypageOrderInfoDTO.setOrdNo(userDetail.getParameterMap().get("ordNo"));
					model.addAttribute("nmbrYn", "Y");
					model.addAttribute("ordNo", mypageOrderInfoDTO.getOrdNo());
					String mobileNo  = mypageOrderInfoDTO.getCstmrMobilNo();
					if("010".equals(mobileNo.substring(0,3))){
						model.addAttribute("mobile1","010");
						model.addAttribute("mobile2",mobileNo.substring(3,7));
						model.addAttribute("mobile3",mobileNo.substring(7,11));
					}else{
						model.addAttribute("mobile1",mobileNo.substring(0,3));
						model.addAttribute("mobile2",mobileNo.substring(3,6));
						model.addAttribute("mobile3",mobileNo.substring(6,10));
					}

				}
					return "tiles:helpdesk/inquiryNl.new";
			}
		}
	}

	/**
	 * 1:1 문의 등록
	 * @param mypageMtmFoDTO
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/csInquiry/add", method = { RequestMethod.POST, RequestMethod.GET })
	public String insertCsInquiry(MypageMtmFoDTO mypageMtmFoDTO
			, @RequestParam(value = "referer", required = false, defaultValue = "/helpdesk/") String referer
			, Model model, HttpServletRequest request)
			throws Exception {
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		mypageMtmFoDTO.setMallId(pk.getMall());
		mypageMtmFoDTO.setDvcCd(pk.getDevice());
		mypageMtmFoDTO.setLangCd(pk.getLang());

		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageMtmFoDTO.setMbr(userDetail.getMbr());
			mypageMtmFoDTO.setMbrNo(userDetail.getMbr().getMbrNo());
			mypageMtmFoDTO.setMbrId(userDetail.getMbr().getMbrId());

		}else{}

		mypageMtmFoDTO.setMbrNm(mypageMtmFoDTO.getCsoMtmInq().getInqCstmrNm());

		CsoMtmInq csoMtmInq = new  CsoMtmInq();
		String erpGodNo = mypageMtmFoDTO.getCsoMtmInq().getErpGodNo();
		if(StringUtils.isEmpty(erpGodNo)){
		List<OrdGod> ordGodList = new ArrayList<>();
			ordGodList.add(mypageMtmFoDTO.getOrdGod());
			mypageMtmFoDTO.setOrdGodList(ordGodList);
		}
		String chkSms = mypageMtmFoDTO.getCsoMtmInq().getCstmrSmsRecptnYn();
		if (StringUtils.isEmpty(chkSms)) {
			mypageMtmFoDTO.getCsoMtmInq().setCstmrSmsRecptnYn("N");
			mypageMtmFoDTO.getCsoMtmInq().setCstmrEmailRecptnYn("Y");
		} else {
			mypageMtmFoDTO.getCsoMtmInq().setCstmrEmailRecptnYn("Y");
		}

		try {
			callcenterFaqFOComponent.insertCsInquiry(pk, mypageMtmFoDTO);
			if (ContextService.hasRole()){
				return "redirect:/mypage/inquiry/detail/" + mypageMtmFoDTO.getCsoMtmInq().getMtmInqSn();
			}

		} catch (Exception e) {
			log.error("", e);
		}
		return "redirect:" + referer;
	}

	/**
	 * 1:1문의 주문상품 리스트
	 * @param mypageMtmFoDTO
	 * @param pageNo
	 * @param chkDay
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/csInquiry/ord/list.ajax", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ModelAndView listCsInquiryOrdGod(MypageMtmFoDTO mypageMtmFoDTO,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			@RequestParam(value = "chkDay", required = false) String chkDay, Model model, HttpServletRequest request)
			throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		PageParam pageParam = getPageService().buildPageParam(pageNo, 5);


		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
	 	mypageMtmFoDTO.setMbr(userDetail.getMbr());

		mypageMtmFoDTO.setMbrNo(userDetail.getMbr().getMbrNo());
		if(userDetail.getMbr().getMbrId()!=null && userDetail.getMbr().getMbrId()!=""){
		mypageMtmFoDTO.setMbrId(userDetail.getMbr().getMbrId());
		}
		mypageMtmFoDTO.setChkDay(chkDay);
		mypageMtmFoDTO.setMallId(pk.getMall());
		mypageMtmFoDTO.setLangCd(pk.getLang());


		Page<MypageMtmFoResult> lists = callcenterFaqFOComponent.selectInquiryOrdGodList(mypageMtmFoDTO,pageParam);

		model.addAttribute("ordGodList",lists.getContent());
		PageService.makePageResult(lists, model);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("tiles:helpdesk/include/inquiry.ordGodList");
		return mav;

	}

	/**단채주문 조회 */
	@RequestMapping(value = "/bundleOrder/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String listBundleOrder( HttpServletRequest request)throws Exception {

		return "tiles:helpdesk/bundleOrder.list";
	}

	/**
	 * 단체주문 등록화면
	 * @param mypageMtmFoDTO
	 * @param bundleOrderFoDTO
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bundleOrder/new", method = { RequestMethod.POST, RequestMethod.GET })
	public String newbundleOrder(MypageMtmFoDTO mypageMtmFoDTO,BundleOrderFoDTO bundleOrderFoDTO, Model model, HttpServletRequest request)
			throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		mypageMtmFoDTO.setChkDay("60");
		mypageMtmFoDTO.setMallId(pk.getMall());
		mypageMtmFoDTO.setLangCd(pk.getLang());

		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageMtmFoDTO.setMbrNo(userDetail.getMbr().getMbrNo());

			if(userDetail.getMbr().getMbrNm()!=null && userDetail.getMbr().getMbrNm()!= ""){
				mypageMtmFoDTO.setMbrNm(userDetail.getMbr().getMbrNm());
			}
			if(userDetail.getMbr().getMbrId()!=null && userDetail.getMbr().getMbrId() != ""){
				mypageMtmFoDTO.setMbrId(userDetail.getMbr().getMbrId());
			}

			if(mypageMtmFoDTO.getMbrCheck()!=null){
				model.addAttribute("mbrCheck", mypageMtmFoDTO.getMbrCheck());
				}
			model.addAttribute("userDetail", userDetail);

			if(userDetail.getMbr().getMbrEmail()!=null && userDetail.getMbr().getMbrEmail()!=""){
				String[] email = userDetail.getMbr().getMbrEmail().split("@");
				if(email !=null&& email.length >0) {
					if(email.length >1){
						model.addAttribute("mbrEmail1", email[0]);
						model.addAttribute("mbrEmail2", email[1]);
					}else{
						model.addAttribute("mbrEmail1", email[0]);
					}
				}
			}

			mypageMtmFoDTO.setChkDay("60");
			mypageMtmFoDTO.setMallId(pk.getMall());
			mypageMtmFoDTO.setLangCd(pk.getLang());

			return "tiles:helpdesk/bundleOrder.new";
		}
		SysStplat nmbPpsnlInfoColctUsefAgr = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.NMBR_PSNL_INFO_COLCT_USEF_AGR.toString());
		model.addAttribute("nmbPpsnlInfoColctUsefAgr", nmbPpsnlInfoColctUsefAgr);

		return "tiles:helpdesk/bundleOrderNl.new";
	}

	/**
	 * 단체/제휴 문의 등록
	 * @param bundleOrderFoDTO
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/bundleOrder/add", method =RequestMethod.POST)
	public String insertBundleOrder(BundleOrderFoDTO bundleOrderFoDTO
			, @RequestParam(value = "referer", required = false, defaultValue = "/helpdesk/") String referer
			, Model model,HttpServletRequest request) throws Exception{


		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		bundleOrderFoDTO.getCsoOrgztOrdAffInq().setMallId(pk.getMall());
		bundleOrderFoDTO.getCsoOrgztOrdAffInq().setDvcCd(pk.getDevice());
		bundleOrderFoDTO.getCsoOrgztOrdAffInq().setLangCd(pk.getLang());


		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			bundleOrderFoDTO.setMbr(userDetail.getMbr());
			if(userDetail.getMbr().getMbrId()!=null && userDetail.getMbr().getMbrId()!=""){
				bundleOrderFoDTO.setMbrId(userDetail.getMbr().getMbrId());
			}
		} else {
		}

		if(StringUtils.isNotEmpty(bundleOrderFoDTO.getCsoOrgztOrdAffInq().getOrgztOrdAffInqTpCd())){
			if(bundleOrderFoDTO.getCsoOrgztOrdAffInq().getOrgztOrdAffInqTpCd().equals("ORGZT_ORD")){
				bundleOrderFoDTO.getCsoOrgztOrdAffInq().setOrgztOrdAffInqDetailTpCd("ORGZT_ORD");
			}else{
				bundleOrderFoDTO.getCsoOrgztOrdAffInq().setOrgztOrdAffInqTpCd("AFF_INQ");
			}
		}

		String chkSms = bundleOrderFoDTO.getCsoOrgztOrdAffInq().getInqerSmsRecptnYn();

		if (StringUtils.isEmpty(chkSms)) {
			bundleOrderFoDTO.getCsoOrgztOrdAffInq().setInqerEmailRecptnYn("Y");
			bundleOrderFoDTO.getCsoOrgztOrdAffInq().setInqerSmsRecptnYn("N");
		} else {
			bundleOrderFoDTO.getCsoOrgztOrdAffInq().setInqerEmailRecptnYn("Y");
			bundleOrderFoDTO.getCsoOrgztOrdAffInq().setInqerSmsRecptnYn("Y");
		}

		bundleOrderFoDTO.getCsoOrgztOrdAffInq().setInqerMobilNationNo("82");


		callcenterBundleFOComponent.insertBundleOrder(pk, bundleOrderFoDTO);

		//return "redirect:/helpdesk/bundleOrder/list";
		return "redirect:" + referer;
	}

	/**
	 * 제휴문의 등록화면
	 * @param mypageMtmFoDTO
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/affiliateInquiry/new", method = { RequestMethod.POST, RequestMethod.GET })
	public String newAffliateInquiry(MypageMtmFoDTO mypageMtmFoDTO, Model model, HttpServletRequest request)
			throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		mypageMtmFoDTO.setChkDay("60");
		mypageMtmFoDTO.setMallId(pk.getMall());
		mypageMtmFoDTO.setLangCd(pk.getLang());

		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageMtmFoDTO.setMbrNo(userDetail.getMbr().getMbrNo());



			if(userDetail.getMbr().getMbrNm()!=null && userDetail.getMbr().getMbrNm()!= ""){
				mypageMtmFoDTO.setMbrNm(userDetail.getMbr().getMbrNm());
			}
			if(userDetail.getMbr().getMbrId()!=null && userDetail.getMbr().getMbrId() != ""){
				mypageMtmFoDTO.setMbrId(userDetail.getMbr().getMbrId());
			}

			if(mypageMtmFoDTO.getMbrCheck()!=null){
				model.addAttribute("mbrCheck", mypageMtmFoDTO.getMbrCheck());
				}
			model.addAttribute("userDetail", userDetail);
			if(userDetail.getMbr().getMbrEmail()!=null && userDetail.getMbr().getMbrEmail()!=""){
				String[] email = userDetail.getMbr().getMbrEmail().split("@");
				if(email !=null&& email.length >0) {
					if(email.length >1){
						model.addAttribute("mbrEmail1", email[0]);
						model.addAttribute("mbrEmail2", email[1]);
					}else{
						model.addAttribute("mbrEmail1", email[0]);
					}
				}
			}

			return "tiles:helpdesk/affiliateInquiry.new";
		}
		//비회원 개인정보 수집 및 이용동의
		SysStplat nmbPpsnlInfoColctUsefAgr = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.NMBR_PSNL_INFO_COLCT_USEF_AGR.toString());
		model.addAttribute("nmbPpsnlInfoColctUsefAgr", nmbPpsnlInfoColctUsefAgr);

		return  "tiles:helpdesk/affiliateInquiryNl.new";
	}

	/**
	 * 대리점개설 등록화면
	 * @param mypageMtmFoDTO
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/openAgencyInquiry/new", method = { RequestMethod.POST, RequestMethod.GET })
	public String newOpenAgencyInquiry(MypageMtmFoDTO mypageMtmFoDTO, Model model, HttpServletRequest request)
			throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		mypageMtmFoDTO.setChkDay("60");
		mypageMtmFoDTO.setMallId(pk.getMall());
		mypageMtmFoDTO.setLangCd(pk.getLang());

		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageMtmFoDTO.setMbrNo(userDetail.getMbr().getMbrNo());



			if(userDetail.getMbr().getMbrNm()!=null && userDetail.getMbr().getMbrNm()!= ""){
				mypageMtmFoDTO.setMbrNm(userDetail.getMbr().getMbrNm());
			}
			if(userDetail.getMbr().getMbrId()!=null && userDetail.getMbr().getMbrId() != ""){
				mypageMtmFoDTO.setMbrId(userDetail.getMbr().getMbrId());
			}

			if(mypageMtmFoDTO.getMbrCheck()!=null){
				model.addAttribute("mbrCheck", mypageMtmFoDTO.getMbrCheck());
				}
			model.addAttribute("userDetail", userDetail);
			if(userDetail.getMbr().getMbrEmail()!=null && userDetail.getMbr().getMbrEmail()!=""){
				String[] email = userDetail.getMbr().getMbrEmail().split("@");
				if(email !=null&& email.length >0) {
					if(email.length >1){
						model.addAttribute("mbrEmail1", email[0]);
						model.addAttribute("mbrEmail2", email[1]);
					}else{
						model.addAttribute("mbrEmail1", email[0]);
					}
				}
			}

			return "tiles:helpdesk/openAgencyInquiry.new";
		}
		//비회원 개인정보 수집 및 이용동의
		SysStplat nmbPpsnlInfoColctUsefAgr = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.NMBR_PSNL_INFO_COLCT_USEF_AGR.toString());
		model.addAttribute("nmbPpsnlInfoColctUsefAgr", nmbPpsnlInfoColctUsefAgr);

		return  "tiles:helpdesk/openAgencyInquiryNl.new";
	}


	/**
	 * Help Desk file Upload
	 *
	 * @param files
	 * @param model
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/fileUploadHelpDesk.json", method = RequestMethod.POST, produces = { "application/json" })
	public void fileUploadHelpDesk(@RequestParam("files") MultipartFile[]  files, Model model, HttpServletRequest request) throws Exception {
		if(files !=null &&  files.length > 0){
			List<MultipartFile> multipartFiles = Arrays.asList(files);

			// excludeExtensions 포함된 데이터는 업로드 할 수 없다.
			String[] excludeExtensions = { "jsp", "php", "exe", "bat"};

			FileUploadResult fileUploadResult = IOService.fileUploadAutoFileName(multipartFiles,  getConfigService().getProperty("ncp.web.content.path")+"/helpdesk/", excludeExtensions);

			model.addAttribute("result", fileUploadResult);
		}
	}



}
