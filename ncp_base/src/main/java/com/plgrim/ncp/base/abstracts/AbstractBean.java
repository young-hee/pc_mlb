package com.plgrim.ncp.base.abstracts;

import com.plgrim.ncp.framework.commons.*;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;

import com.plgrim.ncp.framework.page.PageService;

@Data
public abstract class AbstractBean {
	
	/** 페이지 서비스. */
	@Autowired
	PageService pageService;
	
	/** 스트링 서비스. */
	@Autowired
	StringService stringService;
	
	/** Json 서비스. */
	@Autowired
	JsonService jsonService;
	
	/** 날짜 서비스. */
	@Autowired
	DateService dateService;
	
	/* 파일 IO 서비스*/
	@Autowired
	IOService ioService;

	/* 콜렉션 서비스 */
	@Autowired
	CollectionService collectionService;

	/* 로케일 서비스 */
	@Autowired
	LocaleService localeService;
	
	/*ID 생성 서비스*/
	@Autowired
	IdGenService idGenService;

	/* 컨텍스트트 서비스 */
	@Autowired
	ContextService contextService;

	/* properties 서비스 */
	@Autowired
	ConfigService configService;


	// ~ Instance fields. ~~~~~~~~~~~~~~
	// ~ Constructors. ~~~~~~~~~~~~~~~~~
	// ~ Implementation Method. ~~~~~~~~
	// ~ Public Methods. ~~~~~~~~~~~~~~~
	// ~ Private Methods. ~~~~~~~~~~~~~~

}
