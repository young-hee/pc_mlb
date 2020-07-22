package com.plgrim.ncp.biz.delivery.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryInfErpDTO extends AbstractDTO {

	/**
	 * 
	 */
    private static final long serialVersionUID = -5373257734434049253L;
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	private String ordNo;		//주문번호
	private String ordGodTurn;	//주문상품순번
	private String qtyTurn;		//수량순번
	private String clmNo;		//클레임번호
	private String dlivyDrctGodNo;	//출고지시상품순번
	private String itmNo;		//단품번호
	private String dlvShopId;	//배송매장ID
	
	
	private String vbeln;		//납품문서번호(S_DOC_NO)
	private String fpiaOr;		//주문번호
	private String seqNo;		//문서의품목번호(수량순번)
	private String wadatIst;	//실제자재이동일/처리일
	private String matnr;		//상품번호(SKU_NO)
	private String menge;		//수량
	private String meins;		//기본단위
	private String sto;			//P_STO_NO
	private String cbAmt;		//통합포인트/적립금 사용단가
	private String empNo;		//사번

	private String reswk;		//재고운송오더의 경우 공급(출고)플랜트
	private String reslo;		//재고 운송 오더에 대한 출고 저장 위치
	private String werks;		//플랜트 매장코드
	private String lgort;		//저장위치
	private String dmbtr;		//금액(현지 통화)
	private String vbetr;		//할인율
	private String ssto;		//s_sto_no
	private String docno;		//s_doc_no
	private String rctno;		//rcptfr_no
	private String malltext;	//제휴몰명
	private String pwerks;		//픽업매장코드
	private String plgort;		//픽업매장저장위치
	private String eindt;		
	private String zlsch;		
	private String recNo;		
	
	
	private String dlivyShopId;	//출고매장ID
	private String dlivyDrctYn;	//출고지시 여부
	private String resveRcptfrCreatYn;	//예약영수증 생성여부
	
	//예약영수증 취소로 추가
	private String erpResveCnclRcptfrNo;//취소영수증 번호
	private String erpResveRcptfrCnclYn;//예약영수증 취소 여부
	private String erpResveRcptfrNo;	//예약영수증 생성 번호

	
	private String erpResveRcptfrCreatYn ;
	private String result;				//반환결과
	private String rfcType;				//적용 RFC
	
	private String intrlckTgtYn;		//ERP 연동 대상여부
	
	private BigDecimal namt;	//P포인트사용단가

//	private String VBELN;		//납품문서번호(S_DOC_NO)
//	private String FPIA_OR;		//주문번호
//	private String SEQNO;		//문서의품목번호(수량순번)
//	private String WADAT_IST;	//실제자재이동일
//	private String MATNR;		//상품번호(SKU_NO)
//	private String MENGE;		//수량
//	private String MEINS;		//기본단위
//	private String I_STO;		//P_STO_NO
//	private String CB_AMT;		//통합포인트사용단가
//	private String EMP_NO;		//사번

	/**
	 * 매장 재고 후보충 성공 여부
	 */
	private String shopInvAfSupleSuccesYn;

	/**
	 * 매장 재고 출고 지시 문서 번호
	 */
	private String shopInvDlivyDrctDocNo;

	/**
	 * 매장 재고 후보충 실패 내용
	 */
	private String shopInvAfSupleFailrCont;

	/**
	 * 픽업 변경 여부
	 */
	private String pkupModYn;

	/**
	 * 출고지시 취소 여부
	 */
	private String dlivyDrctCnclYn;
	
	/**
	 * 출고지시 취소 상태 코드
	 */
	private String dlivyDrctCnclStatCd;

	/**
	 * #50212 [개발]픽업 재진열 대상 알림(매장 Dashboard) 기능 추가
	 *
	 * 배송 상태 코드
	 ㅁ. 배송 상태 : DLV_STAT
	 >. 배정대기 : DLV_WAIT
	 >. 교환입고대기: EXCHG_WRHS_WAIT
	 >. 출고지시 : DLIVY_DRCT
	 >. 출고지시취소 : DLIVY_DRCT_CNCL
	 >. 출고완료 : DLIVY_COMPT
	 >. 배송완료 : DLV_COMPT
	 >. 매장 준비 완료 : SHOP_PRPARE_COMPT
	 >. 결품접수 : SHTG_RCEPT
	 */
	private String dlvStatCd;

	/**
	 * #50212 [개발]픽업 재진열 대상 알림(매장 Dashboard) 기능 추가
	 *
	 * 픽업 재진열 코드
	 ㅁ. 픽업 재진열 : PKUP_REDISP
	 >. 재진열 비대상 : REDISP_UTGT
	 >. 재진열 대상 : REDISP_TGT
	 >. 재진열 완료 : REDISP_COMPT
	 */
	private String pkupRedispCd;

	/**
	 * 배송수거지순번
	 */
	private String dlvPcupspTurn;
	
	/**
	 * 배송순번
	 */
	private String dlvTurn;
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
