package com.plgrim.ncp.base.entities.datasource1.inf;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("infGodSizeLktbPomExtend")
public class InfGodSizeLktbPomExtend extends InfGodSizeLktbPom {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4771151182109621910L;

	/**
	 * POM 분류 코드
	 * POM별로 이미지 위치를 나타내는 코드	 
	 */
	private String pomClfcCd;
}
