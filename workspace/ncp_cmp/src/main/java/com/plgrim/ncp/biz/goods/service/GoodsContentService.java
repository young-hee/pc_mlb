package com.plgrim.ncp.biz.goods.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvl;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlAtchFile;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodImgExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyTagResve;
import com.plgrim.ncp.base.entities.datasource1.god.GodModel;
import com.plgrim.ncp.base.entities.datasource1.god.GodModelImgExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodModelSize;
import com.plgrim.ncp.base.entities.datasource1.god.GodMovi;
import com.plgrim.ncp.base.entities.datasource1.god.GodNoti;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiBrndCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiDspCtgryCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodReWhsgNtcn;
import com.plgrim.ncp.base.entities.datasource1.god.GodRelate;
import com.plgrim.ncp.base.entities.datasource1.god.GodRelateExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResve;
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResveCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResveCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResveExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrTodayGod;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnGodExtend;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmLang;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.repository.god.GodLangbyTagResveRepository;
import com.plgrim.ncp.base.repository.god.GodTagResveCnncRepository;
import com.plgrim.ncp.base.repository.god.GodTagResveRepository;
import com.plgrim.ncp.base.repository.prm.PrmLangRepository;
import com.plgrim.ncp.biz.display.data.InterestSearchFoDTO;
import com.plgrim.ncp.biz.display.result.GoodsInterestsGodFoResult;
import com.plgrim.ncp.biz.goods.data.GoodsContentSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsModelImageDTO;
import com.plgrim.ncp.biz.goods.data.GoodsModelSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsNoticeSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsReviewSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsTagSearchDTO;
import com.plgrim.ncp.biz.goods.exception.GoodsImageUploadFailException;
import com.plgrim.ncp.biz.goods.repository.GoodsContentRepository;
import com.plgrim.ncp.biz.goods.result.GoodsInfoResult;
import com.plgrim.ncp.biz.goods.result.GoodsModelResult;
import com.plgrim.ncp.biz.goods.result.GoodsNoticeResult;
import com.plgrim.ncp.biz.goods.result.GoodsRelatedGodResult;
import com.plgrim.ncp.biz.goods.result.GoodsReviewResult;
import com.plgrim.ncp.biz.goods.result.GoodsTagResult;
import com.plgrim.ncp.framework.cloud.CloudFileSystem;
import com.plgrim.ncp.framework.cloud.aws.S3FileSystem;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.utils.Utils;

import lombok.extern.slf4j.Slf4j;

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
 * Description	:	상품 컨텐츠 Service
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Slf4j
@Service
public class GoodsContentService extends GoodsService{

	@Autowired
	private GoodsContentRepository goodsContentRepository;
	
	@Autowired
	private GodTagResveRepository godTagResveRepository;
	
	@Autowired
	private GodTagResveCnncRepository godTagResveCnncRepository;
	
	@Autowired
	private GodLangbyTagResveRepository godLangbyTagResveRepository;
	
	@Autowired
	private PrmLangRepository prmLangRepository;
	
	@Autowired
	private S3FileSystem s3FileSystem;
	
    @Value("${ncp_base.cloud.bucketName}")
    private String bucketName;
    
    @Value("${ncp_web_bo.cloud.bucket.pec.temp.folder.path}")
	private String imageTempPath;
    
    @Value("${ncp_base.image.goods.modelcut.upload.path}")
    private String modelImgUpPath;
	
    @Value("${ncp_web_bo.image.goods.modelcut.http.path}")
    private String modelImgWebPath;
	
    String getSaveImageUrlPath(String mallId) {
    	return getConfigService().getProperty( "ncp_web_bo.cloud."+mallId+".bucket.image.folder.path" );
    }
    
    String getUploadImageTempPath() {
    	return imageTempPath + DateService.getStringCurrentToday()+ "/";
    }
    
    
	/**
	 * 상품 이미지 등록
	 * 
	 * @param godImgExtend
	 * @return
	 */
	public GoodsInfoResult insertGoodsImage(GodImgExtend godImgExtend) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			GodImg godImg = new GodImg();
			BeanUtils.copyProperties(godImgExtend, godImg);
			
			//	임시 파일 유무 체크
			if(StringService.isNotEmpty(godImgExtend.getTempFileName())&& !"Y".equals(godImgExtend.getImgModYn())) {
				godImg.setImgUrl(this.moveImageForCloudFront(godImgExtend));
			}else{
				godImg.setImgUrl(godImgExtend.getTempFileName());
			}
			 
