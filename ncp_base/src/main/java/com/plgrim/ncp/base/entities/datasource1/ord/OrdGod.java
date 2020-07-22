/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.ord;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 주문 상품
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("ordGod")
public class OrdGod extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 주문 번호
	 * OD || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String ordNo;


	/**
	 * 주문 상품 순번	 
	 */
	private java.lang.Integer ordGodTurn;


	/**
	 * 배송 수거지 순번	 
	 */
	private java.lang.Integer dlvPcupspTurn;


	/**
	 * 상품 번호
	 * 상품 유형 1자리 || 업체 코드 3자리 || YYYYMMDD || 6자리 Cycle Sequence	 
	 */
	private String godNo;


	/**
	 * 단품 번호
	 * IT || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String itmNo;


	/**
	 * 상품 이력 순번	 
	 */
	private java.lang.Integer godHistTurn;


	/**
	 * 단품 이력 순번	 
	 */
	private java.lang.Integer itmHistTurn;


	/**
	 * ERP 상품 번호	 
	 */
	private String erpGodNo;


	/**
	 * SKU 번호
	 * ㅁ. Stock Keeping Unit
	 *    >. 자사의 경우 ERP 상품 번호에 사이즈 옵션 값 코드를 붙인것을 SKU로 정의
	 *    >. 입점사는 입력된 ERP 상품 번호에 모든 옵션 값 코드를 붙여 사용	 
	 */
	private String skuNo;


	/**
	 * 상품 명	 
	 */
	private String godNm;


	/**
	 * 모바일 상품 명	 
	 */
	private String mobileGodNm;


	/**
	 * 단품 명	 
	 */
	private String itmNm;


	/**
	 * 색상 명	 
	 */
	private String colorNm;


	/**
	 * 색상 코드	 
	 */
	private String colorCd;


	/**
	 * 컬러칩 이미지 URL	 
	 */
	private String clorChipImgUrl;


	/**
	 * 브랜드 ID
	 * ㅁ. 온라인에서 사용하는 브랜드 ID	 
	 */
	private String brndId;


	/**
	 * 브랜드 그룹 ID
	 * ㅁ. 온라인에서 사용하는 브랜드 ID	 
	 */
	private String brndGrpId;


	/**
	 * 상품 부피
	 * ㅁ. SUM(상품.부피)	 
	 */
	private java.math.BigDecimal godVol;


	/**
	 * 상품 무게
	 * ㅁ. SUM(상품.무게)	 
	 */
	private java.math.BigDecimal godWt;


	/**
	 * 상품 유형 코드
	 * ㅁ. 상품구분 : GOD_TP
	 *    >. 일반상품 : GNRL_GOD
	 *    >. 사입 사은품 : PCHS_GFT
	 *    >. 전환 사은품 : CNVRS_GFT
	 *    >. 세트상품 : SET_GOD
	 *    >. 패키지 상품 : PCKAGE_GOD
	 *    >. 상품권 : GFCT
	 *    >. 마일리지 상품 : MILE_GOD	 
	 */
	private String godTpCd;


	/**
	 * 입점 구분 코드
	 * ㅁ. 입점 구분 : PARTMAL_SECT
	 *    >. 자사 : MCOM
	 *    >. 입점 : PARTMAL	 
	 */
	private String partmalSectCd;


	/**
	 * 리스트 이미지 URL	 
	 */
	private String lstImgUrl;


	/**
	 * 업체 ID	 
	 */
	private String comId;


	/**
	 * 입점 업체 수수료 율	 
	 */
	private java.math.BigDecimal partmalComFeeRt;


	/**
	 * 입점 업체 확인 여부	 
	 */
	private String partmalComCnfirmYn;


	/**
	 * 입점 업체 확인 일시	 
	 */
	private java.util.Date partmalComCnfirmDt;


	/**
	 * 입점 업체 확인 관리자 ID	 
	 */
	private String partmalComCnfirmAdminId;


	/**
	 * 주문제작 상품 여부	 
	 */
	private String ordmkGodYn;


	/**
	 * 상품 출고 대기 시간
	 * ㅁ. 일반 상품 : 48시간
	 * ㅁ. 주문제작 상품 : 주문 완료 후 출고완료까지 소요되는 시간을 입력	 
	 */
	private java.math.BigDecimal godDlivyWaitTime;


	/**
	 * 국내 배송비 정책 일련번호	 
	 */
	private java.lang.Long dmstcDlvCstPlcSn;


	/**
	 * 해외 배송 국내 배송비 정책 일련번호	 
	 */
	private java.lang.Long ovseaDlvDmstcDlvCstPlcSn;


	/**
	 * 해외 배송비 정책 일련번호	 
	 */
	private java.lang.Long ovseaDlvCstPlcSn;


	/**
	 * 판매 매장 코드
	 * ㅁ. ERP와 연동하는 판매 매장 코드
	 * 
	 * ㅁ. 판매 매장 : SALE_SHOP
	 *    >. Online패션피아 : B04A
	 *    >. Online8s : B04B
	 *    >. Beaker자사 : B04C
	 *    >. Beaker위탁 : B04D
	 *    >. Online제휴몰 : B04E
	 *    >. Beaker제휴몰위탁 : B04F	 
	 */
	private String saleShopCd;


	/**
	 * 주문 수량	 
	 */
	private java.lang.Long ordQty;


	/**
	 * 통화 코드
	 * ISO 4217을 준수
	 * 사용 코드 : CRNCY    통화
	 * KRW    대한민국 원
	 * USD    미국 달러
	 * CNY     런민비 (위안)
	 * JPY    일본 엔	 
	 */
	private String crncyCd;


	/**
	 * 환율 적용 시작 일시	 
	 */
	private java.util.Date exchgRtAplBegDt;


	/**
	 * 기준 통화 금액
	 * 사이트의 기준 통화의 기준이 되는 금액	 
	 */
	private java.math.BigDecimal stdrCrncyAmt;


	/**
	 * 결제 환율 통화 금액
	 * 사이트의 기준 통화를 기준으로 기준 통화금액에 환율을 적용한 금액	 
	 */
	private java.math.BigDecimal payExchgRtCrncyAmt;


	/**
	 * 정소가	 
	 */
	private java.math.BigDecimal rtlPrc;


	/**
	 * 판매 금액
	 * ㅁ. 판매 금액(판매가)
	 *    >. 일반 판매 금액 = 정소가*주문수량 - 웹 할인 금액
	 *    >. 임직원 판매 금액 = 정소가*주문수량 - 임직원 할인 금액
	 * 
	 * ㅁ. 공통 공식
	 *    >. 정소가*주문수량 - ( 웹 할인 금액 + 임직원 할인 금액)	 
	 */
	private java.math.BigDecimal saleAmt;


	/**
	 * 웹 할인 금액
	 * ㅁ. 웹 할인 금액 = (정소가 - 실소가)*주문수량
	 * ㅁ. 일반 판매인 경우 입력 됨	 
	 */
	private java.math.BigDecimal webDcAmt;


	/**
	 * 임직원 할인 금액
	 * ㅁ. 임직원 할인 금액 = (정소가 - 임직원가)*주문수량
	 * ㅁ. 임직원 판매인 경우 입력 됨	 
	 */
	private java.math.BigDecimal empDcAmt;


	/**
	 * 행우세 금액	 
	 */
	private java.math.BigDecimal pkagTaxAmt;


	/**
	 * 전체 할인 금액	 
	 */
	private java.math.BigDecimal allDcAmt;


	/**
	 * 상품 할인 금액	 
	 */
	private java.math.BigDecimal godDcAmt;


	/**
	 * 묶음 할인 금액	 
	 */
	private java.math.BigDecimal bundleDcAmt;


	/**
	 * 교차 할인 금액	 
	 */
	private java.math.BigDecimal crsDcAmt;


	/**
	 * 상품 쿠폰 할인 금액	 
	 */
	private java.math.BigDecimal godCpnDcAmt;


	/**
	 * 장바구니 쿠폰 할인 금액	 
	 */
	private java.math.BigDecimal bskCpnDcAmt;


	/**
	 * 즉시 할인 금액	 
	 */
	private java.math.BigDecimal imdtlDcAmt;


	/**
	 * 통합 포인트 사용 금액	 
	 */
	private java.math.BigDecimal unityPntUseAmt;


	/**
	 * 이벤트 포인트 사용 금액	 
	 */
	private java.math.BigDecimal evtPntUseAmt;


	/**
	 * 웹포인트 사용 금액
	 * 적립은 되고 있으나 사용 할 수 없는부분	 
	 */
	private java.math.BigDecimal webpntUseAmt;


	/**
	 * 마일리지 사용 금액
	 * 빈폴 마일리지 제도는 적립은 하지 않으나  마일지지 전체 소진시까지 유지	 
	 */
	private java.math.BigDecimal mileUseAmt;


	/**
	 * 통합 포인트 적립 금액	 
	 */
	private java.math.BigDecimal unityPntAccmlAmt;


	/**
	 * 이벤트 포인트 적립 금액	 
	 */
	private java.math.BigDecimal evtPntAccmlAmt;


	/**
	 * 웹포인트 적립 금액
	 * ㅁ. 2016-04-02 이전 데이터는 모두 정리하고
	 * 
	 * ㅁ. 2016-04-02 이후 데이터는 퍼플코인으로 변경 되어 사용.	 
	 */
	private java.math.BigDecimal webpntAccmlAmt;


	/**
	 * 마일리지 적립 금액
	 * 빈폴 마일리지 제도는 적립은 하지 않으나  마일지지 전체 소진시까지 유지	 
	 */
	private java.math.BigDecimal mileAccmlAmt;


	/**
	 * 예약 판매 상품 여부	 
	 */
	private String resveSaleGodYn;


	/**
	 * 예약 주문 결제 구분 코드	 
	 */
	private String resveOrdPaySectCd;


	/**
	 * 예약 주문 부분 결제 금액	 
	 */
	private java.math.BigDecimal resveOrdPartPayAmt;


	/**
	 * 예약 주문 결제 마감 일시	 
	 */
	private java.util.Date resveOrdPayClosDt;


	/**
	 * 예약 주문 출고 승인 코드	 
	 */
	private String resveOrdDlivyAprvCd;


	/**
	 * 예약 주문 출고 예정 일자	 
	 */
	private String resveOrdDlivyPrearngeDate;


	/**
	 * 정산 일시	 
	 */
	private java.util.Date calDt;


	/**
	 * 제휴사 주문 상품 번호	 
	 */
	private String affComOrdGodNo;


	/**
	 * 등록자 ID
	 * 등록한 관리자 번호	 
	 */
	private String regtrId;


	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;


	/**
	 * 수정자 ID
	 * 수정한 관리자 번호	 
	 */
	private String udterId;


	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;


	/**
	 * 본사 재고 주문 수량
	 * ㅁ. 본사에서 할당된 재고 수량을 체크하기 위한 컬럼	 
	 */
	private java.lang.Long hoffInvOrdQty;

	/**
	 * 고객 구매 확정 여부
	 * 주문 상품 별 구매 확정을 관리
	 */
	private String cstmrPchDcsnYn;
	
	/**
	 * 고객 구매 확정 일시
	 */
	private java.util.Date cstmrPchDcsnDt;
	
	private String saleShopId;
}