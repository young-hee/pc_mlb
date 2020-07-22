package com.plgrim.ncp.biz.member.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrCrtfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrGrd;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrLoginLog;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;
import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.MemberEnum.MemberAtrbCd;
import com.plgrim.ncp.base.enums.MemberEnum.MemberStatCd;
import com.plgrim.ncp.base.enums.MemberEnum.MemberTpCd;
import com.plgrim.ncp.base.enums.MemberEnum.UnityMbrCrtfcSectCd;
import com.plgrim.ncp.base.enums.SysInfoEnum.MallIdEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.MbrPsnlInfoUdtSectCd;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.MemberModLcSectCd;
import com.plgrim.ncp.base.repository.mbr.MbrRepository;
import com.plgrim.ncp.biz.member.data.MemberBoDTO;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.exception.MemberFailSendPasswordException;
import com.plgrim.ncp.biz.member.repository.MemberActivityCommandRepository;
import com.plgrim.ncp.biz.member.repository.MemberBaseCommandRepository;
import com.plgrim.ncp.biz.member.repository.MemberBaseSelectRepository;
import com.plgrim.ncp.biz.member.repository.MemberCertifyCommandRepository;
import com.plgrim.ncp.biz.member.repository.MemberOrderCommandRepository;
import com.plgrim.ncp.biz.member.repository.MemberPersonalInfoCommandRepository;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.cmp.member.fo.MemberAuthFOComponent;
import com.plgrim.ncp.cmp.member.fo.MemberJoinFOComponent;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.LocaleService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.interfaces.member.data.MemberGradeInformationSDO;
import com.plgrim.ncp.interfaces.member.data.MemberInformationSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

/**
 * 회원기본정보 command service
 */
@Service
@Slf4j
public class MemberBaseCommandService extends AbstractService {
 
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;

	@Autowired
	InterfaceApiCommon interfaceApiCommon;

	@Autowired
	private MemberBaseCommandRepository memberBaseCommandRepository;

	@Autowired
	private MemberBaseSelectRepository memberBaseSelectRepository;

	@Autowired
	private MemberPersonalInfoCommandRepository memberPersonalInfoCommandRepository;

	@Autowired
	private MemberActivityCommandRepository memberACtivityCommandRepository;

	@Autowired
	private MemberCertifyCommandRepository memberCertifyCommandRepository;

	@Autowired
	private MemberOrderCommandRepository memberOrderCommandRepository;

	@Autowired
	private MbrRepository mbrRepository;

	@Autowired
	private MemberJoinFOComponent memberJoinFOComponent;
	
	@Autowired
	private MemberAuthFOComponent memberAuthFOComponent;
	
	@Autowired
	private MemberCertifySelectService memberCertifySelectService;
	
	@Autowired
	private MemberCertifyCommandService memberCertifyCommandService;
	
	/**
	 * 회원 비밀번호 수정.
	 *
	 * @param dto
	 *            [설명]
	 * @param loginId
	 *            [설명]
	 * @param mbrPw
	 *            [설명]
	 * @
	 *             the exception
	 * @since 2015. 4. 16
	 */
	public void updateMemberPasswordBy(MemberBoDTO dto, String loginId, String encMbrPw)  {
		Mbr mbr = dto.getMbr();
		mbr.setUdterId(loginId);
		mbr.setMbrPw(encMbrPw);
		mbr.setTmprPwYn("Y");

		memberBaseCommandRepository.updateMemberPassword(mbr);
	}

	/**
	 * 회원 임시비밀번호 Email 발송
	 * @param dto
	 * @param loginId
	 * @param mbrPw
	 * @
	 */
	public void sendMemberPasswordEMail(SystemPK systemPK, MemberBoDTO dto, String loginId, String mbrPw)  {
		MemberBoResult mbrResult = memberBaseSelectRepository.selectMemberSimple(dto);
		if(mbrResult == null){
			throw new MemberFailSendPasswordException(null);
		}

		// TODO 이메일 솔루션 적용

//		String mbrNo = mbrResult.getMbr().getMbrNo();
//		String mbrId = mbrResult.getMbr().getMbrId();
//		String mbrNm = mbrResult.getMbr().getMbrNm();
//		String email = mbrResult.getMbr().getMbrEmail();
//		String callerId = InterfaceAdapterEnum.INTERFACE_ADAPTER_SEND_MEMBER_PASSWORD_EMAIL.toString();
//		String insertData = this.interfaceApiCommon.getCurrentDate();
//
//		long time = System.currentTimeMillis();
//		Date regDt = new Date(time);
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String requestDt = sdf.format(regDt);
//		if(StringService.isEmpty(email)){
//			throw new MemberFailSendPasswordException(null);
//		}
//
//		List<EmailSDO> parameterList = new ArrayList<EmailSDO>();
//		EmailSDO parameter1 = new EmailSDO();
//		parameter1.setAutocode("");							// 필수 : 자동증가 시퀀스.
//		parameter1.setUserId(mbrNo);						// 필수 : 메일 받는 사람의 쇼핑몰 ID.
//		parameter1.setAutotype(dto.getEmailAutoType());		// 필수 : 메일유형
//		parameter1.setEmail(email);							// 필수 : 받는사람 이메일 주소.
//		parameter1.setName(mbrNm);							// 필수 : 받는사람 이름.
//		parameter1.setInsertdate(insertData);				// 필수 : 메일 입력일자.
//		parameter1.setSendtime("");
//		parameter1.setSendyn(MemberEnum.NO.toString());		// 필수 : default : N
//		parameter1.setOpentime("");
//		parameter1.setSenttime("");
//		parameter1.setCmpncode("");
//		parameter1.setFromaddress("");	// 보낸사람 이메일 주소.
//		parameter1.setFromname("");		// 보낸사람 이름.
//		parameter1.setTitle(dto.getEmailTitle() != null ? dto.getEmailTitle() : "");			// 메일제목
//		parameter1.setTag1(requestDt);
//		parameter1.setTag2(mbrPw);
//		parameter1.setTag3("");
//		parameter1.setTag4("");
//		parameter1.setTag5("");
//		parameter1.setTag6("");
//		parameter1.setTag7("");
//		parameter1.setTag8("");
//		parameter1.setTag9("");
//		parameter1.setTag10("");
//		parameter1.setTag11("");
//		parameter1.setTag12("");
//		parameter1.setCallerId(callerId);
//		parameter1.setAdminId("sysadmin");
//		parameterList.add(parameter1);
//
//		// EMAIL 발송 요청
//		AdapterHeader adapterHeader = setBOAdapterHeader(systemPK);
//		String emailResult = "";
//		try {
//			emailResult = emailAdapter.sendEmail(parameterList, adapterHeader);
//		} catch (Exception e) {
//			if(log.isInfoEnabled()) {log.info("> SEND Mail PASSWORD Exception : {}", e.getMessage());}
//		}
//		if(!StringService.equalsIgnoreCase(emailResult, "SUCCESS")){
//			throw new MemberFailSendPasswordException(null);
//		}
	}

