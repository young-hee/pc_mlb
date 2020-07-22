package com.plgrim.ncp.web.pc.mlb.order;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plgrim.ncp.cmp.orderfulfilment.bo.order.OrderBoSelectComponent;
import com.plgrim.ncp.commons.service.MailHtmlReaderService;
import com.plgrim.ncp.interfaces.email.adapter.EmailAdapter;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/email/order")
public class OrderMailController extends OrderController {

	@Autowired 
	MailHtmlReaderService mailHtmlReaderService;

    @Autowired
    EmailAdapter emailAdapter;
    
    @Autowired
    OrderBoSelectComponent orderBoSelectComponent;
	
    @Autowired
    InterfaceApiCommon interfaceApiCommon;

	
    @RequestMapping(value = "/{ordNo}/complete")
    public String complete(Model model, HttpServletRequest request,@PathVariable String ordNo) throws Exception {

		log.debug("{}",  request.getRequestURI());
		
		model.addAttribute("info",orderBoSelectComponent.selectEmailOrderCompt(ordNo));
        
        return "tiles:email/order/complete";
    }
	
	
	@RequestMapping(value = "/{ordNo}/notiNopayment")
    public String notiNopayment(Model model, HttpServletRequest request,@PathVariable String ordNo) throws Exception {

		log.debug("{}",  request.getRequestURI());
		
		model.addAttribute("info",orderBoSelectComponent.selectEmailOrderCompt(ordNo));
        
        return "tiles:email/order/noti.nopayment";
    }
	
	@RequestMapping(value = "/{ordNo}/virPayment")
    public String virPayment(Model model, HttpServletRequest request,@PathVariable String ordNo) throws Exception {

		log.debug("{}",  request.getRequestURI());
		
		model.addAttribute("info",orderBoSelectComponent.selectEmailOrderCompt(ordNo));
        
        return "tiles:email/order/vir.payment";
    }
	
	@RequestMapping(value = "/{ordNo}/allCancel")
    public String allCancel(Model model, HttpServletRequest request,@PathVariable String ordNo) throws Exception {

		log.debug("{}",  request.getRequestURI());
		
		model.addAttribute("info",orderBoSelectComponent.selectEmailOrderCompt(ordNo));
        
        return "tiles:email/order/all.cancel";
    }
	
	@RequestMapping(value = "/{ordNo}/{dmstcWaybilNo}/dlivyCompt")
    public String dlivyCompt(Model model, HttpServletRequest request, @PathVariable String ordNo,
			 @PathVariable String dmstcWaybilNo) throws Exception {

		log.debug("{}",  request.getRequestURI());
		
		model.addAttribute("info",orderBoSelectComponent.selectEmailOrderCompt(ordNo, dmstcWaybilNo));
        
        return "tiles:email/order/dlivy.compt";
    }
	
	@RequestMapping(value = "/{ordNo}/dlvCompt")
	public String dlvCompt(Model model, HttpServletRequest request, @PathVariable String ordNo) throws Exception {
		
		log.debug("{}",  request.getRequestURI());
		
		model.addAttribute("info",orderBoSelectComponent.selectEmailOrderCompt(ordNo, null));
		
		return "tiles:email/order/dlv.compt";
	}
	
	@RequestMapping(value = "/{ordNo}/shopPrpareCompt")
	public String saleCompt(Model model, HttpServletRequest request, @PathVariable String ordNo) throws Exception {
		
		log.debug("{}",  request.getRequestURI());

		model.addAttribute("info",orderBoSelectComponent.selectEmailOrderCompt(ordNo, null));
		
		return "tiles:email/order/shop.prpare.compt";
	}
}
