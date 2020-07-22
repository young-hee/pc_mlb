package com.plgrim.ncp.web.pc.mlb.display;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.data.DspPromtScFrDTO;
import com.plgrim.ncp.biz.display.result.DspPlanFoResult;
import com.plgrim.ncp.biz.goods.data.GoodsContentSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsContentResult;
import com.plgrim.ncp.biz.goods.result.GoodsInfoResult;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MypageWishFoResult;
import com.plgrim.ncp.cmp.display.fo.MbmDisplaySelectComponent;
import com.plgrim.ncp.cmp.display.fo.PlanComponent;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.cmp.product.fo.GoodsContentFOComponent;
import com.plgrim.ncp.cmp.product.fo.GoodsFOComponent;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.utils.Utils;

import groovy.util.logging.Slf4j;

@Slf4j
@Controller
public class PlanController extends DisplayCommonController {

	@Autowired
	PlanComponent planComponent;
	
	@Autowired
	GoodsFOComponent goodsFOComponent;
	
	@Autowired
	GoodsContentFOComponent goodsContentFOComponent;
	
	@Autowired
	MbmDisplaySelectComponent mbmDisplaySelectComponent;
	
	@Autowired
	MemberActivityFOComponent memberActivityFOComponent;
	
	@Value("${ncp_base.locale.iso.code.name}")
    String isoCodeName;
	
	@RequestMapping(value = {"/special/{promtSn}/view", "/special/{promtSn}"})
	public String specialView(Model model, HttpServletRequest request, HttpServletResponse response,
			DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		
		defaultSetting(dspPromtScFrDTO, request);
		dspPromtScFrDTO.setPromtTpCd("GNRL_PROMT");
		DspPlanFoResult dspPlanFoResult = 	planComponent.selectMallPlan(dspPromtScFrDTO);
		model.addAttribute("plan",dspPlanFoResult);
		String domain = getConfigService().getProperty("ncp.url.pc_MLB.root");
		model.addAttribute("og_title", dspPlanFoResult.getDspPromtExtend().getPromtNm());
		model.addAttribute("og_image", dspPlanFoResult.getDspPromtExtend().getPcImgFileUrl());
		model.addAttribute("og_desc", dspPlanFoResult.getDspPromtExtend().getPromtDscr());
		model.addAttribute("og_url", domain + "/special/" + dspPlanFoResult.getDspPromtExtend().getPromtSn() + "/view");
		
		model.addAttribute("seo_title", dspPlanFoResult.getDspPromtExtend().getPromtNm() + " | MLB");
		model.addAttribute("seo_desc", dspPlanFoResult.getDspPromtExtend().getPromtNm() + " | MLB");
		return "tiles:plan/plan.view";
	}
 
	@RequestMapping(value = {"/style/{promtSn}/view", "/style/{promtSn}"})
	public String styleView(Model model, HttpServletRequest request, HttpServletResponse response,
			DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		defaultSetting(dspPromtScFrDTO, request);
		dspPromtScFrDTO.setPromtTpCd("STYLE_PROMT");
		DspPlanFoResult dspPlanFoResult = 	planComponent.selectDxmPlan(dspPromtScFrDTO);
		model.addAttribute("style",dspPlanFoResult);
		String domain = getConfigService().getProperty("ncp.url.pc_DX.root");
		model.addAttribute("og_title", dspPlanFoResult.getDspPromtExtend().getPromtNm());
		model.addAttribute("og_image", dspPlanFoResult.getDspPromtExtend().getPcImgFileUrl());
		model.addAttribute("og_desc", dspPlanFoResult.getDspPromtExtend().getPromtDscr());
		model.addAttribute("og_url", domain + "/style/" + dspPlanFoResult.getDspPromtExtend().getPromtSn() + "/view");
		
		model.addAttribute("seo_title", dspPlanFoResult.getDspPromtExtend().getPromtNm() + " | MLB");
		model.addAttribute("seo_desc", dspPlanFoResult.getDspPromtExtend().getPromtNm() + " | MLB");
		return "tiles:plan/style.view";
	}
	
	@RequestMapping(value = {"/discoverer/{promtSn}/view", "/discoverer/{promtSn}"})
	public String discovererView(Model model, HttpServletRequest request, HttpServletResponse response,
			DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		defaultSetting(dspPromtScFrDTO, request);
		dspPromtScFrDTO.setPromtTpCd("DX_PROMT");
		DspPlanFoResult dspPlanFoResult = 	planComponent.selectDxmPlan(dspPromtScFrDTO);
		model.addAttribute("style",dspPlanFoResult);
		String domain = getConfigService().getProperty("ncp.url.pc_DX.root");
		model.addAttribute("og_title", dspPlanFoResult.getDspPromtExtend().getPromtNm());
		model.addAttribute("og_image", dspPlanFoResult.getDspPromtExtend().getPcImgFileUrl());
		model.addAttribute("og_desc", dspPlanFoResult.getDspPromtExtend().getPromtDscr());
		model.addAttribute("og_url", domain + "/discoverer/" + dspPlanFoResult.getDspPromtExtend().getPromtSn() + "/view");
		
		model.addAttribute("seo_title", dspPlanFoResult.getDspPromtExtend().getPromtNm() + " | MLB");
		model.addAttribute("seo_desc", dspPlanFoResult.getDspPromtExtend().getPromtNm() + " | MLB");
		return "tiles:plan/discoverer.view";
	}
	
