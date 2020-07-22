package com.plgrim.ncp.biz.promotion.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.biz.promotion.data.CouponPromotionDTO;
import com.plgrim.ncp.biz.promotion.data.PrmExtend;
import com.plgrim.ncp.framework.commons.StringService;

@Repository
public class OnOffCouponRepository extends AbstractRepository {
	
	public int insertCpnCrtfcCd(String erpCpnId, String cpnCrtfcCd, String regtrId, String mallId) {
		CouponPromotionDTO couponDTO = this.makeNewCouponPromotionDTO();
		couponDTO.getPrm().setErpCpnId(erpCpnId);
		couponDTO.getMbrIssuCpn().setCpnCrtfcCd(cpnCrtfcCd);
		couponDTO.getMbrIssuCpn().setRegtrId(regtrId);
		couponDTO.setMallId(mallId);
		
		return getSession1().insert("com.plgrim.ncp.biz.promotion.onoffcoupon.insertCouponCrtfcCode", couponDTO);
	}
	
	public int insertCpnCrtfcCdByPrmNo(String erpCpnId, String cpnCrtfcCd, String regtrId, String prmNo) {
		CouponPromotionDTO couponDTO = this.makeNewCouponPromotionDTO();
		couponDTO.getPrm().setErpCpnId(erpCpnId);
		couponDTO.getPrm().setPrmNo(prmNo);
		couponDTO.getMbrIssuCpn().setCpnCrtfcCd(cpnCrtfcCd);
		couponDTO.getMbrIssuCpn().setRegtrId(regtrId);
		
		return getSession1().insert("com.plgrim.ncp.biz.promotion.onoffcoupon.insertCouponCrtfcCodeByPrmNo", couponDTO);
	}
	
	public int insertMbrIssuCpn(String erpCpnId, String cpnCrtfcCd, String mbrNo, String regtrId, String validBegDate, String validEndDate, Date cpnPubliDt, String useYn, Date useDt) {
		CouponPromotionDTO couponDTO = this.makeNewCouponPromotionDTO();
		couponDTO.getPrm().setErpCpnId(erpCpnId);
		couponDTO.getMbrIssuCpn().setCpnCrtfcCd(cpnCrtfcCd);
		couponDTO.getMbrIssuCpn().setMbrNo(mbrNo);
		couponDTO.getMbrIssuCpn().setRegtrId(regtrId);
		couponDTO.getMbrIssuCpn().setValidBegDate(validBegDate);
		couponDTO.getMbrIssuCpn().setValidEndDate(validEndDate);
		couponDTO.getMbrIssuCpn().setCpnPubliDt(cpnPubliDt);
		if(StringService.isNotEmpty(useYn) && "Y".equals(useYn)) {
			couponDTO.getMbrIssuCpn().setCpnStatCd("USE");
		}
		else {
			couponDTO.getMbrIssuCpn().setCpnStatCd("NO_USE");
		}
		couponDTO.getMbrIssuCpn().setCpnUseDt(useDt);

		return getSession1().insert("com.plgrim.ncp.biz.promotion.onoffcoupon.insertMemberIssueCoupon", couponDTO);
	}
	
	public int insertMbrIssuCpn(String erpCpnId, String cpnCrtfcCd, String mbrNo, String regtrId, String validBegDate, String validEndDate, Date cpnPubliDt, String useYn, Date useDt, String mallId) {
		CouponPromotionDTO couponDTO = this.makeNewCouponPromotionDTO();
		couponDTO.getPrm().setErpCpnId(erpCpnId);
		couponDTO.getMbrIssuCpn().setCpnCrtfcCd(cpnCrtfcCd);
		couponDTO.getMbrIssuCpn().setMbrNo(mbrNo);
		couponDTO.getMbrIssuCpn().setRegtrId(regtrId);
		couponDTO.getMbrIssuCpn().setValidBegDate(validBegDate);
		couponDTO.getMbrIssuCpn().setValidEndDate(validEndDate);
		couponDTO.getMbrIssuCpn().setCpnPubliDt(cpnPubliDt);
		couponDTO.setMallId(mallId);
		if(StringService.isNotEmpty(useYn) && "Y".equals(useYn)) {
			couponDTO.getMbrIssuCpn().setCpnStatCd("USE");
		}
		else {
			couponDTO.getMbrIssuCpn().setCpnStatCd("NO_USE");
		}
		couponDTO.getMbrIssuCpn().setCpnUseDt(useDt);

		return getSession1().insert("com.plgrim.ncp.biz.promotion.onoffcoupon.insertMemberIssueCoupon", couponDTO);
	}
	
	public int insertMbrIssuCpnByPrmNo(String erpCpnId, String cpnCrtfcCd, String mbrNo, String regtrId, String validBegDate, String validEndDate, Date cpnPubliDt, String useYn, Date useDt, String prmNo) {
		CouponPromotionDTO couponDTO = this.makeNewCouponPromotionDTO();
		couponDTO.getPrm().setErpCpnId(erpCpnId);
		couponDTO.getPrm().setPrmNo(prmNo);
		couponDTO.getMbrIssuCpn().setCpnCrtfcCd(cpnCrtfcCd);
		couponDTO.getMbrIssuCpn().setMbrNo(mbrNo);
		couponDTO.getMbrIssuCpn().setRegtrId(regtrId);
		couponDTO.getMbrIssuCpn().setValidBegDate(validBegDate);
		couponDTO.getMbrIssuCpn().setValidEndDate(validEndDate);
		couponDTO.getMbrIssuCpn().setCpnPubliDt(cpnPubliDt);
		if(StringService.isNotEmpty(useYn) && "Y".equals(useYn)) {
			couponDTO.getMbrIssuCpn().setCpnStatCd("USE");
		}
		else {
			couponDTO.getMbrIssuCpn().setCpnStatCd("NO_USE");
		}
		couponDTO.getMbrIssuCpn().setCpnUseDt(useDt);

		return getSession1().insert("com.plgrim.ncp.biz.promotion.onoffcoupon.insertMemberIssueCoupon", couponDTO);
	}
	
	private CouponPromotionDTO makeNewCouponPromotionDTO() {
		CouponPromotionDTO couponDTO = new CouponPromotionDTO();
		couponDTO.setPrm(new Prm());
		couponDTO.setMbrIssuCpn(new MbrIssuCpn());
		return couponDTO;
	}
	
	public List<PrmExtend> selectCouponPrmNo(String erpCpnId, String mallId) {
		CouponPromotionDTO couponDTO = this.makeNewCouponPromotionDTO();
		couponDTO.getPrm().setErpCpnId(erpCpnId);		
		couponDTO.setMallId(mallId);
		
		return getSession1().selectList("com.plgrim.ncp.biz.promotion.onoffcoupon.selectCouponPrmNo", couponDTO);
	}
	
	public PrmExtend selectCouponPrmNoFromBenefit(String erpCpnId, String mallId, String mbrNo) {
		CouponPromotionDTO couponDTO = this.makeNewCouponPromotionDTO();
		couponDTO.getPrm().setErpCpnId(erpCpnId);	
		couponDTO.getMbrIssuCpn().setMbrNo(mbrNo);
		couponDTO.setMallId(mallId);
		
		return getSession1().selectOne("com.plgrim.ncp.biz.promotion.onoffcoupon.selectCouponPrmNoFromBenefit", couponDTO);
	}
}
