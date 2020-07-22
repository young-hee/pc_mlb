/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 7. 9       
 */
package com.plgrim.ncp.commons.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper=false)
public class FormSysCmmnTempletDTO  extends AbstractDTO {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5836627492662374731L;
	
	private String calType; // 일자구분
	private String regBegDt;
	private String regEndDt;
	private String tempSj; // 템플릿제목
	private String tempSectCd; // 템플릿구분
	private String useYn; // 게시여부
	private String regtrIdNm; // 작성자
	private String tempCont;
	
	private Long tempSn; // 공지일련번호
	private Long tempFileSn; // 선택첨부파일순번
	private Long tempFileSubSn; // 선택첨부파일순번(한화면에 2개 관리하는 화면에서 사용한다.)
	private String templetSectCd; // 템플릿구분코드
}