	/**
	 * 회원 임시비밀번호 SMS 발송
	 * @param dto
	 * @param loginId
	 * @param mbrPw
	 * @
	 */
	public void sendMemberPasswordSMS(SystemPK systemPK, MemberBoDTO dto, String loginId, String mbrPw)  {

		// step1. 회원 정보 조회 및 변수 설정
		MemberBoResult mbrResult = memberBaseSelectRepository.selectMemberSimple(dto);
		if(mbrResult == null){
			throw new MemberFailSendPasswordException(null);
		}

		String tranId = mbrResult.getMbr().getMbrNo();
		String mobilNo = StringService.remove(mbrResult.getMobilNo(), "-");
		String mallId = systemPK.getMall();

		if(StringService.isEmpty(mobilNo)){
			throw new MemberFailSendPasswordException(null);
		}

		ArrayList<String> params = new ArrayList<>();
		params.add(0, mbrPw);

		// TODO 문자 솔루션 적용

		String msgKey = "DXM_MBR_PW_01";
//		String callerId = InterfaceAdapterEnum.INTERFACE_ADAPTER_SEND_MEMBER_PASSWORD_SMS.toString();
//
//		HumusonSDO humusonSDO = humusonCommonService.getAlimTalkData(tranId, mobilNo, mallId, callerId, msgKey, params);
//
//		// SMS 발송 요청
//		AdapterHeader adapterHeader = setBOAdapterHeader(systemPK);
//		try {
//			String resultJson = humusonCommonService.sendAlimTalk(humusonSDO, adapterHeader);
//			JSONObject jsonObject = (JSONObject) new JSONParser().parse(resultJson);
//
//			// 발송실패시 Exception
//			if (jsonObject != null) {
//				String resultCd = jsonObject.get("resultCode").toString();
//				if (StringService.isEmpty(resultCd) || !"00".equals(resultCd)) {
//					throw new MemberFailSendPasswordException(null);
//				}
//			} else {
//				throw new MemberFailSendPasswordException(null);
//			}
//		} catch (Exception e) {
//			if (log.isInfoEnabled()) log.info("> SEND SMS Exception : {}", e.getMessage());
//			throw new MemberFailSendPasswordException(null);
//		}

	}

	/**
	 * 회원 정보 변경 이력 등록.
	 *
	 * @param afterMap [설명]
	 * @param beforeMap [설명]
	 * @param mpim [설명]
	 * @param codeArr [설명]
	 * @ the exception
	 * @since 2015. 4. 16
	 */
	private void insertPersonalInfo(Map<String, Object> afterMap, Map<String, Object> beforeMap, MbrPsnlInfoModHist mpim, String[] codeArr)  {
		String modBfVal = "";
		String modAfVal = "";

		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("psnl_info_mod_hist_sn", mpim .getPsnlInfoModHistSn());
		int modTurn = 0;

		for(String codeName : codeArr){
			modBfVal = this.makeCheckString(codeName, beforeMap);
			modAfVal = this.makeCheckString(codeName, afterMap);

			if(StringService.contains(codeName, "_NO") || StringService.contains(codeName, "_DATE") || StringService.contains(codeName, "MBR_BRTHDY")){
				modBfVal = StringService.removeHyphen(modBfVal);
				modAfVal = StringService.removeHyphen(modAfVal);

			}

			if(modBfVal.equals(modAfVal)){continue; }

			// 변경이력 값 설정
			modTurn = getIdGenService().generateDBOrder(sqlSession1, "mbr_psnl_info_mod_hist", "psnl_info_mod_hist_turn", conditions, DatabaseType.ORACLE);
			mpim.setPsnlInfoModHistTurn(modTurn);	// 개인정보 변경 이력 순번

			mpim.setPsnlInfoUdtSectCd(codeName); 	// 개인정보 수정 구분 코드
			mpim.setModBfVal(modBfVal);  			// 변경 이전 값
			mpim.setModAfVal(modAfVal);				// 변경 이후 값

			memberPersonalInfoCommandRepository.insertPersonalInfoModHistory(mpim);
		}
	}

	private String makeCheckString(String codeName, Map<String, Object> paramMap) {
		String returnStr = "";

		String[] colunms = StringService.split(MemberPersonalInfoEnum.valueOf(codeName).toString(), "|");

		for (String colunm : colunms) {
			returnStr += StringService.trimToEmpty((String) paramMap.get(colunm));
		}

		return returnStr;
	}


	/**
	 * AdapterHeader 값 설정
	 * 
	 * @return
	 */
	private AdapterHeader setBOAdapterHeader(SystemPK systemPK) {
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId(systemPK.getMall());
		adapterHeader.setDvcCd(systemPK.getDevice());
		// adapterHeader.setMallId("DXM");
		// adapterHeader.setDvcCd("PC");
		adapterHeader.setLangCd("KOR");

		return adapterHeader;
	}

	/**
	 * Object Mall_id , Lang_cd, Dvc_cd 설정
	 * 
	 * @param obj
	 * @param pk
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void setSystemPK(Object obj, SystemPK pk) {
		try {
			Method[] methods = obj.getClass().getMethods();
			Method[] pkMethods = pk.getClass().getMethods();
			for (int i = 0; i < methods.length; i++) {
	
				if (methods[i].getName().substring(0, 3).equals("set")) {
					for (int j = 0; j < pkMethods.length; j++) {
	
						String methodName = methods[i].getName().substring(3);
						if (methodName.toLowerCase().equals("dvccd") && pkMethods[j].getName().equals("getDevice")) {
							methods[i].invoke(obj, pkMethods[j].invoke(pk, null));
							break;
						}
						if (pkMethods[j].getName().substring(0, 3).equals("get") && methodName.toLowerCase()
								.indexOf(pkMethods[j].getName().substring(3).toLowerCase()) > -1) {
							methods[i].invoke(obj, pkMethods[j].invoke(pk, null));
							break;
	
						}
					}
				}
	
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int updatePassword(Mbr mbr) {
		return memberBaseCommandRepository.updateMemberPassword(mbr);
	}

	/**
	 * 회원 휴면 처리.
	 */
	public void updateMemberDormancy(Mbr mbr) {
		int resultCnt = 0;
		String mbrNo = mbr.getMbrNo();

		// step 1. 회원 활동이력 삭제 - DELETE
		// 회원 기념일 삭제
		// resultCnt = memberSecessionRepository.deleteMbrAnnvrsry(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrAnnvrsry COUNT : {}",
		// resultCnt); }

		// 회원 인증 삭제
		resultCnt = memberBaseCommandRepository.deleteMbrCrtfc(mbrNo);
		if (log.isInfoEnabled()) {
			log.info("> deleteMbrCrtfc COUNT : {}", resultCnt);
		}

		// 회원 배송지 삭제
		resultCnt = memberOrderCommandRepository.deleteMbrDlvsp(mbrNo);
		if (log.isInfoEnabled()) {
			log.info("> deleteMbrDlvsp COUNT : {}", resultCnt);
		}

		// 회원 가입 통합 내역 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt = memberSecessionRepository.deleteMbrJoinUnityDetl(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrJoinUnityDetl COUNT :
		// {}", resultCnt); }

		// 회원 로그인 로그 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt = memberSecessionRepository.deleteMbrLoginLog(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrLoginLog COUNT : {}",
		// resultCnt); }

		// 회원 모바일 동의 이력 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt = memberSecessionRepository.deleteMbrMobileAgrHist(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrMobileAgrHist COUNT :
		// {}", resultCnt); }

		// 회원 모바일 동의 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt = memberSecessionRepository.deleteMbrMobileAgr(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrMobileAgr COUNT : {}",
		// resultCnt); }

		// 회원 모바일 장치 이력 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt =
		// memberSecessionRepository.deleteMbrMobileDeviceHist(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrMobileDeviceHist COUNT
		// : {}", resultCnt); }

		// 회원 모바일 장치 정보 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt =
		// memberSecessionRepository.deleteMbrMobileDeviceInfo(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrMobileDeviceInfo COUNT
		// : {}", resultCnt); }

		// 회원 구매 매장 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt = memberSecessionRepository.deleteMbrPchShop(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrPchShop COUNT : {}",
		// resultCnt); }

		// 회원 포인트 연동 이력 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt = memberSecessionRepository.deleteMbrPntIntrlckHist(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrPntIntrlckHist COUNT :
		// {}", resultCnt); }

		// 회원 개인정보 변경 이력 삭제
		resultCnt = memberPersonalInfoCommandRepository.deleteMbrPsnlInfoModHist(mbrNo);
		if (log.isInfoEnabled()) {
			log.info("> deleteMbrPsnlInfoModHist COUNT : {}", resultCnt);
		}

		// 회원 환불 계좌 삭제
		resultCnt = memberBaseCommandRepository.deleteMbrRfdBnkAcct(mbrNo);
		if (log.isInfoEnabled()) {
			log.info("> deleteMbrRfdBnkAcct COUNT : {}", resultCnt);
		}

		// 회원 적립금 이력 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt = memberSecessionRepository.deleteMbrSavMnyHist(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrSavMnyHist COUNT :
		// {}", resultCnt); }

		// 회원 SNS공유이력 삭제
		resultCnt = memberACtivityCommandRepository.deleteMbrSnscnrshist(mbrNo);
		if (log.isInfoEnabled()) {
			log.info("> deleteMbrSnscnrshist COUNT : {}", resultCnt);
		}

		// 회원 약관 동의 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt = memberSecessionRepository.deleteMbrStplatAgr(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrStplatAgr COUNT : {}",
		// resultCnt); }

		// 회원 간편가입 연동 정보 수정
		resultCnt = memberCertifyCommandRepository.updateMbrIdCntcDeleteYn(mbrNo);
		if (log.isInfoEnabled()) {
			log.info("> updateMbrIdCntcDeleteYn COUNT : {}", resultCnt);
		}

		// 회원 나의 사이즈 삭제
//		memberACtivityCommandRepository.deleteMbrSizeClfcDetail(mbrNo, "");
//		memberACtivityCommandRepository.deleteMbrSizeClfc(mbrNo, "");

		// step 2. 회원 마스터 정보 수정
		mbr.setMbrStatCd(MemberStatCd.DRMNCY.toString());
		memberBaseCommandRepository.updateMemberDormancy(mbr);
	}
	
