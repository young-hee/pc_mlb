package com.plgrim.ncp.cmp.member.fo.join;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrGrd;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntcExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSizeClfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrStplatAgr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAuthorGrp;
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplatUse;
import com.plgrim.ncp.base.enums.MemberBenefitEnum;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.MemberEnum.MemberAtrbCd;
import com.plgrim.ncp.base.enums.MemberEnum.MemberPwdSendMethod;
import com.plgrim.ncp.base.enums.MemberEnum.MemberSecsnSectCd;
import com.plgrim.ncp.base.enums.MemberEnum.MemberStatCd;
import com.plgrim.ncp.base.enums.MemberEnum.MemberTpCd;
import com.plgrim.ncp.base.enums.MemberEnum.UnityMbrCrtfcSectCd;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.base.enums.SysInfoEnum.MallIdEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.MbrDlvspPsnlInfoUdtSectCd;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.MbrPsnlInfoUdtSectCd;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.MemberModLcSectCd;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefJobCd;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefJobDetail;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefSectCd;
import com.plgrim.ncp.base.enums.promotion.OnOffCouponEnum;
import com.plgrim.ncp.biz.callcenter.data.CsoCnsltMemoExtendDTO;
import com.plgrim.ncp.biz.callcenter.service.MemoService;
import com.plgrim.ncp.biz.member.data.Ipin;
import com.plgrim.ncp.biz.member.data.MbrExtendDTO;
import com.plgrim.ncp.biz.member.data.MemberBoDTO;
import com.plgrim.ncp.biz.member.data.MemberChildDTO;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.MemberResultDTO;
import com.plgrim.ncp.biz.member.data.MemberSysGrpcoSearchDTO;
import com.plgrim.ncp.biz.member.data.MemberWebSelectResult;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;
import com.plgrim.ncp.biz.member.data.Pcc;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.exception.MemberFailSendPasswordException;
import com.plgrim.ncp.biz.member.exception.MemberSecessionImpossibleException;
import com.plgrim.ncp.biz.member.exception.MemberStatusSecessionException;
import com.plgrim.ncp.biz.member.result.MbrExtendResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitApiResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitResult;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.biz.member.result.MemberSysGrpcoResult;
import com.plgrim.ncp.biz.member.result.MypageCpnFoResult;
import com.plgrim.ncp.biz.member.result.MypageMtmFoResult;
import com.plgrim.ncp.biz.member.result.PurpleCoinMemberBoResult;
import com.plgrim.ncp.biz.member.service.MemberBaseCommandService;
import com.plgrim.ncp.biz.member.service.MemberBaseSelectService;
import com.plgrim.ncp.biz.member.service.MemberBenefitApiCommandService;
import com.plgrim.ncp.biz.member.service.MemberBenefitSelectService;
import com.plgrim.ncp.biz.member.service.MemberCertifySelectService;
import com.plgrim.ncp.biz.member.service.MemberInterfaceService;
import com.plgrim.ncp.biz.member.service.MemberOrderCommandService;
import com.plgrim.ncp.biz.member.service.MemberOrderSelectService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoCommandService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoSelectService;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.result.OrderBoResult;
import com.plgrim.ncp.biz.order.service.OrderOthersMemberService;
import com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent;
import com.plgrim.ncp.cmp.member.fo.MemberAuthFOComponent;
import com.plgrim.ncp.cmp.member.fo.MemberBenefitFOComponent;
import com.plgrim.ncp.cmp.member.fo.MemberJoinFOComponent;
import com.plgrim.ncp.commons.auth.facebook.FacebookUserService;
import com.plgrim.ncp.commons.auth.naver.NaverUserService;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.SecurityParam;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.plgrim.ncp.interfaces.email.data.EmailHtmlSDO;
import com.plgrim.ncp.interfaces.member.data.DropMemberInformationSDO;
import com.plgrim.ncp.interfaces.member.data.FamilyInformationSDO;
import com.plgrim.ncp.interfaces.member.data.MemberGradeInformationSDO;
import com.plgrim.ncp.interfaces.member.data.MemberInformationSDO;
import com.plgrim.ncp.interfaces.member.data.MemberMileageInfoSDO;
import com.plgrim.ncp.interfaces.member.data.SendMemberInformationSDO;
import com.plgrim.ncp.interfaces.mpush.data.MPushAlimTalkSDO;
import com.plgrim.ncp.interfaces.promotion.adapter.PromotionAdapter;
import com.plgrim.ncp.interfaces.promotion.data.OnOffCouponIssueResultDataSDO;
import com.plgrim.ncp.interfaces.promotion.data.OnOffCouponIssueSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

@Transactional(value = "transactionManager")
@Component
@Slf4j
public class MemberJoinFOComponentImpl extends AbstractComponent implements MemberJoinFOComponent {
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	@Autowired
	private FacebookUserService facebookUserService;

	@Autowired
	private NaverUserService<?> naverUserService;

	@Autowired
	private MemberBaseSelectService memberBaseSelectService;

	@Autowired
	private MemberBaseCommandService memberBaseCommandService;

	@Autowired
	private MemberPersonalInfoCommandService memberPersonalInfoCommandService;

	@Autowired
	private MemberPersonalInfoSelectService memberPersonalInfoSelectService;

	@Autowired
	private MemberCertifySelectService memberCertifySelectService;

	@Autowired
	private MemberOrderCommandService memberOrderCommandService;

	@Autowired
	private MemberOrderSelectService memberOrderSelectService;

	@Autowired
	private MemoService memoService;

	@Autowired
	private MemberBenefitSelectService memberBenefitSelectService;

	@Autowired
	private OrderOthersMemberService orderOthersMemberService;
	
	@Autowired
	private MemberInterfaceService memberInterfaceService;

	@Autowired
	private MemberAuthFOComponent memberAuthFOComponent;
	
	@Autowired
	private MemberBenefitFOComponent memberBenefitFOComponent;
	
    @Autowired
    private InterfaceApiCommon interfaceApiCommon;
    
	@Autowired
	private PromotionAdapter promotionAdapter;
	
	@Autowired
	private MemberBenefitApiCommandService memberBenefitApiCommandService;
	
	@Autowired
	private DisplaySelectComponent displaySelectComponent;
	
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

	/**
	 * 회원정보 변경
	 * MypageMemberController.updateMemberAction 에 있던 처리로직을 트랜잭션 처리를 위해 옮겨옴
	 *
	 * @param request
	 * @param dto
	 * @param pk
	 * @param locale
	 * @param mbr
	 * @param loginId
	 * @
	 */
	@Override
	public void updateMemberTransaction(HttpServletRequest request, MemberFoDTO dto, SystemPK pk, Locale locale, Mbr mbr) throws Exception {
		SecurityUserDetail userDetail;
		if(dto.getBaseDlvYn() != null){
			MypageFoDTO mypageFoDTO = new MypageFoDTO();
			MbrDlvsp mbrDlvsp = new MbrDlvsp();
			mbrDlvsp.setMbrNo(dto.getMbr().getMbrNo());
			mbrDlvsp.setAddrseNm(mbr.getMbrNm());
			mbrDlvsp.setPostNo(dto.getMbr().getMbrPostNo());
			mbrDlvsp.setDlvAddrSectCd(dto.getMbr().getMbrAddrSectCd());
			mbrDlvsp.setBaseAddr(dto.getMbr().getMbrBaseAddr());
			mbrDlvsp.setDetailAddr(dto.getMbr().getMbrDetailAddr());
			mbrDlvsp.setBaseDlvspYn(MemberEnum.YES.toString());
			mbrDlvsp.setMobilNationNo(dto.getMbr().getMobilNationNo());
			mbrDlvsp.setMobilAreaNo(dto.getMbr().getMobilAreaNo());
			mbrDlvsp.setMobilTlofNo(dto.getMbr().getMobilTlofNo());
			mbrDlvsp.setMobilTlofWthnNo(dto.getMbr().getMobilTlofWthnNo());

			mbrDlvsp.setTelNationNo(dto.getMbr().getTelNationNo());
			mbrDlvsp.setTelAreaNo(dto.getMbr().getTelAreaNo());
			mbrDlvsp.setTelTlofNo(dto.getMbr().getTelTlofNo());
			mbrDlvsp.setTelTlofWthnNo(dto.getMbr().getTelTlofWthnNo());

			mbrDlvsp.setNationCd(dto.getMbr().getMbrAddrNationCd());
			mbrDlvsp.setCtyNm(dto.getMbr().getMbrCtyNm());
			mbrDlvsp.setLcltyNm(dto.getMbr().getMbrLcltyNm());
			mbrDlvsp.setDlvEmail(dto.getMbr().getMbrEmail());

			mypageFoDTO.setMbrDlvsp(mbrDlvsp);
			log.info("mbrDlvsp : {}",mbrDlvsp);

			this.registerDeliveryLocationBy(pk, mypageFoDTO, mbr.getMbrId(), locale);
		}

		// 마케팅 홍보 약관 동의는 사용하지 않음. 2018.06.15
//		MbrStplatAgr mbrStplatAgr = new MbrStplatAgr();
//		mbrStplatAgr.setMbrNo(mbr.getMbrNo());
//		mbrStplatAgr.setStplatCd(dto.getMarktPsnlInfoColctUsefAgr());
//		mbrStplatAgr.setStplatIemAgrYn(dto.getMarktPsnlInfoColctUsefAgrYn());
//		mbrStplatAgr.setUdterId(dto.getMbrNo());
//		this.insertMbrStplatAgr(mbrStplatAgr);

		// 인증정보
		MbrCrtfc mbrCrtfc = this.selectMemberCertification(dto.getMbr().getMbrNo());
		dto.setMbrCrtfc(mbrCrtfc);
		
		MemberFoResult memberFoResult = null;
		try {
			memberFoResult = this.updateMember(pk, dto);
		} catch (Exception e) {
			log.error("> updateMember Exception : " + e.getMessage(), e);
			throw new RuntimeException(e);
		}

		if (memberFoResult != null && memberFoResult.isPointEarned() == true) {
			request.getSession().setAttribute("mySizePointEarned", "Y");
			log.info("Mysize Point Earned and set Session mySizePointEarned created");
		}

		dto.setMallId(pk.getMall());

		userDetail = memberBaseSelectService.selectSecuredMember(dto);

		SecurityUserDetail beforeUserDetail = (SecurityUserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(userDetail.getMbr() != null){
			beforeUserDetail.setMbr(userDetail.getMbr());
		}

		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
		Authentication authenticatedUser = new UsernamePasswordAuthenticationToken(beforeUserDetail, null, grantedAuths);
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}

	public void insertMbrStplatAgr(MbrStplatAgr agr)  {
		memberPersonalInfoCommandService.insertMbrStplatAgr(agr);
	}

	@Override
	public boolean updatePassword(SystemPK systemPK, MemberFoDTO dto, boolean onlyErpUpdateFlag) throws Exception {
		boolean isFlag = false;

		Mbr mbr = dto.getMbr();
		String newPassword = IdGenService.generateSHA256(dto.getNewPassword());
		String newErpPassword = IdGenService.generateMD5(dto.getNewPassword());
		String mbrId = dto.getMbr().getMbrId();
		mbr.setMbrPw(newPassword);
		mbr.setMbrErpPw(newErpPassword);
		mbr.setUdterId(mbrId);
		mbr.setTmprPwYn("N");
		dto.setMbr(mbr);
		
		int updateCount = 0;
		if(onlyErpUpdateFlag == false) {
			// step 1. 개인정보변경이력 등록
			MbrPsnlInfoModHist mpim = memberPersonalInfoCommandService.setMbrPsnlInfoModHist(dto.getMbr().getMbrNo(), "", dto.getMbr().getMbrNo(), true);
			String[] codeArr = {
					MbrPsnlInfoUdtSectCd.MBR_PW.toString()					// 비밀번호
			};
			mpim.setModLcSectCd(MemberModLcSectCd.ONLNE_MALL.toString()); // 수정이력 수정몰
			memberPersonalInfoCommandService.insertPersonalInfoForMbr(dto.getMbr(), mpim, codeArr, false);
	
			// step 2. 온라인 비밀번호 변경
			updateCount = memberBaseCommandService.updatePassword(mbr);
		}
		
		// step 3. ERP 비밀번호 변경
		if((onlyErpUpdateFlag == true || updateCount > 0)) {
			// ERP에 회원 수정 정보 전송.(R:온라인회원정보 송신, C:회원정보생성, U:회원정보수정)
			// 인터페이스 Exception 발생시 롤백하도록 처리 필요. 
			try {
				memberInterfaceService.sendMemberInformation(systemPK, dto, "P");
				isFlag = true;
			}
			catch(Exception e) {
				isFlag = false;
				throw new Exception(e);
			}
		}

		return isFlag;
	}

	@Override
	public void deleteMember(SystemPK systemPK, Mbr mbr)  {
		//회원 탈퇴

		// step 1. 탈퇴가능 조건 확인
		MbrExtendResult result = memberBaseSelectService.selectMemberForSecession(mbr);
		if (result.getOrdCnt() > 0 || result.getClmCnt() > 0) {
			String[] param = {
					String.valueOf(result.getOrdCnt()),
					String.valueOf(result.getClmCnt())
			};
			throw new MemberSecessionImpossibleException(param);
		}

		// step 2. 개인정보이용이력 등록 -- TODO 개인정보 이용이력의 이용 업무 상세코드에서 탈퇴 없음.
		String[][] usefCdInfo = { // 구분, 업무, 업무상세
				{ UsefSectCd.PSNL_INFO_STTUS.toString(), UsefJobCd.DELETE.toString(),
						UsefJobDetail.STPLAT_APL.toString() } };
		String[] target = { mbr.getMbrNo() };
		memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK, usefCdInfo // 개인정보
				// 코드
				// 정보(구분,
				// 업무,
				// 업무상세)
				, target // 이용대상 : 회원
				, null // 유입 일련번호
				, null			// 메뉴 일련번호
				, mbr.getMbrNo() // 로그인 ID
		);

		// step 3. 탈퇴정보 수정/삭제
		mbr.setSecsnSectCd(MemberSecsnSectCd.MBR_SECSN.toString());
		mbr.setUdterId(mbr.getMbrId());
		
		//운영이 아닐때 erp쪽에 ci,di를 수정해서 재가입이 가능하도록 한다.
//		log.debug("==========aaaaaaaa 서버 : " + systemContext.getStage());
//		if(systemContext.getStage() != Stage.PRODUCTION){
//			MemberFoDTO dd = new MemberFoDTO();
//			dd.setMbr(mbr);
//			
//			MbrCrtfc fc = this.selectMemberCertification(dd.getMbr().getMbrNo());
//			fc.setRlnmCrtfcCi("fimfOXyQrswG1Mbbsouu2VRjTtuZ4cJPi5KtpTccWY+OhVPGX92mwqr5vgIocsY0EZIdozyINxXDnvUC6d1AAA==");
//			fc.setRlnmCrtfcDi("MC0GCCqGSIb3DQIJAyEAyn93nPVQwhDLq7UAh8R3OPaELzqzGIevr0GIWSyn/aa=");			
//			dd.setMbrCrtfc(fc);
//			
//			try{
//				memberInterfaceService.sendMemberInformation(systemPK, dd, "U");
//			}catch(Exception e){
//				log.debug("=======탈퇴시 erp 회원정보 ci,di 바꾸기 실패");
//			}
//			
//		}

		//회원탈퇴 처리
		memberBaseCommandService.updateMemberSecession(mbr);

		try{
			/** ERP로 회원 탈퇴 전송 시작 **/
			MbrExtendResult resultForDropDate = memberBaseSelectService.selectMemberForSecession(mbr);
			DropMemberInformationSDO dropMemberInformationSDO = new DropMemberInformationSDO();
            dropMemberInformationSDO.setCid(mbr.getErpCstmrNo());
            dropMemberInformationSDO.setDropReason(mbr.getSecsnResnCd());
            dropMemberInformationSDO.setRemarkIt(mbr.getSecsnResnDetailCont());
            dropMemberInformationSDO.setDropDate(resultForDropDate.getSecsnDateStr());
			memberInterfaceService.sendDropMemberInformation(systemPK, dropMemberInformationSDO);
			/** ERP로 회원 탈퇴 전송 종료 **/
		}
		catch(Exception e) {
			log.info(CommonResponseCode.MB_40006_통합회원_탈퇴_실패.toMessage()+" : "+e);
			throw new RuntimeException(e);
		}

	}

