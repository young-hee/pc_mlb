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
 * 이벤트 당첨 경품
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("evtPrizeFreeGift")
public class EvtPrizeFreeGift extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 이벤트 참여 일련번호
	 * 이벤트에 대한 참여 일련번호	 
	 */
	private java.lang.Long evtPartcptnSn;


	/**
	 * 당첨 순번	 
	 */
	private java.lang.Integer prizeTurn;


	/**
	 * 이벤트 번호
	 * ㅁ. EV || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String evtNo;


	/**
	 * 경품 순번	 
	 */
	private java.lang.Integer freeGiftTurn;


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