	/**
	 * 회원 탈퇴.
	 */
	public void updateMemberSecession(Mbr mbr) {
		int resultCnt = 0;
		String mbrNo = mbr.getMbrNo();

		// step 1. 회원 활동이력 삭제 - DELETE
		// 회원 기념일 삭제
		// resultCnt = memberSecessionRepository.deleteMbrAnnvrsry(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrAnnvrsry COUNT : {}",
		// resultCnt); }

		// 회원 인증 삭제
		resultCnt = memberBaseCommandRepository.deleteMbrCrtfc(mbrNo);
		if (log.isInfoEnabled()) {
			log.info("> deleteMbrCrtfc COUNT : {}", resultCnt);
		}

		// 회원 배송지 삭제
		resultCnt = memberOrderCommandRepository.deleteMbrDlvsp(mbrNo);
		if (log.isInfoEnabled()) {
			log.info("> deleteMbrDlvsp COUNT : {}", resultCnt);
		}

		// 회원 가입 통합 내역 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt = memberSecessionRepository.deleteMbrJoinUnityDetl(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrJoinUnityDetl COUNT :
		// {}", resultCnt); }

		// 회원 로그인 로그 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt = memberSecessionRepository.deleteMbrLoginLog(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrLoginLog COUNT : {}",
		// resultCnt); }

		// 회원 모바일 동의 이력 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt = memberSecessionRepository.deleteMbrMobileAgrHist(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrMobileAgrHist COUNT :
		// {}", resultCnt); }

		// 회원 모바일 동의 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt = memberSecessionRepository.deleteMbrMobileAgr(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrMobileAgr COUNT : {}",
		// resultCnt); }

		// 회원 모바일 장치 이력 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt =
		// memberSecessionRepository.deleteMbrMobileDeviceHist(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrMobileDeviceHist COUNT
		// : {}", resultCnt); }

		// 회원 모바일 장치 정보 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt =
		// memberSecessionRepository.deleteMbrMobileDeviceInfo(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrMobileDeviceInfo COUNT
		// : {}", resultCnt); }

		// 회원 구매 매장 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt = memberSecessionRepository.deleteMbrPchShop(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrPchShop COUNT : {}",
		// resultCnt); }

		// 회원 포인트 연동 이력 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt = memberSecessionRepository.deleteMbrPntIntrlckHist(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrPntIntrlckHist COUNT :
		// {}", resultCnt); }

		// 회원 개인정보 변경 이력 삭제
		resultCnt = memberPersonalInfoCommandRepository.deleteMbrPsnlInfoModHist(mbrNo);
		if (log.isInfoEnabled()) {
			log.info("> deleteMbrPsnlInfoModHist COUNT : {}", resultCnt);
		}

		// 회원 환불 계좌 삭제
		resultCnt = memberBaseCommandRepository.deleteMbrRfdBnkAcct(mbrNo);
		if (log.isInfoEnabled()) {
			log.info("> deleteMbrRfdBnkAcct COUNT : {}", resultCnt);
		}

		// 회원 적립금 이력 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt = memberSecessionRepository.deleteMbrSavMnyHist(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrSavMnyHist COUNT :
		// {}", resultCnt); }

		// 회원 SNS공유이력 삭제
		resultCnt = memberACtivityCommandRepository.deleteMbrSnscnrshist(mbrNo);
		if (log.isInfoEnabled()) {
			log.info("> deleteMbrSnscnrshist COUNT : {}", resultCnt);
		}

		// 회원 약관 동의 삭제
		// 2016.06.14 steven 개인정보없음 삭제대상에서 제외
		// resultCnt = memberSecessionRepository.deleteMbrStplatAgr(mbrNo);
		// if(log.isInfoEnabled()) { log.info("> deleteMbrStplatAgr COUNT : {}",
		// resultCnt); }

		// 회원 간편가입 연동 정보 수정
		resultCnt = memberCertifyCommandRepository.updateMbrIdCntcDeleteYn(mbrNo);
		if (log.isInfoEnabled()) {
			log.info("> updateMbrIdCntcDeleteYn COUNT : {}", resultCnt);
		}

		// 회원 나의 사이즈 삭제
//		memberACtivityCommandRepository.deleteMbrSizeClfcDetail(mbrNo, "");
//		memberACtivityCommandRepository.deleteMbrSizeClfc(mbrNo, "");

		// step 2. 회원 마스터 정보 수정
		mbr.setMbrStatCd(MemberStatCd.SECSN.toString());
		memberBaseCommandRepository.updateMemberSecession(mbr);
	}

	public void updateFoMemberAddr(MemberFoDTO dto) {
		Mbr mbr = dto.getMbr();
		//행정동코드 추가
//		String audCd = memberBaseSelectRepository.getZipcodeAddongCd(dto.getMbr().getMbrPostNo());
//		mbr.setAudCd(audCd);
		
		memberBaseCommandRepository.updateFoMemberAddr(mbr);
	}

