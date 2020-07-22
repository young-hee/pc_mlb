package com.plgrim.ncp.biz.member.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;

/**
 * Created by Jieun on 2016-09-22.
 */
@Data
public class MysizeInputDTO  extends AbstractDTO {

	private String mbrSizeNm;	  // 사이즈 이름
	
	private String mbrSizeClfcCd;
	private String mbrSizeClfcCdNm;
	private String mbrSizeTurn;

	private String height;       // 키
    private String weight;       // 몸무게
    private String waist;        // 허리
    private String body;         // 체형
}
