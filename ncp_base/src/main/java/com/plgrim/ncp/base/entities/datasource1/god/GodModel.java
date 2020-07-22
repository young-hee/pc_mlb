/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 상품 모델
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godModel")
public class GodModel extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 모델 번호
	 * ㅁ. 모델코드 정책
	 *    >. M || 5자리 숫자
	 * 
	 * ㅁ. 4자리 숫자
	 *    >. SQ_GOD_MODEL Sequence 사용	 
	 */
	private String modelNo;


	/**
	 * 모델 명	 
	 */
	private String modelNm;


	/**
	 * 성별 코드
	 * ㅁ. 성별 : SEX
	 *    >. 남성 : MALE
	 *    >. 여성 : FEMALE	 
	 */
	private String sexCd;


	/**
	 * 전시 여부	 
	 */
	private String dspYn;


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
