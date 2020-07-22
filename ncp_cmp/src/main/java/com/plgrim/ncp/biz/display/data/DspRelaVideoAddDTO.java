package com.plgrim.ncp.biz.display.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspRelaVideoAddDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = 5890646694257941504L;
	
	//비디오 스틸 이미지
	String hiddspRelationImgUrl;
	//비디오 메뉴 이미지
	String hiddspRelationMenuImgUrl;
	//tempfile 리스트
	List<String> tempFileList;
}
