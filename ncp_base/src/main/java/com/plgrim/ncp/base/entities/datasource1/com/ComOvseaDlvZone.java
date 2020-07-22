/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.com;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 업체 해외 배송 ZONE
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("comOvseaDlvZone")
public class ComOvseaDlvZone extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 해외 배송비 정책 일련번호	 
	 */
	private java.lang.Long ovseaDlvCstPlcSn;


	/**
	 * ZONE 순번
	 * 지정된 국가별로 그룹하여 묶은 번호	 
	 */
	private java.lang.Integer zoneTurn;


	/**
	 * ZONE 명	 
	 */
	private String zoneNm;


	/**
	 * ZONE 설명	 
	 */
	private String zoneDscr;


	/**
	 * 사용 여부	 
	 */
	private String useYn;


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
