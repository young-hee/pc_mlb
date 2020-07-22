/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.com;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 업체 신청 첨부파일
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("comReqstAtchmnfl")
public class ComReqstAtchmnfl extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 업체 신청 일련번호	 
	 */
	private java.lang.Long comReqstSn;


	/**
	 * 첨부파일 순번	 
	 */
	private java.lang.Integer atchmnflTurn;


	/**
	 * 첨부 파일 명
	 * ㅁ. 첨부한 파일명
	 *    예) 게시판 캡쳐.GIF	 
	 */
	private String atchFileNm;


	/**
	 * 첨부 파일 URL
	 * ㅁ. 저장 위치 및 저장 파일명
	 *    예) /file/img/20150010112345.gif	 
	 */
	private String atchFileUrl;


	/**
	 * 첨부 파일 용량	 
	 */
	private java.math.BigDecimal atchFileCpcty;


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