	@Override
	public void sendDeleteMailSms(SystemPK systemPK, Mbr mbr) {
		try {
			/**
			 * 회원 탈퇴 메일 발송
			 */
			EmailHtmlSDO emailSDO = new EmailHtmlSDO();
	
	        emailSDO.setCallerId(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());
	        
	        String emailHtmlUrl = "";
	        String emailHtmlParam = "";
	        
	        if(systemPK.getMall().equals("MBM")){
	        	emailSDO.setMallEmail(getConfigService().getProperty("ncp_base.mlb.mall.email.address"));
	        	emailSDO.setSubject(getConfigService().getProperty("ncp_base.mlb.mall.email.subject") + " " + mbr.getMbrNm() +" 고객님의 회원탈퇴 처리가 완료되었습니다.");
	        	emailHtmlUrl = getConfigService().getProperty("ncp.url.pc_MLB.root.secure")+"/email/member/join";
	        }else if(systemPK.getMall().equals("SAM")){
	        	emailSDO.setMallEmail(getConfigService().getProperty("ncp_base.sa.mall.email.address"));
	        	emailSDO.setSubject(getConfigService().getProperty("ncp_base.sa.mall.email.subject") + " " + mbr.getMbrNm() +" 고객님의 회원탈퇴 처리가 완료되었습니다.");
	        	emailHtmlUrl = getConfigService().getProperty("ncp.url.pc_SA.root.secure")+"/email/member/join";
	        }else{
	        	emailSDO.setMallEmail("DISCOVERY <discovery@fnf.co.kr>");
	        	emailSDO.setSubject("[Discovery Expedition] "+ mbr.getMbrNm() +" 고객님의 회원탈퇴 처리가 완료되었습니다.");
	        	emailHtmlUrl = getConfigService().getProperty("ncp.url.pc_DX.root.secure")+"/email/member/join";
	        }	
	        
	        
	        //emailSDO.setMallEmail("DISCOVERY <discovery@fnf.co.kr>");
	        emailSDO.setMbrEmail(mbr.getMbrEmail());
	        //emailSDO.setSubject("[Discovery Expedition] "+ mbr.getMbrNm() +" 고객님의 회원탈퇴 처리가 완료되었습니다.");        
	        //emailHtmlUrl = getConfigService().getProperty("ncp.url.pc_DX.root.secure")+"/email/member/secession";
	        
	        emailHtmlParam = "mbrNo="+mbr.getMbrNo();
	        emailHtmlParam += "&mbrId="+mbr.getMbrId();
	        emailHtmlParam += "&mbrNm="+java.net.URLEncoder.encode(mbr.getMbrNm(), "UTF-8");
	        
	        if(systemPK.getMall().equals("DXM")){
	        	memberInterfaceService.sendHtmlEmail(systemPK, emailSDO, emailHtmlUrl, emailHtmlParam);
	        }
		}
		catch(Exception e) {
			log.info("> SEND Mail Exception : {}", e.getMessage());
		}
		
		try {
			/**
			 * 회원 탈퇴 NTAK, SMS 발송
			 */
			if(systemPK.getMall().equals("DXM")){
				String mobileNumber = mbr.getMobilAreaNo() + mbr.getMobilTlofNo() + mbr.getMobilTlofWthnNo();
				
				long time = System.currentTimeMillis();
				Date curDt = new Date(time);
	
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				String secsnDt = sdf.format(curDt);
				
				ArrayList<String> params = new ArrayList<>();
		        params.add(0, mbr.getMbrNm());
		        params.add(1, secsnDt);
		        
				MPushAlimTalkSDO mPushAlimTalkSDO = new MPushAlimTalkSDO();
				
				mPushAlimTalkSDO.setMbrNo(mbr.getMbrNo());
				mPushAlimTalkSDO.setReceiveMobileNo(mobileNumber);
				mPushAlimTalkSDO.setMallId(systemPK.getMall());
				mPushAlimTalkSDO.setCallerId(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());
				mPushAlimTalkSDO.setMsgKey("DXM_MBR_JOIN_03");
				mPushAlimTalkSDO.setParams(params);
				mPushAlimTalkSDO.setSendDirectFlag(true);
		        
		        memberInterfaceService.sendAlimTalk(systemPK, mPushAlimTalkSDO);
			}
		}
		catch(Exception e) {
			log.info("> SEND NTAK/SMS Exception : {}", e.getMessage());
		}
	}
	
