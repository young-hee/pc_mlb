package com.plgrim.ncp.biz.promotion.repository;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplPd;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnCrtfcCd;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmLang;
import com.plgrim.ncp.biz.promotion.data.CouponPromotionDTO;
import com.plgrim.ncp.biz.promotion.data.PrmCpnCrtfcCdDTO;
import com.plgrim.ncp.biz.promotion.data.PrmExtend;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.biz.promotion.result.CouponPromotionResult;
import com.plgrim.ncp.biz.promotion.result.CouponUseResult;
import com.plgrim.ncp.biz.promotion.result.PromotionBoResult;

@Repository
public class PromotionRepository extends AbstractRepository {
 
    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

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

    public List<PromotionBoResult> selectPromotionList(PromotionBoDTO promotionBoDTO)  {
        List<PromotionBoResult> resultList = getSession1().selectList("com.plgrim.ncp.biz.promotion.selectPromotionList", promotionBoDTO);
        return resultList;
    }

    public int insertPromotion(PromotionBoDTO promotionBoDTO)  {
    	
    	if (StringUtils.isEmpty(promotionBoDTO.getPrm().getRtlPrcAplYn())) {	// Default : "N"
    		promotionBoDTO.getPrm().setRtlPrcAplYn("N");
    	}
    	
        int cnt = getSession1().insert("com.plgrim.ncp.base.insertPrm", promotionBoDTO.getPrm());
        return cnt;
    }

    public int updatePromotionAprvKind(PromotionBoDTO promotionBoDTO)  {
        int result = getSession1().update("com.plgrim.ncp.biz.promotion.updatePromotion", promotionBoDTO.getPrm());
        return result;
    }

    public int updatePromotionStatus(Prm prm)  {
        int result = getSession1().update("com.plgrim.ncp.biz.promotion.updatePromotionStatus", prm);
        return result;
    }

    public int selectPromotionTargetGodCheck(Prm prm)  {
        int result = getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectPromotionTargetGodCheck", prm);
        return result;
    }

    public int deletePromotionApplyPeriod(PrmAplPd prmAplPd)  {
        int result = getSession1().delete("com.plgrim.ncp.biz.promotion.deletePromotionApplyPeriod", prmAplPd);
        return result;
    }

    /**
    * 프로모션 적용 상품 가격 (배치)프로시져 수동 실행
    * 
    * @param promotionBoDTO
    * @
    */
    public void updatePromotionGoodsPriceProcedure(PromotionBoDTO promotionBoDTO)  {
    	getSession1().selectOne("com.plgrim.ncp.biz.promotion.updatePromotionGoodsPriceProcedure", promotionBoDTO);
    }
    
    /**
     * 프로모션 즉시포인트 사용여부 적용 프로시져 실행
     * 
     * @param promotionBoDTO
     * @
     */
    public void updateGodImdtlDcProcedure(PromotionBoDTO promotionBoDTO)  {
		// 프로시져 제거에 따라 주석처리
		// getSession1().selectOne("com.plgrim.ncp.store.biz.promotion.updateGodImdtlDcProcedure", promotionBoDTO);
    }
    
    /**
     * 발급 쿠폰 사용금지 update
     *
     * @param promotionBoDTO
     * @
     */
    public void updateCouponStopStatus(MbrIssuCpn mbrIssuCpn)  {
        getSession1().update("com.plgrim.ncp.biz.promotion.updateCouponStopStatus", mbrIssuCpn);
    }

    /**
     * 사용 온라인 쿠폰 복원 (CS)
     *
     * @param mbrIssuCpn
     * @
     */
    public void updateCouponRevertStatus(MbrIssuCpn mbrIssuCpn)  {
    	getSession1().update("com.plgrim.ncp.biz.promotion.updateCouponRevertStatus", mbrIssuCpn);
    }

    /**
     * 회원 온/오프 보유 쿠폰 일련번호(난수) 조회
     *
     * @param mbrIssuCpn
     * @
     */
    public List<String> selectMemberOwnCouponCrtfcCd(PromotionBoDTO promotionBoDTO)  {
        return getSession1().selectList("com.plgrim.ncp.biz.promotion.selectMemberOwnCouponCrtfcCd", promotionBoDTO);
    }

    /**
     * 쿠폰 일련번호(난수) 조회
     *
     * @param mbrIssuCpn
     * @
     */
    public String selectCouponCrtfcCd(MbrIssuCpn mbrIssuCpn)  {
        return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectCouponCrtfcCd", mbrIssuCpn);
    }

    /**
     * 일련번호(난수) 쿠폰 회원 발급 사용 체크
     *
     * @param mbrIssuCpn
     * @
     */
    public PromotionBoResult selectCouponCrtfcCdIssueUseCheck(MbrIssuCpn mbrIssuCpn)  {
        return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectCouponCrtfcCdIssueUseCheck", mbrIssuCpn);
    }

    /**
     * 발급 가능 일시 체크
     *
     * @param mbrIssuCpn
     * @
     */
    public int selectPrmAplPdCount(PrmAplPd prmAplPd)  {
        return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectPrmAplPdCount", prmAplPd);
    }

