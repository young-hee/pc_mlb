package com.plgrim.ncp.biz.member.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGodInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGodInqAns;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;

@Data
@EqualsAndHashCode(callSuper=false)
public class MypageQnaFoDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4482519648830425052L;
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** Qna 엔티티	 */
	CsoGodInq csoGodInq;
	/**	Qna 답변 엔티티 */
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
	
	private List<String> plgrimshopMallIdList;
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