	@Override
	public MemberFoResult updateMember(SystemPK systemPK, MemberFoDTO dto) throws Exception {
//		Mbr mbr = dto.getMbr();

		MemberFoResult memberFoResult = new MemberFoResult();

		// step 1. 개인정보이용수정 등록
		MbrPsnlInfoModHist mpim = memberPersonalInfoCommandService.setMbrPsnlInfoModHist(dto.getMbr().getMbrNo(), "", dto.getMbr().getMbrNo(), true);

		// 수정이력
		String[] codeArr = {
				MbrPsnlInfoUdtSectCd.MBR_NM.toString()                    // 회원명
				, MbrPsnlInfoUdtSectCd.PHON_NO.toString()                    // 유선전화번호
				, MbrPsnlInfoUdtSectCd.MOBIL_NO.toString()                    // 휴대전화번호
				, MbrPsnlInfoUdtSectCd.MBR_POST_NO.toString()                    // 회원 우편번호
				, MbrPsnlInfoUdtSectCd.MBR_ADDR.toString()                    // 주소
				, MbrPsnlInfoUdtSectCd.MBR_EMAIL.toString()                // 이메일
				, MbrPsnlInfoUdtSectCd.SMS_RECPTN_AGR_YN.toString()        // SMS수신여부
				, MbrPsnlInfoUdtSectCd.EMAIL_RECPTN_AGR_YN.toString()        // E-mail수신동의여부
				, MbrPsnlInfoUdtSectCd.DM_RECPTN_AGR_YN.toString()            // DM수신여부
				, MbrPsnlInfoUdtSectCd.TM_RECPTN_AGR_YN.toString()            // TM수신여부
				, MbrPsnlInfoUdtSectCd.INFOR_NTCN_RECPTN_TP_CD.toString()    // 정보성 알림 수신 유형 코드
		};
		mpim.setModLcSectCd(MemberModLcSectCd.ONLNE_MALL.toString()); //DXM 코드
		memberPersonalInfoCommandService.insertPersonalInfoForMbr(dto.getMbr(), mpim, codeArr, false);

		/**
		 * 기존 회원 정보를 조회하여 E-mail수신동의여부와 SMS수신여부 비교.
		 * 1. 기존 E-mail수신동의여부가 'N'이고 변경 E-mail수신동의여부 'Y'이면 E-mail수신동의시간은 현재시간
		 * 2. 기존 SMS수신동의여부가 'N'이고 변경 SMS수신동의여부 'Y'이면 SMS수신동의시간은 현재시간
		 */
		SecurityUserDetail userDetail = memberBaseSelectService.selectSecuredMember(dto);
		Mbr beforeMbr = userDetail.getMbr();
		if(beforeMbr != null) {
			long time = System.currentTimeMillis();
			Date curDt = new Date(time);
			
			if(beforeMbr.getEmailRecptnAgrYn() != null && "N".equals(beforeMbr.getEmailRecptnAgrYn())
					&& "Y".equals(dto.getMbr().getEmailRecptnAgrYn())
					) {
				dto.getMbr().setLastEmailRecptnAgrDt(curDt);
			}
			if(beforeMbr.getSmsRecptnAgrYn() != null && "N".equals(beforeMbr.getSmsRecptnAgrYn())
					&& "Y".equals(dto.getMbr().getSmsRecptnAgrYn())
					) {
				dto.getMbr().setLastSmsRecptnAgrDt(curDt);
			}
		}
		
		//memberService.updateFoMember(dto);
		//국문,해외몰 회원 주소에 불필요한 값 들어가는 경우 수정
		//memberBaseCommandService.updateFoMemberAddr(dto);
		//회원 정보 수정은 선택 사항을 삭제 할수도 있어서 따로 사용
		memberBaseCommandService.updateMemberChoiceInfo(dto);

		// ERP에 회원 수정 정보 전송.(R:온라인회원정보 송신, C:회원정보생성, U:회원정보수정)
		// 인터페이스 Exception 발생시 롤백하도록 처리 필요. 
		try {
			userDetail = memberBaseSelectService.selectSecuredMember(dto);
			dto.setMbr(userDetail.getMbr());
			// 회원 수정 화면 로딩시 ERP 조회에 실패하여 자녀정보를 조회하지 못하였을 경우
			// 고객이 자녀정보를 입력하지 않고 저장을 하게 되면 ERP에도 자녀정보가 삭제되기 때문에
			// ERP 조회를 다시 하여 자녀정보를 조회하도록 처리.
			String familyExceptionFlag = dto.getFamilyExceptionFlag();
			
			// 회원 수정 화면 로딩시 ERP 조회에 실패한 경우
			if(familyExceptionFlag != null && "Y".equals(familyExceptionFlag)) {
				List<MemberChildDTO> childrenList = dto.getChildrenList();
				
				// 화면에서 받은 자녀정보가 없으면
				if(childrenList == null || childrenList.isEmpty()) {
					// ERP 통합회원정보 조회
		        	MemberInformationSDO memberInformationSDO = memberInterfaceService.getMemberInformation(systemPK, dto.getMbr());
		        	
		        	List<FamilyInformationSDO> familyList = memberInformationSDO.getFamilyList();
		        	// 저장 형식을 맞추기 위해 list를 새로 만듬.
		        	String familyBirth = "";
		        	if(familyList != null && !familyList.isEmpty()) {
		        		childrenList = new ArrayList<MemberChildDTO>();
		        		for(FamilyInformationSDO familyInformationSDO : familyList) {
		        			MemberChildDTO newMemberChildDTO = new MemberChildDTO();
		        			newMemberChildDTO.setChildrenName(familyInformationSDO.getFamilyName());
		        			familyBirth = familyInformationSDO.getFamilyBirth().replaceAll("-", "");
		        			newMemberChildDTO.setChildrenBirthYear(familyBirth.substring(0, 4));
		        			newMemberChildDTO.setChildrenBirthMonth(familyBirth.substring(4, 6));
		        			newMemberChildDTO.setChildrenBirthDay(familyBirth.substring(6, 8));

		        			childrenList.add(newMemberChildDTO);
			        	}	
		        	}
		        	
		        	dto.setChildrenList(childrenList);
				}
			}
			
			memberInterfaceService.sendMemberInformation(systemPK, dto, "U");
		}
		catch(Exception e) {
			log.error("> update ERP Exception : " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
		
		try {
			/**
			 * 회원 정보 수정 메일 발송
			 */
			EmailHtmlSDO emailSDO = new EmailHtmlSDO();
			
			emailSDO.setCallerId(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());
			String emailHtmlUrl = "";
		    String emailHtmlParam = "";
			if(systemPK.getMall().equals("MBM")){
	        	emailSDO.setMallEmail(getConfigService().getProperty("ncp_base.mlb.mall.email.address"));
	        	emailSDO.setSubject(getConfigService().getProperty("ncp_base.mlb.mall.email.subject") + " "+ dto.getMbr().getMbrNm() +" 고객님의 회원정보가 수정되었습니다.");
	        	emailHtmlUrl = getConfigService().getProperty("ncp.url.pc_MLB.root.secure")+"/email/member/modify";
	        }else if(systemPK.getMall().equals("SAM")){
	        	emailSDO.setMallEmail(getConfigService().getProperty("ncp_base.sa.mall.email.address"));
	        	emailSDO.setSubject(getConfigService().getProperty("ncp_base.sa.mall.email.subject") + " "+ dto.getMbr().getMbrNm() +" 고객님의 회원정보가 수정되었습니다.");
	        	emailHtmlUrl = getConfigService().getProperty("ncp.url.pc_SA.root.secure")+"/email/member/modify";
	        }else{
	        	emailSDO.setMallEmail("DISCOVERY <discovery@fnf.co.kr>");
	        	emailSDO.setSubject("[Discovery Expedition] "+ dto.getMbr().getMbrNm() +" 고객님의 회원정보가 수정되었습니다.");
	        	emailHtmlUrl = getConfigService().getProperty("ncp.url.pc_DX.root.secure")+"/email/member/modify";
	        }
	
	       
	        //emailSDO.setMallEmail("DISCOVERY <discovery@fnf.co.kr>");
	        emailSDO.setMbrEmail(dto.getMbr().getMbrEmail());
	        //ailSDO.setSubject("[Discovery Expedition] "+ dto.getMbr().getMbrNm() +" 고객님의 회원정보가 수정되었습니다.");
	        
	        //emailHtmlUrl = getConfigService().getProperty("ncp.url.pc_DX.root.secure")+"/email/member/modify";
	        emailHtmlParam = "mbrNo="+dto.getMbr().getMbrNo();
	        
	        memberInterfaceService.sendHtmlEmail(systemPK, emailSDO, emailHtmlUrl, emailHtmlParam);
		}
		catch(Exception e) {
			log.info("> SEND Mail Exception : {}", e.getMessage());
		}
		
		if(systemPK.getMall().equals("DXM")){	 
		
			try {
				/**
				 * 회원 정보 수정 NTAK, SMS 발송
				 */			
				String mobileNumber = dto.getMbr().getMobilAreaNo() + dto.getMbr().getMobilTlofNo() + dto.getMbr().getMobilTlofWthnNo();
				
				long time = System.currentTimeMillis();
				Date curDt = new Date(time);
	
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				String modifyDt = sdf.format(curDt);
				
				String uurl = getConfigService().getProperty("ncp.url.pc_DX.root.secure")+"/mypage/member/modifyMemberView";;
	//			if(systemPK.getMall().equals("MBM")){	        	
	//	        	uurl = getConfigService().getProperty("ncp.url.pc_MLB.root.secure")+"/mypage/member/modifyMemberView";
	//	        }else if(systemPK.getMall().equals("SAM")){	        	
	//	        	uurl = getConfigService().getProperty("ncp.url.pc_SA.root.secure")+"/mypage/member/modifyMemberView";
	//	        }else{	        	
	//	        	uurl = getConfigService().getProperty("ncp.url.pc_DX.root.secure")+"/mypage/member/modifyMemberView";
	//	        }
				
				ArrayList<String> params = new ArrayList<>();
		        params.add(0, dto.getMbr().getMbrNm());
		        params.add(1, uurl);
		        params.add(2, modifyDt);
		        
				MPushAlimTalkSDO mPushAlimTalkSDO = new MPushAlimTalkSDO();
				
				mPushAlimTalkSDO.setMbrNo(dto.getMbr().getMbrNo());
				mPushAlimTalkSDO.setReceiveMobileNo(mobileNumber);
				mPushAlimTalkSDO.setMallId(systemPK.getMall());
				mPushAlimTalkSDO.setCallerId(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());
				mPushAlimTalkSDO.setMsgKey("DXM_MBR_JOIN_02");
				mPushAlimTalkSDO.setParams(params);
				mPushAlimTalkSDO.setSendDirectFlag(true);
		        
		        memberInterfaceService.sendAlimTalk(systemPK, mPushAlimTalkSDO);
			}
			catch(Exception e) {
				log.info("> SEND NTAK/SMS Exception : {}", e.getMessage());
			}
		}
		
		// 개인정보이용이력 등록 -- TODO 개인정보 이용이력의 이용 업무 상세코드에서 탈퇴 없음.
		String[][] usefCdInfo = { // 구분, 업무, 업무상세
				{UsefSectCd.PSNL_INFO_TRTMNT_CNSGN_DETL.toString(), UsefJobCd.SND_KKO_NTCN_TAK.toString(), UsefJobDetail.STPLAT_APL.toString()},
				{UsefSectCd.PSNL_INFO_TRTMNT_CNSGN_DETL.toString(), UsefJobCd.SND_EMAIL.toString(), UsefJobDetail.STPLAT_APL.toString()}
		};
		String[] target = {dto.getMbr().getMbrNo()};
		memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK, usefCdInfo, target, null, null, dto.getMbr().getMbrNo());
		
		return memberFoResult;
	}

	@Override
	public Mbr addMember(SystemPK systemPK, MemberFoDTO dto) {
		Mbr mbr = dto.getMbr();

		try{
			log.info(CommonResponseCode.MB_00002_온라인회원가입_시도.toMessage()
					+ " MemberFoDTO {} : "+dto);
			
			/**
			 * 온라인 몰에서 신규가입한 경우가 아닌 ERP에는 회원이 있으나 온라인 몰에는 없는 경우에는
			 * 패스워드는 이미 암호화가 되어 있으므로 암호화를 하지 않는다.
			 * 신규가입인지 아닌지의 판단은 인증정보가 현 시점에 있는지 없는지로 판단한다.
			 * 
			 * dto.getMbrCrtfc() 이 정보는 아래의 this.insertMemberCrtfc(dto); 이 함수에서 생성되므로
			 * this.insertMemberCrtfc(dto); 실행 전에 dto.getMbrCrtfc() 이 정보가 있으면 신규가입이 아닌
			 * 것으로 판단한다.
			 * 
			 * 해당 인증정보는 MemberBaseCommandService.insertOnlineMemberByErp 함수에서 세팅한다.
			 */
			boolean newJoinFlag = true;
			if(dto.getMbrCrtfc() != null) {
				newJoinFlag = false;
			}
			
			// 신규 가입
			if(newJoinFlag) {
				//SSO Group Code
				mbr.setSsoGrpCd(memberCertifySelectService.getSsoGrpCd(systemPK.getMall()));

				if(mbr.getJoinMallId() == null || "".equals(mbr.getJoinMallId())) {
					mbr.setJoinMallId(systemPK.getMall());
				}
				mbr.setJoinLangCd(systemPK.getLang());
				mbr.setJoinDvcCd(systemPK.getDevice());
				mbr.setMbrStatCd(MemberStatCd.ACT.toString());
				mbr.setMbrTpCd(MemberTpCd.ONLINE_MBR.toString());
				mbr.setMbrAtrbCd(MemberAtrbCd.GNRL_MBR.toString());
				//정보성 알람 수신방법 디폴트====>카카오톡
				mbr.setInforNtcnRecptnTpCd(MemberEnum.MemberInforRecptnCd.NTCN_TAK.toString());
				
				//암호화
				String sha256Pw = IdGenService.generateSHA256(mbr.getMbrPw());
				String md5Pw = IdGenService.generateMD5(mbr.getMbrPw());
				
				mbr.setMbrPw(sha256Pw);
				mbr.setMbrErpPw(md5Pw);
			}
			
			/*
			 *   osCd : OS
			 *            - IOS / ANDROID / UNKNOWN(알수없음)
			 *   mobileAppSectCd : 모바일 어플리케이션 구분
			 *            - plgrimshop_app / beanpole_app / 8seconds_app
			 */
			String osCd = systemPK.getDevicePlatform();
			String mobileAppSectCd = systemPK.getApp();

			mbr.setOsCd(osCd);
			mbr.setMobileAppSectCd(mobileAppSectCd);

			mbr = memberBaseCommandService.addMember(mbr);

			// 회원 약관 동의
			List<String> stplatCdList = dto.getStplatCd();
			List<String> stplatIemAgrYnList = dto.getStplatIemAgrYn();

			for(int i = 0; i < stplatCdList.size() ; i++){
				MbrStplatAgr mbrStplatAgr = new MbrStplatAgr();
				mbrStplatAgr.setMbrNo(mbr.getMbrNo());
				mbrStplatAgr.setStplatCd(stplatCdList.get(i));
				mbrStplatAgr.setStplatIemAgrYn(stplatIemAgrYnList.get(i));
				mbrStplatAgr.setRegtrId(mbr.getMbrNo());
				mbrStplatAgr.setUdterId(mbr.getMbrNo());
				memberPersonalInfoCommandService.insertMbrStplatAgr(mbrStplatAgr);
			}

			/**
			 * 온라인 몰에서 신규가입한 경우가 아닌 ERP에는 회원이 있으나 온라인 몰에는 없는 경우에는
			 * ERP로 데이터 전송 및 혜택을 처리하지 않는다.
			 * 신규가입인지 아닌지의 판단은 인증정보가 현 시점에 있는지 없는지로 판단한다.
			 * 
			 * dto.getMbrCrtfc() 이 정보는 아래의 this.insertMemberCrtfc(dto); 이 함수에서 생성되므로
			 * this.insertMemberCrtfc(dto); 실행 전에 dto.getMbrCrtfc() 이 정보가 있으면 신규가입이 아닌
			 * 것으로 판단한다.
			 * 
			 * 해당 인증정보는 MemberBaseCommandService.insertOnlineMemberByErp 함수에서 세팅한다.
			 */
			//회원 인증 값이 있는 경우 DB에 넣는다
			if (dto.isSignupType()) {
				this.insertMemberCrtfc(dto);
	        }
			
			/**
			 * 1. 신규 가입
			 * - ERP에 회원가입정보 전송.
			 * - CID 번호를 온라인 회원정보에 업데이트.
			 * - ERP에 회원에 발행된 온/오프라인 쿠폰 조회.
			 * - ERP에서 발행된 온/오프라인 쿠폰 등록.
			 * 
			 * 2. 기존 회원
			 * - ERP에 회원에 발행된 온/오프라인 쿠폰 조회.
			 * - ERP에서 발행된 온/오프라인 쿠폰 등록.
			 */
			// 신규 가입
			if(newJoinFlag) {
				// ERP에 회원가입정보 전송.(R:온라인회원정보 송신, C:회원정보생성, U:회원정보수정)
				// 인터페이스 Exception 발생시 롤백하도록 처리 필요. 
				SendMemberInformationSDO sendMemberInformationSDO = memberInterfaceService.sendMemberInformation(systemPK, dto, "C");
				
				if(sendMemberInformationSDO.getCid() == null) {
					throw new RuntimeException();
				}
				
				dto.getMbr().setErpCstmrNo(sendMemberInformationSDO.getCid());
				
				Mbr mbrForUpdate = new Mbr();
				mbrForUpdate.setMbrNo(dto.getMbr().getMbrNo());
				mbrForUpdate.setErpCstmrNo(sendMemberInformationSDO.getCid());
				MemberFoDTO dtoForUpdate = new MemberFoDTO();
				dtoForUpdate.setMbr(mbrForUpdate);
	
				// 회원정보 업데이트
				memberBaseCommandService.updateFoMember(dtoForUpdate);
				
				// ERP 회원 등급
				//String memberGrade = "";
				if(sendMemberInformationSDO.getMemberGradeList() != null && !sendMemberInformationSDO.getMemberGradeList().isEmpty()) {
					for(MemberGradeInformationSDO memberGradeInformationSDO : sendMemberInformationSDO.getMemberGradeList()) {
						/*
						if("X".equals(memberGradeInformationSDO.getBrandDiv())) {
							memberGrade = memberGradeInformationSDO.getMemberGrade();
							break;
						}else if("M".equals(memberGradeInformationSDO.getBrandDiv())) {
							memberGrade = memberGradeInformationSDO.getMemberGrade();
							break;
						}
						*/
						// 온라인 회원등급 등록
						//기존 discovery 외에 mlb, sa 추가 
						this.insertOnlineGradeInfoForAddMember(systemPK, newJoinFlag, mbr, dto, memberGradeInformationSDO);
					}
				}
				//dto.setMemberGrade(memberGrade);
			}else{
				//기존 ERP 등록회원인 경우 회원정보를 읽어와서 등급을 등록한다.
				MemberInformationSDO memberInformationSDO = memberInterfaceService.getMemberInformation(systemPK, dto.getMbr());
				if(memberInformationSDO.getMemberGradeList() != null && !memberInformationSDO.getMemberGradeList().isEmpty()) {
					for(MemberGradeInformationSDO memberGradeInformationSDO : memberInformationSDO.getMemberGradeList()) {						
						// 온라인 회원등급 등록
						//기존 discovery 외에 mlb, sa 추가 
						this.insertOnlineGradeInfoForAddMember(systemPK, newJoinFlag, mbr, dto, memberGradeInformationSDO);
					}
				}
			}
			
			// 온라인 회원등급 등록
			//this.insertOnlineGradeInfoForAddMember(systemPK, newJoinFlag, mbr, dto);
			
			try {
				// ERP 온/오프라인 쿠폰 등록 처리
				this.insertOnOffCouponForaddMember(systemPK, dto);
			}
			catch(Exception e) {
				StringWriter error = new StringWriter();
				e.printStackTrace(new PrintWriter(error));
				log.error("addMember.addMemberAfterInsert : {}", this.getClass().getName() + " : " + error.toString());
			}
			
			log.info(CommonResponseCode.MB_00003_온라인회원가입_성공.toMessage());
		}catch (Exception e){
			log.info(CommonResponseCode.MB_40002_온라인회원가입_실패.toMessage() +" MemberFoDTO {} : "+e.getMessage());
			throw new RuntimeException(e);
		}

		return mbr;
	}
	
	private void insertOnlineGradeInfoForAddMember(SystemPK systemPK, boolean newJoinFlag, Mbr mbr, MemberFoDTO dto, MemberGradeInformationSDO memberGradeInformationSDO) {
		try{
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
			/*
			// 신규가입
			if(dto.getMemberGrade() == null || "".equals(dto.getMemberGrade())) {
				mbrGrd.setOnlneGrdCd(mbr.getJoinMallId() + "_" + "400");	// Traveler
			}
			else {
				mbrGrd.setOnlneGrdCd(mbr.getJoinMallId() + "_" + dto.getMemberGrade());
			}
			*/
			if(memberGradeInformationSDO.getMemberGrade() == null || "".equals(memberGradeInformationSDO.getMemberGrade())) {
				mbrGrd.setOnlneGrdCd(mbrGrd.getMallId() + "_" + "400");	// Traveler
			}
			else {
				mbrGrd.setOnlneGrdCd(mbrGrd.getMallId() + "_" + memberGradeInformationSDO.getMemberGrade());
			}
			
			SimpleDateFormat insertFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			// 유지기간 시작일시(당일)
			Calendar grdAplBegCal = new GregorianCalendar();
//			grdAplBegCal.add(Calendar.MONTH, -1);
//			grdAplBegCal.set(Calendar.DATE, grdAplBegCal.getMinimum(Calendar.DAY_OF_MONTH));//해당월의 가장첫날 계산

			SimpleDateFormat grdAplBegSdf = new SimpleDateFormat("yyyy-MM-dd");
			String grdAplBegDt = grdAplBegSdf.format(grdAplBegCal.getTime()) + " 00:00:00";
			Date grdBegDt = insertFormat.parse(grdAplBegDt);
			mbrGrd.setGrdAplBegDt(grdBegDt);

			/**
			 * 회원의 등급이 변경되지 않는 회원들은 ERP에서 데이터를 전송받지 않음.
			 * 회원의 등급이 변경되지 않는 회원들의 등급 유지를 위해 등급의 종료일시는 9999-12-31 23:59:59 로 처리.
			 */
			/*
			// 유지기간 종료일시(6개월 후)
			Calendar grdAplEndCal = new GregorianCalendar();
			grdAplEndCal.add(Calendar.MONTH, +6);
			grdAplEndCal.add(Calendar.DATE, -1);
			
			SimpleDateFormat grdAplEndSdf = new SimpleDateFormat("yyyy-MM-dd");
			String grdAplEndDt = grdAplEndSdf.format(grdAplEndCal.getTime()) + " 23:59:59";
			Date grdEndDt = insertFormat.parse(grdAplEndDt);
			mbrGrd.setGrdAplEndDt(grdEndDt);
			*/
			String grdAplEndDt = "9999-12-31 23:59:59";
			Date grdEndDt = insertFormat.parse(grdAplEndDt);
			mbrGrd.setGrdAplEndDt(grdEndDt);
			
			// 등급산정일
			mbrGrd.setGrdSlctnDt(grdBegDt);
			
			// 등록자,수정자는 fo 에서는 회원no로
			mbrGrd.setRegtrId(mbr.getMbrNo());
			mbrGrd.setUdterId(mbr.getMbrNo());

			// 회원등급 등록
			memberBaseCommandService.insertOnlineGradeInfo(mbrGrd);

		}catch (Exception e){
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			log.error("addMember.insertOnlineGradeInfoForAddMember : {}", this.getClass().getName() + " : " + error.toString());
		}
	}
	
	/**
	 * ERP 온/오프라인 쿠폰 등록 처리
	 * 
	 * @param systemPK
	 * @param dto
	 * @throws Exception
	 */
	private void insertOnOffCouponForaddMember(SystemPK systemPK, MemberFoDTO dto) throws Exception {
		
		AdapterHeader adapterHeader = new AdapterHeader();
		
		//Discovery
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId(MallIdEnum.DXM_MALL_ID.toString());
		adapterHeader.setLangCd(systemPK.getLang());
		adapterHeader.setDvcCd(systemPK.getDevice());		
		OnOffCouponIssueSDO onOffCouponIssueSDO = promotionAdapter.getOnOffCouponIssueList(null, "X", dto.getMbr().getErpCstmrNo(), adapterHeader);		
		onOffCpnProcess(systemPK, dto, onOffCouponIssueSDO, MallIdEnum.DXM_MALL_ID.toString());
		
		//MLB
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId(MallIdEnum.MLB_MALL_ID.toString());
		adapterHeader.setLangCd(systemPK.getLang());
		adapterHeader.setDvcCd(systemPK.getDevice());		
		OnOffCouponIssueSDO onOffCouponIssueSDO2 = promotionAdapter.getOnOffCouponIssueList(null, "M", dto.getMbr().getErpCstmrNo(), adapterHeader);		
		onOffCpnProcess(systemPK, dto, onOffCouponIssueSDO2, MallIdEnum.MLB_MALL_ID.toString());
		
		//SA
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId(MallIdEnum.SA_MALL_ID.toString());
		adapterHeader.setLangCd(systemPK.getLang());
		adapterHeader.setDvcCd(systemPK.getDevice());		
		OnOffCouponIssueSDO onOffCouponIssueSDO3 = promotionAdapter.getOnOffCouponIssueList(null, "A", dto.getMbr().getErpCstmrNo(), adapterHeader);		
		onOffCpnProcess(systemPK, dto, onOffCouponIssueSDO3, MallIdEnum.SA_MALL_ID.toString());
	}

	private void onOffCpnProcess(SystemPK systemPK, MemberFoDTO dto, OnOffCouponIssueSDO onOffCouponIssueSDO, String mallId) throws Exception {
		
		List<Map<String, String>> issuedCouponMapListForJoinCpn = new ArrayList<Map<String, String>>(); 
		
		if (onOffCouponIssueSDO != null && onOffCouponIssueSDO.getResponseData() != null) {
			MbrGrd mbrGrdParam = new MbrGrd();			
			mbrGrdParam.setMallId(mallId);
			mbrGrdParam.setMbrNo(dto.getMbr().getMbrNo());			
			
			for (OnOffCouponIssueResultDataSDO sdo : onOffCouponIssueSDO.getResponseData()) {

				// 신규 가입 쿠폰
				if(OnOffCouponEnum.DXM_TRAVELER_30000.getErpCpnId().equals(sdo.getIssuedCouponType())
						|| OnOffCouponEnum.DXM_TRAVELER_50000.getErpCpnId().equals(sdo.getIssuedCouponType())
						|| OnOffCouponEnum.DXM_TRAVELER_100000.getErpCpnId().equals(sdo.getIssuedCouponType())
						|| OnOffCouponEnum.JOIN_10.getErpCpnId().equals(sdo.getIssuedCouponType())
						|| OnOffCouponEnum.JOIN_5000.getErpCpnId().equals(sdo.getIssuedCouponType())
						|| OnOffCouponEnum.JOIN_10000.getErpCpnId().equals(sdo.getIssuedCouponType())
						|| OnOffCouponEnum.JOIN_5.getErpCpnId().equals(sdo.getIssuedCouponType())
						){
		            
					/**
					 * 1건씩 호출할 경우 혜택이력 데이터는 건건이 잘 들어가나 exception 발생. 로그만 찍힘.
					 * 3건을 한번에 호출할 경우 exception은 발생하지 않으나 혜택이력이 1건만 들어감.
					 */
					
					// ERP에서 받은 발행된 온/오프라인 쿠폰 등록
					issuedCouponMapListForJoinCpn = this.makeIssuedCouponMapListForAddMemberAfterInsert(sdo, issuedCouponMapListForJoinCpn, mallId);
					
//					List<Map<String, String>> issuedCouponMapList = this.makeIssuedCouponMapListForAddMemberAfterInsert(sdo, null);
					
//					this.callMemberBenefitForAddMember(systemPK, MemberBenefitEnum.BnefSectCd.NEW_MBR_JOIN, issuedCouponMapList, dto.getMbr(), null);
				}
				// 등급 쿠폰
				else if(OnOffCouponEnum.DXM_ADVENTURER_10DIS.getErpCpnId().equals(sdo.getIssuedCouponType())
						|| OnOffCouponEnum.DXM_EXPLORER_15DIS.getErpCpnId().equals(sdo.getIssuedCouponType())
						|| OnOffCouponEnum.DXM_DISCOVERER_20DIS.getErpCpnId().equals(sdo.getIssuedCouponType())
						){
                    
                    /**
                     * 온/오프라인 등급 쿠폰은 회원가입시에 해당 회원의 등급이 상위등급이면 하위등급의 쿠폰도 보유하고 있을수도 있기 때문에
                     * 혜택 모듈을 사용하지 않고 ERP에서 받은 쿠폰을 다 등록 처리.
                     */
					
					// ERP에서 받은 발행된 온/오프라인 쿠폰 등록
                    List<Map<String, String>> issuedCouponMapList = this.makeIssuedCouponMapListForAddMemberAfterInsert(sdo, null, mallId);
                    log.debug("=======등급 " + issuedCouponMapList);
    				/* 온/오프라인 쿠폰 */
    				boolean isSuccess = memberBenefitApiCommandService.insertIssuedOnOffCoupon(systemPK, dto.getMbr(), issuedCouponMapList);
    				
    				log.info("insertOnOffCouponForaddMember.insertIssuedOnOffCoupon isSuccess : {}", isSuccess);
				}
				// 생일 쿠폰
				else if(OnOffCouponEnum.DXM_TRAVELER_5BIRTH.getErpCpnId().equals(sdo.getIssuedCouponType())
						|| OnOffCouponEnum.DXM_ADVENTURER_10BIRTH.getErpCpnId().equals(sdo.getIssuedCouponType())
						|| OnOffCouponEnum.DXM_EXPLORER_15BIRTH.getErpCpnId().equals(sdo.getIssuedCouponType())
						|| OnOffCouponEnum.DXM_DISCOVERER_20BIRTH.getErpCpnId().equals(sdo.getIssuedCouponType())
						|| OnOffCouponEnum.CHILD_BIRTH_10.getErpCpnId().equals(sdo.getIssuedCouponType())
						){
				
					MbrGrd mbrGrd = memberBaseSelectService.selectMbrGrd(mbrGrdParam);
					
					List<Map<String, String>> issuedCouponMapList = this.makeIssuedCouponMapListForAddMemberAfterInsert(sdo, null, mallId);
                    
                    // 혜택API 온라인 회원 등급별 생일 혜택 - 온라인 회원 등급별 생일 혜택
                    this.callMemberBenefitForAddMember(systemPK, MemberBenefitEnum.BnefSectCd.GRDBY_BRTHDY_BNEF, issuedCouponMapList, dto.getMbr(), mbrGrd, mallId);
				}	// End if
			}	// End for

			/**
			 * 신규 가입 쿠폰은 하나의 혜택에 3개 쿠폰이 걸려있어서 리스트로 담아서 처리.
			 * (혜택 이력이 1개밖에 안 들어감)
			 */
//			//erp 에서 받은 issuedCouponMapListForJoinCpn 이 없어도 online 전용 쿠폰이 있을수 있으므로 호출함. 
//			//if(!issuedCouponMapListForJoinCpn.isEmpty()) {
//	            // 혜택API 멤버십 신규가입 - 신규 회원 가입
//				if(issuedCouponMapListForJoinCpn.isEmpty()) {
//					issuedCouponMapListForJoinCpn = null;
//				}
//				log.debug("==========신규가입 쿠폰 시작=======");
//	            this.callMemberBenefitForAddMember(systemPK, MemberBenefitEnum.BnefSectCd.NEW_MBR_JOIN, issuedCouponMapListForJoinCpn, dto.getMbr(), null);
//	            log.debug("==========신규가입 쿠폰 끝=======");
//			//}
			
            /**
             * 온/오프라인 등급 쿠폰은 회원가입시에 해당 회원의 등급이 상위등급이면 하위등급의 쿠폰도 보유하고 있을수도 있기 때문에
             * 혜택 모듈을 사용하지 않고 쿠폰은 따로 등록을 하고 등급 변경시에 지급되는 포인트만 현재 등급에 맞게 혜택 모듈로 처리.
             */
			//MbrGrd mbrGrd = memberBaseSelectService.selectMbrGrd(mbrGrdParam);
			
            // 혜택API 온라인 회원 등급 변경 - 온라인 회원 등급 변경
            //this.callMemberBenefitForAddMember(systemPK, MemberBenefitEnum.BnefSectCd.ONLNE_GRD_MOD, null, dto.getMbr(), mbrGrd);
		}	// End if
		
		log.debug("==========신규가입 쿠폰 시작=======" + mallId);
		log.debug("==========신규가입 쿠폰 시작=======onoff 쿠폰 : " + issuedCouponMapListForJoinCpn.size());
		if(issuedCouponMapListForJoinCpn.isEmpty()) {
			issuedCouponMapListForJoinCpn = null;
		}
        this.callMemberBenefitForAddMember(systemPK, MemberBenefitEnum.BnefSectCd.NEW_MBR_JOIN, issuedCouponMapListForJoinCpn, dto.getMbr(), null, mallId);
        log.debug("==========신규가입 쿠폰 끝=======" + mallId);
		
		/**
         * 온/오프라인 등급 쿠폰은 회원가입시에 해당 회원의 등급이 상위등급이면 하위등급의 쿠폰도 보유하고 있을수도 있기 때문에
         * 혜택 모듈을 사용하지 않고 쿠폰은 따로 등록을 하고 등급 변경시에 지급되는 포인트만 현재 등급에 맞게 혜택 모듈로 처리.
         */
        MbrGrd mbrGrdParam = new MbrGrd();			
		mbrGrdParam.setMallId(mallId);
		mbrGrdParam.setMbrNo(dto.getMbr().getMbrNo());		
		MbrGrd mbrGrd = memberBaseSelectService.selectMbrGrd(mbrGrdParam);
        // 혜택API 온라인 회원 등급 변경 - 온라인 회원 등급 변경
		log.debug("==========회원 등급 변경 쿠폰 시작=======" + mallId);
        this.callMemberBenefitForAddMember(systemPK, MemberBenefitEnum.BnefSectCd.ONLNE_GRD_MOD, null, dto.getMbr(), mbrGrd, mallId);
        log.debug("==========회원 등급 변경 쿠폰 끝=======" + mallId);
		
	}	
	
	
	private List<Map<String, String>> makeIssuedCouponMapListForAddMemberAfterInsert(OnOffCouponIssueResultDataSDO sdo, List<Map<String, String>> issuedCouponMapList, String mallId) {
		if(issuedCouponMapList == null) {
			issuedCouponMapList = new ArrayList<Map<String, String>>();
		}
        Map<String, String> onOffCouponMap = new HashMap<String, String>();
        onOffCouponMap.put("ISSUED_COUPON_TYPE", sdo.getIssuedCouponType());
        onOffCouponMap.put("ISSUED_COUPON_NO", sdo.getIssueno());
        if(sdo.getUseStartDt() != null) {
        	onOffCouponMap.put("USE_START_DT", sdo.getUseStartDt());
        }
        if(sdo.getUseEndDt() != null) {
        	onOffCouponMap.put("USE_END_DT", sdo.getUseEndDt());
        }
        if(sdo.getCreateDt() != null) {
        	onOffCouponMap.put("CREATE_DT", sdo.getCreateDt());
        }
        if(sdo.getUseYn() != null) {
        	onOffCouponMap.put("USE_YN", sdo.getUseYn());
        }
        if(sdo.getUseDt() != null) {
        	onOffCouponMap.put("USE_DT", sdo.getUseDt());
        }
        
        onOffCouponMap.put("MALL_ID", mallId);
        
        issuedCouponMapList.add(onOffCouponMap);
        
        return issuedCouponMapList;
	}
	
	/**
	 * 회원 가입 및 등록 처리 후 혜택 처리.
	 * 
	 * @param systemPK
	 * @param bnefSectCd
	 * @param issuedOnOffCouponMapList
	 * @param mbr
	 * @param mbrGrd
	 */
	private void callMemberBenefitForAddMember(SystemPK systemPK, MemberBenefitEnum.BnefSectCd bnefSectCd, List<Map<String, String>> issuedOnOffCouponMapList, Mbr mbr, MbrGrd mbrGrd, String mallId) {
		// 혜택 처리
        MemberBenefitApiResult apiResult = memberBenefitFOComponent.callMemberBenefit(systemPK, bnefSectCd, issuedOnOffCouponMapList, mbr, mbrGrd, mallId);
        
        log.info("MEMBER_BENEFIT_API : ResultCd({}),ResultMsg({})", apiResult.getResultCd(), apiResult.getResultMsg());

        if(apiResult != null && apiResult.getMemberBenefitResultList() != null) {
            List<MemberBenefitResult> resultList = apiResult.getMemberBenefitResultList();

            for(int i= 0; i < resultList.size(); i++) {
                MemberBenefitResult memberBenefitResult = resultList.get(i);
                log.info("MEMBER_BENEFIT_API_DETAIL : ResultCd({}),ResultMsg({})", memberBenefitResult.getResultCd(), memberBenefitResult.getResultMsg());
            }
        }
	}
	
	@Override
	public void sendJoinMailSms(SystemPK systemPK, MemberFoDTO dto) {
		Mbr mbr = dto.getMbr();
		try {
			/**
			 * 회원 가입 메일 발송
			 */
			EmailHtmlSDO emailSDO = new EmailHtmlSDO();
	
	        emailSDO.setCallerId(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());
	        
	        String emailHtmlUrl = "";
	        String emailHtmlParam = "";
	        
	        if(systemPK.getMall().equals("MBM")){
	        	emailSDO.setMallEmail(getConfigService().getProperty("ncp_base.mlb.mall.email.address"));
	        	emailSDO.setSubject(getConfigService().getProperty("ncp_base.mlb.mall.email.subject") + " " + mbr.getMbrNm() +" 고객님의 회원가입을 환영합니다.");
	        	emailHtmlUrl = getConfigService().getProperty("ncp.url.pc_MLB.root.secure")+"/email/member/join";
	        }else if(systemPK.getMall().equals("SAM")){
	        	emailSDO.setMallEmail(getConfigService().getProperty("ncp_base.sa.mall.email.address"));
	        	emailSDO.setSubject(getConfigService().getProperty("ncp_base.sa.mall.email.subject") + " " + mbr.getMbrNm() +" 고객님의 회원가입을 환영합니다.");
	        	emailHtmlUrl = getConfigService().getProperty("ncp.url.pc_SA.root.secure")+"/email/member/join";
	        }else{
	        	emailSDO.setMallEmail("DISCOVERY <discovery@fnf.co.kr>");
	        	emailSDO.setSubject("[Discovery Expedition] "+ mbr.getMbrNm() +" 고객님의 회원가입을 환영합니다.");
	        	emailHtmlUrl = getConfigService().getProperty("ncp.url.pc_DX.root.secure")+"/email/member/join";
	        }	        
	        
	        emailSDO.setMbrEmail(mbr.getMbrEmail());		        
	        
	        emailHtmlParam = "mbrNo="+dto.getMbr().getMbrNo();
	        
	        memberInterfaceService.sendHtmlEmail(systemPK, emailSDO, emailHtmlUrl, emailHtmlParam);
		}
		catch(Exception e) {
			log.info("> SEND Mail Exception : {}", e.getMessage());
		}
		
		try {
			/**
			 * 회원 가입 NTAK, SMS 발송
			 */
			String mobileNumber = dto.getMbr().getMobilAreaNo() + dto.getMbr().getMobilTlofNo() + dto.getMbr().getMobilTlofWthnNo();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			
			mbr.setMallId(systemPK.getMall());
			// 발급된 신규 회원 가입 쿠폰 정보 조회
			List<MypageCpnFoResult> list = memberBenefitSelectService.selectIssuedNewJoinCoupon(mbr);
			/**
			 * 2018.09.03
			 * 가입시 발송되는 쿠폰명을 '신규 가입 감사 3천원, 5천원, 1만원 할인쿠폰' 해당 문구로 하드코딩해달라고 하여 수정.
			 * sms문구는 한글로만 보내기에 메세지 처리하지 않음.
			 */
			String cpnNm = "신규 가입 감사 3천원, 5천원, 1만원 할인쿠폰 & 온오프라인 5% 쿠폰";
			String cpnValidEndDt = "";
			String preCpnValidEndDt = "";
			if(list != null) {
				for(MypageCpnFoResult mypageCpnFoResult : list) {
//					cpnNm = mypageCpnFoResult.getPrmNm();
					if(systemPK.getMall().equals("DXM")) {
						// 2020/04/01 (신규가입 감사 3천/5천/1만원 할인쿠폰), 2020/01/31 (온오프라인 5% 쿠폰)까지
						if(StringService.isEmpty(preCpnValidEndDt) && StringService.isNotEmpty(mypageCpnFoResult.getValidEndDate())) {
							cpnValidEndDt += sdf.format(DateService.createDefaultDate(mypageCpnFoResult.getValidEndDate()+"235959")) + " (신규가입 감사 3천/5천/1만원 할인쿠폰)";
						} else if(StringService.isNotEmpty(preCpnValidEndDt) && !preCpnValidEndDt.equals(mypageCpnFoResult.getValidEndDate())) {
							cpnValidEndDt += ", " + sdf.format(DateService.createDefaultDate(mypageCpnFoResult.getValidEndDate()+"235959")) + " (온오프라인 5% 쿠폰)";
							break;
						}
						preCpnValidEndDt = mypageCpnFoResult.getValidEndDate();
					} else {
						if(StringService.isNotEmpty(mypageCpnFoResult.getValidEndDate())) {
							cpnValidEndDt = sdf.format(DateService.createDefaultDate(mypageCpnFoResult.getValidEndDate()+"235959"));
							break;
						}
					}
				}
			}
			
			String uurl = "";
			if(systemPK.getMall().equals("SAM")){
				uurl = getConfigService().getProperty("ncp.url.mb_SA.root.secure");
			}else if(systemPK.getMall().equals("MBM")){
				uurl = getConfigService().getProperty("ncp.url.mb_MLB.root.secure");
			}else{				
				uurl = getConfigService().getProperty("ncp.url.mb_DX.root.secure");
			}
			
			// 신규 회원 가입 쿠폰이 발급된 쿠폰이 있는 경우만 전송.			
			if(StringService.isNotEmpty(cpnValidEndDt)) {
				ArrayList<String> params = new ArrayList<>();
				
				if(systemPK.getMall().equals("SAM")){
					cpnNm = "신규 가입 감사 5천원, 1만원 할인쿠폰";
					
					params.add(0, getConfigService().getProperty("ncp_base.talk.sa.shop.name"));
					params.add(1, dto.getMbr().getMbrNm());
			        params.add(2, cpnNm);
			        params.add(3, cpnValidEndDt);
			        params.add(4, uurl+"/mypage/benefit/listCoupon");
				}else if(systemPK.getMall().equals("MBM")){
					//cpnNm = "신규 가입 감사 10% 할인쿠폰";
					
					params.add(0, getConfigService().getProperty("ncp_base.talk.mlb.shop.name"));
					params.add(1, dto.getMbr().getMbrNm());
			        //params.add(2, cpnNm);
			        //params.add(3, cpnValidEndDt);
			        //params.add(4, uurl+"/mypage/benefit/listCoupon");
				}else{
					params.add(0, dto.getMbr().getMbrNm());
			        params.add(1, cpnNm);
			        params.add(2, cpnValidEndDt);
			        params.add(3, uurl+"/mypage/benefit/listCoupon");
				}
		        
				MPushAlimTalkSDO mPushAlimTalkSDO = new MPushAlimTalkSDO();
				
				mPushAlimTalkSDO.setMbrNo(dto.getMbr().getMbrNo());
				mPushAlimTalkSDO.setReceiveMobileNo(mobileNumber);
				mPushAlimTalkSDO.setMallId(systemPK.getMall());
				mPushAlimTalkSDO.setCallerId(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());
				if(systemPK.getMall().equals("SAM")){
					mPushAlimTalkSDO.setMsgKey("SAM_MBR_JOIN_01");
				}else if(systemPK.getMall().equals("MBM")){
					mPushAlimTalkSDO.setMsgKey("MBM_MBR_JOIN_01");
				}else{				
					mPushAlimTalkSDO.setMsgKey("DXM_MBR_JOIN_01");
				}
				
				mPushAlimTalkSDO.setParams(params);
				mPushAlimTalkSDO.setSendDirectFlag(true);
		        
		        memberInterfaceService.sendAlimTalk(systemPK, mPushAlimTalkSDO);
			}
		}
		catch(Exception e) {
			log.info("> SEND NTAK/SMS Exception : {}", e.getMessage());
		}
		
		// 개인정보이용이력 등록 -- TODO 개인정보 이용이력의 이용 업무 상세코드에서 탈퇴 없음.
		String[][] usefCdInfo = { // 구분, 업무, 업무상세
				{UsefSectCd.PSNL_INFO_TRTMNT_CNSGN_DETL.toString(), UsefJobCd.SND_KKO_NTCN_TAK.toString(), UsefJobDetail.STPLAT_APL.toString()},
				{UsefSectCd.PSNL_INFO_TRTMNT_CNSGN_DETL.toString(), UsefJobCd.SND_EMAIL.toString(), UsefJobDetail.STPLAT_APL.toString()}
		};
		String[] target = {dto.getMbr().getMbrNo()};
		memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK, usefCdInfo, target, null, null, dto.getMbr().getMbrNo());
	}
	
