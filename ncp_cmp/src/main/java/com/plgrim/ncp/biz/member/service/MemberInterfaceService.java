package com.plgrim.ncp.biz.member.service;


import static org.junit.Assert.assertNotNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.biz.member.data.MemberChildDTO;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.exception.MemberFailERPIFException;
import com.plgrim.ncp.commons.service.MailHtmlReaderService;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.interfaces.abstracts.InterfaceBaseSDO;
import com.plgrim.ncp.interfaces.email.adapter.EmailAdapter;
import com.plgrim.ncp.interfaces.email.data.EmailHtmlSDO;
import com.plgrim.ncp.interfaces.member.adapter.InterfaceAdapter;
import com.plgrim.ncp.interfaces.member.data.DropMemberInformationSDO;
import com.plgrim.ncp.interfaces.member.data.FamilyInformationSDO;
import com.plgrim.ncp.interfaces.member.data.MemberEmpInformationListSDO;
import com.plgrim.ncp.interfaces.member.data.MemberInformationSDO;
import com.plgrim.ncp.interfaces.member.data.MemberLoginInfoSDO;
import com.plgrim.ncp.interfaces.member.data.MemberMileageInfoSDO;
import com.plgrim.ncp.interfaces.member.data.MembershipCardSDO;
import com.plgrim.ncp.interfaces.member.data.MembershipSDO;
import com.plgrim.ncp.interfaces.member.data.NaverAccessTokenSDO;
import com.plgrim.ncp.interfaces.member.data.SendCertSmsSDO;
import com.plgrim.ncp.interfaces.member.data.SendMemberInformationSDO;
import com.plgrim.ncp.interfaces.mpush.adapter.MPushAdapter;
import com.plgrim.ncp.interfaces.mpush.data.MPushAlimTalkSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import com.plgrim.ncp.interfaces.util.InterfaceConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberInterfaceService extends AbstractService {

    @Autowired
    InterfaceAdapter interfaceAdapter;

    @Autowired
    private InterfaceApiCommon interfaceApiCommon;
    
    @Autowired
    private MemberBaseSelectService memberBaseSelectService;
    
	@Autowired
	private EmailAdapter emailAdapter;
	
	@Autowired 
	private MailHtmlReaderService mailHtmlReaderService;
	
	@Autowired
	private MPushAdapter mPushAdapter;
	
    
	/**
	 * AdapterHeader 값 설정
	 * 
	 * @return
	 */
	private AdapterHeader setAdapterHeader(SystemPK systemPK) {
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId(systemPK.getMall());
		adapterHeader.setLangCd(systemPK.getLang());
		adapterHeader.setDvcCd(systemPK.getDevice());
		return adapterHeader;
	}
    
	private AdapterHeader setAdapterHeader(SystemPK systemPK, String mallId, String langCd, String dvcCd) {
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId(mallId == null ? systemPK.getMall() : mallId);
		adapterHeader.setLangCd(langCd == null ? systemPK.getLang() : langCd);
		adapterHeader.setDvcCd(dvcCd == null ? systemPK.getDevice() : dvcCd);
		return adapterHeader;
	}
	
    /**
     * 통합회원정보 조회
     * 
     * @param systemPK
     * @param mbr
     * @return MemberInformationSDO
     * @throws Exception
     */
    public MemberInformationSDO getMemberInformation(SystemPK systemPK, Mbr mbr) throws Exception {

        assertNotNull(interfaceAdapter);

        MemberInformationSDO memberInformationSDO = new MemberInformationSDO();
        
        try {
            log.info(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());

            memberInformationSDO.setCallerId(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());
            memberInformationSDO.setCid(mbr.getErpCstmrNo());
            memberInformationSDO.setId(mbr.getMbrId());

            AdapterHeader adapterHeader = setAdapterHeader(systemPK);
            
            String inputJson = JsonService.marshalling(memberInformationSDO);
            String url = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.MEMBER_GET_MEMBER_INFORMATION;
            String result = interfaceAdapter.sendInterface(inputJson, adapterHeader, url);

            memberInformationSDO = JsonService.unmarshalling(result, MemberInformationSDO.class);
            
            if(memberInformationSDO == null || memberInformationSDO.getResponseCd() == null
            		|| "500".equals(memberInformationSDO.getResponseCd())) {
            	String[] str = {"ERP info is null or Fail."};
                throw new MemberFailERPIFException(str);
            }
            else {
            	// 이메일 공백 제거
            	if(StringService.isNotEmpty(memberInformationSDO.getEmail())) {
            		memberInformationSDO.setEmail( memberInformationSDO.getEmail().trim().replaceAll(" ", "") );
            	}
            	// 집전화번호 공백 제거
            	if(StringService.isNotEmpty(memberInformationSDO.getPhoneHome())) {
            		memberInformationSDO.setPhoneHome( memberInformationSDO.getPhoneHome().trim().replaceAll(" ", "") );
            	}
            	// 휴대전화번호 공백 제거
            	if(StringService.isNotEmpty(memberInformationSDO.getPhoneMobile())) {
            		memberInformationSDO.setPhoneMobile( memberInformationSDO.getPhoneMobile().trim().replaceAll(" ", "") );
            	}
            }
            
        } catch (Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            String[] str = {"ERP info is null or Fail."};
            throw new MemberFailERPIFException(str);
        }
        
        return memberInformationSDO;
    }

    /**
     * 회원정보 생성/수정
     * 
     * @param systemPK
     * @param mbr
     * @return SendMemberInformationSDO
     * @throws Exception
     */
    public SendMemberInformationSDO sendMemberInformation(SystemPK systemPK, MemberFoDTO dto, String apiType) throws Exception {

        assertNotNull(interfaceAdapter);

        Mbr mbr = dto.getMbr();
        MbrCrtfc mbrCrtfc = dto.getMbrCrtfc();
        SendMemberInformationSDO sendMemberInformationSDO = new SendMemberInformationSDO();
        
        try {
            log.info(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());

            sendMemberInformationSDO.setCallerId(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());
            
            // 패스워드 수정
            if("P".equals(apiType)) {
            	sendMemberInformationSDO.setCid(mbr.getErpCstmrNo());
            	sendMemberInformationSDO.setApiType(apiType);
            	sendMemberInformationSDO.setPasswd(mbr.getMbrErpPw());
            }
            // 회원정보 생성/수정
            else {
            	sendMemberInformationSDO.setCid(mbr.getErpCstmrNo());
                sendMemberInformationSDO.setApiType(apiType);
                sendMemberInformationSDO.setId(mbr.getMbrId());
                sendMemberInformationSDO.setName(mbr.getMbrNm());
                sendMemberInformationSDO.setPasswd(mbr.getMbrErpPw());
                sendMemberInformationSDO.setStatus(this.setStatus(mbr));
                sendMemberInformationSDO.setGender(this.setGender(mbr));
                sendMemberInformationSDO.setBirthDay(this.setBirthDay(mbr));
                sendMemberInformationSDO.setBirthCal(this.setBirthCal(mbr));
                sendMemberInformationSDO.setEmail(mbr.getMbrEmail());
                sendMemberInformationSDO.setZipcode(mbr.getMbrPostNo());
                sendMemberInformationSDO.setAddress1(mbr.getMbrBaseAddr());
                sendMemberInformationSDO.setAddress2(mbr.getMbrDetailAddr());
                String phoneHome = mbr.getTelAreaNo() + "-" + mbr.getTelTlofNo() + "-" + mbr.getTelTlofWthnNo();
                sendMemberInformationSDO.setPhoneHome(phoneHome);
                String phoneMobile = mbr.getMobilAreaNo() + "-" + mbr.getMobilTlofNo() + "-" + mbr.getMobilTlofWthnNo();
                sendMemberInformationSDO.setPhoneMobile(phoneMobile);
                sendMemberInformationSDO.setRecvEmail(mbr.getEmailRecptnAgrYn());
                sendMemberInformationSDO.setRecvSms(mbr.getSmsRecptnAgrYn());
                sendMemberInformationSDO.setRecvDate(this.setRecvDate(mbr));
                sendMemberInformationSDO.setJoinDate(this.setJoinDate(mbr));
                sendMemberInformationSDO.setLastLogin(null);
                sendMemberInformationSDO.setLastLoginIp(mbr.getJoinMbrIp());
                sendMemberInformationSDO.setBuyLast(null);
                sendMemberInformationSDO.setIpinCi(mbrCrtfc.getRlnmCrtfcCi());
                sendMemberInformationSDO.setIpinDi(mbrCrtfc.getRlnmCrtfcDi());
                sendMemberInformationSDO.setAuthType(this.setAuthType(mbr));
                sendMemberInformationSDO.setAuthRealname(this.setAuthRealname(mbr));
                sendMemberInformationSDO.setFamilyList(this.setFamilyList(dto));
                sendMemberInformationSDO.setJoinDvcCd(mbr.getJoinDvcCd());
                sendMemberInformationSDO.setJoinLangCd(mbr.getJoinLangCd());
                sendMemberInformationSDO.setOsCd(mbr.getOsCd());
                sendMemberInformationSDO.setMobileAppSectCd(mbr.getMobileAppSectCd());
                sendMemberInformationSDO.setInflow(this.setInflow(mbr));
                
                ////자녀정보
//                if(dto.getChildrenList() != null && !dto.getChildrenList().isEmpty()){
//                	List<FamilyInformationSDO> li = new ArrayList<>();
//                	
//                	for(MemberChildDTO m : dto.getChildrenList()){
//                		FamilyInformationSDO ss = new FamilyInformationSDO();
//                		ss.setFamilyName(m.getChildrenName());
//                		ss.setFamilyBirth(m.getChildrenBirthYear()+m.getChildrenBirthMonth()+m.getChildrenBirthDay());
//                		
//                		li.add(ss);
//                	}
//                	
//                	sendMemberInformationSDO.setFamilyList(li);
//                }
            }
            
            AdapterHeader adapterHeader = setAdapterHeader(systemPK);
            
            String inputJson = JsonService.marshalling(sendMemberInformationSDO);
            String url = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.MEMBER_SEND_MEMBER_INFORMATION;
            String result = interfaceAdapter.sendInterface(inputJson, adapterHeader, url);

            sendMemberInformationSDO = JsonService.unmarshalling(result, SendMemberInformationSDO.class);
            
            if(sendMemberInformationSDO == null || sendMemberInformationSDO.getResultCd() == null
            		|| !"00".equals(sendMemberInformationSDO.getResultCd())
            		|| !"200".equals(sendMemberInformationSDO.getResponseCd())
            		) {
            	String[] str = {"ERP info is null or Fail."};
                throw new MemberFailERPIFException(str);
            }
            
        } catch (Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            String[] str = {"ERP info is null or Fail."};
            throw new MemberFailERPIFException(str);
        }
        
        return sendMemberInformationSDO;
    }
    
    /**
     * 통합회원가입여부 확인
     * 
     * @param systemPK
     * @param mbrCrtfc
     * @return MembershipSDO
     * @throws Exception
     */
    public MembershipSDO getMembershipYn(SystemPK systemPK, MbrCrtfc mbrCrtfc) throws Exception {

        assertNotNull(interfaceAdapter);

        MembershipSDO membershipSDO = new MembershipSDO();
        
        try {
            log.info(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());

            membershipSDO.setCallerId(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());
            membershipSDO.setCi(mbrCrtfc.getRlnmCrtfcCi());
            membershipSDO.setDi(mbrCrtfc.getRlnmCrtfcDi());

            AdapterHeader adapterHeader = setAdapterHeader(systemPK);
            
            String inputJson = JsonService.marshalling(membershipSDO);
            String url = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.MEMBER_GET_MEMBERSHIP_YN;
            String result = interfaceAdapter.sendInterface(inputJson, adapterHeader, url);

            membershipSDO = JsonService.unmarshalling(result, MembershipSDO.class);
            
            if(membershipSDO == null || membershipSDO.getResult() == null
            		|| "".equals(membershipSDO.getResult())) {
            	String[] str = {"ERP info is null or Fail."};
            	throw new MemberFailERPIFException(str);
            }
        } catch (Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            String[] str = {"ERP info is null or Fail."};
            throw new MemberFailERPIFException(str);
        }
        
        return membershipSDO;
    }
    
    /**
     * ERP 회원탈퇴
     * 
     * @param systemPK
     * @param dropMemberInformationSDO
     * @return DropMemberInformationSDO
     * @throws Exception
     */
    public DropMemberInformationSDO sendDropMemberInformation(SystemPK systemPK, DropMemberInformationSDO dropMemberInformationSDO) throws Exception {

        assertNotNull(interfaceAdapter);

        try {
            log.info(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());

            dropMemberInformationSDO.setCallerId(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());

            AdapterHeader adapterHeader = setAdapterHeader(systemPK);
            
            String inputJson = JsonService.marshalling(dropMemberInformationSDO);
            String url = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.MEMBER_SEND_DROP_MEMBER;
            String result = interfaceAdapter.sendInterface(inputJson, adapterHeader, url);

            dropMemberInformationSDO = JsonService.unmarshalling(result, DropMemberInformationSDO.class);
            
            if(dropMemberInformationSDO == null || dropMemberInformationSDO.getResultCd() == null
            		|| !"00".equals(dropMemberInformationSDO.getResultCd())
            		|| !"200".equals(dropMemberInformationSDO.getResponseCd())
            		) {
            	String[] str = {"ERP info is null or Fail."};
            	throw new MemberFailERPIFException(str);
            }
        } catch (Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            String[] str = {"ERP info is null or Fail."};
            throw new MemberFailERPIFException(str);
        }
        
        return dropMemberInformationSDO;
    }
    
    /**
     * 회원 로그인등 활동이력
     * 
     * @param systemPK
     * @param memberLoginInfoSDO
     * @return MemberLoginInfoSDO
     * @throws Exception
     */
    public MemberLoginInfoSDO sendMemberLatestLogInInfo(SystemPK systemPK, MemberLoginInfoSDO memberLoginInfoSDO) throws Exception {

        assertNotNull(interfaceAdapter);

        try {
            log.info(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());

            memberLoginInfoSDO.setCallerId(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());

            AdapterHeader adapterHeader = setAdapterHeader(systemPK);
            
            String inputJson = JsonService.marshalling(memberLoginInfoSDO);
            String url = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.MEMBER_SEND_MEMBER_LASTEST_LOGIN_INFO;
            String result = interfaceAdapter.sendInterface(inputJson, adapterHeader, url);

            memberLoginInfoSDO = JsonService.unmarshalling(result, MemberLoginInfoSDO.class);
            
        } catch (Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            String[] str = {"ERP info is null or Fail."};
            throw new MemberFailERPIFException(str);
        }
        
        return memberLoginInfoSDO;
    }
    
    /**
     * 멤버십 카드 등록
     * 
     * @param systemPK
     * @param membershipCardSDO
     * @return MembershipCardSDO
     * @throws Exception
     */
    public MembershipCardSDO addMembershipCard(SystemPK systemPK, MembershipCardSDO membershipCardSDO) throws Exception {

        assertNotNull(interfaceAdapter);

        try {
            log.info(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());

            membershipCardSDO.setCallerId(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());

            AdapterHeader adapterHeader = setAdapterHeader(systemPK);
            
            String inputJson = JsonService.marshalling(membershipCardSDO);
            String url = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.MEMBER_ADD_MEMBERSHIP_CARD;
            String result = interfaceAdapter.sendInterface(inputJson, adapterHeader, url);

            membershipCardSDO = JsonService.unmarshalling(result, MembershipCardSDO.class);
            
            if(membershipCardSDO == null || membershipCardSDO.getResultCd() == null
            		|| !"200".equals(membershipCardSDO.getResponseCd())) {
            	String[] str = {"ERP info is null or Fail."};
            	throw new MemberFailERPIFException(str);
            }
        } catch (Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            String[] str = {"ERP info is null or Fail."};
            throw new MemberFailERPIFException(str);
        }
        
        return membershipCardSDO;
    }
    
    /**
     * 마일리지 조회
     * 
     * @param systemPK
     * @param mypageFoDTO
     * @return
     * @throws Exception
     */
    public MemberMileageInfoSDO getMemberMileage(SystemPK systemPK, MypageFoDTO mypageFoDTO) throws Exception {

    	assertNotNull(interfaceAdapter);

        MemberMileageInfoSDO memberMileageInfoSDO = new MemberMileageInfoSDO();
        
        try {
            log.info(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());

            memberMileageInfoSDO.setCallerId(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());
            memberMileageInfoSDO.setCid(mypageFoDTO.getMbr().getErpCstmrNo());
            memberMileageInfoSDO.setHistoryYn(mypageFoDTO.getHistoryYn());
            // ERP에서 Brand 가 없으면 안된다고 함.
            // 해당 값이 머든 상관없이 전체 포인트와 목록을 준다고 함.
            if(mypageFoDTO.getBrand() == null) {
            	if(systemPK.getMall().equals("MBM")){
            		mypageFoDTO.setBrand("M");
            	}else if(systemPK.getMall().equals("SAM")){
            		mypageFoDTO.setBrand("A");
            	}else{
            		mypageFoDTO.setBrand("X");
            	}
            	
            }
            memberMileageInfoSDO.setBrand(mypageFoDTO.getBrand());
            memberMileageInfoSDO.setStartDate(mypageFoDTO.getDateStart());
            memberMileageInfoSDO.setEndDate(mypageFoDTO.getDateEnd());
            memberMileageInfoSDO.setPageNum(mypageFoDTO.getPageNum());
            memberMileageInfoSDO.setPageSize(mypageFoDTO.getPageSize());

            AdapterHeader adapterHeader = setAdapterHeader(systemPK);
            
            String inputJson = JsonService.marshalling(memberMileageInfoSDO);
            String url = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.MEMBER_GET_MILEAGE_INFORMATION;
            String result = interfaceAdapter.sendInterface(inputJson, adapterHeader, url);

            memberMileageInfoSDO = JsonService.unmarshalling(result, MemberMileageInfoSDO.class);
        } catch (Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            String[] str = {"ERP info is null or Fail."};
            throw new MemberFailERPIFException(str);
        }
        
        return memberMileageInfoSDO;
    }
    
    /**
     * 임직원회원 변경정보 조회
     * 
     * @param systemPK
     * @return
     * @throws Exception
     */
    public MemberEmpInformationListSDO getMemberEmployeeInfo(SystemPK systemPK) throws Exception {

    	assertNotNull(interfaceAdapter);

    	MemberEmpInformationListSDO memberEmpInformationListSDO = new MemberEmpInformationListSDO();
    	InterfaceBaseSDO interfaceBaseSDO = new InterfaceBaseSDO();
        
        try {
            log.info(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());

            interfaceBaseSDO.setCallerId(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());

            AdapterHeader adapterHeader = setAdapterHeader(systemPK);
            
            String inputJson = JsonService.marshalling(interfaceBaseSDO);
            String url = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.MEMBER_EMPLOYEE_INFO;
            String result = interfaceAdapter.sendInterface(inputJson, adapterHeader, url);

            memberEmpInformationListSDO = JsonService.unmarshalling(result, MemberEmpInformationListSDO.class);
        } catch (Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            String[] str = {"ERP info is null or Fail."};
            throw new MemberFailERPIFException(str);
        }
        
        return memberEmpInformationListSDO;
    }
    
    /**
     * 네이버 로그인 Access Token Validate
     * 
     * @param systemPK
     * @param userAccessToken
     * @return String
     * @throws Exception
     */
    public String naverAccessTokenValidator(SystemPK systemPK, String userAccessToken) throws Exception {

    	assertNotNull(interfaceAdapter);

    	String returnJson = "";
    	NaverAccessTokenSDO naverAccessTokenSDO = new NaverAccessTokenSDO();
        
        try {
            log.info(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());

            naverAccessTokenSDO.setCallerId(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());
            naverAccessTokenSDO.setUserAccessToken(userAccessToken);
            
            AdapterHeader adapterHeader = setAdapterHeader(systemPK);
            
            String inputJson = JsonService.marshalling(naverAccessTokenSDO);
            String url = interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.NAVER_ACCESS_TOKEN_VALIDATOR;
            String result = interfaceAdapter.sendInterface(inputJson, adapterHeader, url);

//            returnJson = JsonService.unmarshalling(result, String.class);
            returnJson = result;
        } catch (Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            String[] str = {"ERP info is null or Fail."};
            throw new MemberFailERPIFException(str);
        }
        
        return returnJson;
    }

    /**
     * 이메일 발송(html)
     * 
     * @param systemPK
     * @param emailSDO
     * @param url
     * @param param
     * @return String
     * @throws Exception
     */
    public void sendHtmlEmail(SystemPK systemPK, EmailHtmlSDO emailSDO, String url, String param) throws Exception {
    	
    	assertNotNull(interfaceAdapter);
    	
    	String mailbody = mailHtmlReaderService.readHTML(url, param);
    	
    	emailSDO.setHtmlBody(mailbody);
    	
    	log.info(emailAdapter.sendHtmlEmail(emailSDO, setAdapterHeader(systemPK, null, null, "PC")));
    }
    
    public void sendHtmlEmailTest(SystemPK systemPK, EmailHtmlSDO emailSDO, String url, String param) throws Exception {
    	
    	assertNotNull(interfaceAdapter);
    	
    	String mailbody = "Mail Body";
    	
    	emailSDO.setHtmlBody(mailbody);
    	
    	log.info(emailAdapter.sendHtmlEmail(emailSDO, setAdapterHeader(systemPK, null, null, "PC")));
    }
    
    /**
     * NTAK, SMS 발송
     * 
     * @param systemPK
     * @param mPushAlimTalkSDO
     * @return String
     * @throws Exception
     */
    public void sendAlimTalk(SystemPK systemPK, MPushAlimTalkSDO mPushAlimTalkSDO) throws Exception {
    	
    	assertNotNull(interfaceAdapter);
    	
    	log.info(mPushAdapter.sendAlimTalk(mPushAlimTalkSDO, setAdapterHeader(systemPK, null, null, "PC")));
    }
    
	// 회원 상태 코드 변환 세팅(온라인 -> ERP)
	private String setStatus(Mbr mbr) {
		String mbrStatCd = mbr.getMbrStatCd();
		String status = "";
		
		if(mbrStatCd == null || "".equals(mbrStatCd)) {
			status = "";
		}
		else if(MemberEnum.MemberStatCd.ACT.toString().equals(mbrStatCd)) {
			status = "R";
		}
		else if(MemberEnum.MemberStatCd.SECSN.toString().equals(mbrStatCd)) {
			status = "D";
		}
		else if(MemberEnum.MemberStatCd.DRMNCY.toString().equals(mbrStatCd)) {
			status = "S";
		}
		else if(MemberEnum.MemberStatCd.BLCLST_A.toString().equals(mbrStatCd)) {
			status = "B";
		}
		else if(MemberEnum.MemberStatCd.BLCLST_B.toString().equals(mbrStatCd)) {
			status = "L";
		}
		
		return status;
	}
	
	// 회원 성별 구분 코드 변환 세팅(온라인 -> ERP)
	private String setGender(Mbr mbr) {
		String mbrSexSectCd = mbr.getMbrSexSectCd();
		String gender = "";
		
		if(mbrSexSectCd == null || "".equals(mbrSexSectCd)) {
			gender = "";
		}
		else if("MALE".equals(mbrSexSectCd)) {
			gender = "M";
		}
		else if("FEMALE".equals(mbrSexSectCd)) {
			gender = "F";
		}
		
		return gender;
	}
	
	// 회원 생년월일 변환 세팅(온라인 -> ERP)
	private String setBirthDay(Mbr mbr) {
		String mbrBrthdy = mbr.getMbrBrthdy();
		
		if(mbrBrthdy == null || "".equals(mbrBrthdy)) {
			return mbrBrthdy;
		}
		
		return mbrBrthdy.substring(0, 4) + "-" + mbrBrthdy.substring(4, 6) + "-" + mbrBrthdy.substring(6, 8);
	}
	
	// 회원 생년월일 양력 여부 변환 세팅(온라인 -> ERP)
	private String setBirthCal(Mbr mbr) {
		String mbrBrthdySlrcldYn = mbr.getMbrBrthdySlrcldYn();
		String birthCal = "";
		
		if(mbrBrthdySlrcldYn == null || "".equals(mbrBrthdySlrcldYn)) {
			mbrBrthdySlrcldYn = "";
			birthCal = "";
		}
		else if(MemberEnum.YES.toString().equals(mbrBrthdySlrcldYn)) {
			birthCal = "S";
		}
		else if(MemberEnum.NO.toString().equals(mbrBrthdySlrcldYn)) {
			birthCal = "L";
		}
		
		return birthCal;
	}
	
	// 수신동의일시 변환 세팅(온라인 -> ERP)
	private String setRecvDate(Mbr mbr) {
		String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		String recvDate = "";
		String emailRecptnAgrYn = mbr.getEmailRecptnAgrYn();
		String smsRecptnAgrYn = mbr.getSmsRecptnAgrYn();
		
		long time = System.currentTimeMillis();
		Date curDt = new Date(time);

		if(MemberEnum.YES.toString().equals(emailRecptnAgrYn)
				&& MemberEnum.YES.toString().equals(smsRecptnAgrYn)) {
			if(mbr.getLastEmailRecptnAgrDt() == null) {
				mbr.setLastEmailRecptnAgrDt(curDt);
			}
			
			if(mbr.getLastSmsRecptnAgrDt() == null) {
				mbr.setLastSmsRecptnAgrDt(curDt);
			}
			
			if(mbr.getLastEmailRecptnAgrDt().after(mbr.getLastSmsRecptnAgrDt())) {
				recvDate = DateService.parseString(mbr.getLastEmailRecptnAgrDt(), DEFAULT_DATE_FORMAT);
			}
			else {
				recvDate = DateService.parseString(mbr.getLastSmsRecptnAgrDt(), DEFAULT_DATE_FORMAT);
			}
		}
		else if(MemberEnum.YES.toString().equals(emailRecptnAgrYn)) {
			recvDate = DateService.parseString(mbr.getLastEmailRecptnAgrDt(), DEFAULT_DATE_FORMAT);
		}
		else if(MemberEnum.YES.toString().equals(smsRecptnAgrYn)) {
			recvDate = DateService.parseString(mbr.getLastSmsRecptnAgrDt(), DEFAULT_DATE_FORMAT);
		}
		
		return recvDate;
	}
	
	// 가입일시 변환 세팅(온라인 -> ERP)
	private String setJoinDate(Mbr mbr) {
		String joinDate = "";
        if(mbr.getJoinDt() == null) {
        	Mbr tmpMbr = new Mbr();
        	tmpMbr.setMbrNo(mbr.getMbrNo());
        	Mbr mbrForJoinDt = memberBaseSelectService.selectMbr(tmpMbr);
        	if(mbrForJoinDt != null && mbrForJoinDt.getJoinDt() != null) {
        		joinDate = mbrForJoinDt.getJoinDt().toString();
        	}
        }
        
        return joinDate;
	}
	
	// 외국인 여부 변환 세팅(온라인 -> ERP)
	private String setAuthType(Mbr mbr) {
		String frgnrYn = mbr.getFrgnrYn();
		String authType = "";
		
		if(frgnrYn == null || "".equals(frgnrYn)) {
			authType = "";
		}
		else if(MemberEnum.NO.toString().equals(frgnrYn)) {
			authType = "L";
		}
		else if(MemberEnum.YES.toString().equals(frgnrYn)) {
			authType = "F";
		}
		
		return authType;
	}
	
	// 실명인증방식 변환 세팅(온라인 -> ERP)
	private String setAuthRealname(Mbr mbr) {
		String unityMbrCrtfcSectCd = mbr.getUnityMbrCrtfcSectCd();
		String authRealname = "";

		if(unityMbrCrtfcSectCd == null || "".equals(unityMbrCrtfcSectCd)) {
			authRealname = "";
		}
		else if(MemberEnum.UnityMbrCrtfcSectCd.SLF_CRTFC.toString().equals(unityMbrCrtfcSectCd)) {
			authRealname = "CEL";
		}
		else if(MemberEnum.UnityMbrCrtfcSectCd.IPIN_CRTFC.toString().equals(unityMbrCrtfcSectCd)) {
			authRealname = "IPN";
		}
		
		return authRealname;
	}
	
	// 자녀 목록 변환 세팅(온라인 -> ERP)
	private List<FamilyInformationSDO> setFamilyList(MemberFoDTO dto) {
		
		List<MemberChildDTO> list = dto.getChildrenList();
		
		if(list == null || list.size() == 0) {
			return null;
		}
		
		List<FamilyInformationSDO> rtnList = new ArrayList<FamilyInformationSDO>();
		String childrenBirthYear = "";
		String childrenBirthMonth = "";
		String childrenBirthDay = "";
		for(MemberChildDTO memberChildDTO : list) {
			FamilyInformationSDO familyInformationSDO = new FamilyInformationSDO();
			
			familyInformationSDO.setFamilyName(memberChildDTO.getChildrenName());
			childrenBirthYear = memberChildDTO.getChildrenBirthYear();
			childrenBirthMonth = memberChildDTO.getChildrenBirthMonth();
			if(childrenBirthMonth.length() == 1) childrenBirthMonth = "0" + childrenBirthMonth;
			childrenBirthDay = memberChildDTO.getChildrenBirthDay();
			if(childrenBirthDay.length() == 1) childrenBirthDay = "0" + childrenBirthDay;
			familyInformationSDO.setFamilyBirth(childrenBirthYear + childrenBirthMonth + childrenBirthDay);
			
			//if(familyInformationSDO.getFamilyName() != null && !"".equals(familyInformationSDO.getFamilyName())) {
				rtnList.add(familyInformationSDO);
			//}
		}
		
//		List<FamilyInformationSDO> li = new ArrayList<>();
//		
//		if(dto.getChildrenList() != null && !dto.getChildrenList().isEmpty()){        	
//        	
//        	for(MemberChildDTO m : dto.getChildrenList()){
//        		FamilyInformationSDO ss = new FamilyInformationSDO();
//        		ss.setFamilyName(m.getChildrenName());
//        		ss.setFamilyBirth(m.getChildrenBirthYear()+m.getChildrenBirthMonth()+m.getChildrenBirthDay());
//        		
//        		li.add(ss);
//        	}
//        	
//        	//sendMemberInformationSDO.setFamilyList(li);
//        }
		
		return rtnList;
	}
	
	// 유입 경로 변환 세팅(온라인 -> ERP)
	private String setInflow(Mbr mbr) {
		String inflow = "";
		
		if(mbr.getInflowSn() != null && mbr.getInflowSn() > 0) {
			long inflowSn = mbr.getInflowSn();
			
			SysInflow sysInflow = new SysInflow();
			sysInflow.setInflowSn(inflowSn);
			sysInflow = memberBaseSelectService.selectMbrSysInflow(sysInflow);
			
			if(sysInflow.getInflowSn() != null && (sysInflow.getInflowSn() > 0)) {
				inflow = sysInflow.getExcutRemNm();
			}
		}
		
		return inflow;
	}

	/**
	 * 본인인증 sms
	 * 
	 * @param systemPK
	 * @param birthday
	 * @param gender
	 * @param mbrNm
	 * @param mobileCo
	 * @param mobileNumber
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> sendCertSms(SystemPK systemPK, String birthday, String gender, String mbrNm, String mobileCo, String mobileNumber) throws Exception {

    	assertNotNull(interfaceAdapter);

    	Map<String, String> resultMap = new HashMap<String, String>();
    	SendCertSmsSDO sendCertSmsSDO = new SendCertSmsSDO();
        
        try {
            log.info(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());

            sendCertSmsSDO.setBirthday(birthday);
    		sendCertSmsSDO.setGender(gender);
    		sendCertSmsSDO.setMbrNm(mbrNm);
    		sendCertSmsSDO.setMobileCo(mobileCo);
    		sendCertSmsSDO.setMobileNumber(mobileNumber);
            
            AdapterHeader adapterHeader = setAdapterHeader(systemPK);
            
            String inputJson = JsonService.marshalling(sendCertSmsSDO);
            String url = interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.SEND_CERT_SMS;
            String result = interfaceAdapter.sendInterface(inputJson, adapterHeader, url);
            
            if(StringService.isNotEmpty(result)) {
            	resultMap = JsonService.unmarshalling(result, HashMap.class);
            }
        } catch (Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            String[] str = {"ERP info is null or Fail."};
            throw new MemberFailERPIFException(str);
        }
        
        return resultMap;
    }
	
	/**
	 * 본인인증 sms check
	 * 
	 * @param systemPK
	 * @param REQ_SEQ
	 * @param RES_SEQ
	 * @param sAuthNo
	 * @param birthday
	 * @param gender
	 * @param mbrNm
	 * @param mobileCo
	 * @param mobileNumber
	 * @param frgnrYn
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> sendCertSmsCheck(SystemPK systemPK, String REQ_SEQ, String RES_SEQ, String sAuthNo, String birthday, String gender, String mbrNm, String mobileCo, String mobileNumber, String frgnrYn) throws Exception {

    	assertNotNull(interfaceAdapter);

    	Map<String, String> resultMap = new HashMap<String, String>();
    	SendCertSmsSDO sendCertSmsSDO = new SendCertSmsSDO();
        
        try {
            log.info(this.getClass().getSimpleName() + "." + new Object(){}.getClass().getEnclosingMethod().getName());

    		sendCertSmsSDO.setREQ_SEQ(REQ_SEQ);
    		sendCertSmsSDO.setRES_SEQ(RES_SEQ);
    		sendCertSmsSDO.setSAuthNo(sAuthNo);
    		sendCertSmsSDO.setBirthday(birthday);
    		sendCertSmsSDO.setGender(gender);
    		sendCertSmsSDO.setMbrNm(mbrNm);
    		sendCertSmsSDO.setMobileCo(mobileCo);
    		sendCertSmsSDO.setMobileNumber(mobileNumber);
    		sendCertSmsSDO.setFrgnrYn(frgnrYn);
            
            AdapterHeader adapterHeader = setAdapterHeader(systemPK);
            
            String inputJson = JsonService.marshalling(sendCertSmsSDO);
            // (ID - 확인필요)
//            String url = interfaceApiCommon.getExternalInterfaceServerUrl() + InterfaceConstants.SEND_CERT_SMS_CHECK;
            String url = interfaceApiCommon.getExternalInterfaceServerUrl() + "/sms/sendMobileCertSmsCheck";
            String result = interfaceAdapter.sendInterface(inputJson, adapterHeader, url);
            
            if(StringService.isNotEmpty(result)) {
            	resultMap = JsonService.unmarshalling(result, HashMap.class);
            }
        } catch (Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            String[] str = {"ERP info is null or Fail."};
            throw new MemberFailERPIFException(str);
        }
        
        return resultMap;
    }

}
