package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplGod;

@Data
@EqualsAndHashCode(callSuper = false)
public class PrmAplGodExtend extends PrmAplGod {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    //    private String prmNo;
    //    private String aplGodSectCd;

    private String brndNm;

    private String brndGrp;
    
    private String dspCtgryNm;

    private String godSaleSectNm;

    private String erpGodNo;

	/**
	 * 디자인 그룹 번호
	 * ㅁ. 자사의 경우 <상품.ERP 상품 번호>에서  마지막 색상 부분을 제외한 번호
	 * 
	 * ㅁ. 입점사는 <상품.ERP 상품 번호>와 동일하게 처리	 
	 */
	private String dsgnGrpNo;

    private String godNm;

    private String godSaleSectCd;
    
    private String aplTgtGodGrpCd;
    
    private String sesonCd;
    
    private String sesonNm;

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
