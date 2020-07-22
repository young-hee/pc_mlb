/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 6. 10       
 */
package com.plgrim.ncp.commons.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.repository.mbr.MbrRepository;
import com.plgrim.ncp.base.repository.sys.SysSmsEmailTxtRepository;
import com.plgrim.ncp.commons.data.SysLsmsDTO;
import com.plgrim.ncp.commons.data.SysLsmsDataDTO;
import com.plgrim.ncp.commons.repository.SysLsmsRepository;
import com.plgrim.ncp.commons.result.SysLsmsResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * SMS/LMS 발송 Service
 * @author ed
 *
 */
@Service
public class SysLsmsService extends AbstractService {

	@Autowired
    SysLsmsRepository sysLsmsRepository; // SMS/LMS 발송 DAO

	@Autowired
	MbrRepository mbrRepository;
	
	@Autowired
	SysSmsEmailTxtRepository sysSmsEmailTxtRepository; // 자주 사용하는 문구 DAO
	
	@Autowired
	InterfaceApiCommon interfaceApiCommon;

	/**
	 * SMS/LMS 발송 처리.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void sendSysLsms( SysLsmsDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		// Adapter Header 를 세팅한다.
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
		adapterHeader.setMallId("DXM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");

		// TODO 문자 솔루션 적용
//		List<HumusonSDO> smsList = new ArrayList<>();
//
//		if(paramData != null) {
//			if(paramData.getGridList() != null) {
//				// 데이터 설정
//				for (SysLsmsDataDTO item : paramData.getGridList()) {
//					// 삭제된 행은 보내지 않음
//					if ("D".equals(item.getGridCudTag())) {
//						continue;
//					}
//
//					//대한민국 국제번호 삭제
//					String phoneNumber = item.getTranPhone().trim().replaceAll("-", "");
//					if (phoneNumber.length() > 1) {
//						String chkNum = phoneNumber.substring(0, 2);
//						if ("82".equals(chkNum)) {
//							phoneNumber = phoneNumber.substring(2);
//						}
//					}
//
//					String callerId = SysLsmsService.class.getName() + ".sendSysLsms";
//					String tranId = StringService.isNotEmpty(item.getTranId()) ? item.getTranId() : "NMBR";
//					String mallId = "DXM";
//					String tranMsg = paramData.getFormData().getTranMsg();
//
//					if (StringService.isNotEmpty(tranId) && !"NMBR".equals(tranId)) {
//						Mbr mbr = new Mbr();
//						mbr.setMbrNo(tranId);
//
//						mbr = mbrRepository.selectMbr(mbr);
//						if (mbr != null && StringService.isNotEmpty(mbr.getJoinMallId())) {
//							mallId = mbr.getJoinMallId();
//						}
//					}
//
//					HumusonSDO humusonSDO = humusonCommonService.getSmsData(tranId, phoneNumber.replaceAll("\\D", ""), mallId,
//							null, callerId, tranMsg, null, null);
//
//					humusonSDO.setCallback(paramData.getFormData().getTranCallback());
//					smsList.add(humusonSDO);
//				}
//
//				humusonCommonService.sendSmsMms(smsList, adapterHeader); // 발송
//			}
//		}
	}
	
	/**
	 * SMS/LMS 발송 처리. (미로그인 사용자 이메일 발송 : INF_HIST)
	 *
	 * @param mobileNo [설명]
	 * @param msgKey [설명]
	 * @param tempPwd [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void sendNotLoginSysLsms(String mobileNo, String msgKey, String tempPwd) throws Exception {
		String senderId = "plgrim_adm ";

		ArrayList<String> params = new ArrayList<>();
		params.add(0, tempPwd);

		String phoneNumber = mobileNo.trim().replaceAll("-", "");
		if(phoneNumber.length() > 1){
			String chkNum = phoneNumber.substring(0,2);
			if("82".equals(chkNum)){
				phoneNumber = phoneNumber.substring(2);
			}
		}

		String callerId = SysLsmsService.class.getName()+".sendNotLoginSysLsms";

		// TODO 문자 솔루션 적용

//		HumusonSDO humusonSDO = humusonCommonService.getSmsData(senderId, phoneNumber, "DXM",
//				null, callerId, null, msgKey, params);
//
//		sendCommonSysLsms(humusonSDO);
	}

//	/**
//	 * SMS/LMS 발송 처리. (일반화)
//	 *
//	 * @param paramData [설명]
//	 * @throws Exception the exception
//	 * @since 2015. 5. 29
//	 */
//	public void sendCommonSysLsms( HumusonSDO paramData) throws Exception {
//
//		// Adapter Header 를 세팅한다.
//		AdapterHeader adapterHeader = new AdapterHeader();
//		adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
//		adapterHeader.setMallId("DXM");
//		adapterHeader.setLangCd("KOR");
//		adapterHeader.setDvcCd("PC");
//
//		if (paramData != null) {
//			humusonCommonService.sendSmsMms(paramData, adapterHeader); // 발송
//		}
//	}

	/**
	 * SMS/LMS 자주 사용하는 문구 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysLsmsResult> selectSysLsmsTxtList( SysLsmsDTO paramData) throws Exception {
		return sysLsmsRepository.selectSysLsmsTxtList(paramData); 
	}
	
	/**
	 * SMS/LMS 자주 사용하는 문구 등록.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void saveSysLsmsTxt( List<SysLsmsDataDTO> paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		if(paramData != null) {
			for(SysLsmsDataDTO item : paramData) {
				item.getSysSmsEmailTxt().setSmsEmailSectCd("SMS"); // 구분코드
				item.getSysSmsEmailTxt().setTxtCpcty(new BigDecimal(item.getSysSmsEmailTxt().getTxtCont().getBytes().length)); // 용량
				item.getSysSmsEmailTxt().setTxtUseSectCd("CSO");

				item.getSysSmsEmailTxt().setRegtrId(loginId); // 등록자
				item.getSysSmsEmailTxt().setUdterId(loginId); // 수정자
				
				if("C".equals(item.getGridCudTag())) {
					// 등록일 경우
					sysLsmsRepository.insertSysLsmsTxt(item.getSysSmsEmailTxt()); // 등록
				} else if("U".equals(item.getGridCudTag())) {
					// 수정일 경우
					sysLsmsRepository.updateSysLsmsTxt(item.getSysSmsEmailTxt()); // 수정
				} else if("D".equals(item.getGridCudTag())) {
					// 삭제일 경우
					sysSmsEmailTxtRepository.deleteSysSmsEmailTxt(item.getSysSmsEmailTxt()); // 삭제
				}
			}
			
		}
	}
}
