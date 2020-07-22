package com.plgrim.ncp.web.pc.mlb.goods;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plgrim.ncp.base.abstracts.AbstractController;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.enums.GoodsEnum;

import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.result.DxmDspFoCtgryResult;
import com.plgrim.ncp.biz.goods.data.GoodsContentSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsContentResult;
import com.plgrim.ncp.biz.goods.result.GoodsInfoResult;
import com.plgrim.ncp.biz.goods.result.GoodsRelatedGodResult;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MypageWishFoResult;

import com.plgrim.ncp.cmp.display.fo.DxmDisplaySelectComponent;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.cmp.product.fo.GoodsContentFOComponent;
import com.plgrim.ncp.cmp.product.fo.GoodsFOComponent;

import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.utils.Utils;

import lombok.extern.slf4j.Slf4j;

/**
 * Copyright (c) 2018 plgrim, Inc. All right reserved. http://plgrim.com This
 * software is the confidential and proprietary information of plgrim , Inc. You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * plgrim.
 *
 * ------------------------------------------------------------------------
 *
 * Description :
 *
 * @Author : neps
 * @Date : 2018. 6. 10.
 * @Version :
 *
 */
@Slf4j
@Controller
@RequestMapping("/goods")
public class GoodsController extends AbstractController {

	@Autowired
	GoodsFOComponent goodsFOComponent;

	@Autowired
	GoodsContentFOComponent goodsContentFOComponent;

	@Autowired
	MemberActivityFOComponent memberActivityFOComponent;

	@Autowired
	DxmDisplaySelectComponent dxmDisplaySelectComponent;
	
	@Value("${ncp_base.locale.iso.code.name}")
    String isoCodeName;

