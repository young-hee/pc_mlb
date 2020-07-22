package com.plgrim.ncp.cmp.promotion.fo;

import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.biz.promotion.data.PrmExtend;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;

public interface PromotionCouponFOComponent {
	
	/**
     * (회원) 쿠폰 발급 Count
     */
	public int selectCouponIssueCount(MbrIssuCpn mbrIssuCpn) throws Exception;
	
    /**
     * 기획전 쿠폰다운로드 유효성 체크
     * 
     * @param prm
     * @return
     * @throws Exception
     */
    public PrmExtend selectValidPromtCpnDown(Prm prm) throws Exception;
    
    /**
     * 사용 온라인 쿠폰 복원 (CS)
     *
     * @param mbrCpnNo
     * @throws Exception
     */
    public boolean modifyCouponRevertStatus(String mbrCpnNo) throws Exception;

    /**
     * 회원 가입 대상 온/오프 수신 쿠폰 발급 insert
     *
     * @param promotionBoDTO
     * @throws Exception
     */
    public void addOnoffCouponSignInMemberIssue(PromotionBoDTO promotionBoDTO) throws Exception;

    /**
     * 일련번호(난수) 쿠폰 회원 발급 insert
     *
     * @param promotionBoDTO
     * @throws Exception
     */
    public String addCouponCrtfcCdMemberIssue(PromotionBoDTO promotionBoDTO) throws Exception;

    /**
     * 쿠폰 다운로드 회원 발급 insert
     *
     * @param promotionBoDTO
     * @throws Exception
     */
    public String addCouponDownMemberIssue(PromotionBoDTO promotionBoDTO) throws Exception;
	
}
