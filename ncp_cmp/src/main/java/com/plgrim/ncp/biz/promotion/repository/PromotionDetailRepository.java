package com.plgrim.ncp.biz.promotion.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmBundleDcCnd;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnGftExchg;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmOfferGft;
import com.plgrim.ncp.biz.promotion.data.CouponPromotionDTO;
import com.plgrim.ncp.biz.promotion.data.PrmCpnCrtfcCdDTO;
import com.plgrim.ncp.biz.promotion.data.PrmCpnGftExchgExtend;
import com.plgrim.ncp.biz.promotion.data.PrmOfferGftExtend;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.biz.promotion.result.CouponPromotionResult;
import com.plgrim.ncp.biz.promotion.result.PromotionBoResult;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.framework.page.PageParam;

@Repository
public class PromotionDetailRepository extends AbstractRepository {

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

    public List<PromotionBoResult> selectPromotionList(PromotionBoDTO promotionBoDTO) throws Exception {
        List<PromotionBoResult> resultList = getSession1().selectList("com.plgrim.ncp.biz.promotion.selectPromotionList", promotionBoDTO);
        return resultList;
    }

    public int insertPrmCpn(PrmCpn prmCpn) throws Exception {
        int result = getSession1().insert("com.plgrim.ncp.biz.promotion.insertPrmCpn", prmCpn);
        return result;
    }

    public Page<CouponPromotionResult> selectCouponPromotionList(CouponPromotionDTO searchDTO, PageParam pageParam) throws Exception {

        // 페이지 인덱스 셋팅
        RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
        List<CouponPromotionResult> resultList = getSession1().selectList("com.plgrim.ncp.biz.promotion.selectCouponPromotionList", searchDTO);
        
        String lang = "KOR";

        for (CouponPromotionResult result : resultList) {

            if (CodeUtil.getCode(lang, result.getPrmEx().getPrmStatCd()) != null) {
                result.getPrmEx().setPrmStatNm(CodeUtil.getCode(lang, result.getPrmEx().getPrmStatCd()).getCdNm());
            }

            if (CodeUtil.getCode(lang, result.getPrmEx().getPrmDtlTpCd()) != null) {
                result.getPrmEx().setPrmDtlTpNm(CodeUtil.getCode(lang, result.getPrmEx().getPrmDtlTpCd()).getCdNm());
            }

            if (CodeUtil.getCode(lang, result.getPrmCpnEx().getCpnKndCd()) != null) {
                result.getPrmCpnEx().setCpnKndNm(CodeUtil.getCode(lang, result.getPrmCpnEx().getCpnKndCd()).getCdNm());
            }

            if (CodeUtil.getCode(lang, result.getPrmCpnEx().getCpnIssuMthdCd()) != null) {
                result.getPrmCpnEx().setCpnIssuMthdNm(CodeUtil.getCode(lang, result.getPrmCpnEx().getCpnIssuMthdCd()).getCdNm());
            }

            if (CodeUtil.getCode(lang, result.getPrmCpnEx().getOnoflneCpnOfrTpCd()) != null) {
                result.getPrmCpnEx().setOnoflneCpnOfrTpNm(CodeUtil.getCode(lang, result.getPrmCpnEx().getOnoflneCpnOfrTpCd()).getCdNm());
            }
            
            // 다국어를 KOR,ENG <= 이런 형식으로 받아온 코드를 한국어,중국어 <= 이런 형식으로 표현하도록 변환
            if(result.getPrmEx() != null) {
            	String langCd = result.getPrmEx().getPrmLangCd();
            	String langNm = "";
            	String[] defs = (langCd==null)?new String[]{}:langCd.split(",");
            	
            	for(String tmpLangCd : defs) {
            		langNm += "," + CodeUtil.getCode(lang, tmpLangCd).getCdNm();
            	}

            	result.getPrmEx().setPrmLangNm(langNm.substring(1));
            }
        }

        long totalRow = getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectCouponPromotionListCount", searchDTO);

        return new PageImpl<CouponPromotionResult>(resultList, pageParam.getPageable(), totalRow);
    }

