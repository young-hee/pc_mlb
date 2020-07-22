package com.plgrim.ncp.commons.repository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.em.EmTran;
import com.plgrim.ncp.base.entities.datasource1.em.EmTranMms;
import com.plgrim.ncp.base.entities.datasource1.inf.InfMail;

@Slf4j
@Repository
public class MailSmsRepository extends AbstractRepository {

	public void insertInfMail(InfMail infMail) throws Exception{
		getSession1().insert("com.plgrim.ncp.commons.mailsms.insertInfMail", infMail);
	}
	public void insertEmTran(EmTran emTran) throws Exception{
		
	}
	public void insertEmTranMms(EmTranMms emTranMms) throws Exception{
		
	}
	
}
