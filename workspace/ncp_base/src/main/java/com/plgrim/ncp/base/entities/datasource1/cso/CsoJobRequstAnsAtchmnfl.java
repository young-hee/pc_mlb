/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.cso;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 고객서비스 업무 요청 답변 첨부파일
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("csoJobRequstAnsAtchmnfl")
public class CsoJobRequstAnsAtchmnfl extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 요청 일련번호	 
	 */
	private java.lang.Long requstSn;


	/**
	 * 업무 요청 답변 순번
	 * ㅁ. 상담에 대한 응대 내역을 관리하는 순번을 의미 함.
	 *    >. "상품 문의.상품 답변 순번"의 MAX값 + 1로 등록 되도록 한다.
	 *    >. 숫자로 최대 5자리 " 99999" 로 관리 한다.
	 *       (ORACLE 기준 Format)	 
	 */
	private java.lang.Integer jobRequstAnsTurn;


	/**
	 * 요청 답변 첨부파일 순번
	 * ㅁ. 숫자로 최대 5자리 " 99999" 로 관리 한다.
	 *       (ORACLE 기준 Format)	 
	 */
	private java.lang.Integer requstAnsAtchmnflTurn;


	/**
	 * 요청 답변 첨부 파일 명
	 * ㅁ. 첨부한 파일명
	 *    예) 게시판 캡쳐.GIF	 
	 */
	private String requstAnsAtchFileNm;


	/**
	 * 요청 답변 첨부 파일 URL
	 * ㅁ. 저장 위치 및 저장 파일명
	 *    예) /file/img/20150010112345.gif	 
	 */
	private String requstAnsAtchFileUrl;


	/**
	 * 요청 답변 첨부 파일 용량	 
	 */
	private java.math.BigDecimal requstAnsAtchFileCpcty;


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