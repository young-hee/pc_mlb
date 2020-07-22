package com.plgrim.ncp.cmp.promotion.bo;

import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnCrtfcCd;
import com.plgrim.ncp.biz.promotion.data.CouponPromotionDTO;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.biz.promotion.result.CouponPromotionResult;
import com.plgrim.ncp.biz.promotion.result.MbrIssuCpnResult;
import com.plgrim.ncp.framework.page.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PromotionCouponBOComponent extends PromotionBOComponent{

	/**
     * 쿠폰 프로모션 목록 조회(CS쿠폰 팝업과 같이 사용하기 위해 biz로 올림)
     *
     * @param searchDTO
     * @param pageParam
     * @return
     * @throws Exception
     */
	public Page<CouponPromotionResult> selectCouponPromotionList(CouponPromotionDTO searchDTO, PageParam pageParam) throws Exception;

	/**
     * 발급쿠폰 사용정지 update
     *
     * @param promotionBoDTO
     * @throws Exception
     */
    public void modifyCouponStopStatus(List<PromotionBoDTO> promotionBoDTO) throws Exception;
    
    /**
     * 쿠폰명 다국어 저장 merge
     *
     * @param promotionBoDTO
     * @throws Exception
     */
    public void mergeCouponNmMlang(PromotionBoDTO promotionBoDTO) throws Exception;
    
    /**
     * 온오프라인 프로모션 수정 update
     * @param promotionBoDTO
     * @return
     * @throws Exception
     */
    public int updateOnOffPromotion(PromotionBoDTO promotionBoDTO) throws Exception;
    
    /**
     * 쿠폰 회원 발급 insert
     *
     * @param promotionBoDTO
     * @throws Exception
     */
    public String addCouponMemberIssue(PromotionBoDTO promotionBoDTO) throws Exception;
    
    /**
     * 쿠폰 회원 발급 Excel insert
     *
     * @param list
     * @param prmNo
     * @throws Exception
     */
    public List<MbrIssuCpnResult> addCouponMemberIssueExcel(List<MbrIssuCpn> list, String prmNo) throws Exception;
    
    /**
     * 쿠폰 인증(난수)코드 발급 insert
     *
     * @param promotionBoDTO
     * @throws Exception
     */
    public void addCouponCrtfcCdIssue(PrmCpn prmCpn) throws Exception;
    
    /**
     * 쿠폰 대표 인증(난수)코드 발급 insert
     *
     * @param promotionBoDTO
     * @throws Exception
     */
    public void addCouponSingleCrtfcCdIssue(PrmCpnCrtfcCd prmCpnCrtfcCd) throws Exception;
    
    /**
	 * ERP 발급내역 발급
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String insertOnOffCouponIssue(CouponPromotionResult promotion) throws Exception;
	
}