    public int insertPrmBundleDcCnd(PrmBundleDcCnd prmBundleDcCnd) throws Exception {
        int result = getSession1().insert("com.plgrim.ncp.base.insertPrmBundleDcCnd", prmBundleDcCnd);
        return result;
    }

    public int deletePrmBundleDcCnd(PrmBundleDcCnd prmBundleDcCnd) throws Exception {
        int result = getSession1().delete("com.plgrim.ncp.biz.promotion.deletePrmBundleDcCnd", prmBundleDcCnd);
        return result;
    }

    /**
     * 프로모션 제공 사은품 등록
     *
     * @param prmOfferGft
     * @return
     * @throws Exception
     */
    public int insertPrmOfferGft(PrmOfferGft prmOfferGft) throws Exception {
        int result = getSession1().insert("com.plgrim.ncp.base.insertPrmOfferGft", prmOfferGft);
        return result;
    }
    
    /**
     * 프로모션 제공 사은품 삭제
     *
     * @param prmOfferGft
     * @return
     * @throws Exception
     */
    public int deletePrmOfferGft(PrmOfferGft prmOfferGft) throws Exception {
        int result = getSession1().insert("com.plgrim.ncp.base.deletePrmOfferGft", prmOfferGft);
        return result;
    }
    
    /**
     * 프로모션 제공사은품 유효성 체크
     * 
     * @param prmOfferGft
     * @return
     * @throws Exception
     */
    public PrmOfferGftExtend selectValidPrmOfferGft(PrmOfferGft prmOfferGft) throws Exception {
        PrmOfferGftExtend result = getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectValidPrmOfferGft", prmOfferGft);
        return result;
    }

	/**
	 * 사용가능한 사은품교환권(offline) 조회
	 * 
	 * @return
	 * @throws Exception
	 */
	public PrmCpnGftExchgExtend selectUsablePrmCpnGftExchg(PrmCpnGftExchg prmCpnGftExchg) throws Exception {
		PrmCpnGftExchgExtend result = getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectUsablePrmCpnGftExchg", prmCpnGftExchg);
		return result;
	}

	/**
	 * 사은품교환권(offline) 발급
	 * 
	 * @param prmCpnGftExchg
	 * @return
	 * @throws Exception
	 */
	public int updateIssuePrmCpnGftExchg(PrmCpnGftExchg prmCpnGftExchg) throws Exception {
		int result = getSession1().update("com.plgrim.ncp.biz.promotion.updateIssuePrmCpnGftExchg", prmCpnGftExchg);
		return result;
	}
	
	/**
	 * 앱다운로드 이벤트 오프라인 쿠폰 조회
	 */
	public PrmCpnGftExchgExtend selectPrmCpnGftExchgAppDownload(PrmCpnGftExchg prmCpnGftExchg) throws Exception {
		PrmCpnGftExchgExtend result = getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectPrmCpnGftExchgAppDownload", prmCpnGftExchg);
		return result;
	}

	/**
	 * 앱다운로드 이벤트 오프라인 쿠폰 발급
	 */
	public int updatePrmCpnGftExchgAppDownloadMapping(PrmCpnGftExchg prmCpnGftExchg) throws Exception {
		int result = getSession1().update("com.plgrim.ncp.biz.promotion.updatePrmCpnGftExchgAppDownloadMapping", prmCpnGftExchg);
		return result;
	}
	
	/**
	 * 앱다운로드 이벤트 오프라인 쿠폰 사용
	 */
	public int updatePrmCpnGftExchgAppDownloadUse(PrmCpnGftExchg prmCpnGftExchg) throws Exception {
		int result = getSession1().update("com.plgrim.ncp.biz.promotion.updatePrmCpnGftExchgAppDownloadUse", prmCpnGftExchg);
		return result;
	}

	public List<PrmCpnCrtfcCdDTO> selectPrmCpnCrtfcCdList(PrmCpnCrtfcCdDTO  prmCpnCrtfcCdDTO) throws Exception {
		return   getSession1().selectList("com.plgrim.ncp.biz.promotion.selectPrmCpnGftExchgAppDownload", prmCpnCrtfcCdDTO);
	}
	
    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}