			//	등록
			if(Utils.empty(godImgRepository.selectGodImg(godImg))) {
				godImgRepository.insertGodImg(godImg);
			}else {
				if(String.valueOf(GoodsEnum.YES).equals(godImg.getImgUseYn())) {
					//	수정	
					godImgRepository.updateGodImg(godImg);										
				}else {
					//	삭제
					godImgRepository.deleteGodImg(godImg);
					
					//	이미지 순서 재 조정
					this.updateGoodsImageTurn(godImg.getGodNo(), godImg.getUdterId());
				}
			}
			
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();		
		}		
		return result;
	}
	
	public int deleteGodImg(String godNo) {
 
		return goodsContentRepository.deleteGodImg(godNo);
	}
	
	
	/**
	 * 상품 동영상 등록
	 * 
	 * @param godMovi
	 * @return
	 */
	public GoodsInfoResult insertGoodsMovie(GodMovi godMovi) {
		GoodsInfoResult result = new GoodsInfoResult();		
		try {
			//	등록
			if(Utils.empty(godMoviRepository.selectGodMovi(godMovi))) {				
				godMoviRepository.insertGodMovi(godMovi);
			}else {
			//	수정	
				godMoviRepository.updateGodMovi(godMovi);
			}			
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();	
		}
		
		return result;
	}
	
	/**
	 * 상품 이미지 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodImg> getGoodsImageList(String godNo) {
		List<GodImg> imageList = goodsContentRepository.getGoodsImageList(godNo);
		return imageList;
	}

	/**
	 * 상품 동영상 조회
	 * 
	 * @param godMovi
	 * @return
	 */
	public GodMovi getGoodsMovie(String godNo) {
		GodMovi movi = goodsContentRepository.getGoodsMovie(godNo);
		return movi;
	}
	
	/**
	 * 상품 연관 등록
	 * 
	 * @param godRelate
	 * @return
	 */
	public GoodsInfoResult insertGoodsRelate(GodRelate godRelate) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			godRelateRepository.insertGodRelate(godRelate);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();
		}
		return result;
	}	
	
	/**
	 * 상품 연관 상품 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodRelateExtend> getGoodsRelateList(String godNo) {
		List<GodRelateExtend> godRelateList = new ArrayList<GodRelateExtend>();
		godRelateList = goodsContentRepository.getGoodsRelateList(godNo);
		return godRelateList;
	}
	
	/**
	 * 태그예약 목록 검색
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public Page<GoodsTagResult> searchTagReserveList(GoodsTagSearchDTO goodsTagSearchDTO) {
		try {			
			// 페이지 인덱스 셋팅
			PageParam pageParam = getPageService().buildPageParam(goodsTagSearchDTO.getGPageNo(), goodsTagSearchDTO.getGPageSize());
			RepositoryHelper.makePageEntityIndex(goodsTagSearchDTO, pageParam);

			// 목록 건수 조회
			long totalRow = goodsContentRepository.getTagReserveListCount(goodsTagSearchDTO);
			
			// 목록 건수 조회			
			List<GoodsTagResult> results = new ArrayList<GoodsTagResult>();
			
			if(totalRow > 0) {
				results = goodsContentRepository.selectTagReserveList(goodsTagSearchDTO);
			}
		
			return new PageImpl<GoodsTagResult>(results, pageParam.getPageable(), totalRow);
		} catch (Exception e) {
			super.stackTrace(e);
			throw new RuntimeException();
		}
	}
	
	/**
	 * 태그예약 조회
	 * 
	 * @param goodsTagSearchDTO
	 * @return
	 */
	public GoodsTagResult getTagReserve(GoodsTagSearchDTO goodsTagSearchDTO) {
		return goodsContentRepository.getTagReserve(goodsTagSearchDTO);
	}
		
	/**
	 * 태그예약 수정
	 * 
	 * @param godTagResveExtend
	 * @return
	 */
	public GoodsTagResult updateTagReserve(GodTagResveExtend godTagResveExtend) {
		GoodsTagResult result = new GoodsTagResult();
		try {			
			GodTagResve godTagResve = new GodTagResve();		
			BeanUtils.copyProperties(godTagResveExtend, godTagResve);			
			
			if(StringService.isNotEmpty(godTagResveExtend.getResveBegDtStr())) {			
				String resveBegDt = godTagResveExtend.getResveBegDtStr() + " " 
									+ StringService.nvl(godTagResveExtend.getStartTermHh(), "00") + ":" 
									+ StringService.nvl(godTagResveExtend.getStartTermMm(), "00");
				
				godTagResve.setResveBegDt(DateService.createDate(resveBegDt, "yyyy-MM-dd HH:mm"));
			}
			if(StringService.isNotEmpty(godTagResveExtend.getResveEndDtStr())) {
				String resveEndDt = godTagResveExtend.getResveEndDtStr() + " " 
									+ StringService.nvl(godTagResveExtend.getEndTermHh(), "23") + ":" 
									+ StringService.nvl(godTagResveExtend.getEndTermMm(), "59");			
				godTagResve.setResveEndDt(DateService.createDate(resveEndDt, "yyyy-MM-dd HH:mm"));
			}
			
			int udtCnt = godTagResveRepository.updateGodTagResve(godTagResve);
			
			if(udtCnt > 0) {				
				result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
				// 언어별 태그명 수정
				updateLangByTagReserve(godTagResveExtend);				
			}else {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			}
			
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();
		}
		
		return result;
	}
		
	/**
	 * 태그예약 등록
	 * 
	 * @param godTagResveExtend
	 * @return
	 */
	public GoodsTagResult insertTagReserve(GodTagResveExtend godTagResveExtend) {
		GoodsTagResult result = new GoodsTagResult();
		long tagResveSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_GOD_TAG_RESVE", DatabaseType.ORACLE);
		godTagResveExtend.setTagResveSn(tagResveSn);		
		try {
			GodTagResve godTagResve = new GodTagResve();
			BeanUtils.copyProperties(godTagResveExtend, godTagResve);
			
			if(StringService.isNotEmpty(godTagResveExtend.getResveBegDtStr())) {			
				String resveBegDt = godTagResveExtend.getResveBegDtStr() + " " 
									+ StringService.nvl(godTagResveExtend.getStartTermHh(), "00") + ":" 
									+ StringService.nvl(godTagResveExtend.getStartTermMm(), "00");
				
				godTagResve.setResveBegDt(DateService.createDate(resveBegDt, "yyyy-MM-dd HH:mm"));
			}
			if(StringService.isNotEmpty(godTagResveExtend.getResveEndDtStr())) {
				String resveEndDt = godTagResveExtend.getResveEndDtStr() + " " 
									+ StringService.nvl(godTagResveExtend.getEndTermHh(), "23") + ":" 
									+ StringService.nvl(godTagResveExtend.getEndTermMm(), "59");			
				godTagResve.setResveEndDt(DateService.createDate(resveEndDt, "yyyy-MM-dd HH:mm"));
			}
						
			godTagResveRepository.insertGodTagResve(godTagResve);
			
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
			result.setGodTagResveEx(godTagResveExtend);

			// 언어별 태그명 등록
			insertLangByTagResrve(godTagResveExtend);
			
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();
		}
		return result;
	}
	
	/**
	 * 태그예약 적용된 상품 목록 검색
	 * 
	 * @param goodsTagSearchDTO
	 * @return
	 */
	public Page<GoodsTagResult> getTagReserveGoodsList(GoodsTagSearchDTO goodsTagSearchDTO) {
		try {
			// 페이지 인덱스 셋팅
			PageParam pageParam = getPageService().buildPageParam(goodsTagSearchDTO.getGPageNo(), goodsTagSearchDTO.getGPageSize());
			RepositoryHelper.makePageEntityIndex(goodsTagSearchDTO, pageParam);
	
			// 목록 건수 조회
			long totalRow = goodsContentRepository.getTagReserveGoodsListCount(goodsTagSearchDTO);
	
			// 목록 조회
			List<GoodsTagResult> results = new ArrayList<GoodsTagResult>();
			if (totalRow > 0) {
				results = goodsContentRepository.getTagReserveGoodsList(goodsTagSearchDTO);
			}	
			return new PageImpl<GoodsTagResult>(results, pageParam.getPageable(), totalRow);
		}catch (Exception e){
			super.stackTrace(e);
			throw new RuntimeException();
		}
	}	
	
	/**
	 * 태그예약 적용된 상품 목록 수정
	 * Merge문 사용
	 * 
	 * @param godTagResveCnncExtend
	 * @return
	 */
	public GoodsTagResult updateTagReserveGoods (GodTagResveCnncExtend godTagResveCnncExtend) {
		GoodsTagResult result = new GoodsTagResult();		
		try {			
			if(goodsContentRepository.updateTagReserveGoods(godTagResveCnncExtend) > 0) {				
				result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
			}else {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			}
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();
		}
		return result; 
	}
	
	
	/**
	 * 태그예약 적용된 상품 목록 삭제
	 * 
	 * @param godTagResveCnncExtend
	 * @return
	 */
	public GoodsTagResult deleteTagReserveGoods (GodTagResveCnncExtend godTagResveCnncExtend) {
		GoodsTagResult result = new GoodsTagResult();
		try {			
			GodTagResveCnnc godTagResveCnnc = new GodTagResveCnnc();
			BeanUtils.copyProperties(godTagResveCnncExtend, godTagResveCnnc);			
			
			if(godTagResveCnncRepository.deleteGodTagResveCnnc(godTagResveCnnc) > 0) {				
				result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
			}else {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			}			
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();

		}
		return result; 
	}
	
	/**
	 * 공지사항 목록 검색
	 * 
	 * @param goodsNoticeSearchDTO
	 * @return
	 */
	public Page<GoodsNoticeResult> searchNoticeList(GoodsNoticeSearchDTO goodsNoticeSearchDTO) {
		try {
			// 페이지 인덱스 셋팅
			PageParam pageParam = getPageService().buildPageParam(goodsNoticeSearchDTO.getGPageNo(), goodsNoticeSearchDTO.getGPageSize());
			RepositoryHelper.makePageEntityIndex(goodsNoticeSearchDTO, pageParam);
	
			// 목록 건수 조회
			long totalRow = goodsContentRepository.getNoticeListCount(goodsNoticeSearchDTO);
	
			// 목록 조회
			List<GoodsNoticeResult> results = new ArrayList<GoodsNoticeResult>();
			if (totalRow > 0) {
				results = goodsContentRepository.selectNoticeList(goodsNoticeSearchDTO);
			}	
			return new PageImpl<GoodsNoticeResult>(results, pageParam.getPageable(), totalRow);			
			
		}catch (Exception e) {
			super.stackTrace(e);
			throw new RuntimeException();
		}
	}

	/**
	 * 공지사항 등록
	 * 
	 * @param godNoti
	 * @return
	 */
	public GoodsNoticeResult insertNotice(GodNoti godNoti) {
		GoodsNoticeResult result = new GoodsNoticeResult();		
		try {
			long godNotiSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_GOD_NOTI", DatabaseType.ORACLE);
			godNoti.setGodNotiSn(godNotiSn);
			godNoti.setDeleteYn(String.valueOf(GoodsEnum.NO));
			godNotiRepository.insertGodNoti(godNoti);
			
			result.setGodNoti(godNoti);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();
		}
		return result;
	}
	
	/**
	 * 공지사항 수정
	 * 
	 * @param godNoti
	 * @return
	 */
	public GoodsNoticeResult updateNotice(GodNoti godNoti) {
		GoodsNoticeResult result = new GoodsNoticeResult();
		try {			
			if(goodsContentRepository.updateNotice(godNoti) > 0) {
				result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
			}else {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			}
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();
		}
		return result;
	}
	
	/**
	 * 공지사항 수정리스트
	 * 
	 * @param godNoti
	 * @return
	 */
	public GoodsNoticeResult updateNoticeList(GodNoti godNoti) {
		GoodsNoticeResult result = new GoodsNoticeResult();
		try {			
			if(goodsContentRepository.updateNoticeList(godNoti) > 0) {
				result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
			}else {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			}
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();
		}
		return result;
	}
	
	/**
	 * 공지사항 브랜드 연결 수정
	 * Merge문 사용
	 * 
	 * @param godNotiBrndCnnc
	 * @return
	 */
	public GoodsNoticeResult updateNoticeBrandConnected(GodNotiBrndCnnc godNotiBrndCnnc) {
		GoodsNoticeResult result = new GoodsNoticeResult();
		try {
			if(goodsContentRepository.updateNoticeBrandConnected(godNotiBrndCnnc) > 0) {
				result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
			}else {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			}			
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();
		}
		return result;
	}
	
	/**
	 * 공지사항 상품 연결 수정
	 * Merge문 사용
	 * 
	 * @param godNotiGodCnnc
	 * @return
	 */
	public GoodsNoticeResult updateNoticeGoodsConnected(GodNotiGodCnnc godNotiGodCnnc) {
		GoodsNoticeResult result = new GoodsNoticeResult();
		try {
			if(goodsContentRepository.updateNoticeGoodsConnected(godNotiGodCnnc) > 0) {
				result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
			}else {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			}			
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();
		}
		return result;
	}
	
	/**
	 * 공지사항 카테고리 연결 수정
	 * Merge문 사용
	 * 
	 * @param godNotiDspCtgryCnnc
	 * @return
	 */
	public GoodsNoticeResult updateNoticeDisplayCategoryConnected(GodNotiDspCtgryCnnc godNotiDspCtgryCnnc) {
		GoodsNoticeResult result = new GoodsNoticeResult();		
		try {
			if(goodsContentRepository.updateNoticeDisplayCategoryConnected(godNotiDspCtgryCnnc) > 0){
				result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
			}else{
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			}
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();
		}		
		return result;
	}
	
	
	/**
	 * 공지사항 조회
	 * 
	 * @param goodsNoticeSearchDTO
	 * @return
	 */
	public GoodsNoticeResult getNotice(GoodsNoticeSearchDTO goodsNoticeSearchDTO) {
		GoodsNoticeResult result = new GoodsNoticeResult();
		try {
			GodNoti godNoti = new GodNoti();
			godNoti.setGodNotiSn(Long.parseLong(goodsNoticeSearchDTO.getGodNotiSn()));			
			
			result.setGodNotiEx(goodsContentRepository.getNotice(goodsNoticeSearchDTO));
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();			
		}
		return result;
	}
	
	/**
	 * 공지사항 적용 목록 조회
	 * 
	 * @param goodsNoticeSearchDTO
	 * @return
	 */
	public Page<GoodsNoticeResult> getNoticeTargetList(GoodsNoticeSearchDTO goodsNoticeSearchDTO) {
		try {
			// 페이지 인덱스 셋팅
			PageParam pageParam = getPageService().buildPageParam(goodsNoticeSearchDTO.getGPageNo(), goodsNoticeSearchDTO.getGPageSize());
			RepositoryHelper.makePageEntityIndex(goodsNoticeSearchDTO, pageParam);
	
			// 목록 건수 조회
			long totalRow = goodsContentRepository.getNoticeTargetListCount(goodsNoticeSearchDTO);
	
			// 목록 조회
			List<GoodsNoticeResult> results = new ArrayList<GoodsNoticeResult>();
			if(totalRow > 0) {
				results = goodsContentRepository.getNoticeTargetList(goodsNoticeSearchDTO);
			}
			return new PageImpl<GoodsNoticeResult>(results, pageParam.getPageable(), totalRow);
		}catch (Exception e) {
			super.stackTrace(e);
			throw new RuntimeException();
		}
	}
	
	/**
	 * 상품번호와 연결된 공지사항 목록 모두 조회
	 * -공지기간이 유효한 경우
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodNotiExtend> getAllGoodsNoticeList(String godNo) {
		List<GodNotiExtend> noticeList = new ArrayList<GodNotiExtend>();
		noticeList = goodsContentRepository.getAllGoodsNoticeList(godNo);
		return noticeList;
	}
	
	/**
	 *  상품번호와 연결된 언어별 공지사항 목록 조회 
	 * 
	 * @param goodsContentSearchDTO
	 * @return
	 */
	public List<GodNotiExtend> getGoodsNoticeList(GoodsContentSearchDTO goodsContentSearchDTO) {
		return goodsContentRepository.getGoodsNoticeList(goodsContentSearchDTO);
	}
	
	/**
	 * 모델 목록 조회
	 * 
	 * @param goodsModelSearchDTO
	 * @return
	 */
	public Page<GoodsModelResult> searchModelList(GoodsModelSearchDTO goodsModelSearchDTO)  {
        try {
	        // 페이지 인덱스 셋팅
	        PageParam pageParam = getPageService().buildPageParam(goodsModelSearchDTO.getGPageNo(), goodsModelSearchDTO.getGPageSize());               
	        RepositoryHelper.makePageEntityIndex(goodsModelSearchDTO, pageParam);
	        
	        // 목록 건수 조회
	        long totalRow = goodsContentRepository.getModelListCount(goodsModelSearchDTO);
	        
	        // 목록 조회
	        List<GoodsModelResult> results = new ArrayList<GoodsModelResult>();
	        if(totalRow > 0){
	            results = goodsContentRepository.selectModelList(goodsModelSearchDTO);
	        }
	        return new PageImpl<GoodsModelResult>(results, pageParam.getPageable(), totalRow);
        }catch (Exception e){
        	super.stackTrace(e);
			throw new RuntimeException();
		}		
	}
	
	
	/**
	 * 모델 조회
	 * 
	 * @param goodsModelSearchDTO
	 * @return
	 */
	public GoodsModelResult getModel(GoodsModelSearchDTO goodsModelSearchDTO)  {
		return goodsContentRepository.getModel(goodsModelSearchDTO);	
	}
	
    /**
     * 모델 조회
     * @param modelNo
     * @return
     */        
    public GodModel getModel(String modelNo)  {
    	try {
	        GodModel godModel = new GodModel();
	        godModel.setModelNo(modelNo);
	        return godModelRepository.selectGodModel(godModel);
    	}catch (Exception e){
    		super.stackTrace(e);
			throw new RuntimeException();
		}
    }	
	
	
    /**
     * 모델 수정
     * 
     * @param modelImageDTO
     */
    public void updateModel(GoodsModelImageDTO modelImageDTO)  { 
    	try {
    		godModelRepository.updateGodModel(modelImageDTO.getGodModel());
    	}catch (Exception e){
    		super.stackTrace(e);
			throw new RuntimeException();
		}
    } 
	
    /**
     * 모델 사이즈 수정
     * 
     * @param modelImageDTO
     */
    public void updateModelSizeList(GoodsModelImageDTO modelImageDTO)  {
        try {
	        if(!modelImageDTO.getModelSizeList().isEmpty()){            
	            String modelNo = modelImageDTO.getGodModel().getModelNo();
	            
	            for(GodModelSize modelSize : modelImageDTO.getModelSizeList()){
	                modelSize.setModelNo(modelNo);              
	                //godModelSizeRepository.updateModelSize(modelSize); 
	            }
	        }       
        }catch (Exception e){
        	super.stackTrace(e);
			throw new RuntimeException();
		}
    }	
	
	/**
	 * 모델 번호 생성
	 * 
	 * @return
	 */
	public String createModelNo()  {
		try {
		    String modelNo ="";	    
		    long dbSeq = getIdGenService().generateDBSequence(sqlSession1, "SQ_GOD_MODEL", DatabaseType.ORACLE);	    
		    modelNo = "M" + String.format("%05d", dbSeq);	    
		    return modelNo;
		}catch (Exception e){
			super.stackTrace(e);
			throw new RuntimeException();
		}
	}	
    
	/**
	 * 모델 등록
	 * 
	 * @param modelImageDTO
	 */
	public void insertModel(GoodsModelImageDTO modelImageDTO)  {
		try {
			godModelRepository.insertGodModel(modelImageDTO.getGodModel());
		}catch (Exception e){
			super.stackTrace(e);
			throw new RuntimeException();
		}
	}	
    
	/**
	 * 모델 사이즈 등록
	 * 
	 * @param modelImageDTO
	 */
	public void insertModelSizeList(GoodsModelImageDTO modelImageDTO)  {
	    try {
		    if(!modelImageDTO.getModelSizeList().isEmpty()){	        
		        String modelNo = modelImageDTO.getGodModel().getModelNo();
		        
		        for(GodModelSize modelSize : modelImageDTO.getModelSizeList()){
		            modelSize.setModelNo(modelNo);	            
		            godModelSizeRepository.insertGodModelSize(modelSize); 
		        }
		    }	    
	    }catch (Exception e){
	    	super.stackTrace(e);
			throw new RuntimeException();
		}
	}	
	
    /**
     * 모델 이미지 등록
     * 
     * @param modelImageDTO
     * @return
     */
    public int insertModelImgList(GoodsModelImageDTO modelImageDTO)  {
        try {
	    	int imgCnt = 0;
	        
	        if(!modelImageDTO.getModelImgExList().isEmpty()){            
	            String modelNo = modelImageDTO.getGodModel().getModelNo();
	            
	            for(GodModelImgExtend modelImgEx : modelImageDTO.getModelImgExList()){                
	                modelImgEx.setModelNo(modelNo);
	                if(StringService.isNotEmpty(modelImgEx.getTempFileName())){
	                    String modelImgNm =  modelNo+"_"+modelImgEx.getWearSizeCd()/*+"_"+modelImgEx.getEndpBrndId()*/+".jpg";
	                    modelImgEx.setModelImgUrl(modelImgWebPath+"/"+modelImgNm);                    
	                    this.uploadImg(imageTempPath, modelImgEx.getTempFileName(), modelImgUpPath, modelImgNm);                                    
	                }                
	                
	                if(modelImgEx.getModelImgTurn() == null){
	                    Map<String, Object> conditions = Maps.newHashMap();
	                    conditions.put("MODEL_NO", modelNo);
	                    int getModelImgMaxTurn = idGenService.generateDBOrder(sqlSession1, "GOD_MODEL_IMG", "MODEL_IMG_TURN", conditions, DatabaseType.ORACLE);                                        
	                    modelImgEx.setModelImgTurn(getModelImgMaxTurn);
	                    
	                }                
	                goodsContentRepository.updateModelImg(modelImgEx);
	            }
	        }
	        return imgCnt;
        }catch (Exception e){
        	super.stackTrace(e);
			throw new RuntimeException();
		}
    }	
	
	/**
	 * 모델 이미지 정보 조회
	 * 
	 * @param goodsModelSearchDTO
	 * @return
	 */
	public Page<GodModelImgExtend> searchModel(GoodsModelSearchDTO goodsModelSearchDTO)  {
		try {
	    	// 페이지 인덱스 셋팅
	        PageParam pageParam = getPageService().buildPageParam(goodsModelSearchDTO.getGPageNo(), goodsModelSearchDTO.getGPageSize());               
	        RepositoryHelper.makePageEntityIndex(goodsModelSearchDTO, pageParam);
	        
	        // 목록 건수 조회
	        long totalRow = goodsContentRepository.getModelImageExGridListCount(goodsModelSearchDTO);        
	        
	        // 목록 조회
	        List<GodModelImgExtend> results = new ArrayList<GodModelImgExtend>();
	        if(totalRow > 0){
	            results = goodsContentRepository.selectModelImageExGridList(goodsModelSearchDTO);
	        }
	        return new PageImpl<GodModelImgExtend>(results, pageParam.getPageable(), totalRow);	        
	        
		} catch (Exception e) {
			super.stackTrace(e);
			throw new RuntimeException();
		}
	}
	
    /**
     * 브랜드 모델 정보 조회
     * 
     * @param goodsModelSearchDTO
     * @return
     */
    public GodModelImgExtend getBrandModel(GoodsModelSearchDTO goodsModelSearchDTO) {
    	return goodsContentRepository.getBrandModel(goodsModelSearchDTO);	
    }
        
    /**
     * 상품 리뷰 목록 조회(BO)
     * 
     * @param goodsReviewSearchDTO
     * @return
     */
    public Page<GoodsReviewResult> selectGoodsReviewListForMember(SystemPK systemPK, GodGodEvl gge, PageParam pageParam) {        	
    	
    	try {			
        	// 페이지 인덱스 셋팅
    		GoodsReviewSearchDTO goodsReviewSearchDTO = new GoodsReviewSearchDTO();
    		goodsReviewSearchDTO.setLang(systemPK.getLang());
    		goodsReviewSearchDTO.setMbrNo(gge.getMbrNo());
    		goodsReviewSearchDTO.setMallId(gge.getMallId());
        	RepositoryHelper.makePageEntityIndex(goodsReviewSearchDTO, pageParam);
        	
        	// step 2. 목록 건수 조회
        	long listCount = goodsContentRepository.getReviewListCount(goodsReviewSearchDTO);
        	        	
        	// 목록 조회
    		List<GoodsReviewResult> lists = new ArrayList<GoodsReviewResult>();
    		GoodsReviewResult goodsReviewResult = new GoodsReviewResult();
    		List<GodGodEvlExtend> results = new ArrayList<GodGodEvlExtend>();	 
    		if(listCount > 0){
    			results = goodsContentRepository.selectReviewList(goodsReviewSearchDTO);
    			for(GodGodEvlExtend ex : results) {
    				goodsReviewResult.setGodGodEvlEx(ex);
    				lists.add(goodsReviewResult);
    			}
    		}
        	
        	return new PageImpl<GoodsReviewResult>(lists, pageParam.getPageable(), listCount);
    		
		} catch (Exception e) {
			super.stackTrace(e);
			throw new RuntimeException();	
		}
    }
    
    /**
     * 상품 리뷰 엑셀 목록 조회(BO)
     * 
     * @param goodsReviewSearchDTO
     * @return
     */
    public List<Map<String, Object>> selectReviewListForExcel(SystemPK systemPK, GodGodEvl gge) {        	
		GoodsReviewSearchDTO goodsReviewSearchDTO = new GoodsReviewSearchDTO();
		goodsReviewSearchDTO.setLang(systemPK.getLang());
		goodsReviewSearchDTO.setMbrNo(gge.getMbrNo());
		goodsReviewSearchDTO.setMallId(gge.getMallId());
        	
    	return goodsContentRepository.selectReviewListForExcel(goodsReviewSearchDTO);
    }
    
    /**
     * 상품 리뷰 목록 조회
     * 
     * @param goodsReviewSearchDTO
     * @return
     */
    public Page<GodGodEvlExtend> searchReviewList(GoodsReviewSearchDTO goodsReviewSearchDTO) {        	
    	
    	try {			
    		// 페이지 인덱스 셋팅
    		PageParam pageParam = getPageService().buildPageParam(goodsReviewSearchDTO.getGPageNo(), goodsReviewSearchDTO.getGPageSize());
    		RepositoryHelper.makePageEntityIndex(goodsReviewSearchDTO, pageParam);
    		
    		if(goodsReviewSearchDTO.getMallId() == null) goodsReviewSearchDTO.setMallId(goodsReviewSearchDTO.getMallTpCd()); 
    		
			// 목록 건수 조회	
			long totalRow = goodsContentRepository.getReviewListCount(goodsReviewSearchDTO);			
						
			// 목록 조회
			List<GodGodEvlExtend> results = new ArrayList<GodGodEvlExtend>();	    		
    		
			if(totalRow > 0) {
				results = goodsContentRepository.selectReviewList(goodsReviewSearchDTO);
			}
			
			return new PageImpl<GodGodEvlExtend>(results, pageParam.getPageable(), totalRow);
    		
		} catch (Exception e) {
			super.stackTrace(e);
			throw new RuntimeException();	
		}
    }
    
    public long getReviewListCount(GoodsReviewSearchDTO goodsReviewSearchDTO) {
    	return  goodsContentRepository.getReviewListCount(goodsReviewSearchDTO);
    }
    
    /**
     * 상품 리뷰 첨부 파일 목록 조회
     * 
     * @param goodsReviewSearchDTO
     * @return
     */
    public List<GodGodEvlAtchFile> getGodGodEvlAtchFileList(GoodsReviewSearchDTO goodsReviewSearchDTO) {
    	List<GodGodEvlAtchFile> evlAtchFileList = new ArrayList<GodGodEvlAtchFile>();
    	evlAtchFileList = goodsContentRepository.getGodGodEvlAtchFileList(goodsReviewSearchDTO);
    	return evlAtchFileList; 
    }
    
    /**
     * 상품리뷰  평점 조회
     * 
     * @param goodsReviewSearchDTO
     * @return
     */
    public GodGodEvlExtend getReviewAverageScore(GoodsReviewSearchDTO goodsReviewSearchDTO) {
    	return goodsContentRepository.getReviewAverageScore(goodsReviewSearchDTO);
    }
	
    /**
     * 상품 쿠폰 프로모션 목록 조회
     * 
     * @param goodsContentSearchDTO
     * @return
     */
    public List<PrmCpnGodExtend> getGoodsCouponPromotionList(GoodsContentSearchDTO goodsContentSearchDTO){
    	return goodsContentRepository.getGoodsCouponPromotionList(goodsContentSearchDTO);
    }
    
    /**
     * 할인 혜택이 가장 큰 상품 쿠폰 프로모션 조회
     * 
     * @param goodsContentSearchDTO
     * @return
     */
    public PrmCpnGodExtend getGoodsMostDiscountCouponPromotion(GoodsContentSearchDTO goodsContentSearchDTO){
    	return goodsContentRepository.getGoodsMostDiscountCouponPromotion(goodsContentSearchDTO);
    }
    
    /**
     * 신규가입쿠폰 
     * 
     * @param goodsContentSearchDTO
     * @return
     */
    public PrmCpnGodExtend getGoodsCouponNewMember(GoodsContentSearchDTO goodsContentSearchDTO){
    	return goodsContentRepository.getGoodsCouponNewMember(goodsContentSearchDTO);
    }
    
	/**
	 * 상품 이벤트 목록 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<Evt> getGoodsEventList(GoodsContentSearchDTO goodsContentSearchDTO) {
		return goodsContentRepository.getGoodsEventList(goodsContentSearchDTO);
	}
	
	/**
	 * 상품 카드 이벤트 조회
	 * 
	 * @param goodsContentSearchDTO
	 * @return
	 */
	public Evt getGoodsCardEvent(GoodsContentSearchDTO goodsContentSearchDTO) {
		return goodsContentRepository.getGoodsCardEvent(goodsContentSearchDTO);
	}
	
	/**
	 * 상품 기획전 목록 조회
	 * 
	 * @param goodsContentSearchDTO
	 * @return
	 */
	public List<DspPromt> getGoodsPromotionList(GoodsContentSearchDTO goodsContentSearchDTO) {
		return goodsContentRepository.getGoodsPromotionList(goodsContentSearchDTO);
	}	
	
	/**
	 * 사은품 프로모션 확인
	 * 
	 * @param goodsContentSearchDTO
	 * @return
	 */
	public Integer isGiftPromotion (GoodsContentSearchDTO goodsContentSearchDTO) {
		return goodsContentRepository.isGiftPromotion(goodsContentSearchDTO);
	}    
    
	/**
	 * 상품 프로모션 언어별 목록 조회
	 * 
	 * @param goodsContentSearchDTO
	 * @return
	 */
	public List<PrmLang> getGoodsPromotionLangList(GoodsContentSearchDTO goodsContentSearchDTO) {
		return goodsContentRepository.getGoodsPromotionLangList(goodsContentSearchDTO);
	}
	
	/**
	 * 프로모션 언어 조회
	 * 
	 * @param prmNo
	 * @param langCd
	 * @return
	 */
	public PrmLang getPromotionLanguage(String prmNo, String langCd) {
		PrmLang lang = new PrmLang();
		lang.setPrmNo(prmNo);
		lang.setLangCd(langCd);
		return prmLangRepository.selectPrmLang(lang);
	}
	
	/**
	 * 이미지 등록
	 * 
	 * @param tempPath
	 * @param tempImgNm
	 * @param newImgPath
	 * @param newImgNm
	 */
	public void uploadImg(String tempPath, String tempImgNm, String newImgPath, String newImgNm)  {
		String regex = File.separator;
		String uploadImageType = "base";		
		String tempBasePath = getConfigService().getProperty( "ncp_base.image."+uploadImageType+".upload.temp.path" )+regex;		
		
		try {			
			if(StringService.isNotEmpty(tempImgNm) && StringService.isNotEmpty(newImgNm)){				
				File servFile = new File(newImgPath+regex, newImgNm);
				
				if(StringService.isEmpty(tempPath)){
					tempPath = tempBasePath;
				}
				
				//	파일 존재 유무 체크 후 delete move
				if (servFile.exists()) {
					IOService.deleteFile(newImgPath+regex, newImgNm);
				}
				IOService.moveFileToFile(new File(tempPath+regex+tempImgNm), new File(newImgPath+regex+newImgNm));
			}
        }
        catch (Exception e) {
        	if(log.isInfoEnabled()) {log.info("> IOService.moveFileToFil Exception : {}",  e);}
        	throw new GoodsImageUploadFailException(null);
        	
        }		
		
	}
	
    /**
     * 최근 본 상품 중복조회
     * 
     * @param mbrTodayGod
     */
    public void isDuplicateTodayGod(MbrTodayGod mbrTodayGod)  {

		String todayGodSn = goodsContentRepository.isDuplicateTodayGod(mbrTodayGod);
		log.debug("todayGodSn::: {}",todayGodSn);

	    if(StringService.isEmpty(todayGodSn)){//등록
			long todayGodSn_ = getIdGenService().generateDBSequence(sqlSession1, "SQ_MBR_TODAY_GOD",DatabaseType.ORACLE);
			mbrTodayGod.setTodayGodSn(todayGodSn_); //오늘본상품 일련번호
			mbrTodayGod.setDeleteYn("N");
			goodsContentRepository.insertTodayGod(mbrTodayGod);
	    }else{//최근 본 상품 조회날짜 업데이트
	    	mbrTodayGod.setTodayGodSn(Long.valueOf(todayGodSn));
	    	goodsContentRepository.updateTodayGodInqireDt(mbrTodayGod);
	    }
    }   
    
	/**
	 * 최근 본 상품 조회 by Cookie
	 * 
	 * @param goodsContentSearchDTO
	 * @return
	 */
	public List<GoodsInterestsGodFoResult> getTodayCookiesList(InterestSearchFoDTO interestSearchFoDTO) {
		return goodsContentRepository.selectTodayCookies2(interestSearchFoDTO);
	}	
	
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	private String moveImageForCloudFront(GodImgExtend godImgExtend) {
		String tempResourceFileName = godImgExtend.getTempFileName();
		String tempResourcePath = this.bucketName + ":" + getUploadImageTempPath() + tempResourceFileName;			
		String commitResourceName = godImgExtend.getErpGodNo()+"-"+tempResourceFileName.toLowerCase(); 
		
		// 대량 등록시 이미지 경로 재설정
		if(String.valueOf(GoodsEnum.GoodsUploadImageType.MASS).equals(godImgExtend.getImageUploadType())) {
			tempResourcePath = this.bucketName + ":" + godImgExtend.getTempResourcePath();
			commitResourceName = godImgExtend.getErpGodNo()+"-"+System.nanoTime()+"."+godImgExtend.getFileExt().toLowerCase();
		}
		
		String imgUrl = godImgExtend.getSaveImagePath();
		String commitResourcePath = this.bucketName + ":" + getSaveImageUrlPath(godImgExtend.getCloudFrontType()) + imgUrl + commitResourceName;			
		
		s3FileSystem.move(tempResourcePath, commitResourcePath, CloudFileSystem.Permission.PUBLIC);
		
		return imgUrl + commitResourceName;
	}

	/**
	 * 이미지 순서 재조정
	 * - 임시로 +1000을 하여 UPDATE후 다시 순서대로 UPDATE 처리.
	 * 
	 * @param godNo
	 * @param udterId
	 */
	public void updateGoodsImageTurn(String godNo, String udterId) {
		List<GodImg> imageList = this.getGoodsImageList(godNo);
		
//		int imgTurn = 1;
//		for(GodImg image : imageList) {
//			if(String.valueOf(GoodsEnum.GoodsImageType.DETAIL).equals(image.getImgTpCd())) {				
//				GodImgExtend imageEx = new GodImgExtend();
//				BeanUtils.copyProperties(image, imageEx);			
//				imageEx.setNewImgTurn(imgTurn);
//				
//				goodsContentRepository.updateGoodsImageTurn(imageEx);
//				
//				imgTurn++;
//			}
//		}
		
		// UPDATE시 PK오류가 발생하는 일이 있어서 임시로 +1000을 하여 UPDATE후 다시 순서대로 UPDATE 처리.
		if(imageList != null && !imageList.isEmpty()) {
			List<GodImg> imageDetailList = 
					imageList.stream()
					.filter(t -> t.getImgTpCd().equals(String.valueOf(GoodsEnum.GoodsImageType.DETAIL)))
					.sorted((t, t2) -> t.getImgTurn().compareTo(t2.getImgTurn()))
					.collect(Collectors.toList());
			
			if(imageDetailList != null && !imageDetailList.isEmpty()) {
				int imgTurn = 1;
				int imgTmpTurn = 1000;
				for(GodImg image : imageDetailList) {
					GodImgExtend imageEx = new GodImgExtend();
					BeanUtils.copyProperties(image, imageEx);			
					imageEx.setNewImgTurn(imgTurn+imgTmpTurn);
					imageEx.setUdterId(udterId);
					
					goodsContentRepository.updateGoodsImageTurn(imageEx);
					
					imgTurn++;
				}

				imgTurn = 1;
				for(GodImg image : imageDetailList) {
					GodImgExtend imageEx = new GodImgExtend();
					BeanUtils.copyProperties(image, imageEx);			
					imageEx.setImgTurn(imgTurn+imgTmpTurn);
					imageEx.setNewImgTurn(imgTurn);
					imageEx.setUdterId(udterId);
					
					goodsContentRepository.updateGoodsImageTurn(imageEx);
					
					imgTurn++;
				}
			}
		}
	}
	
	private void updateLangByTagReserve(GodTagResveExtend godTagResveExtend){
		try {			
			GodLangbyTagResve langByTagResve = new GodLangbyTagResve();
			BeanUtils.copyProperties(godTagResveExtend, langByTagResve);
			
			if(StringService.isNotEmpty(godTagResveExtend.getTagNmEng())) {				
				// ENG
				langByTagResve.setTagNm(godTagResveExtend.getTagNmEng());
				langByTagResve.setLangCd(String.valueOf(GoodsEnum.ENG));
				godLangbyTagResveRepository.updateGodLangbyTagResve(langByTagResve);
			}
			/*
			if(StringService.isNotEmpty(godTagResveExtend.getTagNmChi())) {				
				// CHI
				langByTagResve.setTagNm(godTagResveExtend.getTagNmChi());
				langByTagResve.setLangCd(String.valueOf(GoodsEnum.CHI));
				godLangbyTagResveRepository.updateGodLangbyTagResve(langByTagResve);
			}
			*/
		} catch (Exception e) {
			super.stackTrace(e);
		}
	}	
	
	private void insertLangByTagResrve(GodTagResveExtend godTagResveExtend){
		
		try {			
			GodLangbyTagResve langByTagResve = new GodLangbyTagResve();
			BeanUtils.copyProperties(godTagResveExtend, langByTagResve);
			
			if(StringService.isNotEmpty(godTagResveExtend.getTagNmEng())) {				
				// ENG
				langByTagResve.setTagNm(godTagResveExtend.getTagNmEng());
				langByTagResve.setLangCd(String.valueOf(GoodsEnum.ENG));
				godLangbyTagResveRepository.insertGodLangbyTagResve(langByTagResve);
			}
			/*
			if(StringService.isNotEmpty(godTagResveExtend.getTagNmChi())) {				
				// CHI
				langByTagResve.setTagNm(godTagResveExtend.getTagNmChi());
				langByTagResve.setLangCd(String.valueOf(GoodsEnum.CHI));
				godLangbyTagResveRepository.insertGodLangbyTagResve(langByTagResve);
			}
			*/
		} catch (Exception e) {
			super.stackTrace(e);
		}
	}
	
	/**
	 * 상품 동영상 조회
	 * 
	 * @param godMovi
	 * @return
	 */
	public String addGoodsInform(GodReWhsgNtcn godReWhsgNtcn) {
		try {	
			goodsContentRepository.addGoodsInform(godReWhsgNtcn);
		} catch (Exception e) {
			super.stackTrace(e);
			return "";
		}
		return "SUCCES";
	}
	
	/**
	 * 연관상품 조회
	 * 
	 * @param contentSearchDTO
	 * @return
	 */
	public List<GoodsRelatedGodResult> getRelatedGoods(GoodsContentSearchDTO contentSearchDTO) {
		
		List<GoodsRelatedGodResult> resultList =  goodsContentRepository.getRelatedGoods(contentSearchDTO);
		List<GoodsRelatedGodResult> tmpList = goodsContentRepository.getNewRelatedGoods(contentSearchDTO);
		
		if(resultList.isEmpty()) {
			resultList = tmpList;
		}else{
			if(!tmpList.isEmpty()){
				for(int i = 0; i < tmpList.size(); i++){
					if(i == 4){
						break;
					}
					resultList.add(tmpList.get(i));
				}
			}
		}
		
		return resultList;
    
	}

	public GodGodEvlExtend getDsgnGrpCnnGoods(GodGodEvlExtend godGodEvlExtend) {
		return goodsContentRepository.getDsgnGrpCnnGoods(godGodEvlExtend);
	 
		
	}
	public GodGodEvlExtend getDsgnGrpCnnKey(GodGodEvlExtend godGodEvlExtend) {
		return goodsContentRepository.getDsgnGrpCnnKey(godGodEvlExtend);
	 
		
	}

	
	public void insertDsgnGrpCnnGoods(GodGodEvlExtend godGodEvlExtend) {
		goodsContentRepository.insertDsgnGrpCnnGoods(godGodEvlExtend);
		
	}

	public Page<GoodsReviewResult> searchDsgnGrpsGridList(GoodsSearchDTO goodsSearchDTO) {
		try {
			// 페이지 인덱스 셋팅
			PageParam pageParam = getPageService().buildPageParam(goodsSearchDTO.getGPageNo(), goodsSearchDTO.getGPageSize());
			RepositoryHelper.makePageEntityIndex(goodsSearchDTO, pageParam);
			
			// 목록 건수 조회	
			long totalRow = goodsContentRepository.searchDsgnGrpsGridListCount(goodsSearchDTO);
			
			// 목록 조회
			List<GoodsReviewResult> results = new ArrayList<GoodsReviewResult>();
			
			if(totalRow > 0) {
				results = goodsContentRepository.searchDsgnGrpsGridList(goodsSearchDTO);	
			}
			
			return new PageImpl<GoodsReviewResult>(results, pageParam.getPageable(), totalRow);
		} catch (Exception e) {
			super.stackTrace(e);
			throw new RuntimeException();
		}
	}

	public void deleteDsgnGrpList(List<GoodsSearchDTO> gridList) {
		GodGodEvlExtend godGodEvlEx = null;
	 
		for(GoodsSearchDTO dto: gridList) {
			godGodEvlEx = dto.getGodGodEvlEx();
 
			goodsContentRepository.deleteDsgnGrpGrid(godGodEvlEx);
		}
	}
	public List<GoodsReviewResult> searchDsgnGrpsView(GoodsSearchDTO goodsSearchDTO) {
		return goodsContentRepository.searchDsgnGrpsView(goodsSearchDTO);

	}

}
