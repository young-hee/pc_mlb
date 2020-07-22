package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtSprtr;

/**
 * 이벤트 구분자 Bo DTO
 * @author js
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EvtSprtrBoDTO extends AbstractDTO  {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 이벤트 구분자
	 */
	private EvtSprtr evtSprtr;
	
	/**
	 * 구분자명(중국어)
	 */
	private String sprtrNmChi;

	/**
	 * 구분자 이미지명(중국어)
	 */
	private String sprtrImgFileNmChi;

	/**
	 * 구분자 이미지URL(중국어)
	 */
	private String sprtrImgFileUrlChi;

	/**
	 * 구분자  이미지 대체텍스트(중국어)
	 */
	private String sprtrImgAltrtvContChi;
	
	/**
	 * 구분자명(영어)
	 */
	private String sprtrNmEng;

	/**
	 * 구분자 이미지명(영어)
	 */
	private String sprtrImgFileNmEng;

	/**
	 * 구분자 이미지URL(영어)
	 */
	private String sprtrImgFileUrlEng;

	/**
	 * 구분자  이미지 대체텍스트(영어)
	 */
	private String sprtrImgAltrtvContEng;
	
	
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
