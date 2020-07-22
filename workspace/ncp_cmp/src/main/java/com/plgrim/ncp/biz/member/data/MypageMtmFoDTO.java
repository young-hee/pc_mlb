package com.plgrim.ncp.biz.member.data;

import java.util.Date;
import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGodInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqAns;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqAtchFile;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqOrdGod;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoNmbrStplatAgr;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MypageMtmFoDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6609188878199513264L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/**1:1 문의 리스트 Entity */
	CsoMtmInq csoMtmInq;
	
	Mbr mbr;
	
	/**1:1 상품 문의 Entity */
	CsoGodInq csoGodInq;
	
	/**1:1 문의 답변 Entity	 */
	CsoMtmInqAns csoMtmInqAns;
	/**1:1 문의 파일 Entity	 */
	CsoMtmInqAtchFile csoMtmInqAtchFile;
	/**1:1 문의 주문상품 Entity	 */
	CsoMtmInqOrdGod csoMtmInqOrdGod;
	/**고객서비스 비회원 약관 동의 Entity	 */
	CsoNmbrStplatAgr csoNmbrStplatAgr;
	/**주문 Entity	 */
	Ord ord;
	/**주문상품 Entity	 */
	OrdGod ordGod;
	/**단품 Entity	 */
	GodItmHist godItmHist;
	
	List<OrdGod> ordGodList;
	
	List<Ord> ordList; 
	
	private String searchOrdNo;
	//주문번호 조회 날짜
	private Date calendarFrom;
	//주문번호 조회 날짜
	private Date calendarTo;
	
	private String srchMtmInqSn;
	
	private String totalRow;
	
	private String inqRegDt;

	private String ansRegDt;
	
	private String mbrNo;
	
	private String mbrEmail;
	
	private String mobilNo;
	
	private String ordDt;
	
	private String inqDt;
	
	private String mbrId;
	
	private String mbrNm;
	
	private String chkDay;
	
	private Long mtmInqSn;
	
	private String mallId;
	
	private String dvcCd;
	
	private String langCd;
	
	private long mtmInqOrdGodTurn;
	
	private String deleteYn;
	
	private String mobilNationNo;
	
	private String fileNm;
	
	private List<String> deleteFileNm;
	
	private String url;
	
	private String nm;
	
	private String mbrCheck;
	
	private long psnlInfoModHistSn;                 //개인정보 수정이력 seq
	
	private long psnlInfoModHistTurn;               //개인정보 수정이력 순서
	
	private String language;
	
	private List<String> plgrimshopMallIdList;			// 통합몰에서 노출되어야 할 mall id list

	/** 이미지 사이즈 */
	private String imgSizeCd = "100X132";

	private String talkYn;                          /* 시나리오 채팅 호출 여부*/
	
	private String orderYn;							/* 비회원 주문문의여부 */

	String userTrackingId;

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
