package com.plgrim.ncp.biz.goods.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlAtchFile;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodImgExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodModelImgExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodMovi;
import com.plgrim.ncp.base.entities.datasource1.god.GodNoti;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiBrndCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiDspCtgryCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodReWhsgNtcn;
import com.plgrim.ncp.base.entities.datasource1.god.GodRelateExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResveCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrTodayGod;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnGodExtend;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmLang;
import com.plgrim.ncp.biz.display.data.InterestSearchFoDTO;
import com.plgrim.ncp.biz.display.result.GoodsInterestsGodFoResult;
import com.plgrim.ncp.biz.goods.data.GoodsContentSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsModelSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsNoticeSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsReviewSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsTagSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsModelResult;
import com.plgrim.ncp.biz.goods.result.GoodsNoticeResult;
import com.plgrim.ncp.biz.goods.result.GoodsRelatedGodResult;
import com.plgrim.ncp.biz.goods.result.GoodsReviewResult;
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
 * Description	:	상품 컨텐츠 Repository
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Repository
public class GoodsContentRepository extends AbstractRepository{
	
	/**
	 * 상품 이미지 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodImg> getGoodsImageList(String godNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.content.getGoodsImageList", godNo);
	}
	
	/**
	 * 상품 동영상 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public GodMovi getGoodsMovie(String godNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getGoodsMovie", godNo);
	}
    
    /**
     * 이미지 순번 수정
     * 
     * @param godImgExtend
     * @return
     */
    public int updateGoodsImageTurn(GodImgExtend godImgExtend) {
    	return getSession1().update("com.plgrim.ncp.biz.goods.content.updateGoodsImageTurn", godImgExtend);
    }
    
	/**
	 * 상품 연관 상품 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodRelateExtend> getGoodsRelateList(String godNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.content.getGoodsRelateList", godNo);
	}
	
	/**
	 * 태그예약 목록 수
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public long getTagReserveListCount(GoodsTagSearchDTO goodsTagSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getTagReserveListCount", goodsTagSearchDTO);
	}
	
	/**
	 * 태그예약 목록 조회
	 * 
	 * @param goodsTagSearchDTO
	 * @return
	 */
	public List<GoodsTagResult> selectTagReserveList(GoodsTagSearchDTO goodsTagSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.content.selectTagReserveList", goodsTagSearchDTO);
	}
	
	/**
	 * 태그예약 조회
	 * 
	 * @param goodsTagSearchDTO
	 * @return
	 */
	public GoodsTagResult getTagReserve(GoodsTagSearchDTO goodsTagSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getTagReserve", goodsTagSearchDTO);	
	}
	
	/**
	 * 태그예약 적용된 상품 목록 카운트
	 * 
	 * @param goodsTagSearchDTO
	 * @return
	 */
	public long getTagReserveGoodsListCount(GoodsTagSearchDTO goodsTagSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getTagReserveGoodsListCount", goodsTagSearchDTO);
	}
	
	/**
	 * 태그예약 적용된 상품 목록 검색
	 * 
	 * @param goodsTagSearchDTO
	 * @return
	 */
	public List<GoodsTagResult> getTagReserveGoodsList(GoodsTagSearchDTO goodsTagSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.content.getTagReserveGoodsList", goodsTagSearchDTO);
	}	
	
	/**
	 * 태그예약 상품정보 수정
	 * 
	 * @param godTagResveCnncEx
	 */
	public int updateTagReserveGoods(GodTagResveCnncExtend godTagResveCnncExtend) {
		return getSession1().update("com.plgrim.ncp.biz.goods.content.updateTagReserveGoods", godTagResveCnncExtend);
	}
	
	
	/**
	 * 공지사항 목록 수
	 * 
	 * @param goodsNoticeSearchDTO
	 * @return
	 */
	public long getNoticeListCount(GoodsNoticeSearchDTO goodsNoticeSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getNoticeListCount", goodsNoticeSearchDTO);
	}
	
	/**
	 * 공지사항 목록 조회
	 * 
	 * @param goodsNoticeSearchDTO
	 * @return
	 */
	public List<GoodsNoticeResult> selectNoticeList(GoodsNoticeSearchDTO goodsNoticeSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.content.selectNoticeList", goodsNoticeSearchDTO);
	}
	
	/**
	 * 공지사항 수정
	 * 
	 * @param godNoti
	 * @return
	 */
	public int updateNotice(GodNoti godNoti) {
		return getSession1().update("com.plgrim.ncp.biz.goods.content.updateNotice", godNoti);
	}
	
	/**
	 * 공지사항 수정 리스트
	 * 
	 * @param godNoti
	 * @return
	 */
	public int updateNoticeList(GodNoti godNoti) {
		return getSession1().update("com.plgrim.ncp.biz.goods.content.updateNoticeList", godNoti);
	}
	