	@RequestMapping(value = "/style/styleList", method = { RequestMethod.GET, RequestMethod.POST })
	public String styleList(Model model, HttpServletRequest request, HttpServletResponse response,
			 DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
	
		defaultSetting(dspPromtScFrDTO, request);
		
		dspPromtScFrDTO.setPromtTpCd("STYLE_PROMT");
		dspPromtScFrDTO.setSearchCategory("plan");
		dspPromtScFrDTO.setDspUseGrpTpCd("STYLE_LIST_TMPLAT");
		PageParam pageParam = getPageService().buildPageParam(dspPromtScFrDTO.getPageNo(),
				dspPromtScFrDTO.getPageSize());
		planComponent.selectDxmEventPlanList(model,dspPromtScFrDTO, pageParam);
 
		return "tiles:plan/style.list";
	}
	@RequestMapping(value = "/discoverer/discovererList", method = { RequestMethod.GET, RequestMethod.POST })
	public String discovererList(Model model, HttpServletRequest request, HttpServletResponse response,
			 DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
	
		defaultSetting(dspPromtScFrDTO, request);
	 
		dspPromtScFrDTO.setPromtTpCd("DX_PROMT");
		dspPromtScFrDTO.setSearchCategory("plan");
		dspPromtScFrDTO.setDspUseGrpTpCd("DX_LIST_TMPLAT");
		PageParam pageParam = getPageService().buildPageParam(dspPromtScFrDTO.getPageNo(),
				dspPromtScFrDTO.getPageSize());
		planComponent.selectDxmEventPlanList(model,dspPromtScFrDTO, pageParam);
 
		return "tiles:plan/discoverer.list";
	}
	
	@RequestMapping(value = "/culture/cultureList", method = { RequestMethod.GET, RequestMethod.POST })
	public String cultureList(Model model, HttpServletRequest request, HttpServletResponse response,
			 DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
	
		defaultSetting(dspPromtScFrDTO, request);
		
		dspPromtScFrDTO.setPromtTpCd("CULTURE_PROMT");
		dspPromtScFrDTO.setSearchCategory("PLAN");
		dspPromtScFrDTO.setDspUseGrpTpCd("MLB_PC_CULTURE_LIST_TMPLAT"); //MTPA04A01
		PageParam pageParam = getPageService().buildPageParam(dspPromtScFrDTO.getPageNo(),
				dspPromtScFrDTO.getPageSize());
		planComponent.selectMallEventPlanList(model,dspPromtScFrDTO, pageParam);

		model.addAttribute("seo_title", "CULTURE | MLB");
		model.addAttribute("seo_desc", "CULTURE | MLB");
		return "tiles:plan/culture.list";
	}
	
	@RequestMapping(value = "/culture/cultureListPage.json", produces = {"application/json"})
	@ResponseBody
	public Map<String, Object> cultureListPage(Model model, HttpServletRequest request, HttpServletResponse response,
			 DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
	
		defaultSetting(dspPromtScFrDTO, request);
		
		dspPromtScFrDTO.setPromtTpCd("CULTURE_PROMT");
		dspPromtScFrDTO.setSearchCategory("PLAN");
		dspPromtScFrDTO.setDspUseGrpTpCd("MLB_PC_CULTURE_LIST_TMPLAT"); //MTPA04A01
		PageParam pageParam = getPageService().buildPageParam(dspPromtScFrDTO.getPageNo(),
				dspPromtScFrDTO.getPageSize());
		Page<DspPlanFoResult> evtList = planComponent.selectMallEventPlanPage(model, dspPromtScFrDTO, pageParam);
		
		Map<String, Object> map = new HashMap<>();
		map.put("evt", evtList.getContent());
		map.put("totalRow", evtList.getTotalElements());
		map.put("dspPromtScFrDTO", dspPromtScFrDTO);
		
		return map;
	}
	
	@RequestMapping(value = {"/culture/{promtSn}/view", "/culture/{promtSn}"})
	public String cultureView(Model model, HttpServletRequest request, HttpServletResponse response,
			DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		defaultSetting(dspPromtScFrDTO, request);
		dspPromtScFrDTO.setPromtTpCd("CULTURE_PROMT");
		DspPlanFoResult dspPlanFoResult = planComponent.selectMallPlan(dspPromtScFrDTO);
		model.addAttribute("culture", dspPlanFoResult);
		String domain = getConfigService().getProperty("ncp.url.pc_MLB.root");
		model.addAttribute("og_title", dspPlanFoResult.getDspPromtExtend().getPromtNm());
		model.addAttribute("og_image", dspPlanFoResult.getDspPromtExtend().getPcImgFileUrl());
		model.addAttribute("og_desc", dspPlanFoResult.getDspPromtExtend().getPromtDscr());
		model.addAttribute("og_url", domain + "/culture/" + dspPlanFoResult.getDspPromtExtend().getPromtSn() + "/view?promtExpsrSectCd=" + dspPromtScFrDTO.getPromtExpsrSectCd());
		
		model.addAttribute("seo_title", dspPlanFoResult.getDspPromtExtend().getPromtNm() + " | MLB");
		model.addAttribute("seo_desc", dspPlanFoResult.getDspPromtExtend().getPromtNm() + " | MLB");
		return "tiles:plan/culture.view";
	}
	
