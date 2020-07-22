package com.plgrim.ncp.cmp.member.fo.auth;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.plgrim.ncp.base.entities.datasource1.inf.InfMbrGrdInfoRecptn;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrGrd;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntcExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrLoginLog;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrTodayGod;
import com.plgrim.ncp.base.enums.BskEnum.MbrSect;
import com.plgrim.ncp.base.enums.GoodsEnum.TodayGodSectCd;
import com.plgrim.ncp.base.enums.MemberBenefitEnum;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.biz.display.data.InterestSearchFoDTO;
import com.plgrim.ncp.biz.display.result.GoodsInterestsGodFoResult;
import com.plgrim.ncp.biz.goods.data.GoodsPriceSearchDTO;
import com.plgrim.ncp.biz.goods.service.GoodsContentService;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MemberBenefitApiResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitResult;
import com.plgrim.ncp.biz.member.service.MemberBaseCommandService;
import com.plgrim.ncp.biz.member.service.MemberBaseSelectService;
import com.plgrim.ncp.biz.member.service.MemberInterfaceService;
import com.plgrim.ncp.cmp.display.fo.DxmDisplaySelectComponent;
import com.plgrim.ncp.cmp.member.fo.MemberBenefitFOComponent;
import com.plgrim.ncp.cmp.member.fo.MemberJoinFOComponent;
import com.plgrim.ncp.cmp.product.fo.GoodsPriceFOComponent;
import com.plgrim.ncp.commons.auth.naver.NaverAuthenticationFilter;
import com.plgrim.ncp.commons.auth.naver.NaverUserService;
import com.plgrim.ncp.commons.auth.naver.NaverValidationResult;
import com.plgrim.ncp.commons.taglib.Functions;
import com.plgrim.ncp.framework.commons.ConfigService;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.LocaleService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SecurityJsonResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.RedirectOrder;
import com.plgrim.ncp.framework.enums.SecurityParam;
import com.plgrim.ncp.framework.security.SecuritySessionInitHandler;
import com.plgrim.ncp.framework.utils.XSSUtil;
import com.plgrim.ncp.interfaces.member.data.MemberGradeInformationSDO;
import com.plgrim.ncp.interfaces.member.data.MemberInformationSDO;
import com.plgrim.ncp.interfaces.member.data.MemberLoginInfoSDO;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 인증이 성공이 된 후 처리를 담당 한다.
 */