	/**
	 * 공지사항 브랜드 연결 수정
	 * 
	 * @param godNotiBrndCnnc
	 * @return
	 */
	public int updateNoticeBrandConnected(GodNotiBrndCnnc godNotiBrndCnnc) {
		return getSession1().update("com.plgrim.ncp.biz.goods.content.updateNoticeBrandConnected", godNotiBrndCnnc);
	}
	
	/**
	 * 공지사항 상품 연결 수정
	 * 
	 * @param godNotiGodCnnc
	 * @return
	 */
	public int updateNoticeGoodsConnected(GodNotiGodCnnc godNotiGodCnnc) {
		return getSession1().update("com.plgrim.ncp.biz.goods.content.updateGoodsConnected", godNotiGodCnnc);
	}	
	
	/**
	 * 공지사항 카테고리 연결 수정
	 * 
	 * @param godNotiDspCtgryCnnc
	 * @return
	 */
	public int updateNoticeDisplayCategoryConnected(GodNotiDspCtgryCnnc godNotiDspCtgryCnnc) {
		return getSession1().update("com.plgrim.ncp.biz.goods.content.updateNoticeDisplayCategoryConnected", godNotiDspCtgryCnnc);
	}	
	
	/**
	 * 공지사항 조회
	 * 
	 * @param goodsNoticeSearchDTO
	 * @return
	 */
	public GodNotiExtend getNotice(GoodsNoticeSearchDTO goodsNoticeSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getNotice", goodsNoticeSearchDTO);
	}

	/**
	 * 공지사항 적용 목록 수
	 * 
	 * @param goodsNoticeSearchDTO
	 * @return
	 */
	public long getNoticeTargetListCount(GoodsNoticeSearchDTO goodsNoticeSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getgetNoticeTargetListCount", goodsNoticeSearchDTO);
	}
	
	/**
	 * 공지사항 적용 목록 조회
	 * 
	 * @param goodsNoticeSearchDTO
	 * @return
	 */
	public List<GoodsNoticeResult> getNoticeTargetList(GoodsNoticeSearchDTO goodsNoticeSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.content.getgetNoticeTargetList", goodsNoticeSearchDTO);
	}
	
	/**
	 * 상품번호와 연결된 공지사항 목록 모두 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodNotiExtend> getAllGoodsNoticeList(String godNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.content.getAllGoodsNoticeList", godNo);
	}

	/**
	 * 상품번호와 연결된 언어별, 몰별 공지사항 목록 조회
	 * 
	 * @param goodsContentSearchDTO
	 * @return
	 */
	public List<GodNotiExtend> getGoodsNoticeList(GoodsContentSearchDTO goodsContentSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.content.getGoodsNoticeList", goodsContentSearchDTO);
	}
	
    /**
     * 모델 목록 수
     * @param goodsModelSearchDTO
     * @return
     * @throws Exception
     */
    public long getModelListCount(GoodsModelSearchDTO goodsModelSearchDTO) {
        return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getModelListCount", goodsModelSearchDTO);
    }
    
    /**
     * 모델 목록 조회
     * @param goodsModelSearchDTO
     * @return
     * @throws Exception
     */    
    public List<GoodsModelResult> selectModelList(GoodsModelSearchDTO goodsModelSearchDTO) {
        return getSession1().selectList("com.plgrim.ncp.biz.goods.content.selectModelList", goodsModelSearchDTO);
    }    
    
    /**
     * 모델 정보 조회 
     * @param goodsModelSearchDTO
     * @return
     * @throws Exception
     */  
    public GoodsModelResult getModel(GoodsModelSearchDTO goodsModelSearchDTO) {
        return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getModel", goodsModelSearchDTO);
    }    
    
	/**
	 * 모델 이미지 등록
	 * 
	 * @param godModelImgEx
	 * @return
	 */
	public int updateModelImg(GodModelImgExtend godModelImgEx) {
	    return getSession1().insert("com.plgrim.ncp.biz.goods.content.updateModelImage", godModelImgEx);
	}
	
    /**
     * 모델 이미지 정보 수
     * 
     * @param goodsModelSearchDTO
     * @return
     */
    public long getModelImageExGridListCount(GoodsModelSearchDTO goodsModelSearchDTO) {
        return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getModelImageExGridListCount", goodsModelSearchDTO);
    }	
    
    /**
     * 모델 이미지 정보 조회
     * 
     * @param goodsModelSearchDTO
     * @return
     */
    public List<GodModelImgExtend> selectModelImageExGridList(GoodsModelSearchDTO goodsModelSearchDTO) {
    	return getSession1().selectList("com.plgrim.ncp.biz.goods.content.selectModelImageExGridList", goodsModelSearchDTO);
    }
    
