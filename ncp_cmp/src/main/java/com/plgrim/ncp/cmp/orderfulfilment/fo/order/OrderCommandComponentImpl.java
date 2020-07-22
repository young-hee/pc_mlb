package com.plgrim.ncp.cmp.orderfulfilment.fo.order;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;
import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbExtends;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPreferPayMn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdCpstGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrmExtends;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtends;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodInv;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdNmbrStplatAgr;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.pay.PayPgIntrlckLog;
import com.plgrim.ncp.base.enums.BskEnum;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.MemberEnum.MemberTpCd;
import com.plgrim.ncp.base.enums.OrderEnum;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefJobCd;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefJobDetail;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefSectCd;
import com.plgrim.ncp.base.repository.ord.OrdRepository;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.cart.data.GodItmQtySearchDTO;
import com.plgrim.ncp.biz.cart.result.CartCpstGodResult;
import com.plgrim.ncp.biz.cart.result.CartGiftResult;
import com.plgrim.ncp.biz.cart.result.CartGodResult;
import com.plgrim.ncp.biz.cart.result.CartResult;
import com.plgrim.ncp.biz.cart.service.CartCommandService;
import com.plgrim.ncp.biz.cart.service.CartSelectService;
import com.plgrim.ncp.biz.claim.data.RefundPayDTO;
import com.plgrim.ncp.biz.claim.service.ClaimService;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.repository.DeliverySelectRepository;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.delivery.service.DeliveryInfErpExternalService;
import com.plgrim.ncp.biz.delivery.service.DeliveryInfErpService;
import com.plgrim.ncp.biz.delivery.service.DeliveryService;
import com.plgrim.ncp.biz.delivery.service.DeliveryStatusService;
import com.plgrim.ncp.biz.goods.data.GoodsInventoryDTO;
import com.plgrim.ncp.biz.member.data.Ipin;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.Pcc;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.service.MemberActivityCommandService;
import com.plgrim.ncp.biz.member.service.MemberActivitySelectService;
import com.plgrim.ncp.biz.member.service.MemberBaseCommandService;
import com.plgrim.ncp.biz.member.service.MemberBaseSelectService;
import com.plgrim.ncp.biz.member.service.MemberBenefitCommandService;
import com.plgrim.ncp.biz.member.service.MemberBenefitSelectService;
import com.plgrim.ncp.biz.member.service.MemberOrderCommandService;
import com.plgrim.ncp.biz.member.service.MemberOrderSelectService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoCommandService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoSelectService;
import com.plgrim.ncp.biz.order.data.ChangeOrderDTO;
import com.plgrim.ncp.biz.order.data.CouponDTO;
import com.plgrim.ncp.biz.order.data.CouponSearchDTO;
import com.plgrim.ncp.biz.order.data.LgsDlvspDTO;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.biz.order.data.OrdGodDTO;
import com.plgrim.ncp.biz.order.data.OrderCheckResult;
import com.plgrim.ncp.biz.order.data.OrderDTO;
import com.plgrim.ncp.biz.order.data.OrderParamDTO;
import com.plgrim.ncp.biz.order.data.OrderStockDTO;
import com.plgrim.ncp.biz.order.data.SmsRecptnSectDTO;
import com.plgrim.ncp.biz.order.repository.OrderCommandRepository;
import com.plgrim.ncp.biz.order.repository.OrderSelectRepository;
import com.plgrim.ncp.biz.order.result.GoodsCouponResult;
import com.plgrim.ncp.biz.order.result.OrderCompleteResult;
import com.plgrim.ncp.biz.order.result.OrderCouponResult;
import com.plgrim.ncp.biz.order.service.OrderBoSelectService;
import com.plgrim.ncp.biz.order.service.OrderBoService;
import com.plgrim.ncp.biz.order.service.OrderCommandService;
import com.plgrim.ncp.biz.order.service.OrderEntitySetService;
import com.plgrim.ncp.biz.order.service.OrderInterfaceService;
import com.plgrim.ncp.biz.order.service.OrderSelectService;
import com.plgrim.ncp.biz.pay.service.PayBoService;
import com.plgrim.ncp.cmp.member.fo.MemberBenefitFOComponent;
import com.plgrim.ncp.cmp.orderfulfilment.bo.order.OrderBoSelectComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.order.exception.OrderCompleteFailException;
import com.plgrim.ncp.cmp.product.fo.GoodsInventoryFOComponent;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.commons.data.order.KcpCancelParamDTO;
import com.plgrim.ncp.commons.data.order.KcpCommonReceiveDTO;
import com.plgrim.ncp.commons.data.order.KcpReturnDTO;
import com.plgrim.ncp.commons.data.order.NaverCancelReturnDTO;
import com.plgrim.ncp.commons.data.order.NaverReturnDTO;
import com.plgrim.ncp.commons.exception.OrderPGFailException;
import com.plgrim.ncp.commons.service.KcpPayService;
import com.plgrim.ncp.commons.service.MailHtmlReaderService;
import com.plgrim.ncp.commons.service.NaverPayService;
import com.plgrim.ncp.commons.service.SysBrandService;
import com.plgrim.ncp.commons.taglib.Functions;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.framework.commons.CollectionService;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.LocaleService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.plgrim.ncp.framework.systems.SystemContext;
import com.plgrim.ncp.interfaces.email.adapter.EmailAdapter;
import com.plgrim.ncp.interfaces.email.data.EmailHtmlSDO;
import com.plgrim.ncp.interfaces.member.data.MemberMileageInfoSDO;
import com.plgrim.ncp.interfaces.mpush.adapter.MPushAdapter;
import com.plgrim.ncp.interfaces.mpush.data.MPushAlimTalkSDO;
import com.plgrim.ncp.interfaces.order.data.OrderPaymentInfoListSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(value = "transactionManager")
@Component
public class OrderCommandComponentImpl extends AbstractComponent implements OrderCommandComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	@Autowired
	@Qualifier("commonSelectComponentImpl")
	protected CommonSelectComponent commonSelectComponent;

	@Autowired
	OrderSelectService orderSelectService;
	
	@Autowired
	OrderInterfaceService orderInterfaceService;

    @Autowired
    DeliveryStatusService deliveryStatusService;

    @Autowired
    InterfaceApiCommon interfaceApiCommon;

    @Autowired
    OrderBoService orderBoService;

    @Autowired SystemContext systemContext;

    @Autowired
    private NaverPayService naverPayService;

    @Autowired 
    private KcpPayService kcpPayService;
    
    @Autowired
    private GoodsInventoryFOComponent goodsInventoryFOComponent;
    
    @Autowired
    EmailAdapter emailAdapter;
    
    @Autowired
	MemberBenefitFOComponent memberBenefitFOComponent;

    @Autowired 
	MailHtmlReaderService mailHtmlReaderService;
    
    @Autowired
    OrdRepository ordRepository;

    @Autowired
    DeliverySelectRepository deliverySelectRepository;
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
	// //////// FRONT START /////////////////////////////////////////////

	/** The messageSourceAccessor */
	@Autowired
	MessageSourceAccessor messageSourceCmpAccessor;

	@Autowired
	OrderCommandService orderCommandService;

	@Autowired
	CartSelectService cartSelectService;

	@Autowired
	CartCommandService cartCommandService;

	@Autowired
	ClaimService claimService;

	@Autowired
	OrderEntitySetService orderEntitySetService;

	@Autowired
	PayBoService payBoService;

	@Autowired
	DeliveryService deliveryService;

	@Autowired
	DeliveryInfErpExternalService deliveryInfErpExternalService;

	@Autowired
	DeliveryInfErpService deliveryInfErpService;

	@Autowired
    OrderBoSelectService orderBoSelectService;

	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;

	@Autowired
	OrderBoSelectComponent orderBoSelectComponent;

	@Autowired
	SysBrandService sysBrandService;


	@Autowired
	OrderSelectRepository orderSelectRepository;
	
	@Autowired
	OrderCommandRepository orderCommandRepository;

    @Autowired
	MemberPersonalInfoSelectService memberPersonalInfoSelectService;

	@Autowired
	MemberPersonalInfoCommandService memberPersonalInfoCommandService;

	@Autowired
	MemberActivitySelectService memberActivitySelectService;

	@Autowired
	MemberActivityCommandService memberActivityCommandService;

	@Autowired
	MemberBaseSelectService memberBaseSelectService;

	@Autowired
	MemberBaseCommandService memberBaseCommandService;

	@Autowired
	MemberBenefitSelectService memberBenefitSelectService;

	@Autowired
	MemberBenefitCommandService memberBenefitCommandService;

	@Autowired
	MemberOrderSelectService memberOrderSelectService;

	@Autowired
	MemberOrderCommandService memberOrderCommandService;
	
	@Autowired
	MPushAdapter mPushAdapter;




	@Override
	public void addOrder(OrderDTO orderDTO, HttpServletRequest request) throws Exception {
		log.info("AddOrder_Start. orderDTO: {}", orderDTO);
		
		log.info(CommonResponseCode.OD_00001_주문_시작.toMessage()+" orderDTO: {}" , orderDTO.toJSON());
		
		HttpSession session = ContextService.getCurrentRequest().getSession();
		CartSearchDTO cartSearchDTO = (CartSearchDTO) session.getAttribute("SESSION_KEY_CART_GOODS");
		Mbr mbr = new Mbr();
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			
			
			orderCommandService.checkBlackMember();
			
			if(MemberEnum.MemberStatCd.BLCLST_B.toString().equals( mbr.getMbrStatCd())
					||MemberEnum.MemberStatCd.BLCLST_A.toString().equals( mbr.getMbrStatCd())){
				orderDTO.getOrd().setUnityPntUseSumAmt(BigDecimal.ZERO);
				orderDTO.getOrd().setWebpntUseSumAmt(BigDecimal.ZERO);
			}
			
			if(StringService.isNotEmpty(mbr.getMbrNo())
					   && StringService.equalsIgnoreCase(mbr.getMbrAtrbCd(), MemberEnum.MemberAtrbCd.EMP.toString())
					   && "Y".equals(cartSearchDTO.getEmpYn())){
				orderDTO.setEmp(true);
			}
			String cstrno = deliverySelectRepository.getVipList();
			if(mbr!=null&&mbr.getErpCstmrNo()!=null&&cstrno.indexOf(mbr.getErpCstmrNo())!=-1){
				orderDTO.setVipCsmtr(true);
			}
		}
		
		// 가배송여부
		orderDTO.getOrd().setVirtlDlvComptYn(cartSearchDTO.getVirtlDlvComptYn());
		
		
		Ord tmpOrd = orderDTO.getOrd();
		if (session.getAttribute(SessionEnum.PCC.toString()) != null || session.getAttribute(SessionEnum.IPIN.toString()) != null) {
			if (session.getAttribute(SessionEnum.PCC.toString()) != null) {
				Pcc pcc = (Pcc) session.getAttribute(SessionEnum.PCC.toString());
				if (pcc != null) {
					tmpOrd.setSelfCrtfcSectCd("SLF_CRTFC");
					tmpOrd.setRlnmCrtfcDi(pcc.getDi());
					tmpOrd.setRlnmCrtfcCi(pcc.getCi());
				}
			}
			else if (session.getAttribute(SessionEnum.IPIN.toString()) != null) {
				Ipin ipin = (Ipin) session.getAttribute(SessionEnum.IPIN.toString());
				if (ipin != null) {
					tmpOrd.setSelfCrtfcSectCd("IPIN_CRTFC");
					tmpOrd.setRlnmCrtfcDi(ipin.getDiscrhash());
					tmpOrd.setRlnmCrtfcCi(ipin.getCiscrhash());
					tmpOrd.setRlnmCrtfcIpin(ipin.getResult());
				}
			}
		}
		if (session.getAttribute("B2EKEY") != null) {
			//tmpOrd.setB2eEmplCrtfcCd((String) session.getAttribute("B2EKEY"));
		}
		if (session.getAttribute(SessionEnum.INFLOW_SN.toString()) != null) {
			orderDTO.setInflowSn(Long.parseLong((String) session.getAttribute(SessionEnum.INFLOW_SN.toString())));
		}
		if (session.getAttribute(SessionEnum.ADVT_AFF_COM_ID.toString()) != null) {
			orderDTO.getOrd().setAdvtAffComId((String) session.getAttribute(SessionEnum.ADVT_AFF_COM_ID.toString()));
		}
		

		//2017.08.22 배송지 필수값 체크 추가 - 이진형
		List<LgsDlvspDTO> lgsDlvspDTOList = orderDTO.getLgsDlvspDTOList();
		if(!cartSelectService.emptyChkLgsDlvsp(lgsDlvspDTOList)){
			throw new OrderCompleteFailException("ord.error.empty_lgs_dlvsp", null);
		}

		
		List<CartResult> cartResultList = (List<CartResult>) session.getAttribute("SESSION_KEY_CART_RESULTS");
		List<CartGiftResult> cartGiftList = CollectionService.emptyListIfNull((List<CartGiftResult>) session.getAttribute("SESSION_KEY_ORD_GIFTS"));
		List<OrderCouponResult> memberCouponList = this.selectOrderCouponList(cartSearchDTO);
		
		log.info("cartSearchDTO : {}, memberCouponList : {}",cartSearchDTO.toJSON(),memberCouponList);		

		String godTpCd = "";
		String pickupShopId = "";
		String mainGodNm = "";
		String chkResveSaleGodYn = "";// 2015.11.06 Ord-ordtpcd 컬럼에 Null 값일 경우 GNRL_ORD 셋팅
		String chkDlvSectCd = "";	  // 2015.11.06 Ord-ordtpcd 컬럼에 Null 값일 경우 GNRL_ORD 셋팅
		String chkBskNo = ""; // 2015.11.09-alan>중복주문방지
