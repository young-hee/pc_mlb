package com.plgrim.ncp.biz.goods.data;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 상품일괄등록처리용 Validation DTO
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("GoodsValidDTO")
public class GoodsValidDTO {

    /*
	 * ---------------------------------------------------------------------
	 * 엑셀업로드 데이터 검증결과
	 * ---------------------------------------------------------------------
	 */

	/**
 	 * 유효성체크 결과코드
	 */
    private String validCode;

    /**
 	 * 유효성체크 결과메세지
	 */
    private String validText;

    /*
	 * ---------------------------------------------------------------------
	 * 엑셀업로드 템플릿 항목 Validation Gropus
	 * ---------------------------------------------------------------------
	 */
    /*
	 * ---------------------------------------------------------------------
	 * 엑셀업로드 템플릿 항목 Validation
	 * ---------------------------------------------------------------------
	 */


}