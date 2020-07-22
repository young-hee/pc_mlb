package com.plgrim.ncp.web.pc.mlb.goods;

import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plgrim.ncp.base.abstracts.AbstractController;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsInfoResult;
import com.plgrim.ncp.cmp.product.fo.GoodsFOComponent;
import com.plgrim.ncp.framework.data.SystemPK;

import lombok.extern.slf4j.Slf4j;

/** Copyright (c) 2019 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 *
 * Description	: 고도몰 URI 유지용	
 *
 * @Author 	:	alex
 * @Date   	:	2019. 2. 15.
 * @Version	:	
 *
 */
@Slf4j
@Controller
public class GoodsBackupController extends AbstractController {
	@Autowired
	GoodsFOComponent goodsFOComponent;
	
	private final static String[] PRE_EVT_LIST = {
			"26440754"
			, "23901301"
			, "22525566"
			, "25077506"
	};
	private final static String[] CUR_EVT_LIST = {
			"/special/770"
			, "/special/397"
			, "/special/388"
			, "/display/view?dspCtgryNo=MBMA05A08A02&currentCtgryDpthCd=3&ctgrySectCd=GNRL_CTGRY&ctgryNoDpth1=MBMA05&ctgryNoDpth2=MBMA05A08"
	};
	
	private final static String[] PRE_CTG_LIST = {
			"001001"
			, "014001"
			, "014004"
			, "014005"
			, "202005"
			, "202002"
			, "206012"
	};
	private final static String[] CUR_CTG_LIST = {
			"/special/474"
			,"/display/view?dspCtgryNo=MBMA01A05A01&currentCtgryDpthCd=3&ctgrySectCd=GNRL_CTGRY&ctgryNoDpth1=MBMA01&ctgryNoDpth2=MBMA01A05"
			,"/display/view?dspCtgryNo=MBMA01A05A02&currentCtgryDpthCd=3&ctgrySectCd=GNRL_CTGRY&ctgryNoDpth1=MBMA01&ctgryNoDpth2=MBMA01A05"
			,"/display/view?dspCtgryNo=MBMA01A05A04&currentCtgryDpthCd=3&ctgrySectCd=GNRL_CTGRY&ctgryNoDpth1=MBMA01&ctgryNoDpth2=MBMA01A05"
			,"/display/view?dspCtgryNo=MBMA05A05A04&currentCtgryDpthCd=3&ctgrySectCd=GNRL_CTGRY&ctgryNoDpth1=MBMA05&ctgryNoDpth2=MBMA05A05"
			,"/display/view?dspCtgryNo=MBMA05A05A02&currentCtgryDpthCd=3&ctgrySectCd=GNRL_CTGRY&ctgryNoDpth1=MBMA05&ctgryNoDpth2=MBMA05A05"
			,"/display/view?dspCtgryNo=MBMA05A07&currentCtgryDpthCd=2&ctgrySectCd=GNRL_CTGRY&ctgryNoDpth1=MBMA05&ctgryNoDpth2=MBMA05A07"		
	};
	
	@RequestMapping(value = {
			"/shop4/shop/goods/goods_list.php" 
			, "/shop7/shop/goods/goods_list.php"
	})
	public String redirectCategory(@RequestParam(value="category", required=false) String category) {
		String rurl = "/";
		if(category != null) {
			for(int i = 0; i < PRE_CTG_LIST.length; i++) {
				String CTGNO = PRE_CTG_LIST[i];
				if(CTGNO.equals(category)) {
					rurl = CUR_CTG_LIST[i];
					break;
				}
			}
		}		
		return "redirect:" + rurl;
	}
	
	@RequestMapping(value = {
			"/shop4/shop/brand/store.php" 
			, "/shop7/shop/brand/store.php"
	})
	public String redirectHelpdesk() {
		return "redirect:/helpdesk";
	}
	
