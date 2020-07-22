package com.plgrim.ncp.biz.order.data;

import com.plgrim.ncp.base.entities.datasource1.ord.Ord;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderNoPaymentDTO extends Ord {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private String mobile;			// 고객 모바일 전화번호
	private String dlvPcupspTurn;	// 배송수거지번호
	private String dlvTurn;			// 배송순번
	private String dmstcWaybilNo;	// 송장번호
	
	private String partmalSectCd;	// 입점구분
	
	// 주문유형(일반, 픽업)에 따른 배송완료 메시지에서 사용 
	private String godNm;			// 상품명
	private String godCnt;			// 주문상품수량
	
	private String  virtlBnkAcctValidDtStr; //입금기한
	
	private String bnkNm;			//은행명
	private String bnkAcctNo;		//계좌번호
	private String url;				// 택배사 url
	private String mallId;	// mallID

	/*
	 *	1. 수정일자   : 2016-02-05
	 *	2. 수정자     : 김재성 (jskim27)
	 *	3. 요청 SR NO : #15372
	 *	4. 수정내용   : 주문발생 후 24시간 시점에서 배정대기로 남아있는 경우 배송지연안내 SMS추가발송의 건
	 *		- 고객님께서 주문하신 상품(O201505291023054/*** 외 *건)이 물류센터 재고부족으로 매장을 통해 상품확보중이나,
	 *			최종 확보하지 못했을경우 별도 안내드리도록 하겠습니다.
	 *		- RO201512213996700@RO201512213996701 형태의 문자열을 파싱해서 업데이트 처리
	 */
	private String dlivyDrctGodNos;	// 물류출고지시번호 문자열

	/*
	 *	1. 수정일자   : 2016-03-21
	 *	2. 수정자     : 김재성 (jskim27)
	 *	3. 요청 SR NO : #17528
	 *	4. 수정내용   : [고객센터]입점업체 배송문자 확인요청
	 *					- '물류배송' 테이블에 등록된 '배송업체코드' 정보를 이용, SYS_CD에 등록된 코드명을 사용
	 */
	private String dlvCom;			// 택배사명, SYS_CD 에 등록된 정보
	
	private String brndNm;			// SMS개선 by cannon : 2016.07.17
		
	private String smsRecptnSectCd;	//  SMS수신구분
	private String ordmanMobileNo;	//  주문자전화번호
	private String addrseMobileNo;	//  수령자전화번호
	private String addrse;			//  수령자 주소
	private String dlvMemo;			//  메모

	/**
	 * #44103, [개발] 복합 주문 건에 대한 출고 안내 개선
	 * 전체출고수량
	 */
	private java.lang.Integer totalDlivyQty;

	/**
	 * #44103, [개발] 복합 주문 건에 대한 출고 안내 개선
	 * 출고완료수량
	 */
	private java.lang.Integer dlivyComptQty;
	
	/**
	* 출고지시수량
	*/
	private String dlivyDrctQty;

	/**
	 * #44103, [개발] 복합 주문 건에 대한 출고 안내 개선
	 * 운송장개수
	 */
	private java.lang.Integer waybilQty;

	/**
	 * #44103, [개발] 복합 주문 건에 대한 출고 안내 개선
	 * 배송완료수량
	 */
	private java.lang.Integer dlvComptQty;
	
	/**
	 * 배송 상태 코드
	 */
	private String dlvStatCd;
	
	/**
	 * hblNo
	 */
	private String hblNo;

	/**
	 * #55122, [퀵배송]주문배송 출고완료시 이메일/문자발송 수정
	 *
	 * 출고지시 유형 코드
	 ㅁ. 출고지시 유형 : RLOR_TP
	 >. 주문 : ORD
	 >. 교환 : EXCHG
	 >. 맞교환 : DRT_EXCHG
	 >. 매장 픽업 : SHOP_PKUP
	 >. 수선 : REPAIR
	 >. 퀵배송 : QDLV
	 */
	private String dlivyDrctTpCd;

	private String godSumry;

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
