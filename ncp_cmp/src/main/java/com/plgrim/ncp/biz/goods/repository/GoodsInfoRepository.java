package com.plgrim.ncp.biz.goods.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspGodPrc;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodCnncGrpGodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodColorExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodCpstGodCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmOpt;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyCpstGodNmExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.god.GodNtfcDetail;
import com.plgrim.ncp.base.entities.datasource1.god.GodOpt;
import com.plgrim.ncp.base.entities.datasource1.god.GodOptVal;
import com.plgrim.ncp.base.entities.datasource1.god.GodRelateExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodSaleMall;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInv;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsColorResult;
import com.plgrim.ncp.biz.goods.result.GoodsInfoResult;

/** Copyright (c) 2018 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 *
 * Description	:	상품 정보 Repository
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Repository
public class GoodsInfoRepository extends AbstractRepository{

	/**
	 * 상품 조회
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public God getGoods(GoodsSearchDTO goodsSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.info.getGoods", goodsSearchDTO);
	}
	
	/**
	 * 상품목록 수
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public long getGoodsListCount(GoodsSearchDTO goodsSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.info.getGoodsListCount", goodsSearchDTO);
	}
			
	/**
	 * 상품목록 조회
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public List<GoodsInfoResult> selectGoodsList(GoodsSearchDTO goodsSearchDTO){
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.selectGoodsList", goodsSearchDTO);
	}
	
	/**
	 * 상품 테이블 ERP품번 등록 여부 확인
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public long isExistErpGodNo(GoodsSearchDTO goodsSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.info.isExistErpGodNo", goodsSearchDTO);
	}

	/**
	 * 상품 단품 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodItmExtend> getGoodsItemList(String godNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.getGoodsItemList", godNo);	
	}
	
	/**
	 * 상품별 매장 단품(옵션) 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodItmExtend> getGoodsItemListByShop(GodShopItmInv shopItmInv) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.getGoodsItemListByShop", shopItmInv);	
	}
	
	/**
	 * 구성품 연결 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodCpstGodCnncExtend> getGoodsCompositionGoodsConnectionList(String godNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.getGoodsCompositionGoodsConnectionList", godNo);
	}
	
	/**
	 * 구성상품 언어별 상품명 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodLangbyCpstGodNmExtend> getGoodsLangByCompositionGoodsNameList(String godNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.getGoodsLangByCompositionGoodsNameList", godNo);
	}
	
	/**
	 * 상품 구성품 연결의 단품 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodItmExtend> getGoodsCompositionGoodsConnectionItemList(String godNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.getGoodsCompositionGoodsConnectionItemList", godNo);
	}

	/**
	 * 상품 구성품 연결의 매장별 단품 조회
	 * 
	 * @param godShopItmInv
	 * @return
	 */
	public List<GodItmExtend> getGoodsCompositionGoodsConnectionItemListByShop(GodShopItmInv shopItmInv) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.getGoodsCompositionGoodsConnectionItemListByShop", shopItmInv);
	}
	
	/**
	 * 판매몰 정보 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodSaleMall> getGoodsSaleMallList(String godNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.getGoodsSaleMallList", godNo);
	}
	
	/**
	 * 언어별 상품명 목록 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodLangbyGodNm> getGoodsLangByGoodsNameList(String godNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.getGoodsLangByGoodsNameList", godNo);
	}
	
	/**
	 * 상품 고시정보 목록 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodNtfcDetail> getGoodsNotificationDetailList(String godNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.getGoodsNotificationDetailList", godNo);
	}
	
	/**
	 * 디자인 그룹 목록 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodExtend> getDesignColorList(String godNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.getDesignColorList", godNo);
	}
	
	/**
	 * 특정 상품 디자인 그룹 목록 조회 
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodExtend> getSpecificationDesignColorList(String godNo){
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.getSpecificationDesignColorList", godNo); 
	}	
	
	/**
	 * 업체 국내 배송비 정책 목록 조회
	 * 
	 * @param comId
	 * @return
	 */
	public List<ComDmstcDlvCstPlc> getComDomesticDeliveryCostPolicy(Map<String, String> paramMap) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.getComDomesticDeliveryCostPolicy", paramMap);
	}

	/**
	 * 업체 국내 배송비 정책 목록 언어별 목록 조회
	 * 
	 * @param comId
	 * @return
	 */
	public List<ComDmstcDlvCstPlcLang> getComDomesticDeliveryCostPolicyByLangList(String comId) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.getComDomesticDeliveryCostPolicyByLangList", comId);
	}
	
	/**
	 * 상품 컬러 그룹 목록 조회
	 * 
	 * @param brndGrpId
	 * @return
	 */
	public List<GodColorExtend> getGoodsColorList(String brndGrpId) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.getGoodsColorList", brndGrpId);
	}
		
	/**
	 * 상품 옵션 등록
	 * 
	 * @param godNo
	 * @return
	 */
	public int insertGoodsOption(GodOpt godOpt) {
		return getSession1().insert("com.plgrim.ncp.biz.goods.info.insertGoodsOption", godOpt);
	}
	
	/**
	 * 상품 옵션 값 등록
	 * 
	 * @param godOptVal
	 * @return
	 */
	public int insertGoodsOptionValue(GodOptVal godOptVal) {
		return getSession1().insert("com.plgrim.ncp.biz.goods.info.insertGoodsOptionValue", godOptVal);
	}
	
	/**
	 * 상품 단품 옵션 등록
	 * 
	 * @param godItmOpt
	 * @return
	 */
	public int insertGoodsItemOption(GodItmOpt godItmOpt) {
		return getSession1().insert("com.plgrim.ncp.biz.goods.info.insertGoodsItemOption", godItmOpt);
	}
	
	/**
	 * 상품 매장 단품 재고 등록
	 * 
	 * @param erpGodNo
	 * @return
	 */
	public int insertGoodsShopItemInventory(String erpGodNo) {
		return getSession1().insert("com.plgrim.ncp.biz.goods.info.insertGoodsShopItemInventory", erpGodNo);
	}
	
	/**
	 * 상품정보 수정
	 * 
	 * @param god
	 * @return
	 */
	public int updateGoods(God god) {
		return getSession1().update("com.plgrim.ncp.biz.goods.info.updateGoods", god);
	}
	
	/**
	 * 상품 단품정보 수정
	 * 
	 * @param goditm
	 * @return
	 */
	public int updateGoodsItem(GodItm godItm) {
		return getSession1().update("com.plgrim.ncp.biz.goods.info.updateGoodsItem", godItm);
	}
	
	/**
	 * 상품 언어별 상품명 수정
	 * 
	 * @param godLangbyGodNm
	 * @return
	 */
	public int updateGoodsLangByGoodsName(GodLangbyGodNm godLangbyGodNm) {
		return getSession1().update("com.plgrim.ncp.biz.goods.info.updateGoodsLangByGoodsName", godLangbyGodNm);
	}
	
	/**
	 * 상품 판매몰 삭제
	 * 
	 * @param godNo
	 */
	public void deleteGoodsSaleMall(String godNo) {
		getSession1().delete("com.plgrim.ncp.biz.goods.info.deleteGoodsSaleMall", godNo);
	}
	
	/**
	 * 상품 고시 상세 수정
	 * 
	 * @param godNtfcDetail
	 * @return
	 */
	public int updateGoodsNotificationDetail(GodNtfcDetail godNtfcDetail) {
		return getSession1().update("com.plgrim.ncp.biz.goods.info.updateGoodsNotificationDetail", godNtfcDetail);
	}

	/**
	 * 일반상품번호로 세트상품 번호 조회
	 * 
	 * @param cpstGodNo
	 * @return
	 */
	public String getSetGoodsNumber(String cpstGodNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.info.getSetGoodsNumber", cpstGodNo);
	}
	
	/**
	 * 일반상품이 세트의 구성품인 경우 세트의 판매상태 수정
	 * 
	 * @param cpstGodNo
	 * @return
	 */
	public int updateSetGoodsStatus(String cpstGodNo) {
		return getSession1().update("com.plgrim.ncp.biz.goods.info.updateSetGoodsStatus", cpstGodNo);
	}
	
	/**
	 * 상품 상태에 따른 단품 상태 모두 수정
	 * 
	 * @param godNo
	 * @return
	 */
	public int updateGoodsItemAllStatusAccordingToGoodsStatus(String godNo) {
		return getSession1().update("com.plgrim.ncp.biz.goods.info.updateGoodsItemAllStatusAccordingToGoodsStatus", godNo);	
	}
	
	/**
	 * 세트상품 전시 가능 체크
	 * 
	 * @param godNo
	 * @return
	 */
	public int checkSetDisplayValidate(String godNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.info.checkSetDisplayValidate", godNo);
	}
	
	/**
	 * 적립금 조회
	 * 
	 * @param dspGodPrc
	 * @return
	 */
	public HashMap<String, BigDecimal> getGoodsPointSaveMoney(DspGodPrc dspGodPrc){
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.info.getGoodsPointSaveMoney", dspGodPrc);
	}
	
	/**
	 * 상품 상세 URL 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public String getViewGoodsUrl(String godNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.info.getViewGoodsUrl", godNo);
	}
	
	/**
	 * 상품 상세 redirect URL 조회
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public GoodsInfoResult getRedirectGoodsUrl(GoodsSearchDTO goodsSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.info.getRedirectGoodsUrl", goodsSearchDTO);
	}
	
	/**
	 * 연관상품 삭제
	 * 
	 * @param godRelateExtend
	 * @return
	 */
	public int deleteGoodsRelate(String godNo) {
		return getSession1().delete("com.plgrim.ncp.biz.goods.info.deleteGodRelate", godNo);
	}
	
	/**
	 * 연관상품 등록
	 * 
	 * @param godRelateExtend
	 * @return
	 */
	public int insertGoodsRelate(GodRelateExtend godRelateExtend) {
		return getSession1().insert("com.plgrim.ncp.base.insertGodRelate", godRelateExtend);
	}
	
	/**
	 * 상품 승인
	 * 
	 * @param god
	 * @return
	 */
	public int updateGoodsApproval(God god) {
		return getSession1().update("com.plgrim.ncp.biz.goods.info.updateGoodsApproval", god);
	}	
	
	/**
	 * 상품 스타일 연결 목록 수
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public long getStyleConnectionListCount(GoodsSearchDTO goodsSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.info.getStyleConnectionListCount", goodsSearchDTO);
	}

	/**
	 * 상품 스타일 연결 목록 조회
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public List<GoodsColorResult> selectStyleConnectionList(GoodsSearchDTO goodsSearchDTO){
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.selectStyleConnectionList", goodsSearchDTO);
	}
	
	/**
	 * 상품 브랜드 수 조회
	 * 
	 * @param god
	 * @return
	 */
	public int getGoodsBrandCount(List<String> godNos) {		
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.info.getGoodsBrandCount", godNos);
	}
	
	
	/**
	 * 상품 스타일 연결 등록 대상 목록 조회
	 * 
	 * @param godNos
	 * @return
	 */
	public List<GodCnncGrpGodExtend> getStyleConnectionTargetList(List<String> godNos) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.getStyleConnectionTargetList", godNos);
	}
	
	public List<God> getTempGoods() {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.getTempGoods", "");
	}
	public void updateTempNoti(GodNtfcDetail godNtfcDetail) {
		getSession1().insert("com.plgrim.ncp.biz.goods.info.updateTempNoti", godNtfcDetail);
	}
	
	public List<GoodsColorResult> searchStyleCnncView(GoodsSearchDTO goodsSearchDTO){
		return getSession1().selectList("com.plgrim.ncp.biz.goods.info.searchStyleCnncView", goodsSearchDTO);
	}
	
}
