package com.plgrim.ncp.web.pc.mlb.display;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.plgrim.ncp.base.enums.OlapicEnum;
import com.plgrim.ncp.biz.display.data.OlapicDTO;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsInfoResult;
import com.plgrim.ncp.cmp.product.fo.GoodsFOComponent;
import com.plgrim.ncp.framework.commons.LocaleService;
import com.plgrim.ncp.framework.data.SystemPK;

import groovy.util.logging.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/olapic")
public class OlapicController extends DisplayCommonController {
	@Autowired
	GoodsFOComponent goodsFOComponent;
	
	@RequestMapping(value = "/getGoodsInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public String getGoodsInfo(Model model, @ModelAttribute OlapicDTO dto, HttpServletRequest request) throws Exception {
		List<GoodsInfoResult> goodsList = new ArrayList<>();
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		if(dto.getErpGodNos() != null && dto.getErpGodNos().length > 0) {
			for(String erpGodNo : dto.getErpGodNos()) {
				GoodsSearchDTO goodsSearchDTO = new GoodsSearchDTO();
				goodsSearchDTO.setMallTpCd(systemPK.getMall());
				goodsSearchDTO.setLangCd(systemPK.getLang());
				goodsSearchDTO.setDevice(systemPK.getDevice());
				goodsSearchDTO.setErpGodNo(erpGodNo);
				GoodsInfoResult goods = goodsFOComponent.getGoods(goodsSearchDTO);
				goodsList.add(goods);
			}
		}
		model.addAttribute("goodsList", goodsList);
		return "tiles:frame/olapic/olapic.god";
	}
	
	@RequestMapping(value = "/view", method = { RequestMethod.GET, RequestMethod.POST })
	public String view(Model model, @ModelAttribute OlapicDTO dto, HttpServletRequest request) throws Exception {
		model.addAttribute("seo_title", "STYLE in SNS | MLB");
		model.addAttribute("seo_desc", "STYLE in SNS | MLB");
		return "tiles:display/olapic.view";
	}
	
	@RequestMapping(value = "/viewHomepage", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewHomepage(Model model, @ModelAttribute OlapicDTO dto, HttpServletRequest request) throws Exception {
		return "tiles:display/olapic.homepage";
	}
	
	@RequestMapping(value = "/viewPDP", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewPDP(Model model, @ModelAttribute OlapicDTO dto, HttpServletRequest request) throws Exception {
		return "tiles:display/olapic.pdp";
	}
	
}
