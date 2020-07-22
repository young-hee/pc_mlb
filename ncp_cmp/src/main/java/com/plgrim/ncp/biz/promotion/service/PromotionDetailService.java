package com.plgrim.ncp.biz.promotion.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.entities.datasource1.prm.PrmBundleDcCnd;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnGftExchg;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmOfferGft;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionPubliQtyLmitCd;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionUnlimitNumber;
import com.plgrim.ncp.base.repository.prm.PrmCpnRepository;
import com.plgrim.ncp.biz.promotion.data.CouponPromotionDTO;
import com.plgrim.ncp.biz.promotion.data.PrmCpnGftExchgExtend;
import com.plgrim.ncp.biz.promotion.data.PrmOfferGftExtend;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.biz.promotion.exception.PromotionAlreadyExistIssueException;
import com.plgrim.ncp.biz.promotion.repository.PromotionDetailRepository;
import com.plgrim.ncp.biz.promotion.repository.PromotionRepository;
import com.plgrim.ncp.biz.promotion.result.CouponPromotionResult;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;
import com.google.common.collect.Maps;

@Service
public class PromotionDetailService {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    @Autowired
    private IdGenService idGenService;

    @Autowired
    private PromotionDetailRepository promotionDetailRepository;

    @Autowired
    private PrmCpnRepository prmCpnRepository;

    @Autowired
    private PromotionRepository promotionRepository;
    
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

    public int insertPromotionCoupon(PromotionBoDTO promotionBoDTO) throws Exception {

        Long allPubliQty = promotionBoDTO.getPrmCpn().getAllPubliQty();
        promotionBoDTO.getPrmCpn().setAllIssuInvQty(allPubliQty);

        int cnt = promotionDetailRepository.insertPrmCpn(promotionBoDTO.getPrmCpn());
        return cnt;
    }

    /**
     * 프로모션 쿠폰 수정
     *
     * @param promotionBoDTO
     * @return
     * @throws Exception
     */
    public int updatePromotionCoupon(PromotionBoDTO promotionBoDTO) throws Exception {

        String prmNo = promotionBoDTO.getPrm().getPrmNo();

        PrmCpn prmCpn = new PrmCpn();
        prmCpn.setPrmNo(prmNo);
        prmCpn = prmCpnRepository.selectPrmCpn(prmCpn);

        // 발행수량 제한코드(param)
        String paramPubliQtyLmitCd = promotionBoDTO.getPrmCpn().getPubliQtyLmitCd();
        // 전체 발행수량(param)
        Long paramAllPubliQty = promotionBoDTO.getPrmCpn().getAllPubliQty();

        // 전체 발급재고수량
        Long paramAllIssuInvQty = promotionBoDTO.getPrmCpn().getAllIssuInvQty();

        // 발행수량 제한코드
        //String selectPubliQtyLmitCd = prmCpn.getPubliQtyLmitCd();
        // 전체 발행수량
        Long selectAllPubliQty = prmCpn.getAllPubliQty();
        // 전체 발급재고수량
        Long selectAllIssuInvQty = prmCpn.getAllIssuInvQty();

        BigDecimal bdParamPubliQty = BigDecimal.ZERO;
        BigDecimal addQtyBd = BigDecimal.ZERO;

        // 발급제한수량이 존재하는 경우
        if (paramAllPubliQty != null) {
            bdParamPubliQty = BigDecimal.valueOf(paramAllPubliQty);

        }
        // 값이 없지만 발급제한여부가 무제한인 경우
        else if (PromotionPubliQtyLmitCd.UNLMIT.toString().equals(paramPubliQtyLmitCd)) {
            bdParamPubliQty = PromotionUnlimitNumber.UNLIMIT_NUM.toBigDecimal();
        }

        BigDecimal bdSelectPubliQty = BigDecimal.ZERO;
        if (selectAllPubliQty != null) {
            bdSelectPubliQty = BigDecimal.valueOf(selectAllPubliQty);
        }

        BigDecimal bdSelectIssuInvQty = BigDecimal.ZERO;
        if (selectAllIssuInvQty != null) {
            bdSelectIssuInvQty = BigDecimal.valueOf(selectAllIssuInvQty);
        }

        // 기 발급수량 (select 발행수량 - 발급수량)
        BigDecimal dbSubtract = bdSelectPubliQty.subtract(bdSelectIssuInvQty);

        // 기 발급수량보다 변경할 발급수량이 작으면 불가
        if (bdParamPubliQty.compareTo(dbSubtract) < 0) {
            String[] param = { dbSubtract.toString() };
            throw new PromotionAlreadyExistIssueException(param);  //이미 {dbSubtract}건의 발급된 내역이 있습니다.
        }
        else {
            // 전체 발행 수량의 변동 크기 (param - select)
            addQtyBd = bdParamPubliQty.subtract(bdSelectPubliQty);
        }

        // 전체 발급재고수량을 재설정한다.
        paramAllIssuInvQty = bdSelectIssuInvQty.add(addQtyBd).longValue();

        promotionBoDTO.getPrmCpn().setAllIssuInvQty(paramAllIssuInvQty);

        int cnt = promotionRepository.updatePrmCpn(promotionBoDTO.getPrmCpn());
        return cnt;
    }

