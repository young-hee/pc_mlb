package com.plgrim.ncp.web.pc.mlb.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plgrim.ncp.biz.order.service.OrderSelectService;
import com.plgrim.ncp.framework.commons.ContextService;

import lombok.extern.slf4j.Slf4j;


/**
 * [클래스 설명] 카트 조회
 * <p>
 *
 * <ul>
 * <li>[기능1]
 * <li>[기능2]
 * </ul>.
 *
 * @author syvictor.kim 
 * @since 2015. 3. 21 
 */ 
@Slf4j
@Controller
@RequestMapping("/cart/goods")
public class CartGoodsController extends CartController {


	@Autowired
    OrderSelectService orderSelectService;
	
	@RequestMapping(value = "/list")
	public String listCart(Model model, HttpServletRequest request) throws Exception{
		//SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		
		/** 화면 제목 세팅 시작 **/
		model.addAttribute("titleSetKey", "cart.js.txt.title");
		/** 화면 제목 세팅 종료 **/
		
		/** 화면 location 세팅 시작 **/
        Map<String, String> location = null;
        List<Map<String, String>> locationSet = new ArrayList<Map<String, String>>();
        
        location = new HashMap<String, String>();
        location.put("url", "/");
        location.put("msgKey", "common.location.home");
        locationSet.add(location);

        location = new HashMap<String, String>();
        location.put("url", "/cart/goods/list");
        location.put("msgKey", "cart.js.txt.title");
        locationSet.add(location);
        
      
        model.addAttribute("locationSet", locationSet);
        /** 화면 location 세팅 종료 **/
        
        String cartDlvSectCd = (String) ContextService.getCurrentRequest().getSession().getAttribute("SESSION_KEY_DEFAULT_CART");
        model.addAttribute("cartDlvSectCd",cartDlvSectCd);
        
		return "tiles:cart/goods/cart.list";
	}
	


	
		

	
	
}
