/**
 * @author : Generator(Generator)
 * @date : 2018-06-26
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.inf;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 인터페이스 상품 사이즈 조견표 POM
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("infGodSizeLktbPom")
public class InfGodSizeLktbPom extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 브랜드 ID
	 * ㅁ. BRAND : 브랜드코드
	 *    >. 디스커버리 : X
	 *    >. MLB : M
	 *    >. MLB Kids : I	 
	 */
	private String brndId;


	/**
	 * POM 코드
	 * ㅁ. POM : 폼 코드	 
	 */
	private String pomCd;


	/**
	 * POM 명
	 * ㅁ. NAME : 폼 이름	 
	 */
	private String pomNm;


	/**
	 * 영어 POM 명	 
	 */
	private String engPomNm;
	
	
	/**
	 * 중국어 POM 명	 
	 */
	private String chiPomNm;


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