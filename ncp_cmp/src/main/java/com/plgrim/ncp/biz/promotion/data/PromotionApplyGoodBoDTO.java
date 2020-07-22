package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplGod;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCrsDcAplGod;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmOfferGft;
import com.plgrim.ncp.base.enums.PromotionEnum;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionCrsDcAplType;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionOrderDiscountType;

@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionApplyGoodBoDTO extends AbstractDTO {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    private String loginId;

    /**
     * 프로모션 유형코드
     */
    private String prmTpCd;

    /**
     * 프로모션 상세유형 코드
     */
    private String prmDtlTpCd;

    /**
     * 이전 적용상품구분코드
     */
    private String beforeAplGodSectCd;

    /**
     * 이전 제외상품구분코드
     */
    private String beforeExcGodSectCd;

    /**
     * 선택된 적용상품구분코드
     */
    private String checkedAplGodSectCd;

    /**
     * 선택된 제외상품구분코드
     */
    private String checkedExcGodSectCd;

    /**
     * 적용순번(복수)
     */
    private String aplTurns;
    
    /**
     * 제공사은품순번(복수)
     */
    private String offerGftTurns;

    /**
     * 교차할인 적용상품(복수)
     */
    private String godNos;

    private PrmAplGod prmAplGod;

    private PrmCrsDcAplGod prmCrsDcAplGod;
    
    private PrmOfferGft prmOfferGft;
    
    /**
     * 브랜드 코드 레벨
     */
    private String brndLevel;

    private String searchField;
    private String searchText;
    
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

    // 조회를 위한 값 설정
    public String getPrmDtlTpCd() {

        if (PromotionOrderDiscountType.CRS_DC.toString().equals(prmDtlTpCd) && prmAplGod != null) {
            String prmNo = prmAplGod.getPrmNo();
            String godAplYn = prmAplGod.getGodAplYn();
            String aplTgtGodGrpCd = null;
            prmCrsDcAplGod = new PrmCrsDcAplGod();

            prmCrsDcAplGod.setPrmNo(prmNo);

            if (PromotionEnum.YES.toString().equals(godAplYn)) {
                aplTgtGodGrpCd = PromotionCrsDcAplType.APL_TGT_GOD_GRP_1.toString();
            }
            else if (PromotionEnum.NO.toString().equals(godAplYn)) {
                aplTgtGodGrpCd = PromotionCrsDcAplType.APL_TGT_GOD_GRP_2.toString();
            }
            prmCrsDcAplGod.setAplTgtGodGrpCd(aplTgtGodGrpCd);
        }

        return prmDtlTpCd;
    }

    /**
     * 등록자/수정자 각각의 ENTITY에 설정하기 위한 setter
     * @param loginId
     */
    public void setLoginId(String loginId) {

        this.loginId = loginId;

        if (prmAplGod != null) {
            prmAplGod.setRegtrId(loginId);
            prmAplGod.setUdterId(loginId);
        }
        if (prmCrsDcAplGod != null) {
            prmCrsDcAplGod.setRegtrId(loginId);
            prmCrsDcAplGod.setUdterId(loginId);
        }
    }

    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}
