package com.plgrim.ncp.biz.promotion.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;

import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.base.enums.PromotionEnum;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionDatePattern;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionDplctIssuLmitCd;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionPubliQtyLmitCd;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionSeparator;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionUnlimitNumber;
import com.plgrim.ncp.framework.commons.StringService;

@Data
@EqualsAndHashCode(callSuper = false)
public class PrmCpnExtend extends PrmCpn {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    /**
     * 쿠폰종류 명
     */
    private String cpnKndNm;

    /**
     * 쿠폰 발급 방법 명
     */
    private String cpnIssuMthdNm;

    /**
     * 온오프라인 쿠폰 오퍼 유형 명
     */
    private String onoflneCpnOfrTpNm;

    /**
     * 발급제한수량 무제한여부
     */
    private String publiQtyLmitEx;

    /**
     * ID당 발급 수량 제한여부 - 중복 발급 제한 코드
     */
    private String dplctIssuLmitEx;

    /**
     * 최소구매금액 무제한여부
     */
    private String cpnUseMinPchAmtUnlmtnYn = PromotionEnum.YES.toString();

    /**
     * 발급 쿠폰 인증코드 유형 명
     */
    private String cpnCrtfcCdTpCdNm;

    /**
     * 발급 쿠폰 사용 상태 명
     */
    private String cpnStatCdNm;

    /**
     * 쿠폰 발급수량 (allPubliQty - allIssuInvQty)
     */
    private long cpnIssuQty;

    /**
     * 쿠폰사용수량
     */
    private long cpnUsedQty;

    /**
     * 쿠폰사용비율
     */
    private BigDecimal cpnUsedRate;

    /**
     * 쿠폰사용비율 String
     */
    private String cpnUsedRateStr;

    /**
     * 쿠폰사용기간 시작종료일시
     */
    private String cpnUsePdBegEndDateStr;

    /**
     * 쿠폰명(중국어)
     */
    private String cpnNmChi;

    /**
     * 쿠폰명(영어)
     */
    private String cpnNmEng;

    /**
     * 발행구분명
     */
    private String onoflneCpnPubliSectNm;

    private String pcCpnImgNm;

    private String mobileCpnImgNm;

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

    public void setPubliQtyLmitEx(String publiQtyLmitEx) {

        if (PromotionPubliQtyLmitCd.UNLMIT.toString().equals(publiQtyLmitEx)) {
            setAllPubliQty(PromotionUnlimitNumber.UNLIMIT_NUM.toLong());
        }
        setPubliQtyLmitCd(publiQtyLmitEx);
    }

    public void setDplctIssuLmitEx(String dplctIssuLmitEx) {

        if (PromotionDplctIssuLmitCd.UNLMIT.toString().equals(dplctIssuLmitEx)) {
            setDplctIssuPsbQty(PromotionUnlimitNumber.UNLIMIT_NUM.toLong());
        }
        setDplctIssuLmitCd(dplctIssuLmitEx);
    }

    public String getCpnUseMinPchAmtUnlmtnYn() {

        if (getCpnUseMinPchAmt() != null && !BigDecimal.ZERO.equals(getCpnUseMinPchAmt())) {
            cpnUseMinPchAmtUnlmtnYn = PromotionEnum.NO.toString();
        }

        return cpnUseMinPchAmtUnlmtnYn;
    }

    public void setCpnUseMinPchAmtUnlmtnYn(String cpnUseMinPchAmtUnlmtnYn) {

        if (PromotionEnum.YES.toString().equals(cpnUseMinPchAmtUnlmtnYn)) {
            setCpnUseMinPchAmt(PromotionUnlimitNumber.UNLIMIT_NUM.toBigDecimal());
        }
    }

    public long getCpnIssuQty() {

        BigDecimal result = BigDecimal.ZERO;

        // 총발행수량
        BigDecimal allPubliQty = BigDecimal.ZERO;
        if (getAllPubliQty() != null) {
            allPubliQty = BigDecimal.valueOf(getAllPubliQty());
        }

        // 전체발급(가능)재고수량
        BigDecimal allIssuInvQty = BigDecimal.ZERO;
        if (getAllIssuInvQty() != null) {
            allIssuInvQty = BigDecimal.valueOf(getAllIssuInvQty());
        }
        else {
            allIssuInvQty = allPubliQty;
        }

        // 가능수량(총발행수량 - 발급가능수량)
        result = allPubliQty.subtract(allIssuInvQty);

        this.setCpnUsedRate(result);

        cpnIssuQty = result.longValue();
        return cpnIssuQty;
    }

