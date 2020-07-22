package com.plgrim.ncp.biz.display.result;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.god.GodIcon;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrTodayGod;

@Data
@EqualsAndHashCode(callSuper=false)
public class GoodsInterestsGodFoResult extends AbstractResult {
	/**
	 * 
	 */
    private static final long serialVersionUID = 4535601869157598906L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    /** 상품 이미지 엔티티. */
	private GodImg godImg;
	/** 회원 오늘본상품 엔티티. */
	private MbrTodayGod mbrTodayGod;
	private String sbj;
	private String godNm;
	private BigDecimal lastSalePrc;
	private String godImgUrl;
	private String imgUrl;
	private String godImgUrl280;
	private String godImgUrl170;
	private String todayGodSn;
	private String todayGodSectCd;
	private String godNo;
	private String evtNo;
	private String promtSn;
	private String totalCount;
	private String progressCd;
	private String brndNm;
	private String newYn;// 신상품 여부
	private String bestYn;// 베스트상품 여부
	private String godSaleSectCd;// 상품 판매 구분 코드
	private String recomendSexCd;// 상품 성별 코드
	private BigDecimal godDcRt;// 상품 할인
	private BigDecimal cpnDcRt;// 쿠폰 할인
	private String sttRank;// 베스트상품 통계 순위
	private List<GoodsInterestsGodFoResult> godIconList;// 상품 아이콘 목록
	private BigDecimal rtlPrc;	// 정소가 추가 2015.09.18
	private String pcUrl;
	private String cookieKey;
	private String godUrl;
	
	/* 기획전 관련 변수 - s */
    private String promtNm;
    private String promtAprvStatCd;
    private String promtAprvDt;
    private String promtAprvRejectDt;
    private String promtAprvRejectResnCont;
    private String promtTpCd;
    private String dspBegDt;
    private String dspEndDt;
    private String dspYn;
    private String mobileImgFileNm;
    private String mobileImgFileUrl;
    private String mobileImgAltrtvCont;
	/* 기획전 관련 변수 - e */

	/* Strend 관련 변수 - s */
    private String strndSn;
    private String strndNm;
    private String strndDscr;
    private String rprstImgFileNm;
    private String rprstImgFileUrl;
    private String rprstImgAltrtvCont;
    private String brndId;
    private String sesonCd;
    private String sesonNm;
    private String strndTpCd;
    private String strndTpNm;
	/* Strend 관련 변수 - e */

    /* 상품 아이콘 관련 변수 - s */
	private Integer iconTurn;
	private String iconNm;
	private String tagNm;
	private String seasonInfo;
	private List<GodIcon> iconList;
	private String textIconCont;
    /* 상품 아이콘 관련 변수 - e */

    /* GNB 검색 배너 관련 변수 - s */
	private String conttNm;
	private String conttCnncUrl;
    /* GNB 검색 배너 관련 변수 - e */

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
