/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      mc009.park
 * @since       2015. 6. 19
 */
package com.plgrim.ncp.biz.cart.result;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGod;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * [클래스 설명]
 *
 * <p>
 *
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author mc009.park
 * @since 2015. 6. 19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CartResult extends AbstractResult {


	@JsonIgnore
	Pageable pageable = new PageRequest(0, 30);
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5864483298541363133L;

	/** The bsk god. */
	BskGod bskGod;

	/** The god. */
	God god;

	/** The god itm. */
	GodItm godItm;

	/** The shop. */
	private SysShopBrnd shop;

	/** The grp seq. */
	private int grpSeq; //그룹번호

	/** The sub grp seq. */
	private int subGrpSeq;
	private int dlvGrpRnum;
	private int dlvGrpCnt;

	/** The sub grp cnt. */
	private int subGrpCnt;

	/** The adit cnt. */
	private int aditCnt;

	/** The pckage god tp cd. */
	private String pckageGodTpCd;

	/** The brnd nm. */
	String brndNm;

	/** The shop nm. */
	String shopNm;
	String shopId;
	private String shopTelAreaNo;
	private String shopTelTlofNo;
	private String shopTelTlofWthnNo;

	/** The parent god turn. */
	private int parentGodTurn;

	/** The cart group cd. */
	private int cartGroupCd;

	/** The img url. */
	private String imgUrl;

	/** The prc type. */
	private String prcType; //가격 유형(실소가,임직원가,일모가)

	/** The price. */
	private BigDecimal price; //(실소가,임직원가,일모가)

	/** The price. */
	private BigDecimal priceEx; //환율계산금액

	// 상품할인
	/** The god prm no. */
	private String godPrmNo; //상품할인 프로모션 번호

	/** The god prm sect cd. */
	private String godPrmSectCd;

	/** The god prm amt. */
	private BigDecimal godPrmAmt;

	private BigDecimal godPrmAmtEx; // 상품프로모션 환율  금액, 2016.2.27, harris


	/** The god prm dc rt. */
	private BigDecimal godPrmDcRt;

	private BigDecimal godPrmSaleUntPrc;

	/** The god prm type. */
	private String godPrmType; //상품할인 프로모션 유형

	/** The god prm dc amt. */
	private BigDecimal godPrmDcAmt; //상품할인 금액
	private BigDecimal godPrmDcAmtEx; //상품할인 환율(Ex)금액, 2016.2.27, harris


	// 장바구니 할인
	/** The bsk prm no. */
	private String bskPrmNo; //장바구니 할인 프로모션 번호

	/** The bsk prm sect cd. */
	private String bskPrmSectCd;

	/** The bsk prm dc rt. */
	private BigDecimal bskPrmDcRt;

	/** The bsk prm dc amt. */
	private BigDecimal bskPrmDcAmt;

	/** The bsk prm nm. */
	private String bskPrmNm;

	/** The bsk prm type. */
	private String bskPrmType; //장바구니 할인 유형(묶음,교차)

	/** The bsk prm aply dc amt. */
	private BigDecimal bskPrmAplyDcAmt; //장바구니 할인 적용 금액
	private BigDecimal bskPrmAplyDcAmtEx; //장바구니 할인 적용 환율 금액, 2016.2.27

	/** The is balancd target. */
	private String isBalancdTarget = "N";

	/** The bsk prm aply balancd dc amt. */
	private BigDecimal bskPrmAplyBalancdDcAmt;
	private BigDecimal bskPrmAplyBalancdDcAmtEx; //장바구니 할인잔액 환율 금액 2016.2.27

	/**  즉시 할인 쿠폰 정보 */
	private String cpnNo;

	/** The cpn type. */
	private String cpnType;

	/** The cpn nm. */
	private String cpnNm;

	/** The cpn max dc amt. */
	private BigDecimal cpnMaxDcAmt;

	/** The cpn dc sect cd. */
	private String cpnDcSectCd;

	/** The cpn dc amt. */
	private BigDecimal cpnDcAmt;
	private BigDecimal cpnDcAmtEx; // 쿠폰DC 환율금액, 2016.2.27, harris

	/** The cpn dc rt. */
	private BigDecimal cpnDcRt;

	/** The cpn aply dc amt. */
	private BigDecimal cpnAplyDcAmt; // 쿠폰적용할인가
	private BigDecimal cpnAplyDcAmtEx; // 쿠폰적용할이DC환율가 USD

	/** The cpn aply balanced dc amt. */
	private BigDecimal cpnAplyBalancedDcAmt;
	private BigDecimal cpnAplyBalancedDcAmtEx; // 쿠폰할인 잔액환율 금액, 2016.2.27, harris

	/** The god dc dplct cd. */
	private String godDcDplctCd; //상품 할인 중복여부

	/**국내 배송비 정책*/
	private String dlvCstLevySectCd; //배송비 부가 기준 코드
	private BigDecimal dmstcDlvCstExmStdrAmt; //국내 배송비 면제 기준 금액
	private BigDecimal dmstcDlvCst; //국내 배송비

	private String ovseaDmstcDlvCstLevySectCd; //해외배송 국내 배송비 부가 기준 코드
	private BigDecimal ovseaDmstcDlvCstExmStdrAmt; //해외배송 국내 배송비 면제 기준 금액
	private BigDecimal ovseaDmstcDlvCst; //해외배송 국내 배송비 원화
	private BigDecimal ovseaDmstcDlvCstEx; //해외배송 국내 배송비 해외통화

	private String ovseadlvCstLevySectCd; //해외 배송비 부가 기준 코드
	private BigDecimal ovseaDlvCstExmStdrAmt; //해외 배송비 면제 기준 금액
	private BigDecimal ovseaDlvCst; //해외 배송비 원화
	private BigDecimal ovseaDlvCstEx; //해외 배송비 해외통화

	/** 장바구니 상품 정보. */
	List<BskGod> bskGodList = new ArrayList<BskGod>();

	/** 구성 상품 정보 */
	List<God> bskCpstGodCnnc = new ArrayList<God>();

	/** 구성 상품 연결 정보*/
	List<GodItm> bskCpstGodItmCnnc = new ArrayList<GodItm>();

	/** 추가 구성 상품 */
	List<BskGodExtend> bskCpstInvInfo = new ArrayList<BskGodExtend>();

	/** The inv yn. */
	private String invYn;

	/** The sell yn. */
	private String sellYn;

	/** The pkup day. */
	private String pkupDay;

	/** The crnt day. */
	private String crntDay;

	/** The crs god grp cd. */
	private String crsGodGrpCd; //교차할인 그룹 코드

	/** The cdc inv yn. */
	private String cdcInvYn;

	/** The ord dc relate promt sn. */
	private Long ordDcRelatePromtSn;

	private String relateUrl;

	//주문 사은품========================
	/** The ord prm no. */
	private String ordPrmNo;

	/** The god gft sect cd. */
	private String godGftSectCd;

	/** The ord gft pch stdr cd. */
	private String ordGftPchStdrCd;

	/** The ord gft apl cnd qty. */
	private int ordGftAplCndQty;

	/** The ord gft apl cnd amt. */
	private BigDecimal ordGftAplCndAmt;

	/** The gft choise psb qty. */
	private int gftChoisePsbQty;
	//주문 사은품========================

	/** The dlv grp tot amt. */
	private BigDecimal dlvGrpTotAmt;

	/** The god gft prm no. */
	private String godGftPrmNo;

	/** The god gft list. */
	List<BskGodExtend> godGftList;

	/** The sido cd. */
	private String sidoCd;

	private String aditSavPrmNo;

	private String accmlSectCd;

    private BigDecimal accmlAmt;

    private BigDecimal accmlRt;

    private String availMinOrdCnt = "Y";

    private String availMaxOrdCnt = "Y";


    List<CartGiftResult> godGiftPrmList;


    private String wishYn = "N";

    private String webAditSavPrmNo;
    private String webAccmlSectCd;
    private BigDecimal webAccmlAmt;
    private BigDecimal webAccmlRt;

    private OrdGodExtend ordGodExtend;

    //행우세
    private String pkagTax;

    //행우세 환율적용금액
    private String pkagTaxEx;

    //	EMS / PX
    private String ovseaDlvMnSectCd;




	/**
	 * #34425 로 추가 201-01-13
	 */
	private String ovseaDspStatCd;//해외전시상태코드 god_langby_god_nm table
	private String trsltStatCd;//번역상태코드 god_langby_god_nm table
	private String ovseaDlvPsbYn;//해외배송가능여부 god table



	private String AditInvYn; // '17 8/30 이진형 MERGE

	private String pkupShopYn; // '17 09/04 이진형 추가

	/**
	 * 퀵배송비 부과 구분 코드
	 * 무료 : FREE
	 * 유료 : PCHRG
	 * 조건부무료 : COND_FREE
	 */
	private String qdlvCstLevySectCd;

	/**
	 * 퀵배송비 면제 기준 금액
	 */
	private java.math.BigDecimal qdlvCstExmStdrAmt;

	/**
	 * 퀵배송비
	 */
	private java.math.BigDecimal qdlvCst;
	
	private String shopEndHhmm;
	private String shopAddr;
	private String holdyYn;
	private String shopBegHhmm;

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
	
	private int realInvQty;
	private int hoffInvQty;
	private int etcInvQty;
	private String shopDlvItm;
	
	private int rowSpanCount;

}
