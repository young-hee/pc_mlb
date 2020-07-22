/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      
 * @since       2015. 3. 30
 */
package com.plgrim.ncp.biz.delivery.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;

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
 * @author 
 * @since 2015. 3. 30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryOrderGoodResult extends AbstractResult {

    private static final long serialVersionUID = 4535518680341599969L;

	/** 주문 엔터티. */
    private Ord ord;
	/** 주문상품 엔터티. */
    private OrdGod ordGod;
	/** 물류배송지 엔터티. */
    private LgsDlvsp lgsDsp;
	/** 물류배솓 상품 엔터티. */
    private LgsDlv lgsDlv;
	/** 물류출고지시상품 엔터티. */
    private LgsDlivyDrctGod lgsDdg;
    /** 상품정보 */
    private GodItm godItm;


    /** 클레임번호. */
    private String clmNo;
    /** 클레임입고상품순번. */
    private String clmWrhsGodTurn;
    /** 회수지시상품번호. */
    private String rtrvlDrctGodNo;
	/** 지연일수. */
	private int delayTerm;
	/** 해외배송여부. */
	private String ovseaDlvYn;
	/** 회수지시수량. */
	private int rtrvlDrctQty;
	/** 브랜드명 */
	private String brndNm;
	/** 업체 업무담당자 전화번호. */
	private String chrgerMobilNo;
	/** 유효 전화번호여부 */
	private String mobilNoCheck;
	/** today */
	private String today;
	/** 주문상품 수량 */
	private int ordGodCnt;
	/** 묶음상품순번  */
	private String pckageGodTurn;
	/** 주문유형  */
	private String ordTpNm;
	/** 판매제휴사명  */
	private String saleAffComNm;
	/** 상품유형  */
	private String godTpNm;
	/** 반품자명  */
	private String addrseNm;
	/** 수취인주소  */
	private String addrseAddr;
	/** 수취인휴대전화번호  */
	private String addrseMobilNo;
	/** 수취인휴대전화번호  */
	private String addrseMobilNoOrg;
	/** 수취인전화번호  */
	private String addrseTelNo;
	/** 배송매장명  */
	private String dlvShopNm;
	/** 단/복품여부  */
	private String lgsItmTp;
	/** 출고지시유형  */
	private String dlivyDrctTpNm;
	/** 배송상태  */
	private String dlvStatNm;
	/** 배송상태코드  */
	private String dlvStatCd;
	/** 해외/국내 구분  */
	private String ovseaDlvTp;
	/** 남은시간  */
	private String remainTime;
	/** 경과시간  */
	private String elapsedTime;
	/** 배송매장번호  */
	private String orgDlvShopId;
	/** 배송매장번호  */
	private String newDlvShopId;
	/** S STO 번호(STOREORDER_STO번호)  */
	private String sStoNo;
	/** S DOC 번호(STOREORDER_DOC번호)  */
	private String sDocNo;
	/** ST 오류 내용()STORE_ORDER에러내용)  */
	private String stErrCont;
	/** 센터출고확인여부  */
	private String cntrDlivyCnfirmYn;
	/** 예약영수증생성여부  */
	private String resveRcptfrCreatYn;
	/** 예약영수증출고여부  */
	private String resveRcptfrDlivyYn;
	/** 예약영수증확정여부  */
	private String resveRcptfrDcsnYn;
	/** 예약판매여부  */
	private String reservationYn;
	/** 몰명  */
	private String mallNm;
	/** 배송방법  */
	private String dlvMnNm;
	/** 회수상태  */
	private String rtrvlStatNm;
	/** 클레임사유  */
	private String clmResnNm;
	/**  상품옵션  */
	private String options;
	/**  예약 주문 출고 예정 일자*/
	private String resveOrdDlivyPrearngeDate;
	/**  수량순번  */
	private int qtyTurn;
	/**  출고검수여부  */
	private String dlivyAcptYn;
	/**  입고검수여부  */
	private String wrhsAcptYn;
	/**  출고지시수량  */
	private int newDlivyDrctQty;
	/**  원본송장번호  */
	private String dmstcWaybilNoOrg;
	/**  원본송장번호  */
	private String dmstcWaybilNo;
	/**  이미지보기  */
	private String imageView;
	/**  송장보기  */
	private String dmstcWaybilNoView;
	/**  출고지시 상품 메모 내용  */
	private String dlivyDrctGodMemoCont;
	/**  택배사  */
	private String dlvComNm;
	/** 판매제휴사명  */
	private String affComNm;
	/** 옵션  */
	private String optValCd;
	/** 사은품 원상품 주문상품순번  */
	private String orgOrdGodTurn;
	private String gftViewYn;		//사은품 출고관리 노출여부
	private String remainQty;		//잔여수량
	private String viewTxt;
	private String gftGubunNm;		//사은품구분명
	private String comNm;			//업체명
	private String gftGubun;		//사은품구분(S:자체배송,H:합배송,N:사은품아님)
	private String erpGodSn;		//시리얼번호
	private String lmttInvYn;		//한정재고여부
	private String shopStckChckYn;	//매장재고체크여부
	private String aplUnitCd;		//사은품적용단위
	private long invQty;			//가용재고수량
	private int newTargetCnt;		//신규채번 수량
	private String transYn;			//전송여부
	private String params;
	private String erpGodSnView;
	private String erpStoNoView;
	private String skuNo;
	private int dlvPcupspTurn;
	private int dlvTurn;
	private String ordNo;
	private String usefulInvQty;	//가용재고수량
	private String itmStatNm;		//상품상태
	private String stoStat;			//(매장픽업)STO요청상태

	private String hblNo;			//해외 운송장 번호

	private String cstmrNm;			//주문자명

	private String rtrvlDrctDt;		//회수지시일자
	private String rtrvlComptDt;	//회수완료일자
	private String rfdDelayCd;		//환불지연사유코드
	private String rfdDelayNm;		//환불지연사유코드명
	private String rfdDelayResnDscr;//기타사유
	private String rfdDelayDt;		//환불지연 등록 일자

	private String dlivyDrctGodNo;
	private java.lang.Long dlivyDrctQty;
	private String newRtrvlStatCd;
	private String comptDt;

	private String dlvShopDlivyLcId;	//배송매장출고위치ID
    private String imageBtn;
    private String cdDscr; 			// 택배사송장조회주소
    private String smllGodYn;		//소액상품여부
    private java.util.Date erpGodSnModDt;	//시리얼입력일자
    private String svcAplTpNm; 		//선물포장
    private String cdcInvOnlyYn; 	//CDC한정판매여부

    private int rejectCount; 		// 거부건수
    private String rejectResnCd; 	// 거부사유코드
    private int remainHour; 		// 남은시간
    private String asgnSectCdNm; 	// 배정구분명
    private String inspctReqYn; 	// 검수요청여부

    private String ordTpCdNm;       // 주문유형코드명
    private String clmTpCd;         // 클레임유형코드
    private int rtrvlDrctWthdrawQty; // 회수지시 철회 수량
    private String dlvPcupspTurnWthdraw;			// 클레임 교환철회용 회수지
    private String dlvPcupspTurnDlivyWthdraw;		// 클레임 교환철회용 수거지
    private String rfdBnkCd;        // 환불계좌 은행코드
    private String rfdBnkAcctNo;    // 환불계좌
    private String rfdAcnthldrNm;   // 환불계자 예금주명
    private String rtrvlStatCd;     // 회수 진행상태
    private String clmTpCdNm;       // 클레임 상태명
    private String rtrvlStatCdNm;   // 회수 진행상태명
    private int    saleUntPrc;      // 판매단가
    private int    dmstcDlvCstPlcSn; // 국내 배송비 정책 일련번호
    private String godTpCd;         // 상품 구분
	private int    ordGodTurnAnce;  // 주문 구성 상품 연결 상품순번
	private int    ordCpstGodTurn;  // 세트 구성상품 번호
	private String pgSectCd;        // 주결제 수단 PG 구분 코드
	private int    dlvCnt;          // 배송지 갯수
	private String cstmrMobil;      // 주문자 핸드폰 번호
	private String eventDate;		// 이벤트발생일자
	private String statusCode;      // 글로벌 배송 범한 상태
	private String statusCodeNm;    // 글로벌 배송 범한 상태명
	private String addrsePostNo;    // 수취인 우편번호
	private String addrseBaseAaddr; // 수취인 주소
	private String addrseCtyNm;     // 수취인 도시명
	private String addrseLcltyNm;   // 수취인 지방명
	private String addrseMobilNationNo;   // 수취인 휴대전화 국가번호
	private String addrseMobilAreaNo;     // 수취인 휴대전화 지역번호
	private String addrseMobilTlofNo;     // 수취인 휴대전화 국번호
	private String addrseMobilTlofWthnNo; // 수취인 휴대전화 국내번호
	private String passivDlivyYn;         // 수동 출고 여부
	private String passivDlivyComptYn;    // 수동 출고 완료 여부
	private String passivDlivyDt;         // 수동 출고 일시
	private String passivDlivyAdminId;    // 수동 출고 관리자 ID
	private String updateCell;            // 수정된 Cell 구분값
	private String dlvComUrl;             // 택배사 화물추적 url

	private String repairYn;              //클레임이 무료수선인지 여부

	private String erpCstmrNo;            // ERP 고객번호
	private String endpBrndId;            // 대표 브랜드 아이디
	private String endpBrndNm;            // 대표 브랜드명
	private String erpBrndGrpId;          // ERP 브랜드 아이디
	private String trnsmisResultCd;       // ERP전송 리턴 결과값.
	private String trnsmisErrCont;        // ERP전송 리턴 메세지.

	//	ㅁ. 오프라인 쿠폰 유형 : OFLNE_CPN_PYMNT_TP
	//	   >. 픽업 쿠폰 : PKUP_CPN
	//	   >. 반품 쿠폰 : RTGOD_CPN
	//	   >. 교환 쿠폰 : EXCHG_CPN
	private String oflneCpnPymntTpCd;     // 오프라인 쿠폰 지급 유형 코드

	private String dlivyDrctCnclStatNm;
	
	private String dlvShopId;
	
}
