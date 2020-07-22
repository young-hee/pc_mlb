package com.plgrim.ncp.cmp.product.bo;

import java.util.List;

import org.springframework.data.domain.Page;

import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.god.GodColorExtend;
import com.plgrim.ncp.biz.goods.data.GoodsDTO;
import com.plgrim.ncp.biz.goods.data.GoodsDsgnGrpExcelDTO;
import com.plgrim.ncp.biz.goods.data.GoodsExcelDTO;
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
 * Description	:	상품 정보 Component
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
public interface GoodsBOComponent {
	
	/**
	 * 상품 정보 목록 검색
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public Page<GoodsInfoResult> searchGoodsList(GoodsSearchDTO goodsSearchDTO);
	
	/**
	 * 상품정보 목록 수정
	 * 
	 * @param goodsDTO
	 * @return
	 */
	public GoodsInfoResult updateGoodsList(GoodsDTO goodsDTO);
	
	
	/**
	 * 상품등록처리
	 * 
	 * @param goodsDTO
	 * @return
	 */
	public GoodsInfoResult insertGoods(GoodsDTO goodsDTO);
	
	
	/**
	 * 상품 상세정보 조회
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public GoodsInfoResult getGoods(GoodsSearchDTO goodsSearchDTO);
	
	
	/**
	 * 상품수정처리
	 * 
	 * @param goodsDTO
	 * @return
	 */
	public GoodsInfoResult updateGoods(GoodsDTO goodsDTO);
	
	
	/**
	 * 상품 대량등록처리
	 * 
	 * @param list
	 * @return
	 */
	public List<GoodsExcelDTO> insertMassGoods(List<GoodsExcelDTO> list);
	
	
	/**
	 * 상품 대량수정처리
	 * 
	 * @param list
	 * @return
	 */
	public List<GoodsExcelDTO> updateMassGoods(List<GoodsExcelDTO> list);
	
	
	/**
	 * 상품 컬러 목록 조회
	 * 
	 * @param brndGrpId
	 * @return
	 */
	public List<GodColorExtend> getGodColorList(String brndGrpId);
	
	
	/**
	 * 상품 업체 국내 배송정책 조회
	 * 
	 * @param comId
	 * @param mallId
	 * @return
	 */
	public List<ComDmstcDlvCstPlc> getComDomesticDeliveryCostPolicy(String comId, String mallId);
	
	
	public void tempUpdateSize();
	
	
	/**
	 * 상품 스타일 연결 검색
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public Page<GoodsColorResult> searchStyleConnectionList(GoodsSearchDTO goodsSearchDTO);
	
	
	/**
	 * 상품 스타일 연결 삭제
	 * 
	 * @param gridList
	 */
	public void deleteStyleConnectionList(List<GoodsDTO> gridList);
	
	
	/**
	 * 상품스타일 연결 대량등록처리
	 * 
	 * @param list
	 * @return
	 */
	public List<GoodsExcelDTO> insertMassGoodsStyleConnection(List<GoodsExcelDTO> list);
	
	
	public List<GoodsColorResult> searchStyleCnncView(GoodsSearchDTO goodsSearchDTO);
	
	public List<GoodsExcelDTO> addStyleCnncs(GoodsSearchDTO goodsSearchDTO);
}
