package com.plgrim.ncp.biz.promotion.data;

import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplTgt;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.base.enums.PromotionEnum;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionStat;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionUnlimitNumber;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CouponPromotionDTO extends AbstractDTO {

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

    private String prmTpCd;

    /**
     * 온라인상품번호
     */
    private String godNo;

    /**
     * ERP 상품번호
     */
    private String erpGodNo;

	/**
	 * 디자인 그룹 번호
	 * ㅁ. 자사의 경우 <상품.ERP 상품 번호>에서  마지막 색상 부분을 제외한 번호
	 * 
	 * ㅁ. 입점사는 <상품.ERP 상품 번호>와 동일하게 처리	 
	 */
	private String dsgnGrpNo;
    
    // String defaultPrmStatCd;
    private String defaultPrmStatCd = PromotionStat.APRV_WAIT.toString();

    /**
     * 조회 기간 조건
     */
    private String searchPdCnd;

    /**
     * 조회 시작일시
     */
    private String searchBegDate;

    /**
     * 조회 종료일시
     */
    private String searchEndDate;

    /**
     * 총발행수량 무제한 여부
     *
     * <pre>
     *  - Y : PromotionUnlimitNumber.UNLIMIT_NUM.toInteger() = 9999999999(enum ->9 열자리)
     *  - N : prmCpn.allPubliQty 값 설정
     * </pre>
     */
    private String allPubliUnlmtnYn;

    /**
     * 최소구매금액 무제한여부
     *
     * <pre>
     *  - Y : PromotionUnlimitNumber.UNLIMIT_NUM.toInteger() = 9999999999(enum ->9 열자리)
     *  - N : prmCpn.cpnUseMinPchAmt 값 설정
     * </pre>
     */
    private String cpnUseMinPchAmtUnlmtnYn;

    /**
     * 사용자 array
     */
    private List<String> arrMbrNo;

    /**
     * 쿠폰 상태 array
     */
    private List<String> arrCpnStatCd;

    /**
     * 프로모션
     */
    private Prm prm;

    /**
     * 프로모션 확장
     */
    private PrmExtend prmEx;

    /**
     * 프로모션 쿠폰
     */
    private PrmCpn prmCpn;

    /**
     * 프로모션 쿠폰확장
     */
    private PrmCpnExtend prmCpnEx;

	/**
	 * 적용 상품 구분 코드
	 * 브랜드 : BRND
	 * 상품 : GOD
	 */
	private String aplGodSectCd;

    private PrmAplTgt prmAplTgt = new PrmAplTgt();

    /**
	 * 회원 발급 쿠폰
	 */
    private MbrIssuCpn mbrIssuCpn;

	/**
	 * 회원 발급 쿠폰
	 */
    private MbrIssuCpnExtend mbrIssuCpnEx;

    /**
     * 몰구분
     */
    private String searchMallId;

    /**
     * 브랜드ID
     */
    private String brndId;

    /**
     * 네이버EP쿠폰YN 조회조건
     */
    private String srchNaverEp;

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

    public void setAllPubliUnlmtnYn(String allPubliUnlmtnYn) {

        if (PromotionEnum.YES.toString().equals(allPubliUnlmtnYn)) {
            getPrmCpn().setAllPubliQty(PromotionUnlimitNumber.UNLIMIT_NUM.toLong());
        }

        this.allPubliUnlmtnYn = allPubliUnlmtnYn;
    }

    public void setCpnUseMinPchAmtUnlmtnYn(String cpnUseMinPchAmtUnlmtnYn) {

        if (PromotionEnum.YES.toString().equals(cpnUseMinPchAmtUnlmtnYn)) {
            getPrmCpn().setCpnUseMinPchAmt(PromotionUnlimitNumber.UNLIMIT_NUM.toBigDecimal());
        }
        // this.allPubliUnlmtnYn = allPubliUnlmtnYn;
    }

    /**
     * 등록자/수정자 각각의 ENTITY에 설정하기 위한 setter
     * @param loginId
     */
    public void setLoginId(String loginId) {

        this.loginId = loginId;

        if (prm != null) {
            prm.setRegtrId(loginId);
            prm.setUdterId(loginId);
        }

        if (prmCpn != null) {
            prmCpn.setRegtrId(loginId);
            prmCpn.setUdterId(loginId);
        }

        if (prmCpnEx != null) {
            prmCpnEx.setRegtrId(loginId);
            prmCpnEx.setUdterId(loginId);
        }

        if (prmAplTgt != null) {
            prmAplTgt.setRegtrId(loginId);
            prmAplTgt.setUdterId(loginId);
        }
    }

    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */
}

