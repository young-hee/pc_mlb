package com.plgrim.ncp.cmp.product.bo;

import java.util.List;

import org.springframework.data.domain.Page;

import com.plgrim.ncp.base.entities.datasource1.god.GodModelImgExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodRelateExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResveCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResveExtend;
import com.plgrim.ncp.biz.goods.data.GoodsContentDTO;
import com.plgrim.ncp.biz.goods.data.GoodsDsgnGrpExcelDTO;
import com.plgrim.ncp.biz.goods.data.GoodsModelImageDTO;
import com.plgrim.ncp.biz.goods.data.GoodsModelSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsNoticeDTO;
import com.plgrim.ncp.biz.goods.data.GoodsNoticeGridDTO;
import com.plgrim.ncp.biz.goods.data.GoodsNoticeSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsSeriesLineDTO;
import com.plgrim.ncp.biz.goods.data.GoodsSeriesLineSerachDTO;
import com.plgrim.ncp.biz.goods.data.GoodsTagSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsModelResult;
import com.plgrim.ncp.biz.goods.result.GoodsNoticeResult;
import com.plgrim.ncp.biz.goods.result.GoodsResult;
import com.plgrim.ncp.biz.goods.result.GoodsReviewResult;
import com.plgrim.ncp.biz.goods.result.GoodsSeriesLineResult;
import com.plgrim.ncp.biz.goods.result.GoodsTagResult;

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
 * Description	:	상품 컨텐츠 정보 Component
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
public interface GoodsContentBOComponent {
	
	/**
	 * 태그예약 목록 검색
	 * 
	 * @param goodsTagSearchDTO
	 * @return
	 */
	public Page<GoodsTagResult> searchTagReserveList(GoodsTagSearchDTO goodsTagSearchDTO);
	
	/**
	 * 태그예약 등록처리
	 * 
	 * @param godTagResveExtend
	 * @return
	 */
	public GoodsTagResult insertTagReserve(GodTagResveExtend godTagResveExtend);
	
	/**
	 * 태그예약 수정처리
	 * 
	 * @param godTagResveExtend
	 * @return
	 */
	public GoodsTagResult updateTagReserve(GodTagResveExtend godTagResveExtend);
	
	/**
	 * 태그예약 조회
	 * 
	 * @param goodsTagSearchDTO
	 * @return
	 */
	public GoodsTagResult getTagReserve(GoodsTagSearchDTO goodsTagSearchDTO);	
	
	/**
	 * 태그예약 적용된 상품 조회
	 * 
	 * @param goodsTagSearchDTO
	 * @return
	 */
	public Page<GoodsTagResult> getTagReserveGoodsList(GoodsTagSearchDTO goodsTagSearchDTO);
		
	/**
	 * 태그예약 적용된 상품 수정
	 * 
	 * @param godTagResveCnncExtend
	 * @return
	 */
	public GoodsTagResult updateTagReserveGoods(GodTagResveCnncExtend godTagResveCnncExtend);
	
	/**
	 * 태그예약 적용된 상품 삭제
	 * 
	 * @param godTagResveCnncExtend
	 * @return
	 */
	public GoodsTagResult deleteTagReserveGoods(GodTagResveCnncExtend godTagResveCnncExtend);	
		
	/**
	 * 공지사항 목록 검색
	 * 
	 * @param goodsNoticeSearchDTO
	 * @return
	 */
	public Page<GoodsNoticeResult> searchNoticeList(GoodsNoticeSearchDTO goodsNoticeSearchDTO);
	
	/**
	 * 공지사항 목록 수정
	 * 
	 * @param goodsNoticeGridDTO
	 */
	public GoodsNoticeResult updateNoticeList(GoodsNoticeGridDTO goodsNoticeGridDTO);
		
	/**
	 * 공지사항 등록 처리
	 * 
	 * @param goodsNoticeDTO
	 */
	public GoodsNoticeResult insertNotice(GoodsNoticeDTO goodsNoticeDTO) throws Exception;
	
