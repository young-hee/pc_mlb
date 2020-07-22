package com.plgrim.ncp.biz.order.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspFoExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.biz.order.result.MypageOrderCancelFoResult;

@Data
@EqualsAndHashCode(callSuper = false)
public class MypageOrderInfoDTO extends AbstractDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5836627492662374731L;

	private String ordNo;                    	/* 주문 번호 */
	private String payNo;                    	/* payNo */ 
	private String newPayNo;                   /* payNo */ 
	private String mpayMnYn;                   /* 주결제여부 */ 
	private String mbrNo;                    	/* 회원 번호 */
	private String cstmrNm;                    /* 비회원 이름 */
	private String cstmrMobilNo;               /* 비회원 핸드폰번호 */
	private String cstmrMobilAreaNo;
	private String cstmrMobilTlofNo;
	private String cstmrMobilTlofWthnNo;
	private String cstmrEmail;					/* 고객 이메일 */
	private String ordTpCd;       		     	/* 주문 유형 코드 */
	private String ordDt;                    	/* 주문 일시 */
	private String ordStatCd;                	/* 주문 상태 코드 */
	private String godNm;                    	/* 상품명 */
	private String erpGodNo;                    /* ERP상품번호 */
	private String lstImgUrl;                  /* 상품이미지 */
	private String ordGodTurn;               	/* 주문 상품 순번 */
	private String rcptfrReqstCd;              /* 영수증 신청 코드 */
	private String rcptfrPrcsCd;               /* 영수증 처리 코드 */
	private String rcptfrAprvNo;               /* 영수증 승인번호 */
	private String rcptfrPrposCd;              /* 영수증 용도코드 */
	private String resveOrdPaySectCd;          /* 예약주문결제구분코드 */
	private String resveOrdDlivyPrearngeDate;  /* 예약주문 출고예정일자 */
	private String resveOrdPartPayAmt;         /* 예약주문 부분결제금액 */
	private String resveOrdPayClosDate;        /* 예약주문 결제 마감일시 */
	private String remainAmt;        		    /* 예약주문 미결제금액 */
	private String shopNm;        		        /* 매장명 */
	private String shopBaseAddr;        		/* 매장주소1 */
	private String shopDetailAddr;        		/* 매장주소2 */
	private String shopTelTlofWthnNo;        	/* 매장전화번호 */
	private String wkdyOperBegHhmm;        	/* 매장운영시간1 */
	private String wkdyOperEndHhmm;        	/* 매장운영시간2 */
	private String shopId;          			/* 매장ID */
	private String shopBrndId;          		/* 매장브랜드ID */
	private String pkupShopVisitDate;          /* 매장픽업예정일 */
	private java.lang.Integer dlvPcupspTurn;   /* 배송지순번 */
	private java.lang.Integer dlvTurn;         /* 배송 순번 */
	private String itemNoCnt;               	/* 단품수량 */
	private String itmNoStr;               	/* 단품번호 */
	private String godTpCd;               		/* 상품타입 */
	private String regtrId;               		/* 상품타입 */
	private String payTpCd;					/* 결제유형코드 */
	private String dealTpCd;					/* 거래유형코드 */
	private String refundYn;					/* 모빌리언 환불여부 */     
	private String clmNo;						/* 클레임번호 */
	private String cnclAcmtlAmt;				/*	취소누적금액 null이면 취소누적금액update 할 시 주문시 결제정보로 update 된다.	*/
	private String stdrCrncyPayAmt;			/* 기준통화결제금액 / 한화결제 예상금액 */
	private String pgAprvNo;					/*	PG 승인번호	*/
	private String pgSectCd;					/*	PG 구분코드	*/
	private String escrYn;						/*  에스크로여부	*/
	private String payMnCd;					/*	결제 수단코드 	*/
	private String pgStoreId;					/*	PG  상점ID 	*/
	private String mbrCpnNo;					/*	회원쿠폰번호	*/
	private String payCrncyCd;					/*	결제통화코드	*/
	private String payCrncyPayAmt;				/*	결제통화결제금액	*/
	private java.util.Date regDt;              /* 상품타입 */
	private java.util.Date exchgRtAplBegDt;	/* 결제환율적용시작일 */
	
	private java.math.BigDecimal rtlPrcSumAmt;				/* 정소가 */
	private java.math.BigDecimal saleSumAmt;				/* 실소가 */
	private java.math.BigDecimal webDcSumAmt;				/* 웹할인금액 */
	private java.math.BigDecimal stdrCrncySumAmt;			/* 기준 환율 통화 합계 금액 */
	private java.math.BigDecimal payExchgRtCrncySumAmt;	/* 결제 환율 통화 합계 금액 */
	private java.math.BigDecimal payExchgRtCrncyAmt;		/* 클레임 결제 환율 통화 합계 금액 */
	
	private java.math.BigDecimal oriStdrCrncySumAmt;		/* 원결제금액 */
	private java.math.BigDecimal oriCnclAcmtlAmt;			/* 원결제 취소 누적금액 */
	
	private java.math.BigDecimal dlvCstSumAmt;				/* 배송비 합계 금액 */
	private java.math.BigDecimal godDcSumAmt;				/* 상품할인 합계 금액 */
	private java.math.BigDecimal allDcSumAmt;				/* 전체할인 합계 금액 */
	private java.math.BigDecimal imdtlDcSumAmt;			/* 즉시할인 합계 금액 */

	private java.math.BigDecimal godCpnDcSumAmt;			/* 상품 쿠폰 할인 합계 금액 */
	private java.math.BigDecimal bskCpnDcSumAmt;			/* 장바구니 쿠폰 할인 합계 금액 */

	private java.math.BigDecimal bundleDcSumAmt;			/* 묶음할인 합계 금액 */
	private java.math.BigDecimal crsDcSumAmt;				/* 교차할인 합계 금액 */
	
	private java.math.BigDecimal dlvCstCpnDcSumAmt;		/* 배송비 쿠폰 할인 합계 금액 */
	private java.math.BigDecimal unityPntUseSumAmt;		/* 통합 포인트 사용 합계 금액 */
	private java.math.BigDecimal evtPntUseSumAmt;			/* 이벤트 포인트 사용 합계 금액 */
	private java.math.BigDecimal webpntUseSumAmt;			/* 마일리지 사용 합계 금액 */
	
	private java.math.BigDecimal unityPntAccmlSumAmt;		/* 통합 포인트 적립 금액 */
	private java.math.BigDecimal evtPntAccmlSumAmt;		/* 이벤트 포인트 적립 금액 */
	private java.math.BigDecimal webpntAccmlSumAmt;		/* 통합 포인트 적립 금액 */

	private java.math.BigDecimal empDcSumAmt;				/* 임직원할인 금액 */

	private List<LgsDlvspFoExtend> lgsDlvspFoExtend;       /* 주문상품 정보 */
	private List<Pay> payList;                             /* 결제정보 */
	
	private String[] clmTpCd;              /* 클레임 유형 */
	private String[] dlvStatCd;            /* 배송상태 유형 */
	private java.lang.Integer clmCnt;		/* 다중반품 표시를 위한 클레임 건수 */
	private String type;					/* 결제 유형 */
	
	private String godNo;                  /* 상품 번호 */
	private List<GodItm> godItmList; 		/* 상품 옵션 엔티티 */
	
	private String clmTpCdSearch;			/* 검색 클레임 유형 */
	private String dateStart;    			/* 시작일 */
	private String dateEnd;    			/* 종료일 */
	
	private String srchMonth;    									//  Search Month
	private String srchType;    									//  Search Type
	
	private MypageOrderCancelFoResult orderCancel;       // 취소 가능여부
	
	private String pkupShopId;             /* 픽업 매장 ID */                
	private String pkupShopBrndId;         /* 픽업 매장 브랜드 ID */
	
	private String allatCertNo;			/* 휴대폰 , 주민번호 , 사업자 등록번호	*/
	
	private String dlvYn;				    /* 픽업주문 일반 배송 전환 가능여부	*/
	private String pickupDlvChgItmStatYn;	/* 픽업주문 일반 배송 전환 가능여부 ( 옵션 판매상태 기준 : #47710 픽업판매중인 상품이 있는 경우 일반배송으로 전환 불가 ) */
	private String ordItmNo;				/* 주문 옵션 번호*/
	
	private String statCd;				    /* 클레임 상태*/
	
	private String cvnstorHdryAprvNo;	    /* 편의점택배 승인 번호*/
	private String clmDt;	   				/* 클레임일시*/
	
    private String rfdBnkCd;	/* 환불은행 */
    
    private String rfdAcnthldrNm;	/* 환불 예금주명 */
    
    private String rfdBnkAcctNo;	/* 환불 계좌번호 */
    
    private String rfdYn;	/* 핸드폰결제 클레임시 환불계좌 확인 유무 */
    
    private java.lang.Integer godEvlCnt;	/* 상품리뷰 등록 건 */

	private String payMnCdNm;

	private java.math.BigDecimal ordSaleAmtExSum;   /*주문임상품 환율적용 합계*/

	private String addPayClmTpCd;	// 클레임 유형코드 (clmTpCd가 배열로 이미 선언되어 있어서 다른이름으로 사용함)

	private String UdterId; // 수정자ID
	
	private java.math.BigDecimal cnclSaleSumAmt;			/* 실소가취소금액 */
	private java.math.BigDecimal cnclAllDcSumAmt;			/* 전체할인취소금액 */
	private java.math.BigDecimal cnclEmpDcSumAmt;			/* 임직원할인취소금액 */
	private java.math.BigDecimal cnclUnityPntUseSumAmt;	/* 통합포인트사용취소금액 */
	private java.math.BigDecimal cnclEvtPntUseSumAmt;		/* 이벤트포인트사용취소금액 */
	private java.math.BigDecimal cnclWebpntUseSumAmt;		/* 웹포인트사용취소금액 */


	//배송지 변경시 체크박스 체크여부
	/**
	 * 기본배송지 지정 체크여부
	 */
	private String defaultDelv;

	/**
	 * 배송지 목록에 추가 체크여부
	 */
	private String addMbrDlvspCheck;

	/**
	 * 클레임 여부 - 주문상세 UI 노출 기준
	 */
	private String clmYn;
	
	// 품절시 빠른 환불 서비스 선택 가능여부
	/**
	 * 전체자동환불가능여부
	 */
	private String autoAllRefundYn;
	/**
	 * 부분자동환불가능여부
	 */
	private String autoPartRefundYn;
	
	private String nonMember = "N";

}
