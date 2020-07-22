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
 * 시스템 SMS 이메일 문구
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysSmsEmailTxt")
public class SysSmsEmailTxt extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 문구 일련번호	 
	 */
	private java.lang.Long txtSn;


	/**
	 * 문구 사용 구분 코드
	 * ㅁ. 문구 사용 구분 : TXT_USE_SECT
	 *    >. 고객서비스 : CSO
	 *    >. 정보성 : INFOR	 
	 */
	private String txtUseSectCd;


	/**
	 * 문구 템플릿 번호	 
	 */
	private String txtTmplatNo;


	/**
	 * 알림톡 관리 번호	 
	 */
	private String ntcnTakManageNo;


	/**
	 * SMS 이메일 구분 코드
	 * ㅁ. SMS 이메일 구분 : SMS_EMAIL_SECT
	 *    >. SMS : SMS
	 *    >. 이메일 : EMAIL
	 *    >. 알림톡 : NTCN_TAK	 
	 */
	private String smsEmailSectCd;


	/**
	 * 문구 제목	 
	 */
	private String txtSj;


	/**
	 * 문구 내용	 
	 */
	private String txtCont;


	/**
	 * 링크 URL	 
	 */
	private String linkUrl;


	/**
	 * 문구 용량	 
	 */
	private java.math.BigDecimal txtCpcty;


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
	 * 수정한 관리자 번호	 
	 */
	private String udterId;


	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;

	
}