//		Map<String, String> resultMap = null;
		List<String> itmList = new ArrayList<String>();
		for (CartResult cart : cartResultList) {
			godTpCd = cart.getGod().getGodTpCd();
			chkResveSaleGodYn = cart.getGod().getResveSaleGodYn();// 2015.11.06 Ord-ordtpcd 컬럼에 Null 값일 경우 GNRL_ORD 셋팅
			chkDlvSectCd = cart.getBskGod().getDlvSectCd();	      // 2015.11.06 Ord-ordtpcd 컬럼에 Null 값일 경우 GNRL_ORD 셋팅
			chkBskNo = cart.getBskGod().getBskNo(); // 2015.11.09-alan>중복주문방지
			if (!itmList.contains(cart.getBskGod().getItmNo())) {
				itmList.add(cart.getBskGod().getItmNo());
				if ("".equals(mainGodNm)) {
					mainGodNm = cart.getGod().getGodNm();
				}
			}
		}
		// 2015.11.06 Ord-ordtpcd 컬럼에 Null 값일 경우 GNRL_ORD 셋팅 - alan -Start
		if(tmpOrd.getOrdTpCd() == null || "".equals(tmpOrd.getOrdTpCd())){
			tmpOrd.setOrdTpCd("GNRL_ORD"); // default 일반주문
			if("Y".equals(chkResveSaleGodYn)){ // 예약주문
				tmpOrd.setOrdTpCd("RESVE_ORD");
			}
			if("PKUP_DLV".equals(chkDlvSectCd)){ // 매장픽업
				tmpOrd.setOrdTpCd("SHOP_PKUP_ORD");
				
				if(orderDTO.getKcpDTO()!=null&&"Y".equals(orderDTO.getKcpDTO().getPay_mod())){
					throw new OrderCompleteFailException("픽업주문은 에스크로 결제를 할 수 없습니다.");
				}
			}
			if("GFCT".equals(godTpCd)){ // 상품권
				tmpOrd.setOrdTpCd("GFCT_ORD");
			}
		}

		

		int ordCnt = itmList.size();
		long time = System.currentTimeMillis();
		Date regDt = new Date(time);
		

		orderDTO.setOrd(tmpOrd);
		orderDTO.setMbr(mbr);
		orderDTO.setRegDt(regDt);
		orderDTO.setRegtrId(this.avoidNull(mbr.getMbrNo(), "GUEST"));
		orderDTO.setGodSummary(ordCnt > 1 ? mainGodNm + " 외 " + (ordCnt - 1) + "건" : mainGodNm);

		BigDecimal bSumPayAmt = BigDecimal.ZERO; // 총결제금액
		Locale locale = LocaleService.getCurrentLocale(request);
		TreeBasedTable<Integer, String, String> table = this.makeOrderTable(orderDTO, cartResultList, locale);
		SortedMap<Integer, Map<String, String>> sortMap = table.rowMap();


		try {
			// 2015.11.09-alan>중복주문방지 위해서 BSK 테이블에 대하여 Lock 처리 (FOR UPDATE NOWAIT)
			try {
				cartSelectService.selectBskForUpdate(cartSearchDTO.getMbr().getMbrNo());
			} catch(Exception e) {
				log.error("cart object error -> {}",cartSearchDTO.toJSON());
				log.error(CommonResponseCode.OD_40017_주문_중복_진행.toMessage());
				log.error(e.getMessage(),e);
				throw new OrderCompleteFailException("ord.error.not_complete_doubleorder", null);				
			}

			/**
        	(1). ord - 주문마스터 insert (주문번호, 상품가격/할인가/쿠폰가/포인트 의 합계)
        	(1). ord_god - 주문상품 (상품할인/주문할인, 상품쿠폰/주문쿠폰, 상품사은품/주문사은품 의 합계 와 분할)
        	(1). ord_god_apl_prm - 주문상품프로모션
			 */
			String lpInfo = this.setLinkPrice(request);
			Ord ord = orderCommandService.insertOrder(orderDTO, new HashMap<String, String>(),lpInfo);
			orderDTO.setOrd(ord);

			List<OrdGodExtends> ordGodExtendsList = new ArrayList<OrdGodExtends>();
			List<OrdGodExtends> giftList = new ArrayList<OrdGodExtends>();
			List<OrdGodAplPrmExtends> promoList = new ArrayList<OrdGodAplPrmExtends>();
			List<Pay> payList = new ArrayList<Pay>();
			List<InfOrdGodErpDstbExtends> infErpList = new ArrayList<InfOrdGodErpDstbExtends>();
			List<Integer> parentsList = new ArrayList<Integer>();

			HashMap<String, String> payCpnMap = new HashMap<String, String>();
			for (Map.Entry<Integer, Map<String, String>> tbl : sortMap.entrySet()) {
				Map<String, String> column = tbl.getValue();

				// 주문상품
				OrdGodExtends ordGodExtends = orderEntitySetService.makeOrdGod(ord, column);
				ordGodExtendsList.add(ordGodExtends);

				// 주문상품프로모션 - 패키지의 부모상품은 제외
				if (!("Y".equals(column.get("packageYn")) && StringService.isEmpty(column.get("additionalPack"))
						&& StringService.isEmpty(column.get("basicPack")))) {
					promoList.addAll(orderEntitySetService.makeOrdGodAplPrm(ordGodExtends, column));
				}
				else {
					parentsList.add(ordGodExtends.getOrdGodTurn());
				}
			}

			//2016-02-18 정재민 - DTO와 cache data가 상이할 경우 주문 상품데이터 없이 주문(master)만 생성되는 문제가 발생하여 예외처리 추가
			if (ordGodExtendsList == null || ordGodExtendsList.isEmpty()) {				
				log.warn(CommonResponseCode.OD_40018_주문_상품정보_변경.toMessage() + " / "+ord.getOrdNo());
				throw new OrderCompleteFailException("ord.error.no_ord_god", null);
			}

			/**
        	(2). lgs_dlvsp - 물류배송지 insert (주문번호, 배송지번호)
        	(2). lgs_dlv - 물류배송 insert (주문번호, 배송지번호, 배송정책번호, 배송지합계)
        	(2). mbr_issu_cpn - 회원발급쿠폰 : 배송비즉시할인쿠폰인경우
			 */
			String ordNo = ord.getOrdNo();
			String dlvMnCd = null;
			//List<LgsDlvspDTO> lgsDlvspDTOList = orderDTO.getLgsDlvspDTOList();
			List<LgsDlv> lgsDlvList = new ArrayList<LgsDlv>();
			List<MbrIssuCpn> mbrIssuCpnList = new ArrayList<MbrIssuCpn>();	// 배송비즉시할인쿠폰 : by cannon (2016.06.07)

//			Multimap<Long, BigDecimal> dlvPolicyMap = ArrayListMultimap.create();
			Table<Integer, Long, BigDecimal> dlvPolicyMapWms = TreeBasedTable.create();
			Table<Integer, Long, BigDecimal> dlvPolicyMapShop = TreeBasedTable.create();
			{
				for (LgsDlvspDTO lgsDlvspDTO : lgsDlvspDTOList) {
					LgsDlvsp lgsDlvsp = orderCommandService.insertDelivery(orderDTO, lgsDlvspDTO);
					lgsDlvspDTO.setLgsDlvsp(lgsDlvsp);

					// 배송수단코드 물류 배송으로 이동 [AshA]
//					dlvMnCd = lgsDlvsp.getDlvMnCd();
					if (dlvMnCd == null && lgsDlvspDTO.getDlvMnCd() != null) {
						dlvMnCd = lgsDlvspDTO.getDlvMnCd();
					}
					if ("SHOP_PKUP".equals(dlvMnCd) && !StringService.isEmpty(lgsDlvsp.getPkupShopId())) {
						pickupShopId = lgsDlvsp.getPkupShopId();
					}

					// 1. 동일 배송지, 다른상품, 다른배송정책
					// 2. 다른 배송지, 다른상품, 다른배송정책
					LgsDlv lgsDlv = new LgsDlv();
					lgsDlv.setOrdNo(ordNo);
					lgsDlv.setDlvPcupspTurn(lgsDlvsp.getDlvPcupspTurn());
					lgsDlv.setCrncyCd(ord.getCrncyCd());

					List<OrdGodDTO> ordGodDTOList = lgsDlvspDTO.getOrdGodDTOList();

					List<CouponDTO> couponDTOList = lgsDlvspDTO.getCouponDTOList();


					int dlvTurn = 0;

					
					// makek dlv_turn(배송순번)
					for (OrdGodDTO ordGodDTO : ordGodDTOList) {
						OrdGod ordGod = ordGodDTO.getOrdGod();

						if (ordGod.getOrdQty() > 0L) {
							String delvBskNo = ordGodDTO.getBskNo();
							int delvBskGodTurn = ordGodDTO.getGodTurn();
							for (CartResult cart : cartResultList) {
								String bskNo = cart.getBskGod().getBskNo();
								int bskGodTurn = cart.getBskGod().getGodTurn();

								if (delvBskNo.equals(bskNo) && delvBskGodTurn == bskGodTurn) {
									//if (cart.getGod().getDmstcDlvCstPlcSn() != ordGod.getDmstcDlvCstPlcSn()) {
									if ( ! cart.getGod().getDmstcDlvCstPlcSn().equals( ordGod.getDmstcDlvCstPlcSn() ) ) {
										if ( log.isErrorEnabled() ) {
											log.warn(">>[addOrder] Error has occurred. dmstc_dlv_cst_plc_sn[{}/{}/{}/{}/{}]",
													ordGod.getGodNo(), bskNo, String.valueOf( bskGodTurn ), cart.getGod().getDmstcDlvCstPlcSn(), ordGod.getDmstcDlvCstPlcSn() );
										}
										log.warn(CommonResponseCode.OD_40038_배송정책_정합성_에러.toMessage());
										throw new Exception("배송정책 정합성 에러!!!");
									}
									String dlvFeeTp = cart.getDlvCstLevySectCd();
									if("QDLV".equals(cart.getBskGod().getDlvSectCd())){
										dlvFeeTp = cart.getQdlvCstLevySectCd();
									}

									//BigDecimal cpnAplyDcAmt = BigDecimal.ZERO; // 쿠폰적용할인가

									if ((!"Y".equals(cart.getShopDlvItm())&&!dlvPolicyMapWms.contains(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn()))
											|| ("Y".equals(cart.getShopDlvItm())&&!dlvPolicyMapShop.contains(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn()))
											) {
										BigDecimal bRowDlvFee = BigDecimal.ZERO;

//										int dlvTurnCpn = 0;
//										for (OrdGodDTO ordGodDTO2 : ordGodDTOList) {
//											
//											log.debug("cart {}",cart);
//											log.debug("ordGodDTO2 {}",ordGodDTO2);
//											log.debug("couponDTOList {}",couponDTOList);
//											log.debug("dlvTurnCpn {}",dlvTurnCpn);
//											
//											log.debug("couponDTO {}",couponDTOList.get(dlvTurnCpn));
//											
//											
//											if ((ordGodDTO2.getOrdGod().getItmNo()).equals(cart.getBskGod().getItmNo())) {
//												cpnAplyDcAmt = new BigDecimal(this.avoidNull(couponDTOList.get(dlvTurnCpn).getGoodsCouponAmt(),"0"));
//											}
//											dlvTurnCpn++;
//										}
										
										BigDecimal bRowDlvStdAmt = BigDecimal.ZERO;
										for (OrdGod innerGod : ordGodExtendsList){
											if( cart.getBskGod().getGodNo().equals(innerGod.getGodNo()) && cart.getBskGod().getItmNo().equals(innerGod.getItmNo()) 
													&& cart.getGod().getGodTpCd().equals(innerGod.getGodTpCd())){
												bRowDlvStdAmt = innerGod.getSaleAmt();
												bRowDlvStdAmt = bRowDlvStdAmt.subtract(innerGod.getAllDcAmt());
												
											}
										}
										
										if("Y".equals(cart.getShopDlvItm())){
											if(dlvPolicyMapWms.contains(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn())){
												bRowDlvStdAmt = bRowDlvStdAmt.add(dlvPolicyMapWms.get(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn()));
												dlvPolicyMapWms.put(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn(), bRowDlvStdAmt);
											}
											dlvPolicyMapShop.put(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn(), bRowDlvStdAmt);
										}else{
											if(dlvPolicyMapShop.contains(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn())){
												bRowDlvStdAmt = bRowDlvStdAmt.add(dlvPolicyMapShop.get(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn()));
												dlvPolicyMapShop.put(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn(), bRowDlvStdAmt);
											}
											dlvPolicyMapWms.put(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn(), bRowDlvStdAmt);
										}

										if("QDLV".equals(cart.getBskGod().getDlvSectCd())){

											if ("PCHRG".equals(dlvFeeTp)) {
												bRowDlvFee = cart.getQdlvCst();
											}
											else if ("COND_FREE".equals(dlvFeeTp)) {

												if(bRowDlvStdAmt.compareTo(cart.getQdlvCstExmStdrAmt()) < 0) {
													bRowDlvFee = cart.getQdlvCst();
												}
											}

										}else{
											
                                            if ("PCHRG".equals(dlvFeeTp)) {
                                                bRowDlvFee = cart.getDmstcDlvCst();
                                            }
                                            else if ("COND_FREE".equals(dlvFeeTp)) {
                                                if (bRowDlvStdAmt.compareTo(cart.getDmstcDlvCstExmStdrAmt()) < 0) {
                                                    bRowDlvFee = cart.getDmstcDlvCst();
                                                }
                                            }
                                            
                                            log.debug("bRowDlvStdAmt {}",bRowDlvStdAmt);
                                            log.debug("cart.getDmstcDlvCstExmStdrAmt() {}",cart.getDmstcDlvCstExmStdrAmt());
											log.debug("bRowDlvFee {}",bRowDlvFee);

										}


										for (LgsDlv dlv : lgsDlvList) {
											//if (dlv.getDlvPcupspTurn() == lgsDlvsp.getDlvPcupspTurn() && dlv.getDlvTurn() == dlvTurn) {
											if (dlv.getDlvPcupspTurn() == lgsDlvsp.getDlvPcupspTurn()) {
												if(!dlv.getStdrCrncyAmt().equals(BigDecimal.ZERO)){
													dlv.setStdrCrncyAmt(bRowDlvFee);
													dlv.setPayExchgRtCrncyAmt(bRowDlvFee);
													dlv.setRealityDlvCst(bRowDlvFee);
													dlv.setPlcDlvCst(bRowDlvFee);
												}
											}
										}
										
										lgsDlv.setDlvTurn(++dlvTurn);
										lgsDlv.setDmstcDlvCstPlcSn(cart.getGod().getDmstcDlvCstPlcSn());
										//#33360 국문주문 컴포넌트에서 주문데이터 생성 시 물류배송 테이블에 해외배송정책번호 넣는 현상 수정 2016-12-21
										//lgsDlv.setOvseaDlvCstPlcSn(cart.getGod().getOvseaDlvCstPlcSn());
										//lgsDlv.setOvseaDlvDmstcDlvCstPlcSn(cart.getGod().getOvseaDlvDmstcDlvCstPlcSn());
										lgsDlv.setDlvCstBndMbdCd("MCOM".equals(cart.getGod().getPartmalSectCd()) ? "MCOM" : "COM");

										lgsDlv.setCrncyCd(ord.getCrncyCd());
										

										if ("Y".equals(cart.getShopDlvItm())) {
											lgsDlv.setDlvShopDlvYn("Y");
										
											if(dlvPolicyMapWms.contains(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn())){
												lgsDlv.setStdrCrncyAmt(BigDecimal.ZERO);
												lgsDlv.setPayExchgRtCrncyAmt(BigDecimal.ZERO);
												lgsDlv.setRealityDlvCst(BigDecimal.ZERO);
												lgsDlv.setPlcDlvCst(BigDecimal.ZERO);
											}else{
												lgsDlv.setStdrCrncyAmt(bRowDlvFee);
												lgsDlv.setPayExchgRtCrncyAmt(bRowDlvFee);
												lgsDlv.setRealityDlvCst(bRowDlvFee);
												lgsDlv.setPlcDlvCst(bRowDlvFee);
											}
											
										}else{
											lgsDlv.setDlvShopDlvYn("N");
											if(dlvPolicyMapShop.contains(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn())){
												lgsDlv.setStdrCrncyAmt(BigDecimal.ZERO);
												lgsDlv.setPayExchgRtCrncyAmt(BigDecimal.ZERO);
												lgsDlv.setRealityDlvCst(BigDecimal.ZERO);
												lgsDlv.setPlcDlvCst(BigDecimal.ZERO);
											}else{
												lgsDlv.setStdrCrncyAmt(bRowDlvFee);
												lgsDlv.setPayExchgRtCrncyAmt(bRowDlvFee);
												lgsDlv.setRealityDlvCst(bRowDlvFee);
												lgsDlv.setPlcDlvCst(bRowDlvFee);
											}
										}
										

										
										
										lgsDlv.setWaybilNoErpTrnsmisCd("N");
										lgsDlv.setDlvComTrnsmisTgtYn("SHOP_PKUP".equals(dlvMnCd) ? "N" : "Y");
										lgsDlv.setDlvComTrnsmisYn("N");
										lgsDlv.setMailSndYn("N");

										LgsDlv tmpLgsDlv = new LgsDlv();
										BeanUtils.copyProperties(lgsDlv, tmpLgsDlv);
										lgsDlvList.add(tmpLgsDlv);

									} else {
										BigDecimal bRowSumStdAmt = BigDecimal.ZERO;
										BigDecimal bRowDlvFee = BigDecimal.ZERO;

//										int dlvTurnCpn = 0;
//										for (OrdGodDTO ordGodDTO2 : ordGodDTOList) {
//											if ((ordGodDTO2.getOrdGod().getItmNo()).equals(cart.getBskGod().getItmNo())) {
//												cpnAplyDcAmt = new BigDecimal(this.avoidNull(couponDTOList.get(dlvTurnCpn).getGoodsCouponAmt(),"0"));
//											}
//											dlvTurnCpn++;
//										}


										BigDecimal bRowDlvStdAmt = BigDecimal.ZERO;
										for (OrdGod innerGod : ordGodExtendsList){
											if( cart.getBskGod().getGodNo().equals(innerGod.getGodNo()) && cart.getBskGod().getItmNo().equals(innerGod.getItmNo()) 
													&& cart.getGod().getGodTpCd().equals(innerGod.getGodTpCd())){
												bRowDlvStdAmt = innerGod.getSaleAmt();
												bRowDlvStdAmt = bRowDlvStdAmt.subtract(innerGod.getAllDcAmt());
												
											}
										}
										
										if("Y".equals(cart.getShopDlvItm())){
											bRowSumStdAmt = bRowDlvStdAmt.add(dlvPolicyMapShop.get(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn()));
										}else{
											bRowSumStdAmt = bRowDlvStdAmt.add(dlvPolicyMapWms.get(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn()));
										}
										
//										dlvPolicyMapWms.put(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn(), bRowDlvStdAmt);
										
										
										
										if(dlvPolicyMapWms.contains(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn())){
											dlvPolicyMapWms.put(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn(), bRowSumStdAmt);
										}
										if(dlvPolicyMapShop.contains(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn())){
											dlvPolicyMapShop.put(lgsDlv.getDlvPcupspTurn(), cart.getGod().getDmstcDlvCstPlcSn(), bRowSumStdAmt);
										}
										

										

										if ("COND_FREE".equals(dlvFeeTp)) {

											if("QDLV".equals(cart.getBskGod().getDlvSectCd())){

												if (bRowSumStdAmt.compareTo(cart.getQdlvCstExmStdrAmt()) < 0) {

													bRowDlvFee = cart.getQdlvCst();
													log.debug(">>>>>> bRowDlvFee : " + bRowDlvFee);
													log.debug(">>>>>> bRowSumStdAmt : " + bRowSumStdAmt);
													log.debug(">>>>>> cart.getQdlvCstExmStdrAmt() : " + cart.getQdlvCstExmStdrAmt());

												}

											}else{

                                                if (bRowSumStdAmt.compareTo(cart.getDmstcDlvCstExmStdrAmt()) < 0) {
                                                    bRowDlvFee = cart.getDmstcDlvCst();
                                                    log.debug(">>>>>> bRowDlvFee : " + bRowDlvFee);
                                                    log.debug(">>>>>> bRowSumStdAmt : " + bRowSumStdAmt);
                                                    log.debug(">>>>>> cart.getDmstcDlvCstExmStdrAmt() : " + cart.getDmstcDlvCstExmStdrAmt());
                                                }

											}

											for (LgsDlv dlv : lgsDlvList) {
												//if (dlv.getDlvPcupspTurn() == lgsDlvsp.getDlvPcupspTurn() && dlv.getDlvTurn() == dlvTurn) {
												if (dlv.getDlvPcupspTurn() == lgsDlvsp.getDlvPcupspTurn()) {
													if(!dlv.getStdrCrncyAmt().equals(BigDecimal.ZERO)){
														dlv.setStdrCrncyAmt(bRowDlvFee);
														dlv.setPayExchgRtCrncyAmt(bRowDlvFee);
														dlv.setRealityDlvCst(bRowDlvFee);
														dlv.setPlcDlvCst(bRowDlvFee);
													}
												}
											}
										}
									}
									log.debug(">>>>>> dlvPolicyMapWms : " + dlvPolicyMapWms);
									log.debug(">>>>>> dlvPolicyMapShop : " + dlvPolicyMapShop);
								}
							}
						}
					}
				}

				// 배송비무료쿠폰, 배송비즉시할인쿠폰 : by cannon (2016.06.07)
				for (LgsDlvspDTO lgsDlvspDTO : lgsDlvspDTOList){
					LgsDlvsp lgsDlvsp			= lgsDlvspDTO.getLgsDlvsp();
					String mbrCpnNo				= null;
					boolean isImdtlDc			= false;
					boolean isFirstCpn			= true;
					boolean isFirstApl			= true;
					boolean hasCpnAplGod		= false;
					BigDecimal bRowSumDlvCpn	= BigDecimal.ZERO;

					if( lgsDlvspDTO.getDeliveryCouponNo() != null
							&& !lgsDlvspDTO.getDeliveryCouponNo().isEmpty() ){
						//
						for (OrderCouponResult memberCoupon : memberCouponList){
							for (GoodsCouponResult mCoupon : memberCoupon.getGoodsCouponResultList()){
								if ("DLV_CST_CPN".equals(mCoupon.getPrm().getPrmDtlTpCd()) || "QDLV_CST_CPN".equals(mCoupon.getPrm().getPrmDtlTpCd())){

									isImdtlDc = "IMDTL_DC".equals(mCoupon.getPrmCpn().getCpnIssuMthdCd()) ? true : false;

									if( (!isImdtlDc && lgsDlvspDTO.getDeliveryCouponNo().equals(mCoupon.getMbrCpnNo()))	//회원쿠폰번호
									    ||
									    ( isImdtlDc && lgsDlvspDTO.getDeliveryCouponNo().equals(mCoupon.getPrm().getPrmNo())) )	//프로모션번호
									{
										//
										if (isFirstCpn) {
											if (isImdtlDc) {
												mbrCpnNo = orderCommandService.genMbrIssuCpnNo();

												//회원발급쿠폰
												MbrIssuCpn mbrIssuCpn	= new MbrIssuCpn();
												mbrIssuCpn.setMbrCpnNo(mbrCpnNo);
												mbrIssuCpn.setMbrNo(ord.getMbrNo());

												if("DLV_CST_CPN".equals(mCoupon.getPrm().getPrmDtlTpCd())){
												    mbrIssuCpn.setCpnTpCd("DLV_CST_CPN");
												}else if("QDLV_CST_CPN".equals(mCoupon.getPrm().getPrmDtlTpCd())){
													mbrIssuCpn.setCpnTpCd("QDLV_CST_CPN");
												}

												mbrIssuCpn.setCpnStatCd("USE");
												mbrIssuCpn.setCpnPubliDt(ord.getRegDt());
												mbrIssuCpn.setValidBegDate(new SimpleDateFormat("yyyyMMdd").format(ord.getRegDt()));
												mbrIssuCpn.setValidEndDate(new SimpleDateFormat("yyyyMMdd").format(ord.getRegDt()));
												mbrIssuCpn.setCpnUseDt(ord.getRegDt());
												mbrIssuCpn.setOrdNo(ord.getOrdNo());
												mbrIssuCpn.setPrmNo(mCoupon.getPrm().getPrmNo());
												mbrIssuCpn.setRegtrId(ord.getRegtrId());
												mbrIssuCpn.setUdterId(ord.getUdterId());

												mbrIssuCpnList.add(mbrIssuCpn);
											}else{
												mbrCpnNo = mCoupon.getMbrCpnNo();
											}

											//물류배송
											for (LgsDlv lgsDlv : lgsDlvList) {
												if (lgsDlv.getDlvPcupspTurn() == lgsDlvsp.getDlvPcupspTurn()) {
													lgsDlv.setStdrCrncyAmt(BigDecimal.ZERO);
													lgsDlv.setPayExchgRtCrncyAmt(BigDecimal.ZERO);
													lgsDlv.setRealityDlvCst(BigDecimal.ZERO);
													lgsDlv.setDlvCstCpnDcAmt(lgsDlv.getPlcDlvCst());
													lgsDlv.setDlvCstCpnNo(lgsDlv.getPlcDlvCst().intValue() == 0 ? null : mbrCpnNo);
													bRowSumDlvCpn = bRowSumDlvCpn.add(lgsDlv.getPlcDlvCst());
												}
											}
										}

										//주문상품적용프로모션
										for (OrdGod ordGod : ordGodExtendsList){
											if (ordGod.getDlvPcupspTurn() == lgsDlvsp.getDlvPcupspTurn()
													&& ordGod.getGodNo().equals(memberCoupon.getGod().getGodNo())){

												hasCpnAplGod = true;

												int ordGodTurn = ordGod.getOrdGodTurn();

												OrdGodAplPrmExtends ordGodAplPrm = new OrdGodAplPrmExtends();
												ordGodAplPrm.setOrdNo(ordNo);
												ordGodAplPrm.setOrdGodTurn(ordGod.getOrdGodTurn());
												ordGodAplPrm.setPrmNo(mCoupon.getPrm().getPrmNo());

												if("QDLV".equals(lgsDlvsp.getDlvSectCd())){
													ordGodAplPrm.setPrmDtlTpCd("QDLV_CST_CPN");
												}else{
												    ordGodAplPrm.setPrmDtlTpCd("DLV_CST_CPN");
												}

												ordGodAplPrm.setPrmTpCd("CPN");
												ordGodAplPrm.setAplUnitCd(isImdtlDc ? "GOD" : "ORD");
												ordGodAplPrm.setAplTpCd("APL");
												ordGodAplPrm.setMbrCpnNo(mbrCpnNo);
												ordGodAplPrm.setAplAmt(isFirstApl ? bRowSumDlvCpn : BigDecimal.ZERO);
												ordGodAplPrm.setAplQty(Long.valueOf(table.get(ordGodTurn, "ordQty")));
												ordGodAplPrm.setGodNo(table.get(ordGodTurn, "godNo"));

												if(parentsList.contains(ordGodTurn)){
													Map<Integer, String> packages = table.column("parentsGodTurn");
													for(Map.Entry<Integer, String> pkg : packages.entrySet()){
														if (!StringService.isEmpty(pkg.getValue())) {
															if(Integer.parseInt(pkg.getValue()) == ordGodTurn ){ //부모상품순번비교
																OrdGodAplPrmExtends tmpOrdGodAplPrm = new OrdGodAplPrmExtends();
																BeanUtils.copyProperties(ordGodAplPrm, tmpOrdGodAplPrm);

																tmpOrdGodAplPrm.setOrdGodTurn(pkg.getKey());
																tmpOrdGodAplPrm.setAplQty(Long.parseLong(table.get(pkg.getKey(), "ordQty")));
																tmpOrdGodAplPrm.setGodNo(table.get(pkg.getKey(), "godNo"));

																promoList.add(tmpOrdGodAplPrm);
															}
														}
													}
												}else{
													promoList.add(ordGodAplPrm);
												}
												//
												isFirstApl = false;
											}
										}
										//
										if (isFirstCpn && !payCpnMap.containsKey(mbrCpnNo)) {
											payCpnMap.put(mbrCpnNo, mCoupon.getPrm().getPrmNo());
										}
										//
										isFirstCpn = false;
									}
								}
							}
						}
						//
						if(isImdtlDc && !hasCpnAplGod){
							log.warn(CommonResponseCode.OD_40014_배송비_쿠폰_적용_불가.toMessage());
							throw new OrderCompleteFailException("ord.error.invalid_dlv_coupon", null);
						}
					}

				}
			}

			if(!orderDTO.isEmp()&&!orderDTO.isVipCsmtr()){
				// (3.7). 상품단위사은품
				int giftOrdGodTurn = ordGodExtendsList.size();
				for (OrdGodExtends ordGodExtends : ordGodExtendsList) {

					//세트상품일경우 자식상품은 포함하지 않음
					if("SET_GOD".equals(ordGodExtends.getPckageGodTpCd())){
						continue;
					}
					
					//중복체크 : 상품번호+프로모션번호+사은품번호
					String giftDupCheckKey			= null;
					List<String> listGiftDupCheck	= new ArrayList<String>();

					for (CartResult cart : cartResultList) {
						//픽업배송일 경우 사은품 포함안함 비회원도
						if(BskEnum.DlvSect.PKUP_DLV.toString().equals(cart.getBskGod().getDlvSectCd())
								||StringService.isEmpty(mbr.getMbrNo())){
							continue;
						}
						if (ordGodExtends.getGodNo().equals(cart.getBskGod().getGodNo())) {
							String godGiftPrmNo = cart.getGodGftPrmNo();
							if (!StringService.isEmpty(godGiftPrmNo)) {
								for (BskGodExtend gift : cart.getGodGftList()) {
									
									// gift stock check
									OrdGodExtends giftExtends = orderSelectService.selectGiftStock(gift.getGodNo());
									if (giftExtends.getTotUsefulInvQty() <= 0) {
										continue;
									}
									
									//중복체크 : 상품번호+프로모션번호+사은품번호
									giftDupCheckKey	= cart.getBskGod().getGodNo()
													+ cart.getGodGftPrmNo()
													+ gift.getGodNo();
									if(listGiftDupCheck.contains(giftDupCheckKey)){
										continue;
									}else{
										listGiftDupCheck.add(giftDupCheckKey);
									}

									giftExtends.setOrdNo(ordNo);
									giftExtends.setOrdGodTurn(++giftOrdGodTurn);
									giftExtends.setDlvPcupspTurn(ordGodExtends.getDlvPcupspTurn());
									giftExtends.setDmstcDlvCstPlcSn(ordGodExtends.getDmstcDlvCstPlcSn());	// 부모배송정책!
//									giftExtends.setOvseaDlvDmstcDlvCstPlcSn("");
//									giftExtends.setOvseaDlvCstPlcSn("");
									giftExtends.setOrdQty(ordGodExtends.getOrdQty());
									giftList.add(giftExtends);

									// gift promotion
									OrdGodAplPrmExtends ordGodAplPrm = new OrdGodAplPrmExtends();
									ordGodAplPrm.setOrdNo(ordNo);
									ordGodAplPrm.setOrdGodTurn(ordGodExtends.getOrdGodTurn());
									ordGodAplPrm.setPrmNo(godGiftPrmNo);
									ordGodAplPrm.setPrmTpCd("GFT");
									ordGodAplPrm.setPrmDtlTpCd("GOD_GFT");
									ordGodAplPrm.setAplTpCd("APL");
									ordGodAplPrm.setAplUnitCd("GOD");
									ordGodAplPrm.setAplAmt(BigDecimal.ZERO);
									ordGodAplPrm.setAplQty(ordGodExtends.getOrdQty());
									ordGodAplPrm.setOrdGodGftTurn(giftOrdGodTurn);
									ordGodAplPrm.setOrdGodGftNm(gift.getGodNm());
									ordGodAplPrm.setGodNo(ordGodExtends.getGodNo());

									promoList.add(ordGodAplPrm);

									if (!dlvPolicyMapWms.contains(ordGodExtends.getDlvPcupspTurn(), ordGodExtends.getDmstcDlvCstPlcSn())) {
										LgsDlv lgsDlv = new LgsDlv();
										lgsDlv.setOrdNo(ordNo);
										lgsDlv.setDlvPcupspTurn(ordGodExtends.getDlvPcupspTurn());
										lgsDlv.setCrncyCd(ord.getCrncyCd());
										lgsDlv.setDlvTurn(lgsDlvList.size() + 1);
										lgsDlv.setDmstcDlvCstPlcSn(ordGodExtends.getDmstcDlvCstPlcSn());
										//lgsDlv.setOvseaDlvCstPlcSn(ordGod.getOvseaDlvCstPlcSn());
										//lgsDlv.setOvseaDlvDmstcDlvCstPlcSn(ordGod.getOvseaDlvDmstcDlvCstPlcSn());
										lgsDlv.setDlvCstBndMbdCd("MCOM".equals(gift.getPartmalSectCd()) ? "MCOM" : "COM");

										lgsDlv.setCrncyCd(ord.getCrncyCd());
										lgsDlv.setStdrCrncyAmt(BigDecimal.ZERO);
										lgsDlv.setPayExchgRtCrncyAmt(BigDecimal.ZERO);
										lgsDlv.setRealityDlvCst(BigDecimal.ZERO);
										lgsDlv.setPlcDlvCst(BigDecimal.ZERO);
										lgsDlv.setWaybilNoErpTrnsmisCd("N");
										lgsDlv.setDlvComTrnsmisTgtYn("SHOP_PKUP".equals(dlvMnCd) ? "N" : "Y");
										lgsDlv.setDlvComTrnsmisYn("N");
										lgsDlv.setMailSndYn("N");
										lgsDlv.setDlvShopDlvYn("N");

										lgsDlvList.add(lgsDlv);
										
										dlvPolicyMapWms.put(ordGodExtends.getDlvPcupspTurn(), ordGodExtends.getDmstcDlvCstPlcSn(), BigDecimal.ZERO);
									}
								}
							}
						}
					}
				}
				// (3.8). 주문단위사은품
				//List<OrderGiftDTO> ordGiftDTOList = CollectionService.emptyListIfNull(orderDTO.getOrderGiftDTOList());
				
				if(!orderDTO.isEmp()&&!orderDTO.isVipCsmtr()){
					for (CartGiftResult giftDTO : cartGiftList) {
						for (BskGodExtend giftGod : giftDTO.getGod()) {
							boolean isOrderGift = false;
							for (OrdGodExtends ordGodExtends : ordGodExtendsList){
								//세트상품일경우 자식상품은 포함하지 않음
								if("SET_GOD".equals(ordGodExtends.getPckageGodTpCd())){
									continue;
								}
								for (BskGod ordGift : giftDTO.getBskGod()) {
									if(ordGodExtends.getGodNo().equals(ordGift.getGodNo())){
										if (!isOrderGift) {
											isOrderGift = true;
											// gift stock check
											OrdGodExtends giftExtends = orderSelectService.selectGiftStock(giftGod.getGodNo());
											if (giftExtends.getTotUsefulInvQty() <= 0) {
												continue;
											}
											if (giftExtends.getTotUsefulInvQty() < giftDTO.getPrm().getGftChoisePsbQty()) {
												continue;
											}
											
											
												
											giftExtends.setOrdNo(ordNo);
											giftExtends.setOrdGodTurn(++giftOrdGodTurn);
											giftExtends.setDlvPcupspTurn(1); // wms 로 업데이트 주문시
		//										giftExtends.setDmstcDlvCstPlcSn(dmstcDlvCstPlcSn);
											giftExtends.setOrdQty(giftDTO.getPrm().getGftChoisePsbQty());
											giftList.add(giftExtends);
		
											
											// wms lgsdlv가없을경우 한개 생성시킨다
											if (!dlvPolicyMapWms.contains(giftExtends.getDlvPcupspTurn(), giftExtends.getDmstcDlvCstPlcSn())) {
												LgsDlv lgsDlv = new LgsDlv();
												lgsDlv.setOrdNo(ordNo);
												lgsDlv.setDlvPcupspTurn(giftExtends.getDlvPcupspTurn());  // wms 로 업데이트 주문시
												lgsDlv.setCrncyCd(ord.getCrncyCd());
												lgsDlv.setDlvTurn(lgsDlvList.size() + 1);
												lgsDlv.setDmstcDlvCstPlcSn(giftExtends.getDmstcDlvCstPlcSn());
												// lgsDlv.setOvseaDlvCstPlcSn(cart.getGod().getOvseaDlvCstPlcSn());
												// lgsDlv.setOvseaDlvDmstcDlvCstPlcSn(cart.getGod().getOvseaDlvDmstcDlvCstPlcSn());
												lgsDlv.setDlvCstBndMbdCd("MCOM".equals(giftExtends.getPartmalSectCd()) ? "MCOM" : "COM");
	
												lgsDlv.setCrncyCd(ord.getCrncyCd());
												lgsDlv.setStdrCrncyAmt(BigDecimal.ZERO);
												lgsDlv.setPayExchgRtCrncyAmt(BigDecimal.ZERO);
												lgsDlv.setRealityDlvCst(BigDecimal.ZERO);
												lgsDlv.setPlcDlvCst(BigDecimal.ZERO);
												lgsDlv.setWaybilNoErpTrnsmisCd("N");
												lgsDlv.setDlvComTrnsmisTgtYn("SHOP_PKUP".equals(dlvMnCd) ? "N" : "Y");
												lgsDlv.setDlvComTrnsmisYn("N");
												lgsDlv.setMailSndYn("N");
												lgsDlv.setDlvShopDlvYn("N");
	
												lgsDlvList.add(lgsDlv);
												
												dlvPolicyMapWms.put(giftExtends.getDlvPcupspTurn(), giftExtends.getDmstcDlvCstPlcSn(), BigDecimal.ZERO);
											}
										}
										
										// gift promotion
										OrdGodAplPrmExtends ordGodAplPrm = new OrdGodAplPrmExtends();
										ordGodAplPrm.setOrdNo(ordNo);
										ordGodAplPrm.setOrdGodTurn(ordGodExtends.getOrdGodTurn());
										ordGodAplPrm.setPrmNo(giftDTO.getPrm().getPrmNo());
										ordGodAplPrm.setPrmTpCd("GFT");
										ordGodAplPrm.setPrmDtlTpCd("ORD_GFT");
										ordGodAplPrm.setAplTpCd("APL");
										ordGodAplPrm.setAplUnitCd("ORD");
										ordGodAplPrm.setAplAmt(BigDecimal.ZERO);
										ordGodAplPrm.setAplQty(0L);
										ordGodAplPrm.setOrdGodGftTurn(giftOrdGodTurn);
										ordGodAplPrm.setOrdGodGftNm(giftGod.getGodNm());
										ordGodAplPrm.setGodNo(ordGodExtends.getGodNo());
	
										promoList.add(ordGodAplPrm);
									}
								}
								
							}
						}
						
					}
				}
				
				
			}


			/**
			 * table insert
			 */
			//'17 09-18 NEW템플릿을 위한 추가
			BigDecimal bTotalPnt = BigDecimal.ZERO; //누적 멤버십포인트 (최종 = 누적 포인트 - 현 주문 건 사용포인트)
			BigDecimal bTotalWebpnt = BigDecimal.ZERO; //누적 P포인트 (최종 = 누적 코인 - 현 주문 건 사용코인)

			BigDecimal bSumUsePnt = this.nvl(ord.getUnityPntUseSumAmt()); // 사용포인트(맴버십포인트)
			BigDecimal bSumUseWebpnt = this.nvl(ord.getWebpntUseSumAmt()); // 사용포인트(P포인트)
			BigDecimal bSumImdtlPnt = BigDecimal.ZERO;	// 즉시사용포인트
			BigDecimal bSumSavePnt = BigDecimal.ZERO; // 총적립포인트
			BigDecimal bSumSaveWebpnt = BigDecimal.ZERO; // 총적립코인
//			BigDecimal bSumPayAmt = BigDecimal.ZERO; // 총결제금액
			BigDecimal bSumDlvFee = BigDecimal.ZERO; // 총배송비
			BigDecimal bSumDlvCpn = BigDecimal.ZERO; // 총배송비쿠폰
			List<String> onOffCouponList = new ArrayList<String>();

			boolean	   isOffCouponUsed = false;	//온오프라인쿠폰
			SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
			{
				// 배송비즉시할인쿠폰 : by cannon (2016.06.07)
				for(MbrIssuCpn mbrIssuCpn : mbrIssuCpnList){
					orderCommandService.insertMbrIssuCpn(mbrIssuCpn);
				}

				/**
				 * lgs_dlv
				 */
				for (LgsDlv lgsDlv : lgsDlvList) {
					// 배송수단코드 물류 배송으로 이동 [AshA]
					lgsDlv.setDlvMnCd(dlvMnCd);
					lgsDlv.setRegtrId(ord.getRegtrId());
					lgsDlv.setRegDt(ord.getRegDt());
					lgsDlv.setUdterId(ord.getRegtrId());
					lgsDlv.setUdtDt(ord.getRegDt());

					orderCommandService.insertLgsDlv(lgsDlv);
					if("QDLV".equals(dlvMnCd)){
						bSumDlvFee = bSumDlvFee.add(this.nvl(lgsDlv.getRealityDlvCst()));
					}else{
					    bSumDlvFee = bSumDlvFee.add(this.nvl(lgsDlv.getRealityDlvCst()));
					}

					bSumDlvCpn = bSumDlvCpn.add(this.nvl(lgsDlv.getDlvCstCpnDcAmt()));
				}

				/**
				 * goods check
				 * 1. stock
				 * 2. status
				 * 3.
				 */
				String ordTpCd = ord.getOrdTpCd();
				/**
				 * ord_god
				 */
				boolean hasPackageGoods = false;
				// stock1. DB stock sync
				//# 36273 : god_no, itm_no 순으로 정렬 후 처리 (상품재고동기화 와 dead-lock 회피)
				for (OrdGodExtends ordGodExtends : orderCommandService.getListOrdGodSortedByGodnoItmno(ordGodExtendsList)) {
					if ("Y".equals(ordGodExtends.getInvManageYn())) { // 재고관리여부
						// 2015-10-23 입점 구분에 따른 로직 분기 [AshA]
						// 자사인 경우
						if ("MCOM".equals(ordGodExtends.getPartmalSectCd())) {
							if (!"SET_GOD".equals(ordGodExtends.getGodTpCd())
									&& !"PCKAGE_GOD".equals(ordGodExtends.getGodTpCd())
							        && !"RESVE_ORD".equals(ordTpCd)) {
								List<GodItmExtend> itemList = new ArrayList<GodItmExtend>();
								GodItmExtend itm = new GodItmExtend();
								itm.setSkuNo(ordGodExtends.getSkuNo());
								itemList.add(itm);

								// 	ERP 재고 동기화 처리
								// ERP이 아닌 온라인재고 차감 하는 부분 추가 필요
								//goodsOptStockSelectService.interfaceGodItmInvListTransaction(systemPK, ordGodExtends.getErpGodNo(), ordGodExtends.getBrndId(), itemList);
							}
						// 입점 업체인 경우
						} else {
							if (!"SET_GOD".equals(ordGodExtends.getGodTpCd())
									&& !"PCKAGE_GOD".equals(ordGodExtends.getGodTpCd())
							        && !"RESVE_ORD".equals(ordTpCd)) {
								// TODO	상품재작업필요 : goodsOptStockCommandService.updateGodItmStatusByPartmall((OrdGod)ordGodExtends);
							}
						}
					}
				}

				for (OrdGodExtends ordGodExtends : giftList) {
					ordGodExtends.setGift(true);
					ordGodExtends.setStdrCrncyAmt(BigDecimal.ZERO);
					ordGodExtends.setPayExchgRtCrncyAmt(BigDecimal.ZERO);
					ordGodExtends.setRtlPrc(BigDecimal.ZERO);
					ordGodExtends.setSaleAmt(BigDecimal.ZERO);
					ordGodExtends.setStdrCrncyAmt(BigDecimal.ZERO);
					ordGodExtends.setPayExchgRtCrncyAmt(BigDecimal.ZERO);
					ordGodExtends.setSaleAmt(BigDecimal.ZERO);
					ordGodExtends.setWebDcAmt(BigDecimal.ZERO);
					ordGodExtends.setEmpDcAmt(BigDecimal.ZERO);
					ordGodExtends.setAllDcAmt(BigDecimal.ZERO);
					ordGodExtends.setImdtlDcAmt(BigDecimal.ZERO);
					ordGodExtends.setGodDcAmt(BigDecimal.ZERO);
					//ordGodExtends.setSignlDcAmt(BigDecimal.ZERO);
					//ordGodExtends.setB2eSpslDcAmt(BigDecimal.ZERO);
					ordGodExtends.setBundleDcAmt(BigDecimal.ZERO);
					ordGodExtends.setCrsDcAmt(BigDecimal.ZERO);
					ordGodExtends.setGodCpnDcAmt(BigDecimal.ZERO);
					ordGodExtends.setBskCpnDcAmt(BigDecimal.ZERO);
					ordGodExtends.setUnityPntUseAmt(BigDecimal.ZERO);
					ordGodExtends.setUnityPntAccmlAmt(BigDecimal.ZERO);
                    // P포인트
                    ordGodExtends.setWebpntUseAmt(BigDecimal.ZERO);
                    ordGodExtends.setWebpntAccmlAmt(BigDecimal.ZERO);

					ordGodExtends.setCrncyCd(ord.getCrncyCd());
					ordGodExtends.setMallId(ord.getMallId());
					ordGodExtends.setLmttInvYn("N");
					
					//mall 분리
					String saleShopId = OrderEnum.ORD_SALE_SHOP_CD_WMS.toString();
					if("MBM".equals(ord.getMallId())) {
						saleShopId = "M510";
					} else if("SAM".equals(ord.getMallId())) {
						saleShopId = "A30003";
					} else {
						
					}
					ordGodExtends.setSaleShopId(saleShopId);

					ordGodExtendsList.add(ordGodExtends);
				}
				// '17.07.26 이진형 수정및추가 S
				List<OrdGodExtends> sortedList = orderCommandService.getListOrdGodSortedByGodnoItmno(ordGodExtendsList);
				if("N".equals(orderDTO.getOrd().getVirtlDlvComptYn())){
					this.checkOrdGodQtyStatus(cartSearchDTO, locale); //재고체크
					

					// F&F 재고처리 픽업은 RT처리 일반 WMS 는 일반 재고 처리 가상계좌는 입금완료시 처리
					if(orderDTO.getKcpDTO()==null||!OrderEnum.kcpPayComp.VIRTL_BNK_ACCT_PAY.getKcpValue().equals(orderDTO.getKcpDTO().getUse_pay_method())){
						if ("SHOP_PKUP".equals(dlvMnCd)) { // start if
							//실재고처리
							
							for (OrdGodExtends ordGodExtends : sortedList) {
								if(!"SET_GOD".equals(ordGodExtends.getGodTpCd())){
									GoodsInventoryDTO gDTO = new GoodsInventoryDTO();
									gDTO.setShopId(ordGodExtends.getSaleShopId());
									gDTO.setGodNo(ordGodExtends.getGodNo());
									gDTO.setItmNo(ordGodExtends.getItmNo());
									gDTO.setSyncReqType(GoodsEnum.InvSyncReqTp.ORD.toString());
									gDTO.setSyncType(GoodsEnum.InvSyncTp.DDCT.toString());
									
									gDTO.setInvQty(ordGodExtends.getOrdQty());
	
									goodsInventoryFOComponent.updateGoodsInventoryVariation(gDTO);
								
								}
							}
							
						}else{
							for (OrdGodExtends ordGodExtends : sortedList) {
								if(!"SET_GOD".equals(ordGodExtends.getGodTpCd())){
									if(OrderEnum.ORD_SALE_SHOP_CD_WMS.toString().equals(ordGodExtends.getSaleShopId()) || "M510".equals(ordGodExtends.getSaleShopId())
											|| "I50002".equals(ordGodExtends.getSaleShopId()) || "A30003".equals(ordGodExtends.getSaleShopId()) ){
										if("Y".equals(chkResveSaleGodYn)){ // 예약주문
											
											GoodsInventoryDTO gDTO = new GoodsInventoryDTO();
											gDTO.setShopId(ordGodExtends.getSaleShopId());
											gDTO.setGodNo(ordGodExtends.getGodNo());
											gDTO.setItmNo(ordGodExtends.getItmNo());
											gDTO.setSyncReqType(GoodsEnum.InvSyncReqTp.ORD.toString());
											gDTO.setSyncType(GoodsEnum.InvSyncTp.DDCT.toString());
											
											gDTO.setResveInvQty(ordGodExtends.getOrdQty());
											
											goodsInventoryFOComponent.updateGoodsReserveInventoryVariation(gDTO);
										}
											
										if(true){// 예약상품도 재고 차감
											GoodsInventoryDTO gDTO = new GoodsInventoryDTO();
											gDTO.setShopId(ordGodExtends.getSaleShopId());
											gDTO.setGodNo(ordGodExtends.getGodNo());
											gDTO.setItmNo(ordGodExtends.getItmNo());
											gDTO.setSyncReqType(GoodsEnum.InvSyncReqTp.ORD.toString());
											gDTO.setSyncType(GoodsEnum.InvSyncTp.IRDS.toString());
											
											gDTO.setSalePrearngeQty(ordGodExtends.getOrdQty());
											
											goodsInventoryFOComponent.updateGoodsSalePrearrangementInventoryVariation(gDTO);
										}
										
									}
								}
							}
						}// end if
						
					}
				}
				
				// '17.07.26 이진형 수정및추가 E

				// insert ord_god
				for (OrdGodExtends ordGodExtends : ordGodExtendsList) {
					ordGodExtends.setRegtrId(ord.getRegtrId());
					ordGodExtends.setRegDt(ord.getRegDt());
					ordGodExtends.setUdterId(ord.getRegtrId());
					ordGodExtends.setUdtDt(ord.getRegDt());
					ordGodExtends.setLang(ord.getLangCd());
					

					//# 36273 : god_no, itm_no 순으로 정렬 후 처리 (상품재고동기화 와 dead-lock 회피)
					//this.checkOrdGodStatus(ordTpCd, ordGodExtends, pickupShopId, ordGodExtends.isGift(), locale);
					orderCommandService.insertOrdGod(ordGodExtends);

					if (!parentsList.contains(ordGodExtends.getOrdGodTurn())) {
						bSumSavePnt = bSumSavePnt.add(ordGodExtends.getUnityPntAccmlAmt());
                        // P포인트
                        bSumSaveWebpnt = bSumSaveWebpnt.add(ordGodExtends.getWebpntAccmlAmt());
						bSumPayAmt = bSumPayAmt.add(ordGodExtends.getPayExchgRtCrncyAmt());
					} else {
						hasPackageGoods = true;
					}
				}




				/**
				 * ord_cpst_god_cnnc
				 */
				if (hasPackageGoods) {
					//String parentsGodNo = "";

					// 2015-11-19 세트 상품 체크 로직 자사 입점사 분기 추가 [AshA]
					//String partmalSectCd = "";

					int parentsGodTurn = 0;
					//List<GodItmExtend> packItemList = new ArrayList<GodItmExtend>();
					Map<Integer, String> packages = table.column("parentsGodTurn");
					for (Map.Entry<Integer, String> pkg : packages.entrySet()) {
						int cpstGodTurn = pkg.getKey();
						if (!StringService.isEmpty(pkg.getValue())) {
							parentsGodTurn = Integer.parseInt(pkg.getValue());

							// insert cpst
							OrdCpstGodCnnc cpst = new OrdCpstGodCnnc();
							cpst.setOrdNo(ordNo);
							cpst.setOrdGodTurn(parentsGodTurn);
							cpst.setOrdCpstGodTurn(cpstGodTurn);
							cpst.setPckageGodTpCd(table.get(cpstGodTurn, "pckageGodTpCd"));
							cpst.setCpstGodQty(Long.parseLong(table.get(cpstGodTurn, "cpstGodQty")));
							cpst.setSortSeq(Integer.parseInt(table.get(cpstGodTurn, "sortSeq")));
							cpst.setRegtrId(ord.getRegtrId());
							cpst.setRegDt(ord.getRegDt());
							cpst.setUdterId(ord.getRegtrId());
							cpst.setUdtDt(ord.getRegDt());

							orderCommandService.insertOrdCpst(cpst);
						}
					}
					
					}

				
				/**
				 * ord_god_apl_prm
				 */
				for (OrdGodAplPrmExtends ordGodAplPrm : promoList) {

					//if (ordGodAplPrm.getOrdGodTurn() > 0) {

						if ("CPN".equals(ordGodAplPrm.getPrmTpCd()) && !StringService.isEmpty(ordGodAplPrm.getMbrCpnNo())) {
							// check member coupon validation
							boolean validCoupon = false;
							for (OrderCouponResult memberCoupon : memberCouponList) { // member coupon list
								for (GoodsCouponResult mCoupon : memberCoupon.getGoodsCouponResultList()) {
									//온오프라인쿠폰 사용여부체크
									if("ONOFLNE_CPN".equals(mCoupon.getPrmCpn().getCpnKndCd())
											&& ordGodAplPrm.getMbrCpnNo().equals(mCoupon.getMbrCpnNo())){
										if(!mCoupon.isOnoffCheckedErp()){
											throw new OrderCompleteFailException("orderError");
										}else if(!mCoupon.isOnoffCheckedAvailble()){
											String[] params = new String[1];
											params[0] = mCoupon.getPrm().getPrmNm();
											log.warn(CommonResponseCode.OD_40013_X0_은_사용된_쿠폰.toMessage(mCoupon.getPrm().getPrmNm()));
											throw new OrderCompleteFailException("ord.error.invalid_off_coupon_used", params);
										}
										if(!onOffCouponList.contains(mCoupon.getCpnCrtfcCd())){
											onOffCouponList.add(mCoupon.getCpnCrtfcCd());
										}
										isOffCouponUsed = true;
									}

									if ("IMDTL_DC".equals(mCoupon.getPrmCpn().getCpnIssuMthdCd())		//배송비즉시할인쿠폰 : by cannon (2016.06.07)
											|| ordGodAplPrm.getMbrCpnNo().equals(mCoupon.getMbrCpnNo())) {
										validCoupon = true;
									}
								}
							}
							if (!validCoupon) {
								List<String> paramList = new ArrayList<String>();
								String cdNm = CodeUtil.getCode(ord.getLang(), ordGodAplPrm.getPrmDtlTpCd()).getCdNm();
								paramList.add(cdNm); // {0}
								String[] params = paramList.toArray(new String[paramList.size()]);
								String exceptionMessage = messageSourceCmpAccessor.getMessage("ord.error.expired_promotion", params, locale);
								log.warn(CommonResponseCode.OD_40039_X0_는_적용_불가한_프로모션.toMessage(ordGodAplPrm.getPrmNo()));
								throw new OrderCompleteFailException(exceptionMessage);
							}

							// 다운로드쿠폰 사용으로 FLAG 변경
							orderCommandService.updateMbrIssuCoupon(ordGodAplPrm);

							// for pay table
							if (!payCpnMap.containsKey(ordGodAplPrm.getMbrCpnNo())) {
								payCpnMap.put(ordGodAplPrm.getMbrCpnNo(), ordGodAplPrm.getPrmNo());
							}
								}

					//}

						ordGodAplPrm.setRegtrId(ord.getRegtrId());
						ordGodAplPrm.setRegDt(ord.getRegDt());
						ordGodAplPrm.setUdterId(ord.getRegtrId());
						ordGodAplPrm.setUdtDt(ord.getRegDt());
						orderCommandService.insertOrdGodAplPrm(ordGodAplPrm);

				}

				/**
				 * lgs_dlivy_drct_god
				 */
				orderCommandService.insertShipping(ord, dlvMnCd);

				/**
				(3). inf_ord_god_erp_dstb - 인터페이스주문상품 erp 분배
				 */
				for (OrdGodExtends ordGodExtends : ordGodExtendsList) {
					if (!parentsList.contains(ordGodExtends.getOrdGodTurn())) {
						infErpList.addAll(orderEntitySetService.makeInfOrdGodErpDstb(ordGodExtends));
					}
				}

				/**
				 * inf_ord_god_erp_dstb
				 */
				int qtyTurn = 0;
				for (InfOrdGodErpDstb infErp : infErpList) {
					infErp.setQtyTurn(++qtyTurn);
					orderCommandService.insertInfOrdGodErpDstb(infErp);

					bSumImdtlPnt = bSumImdtlPnt.add(infErp.getImdtlDcUntPrc());
				}

				//입점사 쿠폰 분담율에 따른 업체 할인 금액 업데이트
				orderCommandService.updateInfOrdGodErpDstbComputeComBndUntPrc(ordNo);
			}

			/**
			 * (4). pay - 결제 (주결제 / i포인트, 상품쿠폰, 주문쿠폰, 배송비쿠폰)
			 */
			{
//				bSumPayAmt = bSumPayAmt.add(bSumDlvFee).subtract(bSumDlvCpn);
				bSumPayAmt = bSumPayAmt.add(bSumDlvFee);

				// pay pg
				if (bSumPayAmt.compareTo(BigDecimal.ZERO) > 0) {
					// 결제가 100
					if (bSumPayAmt.compareTo(new BigDecimal(100)) < 0) {
						log.warn(CommonResponseCode.OD_40003_금액_100원_미만_결제_불가.toMessage());
						log.error("payment amount {}",bSumPayAmt);
						log.error("dlv amount {}",bSumDlvFee);
						orderDTO.setOrdGodExtendsList(ordGodExtendsList);
						log.error("order dto json {}",orderDTO.toJSON());
						throw new OrderCompleteFailException("ord.error.minimum_price", null);
					}
/*
					resultMap = this.approvePG(orderDTO, request, godTpCd);
					if (resultMap == null || resultMap.isEmpty()) {						
						log.warn(CommonResponseCode.OD_40015_PG_승인_결과_없음.toMessage());
						throw new OrderPGFailException("ord.error.pg", null);
					}
					*/
					Pay pay = orderDTO.getPay();
					pay.setOrdNo(ordNo);
					pay.setPayTpCd("ORD");
					pay.setPayCrncyCd(ord.getCrncyCd());
					pay.setMpayMnYn("Y");
//					pay.setPayMnCd(orderDTO.getPay().getPayMnCd());
					pay.setStdrCrncyPayAmt(bSumPayAmt);
					pay.setPayCrncyPayAmt(bSumPayAmt);
					
					//주결제 수단 base 설정 결제 설정은 제일 마지막에 연동과 함께
					payList.add(pay);
					orderDTO.setPay(pay);
					
				} else {// 0원일 경우
					Pay pay = new Pay();
					pay.setOrdNo(ordNo);
					pay.setPayTpCd("ORD");
					pay.setPayCrncyCd(ord.getCrncyCd());
					pay.setMpayMnYn("Y");
					pay.setPayMnCd("ZERO_PAY");
					pay.setStdrCrncyPayAmt(BigDecimal.ZERO);
					pay.setPayCrncyPayAmt(BigDecimal.ZERO);
					pay.setDealTpCd("PAY_COMPT");
					pay.setEscrYn("N");
					pay.setCardSvDealYn("N"); // 카드세이브거래
					pay.setPayDt(ord.getOrdDt());

					payList.add(pay);
				}
				Pay pay = new Pay();
				pay.setOrdNo(ordNo);
				pay.setPayTpCd("ORD");
				pay.setPayCrncyCd(ord.getCrncyCd());
				pay.setDealTpCd("PAY_COMPT");
				pay.setMpayMnYn("N");
				pay.setEscrYn("N");
				pay.setCardSvDealYn("N"); // 카드세이브거래
				pay.setPayDt(ord.getOrdDt());

				// pay point
				if (bSumUsePnt.compareTo(BigDecimal.ZERO) > 0) {
					pay.setPayMnCd("MBSH_PNT_PAY");
					pay.setStdrCrncyPayAmt(bSumUsePnt);
					pay.setPayCrncyPayAmt(bSumUsePnt);

					Pay tmpPay = new Pay();
					BeanUtils.copyProperties(pay, tmpPay);
					payList.add(tmpPay);
				}
                // pay point
                if (bSumUseWebpnt.compareTo(BigDecimal.ZERO) > 0) {
                    pay.setPayMnCd("WEB_PNT_PAY");
                    pay.setStdrCrncyPayAmt(bSumUseWebpnt);
                    pay.setPayCrncyPayAmt(bSumUseWebpnt);

					Pay tmpPay = new Pay();
					BeanUtils.copyProperties(pay, tmpPay);
					payList.add(tmpPay);
				}
				// pay imdtl dc point
				if (bSumImdtlPnt.compareTo(BigDecimal.ZERO) > 0) {
					pay.setPayMnCd("MBSH_IMDTL_PNT_PAY");
					pay.setStdrCrncyPayAmt(bSumImdtlPnt);
					pay.setPayCrncyPayAmt(bSumImdtlPnt);

					Pay tmpPay = new Pay();
					BeanUtils.copyProperties(pay, tmpPay);
					payList.add(tmpPay);
				}

				// pay coupon
				for (String key : payCpnMap.keySet()) {
					BigDecimal bSumCpnAmt = BigDecimal.ZERO;
					String payMnCd = null;
					for (OrdGodAplPrmExtends ordGodAplPrm : promoList) {
						//if (key.equals(ordGodAplPrm.getMbrCpnNo())) {
					
						if (key.equals(ordGodAplPrm.getMbrCpnNo()) && !("MBSH_PNT".equals(ordGodAplPrm.getPrmTpCd()) && "ADIT_SAV".equals(ordGodAplPrm.getPrmDtlTpCd()))) {
							bSumCpnAmt = bSumCpnAmt.add(ordGodAplPrm.getAplAmt());
							payMnCd = ordGodAplPrm.getPrmDtlTpCd();
						}
					}
					pay.setPayMnCd(payMnCd);
					pay.setStdrCrncyPayAmt(bSumCpnAmt);
					pay.setPayCrncyPayAmt(bSumCpnAmt);
					pay.setUseCpnPrmNo(payCpnMap.get(key));
					pay.setMbrCpnNo(key);

					Pay tmpPay = new Pay();
					BeanUtils.copyProperties(pay, tmpPay);
					payList.add(tmpPay);
				}
			}
			

			/**
			 * update price
			 */
			boolean inValidPrice = false;
			if (!(bSumPayAmt.compareTo(ord.getStdrCrncySumAmt()) == 0 && bSumPayAmt.compareTo(ord.getPayExchgRtCrncySumAmt()) == 0)) {
				inValidPrice = true;
			}
			if (bSumPayAmt.compareTo(BigDecimal.ZERO) > 0&&!bSumPayAmt.toString().equals(ord.getPayExchgRtCrncySumAmt().toString())) {
				inValidPrice = true;
			}
			if (!inValidPrice) {
				ord.setStdrCrncySumAmt(bSumPayAmt);
				ord.setPayExchgRtCrncySumAmt(bSumPayAmt);
				ord.setDlvCstSumAmt(bSumDlvFee);
				ord.setDlvCstCpnDcSumAmt(bSumDlvCpn);
				orderCommandService.updateOrder(ord);
			}
			else {
				orderDTO.getLgsDlvspDTOList().get(0).setLgsDlvList(lgsDlvList);
				orderDTO.setOrdGodExtendsList(ordGodExtendsList);
				log.error("order god list {}", orderDTO.toJSON());
				log.warn(CommonResponseCode.OD_40006_고객결제금액과_서버결제금액_상이.toMessage()+" / bSumPayAmt : " + bSumPayAmt.toString()+" / ord.getPayExchgRtCrncySumAmt() : " + ord.getPayExchgRtCrncySumAmt().toString());
				throw new OrderCompleteFailException("ord.error.invalid_price", null);
			}

			// 1. 회원정보변경관련
			try{
				this.insertMemberInformation(orderDTO, ord, systemPK);
			}catch(Exception e){
				log.error(e.getMessage(),e);
			}
			
			// 카트데이터 삭제 장바구니데이터 삭제
			cartCommandService.deleteCartGoodsFromOrder(cartSearchDTO);

			// 물류배송지,물류배송,출고지시 이력
			orderCommandService.insertHistoryLgs(ord.getOrdNo());

			

			if(bSumUsePnt.intValue() > 0
					&&StringService.isNotEmpty(mbr.getErpCstmrNo()) && StringUtils.defaultString(mbr.getMbrTpCd()).equalsIgnoreCase(MemberTpCd.UNITY_MBR.toString())){
				long memberPoint = 0L;
				try {
					MypageFoDTO mypageFoDTO = new MypageFoDTO();
				    mypageFoDTO.setMbr(mbr);
					mypageFoDTO.setHistoryYn("N");
					
					String tempBrand = "X";
					if("MBM".equals(ord.getMallId())) {
						tempBrand = "M";
					} else if("SAM".equals(ord.getMallId())) {
						tempBrand = "A";
					} 
					mypageFoDTO.setBrand(tempBrand);
					
					// 마일리지 내역 조회
					MemberMileageInfoSDO memberMileageInfoSDO = memberBenefitFOComponent.selectMileageInfo(systemPK, mypageFoDTO);

				    
					if("200".equals(memberMileageInfoSDO.getResponseCd())){
						memberPoint = Long.valueOf(memberMileageInfoSDO.getNowPoint());
					}else{
						memberPoint = 0;
					}
					bTotalPnt = this.nvl(new BigDecimal(memberPoint)); // '17 09-18 새로운 카카운톡 템플릿을 위한 추가.
				} catch(Exception e) {
					log.warn(">>> inferface error : getBpCbDisplay", e);
				}
				log.info("***********@@-Check-ERP을통한멤버십포인트-memberPoint:"+memberPoint);
				long longBSumUsePnt = bSumUsePnt.longValue();
				log.info("***********@@-Check-ERP을통한멤버십포인트-longBSumUsePnt:"+longBSumUsePnt);

				if(bSumUsePnt.intValue() > 0 && ((longBSumUsePnt > memberPoint) || (memberPoint <= 0)) ){
					log.warn("***********@@-사용포인트-bSumUsePnt:"+bSumUsePnt);
					log.warn("***********@@-즉시사용포인트-bSumImdtlPnt:"+bSumImdtlPnt);
					log.warn("***********@@-총적립포인트-bSumSavePnt:"+bSumSavePnt);
                    log.warn("***********@@-총적립P포인트-bSumSaveWebpnt:"+bSumSaveWebpnt);
					log.warn("***********@@-총결제금액-bSumPayAmt:"+bSumPayAmt);
					log.warn("***********@@-총배송비-bSumDlvFee:"+bSumDlvFee);
					log.warn("***********@@-총배송비쿠폰-bSumDlvCpn:"+bSumDlvCpn);
					//throw Exception e
					log.warn("***********@@-Check-사용포인트&멤버십포인트 비교시 에러 발생");
					
					log.warn(CommonResponseCode.OD_40016_멤버쉽_포인트_잔액_부족.toMessage());
					throw new OrderCompleteFailException("ord.error.not_complete_erppoint", null);
				}

				bTotalPnt = this.nvl(new BigDecimal(memberPoint - longBSumUsePnt)); // '17 09-18 새로운 카카운톡 템플릿을 위한 추가.
			}

			// 웹포인트
			if (StringService.isNotEmpty(mbr.getMbrNo()) &&
					( StringUtils.defaultString(mbr.getMbrTpCd()).equalsIgnoreCase(MemberTpCd.ONLINE_MBR.toString())
							|| StringUtils.defaultString(mbr.getMbrTpCd()).equalsIgnoreCase(MemberTpCd.UNITY_MBR.toString()) )) {
				orderCommandService.webPointInsert(ord);
				String sTotWebPnt = memberBenefitSelectService.selectWebPointInfoMap(mbr.getMbrNo()).get("use");
				bTotalWebpnt = this.nvl(new BigDecimal(sTotWebPnt));

			}
			
			// 가격변경 주문일시 클레임 주문 상태 변경
			if("Y".equals(orderDTO.getOrd().getVirtlDlvComptYn())){
				
				this.changeOrderClaimOrderNumber(cartSearchDTO.getVirtlDlvOrdNo(),ordNo);
				
				// 매출 인터페이스 무조건 마지막이어야함 결제완료시만 진입
				if(orderDTO.getKcpDTO()==null||!OrderEnum.kcpPayComp.VIRTL_BNK_ACCT_PAY.getKcpValue().equals(orderDTO.getKcpDTO().getUse_pay_method())){
					//
					this.changeOrderPaymentReplace(ordNo);
				}
			}


			// 회원선호결제수단 // #34475 로 orderDTO.getMbrPreferPayMn() != null 조건추가
			if(ord.getMbrNo() != null){
				try{
					MbrPreferPayMn mbrPreferPayMnDTO = new MbrPreferPayMn();
					mbrPreferPayMnDTO.setMallId(ord.getMallId());
					mbrPreferPayMnDTO.setLangCd(ord.getLangCd());
					mbrPreferPayMnDTO.setMbrNo(ord.getMbrNo());
					mbrPreferPayMnDTO.setPayMnCd(orderDTO.getMbrPreferPayMn());
					mbrPreferPayMnDTO.setRegtrId(ord.getRegtrId());
					mbrPreferPayMnDTO.setUdterId(ord.getUdterId());
					orderCommandService.insertMbrPreferPayMn(mbrPreferPayMnDTO);
				}catch(Exception e){
					log.warn("회원선호결제수단 에러 발생", e);
				}
			}
			
			

			
			/**
			 * 결제 모듈 연동 start 
			 */
			try{

				this.approvePG(orderDTO, bSumPayAmt, ordNo);
				
				for (Pay pay : payList) {
					pay.setRegtrId(ord.getRegtrId());
					pay.setRegDt(ord.getRegDt());
					pay.setUdterId(ord.getRegtrId());
					pay.setUdtDt(ord.getRegDt());
	
					if (StringService.isEmpty(pay.getPayNo())) {
						pay.setPayNo(orderCommandService.selectDBNumber("SQ_PAY", "ST"));
					}
					orderCommandService.insertPayment(pay);
				}
				
				
				
			}catch(OrderPGFailException ooe){
				log.error(ooe.getMessage(),ooe);
				throw ooe;
			}catch(OrderCompleteFailException ocfe){
				log.error(ocfe.getMessage(),ocfe);
				throw ocfe;
			}catch(Exception e){
				log.error(e.getMessage(),e);
				throw new OrderPGFailException("ord.error.pg", new String[]{e.getMessage()});
			}
			/**
			 * 결제 모듈 연동 end 
			 */
				
			
			try{
				if(isOffCouponUsed){ // 온오프 임시사용
					orderInterfaceService.useOnOffCpn(onOffCouponList, mbr.getErpCstmrNo(), ordNo);
				}
				
				// 매출 인터페이스 무조건 마지막이어야함 결제완료시만 진입
				if (!"VIRTL_BNK_ACCT_PAY".equals(orderDTO.getPay().getPayMnCd())) {
					try{
						OrderParamDTO salesDTO = new OrderParamDTO();
						salesDTO.setOrdNo(ordNo);
						List<OrderPaymentInfoListSDO> returnSdoList = orderInterfaceService.sendSalesData(salesDTO);
						
						//if(returnSdoList.isEmpty()){ // 매장배송만 존재할경우 빈 객체 마일리지 임시처리 함
							if(bSumUsePnt.intValue() > 0){
									OrderParamDTO mileSalesDTO = new OrderParamDTO();
									mileSalesDTO.setOrdNo(ordNo);
									mileSalesDTO.setUnAssignedOrderYn("Y");
									orderInterfaceService.sendMileageTempData(mileSalesDTO);
							}
						//}
					}catch(Exception e){
						try{ // 매출오류시 온오프 사용 복원 처리
							if(isOffCouponUsed){ // 온오프 임시사용
								orderInterfaceService.restoreOnOffCpn(onOffCouponList, mbr.getErpCstmrNo(), ordNo);
							}
						}catch(Exception ie){
							log.error(ie.getMessage(),ie);
						}
						log.error(e.getMessage(),e);
						throw new OrderPGFailException("ord.error.not.complete.interface",null);
					}
				}else{
					if(bSumUsePnt.intValue() > 0){
						try{
							OrderParamDTO salesDTO = new OrderParamDTO();
							salesDTO.setOrdNo(ordNo);
							orderInterfaceService.sendMileageTempData(salesDTO);
						}catch(Exception e){
							try{ // 매출오류시 온오프 사용 복원 처리
								if(isOffCouponUsed){ // 온오프 임시사용
									orderInterfaceService.restoreOnOffCpn(onOffCouponList, mbr.getErpCstmrNo(), ordNo);
								}
							}catch(Exception ie){
								log.error(ie.getMessage(),ie);
							}
							log.error(e.getMessage(),e);
							throw new OrderPGFailException("ord.error.not.complete.interface",null);
						}
					}
				}
				
				
				if (!"VIRTL_BNK_ACCT_PAY".equals(orderDTO.getPay().getPayMnCd())) {
					try {
	
						log.debug("send talk or sms order {}",ordNo);
						
						
						ArrayList<String> paramList = new ArrayList<String>();
						
						
						if("MBM".equals(ord.getMallId())) {
							paramList.add(0, getConfigService().getProperty("ncp_base.talk.mlb.shop.name"));
							paramList.add(1, orderDTO.getOrd().getCstmrNm());
							SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
							String ordDt = df.format(orderDTO.getOrd().getOrdDt());
							paramList.add(2, ordDt);
							paramList.add(3, ordNo);
							paramList.add(4, orderDTO.getGodSummary());
							paramList.add(5, StringService.longToString(ord.getPayExchgRtCrncySumAmt().longValue()));
							paramList.add(6, getConfigService().getProperty("ncp.url.mb_MLB.root")+"/mypage/order/list");
						} else if("SAM".equals(ord.getMallId())) {
							paramList.add(0, getConfigService().getProperty("ncp_base.talk.sa.shop.name"));
							paramList.add(1, orderDTO.getOrd().getCstmrNm());
							SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
							String ordDt = df.format(orderDTO.getOrd().getOrdDt());
							paramList.add(2, ordDt);
							paramList.add(3, ordNo);
							paramList.add(4, orderDTO.getGodSummary());
							paramList.add(5, StringService.longToString(ord.getPayExchgRtCrncySumAmt().longValue()));
							paramList.add(6, getConfigService().getProperty("ncp.url.mb_SA.root")+"/mypage/order/list");
						} else {
							paramList.add(0, orderDTO.getOrd().getCstmrNm());
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
							String ordDt = df.format(orderDTO.getOrd().getOrdDt());
							paramList.add(1, ordDt);
							paramList.add(2, ordNo);
							paramList.add(3, orderDTO.getGodSummary());
							paramList.add(4, StringService.longToString(ord.getPayExchgRtCrncySumAmt().longValue())+"원");
							paramList.add(5, "("+orderDTO.getLgsDlvspDTOList().get(0).getLgsDlvsp().getAddrsePostNo()+") "+orderDTO.getLgsDlvspDTOList().get(0).getLgsDlvsp().getAddrseBaseAddr()+" "+orderDTO.getLgsDlvspDTOList().get(0).getLgsDlvsp().getAddrseDetailAddr());
							paramList.add(6, getConfigService().getProperty("ncp.url.mb_DX.root")+"/mypage/order/"+ordNo+"/view");
						}
						
						
						
						String callerId = OrderCommandComponentImpl.class.getName() + ".AlimTalkAddOrder";
						String mobileNumber = ord.getCstmrMobilAreaNo() + ord.getCstmrMobilTlofNo() + ord.getCstmrMobilTlofWthnNo();
	
						
						MPushAlimTalkSDO mPushAlimTalkSDO = new MPushAlimTalkSDO();
						if(ord.getMbrNo()!=null){
							mPushAlimTalkSDO.setMbrNo(ord.getMbrNo());
						}else{
							mPushAlimTalkSDO.setMbrNo("NMBR");
						}
						mPushAlimTalkSDO.setReceiveMobileNo(mobileNumber);
						mPushAlimTalkSDO.setMallId(systemPK.getMall());
						mPushAlimTalkSDO.setCallerId(callerId);
						mPushAlimTalkSDO.setSendDirectFlag(true);
						
						String tempMsgKey = "DXM_ORD_GENERAL_01";
						if("MBM".equals(ord.getMallId())) {
							tempMsgKey = "MBM_ORD_GENERAL_01";
						} else if("SAM".equals(ord.getMallId())) {
							tempMsgKey = "SAM_ORD_GENERAL_01";
						}
						mPushAlimTalkSDO.setMsgKey(tempMsgKey);
						mPushAlimTalkSDO.setParams(paramList);
						
						log.debug("sms alim talk sdo {}, systemPk {}",mPushAlimTalkSDO.toString(),systemPK);
						
						
						log.debug(mPushAdapter.sendAlimTalk(mPushAlimTalkSDO, systemPK));
	
						if (!StringService.isEmpty(ord.getMbrNo())) {
							String[][] usefCdInfo = { // 구분, 업무, 업무상세
									{UsefSectCd.PSNL_INFO_TRTMNT_CNSGN_DETL.toString(), UsefJobCd.SND_KKO_NTCN_TAK.toString(), UsefJobDetail.ORD_DETAIL.toString()}
							};
							this.insertMemberPersonalInfo(systemPK, ord.getMbrNo(), usefCdInfo);
						}
					} catch (Exception e) {
						log.error(e.getMessage(),e);
					}
					if(OrderEnum.ORD_TP_CD_SHOP_PKUP_ORD.toString().equals( ord.getOrdTpCd() )){
						try{
							this.sendMms4SM(ordNo,pickupShopId,orderDTO.getGodSummary(),systemPK);
						}catch (Exception e) {
							log.error(e.getMessage(),e);
						}
					}
				}else{
					try {
	
						log.debug("send talk or sms order {}",ordNo);
						
						ArrayList<String> paramList = new ArrayList<String>();
						
						if("MBM".equals(ord.getMallId())) {
							paramList.add(0, getConfigService().getProperty("ncp_base.talk.mlb.shop.name"));
							paramList.add(1, orderDTO.getOrd().getCstmrNm());
							SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
							String ordDt = df.format(orderDTO.getOrd().getOrdDt());
							paramList.add(2, ordDt);
							paramList.add(3, ordNo);
							paramList.add(4, orderDTO.getGodSummary());
							paramList.add(5, payList.get(0).getBnkNm());
							paramList.add(6, payList.get(0).getBnkAcctNo());
							paramList.add(7, StringService.longToString(ord.getPayExchgRtCrncySumAmt().longValue()));
							SimpleDateFormat dfT = new SimpleDateFormat("MM/dd");
							String reportDate = dfT.format(payList.get(0).getPayTmlmtDt());
							paramList.add(8, reportDate);
							paramList.add(9, getConfigService().getProperty("ncp.url.mb_MLB.root")+"/mypage/order/list");
						} else if("SAM".equals(ord.getMallId())) {
							paramList.add(0, getConfigService().getProperty("ncp_base.talk.sa.shop.name"));
							paramList.add(1, orderDTO.getOrd().getCstmrNm());
							SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
							String ordDt = df.format(orderDTO.getOrd().getOrdDt());
							paramList.add(2, ordDt);
							paramList.add(3, ordNo);
							paramList.add(4, orderDTO.getGodSummary());
							paramList.add(5, payList.get(0).getBnkNm());
							paramList.add(6, payList.get(0).getBnkAcctNo());
							paramList.add(7, StringService.longToString(ord.getPayExchgRtCrncySumAmt().longValue()));
							SimpleDateFormat dfT = new SimpleDateFormat("MM/dd");
							String reportDate = dfT.format(payList.get(0).getPayTmlmtDt());
							paramList.add(8, reportDate);
							paramList.add(9, getConfigService().getProperty("ncp.url.mb_SA.root")+"/mypage/order/list");
						} else {
							paramList.add(0, orderDTO.getOrd().getCstmrNm());
							SimpleDateFormat dfT = new SimpleDateFormat("yyyy-MM-dd");
							String reportDate = dfT.format(payList.get(0).getPayTmlmtDt());
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
							String ordDt = df.format(orderDTO.getOrd().getOrdDt());
							paramList.add(1, ordDt);
							paramList.add(2, ordNo);
							paramList.add(3, orderDTO.getGodSummary());
							paramList.add(4, payList.get(0).getBnkNm());
							paramList.add(5, payList.get(0).getBnkAcctNo());
							paramList.add(6, StringService.longToString(ord.getPayExchgRtCrncySumAmt().longValue())+"원");
							paramList.add(7, reportDate);
							paramList.add(8, getConfigService().getProperty("ncp.url.mb_DX.root")+"/mypage/order/"+ordNo+"/view");
						}
						
						
						String callerId = OrderCommandComponentImpl.class.getName() + ".AlimTalkAddOrderVirtual";
						String mobileNumber = ord.getCstmrMobilAreaNo() + ord.getCstmrMobilTlofNo() + ord.getCstmrMobilTlofWthnNo();
	
						
						MPushAlimTalkSDO mPushAlimTalkSDO = new MPushAlimTalkSDO();
						if(ord.getMbrNo()!=null){
							mPushAlimTalkSDO.setMbrNo(ord.getMbrNo());
						}else{
							mPushAlimTalkSDO.setMbrNo("NMBR");
						}
						mPushAlimTalkSDO.setReceiveMobileNo(mobileNumber);
						mPushAlimTalkSDO.setMallId(systemPK.getMall());
						mPushAlimTalkSDO.setCallerId(callerId);
						mPushAlimTalkSDO.setSendDirectFlag(true);
						
						String tempMsgKey = "DXM_ORD_GENERAL_02";
						if("MBM".equals(ord.getMallId())) {
							tempMsgKey = "MBM_ORD_GENERAL_02";
						} else if("SAM".equals(ord.getMallId())) {
							tempMsgKey = "SAM_ORD_GENERAL_02";
						}
						mPushAlimTalkSDO.setMsgKey(tempMsgKey);
						mPushAlimTalkSDO.setParams(paramList);
						
						log.debug("sms alim talk sdo {}, systemPk {}",mPushAlimTalkSDO.toString(),systemPK);
						
						
						log.debug(mPushAdapter.sendAlimTalk(mPushAlimTalkSDO, systemPK));
	
						if (!StringService.isEmpty(ord.getMbrNo())) {
							String[][] usefCdInfo = { // 구분, 업무, 업무상세
									{UsefSectCd.PSNL_INFO_TRTMNT_CNSGN_DETL.toString(), UsefJobCd.SND_KKO_NTCN_TAK.toString(), UsefJobDetail.ORD_DETAIL.toString()}
							};
							this.insertMemberPersonalInfo(systemPK, ord.getMbrNo(), usefCdInfo);
						}
					} catch (Exception e) {
						log.warn("ERROR !!! sending order SMS >>>>", e);
					}
				}
			}catch(Exception e){
				log.error(e.getMessage(),e);
				throw new OrderPGFailException(e.getMessage());
			}
			
			
			log.info("AddOrder_Complete");
			log.info(CommonResponseCode.OD_00002_주문_성공.toMessage());
		}
		catch (OrderPGFailException error) {
			//#47018 주문파트 일일점검 오류내역 상세화를 위하여 키바나 검색어 로그 추가 >> 2017-07-11
			if(null!=error.getParams()){
				log.warn("AddOrder_OrderPGFail : "+error.getParams()[0], error);
			}else{
				log.warn("AddOrder_OrderPGFail : params null", error);
			}
			
			// PG 승인건 취소처리
			this.cancelApprovePG(orderDTO);
			
			throw error;
		}
		catch (OrderCompleteFailException error) {
			//#47018 주문파트 일일점검 오류내역 상세화를 위하여 키바나 검색어 로그 추가 >> 2017-07-11
			log.warn("AddOrder_OrderCompleteFail / "+error.getDirectMessage()+"/"+error.getMessage()+"/"+error.getCustomKey(), error);
			
			throw error;
		}
		catch (Exception error) {
			
			log.warn("AddOrder_Fail", error);
			log.warn(CommonResponseCode.OD_40001_주문_실패.toMessage() + " exception : {}", error);
			log.warn("error message >>> " + error);
			// throw new Exception();
			
			
			
			throw new OrderCompleteFailException("ord.error.not_complete", null);
		}
	}

	@Override
	public void cancelApprovePG(OrderDTO orderDTO) {
		//mall 분리
		String mallId = "DXM";
		if(orderDTO.getMallId() != null) {
			mallId = orderDTO.getMallId();
		}
		
			// 결제 모듈 연동
			if(OrderEnum.PG_SECT_CD.NAVER_PAY.toString().equals(orderDTO.getPay().getPgSectCd())){
				
				// 망취소
				long cancelAmount = 0;
				if(orderDTO.getNaverPayApprove().getTempTotalPayment()!=null&&orderDTO.getNaverPayApprove().getTempTotalPayment().longValue()>0){
					cancelAmount = orderDTO.getNaverPayApprove().getTempTotalPayment().longValue();
				}else{
					cancelAmount = orderDTO.getPay().getPayCrncyPayAmt().longValue();
				}
						
				NaverCancelReturnDTO nCancelretDTO = naverPayService.nPayCancel(orderDTO.getNaverPayApprove().getPaymentId(),cancelAmount,"fail", mallId);

				
				// PG LOG
				try {
					PayPgIntrlckLog payLog = new PayPgIntrlckLog();
					payLog.setLogDt(orderDTO.getPay().getPayDt());
					payLog.setLogTpCd("CANCEL");
					payLog.setLogCont(nCancelretDTO.toJSON());
					payLog.setPayNo(orderDTO.getPay().getPayNo());
					payLog.setOrdNo(orderDTO.getOrd().getOrdNo());
//						payLog.setClmNo(String clmNo);
					payLog.setMbrNo(orderDTO.getPay().getRegtrId());
					payLog.setPgAprvNo(orderDTO.getPay().getPgAprvNo());

					orderCommandService.insertPayLogTransaction(payLog);
				} catch(Exception e) {
					log.warn(">>>>>>>>>>>>>>>>>>> PAY Cancel LOG ERROR <<<<<<<<<<<<<<<<<<<<<<");
				}
			}else if(OrderEnum.PG_SECT_CD.KCP_PAY.toString().equals(orderDTO.getPay().getPgSectCd())){
				if(orderDTO.getKcpDTO().getTno()!=null&&orderDTO.getKcpDTO().getTno().length()>0){ // transaction no 가 있을경우만 
					
					Mbr mbr = orderDTO.getMbr();   
			        boolean vip = false;  
					String cstrno = deliverySelectRepository.getVipList();
					if (mbr != null && mbr.getErpCstmrNo() != null && cstrno.indexOf(mbr.getErpCstmrNo()) != -1) {
						vip = true;
					}
					KcpReturnDTO kCancelretDTO = kcpPayService.approveCancel(orderDTO.getKcpDTO(), mallId,vip);
	
					
					// PG LOG
					try {
						PayPgIntrlckLog payLog = new PayPgIntrlckLog();
						payLog.setLogDt(orderDTO.getPay().getPayDt());
						payLog.setLogTpCd("CANCEL");
						payLog.setLogCont(kCancelretDTO.toJSON());
						payLog.setPayNo(orderDTO.getPay().getPayNo());
						payLog.setOrdNo(orderDTO.getOrd().getOrdNo());
	//						payLog.setClmNo(String clmNo);
						payLog.setMbrNo(orderDTO.getPay().getRegtrId());
						payLog.setPgAprvNo(orderDTO.getPay().getPgAprvNo());
	
						orderCommandService.insertPayLogTransaction(payLog);
					} catch(Exception e) {
						log.warn(">>>>>>>>>>>>>>>>>>> PAY Cancel LOG ERROR <<<<<<<<<<<<<<<<<<<<<<");
					}
				}
			}
	}
			
			
	/**
	 * PG 승인처리
	 * 
	 * @param orderDTO
	 * @param bSumPayAmt
	 * @param ordNo
	 * @throws Exception
	 */
	@Override
	public void approvePG(OrderDTO orderDTO, BigDecimal bSumPayAmt, String ordNo) throws Exception {		
		log.info("***********@@-approvePG- 결제 모듈 연동 start orderDTO : {}, bSumPayAmt : {}, ordNo : {}", orderDTO, bSumPayAmt, ordNo);
		
		if(OrderEnum.PG_SECT_CD.NAVER_PAY.toString().equals(orderDTO.getPay().getPgSectCd())){
			
			log.info("***********@@-approvePG- 결제 모듈 연동 NAVER_PAY");
			
			//mall 분리
			String mallId = "DXM";
			if(orderDTO.getMallId() != null) {
				mallId = orderDTO.getMallId();
			}
			
			NaverReturnDTO nretDTO = naverPayService.nPayPayment(orderDTO.getNaverPayApprove().getPaymentId(), mallId);
			
			orderEntitySetService.makeNaverPay(orderDTO,nretDTO);
				
			// PG LOG
			try {
				PayPgIntrlckLog payLog = new PayPgIntrlckLog();
				payLog.setLogDt(orderDTO.getPay().getPayDt());
				payLog.setLogTpCd("APPROVAL");
				payLog.setLogCont(nretDTO.toJSON());
				payLog.setPayNo(orderDTO.getPay().getPayNo());
				payLog.setOrdNo(ordNo);
//					payLog.setClmNo(String clmNo);
				payLog.setMbrNo(orderDTO.getPay().getRegtrId());
				payLog.setPgAprvNo(orderDTO.getPay().getPgAprvNo());

				orderCommandService.insertPayLogTransaction(payLog);
				
			} catch(Exception e) {
				log.warn(">>>>>>>>>>>>>>>>>>> PAY LOG ERROR <<<<<<<<<<<<<<<<<<<<<<");
				log.warn(">>>>>>>>>>>>>>>>>>> ORD_NO : " + orderDTO.getPay().getOrdNo());
				log.warn(">>>>>>>>>>>>>>>>>>> PAY_NO : " + orderDTO.getPay().getPayNo());
			}
			if(!"Success".equals(nretDTO.getCode())){
				throw new OrderCompleteFailException(nretDTO.getMessage());
			}
			if(bSumPayAmt.longValue() != nretDTO.getBody().getDetail().getTotalPayAmount().longValue()){
				log.error("order god list post {}", orderDTO.toJSON());
				log.warn(CommonResponseCode.OD_40006_고객결제금액과_서버결제금액_상이.toMessage()+" / bSumPayAmt : " + bSumPayAmt.toString()+" / naver pay amount : " + nretDTO.getBody().getDetail().getTotalPayAmount().toString());
				throw new OrderPGFailException(nretDTO.getMessage());
			}
			
			
		}else if(OrderEnum.PG_SECT_CD.KCP_PAY.toString().equals(orderDTO.getPay().getPgSectCd())){
			
			log.info("***********@@-approvePG- 결제 모듈 연동 KCP_PAY");
 
			Mbr mbr = orderDTO.getMbr();   
	        boolean vip = false;  
			String cstrno = deliverySelectRepository.getVipList();
			if (mbr != null && mbr.getErpCstmrNo() != null && cstrno.indexOf(mbr.getErpCstmrNo()) != -1) {
				vip = true;
			}
			KcpReturnDTO rkcpDTO = kcpPayService.approve(orderDTO.getKcpDTO(), bSumPayAmt.longValue(), orderDTO.getMallId(),vip);
			
			orderEntitySetService.makeKcpPay(orderDTO,rkcpDTO);
			
			// PG LOG
			try {
				PayPgIntrlckLog payLog = new PayPgIntrlckLog();
				payLog.setLogDt(orderDTO.getPay().getPayDt());
				payLog.setLogTpCd("APPROVAL");
				payLog.setLogCont(rkcpDTO.toJSON());
				payLog.setPayNo(orderDTO.getPay().getPayNo());
				payLog.setOrdNo(ordNo);
//					payLog.setClmNo(String clmNo);
				payLog.setMbrNo(orderDTO.getPay().getRegtrId());
				payLog.setPgAprvNo(orderDTO.getPay().getPgAprvNo());

				orderCommandService.insertPayLogTransaction(payLog);
			} catch(Exception e) {
				log.warn(">>>>>>>>>>>>>>>>>>> PAY LOG ERROR <<<<<<<<<<<<<<<<<<<<<<");
				log.warn(">>>>>>>>>>>>>>>>>>> ORD_NO : " + orderDTO.getPay().getOrdNo());
				log.warn(">>>>>>>>>>>>>>>>>>> PAY_NO : " + orderDTO.getPay().getPayNo());
			}
			if(!"0000".equals(rkcpDTO.getResCd())){
				log.warn(rkcpDTO.getResMsg());
				throw new OrderPGFailException(rkcpDTO.getResMsg());
			}
		}
	}


	private TreeBasedTable<Integer, String, String> makeOrderTable(OrderDTO orderDTO, List<CartResult> cartResultList, Locale locale)
	        throws Exception {
		// Table<Integer, String, String> table = HashBasedTable.create();
		TreeBasedTable<Integer, String, String> table = TreeBasedTable.create();
		HttpSession session = ContextService.getCurrentRequest().getSession();
		List<OrderCouponResult> memberCouponList = CollectionService.emptyListIfNull((List<OrderCouponResult>) session.getAttribute("SESSION_KEY_COUPON_LIST"));
		List<LgsDlvspDTO> lgsDlvspDTOList = orderDTO.getLgsDlvspDTOList();
		
		
		Ord ord = orderDTO.getOrd();
		Mbr mbr = orderDTO.getMbr();
		BigDecimal bSumUsePnt = this.nvl(ord.getUnityPntUseSumAmt()); // 사용맴버십포인트
		BigDecimal bSumUseWebpnt = this.nvl(ord.getWebpntUseSumAmt()); // 사용P포인트
		{
			int dlvPcupspTurn = 0;
			int godTurn = 0;

			for (LgsDlvspDTO lgsDlvspDTO : lgsDlvspDTOList) {
				dlvPcupspTurn++;
				String parentsGodTurnstr = "";

				List<CouponDTO> couponDTOList = CollectionService.emptyListIfNull(lgsDlvspDTO.getCouponDTOList());
				List<OrdGodDTO> ordGodDTOList = lgsDlvspDTO.getOrdGodDTOList();
				for (OrdGodDTO ordGodDTO : ordGodDTOList) {
					String delvBskNo = ordGodDTO.getBskNo();
					int delvBskGodTurn = ordGodDTO.getGodTurn();

					OrdGod ordGod = ordGodDTO.getOrdGod();
					int ordQty = (int) (long) ordGod.getOrdQty();

					if (ordQty > 0) {
						godTurn++;
						for (CartResult cart : cartResultList) {
							
							if(orderDTO.isEmp()){
								cart.setGodPrmDcAmt(BigDecimal.ZERO);
								cart.setBskPrmAplyDcAmt(BigDecimal.ZERO);
								cart.setBskPrmAplyBalancdDcAmt(BigDecimal.ZERO);
							}
							String bskNo = cart.getBskGod().getBskNo();
							int bskGodTurn = cart.getBskGod().getGodTurn();
							if (delvBskNo.equals(bskNo) && delvBskGodTurn == bskGodTurn) {

								BigDecimal bUnitRtlPrc = cart.getGod().getRtlPrc();
								BigDecimal bUnitSalePrc = cart.getPrice();
								BigDecimal bUnitBasicDc = bUnitRtlPrc.subtract(bUnitSalePrc);

								BigDecimal bUnitGodDc = this.nvl(cart.getGodPrmDcAmt());
								BigDecimal bUnitOrdDc = this.nvl(cart.getBskPrmAplyDcAmt());
								BigDecimal bUnitBalanceDc = (dlvPcupspTurn == 1 ? this.nvl(cart.getBskPrmAplyBalancdDcAmt()) : bUnitOrdDc);
								BigDecimal bOrdQty = new BigDecimal(ordQty);

								String prcType = cart.getPrcType();
								String prmType = cart.getBskPrmType();
								String packageYn = cart.getBskGod().getPckageGodYn();
								String additionalPack = "";
								if ("Y".equals(packageYn)) {
									if (bskGodTurn != cart.getParentGodTurn()) {
										additionalPack = "Y";
									}
								}

								// 배송지, 상품순번, 수량순번
								table.put(godTurn, "bskNo", bskNo);
								table.put(godTurn, "bskGodTurn", String.valueOf(bskGodTurn));
								table.put(godTurn, "dlvPcupspTurn", String.valueOf(dlvPcupspTurn));
								table.put(godTurn, "godTurn", String.valueOf(godTurn));
								
								if(OrderEnum.ORD_TP_CD_SHOP_PKUP_ORD.toString().equals( ord.getOrdTpCd() )){
									table.put(godTurn, "saleShopId", cart.getBskGod().getPkupShopSn());
								}else if("Y".equals(cart.getShopDlvItm())){
									table.put(godTurn, "shopDlvYn", "Y");
									table.put(godTurn, "saleShopId", "");
									table.put(godTurn, "hoffInvOrdQty", String.valueOf(0));
								}else{
									table.put(godTurn, "shopDlvYn", "N");
									
									String mallId = StringUtils.defaultString(orderDTO.getMallId(), "DXM");
									String saleShopId = OrderEnum.ORD_SALE_SHOP_CD_WMS.toString();
									if("MBM".equals(mallId)) {
										if("I".equals(cart.getGod().getBrndId())) {
											saleShopId = "I50002";
										} else {
											saleShopId = "M510";
										}										
									} else if("SAM".equals(mallId)) {
										saleShopId = "A30003";
									} 
									table.put(godTurn, "saleShopId", saleShopId );
									
									int hopeInvRest = 0;
									int wmsInvRest = cart.getRealInvQty()-cart.getHoffInvQty();
									
									if(wmsInvRest<0){
										hopeInvRest = ordQty;
									}else if((wmsInvRest-ordQty)<0){
										hopeInvRest = wmsInvRest+ordQty;
									}
									
									if(hopeInvRest>0){
										table.put(godTurn, "hoffInvOrdQty", String.valueOf(hopeInvRest));
									}else{
										table.put(godTurn, "hoffInvOrdQty", String.valueOf(0));
									}
								}
								
								table.put(godTurn, "godNm", cart.getGod().getGodNm());
								table.put(godTurn, "godNo", cart.getBskGod().getGodNo());
								table.put(godTurn, "itmNo", cart.getBskGod().getItmNo());
								table.put(godTurn, "skuNo", this.avoidNull(cart.getGodItm().getSkuNo(), ""));
								table.put(godTurn, "erpGodNo", this.avoidNull(cart.getGod().getErpGodNo(), ""));

								table.put(godTurn, "colorCd", this.avoidNull(cart.getGod().getColorCd(), ""));
								table.put(godTurn, "chipUrl", this.avoidNull(cart.getGod().getClorChipImgUrl(), ""));
								table.put(godTurn, "brndId", cart.getGod().getBrndId());
								table.put(godTurn, "invManageYn", this.avoidNull(cart.getGod().getInvManageYn(), ""));
								table.put(godTurn, "lmttInvYn", this.avoidNull(cart.getGod().getLmttInvYn(), ""));

								/*
								 * 2015-10-23 입점 구분 코드 추가 [AshA]
								 * ㅁ. 입점 구분 : PARTMAL_SECT
								 *    >. 자사 : MCOM
								 *    >. 입점 : PARTMAL
								 */
								table.put(godTurn, "partmalSectCd", cart.getGod().getPartmalSectCd());

								// 2015-11-18  상품 구분 코드 추가 [AshA]
								table.put(godTurn, "godTpCd", ordGod.getGodTpCd());

								table.put(godTurn, "imgUrl", this.avoidNull(cart.getImgUrl(), ""));
								table.put(godTurn, "ordQty", String.valueOf(ordQty));
								table.put(godTurn, "prcType", prcType); // GNRL,B2E,SINGLE,EMP
								table.put(godTurn, "packageYn", packageYn); // 세트상품
								table.put(godTurn, "parentsGodTurn", "Y".equals(additionalPack) ? parentsGodTurnstr : "");
								table.put(godTurn, "basicPack", ""); // 기본구성상품
								table.put(godTurn, "additionalPack", additionalPack); // 추가구성상품
								table.put(godTurn, "pckageGodTpCd", "Y".equals(additionalPack) ? "ADIT_CPST_GOD" : "");
								table.put(godTurn, "cpstGodQty", "Y".equals(additionalPack) ? String.valueOf(ordQty) : "");
								table.put(godTurn, "sortSeq", "Y".equals(additionalPack) ? String.valueOf(godTurn - Integer.parseInt(parentsGodTurnstr)) : "");
								table.put(godTurn, "cpstRt", "0"); // 기본구성상품비율
								table.put(godTurn, "cpstLast", "0");

								table.put(godTurn, "dlvFee", this.nvl(cart.getDmstcDlvCst()).toString());
								table.put(godTurn, "dlvLimitAmt", this.nvl(cart.getDmstcDlvCstExmStdrAmt()).toString());
								table.put(godTurn, "dlvFeeTp", this.avoidNull(cart.getDlvCstLevySectCd(), ""));
								table.put(godTurn, "dlvPolicy1", String.valueOf(cart.getGod().getDmstcDlvCstPlcSn()));
								table.put(godTurn, "dlvPolicy2", String.valueOf(cart.getGod().getOvseaDlvCstPlcSn()));
								table.put(godTurn, "dlvPolicy3", String.valueOf(cart.getGod().getOvseaDlvDmstcDlvCstPlcSn()));

								// 0. 상품판매가관련
								table.put(godTurn, "unitRtlPrc", bUnitRtlPrc.toString());	// 정소가
								table.put(godTurn, "salePrc", bUnitSalePrc.multiply(bOrdQty).toString());	// 판매가

								table.put(godTurn, "basicDc", bUnitBasicDc.multiply(bOrdQty).toString());
								table.put(godTurn, "webDc", "GNRL".equals(prcType) ? bUnitBasicDc.multiply(bOrdQty).toString() : "0");
								table.put(godTurn, "empDc", "EMP".equals(prcType) ? bUnitBasicDc.multiply(bOrdQty).toString() : "0");

								// 1. 상품할인
								String godDc = bUnitGodDc.multiply(bOrdQty).toString();
								table.put(godTurn, "signlDc", "SINGLE".equals(prcType) ? godDc : "0");
								table.put(godTurn, "b2eSpslDc", "B2E".equals(prcType) ? godDc : "0");
								table.put(godTurn, "gnrlGodDc", "GNRL".equals(prcType) ? godDc : "0");
								table.put(godTurn, "godDcPrmNo", this.avoidNull(cart.getGodPrmNo(), ""));
								table.put(godTurn, "godDc", godDc);

								// 2. 주문할인
								BigDecimal bOrdDc = bUnitOrdDc.multiply(bOrdQty).subtract(bUnitOrdDc).add(bUnitBalanceDc);
								String ordDc = bOrdDc.toString();
								table.put(godTurn, "bundleDc", "BUNDLE_DC".equals(prmType) ? ordDc : "0");
								table.put(godTurn, "crsDc", "CRS_DC".equals(prmType) ? ordDc : "0");
								table.put(godTurn, "ordDcPrmNo", bOrdDc.compareTo(BigDecimal.ZERO) > 0 ? this.avoidNull(cart.getBskPrmNo(), "") : "");
								table.put(godTurn, "ordDc", ordDc);

								// 3. 상품쿠폰
								table.put(godTurn, "godCpnPrmNo", "");
								table.put(godTurn, "godCpnCpnNo", "");
								table.put(godTurn, "godCpn", "0");

								// 4. 주문쿠폰
								table.put(godTurn, "ordCpnPrmNo", "");
								table.put(godTurn, "ordCpnCpnNo", "");
								table.put(godTurn, "ordCpn", "0");
								table.put(godTurn, "cpnCrtfcCd", "");
								table.put(godTurn, "isOffLineCoupon", "");

								// 5. 사용포인트
								// 2015-12-03 자사 상품인 경우에만 포인트 사용 [AshA]
								table.put(godTurn, "usePntYn",   ("MCOM".equals(cart.getGod().getPartmalSectCd()) ? this.avoidNull(cart.getGod().getPntUseYn(), "") : ""));
								table.put(godTurn, "usePnt", "0");

                                // 5-2. 사용포인트 (P포인트)
                                table.put(godTurn, "useWebpntYn", this.avoidNull(cart.getGod().getWebpntUseYn(), "") );
                                table.put(godTurn, "useWebpnt", "0");

								// 6. 적립포인트,추가적립포인트, 즉시사용포인트
								table.put(godTurn, "imdtlUsePntYn", "");
								table.put(godTurn, "imdtlUsePntRt", "0");
								table.put(godTurn, "imdtlUsePnt", "0");

								table.put(godTurn, "savePntYn", "");
								table.put(godTurn, "savePntRt", "0");
								table.put(godTurn, "savePnt", "0");
								table.put(godTurn, "addSavePntYn", "");
								table.put(godTurn, "addSavePnt", "0");
								table.put(godTurn, "addSavePntRt", "0");
								table.put(godTurn, "addSavePntTp", "");
								table.put(godTurn, "addSavePrmNo", "");
                                // P포인트
                                table.put(godTurn, "saveWebpntYn", "");
                                table.put(godTurn, "saveWebpntRt", "0");
                                table.put(godTurn, "saveWebpnt", "0");
                                table.put(godTurn, "addSaveWebpntYn", "");
                                table.put(godTurn, "addSaveWebpnt", "0");
                                table.put(godTurn, "addSaveWebpntRt", "0");
                                table.put(godTurn, "addSaveWebpntTp", "");
                                table.put(godTurn, "addSaveWebPrmNo", "");

								// 2015-12-03 자사 상품인 경우에만 즉시 포인트 사용 & 적립 포인트 적용 [AshA]
                            boolean imdtlUsePntYn = false;
                            boolean savePntYn = false;
                            boolean saveWebpntYn = false;
                             // 2015-12-03 자사 상품인 경우에만 즉시 포인트 사용 & 적립 포인트 적용 [AshA]
                            if ( ( "UNITY_MBR".equals(mbr.getMbrTpCd()) || "ONLINE_MBR".equals(mbr.getMbrTpCd()) )  && !"GFCT".equals(ordGod.getGodTpCd())) {
                                    // 자사상품 맴버십 적용
                                    if ("MCOM".equals(cart.getGod().getPartmalSectCd()) && "UNITY_MBR".equals(mbr.getMbrTpCd())) {
                                        if ("Y".equals(cart.getGod().getImdtlDcYn()) && "GNRL".equals(cart.getPrcType())) {
                                            imdtlUsePntYn = true;
                                        }
                                        if ("Y".equals(cart.getGod().getPntAccmlYn()) && !imdtlUsePntYn) {
                                            savePntYn = true;
                                        }
                                    }
                                // 자사,입점사 상품 P포인트 사용
                                if ("Y".equals(cart.getGod().getWebpntAccmlYn())) {
                                    saveWebpntYn = true;
                                }
                            }
                            
                            // ECOM-224 대량주문 마일리지 적립 제외처리 (2019-04-15)
                            String cstrno = deliverySelectRepository.getVipList();
                            if(mbr!=null&&mbr.getErpCstmrNo()!=null&&cstrno.indexOf(mbr.getErpCstmrNo())!=-1){
                            	savePntYn = false;
                            	saveWebpntYn = false;
                            }

                            if (imdtlUsePntYn) {
                                table.put(godTurn, "imdtlUsePntYn", "Y");
                                table.put(godTurn, "imdtlUsePntRt", cart.getGod().getImdtlDcRt().toString());
                            }
                            if (savePntYn) {
                                table.put(godTurn, "savePntYn", "Y");
                                
                                if(!orderDTO.isEmp()){
	                                table.put(godTurn, "savePntRt", cart.getGod().getPntAccmlRt().toString());
	
	                                // 추가적립
	                                if (!StringService.isEmpty(cart.getAditSavPrmNo())) {
	                                    table.put(godTurn, "addSavePntYn", "Y");
	                                    table.put(godTurn, "addSavePntTp", cart.getAccmlSectCd());
	                                    table.put(godTurn, "addSavePrmNo", cart.getAditSavPrmNo());
	
	                                    if ("FIXAMT".equals(cart.getAccmlSectCd())) {
	                                    table.put(godTurn, "addSavePnt", cart.getAccmlAmt().multiply(bOrdQty).toString());
	                                    }
	                                    else if ("FIXRT".equals(cart.getAccmlSectCd())) {
	                                        table.put(godTurn, "addSavePntRt", cart.getAccmlRt().toString());
	                                    }
	                                }
                                }else{
                                	table.put(godTurn, "savePntRt", "0");
                                }
                            }
                            // P포인트
                            if (saveWebpntYn) {
                                table.put(godTurn, "saveWebpntYn", "Y");
                                table.put(godTurn, "saveWebpntRt", cart.getGod().getWebpntAccmlRt().toString());
                                // 추가적립
                                if (!StringService.isEmpty(cart.getWebAditSavPrmNo())) {
                                    table.put(godTurn, "addSaveWebpntYn", "Y");
                                    table.put(godTurn, "addSaveWebpntTp", cart.getWebAccmlSectCd());
                                    table.put(godTurn, "addSaveWebPrmNo", cart.getWebAditSavPrmNo());

                                    if ("FIXAMT".equals(cart.getWebAccmlSectCd())) {
                                        table.put(godTurn, "addSaveWebpnt", cart.getWebAccmlAmt().multiply(bOrdQty).toString());
                                    }
                                    else if ("FIXRT".equals(cart.getWebAccmlSectCd())) {
                                        table.put(godTurn, "addSaveWebpntRt", cart.getWebAccmlRt().toString());
                                    }
                                }
								}

								// 3-1. 상품쿠폰 가격세팅
								BigDecimal bRowSalePrc = new BigDecimal(table.get(godTurn, "salePrc"));
								BigDecimal bRowGodDc = new BigDecimal(table.get(godTurn, "godDc"));
								BigDecimal bRowOrdDc = new BigDecimal(table.get(godTurn, "ordDc"));

								log.debug("bRowGodDc1====>"+bRowGodDc);
								log.debug("bRowOrdDc2====>"+bRowOrdDc);

								BigDecimal bRowPayPrc = bRowSalePrc.subtract(bRowGodDc).subtract(bRowOrdDc);
								BigDecimal bRowGodCpn = BigDecimal.ZERO;

								log.debug("bRowPayPrcServerPrice3====>"+bRowPayPrc);
								boolean isPrdCpn = false;
								{
									BigDecimal bUnitGodCpn = BigDecimal.ZERO;
									for (CouponDTO couponDTO : couponDTOList) {
										String cpnBskNo = couponDTO.getBskNo();
										int cpnBskGodTurn = couponDTO.getGodTurn();

										if (delvBskNo.equals(cpnBskNo) && delvBskGodTurn == cpnBskGodTurn) {
											// 상품쿠폰
											if (!StringService.isEmpty(couponDTO.getGoodsPrmNo())) {
												for (OrderCouponResult memberCoupon : memberCouponList) { // member coupon list
													for (GoodsCouponResult mCoupon : memberCoupon.getGoodsCouponResultList()) {
														if (couponDTO.getGoodsPrmNo().equals(mCoupon.getPrm().getPrmNo())) {
															boolean usedCpn = false;
															if ("IMDTL_DC".equals(mCoupon.getPrmCpn().getCpnIssuMthdCd())) {
																usedCpn = true;
															} else {
																if (couponDTO.getGoodsCouponNo().equals(mCoupon.getMbrCpnNo())) {
																	usedCpn = true;
																}
															}
															if (usedCpn) {
																isPrdCpn = true;
																String dcTp = mCoupon.getPrm().getDcSectCd();
																if ("FIXAMT".equals(dcTp)) {
																	bUnitGodCpn = mCoupon.getPrm().getDcAmt();
																}
																else if ("FIXRT".equals(dcTp)) {
																	BigDecimal bTmpAmt = bUnitSalePrc.subtract(bUnitGodDc).subtract(bUnitOrdDc);
																	bUnitGodCpn = bTmpAmt.multiply(mCoupon.getPrm().getDcRt().divide(new BigDecimal(100)));
																	bUnitGodCpn = this.halfup(bUnitGodCpn, 1);	// 상품쿠폰 - 수량당 1원자리올림 계산
																}
																bRowGodCpn = bUnitGodCpn.multiply(bOrdQty);
																bRowGodCpn = this.checkMinus(bRowPayPrc, bRowGodCpn);

																table.put(godTurn, "godCpnPrmNo", mCoupon.getPrm().getPrmNo());
																table.put(godTurn, "godCpnCpnNo", this.avoidNull(mCoupon.getMbrCpnNo(), ""));
																table.put(godTurn, "godCpn", bRowGodCpn.toString());
															}
														}
													}
												}
											}
											// for 주문쿠폰
											if (!StringService.isEmpty(couponDTO.getOrderCouponNo())) {
												for (OrderCouponResult memberCoupon : memberCouponList) { // member coupon list
													for (GoodsCouponResult mCoupon : memberCoupon.getGoodsCouponResultList()) {
														if (couponDTO.getOrderCouponNo().equals(mCoupon.getMbrCpnNo())) {
															table.put(godTurn, "ordCpnPrmNo", mCoupon.getPrm().getPrmNo());
															table.put(godTurn, "ordCpnCpnNo", mCoupon.getMbrCpnNo());
															table.put(godTurn, "ordCpn", "0");
															table.put(godTurn, "cpnCrtfcCd", this.avoidNull(mCoupon.getCpnCrtfcCd(), ""));
															table.put(godTurn, "isOffLineCoupon", "ONOFLNE_CPN".equals(mCoupon.getPrmCpn().getCpnKndCd()) ? "1" : "0");

//															bOrdCpnApplyAmt = bUnitSalePrc.subtract(bUnitGodDc).subtract(bUnitOrdDc).subtract(bUnitGodCpn);
//															bOrdCpnLimitAmt = bUnitSalePrc.subtract(bUnitGodDc);
//															ordCpnCnt++;
															
															if(isPrdCpn&&"IMPS".equals(mCoupon.getPrm().getGodCpnDplctCd())){
																throw new OrderCompleteFailException("ord.error.invalid_ord_coupon", null);
															}
														}
													}
												}
											}
										}
									}
								}

								log.debug("bRowPayPrcServerPrice4====>"+bRowPayPrc);

								bRowPayPrc = bRowPayPrc.subtract(bRowGodCpn);

								log.debug("bRowPayPrcServerPrice5====>"+bRowPayPrc);

								table.put(godTurn, "rowPayPrc", bRowPayPrc.toString());



								// 패키지형 상품의 기본구성상품
								if ("Y".equals(packageYn) && !"Y".equals(additionalPack)) {
									List<CartCpstGodResult> cpstGodList = cartSelectService.selectBskCpstGodCnnc(cart.getBskGod());

									BigDecimal bSumCpstRtlPrc = BigDecimal.ZERO;
									for (CartCpstGodResult cpst : cpstGodList) {
										long cpstQty = cpst.getBskCpstGodCnnc().getCpstGodQty();
										bSumCpstRtlPrc = bSumCpstRtlPrc.add(cpst.getGod().getRtlPrc().multiply(new BigDecimal(cpstQty)));
									}

									parentsGodTurnstr = String.valueOf(godTurn);
									int parentsGodTurn = godTurn;
									int looping = 0;
									int cpstlength = cpstGodList.size();
									for (CartCpstGodResult cpst : cpstGodList) {
										looping++;
										godTurn++;

										long cpstQty = cpst.getBskCpstGodCnnc().getCpstGodQty();
										BigDecimal bCpstQty = new BigDecimal(cpstQty);

										// 0. 상품판매가관련
										BigDecimal bRowCpstRtlPrc = cpst.getGod().getRtlPrc().multiply(bCpstQty);
										BigDecimal bRowRate = bRowCpstRtlPrc.divide(bSumCpstRtlPrc, 10, RoundingMode.DOWN);
										table.put(godTurn, "cpstRt", bRowRate.toString()); // 기본구성상품의 가격비율
										table.put(godTurn, "cpstLast", "0");

										BigDecimal bParentsSalePrc = new BigDecimal(table.get(parentsGodTurn, "salePrc"));
										BigDecimal bParentsBasicDc = new BigDecimal(table.get(parentsGodTurn, "basicDc"));
										BigDecimal bParentsGodDc = new BigDecimal(table.get(parentsGodTurn, "godDc"));
										BigDecimal bParentsOrdDc = new BigDecimal(table.get(parentsGodTurn, "ordDc"));
										BigDecimal bParentsGodCpn = new BigDecimal(table.get(parentsGodTurn, "godCpn"));
										BigDecimal bParentsPayPrc = new BigDecimal(table.get(parentsGodTurn, "rowPayPrc"));

										BigDecimal bRowCpstSalePrc = this.goodsDivision(bParentsSalePrc, bRowRate);
										BigDecimal bRowCpstBasicDc = this.goodsDivision(bParentsBasicDc, bRowRate);
										BigDecimal bRowCpstGodDc = this.goodsDivision(bParentsGodDc, bRowRate);
										BigDecimal bRowCpstOrdDc = this.goodsDivision(bParentsOrdDc, bRowRate);
										BigDecimal bRowCpstGodCpn = this.goodsDivision(bParentsGodCpn, bRowRate);
//										BigDecimal bUnitCpstPayPrc = this.goodsDivision(bParentsUnitPayPrc, bRowRate);
										BigDecimal bRowCpstPayPrc = this.goodsDivision(bParentsPayPrc, bRowRate);

										if (cpstlength == looping) { // last row
											table.put(godTurn, "cpstLast", "1");
											BigDecimal bRestCpstSalePrc = BigDecimal.ZERO;
											BigDecimal bRestCpstBasicDc = BigDecimal.ZERO;
											BigDecimal bRestCpstGodDc = BigDecimal.ZERO;
											BigDecimal bRestCpstOrdDc = BigDecimal.ZERO;
											BigDecimal bRestCpstGodCpn = BigDecimal.ZERO;
//											BigDecimal bRestUnitCpstPayPrc = BigDecimal.ZERO;
											BigDecimal bRestCpstPayPrc = BigDecimal.ZERO;

											Map<Integer, String> packages = table.column("parentsGodTurn");
											for (Map.Entry<Integer, String> pkg : packages.entrySet()) {
												if (parentsGodTurnstr.equals(pkg.getValue())) {
													int cpstGodTurn = pkg.getKey();
													bRestCpstSalePrc = bRestCpstSalePrc.add(new BigDecimal(table.get(cpstGodTurn, "salePrc")));
													bRestCpstBasicDc = bRestCpstBasicDc.add(new BigDecimal(table.get(cpstGodTurn, "basicDc")));
													bRestCpstGodDc = bRestCpstGodDc.add(new BigDecimal(table.get(cpstGodTurn, "godDc")));
													bRestCpstOrdDc = bRestCpstOrdDc.add(new BigDecimal(table.get(cpstGodTurn, "ordDc")));
													bRestCpstGodCpn = bRestCpstGodCpn.add(new BigDecimal(table.get(cpstGodTurn, "godCpn")));
													bRestCpstPayPrc = bRestCpstPayPrc.add(new BigDecimal(table.get(cpstGodTurn, "rowPayPrc")));
												}
											}

											// 나머지 값으로 보정
											bRowCpstSalePrc = bParentsSalePrc.subtract(bRestCpstSalePrc);
											bRowCpstBasicDc = bParentsBasicDc.subtract(bRestCpstBasicDc);
											bRowCpstGodDc = bParentsGodDc.subtract(bRestCpstGodDc);
											bRowCpstOrdDc = bParentsOrdDc.subtract(bRestCpstOrdDc);
											bRowCpstGodCpn = bParentsGodCpn.subtract(bRestCpstGodCpn);
//											bUnitCpstPayPrc = bParentsUnitPayPrc.subtract(bRestUnitCpstPayPrc);
											bRowCpstPayPrc = bParentsPayPrc.subtract(bRestCpstPayPrc);
										}

										// 배송지, 상품순번, 수량순번
										table.put(godTurn, "bskNo", bskNo);
										table.put(godTurn, "bskGodTurn", String.valueOf(bskGodTurn));
										table.put(godTurn, "dlvPcupspTurn", table.get(godTurn - 1, "dlvPcupspTurn"));
										table.put(godTurn, "godTurn", String.valueOf(godTurn));

										table.put(godTurn, "godNm", cpst.getGod().getGodNm());
										table.put(godTurn, "godNo", cpst.getGodNo());
										table.put(godTurn, "itmNo", cpst.getItmNo());
										table.put(godTurn, "skuNo", cpst.getSkuNo());
										table.put(godTurn, "erpGodNo", cpst.getGod().getErpGodNo());
										
										table.put(godTurn, "shopDlvYn", table.get(parentsGodTurn, "shopDlvYn"));
										
										// 패키지는 WMS
										//mall 분리
										String saleShopId = OrderEnum.ORD_SALE_SHOP_CD_WMS.toString();
										if("M".equals(cpst.getGod().getBrndId())) {
											saleShopId = "M510";
										} else if("I".equals(cpst.getGod().getBrndId())) {
											saleShopId = "I50002";
										} else if("A".equals(cpst.getGod().getBrndId())) {
											saleShopId = "A30003";
										}
										table.put(godTurn, "saleShopId", saleShopId );
										

										table.put(godTurn, "colorCd", this.avoidNull(cpst.getGod().getColorCd(), ""));
										table.put(godTurn, "chipUrl", this.avoidNull(cpst.getGod().getClorChipImgUrl(), ""));
										table.put(godTurn, "brndId", cpst.getGod().getBrndId());
										table.put(godTurn, "invManageYn", table.get(godTurn - 1, "invManageYn"));
										table.put(godTurn, "lmttInvYn", table.get(godTurn - 1, "lmttInvYn"));

										/*
										 * 2015-10-23 입점 구분 코드 추가 [AshA]
										 * ㅁ. 입점 구분 : PARTMAL_SECT
										 *    >. 자사 : MCOM
										 *    >. 입점 : PARTMAL
										 */
										table.put(godTurn, "partmalSectCd", table.get(godTurn - 1, "partmalSectCd"));

										table.put(godTurn, "imgUrl", "");
										table.put(godTurn, "ordQty", String.valueOf(ordQty * cpstQty));
										table.put(godTurn, "prcType", prcType); // GNRL,B2E,SINGLE,EMP
										table.put(godTurn, "packageYn", "Y"); // 세트상품
										table.put(godTurn, "parentsGodTurn", String.valueOf(parentsGodTurn));
										table.put(godTurn, "basicPack", "Y");	// 기본구성상품
										table.put(godTurn, "additionalPack", ""); // 추가구성상품
										table.put(godTurn, "pckageGodTpCd", cpst.getBskCpstGodCnnc().getPckageGodTpCd());
										table.put(godTurn, "cpstGodQty", bCpstQty.toString());
										table.put(godTurn, "sortSeq", String.valueOf(cpst.getBskCpstGodCnnc().getSortSeq()));

										table.put(godTurn, "dlvFee", table.get(godTurn - 1, "dlvFee"));
										table.put(godTurn, "dlvLimitAmt", table.get(godTurn - 1, "dlvLimitAmt"));
										table.put(godTurn, "dlvFeeTp", table.get(godTurn - 1, "dlvFeeTp"));
										table.put(godTurn, "dlvPolicy1", table.get(godTurn - 1, "dlvPolicy1"));
										table.put(godTurn, "dlvPolicy2", table.get(godTurn - 1, "dlvPolicy2"));
										table.put(godTurn, "dlvPolicy3", table.get(godTurn - 1, "dlvPolicy3"));

										table.put(godTurn, "unitRtlPrc", cpst.getGod().getRtlPrc().toString()); // 정소가
										table.put(godTurn, "salePrc", bRowCpstSalePrc.toString()); // 판매가
										table.put(godTurn, "basicDc", bRowCpstBasicDc.toString());
										table.put(godTurn, "webDc", "GNRL".equals(prcType) ? bRowCpstBasicDc.toString() : "0");
										table.put(godTurn, "empDc", "EMP".equals(prcType) ? bRowCpstBasicDc.toString() : "0");

										// 1. 상품할인
										table.put(godTurn, "signlDc", "SINGLE".equals(prcType) ? bRowCpstGodDc.toString() : "0");
										table.put(godTurn, "b2eSpslDc", "B2E".equals(prcType) ? bRowCpstGodDc.toString() : "0");
										table.put(godTurn, "gnrlGodDc", "GNRL".equals(prcType) ? bRowCpstGodDc.toString() : "0");
										table.put(godTurn, "godDcPrmNo", table.get(godTurn - 1, "godDcPrmNo"));
										table.put(godTurn, "godDc", bRowCpstGodDc.toString());

										// 2. 주문할인
										table.put(godTurn, "bundleDc", "BUNDLE_DC".equals(prmType) ? bRowCpstOrdDc.toString() : "0");
										table.put(godTurn, "crsDc", "CRS_DC".equals(prmType) ? bRowCpstOrdDc.toString() : "0");
										table.put(godTurn, "ordDcPrmNo", table.get(godTurn - 1, "ordDcPrmNo"));
										table.put(godTurn, "ordDc", bRowCpstOrdDc.toString());

										// 3. 상품쿠폰
										table.put(godTurn, "godCpnPrmNo", table.get(godTurn - 1, "godCpnPrmNo"));
										table.put(godTurn, "godCpnCpnNo", table.get(godTurn - 1, "godCpnCpnNo"));
										table.put(godTurn, "godCpn", bRowCpstGodCpn.toString());

										// 4. 주문쿠폰
										table.put(godTurn, "ordCpnPrmNo", table.get(godTurn - 1, "ordCpnPrmNo"));
										table.put(godTurn, "ordCpnCpnNo", table.get(godTurn - 1, "ordCpnCpnNo"));
										table.put(godTurn, "ordCpn", "0");
										table.put(godTurn, "cpnCrtfcCd", table.get(godTurn - 1, "cpnCrtfcCd"));
										table.put(godTurn, "isOffLineCoupon", table.get(godTurn - 1, "isOffLineCoupon"));


										// 5. 사용포인트
										table.put(godTurn, "usePntYn", table.get(godTurn - 1, "usePntYn"));
										table.put(godTurn, "usePnt", "0");

                                        // 5-2 사용포인트 ( P포인트)
                                        table.put(godTurn, "useWebpntYn", table.get(godTurn - 1, "useWebpntYn"));
                                        table.put(godTurn, "useWebpnt", "0");

										// 6. 적립포인트, 추가적립포인트, 즉시사용포인트
										table.put(godTurn, "imdtlUsePntYn", table.get(godTurn - 1, "imdtlUsePntYn"));
										table.put(godTurn, "imdtlUsePntRt", table.get(godTurn - 1, "imdtlUsePntRt"));
										table.put(godTurn, "imdtlUsePnt", "0");

										table.put(godTurn, "savePntYn", table.get(godTurn - 1, "savePntYn"));
										table.put(godTurn, "savePntRt", table.get(godTurn - 1, "savePntRt"));
										table.put(godTurn, "savePnt", "0");
										table.put(godTurn, "addSavePntYn", table.get(godTurn - 1, "addSavePntYn"));
										table.put(godTurn, "addSavePnt", "0");
										table.put(godTurn, "addSavePntRt", table.get(godTurn - 1, "addSavePntRt"));
										table.put(godTurn, "addSavePntTp", table.get(godTurn - 1, "addSavePntTp"));
										table.put(godTurn, "addSavePrmNo", table.get(godTurn - 1, "addSavePrmNo"));

                                        table.put(godTurn, "saveWebpntYn", table.get(godTurn - 1, "saveWebpntYn"));
                                        table.put(godTurn, "saveWebpntRt", table.get(godTurn - 1, "saveWebpntRt"));
                                        table.put(godTurn, "saveWebpnt", "0");
                                        table.put(godTurn, "addSaveWebpntYn", table.get(godTurn - 1, "addSaveWebpntYn"));
                                        table.put(godTurn, "addSaveWebpnt", "0");
                                        table.put(godTurn, "addSaveWebpntRt", table.get(godTurn - 1, "addSaveWebpntRt"));
                                        table.put(godTurn, "addSaveWebpntTp", table.get(godTurn - 1, "addSaveWebpntTp"));
                                        table.put(godTurn, "addSaveWebPrmNo", table.get(godTurn - 1, "addSaveWebPrmNo"));

										table.put(godTurn, "rowPayPrc", bRowCpstPayPrc.toString());
									}
								}

								//if ( cart.getGod().getDmstcDlvCstPlcSn() != ordGod.getDmstcDlvCstPlcSn() ) {
								if ( ! cart.getGod().getDmstcDlvCstPlcSn().equals( ordGod.getDmstcDlvCstPlcSn() ) ) {
									if ( log.isErrorEnabled() ) {
										log.warn(">>[makeOrderTable] Error has occurred. dmstc_dlv_cst_plc_sn[{}/{}/{}/{}/{}]",
												ordGod.getGodNo(), bskNo, String.valueOf( bskGodTurn ), cart.getGod().getDmstcDlvCstPlcSn(), ordGod.getDmstcDlvCstPlcSn() );
									}									
									log.warn(CommonResponseCode.OD_40038_배송정책_정합성_에러.toMessage());
									throw new Exception("배송정책 정합성 에러!!!");
								}
							}
						}
					}
				}
			}
		}

		// 4-1. 주문쿠폰 분배
		{
			BigDecimal bOrdCpnLimitAmt = BigDecimal.ZERO;
			BigDecimal bOrdCpnApplyAmt = BigDecimal.ZERO;
			BigDecimal bStackAmt = BigDecimal.ZERO;
			BigDecimal bSumOrdCpn = BigDecimal.ZERO;
			int ordCpnCnt = 0;

			// 주문쿠폰 적용대상금액, 기준금액 추출
			Map<Integer, String> coupons = table.column("ordCpnCpnNo");
			for (Map.Entry<Integer, String> cpn : coupons.entrySet()) {
				int godTurn = cpn.getKey();
				String mbrCpnNo = cpn.getValue();
				if (!StringService.isEmpty(mbrCpnNo) && StringService.isEmpty(table.get(godTurn, "basicPack"))) {
					BigDecimal bRowPayPrc = new BigDecimal(table.get(godTurn, "rowPayPrc"));
					BigDecimal ordDc = new BigDecimal(table.get(godTurn, "ordDc"));
					BigDecimal godCpn = new BigDecimal(table.get(godTurn, "godCpn"));

					log.debug("couponbRowPayPrc6====>"+bRowPayPrc);

					bOrdCpnApplyAmt = bOrdCpnApplyAmt.add(bRowPayPrc);
					bOrdCpnLimitAmt = bOrdCpnLimitAmt.add(bRowPayPrc).add(ordDc).add(godCpn);	// 상품할인까지만,
					ordCpnCnt++;
				}
			}

			for (Map.Entry<Integer, String> cpn : coupons.entrySet()) {
				int godTurn = cpn.getKey();
				String mbrCpnNo = cpn.getValue();
				if (!StringService.isEmpty(mbrCpnNo) && StringService.isEmpty(table.get(godTurn, "basicPack"))) {

					if (bSumOrdCpn.compareTo(BigDecimal.ZERO) == 0) {	// 최초 1번만 주문쿠폰금액 계산
						for (OrderCouponResult memberCoupon : memberCouponList) { // member coupon list
							for (GoodsCouponResult mCoupon : memberCoupon.getGoodsCouponResultList()) {
								if (mbrCpnNo.equals(mCoupon.getMbrCpnNo())) {
									String dcTp = mCoupon.getPrm().getDcSectCd();
									BigDecimal bLimitAmt = mCoupon.getPrmCpn().getCpnUseMinPchAmt(); // 최소주문금액(복수)
									if (bOrdCpnLimitAmt.compareTo(bLimitAmt) < 0) {
										log.warn(CommonResponseCode.OD_40012_장바구니_쿠폰_최소구매금액_조건_미달.toMessage());
										throw new OrderCompleteFailException("ord.error.invalid_ord_coupon", null);
									}

									if ("FIXAMT".equals(dcTp)) {
										bSumOrdCpn = mCoupon.getPrm().getDcAmt();
									}
									else if ("FIXRT".equals(dcTp)) {
										bSumOrdCpn = bOrdCpnApplyAmt.multiply(mCoupon.getPrm().getDcRt().divide(new BigDecimal(100)));
										bSumOrdCpn = this.down(bSumOrdCpn, 1); // 주문쿠폰:1원절사

										BigDecimal bMaxDcPsbAmt = mCoupon.getPrmCpn().getCpnMaxDcPsbAmt();// 최대할인금액(복수)
										bSumOrdCpn = this.checkMinus(bMaxDcPsbAmt, bSumOrdCpn);
									}
									bSumOrdCpn = this.checkMinus(bOrdCpnApplyAmt, bSumOrdCpn);
								}
							}
						}
					}

					BigDecimal bRowPayPrc = new BigDecimal(table.get(godTurn, "rowPayPrc"));

					log.debug("RowPayPrc7====>"+bRowPayPrc);

					BigDecimal bRowOrdCpn = this.goodsDivision(bSumOrdCpn, bRowPayPrc, bOrdCpnApplyAmt);
					ordCpnCnt--;
					if (ordCpnCnt == 0) {	// last
						bRowOrdCpn = bSumOrdCpn.subtract(bStackAmt);
					}
					bRowOrdCpn = this.checkMinus(bRowPayPrc, bRowOrdCpn);
					bStackAmt = bStackAmt.add(bRowOrdCpn);

					table.put(godTurn, "ordCpn", bRowOrdCpn.toString());
					table.put(godTurn, "rowPayPrc", bRowPayPrc.subtract(bRowOrdCpn).toString());
				}
			}
		}

		// 0원 상품에 대한 제어
		Map<Integer, String> rowPays = table.column("rowPayPrc");
		for (Map.Entry<Integer, String> rowPay : rowPays.entrySet()) {
			int godTurn = rowPay.getKey();
			BigDecimal bRowPayPrc = new BigDecimal(rowPay.getValue());
			if (bRowPayPrc.compareTo(BigDecimal.ZERO) <= 0) {
				List<String> paramList = new ArrayList<String>();
				paramList.add(table.get(godTurn, "godNm"));
				String[] params = paramList.toArray(new String[paramList.size()]);
				String exceptionMessage = messageSourceCmpAccessor.getMessage("ord.error.invalid_goods_price", params, locale);
				throw new OrderCompleteFailException(exceptionMessage);
			}
		}

        // 5-1. 사용포인트 분배 (P포인트 우선적용)
		// #35633 : 분배로직변경
		if (bSumUseWebpnt.compareTo(BigDecimal.ZERO) > 0) {
            int usePntCnt = 0;
			BigDecimal bUsePntApplyAmt = BigDecimal.ZERO;
			BigDecimal bStackAmt = BigDecimal.ZERO;

            Map<Integer, String> usePoints = table.column("useWebpntYn");
            for (Map.Entry<Integer, String> point : usePoints.entrySet()) {
                int godTurn = point.getKey();
                String usePntYn = point.getValue();
                log.info("P포인트사용분배 ::: godTurn  {}, usePntYN {}", godTurn, usePntYn);
                if ("Y".equals(usePntYn) && StringService.isEmpty(table.get(godTurn, "basicPack"))) {
                    BigDecimal bRowPayPrc = new BigDecimal(table.get(godTurn, "rowPayPrc"));
                    bUsePntApplyAmt = bUsePntApplyAmt.add(bRowPayPrc);
                    usePntCnt++;
                    log.info("P포인트사용분배 ::: bRowPayPrc  {}, bUsePntApplyAmt {}", bRowPayPrc, bUsePntApplyAmt);
                }
            }
			log.info("P포인트사용분배 ::: 사용금액={}, 적용대상금액={}", bSumUseWebpnt, bUsePntApplyAmt);

            BigDecimal bRemainAmt = bSumUseWebpnt;
            for (Map.Entry<Integer, String> point : usePoints.entrySet()) {
                int godTurn = point.getKey();
                String usePntYn = point.getValue();
                if ("Y".equals(usePntYn) && StringService.isEmpty(table.get(godTurn, "basicPack"))) {
					usePntCnt--;
					BigDecimal bRowPayPrc = new BigDecimal(table.get(godTurn, "rowPayPrc"));
                    BigDecimal bRowUsePnt = this.goodsDivision(bSumUseWebpnt, bRowPayPrc, bUsePntApplyAmt, "HALF_UP");
                    log.info("P포인트사용분배 :::godTurn {}  bRowPayPrc={}, bRowUsePnt={}", godTurn, bRowPayPrc, bRowUsePnt);

					if(bRemainAmt.compareTo(BigDecimal.ZERO) == 0){
                    	bRowUsePnt = BigDecimal.ZERO;
                    }else if(usePntCnt == 0  || bRowUsePnt.compareTo(bRemainAmt) >= 0){
                    	bRowUsePnt = bRemainAmt;
                    }

					bStackAmt = bStackAmt.add(bRowUsePnt);
                    bRemainAmt = bRemainAmt.subtract(bRowUsePnt);
                    log.info("P포인트사용분배 :::godTurn {}  bStackAmt={}, bRemainAmt={}, bSumUseWebpnt={}", godTurn, bStackAmt, bRemainAmt, bSumUseWebpnt);
                    table.put(godTurn, "useWebpnt", bRowUsePnt.toString());
                    table.put(godTurn, "rowPayPrc", bRowPayPrc.subtract(bRowUsePnt).toString());

					log.info("P포인트사용분배 :::  분배금액={}, bRowPayPrc={}", bRowUsePnt, bRowPayPrc);
                }
            }

            if(bSumUseWebpnt.compareTo(bStackAmt) != 0){
            	log.warn(CommonResponseCode.OD_40007_P포인트_사용금액과_분배금액_불일치.toMessage()+" 사용금액={}, 분배합={}", bSumUseWebpnt, bStackAmt);
            	throw new OrderCompleteFailException("ord.error.invalid_price", null);
            }
        }


        // 5-2. 사용포인트 분배 (맴버십포인트)
		// #35633 : 분배로직변경
		if (bSumUsePnt.compareTo(BigDecimal.ZERO) > 0) {
            int usePntCnt = 0;
			BigDecimal bUsePntApplyAmt = BigDecimal.ZERO;
			BigDecimal bStackAmt = BigDecimal.ZERO;

            Map<Integer, String> usePoints = table.column("usePntYn");
            for (Map.Entry<Integer, String> point : usePoints.entrySet()) {
                int godTurn = point.getKey();
                String usePntYn = point.getValue();
                if ("Y".equals(usePntYn) && StringService.isEmpty(table.get(godTurn, "basicPack"))) {
                    BigDecimal bRowPayPrc = new BigDecimal(table.get(godTurn, "rowPayPrc"));
                    bUsePntApplyAmt = bUsePntApplyAmt.add(bRowPayPrc);
                    usePntCnt++;
                }
            }
			log.info("멤버쉽포인트사용분배 ::: 사용금액={}, 적용대상금액={}", bSumUsePnt, bUsePntApplyAmt);

            BigDecimal bRemainAmt = bSumUsePnt;
            for (Map.Entry<Integer, String> point : usePoints.entrySet()) {
                int godTurn = point.getKey();
                String usePntYn = point.getValue();
                if ("Y".equals(usePntYn) && StringService.isEmpty(table.get(godTurn, "basicPack"))) {
					usePntCnt--;
					BigDecimal bRowPayPrc = new BigDecimal(table.get(godTurn, "rowPayPrc"));
                    BigDecimal bRowUsePnt = this.goodsDivision(bSumUsePnt, bRowPayPrc, bUsePntApplyAmt, "HALF_UP");

					if(bRemainAmt.compareTo(BigDecimal.ZERO) == 0){
                    	bRowUsePnt = BigDecimal.ZERO;
                    }else if(usePntCnt == 0  || bRowUsePnt.compareTo(bRemainAmt) >= 0){
                    	bRowUsePnt = bRemainAmt;
                    }

					bStackAmt = bStackAmt.add(bRowUsePnt);
                    bRemainAmt = bRemainAmt.subtract(bRowUsePnt);

                    table.put(godTurn, "usePnt", bRowUsePnt.toString());
                    table.put(godTurn, "rowPayPrc", bRowPayPrc.subtract(bRowUsePnt).toString());

					log.info("멤버쉽포인트사용분배 :::  분배금액={}, bRowPayPrc={}", bRowUsePnt, bRowPayPrc);
                }
            }

            if(bSumUsePnt.compareTo(bStackAmt) != 0){
            	log.warn(CommonResponseCode.OD_40008_멤버쉽포인트_사용금액과_분배금액_불일치.toMessage()+" 사용금액={}, 분배합={}", bSumUsePnt, bStackAmt);
            	throw new OrderCompleteFailException("ord.error.invalid_price", null);
            }
        }


		// 6-1. 즉시사용포인트 계산
		{
			Map<String, BigDecimal> itmMap = new HashMap<String, BigDecimal>();
			Map<String, Integer> cntMap = new HashMap<String, Integer>();
			Map<Integer, String> imdtlUsePnts = table.column("imdtlUsePntYn");
			int accImdtVal = 0;
			int imdtlUsePntsSize = 0;
			for (Map.Entry<Integer, String> point : imdtlUsePnts.entrySet()) {
				imdtlUsePntsSize++;
				int godTurn = point.getKey();
				String imdtlUsePntYn = point.getValue();
				if ("Y".equals(imdtlUsePntYn) && StringService.isEmpty(table.get(godTurn, "basicPack"))) {
					String itmNo = table.get(godTurn, "itmNo");
					if (!itmMap.containsKey(itmNo)) {
						int itmCnt = 0;
						BigDecimal bItmRowPayPrc = BigDecimal.ZERO;
						Map<Integer, String> items = table.column("itmNo");
						for (Map.Entry<Integer, String> item : items.entrySet()) {
							int itemTurn = item.getKey();
							if (itmNo.equals(item.getValue())) {
								BigDecimal bItmPayPrc = new BigDecimal(table.get(itemTurn, "rowPayPrc"));
								bItmRowPayPrc = bItmRowPayPrc.add(bItmPayPrc);
								itmCnt++;
							}
						}

						BigDecimal bImdtlUsePntRt = new BigDecimal(table.get(godTurn, "imdtlUsePntRt"));
						BigDecimal bStackImdtlUsePnt = bItmRowPayPrc.multiply(bImdtlUsePntRt.divide(new BigDecimal(100)));

						bStackImdtlUsePnt = this.halfup(bStackImdtlUsePnt, 2); // 10원자리 반올림
						accImdtVal += bStackImdtlUsePnt.intValue(); //즉시할인포인트 누적

						log.debug("bStackImdtlUsePnt========================="+bStackImdtlUsePnt);

						itmMap.put(itmNo, bStackImdtlUsePnt);
						cntMap.put(itmNo, itmCnt);
					}
				}
			}



			for (Map.Entry<Integer, String> point : imdtlUsePnts.entrySet()) {
				int godTurn = point.getKey();
				String imdtlUsePntYn = point.getValue();
				if ("Y".equals(imdtlUsePntYn) && StringService.isEmpty(table.get(godTurn, "basicPack"))) {
					BigDecimal bRowPayPrc = new BigDecimal(table.get(godTurn, "rowPayPrc"));
					BigDecimal bImdtlUsePntRt = new BigDecimal(table.get(godTurn, "imdtlUsePntRt"));

                    log.debug("bRowPayPrc10========================="+bRowPayPrc);

					BigDecimal bRowImdtlUsePnt = bRowPayPrc.multiply(bImdtlUsePntRt.divide(new BigDecimal(100)));
					bRowImdtlUsePnt = this.halfup(bRowImdtlUsePnt, 2); // 10원자리 반올림

					///////////////////////////////////////////////////////////
					String itmNo = table.get(godTurn, "itmNo");
					BigDecimal bStackImdtlUsePnt = itmMap.get(itmNo);
					int itmCnt = cntMap.get(itmNo);
					itmCnt--;
					if (itmCnt == 0) {	// last
						bRowImdtlUsePnt = bStackImdtlUsePnt;
					} else {
						bStackImdtlUsePnt = bStackImdtlUsePnt.subtract(bRowImdtlUsePnt);
						itmMap.put(itmNo, bStackImdtlUsePnt);
						cntMap.put(itmNo, itmCnt);
					}
					///////////////////////////////////////////////////////////

					table.put(godTurn, "imdtlUsePnt", bRowImdtlUsePnt.toString());
					table.put(godTurn, "rowPayPrc", bRowPayPrc.subtract(bRowImdtlUsePnt).toString());
					log.debug("bRowPayPrc10-1 여기를보자========================="+bRowPayPrc+"//즉시할인> "+bRowImdtlUsePnt);
				}
			}
		}

		// 6-2. 적립포인트 계산
		{
			Map<String, BigDecimal> itmMap = new HashMap<String, BigDecimal>();
			Map<String, Integer> cntMap = new HashMap<String, Integer>();
			Map<String, BigDecimal> itmMap1 = new HashMap<String, BigDecimal>();
			Map<String, Integer> cntMap1 = new HashMap<String, Integer>();
			Map<Integer, String> savePoints = table.column("savePntYn");
			for (Map.Entry<Integer, String> point : savePoints.entrySet()) {
				int godTurn = point.getKey();
				String savePntYn = point.getValue();
				if ("Y".equals(savePntYn) && StringService.isEmpty(table.get(godTurn, "basicPack"))) {
					String itmNo = table.get(godTurn, "itmNo");
					if (!itmMap.containsKey(itmNo)) {
						int itmCnt = 0;
						BigDecimal bItmRowPayPrc = BigDecimal.ZERO;
						Map<Integer, String> items = table.column("itmNo");
						for (Map.Entry<Integer, String> item : items.entrySet()) {
							int itemTurn = item.getKey();
							if (itmNo.equals(item.getValue())) {
								BigDecimal bItmPayPrc = new BigDecimal(table.get(itemTurn, "rowPayPrc"));
								bItmRowPayPrc = bItmRowPayPrc.add(bItmPayPrc);
								itmCnt++;
							}
						}

						BigDecimal bSavePntRt = new BigDecimal(table.get(godTurn, "savePntRt"));
						BigDecimal bStackSavePnt = bItmRowPayPrc.multiply(bSavePntRt.divide(new BigDecimal(100)));
						bStackSavePnt = this.down(bStackSavePnt, 1); // 1원 절사

						itmMap.put(itmNo, bStackSavePnt);
						cntMap.put(itmNo, itmCnt);
					}
					// 추가적립포인트
					if ("Y".equals(table.get(godTurn, "addSavePntYn"))) {
						if ("FIXRT".equals(table.get(godTurn, "addSavePntTp"))) {	// 정율일때만
							if (!itmMap1.containsKey(itmNo)) {
								int itmCnt = 0;
								BigDecimal bItmRowPayPrc = BigDecimal.ZERO;
								Map<Integer, String> items = table.column("itmNo");
								for (Map.Entry<Integer, String> item : items.entrySet()) {
									int itemTurn = item.getKey();
									if (itmNo.equals(item.getValue())) {
										BigDecimal bItmPayPrc = new BigDecimal(table.get(itemTurn, "rowPayPrc"));
										bItmRowPayPrc = bItmRowPayPrc.add(bItmPayPrc);
										itmCnt++;
									}
								}

								BigDecimal bAddSavePntRt = new BigDecimal(table.get(godTurn, "addSavePntRt"));
								BigDecimal bStackSavePnt = bItmRowPayPrc.multiply(bAddSavePntRt.divide(new BigDecimal(100)));
								bStackSavePnt = this.down(bStackSavePnt, 1); // 1원 절사

								itmMap1.put(itmNo, bStackSavePnt);
								cntMap1.put(itmNo, itmCnt);
							}
						}
					}
				}
			}
			for (Map.Entry<Integer, String> point : savePoints.entrySet()) {
				int godTurn = point.getKey();
				String savePntYn = point.getValue();
				if ("Y".equals(savePntYn) && StringService.isEmpty(table.get(godTurn, "basicPack"))) {
					BigDecimal bRowPayPrc = new BigDecimal(table.get(godTurn, "rowPayPrc"));

					 log.debug("bRowPayPrc11========================="+bRowPayPrc);

					BigDecimal bSavePntRt = new BigDecimal(table.get(godTurn, "savePntRt"));

					BigDecimal bRowSavePnt = bRowPayPrc.multiply(bSavePntRt.divide(new BigDecimal(100)));
					bRowSavePnt = this.down(bRowSavePnt, 1); // 1원 절사

					///////////////////////////////////////////////////////////
					String itmNo = table.get(godTurn, "itmNo");
					BigDecimal bStackSavePnt = itmMap.get(itmNo);
					int itmCnt = cntMap.get(itmNo);
					itmCnt--;
					if (itmCnt == 0) {	// last
						bRowSavePnt = bStackSavePnt;
					} else {
						bStackSavePnt = bStackSavePnt.subtract(bRowSavePnt);
						itmMap.put(itmNo, bStackSavePnt);
						cntMap.put(itmNo, itmCnt);
					}
					///////////////////////////////////////////////////////////

					table.put(godTurn, "savePnt", bRowSavePnt.toString());

					if ("Y".equals(table.get(godTurn, "addSavePntYn"))) {
						BigDecimal bRowAddSavePnt = new BigDecimal(table.get(godTurn, "addSavePnt"));
						if ("FIXRT".equals(table.get(godTurn, "addSavePntTp"))) {
							BigDecimal bAddSavePntRt = new BigDecimal(table.get(godTurn, "addSavePntRt"));
							bRowAddSavePnt = bRowPayPrc.multiply(bAddSavePntRt.divide(new BigDecimal(100)));
							bRowAddSavePnt = this.down(bRowAddSavePnt, 1); // 1원 절사

							///////////////////////////////////////////////////////////
//							String itmNo = table.get(godTurn, "itmNo");
							BigDecimal bStackSavePnt1 = itmMap1.get(itmNo);
							int itmCnt1 = cntMap1.get(itmNo);
							itmCnt1--;
							if (itmCnt1 == 0) {	// last
								bRowAddSavePnt = bStackSavePnt1;
							} else {
								bStackSavePnt1 = bStackSavePnt1.subtract(bRowAddSavePnt);
								itmMap1.put(itmNo, bStackSavePnt1);
								cntMap1.put(itmNo, itmCnt1);
							}
							///////////////////////////////////////////////////////////
						}
						table.put(godTurn, "addSavePnt", bRowAddSavePnt.toString());
					}
				}
			}
		}

        // 6-3. 적립포인트(P포인트) 계산
        {
            Map<String, BigDecimal> itmMap = new HashMap<String, BigDecimal>();
            Map<String, Integer> cntMap = new HashMap<String, Integer>();
            Map<String, BigDecimal> itmMap1 = new HashMap<String, BigDecimal>();
            Map<String, Integer> cntMap1 = new HashMap<String, Integer>();
            Map<Integer, String> savePoints = table.column("saveWebpntYn");
            for (Map.Entry<Integer, String> point : savePoints.entrySet()) {
                int godTurn = point.getKey();
                String savePntYn = point.getValue();
                if ("Y".equals(savePntYn) && StringService.isEmpty(table.get(godTurn, "basicPack"))) {
                    String itmNo = table.get(godTurn, "itmNo");
                    if (!itmMap.containsKey(itmNo)) {
                        int itmCnt = 0;
                        BigDecimal bItmRowPayPrc = BigDecimal.ZERO;
                        Map<Integer, String> items = table.column("itmNo");
                        for (Map.Entry<Integer, String> item : items.entrySet()) {
                            int itemTurn = item.getKey();
                            if (itmNo.equals(item.getValue())) {
                                BigDecimal bItmPayPrc = new BigDecimal(table.get(itemTurn, "rowPayPrc"));
                                bItmRowPayPrc = bItmRowPayPrc.add(bItmPayPrc);
                                itmCnt++;
                            }
                        }

                        BigDecimal bSavePntRt = new BigDecimal(table.get(godTurn, "saveWebpntRt"));
                        BigDecimal bStackSavePnt = bItmRowPayPrc.multiply(bSavePntRt.divide(new BigDecimal(100)));
                        bStackSavePnt = this.down(bStackSavePnt, 1); // 1원 절사

                        itmMap.put(itmNo, bStackSavePnt);
                        cntMap.put(itmNo, itmCnt);
                    }
                    // 추가적립포인트
                    if ("Y".equals(table.get(godTurn, "addSaveWebpntYn"))) {
                        if ("FIXRT".equals(table.get(godTurn, "addSaveWebpntTp"))) {	// 정율일때만
                            if (!itmMap1.containsKey(itmNo)) {
                                int itmCnt = 0;
                                BigDecimal bItmRowPayPrc = BigDecimal.ZERO;
                                Map<Integer, String> items = table.column("itmNo");
                                for (Map.Entry<Integer, String> item : items.entrySet()) {
                                    int itemTurn = item.getKey();
                                    if (itmNo.equals(item.getValue())) {
                                        BigDecimal bItmPayPrc = new BigDecimal(table.get(itemTurn, "rowPayPrc"));
                                        bItmRowPayPrc = bItmRowPayPrc.add(bItmPayPrc);
                                        itmCnt++;
                                    }
                                }

                                BigDecimal bAddSavePntRt = new BigDecimal(table.get(godTurn, "addSaveWebpntRt"));
                                BigDecimal bStackSavePnt = bItmRowPayPrc.multiply(bAddSavePntRt.divide(new BigDecimal(100)));
                                bStackSavePnt = this.down(bStackSavePnt, 1);

                                itmMap1.put(itmNo, bStackSavePnt);
                                cntMap1.put(itmNo, itmCnt);
                            }
                        }
                    }
                }
            }
            for (Map.Entry<Integer, String> point : savePoints.entrySet()) {
                int godTurn = point.getKey();
                String savePntYn = point.getValue();
                if ("Y".equals(savePntYn) && StringService.isEmpty(table.get(godTurn, "basicPack"))) {
                    BigDecimal bRowPayPrc = new BigDecimal(table.get(godTurn, "rowPayPrc"));

                    //log.debug("bRowPayPrc11========================="+bRowPayPrc);

                    BigDecimal bSavePntRt = new BigDecimal(table.get(godTurn, "saveWebpntRt"));

                    BigDecimal bRowSavePnt = bRowPayPrc.multiply(bSavePntRt.divide(new BigDecimal(100)));
                    bRowSavePnt = this.down(bRowSavePnt, 1);

                    ///////////////////////////////////////////////////////////
                    String itmNo = table.get(godTurn, "itmNo");
                    BigDecimal bStackSavePnt = itmMap.get(itmNo);
                    int itmCnt = cntMap.get(itmNo);
                    itmCnt--;
                    if (itmCnt == 0) {	// last
                        bRowSavePnt = bStackSavePnt;
                    } else {
                        bStackSavePnt = bStackSavePnt.subtract(bRowSavePnt);
                        itmMap.put(itmNo, bStackSavePnt);
                        cntMap.put(itmNo, itmCnt);
                    }
                    ///////////////////////////////////////////////////////////

                    table.put(godTurn, "saveWebpnt", bRowSavePnt.toString());

                    if ("Y".equals(table.get(godTurn, "addSaveWebpntYn"))) {
                        BigDecimal bRowAddSavePnt = new BigDecimal(table.get(godTurn, "addSaveWebpnt"));
                        if ("FIXRT".equals(table.get(godTurn, "addSaveWebpntTp"))) {
                            BigDecimal bAddSavePntRt = new BigDecimal(table.get(godTurn, "addSaveWebpntRt"));
                            bRowAddSavePnt = bRowPayPrc.multiply(bAddSavePntRt.divide(new BigDecimal(100)));
                            bRowAddSavePnt = this.down(bRowAddSavePnt, 1);

                            ///////////////////////////////////////////////////////////
//							String itmNo = table.get(godTurn, "itmNo");
                            BigDecimal bStackSavePnt1 = itmMap1.get(itmNo);
                            int itmCnt1 = cntMap1.get(itmNo);
                            itmCnt1--;
                            if (itmCnt1 == 0) {	// last
                                bRowAddSavePnt = bStackSavePnt1;
                            } else {
                                bStackSavePnt1 = bStackSavePnt1.subtract(bRowAddSavePnt);
                                itmMap1.put(itmNo, bStackSavePnt1);
                                cntMap1.put(itmNo, itmCnt1);
                            }
                            ///////////////////////////////////////////////////////////
                        }
                        table.put(godTurn, "addSaveWebpnt", bRowAddSavePnt.toString());
                    }
                }
            }
        }


		// 7. 패키지형 상품의 기본구성상품 가격분배
		{
			TreeMap<Integer, String> packages = new TreeMap<Integer, String>();
			packages.putAll(table.column("basicPack"));
//			BigDecimal bRowGap = BigDecimal.ZERO;
			for (Map.Entry<Integer, String> pkg : packages.entrySet()) {
				if (!StringService.isEmpty(pkg.getValue())) {
					int cpstGodTurn = pkg.getKey();
					int parentsGodTurn = Integer.parseInt(table.get(cpstGodTurn, "parentsGodTurn"));
					BigDecimal bCpstRt = new BigDecimal(table.get(cpstGodTurn, "cpstRt"));
					log.debug(">>>>>>>>>>>>>> cpstGodTurn : " + pkg.getKey());

					// 부모상품의 값.
					BigDecimal bParentsOrdCpn = new BigDecimal(table.get(parentsGodTurn, "ordCpn"));
					BigDecimal bParentsUsePnt = new BigDecimal(table.get(parentsGodTurn, "usePnt"));
					BigDecimal bParentsImdtlUsePnt = new BigDecimal(table.get(parentsGodTurn, "imdtlUsePnt"));
					BigDecimal bParentsSavePnt = new BigDecimal(table.get(parentsGodTurn, "savePnt"));
					BigDecimal bParentsAddSavePnt = new BigDecimal(table.get(parentsGodTurn, "addSavePnt"));
					BigDecimal bParentsPayPrc = new BigDecimal(table.get(parentsGodTurn, "rowPayPrc"));

                    // P포인트
                    BigDecimal bParentsUseCoin = new BigDecimal(table.get(parentsGodTurn, "useWebpnt"));
                    BigDecimal bParentsSaveCoin = new BigDecimal(table.get(parentsGodTurn, "saveWebpnt"));
                    BigDecimal bParentsAddSaveCoin = new BigDecimal(table.get(parentsGodTurn, "addSaveWebpnt"));

					// 기본구성상품의 값.
					BigDecimal bRowCpstOrdCpn = this.goodsDivision(bParentsOrdCpn, bCpstRt);
					BigDecimal bRowCpstUsePnt = this.goodsDivision(bParentsUsePnt, bCpstRt);
					BigDecimal bRowCpstImdtlUsePnt = this.goodsDivision(bParentsImdtlUsePnt, bCpstRt);
					BigDecimal bRowCpstSavePnt = this.goodsDivision(bParentsSavePnt, bCpstRt);
					BigDecimal bRowCpstAddSavePnt = this.goodsDivision(bParentsAddSavePnt, bCpstRt);

                    // P포인트
                    BigDecimal bRowCpstUseCoin = this.goodsDivision(bParentsUseCoin, bCpstRt);
                    BigDecimal bRowCpstSaveCoin = this.goodsDivision(bParentsSaveCoin, bCpstRt);
                    BigDecimal bRowCpstAddSaveCoin = this.goodsDivision(bParentsAddSaveCoin, bCpstRt);


					if ("1".equals(table.get(cpstGodTurn, "cpstLast"))) {
						BigDecimal bRestCpstOrdCpn = BigDecimal.ZERO;
						BigDecimal bRestCpstUsePnt = BigDecimal.ZERO;
						BigDecimal bRestCpstImdtlUsePnt = BigDecimal.ZERO;
						BigDecimal bRestCpstSavePnt = BigDecimal.ZERO;
						BigDecimal bRestCpstAddSavePnt = BigDecimal.ZERO;
						BigDecimal bRestCpstPayPrc = BigDecimal.ZERO;

                        // P포인트
                        BigDecimal bRestCpstUseCoin = BigDecimal.ZERO;
                        BigDecimal bRestCpstSaveCoin = BigDecimal.ZERO;
                        BigDecimal bRestCpstAddSaveCoin = BigDecimal.ZERO;

						Map<Integer, String> packages1 = table.column("basicPack");
						for (Map.Entry<Integer, String> pkg1 : packages1.entrySet()) {
							int cpstGodTurn1 = pkg1.getKey();
							if (!StringService.isEmpty(pkg1.getValue())) {
								int parentsGodTurn1 = Integer.parseInt(table.get(cpstGodTurn1, "parentsGodTurn"));
								if (parentsGodTurn == parentsGodTurn1 && cpstGodTurn != cpstGodTurn1) {
									log.debug(">>>>>>>>>>>>> cpstGodTurn1 : " + cpstGodTurn1);
									bRestCpstOrdCpn = bRestCpstOrdCpn.add(new BigDecimal(table.get(cpstGodTurn1, "ordCpn")));
									bRestCpstUsePnt = bRestCpstUsePnt.add(new BigDecimal(table.get(cpstGodTurn1, "usePnt")));
									bRestCpstImdtlUsePnt = bRestCpstImdtlUsePnt.add(new BigDecimal(table.get(cpstGodTurn1, "imdtlUsePnt")));
									bRestCpstSavePnt = bRestCpstSavePnt.add(new BigDecimal(table.get(cpstGodTurn1, "savePnt")));
									bRestCpstAddSavePnt = bRestCpstAddSavePnt.add(new BigDecimal(table.get(cpstGodTurn1, "addSavePnt")));
									bRestCpstPayPrc = bRestCpstPayPrc.add(new BigDecimal(table.get(cpstGodTurn1, "rowPayPrc")));

                                    bRestCpstUseCoin = bRestCpstUseCoin.add(new BigDecimal(table.get(cpstGodTurn1, "useWebpnt")));
                                    bRestCpstSaveCoin = bRestCpstSaveCoin.add(new BigDecimal(table.get(cpstGodTurn1, "saveWebpnt")));
                                    bRestCpstAddSaveCoin = bRestCpstAddSaveCoin.add(new BigDecimal(table.get(cpstGodTurn1, "addSaveWebpnt")));
								}
							}
						}

						// 나머지 값으로 보정
						bRowCpstOrdCpn = bParentsOrdCpn.subtract(bRestCpstOrdCpn);
						bRowCpstUsePnt = bParentsUsePnt.subtract(bRestCpstUsePnt);
						bRowCpstImdtlUsePnt = bParentsImdtlUsePnt.subtract(bRestCpstImdtlUsePnt);
						bRowCpstSavePnt = bParentsSavePnt.subtract(bRestCpstSavePnt);
						bRowCpstAddSavePnt = bParentsAddSavePnt.subtract(bRestCpstAddSavePnt);

                        // P포인트
                        bRowCpstUseCoin = bParentsUseCoin.subtract(bRestCpstUseCoin);
                        bRowCpstSaveCoin = bParentsSaveCoin.subtract(bRestCpstSaveCoin);
                        bRowCpstAddSaveCoin = bParentsAddSaveCoin.subtract(bRestCpstAddSaveCoin);
					}

					// 4. 주문쿠폰
					table.put(cpstGodTurn, "ordCpn", bRowCpstOrdCpn.toString());

					// 5. 사용포인트
					table.put(cpstGodTurn, "usePnt", bRowCpstUsePnt.toString());

					// 6. 적립포인트
					table.put(cpstGodTurn, "imdtlUsePnt", bRowCpstImdtlUsePnt.toString());
					table.put(cpstGodTurn, "savePnt", bRowCpstSavePnt.toString());
					table.put(cpstGodTurn, "addSavePnt", bRowCpstAddSavePnt.toString());

                    // 5-1. 사용P포인트
                    table.put(cpstGodTurn, "useWebpnt", bRowCpstUseCoin.toString());

                    // 6-1. 적립P포인트
                    table.put(cpstGodTurn, "saveWebpnt", bRowCpstSaveCoin.toString());
                    table.put(cpstGodTurn, "addSaveWebpnt", bRowCpstAddSaveCoin.toString());


					// 7. 패키지형 상품에 대한 row 단위 가격보정
					// 정소가 - 기본할인 = 판매가
					// 판매가 - 전체할인 = 결제가
					// 전체할인 = 상품할인 + 주문할인 + 상품쿠폰 + 주문쿠폰

					BigDecimal bRowCpstSalePrc = new BigDecimal(table.get(cpstGodTurn, "salePrc"));

					BigDecimal bRowCpstGodDc = new BigDecimal(table.get(cpstGodTurn, "godDc"));
					BigDecimal bRowCpstOrdDc = new BigDecimal(table.get(cpstGodTurn, "ordDc"));
					BigDecimal bRowCpstGodCpn = new BigDecimal(table.get(cpstGodTurn, "godCpn"));
					BigDecimal bRowCpstAllDc = bRowCpstGodDc.add(bRowCpstOrdDc).add(bRowCpstGodCpn).add(bRowCpstOrdCpn);

                    // P포인트 삭제 추가
					BigDecimal bRowCpstPayPrc1 = bRowCpstSalePrc.subtract(bRowCpstAllDc).subtract(bRowCpstUseCoin).subtract(bRowCpstUsePnt).subtract(bRowCpstImdtlUsePnt);
//					bRowGap = bRowCpstPayPrc.subtract(bRowCpstPayPrc1);
					log.debug("cpst - bRowCpstSalePrc {}",bRowCpstSalePrc.toString());
					log.debug("cpst - bRowCpstGodDc {}",bRowCpstGodDc.toString());
					log.debug("cpst - bRowCpstOrdDc {}",bRowCpstOrdDc.toString());
					log.debug("cpst - bRowCpstGodCpn {}",bRowCpstGodCpn.toString());
					log.debug("cpst - bRowCpstAllDc {}",bRowCpstAllDc.toString());
					log.debug("cpst - bRowCpstUseCoin {}",bRowCpstUseCoin.toString());
					log.debug("cpst - bRowCpstUsePnt {}",bRowCpstUsePnt.toString());
					log.debug("cpst - rowpayprc {}",bRowCpstPayPrc1.toString());
//					table.put(cpstGodTurn, "rowPayPrc", bRowCpstPayPrc.toString());
//					table.put(cpstGodTurn, "rowPayPrc", bRowCpstPayPrc.subtract(bRowGap).toString());
					table.put(cpstGodTurn, "rowPayPrc", bRowCpstPayPrc1.toString());
				}
			}
		}

		return table;
	}

	/**
	 * 상품당 분배가
	 * @param targetPrc BigDecimal target price
	 * @param goodsPrc BigDecimal goods price
	 * @param sumPrc BigDecimal summation price
	 * @return BigDecimal
	 */
	private BigDecimal goodsDivision(BigDecimal targetPrc, BigDecimal goodsPrc, BigDecimal sumPrc, String roundingMode){
		// 상품당 분할가(1원자리 (반)올림) = 타겟가격 * (상품가/전체가)
		BigDecimal bTmpPrc = targetPrc.multiply(goodsPrc.divide(sumPrc, 20, RoundingMode.DOWN));
		if("HALF_UP".equals(roundingMode)){
			return this.halfup(bTmpPrc, 1); // 1원자리 반올림
		}else if("DOWN".equals(roundingMode)){
			return this.down(bTmpPrc, 1); // 1원자리 절사
		}else{
			return this.roundup(bTmpPrc, 1); // 1원자리 올림
		}

	}
	private BigDecimal goodsDivision(BigDecimal targetPrc, BigDecimal goodsPrc, BigDecimal sumPrc) {
		return goodsDivision(targetPrc,goodsPrc, sumPrc, "HALF_UP");

		// 상품당 분할가(1원자리 올림) = 타겟가격 * (상품가/전체가)
//		BigDecimal bTmpPrc = targetPrc.multiply(goodsPrc.divide(sumPrc, 10, RoundingMode.DOWN));
//		return this.roundup(bTmpPrc, 1); // 1원자리 올림
	}
