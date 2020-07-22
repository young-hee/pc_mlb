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
 * 이벤트 SNS 공유
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("evtSnsCnrs")
public class EvtSnsCnrs extends AbstractEntity {
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
	 * 경품 순번	 
	 */
	private java.lang.Integer freeGiftTurn;


	/**
	 * 회원 번호
	 * ㅁ. 회원 가입시 부여되는 고유한 관리 번호
	 *    >. MB || YYYYMMDD || 7자리 Cycle	 
	 */
	private String mbrNo;


	/**
	 * SNS 공유 일시	 
	 */
	private java.util.Date snsCnrsDt;


	/**
	 * 이벤트 참여 일련번호
	 * 이벤트에 대한 참여 일련번호	 
	 */
	private java.lang.Long evtPartcptnSn;


	/**
	 * 적용 순번	 
	 */
	private java.lang.Integer aplTurn;


	/**
	 * SNS 구분 코드
	 * ㅁ. SNS 구분 : SNS_SECT
	 *    >. 페이스북 : FACEBUK
	 *    >. 트위터 : TWITTER
	 *    >. 인스타그램 : INSTAGRAM
	 *    >. 카카오톡 : KKO_TAK
	 *    >. 카카오 스토리 : KKO_STORY	 
	 */
	private String snsSectCd;


	/**
	 * SNS ID	 
	 */
	private String snsId;


	/**
	 * 공유 내용	 
	 */
	private String cnrsCont;


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
