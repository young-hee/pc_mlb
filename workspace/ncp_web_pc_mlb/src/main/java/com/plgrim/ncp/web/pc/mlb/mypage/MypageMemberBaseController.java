package com.plgrim.ncp.web.pc.mlb.mypage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.member.data.MemberChildDTO;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.cmp.display.fo.DxmDisplaySelectComponent;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.cmp.member.fo.MemberAuthFOComponent;
import com.plgrim.ncp.cmp.member.fo.MemberJoinFOComponent;
import com.plgrim.ncp.cmp.product.fo.GoodsPriceFOComponent;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.commons.CommonSelectComponentImpl;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.LocaleService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.page.PageService;
import com.plgrim.ncp.framework.systems.SystemContext;
import com.plgrim.ncp.interfaces.member.data.MemberInformationSDO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mypage/member")
public class MypageMemberBaseController extends MypageController {

	@Autowired
	CommonSelectComponent commonSelectComponent;

	@Autowired
	CommonSelectComponentImpl commonSelectComponentImpl;
	
	@Autowired
	DxmDisplaySelectComponent dxmDisplaySelectComponent;
	
	@Autowired
	MemberActivityFOComponent memberActivityFOComponent;
	
	@Autowired
	GoodsPriceFOComponent goodsPriceFOComponent;
	
	@Autowired
	MemberJoinFOComponent memberJoinFOComponent; 
	
	@Autowired
	MemberAuthFOComponent memberAuthFOComponent;

	@Autowired
	MessageSourceAccessor messageSourceCmpAccessor;
		   
	/**
	 * 배송지관리 LIST조회
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = { "/deliveryLocationList" }, method = { RequestMethod.POST, RequestMethod.GET })
	public String deliveryLocation(HttpServletRequest request, MbrDlvsp mbrDlvsp,
			@RequestParam(value = "pageNo", required = false) String pageNo, Model model, DspCtgryScFrDTO dspCtgryScFrDTO)
			throws Exception {

		/** 화면 제목 세팅 시작 **/
		this.setMypageTitleSetKey(model);
		/** 화면 제목 세팅 종료 **/
		
		/** 화면 location 세팅 시작 **/
		List<Map<String, String>> locationSet = this.makeMypageLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
		
        location.put("url", null);
        location.put("msgKey", "mypage.member.ttl");
        locationSet.add(location);
        
        location = new HashMap<String, String>();
		
        location.put("url", "/mypage/member/delivery.location.list");
        location.put("msgKey", "mypage.member.delivery.ttl1");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        /** 화면 location 세팅 종료 **/

		model.addAttribute("seo_title", "마이페이지 | MLB");
		model.addAttribute("seo_desc", "마이페이지 | MLB");
		
