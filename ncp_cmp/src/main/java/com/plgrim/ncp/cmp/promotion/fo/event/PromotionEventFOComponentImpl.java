package com.plgrim.ncp.cmp.promotion.fo.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtAtend;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftInfo;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPartcptnTgt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPrize;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtPrizeFreeGift;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtSnsReply;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSns;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.enums.EventEnum;
import com.plgrim.ncp.base.enums.EventEnum.EventApplcnCountSect;
import com.plgrim.ncp.base.enums.EventEnum.EventDevice;
import com.plgrim.ncp.base.enums.EventEnum.EventFreeGiftKind;
import com.plgrim.ncp.base.enums.EventEnum.EventResultCode;
import com.plgrim.ncp.base.enums.EventEnum.EventType;
import com.plgrim.ncp.base.enums.EventEnum.MbrSnsCd;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.MemberEnum.MemberTpCd;
import com.plgrim.ncp.base.enums.PromotionEnum;
import com.plgrim.ncp.base.enums.PromotionEnum.CouponIssueLimitReason;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionAplTarget;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionSeparator;
import com.plgrim.ncp.base.enums.SysInfoEnum;
import com.plgrim.ncp.base.repository.evt.EvtApplcnRepository;
import com.plgrim.ncp.base.repository.evt.EvtFreeGiftInfoRepository;
import com.plgrim.ncp.base.repository.evt.EvtPrizeFreeGiftRepository;
import com.plgrim.ncp.base.repository.evt.EvtPrizeRepository;
import com.plgrim.ncp.base.repository.evt.EvtSnsReplyRepository;
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
import com.plgrim.ncp.biz.promotion.data.EventBoDTO;
import com.plgrim.ncp.biz.promotion.data.EventExtendsFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventFreeGiftTurnExtendsFoDTO;
import com.plgrim.ncp.biz.promotion.data.EventSearchFoDTO;
import com.plgrim.ncp.biz.promotion.data.EvtApplcnBoDTO;
import com.plgrim.ncp.biz.promotion.data.GoodsListFoDTO;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.biz.promotion.data.SingleEventResultDto;
import com.plgrim.ncp.biz.promotion.exception.EventFreeGiftNotJoinTimeException;
import com.plgrim.ncp.biz.promotion.exception.EventOnlineMemberOnlyException;
import com.plgrim.ncp.biz.promotion.exception.PromotionNotExistException;
import com.plgrim.ncp.biz.promotion.result.EventAtendResult;
import com.plgrim.ncp.biz.promotion.result.EventBoResult;
import com.plgrim.ncp.biz.promotion.result.MbrIssuCpnResult;
import com.plgrim.ncp.biz.promotion.result.PromotionBoResult;
import com.plgrim.ncp.biz.promotion.service.EventService;
import com.plgrim.ncp.biz.promotion.service.PromotionService;
import com.plgrim.ncp.cmp.member.fo.MemberAuthFOComponent;
import com.plgrim.ncp.cmp.member.fo.MemberJoinFOComponent;
import com.plgrim.ncp.cmp.promotion.fo.PromotionEventFOComponent;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.exception.NotSupportedException;
import com.plgrim.ncp.framework.exception.VulnerabilityException;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

/**
 * 이벤트 Command Component 구현
 * @author js
 *
 */
@Slf4j
@Transactional(value = "transactionManager")
@Component
public class PromotionEventFOComponentImpl extends AbstractComponent implements PromotionEventFOComponent {

	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private EvtFreeGiftInfoRepository evtFreeGiftInfoRepository;
	
	@Autowired
	private EvtApplcnRepository evtApplcnRepository;
	
	@Autowired
	private EvtPrizeFreeGiftRepository evtPrizeFreeGiftRepository;
	
	@Autowired
	private EvtPrizeRepository evtPrizeRepository;
	
	@Autowired
	private EvtSnsReplyRepository evtSnsReplyRepository;
	
    @Autowired
	InterfaceApiCommon interfaceApiCommon;
	
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
	MemberJoinFOComponent memberJoinFOComponent;
	
	@Autowired
    MemberAuthFOComponent memberAuthFOComponent;
	
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

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
	
	@Override
	public List<EventExtendsFoDTO> selectEventList(EventSearchFoDTO searchDTO) throws Exception {
		
		return  eventService.selectEventList(searchDTO);
	}
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.promotion.fo.EventSelectComponent#selectEventView(java.lang.String, com.plgrim.ncp.framework.data.SystemPK)
	 */
	@Override
	public EventFoDTO selectEventView(EventSearchFoDTO searchDTO) throws Exception {

		EventExtendsFoDTO evt = eventService.selectEventView(searchDTO);

		/* [2016.10.18] 디바이스 / 다국어 체크 추가
		 * 이벤트 유형 체크로직에서 nullpoint exception 발생으로 PromotionNotExistException throw 
		 */        
		if (evt == null) {
            throw new PromotionNotExistException(null);
        }

		EventFoDTO evtDetail = new EventFoDTO();

		evtDetail.setEventExt(evt);
		if ("ATEND_CHK".equals(evt.getEvtTpCd()) ) {
			// 출석체크 이벤트
			if(StringUtils.isNotEmpty(searchDTO.getMbrNo())){
				// 출석 목록 조회
				List<Integer> atednList = eventService.selectEventAtend(searchDTO);
				evtDetail.setAttendList(atednList);
			}
			List<EventFreeGiftTurnExtendsFoDTO> gift = eventService.selectFreeGiftTurnList(searchDTO);
			evtDetail.setFreeGiftTurnList(gift);
		}else if("SNS_REPLY".equals(evt.getEvtTpCd())){
			// SNS 댓글 이벤트
			
//			PageParam pageParam = getPageService().buildPageParam(Integer.toString(searchDTO.getBeginIndex()), 10);
//			Page<EvtSnsReply> snsReply = eventService.selectSnsReplyList(searchDTO, pageParam);
//			evtDetail.setSnsReplyList(snsReply);
		}else if("TM_CPN".equals(evt.getEvtTpCd())){
			// 타임쿠폰 이벤트
			List<EventFreeGiftTurnExtendsFoDTO> gift = eventService.selectFreeGiftTimeList(searchDTO);
			evtDetail.setFreeGiftTurnList(gift);
		}else{
			
		}
		
		// 이벤트 목록 조회
		searchDTO.setEndYn("no");
		searchDTO.setOrderBy(evt.getEvtGodSortStdrCd());
		evtDetail.setEventList(eventService.selectEventListNoPaging(searchDTO));
		// 상품 그룹 목록 조회
		evtDetail.setSprtrList(eventService.selectEvtSprtrList(searchDTO));
		// 연결 상품 목록 조회
		evtDetail.setGoodsList(eventService.selectEventGoodsList(searchDTO));
		
		return evtDetail;
	}
	@Override
	public EventFoDTO selectEventViewMobile(EventSearchFoDTO searchDTO) throws Exception {

		EventExtendsFoDTO evt = eventService.selectEventView(searchDTO);
		EventFoDTO evtDetail = new EventFoDTO();

		evtDetail.setEventExt(evt);
		if ("ATEND_CHK".equals(evt.getEvtTpCd()) ) {
			// 출석체크 이벤트
			if(StringUtils.isNotEmpty(searchDTO.getMbrNo())){
				// 출석 목록 조회
				List<Integer> atednList = eventService.selectEventAtend(searchDTO);
				evtDetail.setAttendList(atednList);
			}
			List<EventFreeGiftTurnExtendsFoDTO> gift = eventService.selectFreeGiftTurnList(searchDTO);
			evtDetail.setFreeGiftTurnList(gift);
		}else if("SNS_REPLY".equals(evt.getEvtTpCd())){
			// SNS 댓글 이벤트
			
		}else if("TM_CPN".equals(evt.getEvtTpCd())){
			// 타임쿠폰 이벤트
			List<EventFreeGiftTurnExtendsFoDTO> gift = eventService.selectFreeGiftTurnList(searchDTO);
			evtDetail.setFreeGiftTurnList(gift);
		}else{
			
		}
		searchDTO.setOrderBy(evt.getEvtGodSortStdrCd());
        // 상품 그룹 목록 조회
        evtDetail.setSprtrList(eventService.selectEvtSprtrList(searchDTO));
        // 연결 상품 목록 조회
        evtDetail.setGoodsList(eventService.selectEventGoodsList(searchDTO));
		return evtDetail;
	}
	@Override
    public Page<EvtSnsReply> selectSnsReplyList(EventSearchFoDTO searchDTO,
            PageParam pageParam) throws Exception {
		return eventService.selectSnsReplyList(searchDTO, pageParam);
    }
	