//	private BigDecimal goodsDivision10(BigDecimal targetPrc, BigDecimal goodsPrc, BigDecimal sumPrc) {
//		// 상품당 분할가(1원자리 올림) = 타겟가격 * (상품가/전체가)
//		BigDecimal bTmpPrc = targetPrc.multiply(goodsPrc.divide(sumPrc, 10, RoundingMode.DOWN));
//		return this.roundup(bTmpPrc, 2); // 10원자리 올림
//	}

	private BigDecimal goodsDivision(BigDecimal targetPrc, BigDecimal bRate) {
		// 상품당 분할가(1원자리 올림) = 타겟가격 * 할인율
		BigDecimal bTmpPrc = targetPrc.multiply(bRate);
		return this.halfup(bTmpPrc, 1); // 1원자리 반올림
	}

	/**
	 * 수량당 분배가
	 * @param rowPrc BigDecimal target price
	 * @param goodsCount BigDecimal goods count
	 * @param idx int looping sequence
	 * @return BigDecimal
	 */
///*	private BigDecimal countDivision(BigDecimal rowPrc, BigDecimal goodsCount, boolean isLast) {
//		// 수량당 분할가(1원자리 반올림) = 타겟가격 / 수량
//		BigDecimal bTmpPrc = rowPrc.divide(goodsCount, 10, RoundingMode.DOWN);
//		BigDecimal bUnitPrc = this.halfup(bTmpPrc, 1); // 1원자리 반올림
//		if (isLast) {
//			bUnitPrc = bUnitPrc.add(rowPrc.subtract(bUnitPrc.multiply(goodsCount)));
//		}
//		return bUnitPrc;
//	}*/

	private BigDecimal checkMinus(BigDecimal bMaximumVal, BigDecimal bTargetValue) {
		if (bMaximumVal.compareTo(bTargetValue) < 0) {
			return bMaximumVal;
		} else {
			return bTargetValue;
		}
	}

	/**
	 * Returns zero when the given BigDecimal is null
	 */
	private BigDecimal nvl(final BigDecimal bigDecimal) throws Exception {
		return bigDecimal == null || StringService.isEmpty(bigDecimal.toString()) ? BigDecimal.ZERO : bigDecimal;
	}

	private BigDecimal halfup(BigDecimal bTempAmt, int movePoint) {
		// >>> 100.123 => 10.0123 => 10 => 100
		// >>> 100.499 => 10.0499 => 10 => 100
		// >>> 100.501 => 10.0501 => 10 => 100
		// >>> 104.123 => 10.4123 => 10 => 100
		// >>> 104.499 => 10.4499 => 10 => 100
		// >>> 104.601 => 10.4601 => 10 => 100
		// >>> 105.123 => 10.5123 => 11 => 110
		// >>> 105.499 => 10.5499 => 11 => 110
		// >>> 105.501 => 10.5501 => 11 => 110
		return bTempAmt.movePointLeft(movePoint).setScale(0, RoundingMode.HALF_UP).movePointRight(movePoint);
	}
	private BigDecimal down(BigDecimal bTempAmt, int movePoint) {

		return bTempAmt.movePointLeft(movePoint).setScale(0, RoundingMode.DOWN).movePointRight(movePoint);
	}

	private BigDecimal roundup(BigDecimal bTempAmt, int movePoint) {
		// >>> 100.123 => 10.0123 => 10 => 100
		// >>> 100.499 => 10.0499 => 10 => 100
		// >>> 100.501 => 10.0501 => 10 => 100
		// >>> 104.123 => 10.4123 => 10 => 100
		// >>> 104.499 => 10.4499 => 10 => 100
		// >>> 104.601 => 10.4601 => 10 => 100
		// >>> 105.123 => 10.5123 => 11 => 110
		// >>> 105.499 => 10.5499 => 11 => 110
		// >>> 105.501 => 10.5501 => 11 => 110
		return bTempAmt.movePointLeft(movePoint).setScale(0, RoundingMode.UP).movePointRight(movePoint);
	}

	private String avoidNull(final String value, final String replacer) {
		if (value == null || value.trim().length() == 0) {
			return replacer;
		}
		else {
			return value;
		}
	}

	//'17 08/16 수정(상품상태만체크)
	public void checkOrdGodStatus(String ordTpCd, OrdGodExtends ordGodExtends, String pickupShopId, boolean isGift, Locale locale) throws Exception {
		if ("Y".equals(ordGodExtends.getInvManageYn())) { // 재고관리여부

			if (!isGift) {
				OrderStockDTO stock = new OrderStockDTO();
				stock.setGodNo(ordGodExtends.getGodNo());
				stock.setItmNo(ordGodExtends.getItmNo());
				stock.setItmQty(ordGodExtends.getOrdQty());
				stock.setMallId(ordGodExtends.getMallId());
				stock.setBasicPackYn(ordGodExtends.getBasicPackYn());
				stock.setPckageGodTpCd(ordGodExtends.getPckageGodTpCd());

				// stock2. check validation
				if ("RESVE_ORD".equals(ordTpCd)) {
					stock.setReserveYn("Y");
				}
				if ("Y".equals(ordGodExtends.getLmttInvYn())) {
					stock.setOnlineLimitYn("Y");
				}
				if ("SHOP_PKUP_ORD".equals(ordTpCd)) {
					stock.setShopId(pickupShopId); // 픽업매장
				}

				String errorCode = orderSelectService.selectGoodsStatus(stock);

				if (!StringService.isEmpty(errorCode)) {
					if (!"1".equals(errorCode)) {
						List<String> paramList = new ArrayList<String>();
						paramList.add(ordGodExtends.getGodNm()); // {0}
						String[] params = paramList.toArray(new String[paramList.size()]);
						String exceptionMessage = messageSourceCmpAccessor.getMessage("ord.error." + errorCode, params, locale);
						throw new OrderCompleteFailException(exceptionMessage);
					}
				}else {
					List<String> paramList = new ArrayList<String>();
					paramList.add(ordGodExtends.getGodNm()); // {0}
					String[] params = paramList.toArray(new String[paramList.size()]);
					String exceptionMessage = messageSourceCmpAccessor.getMessage("ord.error.goods_notexist", params, locale);
					throw new OrderCompleteFailException(exceptionMessage);
				}
			}
		}
	}

	//'17 08/16 신규 (재고수량 체크만)
	public void checkOrdGodQtyStatus(CartSearchDTO cartSearchDTO, Locale locale) throws Exception {

		List<CartGodResult> cartGodResultList = cartSelectService.getCartGodResultList(cartSearchDTO);

		int chkResultListCnt = 0;
		for(CartGodResult dto : cartGodResultList){
			if(StringService.isEmpty(dto.getPckageGodTpCd())|| "ADIT_CPST_GOD".equalsIgnoreCase(dto.getPckageGodTpCd())){
				chkResultListCnt++;
			}
		}
		
		//세션의 상품목록과 쿼리결과의 상품목록 카운트 체크
		//2017-12-20 상품권의 경우 세션 cartSearchDTO.getBskgodExtendList()가 null 임
		log.debug("chkResultListCnt {}",chkResultListCnt);
		log.debug("cartSearchDTO {}",cartSearchDTO.toJSON());
		if(null != cartSearchDTO.getBskgodExtendList() && chkResultListCnt!=cartSearchDTO.getBskgodExtendList().size()) {
			throw new OrderCompleteFailException("주문 상품이 변경되었습니다. 다시 주문해 주십시오.");
		}

		GodItmQtySearchDTO giqsd = new GodItmQtySearchDTO();
		giqsd.setDlvSectCd(cartSearchDTO.getDlvSectCd());

		List<CartResult> resList = cartSelectService.ordGodQtyValidCheck(cartGodResultList, giqsd, cartSearchDTO);

		for(CartResult cart : resList){
			if( StringService.equalsIgnoreCase(BskEnum.NO.toString(), cart.getInvYn())
					|| StringService.equalsIgnoreCase(BskEnum.NO.toString(), cart.getAvailMinOrdCnt())
					|| StringService.equalsIgnoreCase(BskEnum.NO.toString(), cart.getAvailMaxOrdCnt())
					|| StringService.equalsIgnoreCase(BskEnum.NO.toString(), cart.getAditInvYn())
			  ){
					List<String> paramList = new ArrayList<String>();
					paramList.add(cart.getGod().getGodNm());
					String[] params = paramList.toArray(new String[paramList.size()]);
					String exceptionMessage = messageSourceCmpAccessor.getMessage("ord.error.goods_stock", params, locale); //재고수량부분 에러
					throw new OrderCompleteFailException(exceptionMessage);
			   }
		}
	}

	//'17 08/16 신규(수량부분 업데이트와 히스토리 인서트 분리)
	private void updateOrdGodQtyAndHist(String ordTpCd, OrdGodExtends ordGodExtends, String pickupShopId, boolean isGift, Locale locale) throws Exception {

		if ("Y".equals(ordGodExtends.getInvManageYn())) { // 재고관리여부
			if (!isGift) {
				OrderStockDTO stock = new OrderStockDTO();
				stock.setGodNo(ordGodExtends.getGodNo());
				stock.setItmNo(ordGodExtends.getItmNo());
				stock.setItmQty(ordGodExtends.getOrdQty());
				stock.setMallId(ordGodExtends.getMallId());
				stock.setBasicPackYn(ordGodExtends.getBasicPackYn());

				// stock2. check validation
				if ("RESVE_ORD".equals(ordTpCd)) {
					stock.setReserveYn("Y");
				}
				if ("Y".equals(ordGodExtends.getLmttInvYn())) {
					stock.setOnlineLimitYn("Y");
				}
				if ("SHOP_PKUP_ORD".equals(ordTpCd)) {
					stock.setShopId(pickupShopId); // 픽업매장
				}

//				stock.setShopId(GoodsEnum.ERP_ONLINE_TEMP_WERKS); // 임시매장

				// 2015-11-11 재고수량 변경 : 자사 입점사 분리 [AshA]
				// 자사인 경우
				if ("MCOM".equals(ordGodExtends.getPartmalSectCd())) {
					orderCommandService.updateStock(stock, isGift);
				// 입점사인 경우
				} else {
					// 입점사 예약판매 추가 2017-03-07
					if (!"SET_GOD".equals(ordGodExtends.getGodTpCd()) && !"PCKAGE_GOD".equals(ordGodExtends.getGodTpCd())) {
						orderCommandService.updateStockPartmall(stock, isGift);
					}
				}

				// 2015-11-27 재고 관리 히스토리 추가 [AshA]
				// TODO	상품재작업필요 : goodsStockBOComponent.insertGodItmInvHistByOrder(ordGodExtends, ("SHOP_PKUP_ORD".equals(ordTpCd) ? pickupShopId : null));
			} else {
				// TODO	상품재작업필요 :
				//String giftOrdCd = GoodsHistoryEnum.MODITM_GIFTGOD_HISTORY_CD_ORDER.toString();
				//goodsHistoryService.insertGiftGoodsOrdStockHist(giftOrdCd, ordGodExtends.getItmNo(), ordGodExtends.getOrdQty(), ordGodExtends.getRegtrId());
			}
		}
	}


	/**
	 * 회원쿠폰조회
	 * @param cartSearchDTO CartSearchDTO
	 * @return List<OrderCouponResult>
	 */
	public List<OrderCouponResult> selectOrderCouponList(CartSearchDTO cartSearchDTO) throws Exception {
		CouponSearchDTO couponSearchDTO = new CouponSearchDTO();
		couponSearchDTO.setMbr(cartSearchDTO.getMbr());
		couponSearchDTO.setBskGodList(cartSearchDTO.getBskGodList());
		couponSearchDTO.setMallId(cartSearchDTO.getMallId());
		couponSearchDTO.setLang(cartSearchDTO.getLang());
		couponSearchDTO.setDevice(cartSearchDTO.getDevice());

		HttpSession session = ContextService.getCurrentRequest().getSession();
		List<CartResult> cartResultList = (List<CartResult>) session.getAttribute("SESSION_KEY_CART_RESULTS");

		if (cartResultList.isEmpty()) {				
			throw new OrderCompleteFailException("ord.error.no_ord_god",null);
		}
		
		List<OrderCouponResult> memberCouponList = null;
		
		Mbr mbr = null;
		couponSearchDTO.setEmpYn("N");
		if (ContextService.hasRole()) { // 회원
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			
			if(StringService.isNotEmpty(mbr.getMbrNo())
					   && StringService.equalsIgnoreCase(mbr.getMbrAtrbCd(), MemberEnum.MemberAtrbCd.EMP.toString())
					   && "Y".equals(cartSearchDTO.getEmpYn())){
				couponSearchDTO.setEmpYn("Y");
			}
						
		}
		
		// 묶음/교차 할인이 있을시 쿠폰 적용안함
		// 임직원 주문일 경우 교하 할인 상품이 있는 경우 쿠폰이 조회 되지 않아 수정
		// MLB, SA 경우 묶음/교차 할인이 있을시에도 쿠폰 적용
		if(this.isOrderDcAvailable(cartResultList) && !"Y".equals(couponSearchDTO.getEmpYn()) && !"MBM".equals(cartSearchDTO.getMallId()) && !"SAM".equals(cartSearchDTO.getMallId())){
			log.debug("################## unavaliable coupon ############################");
			memberCouponList = new ArrayList();
		}else{	
			memberCouponList = orderSelectService.selectOrderCouponList(couponSearchDTO);
			if (memberCouponList == null) {
				memberCouponList = new ArrayList<OrderCouponResult>();
			}
	
			// 온오프라인쿠폰 사용여부체크
			if(memberCouponList != null && mbr != null && mbr.getErpCstmrNo() != null){
				AdapterHeader adapterHeader = new AdapterHeader();
		        adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
		        adapterHeader.setMallId(cartSearchDTO.getMallId());
		        adapterHeader.setLangCd(cartSearchDTO.getLang());
		        adapterHeader.setDvcCd(cartSearchDTO.getDevice());
		        
				orderSelectService.checkAvailableOnOffCoupon(mbr.getErpCstmrNo(), memberCouponList, adapterHeader);
			}
	
			for (OrderCouponResult memberCoupon : memberCouponList) {
				String couponBskNo = memberCoupon.getBskGod().getBskNo();
				int couponBskGodTurn = memberCoupon.getBskGod().getGodTurn();
	
				for (CartResult cart : cartResultList) {
					String bskNo = cart.getBskGod().getBskNo();
					int bskGodTurn = cart.getBskGod().getGodTurn();
	
					if (bskNo.equals(couponBskNo) && bskGodTurn == couponBskGodTurn) {
						for (GoodsCouponResult mCoupon : memberCoupon.getGoodsCouponResultList()) {
	//						log.debug(">>>>>>>>>>>>>> PrmDtlTpCd() : " + mCoupon.getPrm().getPrmDtlTpCd() + ", cart.getGodPrmNo() : " + cart.getGodPrmNo());
							mCoupon.setAvailble(false);
							if ("DLV_CST_CPN".equals(mCoupon.getPrm().getPrmDtlTpCd())) {
								mCoupon.setAvailble(true);
							}else if("QDLV_CST_CPN".equals(mCoupon.getPrm().getPrmDtlTpCd())){
								mCoupon.setAvailble(true);
							} else {
								if (StringService.isEmpty(cart.getGodPrmNo()) || "PERM".equals(mCoupon.getPrm().getGodDcDplctCd())) {
									if ("GNRL".equals(cart.getPrcType())) {
										if ("ONOFLNE_CPN".equals(mCoupon.getPrmCpn().getCpnKndCd())){
											if(mCoupon.isOnoffCheckedAvailble()){
												mCoupon.setAvailble(true);
											}
										}else{
											mCoupon.setAvailble(true);
										}
									}
								}
							}
						}
					}
				}
			}
		} 
		
		session.setAttribute("SESSION_KEY_COUPON_LIST", memberCouponList);

		return memberCouponList;
	}

	private void cancelPG(OrderDTO orderDTO, HttpServletRequest request, String godTpCd, Map<String, String> resultMap) throws Exception {
		String payMnCd = orderDTO.getPay().getPayMnCd();
		
		// PG사별 결제 취소
		if ("PG".equals(payMnCd)) {
			// 결제취소처리
		}
	}

	private void insertMemberInformation(OrderDTO orderDTO, Ord ord, SystemPK systemPK) throws Exception {
		this.agreeTerms(orderDTO, ord);
		Mbr mbr = orderDTO.getMbr();
		for (LgsDlvspDTO lgsDlvspDTO : orderDTO.getLgsDlvspDTOList()) {

			String defaultDelv = lgsDlvspDTO.getDefaultDelv();//기본배송지로 체크 여부
			String addMbrDlvspCheck =lgsDlvspDTO.getAddMbrDlvspCheck();//배송지목록에 추가 체크여부
			if(defaultDelv == null){
				defaultDelv="N";
			}
			if(addMbrDlvspCheck == null){
				addMbrDlvspCheck ="N";
			}

			if ("Y".equalsIgnoreCase(defaultDelv) ||
					"Y".equalsIgnoreCase(addMbrDlvspCheck)) { // 회원만
				//#34917 [배송매장] FO 주문 시, 고객 주소 연동 오류 확인 요청 으로 수정 2017-01-31
				if(!"SHOP_PKUP_ORD".equals(ord.getOrdTpCd())) {
					MbrDlvsp mbrDlvsp = new MbrDlvsp();
					Functions.copyProperties2(mbrDlvsp, lgsDlvspDTO.getLgsDlvsp());
					mbrDlvsp.setDlvAddrSectCd(lgsDlvspDTO.getLgsDlvsp().getAddrSectCd());
					mbrDlvsp.setDlvAdbukTurn(lgsDlvspDTO.getDlvAdbukTurn());
					//mbrDlvsp.setBaseDlvspYn("Y");
					mbrDlvsp.setMbrNo(mbr.getMbrNo());

					mbrDlvsp.setRegtrId(mbr.getMbrNo());
					mbrDlvsp.setRegDt(orderDTO.getRegDt());
					mbrDlvsp.setUdterId(mbr.getMbrNo());
					mbrDlvsp.setUdtDt(orderDTO.getRegDt());
					mbrDlvsp.setLang(systemPK.getLang());

					//동일 회원배송지 존재여부 체크
					//존재하면 수정, 없으면 수정
					mbrDlvsp.setDlvAdbukTurn(memberOrderSelectService.getMbrDeliveryAdbukTurn(mbrDlvsp));

					//String mbrId = mbr.getMbrId();
					String mbrId = mbr.getMbrNo();



					if("Y".equalsIgnoreCase(defaultDelv) ) {
						mbrDlvsp.setBaseDlvspYn("Y");
						memberOrderCommandService.updateBaseDeliveryLocationRevomeBy(mbrDlvsp, mbrId);
					}
					if ("null".equals(String.valueOf(mbrDlvsp.getDlvAdbukTurn()))) {//insert하는경우
						mbrDlvsp.setBaseDlvspYn(defaultDelv);
					}

					if(StringService.isEmpty(mbrDlvsp.getDlvAddrSectCd())){
						mbrDlvsp.setDlvAddrSectCd("RD_ADDR");
						
					}

					memberOrderCommandService.mergeDeliveryLocationBy(mbrDlvsp, mbrId);

					MbrPsnlInfoModHist mpim = memberPersonalInfoCommandService.setMbrPsnlInfoModHist(mbr.getMbrNo(), "", mbrId, true);
				
					boolean isReg = mbrDlvsp.getDlvAdbukTurn() == null ? true  : false ;


					// 배송지 변경이력 등록
					// step 1.  배송지 변경이력 등록
					String[] codeArr2 = {
							MemberPersonalInfoEnum.MbrDlvspPsnlInfoUdtSectCd.DLVSP_ADDR.toString()				// 배송지 주소
							, MemberPersonalInfoEnum.MbrDlvspPsnlInfoUdtSectCd.DLVSP_ADDRSE.toString()				// 배송지 수취인
							, MemberPersonalInfoEnum.MbrDlvspPsnlInfoUdtSectCd.DLVSP_TEL_NO.toString()				// 배송지 전화 번호
							, MemberPersonalInfoEnum.MbrDlvspPsnlInfoUdtSectCd.DLVSP_MOBIL_NO.toString()			// 배송지 휴대전화 번호
					};
					memberPersonalInfoCommandService.insertPersonalInfoForMbrDlvsp(mbrDlvsp, mpim, codeArr2, isReg);


					}
				}



		}

	}

	private void agreeTerms(OrderDTO orderDTO, Ord ord) throws Exception {
		Mbr mbr = orderDTO.getMbr();

		// 2016.12.13 멤버십 약관동의 인증 이력 저장하는 로직이로 이동 비회원일경우만 이력 저장
		if (StringService.isEmpty(mbr.getMbrNo())) {
			OrdNmbrStplatAgr ordNmbrStplatAgr = new OrdNmbrStplatAgr();
			ordNmbrStplatAgr.setOrdNo(ord.getOrdNo());
			ordNmbrStplatAgr.setStplatCd("NMBR_PSNL_INFO_COLCT_USEF_AGR");	// 비회원 개인정보 수집 약관동의
			ordNmbrStplatAgr.setStplatIemAgrYn(this.avoidNull(orderDTO.getStplatIemAgrYn(), "N"));
			ordNmbrStplatAgr.setRegtrId(ord.getRegtrId());
			ordNmbrStplatAgr.setRegDt(ord.getRegDt());
			ordNmbrStplatAgr.setUdterId(ord.getRegtrId());
			ordNmbrStplatAgr.setUdtDt(ord.getRegDt());

			orderCommandService.insertMemberOrder(ordNmbrStplatAgr);
		}
		/*boolean retVal = false;
		if (!StringService.isEmpty(mbr.getMbrNo())) {
			if ("ONLINE_MBR".equals(mbr.getMbrTpCd()) && "Y".equals(orderDTO.getStplatIemAgrYn())) {
				if (!StringService.isEmpty(mbr.getUnityMbrCrtfcSectCd())) {
					MbrStplatAgr mbrStplatAgr = new MbrStplatAgr();
					mbrStplatAgr.setMbrNo(mbr.getMbrNo());
					mbrStplatAgr.setStplatCd("MBSH_STPLAT"); // 멤버쉽 약관동의
					mbrStplatAgr.setStplatIemAgrYn(this.avoidNull(orderDTO.getStplatIemAgrYn(), "N"));
					mbrStplatAgr.setRegtrId(ord.getRegtrId());
					mbrStplatAgr.setRegDt(ord.getRegDt());
					mbrStplatAgr.setUdterId(ord.getRegtrId());
					mbrStplatAgr.setUdtDt(ord.getRegDt());

					memberStplatAgrService.insertMbrStplatAgr(mbrStplatAgr);

					mbr.setMbrTpCd("UNITY_MBR");
					mbr.setUnityMbrCrtfcSectCd(ord.getSelfCrtfcSectCd()); // 회원과주문통일필요
					mbr.setUdterId(ord.getRegtrId());

					MemberFoDTO dto = new MemberFoDTO();
					dto.setMbr(mbr);
					memberService.updateFoMember(dto);

					// 개인정보 이용 등록 (실명인증/IPIN)
					if (!StringService.isEmpty(mbr.getMbrNo())) {
						String[][] usefCdInfo = { // 구분, 업무, 업무상세
						{ UsefSectCd.PSNL_INFO_TRTMNT_CNSGN_DETL.toString(), UsefJobCd.RLNM_CRTFC_IPIN.toString(),
						        UsefJobDetail.RLNM_CRTFC_IPIN.toString() } };
						this.insertMemberPersonalInfo(systemPK, mbr.getMbrNo(), usefCdInfo);
					}
				}
			}
			retVal = true;
		}
		else {
			OrdNmbrStplatAgr ordNmbrStplatAgr = new OrdNmbrStplatAgr();
			ordNmbrStplatAgr.setOrdNo(ord.getOrdNo());
			ordNmbrStplatAgr.setStplatCd("NMBR_PSNL_INFO_COLCT_USEF_AGR");	// 비회원 개인정보 수집 약관동의
			ordNmbrStplatAgr.setStplatIemAgrYn(this.avoidNull(orderDTO.getStplatIemAgrYn(), "N"));
			ordNmbrStplatAgr.setRegtrId(ord.getRegtrId());
			ordNmbrStplatAgr.setRegDt(ord.getRegDt());
			ordNmbrStplatAgr.setUdterId(ord.getRegtrId());
			ordNmbrStplatAgr.setUdtDt(ord.getRegDt());

			orderCommandService.insertMemberOrder(ordNmbrStplatAgr);
			retVal = true;
		}
		return retVal;*/
	}

	/**
	 * 가상계좌(무통장) 입금확인
	 * @param host String
	 * @param addr String
	 * @return String
	 */
	@Override
	public String setVirtualAccountDeposit(KcpCommonReceiveDTO kcpCommonReceiveDTO,OrderDTO orderDTO,String host, String addr) throws Exception {

		// DB 내 입금계좌 정보 확인
		String payNo = kcpCommonReceiveDTO.getOrder_no();
		Map<String, String> resultMap = this.checkVBNKParam(payNo);
		String resultCode = this.avoidNull(resultMap.get("code"), "9999");
		if("13".equals(kcpCommonReceiveDTO.getOp_cd())){
			resultCode += " 0013 : 입금처리결과 오류 .";
		}
		else if (resultCode.equals("9999")) { // 주문번호 없음
			resultCode += " : 존재하지 않는 주문번호 입니다.";
		}
		else if (resultCode.equals("9998")) { // 결제수단이 다름
			resultCode = "9999 : 해당 주문의 결제수단이 가상계좌가 아닙니다.";
		}
		else if (resultCode.equals("0001")) { // 입금 대기 상태
			/**
			 * PG 연동.. 가상계좌 입금정보
			 */
			Map<String, String> param = kcpPayService.setVirtualAccountDeposit(kcpCommonReceiveDTO, resultMap, host, addr);
			resultCode = param.get("code");

			if (resultCode.equals("0000")) {

				/**
				 * 2. ord/pay update
				 */
				orderCommandService.updateDealTpCd(param);
				orderCommandService.updateOrdStatCd(param);

				String ordNo = resultMap.get("ordNo");
				String cstmrMobilAreaNo = resultMap.get("cstmrMobilAreaNo");
				String cstmrMobilTlofNo = resultMap.get("cstmrMobilTlofNo");
				String cstmrMobilTlofWthnNo = resultMap.get("cstmrMobilTlofWthnNo");
				String brndId = resultMap.get("brndId");
				String godSumry = resultMap.get("godSumry");
				String mbrNo = resultMap.get("mbrNo");
				String cstmrEmail = resultMap.get("cstmrEmail");
				String cstmrNm = resultMap.get("cstmrNm");
				String mbrTpCd = resultMap.get("mbrTpCd");
				
				

				BigDecimal amt = new BigDecimal(resultMap.get("amt"));
				BigDecimal bSumSavePnt = new BigDecimal(resultMap.get("unityPntAccmlSumAmt"));         //적립멤버십포인트
				BigDecimal bSumSaveWebpnt = new BigDecimal(resultMap.get("webpntAccmlSumAmt"));        //적립P포인트
				BigDecimal bSumUsePnt = this.nvl(new BigDecimal(resultMap.get("unityPntUseSumAmt")));  // 사용맴버십포인트
				BigDecimal bSumUseWebpnt = this.nvl(new BigDecimal(resultMap.get("webpntUseSumAmt"))); // 사용P포인트
				BigDecimal bTotalPnt = BigDecimal.ZERO;                                                //누적 포인트
				BigDecimal bTotalWebpnt = BigDecimal.ZERO;                                               //누적 P포인트

				/** '17 09-18 새로운 카카운톡 템플릿을 위한 추가 START **/
//				long memberPoint = 0L;
//				if ( StringService.isNotEmpty(resultMap.get("erpCstmrNo"))
//						&& StringUtils.defaultString(mbrTpCd).equalsIgnoreCase(MemberTpCd.UNITY_MBR.toString()) ){
//					try {
//				    	memberPoint = memberBenefitSelectService.getMemberReserveRemainAmount(resultMap.get("erpCstmrNo"));
//				    	bTotalPnt = this.nvl(new BigDecimal(memberPoint)); //
//				    } catch(Exception e) {
//				    	log.warn(">>> inferface error : getBpCbDisplay", e);
//				    }
//				}
				
				// 재고 처리
				Ord ord = new Ord();
				ord.setOrdNo(ordNo);
				
				// 배송처리 안할시 재고 처리 안함
				if("N".equals(this.avoidNull(resultMap.get("virtlDlvComptYn"),"N"))){
					this.virtualAccountDepositUpdateInv(ord);
				}else{
					this.changeOrderPaymentReplace(ordNo);
				}

				if (StringService.isNotEmpty(mbrNo) &&
						( StringUtils.defaultString(mbrTpCd).equalsIgnoreCase(MemberTpCd.ONLINE_MBR.toString())
								|| StringUtils.defaultString(mbrTpCd).equalsIgnoreCase(MemberTpCd.UNITY_MBR.toString()) )) {

					bTotalWebpnt = this.nvl(new BigDecimal(memberBenefitSelectService.selectWebPointInfoMap(mbrNo).get("use")));

				}
				
				
				try{
					OrderParamDTO salesDTO = new OrderParamDTO();
					salesDTO.setOrdNo(ordNo);
					orderInterfaceService.sendSalesData(salesDTO);
				}catch(Exception e){
					log.error(e.getMessage(),e);
					throw new OrderPGFailException("ord.error.not.complete.interface",null);
				}
				
				orderDTO.setOrd(new Ord());
				orderDTO.getOrd().setOrdNo(ordNo);
				orderDTO.getOrd().setCstmrNm(cstmrNm);
				orderDTO.getOrd().setCstmrEmail(cstmrEmail);
				
				
				SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(ContextService.getCurrentRequest());
				try {

					log.debug("send talk or sms order {}",ordNo);
					
					ArrayList<String> paramList = new ArrayList<String>();
					
					Ord tempOrd = orderSelectService.selectOrdEntity(ordNo);
					String mallId = tempOrd.getMallId();
					
					if("MBM".equals(mallId)) {
						paramList.add(0, getConfigService().getProperty("ncp_base.talk.mlb.shop.name"));
						paramList.add(1, cstmrNm);
						SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
						String ordDt = df.format(tempOrd.getOrdDt());
						paramList.add(2, ordDt);
						paramList.add(3, ordNo);
						paramList.add(4, godSumry);
						DecimalFormat decimalFormat = new DecimalFormat("###,###");
						paramList.add(5, decimalFormat.format(amt));
						paramList.add(6, getConfigService().getProperty("ncp.url.mb_MLB.root")+"/mypage/order/list");
					} else if("SAM".equals(mallId)) {
						paramList.add(0, getConfigService().getProperty("ncp_base.talk.sa.shop.name"));
						paramList.add(1, cstmrNm);
						SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
						String ordDt = df.format(tempOrd.getOrdDt());
						paramList.add(2, ordDt);
						paramList.add(3, ordNo);
						paramList.add(4, godSumry);
						DecimalFormat decimalFormat = new DecimalFormat("###,###");
						paramList.add(5, decimalFormat.format(amt));
						paramList.add(6, getConfigService().getProperty("ncp.url.mb_SA.root")+"/mypage/order/list");
					} else {
						paramList.add(0, cstmrNm);
						paramList.add(1, ordNo);
						DecimalFormat decimalFormat = new DecimalFormat("###,###");
						paramList.add(2, decimalFormat.format(amt)+"원");
						
						paramList.add(3, getConfigService().getProperty("ncp.url.mb_DX.root")+"/mypage/order/"+ordNo+"/view");
						paramList.add(4, godSumry);
					}					
					
					String callerId = OrderCommandComponentImpl.class.getName() + ".AlimTalkAddVirtualCompleteOrder";
					String mobileNumber = cstmrMobilAreaNo + cstmrMobilTlofNo + cstmrMobilTlofWthnNo;
					
					MPushAlimTalkSDO mPushAlimTalkSDO = new MPushAlimTalkSDO();
					if(mbrNo!=null){
						mPushAlimTalkSDO.setMbrNo(mbrNo);
					}else{
						mPushAlimTalkSDO.setMbrNo("NMBR");
					}
					
					mPushAlimTalkSDO.setReceiveMobileNo(mobileNumber);
					mPushAlimTalkSDO.setMallId(systemPK.getMall());
					mPushAlimTalkSDO.setCallerId(callerId);
					mPushAlimTalkSDO.setSendDirectFlag(true);
					mPushAlimTalkSDO.setMsgKey(mallId + "_ORD_GENERAL_03");
					mPushAlimTalkSDO.setParams(paramList);
					
					log.debug("sms alim talk sdo {}, systemPk {}",mPushAlimTalkSDO.toString(),systemPK);
					
					
					log.debug(mPushAdapter.sendAlimTalk(mPushAlimTalkSDO, systemPK));

					if (!StringService.isEmpty(ord.getMbrNo())) {
						String[][] usefCdInfo = { // 구분, 업무, 업무상세
								{UsefSectCd.PSNL_INFO_TRTMNT_CNSGN_DETL.toString(), UsefJobCd.SND_KKO_NTCN_TAK.toString(), UsefJobDetail.ORD_DETAIL.toString()}
						};
						this.insertMemberPersonalInfo(systemPK, mbrNo, usefCdInfo);
					}
				} catch (Exception e) {
					log.error(e.getMessage(),e);
					log.warn("ERROR !!! sending order SMS >>>>", e);
				}
			}
		}

		return resultCode;
	}

	private void virtualAccountDepositUpdateInv(Ord ord) {
		List<OrdGodInv> ordGodList = orderSelectRepository.selectOrderGoodsWmsItemQuantityList(ord);
		for(OrdGodInv item:ordGodList){
			// 예약 주문인지 확인
			if(OrderEnum.ORD_TP_CD_GNRL_ORD.toString().equals(item.getOrdTpCd())){
				if(OrderEnum.ORD_SALE_SHOP_CD_WMS.toString().equals(item.getSaleShopId()) || "M510".equals(item.getSaleShopId())
						|| "I50002".equals(item.getSaleShopId()) || "A30003".equals(item.getSaleShopId()) ) {
					item.setUdterId("SYSTEM");
					
					if(item.getOrdQty()>item.getRealInvQty()){ // 결품
						orderCommandRepository.updateShortageOrderGod(item);
					}else{

						//재고처리
						long hopeInvRest = 0;
						int wmsInvRest = item.getRealInvQty()-item.getHoffInvQty();
						
						if(wmsInvRest<0){
							hopeInvRest = item.getOrdQty();
						}else if((wmsInvRest-item.getOrdQty())<0){
							hopeInvRest = wmsInvRest+item.getOrdQty();
						}
						
						if(hopeInvRest>0){
							item.setHoffInvOrdQty(hopeInvRest);
						}else{
							item.setHoffInvOrdQty(0L);
						}
						
						
						if(!"SET_GOD".equals(item.getGodTpCd())){
							// 재고처리
							GoodsInventoryDTO gDTO = new GoodsInventoryDTO();
							gDTO.setShopId(item.getSaleShopId());
							gDTO.setGodNo(item.getGodNo());
							gDTO.setItmNo(item.getItmNo());
							gDTO.setSyncReqType(GoodsEnum.InvSyncReqTp.ORD.toString());
							gDTO.setSyncType(GoodsEnum.InvSyncTp.IRDS.toString());
							
							gDTO.setSalePrearngeQty(item.getOrdQty());
						
							

							goodsInventoryFOComponent.updateGoodsSalePrearrangementInventoryVariation(gDTO);
							
							// 주문상품 본사재고 업데이트
							orderCommandRepository.updateHoffInventoryOrderQuantity(item);
							
						}
					}
					
				}
				
			}else if(OrderEnum.ORD_TP_CD_RESVE_ORD.toString().equals(item.getOrdTpCd())){
				
				if(OrderEnum.ORD_SALE_SHOP_CD_WMS.toString().equals(item.getSaleShopId()) || "M510".equals(item.getSaleShopId())
						|| "I50002".equals(item.getSaleShopId()) || "A30003".equals(item.getSaleShopId())) {
					if(item.getOrdQty()>item.getReserveInvQty()){// 결품   
						if(!"PCHS_GFT".equals(item.getGodTpCd())){ // 사은품이 아닐경우
							orderCommandRepository.updateShortageOrderGod(item);
						}
						
					}else{
						if(true){ // 예약주문
							
							GoodsInventoryDTO gDTO = new GoodsInventoryDTO();
							gDTO.setShopId(item.getSaleShopId());
							gDTO.setGodNo(item.getGodNo());
							gDTO.setItmNo(item.getItmNo());
							gDTO.setSyncReqType(GoodsEnum.InvSyncReqTp.ORD.toString());
							gDTO.setSyncType(GoodsEnum.InvSyncTp.DDCT.toString());
							
							gDTO.setResveInvQty(item.getOrdQty());
							
							
							goodsInventoryFOComponent.updateGoodsReserveInventoryVariation(gDTO);
						}
							
						if(true){// 예약상품도 재고 차감
							GoodsInventoryDTO gDTO = new GoodsInventoryDTO();
							gDTO.setShopId(item.getSaleShopId());
							gDTO.setGodNo(item.getGodNo());
							gDTO.setItmNo(item.getItmNo());
							gDTO.setSyncReqType(GoodsEnum.InvSyncReqTp.ORD.toString());
							gDTO.setSyncType(GoodsEnum.InvSyncTp.IRDS.toString());
							
							gDTO.setSalePrearngeQty(item.getOrdQty());
							
		
							goodsInventoryFOComponent.updateGoodsSalePrearrangementInventoryVariation(gDTO);
						}
					}
				}
				
			}else if(OrderEnum.ORD_TP_CD_LAG_QTY_ORD.toString().equals(item.getOrdTpCd())){
				// 대량 주문 재고처리 안함
			}
		}
	}



	private Map<String, String> checkVBNKParam(String payNo) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		OrderCompleteResult result = orderSelectService.selectVirtualConfirm(payNo);
		if (result == null) { // 주문번호가 없음.
			resultMap.put("code", "9999");
		}
		else {
			Ord ord = result.getOrd();
			Pay pay = result.getPay();
			List<OrdGodExtend> ordGodExtendsList = result.getOrdGodExtendList();
			String dealTpCd = this.avoidNull(pay.getDealTpCd(), "9998"); // 결재상태
			if (dealTpCd.equals("DEPST_WAIT")) {
				/* 입금대기 상태 */
				resultMap.put("pgStoreId", pay.getPgStoreId()); // 상점아이디
				resultMap.put("pgCrsKey", pay.getPgCrsKey()); // 상점키
				resultMap.put("ordNo", ord.getOrdNo()); // 주문번호
				resultMap.put("payNo", pay.getPayNo()); // 결제번호
				resultMap.put("approval_no", pay.getPgAprvNo()); // 거래일련번호
				resultMap.put("bnkAcctNo", pay.getBnkAcctNo()); // 은행계좌번호
				resultMap.put("bnkCd", pay.getBnkCd()); // 은행코드
				resultMap.put("bnkNm", pay.getBnkNm()); // 은행명
				resultMap.put("amt", String.valueOf(pay.getPayCrncyPayAmt()));
				resultMap.put("brndId", ordGodExtendsList.get(0).getBrndId());
				resultMap.put("godSumry", ord.getGodSumry());
				resultMap.put("cstmrMobilAreaNo", ord.getCstmrMobilAreaNo());
				resultMap.put("cstmrMobilTlofNo", ord.getCstmrMobilTlofNo());
				resultMap.put("cstmrMobilTlofWthnNo", ord.getCstmrMobilTlofWthnNo());
				resultMap.put("cstmrEmail", ord.getCstmrEmail());
				resultMap.put("cstmrNm", ord.getCstmrNm());

				/* 구매이력 연동을 위한 파라메터 */
				resultMap.put("mbrId", result.getMbrId()); // 회원아이디
				resultMap.put("mbrNo", result.getMbrNo()); // 회원번호
				resultMap.put("unityPntUseSumAmt", String.valueOf(ord.getUnityPntUseSumAmt())); // 통합포인트사용
				resultMap.put("unityPntAccmlSumAmt", String.valueOf(ord.getUnityPntAccmlSumAmt())); // 통합포인트적립

				resultMap.put("webpntUseSumAmt", String.valueOf(ord.getWebpntUseSumAmt()));	// P포인트 사용
				resultMap.put("webpntAccmlSumAmt", String.valueOf(ord.getWebpntAccmlSumAmt()));	// P포인트 적립

				/* 최종구매일자 연동을 위한 파라메터 */
				resultMap.put("erpCstmrNo", result.getErpCstmrNo());

				/* '17 09-18 NEW 알림톡 템플릿을 위한 추가*/
				resultMap.put("mbrTpCd", ord.getMbrTpCd());

				/* 회원구매매장 (픽업주문일시) */
				String ordTpCd = this.avoidNull(ord.getOrdTpCd(), "");
				resultMap.put("ordTpCd", ordTpCd);
				if (ordTpCd.equals("SHOP_PKUP_ORD")) {
					resultMap.put("shopId", result.getSysShop().getShopId());
//					resultMap.put("brndId", result.getSysShop().getBrndId());
//					resultMap.put("erpBrndId", result.getSysShop().getErpBrndId());
				}

				//#48146 : 가상배송여부 추가
				resultMap.put("virtlDlvComptYn", ord.getVirtlDlvComptYn());

				resultMap.put("code", "0001");

			}
			else if (dealTpCd.equals("PAY_COMPT")) { // 결제완료상태
				resultMap.put("code", "0000");
			}
			else { // 결제수단이 무통장(가상계좌)이 아님
				resultMap.put("code", "9998");
			}
		}

		return resultMap;
	}

	@Override
	public String selectPayNumber() throws Exception {
		return orderCommandService.selectPayNumber();
	}

	// 개인정보 이용 저장
	private void insertMemberPersonalInfo(SystemPK systemPK, String mbrNo, String[][] usefCdInfo) throws Exception {
		String[] target = {mbrNo};
		memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
				, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
				, target					// 이용대상 : 회원
				, null						// 유입 일련번호
				, null						// 메뉴 일련번호
				, mbrNo						// mbr_no
				);
	}

	// ################################################################################################################
	// 마이페이지 결제 모듈 hongsub.lim START
	// ################################################################################################################
	
	/**
	 * 대량주문 결제
	 */
	@Override
	public void updaePayMethodChange(OrderDTO orderDTO, HttpServletRequest request) throws Exception {
		MypageOrderInfoDTO mypageOrderInfoDTO = new MypageOrderInfoDTO();

		// 원주문 정보를 가져온다
		mypageOrderInfoDTO.setOrdNo(orderDTO.getOrd().getOrdNo());
		mypageOrderInfoDTO.setMbrNo(orderDTO.getMbr().getMbrNo());
		mypageOrderInfoDTO = orderSelectService.selectPayMethodChange(mypageOrderInfoDTO);

		// 기본 업데이트 정보 세팅
		long time = System.currentTimeMillis();
		Date regDt = new Date(time);
		orderDTO.setRegDt(regDt);
		orderDTO.setRegtrId(orderDTO.getMbr().getMbrNo());
		mypageOrderInfoDTO.setRegDt(regDt);
		mypageOrderInfoDTO.setRegtrId(orderDTO.getMbr().getMbrNo());

		// 주문 승인 금액 일치 여부 확인
		BigDecimal ordTotAmt = orderDTO.getOrd().getPayExchgRtCrncySumAmt();
		BigDecimal og_ordTotAmt = mypageOrderInfoDTO.getPayExchgRtCrncySumAmt();
		
		Map<String, String> resultMap = new HashMap<String, String>();
		if (ordTotAmt.compareTo(og_ordTotAmt) != 0) {
			throw new Exception("updaePayMethodChange ordTotAmt Error");
		}
		
		
		/**
		 * 결제 모듈 연동 start 
		 */
		try{
			this.approvePG(orderDTO, orderDTO.getOrd().getPayExchgRtCrncySumAmt(), orderDTO.getOrd().getOrdNo());
		}catch(OrderPGFailException ooe){
			log.error(ooe.getMessage(),ooe);
			throw ooe;
		}catch(OrderCompleteFailException ocfe){
			log.error(ocfe.getMessage(),ocfe);
			throw ocfe;
		}catch(Exception e){
			log.error(e.getMessage(),e);
			throw new OrderPGFailException(e.getMessage(), null);
		}
		
		try {
			
			// 중복주문방지 위해서 ORD 테이블에 대하여 Lock 처리 (FOR UPDATE NOWAIT)
			try {
				orderCommandService.selectOrdForUpdate(orderDTO.getOrd().getOrdNo());
			} catch(Exception e) {
				log.info(CommonResponseCode.OD_40017_주문_중복_진행.toMessage());
				throw new OrderCompleteFailException("ord.error.not_complete_doubleorder", null);
			}

			// ord 테이블 결제 처리

			orderDTO.getOrd().setRcptfrReqstCd("");
			orderDTO.getOrd().setRcptfrPrcsCd("");
			orderDTO.getOrd().setRcptfrPrposCd("");
			orderDTO.getOrd().setRcptfrAprvNo("");

			Ord ord = orderCommandService.updatePayMethodChangeOrd(orderDTO, resultMap);

			// pay 승인건 insert
			// pg 승인때 보낸 payNo
			orderCommandService.insertPayment(orderDTO, ord);

			// 기존 원주문 payNo
			// pay 취소건 취소누적금액 update
			mypageOrderInfoDTO.setMpayMnYn("N");
			orderCommandService.updateCancelPay(mypageOrderInfoDTO);
			
			RefundPayDTO refundPayDTO = new RefundPayDTO();
			
			refundPayDTO.setPayNo(mypageOrderInfoDTO.getPayNo());
			refundPayDTO.setOrdNo(orderDTO.getOrd().getOrdNo());
			refundPayDTO.setNewPayNo(orderCommandService.selectPayNumber());
			
			refundPayDTO.setStdrCrncyPayAmt(mypageOrderInfoDTO.getStdrCrncyPayAmt());
			refundPayDTO.setPayCrncyPayAmt(mypageOrderInfoDTO.getPayCrncyPayAmt());
			
			refundPayDTO.setPayTpCd(mypageOrderInfoDTO.getPayTpCd());
			refundPayDTO.setDealTpCd("");
			
			refundPayDTO.setRfdBnkAcctNo(mypageOrderInfoDTO.getRfdBnkAcctNo());
			refundPayDTO.setRfdBnkCd(mypageOrderInfoDTO.getRfdBnkCd());
			refundPayDTO.setRfdAcnthldrNm(mypageOrderInfoDTO.getRfdAcnthldrNm());
			refundPayDTO.setRegtrId(orderDTO.getMbr().getMbrNo());
			
			
			orderCommandService.insertCancelPay(refundPayDTO);

			/*취소처리*/
			Pay searchPay =  new Pay();
			searchPay.setPayNo(mypageOrderInfoDTO.getPayNo());
			Pay refundPay = payBoService.selectPayByPayNo(searchPay); //주결제 pay 정보

			if ("KCP_PAY".equals(refundPay.getPgSectCd())) {
				
				KcpCancelParamDTO kcpCancelDTO = new KcpCancelParamDTO();

				kcpCancelDTO.setSiteCd(refundPay.getPgStoreId());
				kcpCancelDTO.setSiteKey(refundPay.getPgCrsKey());
				
				kcpCancelDTO.setMod_desc("결제수단변경");
				kcpCancelDTO.setTno(refundPay.getPgAprvNo());
				kcpCancelDTO.setOrdr_idxx(refundPay.getPayNo());

				if ("Y".equals(refundPay.getEscrYn())) {
					kcpCancelDTO.setMod_type("STE5");
				}else {
					kcpCancelDTO.setMod_type("STSC");
				}
				
				kcpPayService.approveCancel(kcpCancelDTO);
			}

			OrderParamDTO salesDTO = new OrderParamDTO();
			salesDTO.setOrdNo(orderDTO.getOrd().getOrdNo());
			
			orderInterfaceService.sendSalesData(salesDTO);
			
		} catch (Exception error) {
			log.error(error.getMessage(), error);
			this.cancelApprovePG(orderDTO);
			throw new OrderCompleteFailException("orderError");
		}
	}

	/**
	 * FO가상계좌 결제수단 변경
	 */
	@Override
	public void rerunPayMethodChange(OrderDTO orderDTO, HttpServletRequest request) throws Exception {
		MypageOrderInfoDTO mypageOrderInfoDTO = new MypageOrderInfoDTO();

		// 원주문 정보를 가져온다
		mypageOrderInfoDTO.setOrdNo(orderDTO.getOrd().getOrdNo());
		mypageOrderInfoDTO.setMbrNo(orderDTO.getMbr().getMbrNo());
		mypageOrderInfoDTO = orderSelectService.selectPayMethodChange(mypageOrderInfoDTO);

		// 기본 업데이트 정보 세팅
		long time = System.currentTimeMillis();
		Date regDt = new Date(time);
		orderDTO.setRegDt(regDt);
		orderDTO.setRegtrId(orderDTO.getMbr().getMbrNo());
		mypageOrderInfoDTO.setRegDt(regDt);
		mypageOrderInfoDTO.setRegtrId(orderDTO.getMbr().getMbrNo());

		// 주문 승인 금액 일치 여부 확인
		BigDecimal ordTotAmt = orderDTO.getOrd().getPayExchgRtCrncySumAmt();
		BigDecimal og_ordTotAmt = mypageOrderInfoDTO.getPayExchgRtCrncySumAmt();
		
		Map<String, String> resultMap = new HashMap<String, String>();
		if (ordTotAmt.compareTo(og_ordTotAmt) != 0) {
			throw new Exception("updaePayMethodChange ordTotAmt Error");
		}
		
		// 재고 처리
		Ord ordParam = new Ord();
		ordParam.setOrdNo(orderDTO.getOrd().getOrdNo());
		ordParam = ordRepository.selectOrd(ordParam);
		
		// 배송처리 안할시 재고 처리 안함
		if("N".equals(this.avoidNull(ordParam.getVirtlDlvComptYn(),"N"))){
			this.virtualAccountDepositUpdateInv(ordParam);
		}else{
			this.changeOrderPaymentReplace(orderDTO.getOrd().getOrdNo());
		}
		
		/**
		 * 결제 모듈 연동 start 
		 */
		try{
			this.approvePG(orderDTO, orderDTO.getOrd().getPayExchgRtCrncySumAmt(), orderDTO.getOrd().getOrdNo());
		}catch(OrderPGFailException ooe){
			log.error(ooe.getMessage(),ooe);
			throw ooe;
		}catch(OrderCompleteFailException ocfe){
			log.error(ocfe.getMessage(),ocfe);
			throw ocfe;
		}catch(Exception e){
			log.error(e.getMessage(),e);
			throw new OrderPGFailException(e.getMessage(), null);
		}
		
		try {
			
			// 중복주문방지 위해서 ORD 테이블에 대하여 Lock 처리 (FOR UPDATE NOWAIT)
			try {
				orderCommandService.selectOrdForUpdate(orderDTO.getOrd().getOrdNo());
			} catch(Exception e) {
				log.info(CommonResponseCode.OD_40017_주문_중복_진행.toMessage());
				throw new OrderCompleteFailException("ord.error.not_complete_doubleorder", null);
			}

			// ord 테이블 결제 처리

			orderDTO.getOrd().setRcptfrReqstCd("");
			orderDTO.getOrd().setRcptfrPrcsCd("");
			orderDTO.getOrd().setRcptfrPrposCd("");
			orderDTO.getOrd().setRcptfrAprvNo("");

			Ord ord = orderCommandService.updatePayMethodChangeOrd(orderDTO, resultMap);

			// pay 승인건 insert
			// pg 승인때 보낸 payNo
			orderCommandService.insertPayment(orderDTO, ord);

			// 기존 원주문 payNo
			// pay 취소건 취소누적금액 update
			mypageOrderInfoDTO.setMpayMnYn("N");
			orderCommandService.updateCancelPay(mypageOrderInfoDTO);
			
			RefundPayDTO refundPayDTO = new RefundPayDTO();
			
			refundPayDTO.setPayNo(mypageOrderInfoDTO.getPayNo());
			refundPayDTO.setOrdNo(orderDTO.getOrd().getOrdNo());
			refundPayDTO.setNewPayNo(orderCommandService.selectPayNumber());
			
			refundPayDTO.setStdrCrncyPayAmt(mypageOrderInfoDTO.getStdrCrncyPayAmt());
			refundPayDTO.setPayCrncyPayAmt(mypageOrderInfoDTO.getPayCrncyPayAmt());
			
			refundPayDTO.setPayTpCd(mypageOrderInfoDTO.getPayTpCd());
			refundPayDTO.setDealTpCd("");
			
			refundPayDTO.setRfdBnkAcctNo(mypageOrderInfoDTO.getRfdBnkAcctNo());
			refundPayDTO.setRfdBnkCd(mypageOrderInfoDTO.getRfdBnkCd());
			refundPayDTO.setRfdAcnthldrNm(mypageOrderInfoDTO.getRfdAcnthldrNm());
			refundPayDTO.setRegtrId(orderDTO.getMbr().getMbrNo());
			
			
			orderCommandService.insertCancelPay(refundPayDTO);

			/*취소처리*/
			Pay searchPay =  new Pay();
			searchPay.setPayNo(mypageOrderInfoDTO.getPayNo());
			Pay refundPay = payBoService.selectPayByPayNo(searchPay); //주결제 pay 정보

			if ("KCP_PAY".equals(refundPay.getPgSectCd())) {
				
				KcpCancelParamDTO kcpCancelDTO = new KcpCancelParamDTO();

				kcpCancelDTO.setSiteCd(refundPay.getPgStoreId());
				kcpCancelDTO.setSiteKey(refundPay.getPgCrsKey());
				
				kcpCancelDTO.setMod_desc("결제수단변경");
				kcpCancelDTO.setTno(refundPay.getPgAprvNo());
				kcpCancelDTO.setOrdr_idxx(refundPay.getPayNo());

				if ("Y".equals(refundPay.getEscrYn())) {
					kcpCancelDTO.setMod_type("STE5");
				}else {
					kcpCancelDTO.setMod_type("STSC");
				}
				
				kcpPayService.approveCancel(kcpCancelDTO);
			}

			OrderParamDTO salesDTO = new OrderParamDTO();
			salesDTO.setOrdNo(orderDTO.getOrd().getOrdNo());
			
			orderInterfaceService.sendSalesData(salesDTO);
			
		} catch (Exception error) {
			log.error(error.getMessage(), error);
			this.cancelApprovePG(orderDTO);
			throw new OrderCompleteFailException("orderError");
		}
	}

        /*
	 * '픽업주문 관련해서 SM에게 메시지 발송
	 *
	 * '픽업주문'에서 '일반주문' 변경에 따른 SM에게 문자메시지발송
     *	- sms.order.pickup.change
     * '픽업주문' 결제완료 시 발송
     *	- sms.order.reserve_pay_notify
	 */
	private void sendMms4SM(String ordNo, String dlvShopId, String desc, SystemPK systemPK ) throws Exception {

		/*
		 * 픽업매장의 '운영시간' 조회, '10:00:00@20:00:00' 형태로 리턴
		 */
//		String operTime = orderBoSelectService.selectShopOperTime(dlvShopId);
//		if (log.isInfoEnabled()) {
//			log.info("Cancel pickup-order oper-time.[{}::{}]", dlvShopId, operTime);
//		}
//		String[] arrOper = operTime.split("@");
//		String startOperTime = arrOper[0];
//		String endOperTime = arrOper[1];
//
//        Calendar cal = Calendar.getInstance();
//        Date currentDt  = cal.getTime();
//    	SimpleDateFormat sdformat = new SimpleDateFormat("HH:mm:ss");

//    	if( sdformat.parse(sdformat.format(currentDt)).after(sdformat.parse(startOperTime))
//    			&& sdformat.parse(sdformat.format(currentDt)).before(sdformat.parse(endOperTime)) ) {

    		List<Ord> ordSmList = orderBoSelectService.selectShopMaster4Pkup( dlvShopId );

    		if ( ordSmList.size() > 0 ) {
				ArrayList<String> params = new ArrayList<>();
				params.add(0, ordNo);

    			for ( Ord ordSm : ordSmList ) {

    				if ( StringService.isNotEmpty( ordSm.getCstmrMobilAreaNo() ) &&
    						StringService.isNotEmpty( ordSm.getCstmrMobilTlofNo() ) &&
    						StringService.isNotEmpty( ordSm.getCstmrMobilTlofWthnNo() ) ) {

    					String mobileNo = new StringBuffer( ordSm.getCstmrMobilAreaNo() )
    							.append( ordSm.getCstmrMobilTlofNo() )
    							.append( ordSm.getCstmrMobilTlofWthnNo() ).toString();
						String callerId = OrderCommandComponentImpl.class.getName() + ".sendMmms4SM";

						String mallId = orderSelectService.selectOrdMallId(ordNo);
						
						ArrayList<String> paramList = new ArrayList<String>();
						
						if("MBM".equals(mallId)) {
							paramList.add(0, getConfigService().getProperty("ncp_base.talk.mlb.shop.name"));
							paramList.add(1, ordSm.getCstmrNm());
							paramList.add(2, ordSm.getCstmrNm());
							paramList.add(3, ordNo);
							paramList.add(4, desc);
						} else if("SAM".equals(mallId)) {
							paramList.add(0, getConfigService().getProperty("ncp_base.talk.sa.shop.name"));
							paramList.add(1, ordSm.getCstmrNm());
							paramList.add(2, ordSm.getCstmrNm());
							paramList.add(3, ordNo);
							paramList.add(4, desc);
							
						} else {
							paramList.add(0, ordSm.getCstmrNm());
							paramList.add(1, ordNo);
							paramList.add(2, desc);
						}					
						
						
						MPushAlimTalkSDO mPushAlimTalkSDO = new MPushAlimTalkSDO();
						mPushAlimTalkSDO.setMbrNo("NMBR");
						mPushAlimTalkSDO.setReceiveMobileNo(mobileNo);
						mPushAlimTalkSDO.setMallId(mallId);
						mPushAlimTalkSDO.setCallerId(callerId);
						mPushAlimTalkSDO.setMsgKey(mallId + "_ORD_GENERAL_04");
						mPushAlimTalkSDO.setParams(paramList);
						mPushAlimTalkSDO.setSendDirectFlag(false);
						mPushAlimTalkSDO.setSendReserveDate(ordSm.getResveOrdDlivyPrearngeDate());
						
						log.debug("sms alim talk sdo {}, systemPk {}",mPushAlimTalkSDO.toString(),systemPK);
						
						
						log.debug(mPushAdapter.sendAlimTalk(mPushAlimTalkSDO, systemPK));

    				}
    			}
    		}

//    	}

	}

	/*
	 * '픽업주문 관련해서 SM에게 메시지 발송
	 *
     * '픽업주문'이 물류센터에서 재고가 매장으로 전달되는 경우 메시지
     *  - sms.order.pickup.create.rt
	 */
	public void sendMms4SM4Batch(String ordNo, String dlvShopId, String msgKey, SystemPK systemPK)
			throws Exception {
		List<Ord> ordSmList = orderBoSelectService.selectShopMaster4Pkup( dlvShopId );

    	if ( ordSmList.size() > 0 ) {
			for ( Ord ordSm : ordSmList ) {
				if ( StringService.isNotEmpty( ordSm.getCstmrMobilAreaNo() )
						&& StringService.isNotEmpty( ordSm.getCstmrMobilTlofNo() )
						&& StringService.isNotEmpty( ordSm.getCstmrMobilTlofWthnNo() ) ) {

					String mobileNo = new StringBuilder()
											.append( ordSm.getCstmrMobilAreaNo() 	)
											.append( ordSm.getCstmrMobilTlofNo() 	)
											.append( ordSm.getCstmrMobilTlofWthnNo())
											.toString();

					ArrayList<String> params = new ArrayList<>();
					params.add(0, ordNo);

					String callerId = OrderCommandComponentImpl.class.getName() + ".sendMms4SM4Batch";

					// 알림처리

				}
			}
    	}
	}

	// ################################################################################################################
	// 마이페이지 결제 모듈 hongsub.lim END
	// ################################################################################################################

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	private String setLinkPrice(HttpServletRequest _request) throws Exception{

	    Cookie[] cookies = _request.getCookies();
	    String lpinfo = null;

	    for (int i = 0; i < cookies.length; i++) {
	        if (cookies[i].getName().equals("LPINFO")) {
	            lpinfo = cookies[i].getValue();
	        }
	    }

	    return lpinfo;

	}

	public void setSmsRecptnSect(SmsRecptnSectDTO smsRecptnSectDTO, HttpServletRequest request) throws Exception{

		if (ContextService.hasRole()) {
			smsRecptnSectDTO.setRegtrId(((SecurityUserDetail) ContextService.getCurrentUserDetail()).getMbr().getMbrNo());
		}
		if(smsRecptnSectDTO.getRegtrId() == null || smsRecptnSectDTO.getRegtrId().isEmpty() ){
			smsRecptnSectDTO.setRegtrId("GUEST");
		}
		smsRecptnSectDTO.setUdterId(smsRecptnSectDTO.getRegtrId());

		orderCommandService.setSmsRecptnSect(smsRecptnSectDTO);
	}


	/**
	 * Mypage 추가결제 및 결제수단 변경시 결제번호 채번 
	 */
	public String getNewPayNoForMypagePayChange(MypageOrderInfoDTO mypageOrderInfoDTO , HttpServletRequest request) throws Exception{
		
		String type = mypageOrderInfoDTO.getType();

		String newPayNo = "";

		//신청시 이미 생성되어진 PAY.pay_no 를 사용하는 경우
		if("U".equals(type)){
			newPayNo = mypageOrderInfoDTO.getPayNo();
		//결제시 PAY 데이터가 생성되는 경우
		}else{
			newPayNo = orderCommandService.selectPayNumber();
		}

		return newPayNo;

	}


	/**
	 * PG사 구분 코드 구하기
	 * 
	 * @param pay
	 * @return
	 */
	private String getPgSectCd (Pay pay){
		
		if(pay == null || pay.getPayMnCd() == null || pay.getPayMnCd().length() == 0){
			return "";
		}
		
		String payMnCd = pay.getPayMnCd();
		
		if("PG".equals(payMnCd)){
			return "PG";
		}
		
		return "PG";
	}

	/** [FO] 배송상태 변경. */
	@Override
	public int updateDeliveryStatusFO(SystemPK systemPK,  List<DeliveryOrderGoodResult> listDTO) throws Exception {
		List<DeliveryOrderGoodResult> packList = new ArrayList<DeliveryOrderGoodResult>();
		DeliverySearchDTO deliverySearchDTO = new DeliverySearchDTO();
		String udtId = "";
		int procCnt = 0;	//처리건수
		if(ContextService.hasRole()){ // 회원
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			udtId = userDetail.getMbr().getMbrNo();
		}else{ // 비회원
			udtId = GoodsEnum.RegtrId.GUEST.toString();
		}

		try {
			for (DeliveryOrderGoodResult dto : listDTO) {
				//패키지 상품
				if(null != dto.getLgsDdg().getPckageGodTurn() && 0 != dto.getLgsDdg().getPckageGodTurn()) {
					packList.add(dto);

					//일반 상품
				} else {
					//물류 출고지시 상품 배송상태 수정
					DeliveryOrderGoodDTO retObj = deliveryStatusService.updateDeliveryStatusFO(dto);
					procCnt += retObj.getCount();	//처리건수

					//주문 배송상태 수정
					Ord ord = new Ord();
					ord.setOrdNo(retObj.getOrdNo());
					ord.setUdterId(udtId);
					ord.setOrdStatCd(OrderEnum.ORD_STAT_DLV_COMPT.toString());	//배송완료
					orderBoService.updateOrdStatAboutCompt(ord);

				}

				deliverySearchDTO.setOrdNo(dto.getOrd().getOrdNo());
				deliverySearchDTO.setWayBilNo(dto.getLgsDlv().getDmstcWaybilNo());
			}

			if(packList.size() > 0) {
				//물류 출고지시 상품 배송상태 수정
				List<DeliveryOrderGoodDTO> retList = deliveryStatusService.updateDeliveryStatus4PackageFO(packList);

				for (DeliveryOrderGoodDTO retObj : retList) {

					//주문 배송상태 수정
					Ord ord = new Ord();
					ord.setOrdNo(retObj.getOrdNo());
					ord.setUdterId(udtId);
					ord.setOrdStatCd(OrderEnum.ORD_STAT_DLV_COMPT.toString());	//배송완료
					orderBoService.updateOrdStatAboutCompt(ord);

					procCnt++;
				}
			}

			// 알림톡 or 문자 SMS 전송
			deliveryStatusService.sendDeliveryComptMsg(systemPK, deliverySearchDTO);

			if(procCnt < 1){
				log.info(CommonResponseCode.DL_40901_X0_상품수령완료_실패.toMessage(systemPK.getLang()));
			}else{
				log.info(CommonResponseCode.DL_00901_X0_상품수령완료_성공.toMessage(systemPK.getLang()));
			}
		}catch (Exception e){
			log.info(CommonResponseCode.DL_40901_X0_상품수령완료_실패.toMessage(systemPK.getLang()));
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			if(log.isErrorEnabled()){
				log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
			}
		}
		return procCnt;
	}

	/**
	 * 구매확정 처리 - 상품단위
	 * 
	 * @param mypageOrderInfoDTO
	 */
	@Override
	public void updateOrderDecision(MypageOrderInfoDTO mypageOrderInfoDTO, SystemPK pk) {
		
		List<OrdGod> ordGodList = new ArrayList<OrdGod>();
		
		OrdGod ordGod = new OrdGod();
		ordGod.setOrdNo(mypageOrderInfoDTO.getOrdNo());
		ordGod.setOrdGodTurn(Integer.parseInt(mypageOrderInfoDTO.getOrdGodTurn()));
		
		ordGodList.add(ordGod);
		
		orderCommandService.updateOrderDecision(ordGodList, pk);
	}
	
	/**
	 * 구매확정 처리 - 복수상품
	 * 
	 * @param mypageOrderInfoDTO
	 */
	@Override
	public void updateOrderDecision(List<OrdGod> ordGod) {
		orderCommandService.updateOrderDecision(ordGod, null);
	}
	
	public OrderCheckResult checkOrder(OrderDTO orderDTO, HttpServletRequest request) throws Exception {
		log.info("AddOrder_Start. orderDTO: {}", orderDTO);
		
		log.info(CommonResponseCode.OD_00001_주문_시작.toMessage()+" orderDTO: {}" , orderDTO);
		
		HttpSession session = ContextService.getCurrentRequest().getSession();
		Ord tmpOrd = orderDTO.getOrd();
		if (session.getAttribute(SessionEnum.PCC.toString()) != null || session.getAttribute(SessionEnum.IPIN.toString()) != null) {
			if (session.getAttribute(SessionEnum.PCC.toString()) != null) {
				Pcc pcc = (Pcc) session.getAttribute(SessionEnum.PCC.toString());
				if (pcc != null) {
					tmpOrd.setSelfCrtfcSectCd("SLF_CRTFC");
					tmpOrd.setRlnmCrtfcDi(pcc.getDi());
					tmpOrd.setRlnmCrtfcCi(pcc.getCi());
				}
			}
			else if (session.getAttribute(SessionEnum.IPIN.toString()) != null) {
				Ipin ipin = (Ipin) session.getAttribute(SessionEnum.IPIN.toString());
				if (ipin != null) {
					tmpOrd.setSelfCrtfcSectCd("IPIN_CRTFC");
					tmpOrd.setRlnmCrtfcDi(ipin.getDiscrhash());
					tmpOrd.setRlnmCrtfcCi(ipin.getCiscrhash());
					tmpOrd.setRlnmCrtfcIpin(ipin.getResult());
				}
			}
		}


		CartSearchDTO cartSearchDTO = (CartSearchDTO) session.getAttribute("SESSION_KEY_CART_GOODS");
		List<CartResult> cartResultList = (List<CartResult>) session.getAttribute("SESSION_KEY_CART_RESULTS");
//		List<CartGiftResult> cartGiftList = CollectionService.emptyListIfNull((List<CartGiftResult>) session.getAttribute("SESSION_KEY_ORD_GIFTS"));
		List<OrderCouponResult> memberCouponList = this.selectOrderCouponList(cartSearchDTO);
		
		log.info("cartSearchDTO : {}, memberCouponList : {}",cartSearchDTO,memberCouponList);		

		
		String godTpCd = "";
		String pickupShopId = "";
		String mainGodNm = "";
		String chkResveSaleGodYn = "";// 2015.11.06 Ord-ordtpcd 컬럼에 Null 값일 경우 GNRL_ORD 셋팅
		String chkDlvSectCd = "";	  // 2015.11.06 Ord-ordtpcd 컬럼에 Null 값일 경우 GNRL_ORD 셋팅
		String chkBskNo = ""; // 2015.11.09-alan>중복주문방지
//		Map<String, String> resultMap = null;
		List<String> itmList = new ArrayList<String>();
		for (CartResult cart : cartResultList) {
			godTpCd = cart.getGod().getGodTpCd();
			chkResveSaleGodYn = cart.getGod().getResveSaleGodYn();// 2015.11.06 Ord-ordtpcd 컬럼에 Null 값일 경우 GNRL_ORD 셋팅
			chkDlvSectCd = cart.getBskGod().getDlvSectCd();	      // 2015.11.06 Ord-ordtpcd 컬럼에 Null 값일 경우 GNRL_ORD 셋팅
			chkBskNo = cart.getBskGod().getBskNo(); // 2015.11.09-alan>중복주문방지
			if (!itmList.contains(cart.getBskGod().getItmNo())) {
				itmList.add(cart.getBskGod().getItmNo());
				if ("".equals(mainGodNm)) {
					mainGodNm = cart.getGod().getGodNm();
				}
			}
		}
		// 2015.11.06 Ord-ordtpcd 컬럼에 Null 값일 경우 GNRL_ORD 셋팅 - alan -Start
		if(tmpOrd.getOrdTpCd() == null || "".equals(tmpOrd.getOrdTpCd())){
			tmpOrd.setOrdTpCd("GNRL_ORD"); // default 일반주문
			if("Y".equals(chkResveSaleGodYn)){ // 예약주문
				tmpOrd.setOrdTpCd("RESVE_ORD");
			}
			if("PKUP_DLV".equals(chkDlvSectCd)){ // 매장픽업
				tmpOrd.setOrdTpCd("SHOP_PKUP_ORD");
			}
			if("GFCT".equals(godTpCd)){ // 상품권
				tmpOrd.setOrdTpCd("GFCT_ORD");
			}
		}
		// 2015.11.06 Ord-ordtpcd 컬럼에 Null 값일 경우 GNRL_ORD 셋팅 - alan -End
		Mbr mbr = new Mbr();
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			
			if(StringService.isNotEmpty(mbr.getMbrNo())
					   && StringService.equalsIgnoreCase(mbr.getMbrAtrbCd(), MemberEnum.MemberAtrbCd.EMP.toString())
					   && "Y".equals(cartSearchDTO.getEmpYn())){
				orderDTO.setEmp(true);
			}
			String cstrno = deliverySelectRepository.getVipList();
			if(mbr!=null&&mbr.getErpCstmrNo()!=null&&cstrno.indexOf(mbr.getErpCstmrNo())!=-1){
				orderDTO.setVipCsmtr(true);
			}
		}

		int ordCnt = itmList.size();
		long time = System.currentTimeMillis();
		Date regDt = new Date(time);

		orderDTO.setOrd(tmpOrd);
		orderDTO.setMbr(mbr);
		orderDTO.setRegDt(regDt);
		orderDTO.setRegtrId(this.avoidNull(mbr.getMbrNo(), "GUEST"));
		orderDTO.setGodSummary(ordCnt > 1 ? mainGodNm + " 외 " + (ordCnt - 1) + "건" : mainGodNm);
		
		Locale locale = LocaleService.getCurrentLocale(request);
		TreeBasedTable<Integer, String, String> table = this.makeOrderTable(orderDTO, cartResultList, locale);
		SortedMap<Integer, Map<String, String>> sortMap = table.rowMap();
		
		List<OrdGodExtends> ordGodExtendsList = new ArrayList<OrdGodExtends>();
		
		List<OrdGodAplPrmExtends> promoList = new ArrayList<OrdGodAplPrmExtends>();
		List<Pay> payList = new ArrayList<Pay>();
		List<InfOrdGodErpDstbExtends> infErpList = new ArrayList<InfOrdGodErpDstbExtends>();
		List<Integer> parentsList = new ArrayList<Integer>();

		HashMap<String, String> payCpnMap = new HashMap<String, String>();
		for (Map.Entry<Integer, Map<String, String>> tbl : sortMap.entrySet()) {
			Map<String, String> column = tbl.getValue();

			// 주문상품
			OrdGodExtends ordGodExtends = orderEntitySetService.makeOrdGod(orderDTO.getOrd(), column);
			ordGodExtendsList.add(ordGodExtends);

			// 주문상품프로모션 - 패키지의 부모상품은 제외
//			if (!("Y".equals(column.get("packageYn")) && StringService.isEmpty(column.get("additionalPack"))
//					&& StringService.isEmpty(column.get("basicPack")))) {
//				promoList.addAll(orderEntitySetService.makeOrdGodAplPrm(ordGodExtends, column));
//			}
//			else {
//				parentsList.add(ordGodExtends.getOrdGodTurn());
//			}
		}
		OrderCheckResult ocr = new OrderCheckResult();
		ocr.setOrdGodExtendsList(ordGodExtendsList);
		
		return ocr;

	}
	
	public void sendOrderMail(OrderDTO orderDTO, HttpServletRequest request){
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		try {
			log.debug("send email order {}",orderDTO.getOrd().getOrdNo());
			
			String mallId = orderSelectService.selectOrdMallId(orderDTO.getOrd().getOrdNo());
			String mallBodyUrl = getConfigService().getProperty("ncp.url.pc_DX.root.secure");
			String mallEmail = "DISCOVERY <discovery@fnf.co.kr>";
			String mallSubject = "[Discovery Expedition] ";
			if("MBM".equals(mallId)) {
				mallBodyUrl = getConfigService().getProperty("ncp.url.pc_MLB.root.secure");
				mallEmail = getConfigService().getProperty("ncp_base.mlb.mall.email.address");
				mallSubject = getConfigService().getProperty("ncp_base.mlb.mall.email.subject") + " ";
			} else if("SAM".equals(mallId)) {
				mallBodyUrl = getConfigService().getProperty("ncp.url.pc_SA.root.secure");
				mallEmail = getConfigService().getProperty("ncp_base.sa.mall.email.address");
				mallSubject = getConfigService().getProperty("ncp_base.sa.mall.email.subject") + " ";
			}

			String mailbody = mailHtmlReaderService.readHTML(mallBodyUrl+"/email/order/"+orderDTO.getOrd().getOrdNo()+"/complete", "");
			
			EmailHtmlSDO emailHtmlSDO = new EmailHtmlSDO();
	        emailHtmlSDO.setCallerId(getClass().getName() + ".sendEmailAddOrder");
	        emailHtmlSDO.setMallEmail(mallEmail);
	        emailHtmlSDO.setMbrEmail(orderDTO.getOrd().getCstmrEmail());
	        emailHtmlSDO.setSubject(mallSubject+orderDTO.getOrd().getCstmrNm()+" 고객님의 주문내역 안내입니다");
	        emailHtmlSDO.setHtmlBody(mailbody);
	        
	        AdapterHeader adapterHeader = new AdapterHeader();
	        adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
	        adapterHeader.setMallId(systemPK.getMall());
	        adapterHeader.setLangCd(systemPK.getLang());
	        adapterHeader.setDvcCd(systemPK.getDevice());

	        log.info(emailAdapter.sendHtmlEmail(emailHtmlSDO, adapterHeader));
	        
			if (!StringService.isEmpty(orderDTO.getOrd().getMbrNo())) {
				String[][] usefCdInfo = { // 구분, 업무, 업무상세
						{UsefSectCd.PSNL_INFO_TRTMNT_CNSGN_DETL.toString(), UsefJobCd.SND_EMAIL.toString(), UsefJobDetail.ORD_DETAIL.toString()}
				};
				this.insertMemberPersonalInfo(systemPK, orderDTO.getOrd().getMbrNo(), usefCdInfo);
			}
		}
		catch (Exception e) {
			log.error(e.getMessage(),e);
			log.warn("ERROR !!! sending order mail >>>", e);
		}
	}
	
	public void sendVirOrderMail(OrderDTO orderDTO, HttpServletRequest request){
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		try {
			log.debug("send email order {}",orderDTO.getOrd().getOrdNo());
			
			String mallId = orderSelectService.selectOrdMallId(orderDTO.getOrd().getOrdNo());
			String mallBodyUrl = getConfigService().getProperty("ncp.url.pc_DX.root.secure");
			String mallEmail = "DISCOVERY <discovery@fnf.co.kr>";
			String mallSubject = "[Discovery Expedition] ";
			if("MBM".equals(mallId)) {
				mallBodyUrl = getConfigService().getProperty("ncp.url.pc_MLB.root.secure");
				mallEmail = getConfigService().getProperty("ncp_base.mlb.mall.email.address");
				mallSubject = getConfigService().getProperty("ncp_base.mlb.mall.email.subject") + " ";
			} else if("SAM".equals(mallId)) {
				mallBodyUrl = getConfigService().getProperty("ncp.url.pc_SA.root.secure");
				mallEmail = getConfigService().getProperty("ncp_base.sa.mall.email.address");
				mallSubject = getConfigService().getProperty("ncp_base.sa.mall.email.subject") + " ";
			}

			String mailbody = mailHtmlReaderService.readHTML(mallBodyUrl+"/email/order/"+orderDTO.getOrd().getOrdNo()+"/virPayment", "");
			
			EmailHtmlSDO emailHtmlSDO = new EmailHtmlSDO();
	        emailHtmlSDO.setCallerId(getClass().getName() + ".sendEmailVirOrder");
	        emailHtmlSDO.setMallEmail(mallEmail);
	        emailHtmlSDO.setMbrEmail(orderDTO.getOrd().getCstmrEmail());
	        emailHtmlSDO.setSubject(mallSubject+orderDTO.getOrd().getCstmrNm()+" 고객님의 입금확인 안내입니다");
	        emailHtmlSDO.setHtmlBody(mailbody);
	        
	        AdapterHeader adapterHeader = new AdapterHeader();
	        adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
	        adapterHeader.setMallId(systemPK.getMall());
	        adapterHeader.setLangCd(systemPK.getLang());
	        adapterHeader.setDvcCd(systemPK.getDevice());

	        log.info(emailAdapter.sendHtmlEmail(emailHtmlSDO, adapterHeader));
	        
			if (!StringService.isEmpty(orderDTO.getOrd().getMbrNo())) {
				String[][] usefCdInfo = { // 구분, 업무, 업무상세
						{UsefSectCd.PSNL_INFO_TRTMNT_CNSGN_DETL.toString(), UsefJobCd.SND_EMAIL.toString(), UsefJobDetail.ORD_DETAIL.toString()}
				};
				this.insertMemberPersonalInfo(systemPK, orderDTO.getOrd().getMbrNo(), usefCdInfo);
			}
		}
		catch (Exception e) {
			log.error(e.getMessage(),e);
			log.warn("ERROR !!! sending order mail >>>", e);
		}
	}
	
	private boolean isOrderDcAvailable(List<CartResult> cartResultList){
		
		for(CartResult item:cartResultList){
			if(item.getBskPrmAplyDcAmt()!=null&&BigDecimal.ZERO.compareTo(item.getBskPrmAplyDcAmt()) < 0){
				return true;
			}
		}
		
		return false;
	}
	
	private void changeOrderClaimOrderNumber(String sourceOrdNo,String ordNo){
		ChangeOrderDTO coDTO = new ChangeOrderDTO();
		coDTO.setSourceOrdNo(sourceOrdNo);
		coDTO.setOrdNo(ordNo);
		if(orderCommandRepository.updateChangeOrderClaimOrderNumber(coDTO)==0){
			throw new OrderCompleteFailException("가격변경 주문이 없습니다.");			
		}
	}
	
	private void changeOrderPaymentReplace(String ordNo){
		orderCommandRepository.updateChangeOrderDeliveryComplete(ordNo);
		orderCommandRepository.updateChangeOrderLgsDeliveryComplete(ordNo);
		orderCommandRepository.changeOrderClaimRtrvlComplete(ordNo);
	}
}