		return "tiles:mypage/member/delivery.location.list";
	}
	
	/**
	 * 배송지관리 LIST조회
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value ="/include/deliveryLocationList.ajax")
	public String deliveryLocationList(HttpServletRequest request, MbrDlvsp mbrDlvsp,
			@RequestParam(value = "pageNo", required = false) String pageNo, Model model, DspCtgryScFrDTO dspCtgryScFrDTO)
			throws Exception {
		
		if (StringService.isEmpty(pageNo)) {
			pageNo = "1";
		}
		
		PageParam pageParam = getPageService().buildPageParam(pageNo, 10);
			
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		
		mbrDlvsp.setMallId(pk.getMall());
		mbrDlvsp.setLang(pk.getLang());
		
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbrDlvsp.setMbrNo(userDetail.getMbr().getMbrNo());
		}
		
		// 배송지관리 LIST조회
		Page<MemberBoResult> deliveryLocationList = memberActivityFOComponent.getDeliveryLocationPageList(pk, mbrDlvsp, pageParam);
		
		model.addAttribute("deliveryLocationList", deliveryLocationList.getContent());
		PageService.makePageResult(deliveryLocationList, model);
		
		return "tiles:mypage/member/include/delivery.list.include";
	}
	
	/**
	 * 배송지 수정,추가 팝업
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/deliveryLocationPop.ajax", method = RequestMethod.POST)
	public ModelAndView getDeliveryLocationPop(@ModelAttribute MbrDlvsp mbrDlvsp, Model model,HttpServletRequest request) throws Exception {
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

			mbrDlvsp.setMbrNo(userDetail.getMbr().getMbrNo());
		}
		
		String type = request.getParameter("type");

		model.addAttribute("type", type);
		if("modify".equals(type)){
			model.addAttribute("deliveryLocationList", memberActivityFOComponent.getDeliveryLocationList(pk, mbrDlvsp,""));
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypage/member/popup/delivery.location.popup");
		
		return mav;
	}
	
	/**
	 * 배송지 삭제
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/deleteDeliveryLocation.ajax", method = RequestMethod.POST)
	public String setUserDeliveryDelete(@ModelAttribute MypageFoDTO mypageFoDTO, HttpServletRequest request , Model model, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		// 배송지 관리에서 기본배송지 설정 및 회원정보 변경여부
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageFoDTO.getMbrDlvsp().setMbrNo(userDetail.getMbr().getMbrNo());
		}
		
		memberActivityFOComponent.deleteUserDelivery(pk, mypageFoDTO);
		
		return "tiles:mypage/member/delivery.location.list";
	}
	
	/**
	 * 기본배송지 설정
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/setUserDeliveryBase.ajax", method = RequestMethod.POST)
	public String setUserDeliveryBase(@ModelAttribute  MypageFoDTO mypageFoDTO , HttpServletRequest request , Model model, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		// 배송지 관리에서 기본배송지 설정 및 회원정보 변경여부
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		
		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
		
		mypageFoDTO.getMbrDlvsp().setMbrNo(userDetail.getMbr().getMbrNo());
		memberActivityFOComponent.updateUserDeliveryBase(pk, mypageFoDTO);
		
		return "tiles:mypage/member/delivery.location.list";
	}
	
	
	/**
	 * 배송지 수정 , 추가
	 *
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/addDeliveryLocation", method = RequestMethod.POST )
	public String addDeliveryLocation(@ModelAttribute MypageFoDTO mypageFoDTO, Model model,HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		addDeliveryLocationProc(mypageFoDTO, request);

		FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
		fm.put("dspCtgryScFrDTO", dspCtgryScFrDTO);
		
		return "redirect:/mypage/member/deliveryLocationList";
	}

	private void addDeliveryLocationProc(MypageFoDTO mypageFoDTO, HttpServletRequest request) throws Exception {
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		
		Locale locale = LocaleService.getCurrentLocale(request);

		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
		
		mypageFoDTO.getMbrDlvsp().setMbrNo(userDetail.getMbr().getMbrNo());
		MemberFoDTO dto = new MemberFoDTO();
		dto.setMbr(userDetail.getMbr());
		dto.setMallId(pk.getMall());
		memberJoinFOComponent.registerDeliveryLocationBy(pk, mypageFoDTO, "", locale);
		if("Y".equals(mypageFoDTO.getMember())){
			userDetail = memberJoinFOComponent.getMemberInfo(dto);
			memberAuthFOComponent.updateAuthentication(userDetail);
		}
	}
	
	/**
	 * 회원정보 수정 전 패스워드 검증 화면
	 * 
	 * @param request
	 * @param dto
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = { "/checkPasswordView" }, method = { RequestMethod.POST, RequestMethod.GET })
	public String checkPasswordView(HttpServletRequest request, MemberFoDTO dto, Model model, @RequestParam(value="targetUrl") String targetUrl
			, DspCtgryScFrDTO dspCtgryScFrDTO
			, @RequestParam(value = "targetPath", required = false) String targetPath) throws Exception {

		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap !=null) {  
			model.addAttribute("dspCtgryScFrDTO", (DspCtgryScFrDTO) flashMap.get("dspCtgryScFrDTO"));
		}
		
		if(!ContextService.hasRole()) {
			return "redirect:/member/login/view";
		}
		else {
			if(request.getSession().getAttribute(SessionEnum.CHECK_PASSWORD_FLAG.toString()) != null) {
				if(dto.getCheckPasswordFlag() != null
						&& dto.getCheckPasswordFlag().equals((String) request.getSession().getAttribute(SessionEnum.CHECK_PASSWORD_FLAG.toString()))) {
					FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
					fm.put("dspCtgryScFrDTO", dspCtgryScFrDTO);
					return "redirect:/mypage/member/" + targetUrl;
				}
			}
		}

		/** 화면 제목 세팅 시작 **/
		this.setMypageTitleSetKey(model);
		/** 화면 제목 세팅 종료 **/
		
		/** 화면 location 세팅 시작 **/
		List<Map<String, String>> locationSet = this.makeMypageLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
		
        location.put("url", null);
        location.put("msgKey", "mypage.member.ttl");
        locationSet.add(location);
        
        location = new HashMap<String, String>();
		
        location.put("url", "/mypage/member/checkPasswordView");
        location.put("msgKey", "mypage.member.check.password.ttl");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        /** 화면 location 세팅 종료 **/
		
        model.addAttribute("targetUrl", targetUrl);
        model.addAttribute("targetPath", targetPath);

		model.addAttribute("seo_title", "마이페이지 | MLB");
		model.addAttribute("seo_desc", "마이페이지 | MLB");
		
		return "tiles:mypage/member/check.password.view";
	}
	
	/**
	 * 회원정보 수정 전 패스워드 검증
	 * 
	 * @param request
	 * @param dto
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = { "/checkPassword.json" }, method = { RequestMethod.POST })
	public void checkPassword(HttpServletRequest request, MemberFoDTO dto, Model model) throws Exception {

		boolean isFlag = true;
		String failMessage = "";
		
		if(ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
            Mbr mbr = userDetail.getMbr();
            
            dto.getMbr().setMbrNo(mbr.getMbrNo());
            
            Mbr tmpMbr = memberJoinFOComponent.selectMbr(mbr);
            Locale locale = LocaleService.getCurrentLocale(request);
            
//            if (tmpMbr.getMbrLoginLastFailrCount() != null && tmpMbr.getMbrLoginLastFailrCount() >= 5) {
//            	// 5회 이상 로그인에 실패했습니다.<br>비밀번호 찾기에서 새로운 비밀번호를 설정해주세요.
//                failMessage = messageSourceCmpAccessor.getMessage("member.login.pw.failre.msg", locale);
//                isFlag = false;
//            }
            
            if(isFlag) {
            	isFlag = memberJoinFOComponent.checkMemberPassword(dto);
            }
            
			if(isFlag) {
				int numIpinLength = 6;
				String randomStr = "";
				Random ran = new Random();
				for (int i = 0; i < numIpinLength; i++) {
					// 0 ~ 9 랜덤 숫자 생성
					randomStr += ran.nextInt(10);
				}
				Calendar today = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String day = sdf.format(today.getTime());
				String random = mbr.getMbrNo() + day + randomStr;
				
				request.getSession().setAttribute(SessionEnum.CHECK_PASSWORD_FLAG.toString(), random);
				model.addAttribute(SessionEnum.CHECK_PASSWORD_FLAG.toString(), random);
				
	            // 로그인 실패횟수 0 업데이트
				tmpMbr.setMbrLoginLastFailrCount(0);
	            memberJoinFOComponent.updateLoginFailCount(tmpMbr);
			}
			else {
				if(tmpMbr.getMbrLoginLastFailrCount() < 5) {
		            // 로그인 실패횟수 +1 업데이트
					tmpMbr.setMbrLoginLastFailrCount(tmpMbr.getMbrLoginLastFailrCount() + 1);
		            memberJoinFOComponent.updateLoginFailCount(tmpMbr);
				}
//	            if (tmpMbr.getMbrLoginLastFailrCount() == 5) {
//	            	// 5회 이상 로그인에 실패했습니다.<br>비밀번호 찾기에서 새로운 비밀번호를 설정해주세요.
//	            	failMessage = messageSourceCmpAccessor.getMessage("member.login.pw.failre.msg", locale);
//	            	isFlag = false;
//	            }
			}
		}
		
		model.addAttribute("isFlag", isFlag);
		model.addAttribute("failMessage", failMessage);

	}
	
	/**
	 * 회원정보 수정 화면
	 * 
	 * @param request
	 * @param dto
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = { "/modifyMemberView" }, method = { RequestMethod.POST, RequestMethod.GET })
	public String modifyMemberView(HttpServletRequest request, MemberFoDTO dto, Model model, DspCtgryScFrDTO dspCtgryScFrDTO, @RequestParam(value = "targetPath", required = false) String targetPath) throws Exception {

		if(!ContextService.hasRole()) {
			return "redirect:/member/login/view";
		}
		else {
			if(request.getSession().getAttribute(SessionEnum.CHECK_PASSWORD_FLAG.toString()) == null
					|| dto.getCheckPasswordFlag() == null
					|| !dto.getCheckPasswordFlag().equals((String) request.getSession().getAttribute(SessionEnum.CHECK_PASSWORD_FLAG.toString()))
					) {
				FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
				fm.put("dspCtgryScFrDTO", dspCtgryScFrDTO);
				String targetPathUrl = "";
				if(StringService.isNotEmpty(targetPath)) {
					targetPathUrl = "&targetPath=" + targetPath;
				}
				return "redirect:/mypage/member/checkPasswordView?targetUrl=modifyMemberView" + targetPathUrl;
			}
		}

		/** 화면 제목 세팅 시작 **/
		this.setMypageTitleSetKey(model);
		/** 화면 제목 세팅 종료 **/
		
		/** 화면 location 세팅 시작 **/
		List<Map<String, String>> locationSet = this.makeMypageLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
		
        location.put("url", null);
        location.put("msgKey", "mypage.member.ttl");
        locationSet.add(location);
        
        location = new HashMap<String, String>();
		
        location.put("url", "/mypage/member/modifyMemberView");
        location.put("msgKey", "mypage.member.modify.ttl");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        /** 화면 location 세팅 종료 **/
		
        SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
        SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
        Mbr mbr = userDetail.getMbr();
        
        dto.setMallId(pk.getMall());
		dto.setMbr(mbr);
		
        mbr = ((SecurityUserDetail)memberJoinFOComponent.getMemberInfo(dto)).getMbr();
        
        model.addAttribute("mbr", mbr);
        
        MbrCrtfc mbrCrtfc = memberJoinFOComponent.selectMemberCertification(mbr.getMbrNo());
        if(mbrCrtfc != null) {
        	model.addAttribute("mbrCrtfcTpCd", mbrCrtfc.getMbrCrtfcTpCd());
        }
        
