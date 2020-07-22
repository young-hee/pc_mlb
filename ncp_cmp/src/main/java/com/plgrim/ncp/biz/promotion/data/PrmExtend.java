package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;

import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionDatePattern;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionSeparator;
import com.plgrim.ncp.framework.commons.StringService;

@Data
@EqualsAndHashCode(callSuper = false)
public class PrmExtend extends Prm {

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
     * 프로모션상태 명
     */
    private String prmStatNm;

    /**
     * 프로모션 시작종료일시
     */
    private String prmBegEndDateStr;

    /**
     * 프로모션 시작일시 변환값
     */
    private String prmBegDateStr;

    /**
     * 프로모션 종료일시 변환값
     */
    private String prmEndDateStr;

    /**
     * 프로모션 상세유형 명
     */
    private String prmDtlTpNm;

    //    BigDecimal mcomCostBndRt;

    /**
     * 프로모션 브랜드 적용 조건
     */
    private String prmAplBrnd;

    /**
     * 프로모션 브랜드 제외 조건
     */
    private String prmExcBrnd;

    /**
     * 프로모션 사이트(유입경로) 적용 조건
     */
    private String prmAplSite;
    
    /**
     * 프로모션  몰 적용 조건
     */
    private String prmAplMall;
    
    /**
     * 프로모션 언어 적용 조건
     */
    private String prmAplLang;
    
    /**
     * 프로모션 대상 회원 유형 적용조건
     */
    private String prmAplTgtMbrTp;
    
    /**
     * 프로모션 대상회원 속성 적용조건
     */
    private String prmAplTgtMbrAtrb;
    
    /**
     * 프로모션 그룹사 ID 적용조건
     */
    private String prmAplGrpcoId;
    
    /**
     * 프로모션 제휴업체 ID 적용조건
     */
    private String prmAplAffComId;
    

    private String rprstImgPcNm;

    private String rprstImgMobileNm;

    /**
     * 등록자 명
     */
    private String regtrNm;

    /**
     * 등록자 ID/명
     */
    private String regtrIdNm;

    /**
     * 수정자 명
     */
    private String udterNm;

    /**
     * 수정자 ID/명
     */
    private String udterIdNm;
    
    /**
     * 프로모션 명
     */
    private String prmTpNm;

    /*
     * ************************************************************************
     * validate 용도
     * ************************************************************************
     */
    
    /**
     * 프로모션 count
     */
    private int prmCount = 0;
    
    /**
     * 적용대상상품 count
     */
    private int godAplCount = 0;
    
    private int godAplSesonCount = 0;

    private int crsDcAplCount = 0;


    private String useEndMonths;

    /**
     * 다국어
     */
    private String prmLangCd;

    private String prmLangNm;

    private String prmNmEng;
    
    private String prmNmChi;
    
    /**
     * 추가적립 프로모션 유효기간(사용기간) 시작일자
     */
    private String prmUseBegDate;

    /**
     * 추가적립 프로모션 유효기간(사용기간) 종료일자
     */
    private String prmUseEndDate;
    
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

    public String getRprstImgPcNm() {

        if (StringUtils.isEmpty(rprstImgPcNm) && StringService.isNotEmpty(this.getRprstImgPcUrl())) {
            String[] list = this.getRprstImgPcUrl().split(PromotionSeparator.SLASH.toString());
            rprstImgPcNm = list[list.length - 1];
        }
        return rprstImgPcNm;
    }

    public String getRprstImgMobileNm() {

        if (StringUtils.isEmpty(rprstImgMobileNm) && StringService.isNotEmpty(this.getRprstImgMobileUrl())) {
            String[] list = this.getRprstImgMobileUrl().split(PromotionSeparator.SLASH.toString());
            rprstImgMobileNm = list[list.length - 1];
        }
        return rprstImgMobileNm;
    }

    public String getPrmBegDateStr() {

        String pbd = getPrmBegDate();

        String sp = PromotionDatePattern.SHORT.toString();
        String svp = PromotionDatePattern.SHORT_VIEW.toString();
        DateTimeFormatter spDtf = DateTimeFormat.forPattern(sp);
        DateTimeFormatter svpDtf = DateTimeFormat.forPattern(svp);

        if (StringUtils.isEmpty(pbd)) {
            pbd = PromotionSeparator.DASH.toString();
        }
        else {
            String pbdTrim = StringUtils.trimWhitespace(pbd);
            DateTime pbdDt = spDtf.parseDateTime(pbdTrim);
            pbd = svpDtf.print(pbdDt);
        }

        // 시작일시 ~ 종료일시
        prmBegDateStr = pbd;

        return prmBegDateStr;
    }

    public String getPrmEndDateStr() {

        String ped = getPrmEndDate();

        String sp = PromotionDatePattern.SHORT.toString();
        String svp = PromotionDatePattern.SHORT_VIEW.toString();
        DateTimeFormatter spDtf = DateTimeFormat.forPattern(sp);
        DateTimeFormatter svpDtf = DateTimeFormat.forPattern(svp);

        if (StringUtils.isEmpty(ped)) {
            ped = PromotionSeparator.DASH.toString();
        }
        else {
            String pedTrim = StringUtils.trimWhitespace(ped);
            DateTime pedDt = spDtf.parseDateTime(pedTrim);
            ped = svpDtf.print(pedDt);
        }

        // 시작일시 ~ 종료일시
        prmEndDateStr = ped;

        return prmEndDateStr;
    }

    public String getPrmBegEndDateStr() {

        // 구분자
        String spt = PromotionSeparator.WHITE_SPACE.toString() + PromotionSeparator.TILDE.toString() + PromotionSeparator.WHITE_SPACE;

        // 시작일시 ~ 종료일시
        prmBegEndDateStr = getPrmBegDateStr() + spt + getPrmEndDateStr();

        return prmBegEndDateStr;
    }

    public String getRegtrIdNm() {

        String id = getRegtrId();
        if (StringUtils.isEmpty(id)) {
            regtrIdNm = PromotionSeparator.DASH.toString();
        }
        else {
            String nm = getRegtrNm();
            if (StringUtils.isEmpty(nm)) {
                nm = PromotionSeparator.DASH.toString();
            }
            regtrIdNm = nm + PromotionSeparator.WHITE_SPACE.toString() + "(" + id + ")";
        }

        return regtrIdNm;
    }

    public String getUdterIdNm() {

        String id = getUdterId();
        if (StringUtils.isEmpty(id)) {
            udterIdNm = PromotionSeparator.DASH.toString();
        }
        else {
            String nm = getUdterNm();
            if (StringUtils.isEmpty(nm)) {
                nm = PromotionSeparator.DASH.toString();
            }
            udterIdNm = nm + PromotionSeparator.WHITE_SPACE.toString() + "(" + id + ")";
        }

        return udterIdNm;
    }

    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}
