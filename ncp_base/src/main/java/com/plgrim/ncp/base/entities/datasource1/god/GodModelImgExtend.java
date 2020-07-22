package com.plgrim.ncp.base.entities.datasource1.god;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 상품 모델 이미지 확장
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godModelImgExtend")
public class GodModelImgExtend extends GodModelImg{

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */
    /**
     * 임시파일명
     */
    private String tempFileName;
    
    /**
     * 착장 사이즈 명
     */
    private String wearSizeNm;

    /**
     * 모델 명
     */
    private String modelNm;

    /**
     * 모델 키
     */
    private String height;

    /**
     * 모델 몸무게
     */
    private String bdWt;
    
    /**
     * 모델 허리둘레
     */    
    private String waist;

    /**
     * 모델 성별 코드
     */
    private String sexCd;

    /**
     * 모델 성별 명
     */
    private String sexNm;
    /*
     * ---------------------------------------------------------------------
     * Constructors.
     * ---------------------------------------------------------------------
     */

    /*
     * ---------------------------------------------------------------------
     * public & interface method.
     * ---------------------------------------------------------------------
     */

    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}
