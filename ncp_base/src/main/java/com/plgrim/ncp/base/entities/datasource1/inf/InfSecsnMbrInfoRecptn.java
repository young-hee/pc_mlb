/**
 * @author : Generator(Generator)
 * @date : 2018-06-11
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.inf;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 인터페이스 탈퇴 회원 정보 수신
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("infSecsnMbrInfoRecptn")
public class InfSecsnMbrInfoRecptn extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ERP 고객번호
	 * ㅁ. CID : 회원 일렬 번호	 
	 */
	private String erpCstmrNo;


	/**
	 * 탈퇴 일시
	 * ㅁ. DROP_DTM : 탈퇴일시	 
	 */
	private java.util.Date secsnDt;


	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;

	
}
