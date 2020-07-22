/**
 * @package : com.plgrim.ncp.base.entities..god
 * @author : ()
 * @date : 20160314
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 상품 상세 이미지
 */
/**
 * @author Vito
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GodDetailImgExtend extends GodDetailImg {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * ERP 상품번호
     */
    private String erpGodNo;

    /**
     * 상품번호 count
     */
    private int godNoCnt;

    /**
     * KOR 상품번호 count
     */
    private int korGodNoCnt;


    /**
     * KOR 설명 count
     */
    private int korImgDscrCnt;

    /**
     * 유효성체크
     */
    private String validResult = "SUCCESS";
    
    /**
     * 입점구분코드
     *     >. 자사 : MCOM
     *     >. 입점 : PARTMAL
     */
    private String partmalSectCd;

    /**
     * 접근 가능한 site_id
     */
    private String accessSiteId;
    
    private String brndId;
    
    private String brndNm;
    
    private String imgDscrKor;
    
    private String imgDscrEng;
    
    private String imgDscrChi;
}