//        model.addAttribute("checkPasswordFlag", dto.getCheckPasswordFlag());
        model.addAttribute("targetPath", targetPath);
        
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap !=null) {  
			model.addAttribute("dspCtgryScFrDTO", (DspCtgryScFrDTO) flashMap.get("dspCtgryScFrDTO"));
		}
        
		return "tiles:mypage/member/modify.member.view";
	}
	
	/**
	 * ERP 통합회원 조회
	 * 
	 * @param request [설명]
	 * @param response [설명]
	 * @param dto [설명]
	 * @param model [설명]
	 * @throws Exception the exception
	 * @since 2018. 6. 18
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/getMemberInformation.json", method = RequestMethod.POST, produces = { "application/json" })
	public Model getMemberInformation(HttpServletRequest request,HttpServletResponse response, MemberFoDTO dto, Model model ) throws Exception {
        String familyExceptionFlag = "";
        try {
        	SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
            SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
            Mbr mbr = userDetail.getMbr();
            
        	// ERP 통합회원정보 조회
        	MemberInformationSDO memberInformationSDO = memberJoinFOComponent.getMemberInformation(pk, mbr);
        	
        	model.addAttribute("memberInformationSDO", memberInformationSDO);
        	familyExceptionFlag = "N";
        }
        catch(Exception e) {
        	log.warn("", e);
        	familyExceptionFlag = "Y";
        }
        
        model.addAttribute("familyExceptionFlag", familyExceptionFlag);
		return model;
	}
	
	/**
	 * 회원정보 수정
	 * 
	 * @param request
	 * @param dto
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = { "/modifyMember" }, method = { RequestMethod.POST, RequestMethod.GET })
	public String modifyMember(HttpServletRequest request
								,MemberFoDTO dto 
								,Model model 
								,DspCtgryScFrDTO dspCtgryScFrDTO 
								,@RequestParam(value = "targetPath", required = false) String targetPath
								,@RequestParam(value="childrenName", required=false) List<String> childrenName
								,@RequestParam(value="childrenBirthDate", required=false) List<String> childrenBirthDate
								,@RequestParam(value="childrenBirthYear", required=false) List<String> childrenBirthYear
								,@RequestParam(value="childrenBirthMonth", required=false) List<String> childrenBirthMonth
								,@RequestParam(value="childrenBirthDay", required=false) List<String> childrenBirthDay
								) throws Exception {
		try {
			if(ContextService.hasRole()) {
				SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
				
				Locale locale = LocaleService.getCurrentLocale(request);
				
				SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
				Mbr mbr = userDetail.getMbr();
				
				dto.getMbr().setMbrId(mbr.getMbrId());
	            dto.getMbr().setMbrNo(mbr.getMbrNo());
				
	            // 국문용 자동세팅
	    		if(dto.getMbr().getMbrAddrSectCd() != null) {
	    			dto.getMbr().setMbrAddrNationCd("kr");
	    			dto.getMbr().setMbrAddrTpCd("OWNHOM");
	    		}
	    		if(dto.getMbr().getTelAreaNo() != null && !"".equals(dto.getMbr().getTelAreaNo())) {
	    			dto.getMbr().setTelNationNo("82");
	    		}
	    		
	    		//자녀정보
	    		List<MemberChildDTO> cDto = new ArrayList<MemberChildDTO>();		
	    		if(childrenName != null && !childrenName.isEmpty()){
	    			for(int k = 0 ; k < childrenName.size() ; k++){
	    				try{
	    					String cName = childrenName.get(k);
	    					if(cName != null && !"".equals(cName)){
	    						String cBirthY = childrenBirthYear.get(k);
	    						String cBirthM = childrenBirthMonth.get(k);
	    						String cBirthD = childrenBirthDay.get(k);
	    												
	    						if(!StringUtils.isEmpty(cBirthY) && !StringUtils.isEmpty(cBirthM) && !StringUtils.isEmpty(cBirthD)){
	    							MemberChildDTO m = new MemberChildDTO();
	    							m.setChildrenName(cName);
	    							m.setChildrenBirthYear(cBirthY);
	    							m.setChildrenBirthMonth(cBirthM);
	    							m.setChildrenBirthDay(cBirthD);
	    							
	    							cDto.add(m);
	    						}
	    					}
	    				}catch(Exception e){
	    					log.debug("자녀정보 오류");
	    				}				
	    			}
	    			
	    			if(!cDto.isEmpty()){
	    				dto.setChildrenList(cDto);
	    			}
	    		}
	    		
				memberJoinFOComponent.updateMemberTransaction(request, dto, pk, locale, mbr);
				
				if(!dto.getNewPassword().isEmpty()){
					log.debug("===비밀번호 변경");
					 memberJoinFOComponent.updatePassword(pk, dto, false);
				}
				
			}
		}
		catch(Exception e) {
			log.error("", e);
			throw e;
		}
		
//		model.addAttribute("checkPasswordFlag", dto.getCheckPasswordFlag());

		if(StringService.isNotEmpty(targetPath)) {
			return "redirect:" + targetPath;
		}
		else {
			return "redirect:/mypage/view";
		}
	}
	
	/**
	 * 회원탈퇴 화면
	 * 
	 * @param request
	 * @param dto
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = { "/secessionMemberView" }, method = { RequestMethod.POST, RequestMethod.GET })
	public String secessionMemberView(HttpServletRequest request, MemberFoDTO dto, Model model, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		if(!ContextService.hasRole()) {
			return "redirect:/member/login/view";
		}
		else {
			if(request.getSession().getAttribute(SessionEnum.CHECK_PASSWORD_FLAG.toString()) == null
					|| dto.getCheckPasswordFlag() == null
					|| !dto.getCheckPasswordFlag().equals((String) request.getSession().getAttribute(SessionEnum.CHECK_PASSWORD_FLAG.toString()))
					) {
				FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
				fm.put("dspCtgryScFrDTO", dspCtgryScFrDTO);
				return "redirect:/mypage/member/checkPasswordView?targetUrl=secessionMemberView";
			}
		}

		/** 화면 제목 세팅 시작 **/
		this.setMypageTitleSetKey(model);
		/** 화면 제목 세팅 종료 **/
		
		/** 화면 location 세팅 시작 **/
		List<Map<String, String>> locationSet = this.makeMypageLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
		
        location.put("url", null);
        location.put("msgKey", "mypage.member.ttl");
        locationSet.add(location);
        
        location = new HashMap<String, String>();
		
        location.put("url", "/mypage/member/secessionMemberView");
        location.put("msgKey", "mypage.member.secession.ttl");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        /** 화면 location 세팅 종료 **/
		
        SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
        SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
        dto.setMbr(userDetail.getMbr());
		
        //dto.setMallId(pk.getMall());       
        dto.setSecessMallId(pk.getMall());
        model.addAttribute("memberFoResult", memberJoinFOComponent.selectSecessionCheck(pk, dto));
		model.addAttribute("checkPasswordFlag", dto.getCheckPasswordFlag());
		
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap !=null) {  
			model.addAttribute("dspCtgryScFrDTO", (DspCtgryScFrDTO) flashMap.get("dspCtgryScFrDTO"));
		}
		
		return "tiles:mypage/member/secession.member.view";
	}
	
	/**
	 * 회원탈퇴 처리
	 * 
	 * @param request
	 * @param dto
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = { "/secessionMember.json" }, method = { RequestMethod.POST, RequestMethod.GET })
	public void secessionMember(HttpServletRequest request, HttpServletResponse response, MemberFoDTO dto, Model model) throws Exception {
		boolean secessionResult = false;
		try {
			if(ContextService.hasRole()) {
				SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
				
				SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
				Mbr mbr = userDetail.getMbr();
				mbr.setSecsnResnCd(dto.getMbr().getSecsnResnCd());
				mbr.setSecsnResnDetailCont(dto.getMbr().getSecsnResnDetailCont());
				
				// 회원 탈퇴 처리
				memberJoinFOComponent.deleteMember(pk, mbr);
				
				// 회원 탈퇴 메일/SMS 발송
				memberJoinFOComponent.sendDeleteMailSms(pk, mbr);
				
				secessionResult = true;
				
				final String serverName = request.getServerName();
				String cookieName;
				if(serverName.contains("m.") || serverName.contains("m2.")){
					cookieName = "mb-remember-me";
				} else {
					cookieName = "remember-me";
				}

				String contextPath = request.getContextPath();
				Cookie cookie = new Cookie(cookieName, null);
				cookie.setPath(contextPath.length() > 0 ? contextPath : "/");
				response.addCookie(cookie);

				cookie = new Cookie("MBRNO", null);
				cookie.setPath(contextPath.length() > 0 ? contextPath : "/");
				response.addCookie(cookie);
				
				cookie = new Cookie("e_mbr", null);
				cookie.setPath(contextPath.length() > 0 ? contextPath : "/");
				response.addCookie(cookie);

				CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(cookieName);
			    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
			    cookieClearingLogoutHandler.logout(request, response, null);
			    securityContextLogoutHandler.logout(request, response, null);

				// 현재 오류가 발생함. 로그 상으로 보면 이상이 없는 오류라고 하여 catch함.
				try {
					/** 회원 탈퇴 후 세션이 초기화 되어 다시 로케일 정보 설정 */
					RequestContextUtils.getLocaleResolver(request).setLocale(request, response, LocaleService.getCurrentLocale(request));
				}
				catch(Exception e) {
					log.error("", e);
				}
			}
		}
		catch(Exception e) {
			log.error("", e);
			secessionResult = false;
			throw e;
		}
		
		model.addAttribute("secessionResult", secessionResult);
	}
	
}
