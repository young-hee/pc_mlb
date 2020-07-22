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
 * 시스템 우편번호 임시
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysPostNoTmpr")
public class SysPostNoTmpr extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 우편번호
	 * ㅁ. 우편번호 관리	 
	 */
	private String postNo;


	/**
	 * 신규 우편번호
	 * ㅁ. 신규 우편번호 관리	 
	 */
	private String newPostNo;


	/**
	 * 도로명주소 기본주소	 
	 */
	private String rdAddrBaseAddr;


	/**
	 * 지번주소 기본주소	 
	 */
	private String lnmAddrBaseAddr;


	/**
	 * 행정동 코드	 
	 */
	private String audCd;


	/**
	 * 시군구 코드	 
	 */
	private String signguCd;


	/**
	 * 도로 명	 
	 */
	private String roadNm;


	/**
	 * 주건물 번호	 
	 */
	private String mjrBuldNo;


	/**
	 * 부건물 번호	 
	 */
	private String mnrBuldNo;


	/**
	 * 법정동 명	 
	 */
	private String sudNm;


	/**
	 * 행정동 명	 
	 */
	private String audNm;


	/**
	 * 법정리 명	 
	 */
	private String legalriNm;


	/**
	 * 주지번	 
	 */
	private String mjrLnm;


	/**
	 * 부지번	 
	 */
	private String mnrLnm;


	/**
	 * 시군구 건물 명	 
	 */
	private String signguBuldNm;


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