	/**
	 * 인증 이력 저장
	 * 
	 * @param request
	 * @throws Exception
	 */
	private void insertMemberCrtfc(MemberFoDTO dto) throws Exception{
		try {
			HttpServletRequest request = ContextService.getCurrentRequest();
			SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);

			Mbr mbr = dto.getMbr();

			MbrCrtfc crtfc = new MbrCrtfc();
			
			if(dto.getMbrCrtfc() != null) {
				crtfc = dto.getMbrCrtfc();
			}
			
			crtfc.setMbrNo(mbr.getMbrNo());

			if(crtfc.getRlnmCrtfcDi() == null || crtfc.getRlnmCrtfcCi() == null) {
				String di = "";
				String ci = "";
				// 본인인증, 아이핀인증 정보를 세션에서 조회
				if(request.getSession().getAttribute(SessionEnum.PCC.toString()) != null) {
		        	Pcc pcc = (Pcc)request.getSession().getAttribute(SessionEnum.PCC.toString());
	
		        	di = pcc.getDi();
		        	ci = pcc.getCi();
		        	
		        	crtfc.setMbrCrtfcTpCd(UnityMbrCrtfcSectCd.SLF_CRTFC.toString());
		        }
				else if(request.getSession().getAttribute(SessionEnum.IPIN.toString()) != null) {
		        	Ipin ipin = (Ipin)request.getSession().getAttribute(SessionEnum.IPIN.toString());
		        	
		        	di = ipin.getDiscrhash();
		        	ci = ipin.getCiscrhash();
		        	
		        	crtfc.setMbrCrtfcTpCd(UnityMbrCrtfcSectCd.IPIN_CRTFC.toString());
		        }
				
				crtfc.setRlnmCrtfcDi(di);
				crtfc.setRlnmCrtfcCi(ci);
			}
			crtfc.setRlnmCrtfcResultCd(MemberEnum.YES.toString());
			crtfc.setRlnmCrtfcVer("1");

			/*
			 * 멤버십 약관 동의가 따로 존재하지 않아 데이터는 넣지 않음.
			String srv = "";
			
			if(request.getSession().getAttribute("srv") != null) {
				srv = request.getSession().getAttribute("srv").toString();
			}
			
			if("newjoin".equals(srv)) {
				// 멤버십 회원 중복 체크
				MemberFoResult checkCertMbr = memberAuthFOComponent.checkMemberCertify(crtfc.getRlnmCrtfcDi(), null, null);
				// 멤버십 회원 중복 계정이 없으면 멤버십 회원으로 전환
				if(checkCertMbr == null) {
					mbr.setMbrTpCd(MemberTpCd.UNITY_MBR.toString());
					mbr.setUnityMbrCrtfcDt(regDt);

					// 멤버십 약관동의
					String stplatCd = MemberEnum.StplatCd.MBSH_STPLAT.toString();
					String stplatIemAgrYn = MemberEnum.YES.toString();

					List<String> stplatCdList = new ArrayList<String>();
					List<String> stplatIemAgrYnList = new ArrayList<String>();
					stplatCdList.add(stplatCd);
					stplatIemAgrYnList.add(stplatIemAgrYn);

					dto.setStplatCd(stplatCdList);
					dto.setStplatIemAgrYn(stplatIemAgrYnList);
				}
			}
			else {
				mbr.setMbrTpCd(MemberTpCd.UNITY_MBR.toString());
				mbr.setUnityMbrCrtfcDt(regDt);
			}
			*/
			mbr.setMbrTpCd(MemberTpCd.UNITY_MBR.toString());
			if(mbr.getUnityMbrCrtfcDt() == null) {
				long time = System.currentTimeMillis();
				Date regDt = new Date(time);
				mbr.setUnityMbrCrtfcDt(regDt);
			}
			
			mbr.setUnityMbrCrtfcSectCd(crtfc.getMbrCrtfcTpCd());
			crtfc.setMbrCrtfcYn(MemberEnum.YES.toString());
			dto.setMbr(mbr);
			dto.setMbrCrtfc(crtfc);

			/**
			 * 간편 가입 관련 응답코드 설정을 위한 세팅
			 */
			String mbrIdCntcCd = String.valueOf(request.getSession().getAttribute(SessionEnum.ID_CNTC_TP_CD.toString()));
			if (!"null".equals(mbrIdCntcCd) && StringService.isNotEmpty(mbrIdCntcCd)) {
				dto.setMbrIdCntcCd(mbrIdCntcCd);
			}

			// 멤버십 인증 저장
			memberAuthFOComponent.insertMemberCrtfc(pk, dto);
			dto.setMallId(pk.getMall());
		}
		catch (Exception e) {
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			log.error("insertMemberCrtfc : {}", this.getClass().getName() + " : " + error.toString());
		}
	}

	/**
	 * 회원 비밀번호 초기화.
	 */
	@Override
	public void modifyMemberPasswordBy(SystemPK systemPK, MemberBoDTO dto, String loginId)  {
		// step 1. 임시비밀번호 생성 및 암호화
		String mbrPw = IdGenService.generateTempPassword();
		String encMbrPw = IdGenService.generateSHA256(mbrPw);
		dto.getMbr().setMbrPw(encMbrPw);

		// step 2. 개인정보변경이력 등록
		MbrPsnlInfoModHist mpim = memberPersonalInfoCommandService.setMbrPsnlInfoModHist(dto.getMbr().getMbrNo(), "", loginId, false);
		String[] codeArr = {
				MbrPsnlInfoUdtSectCd.MBR_PW.toString()					// 비밀번호
		};
		mpim.setModLcSectCd(MemberModLcSectCd.CC.toString()); // 수정이력 수정몰
		memberPersonalInfoCommandService.insertPersonalInfoForMbr(dto.getMbr(), mpim, codeArr, false);

		// step 3. 임시비밀번호 업데이트
		memberBaseCommandService.updateMemberPasswordBy(dto, loginId, encMbrPw);

		// step 4. 임시비밀번호 발송
		if (StringService.equalsIgnoreCase(dto.getSendMethod(), MemberPwdSendMethod.SMS.toString()) &&
				"KOR".equalsIgnoreCase(dto.getMbr().getJoinLangCd())) {
			/** 해외몰 SMS 전송 X*/
			memberBaseCommandService.sendMemberPasswordSMS(systemPK, dto, loginId, mbrPw);
		} else if (StringService.equalsIgnoreCase(dto.getSendMethod(), MemberPwdSendMethod.EMAIL.toString())) {
			memberBaseCommandService.sendMemberPasswordEMail(systemPK, dto, loginId, mbrPw);
		} else {
			throw new MemberFailSendPasswordException(null);
		}

		// step 5. 개인 정보 이용 이력 등록
		String[][] usefCdInfo = { // 구분, 업무, 업무상세
				{UsefSectCd.PSNL_INFO_TRTMNT_CNSGN_DETL.toString(), UsefJobCd.SND_KKO_NTCN_TAK.toString(), UsefJobDetail.STPLAT_APL.toString()}
		};
		String[] target = {dto.getMbr().getMbrNo()};
		memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
				, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
				, target					// 이용대상 : 회원
				, null						// 유입 일련번호
				, dto.getAccessMenuSn()			// 메뉴 일련번호
				, loginId					// 로그인 ID
		);
	}

	/**
	 * 회원 메모 등록.
	 */
	@Override
	public void addMemberMemoBy(SystemPK systemPK, String mbrNo, String memoCont, String loginId)  {
		// step 1. 개인정보변경이력 등록 ?

		// step 2. 회원 상태 수정
		CsoCnsltMemo ccm = new CsoCnsltMemo();
		ccm.setMbrNo(mbrNo);
		ccm.setMemoCont(memoCont);
		ccm.setMemoRegtrId(loginId);
		ccm.setRegtrId(loginId);
		ccm.setUdterId(loginId);
		ccm.setMemoTpCd("MBR");
		ccm.setCstmrTpCd("");
		ccm.setExpsrYn(MemberEnum.NO.toString());

		try {
			memoService.insertCsoCnsltMemo(ccm);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 회원 정보 변경.
	 */
	@Override
	public void modifyMemberBy(SystemPK systemPK, MemberBoDTO dto, String loginId)  {

			// 휴대폰번호, 전화번호, 법인전화번호 처리
			List<String> mobileNo = makePhoneNo(dto.getMobilNo());
			dto.getMbr().setMobilAreaNo(mobileNo.get(0));
			dto.getMbr().setMobilTlofNo(mobileNo.get(1));
			dto.getMbr().setMobilTlofWthnNo(mobileNo.get(2));
			List<String> telNo = makePhoneNo(dto.getTelNo());
			dto.getMbr().setTelAreaNo(telNo.get(0));
			dto.getMbr().setTelTlofNo(telNo.get(1));
			dto.getMbr().setTelTlofWthnNo(telNo.get(2));
			List<String> cprTelNo = makePhoneNo(dto.getCprtelNo());
			dto.getMbr().setCprtelAreaNo(cprTelNo.get(0));
			dto.getMbr().setCprtelTlofNo(cprTelNo.get(1));
			dto.getMbr().setCprtelTlofWthnNo(cprTelNo.get(2));

			// step 1. 개인정보이용수정 등록
			MbrPsnlInfoModHist mpim = memberPersonalInfoCommandService.setMbrPsnlInfoModHist(dto.getMbr().getMbrNo(), "", loginId, false);
			String[] codeArr = {
					MbrPsnlInfoUdtSectCd.MBR_NM.toString()						// 이름
					, MbrPsnlInfoUdtSectCd.MBR_SEX_SECT_CD.toString()			// 성별
					, MbrPsnlInfoUdtSectCd.MBR_BRTHDY.toString()				// 생년월일
					, MbrPsnlInfoUdtSectCd.MBR_BRTHDY_SLRCLD_YN.toString()		// 회원 생년월일 양력 여부
					, MbrPsnlInfoUdtSectCd.PHON_NO.toString()					// 유선전화번호
					, MbrPsnlInfoUdtSectCd.MOBIL_NO.toString()					// 휴대전화번호
					, MbrPsnlInfoUdtSectCd.MBR_POST_NO.toString()				// 회원 우편번호
					, MbrPsnlInfoUdtSectCd.MBR_ADDR.toString()					// 주소
					, MbrPsnlInfoUdtSectCd.MBR_EMAIL.toString()					// 이메일
					, MbrPsnlInfoUdtSectCd.SMS_RECPTN_AGR_YN.toString()			// SMS수신여부
					, MbrPsnlInfoUdtSectCd.EMAIL_RECPTN_AGR_YN.toString()		// E-mail수신동의여부
					, MbrPsnlInfoUdtSectCd.INFOR_NTCN_RECPTN_TP_CD.toString()  // 정보성 알림 수신 유형 코드
					, MbrPsnlInfoUdtSectCd.CPR_RPRSTIV_NM.toString()			// 대표자명
					, MbrPsnlInfoUdtSectCd.CPR_NM.toString()					// 법인명
					, MbrPsnlInfoUdtSectCd.CPRTEL_NO.toString()				// 법인유선전화번호
					, MbrPsnlInfoUdtSectCd.BMAN_REG_NO.toString()				// 사업자등록번호
			};
			mpim.setModLcSectCd(MemberModLcSectCd.CC.toString()); //BO 코드
			memberPersonalInfoCommandService.insertPersonalInfoForMbr(dto.getMbr(), mpim, codeArr, false);

			// step 2. 회원 정보 수정
			memberBaseCommandService.updateMemberBy(dto, loginId);

			// 2017.03.21 SR #40218 BO 홍보마케팅 약관 및 채널별 광고수신 동의 정보 오류 조치
			String mbrNo = dto.getMbr().getMbrNo();
			String ssoGroupCd = memberCertifySelectService.getSsoGrpCd(dto.getMbr().getJoinMallId());
			Mbr mbrRecptnAgr = memberBaseSelectService.selectMbrStatus(mbrNo);
			MemberFoResult result = new MemberFoResult();
			List<MbrStplatAgr> mbrStplatAgrList = memberBaseSelectService.selectMbrStplatAgrList(mbrNo);
			result.setMbrStplatAgrs(mbrStplatAgrList);
			String agrYn = null;

			List<Mbr> mbrNoList = new ArrayList<Mbr>();

			// 광고성 정보 수신여부가 하나라도 'Y'이면 홍보 마케팅 동의도 'Y'로 데이터 보정
			if("Y".equals(mbrRecptnAgr.getSmsRecptnAgrYn()) || "Y".equals(mbrRecptnAgr.getEmailRecptnAgrYn()) ||
					"Y".equals(mbrRecptnAgr.getTmRecptnAgrYn()) || "Y".equals(mbrRecptnAgr.getDmRecptnAgrYn())) {
				// 선택동의 약관의 동의여부 업데이트
				agrYn = "Y";
				mbrNoList.add(mbrRecptnAgr);
				memberPersonalInfoCommandService.updateMktAgrYn(mbrNoList, result, ssoGroupCd, agrYn);
			}
			if("N".equals(mbrRecptnAgr.getSmsRecptnAgrYn()) && "N".equals(mbrRecptnAgr.getEmailRecptnAgrYn()) &&
					"N".equals(mbrRecptnAgr.getTmRecptnAgrYn()) && "N".equals(mbrRecptnAgr.getDmRecptnAgrYn())) {
				// 선택동의 약관의 동의여부 업데이트
				agrYn = "N";
				mbrNoList.add(mbrRecptnAgr);
				memberPersonalInfoCommandService.updateMktAgrYn(mbrNoList, result, ssoGroupCd, agrYn);
			}

			if ("Y".equalsIgnoreCase(dto.getBaseDlvspYn())) {

				MbrDlvsp mdParam = new MbrDlvsp();

				if ("kr".equalsIgnoreCase(dto.getMbr().getMbrAddrNationCd())) {
					//회원정보 수정시 국문몰에서 가입한 경우라도 해외국가로 기본정보수정가능
					//에 따른 조건 수정

					mdParam.setMbrNo(dto.getMbr().getMbrNo());
					//			mdParam.setDlvAdbukTurn(0);
					mdParam.setAddrseNm(dto.getMbr().getMbrNm());
					mdParam.setBaseDlvspYn(MemberEnum.YES.toString());
					mdParam.setDlvAddrSectCd(dto.getMbr().getMbrAddrSectCd());
					mdParam.setNationCd(dto.getMbr().getMbrAddrNationCd());
					mdParam.setPostNo(dto.getMbr().getMbrPostNo());
					mdParam.setBaseAddr(dto.getMbr().getMbrBaseAddr());
					mdParam.setDetailAddr(dto.getMbr().getMbrDetailAddr());
					mdParam.setMobilNationNo(dto.getMbr().getMobilNationNo());
					mdParam.setMobilAreaNo(dto.getMbr().getMobilAreaNo());
					mdParam.setMobilTlofNo(dto.getMbr().getMobilTlofNo());
					mdParam.setMobilTlofWthnNo(dto.getMbr().getMobilTlofWthnNo());
					mdParam.setRegtrId(loginId);
					mdParam.setLang("KOR");


				} else {

					mdParam.setMbrNo(dto.getMbr().getMbrNo());
					//			mdParam.setDlvAdbukTurn(0);
					mdParam.setAddrseNm(dto.getMbr().getMbrNm());
					mdParam.setBaseDlvspYn(MemberEnum.YES.toString());
					mdParam.setDlvAddrSectCd(dto.getMbr().getMbrAddrSectCd());
					mdParam.setNationCd(dto.getMbr().getMbrAddrNationCd());
					mdParam.setPostNo(dto.getMbr().getMbrPostNo());
					mdParam.setBaseAddr(dto.getMbr().getMbrBaseAddr());
					mdParam.setCtyNm(dto.getMbr().getMbrCtyNm());
					mdParam.setLcltyNm(dto.getMbr().getMbrLcltyNm());
					mdParam.setMobilNationNo(dto.getMbr().getMobilNationNo());
					mdParam.setMobilAreaNo(dto.getMbr().getMobilAreaNo());
					mdParam.setMobilTlofNo(dto.getMbr().getMobilTlofNo());
					mdParam.setMobilTlofWthnNo(dto.getMbr().getMobilTlofWthnNo());
					mdParam.setRegtrId(loginId);

				}

				//동일 회원배송지 존재여부 체크
				//존재하면 수정, 없으면 수정
				mdParam.setDlvAdbukTurn(memberOrderSelectService.getMbrDeliveryAdbukTurn(mdParam));

				// step 3. 추가 배송지가 대표배송지인 경우에 기존 대표배송지 설정 제거
				if(StringService.equalsIgnoreCase(mdParam.getBaseDlvspYn(), MemberEnum.YES.toString())){
					memberOrderCommandService.updateBaseDeliveryLocationRevomeBy(mdParam, loginId);
				}

				// step 3. 배송지 저장
				memberOrderCommandService.mergeDeliveryLocationBy(mdParam, loginId);

				boolean isReg = mdParam.getDlvAdbukTurn() == null ? true : false;

				// step 1. 개인정보변경이력 등록
//				if(mpim == null){
//					mpim = memberPersonalInfoCommandService.setMbrPsnlInfoModHist(mdParam.getMbrNo(), "", loginId, false);
//				}

				String[] codeArr2 = {
						MbrDlvspPsnlInfoUdtSectCd.DLVSP_ADDR.toString()					// 배송지 주소
						, MbrDlvspPsnlInfoUdtSectCd.DLVSP_ADDRSE.toString()				// 배송지 수취인
						, MbrDlvspPsnlInfoUdtSectCd.DLVSP_TEL_NO.toString()				// 배송지 전화 번호
						, MbrDlvspPsnlInfoUdtSectCd.DLVSP_MOBIL_NO.toString()			// 배송지 휴대전화 번호
				};
				memberPersonalInfoCommandService.insertPersonalInfoForMbrDlvsp(mdParam, mpim, codeArr2, isReg);

			}
			log.info(CommonResponseCode.MB_00034_회원_BO_정보변경_성공.toMessage());

			// 통합회원인 경우만 step 3. ~ step 4. 진행.
			if(!StringService.equalsIgnoreCase(dto.getMbr().getMbrTpCd(), MemberTpCd.UNITY_MBR.toString())){ return;}

			memberPersonalInfoCommandService.updatePersonalInfoErpTrnsmisYn(mpim, loginId);

	}

	/**
	 * 전화번호 설정
	 * @param phoneNo
	 * @return
	 */
	private List<String> makePhoneNo(String phoneNo){
		String checkNo = StringService.substring(StringService.removeHyphen(StringService.nvl(phoneNo, "")), 0, 11);
		String regEx = "(\\d{0})(\\d{0})(\\d{0})";
		String phoneForm = "$1-$2-$3";
		if (checkNo.length() < 9){
			regEx = "(\\d{0})(\\d{0,4})(\\d{4})";
		} else if (StringService.substring(checkNo, 0, 2).equals("02") || StringService.substring(checkNo, 0, 2).equals("10")){
			regEx = "(\\d{2})(\\d{3,4})(\\d{4})";
		} else {
			regEx = "(\\d{2,3})(\\d{3,4})(\\d{4})";
		}

		String[] orgArr =  StringService.split(checkNo.replaceAll(regEx, phoneForm), "-");
		List<String> returnList = new ArrayList<String>();

		for(int i = 0; i < 3-orgArr.length; i++){
			returnList.add("");
		}
		int aIndex = 0;
		for(int i = returnList.size(); i < 3; i++){
			returnList.add(orgArr[aIndex++]);
		}
		return returnList;
	}

	/**
	 * 전화번호 설정(국가번호 포함)
	 * @param phoneNo
	 * @return
	 */
//	private List<String> makeGlobalPhoneNo(String phoneNo){
//
//		String checkNo = StringService.substring(StringService.removeHyphen(StringService.nvl(phoneNo, "")), 0, 13);
//		String regEx = "(\\d{0})(\\d{0})(\\d{0})(\\d{0})";
//		String phoneForm = "$1-$2-$3-$4";
//		if (checkNo.length() < 9){
//			regEx = "(\\d{0})(\\d{0})(\\d{0,4})(\\d{4})";
//		} else if (checkNo.length() > 11){
//			regEx = "(\\d{2})(\\d{2,3})(\\d{3,4})(\\d{4})";
//		} else if (StringService.substring(checkNo, 0, 2).equals("02")){
//			regEx = "(\\d{0})(\\d{2})(\\d{3,4})(\\d{4})";
//		} else {
//			regEx = "(\\d{0})(\\d{2,3})(\\d{3,4})(\\d{4})";
//		}
//
//		String[] orgArr =  StringService.split(checkNo.replaceAll(regEx, phoneForm), "-");
//		String[] spNo = phoneNo.split("-");
//		if(spNo.length == 3 || spNo.length == 4){
//			orgArr = spNo;
//		}
//
//		List<String> returnList = new ArrayList<String>();
//
//		for(int i = 0; i < 4-orgArr.length; i++){
//			returnList.add("");
//		}
//		int aIndex = 0;
//		for(int i = returnList.size(); i < 4; i++){
//			returnList.add(orgArr[aIndex++]);
//		}
//		return returnList;
//	}

	/**
	 * 배송지 목록 저장.
	 */
	@Override
	public void registerDeliveryLocationBy(SystemPK systemPK, MypageFoDTO mypageFoDTO , String loginId, Locale locale)  {
		MbrDlvsp mbrDlvsp = new MbrDlvsp();
		mbrDlvsp = mypageFoDTO.getMbrDlvsp();
		//mbrDlvsp.setDlvAddrSectCd("RD_ADDR"); //도로명 주소로 세팅
		// 개인정보변경이력 SEQ 생성
		MbrPsnlInfoModHist mpim = memberPersonalInfoCommandService.setMbrPsnlInfoModHist(mbrDlvsp.getMbrNo(), "", mbrDlvsp.getMbrNo(), true);
		mbrDlvsp.setUdterId(loginId);
		boolean isReg = mbrDlvsp.getDlvAdbukTurn() == null ? true : false;

//		String loginNationCd = locale.getCountry();

		// 언어코드 설정
		mbrDlvsp.setLang(systemPK.getLang());

		if(StringService.isEmpty(mypageFoDTO.getMbrDlvsp().getNationCd())){
			mbrDlvsp.setNationCd("kr");
		}
		// 휴대폰번호, 전화번호 처리
		/*List<String> mobileNo = makePhoneNo(mypageFoDTO.getMobilNo());
		mbrDlvsp.setMobilNationNo("");
		mbrDlvsp.setMobilAreaNo(mobileNo.get(0));
		mbrDlvsp.setMobilTlofNo(mobileNo.get(1));
		mbrDlvsp.setMobilTlofWthnNo(mobileNo.get(2));
		List<String> telNo = makePhoneNo(mypageFoDTO.getTelNo());
		mbrDlvsp.setTelNationNo("");
		mbrDlvsp.setTelAreaNo(telNo.get(0));
		mbrDlvsp.setTelTlofNo(telNo.get(1));
		mbrDlvsp.setTelTlofWthnNo(telNo.get(2));*/

		// step 1. 개인정보변경이력 등록
		String[] codeArr = {
				MbrDlvspPsnlInfoUdtSectCd.DLVSP_ADDR.toString()				// 배송지 주소
				, MbrDlvspPsnlInfoUdtSectCd.DLVSP_ADDRSE.toString()				// 배송지 수취인
				, MbrDlvspPsnlInfoUdtSectCd.DLVSP_TEL_NO.toString()				// 배송지 전화 번호
				, MbrDlvspPsnlInfoUdtSectCd.DLVSP_MOBIL_NO.toString()			// 배송지 휴대전화 번호
		};
		mpim.setModLcSectCd(MemberModLcSectCd.ONLNE_MALL.toString()); // 수정이력 수정몰
		memberPersonalInfoCommandService.insertPersonalInfoForMbrDlvsp(mbrDlvsp, mpim, codeArr, isReg);
		// step 2. 대표배송지 설정값 확인
		if(StringService.equalsIgnoreCase(mbrDlvsp.getBaseDlvspYn(), "1") || StringService.equalsIgnoreCase(mbrDlvsp.getBaseDlvspYn(), MemberEnum.YES.toString())){
			mbrDlvsp.setBaseDlvspYn(MemberEnum.YES.toString());
		} else {
			mbrDlvsp.setBaseDlvspYn(MemberEnum.NO.toString());
		}
		// step 3. 추가 배송지가 대표배송지인 경우에 기존 대표배송지 설정 제거
		if(StringService.equalsIgnoreCase(mbrDlvsp.getBaseDlvspYn(), MemberEnum.YES.toString())){
			memberOrderCommandService.updateBaseDeliveryLocationRevomeBy(mbrDlvsp, loginId);
		}
		// step 3. 배송지 저장
		memberOrderCommandService.mergeDeliveryLocationBy(mbrDlvsp, loginId);
		if(MemberEnum.YES.toString().equals(mypageFoDTO.getMember())){

			Mbr mbr = new Mbr();

			mbr.setMbrNo(mbrDlvsp.getMbrNo()); /* 회원번호 */
			mbr.setMbrBaseAddr(mbrDlvsp.getBaseAddr()); /* 수취인 기본주소 */
			mbr.setMbrAddrSectCd(mbrDlvsp.getDlvAddrSectCd()); /* 주소구분코드 */
			mbr.setMbrAddrNationCd(mbrDlvsp.getNationCd());
			mbr.setMbrDetailAddr(mbrDlvsp.getDetailAddr());/* 수취인 상세주소 */
			mbr.setMbrCtyNm(mbrDlvsp.getCtyNm());
			mbr.setMbrLcltyNm(mbrDlvsp.getLcltyNm());
			mbr.setMbrPostNo(mbrDlvsp.getPostNo());/* 수취인 우편번호 */
			mbr.setMobilNationNo(mbrDlvsp.getMobilNationNo());  /* 수취인 휴대전화 국가번호 */
			mbr.setMobilAreaNo(mbrDlvsp.getMobilAreaNo());  /* 수취인 휴대전화 지역번호 */
			mbr.setMobilTlofNo(mbrDlvsp.getMobilTlofNo());  /* 수취인 휴대전화 국번호 */
			mbr.setMobilTlofWthnNo(mbrDlvsp.getMobilTlofWthnNo());  /* 수취인 휴대전화 국내번호 */
			mbr.setTelNationNo(mbrDlvsp.getTelNationNo());  /* 수취인 전화 국가번호 */
			mbr.setTelAreaNo(mbrDlvsp.getTelAreaNo()); /* 수취인 전화 지역번호 */
			mbr.setTelTlofNo(mbrDlvsp.getTelTlofNo());  /* 수취인 전화 국번호 */
			mbr.setTelTlofWthnNo(mbrDlvsp.getTelTlofWthnNo());  /* 수취인 전화 국내번호 */

			MemberFoDTO dto = new MemberFoDTO();
			dto.setMbr(mbr);


			// 회원정보 업데이트
			memberBaseCommandService.updateFoMember(dto);

			// 개인정보변경이력 ERP 전송 여부 업데이트 (오류발생되더라도 프로세스에 영향 없도록 처리)
			try{
				memberPersonalInfoCommandService.updatePersonalInfoErpTrnsmisYn(mpim, mbrDlvsp.getMbrNo());
			} catch(Exception e){
				if(log.isInfoEnabled()) log.info("> updatePersonalInfoErpTrnsmisYn Exception : {}", e.getMessage());
			}

		}

	}

	/**
	 * 비밀번호 확인
	 */
	@Override
	public boolean checkMemberPassword(MemberFoDTO dto)  {
		String password = memberBaseSelectService.checkMemberPassword(dto);
		String sha256 = IdGenService.generateSHA256(dto.getMbr().getMbrPw());
		String md5 = IdGenService.generateMD5(dto.getMbr().getMbrPw());
		String md5AndSha256 = IdGenService.generateSHA256(IdGenService.generateMD5(dto.getMbr().getMbrPw()));


		if (password.equals(sha256)) {
			return true;
		} else if (password.equals(md5)) {
			return true;
		} else if (password.equals(md5AndSha256)) {
			return true;
		} else {
			return false;
		}
	}


	@Override
	public List<SysStplat> selectSysStplat(SysStplatUse sysStplatUse)  {
		List<SysStplat> sysStplatList = memberBaseSelectService.selectSysStplat(sysStplatUse);
		return sysStplatList;
	}


	@Override
	public List<SysStplat> selectJoinSysStplat(SysStplatUse sysStplatUse)  {
		List<SysStplat> sysStplatList = memberBaseSelectService.selectJoinSysStplat(sysStplatUse);
		return sysStplatList;
	}

	@Override
	public SecurityUserDetail getMemberInfo(MemberFoDTO dto)  {
		return memberBaseSelectService.selectSecuredMember(dto);
	}

	/**
	 * 회원 상태 가져오기
	 */
	@Override
	public Mbr selectMbrStatus(String mbrNo)  {
		return memberBaseSelectService.selectMbrStatus(mbrNo);
	}

	public MemberFoResult selectSecessionCheck(SystemPK systemPK, MemberFoDTO dto)  {
		//dto.setMallId(systemPK.getMall());
		MemberFoResult result = memberBaseSelectService.selectSecessionCheck(dto);

		// ERP 조회
		if(!StringService.isEmpty(result.getMbr().getErpCstmrNo())) {
			try {
				MypageFoDTO mypageFoDTO = new MypageFoDTO();
				mypageFoDTO.setMbr(result.getMbr());
				mypageFoDTO.setHistoryYn("N");
				MemberMileageInfoSDO memberMileageInfoSDO = memberInterfaceService.getMemberMileage(systemPK, mypageFoDTO);
				// 마일리지
				result.setMilage(new Long(memberMileageInfoSDO.getNowPoint()));

			}
			catch (Exception e) {
				if(log.isInfoEnabled()) log.info("getMemberMileage Exception : {}", e.getMessage());
				result.setMilage(0);
				result.setErpConnectFlag("N");
			}
		}

		// 웹 포인트
		MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
		mbrWebpntHist.setMbrNo(result.getMbr().getMbrNo());
		//mbrWebpntHist.setMallId(result.getMbr().getJoinMallId());
		MbrWebpntHistExtend mbrWebpntHistExt = memberBenefitSelectService.selectWebPointInfo(mbrWebpntHist);
		//MbrWebpntHistExtend mbrWebpntHistExt = memberBenefitSelectService.selectWebPointInfo(result.getMbr().getMbrNo());
		result.setMbrWebpntHistExt(mbrWebpntHistExt);

		// 회원 등급
		MbrGrd mbrGrd = new MbrGrd();
		mbrGrd.setMbrNo(result.getMbr().getMbrNo());
		mbrGrd.setMallId(systemPK.getMall());
		result.setMbrGrd(memberBaseSelectService.selectMbrGrd(mbrGrd));
		
		return result;
	}


	@Override
	public boolean compareMbrData(String langCd , SecurityUserDetail userDetail, Mbr mbrInfo) {
		return memberBaseSelectService.compareMbrData(langCd ,userDetail, mbrInfo);
	}


	@Override
	public MemberFoResult selectMbrSysStplat(MemberFoDTO dto)  {
		return memberBaseSelectService.selectMbrSysStplat(dto);
	}

	@Override
	public Mbr getMbrInfoByMbrNo(String mbrNo)  {
		return memberBaseSelectService.getMbrInfoByMbrNo(mbrNo);
	}

	/**
	 * 아이디 찾기 결과
	 */
	@Override
	public List<MemberFoResult> getMbrId(MemberFoDTO dto)  {
		List<MemberFoResult> resultList = memberBaseSelectService.getMbrId(dto);
		return resultList;
	}

	@Override
	public int checkAddMember(MemberFoDTO dto)  {
		return memberBaseSelectService.checkAddMember(dto);
	}
	
	@Override
	public boolean isCheckId(MemberFoDTO dto)  {
		return memberBaseSelectService.isCheckId(dto);
	}

	@Override
	public boolean isCheckIdByErp(SystemPK pk, MemberFoDTO dto) throws Exception {
		boolean flag = false;
		
		MemberInformationSDO memberInformationSDO = null;
		try {
			memberInformationSDO = memberInterfaceService.getMemberInformation(pk, dto.getMbr());
		} catch (Exception e) {
			flag = true;
			throw new Exception();
		}
		
		if(memberInformationSDO == null
				|| (memberInformationSDO.getId() != null && !"".equals(memberInformationSDO.getId()))
				) {
			flag = true;
		}
		
		return flag;
	}
	
	@Override
	public boolean checkMobilNoAsId(MemberFoDTO dto){
		return memberBaseSelectService.checkMobilNoAsId(dto);
	}

	@Override
	public boolean isValidId(MemberFoDTO dto){
		return memberBaseSelectService.isValidId(dto);
	}

	@Override
	public String selectRecommandMbrNo(MemberFoDTO dto){
		return memberBaseSelectService.selectRecommandMbrNo(dto);
	}

	@Override
	public String selectRecommandMbrId(MemberBoDTO dto){
		return memberBaseSelectService.selectRecommandMbrId(dto);
	}

	@Override
	public boolean isCheckEmail(MemberFoDTO dto)  {
		return memberBaseSelectService.isCheckEmail(dto);
	}

	@Override
	public boolean isCheckEmailOthers(MemberFoDTO dto)  {
		return memberBaseSelectService.isCheckEmailOthers(dto);
	}
	
	/**
	 * 비회원 주문 인증 절차
	 */
	@Override
	public void nonMbrAuthentication()  {
		HttpServletRequest request = ContextService.getCurrentRequest();
		String sessionId = request.getSession().getId();

		log.info("################## Guest Role #####################");

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		//spring 4.0 변경
		grantedAuthorities.add(new SimpleGrantedAuthority(SecurityParam.GUEST_ROLE.toString()));
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				sessionId, sessionId, grantedAuthorities);


		token.setDetails(new WebAuthenticationDetails(request));
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority(SecurityParam.GUEST_ROLE.toString()));
		Authentication authenticatedUser = new UsernamePasswordAuthenticationToken(sessionId, null, grantedAuths);

		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		log.info("################## Guest Role #####################" + SecurityContextHolder.getContext().getAuthentication());

	}

	@Override
	public Mbr selectMbr(Mbr mbr)  {
		return memberBaseSelectService.selectMbr(mbr);
	}

	/**
	 * 회원 목록 조회.
	 */
	@Override
	public MemberResultDTO getMemberList(SystemPK systemPK, MemberBoDTO dto, String loginId)  {
		// step 1. 페이지 인덱스 및 변수 설정
		MemberResultDTO resultDTO = new MemberResultDTO();
		dto.setLang(systemPK.getLang());

		// step 2. 목록 건수 조회
		long listCount = memberBaseSelectService.selectMemberListCount(dto);
		resultDTO.setListCount(listCount);

		// step 3. 목록 조회
		List<MemberBoResult> lists = new ArrayList<MemberBoResult>();
		if(listCount > 0){
			lists = memberBaseSelectService.selectMemberList(dto);
		}
		resultDTO.setLists(lists);

		// step 4. 개인 정보 이용 이력 등록
		if(listCount > 0){
			String[][] usefCdInfo = { // 구분, 업무, 업무상세
					{UsefSectCd.PSNL_INFO_STTUS.toString(), UsefJobCd.INQIRE.toString(), UsefJobDetail.LIST.toString()}
			};
			String[] target = {};
			memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
					, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
					, target					// 이용대상 : 회원
					, null						// 유입 일련번호
					, dto.getAccessMenuSn()			// 메뉴 일련번호
					, loginId					// 로그인 ID
			);
		}

		return resultDTO;
	}

	/**
	 * 회원 엑셀 다운로드 목록 조회.
	 */
	@Override
	public List<Map<String, Object>> getMemberListExcel(SystemPK systemPK, MemberBoDTO dto, String loginId)  {
		// step 1. 회원 엑셀 조회
		dto.setLang(systemPK.getLang());
		List<Map<String, Object>> lists = memberBaseSelectService.selectMemberListExcel(dto);

		// step 2. 개인 정보 이용 이력 등록
		if(lists != null && lists.size() > 0){
			String[][] usefCdInfo = { // 구분, 업무, 업무상세
					{UsefSectCd.PSNL_INFO_STTUS.toString(), UsefJobCd.SAVE.toString(), UsefJobDetail.EXCEL_DOWN.toString()}
			};
			String[] target = null;
			memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
					, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
					, target					// 이용대상 : 회원
					, null						// 유입 일련번호
					, dto.getAccessMenuSn()			// 메뉴 일련번호
					, loginId					// 로그인 ID
			);
		}
		return lists;
	}


	/**
	 * 회원 간략 정보 조회(회원 팝업 호출).
	 */
	public MemberBoResult getMemberSimple(SystemPK systemPK, MemberBoDTO dto, String loginId)  {
		// 회원 정보 조회
		MemberBoResult result = memberBaseSelectService.selectMemberSimple(dto);
		if(StringService.equalsIgnoreCase(result.getMbr().getMbrStatCd(), MemberStatCd.SECSN.toString())){
			throw new MemberStatusSecessionException(null);
		}

		// 개인 정보 이용 이력 등록
		String[][] usefCdInfo = { // 구분, 업무, 업무상세
				{UsefSectCd.PSNL_INFO_STTUS.toString(), UsefJobCd.INQIRE.toString(), UsefJobDetail.MBR_DETAIL.toString()}
		};
		String[] target = {dto.getMbr().getMbrNo()};
		memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
				, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
				, target					// 이용대상 : 회원
				, null						// 유입 일련번호
				, dto.getAccessMenuSn()			// 메뉴 일련번호
				, loginId					// 로그인 ID
		);

		return result;
	}

	/**
	 * 회원 상세 정보 조회.
	 */
	public MemberResultDTO getMember(SystemPK systemPK, MemberBoDTO dto, String loginId)  {
		MemberResultDTO resultDTO = new MemberResultDTO();

		// 상세 정보 조회
		MemberBoResult result = memberBaseSelectService.selectMember(dto);
		resultDTO.setMbrDetail(result);
		String mbrNo = result.getMbr().getMbrNo();
		String mbrTpCd = result.getMbr().getMbrTpCd();


		OrderBoDTO orderBoDTO = new OrderBoDTO();
		orderBoDTO.setPchId(mbrNo);
		OrderBoResult orderBoResult = orderOthersMemberService.selectOrderCountAndAmtForMember(systemPK, orderBoDTO);
		resultDTO.getMbrDetail().setPchCount(orderBoResult.getPchCount());
		resultDTO.getMbrDetail().setPchAmt(orderBoResult.getPchAmt());

		if(!"KOR".equalsIgnoreCase(result.getMbr().getJoinLangCd())){
			MemberBoResult mcResult = memberCertifySelectService.selectMemberCertification(mbrNo, "EMAIL_CRTFC", MemberEnum.YES.toString(), dto.getMaskingYn());
			if( mcResult!= null && mcResult.getMbrCrtfc() != null){
				resultDTO.setMbrCrtfcEmail(mcResult.getMbrCrtfc());
			}
		}

		// 통합회원 인증 정보 조회
		if(StringService.equalsIgnoreCase(mbrTpCd, MemberTpCd.UNITY_MBR.toString())){
			MemberBoResult mcResult = memberCertifySelectService.selectMemberCertification(mbrNo, result.getMbr().getUnityMbrCrtfcSectCd(), MemberEnum.YES.toString(), dto.getMaskingYn());
			if( mcResult!= null && mcResult.getMbrCrtfc() != null){
				resultDTO.setMbrCrtfcUnion(mcResult.getMbrCrtfc());
				resultDTO.setCrtfcMobilNo(mcResult.getMobilNo());
			}
		}

		//네이버 연동 관련

		MbrIdCntcExtend mbrIdCntcExtend = new MbrIdCntcExtend();
		mbrIdCntcExtend.setMbrNo(mbrNo);
		mbrIdCntcExtend.setIdCntcTpCd("NAVER");
		mbrIdCntcExtend.setMaskingYn("Y");

		MbrIdCntc mbrIdCntc = naverUserService.selectNaverUserInfo(mbrIdCntcExtend);
		if(mbrIdCntc != null){
			result.setMbrIdCntc(mbrIdCntc);
		}

		//Facebook 연동 관련
		MbrIdCntc facebukMbrIdCntc = facebookUserService.selectFaceBookUserInfo(mbrNo, result.getMbr().getMbrId(), null);
		if(facebukMbrIdCntc != null){
			result.setFaceBukMbrIdCntc(facebukMbrIdCntc);
		}

		return resultDTO;
	}

	/**
	 * 회원 개인정보 변경이력 목록 조회.
	 */
	public MemberResultDTO getPersonalInfoModHistoryListForMember(SystemPK systemPK, MbrPsnlInfoModHist mpimh, String loginId)  {
		// step 1. 페이지 인덱스 및 변수 설정
		MemberResultDTO resultDTO = new MemberResultDTO();
		mpimh.setLang(systemPK.getLang());

		// step 2. 목록 건수 조회
		long listCount = memberPersonalInfoSelectService.selectPersonalInfoModHistoryListCountForMember(mpimh);
		resultDTO.setListCount(listCount);

		// step 3. 목록 조회
		List<MemberBoResult> lists = new ArrayList<MemberBoResult>();
		if(listCount > 0){
			lists = memberPersonalInfoSelectService.selectPersonalInfoModHistoryListForMember(mpimh);
		}
		resultDTO.setLists(lists);

		return resultDTO;
	}


	/**
	 * 회원 메모 조회.
	 */
	public List<CsoCnsltMemoExtendDTO> getCsoCnsltMemoList(SystemPK systemPK, String mbrNo, String loginId)  {
		CsoCnsltMemo csoCnsltMemo = new CsoCnsltMemo();
		csoCnsltMemo.setMbrNo(mbrNo);

		try {
			return memoService.selectCsoCnsltMemoList(csoCnsltMemo);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 회원 탈퇴 조건 조회.
	 */
	public MbrExtendResult getMemberForTerminate(SystemPK systemPK, String mbrNo, String loginId)  {

		// step 1. 페이지 인덱스 및 변수 설정
		MbrExtendDTO dto = new MbrExtendDTO();
		dto.setMbrNo(mbrNo);

		// step 2. 회원정보 & 주문건수 & 클레임건수 & 쿠폰건수 & 위시리스트건수 조회
		MbrExtendResult result = memberBaseSelectService.selectMemberForSecession(dto);

		// step 3. 포인트 조회 - 포인트 조회 실패시 전체 프로세스에 영향 없도록 처리.
		if(!StringService.isEmpty(result.getErpCstmrNo())
				&& StringService.equalsIgnoreCase(result.getMbrTpCd(), MemberTpCd.UNITY_MBR.toString()) ){
			try {
				// 멤버쉽포인트
				long reserveAmount = memberBenefitSelectService.getMemberReserveRemainAmount(result.getErpCstmrNo());
				result.setReserve(reserveAmount);
			} catch (Exception e){
				if(log.isInfoEnabled()) log.info("getMemberForTerminate reserve Exception : {}", e.getMessage());
				result.setReserve(-99999);
				result.setEventReserve(-99999);
			}
		}

		//MbrWebpntHistExtend total = memberBenefitSelectService.selectWebPointInfo(mbrNo);
		MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
		mbrWebpntHist.setMbrNo(mbrNo);
		mbrWebpntHist.setMallId(systemPK.getMall());
		MbrWebpntHistExtend total = memberBenefitSelectService.selectWebPointInfo(mbrWebpntHist);
		result.setWebPntReserve(total.getUsefulAmt());

		return result;
	}


	/**
	 * 정지 회원 목록 조회.
	 */
	@Override
	public MemberResultDTO getSuspendMemberList(SystemPK systemPK, MemberBoDTO dto, String loginId) {
		// step 1. 페이지 인덱스 및 변수 설정
		MemberResultDTO resultDTO = new MemberResultDTO();
		dto.setLang(systemPK.getLang());

		// step 2. 목록 건수 조회
		long listCount = memberBaseSelectService.selectSuspendMemberListCount(dto);
		resultDTO.setListCount(listCount);

		// step 3. 목록 조회
		List<MemberBoResult> lists = new ArrayList<MemberBoResult>();
		if(listCount > 0){
			lists = memberBaseSelectService.selectSuspendMemberList(dto);
		}
		resultDTO.setLists(lists);

		if(listCount > 0){
			// step 4. 개인 정보 이용 이력 등록
			String[][] usefCdInfo = { // 구분, 업무, 업무상세
					{UsefSectCd.PSNL_INFO_STTUS.toString(), UsefJobCd.INQIRE.toString(), UsefJobDetail.LIST.toString()}
			};
			String[] target = {};
			memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
					, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
					, target					// 이용대상 : 회원
					, null						// 유입 일련번호
					, dto.getAccessMenuSn()			// 메뉴 일련번호
					, loginId					// 로그인 ID
			);
		}

		return resultDTO;
	}



	/**
	 * 정지 회원 엑셀 다운로드 목록 조회.
	 */
	@Override
	public List<Map<String, Object>> getSuspendMemberListExcel(SystemPK systemPK, MemberBoDTO dto, String loginId)  {
		dto.setLang(systemPK.getLang());
		List<Map<String, Object>> lists = memberBaseSelectService.selectSuspendMemberListExcel(dto);


		if(lists != null && lists.size() > 0){
			// step 2. 개인 정보 이용 이력 등록
			String[][] usefCdInfo = { // 구분, 업무, 업무상세
					{UsefSectCd.PSNL_INFO_STTUS.toString(), UsefJobCd.SAVE.toString(), UsefJobDetail.EXCEL_DOWN.toString()}
			};
			String[] target = null;
			memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
					, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
					, target					// 이용대상 : 회원
					, null						// 유입 일련번호
					, dto.getAccessMenuSn()			// 메뉴 일련번호
					, loginId					// 로그인 ID
			);
		}

		return lists;
	}

	/**
	 * 탈퇴 회원 목록 조회.
	 */
	@Override
	public MemberResultDTO getSecessionMemberList(SystemPK systemPK, MemberBoDTO dto) {
		// step 1. 페이지 인덱스 및 변수 설정
		MemberResultDTO resultDTO = new MemberResultDTO();
		dto.setLang(systemPK.getLang());

		// step 2. 목록 건수 조회
		long listCount = memberBaseSelectService.selectSecessionMemberListCount(dto);
		resultDTO.setListCount(listCount);

		// step 3. 목록 조회
		List<MemberBoResult> lists = new ArrayList<MemberBoResult>();
		if(listCount > 0){
			lists = memberBaseSelectService.selectSecessionMemberList(dto);
		}
		resultDTO.setLists(lists);

		return resultDTO;
	}

	/**
	 * 탈퇴 회원 엑셀 다운로드 목록 조회.
	 */
	@Override
	public List<Map<String, Object>> getSecessionMemberListExcel(SystemPK systemPK, MemberBoDTO dto)  {
		dto.setLang(systemPK.getLang());
		return memberBaseSelectService.selectSecessionMemberListExcel(dto);
	}

	/**
	 * 온라인클럽 회원 목록 조회.
	 */
	@Override
	public PageImpl<MemberBoResult> getOnlineClubMemberList(SystemPK systemPK, MemberBoDTO dto) {
		dto.setLang(systemPK.getLang());
		return memberBaseSelectService.selectOnlineClubMemberList(dto);
	}

	/**
	 * 온라인클럽 회원 엑셀 다운로드 목록 조회.
	 */
	@Override
	public List<Map<String, Object>> getOnlineClubMemberExcel(SystemPK systemPK, MemberBoDTO dto)  {
		dto.setLang(systemPK.getLang());
		return memberBaseSelectService.selectOnlineClubMemberExcel(dto);
	}

	/** 회원 정보 */
	@Override
	public List<MypageMtmFoResult> selectMemberMtmInfo(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO) {
		return memberBaseSelectService.selectMemberMtmInfo(mypageMtmFoDTO);
	}


	/**
	 * 그룹사 조회
	 */
	@Override
	public Page<MemberSysGrpcoResult> selectSysGrpcoList(SystemPK systemPK, MemberSysGrpcoSearchDTO memberSysGrpcoSearchDTO) {
		return memberBaseSelectService.selectSysGrpcoList(memberSysGrpcoSearchDTO);
	}

	/**
	 * 권한 그룹 정보 가져오기
	 *
	 * @param loginId
	 * @return
	 * @
	 */
	@Override
	public SysAuthorGrp selectAdminAuthorGrp(String loginId) {
		return memberBaseSelectService.selectAdminAuthorGrp(loginId);
	}

	@Override
	public String selectErpGrdCd(SystemPK systemPK, String mbrNo)
	{
		return memberBaseSelectService.selectErpGrdcd(mbrNo);
	}

	/**
	 * P포인트회원 엑셀 다운로드 목록 조회.
	 */
	@Override
	public List<Map<String, Object>> getPurpleCoinMemberListExcel(SystemPK systemPK, MemberBoDTO dto, String loginId) {
		// step 1. 회원 엑셀 조회
		dto.setLang(systemPK.getLang());
		List<Map<String, Object>> lists = memberBaseSelectService.selectPurpleCoinMemberListExcel(dto);

		// step 2. 개인 정보 이용 이력 등록
		if(lists != null && lists.size() > 0){
			String[][] usefCdInfo = { // 구분, 업무, 업무상세
					{UsefSectCd.PSNL_INFO_STTUS.toString(), UsefJobCd.SAVE.toString(), UsefJobDetail.EXCEL_DOWN.toString()}
			};
			String[] target = null;
			memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
					, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
					, target					// 이용대상 : 회원
					, null						// 유입 일련번호
					, dto.getAccessMenuSn()			// 메뉴 일련번호
					, loginId					// 로그인 ID
			);
		}
		return lists;
	}

	/**
	 * P포인트회원 목록 조회.
	 */
	@Override
	public MemberResultDTO getPurpleCoinMemberList(SystemPK systemPK, MemberBoDTO dto, String loginId)  {
		// step 1. 페이지 인덱스 및 변수 설정

		MemberResultDTO resultDTO = new MemberResultDTO();
		dto.setLang(systemPK.getLang());

		// step 2. 목록 건수 조회
		long listCount = memberBenefitSelectService.selectPurpleCoinMemberListCount(dto);
		resultDTO.setListCount(listCount);

		// step 3. 목록 조회
		List<PurpleCoinMemberBoResult> lists = new ArrayList<PurpleCoinMemberBoResult>();
		if(listCount > 0){
			lists = memberBenefitSelectService.selectPurpleCoinMemberList(dto);
		}
		resultDTO.setPurpleCoinMbrlists(lists);

		// step 4. 개인 정보 이용 이력 등록
		if(listCount > 0){
			String[][] usefCdInfo = { // 구분, 업무, 업무상세
					{UsefSectCd.PSNL_INFO_STTUS.toString(), UsefJobCd.INQIRE.toString(), UsefJobDetail.LIST.toString()}
			};
			String[] target = {};
			memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
					, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
					, target					// 이용대상 : 회원
					, null						// 유입 일련번호
					, dto.getAccessMenuSn()			// 메뉴 일련번호
					, loginId					// 로그인 ID
			);
		}

		return resultDTO;
	}

	/**
	 * 회원 SMS 발송 내역 건수 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
	@Override
	public long selectSmsListCountForMember(MemberBoDTO dto) {
		return memberBaseSelectService.selectSmsListCountForMember(dto);
	}

	/**
	 * 회원 SMS 발송 내역 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
	@Override
	public List<MemberWebSelectResult> selectSmsListForMember(MemberBoDTO dto) {
		return memberBaseSelectService.selectSmsListForMember(dto);
	}

	/**
	 * 회원 SMS 발송 내역 엑셀 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
	@Override
	public List<Map<String, Object>> selectSmsExcelForMember(MemberBoDTO dto) {
		return memberBaseSelectService.selectSmsExcelForMember(dto);
	}

	/**
	 * 회원 Mail 발송 내역 건수 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
	@Override
	public long selectEMailListCountForMember(MemberBoDTO dto) {
		return memberBaseSelectService.selectEMailListCountForMember(dto);
	}

	/**
	 * 회원 Mail 발송 내역 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
	@Override
	public List<MemberWebSelectResult> selectEMailListForMember(MemberBoDTO dto) {
		return memberBaseSelectService.selectEMailListForMember(dto);
	}

	/**
	 * 회원 Mail 발송 내역 엑셀 조회.
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 5. 4
	 */
	@Override
	public List<Map<String, Object>> selectEMailExcelForMember(MemberBoDTO dto) {
		return memberBaseSelectService.selectEMailExcelForMember(dto);
	}

	@Override
	public boolean selectMysizeExistYn(String mbrNo, String mbrSizeClfcCd){
		MbrSizeClfc mbrSizeClfc = new MbrSizeClfc();
		mbrSizeClfc.setMbrNo(mbrNo);
		mbrSizeClfc.setMbrSizeClfcCd(mbrSizeClfcCd);

		int cnt = memberBaseSelectService.selectMysizeExistYn(mbrSizeClfc);
		return cnt > 0 ? true : false;
	}

	@Override
	public MbrCrtfc selectMemberCertification(String mbrNo) {
		MemberBoResult mcResult = memberCertifySelectService.selectMemberCertification(mbrNo, null, MemberEnum.YES.toString(), MemberEnum.NO.toString());
		
		MbrCrtfc mbrCrtfc = null;
		
		if(mcResult != null) {
			mbrCrtfc = mcResult.getMbrCrtfc();
		}
		
		return mbrCrtfc;
	}
	
	@Override
	public MemberInformationSDO getMemberInformation(SystemPK systemPK, Mbr mbr) throws Exception {
		// ERP 통합회원정보 조회
    	return memberInterfaceService.getMemberInformation(systemPK, mbr);
	}
	
	@Override
	public SysInflow selectMbrSysInflow(SysInflow sysInflow) {
		return memberBaseSelectService.selectMbrSysInflow(sysInflow);
	}
	
	@Override
	public int updateLoginFailCount(Mbr mbr) {
		return memberBaseCommandService.updateLoginFailCount(mbr);
	}
	
	@Override
    public String processReleaseDrmncyMember(HttpServletRequest request, SystemPK systemPK, MemberInformationSDO memberInformationSDO) {
		String rtn = "00";	// 성공
		
		try {
			String cid = "";
			if(StringService.isNotEmpty(memberInformationSDO.getCid())) {
				cid = memberInformationSDO.getCid();
			}
			else {
				throw new Exception("CID is null");
			}
			List<Mbr> mbrList = memberBaseSelectService.selectMemberByErpCstmrNo(cid);
			
			if(mbrList != null && !mbrList.isEmpty()) {
				for(Mbr tmpMbr : mbrList) {
					// 휴면이면 UPDATE 처리. 
					if(MemberEnum.MemberStatCd.DRMNCY.toString().equals(tmpMbr.getMbrStatCd())) {
						memberBaseCommandService.updateOnlineMemberByErp(request, systemPK, memberInformationSDO, tmpMbr);
						break;	// 최근 1 건만 처리.
					}
				}
			}
		}
		catch(Exception e) {
			rtn = "99";	// 실패
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			log.error("processReleaseDrmncyMember : {}", this.getClass().getName() + " : " + error.toString());
		}
		
		return rtn;
    }
	
	@Override
    public Map<String, String> sendCertSms(SystemPK systemPK, String birthday, String gender, String mbrNm, String mobileCo, String mobileNumber){
		Map<String, String> ret = new HashMap<>();
		try {
			ret = memberInterfaceService.sendCertSms(systemPK, birthday, gender, mbrNm, mobileCo, mobileNumber);
		}catch(Exception e) {			
			log.error("sendCertSms : {}", this.getClass().getName() + " : " + e.getMessage());
		}
		
		return ret;
	}
	
	@Override
    public Map<String, String> sendCertSmsCheck(SystemPK systemPK, String REQ_SEQ, String RES_SEQ, String sAuthNo, String birthday, String gender, String mbrNm, String mobileCo, String mobileNumber, String frgnrYn){
		Map<String, String> ret = new HashMap<>();
		try {
			ret = memberInterfaceService.sendCertSmsCheck(systemPK, REQ_SEQ, RES_SEQ, sAuthNo, birthday, gender, mbrNm, mobileCo, mobileNumber, frgnrYn);
		}catch(Exception e) {			
			log.error("sendCertSmsCheck : {}", this.getClass().getName() + " : " + e.getMessage());
		}
		
		return ret;
	}
	
	@Override
	public String getRequestUrlForAddMember(HttpServletRequest request, SystemPK systemPK) {
		return memberBaseSelectService.getRequestUrlForAddMember(request, systemPK);
	}
	
	@Override
	public Model getMccStplat(Model model, SystemPK pk, String opt) throws Exception {
		if("1".equals(opt)) {
			// 개인정보 이용 동의 SK
			SysStplat psnlInfoUsefArgSK = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.PSNL_INFO_USEF_ARG_SK.toString());
			model.addAttribute("mccStplat1", psnlInfoUsefArgSK);

			// 개인정보 이용 동의 KT
			SysStplat psnlInfoUsefArgKT = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.PSNL_INFO_USEF_ARG_KT.toString());
			model.addAttribute("mccStplat2", psnlInfoUsefArgKT);
			
			// 개인정보 이용 동의 LG
			SysStplat psnlInfoUsefArgLG = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.PSNL_INFO_USEF_ARG_LG.toString());
			model.addAttribute("mccStplat3", psnlInfoUsefArgLG);
		}
		else if("2".equals(opt)) {
			// 고유식별정보 처리 동의 SK
			SysStplat orgIdntfcInfoPrcsAgrSK = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.ORG_IDNTFC_INFO_PRCS_AGR_SK.toString());
			model.addAttribute("mccStplat1", orgIdntfcInfoPrcsAgrSK);

			// 고유식별정보 처리 동의 KT
			SysStplat orgIdntfcInfoPrcsAgrKT = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.ORG_IDNTFC_INFO_PRCS_AGR_KT.toString());
			model.addAttribute("mccStplat2", orgIdntfcInfoPrcsAgrKT);
			
			// 고유식별정보 처리 동의 LG
			SysStplat orgIdntfcInfoPrcsAgrLG = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.ORG_IDNTFC_INFO_PRCS_AGR_LG.toString());
			model.addAttribute("mccStplat3", orgIdntfcInfoPrcsAgrLG);
		}
		else if("3".equals(opt)) {
			// 본인인증 서비스 이용약관 SK
			SysStplat slfCrtfcSvcUsefStplatSK = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.SLF_CRTFC_SVC_USEF_STPLAT_SK.toString());
			model.addAttribute("mccStplat1", slfCrtfcSvcUsefStplatSK);

			// 본인인증 서비스 이용약관 KT
			SysStplat slfCrtfcSvcUsefStplatKT = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.SLF_CRTFC_SVC_USEF_STPLAT_KT.toString());
			model.addAttribute("mccStplat2", slfCrtfcSvcUsefStplatKT);
			
			// 본인인증 서비스 이용약관 LG
			SysStplat slfCrtfcSvcUsefStplatLG = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.SLF_CRTFC_SVC_USEF_STPLAT_LG.toString());
			model.addAttribute("mccStplat3", slfCrtfcSvcUsefStplatLG);
		}
		else if("4".equals(opt)) {
			// 통신사 이용약관 동의 SK
			SysStplat mccUsefStplatAgrSK = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.MCC_USEF_STPLAT_AGR_SK.toString());
			model.addAttribute("mccStplat1", mccUsefStplatAgrSK);

			// 통신사 이용약관 동의 KT
			SysStplat mccUsefStplatAgrKT = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.MCC_USEF_STPLAT_AGR_KT.toString());
			model.addAttribute("mccStplat2", mccUsefStplatAgrKT);
			
			// 통신사 이용약관 동의 LG
			SysStplat mccUsefStplatAgrLG = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.MCC_USEF_STPLAT_AGR_LG.toString());
			model.addAttribute("mccStplat3", mccUsefStplatAgrLG);
		}
		else if("5".equals(opt)) {
			// 개인정보 제3자 제공동의 KT
			SysStplat psnlInfoThprOfferAgrKT = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.PSNL_INFO_THPR_OFFER_AGR_KT.toString());
			model.addAttribute("mccStplat2", psnlInfoThprOfferAgrKT);
			
			// 개인정보 제3자 제공동의 LG
			SysStplat psnlInfoThprOfferAgrLG = displaySelectComponent.selectSysStplatCont(pk, MemberEnum.StplatCd.PSNL_INFO_THPR_OFFER_AGR_LG.toString());
			model.addAttribute("mccStplat3", psnlInfoThprOfferAgrLG);
		}
		
		return model;
	}
}
