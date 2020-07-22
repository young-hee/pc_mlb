package com.plgrim.ncp.web.pc.mlb.cart;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodExtend;
import com.plgrim.ncp.base.enums.BskEnum.BskTp;
import com.plgrim.ncp.biz.cart.data.CartDTO;
import com.plgrim.ncp.biz.cart.data.CartGodOptionDTO;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.cart.data.ReOrderCartDTO;
import com.plgrim.ncp.biz.cart.repository.CartCPSRepository;
import com.plgrim.ncp.biz.cart.result.CartListResult;
import com.plgrim.ncp.biz.cart.result.CartOrderCheckResult;
import com.plgrim.ncp.biz.cart.result.CartResult;
import com.plgrim.ncp.biz.cart.result.CartSimpleListResult;
import com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartCommandComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartSelectComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.order.OrderSelectComponent;
import com.plgrim.ncp.cmp.vendor.fo.VendorShopFOComponent;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.framework.commons.CollectionService;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.data.SystemPK;

import lombok.extern.slf4j.Slf4j;


/**
 * [클래스 설명] 카트 조회
 * <p>
 *
 * <ul>
 * <li>[기능1]
 * <li>[기능2]
 * </ul>.
 *
 * @author syvictor.kim 
 * @since 2015. 3. 21 
 */ 
@Slf4j
@Controller
@RequestMapping("/cart/goods")
public class CartGoodsAjaxController extends CartController {


	/** The cart select component. */
	@Autowired
	CartSelectComponent cartSelectComponent;

	@Autowired
	OrderSelectComponent orderSelectComponent;
	/** The common select component. */
	@Autowired
	CommonSelectComponent commonSelectComponent;
	/** The cart cps repository. */
	@Autowired
	protected CartCPSRepository cartCPSRepository;

	@Autowired
	MessageSourceAccessor messageSourceAccessor;

	@Autowired
	VendorShopFOComponent vendorShopFOComponent;
	
	/** The display select commant. */
	@Autowired
    DisplaySelectComponent displaySelectCommant;
		

	@Autowired
    DisplaySelectComponent displaySelectComponent;

	
	@Autowired
	CartCommandComponent cartCommandComponent;


	@RequestMapping(value = "/list.json", produces = { "application/json" })
	public void cartListAjax(Model model,@ModelAttribute CartSearchDTO cartSearchDTO, HttpServletRequest request) throws Exception{
		log.debug("{}",  request.getRequestURI());
		log.debug("{}",  cartSearchDTO);
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		

		CartListResult result = cartSelectComponent.selectCartList(systemPK,cartSearchDTO);


		
		result.setLang(systemPK.getLang());


		BigDecimal bd = null;


		model.addAttribute("cart", result);
		
		
		if (ContextService.hasRole()) { // 회원
			model.addAttribute("mbrYn", "Y");
		}else{
			model.addAttribute("mbrYn", "N");
		}

//		//배송비즉시할인쿠폰 : by cannon (2016.06.07)
//		if(result.getDlvFeeImdtlCpnResultList() == null){
//			model.addAttribute("dlvfeeimdtlcpn","[]");
//		}else{
//			model.addAttribute("dlvfeeimdtlcpn", JsonUtil.marshallingJson(result.getDlvFeeImdtlCpnResultList()));
//		}


		model.addAttribute("recomCdList",CodeUtil.getCodes("RECOMEND_SEX", systemPK.getLang()));
		model.addAttribute("resultMsg", "ok");
		
	}
	
	
	
	
	/**
	 * [장바구니 상품 추가].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartDTO [설명]
	 * @param model [설명]
	 * @param request [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 24
	 */
	//@RequestMapping(value = "/add.json", method = RequestMethod.POST, produces = { "application/json" })
	@RequestMapping(value = "/add.json", produces = { "application/json" })
	public void addCart(@ModelAttribute CartDTO cartDTO, Model model, HttpServletRequest request) throws Exception {


		log.debug("{}",  request.getRequestURI());
		log.debug("{}",  cartDTO);
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);


