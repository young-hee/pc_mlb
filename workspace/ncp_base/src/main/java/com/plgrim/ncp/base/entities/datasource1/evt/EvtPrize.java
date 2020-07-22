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
 * 이벤트 당첨
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("evtPrize")
public class EvtPrize extends AbstractEntity {
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
	 * 당첨 순위
	 * ㅁ. 선착순이나 등수별로 차등지급을
	 * 가능하게 한다.	 
	 */
	private java.lang.Integer prizeRank;


	/**
	 * 이벤트 번호
	 * ㅁ. EV || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String evtNo;


	/**
	 * 당첨 일시
	 * 이벤트가 시작되는 시점	 
	 */
	private java.util.Date prizeDt;


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