	@Override
	public List<EventBoResult> selectEventList(EventBoDTO eventBoDTO) throws Exception {
		List<EventBoResult> resultList = eventService.selectEventList(eventBoDTO);
		return resultList;
	}

	/**
	 * 이벤트 카운트
	 * @param searchDTO
	 * @return
	 */
	public long selectEventCount(EventSearchFoDTO searchDTO) {
		return eventService.selectEventCount(searchDTO);
    }
	
	@Override
    public Page<EventExtendsFoDTO> selectEventEndList(
            EventSearchFoDTO searchDTO, PageParam pageParam) throws Exception {
	    return eventService.selectEventEndList(searchDTO, pageParam);
    }

	@Override
    public List<GoodsListFoDTO> selectEventGodList(EventSearchFoDTO searchDTO) {
	    return eventService.selectEventGoodsList(searchDTO);
    }

	@Override
    public List<EventExtendsFoDTO> selectEventListNoPaging(EventSearchFoDTO searchDTO) throws Exception {
		searchDTO.setEndYn("no");
	    return eventService.selectEventListNoPaging(searchDTO);
    }

    /**
     * [FO]전시 이벤트 기본정보
     * 
     * @param model
     * @param request
     * @param searchDTO
     * @since 2015. 8. 28
     */
    public EventFoDTO selectEventBaseInfo(EventSearchFoDTO searchDTO) throws Exception {
        EventFoDTO evtDetail = new EventFoDTO();

        //이벤트 상세 조회
        evtDetail.setEventExt(eventService.selectEventView(searchDTO));
        
        return evtDetail;
    }
    
    /**
     * 이벤트 경품 목록 가져오기
     * 
     * @param model
     * @param request
     * @param searchDTO
     * @since 2015. 8. 28
     */
    @Override
    public List<EventFreeGiftTurnExtendsFoDTO> selecEvtFreeGiftList(EventSearchFoDTO searchDTO) throws Exception {
        return eventService.selecEvtFreeGiftList(searchDTO);
    }

    /**
     * 경품(쿠폰) 재고 잔여 수량
     * 
     * @param model
     * @param request
     * @param searchDTO
     * @since 2015. 8. 28
     */
    @Override
    public String seleceventCouponDownIssueCheck(EventSearchFoDTO searchDTO) throws Exception {
        return eventService.seleceventCouponDownIssueCheck(searchDTO);
    }
	
    @Override
    public EventBoResult validEventPartcptn(EventBoDTO eventBoDTO, SystemPK systemPK) throws Exception {
        EventBoResult result = new EventBoResult();
        String resultCode = EventResultCode.SUCCESS.toString();
        
        // 로그인 정보 Check
        String loginCheckResult = validLogin(resultCode);
        
        if (EventResultCode.SUCCESS.toString().equals(loginCheckResult)) {
            
            // 응모가능 여부
            eventBoDTO.setDevice(systemPK.getDevice());
            result = eventService.validEventPartcptn(eventBoDTO);
            
            if (EventEnum.NO.toString().equals(result.getApplcnDateCheck())) {
            	resultCode = EventResultCode.INVALID_APPLCN_DATE.toString();
            } else if (EventEnum.NO.toString().equals(result.getDeviceCheck())) {
            	resultCode = EventResultCode.INVALID_DEVICE.toString();
            } else if (EventEnum.NO.toString().equals(result.getMemberTypeCheck())) {
            	resultCode = EventResultCode.INVALID_MEMBER.toString();
            } else if (result.getEvt() == null || StringService.isEmpty(result.getEvt().getEvtNo())) {
            	resultCode = EventResultCode.ERROR.toString();
            }
        } else {
            resultCode = loginCheckResult;
        }
        result.setResultCode(resultCode);
        
        return result;
    }

    @Override
    public EventBoResult selectEventTotalPrize(EventBoDTO eventBoDTO) throws Exception {
        EventBoResult result = eventService.selectEventTotalPrize(eventBoDTO);
        return result;
    }

    @Override
    public String validLogin() throws Exception {
        String resultCode = EventResultCode.SUCCESS.toString();
        return this.validLogin(resultCode);
    }

    private String validLogin(String resultCode) throws Exception {
        String loginMbrNo = null;
        if (ContextService.hasRole()) {
            SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
            if (userDetail != null) {
                loginMbrNo = userDetail.getMbr().getMbrNo();
            }
            else {
                resultCode = EventResultCode.NOT_LOGIN.toString();
            }
            // 로그인 정보 소실된 경우
            if (StringService.isEmpty(loginMbrNo)) {
                resultCode = EventResultCode.NOT_LOGIN.toString();
            }
            // 로그인 하지 않아 권한 미부여
        }
        else {
            resultCode = EventResultCode.NOT_LOGIN.toString();
        }
        return resultCode;
    }

