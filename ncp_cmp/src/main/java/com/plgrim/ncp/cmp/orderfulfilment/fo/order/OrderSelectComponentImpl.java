/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      syvictor.kim
 * @since       2015. 4. 3
 */
package com.plgrim.ncp.cmp.orderfulfilment.fo.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvZoneNationExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPreferPayMn;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.pay.PayExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrndExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.cart.result.CartGiftResult;
import com.plgrim.ncp.biz.cart.result.CartListResult;
import com.plgrim.ncp.biz.cart.result.CartResult;
import com.plgrim.ncp.biz.cart.service.CartSelectService;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.delivery.service.DeliveryListService;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MypageOrderRtExchgPrmResult;
import com.plgrim.ncp.biz.order.data.ComOvseaDlvZoneChrgeDTO;
import com.plgrim.ncp.biz.order.data.MbrDelvSearchDTO;
import com.plgrim.ncp.biz.order.data.MypageOrderRtExchgPrmSearchDTO;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.data.OrderFormDTO;
import com.plgrim.ncp.biz.order.data.OrderParamDTO;
import com.plgrim.ncp.biz.order.data.SysExchgRtDTO;
import com.plgrim.ncp.biz.order.exception.OrderFailException;
import com.plgrim.ncp.biz.order.result.OrdGodForRtnClmResult;
import com.plgrim.ncp.biz.order.result.OrderCompleteResult;
import com.plgrim.ncp.biz.order.result.OrderGoodWrapResult;
import com.plgrim.ncp.biz.order.result.OrderLinkPriceResult;
import com.plgrim.ncp.biz.order.service.OrderSelectService;
import com.plgrim.ncp.framework.commons.CollectionService;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;

/**
 * [클래스 설명]
 *
 * <p>
 *
 * <ul>
 * <li>[기능1]
 * <li>[기능2]
 * </ul>
 * .
 * 
 * @author syvictor.kim
 * @since 2015. 4. 3
 */
@Slf4j
@Component
public class OrderSelectComponentImpl extends AbstractComponent implements OrderSelectComponent {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */
    @Autowired
    CartSelectService cartSelectService;

    @Autowired
    OrderSelectService orderSelectService;
    
    @Autowired
    DeliveryListService deliveryListService;

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
    // ////////// FRONT START /////////////////////////////////////////////
    @Override
    public void selectGiftList(SystemPK systemPK, List<String> cartList) throws Exception {
        List<BskGod> bskGodList = new ArrayList<BskGod>();
        for (String row : cartList) {
            String[] column = StringService.split(row, "|");

            BskGod bskGod = new BskGod();
            bskGod.setBskNo(column[0]);
            bskGod.setGodTurn(Integer.parseInt(column[1]));

            bskGodList.add(bskGod);
        }
    }