    /**
     * 브랜드 모델 정보 조회
     * 
     * @param goodsModelSearchDTO
     * @return
     */
    public GodModelImgExtend getBrandModel(GoodsModelSearchDTO goodsModelSearchDTO) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getModelImageExList", goodsModelSearchDTO);
    }
    
    /**
     * 상품 리뷰 목록 수
     * 
     * @param goodsReviewSearchDTO
     * @return
     */
    public long getReviewListCount(GoodsReviewSearchDTO goodsReviewSearchDTO) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getReviewListCount", goodsReviewSearchDTO);
    }
    
    /**
     * 상품 리뷰 목록 조회
     * 
     * @param goodsReviewSearchDTO
     * @return
     */
    public List<GodGodEvlExtend> selectReviewList(GoodsReviewSearchDTO goodsReviewSearchDTO) {
    	return getSession1().selectList("com.plgrim.ncp.biz.goods.content.selectReviewList", goodsReviewSearchDTO);
    }
    
    /**
     * 상품 리뷰 엑셀 목록 조회(BO)
     * 
     * @param goodsReviewSearchDTO
     * @return
     */
    public List<Map<String, Object>> selectReviewListForExcel(GoodsReviewSearchDTO goodsReviewSearchDTO) {
    	return getSession1().selectList("com.plgrim.ncp.biz.goods.content.selectReviewListForExcel", goodsReviewSearchDTO);
    }
    
    /**
     * 상품 리뷰 첨부파일 목록 조회
     * 
     * @param goodsReviewSearchDTO
     * @return
     */
    public List<GodGodEvlAtchFile> getGodGodEvlAtchFileList(GoodsReviewSearchDTO goodsReviewSearchDTO) {
    	return getSession1().selectList("com.plgrim.ncp.biz.goods.content.getGodGodEvlAtchFileList", goodsReviewSearchDTO);	
    }
    
    /**
     * 상품리뷰 평점 조회
     * 
     * @param goodsReviewSearchDTO
     * @return
     */
    public GodGodEvlExtend getReviewAverageScore(GoodsReviewSearchDTO goodsReviewSearchDTO) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getReviewAverageScore", goodsReviewSearchDTO);	
    }

    /**
     * 상품 쿠폰 프로모션 목록 조회
     * 
     * @param goodsContentSearchDTO
     * @return
     */
    public List<PrmCpnGodExtend> getGoodsCouponPromotionList(GoodsContentSearchDTO goodsContentSearchDTO){
    	return getSession1().selectList("com.plgrim.ncp.biz.goods.content.getGoodsCouponPromotionList", goodsContentSearchDTO);
    }
    
    /**
     * 할인 혜택이 가장 큰 상품 쿠폰 프로모션 조회
     * 
     * @param goodsContentSearchDTO
     * @return
     */
    public PrmCpnGodExtend getGoodsMostDiscountCouponPromotion(GoodsContentSearchDTO goodsContentSearchDTO){
    	return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getGoodsMostDiscountCouponPromotion", goodsContentSearchDTO);
    }
    
    /**
     * 신규가입 쿠폰 조회
     * 
     * @param goodsContentSearchDTO
     * @return
     */
    public PrmCpnGodExtend getGoodsCouponNewMember(GoodsContentSearchDTO goodsContentSearchDTO){
    	return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getGoodsCouponNewMember", goodsContentSearchDTO);
    }
    
    /**
     * 상품 이벤트 목록 조회
     * 
     * @param godNo
     * @return
     */
    public List<Evt> getGoodsEventList(GoodsContentSearchDTO goodsContentSearchDTO) {
    	return getSession1().selectList("com.plgrim.ncp.biz.goods.content.getGoodsEventList", goodsContentSearchDTO);
    }
    
    /**
     * 상품 카드 이벤트 조회
     * 
     * @param godNo
     * @return
     */
    public Evt getGoodsCardEvent(GoodsContentSearchDTO goodsContentSearchDTO) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getGoodsCardEvent", goodsContentSearchDTO);
    }    
    
    /**
     * 상품 기획전 목록 조회
     * 
     * @param goodsContentSearchDTO
     * @return
     */
    public List<DspPromt> getGoodsPromotionList(GoodsContentSearchDTO goodsContentSearchDTO) {
    	return getSession1().selectList("com.plgrim.ncp.biz.goods.content.getGoodsPromotionList", goodsContentSearchDTO);
    }

    /**
     * 사은품 프로모션 확인
     * 
     * @param goodsContentSearchDTO
     * @return
     */
    public Integer isGiftPromotion(GoodsContentSearchDTO goodsContentSearchDTO) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.isGiftPromotion", goodsContentSearchDTO);
    }
    
    /**
     * 상품 프로모션 언어별 목록 조회
     * 
     * @param goodsContentSearchDTO
     * @return
     */
    public List<PrmLang> getGoodsPromotionLangList(GoodsContentSearchDTO goodsContentSearchDTO) {
    	return getSession1().selectList("com.plgrim.ncp.biz.goods.content.getGoodsPromotionLangList", goodsContentSearchDTO);
    }
    
	/**
	 * Front 최근 본 상품 등록시 중복여부 체크
	 *
	 * @param mbrTodayGod
	 * @return the int
	 */
	public String isDuplicateTodayGod(MbrTodayGod mbrTodayGod) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.isDuplicateTodayGod", mbrTodayGod);
	}
    
	/**
	 * Front 최근 본 상품 등록.
	 * 
	 * @param mbrTodayGod MbrTodayGod entities
	 */
	public void insertTodayGod(MbrTodayGod mbrTodayGod) {
		getSession1().insert("com.plgrim.ncp.biz.goods.content.insertTodayGod", mbrTodayGod);
	}	
	
	/**
	 * Front 최근 본 상품 조회날짜 업데이트
	 *
	 * @param mbrTodayGod [설명]
	 * @throws Exception the exception
	 */
	public void updateTodayGodInqireDt(MbrTodayGod mbrTodayGod){
		getSession1().update("com.plgrim.ncp.biz.goods.content.updateTodayGodInqireDt", mbrTodayGod);
	}	
	
	/**
	 * Front 최근 본 상품 조회 by Cookie
	 *
	 * @param mbrTodayGod [설명]
	 * @throws Exception the exception
	 */
	public List<GoodsInterestsGodFoResult> selectTodayCookies(InterestSearchFoDTO interestSearchFoDTO){
		return getSession1().selectList("com.plgrim.ncp.biz.goods.content.selectTodayCookies", interestSearchFoDTO);
	}	
	
	public List<GoodsInterestsGodFoResult> selectTodayCookies2(InterestSearchFoDTO interestSearchFoDTO){
		return getSession1().selectList("com.plgrim.ncp.biz.goods.content.selectTodayCookies2", interestSearchFoDTO);
	}	
	
	/**
	 * 재입고 알림 저장
	 * 
	 * @param godReWhsgNtcn
	 * @return
	 */
	public void addGoodsInform(GodReWhsgNtcn godReWhsgNtcn) {
		getSession1().insert("com.plgrim.ncp.biz.goods.content.addGoodsInform", godReWhsgNtcn);
	}
	
	/**
	 * 재입고 알림 저장
	 * 
	 * @param godReWhsgNtcn
	 * @return
	 */
	public List<GoodsRelatedGodResult> getRelatedGoods(GoodsContentSearchDTO contentSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.content.getRelatedGoods", contentSearchDTO);
	}
	
	/**
	 * 연관상품 노출
	 * 
	 * @param getNewRelatedGoods
	 * @return
	 */
	public List<GoodsRelatedGodResult> getNewRelatedGoods(GoodsContentSearchDTO contentSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.content.getNewRelatedGoods", contentSearchDTO);
	}
	
	public int deleteGodImg(String godNo) {
		return getSession1().delete("com.plgrim.ncp.biz.goods.content.deleteGodImgDetail", godNo);
	}

	public GodGodEvlExtend getDsgnGrpCnnGoods(GodGodEvlExtend godGodEvlExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getDsgnGrpCnnGoods", godGodEvlExtend);
	}
	
	public GodGodEvlExtend getDsgnGrpCnnKey(GodGodEvlExtend godGodEvlExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.getDsgnGrpCnnKey", godGodEvlExtend);
	}

	public void insertDsgnGrpCnnGoods(GodGodEvlExtend godGodEvlExtend) {
		getSession1().insert("com.plgrim.ncp.biz.goods.content.insertDsgnGrpCnnGoods", godGodEvlExtend);
	}

	public long searchDsgnGrpsGridListCount(GoodsSearchDTO goodsSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.content.searchDsgnGrpsGridListCount", goodsSearchDTO);
	}

	public List<GoodsReviewResult> searchDsgnGrpsGridList(GoodsSearchDTO goodsSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.content.searchDsgnGrpsGridList", goodsSearchDTO);

	}

	public int deleteDsgnGrpGrid(GodGodEvlExtend godGodEvlEx) {
		return getSession1().delete("com.plgrim.ncp.biz.goods.content.deleteDsgnGrpGrid", godGodEvlEx);

	}
	
	public List<GoodsReviewResult> searchDsgnGrpsView(GoodsSearchDTO goodsSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.goods.content.searchDsgnGrpsView", goodsSearchDTO);

	}
}
