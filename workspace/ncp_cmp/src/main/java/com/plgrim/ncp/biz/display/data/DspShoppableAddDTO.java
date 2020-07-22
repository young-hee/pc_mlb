/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      kwonoh.kim
 * @since       2015. 9. 2       
 */
package com.plgrim.ncp.biz.display.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspShoppableAddDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = 5890646694257941504L;
	
	//비디오 시작 이미지(PC)
	String hidvideoStartImgUrl;
	//비디오 종료 이미지(PC)
	String hidvideoEndImgUrl;
	//비디오 시작 이미지(Mobile)
	String hidvideoMobileStartImgUrl;
	//비디오 종료 이미지(Mobile)
	String hidvideoMobileEndImgUrl;
	//tempfile 리스트
	List<String> tempFileList;
}