@Data
@Slf4j
public class SecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	RequestCache requestCache = new HttpSessionRequestCache();

	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/* referer 헤더 사용 유무 */
	boolean referer = false;

	/* 기본 성공 url */
	String defaultUrl;

	String popupSuccessURL;

	@Autowired(required = false)
	SecuritySessionInitHandler securitySessionInitHandler;

	@Autowired(required = false)
	SessionRegistry sessionRegistry;

	@Autowired
	IdGenService idGenService;

	@Autowired
	ConfigService configService;

	@Autowired
	NaverUserService naverUserService;
	
	@Autowired
	MemberBaseSelectService memberBaseSelectService;

	@Autowired
	MemberBaseCommandService memberBaseCommandService;

	@Autowired
    MemberJoinFOComponent memberJoinFOComponent;
	
	@Autowired
	private MemberBenefitFOComponent memberBenefitFOComponent;

	@Autowired
	MemberInterfaceService memberInterfaceService;
	
	@Autowired
	AuthenticationMemberService authenticationMemberService;
	
	@Autowired
	DxmDisplaySelectComponent dxmDisplaySelectComponent;
	
	@Autowired
	GoodsPriceFOComponent goodsPriceFOComponent;
	
	@Autowired
	private GoodsContentService goodsContentService;
	
	/**
	 * 프런트 (PC, MOBILE) 회원들에게 부여되는 권한.
	 */
	final static String FRONT_ROLE_NAME = "ROLE_USER";


	/** {@inheritDoc} */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		/*** 인증 성공후 business logic **/
		String password = StringService.trimToEmpty(request.getParameter(SecurityParam.PASSWORD_INPUT_NAME.toString()));
		
		HttpSession session = request.getSession();
		
		try {

			SystemPK pk = idGenService.getAutoGeneratorSystemPK(request);

			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			Mbr mbr = userDetail.getMbr();
			
			boolean needPasswordUpdateFlag = false;

			handleNaverAuthenticationSuccess(request, mbr);
			// 인증이 실패 시키는 방법은 null 값을 리턴 하면 됨.
			
			MemberInformationSDO memberInformationSDO = null;
			// ERP에는 회원정보가 있지만 온라인에는 없는 경우 온라인에 회원정보를 등록
			if(session.getAttribute(SessionEnum.ONLINE_INSERT_FLAG.toString()) != null
					&& JsonService.marshalling(true).equals(session.getAttribute(SessionEnum.ONLINE_INSERT_FLAG.toString()))) {
				memberInformationSDO = (MemberInformationSDO) session.getAttribute(SessionEnum.MEMBER_INFORMATION_SDO.toString());
				
				// 1. ERP에서 받은 CID번호로 온라인에 회원정보가 있는지 확인.(UDT_DT 최근 순으로 조회)
				List<Mbr> mbrList = memberBaseSelectService.selectMemberByErpCstmrNo(memberInformationSDO.getCid());
				
				// 2. ERP에서 받은 CID번호로 온라인에 회원정보가 있으면 기존 MBR_NO로 등록
				// 기존 MBR_NO가 탈퇴이면 ERP_CSTMR_NO 컬럼을 NULL로 처리.
				// 기존 MBR_NO가 전부 탈퇴이면 INSERT 처리.
				// 기존 MBR_NO가 휴면이면 해당 MBR_NO로 UPDATE 처리.
				int drmncyCount = 0;
				if(mbrList != null && !mbrList.isEmpty()) {
					for(Mbr tmpMbr : mbrList) {
						if(MemberEnum.MemberStatCd.DRMNCY.toString().equals(tmpMbr.getMbrStatCd())) {
							drmncyCount++;
						}
					}
					// 2-1. 휴면이 없으면 ERP_CSTMR_NO 컬럼을 NULL로 처리 후 INSERT.
					if(drmncyCount == 0) {
						for(Mbr tmpMbr : mbrList) {
							memberBaseCommandService.updateMemberForErpCstmrNoInit(tmpMbr);
						}
						
						userDetail.getMbr().setMbrPw(IdGenService.generateSHA256(password));
						memberBaseCommandService.insertOnlineMemberByErp(request, pk, memberInformationSDO, userDetail.getMbr());
					}
					// 2-2. 휴면은 UPDATE, 탈퇴는 ERP_CSTMR_NO 컬럼을 NULL로 처리.
					else {
						for(Mbr tmpMbr : mbrList) {
							// 휴면이면 UPDATE 처리. 
							if(MemberEnum.MemberStatCd.DRMNCY.toString().equals(tmpMbr.getMbrStatCd())) {
								userDetail.setMbr(tmpMbr);
								userDetail.getMbr().setMbrId(memberInformationSDO.getId());
								userDetail.getMbr().setMbrPw(IdGenService.generateSHA256(password));
								userDetail.getMbr().setMbrErpPw(IdGenService.generateMD5(password));
								memberBaseCommandService.updateOnlineMemberByErp(request, pk, memberInformationSDO, userDetail.getMbr());
								break;	// 최근 1 건만 처리.
							}
							else {
								memberBaseCommandService.updateMemberForErpCstmrNoInit(tmpMbr);
							}
						}
					}
				}
				// 3. ERP에서 받은 CID번호로 온라인에 회원정보가 없으면 신규 등록
				else {
					userDetail.getMbr().setMbrPw(IdGenService.generateSHA256(password));
					memberBaseCommandService.insertOnlineMemberByErp(request, pk, memberInformationSDO, userDetail.getMbr());
				}
				
				// 4. 회원정보 등록 후 재인증
				userDetail = (SecurityUserDetail) authenticationMemberService.loadUserByUsername(mbr.getMbrId());
			}
			else {
//				// 로그인 실패횟수 0
//				userDetail.getMbr().setMbrLoginLastFailrCount(0);
//				userDetail.getMbr().setDrmcResnCont("");
//	
				//boolean needPasswordUpdateFlag = false;
				
				// 온라인의 SHA256이 없거나
				if(userDetail.getMbr().getMbrPw() == null) {
					userDetail.getMbr().setMbrPw(IdGenService.generateSHA256(password));
					
					needPasswordUpdateFlag = true;
				}
				
				// 온라인의 md5가 없거나
				if (userDetail.getMbr().getMbrErpPw() == null) {
					userDetail.getMbr().setMbrErpPw(IdGenService.generateMD5(password));

					needPasswordUpdateFlag = true;
				}
				
				// 현재 입력된 패스워드가 ERP의 md5와 비교하고 온라인의 SHA256으로 비교하여
				// md5는 일치하고 SHA256은 일치하지 않으면 SHA256 패스워드를 현재 입력된 패스워드로 변경한다.
				if(IdGenService.generateMD5(password).toLowerCase().equals(userDetail.getMbr().getMbrErpPw().toLowerCase())
					&& !IdGenService.generateSHA256(password).toLowerCase().equals(userDetail.getMbr().getMbrPw().toLowerCase())
					) {
					userDetail.getMbr().setMbrPw(IdGenService.generateSHA256(password));
					
					needPasswordUpdateFlag = true;
				}
				
				if(needPasswordUpdateFlag) {
					userDetail.getMbr().setTmprPwYn(MemberEnum.NO.toString());
					memberBaseCommandService.updatePassword(userDetail.getMbr());
				}
				
		        // ERP 회원 정보를 받아서 온라인 회원으로 업데이트한다.
				if(session.getAttribute(SessionEnum.MEMBER_INFORMATION_SDO.toString()) != null) {
					memberInformationSDO = (MemberInformationSDO) session.getAttribute(SessionEnum.MEMBER_INFORMATION_SDO.toString());
					memberBaseCommandService.updateOnlineMemberByErp(request, pk, memberInformationSDO, userDetail.getMbr());
				}
			}
			
						
			// 휴면 인 경우 휴면이였던 변수를 가지고 있고
			// 휴면을 해제한다.
			String beforeMbrStatCd = userDetail.getMbr().getMbrStatCd();
			if(MemberEnum.MemberStatCd.DRMNCY.toString().equals(beforeMbrStatCd)
					|| (memberInformationSDO != null && memberInformationSDO.getStatus() != null && "S".equals(memberInformationSDO.getStatus()))
					) {
//				Mbr mbrForReleaseDrmncy = new Mbr();
//				mbrForReleaseDrmncy.setMbrNo(userDetail.getMbr().getMbrNo());
//				mbrForReleaseDrmncy.setMbrStatCd(MemberEnum.MemberStatCd.ACT.toString());
//				mbrForReleaseDrmncy.setUdterId(userDetail.getMbr().getMbrNo());
				
				userDetail.getMbr().setMbrStatCd(MemberEnum.MemberStatCd.ACT.toString());
				userDetail.getMbr().setUdterId(userDetail.getMbr().getMbrNo());
				
				memberBaseCommandService.updateMemberForReleaseDrmncy(userDetail.getMbr());
				
				session.setAttribute(SecurityParam.RELEASE_DRMNCY.toString(), "true");
			}
			
			// 회원등급을 로그인시 받은것으로 update한다
			// 기존 회원이 mlb, sa 에 등급이 없을수 있으므로 merge한다
			if(memberInformationSDO != null && memberInformationSDO.getMemberGradeList() != null && !memberInformationSDO.getMemberGradeList().isEmpty()) {
				insertOnlineGradeInfo(pk, mbr, memberInformationSDO.getMemberGradeList());
			}
			
			/** 로그인 히스토리 추가 시작 **/
			String ip = request.getRemoteAddr();
			Locale locale = LocaleService.getCurrentLocale(request);
			MbrLoginLog mbrLoginLog = new MbrLoginLog();
			mbrLoginLog.setMbrNo(userDetail.getMbr().getMbrNo());
			mbrLoginLog.setMbrLoginCd("LOGIN");
			mbrLoginLog.setLoginIp(ip);

			String loginNationCd = locale.getCountry();

			if (StringService.isNotEmpty(loginNationCd)) {
				mbrLoginLog.setLoginNationCd(loginNationCd.toLowerCase());
			} else {
				mbrLoginLog.setLoginNationCd("kr");
			}

			mbrLoginLog.setLangCd(pk.getLang());
			mbrLoginLog.setDvcCd(pk.getDevice());
			mbrLoginLog.setMallId(pk.getMall());
			
			log.info("mbrLoginLog {}", mbrLoginLog);
			memberBaseCommandService.addMbrLoginLog(pk, mbrLoginLog);
			/** 로그인 히스토리 추가 종료 **/

			try {
				/** ERP로 로그인 성공 전송 시작 **/
				String logDate = memberBaseSelectService.selectMbrLoginLog(mbrLoginLog);
				MemberLoginInfoSDO memberLoginInfoSDO = new MemberLoginInfoSDO();
				memberLoginInfoSDO.setCid(userDetail.getMbr().getErpCstmrNo());
				memberLoginInfoSDO.setId(userDetail.getMbr().getMbrId());
				memberLoginInfoSDO.setIp(ip);
				// 휴면 해제인 경우 로그인 로그에 "Y"로 세팅
				if(MemberEnum.MemberStatCd.DRMNCY.toString().equals(beforeMbrStatCd)) {
					memberLoginInfoSDO.setSleepReleaseYn("Y");	
				}
				else {
					memberLoginInfoSDO.setSleepReleaseYn("N");
				}
				memberLoginInfoSDO.setLogDate(logDate);
				
				String requestUrl = memberJoinFOComponent.getRequestUrlForAddMember(request, pk);
				
				memberLoginInfoSDO.setSite(requestUrl);
				
				memberInterfaceService.sendMemberLatestLogInInfo(pk, memberLoginInfoSDO);
				/** ERP로 로그인 성공 전송 종료 **/
			}
			catch(Exception e) {
				/** ERP로 로그인 성공 전송은 실패하여도 로그만 찍고 진행 **/
				StringWriter error = new StringWriter();
				e.printStackTrace(new PrintWriter(error));
				log.error("> ERP 로그인 성공 전송 실패 Failure message : {}", this.getClass().getName() + " : " + error.toString());
			}
			
			// 비밀번호는 세션에 담을수 없으므로
			userDetail.getMbr().setMbrPw("");
			userDetail.getMbr().setMbrErpPw("");
			
			//로그인 실패횟수 변경
			if(!needPasswordUpdateFlag){
				userDetail.getMbr().setTmprPwYn(MemberEnum.NO.toString());
				memberBaseCommandService.updatePassword(userDetail.getMbr());
			}

			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			grantedAuths.add(new SimpleGrantedAuthority(FRONT_ROLE_NAME));
			authentication = new UsernamePasswordAuthenticationToken(userDetail, null, grantedAuths);

			// ERP에는 회원정보가 있지만 온라인에는 회원정보가 없어서 온라인에 회원정보를 등록한 경우
			// 휴면 해제인 경우
			if((session.getAttribute(SessionEnum.ONLINE_INSERT_FLAG.toString()) != null
					&& JsonService.marshalling(true).equals(session.getAttribute(SessionEnum.ONLINE_INSERT_FLAG.toString())))
					|| MemberEnum.MemberStatCd.DRMNCY.toString().equals(beforeMbrStatCd)
					) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
			
			//오늘본상품 복사
			//쿠키에 있던 상품을 등록한다.
			GoodsPriceSearchDTO priceDTO = goodsPriceFOComponent.getMemberTypeForPriceForPC();
			Cookie cookies[] = ContextService.getCurrentRequest().getCookies();
			List<GoodsInterestsGodFoResult> result = new ArrayList<GoodsInterestsGodFoResult>();
			List<MbrTodayGod> skyScrapers = new ArrayList<MbrTodayGod>();
			if(cookies != null){
				for (int i = 0; i < cookies.length; i++) {
					Functions.cookies(cookies, skyScrapers, i);
				}
			}
			InterestSearchFoDTO interestSearchFoDTO =  new InterestSearchFoDTO();
			interestSearchFoDTO.setMallId(pk.getMall());
			interestSearchFoDTO.setLang(pk.getLang());
			interestSearchFoDTO.setPrcSectCd(priceDTO.getPrcSectCd());			
			interestSearchFoDTO.setEndIndex(10);			
			interestSearchFoDTO.setSesionId(request.getSession().getId());
			if(skyScrapers != null && skyScrapers.size() > 0){
				interestSearchFoDTO.setSkyScrapers(skyScrapers);
				result = dxmDisplaySelectComponent.getTodayCookiesList(interestSearchFoDTO);
			}			
			
			if(result != null && result.size() > 0){
				for(GoodsInterestsGodFoResult r : result){
					MbrTodayGod mbrTodayGod = new MbrTodayGod();
					mbrTodayGod.setGodNo(r.getGodNo());
					mbrTodayGod.setMallId(pk.getMall());
					mbrTodayGod.setLangCd(pk.getLang());
					mbrTodayGod.setMbrSectCd(MbrSect.MBR.toString());
					mbrTodayGod.setMbrNo(mbr.getMbrNo());
					mbrTodayGod.setRegtrId(mbr.getMbrNo());
					mbrTodayGod.setUdterId(mbr.getMbrNo());
					mbrTodayGod.setTodayGodSectCd(TodayGodSectCd.GOD.toString());
				
					// 최근 본 상품 중복조회
					goodsContentService.isDuplicateTodayGod(mbrTodayGod);
				}
			}

			// 휴면 해제인 경우 쿠폰 발급
			if(MemberEnum.MemberStatCd.DRMNCY.toString().equals(beforeMbrStatCd)) {
				// DXM 만 주므로 DXM으로 세팅하여 처리.
				SystemPK systemPK = new SystemPK();
				systemPK.setMall("DXM");
				systemPK.setLang(pk.getLang());
				systemPK.setDevice(pk.getDevice());
				
				// 혜택API 휴면 해제 회원 혜택 - 휴면 해제 회원 혜택
				MemberBenefitApiResult apiResult = memberBenefitFOComponent.callMemberBenefit(systemPK, MemberBenefitEnum.BnefSectCd.RELIS_DRMC_MBR_BNEF, null);
				
				log.info("MEMBER_BENEFIT_API : ResultCd({}),ResultMsg({})", apiResult.getResultCd(), apiResult.getResultMsg());
	
				if(apiResult != null && apiResult.getMemberBenefitResultList() != null) {
				    List<MemberBenefitResult> resultList = apiResult.getMemberBenefitResultList();
	
				    for(int i= 0; i < resultList.size(); i++) {
				        MemberBenefitResult memberBenefitResult = resultList.get(i);
				        log.info("MEMBER_BENEFIT_API_DETAIL : ResultCd({}),ResultMsg({})", memberBenefitResult.getResultCd(), memberBenefitResult.getResultMsg());
				    }
				}
			}
			
			// 회원등급에 따른 쿠폰 발행
			InfMbrGrdInfoRecptn infMbrGrdInfoRecptn = new InfMbrGrdInfoRecptn();
			String brndId = "";
			if("DXM".equals(pk.getMall())) {
				brndId = "X";
			}else if("MBM".equals(pk.getMall())) {
				brndId = "M";
			}else if("SAM".equals(pk.getMall())) {
				brndId = "A";
			}									
			infMbrGrdInfoRecptn.setBrndId(brndId);
			infMbrGrdInfoRecptn.setErpCstmrNo(userDetail.getMbr().getErpCstmrNo());
			memberBenefitFOComponent.memberGradeIssuCpn(infMbrGrdInfoRecptn);   

			// 세션 정보를 체크하여 중복로그인을 제거한다.
			// 마지막 세션을 threadlocal에 저장
			if (securitySessionInitHandler != null && sessionRegistry != null) {
				securitySessionInitHandler.initSecuritySession(request, response, authentication, sessionRegistry);
			}

		} catch (Exception ex) {
			throw new AuthenticationServiceException("Credentials could not be verified", ex);
		}
		finally {
			session.removeAttribute(SessionEnum.MEMBER_INFORMATION_SDO.toString());
			session.removeAttribute(SessionEnum.ONLINE_INSERT_FLAG.toString());
		}
		
		log.info("============ login end============");

		String ajax = request.getHeader("ajax-login");
		RedirectOrder redirectOrder = decideRedirectStrategy(request, response);
		
		log.info("============ redirectOrder : " + redirectOrder);

		switch (redirectOrder) {
		case TARGET_PARAM:
			useTargetUrl(request, response);
			break;
		case SESSION_URL:
			useSessionUrl(request, response);
			break;
		case SESSION_REFERER_URL:
			useSessionRefererUrl(request, response);
			break;
		case HEADER_REFERER:
			useRefererUrl(request, response);
			break;
		default:
			useDefaultUrl(request, response);
		}
	}

	void handleNaverAuthenticationSuccess(HttpServletRequest request, Mbr mbr) throws Exception {
		NaverValidationResult validationResult = retriveNaverValidationResult(request);
		if (validationResult == null) {
			log.trace("Naver 연결 대상이 아닙니다");
			return;
		}

		if (isInvalidatedEmail(mbr, validationResult)) {
			log.warn("Email을 검증할수 없습니다. mbrEmail:{} validationEmail: {}", mbr.getMbrEmail(), validationResult.getEmail());
			return;
		}
		saveNaverLinkedForMbr(mbr, validationResult);
	}

	boolean isInvalidatedEmail(Mbr mbr, NaverValidationResult validationResult) {
		return isBlank(mbr.getMbrEmail()) || isBlank(validationResult.getEmail())
				|| mbr.getMbrEmail().equals(validationResult.getEmail()) == false;
	}

	NaverValidationResult retriveNaverValidationResult(HttpServletRequest request) {
		NaverValidationResult validationResult = (NaverValidationResult) request.getSession(false)
				.getAttribute(NaverAuthenticationFilter.NAVER_VALIDATION_RESULT);
		return validationResult;
	}

	void saveNaverLinkedForMbr(Mbr mbr, NaverValidationResult validationResult) {
		MbrIdCntcExtend mbrIdCntcExtend = new MbrIdCntcExtend();
		mbrIdCntcExtend.setMbrNo(mbr.getMbrNo());
		mbrIdCntcExtend.setMbrId(mbr.getMbrId());
		mbrIdCntcExtend.setMallId(mbr.getMallId());
		mbrIdCntcExtend.setIdCntcTpCd("NAVER");

		MbrIdCntc mbrIdCntc = naverUserService.selectNaverUserInfo(mbrIdCntcExtend);

		log.info(" saveNaverLinkedForMbr mbrIdCntc {}: ", mbrIdCntc);
		//값이 있으면
		if(mbrIdCntc != null){
			//연동 해제일때만 update
			if(mbrIdCntc.getDeleteYn() != null && mbrIdCntc.getDeleteYn().equals("Y")){
				naverUserService.updateMemberNaverUserId(mbr.getMbrNo(), validationResult.getUserId(), validationResult.getEmail());
			}
		}else{
			naverUserService.updateMemberNaverUserId(mbr.getMbrNo(), validationResult.getUserId(), validationResult.getEmail());
		}
	}
	
	private void insertOnlineGradeInfo(SystemPK systemPK, Mbr mbr, List<MemberGradeInformationSDO> memberGradeInformationSDOList) {
		try{
			
			if(memberGradeInformationSDOList != null && memberGradeInformationSDOList.size() > 0){
			
				for(MemberGradeInformationSDO memberGradeInformationSDO : memberGradeInformationSDOList){
			
					MbrGrd mbrGrd = new MbrGrd();
					//mbrGrd.setMallId(systemPK.getMall());
					if("X".equals(memberGradeInformationSDO.getBrandDiv())){
						mbrGrd.setMallId("DXM");				
					}else if("M".equals(memberGradeInformationSDO.getBrandDiv())){
						mbrGrd.setMallId("MBM");
					}else if("A".equals(memberGradeInformationSDO.getBrandDiv())){
						mbrGrd.setMallId("SAM");
					}else{
						mbrGrd.setMallId(systemPK.getMall());
					}
					mbrGrd.setMbrNo(mbr.getMbrNo());	
					mbrGrd.setOnlneGrdCd(mbrGrd.getMallId() + "_" + memberGradeInformationSDO.getMemberGrade());					
					
					SimpleDateFormat insertFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
					// 유지기간 시작일시(당일)
					Calendar grdAplBegCal = new GregorianCalendar();
		//			grdAplBegCal.add(Calendar.MONTH, -1);
		//			grdAplBegCal.set(Calendar.DATE, grdAplBegCal.getMinimum(Calendar.DAY_OF_MONTH));//해당월의 가장첫날 계산
		
					SimpleDateFormat grdAplBegSdf = new SimpleDateFormat("yyyy-MM-dd");
					String grdAplBegDt = grdAplBegSdf.format(grdAplBegCal.getTime()) + " 00:00:00";
					Date grdBegDt = insertFormat.parse(grdAplBegDt);
					mbrGrd.setGrdAplBegDt(grdBegDt);		
					
					String grdAplEndDt = "9999-12-31 23:59:59";
					Date grdEndDt = insertFormat.parse(grdAplEndDt);
					mbrGrd.setGrdAplEndDt(grdEndDt);
					
					// 등급산정일
					mbrGrd.setGrdSlctnDt(grdBegDt);
					
					// 등록자,수정자는 fo 에서는 회원no로
					mbrGrd.setRegtrId(mbr.getMbrNo());
					mbrGrd.setUdterId(mbr.getMbrNo());
		
					// 회원등급 등록
					memberBaseCommandService.insertOnlineGradeInfoLogin(mbrGrd);
				}
			}

		}catch (Exception e){
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			log.error("addMember.insertOnlineGradeInfoForAddMember : {}", this.getClass().getName() + " : " + error.toString());
		}
	}


	/**
	 * 인증 성공후 어떤 URL로 redirect 할지를 결정한다
	 * <p/>
	 * 순서되로 체크를 하며 먼저 해당이 되는 것을 처리 하도록 한다.
	 * <p/>
	 * <ol>
	 * <li> 1순위 헤더 값 ajax-login이 true일 경우
	 * <li> 2순위 loginTarget 파라미터 값을 읽은 URL이 존재할 경우
	 * <li> 3순위 Spring Security가 세션에 저장한 URL이 존재할 경우
	 * <li> 4순위 Request의 REFERER의 URL이 존재 할 경우
	 * <li> 5순위 모두 해당 사항이 없으면 Default URL를 사용 한다.
	 *</ol>
	 * @param request
	 * @param response
	 * @return RedirectOrder
	 */
	private RedirectOrder decideRedirectStrategy(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		RedirectOrder redirectOrder = RedirectOrder.DEFAULT;
		HttpSession session = request.getSession();
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		// request 부터 호출한 URL 정보가 있는지 파악을 한다.
		String targetURL = request.getParameter(SecurityParam.TARGET_PARAM_NAME.toString());
		// targetURL이 존재 하면
		if (!isEmpty(targetURL)) {
			return RedirectOrder.TARGET_PARAM;
		} else {

			// 세션에 URL 정보가 있을 경우
			if (savedRequest != null) {
				return RedirectOrder.SESSION_URL;
			} else {
				if (session != null && session.getAttribute("REFERER_URL") != null) {
					return RedirectOrder.SESSION_REFERER_URL;

				} else {
					// referer 헤더를 사용할 경우
					if (referer) {
						String refererUrl = request.getHeader("REFERER");
						if (isEmpty(refererUrl)) {
							return RedirectOrder.HEADER_REFERER;
						}
					}

				}
			}
		}
		return redirectOrder;

	}

	/* 실패시 임시로 생성한 세션 객체를 삭제 한다. */
	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	private void useTargetUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest != null) {
			requestCache.removeRequest(request, response);
		}
		String targetUrl = XSSUtil.stripXSS(request.getParameter(SecurityParam.TARGET_PARAM_NAME.toString()));

		// 로그인 모드가 팝업일 경우 팝업 로그인 성공 페이지로 이동 한다.
		String pageMode = request.getParameter(SecurityParam.LOGIN_POPUP_ENALBED.toString());
		
		SystemPK pk = idGenService.getAutoGeneratorSystemPK(request);

		// pageMode값 true일 경우		
		if(pk.getMall().equals("DXM")){
			if (StringService.isNotEmpty(pageMode) && BooleanUtils.toBoolean(pageMode)) {
				targetUrl = popupSuccessURL + "?" + SecurityParam.TARGET_PARAM_NAME.toString() + "=" + targetUrl;
			}
		}

		renderView(request, response, targetUrl);

	}

	private void useSessionUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		String targetUrl = savedRequest.getRedirectUrl();
		renderView(request, response, targetUrl);
	}

	private void useRefererUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String targetUrl = request.getHeader("REFERER");
		renderView(request, response, targetUrl);
	}

	private void useSessionRefererUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String targetUrl = (String) session.getAttribute("REFERER_URL");
		renderView(request, response, targetUrl);
	}

	private void useDefaultUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		renderView(request, response, defaultUrl);
	}

	private void renderView(HttpServletRequest request, HttpServletResponse response, String targetURL) throws IOException {
		log.info("========= targetURL : " + targetURL);
		boolean ajaxAvailable = BooleanUtils.toBoolean(request.getHeader(SecurityParam.HEADER_AJAX_LOGIN.toString()));
		// 요청이 ajax 면
		if (ajaxAvailable) {
			String releaseDrmncy = "";
			if( request.getSession().getAttribute(SecurityParam.RELEASE_DRMNCY.toString()) != null ) {
				releaseDrmncy = (String) request.getSession().getAttribute(SecurityParam.RELEASE_DRMNCY.toString());
				request.getSession().removeAttribute(SecurityParam.RELEASE_DRMNCY.toString());
			}

			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");

			SecurityJsonResult resultData = new SecurityJsonResult();
			resultData.setLoginTarget(targetURL);
			resultData.setReleaseDrmncy(releaseDrmncy);
			String jsonData = defaultUrl;
			try {
				jsonData = JsonService.marshalling(resultData);
			} catch (Exception e) {
				throw new IOException(e);
			}
			InputStream is = new ByteArrayInputStream(jsonData.getBytes());
			IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
		}
		else {
			redirectStrategy.sendRedirect(request, response, targetURL);
		}
	}
}
