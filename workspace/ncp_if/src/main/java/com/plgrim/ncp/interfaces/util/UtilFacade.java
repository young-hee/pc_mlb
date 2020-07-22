package com.plgrim.ncp.interfaces.util;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.framework.commons.CollectionService;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.SSOService;
import com.plgrim.ncp.framework.commons.StringService;

/**
 * 
 * @author John_Kim
 *
 */
@Data
@Service
public class UtilFacade {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/** 스트링 서비스. */
	@Autowired
	StringService stringService;
	
	/** Json 서비스. */
	@Autowired
	JsonService jsonService;
	
	/** 날짜 서비스. */
	@Autowired
	DateService dateService;
	
	@Autowired
	IOService ioService;
	
	/* 콜렉션 서비스 */
	@Autowired
	CollectionService collectionService;
	
	/* SSo 서비스. */
//	@Autowired
//	SSOService ssoService;
	
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

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