		/*int cartCnt = cartSelectComponent.selectMbrCartCnt(pk);

		if(cartCnt>=99){
			log.error("장바구니 최대 수량을 넘었습니다.[99개]");
			model.addAttribute("msg", "false");
			//model.addAttribute("errMsg", "장바구니 최대 가능 수량을 넘었습니다.[99개]");
			model.addAttribute("errMsg", messageSourceAccessor.getMessage("cart.err.maxcartcnt", getLocaleService().getCurrentLocale(request)));


		}else{
			cartDTO.getBsk().setBskTpCd(BskTp.BSK.toString());
			Map rtn = cartCommandComponent.addCart(pk, cartDTO);
			model.addAttribute("rtncode", rtn.get("rtn"));
			if(((Integer)rtn.get("rtn")).intValue() > 0){
				model.addAttribute("msg", "success");
			}else{
				model.addAttribute("msg", "false");
				model.addAttribute("errMsg", messageSourceAccessor.getMessage("cart.err.inv", getLocaleService().getCurrentLocale(request)));
			}
		}*/
		
		cartDTO.getBsk().setBskTpCd(BskTp.BSK.toString());
		Map rtn = cartCommandComponent.addCart(pk, cartDTO);
		model.addAttribute("rtncode", rtn.get("rtn"));
		if(((Integer)rtn.get("rtn")).intValue() > 0){
			model.addAttribute("msg", "success");
		}else{
			model.addAttribute("msg", "false");
			model.addAttribute("errMsg", messageSourceAccessor.getMessage("cart.err.inv", getLocaleService().getCurrentLocale(request)));
		}
	}

	@RequestMapping(value = "/addJSON.json", method = RequestMethod.POST, produces = { "application/json" })
	public void addCartJSON(@RequestBody CartDTO cartDTO, Model model, HttpServletRequest request) throws Exception {


		log.debug("{}",  request.getRequestURI());
		log.debug("{}",  cartDTO);
		
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);


		/*int cartCnt = cartSelectComponent.selectMbrCartCnt(pk);

		if(cartCnt>=99){
			log.error("장바구니 최대 수량을 넘었습니다.[99개]");
			model.addAttribute("msg", "false");
			//model.addAttribute("errMsg", "장바구니 최대 가능 수량을 넘었습니다.[99개]");
			model.addAttribute("errMsg", messageSourceAccessor.getMessage("cart.err.maxcartcnt", getLocaleService().getCurrentLocale(request)));


		}else{
			cartDTO.getBsk().setBskTpCd(BskTp.BSK.toString());
			Map rtn = cartCommandComponent.addCart(pk, cartDTO);
			model.addAttribute("rtncode", rtn.get("rtn"));
			if(((Integer)rtn.get("rtn")).intValue() > 0){
				model.addAttribute("msg", "success");
				model.addAttribute("godTurn", rtn.get("newGodTurn"));
			}else{
				model.addAttribute("msg", "false");
				model.addAttribute("errMsg", messageSourceAccessor.getMessage("cart.err.inv", getLocaleService().getCurrentLocale(request)));
			}
		}*/
		
		cartDTO.getBsk().setBskTpCd(BskTp.BSK.toString());
		Map rtn = cartCommandComponent.addCart(pk, cartDTO);
		model.addAttribute("rtncode", rtn.get("rtn"));
		if(((Integer)rtn.get("rtn")).intValue() > 0){
			model.addAttribute("msg", "success");
			model.addAttribute("godTurn", rtn.get("newGodTurn"));
		}else{
			model.addAttribute("msg", "false");
			model.addAttribute("errMsg", messageSourceAccessor.getMessage("cart.err.inv", getLocaleService().getCurrentLocale(request)));
		}
	}

	/**
	 * [즉시구매 장바구니 추가].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartDTO [설명]
	 * @param model [설명]
	 * @param request [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
	@RequestMapping(value = "/add/orderNow.json", method = RequestMethod.POST, produces = { "application/json" })
	public void addCartOrder(@ModelAttribute CartDTO cartDTO, Model model, HttpServletRequest request) throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		cartDTO.getBsk().setBskTpCd(BskTp.DIRT.toString());
		Map rtn = cartCommandComponent.addCart(pk, cartDTO);
		model.addAttribute("rtncode", rtn.get("rtn"));
		if(((Integer)rtn.get("rtn")).intValue() > 0){
			model.addAttribute("msg", "success");
		}else{
			model.addAttribute("msg", "false");
			model.addAttribute("errMsg", messageSourceAccessor.getMessage("cart.err.inv", getLocaleService().getCurrentLocale(request)));
		}
	}
	
	

	/**
	 * [장바구니 상품 수량 변경].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param bskGod [설명]
	 * @param model [설명]
	 * @param request [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
	@RequestMapping(value = "/changeQuantity.json", method = RequestMethod.POST, produces = { "application/json" })
	public void changeCartGoodsQuantity(@RequestBody BskGodExtend bskGod,Model model, HttpServletRequest request) throws Exception {

		log.debug("{}",  request.getRequestURI());
		log.debug("{}",  bskGod);
		
		CartDTO cartDTO = new CartDTO();
		cartDTO.setGod(bskGod);

		// 예약장바구니 기능 추가  : by cannon (2016.04.18)
		if("RSV".equals(bskGod.getDlvSectCd())){
			cartDTO.setResveSaleGodYn("Y");
		}else{
			cartDTO.setResveSaleGodYn("N");
		}

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		HashMap<String,Integer> result = null;
		result =  cartCommandComponent.updateGoodsQty(pk,cartDTO);
		model.addAttribute("rtncode", result.get("rtn"));
		if(result.get("rtn") > 0){
			model.addAttribute("msg", "success");
		}else{
			model.addAttribute("msg", "false");
			model.addAttribute("errMsg", messageSourceAccessor.getMessage("cart.err.inv", getLocaleService().getCurrentLocale(request)));
		}
	}

	/**
	 * [장바구니 상품 삭제].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
	@RequestMapping(value = "/delete.json", method = RequestMethod.POST, produces = { "application/json" })
	public void deleteCartGoods(@RequestBody CartSearchDTO cartSearchDTO,Model model, HttpServletRequest request) throws Exception {
		log.debug("{}",  request.getRequestURI());
		log.debug("cartobject {}",  cartSearchDTO.toJSON());
		
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);

		cartCommandComponent.deleteCartGoods(systemPK,cartSearchDTO);
	}

	/**
	 * [상품 옵션 변경].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @param model [설명]
	 * @param request [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 18
	 */
	@RequestMapping(value = "/option/update.json", method = RequestMethod.POST, produces = { "application/json" })
	public void updateBskGodOption(@ModelAttribute CartSearchDTO cartSearchDTO,Model model, HttpServletRequest request) throws Exception {
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);

		boolean rtn = cartCommandComponent.updateBskGodOption(systemPK,cartSearchDTO);

		if(rtn){
			model.addAttribute("msg", "success");
		}else{
			model.addAttribute("msg", "false");
			model.addAttribute("errMsg", messageSourceAccessor.getMessage("cart.err.inv", getLocaleService().getCurrentLocale(request)));
		}
	}

	/**
	 * [패키지형 상품 옵션 변경].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartDTO [설명]
	 * @param model [설명]
	 * @param request [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 18
	 */
	@RequestMapping(value = "/pckageoption/update.json", method = RequestMethod.POST, produces = { "application/json" })
	public void updateBskPckageGodOption(@ModelAttribute CartGodOptionDTO cartDTO,Model model, HttpServletRequest request) throws Exception {


		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);

		boolean rtn = cartCommandComponent.updateBskPckageGodOption(systemPK,cartDTO);

		if(rtn){
			model.addAttribute("msg", "success");
		}else{
			model.addAttribute("msg", "false");
			model.addAttribute("errMsg", messageSourceAccessor.getMessage("cart.err.inv", getLocaleService().getCurrentLocale(request)));
		}

	}

	/**
	 * [배송 유형 변경].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @param model [설명]
	 * @param request [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 18
	 */
