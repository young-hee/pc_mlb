/**
 * @package : com.plgrim.ncp.base.entities..mbr
 * @author : jackie(jackie)
 * @date : 20150624
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.biz.promotion.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 회원 발급 쿠폰
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mbrIssuCpnResult")
public class MbrIssuCpnResult extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 회원 쿠폰 번호
ㅁ. CP || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String mbrCpnNo;
	/**
	 * 회원 번호
ㅁ. 회원 가입시 부여되는 고유한 관리 번호
   >. MB || YYYYMMDD || 7자리 Cycle	 
	 */
	private String mbrNo;
	/**
	 * 쿠폰 유형 코드
ㅁ. 고객에게 지급되는 쿠폰의 종류를 정의 함

ㅁ. 쿠폰 유형 : CPN_TP
   >. 배송비쿠폰 : DLV_CPN
   >. 상품쿠폰 : GOD_CPN
   >. 장바구니쿠폰 : BSK_CPN	 
	 */
	private String cpnTpCd;
	/**
	 * 쿠폰 상태 코드
ㅁ. 쿠폰 상태 : CPN_STAT
   >. 미사용 : NO_USE
   >. 사용불가 : USE_IMPS
   >. 사용 : USE

ㅁ. 쿠폰의 상태를 관리
  >. 미사용(사용 가능하지만 회원이 사용하지 않은 상태-기능정상)
  >. 사용불가(쿠폰의 사용을 임의적으로 제한한 상태-기능 제한)
  >. 사용(정상적 사용한 쿠폰-기능 소멸의 의미를 지님-기능소멸)

=== 논리적 상태===
  >. 사용대기(사용가능한 기간이 도래하지 않은 상태-기능제한)
  >. 기한만료(사용하지 않았으나 쿠폰 사용기한이 지나버린 상태-기능소멸)
	 
	 */
	private String cpnStatCd;
	/**
	 * 쿠폰 발행 일시
ㅁ. 쿠폰을 발행한 일자로 발행한 시점의 초단위까지 관리 한다.
   >. YYYYMMDD HH24:MI:SS
      (ORACLE 기준 Format)	 
	 */
	private java.util.Date cpnPubliDt;
	/**
	 * 유효 시작 일자
ㅁ. 쿠폰사용 가능 한 일자로 초단위까지 관리 한다.
   >. YYYYMMDD HH24:MI:SS
      (ORACLE 기준 Format)	 
	 */
	private String validBegDate;
	/**
	 * 유효 종료 일자
ㅁ. 쿠폰의 효력이 정지되는 일자로 초단위까지 관리 한다.
   >. YYYYMMDD HH24:MI:SS
     (ORACLE 기준 Format)	 
	 */
	private String validEndDate;
	/**
	 * 쿠폰 사용 일시
ㅁ. 쿠폰을 사용한 일자를 의미하며 사용한 시점의 초단위까지 관리 한다.
   >. YYYYMMDD HH24:MI:SS
      (ORACLE 기준 Format)
	 
	 */
	private java.util.Date cpnUseDt;
	/**
	 * 주문 번호
OD || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String ordNo;
	/**
	 * 클레임 번호
CL || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String clmNo;
	/**
	 * 프로모션 번호
ㅁ. PR || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String prmNo;
	/**
	 * 쿠폰 인증 코드
프로모션 코드로 난수로 발행된 쿠폰 번호	 
	 */
	private String cpnCrtfcCd;
	/**
	 * 발급 관리자 ID	 
	 */
	private String issuAdminId;
	/**
	 * 쿠폰 사용 불가 사유 설명	 
	 */
	private String cpnUseImpsResnDscr;
	/**
	 * 등록자 ID
등록한 관리자 번호	 
	 */
	private String regtrId;
	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;
	/**
	 * 수정자 ID
수정한 관리자 번호	 
	 */
	private String udterId;
	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;

	/**
	 * 결과 코드
	 */
	private String rstCd;

	/**
	 * 오류 메세지 
	 */
	private String errMsg;
	
}
