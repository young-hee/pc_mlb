package com.plgrim.ncp.cmp.orderfulfilment.fo.order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvZoneNationExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.pay.PayExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.member.result.MypageOrderRtExchgPrmResult;
import com.plgrim.ncp.biz.order.data.ComOvseaDlvZoneChrgeDTO;
import com.plgrim.ncp.biz.order.data.MbrDelvSearchDTO;
import com.plgrim.ncp.biz.order.data.MypageOrderRtExchgPrmSearchDTO;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.data.OrderFormDTO;
import com.plgrim.ncp.biz.order.data.OrderParamDTO;
import com.plgrim.ncp.biz.order.data.SysExchgRtDTO;
import com.plgrim.ncp.biz.order.result.OrdGodForRtnClmResult;
import com.plgrim.ncp.biz.order.result.OrderCompleteResult;
import com.plgrim.ncp.biz.order.result.OrderGoodWrapResult;
import com.plgrim.ncp.biz.order.result.OrderLinkPriceResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

@Component
public interface OrderSelectComponent {

	// //////// FRONT START /////////////////////////////
	/**
	 * 사은품리스트 조회 (객체 내 사은품 리스트 추가함)
	 * 
	 * @param systemPK
	 * @param cartList
	 * @throws Exception
	 */
	void selectGiftList(SystemPK systemPK, List<String> cartList) throws Exception;

	/**
	 * 주문서 페이지 정보 조회
	 * 
	 * @param cartSearchDTO
	 * @return
	 * @throws Exception
	 */
	OrderFormDTO newOrder(CartSearchDTO cartSearchDTO) throws Exception;

	/**
	 * 주문완료페이지 정보 조회
	 * 
	 * @param ordNo
	 * @return
	 * @throws Exception
	 */
	OrderCompleteResult viewOrderComplete(OrderParamDTO ord) throws Exception;

	/**
	 * 배송지목록 조회(회원배송지목록 or 최근주문배송지목록)
	 * 
	 * @param mbrDelvSearchDTO
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	Page<MbrDlvsp> selectOrderDeliveryList(MbrDelvSearchDTO mbrDelvSearchDTO, PageParam pageParam) throws Exception;

	/**
	 * 매장정보조회
	 * 
	 * @param shopId
	 * @return
	 * @throws Exception
	 */
	SysShopExtends selectSysShop(String shopId) throws Exception;
	
	/**
	 * linkPrice 전송대상 조회
	 * 
	 * @param ordNo
	 * @return
	 * @throws Exception
	 */
	List<OrderLinkPriceResult> selectOrderLinkPriceInfo(String ordNo) throws Exception;
	// //////// FRONT END /////////////////////////////

	/**
	 * 통화코드를 셋팅해서 콜하면 현제 환율정보를 리턴한다.
	 * @param sysExchgRtDTO
	 * @return
	 */
	SysExchgRtDTO selectCurrentExchangeRt(String crncyCd) throws Exception;
	
	/**
	 * 통화코드를 셋팅해서 콜하면 현제 환율정보를 리턴한다.
	 * @param sysExchgRtDTO
	 * @return
	 */
	SysExchgRtDTO selectCurrentExchangeRt(SystemPK systemPK) throws Exception;
	
	

	/**
	 * selectCurrentExchangeRt 를 통해서 환율정보를 구한 후
	 * 환율 적용을 원하는 가격정보를 oriAmt 에 셋팅해주면
	 * exAmt컬럼에 환율 적용된 가격을 리턴해준다.
	 * 
	 * 반환된 환율금은 소수점 2자리 이하는 버림처리된다.
	 * 
	 * @param sysExchgRtDTO
	 * @return
	 */
	String applyExchangeRt(SysExchgRtDTO sysExchgRtDTO) throws Exception;
	
	
	/**
	 * 받은 환율 정보로 적용대상금액을 환율적용해준다.
	 * 
	 * @param sysExchgRtDTO 환율 적용 대상에 대하여 oriAmt에 대한 환율 적용가격은 exAmt로 설정함 
	 * @param truncFlag 환율에 계산시 소수점 2자리에서 내림처리 값으로, true는 내림처리, false는 올림처리함.
	 * @return
	 * @throws Exception
	 */
	public String applyExchangeRt(SysExchgRtDTO sysExchgRtDTO, boolean truncFlag)  throws Exception;
	
