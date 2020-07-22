/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.evt;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 이벤트 연관 상품
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("evtRelateGod")
public class EvtRelateGod extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 이벤트 번호
	 * ㅁ. EV || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String evtNo;


	/**
	 * 구분자 순번	 
	 */
	private java.lang.Integer sprtrTurn;


	/**
	 * 상품 번호
	 * ㅁ. 상품 유형 1자리 || 업체 코드 3자리 || YYMMDD || 5자리 Cycle Sequence	 
	 */
	private String godNo;


	/**
	 * 정렬 순서
	 * 우선 순위	 
	 */
	private java.lang.Integer sortSeq;


	/**
	 * 전시 여부
	 * 전시여부	 
	 */
	private String dspYn;


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
	 */
	private String udterId;


	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;

	
}
