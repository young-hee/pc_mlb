/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.clm;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 클레임 입고 상품 적용 프로모션
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("clmWrhsGodAplPrm")
public class ClmWrhsGodAplPrm extends AbstractEntity {
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
	 * 클레임 번호
	 * ㅁ. CL || YYYYMMDD || 7자리 Cycle Sequence
	 * ㅁ. 수선 클레임인 경우 AS || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String clmNo;


	/**
	 * 적용 프로모션 순번	 
	 */
	private java.lang.Integer aplPrmTurn;


	/**
	 * 프로모션 번호
	 * ㅁ. PR || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String prmNo;


	/**
	 * 회원 쿠폰 번호
	 * ㅁ. CP || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String mbrCpnNo;


	/**
	 * 적용 유형 코드
	 * ㅁ. 적용 유형 :  APL_TP
	 *    >. 적용 : APL
	 *    >. 취소 : CNCL	 
	 */
	private String aplTpCd;


	/**
	 * 적용 단위 코드
	 * ㅁ. 적용 단위 : APL_UNIT
	 *    >. 주문 : ORD
	 *    >. 상품 : GOD	 
	 */
	private String aplUnitCd;


	/**
	 * 프로모션 유형 코드
	 * ㅁ. 프로모션 유형 : PRM_TP
	 *    >. 쿠폰 : CPN
	 *    >. 상품 할인 : GOD_DC
	 *    >. 주문 할인 : ORD_DC
	 *    >. 사은품 : GFT
	 *    >. 멤버쉽 포인트 : MBSH_PNT
	 *    >. 카드사 제휴 : CRCOM_AFF	 
	 */
	private String prmTpCd;


	/**
	 * 프로모션 세부 유형 코드
	 * 프로모션 세부 유형 코드
	 * ㅁ. 프로모션 세부 유형 : PRM_DTL_TP
	 *    >. 쿠폰 : CPN
	 *       >>. 상품쿠폰 : GOD_CPN
	 *       >>. 장바구니쿠폰 : BSK_CPN
	 *       >>. 배송비쿠폰 : DLV_CST_CPN
	 *    >. 상품 할인 : GOD_DC
	 *       >>. 상품 할인 : SUBD_GOD_DC
	 *    >. 주문 할인 : ORD_DC
	 *       >>. 묶음 할인 : BUNDLE_DC
	 *       >>. 교차 할인 : CRS_DC
	 *    >. 사은품 : GFT
	 *       >>. 상품 사은품 : GOD_GFT
	 *       >>. 주문 사은품 : ORD_GFT
	 *    >. 멤버쉽 포인트 : MBSH_PNT
	 *       >>. 추가 적립 : ADIT_SAV
	 *    >. 카드사 제휴 : CRCOM_AFF
	 *       >>. 청구 할인 : RQEST_DC
	 *       >>. 무이자 할부 : NINT_INSTM	 
	 */
	private String prmDtlTpCd;


	/**
	 * 적용 금액	 
	 */
	private java.math.BigDecimal aplAmt;


	/**
	 * 클레임 입고 상품 순번	 
	 */
	private java.lang.Integer clmWrhsGodTurn;


	/**
	 * 적용 수량	 
	 */
	private java.lang.Long aplQty;


	/**
	 * 클레임 입고 상품 사은품 순번	 
	 */
	private java.lang.Integer clmWrhsGodGftTurn;


	/**
	 * 클레임 입고 상품 사은품 명
	 * ㅁ. ASIS 데이터 이관용 컬럼
	 * 
	 * ㅁ. ASIS는 사은품을 상품으로 관리하지 않아 주문 상품 테이블에 입력 불가 하였음.	 
	 */
	private String clmWrhsGodGftNm;


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
