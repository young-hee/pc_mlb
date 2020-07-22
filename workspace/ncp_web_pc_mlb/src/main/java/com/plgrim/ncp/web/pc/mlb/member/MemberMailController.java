package com.plgrim.ncp.web.pc.mlb.member;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.cmp.member.fo.MemberJoinFOComponent;
import com.plgrim.ncp.cmp.orderfulfilment.bo.order.OrderBoSelectComponent;
import com.plgrim.ncp.commons.service.MailHtmlReaderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/email/member")
public class MemberMailController extends MemberBaseController {

	@Autowired 
	MailHtmlReaderService mailHtmlReaderService;

    @Autowired
    OrderBoSelectComponent orderBoSelectComponent;
    
    @Autowired
    MemberJoinFOComponent memberJoinFOComponent;
	
    /**
     * 회원 가입 메일
     * 
     * @param model
     * @param request
     * @param mbrNo
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "/join")
    public String join(Model model, HttpServletRequest request, String mbrNo) throws Exception {

		log.debug("{}",  request.getRequestURI());
		
		Mbr mbr = memberJoinFOComponent.selectMbrStatus(mbrNo);
    	String mbrId = mbr.getMbrId();
    	mbrId = mbrId.substring(0, mbrId.length()-2) + "**";
		model.addAttribute("mbrId", mbrId);
		model.addAttribute("mbrNm", mbr.getMbrNm());
		model.addAttribute("mbrEmail", mbr.getMbrEmail());
        
        return "tiles:email/member/join";
    }
	
	/**
	 * 회원 정보 수정 메일
	 * 
	 * @param model
	 * @param request
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modify")
    public String update(Model model, HttpServletRequest request, String mbrNo) throws Exception {

		log.debug("{}",  request.getRequestURI());
		
		Mbr mbr = memberJoinFOComponent.selectMbrStatus(mbrNo);
		model.addAttribute("mbrNm", mbr.getMbrNm());
		
		String modifyUrl = getConfigService().getProperty("ncp.url.pc_MLB.root.secure")+"/mypage/member/modifyMemberView";
		model.addAttribute("modifyUrl", modifyUrl);
        
		Date udtDt = mbr.getUdtDt();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String modifyDt = sdf.format(udtDt);
		model.addAttribute("modifyDt", modifyDt);
		
        return "tiles:email/member/modify";
    }
	
	/**
	 * 회원 탈퇴 메일
	 * 
	 * @param model
	 * @param request
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/secession")
    public String secession(Model model, HttpServletRequest request, String mbrNo, String mbrId, String mbrNm) throws Exception {

		log.debug("{}",  request.getRequestURI());
		
		if(mbrId != null && !"".equals(mbrId)) {
			mbrId = mbrId.substring(0, mbrId.length()-2) + "**";
			model.addAttribute("mbrId", mbrId);
		}

		model.addAttribute("mbrNm", mbrNm);
		
		Mbr mbr = memberJoinFOComponent.selectMbrStatus(mbrNo);
		
		Date secsnDate = mbr.getSecsnDate() == null ? new Date() : mbr.getSecsnDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String secsnDt = sdf.format(secsnDate);
		model.addAttribute("secsnDt", secsnDt);
		
        return "tiles:email/member/secession";
    }
	
}