    public void extractResultMessageSett(HttpServletRequest request, EventBoResult result) throws Exception {
        String resultCode = result.getResultCode();
        String resultAdtnCode = result.getResultAdtnCode();
        String resultUrl = null;

        String messageSource = null;
        //String lang = systemPK.getLang();

        SecurityUserDetail userDetail = null;
        if (ContextService.hasRole()) {
            userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
        }

        if (EventResultCode.SUCCESS.toString().equals(resultCode)) {
        	if (result.getEvtFreeGiftInfo() != null && EventFreeGiftKind.COUPON.toString().equals(result.getEvtFreeGiftInfo().getFreeGiftKndCd()) && !EventType.STMP.toString().equals(result.getEvt().getEvtTpCd())) {
        		messageSource = "event.detail.text42";      // 쿠폰이 발급되었습니다.
        	} else {
        		messageSource = "event.detail.text34";      // 응모되었습니다.
        	}
        }
        else if (EventResultCode.INVALID_APPLCN_DATE.toString().equals(resultCode)) {
            messageSource = "event.detail.text4";       // 이벤트 응모 기간이 아닙니다.
        }
        else if (EventResultCode.INVALID_DEVICE.toString().equals(resultCode)) {

            if (StringService.isNotEmpty(result.getDeviceCodes())) {
                List<String> dvc = Arrays.asList(result.getDeviceCodes().split(PromotionSeparator.COMMA.toString()));
                if (dvc.contains(EventDevice.PC.toString())) {
                    // PC 가능 - (미사용)
                    if (dvc.contains(EventDevice.MOBILE_WEB.toString())) {
                        // PC + M_WEB + M_APP 가능 - (미사용)
                        if (!dvc.contains(EventDevice.MOBILE_APP.toString())) {
                            // PC + M_WEB - (미사용)
                        }
                    }
                    else {
                        if (dvc.contains(EventDevice.MOBILE_APP.toString())) {
                            // PC + M_APP 가능 - (미사용)
                        }
                    }
                }
                else {
                    // 모바일 전용
                    if (dvc.contains(EventDevice.MOBILE_WEB.toString())) {
                        // M_WEB + M_APP 가능
                        messageSource = "event.detail.text41"; // 해당 이벤트는 모바일에서만 참여 가능한 이벤트 입니다.
                        if (!dvc.contains(EventDevice.MOBILE_APP.toString())) {
                            // M_WEB 가능 - (미사용)
                        }
                    }
                    else {
                        if (dvc.contains(EventDevice.MOBILE_APP.toString())) {
                            // M_APP 가능
                            messageSource = "event.detail.text41"; // 해당 이벤트는 모바일 앱에서만 참여 가능한 이벤트 입니다.
                        }
                    }
                }
            }
            else {
                throw new VulnerabilityException(null);
            }
        }
        else if (EventResultCode.INVALID_MEMBER.toString().equals(resultCode)) {
            if (MemberTpCd.ONLINE_MBR.toString().equals(userDetail.getMbr().getMbrTpCd())) {
                messageSource = "event.detail.text39";  // 해당 이벤트는 멤버십 회원만 참여 가능 합니다.\n멤버십회원 전환 후 참여 하세요.
                resultUrl = "/secured/mypage/certifyMember";
            }
        }
        else if (EventResultCode.NO_MORE_CHANCE.toString().equals(resultCode)) {
            if (EventApplcnCountSect.EVT_STDR_1BT.toString().equals(resultAdtnCode)) {
                messageSource = "event.detail.text36";  // 이미 참여하셨습니다.
            }
            else if (EventApplcnCountSect.ONE_DAY_1BT.toString().equals(resultAdtnCode)) {
                messageSource = "event.detail.text35";  // 하루 한번만 하실 수 있습니다.
            }
        }
        else if (EventResultCode.UNITY_MBR_ONLY.toString().equals(resultCode)) {
            resultUrl = "/secured/mypage/certifyMember";
        }
        else if (EventResultCode.NOT_LOGIN.toString().equals(resultCode)) {
            resultUrl = "/public/member/login";
        }
        else if (EventResultCode.NOT_PARTCPTN_TGT.toString().equals(resultCode)) {
            messageSource = "event.detail.text55";  // 고객님은 대상자가 아닙니다.
        }
        else if (EventResultCode.CHG_GIFT_SUCCESS.toString().equals(resultCode)) {
            messageSource = "event.detail.text56";  // 응모하신 상품이 수정되었습니다.
        }
        else if (PromotionEnum.CouponIssueLimitReason.IDBY_ISSU_QTY_EXCESS.toString().equals(resultCode)) {
            messageSource = "event.detail.text57";  // 발급수량이 초과되었습니다.
        }
        else if (EventResultCode.MULTI_TRY_PART_SUCCESS.toString().equals(resultCode)) {
            messageSource = "event.detail.text66";  // 쿠폰 중 일부만 발급되었습니다.(회원등급 및 조건에 따라 일부만 발급 될 수 있습니다.)
        }
        else {
        	if (result.getEvt() != null && EventType.TM_CPN.toString().equals(result.getEvt().getEvtTpCd())) {
        		if (EventEnum.NO.toString().equals(result.getPrmAplPdCheck())) {
        			messageSource = "event.detail.text38";      // 참여할 수 없습니다.
        		}
        	}
            messageSource = "event.detail.text38";      // 참여할 수 없습니다.
        }

        result.setMessageSource(messageSource);
        result.setResultUrl(resultUrl);
    }
    
    public EventBoResult extractResultMessageSettMulti(HttpServletRequest request, List<EventBoResult> resultList, EventBoResult returnResult) throws Exception {
    	int resultListSize = resultList.size();
    	int successCount = 0;
    	int failCountByApplcnDate = 0;
    	int failCountByDevice = 0;
    	int failCountByMember = 0;
    	int failCountByNotLogin = 0;
    	int failCountByNoMoreChance = 0;
    	int failCountByUnityMbrOnly = 0;
    	int failCountByIdbyIssuQtyExcess = 0;
    	int failCountByOthers = 0;
    	String returnResultCode = "";
    	
    	for(int i=0; i<resultListSize; i++ ) {
    		EventBoResult result = resultList.get(i);
    	
    		// list안에 기본 정보는 같기에 기본 정보를 복사하기 위한 처리.
    		// 해당 기능은 하위 이벤트만 사용할 경우 처리가 가능하다.
    		// 혹시 추후에 대표 이벤트로 하위 이벤트의 경품을 처리를 하는 경우는 아래처럼 처리하면 정상적인 문구가 나오지 않을 수 있다.
    		if(i == 0) {
    			returnResult = result;
    		}
    		
	        String resultCode = result.getResultCode();
	
	        if (EventResultCode.SUCCESS.toString().equals(resultCode)) {
	        	successCount++;
	        }
	        else if (EventResultCode.INVALID_APPLCN_DATE.toString().equals(resultCode)) {
	        	failCountByApplcnDate++;
	        }
	        else if (EventResultCode.INVALID_DEVICE.toString().equals(resultCode)) {
	        	failCountByDevice++;
	        }
	        else if (EventResultCode.INVALID_MEMBER.toString().equals(resultCode)) {
	        	failCountByMember++;
	        }
	        else if (EventResultCode.NO_MORE_CHANCE.toString().equals(resultCode)) {
	        	failCountByNoMoreChance++;
	        }
	        else if (EventResultCode.UNITY_MBR_ONLY.toString().equals(resultCode)) {
	        	failCountByUnityMbrOnly++;
	        }
	        else if (EventResultCode.NOT_LOGIN.toString().equals(resultCode)) {
	        	failCountByNotLogin++;
	        }
	        else if (EventResultCode.NOT_PARTCPTN_TGT.toString().equals(resultCode)) {
	        	failCountByOthers++;
	        }
	        else if (EventResultCode.CHG_GIFT_SUCCESS.toString().equals(resultCode)) {
	        	failCountByOthers++;
	        }
	        else if (PromotionEnum.CouponIssueLimitReason.IDBY_ISSU_QTY_EXCESS.toString().equals(resultCode)) {
	        	failCountByIdbyIssuQtyExcess++;
	        }
	        else {
	        	failCountByOthers++;
	        }
    	}
    	
    	if(failCountByNotLogin > 0) {
    		returnResultCode = EventResultCode.NOT_LOGIN.toString();
    	}
    	else if(resultListSize == successCount) {
    		returnResultCode = EventResultCode.SUCCESS.toString();
    	}
    	else if(resultListSize == failCountByApplcnDate) {
    		returnResultCode = EventResultCode.INVALID_APPLCN_DATE.toString();
    	}
    	else if(resultListSize == failCountByNoMoreChance
    			|| (failCountByNoMoreChance > 0 && resultListSize == (failCountByNoMoreChance + failCountByOthers))
    			) {
    		returnResultCode = EventResultCode.NO_MORE_CHANCE.toString();
    	}
    	else if(resultListSize == failCountByUnityMbrOnly) {
    		returnResultCode = EventResultCode.UNITY_MBR_ONLY.toString();
    	}
    	else if(resultListSize == failCountByOthers) {
    		returnResultCode = "";
    	}
    	else if(resultListSize == failCountByIdbyIssuQtyExcess
    			|| (failCountByIdbyIssuQtyExcess > 0 && resultListSize == (failCountByIdbyIssuQtyExcess + failCountByOthers))
    			) {
    		returnResultCode = PromotionEnum.CouponIssueLimitReason.IDBY_ISSU_QTY_EXCESS.toString();
    	}
    	else if(failCountByDevice > 0) {
    		returnResultCode = EventResultCode.INVALID_DEVICE.toString();
    	}
    	else if(failCountByMember > 0) {
    		returnResultCode = EventResultCode.INVALID_MEMBER.toString();
    	}
    	else if(successCount > 0 || failCountByOthers > 0 || failCountByUnityMbrOnly > 0 || failCountByIdbyIssuQtyExcess > 0) {
    		returnResultCode = EventResultCode.MULTI_TRY_PART_SUCCESS.toString();
    	}
    	
    	returnResult.setResultCode(returnResultCode);
    	
    	return returnResult;
    }

