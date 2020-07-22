package com.plgrim.ncp.cmp.product.bo;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodPrcResveRegExtend;
import com.plgrim.ncp.biz.goods.data.GoodsDTO;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsInfoResult;
import com.plgrim.ncp.framework.data.SystemPK;

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
 * Description	:	상품 가격 정보 Component
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
public interface GoodsPriceBOComponent {
	
	/**
	 * 실소가 변경예약상품정보를 등록/수정한다
	 * 
	 * @param systemPK
	 * @param godPrcResveReg
	 */
	public void insertPrcReserveGoods(SystemPK systemPK, GodPrcResveRegExtend godPrcResveReg);
	  
	/**
	 * 실소가 변경에약 승인처리한다
	 * 
	 * @param systemPK
	 * @param godPrcResveReg
	 */
	public void updatePrcReserveAprv(SystemPK systemPK, GodPrcResveRegExtend godPrcResveReg);
	
	/**
	 * 실소가 변경에약에서 수수료율 업데이트 처리
	 * 
	 * @param god
	 */
	public void updatePartmalComFeeRt(God god);
	
	/**
	 * 실소가 변경에약 삭제처리한다
	 * 
	 * @param systemPK
	 * @param godPrcResveReg
	 */
	public void deletePrcReserveAprv(SystemPK systemPK, GodPrcResveRegExtend godPrcResveReg);
	
	/**
	 * 오프라인 가격할인정보 판매가적용 처리
	 * 
	 * @param systemPK
	 * @param infSalesPrice
	 */
	//public void updateInfSalesPriceAprv(SystemPK systemPK, InfZd5sdm0011Extend infSalesPrice);
	
	/**
	 * 오프라인 가격할인정보 판매가적용 처리
	 * 
	 * @param systemPK
	 * @param goodsGridList
	 */
	public void updateInfSalesPriceAprv(SystemPK systemPK, List<GoodsDTO> goodsGridList);
	
	/**
	 * 가격정보 반영 프로시저 실행
	 * 
	 * @param params
	 */
	public void updateGoodsPriceProcedureVari(Map<String, String> params);
	
	/**
	 * 판매가적용 즉시반영 대상 프로시저 실행처리
	 * 
	 * @param godNo
	 * @return
	 */
	public int updateGoodsPriceProcedureApply(String godNo);
	
	/**
	 * 실소가 예약적용 목록을 조회한다
	 * 
	 * @param systemPK
	 * @param goodsSearchDTO
	 * @return
	 */
	public Page<GoodsInfoResult> selectUpdatePriceList(SystemPK systemPK, GoodsSearchDTO goodsSearchDTO);	
	 
	/**
	 * 실소가 변경예약 목록을 조회한다
	 * 
	 * @param systemPK
	 * @param goodsSearchDTO
	 * @return
	 */
	public Page<GoodsInfoResult> selectReservePriceList(SystemPK systemPK, GoodsSearchDTO goodsSearchDTO);
	
	/**
	 * 판매가적용 즉시반영 목록 조회
	 * 
	 * @param systemPK
	 * @return
	 */
	public List<String> selectResvePriceApplyList(SystemPK systemPK);

}