	@RequestMapping(value = "/lookbook/lookbookList", method = { RequestMethod.GET, RequestMethod.POST })
	public String lookbookList(Model model, HttpServletRequest request, HttpServletResponse response,
			DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
	
		defaultSetting(dspCtgryScFrDTO, request);
		
		model.addAttribute("lookbook", mbmDisplaySelectComponent.selectLookBookCtgryList(dspCtgryScFrDTO));

		model.addAttribute("seo_title", "LOOKBOOK | MLB");
		model.addAttribute("seo_desc", "LOOKBOOK | MLB");
		return "tiles:plan/lookbook.list";
	}
	
	@RequestMapping(value = "/lookbook/lookbookConttList", method = { RequestMethod.GET, RequestMethod.POST })
	public String lookbookConttList(Model model, HttpServletRequest request, HttpServletResponse response,
			DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
	
		defaultSetting(dspCtgryScFrDTO, request);
		
		model.addAttribute("lookbook", dspCtgryScFrDTO);
		model.addAttribute("lookbookContt", mbmDisplaySelectComponent.selectLookBookCtgryConttList(dspCtgryScFrDTO));
 
		return "plan/include/lookbookConttList";
	}
	
	@RequestMapping(value = "/lookbook/{upperCategory}/view", method = { RequestMethod.GET, RequestMethod.POST })
	public String lookbookDetailView(Model model, HttpServletRequest request, HttpServletResponse response,
			DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
	
		defaultSetting(dspCtgryScFrDTO, request);
		
		if (ContextService.hasRole()) { // 회원
			Mbr mbr = ((SecurityUserDetail) ContextService.getCurrentUserDetail()).getMbr();
			dspCtgryScFrDTO.setMbrNo(mbr.getMbrNo());
		}
		
		model.addAttribute("lookbook", dspCtgryScFrDTO);
		model.addAttribute("lookbookContt", mbmDisplaySelectComponent.selectLoobookCornerConttList(dspCtgryScFrDTO, model, getConfigService().getProperty("ncp.url.pc_MLB.root")));
 
		return "tiles:plan/lookbook.view";
	}
	
	@RequestMapping(value = "/lookbook/godInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public String lookbookGodInfo(Model model, HttpServletRequest request, HttpServletResponse response,
			DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
	
		defaultSetting(dspCtgryScFrDTO, request);
		
		if (ContextService.hasRole()) { // 회원
			Mbr mbr = ((SecurityUserDetail) ContextService.getCurrentUserDetail()).getMbr();
			dspCtgryScFrDTO.setMbrNo(mbr.getMbrNo());
		}
		
		model.addAttribute("lookbook", dspCtgryScFrDTO);
		model.addAttribute("godContt", mbmDisplaySelectComponent.selectLoobookGodInfo(dspCtgryScFrDTO));
		
		return "plan/include/lookbookGodInfo";
 
	}
	
	@RequestMapping(value = {"/lookbook/godDetailInfo"}, method = { RequestMethod.GET, RequestMethod.POST }, produces="text/plain;charset=UTF-8")
	public String lookbookGodDetailInfo(Model model, GoodsSearchDTO goodsSearchDTO, DspCtgryScFrDTO dspCtgryScFrDTO,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		String viewPage = "";

		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		goodsSearchDTO.setMallTpCd(systemPK.getMall());
		goodsSearchDTO.setLangCd(systemPK.getLang());
		goodsSearchDTO.setDevice(systemPK.getDevice());
		
		//상품정보 조회
		GoodsInfoResult goods = goodsFOComponent.getGoods(goodsSearchDTO);
		
		if (String.valueOf(GoodsEnum.SUCCESS_CODE).equals(goods.getRstCd())) {
			model.addAttribute("goods", goods);
			model.addAttribute("isBoPreview", goodsSearchDTO.getIsBoPreview());
		
			String godNo = goods.getGodEx().getGodNo();
			
			String basketOrWish = request.getParameter("basketOrWish");
			
			if("B".equals(basketOrWish)) {
				//				상품 컨텐츠 조회
				this.getGoodsContent(model, response, request, systemPK, godNo);
			} else {
				//				회원 위시 정보 설정
				this.setMemberWish(model, godNo);
			}
			
			// IP에 따른 언어 SET
			HttpSession session = request.getSession();
			model.addAttribute("isoCodeName", session.getAttribute(isoCodeName));
			
			viewPage = "plan/include/lookbookGodDetailInfo";
			
		} 
		
		return viewPage;
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
	
}
