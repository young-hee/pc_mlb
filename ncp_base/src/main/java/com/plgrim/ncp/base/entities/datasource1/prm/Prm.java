/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.prm;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 프로모션
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("prm")
public class Prm extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 프로모션 번호
	 * ㅁ. PR || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String prmNo;


	/**
	 * 연계 프로모션 번호
	 * ㅁ. PR || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String cntcPrmNo;


	/**
	 * 연계 프로모션 순번	 
	 */
	private java.lang.Integer cntcPrmTurn;


	/**
	 * 프로모션 유형 코드
	 * ㅁ. 프로모션 유형 : PRM_TP
	 *    >. 쿠폰 : CPN
	 *    >. 상품 할인 : GOD_DC
	 *    >. 주문 할인 : ORD_DC
	 *    >. 사은품 : GFT
	 *    >. 멤버쉽 포인트 : MBSH_PNT
	 *    >. 웹포인트 : WEBPNT
	 *    >. 카드사 제휴 : CRCOM_AFF	 
	 */
	private String prmTpCd;


	/**
	 * 프로모션 세부 유형 코드
	 * ㅁ. 프로모션 세부 유형 : PRM_DTL_TP
	 *    >. 쿠폰 : CPN
	 *       >>. 상품쿠폰 : GOD_CPN
	 *       >>. 장바구니쿠폰 : BSK_CPN
	 *       >>. 배송비쿠폰 : DLV_CST_CPN
	 *       >>. 반품 쿠폰 : RTGOD_CPN
	 *       >>. 교환 쿠폰 : EXCHG_CPN
	 *    >. 상품 할인 : GOD_DC
	 *       >>. 상품 할인 : SUBD_GOD_DC
	 *    >. 주문 할인 : ORD_DC
	 *       >>. 함께 할인 : BUNDLE_DC
	 *       >>. 교차 할인 : CRS_DC
	 *    >. 사은품 : GFT
	 *       >>. 상품 사은품 : GOD_GFT
	 *       >>. 주문 사은품 : ORD_GFT
	 *    >. 멤버쉽 포인트 : MBSH_PNT
	 *       >>. 추가 적립 : ADIT_SAV
	 *    >. 웹포인트 : WEBPNT
	 *       >>. 추가 적립 : ADIT_SAV
	 *    >. 카드사 제휴 : CRCOM_AFF
	 *       >>. 청구 할인 : RQEST_DC
	 *       >>. 무이자 할부 : NINT_INSTM
	 * 
	 * ㅁ. 교차 할인의 경우 프로모션 적용 상품을 사용 하지 않고  프로모션 교차 할인 적용 상품을 사용 하여 작업	 
	 */
	private String prmDtlTpCd;


	/**
	 * 정소가 적용 여부
	 * ㅁ. Y인 경우 정소가와 판매가가 동일한 경우만 프로모션 적용
	 * 
	 * ㅁ. 즉시 할인 쿠폰만 적용중	 
	 */
	private String rtlPrcAplYn;


	/**
	 * 프로모션 상태 코드
	 * ㅁ. 프로모션 상태 : PRM_STAT
	 *    >. 승인대기 : APRV_WAIT
	 *    >. 승인 : APRV
	 *    >. 중지 : STPGE	 
	 */
	private String prmStatCd;


	/**
	 * 프로모션 취소 설명	 
	 */
	private String prmCnclDscr;


	/**
	 * 프로모션 명
	 * 관리자가 진행하고자 하는 프로모션의 명칭	 
	 */
	private String prmNm;


	/**
	 * 프로모션 설명
	 * 관리자가 진행하는 프로모션에 대한 상세 내용	 
	 */
	private String prmDscr;


	/**
	 * ERP 캠페인 ID	 
	 */
	private String erpCmpgId;


	/**
	 * ERP 쿠폰 ID	 
	 */
	private String erpCpnId;


	/**
	 * ERP 캠페인 명
	 * 관리자가 진행하고자 하는 프로모션의 명칭	 
	 */
	private String erpCmpgNm;


	/**
	 * 프로모션 시작 일자
	 * 프로모션의 시작일시	 
	 */
	private String prmBegDate;


	/**
	 * 프로모션 종료 일자
	 * 프로모션의 종료일시	 
	 */
	private String prmEndDate;


	/**
	 * 프로모션 일자별 시작 시각	 
	 */
	private String prmDatebyBegHour;


	/**
	 * 프로모션 일자별 종료 시각	 
	 */
	private String prmDatebyEndHour;


	/**
	 * 프로모션 적용 요일집계	 
	 */
	private String prmAplDowsmon;


	/**
	 * 대표 이미지 사용 구분 코드
	 * ㅁ. 대표 이미지 사용 구분 : RPRST_IMG_USE_SECT
	 *    >. PC/Mobile 통합 사용 : PC_MOBILE_UNITY_USE
	 *    >. PC/Mobile 개별 사용 : PC_MOBILE_IND_USE	 
	 */
	private String rprstImgUseSectCd;


	/**
	 * 대표 이미지 PC URL	 
	 */
	private String rprstImgPcUrl;


	/**
	 * 대표 이미지 PC 대체 내용
	 * 컨텐츠 이미지 대체 텍스트	 
	 */
	private String rprstImgPcAltrtvCont;


	/**
	 * 대표 이미지 모바일 URL	 
	 */
	private String rprstImgMobileUrl;


	/**
	 * 대표 이미지 모바일 대체 내용
	 * 컨텐츠 이미지 대체 텍스트	 
	 */
	private String rprstImgMobileAltrtvCont;


	/**
	 * 구매 제한 수량
	 * 할인 프로모션 별 구매 제한 수량
	 * 상품할인에 대한 경우 상품별 구매 제한 수량이 되며 상품별로 제한 수량을 별도 설정을 하는 경우엔 적용 Entity에 속해야 됨	 
	 */
	private java.lang.Long pchLmitQty;


	/**
	 * 최대 할인 가능 금액
	 * 할인 금액, 할인 율 과는 별개로  할인을 제한하는 한도 금액	 
	 */
	private java.math.BigDecimal maxDcPsbAmt;


	/**
	 * 할인 구분 코드
	 * ㅁ. 할인 구분 : DC_SECT(DC)
	 *    >. 정액 : FIXAMT(DC)
	 *    >. 정률 : FIXRT(DC)
	 *    >. 균일가 : FLAT_PRC(DC)
	 *    >. 배송비 무료 : DLV_CST_FREE(DLV_CST)
	 * 
	 * ㅁ. () 보조코드1	 
	 */
	private String dcSectCd;


	/**
	 * 할인 금액
	 * 할인 유형별 차감되는 할인 금액	 
	 */
	private java.math.BigDecimal dcAmt;


	/**
	 * 할인 율
	 * 할인 유형별 차감되는 할인 율	 
	 */
	private java.math.BigDecimal dcRt;


	/**
	 * 판매 단가
	 * 할인 유형별 지정된 판매 금액
	 * 
	 * 세트할인도 균일가 처리할 수 있음	 
	 */
	private java.math.BigDecimal saleUntPrc;


	/**
	 * 주문 할인 적용 코드
	 * ㅁ. 주문 할인 적용 : ORD_DC_APL
	 *    >. 조건수량 단위(마다) : CND_QTY_UNIT
	 *    >. 조건수량 이상(이상) : CND_QTY_OVER	 
	 */
	private String ordDcAplCd;


	/**
	 * 주문 할인 연관 기획전 일련번호
	 * "1"부터 오라클 시퀀스(MPD_SPDP_BASE_SQ01)를 사용해서 발행	 
	 */
	private java.lang.Long ordDcRelatePromtSn;


	/**
	 * 연관 URL	 
	 */
	private String relateUrl;


	/**
	 * 상품 사은품 구분 코드
	 * ㅁ. 상품 사은품 구분 : GOD_GFT_SECT
	 *    >. 1+1 : 1_1
	 *    >. 1+N : 1_N
	 *    >. N+1 : N_1	 
	 */
	private String godGftSectCd;


	/**
	 * 주문 사은품 구매 기준 코드
	 * ㅁ. 주문 사은품 구매 기준 : ORD_GFT_PCH_STDR
	 *    >. 금액 기준 : AMT_STDR
	 *    >. 수량 기준 : QTY_STDR	 
	 */
	private String ordGftPchStdrCd;


	/**
	 * 주문 사은품 적용 조건 수량
	 * ㅁ. N+1 일때만 사용	 
	 */
	private java.lang.Long ordGftAplCndQty;


	/**
	 * 주문 사은품 적용 조건 금액
	 * 사은품프로모션유형이
	 * 주문사은품 일때
	 * 입력된 금액 이상일경우	 
	 */
	private java.math.BigDecimal ordGftAplCndAmt;


	/**
	 * 주문 사은품 설명	 
	 */
	private String ordGftDscr;


	/**
	 * 주문 사은품 주의사항 내용	 
	 */
	private String ordGftAtndmatterCont;


	/**
	 * 사은품 선택 가능 수량	 
	 */
	private java.lang.Long gftChoisePsbQty;


	/**
	 * 제휴 업체 적용 코드
	 * ㅁ. 제휴 업체 적용 : AFF_COM_APL
	 *    >. 제휴 업체 미적용 : AFF_COM_NAPL
	 *    >. 제휴 업체 적용 : AFF_COM_APL	 
	 */
	private String affComAplCd;


	/**
	 * 적립 구분 코드
	 * ㅁ. 적립 유형 : ACCML_SECT
	 *    >. 정액 : FIXAMT
	 *    >. 정률 : FIXRT
	 * 
	 * ㅁ 정액
	 *   - 정해진 적립 금액
	 * 
	 * ㅁ 정률
	 *   - 정해진 적립 비율	 
	 */
	private String accmlSectCd;


	/**
	 * 적립 금액
	 * 적립 유형 정액에 다른 적립 금액	 
	 */
	private java.math.BigDecimal accmlAmt;


	/**
	 * 적립 율
	 * 적립 유형 정률에 다른 적립 비율	 
	 */
	private java.math.BigDecimal accmlRt;


	/**
	 * 디바이스 구분 코드
	 * ㅁ. 디바이스 구분 : DVC_SECT
	 *    >. PC : PC
	 *    >. 모바일 : MOBILE
	 *    >. PC + 모바일 : PC_MOBILE	 
	 */
	private String dvcSectCd;


	/**
	 * 무이자 월수
	 * 한번 지급시 지급되는 수량	 
	 */
	private java.lang.Integer nintMons;


	/**
	 * 상품 할인 중복 코드
	 * ㅁ. 상품 할인 중복 : GOD_DC_DPLCT
	 *    >. 허용 : PERM
	 *    >. 불가 : IMPS	 
	 */
	private String godDcDplctCd;


	/**
	 * 상품 쿠폰 중복 코드
	 * ㅁ. 상품 쿠폰 중복 : GOD_CPN_DPLCT
	 *    >. 허용 : PERM
	 *    >. 불가 : IMPS	 
	 */
	private String godCpnDplctCd;


	/**
	 * 적용 업체 구분 코드
	 * ㅁ. 적용 업체 구분 : APL_COM_SECT
	 *    >. 전체 : ALL
	 *    >. 입점 업체 : PARTMAL_COM	 
	 */
	private String aplComSectCd;


	/**
	 * 입점 업체 ID	 
	 */
	private String partmalComId;


	/**
	 * 입점 업체 비용 부담 율	 
	 */
	private java.math.BigDecimal partmalComCostBndRt;


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

	
}