	/**
	 * 회원가입.
	 *
	 * @param mbr
	 *            [설명]
	 * @return String [설명]
	 * @
	 *             the exception
	 * @since 2015. 4. 15
	 */
	public Mbr addMember(Mbr mbr)  {
		String mbrNo = getIdGenService().generateDBNumber(sqlSession1, "SQ_MBR", "MB", DatabaseType.ORACLE);
		mbr.setMbrNo(mbrNo);
		// 사용자번호 + 사용자ID + 이메일 - >tokenid
		String tokenId = mbrNo + mbr.getMbrId() + mbr.getMbrEmail();
		mbr.setEmailRecptnRejectToknId(IdGenService.generateSHA256(tokenId));
		memberBaseCommandRepository.insertMember(mbr);
		return mbr;
	}

	/**
	 * 회원 비밀번호 수정.
	 *
	 * @param dto
	 *            [설명]
	 * @
	 *             the exception
	 * @since 2015. 4. 16
	 */
	public void updateMemberPasswordFo(MemberFoDTO dto, String loginId, String encMbrPw)  {
		Mbr mbr = dto.getMbr();
		mbr.setUdterId(loginId);
		mbr.setMbrPw(encMbrPw);
		mbr.setTmprPwYn("Y");

		memberBaseCommandRepository.updateMemberPassword(mbr);
	}

	public MemberFoResult selectMemberErp(MemberFoDTO dto){
		return memberBaseSelectRepository.selectMemberForErp(dto);
	}

	/**
	 * 회원 정보 변경.
	 *
	 * @param param
	 *            [설명]
	 * @
	 *             the exception
	 */
	public int updateMemberCampagin(Map<String, String> map)  {
		return memberBaseCommandRepository.updateMemberCampagin(map);
	}

	/**
	 * 로그인 실패 카운트 업데이트.
	 *
	 * @param mbr [설명]
	 * @return Int [설명]
	 * @since 2015. 4. 15
	 */
	public int updateLoginFailCount(Mbr mbr){
		return memberBaseCommandRepository.updateLoginFailCount(mbr);
	}

	public void addMbrLoginLog(SystemPK pk, MbrLoginLog mbrLoginLog)  {
		String osCd = pk.getDevicePlatform();
		String mobileAppSectCd = pk.getApp();

		mbrLoginLog.setOsCd(osCd);
		mbrLoginLog.setMobileAppSectCd(mobileAppSectCd);

		memberBaseCommandRepository.insertMemberLoginLog(mbrLoginLog);
	}

	/**
	 * 회원 상태 변경.
	 *
	 * @param mbr [설명]
	 * @param loginId [설명]
	 * @ the exception
	 * @since 2015. 4. 28
	 */
	public void updateMemberStatus(Mbr mbr, String loginId)  {
		mbr.setUdterId(loginId);
		memberBaseCommandRepository.updateMemberStatus(mbr);
	}

	/**
	 * 회원 정보 변경.
	 *
	 * @param dto [설명]
	 * @param loginId [설명]
	 * @ the exception
	 * @since 2015. 4. 28
	 */
	public void updateMemberBy(MemberBoDTO dto, String loginId)  {
		Mbr mParam = dto.getMbr();
		mParam.setUdterId(loginId);
		//행정동코드 추가
//		String audCd = memberBaseSelectRepository.getZipcodeAddongCd(dto.getMbr().getMbrPostNo());
//		mParam.setAudCd(audCd);

		memberBaseCommandRepository.updateMember(mParam);
	}

	/**
	 * 회원가입
	 *
	 * @param mbr
	 * @return
	 * @
	 */
	public int inserJoinMember(SystemPK systemPK, Mbr mbr)  {

		// 변수 설정
		this.extractSettingJoinMemberInfo(systemPK, mbr);

		String mbrNo = getIdGenService().generateDBNumber(sqlSession1, "SQ_MBR", "MB",DatabaseType.ORACLE);
		mbr.setMbrNo(mbrNo);

		mbr.setFrgnrYn(MemberEnum.NO.toString());

		//사용자번호 + 사용자ID + 이메일  - >tokenid
		String tokenId = mbrNo + mbr.getMbrId() + mbr.getMbrEmail();
		mbr.setEmailRecptnRejectToknId(IdGenService.generateSHA256(tokenId));

		int insertCnt = memberBaseCommandRepository.insertMember(mbr);

		return insertCnt;
	}

	public void updateFoMember(MemberFoDTO dto)  {
		Mbr mbr = dto.getMbr();

		memberBaseCommandRepository.updateFoMember(mbr);
	}

	/**
	 * 회원 정보 수정만 사용
	 * 
	 * @param dto
	 * @
	 */
	public void updateMemberChoiceInfo(MemberFoDTO dto)  {
		Mbr mbr = dto.getMbr();

		memberBaseCommandRepository.updateMemberChoiceInfo(mbr);
	}

	private void extractSettingJoinMemberInfo(SystemPK systemPK, Mbr mbr)  {

		String mallId = systemPK.getMall();
		String lang = systemPK.getLang();
		String device = systemPK.getDevice();

		mbr.setJoinMallId(mallId);
		mbr.setJoinLangCd(lang);
		mbr.setJoinDvcCd(device);
		mbr.setMbrStatCd(MemberStatCd.ACT.toString());
		mbr.setMbrAtrbCd(MemberAtrbCd.GNRL_MBR.toString());

		// TODO 국가코드 등 컬럼 추가분 설정 필요

		// CSS 몰ID로 임시 구분
		if (MallIdEnum.CSS_MALL_ID.toString().equals(mallId)) {
			mbr.setMbrTpCd(MemberTpCd.UNITY_MBR.toString());
		} else {
			mbr.setMbrTpCd(MemberTpCd.ONLINE_MBR.toString());
		}

		//암호화
		String sha256Pw = IdGenService.generateSHA256(mbr.getMbrPw());
		mbr.setMbrPw(sha256Pw);

		// (sms / e-mail / dm 여부)
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();

		if (!StringUtils.isEmpty(mbr.getEmailRecptnAgrYn()) && MemberEnum.YES.toString().equals(mbr.getEmailRecptnAgrYn())) {
			mbr.setEmailRecptnAgrYn(MemberEnum.YES.toString());
			mbr.setLastEmailRecptnAgrDt(today);
		}
		else {
			mbr.setEmailRecptnAgrYn(MemberEnum.NO.toString());
		}
		if (!StringUtils.isEmpty(mbr.getSmsRecptnAgrYn()) && MemberEnum.YES.toString().equals(mbr.getSmsRecptnAgrYn())) {
			mbr.setSmsRecptnAgrYn(MemberEnum.YES.toString());
			mbr.setLastSmsRecptnAgrDt(today);
		}
		else {
			mbr.setSmsRecptnAgrYn(MemberEnum.NO.toString());
		}
		if (!StringUtils.isEmpty(mbr.getDmRecptnAgrYn()) && MemberEnum.YES.toString().equals(mbr.getDmRecptnAgrYn())) {
			mbr.setDmRecptnAgrYn(MemberEnum.YES.toString());
			mbr.setLastDmRecptnAgrDt(today);
		}
		else {
			mbr.setDmRecptnAgrYn(MemberEnum.NO.toString());
		}
	}


