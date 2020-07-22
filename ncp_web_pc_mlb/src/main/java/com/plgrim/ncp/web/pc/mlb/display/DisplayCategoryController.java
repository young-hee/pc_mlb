package com.plgrim.ncp.web.pc.mlb.display;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrTodayGod;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.base.enums.OlapicEnum;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.data.InterestSearchFoDTO;
import com.plgrim.ncp.biz.display.data.OlapicDTO;
import com.plgrim.ncp.biz.display.result.DspCnrFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryGodFoResult;
import com.plgrim.ncp.biz.display.result.MallDspFoCtgryResult;
import com.plgrim.ncp.biz.display.result.GoodsInterestsGodFoResult;
import com.plgrim.ncp.biz.goods.data.GoodsPriceSearchDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MypageTdGodFoResult;
import com.plgrim.ncp.cmp.display.fo.MbmDisplaySelectComponent;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.cmp.product.fo.GoodsPriceFOComponent;
import com.plgrim.ncp.commons.taglib.Functions;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.LocaleService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.page.PageService;

import groovy.util.logging.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/display")
public class DisplayCategoryController extends DisplayCommonController {

	@Autowired
	MbmDisplaySelectComponent mbmDisplaySelectComponent;
	
	@Autowired
	MemberActivityFOComponent memberActivityFOComponent;
	
	@Autowired
	GoodsPriceFOComponent goodsPriceFOComponent;	

	@RequestMapping(value = "/view", method = { RequestMethod.GET, RequestMethod.POST })
	public String view(Model model, HttpServletRequest request, HttpServletResponse response,
			DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		defaultSetting(dspCtgryScFrDTO, request);
		PageParam pageParam = getPageService().buildPageParam(dspCtgryScFrDTO.getPageNo(),
				dspCtgryScFrDTO.getMallPageSize());
		mbmDisplaySelectComponent.selectCommonCtgryList(model, dspCtgryScFrDTO, pageParam);
		
		mbmDisplaySelectComponent.selectGodTeamList(model, dspCtgryScFrDTO, getLocaleService().getCurrentLocale(request));
		
		model.addAttribute("og_title", dspCtgryScFrDTO.getMetaSjNm());
		model.addAttribute("og_desc", dspCtgryScFrDTO.getMetaDscrCont());

		if (DisplayEnum.CTGRY_SECT_CD.STRTGY_CTGRY.name().equals(dspCtgryScFrDTO.getCtgrySectCd())) {
			return "tiles:display/display.special.category";
		} else if (DisplayEnum.CTGRY_SECT_CD.OTLT_CTGRY.name().equals(dspCtgryScFrDTO.getCtgrySectCd())) {
			return "tiles:display/display.outlet.category";
		} else {
			return "tiles:display/display.category";
		}
	}

	@RequestMapping(value = "/majorView", method = { RequestMethod.GET, RequestMethod.POST })
	public String majorView(Model model, HttpServletRequest request, HttpServletResponse response,
			DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		defaultSetting(dspCtgryScFrDTO, request);
		PageParam pageParam = getPageService().buildPageParam(dspCtgryScFrDTO.getPageNo(),
				dspCtgryScFrDTO.getMallPageSize());
		mbmDisplaySelectComponent.selectCommonCtgryList(model, dspCtgryScFrDTO, pageParam);
		mbmDisplaySelectComponent.selectGodTeamList(model, dspCtgryScFrDTO, getLocaleService().getCurrentLocale(request));
		
		String env  = getConfigService().getProperty("ncp_base.env");
		
		List<DspCnrFrResult> dspCnrFrResultList = mbmDisplaySelectComponent.selectMajorCategoryContt(dspCtgryScFrDTO);
		
		LinkedHashMap<String, Object> dspCnrMap = new LinkedHashMap<String, Object>();
		
		for(DspCnrFrResult dspCnrFrResult : dspCnrFrResultList) {
			
			dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
			dspCtgryScFrDTO.setDspCtgryNo(dspCnrFrResult.getDspCtgryNo());
			
			dspCnrMap.put(dspCtgryScFrDTO.getCnrSn(), mbmDisplaySelectComponent.selectCnrConttList(dspCtgryScFrDTO));
		
		}
		
		model.addAttribute("env", env);
		model.addAttribute("ctgryTmplat", dspCnrMap);
		
		return "tiles:display/display.major.category";

	}

