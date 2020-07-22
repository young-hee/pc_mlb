package com.plgrim.ncp.biz.promotion.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplGod;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplTgt;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCrsDcAplGod;
import com.plgrim.ncp.biz.promotion.data.PrmAplGodExtend;
import com.plgrim.ncp.biz.promotion.data.PromotionApplyGoodBoDTO;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.biz.promotion.result.PromotionApplyGoodBoResult;
import com.plgrim.ncp.biz.promotion.result.PromotionBoResult;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;

@Repository
public class PromotionTargetRepository extends AbstractRepository {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */



    @Autowired
    private IdGenService idGenService;

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

    public int insertPromotionApplyTarget(SqlSession sqlSession, PrmAplTgt prmAplTgt) throws Exception {

        Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("prm_no", prmAplTgt.getPrmNo());

        int aplTurn = idGenService.generateDBOrder(sqlSession, "PRM_APL_TGT", "APL_TURN", conditions, DatabaseType.ORACLE);
        prmAplTgt.setAplTurn(aplTurn);

        int result = getSession1().insert("com.plgrim.ncp.base.insertPrmAplTgt", prmAplTgt);
        return result;
    }

    public int deletePromotionApplyTarget(PrmAplTgt prmAplTgt) throws Exception {

        int result = getSession1().delete("com.plgrim.ncp.biz.promotion.deletePrmAplTgt", prmAplTgt);
        return result;
    }

    public int insertPromotionApplyGoods(SqlSession sqlSession, PrmAplGod prmAplGod) throws Exception {

        Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("prm_no", prmAplGod.getPrmNo());

        int aplTurn = idGenService.generateDBOrder(sqlSession, "PRM_APL_GOD", "APL_TURN", conditions, DatabaseType.ORACLE);
        prmAplGod.setAplTurn(aplTurn);

        int result = getSession1().insert("com.plgrim.ncp.base.insertPrmAplGod", prmAplGod);
        return result;
    }

    public int deletePromotionTargetGoods(PrmAplGod prmAplGod) throws Exception {
        int result = getSession1().delete("com.plgrim.ncp.biz.promotion.deletePromotionTargetGoods", prmAplGod);
        return result;
    }

    public Page<PromotionApplyGoodBoResult> selectPromotionApplyGoodList(PromotionApplyGoodBoDTO searchDTO, PageParam pageParam)
            throws Exception {

        String lang = "KOR";

        // 페이지 인덱스 셋팅
        RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
        List<PromotionApplyGoodBoResult> resultList = getSession1()
                .selectList("com.plgrim.ncp.biz.promotion.selectPrmAplGodList", searchDTO);

        for (PromotionApplyGoodBoResult result : resultList) {

            if (CodeUtil.getCode(lang, result.getPrmAplGodEx().getGodSaleSectCd()) != null) {
                result.getPrmAplGodEx().setGodSaleSectNm(CodeUtil.getCode(lang, result.getPrmAplGodEx().getGodSaleSectCd()).getCdNm());
            }
        }

        long totalRow = getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectPrmAplGodListCount", searchDTO);

        return new PageImpl<PromotionApplyGoodBoResult>(resultList, pageParam.getPageable(), totalRow);
    }

    public int insertPrmCrsDcAplGod(PrmCrsDcAplGod prmCrsDcAplGod) throws Exception {
        int result = getSession1().insert("com.plgrim.ncp.base.insertPrmCrsDcAplGod", prmCrsDcAplGod);
        return result;
    }

    public int deletePrmCrsDcAplGod(PrmCrsDcAplGod prmCrsDcAplGod) throws Exception {
        int result = getSession1().delete("com.plgrim.ncp.biz.promotion.deletePrmCrsDcAplGod", prmCrsDcAplGod);
        return result;
    }

    public Page<PromotionApplyGoodBoResult> selectPrmCrsDcAplGodList(PromotionApplyGoodBoDTO searchDTO, PageParam pageParam)
            throws Exception {

        String lang = "KOR";

        // 페이지 인덱스 셋팅
        RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
        List<PromotionApplyGoodBoResult> resultList = getSession1().selectList("com.plgrim.ncp.biz.promotion.selectPrmCrsDcAplGodList",
                searchDTO);

        for (PromotionApplyGoodBoResult result : resultList) {

            if (CodeUtil.getCode(lang, result.getPrmAplGodEx().getGodSaleSectCd()) != null) {
                result.getPrmAplGodEx().setGodSaleSectNm(CodeUtil.getCode(lang, result.getPrmAplGodEx().getGodSaleSectCd()).getCdNm());
            }
        }

        long totalRow = getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectPrmCrsDcAplGodListCount", searchDTO);

        return new PageImpl<PromotionApplyGoodBoResult>(resultList, pageParam.getPageable(), totalRow);
    }

    public Page<PromotionBoResult> selectPrmAplTgtGrpcdList(PromotionBoDTO searchDTO, PageParam pageParam) throws Exception {

        // 페이지 인덱스 셋팅
        RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
        List<PromotionBoResult> resultList = getSession1().selectList("com.plgrim.ncp.biz.promotion.selectPrmAplTgtGrpcdList", searchDTO);

        long totalRow = getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectPrmAplTgtGrpcdListCount", searchDTO);

        return new PageImpl<PromotionBoResult>(resultList, pageParam.getPageable(), totalRow);
    }
    
    /**
     * 주문할인 프로모션 적용상품 유효성 체크
     * 
     * @param prmAplGodEx
     * @return
     * @throws Exception
     */
    public PromotionApplyGoodBoResult selectValidOrderPromotionApplyGoodInfo(PrmAplGodExtend prmAplGodEx) throws Exception {
        PromotionApplyGoodBoResult result = getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectValidOrderPromotionApplyGoodInfo", prmAplGodEx);
        return result;
    }
    
    /**
     * 적용대상상품 유효성 체크
     * 
     * @param prmAplGodEx
     * @return
     * @throws Exception
     */
    public PromotionApplyGoodBoResult selectValidPrmAplGod(PrmAplGod prmAplGod) throws Exception {
        PromotionApplyGoodBoResult result = getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectValidPrmAplGod", prmAplGod);
        return result;
    }
    
    /**
     * 적용대상상품 전체대상 여부 체크
     *   - 쿠폰프로모션 > 배송비쿠폰의 경우 default(전체)로 쿼리에 not exists 로 구분
     *                   적용대상상품 추가시 쿼리에서 제거요망
     * 
     * @param prmAplGodEx
     * @return
     * @throws Exception
     */
    public int selectValidPrmAplGodTargetAll(PrmAplGod prmAplGod) throws Exception {
        int result = getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectValidPrmAplGodTargetAll", prmAplGod);
        return result;
    }
    
    /**
     * 쿠폰 적용대상브랜드 - 상위브랜드 코드의 하위브랜드 코드 일괄등록
     */
    public int insertPrmAplGodByBrandCode(SqlSession sqlSession, PrmAplGod prmAplGod) throws Exception {
        return getSession1().update("com.plgrim.ncp.biz.promotion.insertPrmAplGodByBrandCode", prmAplGod);
    }
    
    /**
     * 적용 대상 상품 검색
     * 
     * @param searchDTO
     * @return
     * @throws Exception
     */
    public int selectPrmAplGodInfoForSearch(PromotionApplyGoodBoDTO searchDTO) throws Exception {
        return getSession1().selectOne("com.plgrim.ncp.biz.promotion.selectPrmAplGodInfoForSearch", searchDTO);
    }
    
    
    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}