	/**
	 * zone 과 무게 해외배송비정책번호로 배송비 정보를 구해온다.
	 * @param comOvseaDlvZoneChrge
	 * @return
	 */
	ComOvseaDlvZoneChrgeDTO selectOvseaDlvFee(ComOvseaDlvZoneChrgeDTO comOvseaDlvZoneChrge);
/**
	 * 해외 국내배송비정책번호로 배송비 정보를 구해온다.
	 * @param comOvseaDlvZoneChrge
	 * @return
	 */
	String selectDmstcDlvFee(String ordNo) throws Exception;;

	/**
	 * zone 과 해외배송비정책번호로 국가별 배송비 정책 정보를 구해온다.
	 * 쇼핑백 조회용
	 * @param comOvseaDlvZoneChrge
	 * @return
	 */
	List<ComOvseaDlvZoneChrgeDTO> selectOvseaDlvZoneChrgeList(ComOvseaDlvZoneChrgeDTO comOvseaDlvZoneChrge);
	

	/**
	 * 글로벌 주문 환율 적용을 위한 국가정보를 리턴한다.
	 * @param sysExchgRtDTO
	 * @return
	 * @throws Exception
	 */
	List<ComOvseaDlvZoneNationExtend> selectNationInfo(ComOvseaDlvZoneNationExtend comOvseaDlvZoneNationExtend) throws Exception;

	/**
	 * payno 로 pay를 조회
	 * @param result
	 * @return
	 */
	Pay selectPayByPayNo(String result);
	
	/**
	 * 주문 상품 반품/교환 쿠폰 정보 조회
	 * 
	 * @param search
	 * @return
	 * @throws Exception
	 */
	public List<MypageOrderRtExchgPrmResult> getOrderGoodsRtExchgPromotionInfoList(MypageOrderRtExchgPrmSearchDTO search) throws Exception;

	/**
	 * 선물포장 대상 상품 조회
	 * 
	 * @param goodsWrapDTO
	 * @return
	 */
	// TODO	상품재작업필요 : 
	List<OrderGoodWrapResult> selectGoodsWrap();

	/**
	 * 우편번호에 대한 퀵배송 가능지역 개수 조회
	 * 
	 * @param postNo
	 * @return
	 */
	int selectQdlvComArea(String postNo);

	/**
	 * 주문서 내 퀵배송 가능지역 검색
	 * 
	 * @param ordNo
	 * @return
	 */
    LgsDlvsp selectLgsDlvspOfQdlvOrder(String ordNo);
    
	/**
	 * [FO] 배송조회.
	 *
	 * @param systemPK [설명]
	 * @param deliverySearchDTO [설명]
	 * @return the delivery list
	 * @throws Exception the exception
	 * @since 2017. 9. 29
	 */
	public List<DeliveryOrderGoodResult> getDeliveryListFO(SystemPK systemPK, DeliverySearchDTO deliverySearchDTO) throws Exception;

	/**
	 * 주문번호로 주문 엔티티를 반환한다.
	 * 
	 * @param ordNo
	 * @return
	 * @throws Exception
	 */
	public Ord selectOrdEntity(String ordNo) ;
	
	/**
	 * 주문상품정보 조회
	 * (BO service에 있는 부분을 사용하던 걸 FO에서도 사용하므로 위치 변경함.)
	 * 
	 * @param orderDTO
	 * @return
	 */
	public List<OrdGodExtend> selectCoOrdGodList(OrderBoDTO orderDTO);
	
	/**
	 * 주문의 마지막 클레임 정보 조회
	 * 
	 * @param pay
	 * @return
	 */
	public PayExtend selectLastClmOfOrd(Pay pay);
	
	/**
	 * 주문 - 부분취소접수 클레임접수기본정보조회[부분취소접수-물류배송지별]
	 */
	public List<OrdGodForRtnClmResult> selectOrdGodPartCancelClmWithGft(SystemPK systemPK, OrderBoDTO orderDTO) throws Exception;
}