    /**
     * 이벤트 약관 조회
     * @param sysStplat
     * @return
     * @throws Exception
     */
    @Override
    public List<SysStplat> selectStplatList(String sysStplat) throws Exception {
        return eventService.selectStplatList(sysStplat);
    }

	/**
	 * 이벤트 응모가능여부
	 * 
	 */
    public String selectCampaginEvtInfo(String evtNo) throws Exception {
		return eventService.selectCampaginEvtInfo(evtNo);
	}

	/**
	 * 이벤트 경품 목록 조회
	 */
	public List<EventFreeGiftTurnExtendsFoDTO> selectFreeGiftTurnList(EventSearchFoDTO eventSearchFoDTO) throws Exception {
		return eventService.selectFreeGiftTurnList(eventSearchFoDTO);
	}
	
	/**
	 * 성명, ID 등 마스킹 처리 후 반환(FN_MASKING 사용)
	 * 
	 * @param paramMap maskingType, sourceString, maskingYn
	 * paramMap.maskingType		- 'FLNM':성명, 'ID':ID
	 * paramMap.sourceString	- 마스킹할 string
	 * paramMap.maskingYn		- 마스킹 처리 여부 'Y', 'N'
	 * @return
	 * @throws Exception
	 */
	public String selectFnMakingData(Map<String,String> paramMap) throws Exception {
		return eventService.selectFnMakingData(paramMap);
	}
	
	/**
	 * 이벤트 응모여부 체크 
	 * 
	 * @param searchDTO
	 * @return
	 */
	@Override
	public String checkEventApplCn(EventSearchFoDTO searchDTO) throws Exception{
		return eventService.checkEventApplCn(searchDTO);
	}
	
	@Override
	public Map<String,String> checkEventTarget(String evtNo, String mbrNo, SystemPK pk)
	        throws Exception {
		String mbrTpCd = "";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbrTpCd = userDetail.getMbr().getMbrTpCd();
		}
		
		// 응모 자격 체크
		List<EvtPartcptnTgt> targets = eventService.selectEvtPartcptnTgtList(evtNo);
		boolean isAvailableDevice = false;
		boolean isAvailableMember = false;
		String targetDevice= "";
		String targetMember= "";
		for(EvtPartcptnTgt target : targets){
			if(PromotionAplTarget.DVC.toString().equals(target.getPartcptnTgtTpCd())){
				if("MOBILE_WEB".equals(target.getDvcCd())){
					if(StringUtils.isEmpty(targetDevice)){
						targetDevice += "모바일웹";
					}else{
						targetDevice += ", 모바일웹";
					}
				}else if("MOBILE_APP".equals(target.getDvcCd())){
					if(StringUtils.isEmpty(targetDevice)){
						targetDevice += "모바일앱";
					}else{
						targetDevice += ", 모바일앱";
					}
				}else if("PC".equals(target.getDvcCd())){
					if(StringUtils.isEmpty(targetDevice)){
						targetDevice += "PC";
					}else{
						targetDevice += ", PC";
					}
				}
				
				if(pk.getDevice().equals(target.getDvcCd())){
					isAvailableDevice = true;
				}
			}
			
			if(PromotionAplTarget.TGT_MBR_TP.toString().equals(target.getPartcptnTgtTpCd())){
				if("ONLINE_MBR".equals(target.getTgtMbrTpCd())){
					targetMember = "온라인 회원";
				}else if("UNITY_MBR".equals(target.getTgtMbrTpCd())){
					targetMember = "통합 회원";
				}
				if(mbrTpCd.equals(target.getTgtMbrTpCd())){
					isAvailableMember = true;	
				}
			}
		}
		String resultCode = "OK";
		if(!isAvailableDevice){
			resultCode = "INVALID_DEVICE";
		}
		if(!isAvailableMember){
			resultCode = "INVALID_MEMBER";
		}
		