    public void setCpnUsedRate(BigDecimal cpnIssuQty) {

        // 기본값 0
        BigDecimal result = BigDecimal.ZERO;

        // 사용수량
        BigDecimal cpnUsedQty = BigDecimal.ZERO;

        if (getCpnUsedQty() > BigDecimal.ZERO.longValue()) {
            cpnUsedQty = BigDecimal.valueOf(this.cpnUsedQty);

            if (cpnIssuQty != null && !BigDecimal.ZERO.equals(cpnIssuQty)) {

                // 사용수량 / 총발행수량
                result = cpnUsedQty.divide(cpnIssuQty, 2, RoundingMode.HALF_UP);

                // result * 100
                result = result.multiply(BigDecimal.TEN.multiply(BigDecimal.TEN));
            }
        }

        // 소수점 제거
        String resultStr = String.valueOf(result.intValue());
        this.setCpnUsedRateStr(resultStr);

        this.cpnUsedRate = result;
    }

    public void setCpnUsedRateStr(String cpnUsedRateStr) {

        String suffix = PromotionSeparator.PERCENT.toString();
        cpnUsedRateStr = cpnUsedRateStr + " " + suffix;

        this.cpnUsedRateStr = cpnUsedRateStr;
    }

    public String getCpnUsePdBegEndDateStr() {

        String cpnbd = getCpnUsePdBegDate();
        String cpned = getCpnUsePdEndDate();
        String pdcd = getCpnUsePdCd();

        if ("PD_APPN".equals(pdcd)) {

            String sp = PromotionDatePattern.SHORT.toString();
            String svp = PromotionDatePattern.SHORT_VIEW.toString();
            DateTimeFormatter spDtf = DateTimeFormat.forPattern(sp);
            DateTimeFormatter svpDtf = DateTimeFormat.forPattern(svp);

            if (StringUtils.isEmpty(cpnbd)) {
                cpnbd = PromotionSeparator.DASH.toString();
            }
            else {
                String cpnbdTrim = StringUtils.trimWhitespace(cpnbd);
                DateTime cpnbdDt = spDtf.parseDateTime(cpnbdTrim);
                cpnbd = svpDtf.print(cpnbdDt);
            }

            if (StringUtils.isEmpty(cpned)) {
                cpned = PromotionSeparator.DASH.toString();
            }
            else {
                String cpnedTrim = StringUtils.trimWhitespace(cpned);
                DateTime cpnedDt = spDtf.parseDateTime(cpnedTrim);
                cpned = svpDtf.print(cpnedDt);
            }

            // 구분자
            String spt = PromotionSeparator.WHITE_SPACE.toString() + PromotionSeparator.TILDE.toString() + PromotionSeparator.WHITE_SPACE;

            // 시작일시 ~ 종료일시
            cpnUsePdBegEndDateStr = cpnbd + spt + cpned;
        }
        else {
            cpnUsePdBegEndDateStr = PromotionSeparator.DASH.toString();
        }

        return cpnUsePdBegEndDateStr;
    }

    public String getPcCpnImgNm() {
    	 if (StringUtils.isEmpty(pcCpnImgNm) && StringService.isNotEmpty(this.getPcCpnImgUrl())) {
             String[] list = this.getPcCpnImgUrl().split(PromotionSeparator.SLASH.toString());
             pcCpnImgNm = list[list.length - 1];
         }
         return pcCpnImgNm;
    }

    public String getMobileCpnImgNm() {

        if (StringUtils.isEmpty(mobileCpnImgNm) && StringService.isNotEmpty(this.getMobileCpnImgUrl())) {
            String[] list = this.getMobileCpnImgUrl().split(PromotionSeparator.SLASH.toString());
            mobileCpnImgNm = list[list.length - 1];
        }
        return mobileCpnImgNm;
    }

    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}
