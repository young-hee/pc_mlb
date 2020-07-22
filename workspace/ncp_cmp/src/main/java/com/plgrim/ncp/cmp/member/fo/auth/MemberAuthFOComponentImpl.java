package com.plgrim.ncp.cmp.member.fo.auth;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrLoginLog;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrStplatAgr;
import com.plgrim.ncp.base.enums.MemberBenefitEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.MbrPsnlInfoUdtSectCd;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.MemberModLcSectCd;
import com.plgrim.ncp.biz.member.data.Ipin;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.Pcc;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MemberBenefitApiResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitResult;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.biz.member.service.MemberBaseCommandService;
import com.plgrim.ncp.biz.member.service.MemberBaseSelectService;
import com.plgrim.ncp.biz.member.service.MemberCertifyCommandService;
import com.plgrim.ncp.biz.member.service.MemberCertifySelectService;
import com.plgrim.ncp.biz.member.service.MemberInterfaceService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoCommandService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoSelectService;
import com.plgrim.ncp.cmp.member.fo.MemberAuthFOComponent;
import com.plgrim.ncp.cmp.member.fo.MemberBenefitFOComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.cart.CartCommandComponent;
import com.plgrim.ncp.commons.CommonSelectComponent;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.LocaleService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.SecurityParam;
import com.plgrim.ncp.framework.utils.StringUtils;
import com.plgrim.ncp.interfaces.member.data.MemberInformationSDO;
import com.plgrim.ncp.interfaces.member.data.MemberLoginInfoSDO;
import com.plgrim.ncp.interfaces.member.data.MembershipSDO;

import lombok.extern.slf4j.Slf4j;

@Transactional(value = "transactionManager")
@Component
@Slf4j
public class MemberAuthFOComponentImpl extends AbstractComponent implements MemberAuthFOComponent {
    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    /**
     * The messageSourceAccessor
     */
    @Autowired
    MessageSourceAccessor messageSourceCmpAccessor;

    @Autowired
    MemberCertifySelectService memberCertifySelectService;

    @Autowired
    MemberPersonalInfoSelectService memberPersonalInfoSelectService;

    @Autowired
    MemberPersonalInfoCommandService memberPersonalInfoCommandService;

    @Autowired
    MemberBaseCommandService memberBaseCommandService;

    @Autowired
    MemberBaseSelectService memberBaseSelectService;

    @Autowired
    MemberCertifyCommandService memberCertifyCommandService;

    @Autowired
    MemberInterfaceService memberInterfaceService;
    
    @Autowired
    CommonSelectComponent commonSelectComponent;

    @Autowired
	private CartCommandComponent cartCommandComponent;
    
	@Autowired
	private MemberBenefitFOComponent memberBenefitFOComponent;

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
    public boolean insertMemberCrtfc(SystemPK systemPK, MemberFoDTO dto) {
        // step 1. 개인정보수정 등록
        MbrPsnlInfoModHist mpim = memberPersonalInfoCommandService.setMbrPsnlInfoModHist(dto.getMbr().getMbrNo(), "", dto.getMbr().getMbrNo(), true);
        String[] codeArr = {
                MbrPsnlInfoUdtSectCd.MBR_EMAIL.toString()                                // 이메일
                , MbrPsnlInfoUdtSectCd.UNITY_MBR_CRTFC_SECT_CD.toString()                // 통합 회원 인증 구분 코드
        };
        mpim.setModLcSectCd(MemberModLcSectCd.ONLNE_MALL.toString()); // 수정이력 수정몰
        memberPersonalInfoCommandService.insertPersonalInfoForMbr(dto.getMbr(), mpim, codeArr, false);

        // step 2. mbr 회원
        memberBaseCommandService.updateFoMember(dto);

        // step3. mbr stplat 회원 약관 동의
        List<String> stplatCdList = dto.getStplatCd();
        List<String> stplatIemAgrYnList = dto.getStplatIemAgrYn();
        if (stplatCdList != null && stplatCdList.size() > 0) {
            for (int i = 0; i < stplatCdList.size(); i++) {
                MbrStplatAgr mbrStplatAgr = new MbrStplatAgr();
                mbrStplatAgr.setMbrNo(dto.getMbr().getMbrNo());
                mbrStplatAgr.setStplatCd(stplatCdList.get(i));
                mbrStplatAgr.setStplatIemAgrYn(stplatIemAgrYnList.get(i));
                mbrStplatAgr.setRegtrId(dto.getMbr().getMbrNo());
                mbrStplatAgr.setUdterId(dto.getMbr().getMbrNo());

                memberPersonalInfoCommandService.insertMbrStplatAgr(mbrStplatAgr);
            }
        }

        //step4. mbr crtfc 회원 인증
        memberCertifyCommandService.inesrtMemberCrtfc(dto);
        
        return false;
    }


