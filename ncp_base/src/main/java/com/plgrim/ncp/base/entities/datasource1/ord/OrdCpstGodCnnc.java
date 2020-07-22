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
 * 주문 구성 상품 연결
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("ordCpstGodCnnc")
public class OrdCpstGodCnnc extends AbstractEntity {
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
	 * 주문 구성 상품 순번	 
	 */
	private java.lang.Integer ordCpstGodTurn;


	/**
	 * 패키지형상품 유형 코드
	 * ㅁ. 패키지형상품 유형 : PCKAGE_GOD_TP
	 *    >. 세트상품 : SET_GOD
	 *    >. 패키지 상품 : PCKAGE_GOD
	 *    >. 추가 구성 상품 : ADIT_CPST_GOD
	 *    >. 추가 구매 상품 : ADIT_PCH_GOD	 
	 */
	private String pckageGodTpCd;


	/**
	 * 구성 상품 수량	 
	 */
	private java.lang.Long cpstGodQty;


	/**
	 * 정렬 순서	 
	 */
	private java.lang.Integer sortSeq;


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