    @Override
    public OrderFormDTO newOrder(CartSearchDTO cartSearchDTO) throws Exception {
        OrderFormDTO orderFormDTO = new OrderFormDTO();

        // 임직원 필터
        if("Y".equals(cartSearchDTO.getEmpYn())){
			if (ContextService.hasRole()) {
				SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
				if(!MemberEnum.MemberAtrbCd.EMP.toString().equals(userDetail.getMbr().getMbrAtrbCd())){
					throw new OrderFailException("ord.error.not.emp.order",null);
				}else{
					//쿠폰 유무
				}
			}
		}
        log.debug("cartSearchDTO {}",cartSearchDTO.toJSON());
        
		// 1. selectCartList
		cartSearchDTO.setCrncyCd(getCrncyCd(cartSearchDTO.getLang())); // 통화코드
        CartListResult cartGroupList = cartSelectService.selectCartGodList(cartSearchDTO);
        
        
        List<CartResult> cartResultList = cartGroupList.getCartList().get(0);
        orderFormDTO.setCartResultList(cartResultList);
        
        // 2. get pickup delivery
		if ("PKUP_DLV".equals(cartSearchDTO.getDlvSectCd())) {
			String shopId = cartResultList.get(0).getBskGod().getPkupShopSn();
			orderFormDTO.setSysShop(this.selectSysShop(shopId));
		}

		// 3. getMemberDelivery
		if (!StringService.isEmpty(cartSearchDTO.getMbr().getMbrNo())) {
			MbrDlvspExtend mbrDlvsp = new MbrDlvspExtend();
			mbrDlvsp.setMbrNo(cartSearchDTO.getMbr().getMbrNo());
			mbrDlvsp.setLang(cartSearchDTO.getLang());
			mbrDlvsp.setBaseDlvspYn("Y");

			orderFormDTO.setMbrDlvsp(orderSelectService.selectMemberDelivery(mbrDlvsp));

			MbrDelvSearchDTO mbrDelvSearchDTO = new MbrDelvSearchDTO();
			mbrDelvSearchDTO.setLang(cartSearchDTO.getLang());
			mbrDelvSearchDTO.setMallId(cartSearchDTO.getMallId());
			mbrDelvSearchDTO.setDevice(cartSearchDTO.getDevice());
			mbrDelvSearchDTO.setMbrNo(mbrDlvsp.getMbrNo());

			if (orderSelectService.selectMemberDeliveryListCount(mbrDelvSearchDTO) > 0L) {
				orderFormDTO.setSearchDelv("member"); // 회원주소록
			}
			else if (orderSelectService.selectOrderDeliveryListCount(mbrDelvSearchDTO) > 0L) {
				orderFormDTO.setSearchDelv("delv"); // 최근배송지
			}
		}
		else {
			orderFormDTO.setMbrDlvsp(new MbrDlvspExtend());
		}

		// 4. order gifts
		List<CartGiftResult> ordGiftPrmList = cartGroupList.getOrdGiftList();
		if (!CollectionService.isEmpty(ordGiftPrmList)) {
			// List<OrderGiftDTO> list = new ArrayList<OrderGiftDTO>();
			// for (CartGodPrmResult ordGiftPrm : ordGiftPrmList) {
			// OrderGiftDTO giftDTO =
			// orderSelectService.selectOrderGiftList(ordGiftPrm.getPrm().getPrmNo());
			// giftDTO.setCartGodNo(ordGiftPrm.getGodNo());
			//
			// list.add(giftDTO);
			// }
			// orderFormDTO.setOrderGiftList(ordGiftPrmList);
			orderFormDTO.setOrdGiftPrmList(ordGiftPrmList);
		}

		// 5. credit card benefits
		// TODO	상품재작업필요 : orderFormDTO.setCardBenefitList(goodsEtcSelectService.selectCardBenefitList());
		
		// 6. 배송비즉시할인쿠폰 : by cannon (2016.06.07)
		orderFormDTO.setDlvFeeImdtlCpnResultList(cartGroupList.getDlvFeeImdtlCpnResultList());
		
		// 7. 회원선호결제수단 : UXUI개선
		if (!StringService.isEmpty(cartSearchDTO.getMbr().getMbrNo())) {
			MbrPreferPayMn mbrPreferPayMnSearchDTO = new MbrPreferPayMn();
			mbrPreferPayMnSearchDTO.setMallId(cartSearchDTO.getMallId());
			mbrPreferPayMnSearchDTO.setLangCd(cartSearchDTO.getLang());
			mbrPreferPayMnSearchDTO.setMbrNo(cartSearchDTO.getMbr().getMbrNo());
			
			MbrPreferPayMn mbrPreferPayMnResultDTO = orderSelectService.selectMbrPreferPayMn(mbrPreferPayMnSearchDTO);
			if(mbrPreferPayMnResultDTO != null){
				orderFormDTO.setMbrPreferPayMn(mbrPreferPayMnResultDTO.getPayMnCd());
			}
		}
		
		return orderFormDTO;
	}

	public SysShopExtends selectSysShop(String shopId) throws Exception {
		SysShopBrndExtend sysShopBrnd = new SysShopBrndExtend();
		sysShopBrnd.setShopId(shopId);
		return orderSelectService.selectSysShop(sysShopBrnd);
	}

	public OrderCompleteResult viewOrderComplete(OrderParamDTO ord) throws Exception {
		return orderSelectService.selectOrderCompleteResult(ord);
	}

