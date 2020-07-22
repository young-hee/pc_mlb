/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 시스템 달력
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysCldr")
public class SysCldr extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 시스템 일자	 
	 */
	private String sysDate;


	/**
	 * 음력일자	 
	 */
	private String lrrdate;


	/**
	 * 년도	 
	 */
	private String year;


	/**
	 * 분기	 
	 */
	private String qu;


	/**
	 * 월	 
	 */
	private String mt;


	/**
	 * 주	 
	 */
	private String week;


	/**
	 * 일	 
	 */
	private String day;


	/**
	 * 요일 코드
	 * ㅁ. 요일 : DOW
	 *    >. 일요일 : SUN
	 *    >. 월요일 : MON
	 *    >. 화요일 : TUES
	 *    >. 수요일 : WED
	 *    >. 목요일 : THUR
	 *    >. 금요일 : FRI
	 *    >. 토요일 : SAT	 
	 */
	private String dowCd;


	/**
	 * 공휴일 여부	 
	 */
	private String hldyYn;


	/**
	 * 설명	 
	 */
	private String dscr;


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
