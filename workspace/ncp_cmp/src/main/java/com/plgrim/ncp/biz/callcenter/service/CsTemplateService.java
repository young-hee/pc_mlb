/* Copyright (c) 2013 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 *                       
 */
package com.plgrim.ncp.biz.callcenter.service;

import java.util.Iterator;
import java.util.List;

import com.plgrim.ncp.commons.util.BOSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.enums.CsTemplateEnum;
import com.plgrim.ncp.biz.callcenter.data.CsTemplateDTO;
import com.plgrim.ncp.biz.callcenter.data.CsTemplateGridDTO;
import com.plgrim.ncp.biz.callcenter.data.EmailSmsTemplateDTO;
import com.plgrim.ncp.biz.callcenter.data.EmailSmsTemplateGridDTO;
import com.plgrim.ncp.biz.callcenter.repository.CsTemplateRepository;

/**
 * CodeViewService Implementation
 * @author Park
 *
 */
@Service
public class CsTemplateService {

	@Autowired
	private CsTemplateRepository csTemplateRepository;

	public void insertCsTemplate(CsTemplateDTO csTemplateDTO) throws Exception{
		
		if(CsTemplateEnum.cnsltTmplatKndCd.CNSLT_ANS.equals(csTemplateDTO.getCnsltTmplatKndCd())){
			csTemplateDTO.setMtmInqAnsTpCd(null);
			csTemplateDTO.setMtmInqAnsDetailTpCd(null);
			csTemplateDTO.setTransTgtCd(null);
			csTemplateDTO.setChrgJobTpCd(null);
			csTemplateDTO.setTransRequstTpCd(null);
			csTemplateDTO.setCnsltTmplatSj(null);
		}else if(CsTemplateEnum.cnsltTmplatKndCd.MTM_INQ_ANS.equals(csTemplateDTO.getCnsltTmplatKndCd())){
			csTemplateDTO.setInqTpCd(null);
			csTemplateDTO.setInqDetailTpCd(null);
			csTemplateDTO.setTransTgtCd(null);
			csTemplateDTO.setChrgJobTpCd(null);
			csTemplateDTO.setTransRequstTpCd(null);
		}else if(CsTemplateEnum.cnsltTmplatKndCd.TRANS_ANS.equals(csTemplateDTO.getCnsltTmplatKndCd())){
			csTemplateDTO.setInqTpCd(null);
			csTemplateDTO.setInqDetailTpCd(null);
			csTemplateDTO.setMtmInqAnsTpCd(null);
			csTemplateDTO.setMtmInqAnsDetailTpCd(null);
			csTemplateDTO.setCnsltTmplatSj(null);
		}

		csTemplateDTO.setRegtrId(BOSecurityUtil.getLoginId());
		csTemplateDTO.setUdterId(BOSecurityUtil.getLoginId());
		
		csTemplateRepository.insertCsTemplatetWithGenSn(csTemplateDTO);
		
		/*if(CsTemplateEnum.cnsltTmplatKndCd.CNSLT_ANS.equals(csTemplateDTO.getCnsltTmplatKndCd())){
			csTemplateDTO.setCnsltTmplatSn(sn);
			csTemplateRepository.insertCsTemplateInqWithGenTurn(csTemplateDTO);
		}*/
	}

	public void updateCsTemplateGrid(List<CsTemplateGridDTO> updateList) {
		Iterator<CsTemplateGridDTO> iterator = updateList.iterator();
		while(iterator.hasNext()) {
			csTemplateRepository.updateCsTemplateGrid(iterator.next());
		}
    }

	
	public void updateCsTemplate(CsTemplateDTO csTemplateDTO) {
	    csTemplateRepository.updateCsTemplate(csTemplateDTO);
	    
    }
	
	public void insertEmailSmsTemplate(EmailSmsTemplateDTO emailSmsTemplateDTO) throws Exception {
		csTemplateRepository.insertEmailSmsTemplateWithGenSn(emailSmsTemplateDTO);
	}
	
	public void updateEmailsmsTemplate(EmailSmsTemplateDTO emailSmsTemplateDTO) {
	    csTemplateRepository.updateEmailsmsTemplate(emailSmsTemplateDTO);
	    
    }

	public void updateEmailSmsTemplateGrid(List<EmailSmsTemplateGridDTO> updateList) throws Exception {
		Iterator<EmailSmsTemplateGridDTO> iterator = updateList.iterator();
		while(iterator.hasNext()) {
			csTemplateRepository.updateEmailSmsTemplateGrid(iterator.next());
		}
	}

}
