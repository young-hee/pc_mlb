package com.plgrim.ncp.web.pc.mlb.mypage;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.plgrim.ncp.base.entities.datasource1.god.GodItmExtend;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.goods.data.GoodsPriceSearchDTO;
import com.plgrim.ncp.biz.goods.service.GoodsInfoService;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MypageWishFoResult;
import com.plgrim.ncp.cmp.display.fo.DxmDisplaySelectComponent;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartCommandComponent;
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
@RequestMapping("/mypage/wishlist")
public class MypageWishlistController extends MypageController {

	@Autowired
	CommonSelectComponent commonSelectComponent;

	@Autowired
	CommonSelectComponentImpl commonSelectComponentImpl;

	@Autowired
	DxmDisplaySelectComponent dxmDisplaySelectComponent;

	@Autowired
	MemberActivityFOComponent memberActivityFOComponent;

	@Autowired
	GoodsPriceFOComponent goodsPriceFOComponent;

	@Autowired
	IdGenService idGenService;
	/** The cart command component. */
	@Autowired
	private CartCommandComponent cartCommandComponent;

	@Autowired
	private GoodsInfoService goodsInfoService;

	/**
	 * 위시리스트
	 */
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String getCouponList(@ModelAttribute  MypageFoDTO mypageFoDTO , Model model, HttpServletRequest request, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		this.setMypageTitleSetKey(model);

		/** 화면 location 세팅 시작 **/
		List<Map<String, String>> locationSet = this.makeMypageLocationSet();

		Map<String, String> location = new HashMap<String, String>();

		location.put("url", null);
        location.put("msgKey", "mypage.submain.activeinfo.ttl");
        locationSet.add(location);

        location = new HashMap<String, String>();

        location.put("url", "/mypage/wishlist/list");
        location.put("msgKey", "mypage.wishlist.ttl1");
        locationSet.add(location);

        model.addAttribute("locationSet", locationSet);
        /** 화면 location 세팅 종료 **/

		model.addAttribute("myPage",mypageFoDTO);

		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap !=null) {
			model.addAttribute("dspCtgryScFrDTO", (DspCtgryScFrDTO) flashMap.get("dspCtgryScFrDTO"));
		}

		model.addAttribute("seo_title", "마이페이지 | MLB");
		model.addAttribute("seo_desc", "마이페이지 | MLB");
		