    @Override
    public void updateAuthentication(SecurityUserDetail userDetail) {
        SecurityUserDetail beforeUserDetail = (SecurityUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetail.getMbr() != null) {
            beforeUserDetail.setMbr(userDetail.getMbr());
        }

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority(SecurityParam.DEFAULT_ROLE.toString()));
        Authentication authenticatedUser = new UsernamePasswordAuthenticationToken(beforeUserDetail, null, grantedAuths);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }

    /**
     * 휴대전화 인증 세팅
     */
    @Override
    public Pcc setPCC(String reqPccNum, String day, String srv, String device) {
        return memberCertifyCommandService.setPCC(reqPccNum, day, srv, device);
    }

    /**
     * 휴대전화 인증 완료 값 세팅
     */
    @Override
    public Pcc getPCC(String reqPccNum, String retInfo, boolean successFlag) {
        return memberCertifyCommandService.getPCC(reqPccNum, retInfo, successFlag);
    }

    /**
     * IPIN 인증 세팅
     */
    @Override
    public Ipin setIPIN(String reqIpinNum, String srv) {
        return memberCertifyCommandService.setIPIN(reqIpinNum, srv);
    }

    /**
     * IPIN 인증 완료 값 세팅
     */
    @Override
    public Ipin getIPIN(String reqIpinNum, String retInfo) {
        return memberCertifyCommandService.getIPIN(reqIpinNum, retInfo);
    }

    /**
     * 로그인시 멤버 인증 절차
     */
    @Override
    public void mbrAuthentication() {
        HttpServletRequest request = ContextService.getCurrentRequest();

        String userId = (String) request.getAttribute("userId");

        MemberFoDTO dto = new MemberFoDTO();
        SystemPK systemPK;
        try {
            systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        dto.setMallId(systemPK.getMall());

        Mbr mbr = new Mbr();
        mbr.setMbrId(userId);
        dto.setMbr(mbr);
        dto.setMallId(systemPK.getMall());
        SecurityUserDetail userDetail = null;
        try {
            userDetail = memberBaseSelectService.selectSecuredMember(dto);

            log.info("userDetail : {}", userDetail);
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority(SecurityParam.DEFAULT_ROLE.toString()));
            userDetail.setGrantedAuths(grantedAuths);
            Authentication authenticatedUser = new UsernamePasswordAuthenticationToken(userDetail, null, grantedAuths);
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

        } catch (Exception e) {
            log.error("자동 로그인 에러 :::::::::::::::::::::::::::::::::::::::::::::><>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + e.getMessage());
        }

    }

    /**
     * 회원 가입 후에 로그인 처리하고 호출되는 함수
     * - 로그인 로그, ERP 로그인 성공 전송
     * - 장바구니 병합, 로그인 혜택 처리
     */
    @Override
    public void processAfterAddAndLogin(HttpServletRequest request) {
		try{
			SystemPK systemPK = getIdGenService().getAutoGeneratorSystemPK(request);
			
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			
			if(ContextService.hasRole()){
				
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

				mbrLoginLog.setLangCd(systemPK.getLang());
				mbrLoginLog.setDvcCd(systemPK.getDevice());
				mbrLoginLog.setMallId(systemPK.getMall());
				
				log.info("locale {} ", locale);

				log.info("mbrLoginLog {}", mbrLoginLog);
				memberBaseCommandService.addMbrLoginLog(systemPK, mbrLoginLog);
				/** 로그인 히스토리 추가 종료 **/
				
				try {
					/** ERP로 로그인 성공 전송 시작 **/
					String logDate = memberBaseSelectService.selectMbrLoginLog(mbrLoginLog);
					MemberLoginInfoSDO memberLoginInfoSDO = new MemberLoginInfoSDO();
					memberLoginInfoSDO.setCid(userDetail.getMbr().getErpCstmrNo());
					memberLoginInfoSDO.setId(userDetail.getMbr().getMbrId());
					memberLoginInfoSDO.setIp(ip);
					memberLoginInfoSDO.setSleepReleaseYn("N");
					memberLoginInfoSDO.setLogDate(logDate);
					
					String requestUrl = memberBaseSelectService.getRequestUrlForAddMember(request, systemPK);
					
					memberLoginInfoSDO.setSite(requestUrl);
					
					memberInterfaceService.sendMemberLatestLogInInfo(systemPK, memberLoginInfoSDO);
					/** ERP로 로그인 성공 전송 종료 **/
				}
				catch(Exception e) {
					/** ERP로 로그인 성공 전송은 실패하여도 로그만 찍고 진행 **/
					StringWriter error = new StringWriter();
					e.printStackTrace(new PrintWriter(error));
					log.error("> ERP 로그인 성공 전송 실패 Failure message : {}", this.getClass().getName() + " : " + error.toString());
				}
				
				// 장바구니 병합
				cartCommandComponent.mergeCart(systemPK);

				// 혜택API 로그인 - 로그인
				MemberBenefitApiResult apiResult = memberBenefitFOComponent.callMemberBenefit(systemPK, MemberBenefitEnum.BnefSectCd.LOGIN, null);
				
				log.info("MEMBER_BENEFIT_API : ResultCd({}),ResultMsg({})", apiResult.getResultCd(), apiResult.getResultMsg());

				if(apiResult != null && apiResult.getMemberBenefitResultList() != null) {
				    List<MemberBenefitResult> resultList = apiResult.getMemberBenefitResultList();

				    for(int i= 0; i < resultList.size(); i++) {
				        MemberBenefitResult memberBenefitResult = resultList.get(i);
				        log.info("MEMBER_BENEFIT_API_DETAIL : ResultCd({}),ResultMsg({})", memberBenefitResult.getResultCd(), memberBenefitResult.getResultMsg());
				    }
				}
			}
		}
		catch(Exception e) {
			log.error("",e);
		}
    }
    
    /*di값으로 가입 되어 있는지 체크*/
    @Override
    public MemberFoResult checkMemberCertify(String di, String joinMallId, String maskingYn) {
        return memberCertifySelectService.checkMemberCertify(di, joinMallId, maskingYn);
    }

    @Override
    public MembershipSDO checkMemberJoinErp(SystemPK systemPK, MbrCrtfc mbrCrtfc) {
    	MembershipSDO membershipSDO = null;
    	try {
    		membershipSDO = memberInterfaceService.getMembershipYn(systemPK, mbrCrtfc);
		} catch (Exception e) {
			log.warn(">> checkMemberJoinErp ", e);
		}
    	
    	return membershipSDO;
    }

    @Override
	public MemberFoResult selectSecessionMbrInfo(Mbr mbr) {
		return memberBaseSelectService.selectSecessionMbrInfo(mbr);
	}
    
    @Override
    public void setModelForCertificationAfter(Model model, SystemPK pk, String srv, String di, String ci) {
		if("newjoin".equals(srv) || "find".equals(srv)) {
			/* 멤버십 가입 확인*/
			MemberFoResult checkCertMbr = null;
			
			if(pk.getMall() != null && pk.getMall().equals("MBM")){
				//MLB
				checkCertMbr = this.checkMemberCertify(di, null, "N");
			}else{
				checkCertMbr = this.checkMemberCertify(di, null, "Y");
			}

			if(checkCertMbr != null){
				model.addAttribute("checkCertMbr", JsonService.marshalling(checkCertMbr));
			
				if(ContextService.hasRole()){
					SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
					Mbr currMbr = userDetail.getMbr();
					if(StringUtils.equals(currMbr.getErpCstmrNo(), checkCertMbr.getMbr().getErpCstmrNo())){
						model.addAttribute("checkSameMbrYn", "Y");
					}else{
						model.addAttribute("checkSameMbrYn", "N");
					}	
				}else{
					model.addAttribute("checkSameMbrYn", "Y");
				}
				
			}
			else {
				// ERP 인터페이스 조회하여 회원가입이 가능한지 조회.
				MbrCrtfc mbrCrtfc = new MbrCrtfc();
				mbrCrtfc.setRlnmCrtfcDi(di);
				mbrCrtfc.setRlnmCrtfcCi(ci);
				
				MembershipSDO membershipSDO = this.checkMemberJoinErp(pk, mbrCrtfc);
				if(membershipSDO == null || membershipSDO.getResult() == null || "".equals(membershipSDO.getResult())) {
					model.addAttribute("joinPossibility", JsonService.marshalling(false));
					model.addAttribute("memberFailERPIF", JsonService.marshalling(true));
				}
				else {
					if("Y".equals(membershipSDO.getResult())) {
						model.addAttribute("joinPossibility", JsonService.marshalling(true));
					}
					else {
						if("find".equals(srv) && !"C".equals(membershipSDO.getPossibility())) {
							model.addAttribute("joinPossibility", JsonService.marshalling(true));
						}
						if("C".equals(membershipSDO.getPossibility())) {
							model.addAttribute("joinPossibility", JsonService.marshalling(false));
							checkCertMbr = new MemberFoResult();
							Mbr mbr = new Mbr();
							mbr.setErpCstmrNo(membershipSDO.getCid());
							try {
								MemberInformationSDO memberInformationSDO = memberInterfaceService.getMemberInformation(pk, mbr);
								mbr.setMbrId(memberInformationSDO.getId().substring(0, memberInformationSDO.getId().length()-2) + "**");
								mbr.setMbrNm(memberInformationSDO.getName());
								memberBaseCommandService.setMbrStatCd(memberInformationSDO.getStatus(), mbr);
								// 가입 일시
								String joinDate = memberInformationSDO.getJoinDate();
								Date joinDt = null;
								if(joinDate != null) {
									try {
										joinDt = new Date();
										joinDt.setTime(Long.parseLong(joinDate));
									}
									catch(Exception e) {
										joinDt = null;
									}
								}
								checkCertMbr.setJoinDtStr(DateService.parseString(joinDt, "yyyy-MM-dd"));
								checkCertMbr.setMbr(mbr);
								model.addAttribute("checkCertMbr", JsonService.marshalling(checkCertMbr));
							} catch (Exception e) {
								log.warn(">> setModelForCertificationAfter.getMemberInformation ", e);
								model.addAttribute("memberFailERPIF", JsonService.marshalling(true));
							}
						}
						else {
							model.addAttribute("joinPossibility", JsonService.marshalling(false));
						}
						model.addAttribute("possibilityCode", membershipSDO.getPossibility());
						
						if(checkCertMbr == null) {
							Mbr mbrForSecessionInfo = new Mbr();
							mbrForSecessionInfo.setErpCstmrNo(membershipSDO.getCid());
							checkCertMbr = this.selectSecessionMbrInfo(mbrForSecessionInfo);
														
							model.addAttribute("checkCertMbr", JsonService.marshalling(checkCertMbr));
						}
					}
				}
			}
		}
	}
    
    @Override
    public List<Mbr> selectMemberByErpCstmrNo(String erpCstmrNo) {
    	return memberBaseSelectService.selectMemberByErpCstmrNo(erpCstmrNo);
    }
    
}
