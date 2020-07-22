package com.plgrim.ncp.cmp.orderfulfilment.fo.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtends;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.biz.order.data.OrderCheckResult;
import com.plgrim.ncp.biz.order.data.OrderDTO;
import com.plgrim.ncp.biz.order.data.SmsRecptnSectDTO;
import com.plgrim.ncp.biz.order.result.OrderCouponResult;
import com.plgrim.ncp.commons.data.order.KcpCommonReceiveDTO;
import com.plgrim.ncp.framework.data.SystemPK;

public interface OrderCommandComponent {





    ////////// FRONT START /////////////////////////////
	/**
	 * 주문결제 처리
	 * 
	 * @param orderDTO
	 * @param request
	 * @throws Exception
	 */
    void addOrder(OrderDTO orderDTO, HttpServletRequest request) throws Exception;

    /**
     * 결제번호 채번
     * 
     * @return
     * @throws Exception
     */
    String selectPayNumber() throws Exception;

    /**
     * 주문쿠폰리스트 조회
     * 
     * @param cartSearchDTO
     * @return
     * @throws Exception
     */
    List<OrderCouponResult> selectOrderCouponList(CartSearchDTO cartSearchDTO) throws Exception;

	/**
	 * 전용 가상계좌 입금 결과 수신
	 * 
	 * @param virtlAccChkDTO
	 * @param host
	 * @param addr
	 * @return
	 * @throws Exception
	 */
    public String setVirtualAccountDeposit(KcpCommonReceiveDTO kcpCommonReceiveDTO,OrderDTO orderDTO,String host, String addr) throws Exception;

    ////////// FRONT END /////////////////////////////



    // ################################################################################################################
    // 마이페이지 결제 모듈 hongsub.lim START
    // ################################################################################################################

    /**
     * 대량주문,전화주문 결제
     * 
     * @param orderDTO
     * @param request
     * @throws Exception
     */
    public void updaePayMethodChange(OrderDTO orderDTO, HttpServletRequest request) throws Exception ;

    /**
     * FO가상계좌 결제수단 변경
     * 
     * @param orderDTO
     * @param request
     * @throws Exception
     */
    public void rerunPayMethodChange(OrderDTO orderDTO, HttpServletRequest request) throws Exception ;

    /**
     * 마이페이지 결제번호 채번
     * 
     * @param mypageOrderInfoDTO
     * @param request
     * @return
     * @throws Exception
     */
    public String getNewPayNoForMypagePayChange(MypageOrderInfoDTO mypageOrderInfoDTO , HttpServletRequest request) throws Exception;

    // ################################################################################################################
    // 마이페이지 결제 모듈 hongsub.lim END
    // ################################################################################################################
    /**
     * SMS 수신구분 설정
     * 
     * @param smsRecptnSectDTO
     * @param request
     * @throws Exception
     */
    public void setSmsRecptnSect(SmsRecptnSectDTO smsRecptnSectDTO, HttpServletRequest request) throws Exception;

    /**
     * 주문상품 재고 상태 체크
     * 
     * @param ordTpCd
     * @param ordGodExtends
     * @param pickupShopId
     * @param isGift
     * @param locale
     * @throws Exception
     */
    public void checkOrdGodStatus(String ordTpCd, OrdGodExtends ordGodExtends, String pickupShopId, boolean isGift, Locale locale) throws Exception;

    /**
     * 주문상품 재고 상태 체크
     * @param cartSearchDTO
     * @param locale
     * @throws Exception
     */
    public void checkOrdGodQtyStatus(CartSearchDTO cartSearchDTO, Locale locale) throws Exception;

    
    /**
     * [FO] 배송상태변경
     *
     * <p/>
     *
     * [사용 방법 설명].
     *
     * @param systemPK [설명]
     * @param listDTO [설명]
     * @return true:[설명], false:[설명]
     * @throws Exception the exception
     * @since 2017. 9. 27
     */
    public int updateDeliveryStatusFO(SystemPK systemPK, List<DeliveryOrderGoodResult> listDTO)  throws Exception;
    
    /**
	 * 구매확정 처리 - 상품단위
	 * 
	 * @param mypageOrderInfoDTO
	 */
	public void updateOrderDecision(MypageOrderInfoDTO mypageOrderInfoDTO, SystemPK pk);
	
	/**
	 * 구매확정 처리 - 복수상품
	 * 
	 * @param mypageOrderInfoDTO
	 */
	public void updateOrderDecision(List<OrdGod> ordGod);
	
	public OrderCheckResult checkOrder(OrderDTO orderDTO, HttpServletRequest request) throws Exception;
	
	/**
	 * PG 승인처리
	 * 
	 * @param orderDTO
	 * @param bSumPayAmt
	 * @param ordNo
	 * @throws Exception
	 */
	public void approvePG(OrderDTO orderDTO, BigDecimal bSumPayAmt, String ordNo) throws Exception;
	
	/**
	 * PG 승인취소
	 * 
	 * @param orderDTO
	 */
	public void cancelApprovePG(OrderDTO orderDTO);
	
	public void sendOrderMail(OrderDTO orderDTO, HttpServletRequest request);
	
	public void sendVirOrderMail(OrderDTO orderDTO, HttpServletRequest request);
}
