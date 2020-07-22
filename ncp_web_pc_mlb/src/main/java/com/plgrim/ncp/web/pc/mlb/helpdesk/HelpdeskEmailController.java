package com.plgrim.ncp.web.pc.mlb.helpdesk;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plgrim.ncp.base.abstracts.AbstractController;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInq;
import com.plgrim.ncp.biz.callcenter.data.SendMailDTO;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskEmailResult;
import com.plgrim.ncp.biz.member.service.MemberInterfaceService;
import com.plgrim.ncp.cmp.callcenter.fo.helpdesk.CallcenterBundleFOComponent;
import com.plgrim.ncp.cmp.callcenter.fo.helpdesk.CallcenterFaqFOComponent;
import com.plgrim.ncp.cmp.callcenter.fo.helpdesk.CallcenterNoticeFOComponent;
import com.plgrim.ncp.cmp.callcenter.fo.helpdesk.HelpdeskCommonFOComponent;
import com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.commons.CommonSelectComponentImpl;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.data.SystemPK;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/helpdesk/email")
public class HelpdeskEmailController extends AbstractController {

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
	
	@Autowired
	HelpdeskCommonFOComponent helpdeskCommonFOComponent;
	
	@Autowired
	MemberInterfaceService memberInterfaceService;

	
	/**
	 *1:1 문의 이메일 템플릿
	 */
	@RequestMapping(value = "/{mtmInqSn}/sendEmailTemplate")
	public String sendEmailTemplate(Model model, HttpServletRequest request,SendMailDTO sendMailDTO,
			@PathVariable String mtmInqSn) throws Exception {			
		SystemPK systemPK = new SystemPK();
		CsoMtmInq csoMtmInq = new CsoMtmInq();
		csoMtmInq.setMtmInqSn(Long.parseLong(mtmInqSn));
		sendMailDTO.setCsoMtmInq(csoMtmInq);
		sendMailDTO.setLang(systemPK.getLang());
		sendMailDTO.setMallId(systemPK.getMall());
		HelpdeskEmailResult result = new HelpdeskEmailResult();
		 result = helpdeskCommonFOComponent.getSendEmailTemplateInfo(sendMailDTO);
		 /* Date ansDate = new Date();
		  SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		  result.setAnsDate(date.format(ansDate));// 답변일시
*/
		 log.info("################################ sendEmailTemplate : {}",result.toString()+" -->--> 상담정보#############################");
		 model.addAttribute("info",result);
		 
		
		 
		 
		log.debug("{}",  request.getRequestURI());
		
		 return "tiles:email/helpdesk/counselEmail.view";
	}
	
	
	/**
	 * 단체 제휴 문의 이메일 템플릿
	 */
	@RequestMapping(value = "/{orgztInqSn}/sendAffOrdEmailTemplate")
	public String sendAffEmailTemplate(Model model, HttpServletRequest request,SendMailDTO sendMailDTO,
			@PathVariable String orgztInqSn) throws Exception {			
		SystemPK systemPK = new SystemPK();
		sendMailDTO.setOrgztInqSn(Long.parseLong(orgztInqSn));
		sendMailDTO.setLang(systemPK.getLang());
		sendMailDTO.setMallId(systemPK.getMall());
		HelpdeskEmailResult result = new HelpdeskEmailResult();
		 result = helpdeskCommonFOComponent.getSendAffOrdEmailTemplateInfo(sendMailDTO);
		 /* Date ansDate = new Date();
		  SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		  result.setAnsDate(date.format(ansDate));// 답변일시
*/
		 log.info("################################ sendEmailTemplate : {}",result.toString()+" -->--> 상담정보#############################");
		 model.addAttribute("info",result);
		 
		 log.debug("{}",  request.getRequestURI());
		
		 
		 if("ORGZT_ORD".equals(result.getOrgztOrdAffInqTpCd())){//단체제휴
			 return "tiles:email/helpdesk/bundleOrdEmail.view";
		 }else{
			 return "tiles:email/helpdesk/affInquiryEmail.view";
		 }
		 
		
		
		 
	}

}
