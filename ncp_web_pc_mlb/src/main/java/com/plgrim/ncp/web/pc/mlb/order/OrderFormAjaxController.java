package com.plgrim.ncp.web.pc.mlb.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplatUse;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.biz.cart.data.CartSearchDTO;
import com.plgrim.ncp.biz.cart.exception.CartFailException;
import com.plgrim.ncp.biz.cart.result.CartResult;
import com.plgrim.ncp.biz.delivery.repository.DeliverySelectRepository;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.biz.order.data.MbrDelvSearchDTO;
import com.plgrim.ncp.biz.order.data.NaverPayOpenDTO;
import com.plgrim.ncp.biz.order.data.OrderCheckResult;
import com.plgrim.ncp.biz.order.data.OrderDTO;
import com.plgrim.ncp.biz.order.data.OrderFormDTO;
import com.plgrim.ncp.biz.order.data.OrderParamDTO;
import com.plgrim.ncp.biz.order.exception.OrderFailException;
import com.plgrim.ncp.biz.order.result.GoodsCouponResult;
import com.plgrim.ncp.biz.order.result.OrderCompleteResult;
import com.plgrim.ncp.biz.order.result.OrderCouponResult;
import com.plgrim.ncp.biz.order.service.OrderCommandService;
import com.plgrim.ncp.biz.order.service.OrderSelectService;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.cmp.member.fo.MemberBenefitFOComponent;
import com.plgrim.ncp.commons.data.order.KcpParamDTO;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.framework.commons.CollectionService;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.SecurityParam;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.page.PageService;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.plgrim.ncp.framework.utils.JsonUtil;
import com.plgrim.ncp.interfaces.member.data.MemberMileageInfoSDO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OrderFormAjaxController extends OrderController {
	
	@Autowired
	OrderCommandService orderCommandService;
	
	@Autowired
	OrderSelectService orderSelectService;

	
	@Autowired
	MemberBenefitFOComponent memberBenefitFOComponent;
	
	@Autowired
	DeliverySelectRepository deliverySelectRepository;
	
	@RequestMapping(value = "/orderform/load.json", produces = { "application/json" })
	public void orderLoad(Model model, @RequestParam(value = "cartNationCd", required = false) String cartNationCd, HttpServletRequest request) throws Exception{
		log.debug("{}",  request.getRequestURI());

		try {

			orderCommandService.checkBlackMember();
			
			SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
			this.removeSession(false);
			SecurityUserDetail userDetail = null;
			MemberFoResult mbrFoResult = null;    // 이메일 인증
			
			
			HttpSession session = ContextService.getCurrentRequest().getSession();
			CartSearchDTO cartSearchDTO = (CartSearchDTO) session.getAttribute("SESSION_KEY_CART_GOODS");
			if (cartSearchDTO == null) {
			    log.info(CommonResponseCode.OD_40036_주문서_진입_실패.toMessage() + " / 세션데이터 없음 메인페이지이동");
			    throw new OrderFailException(CommonResponseCode.OD_40036_주문서_진입_실패.toMessage());
			}
			
			Mbr mbr;
			String stplatCd = "MBSH_STPLAT";
			boolean isMember = false;
			boolean showAuth = false;
			String userKey = "";
			String chkMobile = "Y";
			if (ContextService.hasRole()) { // 회원
				userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
				mbr = userDetail.getMbr();
				
				isMember = true;
				Ord ord = new Ord();
				ord.setMbrNo(mbr.getMbrNo());
				model.addAttribute("dlvMemoList", orderSelectService.selectDeliveryMemoList(ord));
				
				if ("ONLINE_MBR".equals(mbr.getMbrTpCd()) && "NO_CRTFC".equals(StringService.nvl(mbr.getUnityMbrCrtfcSectCd(), "NO_CRTFC"))) {
					showAuth = true;
				
					model.addAttribute("availableMembership", "Y");
					if (mbr.getMbrAddrNationCd() != null
					        && !"".equals(mbr.getMbrAddrNationCd())
					        && !"kr".equals(mbr.getMbrAddrNationCd().toLowerCase())) {
					    model.addAttribute("availableMembership", "N");
					}
					if (mbr.getMobilNationCd() != null
					        && !"".equals(mbr.getMobilNationCd())
					        && !"kr".equals(mbr.getMobilNationCd().toLowerCase())) {
					    model.addAttribute("availableMembership", "N");
					}
				
					String mobilNationNo = userDetail.getMbr().getMobilNationNo(); //휴대전화 국가번호
					String mobilAreaNo = userDetail.getMbr().getMobilAreaNo(); //휴대전화 지역번호
					String mobilTlofNo = userDetail.getMbr().getMobilTlofNo(); //휴대전화 국번호
					String mobilTlofWthnNo = userDetail.getMbr().getMobilTlofWthnNo(); //휴대전화 국내번호
				
					// 휴대폰번호 null 체크
					if (StringUtils.isEmpty(mobilNationNo) || StringUtils.isEmpty(mobilAreaNo) || StringUtils.isEmpty(mobilTlofNo) || StringUtils.isEmpty(mobilTlofWthnNo)) {
					    chkMobile = "N";
					}
				}
				String vipList = deliverySelectRepository.getVipList();
				
				// mlb vip회원 여부
				if(mbr!=null&&mbr.getErpCstmrNo()!=null&&vipList.indexOf(mbr.getErpCstmrNo())!=-1){
					model.addAttribute("vipYn", "Y");
				}
			} else {
			  
			    mbr = new Mbr();
			    mbr.setMbrTpCd(MemberEnum.MemberTpCd.NMBR.toString());
			    stplatCd = "NMBR_PSNL_INFO_COLCT_USEF_AGR";
			    showAuth = true;
			  
			}
			
			boolean authYn = false;
			if (session.getAttribute(SessionEnum.PCC.toString()) != null || session.getAttribute(SessionEnum.IPIN.toString()) != null) {
				authYn = true;
			}
			
			session.setAttribute("SESSION_KEY_SHOW_AUTH", showAuth);
			model.addAttribute("authYn", authYn);
			
			model.addAttribute("showAuth", false);	// 무조건 인증 필요 없도록..
			model.addAttribute("isMember", isMember);
			model.addAttribute("isOrder", true);
			model.addAttribute("chkMobile", chkMobile);
			
			
			//	            Object obj = session.getAttribute(SessionEnum.SINGLE_DTO.toString());
			//	            if (obj != null) {
			//	                cartSearchDTO.setEpUserid(((SingleDTO) obj).getEpUserid()); // 싱글EPID
			//	            }
			
			  
			
			cartSearchDTO.setMbr(mbr);
			model.addAttribute("mbr", mbr);
			
			/**
			 * get order goods
			 */
			OrderFormDTO orderFormDTO = new OrderFormDTO();
			cartSearchDTO.setLang(systemPK.getLang());
			
			try {
			    orderFormDTO = orderSelectComponent.newOrder(cartSearchDTO);
			} catch (Exception e) {
			    //로그인 전 카트에 담고 로그인 하면 해당상품이 카트 정보에 없다. 2016-04-11 추가
				//return "cps/public/display/main/view";
				//#24480 로 수정 2016-08-01 세션에 담긴 카트정보가 날아갈 경우 메인 페이지로 이동 / 정상인경우는 주문서 페이지로 이동
				log.error(CommonResponseCode.OD_40036_주문서_진입_실패.toMessage() + " / 세션데이터 없음 메인페이지이동",e);
				throw new OrderFailException(CommonResponseCode.OD_40036_주문서_진입_실패.toMessage());
			
			}
			
			
			List<CartResult> cartResultList = orderFormDTO.getCartResultList();
			
			
			
			
			session.setAttribute("SESSION_KEY_CART_RESULTS", cartResultList);
			
			model.addAttribute("cartResultList", cartResultList);
			model.addAttribute("delv", orderFormDTO.getMbrDlvsp());
			model.addAttribute("searchDelv", StringService.isEmpty(orderFormDTO.getSearchDelv()) ? "" : orderFormDTO.getSearchDelv());
			model.addAttribute("pickup", orderFormDTO.getSysShop());
			model.addAttribute("cardList", orderFormDTO.getCardBenefitList());
			
			
			
			String delvMaxSeq = "1";
			if (!"PKUP_DLV".equals(cartSearchDTO.getDlvSectCd())
			    && !CollectionService.isEmpty(orderFormDTO.getOrdGiftPrmList())) {
			String giftDelvSeq = "1";
			if ("PKUP_DLV".equals(cartSearchDTO.getDlvSectCd())) {
			delvMaxSeq = "2";
			giftDelvSeq = "2";
			}
			session.setAttribute("SESSION_KEY_ORD_GIFTS", orderFormDTO.getOrdGiftPrmList());
			model.addAttribute("giftList", orderFormDTO.getOrdGiftPrmList());
			model.addAttribute("giftDelvSeq", StringService.nvl(request.getParameter("giftDelvSeq"), giftDelvSeq));
			}
			
			model.addAttribute("dlvSectCd", cartSearchDTO.getDlvSectCd());
			model.addAttribute("delvMaxSeq", StringService.nvl(request.getParameter("delvMaxSeq"), delvMaxSeq));
			
			if (StringService.isEmpty(request.getParameter("delvMaxSeq"))) {
			List<OrderCouponResult> memberCouponList = orderCommandComponent.selectOrderCouponList(cartSearchDTO);
			List<String> couponList = new ArrayList<String>();
			for (OrderCouponResult memberCoupon : memberCouponList) {
			    for (GoodsCouponResult mCoupon : memberCoupon.getGoodsCouponResultList()) {
			        if (mCoupon.isAvailble()) {
			            if (!"IMDTL_DC".equals(mCoupon.getPrmCpn().getCpnIssuMthdCd())) {
			            if (!couponList.contains(mCoupon.getMbrCpnNo())) {
			                couponList.add(mCoupon.getMbrCpnNo());
			            }
			        } else {
			            // 배송비즉시할인쿠폰 : by cannon (2016.06.07)
			            if ("DLV_CST_CPN".equals(mCoupon.getPrm().getPrmDtlTpCd()) || "QDLV_CST_CPN".equals(mCoupon.getPrm().getPrmDtlTpCd())) {
			//										if (!couponList.contains(mCoupon.getMbrCpnNo())) {
			//											couponList.add(mCoupon.getMbrCpnNo());
			//										}
			                } else {
			                    couponList.add(mCoupon.getPrm().getPrmNo());
			                }
			            }
			        }
			    }
			}
			
			
			model.addAttribute("availableCouponCnt", couponList.size());
			
			// 이용약관
			SysStplatUse sysStplatUse = new SysStplatUse();
			sysStplatUse.setStplatCd(stplatCd);
			sysStplatUse.setStplatUseTpCd("ORD_PAY");
			
			
			if (ContextService.hasRole()) { // 회원
			    MypageFoDTO mypageFoDTO = new MypageFoDTO();
			    mypageFoDTO.setMbr(userDetail.getMbr());
				mypageFoDTO.setHistoryYn("N");
				mypageFoDTO.setBrand("M");
				
				// 마일리지 내역 조회
				MemberMileageInfoSDO memberMileageInfoSDO = memberBenefitFOComponent.selectMileageInfo(systemPK, mypageFoDTO);

			    model.addAttribute("memberMileage", memberMileageInfoSDO);
			    userKey = userDetail.getMbr().getMbrNo();
			
			}else{
				userKey = "";
			}
			
			
			model.addAttribute("webPoint", memberBenefitSelectService.selectWebPointInfoMap(mbr.getMbrNo()).get("use")); /* use: 가용 exp: 적립예정 ext: 소멸예정 */
			
			//배송비즉시할인쿠폰 : by cannon (2016.06.07)
			if (orderFormDTO.getDlvFeeImdtlCpnResultList() == null || orderFormDTO.getDlvFeeImdtlCpnResultList().isEmpty()) {
			    model.addAttribute("dlvFeeImdtlDcYn", "N");
			model.addAttribute("dlvfeeimdtlcpn", "[]");
			} else {
			    model.addAttribute("dlvFeeImdtlDcYn", "Y");
			model.addAttribute("dlvfeeimdtlcpn", JsonUtil.marshallingJson(orderFormDTO.getDlvFeeImdtlCpnResultList()));
			    }
			}
			
			Map<String, String> location = new HashMap<String, String>();
			List<Map<String, String>> locations = new ArrayList<Map<String, String>>();
			location.put("url", "/secured/order/new");
			location.put("msgKey", "order.txt.title");
			locations.add(location);
			
			model.addAttribute("locations", locations);
			model.addAttribute("mapKey", getConfigService().getProperty("ncp_web_pc_sf.mapKey"));
			
			
			//장바구니에서 선택한 국가 코드
			model.addAttribute("cartNationCd", cartNationCd);
			
			//UXUI개선: 고객선호결제수단
			model.addAttribute("mbrPreferPayMn", orderFormDTO.getMbrPreferPayMn());
			// naver pay처리
			if("Y".equals(cartSearchDTO.getNaverPayYn())){
				model.addAttribute("mbrPreferPayMn", "naver");
			}
			

			String payNo = orderCommandService.selectDBNumber("SQ_PAY", "ST");
			// naver pay install
			NaverPayOpenDTO npayDTO = new NaverPayOpenDTO();
			npayDTO.setMerchantPayKey(payNo);
			npayDTO.setMerchantUserKey(userKey);
			npayDTO.setReturnUrl(getConfigService().getProperty("ncp_base.order.naverPay.returnUrl"));
			npayDTO.setClientId(getConfigService().getProperty("ncp_base.order.naverPay.mlb.clientId"));
			npayDTO.setMode(getConfigService().getProperty("ncp_base.order.naverPay.mode"));
			
			model.addAttribute("npayInfo", npayDTO);
			KcpParamDTO kcpDTO = new KcpParamDTO();
			kcpDTO.setOrdr_idxx(payNo);
			kcpDTO.setReq_tx(getConfigService().getProperty("ncp_base.order.kcp.rex.tx"));
			kcpDTO.setSite_cd(getConfigService().getProperty("ncp_base.order.kcp.mlb.site.cd"));
			if (ContextService.hasRole()) {
		        userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
	            String  erpCstmrNo = userDetail.getMbr().getErpCstmrNo();
	            String cstrno = deliverySelectRepository.getVipList();
	            if(StringService.isNotEmpty(erpCstmrNo)&&cstrno.indexOf(erpCstmrNo)!=-1){
	                kcpDTO.setSite_cd(getConfigService().getProperty("ncp_base.order.kcp.mlb.site.vip.cd"));
	            }
	        }
			
			kcpDTO.setSite_name(getConfigService().getProperty("ncp_base.order.kcp.mlb.site.name"));
			kcpDTO.setQuota(getConfigService().getProperty("ncp_base.order.kcp.quota.month"));
			kcpDTO.setCurrency(getConfigService().getProperty("ncp_base.order.kcp.currency"));
			kcpDTO.setModule_type(getConfigService().getProperty("ncp_base.order.kcp.module.type"));
			kcpDTO.setEscw_used("Y");
			kcpDTO.setPay_mod("Y");
			kcpDTO.setDeli_term(getConfigService().getProperty("ncp_base.order.kcp.delivery.term"));
			//<!-- 2012년 8월 18일 정자상거래법 개정 관련 설정 부분 -->
			//<!-- 제공 기간 설정 0:일회성 1:기간설정(ex 1:2012010120120131)  -->
			kcpDTO.setGood_expr("0");
			kcpDTO.setVcnt_expire_term(getConfigService().getProperty("ncp_base.order.kcp.vcnt.expire.term"));
			
			
			model.addAttribute("kcpInfo",kcpDTO);
			model.addAttribute("recomCdList",CodeUtil.getCodes("RECOMEND_SEX", systemPK.getLang()));
			
			model.addAttribute("bankCodeList",CodeUtil.getCodes("BNK", systemPK.getLang()));
			
			
			
			ContextService.getCurrentRequest().getSession().setAttribute("SESSION_KEY_CART_GOODS", cartSearchDTO);
			log.info(CommonResponseCode.OD_00020_주문서_진입_성공.toMessage());
		
		} catch (Exception e) {
		    log.error(CommonResponseCode.OD_40036_주문서_진입_실패.toMessage(), e);
		    throw e;
		}
    }

	
	
    @RequestMapping(value = "/addnew.json", method = RequestMethod.POST)
    public void addOrder(Model model, @RequestBody OrderDTO orderDTO, HttpServletRequest request, RedirectAttributes redirectAttr) throws Exception {
		log.debug("{}",  request.getRequestURI());
		log.debug("orderDTO {}",  orderDTO.toJSON());
		
		
		
        SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
        orderDTO.setMallId(systemPK.getMall());
        orderDTO.setLang(systemPK.getLang());
        orderDTO.setDevice(systemPK.getDevice());

        Ord ord = orderDTO.getOrd();
        if (ord != null) {
            orderDTO.getOrd().setOsCd(systemPK.getDevicePlatform());
            orderDTO.getOrd().setMobileAppSectCd(systemPK.getApp());
        }

        orderDTO.setCrncyCd("KRW");//default

        
        orderCommandComponent.addOrder(orderDTO, request);
        orderCommandComponent.sendOrderMail(orderDTO, request);

        //#33772 로 추가 2016-12-30
        HttpSession session = ContextService.getCurrentRequest().getSession();
        session.setAttribute("ORDER_COMPLETE_ORDNO", orderDTO.getOrd().getOrdNo());
        
        // 비회원 로그인
        if (!ContextService.hasRole()) {
        	SecurityUserDetail userDetail = new SecurityUserDetail();
			Mbr mbr = new Mbr(); 
			userDetail.setMbr(mbr);
			
			Map<String,String> parametetMap = new HashMap<String,String>();
			parametetMap.put("cstmrNm", orderDTO.getOrd().getCstmrNm());
			parametetMap.put("cstmrMobilNo", orderDTO.getOrd().getCstmrMobilAreaNo()+orderDTO.getOrd().getCstmrMobilTlofNo()+orderDTO.getOrd().getCstmrMobilTlofWthnNo());
			parametetMap.put("ordNo",  orderDTO.getOrd().getOrdNo());
			userDetail.setParameterMap(parametetMap);
	
	        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
	        //spring 4.0 변경
	          
	        grantedAuthorities.add(new SimpleGrantedAuthority(SecurityParam.GUEST_ROLE.toString()));
	        Authentication authenticatedUser = new UsernamePasswordAuthenticationToken(userDetail, null, grantedAuthorities);
	        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        }

        model.addAttribute("orderNumber",orderDTO.getOrd().getOrdNo());
        
        
    }
    
    
    @RequestMapping(value = "/checkOrder.json", method = RequestMethod.POST)
    public void checkOrder(Model model, @RequestBody OrderDTO orderDTO, HttpServletRequest request, RedirectAttributes redirectAttr) throws Exception {
		log.debug("{}",  request.getRequestURI());
		log.debug("orderDTO {}",  orderDTO.toJSON());
		
		
		
        SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
        orderDTO.setMallId(systemPK.getMall());
        orderDTO.setLang(systemPK.getLang());
        orderDTO.setDevice(systemPK.getDevice());

        Ord ord = orderDTO.getOrd();
        if (ord != null) {
            orderDTO.getOrd().setOsCd(systemPK.getDevicePlatform());
            orderDTO.getOrd().setMobileAppSectCd(systemPK.getApp());
        }

        orderDTO.setCrncyCd("KRW");//default

        
        
        OrderCheckResult ocr = orderCommandComponent.checkOrder(orderDTO, request);
        
        log.debug("result map {}",ocr.toJSON());

      

        model.addAttribute("orderInfo",ocr);
        
        
    }
    
    
	@RequestMapping(value = "/coupon/list", method = RequestMethod.POST)
	public void listCoupon(@RequestBody OrderDTO orderDTO, Model model, HttpServletRequest request) throws Exception {
		HttpSession session = ContextService.getCurrentRequest().getSession();
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);

		CartSearchDTO cartSearchDTO = (CartSearchDTO) session.getAttribute("SESSION_KEY_CART_GOODS");
		List<OrderCouponResult> memberCouponList = orderCommandComponent.selectOrderCouponList(cartSearchDTO);
		
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			cartSearchDTO.setMbr(userDetail.getMbr());
			model.addAttribute("results", "success");
		}
		else {
			model.addAttribute("results", "fail");
			model.addAttribute("message", "로그인이 필요한 항목입니다.");
		}

		 
		//온오프라인쿠폰 사용불가쿠폰제거
		int iCnt = memberCouponList.size();
		for(int i=iCnt-1 ; i>=0 ; i--){
			List<GoodsCouponResult> mCouponList = memberCouponList.get(i).getGoodsCouponResultList();
			int jCnt = mCouponList.size();
			for(int j=jCnt-1 ; j>=0 ; j--){
				GoodsCouponResult mCoupon = mCouponList.get(j);
				if(	"ONOFLNE_CPN".equals(mCoupon.getPrmCpn().getCpnKndCd())
					&& !mCoupon.isAvailble()){
					mCouponList.remove(j);
				}
			}
			if(mCouponList.size()==0){
				memberCouponList.remove(i);
			}
		}
		

		model.addAttribute("memberCouponList", memberCouponList);
		model.addAttribute("lgsDlvspDTOList", orderDTO.getLgsDlvspDTOList());

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/coupon/add.json", method = RequestMethod.POST, produces = { "application/json" })
	public void addCoupon(Model model, HttpServletRequest request) throws Exception {
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		String issueSerial = request.getParameter("issueSerial");

		PromotionBoDTO promotionBoDTO = new PromotionBoDTO();
		promotionBoDTO.setMallId(systemPK.getMall());
		promotionBoDTO.setLang(systemPK.getLang());
		promotionBoDTO.setDevice(systemPK.getDevice());

		String mbrNo = null;
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			Mbr mbr = userDetail.getMbr();
			promotionBoDTO.setAbsMbrTpCd(mbr.getMbrTpCd());
			promotionBoDTO.setAbsMbrAtrbCd(mbr.getMbrAtrbCd());
			promotionBoDTO.setAbsGepcoId(mbr.getPsitnGrpcoId());
			
			mbrNo = mbr.getMbrNo();
			promotionBoDTO.setMbr(mbr);
		}
		
		MbrIssuCpn issue = new MbrIssuCpn();
		issue.setCpnCrtfcCd(issueSerial);
		issue.setMbrNo(mbrNo);
		issue.setRegtrId(mbrNo);
		issue.setUdterId(mbrNo);
		promotionBoDTO.setMbrIssuCpn(issue);

		String resultMsg = promotionCouponFOComponent.addCouponCrtfcCdMemberIssue(promotionBoDTO);
		model.addAttribute("resultMsg", resultMsg);

		// return "redirect:/secured/order/coupon/list.popup";
	}
	
	@RequestMapping(value = "/{ordNo}/orderInfo.json", method = RequestMethod.POST, produces = { "application/json" })
	public void orderInfo(Model model, @PathVariable String ordNo, HttpServletRequest request) throws Exception {
		this.removeSession(true);
		
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);


        HttpSession session = ContextService.getCurrentRequest().getSession();
        //세션에 담겨있는 주문번호와 파라메터로 넘어온 주문번호가 다르면 튕겨낸다.
        if (!ordNo.equals(session.getAttribute("ORDER_COMPLETE_ORDNO"))) {
            throw new Exception("viewOrderComplete : invalid ordNo");
        }
        

        OrderParamDTO ordParam = new OrderParamDTO();
        ordParam.setOrdNo(ordNo);
        ordParam.setLang(systemPK.getLang());
        OrderCompleteResult result = orderSelectComponent.viewOrderComplete(ordParam);
        model.addAttribute("info", result);
        
        model.addAttribute("payCodeList",CodeUtil.getCodes("PAY_MN", systemPK.getLang()));


	}
	

	@RequestMapping(value = "/delivery/list", method = RequestMethod.POST)
	public void getOrderDeliveryLocationList(@ModelAttribute MbrDelvSearchDTO mbrDelvSearchDTO, Model model, HttpServletRequest request) throws Exception {
		SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
		mbrDelvSearchDTO.setLang(systemPK.getLang());
		mbrDelvSearchDTO.setMallId(systemPK.getMall());
		mbrDelvSearchDTO.setDevice(systemPK.getDevice());

		mbrDelvSearchDTO.setDelvFlag("member");
		PageParam pageParam = getPageService().buildPageParam(mbrDelvSearchDTO.getPageNo(), 10000);
		Page<MbrDlvsp> mbrDlvspList = orderSelectComponent.selectOrderDeliveryList(mbrDelvSearchDTO, pageParam);

		model.addAttribute("mbrDlvspList", mbrDlvspList.getContent());
		
		mbrDelvSearchDTO.setDelvFlag("delv");
		pageParam = getPageService().buildPageParam(mbrDelvSearchDTO.getPageNo(), 5);
		Page<MbrDlvsp> ordDlvspList = orderSelectComponent.selectOrderDeliveryList(mbrDelvSearchDTO, pageParam);
		
		model.addAttribute("ordDlvspList", ordDlvspList.getContent());

		model.addAttribute("delvSeq", request.getParameter("delvSeq"));
		model.addAttribute("delvFlag", mbrDelvSearchDTO.getDelvFlag());
		PageService.makePageResult(mbrDlvspList, model);
	}


    
}
