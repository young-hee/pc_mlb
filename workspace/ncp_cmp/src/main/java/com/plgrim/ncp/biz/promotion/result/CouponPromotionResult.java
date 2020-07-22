package com.plgrim.ncp.biz.promotion.result;

import java.util.Date;
import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplGod;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.biz.promotion.data.MbrIssuCpnExtend;
import com.plgrim.ncp.biz.promotion.data.PrmAplGodExtend;
import com.plgrim.ncp.biz.promotion.data.PrmAplTgtExtend;
import com.plgrim.ncp.biz.promotion.data.PrmCpnExtend;
import com.plgrim.ncp.biz.promotion.data.PrmExtend;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CouponPromotionResult extends AbstractResult {
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
     * 프로모션 번호
     */
    String prmNo;

    /**
     * 등록자ID
     */
    String regtrId;

    /**
     * 등록자명
     */
    String regtrNm;

    /**
     * 등록일시
     */
    Date regDt;

    /**
     * 수정자ID
     */
    String udterId;

    /**
     * 수정자명
     */
    String udterNm;

    /**
     * 업체명
     */
    String comNm;

    /**
     * 수정자일시
     */
    Date udtDt;

    /**
     * 프로모션
     */
    Prm prm;

    /**
     * 프로모션 확장
     */
    PrmExtend prmEx;

    /**
     * 프로모션 쿠폰
     */
    PrmCpn prmCpn;

    /**
     * 프로모션 쿠폰
     */
    PrmCpnExtend prmCpnEx;

	/**
	 * 회원 발급 쿠폰
	 */
	MbrIssuCpn mbrIssuCpn;

	/**
	 * 회원 발급 쿠폰
	 */
	MbrIssuCpnExtend mbrIssuCpnEx;
	
    List<PrmAplTgtExtend> prmAplTgtList;

    List<PrmAplGod> prmAplGodList;
    
    /**
     * 프로모션 적용 상품
     */
    PrmAplGod prmAplGod;

    /**
     * 프로모션 적용 상품 확장
     */
    PrmAplGodExtend prmAplGodEx;
    
    /**
     * 적용상품 구분코드 - 적용
     */
    String aplGodSectAplCd;

    /**
     * 적용상품 구분코드 - 제외
     */
    String aplGodSectExcCd;

    List<String> langList;

    List<String> deviceList;

    List<String> memberTypeList;

    List<String> memberAttributeList;
    
    String mallId;
    String mallNm;

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
