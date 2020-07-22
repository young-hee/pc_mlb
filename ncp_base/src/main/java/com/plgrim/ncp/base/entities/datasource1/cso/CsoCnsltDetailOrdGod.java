/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.cso;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 고객서비스 상담 상세 주문 상품
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("csoCnsltDetailOrdGod")
public class CsoCnsltDetailOrdGod extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 상담 일련번호	 
	 */
	private java.lang.Long cnsltSn;


	/**
	 * 상담 상세 순번
	 * ㅁ. 상담상세 이력에 대한 순번으로 상담 번호별 순번을 발급하도록 한다.
	 *    >. "고객 상담 상세 이력.상담 상세 순번"의 MAX값 + 1로 등록 되도록 한다.
	 *    >. 숫자로 최대 5자리 " 99999" 로 관리 한다.
	 *       (ORACLE 기준 Format)	 
	 */
	private java.lang.Integer cnsltDetailTurn;


	/**
	 * 상담 상세 주문 상품 순번	 
	 */
	private java.lang.Integer cnsltDetailOrdGodTurn;


	/**
	 * 상품 명	 
	 */
	private String godNm;


	/**
	 * 주문 상품 구분 코드
	 * ㅁ. 주문 상품 구분 : ORD_GOD_SECT
	 *    >. 주문 : ORD
	 *    >. 상품 : GOD	 
	 */
	private String ordGodSectCd;


	/**
	 * 상품 번호
	 * ㅁ. 상품 유형 1자리 || 업체 코드 3자리 || YYMMDD || 5자리 Cycle Sequence	 
	 */
	private String godNo;


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
	 * 등록자 ID	 
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