    public int insertPrmBundleDcCnd(SqlSession sqlSession, PromotionBoDTO promotionBoDTO) throws Exception {

        int result = 0;

        String prmNo = promotionBoDTO.getPrm().getPrmNo();
        String loginId = promotionBoDTO.getLoginId();

        List<PrmBundleDcCnd> list = promotionBoDTO.getPrmBundleDcCndList();

        for (PrmBundleDcCnd prmBundleDcCnd : list) {

            Map<String, Object> conditions = Maps.newHashMap();
            conditions.put("prm_no", prmNo);
            int cnt = idGenService.generateDBOrder(sqlSession, "PRM_BUNDLE_DC_CND", "BUNDLE_DC_CND_TURN", conditions, DatabaseType.ORACLE);

            prmBundleDcCnd.setPrmNo(prmNo);
            prmBundleDcCnd.setBundleDcCndTurn(cnt);
            prmBundleDcCnd.setRegtrId(loginId);
            prmBundleDcCnd.setUdterId(loginId);

            result += promotionDetailRepository.insertPrmBundleDcCnd(prmBundleDcCnd);
        }

        return result;
    }

    public int deletePrmBundleDcCnd(PromotionBoDTO promotionBoDTO) throws Exception {

        String prmNo = promotionBoDTO.getPrm().getPrmNo();
        PrmBundleDcCnd prmBundleDcCnd = new PrmBundleDcCnd();
        prmBundleDcCnd.setPrmNo(prmNo);

        int result = promotionDetailRepository.deletePrmBundleDcCnd(prmBundleDcCnd);
        return result;
    }

    public Page<CouponPromotionResult> selectCouponPromotionList(CouponPromotionDTO searchDTO, PageParam pageParam) throws Exception {
        Page<CouponPromotionResult> resultList = promotionDetailRepository.selectCouponPromotionList(searchDTO, pageParam);
        return resultList;
    }

    /**
     * 프로모션 제공 사은품 등록
     *
     * @param prmOfferGft
     * @return
     * @throws Exception
     */
    public int insertPrmOfferGft(PrmOfferGft prmOfferGft) throws Exception {
        int cnt = promotionDetailRepository.insertPrmOfferGft(prmOfferGft);
        return cnt;
    }

    /**
     * 프로모션 제공 사은품 삭제
     *
     * @param prmOfferGft
     * @return
     * @throws Exception
     */
    public int deletePrmOfferGft(PrmOfferGft prmOfferGft) throws Exception {
        int cnt = promotionDetailRepository.deletePrmOfferGft(prmOfferGft);
        return cnt;
    }
    
    /**
     * 프로모션 제공사은품 유효성 체크
     * 
     * @param prmOfferGft
     * @return
     * @throws Exception
     */
    public PrmOfferGftExtend selectValidPrmOfferGft(PrmOfferGft prmOfferGft) throws Exception {
        PrmOfferGftExtend result = promotionDetailRepository.selectValidPrmOfferGft(prmOfferGft);
        return result;
    }
    
    /**
     * 사용가능한 사은품교환권(offline) 조회 
     * 
     * @param prmOfferGft
     * @return
     * @throws Exception
     */
    public PrmCpnGftExchgExtend selectUsablePrmCpnGftExchg(PrmCpnGftExchg prmCpnGftExchg) throws Exception {
    	PrmCpnGftExchgExtend result = promotionDetailRepository.selectUsablePrmCpnGftExchg(prmCpnGftExchg);
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
    	int result = promotionDetailRepository.updateIssuePrmCpnGftExchg(prmCpnGftExchg);
    	return result;
    }
    
    /**
     * 앱다운로드 이벤트 오프라인 쿠폰 조회
     */
    public PrmCpnGftExchgExtend selectPrmCpnGftExchgAppDownload(PrmCpnGftExchg prmCpnGftExchg) throws Exception {
    	PrmCpnGftExchgExtend result = promotionDetailRepository.selectPrmCpnGftExchgAppDownload(prmCpnGftExchg);
    	return result;
    }
    
    /**
     * 앱다운로드 이벤트 오프라인 쿠폰 발급
     */
    public int updatePrmCpnGftExchgAppDownloadMapping(PrmCpnGftExchg prmCpnGftExchg) throws Exception {
    	int result = promotionDetailRepository.updatePrmCpnGftExchgAppDownloadMapping(prmCpnGftExchg);
    	return result;
    }

    /**
     * 앱다운로드 이벤트 오프라인 쿠폰 사용
     */
    public int updatePrmCpnGftExchgAppDownloadUse(PrmCpnGftExchg prmCpnGftExchg) throws Exception {
    	int result = promotionDetailRepository.updatePrmCpnGftExchgAppDownloadUse(prmCpnGftExchg);
    	return result;
    }

    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}