//	@RequestMapping(value = "/dlv/update", method = RequestMethod.POST, produces = { "application/json" })
//	public void updateCartDlv(@ModelAttribute("cartSearchDTO") CartSearchDTO cartSearchDTO,Model model, HttpServletRequest request) throws Exception {
//
//		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
//
//		cartSearchDTO.setDlvSectCd("PKUP_DLV"); //매장픽업인 경우만 들어오므로 강제 픽업매장 셋
//
//		boolean rtn = cartCommandComponent.updateCartDlv(systemPK,cartSearchDTO);
//
//		if(rtn){
//			model.addAttribute("msg", "success");
//		}else{
//			model.addAttribute("msg", "fail");
//			model.addAttribute("errMsg", messageSourceAccessor.getMessage("cart.err.itmStat", getLocaleService().getCurrentLocale(request)));
//		}
//
//		//return "redirect:/public/cart/list";
//	}

	

	/**
	 * [픽업매장 변경].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @param model [설명]
	 * @param request [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 18
	 */
	@RequestMapping(value = "/shop/update.json", method = RequestMethod.POST ,produces = { "application/json" })
	public void updatePkupShop(@ModelAttribute("cartSearchDTO") CartSearchDTO cartSearchDTO,Model model, HttpServletRequest request) throws Exception {
		//SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		cartCommandComponent.updatePkupShop(cartSearchDTO);
		model.addAttribute("msg", "success");

	}
	

	
	
	/*
	 * [주문 대상 상품 유효성 체크].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @param model [설명]
	 * @param request [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	@RequestMapping(value = "/orderCheck.json", method = RequestMethod.POST, consumes = {"application/json"}, produces = { "application/json" })
	public void orderCheck(@RequestBody CartSearchDTO cartSearchDTO,Model model, HttpServletRequest request) throws Exception{

		log.debug("{}",  request.getRequestURI());
		log.debug("cartobject {}",  cartSearchDTO.toJSON());
		
		
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);


		CartOrderCheckResult cartOrderCheckResult = cartSelectComponent.isValidOrderGod(systemPK,cartSearchDTO);
		
		if(!cartOrderCheckResult.getResult()){
			List<CartResult> cartList = cartSelectComponent.selectInvalidOrderGod(systemPK,cartSearchDTO);
			model.addAttribute("cartList",cartList);
		}

		model.addAttribute("isValidOrdGod",cartOrderCheckResult.getResult());
		model.addAttribute("cpnCnt",cartOrderCheckResult.getCpnCnt());

		model.addAttribute("type",cartOrderCheckResult.getResultType());

	}
	
	/**
	 * [주문 페이지 이동].
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param cartSearchDTO [설명]
	 * @param model [설명]
	 * @param request [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 18
	 */
	@RequestMapping(value = "/order.page.json", method = RequestMethod.POST)
	public void orderPage(@RequestBody CartSearchDTO cartSearchDTO,Model model, HttpServletRequest request) throws Exception {
		
		log.info("1. order.page cartSearchDTO : {}",cartSearchDTO.toJSON());
		
		if(CollectionService.isEmpty(cartSearchDTO.getBskGodList())){
			HttpSession session = ContextService.getCurrentRequest().getSession();
			cartSearchDTO = (CartSearchDTO) session.getAttribute("SESSION_KEY_CART_GOODS");

			if(CollectionService.isEmpty(cartSearchDTO.getBskGodList())){
				throw new Exception("선택된 상품이 없습니다.");
			}
		}

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

		cartSearchDTO.setBsk(cartCPSRepository.selectBskInfoByBskNo(cartSearchDTO.getBskGodList().get(0).getBskNo()));
		cartSearchDTO.setMallId(pk.getMall());
		cartSearchDTO.setDevice(pk.getDevice());
		cartSearchDTO.setLang(pk.getLang());
		

		int cartCnt = cartSelectComponent.selectMbrCartCnt(pk);
		// 조회된 데이터를 세션에 저장
		Map<String, String> storeData = new HashMap<String, String>();
		storeData.put("cartCnt", cartCnt + "");
		HttpSession session = request.getSession();
		session.setAttribute("CART_COUNT_MYALRAM_COUNT", storeData);
		
		ContextService.getCurrentRequest().getSession().setAttribute("SESSION_KEY_CART_GOODS", cartSearchDTO);
		
		log.info("2. order.page cartSearchDTO : {}",cartSearchDTO.toJSON());
		
		model.addAttribute("redirectUrl","/order/orderform/new");
				
				 
		
	}

	@RequestMapping(value = "/gnblist.json", produces = { "application/json" })
	public void gnbListAjax(Model model,@ModelAttribute CartDTO cartDTO, HttpServletRequest request) throws Exception{
		log.debug("{}",  request.getRequestURI());
		log.debug("{}",  cartDTO);
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		
		
		List<CartSimpleListResult> result = cartCommandComponent.getMiniCartList(systemPK,cartDTO);


		model.addAttribute("cart", result);
		
	}
	

	/**
	 * [가격변경 장바구니 추가].
	 *
	 */
	@RequestMapping(value = "/add/reorder.json", method = RequestMethod.POST, produces = { "application/json" })
	public void addCartReOrder(@ModelAttribute ReOrderCartDTO reOrderCartDTO, Model model, HttpServletRequest request) throws Exception {

		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		try{
			cartCommandComponent.addReOrderCartList(pk, reOrderCartDTO);
			model.addAttribute("code", "1");
			model.addAttribute("msg", "success");
		}catch(RuntimeException e){
			model.addAttribute("code", "-1");
			model.addAttribute("msg", e.getMessage());
		}
	}


	
}
