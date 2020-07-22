package com.plgrim.ncp.biz.member.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGodInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGodInqAns;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MypageQnaFoResult extends AbstractResult{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3707017355035247542L;

	/**상품 QA 엔티티	 */
	CsoGodInq csoGodInq;
	
	/**상품 QA 답변 엔티티	 */
	CsoGodInqAns csoGodInqAns;
	
	/** 상품 엔티티	 */
	God god;
	
	/** 상품 이미지 엔티티 */
	GodImg godImg;
	
	private String searchQna;
	
	private String inqRegDt;
	
	private String ansRegDt;
	
	private long searchGodInqSn;
	
	private String chgInqCont;
	
	private String rdGroup01;
	
	private String mbrNo;
	
	private String mbrId;
	
	private Long godInqSn;
	
	private String itmNm;
	
	private String brndNm;
	
	private String imgUrl;
	
	private String imgSizeCd;
	
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
