package com.plgrim.ncp.web.pc.mlb.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plgrim.ncp.base.abstracts.AbstractController;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.biz.member.service.MemberBenefitSelectService;
import com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartSelectComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.order.OrderCommandComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.order.OrderSelectComponent;
import com.plgrim.ncp.cmp.promotion.fo.PromotionCouponFOComponent;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.framework.commons.ContextService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/order")
public abstract class OrderController extends AbstractController {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	@Qualifier("orderCommandComponentImpl")
	protected OrderCommandComponent orderCommandComponent;

	

	@Autowired
	@Qualifier("orderSelectComponentImpl")
	protected OrderSelectComponent orderSelectComponent;

	@Autowired
	@Qualifier("cartSelectComponentImpl")
	protected CartSelectComponent cartSelectComponent;

	@Autowired
	@Qualifier("commonSelectComponentImpl")
	protected CommonSelectComponent commonSelectComponent;
	
	@Autowired
	PromotionCouponFOComponent promotionCouponFOComponent;
	
	@Autowired
	MemberBenefitSelectService memberBenefitSelectService;
	

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	@InitBinder
	public void initOrder(HttpServletRequest request, HttpSession session) throws Exception {
		// SystemPK systemPK =
		// commonSelectComponent.getAutoGeneratorSystemPK(request);

		// log.info("######### getChannel : " + systemPK.getChannel());
		// log.info("######### getDevice : " + systemPK.getDevice());
		// log.info("######### getLang : " + systemPK.getLang());
		// log.info("######### getMall : " + systemPK.getMall());
	}
	
	protected void removeSession(boolean deleteAll) {
        HttpSession session = ContextService.getCurrentRequest().getSession();
        if (deleteAll) {
            session.removeAttribute("SESSION_KEY_CART_GOODS");
            session.removeAttribute("SESSION_KEY_COUPON_LIST");
            session.removeAttribute(SessionEnum.PCC.toString());
            session.removeAttribute(SessionEnum.IPIN.toString());
        }
        session.removeAttribute("SESSION_KEY_CART_RESULTS");
        session.removeAttribute("SESSION_KEY_ORD_GIFTS");
    }
}