	/**
	 * 회원 개인정보 변경 이력 등록 정보 설정.
	 *
	 * @param mbrNo 개인정보 변경 회원번호
	 * @param modResnDscr 개인정보 변경 사유
	 * @param loginId 시스템 접근자 ID (FRONT - mbr_no, BO/CS/PO - loginId(admin_id))
	 * @param isMember 변경주체의 회원 여부
	 * @return Mbr psnl info mod hist [설명]
	 * @ the exception
	 * @since 2015. 4. 16
	 */
	public MbrPsnlInfoModHist setMbrPsnlInfoModHist(String mbrNo, String modResnDscr, String loginId, boolean isMember) {

		long psnlInfoModHistSn = getIdGenService().generateDBSequence(sqlSession1, "sq_mbr_psnl_info_mod_hist", DatabaseType.ORACLE);

		MbrPsnlInfoModHist mpim = new MbrPsnlInfoModHist();
		mpim.setPsnlInfoModHistSn(psnlInfoModHistSn);              // 개인정보 변경 이력 일련번호
		mpim.setMbrNo(mbrNo);                                       // 회원번호
		mpim.setModResnDscr(modResnDscr);                         // 변경 사유 설명
		mpim.setErpTrnsmisYn("N");       // ERP 전송 여부
		mpim.setRegtrId(loginId);
		mpim.setUdterId(loginId);

		if(isMember){
			mpim.setModMbrNo(loginId);                       // 변경 회원 번호
		} else {
			mpim.setModAdminId(loginId);                    // 변경 관리자 ID
		}

		return mpim;
	}