	@RequestMapping(value = "/lnbCtgryList.json", produces = { "application/json" })
	@ResponseBody
	public Map<String, Object> lnbCtgryList(Model model, HttpServletRequest request, HttpServletResponse response,
			DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		defaultSetting(dspCtgryScFrDTO, request);
		dspCtgryScFrDTO.setUpperCategory("MBM");
		MallDspFoCtgryResult mallDspFoCtgryResult = mbmDisplaySelectComponent.lnbCtgryList(dspCtgryScFrDTO);
		Map<String, Object> map = new HashMap<>();
		map.put("lnbCategorySet", mallDspFoCtgryResult.getMallDspFoCtgryResults());
		map.put("ctgryNoDpth1", dspCtgryScFrDTO.getCtgryNoDpth1());
		map.put("ctgryNoDpth2", dspCtgryScFrDTO.getCtgryNoDpth2());
		map.put("ctgryNoDpth3", dspCtgryScFrDTO.getCtgryNoDpth3());
		return map;
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(Model model, HttpServletRequest request, HttpServletResponse response,
			DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		defaultSetting(dspCtgryScFrDTO, request);
		PageParam pageParam = getPageService().buildPageParam(dspCtgryScFrDTO.getPageNo(),
				dspCtgryScFrDTO.getMallPageSize());
		
		if(StringService.isEmpty(dspCtgryScFrDTO.getSortColumn())){
			dspCtgryScFrDTO.setSortColumn("NEW_GOD_SEQ");	
		}
		
		
		mbmDisplaySelectComponent.searchDisplayGodList(model, dspCtgryScFrDTO, pageParam);
		
		mbmDisplaySelectComponent.selectGodTeamList(model, dspCtgryScFrDTO, getLocaleService().getCurrentLocale(request));

		String sTitle = "";
		if(!dspCtgryScFrDTO.getSearchText().isEmpty()) sTitle = dspCtgryScFrDTO.getSearchText();
		else sTitle = "검색";
		
		model.addAttribute("seo_title", sTitle + " | MLB");
		model.addAttribute("seo_desc", sTitle + " | MLB");
		
		return "tiles:display/display.search";
	}
	
	@RequestMapping(value = "/aboutDiscovery", method = { RequestMethod.GET, RequestMethod.POST })
	public String aboutDiscovery(Model model, HttpServletRequest request, HttpServletResponse response,
			DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		defaultSetting(dspCtgryScFrDTO, request);
		model.addAttribute("filterType", dspCtgryScFrDTO.getFilterType());
		return "tiles:display/about.discovery";
	}

	@RequestMapping(value = "/reviewsList", method = { RequestMethod.GET, RequestMethod.POST })
	public String reviewsList(Model model, HttpServletRequest request, HttpServletResponse response,
			DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		defaultSetting(dspCtgryScFrDTO, request);

		PageParam pageParam = getPageService().buildPageParam(dspCtgryScFrDTO.getPageNo(),
				dspCtgryScFrDTO.getPageSize());
		Page<GodGodEvlExtend> reviews = mbmDisplaySelectComponent.selectDisplayReviewList( dspCtgryScFrDTO, pageParam);
		PageService.makePageResult(reviews, model);
		model.addAttribute("reviews", reviews.getContent());
		model.addAttribute("totalRow", reviews.getTotalElements());
		model.addAttribute("dspCtgryScFrDTO", dspCtgryScFrDTO);
		dspCtgryScFrDTO.setBstGodEvlYn("Y");
		model.addAttribute("best",  mbmDisplaySelectComponent.selectDisplayBestReviewList( dspCtgryScFrDTO));
		model.addAttribute("bestCategory",  mbmDisplaySelectComponent.bestCtgryList( dspCtgryScFrDTO));
		
		return "tiles:display/display.reviews";
	}
	
	@RequestMapping(value = "/todayGoodList.json", produces = { "application/json" })
	public void getTodayGoodListAjax(Model model,@ModelAttribute MypageFoDTO dto, HttpServletRequest request) throws Exception{			
		boolean loginYn = false;
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		dto.setMallId(pk.getMall());
		dto.setLang(pk.getLang());

		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			dto.setMbr(userDetail.getMbr());
			loginYn = true;
		}
		
		GoodsPriceSearchDTO priceDTO = goodsPriceFOComponent.getMemberTypeForPriceForPC();
		
		if(loginYn){
			PageParam pageParam = getPageService().buildPageParam("1", 10);			
			dto.setSpcPrmTp(priceDTO.getSpcPrmTp());
			dto.setPrcSectCd(priceDTO.getPrcSectCd());
			Page<MypageTdGodFoResult> lists = memberActivityFOComponent.getMyTodayGodList(pk, dto, pageParam);
			model.addAttribute("listTdGod", lists.getContent());
		}else{
			Cookie cookies[] = ContextService.getCurrentRequest().getCookies();
			List<GoodsInterestsGodFoResult> result = new ArrayList<GoodsInterestsGodFoResult>();
			List<MbrTodayGod> skyScrapers = new ArrayList<MbrTodayGod>();
			for (int i = 0; i < cookies.length; i++) {
				Functions.cookies(cookies, skyScrapers, i);
			}
			InterestSearchFoDTO interestSearchFoDTO =  new InterestSearchFoDTO();
			interestSearchFoDTO.setMallId(pk.getMall());
			interestSearchFoDTO.setLang(pk.getLang());
			interestSearchFoDTO.setPrcSectCd(priceDTO.getPrcSectCd());			
			interestSearchFoDTO.setEndIndex(10);			
			interestSearchFoDTO.setSesionId(request.getSession().getId());
			if(skyScrapers != null && skyScrapers.size() > 0){
				interestSearchFoDTO.setSkyScrapers(skyScrapers);
				result = mbmDisplaySelectComponent.getTodayCookiesList(interestSearchFoDTO);
			}			
			model.addAttribute("listTdGod", result);
		}
	}
	
	@RequestMapping(value = "/recoPick.json" , produces = {"application/json"})
	@ResponseBody
	public  Map<String, Object>  recoPick(Model model, HttpServletRequest request, HttpServletResponse response,
			DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
 
		defaultSetting(dspCtgryScFrDTO, request);
		List<DspCtgryGodFoResult> godList = mbmDisplaySelectComponent.selectRecoPickGodList(dspCtgryScFrDTO);

		Map<String, Object> map = new HashMap<>();
		map.put("godList",  godList);
 
		return map;
	}
}
