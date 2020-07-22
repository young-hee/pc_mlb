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
 * 회원 사이즈 분류
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mbrSizeClfc")
public class MbrSizeClfc extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 회원 번호
	 * ㅁ. 회원 가입시 부여되는 고유한 관리 번호
	 *    >. MB || YYYYMMDD || 7자리 Cycle	 
	 */
	private String mbrNo;


	/**
	 * 회원 사이즈 순번	 
	 */
	private java.lang.Integer mbrSizeTurn;


	/**
	 * 회원 사이즈 분류 코드
	 * ㅁ. 회원 사이즈 분류 : MBR_SIZE_CLFC
	 *    >. 전체상품 기본사이즈 : ALL_BASE_SIZE
	 *    >. 여성상품 기본사이즈 : WOMEN_BASE_SIZE
	 *    >. 남성상품 기본사이즈 : MEN_BASE_SIZE
	 *    >. 키즈상품 기본사이즈 : KIDS_BASE_SIZE
	 *    >. 기본사이즈 미지정 : BASE_SIZE_UNDS
	 * 
	 * ㅁ. 보조코드1 : 최상위 전시 카테고리 번호	 
	 */
	private String mbrSizeClfcCd;


	/**
	 * 회원 사이즈 명	 
	 */
	private String mbrSizeNm;


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