	/**
	 * 회원 상세 정보 변경 이력 등록.
	 *
	 * @param afterMbr [설명]
	 * @param mpim [설명]
	 * @param codeArr [설명]
	 * @param isReg [설명]
	 * @ the exception
	 * @since 2015. 4. 16
	 */
	public void insertPersonalInfoForMbr(MbrPsnlInfoModHist mpim, String[] codeArr, Mbr afterMbr) {
		try{
			// 기존 정보 조회
	    	Mbr beforeMbr = mbrRepository.selectMbr(afterMbr);
		
			// AFTER
			Map<String, Object> afterMap = makeAfterMap(afterMbr);
		
			// BEFORE
			Map<String, Object> beforeMap = makeAfterMap(beforeMbr);
		
			this.insertPersonalInfo(afterMap, beforeMap, mpim, codeArr);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 온라인회원등급 변경
	 * 회원등급 등록 전 기존의 등급이 등급적용시작일자, 등급적용종료일자 가 중복되는 건에 대해서
	 * 등급적용일자를 새로 등록할 등급적용시작일자의 전일자로 변경.
	 * 
	 * @param mbrGrd
	 * @return
	 * @throws Exception
	 */

	public void updateOnlineGradeInfoBeforeInsert(MbrGrd mbrGrd){
		memberBaseCommandRepository.updateOnlineGradeInfoBeforeInsert(mbrGrd);
	}
	
	/**
	 * 온라인회원등급 등록
	 * 
	 * @param mbrGrd
	 * @return
	 * @throws Exception
	 */

	public void insertOnlineGradeInfo(MbrGrd mbrGrd){
		memberBaseCommandRepository.insertOnlineGradeInfo(mbrGrd);
	}
	
	public void insertOnlineGradeInfoLogin(MbrGrd mbrGrd){
		memberBaseCommandRepository.insertOnlineGradeInfoLogin(mbrGrd);
	}
	
	/**
	 * 휴면 해제 처리
	 *
	 * @param mbr [설명]
	 * @return Int [설명]
	 * @since 2018. 5. 29
	 */
	public int updateMemberForReleaseDrmncy(Mbr mbr){
		// 개인정보이용수정 등록
		MbrPsnlInfoModHist mpim = this.setMbrPsnlInfoModHist(mbr.getMbrNo(), "", mbr.getMbrNo(), true);
		String[] codeArr = {
				MbrPsnlInfoUdtSectCd.MBR_STAT_CD.toString()										// 회원 상태 코드
		};
		
		mpim.setModLcSectCd(MemberModLcSectCd.ONLNE_MALL.toString());
		
		//변경이력 insert
		this.insertPersonalInfoForMbr(mpim, codeArr, mbr);
		
		return memberBaseCommandRepository.updateMemberForReleaseDrmncy(mbr);
	}
	
	/**
	 * ERP_CSTMR_NO 삭제 처리
	 *
	 * @param mbr [설명]
	 * @return Int [설명]
	 * @since 2018. 7. 18
	 */
	public int updateMemberForErpCstmrNoInit(Mbr mbr){
		return memberBaseCommandRepository.updateMemberForErpCstmrNoInit(mbr);
	}
	
	/**
	 * ERP 회원 정보를 받아서 온라인 회원으로 등록한다.
	 * 
	 * @param request
	 * @param pk
	 * @param memberInformationSDO
	 * @param mbr
	 */
	public void insertOnlineMemberByErp(HttpServletRequest request, SystemPK systemPK, MemberInformationSDO memberInformationSDO, Mbr mbr) {
		// ERP에서 받은 정보를 온라인 회원으로 등록 및 변경 처리를 하기 위한 정보 세팅.
		setOnlineMemberInfoByErp(request, systemPK, memberInformationSDO, mbr);

		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		
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
		if(joinDt == null) {
			joinDt = today;
		}
		mbr.setJoinDt(joinDt);
		
		mbr.setJoinLangCd(systemPK.getLang());
		mbr.setJoinDvcCd(systemPK.getDevice());
		mbr.setMbrTpCd(MemberTpCd.ONLINE_MBR.toString());
		//정보성 알람 수신방법 디폴트====>카카오톡
		mbr.setInforNtcnRecptnTpCd(MemberEnum.MemberInforRecptnCd.NTCN_TAK.toString());
		
		MemberFoDTO dto = new MemberFoDTO();
		dto.setMbr(mbr);
		
		// 회원가입시 가입국가코드에 들어가는 국가코드가 코드테이블에 있는 국가코드와 상이
		Locale locale = LocaleService.getCurrentLocale(request);
		String joinNationCd = locale.getCountry();

		if(StringService.isNotEmpty(joinNationCd)){
			dto.getMbr().setJoinNationCd(joinNationCd.toLowerCase());
		} else {
			dto.getMbr().setJoinNationCd("kr");
		}

		// 국문용 자동세팅
		dto.getMbr().setMobilNationCd("kr");
		dto.getMbr().setMobilNationNo("82");
		if(dto.getMbr().getMbrAddrSectCd() != null) {
			dto.getMbr().setMbrAddrNationCd("kr");
			dto.getMbr().setMbrAddrTpCd("OWNHOM");
		}
		if(dto.getMbr().getTelAreaNo() != null && !"".equals(mbr.getTelAreaNo())) {
			dto.getMbr().setTelNationNo("82");
		}
		
		// ERP 회원 등급
//		String memberGrade = "";
//		if(memberInformationSDO.getMemberGradeList() != null && !memberInformationSDO.getMemberGradeList().isEmpty()) {
//			for(MemberGradeInformationSDO memberGradeInformationSDO : memberInformationSDO.getMemberGradeList()) {
//				if("X".equals(memberGradeInformationSDO.getBrandDiv())) {
//					memberGrade = memberGradeInformationSDO.getMemberGrade();
//					break;
//				}
//			}
//		}
//		dto.setMemberGrade(memberGrade);
		
		// 약관 동의 정보 세팅
		List<String> stplatList = new ArrayList<String>();
		stplatList.add(MemberEnum.StplatCd.ONLNE_SITE_USEF_STPLAT.toString());
		stplatList.add(MemberEnum.StplatCd.PSNL_INFO_TRTMNT_POLCY.toString());

		List<String> stplatYnList = new ArrayList<String>();
		stplatYnList.add("Y");
		stplatYnList.add("Y");
		stplatYnList.add(dto.getMarktPsnlInfoColctUsefAgrYn());
		
		dto.setStplatCd(stplatList);
		dto.setStplatIemAgrYn(stplatYnList);
		
		// 회원 인증 정보 세팅
		MbrCrtfc crtfc = new MbrCrtfc();
		crtfc.setRlnmCrtfcDi(memberInformationSDO.getIpinDi());
		crtfc.setRlnmCrtfcCi(memberInformationSDO.getIpinCi());
		
		if("CEL".equals(memberInformationSDO.getAuthRealname())) {
			crtfc.setMbrCrtfcTpCd(UnityMbrCrtfcSectCd.SLF_CRTFC.toString());
		}
		else {
			crtfc.setMbrCrtfcTpCd(UnityMbrCrtfcSectCd.IPIN_CRTFC.toString());
		}
		crtfc.setMbrCrtfcDate(joinDt);
		dto.setMbrCrtfc(crtfc);
		dto.getMbr().setUnityMbrCrtfcDt(joinDt);
		
		// 인증 정보를 저장하기 위한 flag
		dto.setSignupType(true);
		
		memberJoinFOComponent.addMember(systemPK, dto);

		// security 설정 자동 로그인
		memberAuthFOComponent.mbrAuthentication();
	}
	
	/**
	 * ERP 회원 정보를 받아서 온라인 회원으로 업데이트한다.
	 * 
	 * @param memberInformationSDO
	 * @param mbr
	 */
	public void updateOnlineMemberByErp(HttpServletRequest request, SystemPK systemPK, MemberInformationSDO memberInformationSDO, Mbr mbr) {
		try {
			// ERP에서 받은 정보를 온라인 회원으로 등록 및 변경 처리를 하기 위한 정보 세팅.
			setOnlineMemberInfoByErp(request, systemPK, memberInformationSDO, mbr);

			// 국문용 자동세팅
			mbr.setMobilNationCd("kr");
			mbr.setMobilNationNo("82");
			if(mbr.getMbrAddrSectCd() != null) {
				mbr.setMbrAddrNationCd("kr");
				mbr.setMbrAddrTpCd("OWNHOM");
			}
			if(mbr.getTelAreaNo() != null && !"".equals(mbr.getTelAreaNo())) {
				mbr.setTelNationNo("82");
			}
			
			// 개인정보이용수정 등록
			MbrPsnlInfoModHist mpim = this.setMbrPsnlInfoModHist(mbr.getMbrNo(), "", "", false);
			String[] codeArr = {
					MbrPsnlInfoUdtSectCd.MBR_NM.toString()										// 이름
					, MbrPsnlInfoUdtSectCd.MBR_SEX_SECT_CD.toString()				// 회원 성별 구분 코드
					, MbrPsnlInfoUdtSectCd.MBR_BRTHDY.toString()							// 회원 생년월일
					, MbrPsnlInfoUdtSectCd.MBR_BRTHDY_SLRCLD_YN.toString()	// 회원 생년월일 양력 여부
					, MbrPsnlInfoUdtSectCd.PHON_NO.toString()									// 유선전화번호
					, MbrPsnlInfoUdtSectCd.MOBIL_NO.toString()									// 휴대전화번호
					, MbrPsnlInfoUdtSectCd.MBR_POST_NO.toString()							// 회원 우편번호
					, MbrPsnlInfoUdtSectCd.MBR_ADDR.toString()								// 주소
					, MbrPsnlInfoUdtSectCd.MBR_EMAIL.toString()								// 이메일
					, MbrPsnlInfoUdtSectCd.SMS_RECPTN_AGR_YN.toString()			// SMS수신여부
					, MbrPsnlInfoUdtSectCd.EMAIL_RECPTN_AGR_YN.toString()		// E-mail수신동의여부
					, MbrPsnlInfoUdtSectCd.MBR_STAT_CD.toString()					// 회원 상태 코드
			};
			
			mpim.setModLcSectCd(MemberModLcSectCd.OFLNE_BRND_SHOP.toString());	//OFLNE_BRND_SHOP
			
			//변경이력 insert
			this.insertPersonalInfoForMbr(mpim, codeArr, mbr);
			
			memberBaseCommandRepository.updateMemberChoiceInfo(mbr);
			
			// // 회원 인증 정보 세팅(인증 정보가 없는 경우 저장)
			String unityMbrCrtfcSectCd = "";
			if("CEL".equals(memberInformationSDO.getAuthRealname())) {
				unityMbrCrtfcSectCd = UnityMbrCrtfcSectCd.SLF_CRTFC.toString();
			}
			else {
				unityMbrCrtfcSectCd = UnityMbrCrtfcSectCd.IPIN_CRTFC.toString();
			}
			
			// 온라인 쪽에 회원 인증 정보가 없는 경우 등록.
			MemberBoResult mcResult = memberCertifySelectService.selectMemberCertification(mbr.getMbrNo(), unityMbrCrtfcSectCd, MemberEnum.YES.toString(), MemberEnum.NO.toString());
			if( mcResult == null ) {
				MemberFoDTO dto = new MemberFoDTO();
				MbrCrtfc crtfc = new MbrCrtfc();
				crtfc.setRlnmCrtfcDi(memberInformationSDO.getIpinDi());
				crtfc.setRlnmCrtfcCi(memberInformationSDO.getIpinCi());
				
				if("CEL".equals(memberInformationSDO.getAuthRealname())) {
					crtfc.setMbrCrtfcTpCd(UnityMbrCrtfcSectCd.SLF_CRTFC.toString());
				}
				else {
					crtfc.setMbrCrtfcTpCd(UnityMbrCrtfcSectCd.IPIN_CRTFC.toString());
				}
				crtfc.setMbrNo(mbr.getMbrNo());
				crtfc.setRlnmCrtfcResultCd(MemberEnum.YES.toString());
				crtfc.setRlnmCrtfcVer("1");
				crtfc.setMbrCrtfcYn(MemberEnum.YES.toString());
				
				// 회원 가입 일시를 회원 인증 일시로 등록.
				Calendar calendar = Calendar.getInstance();
				Date today = calendar.getTime();
				
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
				if(joinDt == null) {
					joinDt = today;
				}
				crtfc.setMbrCrtfcDate(joinDt);
				
				dto.setMbrCrtfc(crtfc);
				memberCertifyCommandService.inesrtMemberCrtfc(dto);
			}

		}
		catch (Exception e) {
			log.error("error.", e);
		}
	}
	
	// ERP에서 받은 정보를 온라인 회원으로 등록 및 변경 처리를 하기 위한 정보 세팅.
	private void setOnlineMemberInfoByErp(HttpServletRequest request, SystemPK systemPK, MemberInformationSDO memberInformationSDO, Mbr mbr) {
		// 회원 상태 코드
		this.setMbrStatCd(memberInformationSDO.getStatus(), mbr);
		
		// 회원 속성 코드
		this.setMbrAtrbCd(memberInformationSDO.getEmpNo(), mbr);
		
		// 회원 명
		mbr.setMbrNm(memberInformationSDO.getName());
		
		// 회원 생년월일 양력 여부
		this.setMbrBrthdySlrcldYn(memberInformationSDO.getBirthCal(), mbr);
		
		// 회원 생년월일
		if(memberInformationSDO.getBirthDay() != null) {
			mbr.setMbrBrthdy(memberInformationSDO.getBirthDay().replaceAll("-", ""));
		}
		
		// 회원 성별 구분 코드
		this.setMbrSexSectCd(memberInformationSDO.getGender(), mbr);

		// 외국인 여부
		this.setFrgnrYn(memberInformationSDO.getAuthType(), mbr);
		
		// 회원 사원 번호
		mbr.setMbrEmplNo(memberInformationSDO.getEmpNo());
		
		// 회원 ERP 비밀번호
		mbr.setMbrErpPw(memberInformationSDO.getPasswd());
		
		// 회원 이메일
		mbr.setMbrEmail(memberInformationSDO.getEmail());
		
		// 휴대전화
		String[] mobileNo = toArrayForTel(memberInformationSDO.getPhoneMobile());
		if(mobileNo != null) {
			mbr.setMobilAreaNo(mobileNo[0]);
			mbr.setMobilTlofNo(mobileNo[1]);
			mbr.setMobilTlofWthnNo(mobileNo[2]);
			mobileNo = null;	// 초기화
		}
		
		// 전화
		String[] telNo = toArrayForTel(memberInformationSDO.getPhoneHome());
		if(telNo != null) {
			mbr.setTelAreaNo(telNo[0]);
			mbr.setTelTlofNo(telNo[1]);
			mbr.setTelTlofWthnNo(telNo[2]);
			telNo = null;	// 초기화
		}
		
		// 유입 일련번호
		Long inflowSn = null;
		// 가입 몰 ID
		String joinMallId = null;
		String joinFrom = memberInformationSDO.getJoinFrom();
		
		if(joinFrom != null) {
			joinFrom = joinFrom.trim();
				joinFrom = joinFrom.replace("http://", "").replace("https://", "");
				if(joinFrom.indexOf("/") > 0) {
					joinFrom = joinFrom.substring(0, joinFrom.indexOf("/"));	
				}
				if(joinFrom.indexOf(":") > 0) {
					joinFrom = joinFrom.substring(0, joinFrom.indexOf(":"));	
				}
		
			SysInflow sysInflow = new SysInflow();
			sysInflow.setMallId(systemPK.getMall());
			sysInflow.setExcutRemNm(joinFrom);
			sysInflow = memberBaseSelectRepository.selectMbrSysInflow(sysInflow);
			
			if(sysInflow != null && sysInflow.getInflowSn() != null) {
				if(sysInflow.getInflowSn() > 0) {
					inflowSn = sysInflow.getInflowSn();
					joinMallId = sysInflow.getMallId();
				}
			}
		}

		mbr.setInflowSn(inflowSn);
		
		// 가입 회원 IP
		if(StringService.isEmpty(mbr.getJoinMbrIp())) {
			String ipAddress = "";
			if(StringService.isNotEmpty(memberInformationSDO.getLastLoginIp())) {
				ipAddress = memberInformationSDO.getLastLoginIp();
			}
			else {
				ipAddress = request.getRemoteAddr();
			}
			mbr.setJoinMbrIp(ipAddress);
		}
		
		if(joinMallId == null || "".equals(joinMallId)) {
			joinMallId = systemPK.getMall();
		}
		mbr.setJoinMallId(joinMallId);
		
		// SSO Group Code
		if(mbr.getSsoGrpCd() == null || "".equals(mbr.getSsoGrpCd())) {
			mbr.setSsoGrpCd(memberCertifySelectService.getSsoGrpCd(joinMallId));
		}
		
		// 회원 우편번호
		mbr.setMbrPostNo(memberInformationSDO.getZipcode());
		
		// 회원 기본주소
		if(memberInformationSDO.getAddressRoad() != null) {
			mbr.setMbrBaseAddr(memberInformationSDO.getAddressRoad());
			mbr.setMbrAddrSectCd("RD_ADDR");
		}
		else {
			mbr.setMbrBaseAddr(memberInformationSDO.getAddress1());
			if(memberInformationSDO.getAddress1() != null && !"".equals(memberInformationSDO.getAddress1())) {
				mbr.setMbrAddrSectCd("LNM_ADDR");	
			}
		}
		
		// 회원 상세주소
		mbr.setMbrDetailAddr(memberInformationSDO.getAddress2());

		// 홍보 마케팅 목적 개인정보 수집 및 이용 동의 (선택)
		this.setRecptnAgrYn(memberInformationSDO, mbr);
		
		// ERP 고객번호
		mbr.setErpCstmrNo(memberInformationSDO.getCid());
	}
	
    /**
	 * 임직원 변경 처리
	 *
	 * @param mbr [설명]
	 * @return Int [설명]
	 * @since 2017. 7. 27
	 */
	public int updateMbrForEmp(Mbr mbr) {
		return memberBaseCommandRepository.updateMbrForEmp(mbr);
	}
	
	// 회원 상태 코드 변환 세팅(ERP -> 온라인)
	public void setMbrStatCd(String statCd, Mbr mbr) {
		String mbrStatCd = "";
		
		if(statCd == null || "".equals(statCd)) {
			mbrStatCd = "";
		}
		else if("R".equals(statCd)) {
			mbrStatCd = MemberEnum.MemberStatCd.ACT.toString();
		}
		else if("D".equals(statCd)) {
			mbrStatCd = MemberEnum.MemberStatCd.SECSN.toString();
		}
		else if("S".equals(statCd)) {
			mbrStatCd = MemberEnum.MemberStatCd.DRMNCY.toString();
		}
		else if("B".equals(statCd)) {
			mbrStatCd = MemberEnum.MemberStatCd.BLCLST_A.toString();
		}
		else if("L".equals(statCd)) {
			mbrStatCd = MemberEnum.MemberStatCd.BLCLST_B.toString();
		}
		
		if(!"".equals(mbrStatCd)) {
			mbr.setMbrStatCd(mbrStatCd);
		}
	}
	
	// 회원 속성 코드 변환 세팅(ERP -> 온라인)
	private void setMbrAtrbCd(String empNo, Mbr mbr) {
		String mbrAtrbCd = "";
		
		if(empNo == null || "".equals(empNo)) {
			mbrAtrbCd = MemberEnum.MemberAtrbCd.GNRL_MBR.toString();
			mbr.setMbrEmplNo(null);
		}
		else {
			mbrAtrbCd = MemberEnum.MemberAtrbCd.EMP.toString();
			mbr.setMbrEmplNo(empNo);
		}
		
		if(!"".equals(mbrAtrbCd)) {
			mbr.setMbrAtrbCd(mbrAtrbCd);
		}
	}
	
	// 회원 생년월일 양력 여부 변환 세팅(ERP -> 온라인)
	private void setMbrBrthdySlrcldYn(String brthdySlrcldYn, Mbr mbr) {
		String mbrBrthdySlrcldYn = "";
		
		if(brthdySlrcldYn == null || "".equals(brthdySlrcldYn)) {
			mbrBrthdySlrcldYn = "";
		}
		else if("S".equals(brthdySlrcldYn)) {
			mbrBrthdySlrcldYn = MemberEnum.YES.toString();
		}
		else if("L".equals(brthdySlrcldYn)) {
			mbrBrthdySlrcldYn = MemberEnum.NO.toString();
		}
		
		if(!"".equals(mbrBrthdySlrcldYn)) {
			mbr.setMbrBrthdySlrcldYn(mbrBrthdySlrcldYn);
		}
	}
	
	// 회원 성별 구분 코드 변환 세팅(ERP -> 온라인)
	private void setMbrSexSectCd(String sexSectCd, Mbr mbr) {
		String mbrSexSectCd = "";
		
		if(sexSectCd == null || "".equals(sexSectCd)) {
			mbrSexSectCd = "";
		}
		else if("M".equals(sexSectCd)) {
			mbrSexSectCd = "MALE";
		}
		else if("F".equals(sexSectCd)) {
			mbrSexSectCd = "FEMALE";
		}
		
		if(!"".equals(mbrSexSectCd)) {
			mbr.setMbrSexSectCd(mbrSexSectCd);
		}
	}
	
	// 외국인 여부 변환 세팅(ERP -> 온라인)
	private void setFrgnrYn(String authType, Mbr mbr) {
		String frgnrYn = "";
		
		if(authType == null || "".equals(authType)) {
			frgnrYn = "";
		}
		else if("L".equals(authType)) {
			frgnrYn = MemberEnum.NO.toString();
		}
		else if("F".equals(authType)) {
			frgnrYn = MemberEnum.YES.toString();
		}
		
		if(!"".equals(frgnrYn)) {
			mbr.setFrgnrYn(frgnrYn);
		}
	}
	
	// 수신 동의 여부 변환 세팅(ERP -> 온라인)
	private void setRecptnAgrYn(MemberInformationSDO memberInformationSDO, Mbr mbr) {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		
		// 온라인의 정보와 ERP의 정보가 동일하면
		if(mbr.getEmailRecptnAgrYn() != null && mbr.getSmsRecptnAgrYn() != null
				&& memberInformationSDO.getRecvEmail() != null && memberInformationSDO.getRecvSms() != null
				&& memberInformationSDO.getRecvEmail().equals(mbr.getEmailRecptnAgrYn()) && memberInformationSDO.getRecvSms().equals(mbr.getSmsRecptnAgrYn())
				) {
//			mbr.setEmailRecptnAgrYn(memberInformationSDO.getRecvEmail());
//			mbr.setSmsRecptnAgrYn(memberInformationSDO.getRecvSms());
		}
		// 온라인의 정보와 ERP의 정보가 동일하지 않으면
		else {
			// 이메일 수신 동의 여부
			if(memberInformationSDO.getRecvEmail() == null) {
				mbr.setEmailRecptnAgrYn(MemberEnum.NO.toString());
			}
			else {
				mbr.setEmailRecptnAgrYn(memberInformationSDO.getRecvEmail());
			}
			
			// SMS 수신 동의 여부
			if(memberInformationSDO.getRecvSms() == null) {
				mbr.setSmsRecptnAgrYn(MemberEnum.NO.toString());
			}
			else {
				mbr.setSmsRecptnAgrYn(memberInformationSDO.getRecvSms());
			}
			
			String recvDate = memberInformationSDO.getRecvDate();
			Date recv = null;
			if(recvDate != null) {
				try {
					recv = new Date();
					recv.setTime(Long.parseLong(recvDate));
				}
				catch(Exception e) {
					recv = null;
				}
			}
			if(recv == null) {
				recv = today;
			}
			
			if(mbr.getLastEmailRecptnAgrDt() == null || MemberEnum.YES.toString().equals(memberInformationSDO.getRecvEmail())) {
				mbr.setLastEmailRecptnAgrDt(recv);
			}
			if(mbr.getLastSmsRecptnAgrDt() == null || MemberEnum.YES.toString().equals(memberInformationSDO.getRecvSms())) {
				mbr.setLastSmsRecptnAgrDt(recv);
			}
		}
		
		if(mbr.getDmRecptnAgrYn() == null) {
			// 우편 수신동의여부
			mbr.setDmRecptnAgrYn(MemberEnum.NO.toString());
		}
		if(mbr.getTmRecptnAgrYn() == null) {
			// 전화 수신동의여부
			mbr.setTmRecptnAgrYn(MemberEnum.NO.toString());
		}
		if(mbr.getLastDmRecptnAgrDt() == null) {
			mbr.setLastDmRecptnAgrDt(today);
		}
		if(mbr.getLastTmRecptnAgrDt() == null) {
			mbr.setLastTmRecptnAgrDt(today);
		}

	}
	
	// 전화번호, 휴대폰번호 나눠서 반환하기
	private String[] toArrayForTel(String tel) {
		if(tel == null || "".equals(tel)) {
			return null;
		}
		String tmp = tel.replaceAll("-", "");
		if(tmp == null || "".equals(tmp)) {
			return null;
		}
		String[] returnArray = new String[3];
		
		if( "02".equals(tmp.substring(0, 2)) ) {
			if(tmp.substring(2).length() == 6) {
				returnArray[0] = tmp.substring(0, 2);
				returnArray[1] = tmp.substring(2, 5);
				returnArray[2] = tmp.substring(5);
			}
			else if(tmp.substring(2).length() == 7) {
				returnArray[0] = tmp.substring(0, 2);
				returnArray[1] = tmp.substring(2, 5);
				returnArray[2] = tmp.substring(5);
			}
			else if(tmp.substring(2).length() == 8) {
				returnArray[0] = tmp.substring(0, 2);
				returnArray[1] = tmp.substring(2, 6);
				returnArray[2] = tmp.substring(6);
			}
		}
		else {
			if( tmp.substring(0, 2).length() == 12 ) {
				if(tmp.substring(3).length() == 7) {
					returnArray[0] = tmp.substring(0, 4);
					returnArray[1] = tmp.substring(4, 7);
					returnArray[2] = tmp.substring(7);
				}
				else if(tmp.substring(3).length() == 8) {
					returnArray[0] = tmp.substring(0, 4);
					returnArray[1] = tmp.substring(4, 8);
					returnArray[2] = tmp.substring(8);
				}
				else if(tmp.substring(3).length() == 9) {
					returnArray[0] = tmp.substring(0, 4);
					returnArray[1] = tmp.substring(4, 8);
					returnArray[2] = tmp.substring(8);
				}
			}
			else {
				if(tmp.substring(3).length() == 6) {
					returnArray[0] = tmp.substring(0, 3);
					returnArray[1] = tmp.substring(3, 6);
					returnArray[2] = tmp.substring(6);
				}
				else if(tmp.substring(3).length() == 7) {
					returnArray[0] = tmp.substring(0, 3);
					returnArray[1] = tmp.substring(3, 6);
					returnArray[2] = tmp.substring(6);
				}
				else if(tmp.substring(3).length() == 8) {
					returnArray[0] = tmp.substring(0, 3);
					returnArray[1] = tmp.substring(3, 7);
					returnArray[2] = tmp.substring(7);
				}
			}
		}
		
		return returnArray;
	}
	
}
