package com.plgrim.ncp.cmp.newsletter.fo.newsletter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.biz.newsletter.data.NewsLetterDTO;
import com.plgrim.ncp.biz.newsletter.service.NewsLetterService;
import com.plgrim.ncp.cmp.newsletter.fo.NewsLetterFOComponent;
import com.plgrim.ncp.framework.systems.SystemContext;

import lombok.extern.slf4j.Slf4j;

@Transactional(value = "transactionManager")
@Component
@Slf4j
public class NewsLetterFOComponentImpl extends AbstractComponent implements NewsLetterFOComponent {
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	@Autowired
	NewsLetterService newsLetterService;
	
	@Autowired
    SystemContext systemContext;
	
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
	 * newsletter delete.
	 */
	@Override
	public void deleteNewsLetter(NewsLetterDTO dto)  {		
		newsLetterService.deleteNewsLetter(dto);
	}
	
	/**
	 * newsletter insert.
	 */
	@Override
	public void insertNewsLetter(NewsLetterDTO dto)  {		
		newsLetterService.insertNewsLetter(dto);
	}




}