		return "tiles:mypage/wishlist/wishlist.list";
	}

	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping(value = { "/include/list.ajax" }, method = { RequestMethod.POST, RequestMethod.GET })
	public String listWishlist(HttpServletRequest request, MypageFoDTO dto,
			@RequestParam(value = "pageNo", required = false) String pageNo
			, @RequestParam(value="mypageMainYn" , required = false ) String mypageMainYn
			, Model model) throws Exception {

		if (StringService.isEmpty(pageNo)) {
			pageNo = "1";
		}

		PageParam pageParam = null;
		// 노출 갯수 조정
		if( "Y".equals(mypageMainYn) ) {
			pageParam = getPageService().buildPageParam(pageNo, 4);
		}else {
			pageParam = getPageService().buildPageParam(pageNo, 8);
		}

		try {
			SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
			dto.setMallId(pk.getMall());
			dto.setLang(pk.getLang());

			if (ContextService.hasRole()) {
				SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

				//userDetail.getMbr().setMbrNo("MB201805160000021");
				dto.setMbr(userDetail.getMbr());
			}

			GoodsPriceSearchDTO priceDTO = goodsPriceFOComponent.getMemberTypeForPriceForPC();
			dto.setSpcPrmTp(priceDTO.getSpcPrmTp());
			dto.setPrcSectCd(priceDTO.getPrcSectCd());

			log.info("WishList Log getPrcSectCd ==================================== " + priceDTO.getPrcSectCd());
			log.info("WishList Log getSpcPrmTp  ==================================== " + priceDTO.getSpcPrmTp());

			Page<MypageWishFoResult> lists = memberActivityFOComponent.getMyWishList(pk, dto, pageParam);

			model.addAttribute("wishlistList", lists.getContent());
			model.addAttribute("mypageMainYn", mypageMainYn);
			PageService.makePageResult(lists, model);

			log.info("WishList Log ==================================== ");

			log.info(lists.getContent().toString());
			log.info(pk.toString());
		} catch (Exception e) {
			log.error("", e);
		}

		return "tiles:mypage/wishlist/include/wishlist.list.include";
	}


	/**
	 * Wish List 삭제
	 * @param model [설명]
	 * @param request [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/deleteMyWishList", method = {RequestMethod.POST })
	public String deleteMyWishList(MypageFoDTO mypageFoDTO, HttpServletRequest request , Model model, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		memberActivityFOComponent.deleteMyWishList(mypageFoDTO);

		model.addAttribute("myPage", mypageFoDTO);

		FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
		fm.put("dspCtgryScFrDTO", dspCtgryScFrDTO);

		return "redirect:/mypage/wishlist/list";
	}

	/**
	 * Wish List 전체삭제
	 * @param model [설명]
	 * @param request [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/deleteAllMyWishList", method = {RequestMethod.POST })
	public String deleteAllMyWishList(MypageFoDTO mypageFoDTO, HttpServletRequest request , Model model, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		mypageFoDTO.setLang(pk.getLang());

		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mypageFoDTO.setMbr(userDetail.getMbr());
		}

		memberActivityFOComponent.deleteAllMyWishList(mypageFoDTO);

		model.addAttribute("myPage", mypageFoDTO);

		FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
		fm.put("dspCtgryScFrDTO", dspCtgryScFrDTO);

		return "redirect:/mypage/wishlist/list";
	}

	@RequestMapping(value = "/insert.json", method = RequestMethod.POST, produces = { "application/json" })
	public void moveToWishList(@ModelAttribute("cartSearchDTO") CartSearchDTO cartSearchDTO,Model model, HttpServletRequest request) throws Exception {

		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		int regCnt = cartCommandComponent.moveToWishList(systemPK,cartSearchDTO);

		if(regCnt > 0){
			model.addAttribute("msg", "success");
			model.addAttribute("wishlstSn", cartSearchDTO.getWishlstSn());
			model.addAttribute("godTurn", cartSearchDTO.getGodTurn());
		}else{
			model.addAttribute("msg", "false");

		}

	}
	@RequestMapping(value = "/deleteGodWishList.json", method = RequestMethod.POST, produces = { "application/json" })
	public void deleteGodWishList(MypageFoDTO mypageFoDTO, HttpServletRequest request , Model model ) throws Exception {

		memberActivityFOComponent.deleteMyWishList(mypageFoDTO);
		model.addAttribute("myPage", mypageFoDTO);
		model.addAttribute("msg", "success");
	}

	@RequestMapping(value = "/gnbListCount.json", method = RequestMethod.POST, produces = { "application/json" })
	public void gnbListCount(MypageFoDTO dto, HttpServletRequest request , Model model ) throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		dto.setMallId(pk.getMall());
		dto.setLang(pk.getLang());

		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			dto.setMbr(userDetail.getMbr());

			GoodsPriceSearchDTO priceDTO = goodsPriceFOComponent.getMemberTypeForPriceForPC();
			dto.setSpcPrmTp(priceDTO.getSpcPrmTp());
			dto.setPrcSectCd(priceDTO.getPrcSectCd());

			List<MypageWishFoResult> lists = memberActivityFOComponent.getMyWishListMobile(pk, dto);
			model.addAttribute("gnbListCount", lists);
		}else{
			model.addAttribute("gnbListCount", new ArrayList<MypageWishFoResult>());
		}

	}

	@RequestMapping(value="/getItemNo", method=RequestMethod.GET)
	@ResponseBody
	public String getItemNo(@RequestParam(value="godNo", required=true) String godNo) throws Exception {
		StringBuffer sb = new StringBuffer();
		List<GodItmExtend> itemList = goodsInfoService.getGoodsItemList(godNo);
		for (GodItmExtend godItem : itemList) {
			sb.append(godItem.getItmNo());
			sb.append(" ");
		}
		return sb.toString().trim();
	}
}
