package com.plgrim.ncp.web.pc.mlb.mypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.goods.data.GoodsPriceSearchDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MypageTdGodFoResult;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.cmp.product.fo.GoodsPriceFOComponent;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.commons.CommonSelectComponentImpl;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.page.PageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mypage/todaygood")
public class MypageTodayGoodController extends MypageController {

	@Autowired
	CommonSelectComponent commonSelectComponent;

	@Autowired
	CommonSelectComponentImpl commonSelectComponentImpl;
	
	@Autowired
	MemberActivityFOComponent memberActivityFOComponent;
	
	@Autowired
	GoodsPriceFOComponent goodsPriceFOComponent;
	
	@Autowired
	IdGenService idGenService;
	
	/**
	 * 최근본상품
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String getCouponList(@ModelAttribute  MypageFoDTO mypageFoDTO , Model model, HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		this.setMypageTitleSetKey(model);
		List<Map<String, String>> locationSet = this.makeMypageLocationSet();
		
		Map<String, String> location = new HashMap<String, String>();
		
		location.put("url", null);
        location.put("msgKey", "mypage.submain.activeinfo.ttl");
        locationSet.add(location);
        
        location = new HashMap<String, String>();
        
        location.put("url", "/mypage/todaygood/list");
        location.put("msgKey", "mypage.todaygood.ttl1");
        locationSet.add(location);
        
        model.addAttribute("locationSet", locationSet);
        
		model.addAttribute("myPage",mypageFoDTO);
		
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap !=null) {  
			model.addAttribute("dspCtgryScFrDTO", (DspCtgryScFrDTO) flashMap.get("dspCtgryScFrDTO"));
		}

		model.addAttribute("seo_title", "마이페이지 | MLB");
		model.addAttribute("seo_desc", "마이페이지 | MLB");
		
		return "tiles:mypage/todaygood/todaygood.list";		
	}
	
	@RequestMapping(value = { "/include/list" }, method = { RequestMethod.POST, RequestMethod.GET })
	public String listWishlist(HttpServletRequest request, MypageFoDTO dto,
			@RequestParam(value = "pageNo", required = false) String pageNo, Model model)
			throws Exception {

		if (StringService.isEmpty(pageNo)) {
			pageNo = "1";
		}
		PageParam pageParam = getPageService().buildPageParam(pageNo, 8);

		try {
			SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
			dto.setMallId(pk.getMall());
			dto.setLang(pk.getLang());
			
			if (ContextService.hasRole()) {
				SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
				dto.setMbr(userDetail.getMbr());
			}
			
			GoodsPriceSearchDTO priceDTO = goodsPriceFOComponent.getMemberTypeForPriceForPC();
			dto.setSpcPrmTp(priceDTO.getSpcPrmTp());
			dto.setPrcSectCd(priceDTO.getPrcSectCd());
			
			log.info("todaygood Log getPrcSectCd ==================================== " + priceDTO.getPrcSectCd());
			log.info("todaygood Log getSpcPrmTp  ==================================== " + priceDTO.getSpcPrmTp());

			Page<MypageTdGodFoResult> lists = memberActivityFOComponent.getMyTodayGodList(pk, dto, pageParam);

			model.addAttribute("listTdGod", lists.getContent());
			PageService.makePageResult(lists, model);

			log.info("todaygood Log ==================================== ");
			log.info(lists.getContent().toString());
			log.info(pk.toString());
		} catch (Exception e) {
			log.error("", e);
		}

		return "tiles:mypage/todaygood/include/todaygood.list.include";
	}
	
	
	/**
	 * MyPage 회원 최근본상품 삭제
	 * @param model [설명]
	 * @param request [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	@RequestMapping(value = "/deleteTodayGoodList", method = {RequestMethod.POST })
	public String deleteTodayGoodList(MypageFoDTO mypageFoDTO, HttpServletRequest request , Model model, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		memberActivityFOComponent.deleteMyTodayGodList(mypageFoDTO);
		model.addAttribute("myPage", mypageFoDTO);
		
		FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
		fm.put("dspCtgryScFrDTO", dspCtgryScFrDTO);
		
		return "redirect:/mypage/todaygood/list";
	}
	
	
	/**
	 * MyPage 회원 최근본상품 전체삭제
	 * @param model [설명]
	 * @param request [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	@RequestMapping(value = "/deleteAllTodayGoodList", method = {RequestMethod.POST })
	public String deleteAllMyWishList(MypageFoDTO mypageFoDTO, HttpServletRequest request , Model model, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageFoDTO.setMbr(userDetail.getMbr());
		}
		
		mypageFoDTO.setLang(pk.getLang());
		
		memberActivityFOComponent.deleteAllTodayGoodList(mypageFoDTO);
		
		model.addAttribute("myPage", mypageFoDTO);
		
		FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
		fm.put("dspCtgryScFrDTO", dspCtgryScFrDTO);
		
		return "redirect:/mypage/wishlist/list";
	}	
	
	@RequestMapping(value = "/deleteGodTodayGoodList.json", method = RequestMethod.POST, produces = { "application/json" })
	public void deleteGodTodayGoodList(MypageFoDTO mypageFoDTO, HttpServletRequest request , Model model ) throws Exception {
 
		memberActivityFOComponent.deleteMyTodayGodList(mypageFoDTO);
		model.addAttribute("myPage", mypageFoDTO);
		model.addAttribute("msg", "success");
	}
}
