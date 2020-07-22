package com.plgrim.ncp.web.pc.mlb.goods;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.plgrim.ncp.base.entities.datasource1.god.GodReWhsgNtcn;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.biz.goods.data.GoodsOptionDTO;
import com.plgrim.ncp.biz.goods.data.GoodsReviewSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsReviewResult;
import com.plgrim.ncp.biz.vendor.data.ShopSearchDTO;

import com.plgrim.ncp.cmp.product.fo.GoodsContentFOComponent;
import com.plgrim.ncp.cmp.product.fo.GoodsFOComponent;
import com.plgrim.ncp.cmp.product.fo.GoodsInventoryFOComponent;
import com.plgrim.ncp.cmp.product.fo.GoodsSearchFOComponent;

import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageService;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

/** Copyright (c) 2018 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 *
 * Description	:	
 *
 * @Author 	:	neps
 * @Date   	:	2018. 6. 10.
 * @Version	:	
 *
 */
@Slf4j
@Controller
@RequestMapping("/goods/content")
public class GoodsContentController extends GoodsController{

	@Autowired
	GoodsContentFOComponent goodsContentFOComponent;
	
	@Autowired
	GoodsSearchFOComponent goodsSearchFOComponent;

	@Autowired
	GoodsFOComponent goodsFOComponent;

	@Autowired
	GoodsInventoryFOComponent goodsInventoryFOComponent;
	/**
	 * 상품 리뷰 목록 조회
	 * 
	 * @param pageNo
	 * @param goodsReviewSearchDTO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listReview.ajax", method = RequestMethod.GET)
	public String searchReviewList(@RequestParam(value = "pageNo", required = false) String pageNo, GoodsReviewSearchDTO goodsReviewSearchDTO, HttpServletRequest request, Model model) throws Exception{		
		String returnUrl = "tiles:goods/include/popGoodsReview";
		
		goodsReviewSearchDTO.setGPageNo(StringUtils.defaultIfEmpty(pageNo, "1"));
		goodsReviewSearchDTO.setGPageSize(10);
		
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		goodsReviewSearchDTO.setMallTpCd(systemPK.getMall());
		
		//	고객 컨테츠 내용 국문만 지원
		// goodsReviewSearchDTO.setLang(String.valueOf(GoodsEnum.KOR));
		goodsReviewSearchDTO.setLang(systemPK.getLang());
		
		GoodsReviewResult result = goodsContentFOComponent.searchReviewList(goodsReviewSearchDTO);
		PageService.makePageResult(result.getGodGodEvlExList(), model);
		
		model.addAttribute("reviewAverageScore", result.getReviewAverageScore());
		model.addAttribute("reviewRows", result.getGodGodEvlExList().getContent());
		model.addAttribute("reviewAtchFileRows", result.getGodGodEvlAtchFileList());
		model.addAttribute("goodsReviewSearchDTO", goodsReviewSearchDTO);
		
		if(StringService.isNotEmpty(goodsReviewSearchDTO.getSortFlag()) || StringService.isNotEmpty(goodsReviewSearchDTO.getPhotoReviewYn())){
			returnUrl = "tiles:goods/include/popGoodsReviewList";
		}
		
		return returnUrl;
	}
	
	/**
	 * 매장 목록 조회
	 * 
	 * @param shopSearchDTO
	 * @return
	 */
	@RequestMapping(value = "/getGoodsShopList.json", method = RequestMethod.POST, produces = { "application/json" })
	public void getGoodsShopList(@ModelAttribute ShopSearchDTO shopSearchDTO, Model model, HttpServletRequest request) throws Exception {
		try{
			SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
			shopSearchDTO.setLang(systemPK.getLang());
			List<SysShopExtends> sysShopExtendsList = goodsSearchFOComponent.getGoodsShopList(shopSearchDTO);
			model.addAttribute("shopList", sysShopExtendsList);
			model.addAttribute("totalCnt", sysShopExtendsList.size());
		}
		catch (Exception e) {
	        log.error("",e);
        }				
	}

	/**
	 * 상품 할인쿠폰 회원발급
	 * 
	 * @param prmNo
	 * @param request
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/addMemberCoupon.json", method = RequestMethod.POST, produces = { "application/json" })
	public void addMemberCoupon(String prmNo, HttpServletRequest request , Model model ) throws Exception {
 
		String message = goodsFOComponent.addCouponDownMemberIssue(prmNo);
		
		if("SUCCES".equals(message)){
			model.addAttribute("msg", "success");
			model.addAttribute("successMsg", "해당 쿠폰이 발행되었습니다.");
		}else{
			model.addAttribute("msg", "false");
			model.addAttribute("errMsg", "해당 쿠폰은 이미 발급받았습니다. ");
		}
	}

	
	@RequestMapping(value = "/getChangeOption.json", method = RequestMethod.POST, produces = { "application/json" })
	public void getChangeOption(@ModelAttribute GoodsOptionDTO goodsOptionDTO, Model model, HttpServletRequest request) throws Exception {
		try{
			SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
			goodsOptionDTO.setLang(systemPK.getLang());
 			model.addAttribute("data", goodsInventoryFOComponent.getChangeOption(goodsOptionDTO));
		}
		catch (Exception e) {
	        log.error("",e);
        }				
	}
	
	/**
	 * 입고알림 서비스 신청
	 * 
	 * @param prmNo
	 * @param request
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/addGoodsInform.json", method = RequestMethod.POST, produces = { "application/json" })
	public void addGoodsInform(@ModelAttribute GodReWhsgNtcn godReWhsgNtcn, HttpServletRequest request , Model model ) throws Exception {
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		godReWhsgNtcn.setMallId(systemPK.getMall());
		String message = goodsFOComponent.addGoodsInform(godReWhsgNtcn);
		
		if("SUCCES".equals(message)){
			model.addAttribute("msg", "success");
		}else{
			model.addAttribute("msg", "false");
		}
	}
}
