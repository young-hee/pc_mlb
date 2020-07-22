package com.plgrim.ncp.web.pc.mlb.order;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plgrim.ncp.biz.order.data.OrderInterfaceDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OrderIntefaceController{

	@RequestMapping(value = "/order/interface/call.json", produces = { "application/json" })
	public void call(Model model, @RequestBody OrderInterfaceDTO orderInterfaceDTO, HttpServletRequest request){
		log.debug("{}",  request.getRequestURI());
		log.debug("orderInterfaceDTO {}",  orderInterfaceDTO);


		model.addAttribute("result", "ok");
    }
    
}
