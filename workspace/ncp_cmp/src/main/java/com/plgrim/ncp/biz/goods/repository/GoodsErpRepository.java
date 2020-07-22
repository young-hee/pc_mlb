package com.plgrim.ncp.biz.goods.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndrDscr;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndrImg;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodFitLktbExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodSizeLktb;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodSizeLktbPom;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodSizeLktbPomExtend;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsErpResult;

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
 * Description	:	상품 ERP Repository
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Repository
public class GoodsErpRepository extends AbstractRepository{

	/**
	 * ERP 상품 정보 조회
	 * 
	 * @param erpGodNo
	 * @return
	 */
	public InfGodItmExtend getErpGoods(String erpGodNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.erp.getErpGoods", erpGodNo);
	}
	
	/**
	 * ERP 상품 단품 조회
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public List<GodItmExtend> getErpGoodsItem(GoodsSearchDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.erp.getErpGoodsItem", searchDTO);
	}
	
	/**
	 * 세탁 코드 설명 목록 조회
	 * 
	 * @param lndrCd
	 * @return
	 */
	public List<GodLndrDscr> getGoodsLaundryDescriptionList(String lndrCd) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.erp.getGoodsLaundryDescriptionList", lndrCd);
	}
	
	/**
	 * 세탁 코드 이미지 조회 
	 * 
	 * @param lndrCd
	 * @return
	 */
	public List<GodLndrImg> getGoodsLaundryImageList(GoodsSearchDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.erp.getGoodsLaundryImageList", searchDTO);
	}	
	
	/**
	 * ERP상품목록 수
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public long getErpGoodsListCount(GoodsSearchDTO goodsSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.erp.getErpGoodsListCount", goodsSearchDTO);
	}
	
	/**
	 * ERP상품목록 조회
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public List<GoodsErpResult> selectErpGoodsList(GoodsSearchDTO goodsSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.erp.selectErpGoodsList", goodsSearchDTO);
	}
	
	/**
	 * ERP 상품정보 엑셀 템플릿 생성 데이터 조회
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public List<Map<String, String>> getErpGoodsUploadExcelTemplate(GoodsSearchDTO goodsSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.erp.getErpGoodsUploadExcelTemplate", goodsSearchDTO);
	}
	
	/**
	 * ERP 상품의 사이즈조견표 POM 정보 조회
	 * 
	 * @param dsgnGrpNo
	 * @return
	 */
	public List<InfGodSizeLktbPom> getErpGoodsSizeLktbPomList(String dsgnGrpNo){
		return getSession1().selectList("com.plgrim.ncp.biz.goods.erp.getErpGoodsSizeLktbPomList", dsgnGrpNo);
	}
	
	/**
	 * ERP 상품의 사이즈조견표 POM 정보 조회
	 * 	- 조견표 이미지 영역 추가
	 * 
	 * @param dsgnGrpNo
	 * @return
	 */
	public List<InfGodSizeLktbPomExtend> getErpGoodsSizeLktbPomExList(InfGodSizeLktb sizeParam){
		return getSession1().selectList("com.plgrim.ncp.biz.goods.erp.getErpGoodsSizeLktbPomExList", sizeParam);
	}
	
	/**
	 * ERP 상품의 사이즈조견표 정보 조회
	 * 
	 * @param param
	 * @return
	 */
	public List<InfGodSizeLktb> getErpGoodsSizeLktbList(InfGodSizeLktb sizeParam){
		return getSession1().selectList("com.plgrim.ncp.biz.goods.erp.getErpGoodsSizeLktbList", sizeParam);
	}
	
	/**
	 * MLB/MLBK 신발, 모자 상품의 사이즈조견표 조회
	 * 	- 조견표 이미지 영역 추가
	 * 
	 * @param dsgnGrpNo
	 * @return
	 */
	public List<Map<String, String>> getMlbGoodsSizeLktbExList(Map<String, String> paramMap){
		return getSession1().selectList("com.plgrim.ncp.biz.goods.erp.getMlbGoodsSizeLktbExList", paramMap);
	}

	/**
	 * ERP 핏 조견표 정보 조회시 필요한  사이즈 조견표 POM 코드 존재 여부 체크
	 * 
	 * @param paramMap
	 * @return
	 */
	public int isExistFitGuideCheckSizePomCd(Map<String, String> paramMap){
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.erp.isExistFitGuideCheckSizePomCd", paramMap);
	}
		
	/**
	 * ERP 상품 핏조견표 정보 조회
	 * 
	 * @param fitParam
	 * @return
	 */
	public InfGodFitLktbExtend getGoodsErpFitGuide(InfGodFitLktbExtend fitParam) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.erp.getGoodsErpFitGuide", fitParam);
	}
	
}
