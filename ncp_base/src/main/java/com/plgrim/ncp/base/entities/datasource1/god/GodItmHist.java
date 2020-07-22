/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 상품 단품 이력
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godItmHist")
public class GodItmHist extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 단품 번호
	 * IT || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String itmNo;


	/**
	 * 단품 이력 순번	 
	 */
	private java.lang.Integer itmHistTurn;


	/**
	 * 상품 번호	 
	 */
	private String godNo;


	/**
	 * SKU 번호
	 * ㅁ. Stock Keeping Unit
	 *    >. 자사의 경우 ERP 상품 번호에 사이즈 옵션 값 코드를 붙인것을 SKU로 정의
	 *    >. 입점사는 입력된 ERP 상품 번호에 모든 옵션 값 코드를 붙여 사용	 
	 */
	private String skuNo;


	/**
	 * 단품 상태 코드
	 * ㅁ. 단품 상태 : ITM_STAT
	 *    >. 판매중 : SALE_PROGRS
	 *    >. 판매 종료 : SALE_END
	 *    >. 품절 : SLDOUT
	 *    >. 임시 품절 : TMP_SLDOUT	 
	 */
	private String itmStatCd;


	/**
	 * 단품 명	 
	 */
	private String itmNm;


	/**
	 * 옵션 코드 1	 
	 */
	private String optCd1;


	/**
	 * 옵션 명 1	 
	 */
	private String optNm1;


	/**
	 * 옵션 값 코드 1	 
	 */
	private String optValCd1;


	/**
	 * 옵션 값 명 1	 
	 */
	private String optValNm1;


	/**
	 * 옵션 코드 2	 
	 */
	private String optCd2;


	/**
	 * 옵션 명 2	 
	 */
	private String optNm2;


	/**
	 * 옵션 값 코드 2	 
	 */
	private String optValCd2;


	/**
	 * 옵션 값 명 2	 
	 */
	private String optValNm2;


	/**
	 * 옵션 코드 3	 
	 */
	private String optCd3;


	/**
	 * 옵션 명 3	 
	 */
	private String optNm3;


	/**
	 * 옵션 값 코드 3	 
	 */
	private String optValCd3;


	/**
	 * 옵션 값 명 3	 
	 */
	private String optValNm3;


	/**
	 * 옵션 코드 4	 
	 */
	private String optCd4;


	/**
	 * 옵션 명 4	 
	 */
	private String optNm4;


	/**
	 * 옵션 값 코드 4	 
	 */
	private String optValCd4;


	/**
	 * 옵션 값 명 4	 
	 */
	private String optValNm4;


	/**
	 * 옵션 코드 5	 
	 */
	private String optCd5;


	/**
	 * 옵션 명 5	 
	 */
	private String optNm5;


	/**
	 * 옵션 값 코드 5	 
	 */
	private String optValCd5;


	/**
	 * 옵션 값 명 5	 
	 */
	private String optValNm5;


	/**
	 * 총 가용 재고 수량
	 * ㅁ. 실재고 계산 방법
	 *    >. 총 가용 재고 수량 - 판매 예정 수 - IF(안전 재고 사용여부=Y,안전 재고 수,0)	 
	 */
	private java.lang.Long totUsefulInvQty;


	/**
	 * 판매 예정 수량
	 * ㅁ. 재고 수는 30분에 한번씩 연동 되기 때문에 ERP에 예약 영수증을 보내기전에까지는 판매 예정 재고에서 재고를 차감 해야 한다.
	 * ERP에 예약 영수증을 보낸 후 재고 수와 판매 예정 수는  보낸 량 만큼 차감
	 * 
	 * ㅁ. 실재고 계산 방법
	 *    >. 총 가용 재고 수량 - 판매 예정 수 - IF(안전 재고 사용여부=Y,안전 재고 수,0)	 
	 */
	private java.lang.Long salePrearngeQty;


	/**
	 * 안전 재고 사용 여부	 
	 */
	private String safeInvUseYn;


	/**
	 * 안전 재고 수량	 
	 */
	private java.lang.Long safeInvQty;


	/**
	 * 온라인 한정 재고 수량
	 * ㅁ. 한정 재고 여부가 Y인 경우 온라인에서 사용하는 재고	 
	 */
	private java.lang.Long onlneLmttInvQty;


	/**
	 * 제휴 업체 한정 재고 수량
	 * ㅁ. 한정 재고 여부가 Y인 경우 제휴업체 매출에서 사용하는 재고	 
	 */
	private java.lang.Long affComLmttInvQty;


	/**
	 * 예약 수량	 
	 */
	private java.lang.Long resveQty;


	/**
	 * 예약 재고 수량	 
	 */
	private java.lang.Long resveInvQty;


	/**
	 * 입점 업체 재고 알림 여부	 
	 */
	private String partmalComInvNtcnYn;


	/**
	 * 입점 업체 재고 알림 수량
	 * ㅁ. 최초 예약 초기 수량	 
	 */
	private java.lang.Long partmalComInvNtcnQty;


	/**
	 * 입고 일시	 
	 */
	private java.util.Date wrhsDt;


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
