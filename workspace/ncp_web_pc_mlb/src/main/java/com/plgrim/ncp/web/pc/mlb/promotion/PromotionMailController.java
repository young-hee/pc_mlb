package com.plgrim.ncp.web.pc.mlb.promotion;

import javax.servlet.http.HttpServletRequest;

import com.plgrim.ncp.web.pc.mlb.order.OrderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.cmp.display.fo.PlanComponent;
import com.plgrim.ncp.commons.service.MailHtmlReaderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/email/promotion")
public class PromotionMailController extends OrderController {

	@Autowired 
	MailHtmlReaderService mailHtmlReaderService;
 
	@Autowired 
	PlanComponent planComponent;
	
	@RequestMapping(value = "/pntNotiSms")
    public String pntNotiSms(Model model, HttpServletRequest request,String mbrNo) throws Exception {

		log.debug("{}",  request.getRequestURI());
		
		log.info(">> PromotionMailController.pntNotiSms mbrNo[" + mbrNo + "]");
		
		MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
		mbrWebpntHist.setMbrNo(mbrNo);
		mbrWebpntHist.setMallId("MBM");
		
		model.addAttribute("info", planComponent.selectWebPointInfoNew(mbrWebpntHist));
        
        return "tiles:email/promotion/pntnotisms";
    }
	
 
}
