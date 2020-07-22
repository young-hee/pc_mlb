package com.plgrim.ncp.biz.promotion.data;

import java.util.ArrayList;
import java.util.List;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplGod;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplPd;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplTgt;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmBundleDcCnd;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCrsDcAplGod;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmOfferGft;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Vito
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionBoDTO extends AbstractDTO {

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
     * 사용자 array
     */
    private List<String> listMbrNo;

    /**
     * 쿠폰 사용 여부 체크
     */
    private String mbrCpnUseChk;

    /**
     * 가격배치 param : 구분값(전체:ALL, 상품:GOD, 프로모션:PRM)
     */
    private String icSect;

    /**
     * 상품번호
     */
    private String godNo;

    /**
     * 결과 코드
     */
    private String ocResultCd = "0";

    /**
     * 결과 메세지
     */
    private String ocResultCont;

    /**
     * ERP 고객번호
     */
    private String erpCstmrNo;

    /**
     * 프로모션
     */
    private Prm prm = new Prm();

    /**
     * 프로모션 확장
     */
    private PrmExtend prmEx = new PrmExtend();

    /**
     * 프로모션 쿠폰
     */
    private PrmCpn prmCpn = new PrmCpn();

    /**
     * 프로모션 쿠폰
     */
    private PrmCpnExtend prmCpnEx = new PrmCpnExtend();

    /**
     * 프로모션 적용대상
     */
    private PrmAplTgt prmAplTgt = new PrmAplTgt();

    /**
     * 프로모션 적용상품
     */
    private PrmAplGod prmAplGod = new PrmAplGod();

    private PrmCrsDcAplGod prmCrsDcAplGod;

    private PrmOfferGft prmOfferGft;

    /**
     * 쿠폰 발급/사용 일자 정보
     */
    private PrmAplPd prmAplPd;
    
    /**
     * 회원
     */
    private Mbr mbr = new Mbr();
    
    /**
     * 회원 발급 쿠폰
     */
    private MbrIssuCpn mbrIssuCpn = new MbrIssuCpn();

    private List<PrmBundleDcCnd> prmBundleDcCndList = new ArrayList<PrmBundleDcCnd>();

    /**
     * 임시파일명 리스트
     */
    private List<String> tempFileList;

    /**
     * 임시파일 구분값 리스트
     */
    private List<String> sectFileList;

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

    /**
     * 등록자/수정자 각각의 ENTITY에 설정하기 위한 setter
     * @param loginId
     */
    public void setLoginId(String loginId) {

        this.loginId = loginId;

        prm.setRegtrId(loginId);
        prm.setUdterId(loginId);

        prmCpn.setRegtrId(loginId);
        prmCpn.setUdterId(loginId);

        if (prmAplTgt != null) {
            prmAplTgt.setRegtrId(loginId);
            prmAplTgt.setUdterId(loginId);
        }

        prmAplGod.setRegtrId(loginId);
        prmAplGod.setUdterId(loginId);

    }

    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}
