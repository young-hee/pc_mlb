package com.plgrim.ncp.commons.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.em.EmTran;
import com.plgrim.ncp.base.entities.datasource1.em.EmTranMms;
import com.plgrim.ncp.base.entities.datasource1.inf.InfMail;
import com.plgrim.ncp.commons.repository.MailSmsRepository;

@Slf4j
@Service
public class MailSmsService extends AbstractService {
	
	@Autowired
	MailSmsRepository mailSmsRepository;

	public void sendSmsEmail(EmTran emTran, EmTranMms emTranMms, InfMail infMail) throws Exception{
		if(emTran != null){
			mailSmsRepository.insertEmTran(emTran);
		}
		if(emTranMms != null){
			mailSmsRepository.insertEmTranMms(emTranMms);
			
		}
		if(infMail != null){
			mailSmsRepository.insertInfMail(infMail);
			
		}
	}

}
