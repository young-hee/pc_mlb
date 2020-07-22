package com.plgrim.ncp.web.pc.mlb.main;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.result.DspCnrFrResult;
import com.plgrim.ncp.cmp.display.fo.MbmDisplaySelectComponent;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.web.pc.mlb.display.DisplayCommonController;

import groovy.util.logging.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/main/mall")
public class MainMallController extends DisplayCommonController {

	@Autowired
	MbmDisplaySelectComponent mbmDisplaySelectComponent;
	@Autowired
	CommonSelectComponent commonSelectComponent;

	@RequestMapping(value = "/view", method = { RequestMethod.GET, RequestMethod.POST })
	public String mallView(Model model, HttpServletRequest request, HttpServletResponse response,
			DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		defaultSetting(dspCtgryScFrDTO, request);
		dspCtgryScFrDTO.setMallGubun("MBM");
		dspCtgryScFrDTO.setUpperCategory("MBM");
		dspCtgryScFrDTO.setPrcSectCd("GNRL");
		dspCtgryScFrDTO.setDspUseGrpTpCd("MLB_MAIN_TMPLAT"); // MTPA01A01
		
		String env  = getConfigService().getProperty("ncp_base.env");

		List<DspCnrFrResult> dspCnrFrResultList = mbmDisplaySelectComponent.selectMainContt(dspCtgryScFrDTO);
		
		LinkedHashMap<String, Object> dspCnrMap = new LinkedHashMap<String, Object>();
		
		for(DspCnrFrResult dspCnrFrResult : dspCnrFrResultList) {
			
			dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
			dspCtgryScFrDTO.setDspCtgryNo(dspCnrFrResult.getDspCtgryNo());
			
			dspCnrMap.put(dspCtgryScFrDTO.getCnrSn(), mbmDisplaySelectComponent.selectCnrConttList(dspCtgryScFrDTO));
		
		}
		
		model.addAttribute("og_title", "MLB");
		model.addAttribute("og_image", "/display/content/MLB-OG.png");
		model.addAttribute("og_type", "website");
		model.addAttribute("og_desc", "Young Generation의 패션 아이콘. 스트릿 캐주얼 브랜드 MLB 공식 온라인 스토어");
		model.addAttribute("og_url", "https://www.mlb-korea.com/");
		
		model.addAttribute("seo_title", "MLB");
		model.addAttribute("seo_desc", "Young Generation의 패션 아이콘. 스트릿 캐주얼 브랜드 MLB 공식 온라인 스토어");
		
		model.addAttribute("env", env);
//		model.addAttribute("bnr",dspCnrFrResult.getCnrBnrConttList());
//		model.addAttribute("newArrival", dspCnrFrResult.getNewArrivalList());
//		model.addAttribute("contt", dspCnrFrResult.getCnrMainConttList());
//		model.addAttribute("acrticleCss","contents-type01");
		
		mbmDisplaySelectComponent.selectMainRankingGod(model, dspCtgryScFrDTO);
		
		model.addAttribute("mainTmplat", dspCnrMap);
	
		return "tiles:main/mall.view";
	}

	@RequestMapping(value = "/conttAppend", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ModelAndView conttAppend(Model model, HttpServletRequest request, HttpServletResponse response,
			DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		defaultSetting(dspCtgryScFrDTO, request);
		dspCtgryScFrDTO.setMallGubun("MBM");
		dspCtgryScFrDTO.setUpperCategory("MBM");
		dspCtgryScFrDTO.setPrcSectCd("GNRL");
		dspCtgryScFrDTO.setDspUseGrpTpCd("MAIN_TMPLAT"); // THMA01A01

		DspCnrFrResult dspCnrFrResult = mbmDisplaySelectComponent.conttAppend(dspCtgryScFrDTO);
		model.addAttribute("contt", dspCnrFrResult.getCnrMainConttList());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("tiles:main/include/contt.append");
		return mav;
	}
	
}
