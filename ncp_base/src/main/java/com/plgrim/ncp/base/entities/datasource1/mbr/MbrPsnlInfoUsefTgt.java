/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.mbr;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 회원 개인정보 이용 대상
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mbrPsnlInfoUsefTgt")
public class MbrPsnlInfoUsefTgt extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 개인정보 이용 일련번호
	 * 개인정보 이용 일련번호	 
	 */
	private java.lang.Long psnlInfoUsefSn;


	/**
	 * 회원 번호
	 * ㅁ. 회원 가입시 부여되는 고유한 관리 번호
	 *    >. MB || YYYYMMDD || 7자리 Cycle	 
	 */
	private String mbrNo;


	/**
	 * 이용 일시
	 * ㅁ. 개인정보를 이용한 일자를 의미하며, 이용한 시점의 초단위까지 관리 한다.
	 *    >. YYYYMMDD HH24:MI:SS
	 *       (ORACLE 기준 Format)	 
	 */
	private java.util.Date usefDt;


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
