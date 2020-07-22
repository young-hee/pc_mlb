package com.plgrim.ncp.web.pc.mlb.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.plgrim.ncp.base.abstracts.AbstractController;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.biz.vendor.data.ShopSearchDTO;
import com.plgrim.ncp.cmp.product.fo.GoodsSearchFOComponent;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/common")
public class CommonShopController extends AbstractController{

	@Autowired
	GoodsSearchFOComponent goodsSearchFOComponent;
	
    /**
	 * 매장 약도보기 
	 *
	 * @param request [설명]
	 * @param model [설명]
	 * @param dto [설명]
	 * @return String [설명]
	 */
	@RequestMapping(value = "/shop/view.json", method = RequestMethod.POST, produces = { "application/json" }) 
	public void getSearchShop(Model model,@RequestParam(value = "shopId" ,required = false) String shopId, HttpServletRequest request) throws Exception {

		if(StringService.isNotEmpty(shopId)){
			SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
			ShopSearchDTO shopSearchDTO = new ShopSearchDTO();
			shopSearchDTO.setLang(systemPK.getLang());
			shopSearchDTO.setShopId(shopId);
			SysShopExtends sysShopExtends = goodsSearchFOComponent.getGoodsShop(shopSearchDTO);
			model.addAttribute("sysShopExtends", sysShopExtends);
		}else{
			model.addAttribute("sysShopExtends", null);
		}
	}	
	
}