	/**
	 * 상품 상세 조회
	 * 
	 * @param model
	 * @param goodsSearchDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/{godNo}/view", "/{sexNm}/{itemNm}/{erpGodNo}"}, produces="text/plain;charset=UTF-8")
	public String viewGoods(Model model, GoodsSearchDTO goodsSearchDTO, DspCtgryScFrDTO dspCtgryScFrDTO,
			HttpServletResponse response, HttpServletRequest request) {
		String viewPage = "tiles:goods/goods";

		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		goodsSearchDTO.setMallTpCd(systemPK.getMall());
		goodsSearchDTO.setLangCd(systemPK.getLang());
		goodsSearchDTO.setDevice(systemPK.getDevice());

		try {
			//	상품정보 조회
			GoodsInfoResult goods = goodsFOComponent.getGoods(goodsSearchDTO);
			
			if (String.valueOf(GoodsEnum.SUCCESS_CODE).equals(goods.getRstCd())) {
				model.addAttribute("goods", goods);
				model.addAttribute("isBoPreview", goodsSearchDTO.getIsBoPreview());
			
				String godNo = goods.getGodEx().getGodNo();

				//	상품 컨텐츠 조회
				this.getGoodsContent(model, response, request, systemPK, godNo);			
				
				//	전시 카테고리 정보 설정
				dspCtgryScFrDTO.setLang(systemPK.getLang());
				this.setCategory(model, dspCtgryScFrDTO, godNo);

				//	회원 위시 정보 설정
				this.setMemberWish(model, godNo);
				
				//	상품 메타 정보 설정
				this.setGoodsMeta(model, goodsSearchDTO, goods);
				
				// 연관상품 조회
				this.getGoodsRelated(model, systemPK, godNo);
				
				// IP에 따른 언어 SET
				HttpSession session = request.getSession();
				model.addAttribute("isoCodeName", session.getAttribute(isoCodeName));
				
			} else {
				// Error page
				viewPage =  "tiles:goods/goods.unexhibited";
			}			
		} catch (Exception e) {
			log.debug("error viewGoods {}", e.getMessage());
			// Error page
			viewPage =  "tiles:goods/goods.unexhibited";
		}

		return viewPage;
	}
	
	private void getGoodsRelated(Model model, SystemPK systemPK, String godNo) throws Exception {
		GoodsContentSearchDTO contentSearchDTO = new GoodsContentSearchDTO();
		contentSearchDTO.setGodNo(godNo);
		contentSearchDTO.setMallId(systemPK.getMall());
		contentSearchDTO.setLang(systemPK.getLang());
		contentSearchDTO.setDevice(systemPK.getDevice());
		
		List<GoodsRelatedGodResult> relatedGoods = goodsContentFOComponent.getRelatedGoods(contentSearchDTO);	
		model.addAttribute("relatedGoods", relatedGoods);
	}

	private void getGoodsContent(Model model, HttpServletResponse response,
			HttpServletRequest request, SystemPK systemPK, String godNo) throws Exception {
		GoodsContentSearchDTO contentSearchDTO = new GoodsContentSearchDTO();
		contentSearchDTO.setGodNo(godNo);
		contentSearchDTO.setMallId(systemPK.getMall());
		contentSearchDTO.setLang(systemPK.getLang());
		contentSearchDTO.setDevice(systemPK.getDevice());

		GoodsContentResult goodsContent = goodsContentFOComponent.getGoodsContent(contentSearchDTO, response, request);		
		model.addAttribute("content", goodsContent);
		
	}
	
	private void setCategory(Model model, DspCtgryScFrDTO dspCtgryScFrDTO, String godNo) throws Exception {
		if (StringUtils.isEmpty(dspCtgryScFrDTO.getDspCtgryNo())) {
			dspCtgryScFrDTO.setGodNos(godNo);
			DxmDspFoCtgryResult dxmDspFoCtgryResult = dxmDisplaySelectComponent.selectLnbCategory(dspCtgryScFrDTO);

			int count = 1;
			if (dxmDspFoCtgryResult != null && StringUtils.isNotEmpty(dxmDspFoCtgryResult.getDspCtgryNo())) {
				String[] dspCtgryNos = dxmDspFoCtgryResult.getDspCtgryNo().split(",");
				for (String dspCtgryNo : dspCtgryNos) {
					if (count == 1) {
						dspCtgryScFrDTO.setCtgryNoDpth1(dspCtgryNo);
					} else if (count == 2) {
						dspCtgryScFrDTO.setCtgryNoDpth2(dspCtgryNo);
					} else if (count == 3) {
						dspCtgryScFrDTO.setCtgryNoDpth3(dspCtgryNo);
					}
					dspCtgryScFrDTO.setDspCtgryNo(dspCtgryNo);
					dspCtgryScFrDTO.setCurrentCtgryDpthCd(String.valueOf(count));
					count++;
				}
				count = 1;
				String[] dspCtgryNms = dxmDspFoCtgryResult.getDspCtgryNm().split(",");
				for (String dspCtgryNm : dspCtgryNms) {
					if (count == 1) {
						dspCtgryScFrDTO.setCtgryNoDpthNm1(dspCtgryNm);
					} else if (count == 2) {
						dspCtgryScFrDTO.setCtgryNoDpthNm2(dspCtgryNm);
					} else if (count == 3) {
						dspCtgryScFrDTO.setCtgryNoDpthNm3(dspCtgryNm);
					}
					count++;
				}
			}
		}else{
			
			DspCtgryScFrDTO dspCtgryScFrDTO2= new DspCtgryScFrDTO();
			dspCtgryScFrDTO2.setLang(dspCtgryScFrDTO.getLang());
			if (StringUtils.isNotEmpty(dspCtgryScFrDTO.getCtgryNoDpth1())) {
				dspCtgryScFrDTO2.setDspCtgryNo(dspCtgryScFrDTO.getCtgryNoDpth1());
			    dxmDisplaySelectComponent.selectCtgryList(dspCtgryScFrDTO2);
				dspCtgryScFrDTO.setCtgryNoDpthNm1(dspCtgryScFrDTO2.getFLocation());
			}
			if (StringUtils.isNotEmpty(dspCtgryScFrDTO.getCtgryNoDpth2())) {
				dspCtgryScFrDTO2.setDspCtgryNo(dspCtgryScFrDTO.getCtgryNoDpth2());
			    dxmDisplaySelectComponent.selectCtgryList(dspCtgryScFrDTO2);
				dspCtgryScFrDTO.setCtgryNoDpthNm2(dspCtgryScFrDTO2.getFLocation());
			}
			if (StringUtils.isNotEmpty(dspCtgryScFrDTO.getCtgryNoDpth3())) {
				dspCtgryScFrDTO2.setDspCtgryNo(dspCtgryScFrDTO.getCtgryNoDpth3());
			    dxmDisplaySelectComponent.selectCtgryList(dspCtgryScFrDTO2);
				dspCtgryScFrDTO.setCtgryNoDpthNm3(dspCtgryScFrDTO2.getFLocation());
			}
 
		}
		model.addAttribute("dspCtgryScFrDTO", dspCtgryScFrDTO);
	}	

	private void setMemberWish(Model model, String godNo) throws Exception {
		MypageFoDTO dto = new MypageFoDTO();
		dto.setGodNo(godNo);
		MypageWishFoResult mypageWishFoResult = new MypageWishFoResult();
		if (ContextService.hasRole()) { // 회원
			Mbr mbr = ((SecurityUserDetail) ContextService.getCurrentUserDetail()).getMbr();
			dto.setMbrNo(mbr.getMbrNo());
			mypageWishFoResult = memberActivityFOComponent.selectGodWishList(dto);
			if (!Utils.empty(mypageWishFoResult)) {
				model.addAttribute("wishlstSn", mypageWishFoResult.getWishlstSn());
				model.addAttribute("godTurn", mypageWishFoResult.getGodTurn());
			}
		}

		mypageWishFoResult = memberActivityFOComponent.selectGodWishListCount(dto);
		if (!Utils.empty(mypageWishFoResult)) {
			model.addAttribute("wishListYn", mypageWishFoResult.getWishListYn());
			model.addAttribute("wishListCount", mypageWishFoResult.getWishListCount());
		}
	}

	private void setGoodsMeta(Model model, GoodsSearchDTO goodsSearchDTO, GoodsInfoResult goods) throws Exception {
		String domain = getConfigService().getProperty("ncp.url.pc_MLB.root.secure");
		model.addAttribute("og_title", goods.getGodEx().getGodNm());
		model.addAttribute("og_desc", goods.getGodEx().getGodNm());
		if (!Utils.empty(goods.getGodImg())) {				
			model.addAttribute("og_image", goods.getGodImg().getImgUrl());
		}
		model.addAttribute("og_url", domain + "/goods/" + goods.getGodEx().getTeamCd() + "/"+goods.getGodEx().getPrdlstCd()+ "/"+goods.getGodEx().getErpGodNo());

		model.addAttribute("seo_title", goods.getGodEx().getGodNm() + " | " + goods.getGodEx().getErpGodNo() + " | MLB");
		model.addAttribute("seo_desc", goods.getGodEx().getGodNm() + " | " + goods.getGodEx().getErpGodNo() + " | MLB");
	}



}