    /**
     * 쿠폰 발급 대상 체크 - 몰, 디바이스, 언어, 회원유형, 회원속성
     *
     * @param promotionBoDTO
     * @
     */
    public int selectCouponIssueTargetCheck(PromotionBoDTO promotionBoDTO)  {
        return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectCouponIssueTargetCheck", promotionBoDTO);
    }

    /**
     * (회원) 쿠폰 발급 Count
     *
     * @param mbrIssuCpn
     * @
     */
    public int selectCouponIssueCount(MbrIssuCpn mbrIssuCpn)  {
        return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectCouponIssueCount", mbrIssuCpn);
    }
 
    /**
     * 
     * @param mbrIssuCpn
     * @return
     */
    public int selectOnlneGrdBnefCpnPntCount(MbrIssuCpn mbrIssuCpn) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectOnlneGrdBnefCpnPntCount", mbrIssuCpn);
    }

    /**
     * 회원번호 유효체크
     *
     * @param mbrIssuCpn
     * @
     */
    public int selectMemberCheck(MbrIssuCpn mbrIssuCpn)  {
        return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectMemberCheck", mbrIssuCpn);
    }
    
    /**
     * 쿠폰 회원 발급 insert
     *
     * @param mbrIssuCpn
     * @
     */
    public int insertCouponIssue(MbrIssuCpn mbrIssuCpn)  {
        return getSession1().insert("com.plgrim.ncp.biz.promotion.insertCouponIssue", mbrIssuCpn);
    }

    /**
     * 쿠폰 발급 재고 수량 update
     *
     * @param prmCpn
     * @
     */
    public void updateIssueCouponInvQty(PrmCpn prmCpn)  {
        getSession1().update("com.plgrim.ncp.biz.promotion.updateIssueCouponInvQty", prmCpn);
    }

    /**
     * 쿠폰 인증(난수)코드 발급 insert
     *
     * @param prmCpn
     * @
     */
    public void insertCouponCrtfcCdIssue(PrmCpnCrtfcCd prmCpnCrtfcCd)  {
        getSession1().insert("com.plgrim.ncp.biz.promotion.insertCouponCrtfcCdIssue", prmCpnCrtfcCd);
    }

    /**
     * 쿠폰 인증(난수)코드 발급 insert
     *
     * @param prmCpn
     * @
     */
    public void insertCouponCrtfcCode(PrmCpnCrtfcCd prmCpnCrtfcCd)  {
        getSession1().insert("com.plgrim.ncp.biz.promotion.insertCouponCrtfcCode", prmCpnCrtfcCd);
    }
    
    /**
     * 쿠폰 발급 유형 코드 update
     *
     * @param prmCpn
     * @
     */
    public void updateCouponCrtfcCdTp(PrmCpn prmCpn)  {
        getSession1().update("com.plgrim.ncp.biz.promotion.updateCouponCrtfcCdTp", prmCpn);
    }

    /**
     * 프로모션 유효성 체크
     * 
     * @param prm
     *   - prmNo
     * @return
     *   - PrmExtend - prmCount
     */
    public PrmExtend selectValidPromotionInfo(Prm prm) {
        return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectValidPromotionInfo", prm);
    }

    /**
     * 기획전 쿠폰다운로드 유효성 체크
     *   - 다운로드 쿠폰만 가능
     * 
     * @param prm
     * @return
     */
    public PrmExtend selectValidPromtCpnDown(Prm prm)  {
        PrmExtend result = getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectValidPromtCpnDown", prm);
        return result;
    }

    /**
     * 프로모션 다국어 삭제
     *
     * @param prmLang
     * @return
     */
    public int deletePrmLang(PrmLang prmLang)  {
        int cnt = getSession1().delete("com.plgrim.ncp.web.bo.promotion.deletePrmLang", prmLang);
        return cnt;
    }

    /**
     * 프로모션 다국어 저장
     *
     * @param prmLang
     * @return
     */
    public int insertPrmlang(PrmLang prmLang)  {
        int cnt = getSession1().insert("com.plgrim.ncp.web.bo.promotion.insertPrmLang", prmLang);
        return cnt;
    }

    /**
     * 쿠폰 적용 대상 디바이스 조회
     * @param promotionBoDTO
     * @return
     * @
     */
    public String selectCpnIssuTgtDvc(PromotionBoDTO promotionBoDTO)  {
        return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectCpnIssuTgtDvc", promotionBoDTO);
    }

    /**
     * 상품상세노출 프로모션 생성 프로시져 실행
     */
    public int updateDspGodPrmProcedure(PromotionBoDTO promotionBoDTO)  {
    	return getSession1().update("com.plgrim.ncp.biz.promotion.updateDspGodPrmProcedure", promotionBoDTO);
    }
    
    /**
     *  캠페인아이디로 프로모션 번호 찾기
     */
    public String selectPrmNoByErpCmpgId(PromotionBoDTO promotionBoDTO)  {
        return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectPrmNoByErpCmpgId", promotionBoDTO);
    }

    /**
     * 프로모션 쿠폰 인증 코드 상세 조회. [병렬]
     *
     * @param mbrIssuCpn
     * @
     */
    public List<PrmCpnCrtfcCd> selectPrmCpnCrtfcCdList(PrmCpnCrtfcCdDTO prmCpnCrtfcCdDTO)  {
        return getSession1().selectList("com.plgrim.ncp.biz.promotion.selectPrmCpnCrtfcCdList", prmCpnCrtfcCdDTO);
    }
    
    /**
     * 프로모션 쿠폰 인증 코드 상세 조회. [병렬]
     *
     * @param mbrIssuCpn
     * @
     */
    public List<CouponUseResult> selectCouponList(PrmCpnCrtfcCdDTO prmCpnCrtfcCdDTO)  {
        return getSession1().selectList("com.plgrim.ncp.biz.promotion.selectCouponList", prmCpnCrtfcCdDTO);
    }
    
    
    /**
     * 쿠폰 사용 유무 업데이트
     * 
     * @param mbrIssuCpn
     * @
     */
    public int updateCouponUse(MbrIssuCpn mbrIssuCpn)  {
    	return getSession1().update("com.plgrim.ncp.biz.promotion.updateCouponUse", mbrIssuCpn);
    }
    
    /**
     * (회원) 쿠폰 발급 Count
     *
     * @param mbrIssuCpn
     * @
     */
    public int selectCpnCrtfcCount(PrmCpnCrtfcCd prmCpnCrtfcCd)  {
        return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectCpnCrtfcCount", prmCpnCrtfcCd);
    }
    
    /**
     * 온오프라인 대상 브랜드 상세 리스트 조회
     * @param searchDTO
     * @return
     * @
     */
    public CouponPromotionResult selectOnOffCpnTargetBrnd(CouponPromotionDTO searchDTO)  {
        return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectOnOffCpnTargetBrnd", searchDTO);
    }
    
    /**
     * 온오프라인 쿠폰 대상 상품 상세 리스트 조회
     * @param searchDTO
     * @return
     * @
     */
    public List<CouponPromotionResult> selectOnOffCpnTargetGod(CouponPromotionDTO searchDTO)  {
        return getSession1().selectList("com.plgrim.ncp.biz.promotion.selectOnOffCpnTargetGod", searchDTO);
    }
    
    /**
	 * ERP고객 온라인 회원번호 받아오기
	 */
	public String selectErpCustomerInfo(Mbr mbr) {
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectErpCustomerInfo", mbr);
	}
	
	/**
	 * 쿠폰 정보 저장 update
	 */
	public void updateCouponPromotion(PrmCpn prmCpn) {
		getSession1().insert("com.plgrim.ncp.biz.promotion.updateCouponPromotion", prmCpn);
	}
	
	 /**
     * 쿠폰 인증(난수)코드 발급 insert
     *
     * @param prmCpn
     * @
     */
    public int mergeCouponCrtfcCode(PrmCpnCrtfcCd prmCpnCrtfcCd)  {
        return getSession1().update("com.plgrim.ncp.biz.promotion.mergeCouponCrtfcCode", prmCpnCrtfcCd);
    }
    
    /**
     * 기등록된 회원발급쿠폰 정보 조회
     * @param mbrIssuCpn
     * @return
     * @
     */
    public MbrIssuCpn selectMbrIssuCpnInfo(MbrIssuCpn mbrIssuCpn)  {
    	return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectMbrIssuCpnInfo", mbrIssuCpn);
    }
    
    /**
     * 캠페인 등록여부 확인
     * @param prm
     * @return
     * @
     */
    public int selectAddCheck(Prm prm)  {
    	return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectAddCheck", prm);
    }
    
    /**
     * 온오프라인 프로모션 update
     * @param prm
     * @return
     * @
     */
    public int updateOnOffPrm(Prm prm)  {
    	return getSession1().update("com.plgrim.ncp.biz.promotion.updateOnOffPrm", prm);
    }
    
    /**
     * 온오프라인 프로모션 쿠폰 update
     * @param prmCpn
     * @return
     * @
     */
    public int updateOnOffPrmCpn(PrmCpn prmCpn)  {
    	return getSession1().update("com.plgrim.ncp.biz.promotion.updateOnOffPrmCpn", prmCpn);
    }
    
    
    /**
     * 첫 앱다운로드 쿠폰 정보
     */
    public CouponPromotionResult selectFirstAppCpnInfo(PrmCpn prmCpn)  {
        return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectFirstAppCpnInfo", prmCpn);
    }

    /**
	 * 프로모션 쿠폰 등록.
	 *
	 * @param prmCpn the PrmCpn
	 */
	public void insertPrmCpn(PrmCpn prmCpn) {
		getSession1().insert("com.plgrim.ncp.biz.promotion.insertPrmCpn", prmCpn);
	}
	
	/**
	 * 프로모션 쿠폰 수정.
	 *
	 * @param prmCpn the PrmCpn
	 */
	public int updatePrmCpn(PrmCpn prmCpn) {
		return getSession1().update("com.plgrim.ncp.biz.promotion.updatePrmCpn", prmCpn);
	}
	
    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}