	/**
	 * 공지사항 조회
	 * 
	 * @param goodsNoticeSearchDTO
	 * @return
	 */
	public GoodsNoticeResult getNotice(GoodsNoticeSearchDTO goodsNoticeSearchDTO);
	
	/**
	 * 공지사항 적용 목록 조회
	 * 
	 * @param goodsNoticeSearchDTO
	 * @return
	 */
	public Page<GoodsNoticeResult> getNoticeTargetList(GoodsNoticeSearchDTO goodsNoticeSearchDTO);
	
	/**
	 * 공지사항 적용 목록 삭제
	 * 
	 * @param goodsNoticeDTO
	 * @return
	 */
	public GoodsNoticeResult deleteNoticeTarget(GoodsNoticeDTO goodsNoticeDTO);
	
	/**
	 * 공지사항 수정 처리
	 * 
	 * @param goodsNoticeDTO
	 */
	public GoodsNoticeResult updateNotice(GoodsNoticeDTO goodsNoticeDTO) throws Exception;
	
	/**
	 * 시리즈/라인 목록 검색
	 * 
	 * @param seriesSerachDTO
	 * @return
	 */
	public Page<GoodsSeriesLineResult> searchSeriesLineList(GoodsSeriesLineSerachDTO seriesSerachDTO);
	
	/**
	 * 시리즈/라인 등록/수정 처리
	 * 
	 * @param seriesDTO
	 */
	public void updateSeriesLine(List<GoodsSeriesLineDTO> seriesDTO);
	
	/**
	 * 모델 목록 검색
	 * 
	 * @param goodsModelSearchDTO
	 * @return
	 */
	public Page<GoodsModelResult> searchModelList(GoodsModelSearchDTO goodsModelSearchDTO);
		
	/**
	 * 모델 조회
	 * 
	 * @param goodsModelSearchDTO
	 * @return
	 */
	public GoodsModelResult getModel(GoodsModelSearchDTO goodsModelSearchDTO);
	
	/**
	 * 모델 등록
	 * 
	 * @param goodsModelImageDTO
	 * @return
	 */
	public GoodsModelResult insertModel(GoodsModelImageDTO goodsModelImageDTO);
	
	/**
	 * 모델 검색
	 * 
	 * @param goodsModelSearchDTO
	 * @return
	 */
	public Page<GodModelImgExtend> searchModel(GoodsModelSearchDTO goodsModelSearchDTO);
	
	/**
	 * 브랜드 모델 조회
	 * 
	 * @param goodsModelSearchDTO
	 * @return
	 */
	public GodModelImgExtend getBrandModel(GoodsModelSearchDTO goodsModelSearchDTO);
	
	/**
	 * 상품 메모 등록
	 * 
	 * @param csoCnsltMemo
	 * @return
	 */
	public GoodsResult insertGoodsMemo(GoodsContentDTO goodsContentDTO, String loginId);
	
	/**
	 * 상품 이미지 ZIP 업로드 등록
	 * 
	 * @param targetPath
	 * @return
	 */
	public GoodsResult insertGoodsImageZipUpload(String targetPath);
	
	/**
	 * 상품 연관 상품 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodRelateExtend> getGoodsRelateList(String godNo);

	/**
	 * 상품평 연결 검색
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public Page<GoodsReviewResult> searchDsgnGrpsGridList(GoodsSearchDTO goodsSearchDTO);

	/**
	 * 상품평 연결 등록
	 * 
	 * @param list
	 * @return
	 */
	public List<GoodsDsgnGrpExcelDTO> insertDsgnGrpCnnGoods(List<GoodsDsgnGrpExcelDTO> list);
	
	/**
	 * 상품평 연결 삭제
	 * 
	 * @param gridList
	 */
	public void deleteDsgnGrpList(List<GoodsSearchDTO> gridList);
	
	
	public List<GoodsDsgnGrpExcelDTO> addDsgnGrpNos(GoodsSearchDTO goodsSearchDTO);
	
	public List<GoodsReviewResult> searchDsgnGrpsView(GoodsSearchDTO goodsSearchDTO);
	
}
