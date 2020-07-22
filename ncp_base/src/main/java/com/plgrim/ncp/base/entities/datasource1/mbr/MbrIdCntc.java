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
 * 회원 ID 연계
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mbrIdCntc")
public class MbrIdCntc extends AbstractEntity {
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
	 * ID 연계 유형 코드
	 * ㅁ. ID 연계 유형 : ID_CNTC_TP
	 *    >. Facebook : FACEBUK
	 *    >. Naver : NAVER
	 *    >. Samsung Pass : SSPASS	 
	 */
	private String idCntcTpCd;


	/**
	 * 로그인 ID	 
	 */
	private String loginId;


	/**
	 * 토큰 ID	 
	 */
	private String toknId;


	/**
	 * 삭제 여부	 
	 */
	private String deleteYn;


	/**
	 * 비밀번호 재설정 여부	 
	 */
	private String pwReconfigYn;


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