		Map<String,String> result = new HashMap<String,String>();
		result.put("resultCode", resultCode);
		result.put("targetDevice", targetDevice);
		result.put("targetMember", targetMember);
		return result;
	}
	
	/**
	 * 출석 등록 
	 * @param evtNo
	 * @param mbrNo
	 * @return true
	 * @throws Exception
	 */
	@Override
	public EventAtendResult insertEventAtend(String evtNo, String mbrNo) throws Exception {
		
	    EventAtendResult eventAtendResult = new EventAtendResult();
	    
		// 이벤트 응모 여부 체크
		boolean result = eventService.checkEventAtend(evtNo, mbrNo);
		
		// 오늘 미 응모시 응모
		if (result) {

			// 1. 응모 테이블 insert
			long time = System.currentTimeMillis();
			Date regDt = new Date(time);
			//1-1 응모 테이블 
			long evtApplcnSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
			EvtApplcn evtApplcn = new EvtApplcn();
			evtApplcn.setEvtPartcptnSn(evtApplcnSn);
			evtApplcn.setEvtNo(evtNo);
			evtApplcn.setMbrNo(mbrNo);
//			evtApplcn.setAtendDaycnt(attendCount);
			evtApplcn.setApplcnDt(regDt);
			evtApplcn.setUdterId(mbrNo);
			evtApplcn.setRegtrId(mbrNo);
			evtApplcnRepository.insertEvtApplcn(evtApplcn);
			
			//1-2 출석 참여 테이블 insert
			EvtAtend evtAtend = new EvtAtend();
			evtAtend.setEvtNo(evtNo);
			evtAtend.setMbrNo(mbrNo);
			evtAtend.setUdterId(mbrNo);
			evtAtend.setRegtrId(mbrNo);
			evtAtend.setEvtPartcptnSn(evtApplcnSn);
			eventService.insertEventAtend(evtAtend);
			
			EventSearchFoDTO searchDTO = new EventSearchFoDTO();
			searchDTO.setEvtNo(evtNo);
			searchDTO.setMbrNo(mbrNo);
			// 출석 목록 조회
			List<Integer> attendList = eventService.selectEventAtend(searchDTO);
			
			int attendCount = 0;
			
			if(attendList!=null){
				// 출석횟수
				attendCount = attendList.size();
				eventAtendResult.setAttendCount(attendCount);
			}
			
			
			List<EventFreeGiftTurnExtendsFoDTO> freeGiftList = eventService.selectFreeGiftTurnList(searchDTO);
			for(EventFreeGiftTurnExtendsFoDTO dto : freeGiftList){
			    // 현재 출석횟수 기준 다음 당첨일수 가져오기.
			    if (attendCount <= dto.getAtendDaycnt()) {
			        eventAtendResult.setAbledFreeCheck(true);
			        
			        EvtFreeGiftInfo efgi = new EvtFreeGiftInfo();
			        efgi.setAtendDaycnt(dto.getAtendDaycnt());
			        efgi.setFreeGiftNm(dto.getFreeGiftNm());
			        
			        eventAtendResult.getEvtFreeGiftInfoList().add(efgi);
                }
				
				// 조건 충족시 경품 지급
				if(attendCount == dto.getAtendDaycnt()){

					// 2. 당첨 테이블 insert
					Map<String,Object>conditions = new HashMap<String,Object>();
					conditions.put("EVT_PARTCPTN_SN", evtApplcnSn);
					int turn = getIdGenService().generateDBOrder(sqlSession1, "EVT_PRIZE", "PRIZE_TURN", conditions,  DatabaseType.ORACLE);
					EvtPrize evtPrize = new EvtPrize();
					evtPrize.setEvtNo(evtNo);
					evtPrize.setPrizeTurn(turn);
					evtPrize.setEvtPartcptnSn(evtApplcnSn);
					evtPrize.setPrizeRank(attendCount);
					evtPrize.setPrizeDt(regDt);
					evtPrize.setUdterId(mbrNo);
					evtPrize.setRegtrId(mbrNo);
					evtPrizeRepository.insertEvtPrize(evtPrize);

					// 3. 이벤트 당첨 경품 지급 insert
					EvtPrizeFreeGift evtPrizeFreeGift = new EvtPrizeFreeGift();
					evtPrizeFreeGift.setEvtNo(evtNo);
					evtPrizeFreeGift.setEvtPartcptnSn(evtApplcnSn);
					evtPrizeFreeGift.setFreeGiftTurn(dto.getFreeGiftTurn());
					evtPrizeFreeGift.setPrizeTurn(turn);
					evtPrizeFreeGift.setUdterId(mbrNo);
					evtPrizeFreeGift.setRegtrId(mbrNo);
					evtPrizeFreeGiftRepository.insertEvtPrizeFreeGift(evtPrizeFreeGift);

                    // 4. 경품이 쿠폰일 경우 회원 쿠폰 테이블 insert
                    if (EventFreeGiftKind.COUPON.toString().equals(dto.getFreeGiftKndCd())) {
					    MbrIssuCpn cpn = new MbrIssuCpn();
					    cpn.setPrmNo(dto.getCpnPrmNo());
					    cpn.setMbrNo(mbrNo);
					    cpn.setUdterId(mbrNo);
					    cpn.setRegtrId(mbrNo);
                        promotionService.insertCouponIssue(cpn);
                    }
				}
			}
		}
		
		eventAtendResult.setAtendCheckResult(result);
		return eventAtendResult;
	}
	
	@Override
	public String downloadCpn(String evtNo, String mbrNo, int freeGiftTurn, int prizeRank) throws Exception {
		String resultCode = "OK";
		
		// 선택한 경품 정보 조회
		EvtFreeGiftInfo evtFreeGiftInfo = new EvtFreeGiftInfo();
		evtFreeGiftInfo.setEvtNo(evtNo);
		evtFreeGiftInfo.setFreeGiftTurn(freeGiftTurn);
		EvtFreeGiftInfo gift = evtFreeGiftInfoRepository.selectEvtFreeGiftInfo(evtFreeGiftInfo);
		
		// 1. 이벤트 유효성 체크 : 이벤트 기간, 일자별 시간, 상태 체크
		boolean evtEnable = eventService.getEvtApplcnEnable(evtNo);
		if(!evtEnable){
			return "INVALID_STATUS";
		}
		
		// 2. 오늘 이벤트 참여 여부 체크
		EventSearchFoDTO searchDTO = new EventSearchFoDTO();
		searchDTO.setEvtNo(evtNo);
		searchDTO.setMbrNo(mbrNo);
		searchDTO.setDayCheckCpnYn("Y");
		
		boolean check = eventService.checkEvtApplcnToday(searchDTO);
		if(!check){
			resultCode = "AREADY_JOIN";
			return resultCode;
		}
		
		// 3. 쿠폰 남은 수량 체크
		boolean qtyCheck = eventService.checkTimeCouponPrizeCount(evtNo, freeGiftTurn, gift.getPrpareFreeGiftQty());
		if(!qtyCheck){
			return "NO_QTY";
		}
		
		// 4. 경품 지급
		// 4-1. 응모 테이블 insert
		long evtApplcnSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
		EvtApplcn evtApplcn = new EvtApplcn();
		evtApplcn.setEvtPartcptnSn(evtApplcnSn);
		evtApplcn.setEvtNo(evtNo);
		evtApplcn.setMbrNo(mbrNo);
		
		long time = System.currentTimeMillis();
		Date regDt = new Date(time);
		evtApplcn.setApplcnDt(regDt);
		evtApplcn.setUdterId(mbrNo);
		evtApplcn.setRegtrId(mbrNo);
		evtApplcnRepository.insertEvtApplcn(evtApplcn);
		
		// 4-2. 당첨 테이블 insert
		Map<String,Object>conditions = new HashMap<String,Object>();
		conditions.put("EVT_PARTCPTN_SN", evtApplcnSn);
		int turn = getIdGenService().generateDBOrder(sqlSession1, "EVT_PRIZE", "PRIZE_TURN", conditions,  DatabaseType.ORACLE);
		EvtPrize evtPrize = new EvtPrize();
		evtPrize.setEvtNo(evtNo);
		evtPrize.setPrizeTurn(turn);
		evtPrize.setEvtPartcptnSn(evtApplcnSn);
		evtPrize.setPrizeRank(prizeRank);
		evtPrize.setPrizeDt(regDt);
		evtPrize.setUdterId(mbrNo);
		evtPrize.setRegtrId(mbrNo);		
		evtPrizeRepository.insertEvtPrize(evtPrize);

		// 4-3. 이벤트 당첨 경품 지급 insert
		EvtPrizeFreeGift evtPrizeFreeGift = new EvtPrizeFreeGift();
		evtPrizeFreeGift.setEvtNo(evtNo);
		evtPrizeFreeGift.setEvtPartcptnSn(evtApplcnSn);
		evtPrizeFreeGift.setFreeGiftTurn(freeGiftTurn);
		evtPrizeFreeGift.setPrizeTurn(turn);
		evtPrizeFreeGift.setUdterId(mbrNo);
		evtPrizeFreeGift.setRegtrId(mbrNo);			
		evtPrizeFreeGiftRepository.insertEvtPrizeFreeGift(evtPrizeFreeGift);
		
		// 4-4. 쿠폰 발급
//		MbrIssuCpn cpn = new MbrIssuCpn();
//		cpn.setPrmNo(gift.getCpnPrmNo());
//		cpn.setMbrNo(mbrNo);
//		promotionService.insertCouponIssue(cpn);
		
		PromotionBoDTO promotionBoDTO = new PromotionBoDTO();
		List<String> listMbrNo = new ArrayList<String>();
		listMbrNo.add(mbrNo);
		promotionBoDTO.setListMbrNo(listMbrNo);
        promotionBoDTO.getMbrIssuCpn().setPrmNo(gift.getCpnPrmNo());
//        promotionBoDTO.getMbrIssuCpn().setIssuAdminId(loginId);	//DB CONSTRAINT R_534 확인
        promotionBoDTO.getMbrIssuCpn().setRegtrId(mbrNo);
        promotionBoDTO.getMbrIssuCpn().setUdterId(mbrNo);

        promotionBoDTO.getPrmCpn().setPrmNo(gift.getCpnPrmNo());
        promotionBoDTO.getPrmCpn().setUdterId(mbrNo);
        promotionBoDTO.getPrmCpn().setRegtrId(mbrNo);
        
        Prm prm = new Prm();
        prm.setPrmNo(gift.getCpnPrmNo());
        promotionBoDTO.setPrm(prm);
        //쿠폰 회원 발급 가능 체크
        PromotionBoResult couponResult = promotionService.selectCouponIssueUseCheck(promotionBoDTO);

        if(CouponIssueLimitReason.SUCCES.toString().equals(couponResult.getChkMsg())){
//        if("".equals(couponResult.getChkMsg())){
        	//회원별 쿠폰 발급 처리 후 mbrIssuCpn결과 값 return
        	List<MbrIssuCpnResult> couponIssueResult = promotionService.selectCouponIssueCheckResult(promotionBoDTO, couponResult.getPrmCpn());
        	
        	for(MbrIssuCpnResult info : couponIssueResult){
        		if("01".equals(info.getRstCd())){
//        			chkMsg = "\n\nID당 발급 수량을 초과하는 사용자가 존재합니다.\n발급수량 초과 대상자는 제외 되었습니다.";
        			
        			resultCode = "INVALID_COUPON";
        			throw new NotSupportedException(null);
        		}
        	}
        	
        }else{
        	resultCode = "INVALID_COUPON";
        	throw new NotSupportedException(null);
        	// 발급가능일, 회원쿠폰 발급 가능 수 오류
        }
		// 4-5. 경품 수량 감소 : 타임쿠폰은 경품수량 감소하지 않음 (일자별 수량을 관리하기 때문)
//		eventService.updateFreeGiftInvQty(evtNo, freeGiftTurn);
		
	    return resultCode;
    }

	@Override
    public void insertReply(EvtSnsReply reply, String mbrNo) throws Exception {
		// 1. 응모 테이블 insert
		long evtApplcnSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
		EvtApplcn evtApplcn = new EvtApplcn();
		evtApplcn.setEvtPartcptnSn(evtApplcnSn);
		evtApplcn.setEvtNo(reply.getEvtNo());
		evtApplcn.setMbrNo(mbrNo);
		long time = System.currentTimeMillis();
		Date regDt = new Date(time);
		evtApplcn.setApplcnDt(regDt);
		evtApplcn.setUdterId(mbrNo);
		evtApplcn.setRegtrId(mbrNo);		
		evtApplcnRepository.insertEvtApplcn(evtApplcn);
		
		// 2. 댓글 테이블 insert
		long snsReplySn = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_REPLY", DatabaseType.ORACLE);
		reply.setReplySn(snsReplySn);
		reply.setEvtPartcptnSn(evtApplcnSn);
		reply.setUdterId(mbrNo);
		reply.setRegtrId(mbrNo);
		evtSnsReplyRepository.insertEvtSnsReply(reply);
    }
	
	@Override
	public int insertEvtApplCn(SystemPK systemPK, EvtApplcnBoDTO applcnBoDTO) throws Exception {
		return eventService.insertEvtApplCn(applcnBoDTO);
	}
	
	@Override
	public int insertEventPrize(SystemPK systemPK, EventBoDTO eventBoDTO) throws Exception {
		return eventService.insertEventPrize(eventBoDTO);
	}
	
	/**
     * SNS 인증정보 token 값 세팅
     */
	@Override
	public void setModelMbrSnsInfo(String mbrNo, Model model, HttpServletRequest request) throws Exception {
		// 페이스북 로그인 후 callback일경우
		model.addAttribute("facebookLogin", request.getParameter("facebook"));
		String twitterLogin = "";
        String kakaoToken = "";

		if(request.getParameter("facebook") == null || request.getParameter("facebook").isEmpty()) {
			MbrSns paramMbrSns = new MbrSns();
			paramMbrSns.setMbrNo(mbrNo);
			paramMbrSns.setLastUseSnsYn("Y");
	        List<MbrSns> mbrSnsList = eventService.selectMbrSnsList(paramMbrSns);
	        if(mbrSnsList != null && mbrSnsList.size() > 0) {
	        	for(MbrSns mbrSns : mbrSnsList){
	        		if(MbrSnsCd.TWTR.toString().equals(mbrSns.getMbrSnsCd())) {
	        			// 트위터 로그인 후 callback일경우
	        			if(mbrSns.getMbrSnsCrtfcToknId().length() > 0) {
	        				twitterLogin = "Y";
	        				break;
	        			}
	        			request.getSession().removeAttribute("twitterLoginY");
	        		} else if(MbrSnsCd.KKOST.toString().equals(mbrSns.getMbrSnsCd())) {
	        			// 카카오스토리 로그인 후 callback일경우
	        			if(mbrSns.getMbrSnsCrtfcToknId().length() > 0) {
	        				kakaoToken = mbrSns.getMbrSnsCrtfcToknId();
	        				break;
	        			}
	        			request.getSession().removeAttribute("kakaoToken");
	        		}
	        	}

	        	// 최종사용 SNS 여부 update
				MbrSns paramMbrSns2 = new MbrSns();
				paramMbrSns2.setMbrNo(mbrNo);
				paramMbrSns2.setLastUseSnsYn("N");
		        eventService.updateMbrSnsLastUseYn(paramMbrSns2);
	        }
		}

        model.addAttribute("twitterLogin", twitterLogin);
        model.addAttribute("kakaoToken", kakaoToken);
    }

	/**
     * SNS 인증정보 등록
     */
	@Override
	public int insertMbrSnsMerge(MbrSns mbrSns) throws Exception {
		return eventService.insertMbrSnsMerge(mbrSns);
	}

	/**
     * SNS 인증정보 리스트 조회
     */
	@Override
    public List<MbrSns> selectMbrSnsList(MbrSns mbrSns) throws Exception {
    	return eventService.selectMbrSnsList(mbrSns);
    }

    /**
     * 최종사용 SNS 여부 update
     */
	@Override
	public int updateMbrSnsLastUseYn(MbrSns mbrSns) throws Exception {
		return eventService.updateMbrSnsLastUseYn(mbrSns);
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	@Override
	public boolean checkEventEnable(String evtNo) throws Exception {
		return eventService.checkEventEnable(evtNo);
	}

    @Override
    public EventBoResult insertEventStampApplcn(EventBoDTO eventBoDTO, SystemPK systemPK) throws Exception {

        // Step 1. 이벤트 응모 insert
        eventService.insertEvtApplcn(eventBoDTO);
        EventBoResult validResult = eventBoDTO.getEventBoResult();

        // Step 2. 당첨처리 - 현물, 쿠폰, 포인트
        if (validResult.getEvtFreeGiftInfo() != null && validResult.getEvtFreeGiftInfo().getPrizeQty() != null && eventBoDTO.getEvtApplcn() != null) {

            // 이벤트 당첨 및 당첨경품 insert
            int prizeCnt = eventService.insertEventPrize(eventBoDTO);

            if (prizeCnt > 0) {
                String evtNo = eventBoDTO.getEvtNo();
                long prpareFreeGiftQty = validResult.getEvtFreeGiftInfo().getPrpareFreeGiftQty();   // 경품재고수량
                String freeGiftKndCd = validResult.getEvtFreeGiftInfo().getFreeGiftKndCd();         // 경품유형코드
                int freeGiftTurn = validResult.getEvtFreeGiftInfo().getFreeGiftTurn();              // 경품순번
                
                // Step 2-1 쿠폰 및 포인트 지급
                for (int i = 0; i < prpareFreeGiftQty; i++) {
                    // 경품이 쿠폰일 경우 회원 쿠폰 테이블 insert
                    if (EventFreeGiftKind.COUPON.toString().equals(freeGiftKndCd)) {
                    	PromotionBoDTO promotionBoDTO = new PromotionBoDTO();
                		List<String> listMbrNo = new ArrayList<String>();
                		listMbrNo.add(eventBoDTO.getMbrNo());
                		promotionBoDTO.setListMbrNo(listMbrNo);
                        promotionBoDTO.getMbrIssuCpn().setPrmNo(validResult.getEvtFreeGiftInfo().getCpnPrmNo());
                        promotionBoDTO.getMbrIssuCpn().setRegtrId(eventBoDTO.getMbrNo());
                        promotionBoDTO.getMbrIssuCpn().setUdterId(eventBoDTO.getMbrNo());
                        promotionBoDTO.getPrmCpn().setPrmNo(validResult.getEvtFreeGiftInfo().getCpnPrmNo());
                        promotionBoDTO.getPrmCpn().setUdterId(eventBoDTO.getMbrNo());
                        promotionBoDTO.getPrmCpn().setRegtrId(eventBoDTO.getMbrNo());
                        
                        Prm prm = new Prm();
                        prm.setPrmNo(validResult.getEvtFreeGiftInfo().getCpnPrmNo());
                        promotionBoDTO.setPrm(prm);
                        //쿠폰 회원 발급 가능 체크
                        PromotionBoResult couponResult = promotionService.selectCouponIssueUseCheck(promotionBoDTO);

                        if(CouponIssueLimitReason.SUCCES.toString().equals(couponResult.getChkMsg())){
                        	//회원별 쿠폰 발급 처리 후 mbrIssuCpn결과 값 return
                        	List<MbrIssuCpnResult> couponIssueResult = promotionService.selectCouponIssueCheckResult(promotionBoDTO, couponResult.getPrmCpn());
                        	for(MbrIssuCpnResult info : couponIssueResult){
                        		if(CouponIssueLimitReason.SUCCES.toString().equals(info.getRstCd())){
                        			//경품 수량 감소
                                    eventService.updateFreeGiftInvQty(evtNo, freeGiftTurn);
                        		}
                        	}
                        }
                    }
                    else if (EventFreeGiftKind.WEB_POINT.toString().equals(freeGiftKndCd)) {
						this.insertWebpnt(validResult);
						eventService.updateFreeGiftInvQty(evtNo, freeGiftTurn);
					}
                }
                validResult.setResultCode(EventResultCode.SUCCESS.toString());
            }
        }
        eventBoDTO.setEventBoResult(validResult);

        return validResult;
    }
    
	@Override
	public EventBoResult insertPartcptnEvent(EventBoDTO eventBoDTO) throws Exception {

		// 이벤트 응모
		// Step 1. 이벤트 유형에 따라 분기
		int applcnCnt = eventService.insertEvtApplcn(eventBoDTO);
		EventBoResult validResult = eventBoDTO.getEventBoResult();
		String evtTpCd = validResult.getEvt().getEvtTpCd();

		// Step 2. 이벤트 당첨
		if (applcnCnt > 0) {

			// 이벤트 유형별
			// 전체추첨
			if (EventType.ALL_DRWT.toString().equals(evtTpCd)) {
				// 당첨제외 - 처리 없음
				validResult.setResultCode(EventResultCode.SUCCESS.toString());
			}
			else if (EventType.ALL_PRIZE.toString().equals(evtTpCd)) {
				// 전체당첨 케이스
				int prizeCnt = eventService.insertEventPrize(eventBoDTO);
				
				if (prizeCnt > 0) {
					
					String evtNo = eventBoDTO.getEvtNo();
					long prpareFreeGiftQty = validResult.getEvtFreeGiftInfo().getPrpareFreeGiftQty();   // 경품재고수량
					String freeGiftKndCd = validResult.getEvtFreeGiftInfo().getFreeGiftKndCd();         // 경품유형코드
					int freeGiftTurn = validResult.getEvtFreeGiftInfo().getFreeGiftTurn();              // 경품순번
					long successCount = 0;
					
					// Step 2-1 쿠폰 및 포인트 지급
					for (int i = 0; i < prpareFreeGiftQty; i++) {
						
						// 경품이 쿠폰일 경우 회원 쿠폰 테이블 insert
						if (EventFreeGiftKind.COUPON.toString().equals(freeGiftKndCd)) {
							this.extractCouponIssue(validResult);
							if(CouponIssueLimitReason.SUCCES.toString().equals(validResult.getResultCode())){
								successCount++;
							}
							else if(CouponIssueLimitReason.ISMCBTW_OFF.toString().equals(validResult.getResultCode())){
								throw new EventFreeGiftNotJoinTimeException(null);
							}
							else {
								break;
							}
						}
						// 경품이 현물인 경우(현물인 경우는 성공으로 반환 - insertPartcptnEvent 함수 들어오기 전 수량검증 처리)
						else if (EventFreeGiftKind.ACTUAL_THING.toString().equals(freeGiftKndCd)) {
							successCount++;
						}
						// 경품이 웹포인트인 경우
						else if (EventFreeGiftKind.WEB_POINT.toString().equals(freeGiftKndCd)) {
							this.insertWebpnt(validResult);
						}
						eventService.updateFreeGiftInvQty(evtNo, freeGiftTurn);
					}
					
					/* 쿠폰 발급 검증 내용
					1. 쿠폰 프로모션 상태 체크
					 - 중지 상태 이면 사용 불가

					2. 쿠폰 발급 수량 체크
					 - 발행수량 제한코드 가 제한 이고 전체 발행 수량(프로모션 내 지급될 쿠폰 수량)이 이미 발행한 수량보다 많으면 사용 불가

					3. 발급 가능 일시 체크
					 - 쿠폰 발급 가능 기간이 아니면 사용 불가

					4. 회원번호 유효 체크
					 - 해당 회원의 상태가 정상 만 사용 가능

					5. 회원ID별 발급 제한 체크
					 - 중복 발급 제한 코드 가 제한 이고 ID별 발급 제한 수량을 초과하면 사용 불가
					
					1, 3, 4 의 경우는 같은 경품이기 때문에 첫번째 loop에서 반환된다.
					2, 5 의 경우만 부분발급의 경우가 생김.
					그리하여 전체성공이거나 성공건수가 있으면 성공으로 반환하고 성공이 한개도 없는 경우는 반환받은 오류코드를 그대로 사용한다.
					*/
					if(successCount > 0 || prpareFreeGiftQty == successCount) {
						validResult.setResultCode(EventResultCode.SUCCESS.toString());
					}
				}
			}
		}
		else {
			if (StringService.isEmpty(validResult.getResultCode())) {
				validResult.setResultCode(EventResultCode.ERROR.toString());
			}
		}

		return validResult;
	}

	// 쿠폰발급
	private void extractCouponIssue(EventBoResult validResult) throws Exception {
		String mbrNo = validResult.getMbrNo();
		String prmNo = validResult.getEvtFreeGiftInfo().getCpnPrmNo();
		// 쿠폰 발급
		PromotionBoDTO promotionBoDTO = new PromotionBoDTO();
		List<String> listMbrNo = new ArrayList<String>();
		listMbrNo.add(mbrNo);
		promotionBoDTO.setListMbrNo(listMbrNo);
		promotionBoDTO.getMbrIssuCpn().setPrmNo(prmNo);
		promotionBoDTO.getMbrIssuCpn().setRegtrId(mbrNo);
		promotionBoDTO.getMbrIssuCpn().setUdterId(mbrNo);
		promotionBoDTO.getPrmCpn().setPrmNo(prmNo);
		promotionBoDTO.getPrmCpn().setUdterId(mbrNo);
		promotionBoDTO.getPrmCpn().setRegtrId(mbrNo);

		Prm prm = new Prm();
		prm.setPrmNo(prmNo);
		promotionBoDTO.setPrm(prm);
		//쿠폰 회원 발급 가능 체크
		PromotionBoResult couponResult = promotionService.selectCouponIssueUseCheck(promotionBoDTO);

		if(CouponIssueLimitReason.SUCCES.toString().equals(couponResult.getChkMsg())){
			//회원별 쿠폰 발급 처리 후 mbrIssuCpn결과 값 return
			List<MbrIssuCpnResult> couponIssueResult = promotionService.selectCouponIssueCheckResult(promotionBoDTO, couponResult.getPrmCpn());
			for(MbrIssuCpnResult info : couponIssueResult){
				if(CouponIssueLimitReason.IDBY_ISSU_QTY_EXCESS.toString().equals(info.getRstCd())){
					validResult.setResultCode(CouponIssueLimitReason.IDBY_ISSU_QTY_EXCESS.toString());
					return;
				}
				else {
					validResult.setResultCode(info.getRstCd());
				}
			}
		}
		else {
			validResult.setResultCode(couponResult.getChkMsg());
		}
	}

	// P포인트 지급 이벤트용
	private void insertWebpnt(EventBoResult validResult) throws Exception {

		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
		Mbr mbr = userDetail.getMbr();

		// 온라인회원만 P포인트 지급 대상
		if (mbr.getMbrTpCd().equals(MemberEnum.MemberTpCd.UNITY_MBR.toString())) {

			// P포인트적립
			MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
			mbrWebpntHist.setMbrNo(mbr.getMbrNo());
			mbrWebpntHist.setWebpntTpCd("WEBPNT");
			mbrWebpntHist.setWebpntResnCd("EVT");
			mbrWebpntHist.setWebpntDetailResnCd(validResult.getEvt().getEvtTpCd());
			mbrWebpntHist.setWebpntAmt(validResult.getEvtFreeGiftInfo().getWebpntAmt());
			mbrWebpntHist.setWebpntStatCd("ACCML_DCSN");
			mbrWebpntHist.setUseBegDt(validResult.getEvtFreeGiftInfo().getWebpntUseBegDt());
			mbrWebpntHist.setUseEndDt(validResult.getEvtFreeGiftInfo().getWebpntUseEndDt());
			mbrWebpntHist.setEvtNo(validResult.getEvt().getEvtNo());
			mbrWebpntHist.setResnDscr(validResult.getEvt().getEvtNm());
			mbrWebpntHist.setMallId(getIdGenService().getEnvValue(SysInfoEnum.MALL_ID_SYSTEM_VARIABLE_ID.toString()));
			
			memberBenefitCommandService.insertWebPoint(mbrWebpntHist);
		}
		else {
			throw new EventOnlineMemberOnlyException(null);
		}
	}

	// 쿠폰발급
	private void extractCouponIssueByCpnPrmNo(String cpnPrmNo) throws Exception {

		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
		String mbrNo = userDetail.getMbr().getMbrNo();

		MbrIssuCpn cpn = new MbrIssuCpn();
		cpn.setPrmNo(cpnPrmNo);
		cpn.setMbrNo(mbrNo);
		cpn.setUdterId(mbrNo);
		cpn.setRegtrId(mbrNo);
		promotionService.insertCouponIssue(cpn);
		// 쿠폰 발급 재고 수량 update
		PrmCpn prmCpn = new PrmCpn();
		prmCpn.setPrmNo(cpnPrmNo);
		promotionService.updateIssueCouponInvQty(prmCpn);
	}

    /*
     * 여러장의 쿠폰 다운로드
     */
	@Override
	public SingleEventResultDto insertMultipleCouponIssued(SystemPK systemPK, EventBoDTO eventBoDTO, Mbr mbr) throws Exception {
		SingleEventResultDto result = new SingleEventResultDto();
//		result.setRtnMsg("SNS 등록에 실패했습니다.");
		result.setRtnCd(EventResultCode.ERROR.toString());

		EventBoDTO paramEventBoDTO = new EventBoDTO();
		paramEventBoDTO.setEvtNo(eventBoDTO.getEvtNo());
		paramEventBoDTO.setMbrNo(mbr.getMbrNo());
		paramEventBoDTO.setPrizeRank(eventBoDTO.getPrizeRank());

		EventBoResult boResult = this.validEventPartcptn(paramEventBoDTO, systemPK);
		if (boResult.isCheckResult()) {
			try {
				EventBoResult validResult =  eventService.validEventApplcn(paramEventBoDTO);
				if (EventEnum.YES.toString().equals(validResult.getApplcnCheck()) && validResult.getEvtFreeGiftInfo() != null) {
					// 1. 응모 테이블 insert
					long evtApplcnSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
					EvtApplcn evtApplcn = new EvtApplcn();
					evtApplcn.setEvtPartcptnSn(evtApplcnSn);
					evtApplcn.setEvtNo(eventBoDTO.getEvtNo());
					evtApplcn.setMbrNo(mbr.getMbrNo());
					long time = System.currentTimeMillis();
					Date regDt = new Date(time);
					evtApplcn.setApplcnDt(regDt);
					evtApplcn.setUdterId(mbr.getMbrNo());
					evtApplcn.setRegtrId(mbr.getMbrNo());
					evtApplcnRepository.insertEvtApplcn(evtApplcn);

					//TODO

					// 2. 쿠폰 발급 및 잔여수량 차감
					List<EventFreeGiftTurnExtendsFoDTO> freeGiftTurnList = new ArrayList<EventFreeGiftTurnExtendsFoDTO>();
					EventSearchFoDTO eventSearchFoDTO = new EventSearchFoDTO();
					eventSearchFoDTO.setEvtNo(paramEventBoDTO.getEvtNo());
					freeGiftTurnList = eventService.selectFreeGiftTurnList(eventSearchFoDTO);
					for(int i=0; i<freeGiftTurnList.size(); i++) {
						// 2-1. 쿠폰 발급
						EventFreeGiftTurnExtendsFoDTO freeGiftTurnExtendsInfo = new EventFreeGiftTurnExtendsFoDTO();
						freeGiftTurnExtendsInfo = freeGiftTurnList.get(i);
						this.extractCouponIssueByCpnPrmNo(freeGiftTurnExtendsInfo.getCpnPrmNo());

						// 2-2. 이벤트 경품 정보 잔여수량 차감
						int freeGiftTurn = freeGiftTurnExtendsInfo.getFreeGiftTurn();	// 경품순번
						eventService.updateFreeGiftInvQty(eventBoDTO.getEvtNo(), freeGiftTurn);
					}

					result.setRtnMsg("이벤트 참여가 완료되었습니다.");
					result.setRtnCd(EventResultCode.SUCCESS.toString());
				} else {
					result.setRtnMsg("이미 응모하셨습니다.");
					result.setRtnCd(EventResultCode.NO_MORE_CHANCE.toString());
				}
			} catch (Exception e) {
				log.error("",e);
			}
		} else {
			throw new NotSupportedException(null);
		}

		return result;
	}

}
