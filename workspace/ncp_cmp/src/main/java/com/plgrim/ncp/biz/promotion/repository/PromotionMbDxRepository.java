package com.plgrim.ncp.biz.promotion.repository;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.biz.device.result.PromotionMbResult;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.bsk.BskGodExtend;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtStmp;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.biz.promotion.data.PromotionMbSearchDTO;

@Repository
public class PromotionMbDxRepository extends AbstractRepository {

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
	@Autowired
    @Qualifier("sessionTemplate1")
    private SqlSession sqlSession1;
	
	/**
	 *  브랜드 온오프라인 쿠폰 조회
	 *  
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public PromotionMbResult selectBrnadOnOffLineCoupon(PromotionMbSearchDTO dto) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.web.mb.dx.promotion.selectBrnadOnOffLineCoupon", dto);
	}
	
	/**
	 *  브랜드 온오프라인 쿠폰 리스트 조회
	 *  
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public List<PromotionMbResult> selectBrnadOnOffLineCouponList(PromotionMbSearchDTO dto) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.web.mb.dx.promotion.selectBrnadOnOffLineCouponList", dto);
	}
	
	/**
	 *  회원에게 발급된 브랜드 온오프라인 쿠폰 조회
	 *  
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public List<PromotionMbResult> selectMbrBrnadOnOffLineCoupon(PromotionMbSearchDTO dto) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.web.mb.dx.promotion.selectMbrBrnadOnOffLineCoupon", dto);
	}
	
	
	/**
	 * 브랜드 스탬프 이벤트 조회
	 * 
	 * @param brndId
	 * @return
	 * @throws Exception
	 */
	public PromotionMbResult selectBrndStampEvent(String brndId) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.web.mb.dx.promotion.selectBrndStampEvent", brndId);
	}
	
	/**
	 * 회원 스탬프 cnt 조회
	 * 
	 * @param brndId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Integer> selectMbrStampEventCnt(EvtStmp dto) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.web.mb.dx.promotion.selectMbrStampEventCnt", dto);
	}
	
	/**
	 * 이벤트 참여 내역 등록
	 * 
	 * @param brndId
	 * @return
	 * @throws Exception
	 */
	public void insertMbEvtApplcn(EvtApplcn evtApplcn) throws Exception{
		long evtPartcptnSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_EVT_APPLCN", DatabaseType.ORACLE);
		evtApplcn.setEvtPartcptnSn(evtPartcptnSn);
		getSession1().selectOne("com.plgrim.ncp.web.mb.dx.promotion.insertMbEvtApplcn", evtApplcn);
	}

	/**
	 * 회원 스탬프 등록
	 * 
	 * @param brndId
	 * @return
	 * @throws Exception
	 */
	public void insertMbEvtStmp(EvtStmp dto) throws Exception{
		getSession1().selectOne("com.plgrim.ncp.web.mb.dx.promotion.insertMbEvtStmp", dto);
	}
	
	/**
	 *  매장에서 사용할수 있는 회원의 온오프라인 쿠폰 조회
	 *  
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public PromotionMbResult selectMbrBrndShopOnOffLineCoupon(PromotionMbSearchDTO dto) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.web.mb.dx.promotion.selectMbrBrndShopOnOffLineCoupon", dto);
	}

	/**
	 *  GEOFENCE 매장에서 사용할수 있는 회원의 온오프라인 쿠폰 조회
	 *  
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public List<PromotionMbResult> selectMbrGeoBrndShopOnOffLineCoupon(PromotionMbSearchDTO dto) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.web.mb.dx.promotion.selectMbrGeoBrndShopOnOffLineCoupon", dto);
	}
	
	/**
	 *  회원 장바구니 상품 조회
	 *  
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public List<BskGodExtend> selectMbrCarGodNo(Map<String, Object> mapData) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.web.mb.dx.promotion.selectMbrCarGodNo", mapData);
	}

	/* ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */


	

}