	/**
	 * 배송지목록 조회(회원배송지목록 or 최근주문배송지목록)
	 * @param mbrDelvSearchDTO MbrDelvSearchDTO
	 * @param pageParam PageParam
	 * @return Page<MbrDlvsp>
	 * @throws Exception
	 */
	@Override
	public Page<MbrDlvsp> selectOrderDeliveryList(MbrDelvSearchDTO mbrDelvSearchDTO, PageParam pageParam) throws Exception {
		String mbrNo = null;
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbrNo = userDetail.getMbr().getMbrNo();
		}

		if (mbrNo != null) {
			mbrDelvSearchDTO.setMbrNo(mbrNo);
			if ("member".equals(StringService.nvl(mbrDelvSearchDTO.getDelvFlag(), "member"))) {
				return orderSelectService.selectMemberDeliveryList(mbrDelvSearchDTO, pageParam);
			}
			else {
				return orderSelectService.selectOrderDeliveryList(mbrDelvSearchDTO, pageParam);
			}
		}
		else {
			return null;
		}
	}

	public List<OrderLinkPriceResult> selectOrderLinkPriceInfo(String ordNo) throws Exception {
		return orderSelectService.selectOrderLinkPriceInfo(ordNo);
	}

	/**
	 * 통화코드로 현제의 환율을 구한다.
	 */
	@Override
	public SysExchgRtDTO selectCurrentExchangeRt(String crncyCd) throws Exception {
		return orderSelectService.selectExchangeRt(crncyCd);
	}

	/**
	 * 통화코드로 현제의 환율을 구한다. 위에꺼 오버로딩
	 */
	@Override
	public SysExchgRtDTO selectCurrentExchangeRt(SystemPK systemPK) throws Exception {
		return orderSelectService.selectExchangeRt(systemPK);
	}

	/**
	 * 받은 환율 정보로 적용대상금액을 환율적용해준다.
	 */
	@Override
	public String applyExchangeRt(SysExchgRtDTO sysExchgRtDTO) throws Exception {

		return applyExchangeRt(sysExchgRtDTO, true);
	}

	/**
	 * 받은 환율 정보로 적용대상금액을 환율적용해준다.
	 */
	@Override
	public String applyExchangeRt(SysExchgRtDTO sysExchgRtDTO, boolean truncFlag) throws Exception {

		// 2016-01-28 회의에서 정해짐.
		// 반올림할 자리수 소수점 2자리에서 올림 한다. (상품 할인, 쿠폰할인 등 최종계산된 금액에 내림한다.)
		double dPoint = 100;
		// 환율 외화금액
		double exAmt = Double.parseDouble(sysExchgRtDTO.getExchgRtCrncyAmt().toString());
		// 환율 한화금액
		double stAmt = Double.parseDouble(sysExchgRtDTO.getStdrCrncyAmt().toString());

		// 적용 대상금액
		double target = Double.parseDouble(sysExchgRtDTO.getOriAmt());

		double result = 0;
		
		if (truncFlag) {
			// 환율 적용 후 금액. 1.005, 1.001, 1.009는 모두 1.00로 처리함.
			result = Math.floor((target / stAmt * exAmt) * dPoint) / dPoint;
		} else {
			// 환율 적용 후 금액. 1.005, 1.001, 1.009 모두 1.01로 처리함.
			 result = Math.ceil((target / stAmt * exAmt) * dPoint) / dPoint;
		}

		return result + "";
	}

	/**
	 * zone 무게 해외배송비정책번호로 배송비 정보를 구한다.
	 */
	@Override
	public ComOvseaDlvZoneChrgeDTO selectOvseaDlvFee(ComOvseaDlvZoneChrgeDTO comOvseaDlvZoneChrge) {

		return orderSelectService.selectOvseaDlvFee(comOvseaDlvZoneChrge);
	}

	/**
	 * zone 과 해외배송비정책번호로 국가별 배송비 정책 정보를 구해온다.
	 */
	@Override
	public List<ComOvseaDlvZoneChrgeDTO> selectOvseaDlvZoneChrgeList(ComOvseaDlvZoneChrgeDTO comOvseaDlvZoneChrge) {

		return orderSelectService.selectOvseaDlvZoneChrgeList(comOvseaDlvZoneChrge);
	}

	/**
	 * 글로벌 주문 환율 적용을 위한 국가정보를 리턴한다.
	 */
	@Override
	public List<ComOvseaDlvZoneNationExtend> selectNationInfo(ComOvseaDlvZoneNationExtend comOvseaDlvZoneNationExtend) throws Exception {
		return orderSelectService.selectNationInfo(comOvseaDlvZoneNationExtend);
	}
	   
    /**
	 * 주문 상품 반품/교환 쿠폰 정보 조회
	 */
    @Override
    public List<MypageOrderRtExchgPrmResult> getOrderGoodsRtExchgPromotionInfoList(MypageOrderRtExchgPrmSearchDTO search) throws Exception {
		return orderSelectService.getOrderGoodsRtExchgPromotionInfoList(search);
	}

	/**
	 * 장바구니에 들어있는 상품중 포장서비스 대상 조회
	 */
    @Override
    public List<OrderGoodWrapResult> selectGoodsWrap() {

    	List<OrderGoodWrapResult> result = null;

        return result;
    }

	@Override
	public int selectQdlvComArea(String postNo) {

    	int postNoCount = 0;

		if(postNo.length() > 5){

			String splitPostNo = "";
			if(postNo.indexOf("-") > -1){
				splitPostNo = postNo.split("-")[0];
			}else{
				splitPostNo = postNo.substring(0, 3);
			}

			//String splitPostNo = postNo.split("-")[0];
			postNoCount = orderSelectService.selectComQdlvOldArea(splitPostNo);

		}else{
			postNoCount = orderSelectService.selectQdlvComArea(postNo);
		}

		return postNoCount;


	}

    @Override
    public LgsDlvsp selectLgsDlvspOfQdlvOrder(String ordNo) {
        return orderSelectService.selectLgsDlvspOfQdlvOrder(ordNo);
    }


    //////////// FRONT END /////////////////////////////////////////////

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param langCd [설명]
	 * @return String [설명]
	 * @throws Exception the exception
	 * @since 2015. 6. 19
	 */
	private String getCrncyCd(String langCd) throws Exception {

		String crncyCd = "KRW";
		if (!StringService.isEmpty(langCd)) {
			if ("CHI".equals(langCd)) {
				crncyCd = "CNY";
			}
			else if ("ENG".equals(langCd)) {
				crncyCd = "USD";
			}
			else if ("JPN".equals(langCd)) {
				crncyCd = "JPY";
			}
		}

		return crncyCd;
	}

	public String selectDmstcDlvFee(String ordNo) throws Exception {
		return orderSelectService.selectDmstcDlvFee(ordNo);
	}

	public String getInterfaceOrder(String payNo) throws Exception {
		return orderSelectService.getInterfaceOrder(payNo);
	}
	
	@Override
	public Pay selectPayByPayNo(String result) {
		return orderSelectService.selectPayByPayNo(result);
	}
	
	/** [FO] 상품 배송조회. */
	public List<DeliveryOrderGoodResult> getDeliveryListFO(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryListService.getDeliveryListFO(systemPK,deliverySearchDTO);
	}

	@Override
	public Ord selectOrdEntity(String ordNo) {

		return orderSelectService.selectOrdEntity(ordNo);
	}
	
	@Override
	public List<OrdGodExtend> selectCoOrdGodList(OrderBoDTO orderDTO) {
		return orderSelectService.selectCoOrdGodList(orderDTO);
	}
	
	@Override
	public PayExtend selectLastClmOfOrd(Pay pay){
		return orderSelectService.selectLastClmOfOrd(pay);
	}
	
	/**
	 * 주문 - 부분취소접수 클레임접수기본정보조회[부분취소접수-물류배송지별]
	 */
	@Override
	public List<OrdGodForRtnClmResult> selectOrdGodPartCancelClmWithGft(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception {
		
		return orderSelectService.selectOrdGodPartCancelClmWithGft(orderDTO);
	}
}
