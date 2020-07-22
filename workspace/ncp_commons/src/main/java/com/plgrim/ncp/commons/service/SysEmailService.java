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
import com.plgrim.ncp.base.repository.sys.SysSmsEmailTxtRepository;
import com.plgrim.ncp.commons.data.SysEmailDTO;
import com.plgrim.ncp.commons.data.SysEmailDataDTO;
import com.plgrim.ncp.commons.repository.SysEmailRepository;
import com.plgrim.ncp.commons.result.SysEmailResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 이메일 발송 Service
 * @author ed
 *
 */
@Service
public class SysEmailService extends AbstractService {

	@Autowired
    SysEmailRepository sysEmailRepository; // 이메일 발송 DAO
	
	@Autowired
	SysSmsEmailTxtRepository sysSmsEmailTxtRepository; // 자주 사용하는 문구 DAO
	
	@Autowired
	InterfaceApiCommon interfaceApiCommon;

	/**
	 * 이메일 발송 처리.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void sendSysEmail( SysEmailDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		// Adapter Header 를 세팅한다.
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
		adapterHeader.setMallId("DXM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");

		// TODO 이메일 솔루션 적용

//		List<EmailSDO> emailList = new ArrayList<EmailSDO>();
//
//		if(paramData != null) {
//			for(int i=0; i<paramData.getEmailList().size(); i++){
//				EmailSDO item = new EmailSDO();
//				EmailSDO paramEmail = new EmailSDO();
//				paramEmail = (EmailSDO)paramData.getEmailList().get(i);
//
//				item.setAutocode("");
//				item.setUserId(paramEmail.getUserId());
//				item.setAutotype(StringService.nvl(paramData.getFormData().getEmailSDO().getAutotype(), ""));
//				item.setEmail(paramEmail.getEmail());
//				item.setName(paramEmail.getName());
//				item.setInsertdate("");
//				item.setSendtime("");
//				item.setSendyn("N");
//				item.setOpentime("");
//				item.setSenttime("");
//				item.setCmpncode("");
//				item.setFromaddress(paramData.getFormData().getEmailSDO().getFromaddress());
//				item.setFromname(paramData.getFormData().getEmailSDO().getFromname());
//				item.setTitle(paramData.getFormData().getEmailSDO().getTitle());
//				item.setTag1(paramData.getFormData().getEmailSDO().getTag1());
//				item.setTag2("");
//				item.setTag3("");
//				item.setTag4("");
//				item.setTag5("");
//				item.setTag6("");
//				item.setTag7("");
//				item.setTag8("");
//				item.setTag9("");
//				item.setTag10("");
//				item.setTag11("");
//				item.setTag12("");
//				item.setCallerId(loginId);
//				item.setAdminId(loginId);
//
//				emailList.add(item); // 수신자 추가
//			}
//
//			emailAdapter.sendEmail(emailList, adapterHeader); // 발송
//		}
	}
	
	/**
	 * 이메일 자주 사용하는 문구 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysEmailResult> selectSysEmailTxtList( SysEmailDTO paramData) throws Exception {
		return sysEmailRepository.selectSysEmailTxtList(paramData); 
	}
	
	/**
	 * 이메일 자주 사용하는 문구 등록.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void saveSysEmailTxt( List<SysEmailDataDTO> paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		if(paramData != null) {
			for(SysEmailDataDTO item : paramData) {
				item.getSysSmsEmailTxt().setSmsEmailSectCd("EMAIL"); // 구분코드
				item.getSysSmsEmailTxt().setTxtCpcty(new BigDecimal(item.getSysSmsEmailTxt().getTxtCont().getBytes().length)); // 용량
				item.getSysSmsEmailTxt().setTxtUseSectCd("CSO");

				item.getSysSmsEmailTxt().setRegtrId(loginId); // 등록자
				item.getSysSmsEmailTxt().setUdterId(loginId); // 수정자
				
				if("C".equals(item.getGridCudTag())) {
					// 등록일 경우
					sysEmailRepository.insertSysEmailTxt(item.getSysSmsEmailTxt()); // 등록
				} else if("U".equals(item.getGridCudTag())) {
					// 수정일 경우
					sysEmailRepository.updateSysEmailTxt(item.getSysSmsEmailTxt()); // 수정
				} else if("D".equals(item.getGridCudTag())) {
					// 삭제일 경우
					sysSmsEmailTxtRepository.deleteSysSmsEmailTxt(item.getSysSmsEmailTxt()); // 삭제
				}
			}
		}
	}
}
