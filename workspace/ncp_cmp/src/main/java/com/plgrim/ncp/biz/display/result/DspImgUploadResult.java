package com.plgrim.ncp.biz.display.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspConttImg;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspImgUploadResult extends AbstractResult {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */
    
    /** 전시 컨텐츠 이미지 엔티티. */
    private DspConttImg dspConttImg;
    
    /** 등록 파일 개수. */
    private int conttImgCnt;
    
    /** 템플릿 유형 이름. */
    private String tmplatTpNm;
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
