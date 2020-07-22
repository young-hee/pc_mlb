package com.plgrim.ncp.base.enums;

import java.io.File;
import java.math.BigDecimal;

public enum PromotionEnum {

    // PROMOTION_CONSTANTS
    YES("Y"), NO("N");

    private final String value;

    private PromotionEnum(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * 프로모션 구분자
     */
    public enum PromotionSeparator {

        DELIMITER("-"), DASH("-"), UNDER_BAR("_"), COMMA(","),
        FILE(File.separator),
        DOT("."), SLASH("/"), PERCENT("%"), TILDE("~"), WHITE_SPACE(" ");

        private final String val;

        private PromotionSeparator(final String val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val;
        }
    }

    /**
     * 프로모션 유형
     */
    public enum PromotionType {
        CPN, GOD_DC, ORD_DC, GFT, MBSH_PNT, EVT_PNT, CRCOM_AFF, WEBPNT;
    }

    /**
     * 프로모션 쿠폰유형
     *  GOD_CPN      - 상품 쿠폰
     *  BSK_CPN      - 장바구니 쿠폰
     *  DLV_CST_CPN  - 일반배송비 쿠폰
     */
    public enum PromotionCouponType {
        GOD_CPN, BSK_CPN, DLV_CST_CPN;
    }

    /**
     * 프로모션 주문할인유형
     */
    public enum PromotionOrderDiscountType {
        BUNDLE_DC, CRS_DC;
    }

    /**
     * 프로모션 교차할인적용유형
     */
    public enum PromotionCrsDcAplType {
        APL_TGT_GOD_GRP_1, APL_TGT_GOD_GRP_2;
    }

    /**
     * 프로모션 상태코드
     *
     * APRV_WAIT(승인대기), APRV(승인), STPGE(중지);
     */
    public enum PromotionStat {
        APRV_WAIT, APRV, STPGE;
    }

    /**
     * 프로모션 적용상품
     *
     */
    public enum PromotionAplGoods {
        ALL, BRND, DSP_CTGRY, GOD, SESON;
    }

    /**
     * 프로모션 적용범위
     *
     */
    public enum PromotionAplTarget {
        MALL_ID, LANG, DVC, TGT_MBR_TP, TGT_MBR_ATRB, TGT_MBR_ATRB_DATA, GRPCO_ID, AFF_COM_ID;
    }

    /**
     * 쿠폰 상태 코드(CPN_STAT) - 미사용, 사용중지, 사용
     *
     */
    public enum CouponUseStatusCd {
        NO_USE, USE_STPGE, USE;
    }

    /**
     * 프로모션 적용기간 코드
     *
     */
    public enum PromotionAplPeriod {
    	USE_PD, APL_PD
    }

    /**
     * 발행수량 제한코드 - 무제한, 제한
     *
     */
    public enum PromotionPubliQtyLmitCd {
        UNLMIT, LMIT;
    }

    /**
     * 중복 발급 수량 제한코드 - 무제한, 제한
     *
     */
    public enum PromotionDplctIssuLmitCd {
        UNLMIT, LMIT;
    }

    /**
     * B2E 인증방식
     *
     */
    public enum PromotionB2ECrtificationMeansMethod {
        EMAIL, B2E_KEY;
    }

    /**
     * 쿠폰 발급 방법 코드
     * 즉시 할인 : IMDTL_DC
     * 다운로드 : DWLD
     * 계정 발급 : ACNT_ISSU
     * 인증 코드 : CRTFC_CD
     */
    public enum CouponIssueMethodCode {
    	IMDTL_DC, DWLD, ACNT_ISSU, CRTFC_CD;
    }

    /**
     * 쿠폰 인증 코드 유형 개별 인증 코드 : IND_CRTFC_CD(개별인증코드), RPRST_CRTFC_CD(대표인증코드)
     */
    public enum CouponCrtfcCodeTypeCode {
        IND_CRTFC_CD, RPRST_CRTFC_CD;
    }

    /**
     * 쿠폰 발급 구분 코드
     * NEW_JOIN_10000_DC : 신규가입시 10000원 할인권
     * BRTHDY_CGRT_DC : 생일축하 할인권
     * PCH_AF_1MM_5PT_DC : 첫구매 후 1달간 미구매 고객 5% 할인권
     * MBSH_PLGRIM_APP_FIRST_LOGIN_10000_DC : 멤버쉽 PLGRIM 모바일앱 최초 로그인 10000원 할인권
     */
    public enum CouponIssueSectCode {
    	NEW_JOIN_10000_DC, BRTHDY_CGRT_DC, PCH_AF_1MM_5PT_DC, MBSH_PLGRIM_APP_FIRST_LOGIN_10000_DC
    }

    /**
     * 쿠폰 사용 기간 코드 : ISSU_STDR(발급기간), PD_APPN(기간지정)
     */
    public enum CouponUsePeriodCode {
    	ISSU_STDR, PD_APPN;
    }

    /**
     * 쿠폰 발급 제한 사유 - CPN_ISSU_LMIT_RESN
     *
     * PRM_STAT_STPGE : 프로모션 상태 중지
     * TOT_ISSU_QTY_EXCESS : 총 발급 수량 초과
     * ISMCBTW_OFF : 발급기간 오프
     * IDBY_ISSU_QTY_EXCESS : ID별 발급 수량 초과
     * SUCCES : 성공
     * ADMIN_INQ : 관리자 문의
     * MBR_INFO_ABSNCE : 회원정보 부재
     * PROSISSU_SN : 기발급 일련번호
     * SN_EXST_NOT : 존재한지 않는 일련번호
     * SN_NONINPUT : 일련번호 미입력
     * NONISSU_TGT : 미발급 대상
     */
    public enum CouponIssueLimitReason {
    	PRM_STAT_STPGE, TOT_ISSU_QTY_EXCESS, ISMCBTW_OFF, IDBY_ISSU_QTY_EXCESS, SUCCES,
    	ADMIN_INQ, MBR_INFO_ABSNCE, PROSISSU_SN, SN_EXST_NOT, SN_NONINPUT, NONISSU_TGT;
    }

    /**
     * 대표 이미지 사용 구분
     *
     * <pre>
     *   - PC_MOBILE_IND_USE : PC/MOBILE 개별사용
     *   - PC_MOBILE_UNITY_USE : PC/MOBILE 통합사용
     * </pre>
     */
    public enum PromotionPrstImgUseSect {
        PC_MOBILE_IND_USE, PC_MOBILE_UNITY_USE;
    }

    /**
     * DEVICE 종류
     *
     * <pre>
     *   - PC
     *   - MOBILE
     * </pre>
     */
    public enum PromotionDeviceKind {
        PC, MOBILE;
    }

    public enum PromotionProcedureSect {
        ALL, PRM, GOD;
    }

	/**
	 * 언어 코드
	 * ㅁ. 코드 테이블 규칙에 따라 대문자를 사용한 ISO 639 표준에 따른다
	 * <p/>
	 * ㅁ. 언어 : LANG
	 * >. 한국어 : KOR
	 * >. 중국어 : CHI
	 * >. 영어 : ENG
	 * >. 일본어 : JPN
	 */
	public enum langCd {
		KOR, CHI, ENG, JPN
	}

    /**
     * 프로모션 무제한 값(toLong() / toBigDecimal())
     */
    public enum PromotionUnlimitNumber {

        UNLIMIT_NUM("9999999999");

        private final String val;

        private PromotionUnlimitNumber(final String val) {
            this.val = val;
        }

        public Long toLong() {
            return Long.parseLong(val);
        }

        public BigDecimal toBigDecimal() {
            return BigDecimal.valueOf(toLong());
        }
    }

    /**
     * 프로모션 날짜 패턴
     */
    public enum PromotionDatePattern {

        SHORT("yyyyMMdd"), SHORT_VIEW("yyyy-MM-dd"), LONG("yyyyMMddHHmm"), LONG_VIEW("yyyy-MM-dd HH:mm");

        private final String val;

        private PromotionDatePattern(final String val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val;
        }
    }

    public enum PromotionFilePath {
        TEMP_IMAGE_UPLOAD_PATH("ncp_base.image.base.upload.temp.path"),
        TEMP_IMAGE_HTTP_UPLOAD_PATH("ncp_base.image.base.http.temp.path"),
        IMAGE_UPLOAD_PATH("ncp_web_bo.image.promotion.promotion.upload.path"),
        IMAGE_NAVER_UPLOAD_PATH("ncp_web_bo.image.promotion.promotion.naverep.upload.path"),
        IMAGE_HTTP_UPLOAD_PATH("ncp_web_bo.image.promotion.promotion.http.path"),
    	IMAGE_HTTP_NAVER_UPLOAD_PATH("ncp_web_bo.image.promotion.promotion.naverep.http.path");



        private final String value;

        private PromotionFilePath(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