	@RequestMapping(value = {
			"/shop4/shop/proc/event_view.php" 
			, "/shop7/shop/proc/event_view.php"
	})
	public String redirectEvent(
			@RequestParam(value="sno", required=false) String sno
			, @RequestParam(value="id", required=false) String id
			) {
		//?sno=26440754&id=ing
		String rurl = "/";
		if(sno != null) {
			for(int i = 0; i < PRE_EVT_LIST.length; i++) {
				String SNO = PRE_EVT_LIST[i];
				if(SNO.equals(sno)) {
					rurl = CUR_EVT_LIST[i];
					break;
				}
			}
		}
		return "redirect:" + rurl;
	}
	
	@RequestMapping(value = {
			"/shop4/shop/goods/goods_view.php" 
			, "/shop7/shop/goods/goods_view.php"
	})
	public String redirectGoods(Model model, HttpServletRequest request,
			@RequestParam(value="goodsno", required=false) String goodsno, RedirectAttributes redirectAttributes) {
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		String rtUrl = "";
		try {
			log.info("goodsno = " + goodsno);
			GoodsSearchDTO goodsSearchDTO = new GoodsSearchDTO();
			goodsSearchDTO.setMallId(systemPK.getMall());
			goodsSearchDTO.setLang(systemPK.getLang());
			goodsSearchDTO.setDevice(systemPK.getDevice());
			goodsSearchDTO.setGoodsno(goodsno);
			
			GoodsInfoResult goodsInfoResult = goodsFOComponent.getRedirectGoodsUrl(goodsSearchDTO);
			
			log.info("rtUrl = " + rtUrl);
			if(goodsInfoResult != null) {
				
				Enumeration params = request.getParameterNames();
				
				rtUrl = URLEncoder.encode(goodsInfoResult.getGodEx().getGodDetailUrl(), "UTF-8");
				rtUrl = rtUrl.replaceAll("%2F", "/");
				
				while (params.hasMoreElements()){
				    String name = (String)params.nextElement();
				    if("inflow".equals(name)) {
				    	rtUrl = "/inflow/enginepage/" + request.getParameter(name);
				    	redirectAttributes.addAttribute("godNo", goodsInfoResult.getGodEx().getGodNo());
				    } else if("utm_source".equals(name) || "utm_medium".equals(name)) {
				    	redirectAttributes.addAttribute(name, request.getParameter(name));
				    } 
				}
				
			} else {
				rtUrl = "/goods/-/-/" + goodsno;
			}
			
			log.info("rtUrl = " + rtUrl);
			
		} catch (Exception e) {
			log.debug("error viewGoods {}", e.getMessage());
			return "tiles:goods/goods.unexhibited";
		}
		return "redirect:" + rtUrl;
	}
	
	@RequestMapping(value = {
			"/product/detail/brandno/{dummy1}/no/{goodsno}" 
	})
	public String redirectGoodsOfURI(Model model, HttpServletRequest request,
			@PathVariable(value="goodsno") String goodsno) {
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		String rtUrl = "/";
		try {
			log.info("goodsno = " + goodsno);
			GoodsSearchDTO goodsSearchDTO = new GoodsSearchDTO();
			goodsSearchDTO.setMallTpCd(systemPK.getMall());
			goodsSearchDTO.setLangCd(systemPK.getLang());
			goodsSearchDTO.setDevice(systemPK.getDevice());
			goodsSearchDTO.setGoodsno(goodsno);
			GoodsInfoResult goods = goodsFOComponent.getGoods(goodsSearchDTO);
			for(GodExtend ge : goods.getDesignColorList()) {
				if(ge.getErpGodNo().equals(goods.getGodEx().getErpGodNo())) {
					rtUrl = ge.getGodDetailUrl();
				}
			}
			
			log.info("rtUrl = " + rtUrl);
			rtUrl = URLEncoder.encode(rtUrl, "UTF-8");
			rtUrl = rtUrl.replaceAll("%2F", "/");
			log.info("rtUrl = " + rtUrl);
		} catch (Exception e) {
			log.debug("error viewGoods {}", e.getMessage());
			return "tiles:goods/goods.unexhibited";
		}
		return "redirect:" + rtUrl;
	}
}
