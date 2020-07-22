package com.plgrim.ncp.biz.goods.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspGodPrc;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodAditDetail;
import com.plgrim.ncp.base.entities.datasource1.god.GodCnncGrpGod;
import com.plgrim.ncp.base.entities.datasource1.god.GodCnncGrpGodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodColorExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodCpstGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodCpstGodCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodDsgnGrp;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodIconCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmOpt;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyCpstGodNmExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.god.GodModelImgCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodNtfcDetail;
import com.plgrim.ncp.base.entities.datasource1.god.GodOpt;
import com.plgrim.ncp.base.entities.datasource1.god.GodOptVal;
import com.plgrim.ncp.base.entities.datasource1.god.GodRelateExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodSaleMall;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInv;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndPrdlst;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.repository.com.ComDmstcDlvCstPlcLangRepository;
import com.plgrim.ncp.base.repository.god.GodCnncGrpGodRepository;
import com.plgrim.ncp.biz.goods.data.GoodsExcelDTO;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.repository.GoodsInfoRepository;
import com.plgrim.ncp.biz.goods.result.GoodsColorResult;
import com.plgrim.ncp.biz.goods.result.GoodsErpSizeChartResult;
import com.plgrim.ncp.biz.goods.result.GoodsInfoResult;
import com.plgrim.ncp.biz.vendor.data.VendorGoodsOptionDTO;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.commons.util.CodeUtil.Code;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.utils.Utils;

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
 * Description	:	상품 정보 Service
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Service
public class GoodsInfoService extends GoodsService{

	@Autowired
	private GoodsInfoRepository goodsInfoRepository;

	@Autowired
	private ComDmstcDlvCstPlcLangRepository comDmstcDlvCstPlcLangRepository;
	
	@Autowired
	private GodCnncGrpGodRepository godCnncGrpGodRepository;
	
	/**
	 * 상품 정보 조회
	 * - God table select by search DTO 
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public God getGoods(GoodsSearchDTO goodsSearchDTO) {
		return goodsInfoRepository.getGoods(goodsSearchDTO);		
	}
	
	/**
	 * 상품 조회 (검색)
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public Page<GoodsInfoResult> searchGoodsList(GoodsSearchDTO goodsSearchDTO) {		
		try {			
			// 페이지 인덱스 셋팅
			PageParam pageParam = getPageService().buildPageParam(goodsSearchDTO.getGPageNo(), goodsSearchDTO.getGPageSize());
			RepositoryHelper.makePageEntityIndex(goodsSearchDTO, pageParam);
			
			// 목록 건수 조회	
			long totalRow = goodsInfoRepository.getGoodsListCount(goodsSearchDTO);			
						
			// 목록 조회
			List<GoodsInfoResult> results = new ArrayList<GoodsInfoResult>();			
			
			if(totalRow > 0) {
				// 20191218 정렬 조건용 상품번호 배열
				goodsSearchDTO.setOrdrGodNos(goodsSearchDTO.getGodNos());
				results = goodsInfoRepository.selectGoodsList(goodsSearchDTO);
			}
			return new PageImpl<GoodsInfoResult>(results, pageParam.getPageable(), totalRow);
		} catch (Exception e) {
			super.stackTrace(e);
			throw new RuntimeException();			
		}
	}
	
	/**
	 * GOD 테이블 ERP품번 등록 확인
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public long isExistErpGodNo(GoodsSearchDTO goodsSearchDTO) {		
		return goodsInfoRepository.isExistErpGodNo(goodsSearchDTO);
	}	
	
	/**
	 * ERP 상품 번호 생성
	 * 
	 * @param goodsDTO
	 * @return
	 */
	public String createErpGodNo(God god) {
		try {
			String erpGodNo = "";
			if(String.valueOf(GoodsEnum.GoodsType.GNRL_GOD).equals(god.getGodTpCd()) || String.valueOf(GoodsEnum.GoodsType.PCHS_GFT).equals(god.getGodTpCd())) {			
				erpGodNo = god.getErpGodNo();
			}else{
				//	상품 DB Sequence type
				String godTyPrefix = "";				
								
				List<Code> cdList = CodeUtil.getCodes(String.valueOf(GoodsEnum.GOD_TP_GRP), String.valueOf(GoodsEnum.KOR));
				
				for(Code code : cdList){			
					if(god.getGodTpCd().equals(code.getCd().toString())){
						godTyPrefix = code.getAsstnCd1().toString();				
					}
				}
						
				long dbSeq = getIdGenService().generateDBSequence(sqlSession1, "SQ_GOD_ERP_GOD_NO", DatabaseType.ORACLE);
				erpGodNo = godTyPrefix + String.format("%08d", dbSeq);
				
				if(null != god.getColorCd()){
					erpGodNo += god.getColorCd();
				}
			}
			return erpGodNo;
		}catch (Exception e){
			super.stackTrace(e);
			throw new RuntimeException();
		}		
	}

	/**
	 * 상품 번호 생성
	 * 
	 * @param godTpCd
	 * @param comId
	 * @return
	 */
	public String createGodNo(String godTpCd, String comId)  {
		try {
			//	상품 DB Sequence type
			String godTyPrefix = "";
	
			//	상품 DB Sequence No
			String godNo = "";
				
			List<Code> cdList = CodeUtil.getCodes(String.valueOf(GoodsEnum.GOD_TP_GRP), String.valueOf(GoodsEnum.KOR));
					
			//	상품 등록 유형에 따라 상품 번호 Prefix 설정
			for(Code code : cdList){			
				if(godTpCd.equals(code.getCd().toString())){
					godTyPrefix = code.getAsstnCd1().toString();				
				}
			}
			
			String yyMMdd = DateService.parseString(new Date(), "yyMMdd");
			
			long dbSeq = getIdGenService().generateDBSequence(sqlSession1, "SQ_GOD", DatabaseType.ORACLE);
			godNo = godTyPrefix + comId + yyMMdd + String.format("%05d", dbSeq);
			
			return godNo;
		}catch (Exception e){
			super.stackTrace(e);
			throw new RuntimeException();
		}
	}
	
	/**
	 * 디자인 그룹 등록
	 * 
	 * @param godDsgnGrp
	 * @return
	 */
	public GoodsInfoResult insertGoodsDesignGroup(GodDsgnGrp godDsgnGrp) {
		GoodsInfoResult result = new GoodsInfoResult();				
		try {
			godDsgnGrpRepository.insertGodDsgnGrp(godDsgnGrp);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException("디자인 그룹 등록 실패", e);
		}
		return result;
	}
	
	
	/**
	 * 상품 등록
	 * 
	 * @param god
	 * @return
	 */
	public GoodsInfoResult insertGoods(God god) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			godRepository.insertGod(god);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException("상품 등록 실패", e);			
		}
		return result;
	}
		
	/**
	 * 언어별 상품명 등록
	 * 
	 * @param godLangbyGodNm
	 * @return
	 */
	public GoodsInfoResult insertGoodsLangByGoodsName(GodLangbyGodNm godLangbyGodNm) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			godLangbyGodNmRepository.insertGodLangbyGodNm(godLangbyGodNm);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException("언어별 상품명 등록 실패", e);						
		}
		return result;
	}
	
	/**
	 * 상품 단품 등록
	 * 
	 * @param godItm
	 * @return
	 */
	public GoodsInfoResult insertGoodsItem(GodItm godItm) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {			
			String itmNo = getIdGenService().generateDBNumber(sqlSession1, "SQ_GOD_ITM", "IT", DatabaseType.ORACLE);			
			godItm.setItmNo(itmNo);						
			godItmRepository.insertGodItm(godItm);			
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException("상품 단품 등록 실패", e);
		}
		
		return result;
	}
		
	/**
	 * 상품 옵션 등록
	 * 
	 * @param godOpt
	 * @return
	 */
	public GoodsInfoResult insertGoodsOption(GodOpt godOpt) {
		GoodsInfoResult result = new GoodsInfoResult();		
		try {
			goodsInfoRepository.insertGoodsOption(godOpt);			
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException("상품 옵션 등록 실패", e);
		}
		return result;
	}
	
	/**
	 * 상품 옵션 값 등록
	 * 
	 * @param godOptVal
	 * @return
	 */
	public GoodsInfoResult insertGoodsOptionValue(GodOptVal godOptVal) {
		GoodsInfoResult result = new GoodsInfoResult();		
		try {
			goodsInfoRepository.insertGoodsOptionValue(godOptVal);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException("상품 옵션 값 등록 실패", e);
		}
		return result;
	}	
	
	/**
	 * 상품 단품 옵션 등록
	 * 
	 * @param godItmOpt
	 * @return
	 */
	public GoodsInfoResult insertGoodsItemOption(GodItmOpt godItmOpt) {
		GoodsInfoResult result = new GoodsInfoResult();		
		try {
			goodsInfoRepository.insertGoodsItemOption(godItmOpt);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException("상품 단품 옵션 등록 실패", e);
		}
		return result;
	}
	
	/**
	 * 상품 매장 단품 재고 등록
	 * 
	 * @param erpGodNo
	 * @return
	 */
	public GoodsInfoResult insertGoodsShopItemInventory(String erpGodNo) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			goodsInfoRepository.insertGoodsShopItemInventory(erpGodNo);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException("상품 매장 단품 재고 등록 실패", e);
		}		
		
		return result;
	}
	
	/**
	 * 업체 상품 옵션 등록 항목 설정
	 * 
	 * @param optValList
	 * @param comId
	 * @param loginId
	 * @return
	 */
	public List<VendorGoodsOptionDTO> setVendorGoodsOption(List<GodOptVal> optValList, String comId, String loginId) {
		List<VendorGoodsOptionDTO> savList = new ArrayList<VendorGoodsOptionDTO>();
		for(GodOptVal optVal : optValList){
			VendorGoodsOptionDTO vendorGoodsOptionDTO = new VendorGoodsOptionDTO();
			vendorGoodsOptionDTO.setComId(comId);
			vendorGoodsOptionDTO.setOptCd(optVal.getOptCd());
			vendorGoodsOptionDTO.setOptNm(String.valueOf(GoodsEnum.DummySize.OPT_TY_NM));
			vendorGoodsOptionDTO.setOptValCd(optVal.getOptValCd());
			vendorGoodsOptionDTO.setOptValNm(optVal.getOptValCd());
			vendorGoodsOptionDTO.setSortSeq(optVal.getSortSeq());
			vendorGoodsOptionDTO.setUseYn(String.valueOf(GoodsEnum.YES));
			vendorGoodsOptionDTO.setRegtrId(loginId);
			savList.add(vendorGoodsOptionDTO);
		}
		return savList;
	}
	
	
	/**
	 * 상품 고시 상세 등록
	 * 
	 * @param godNtfcDetail
	 * @return
	 */
	public GoodsInfoResult insertGoodsNotificationDetail(GodNtfcDetail godNtfcDetail) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			godNtfcDetailRepository.insertGodNtfcDetail(godNtfcDetail);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException("상품 고시 상세 등록 실패", e);
		}
		return result;
	}
	
	/**
	 *  상품 구성 상품 연결 등록
	 * 
	 * @param godCpstGodCnnc
	 * @return
	 */
	public GoodsInfoResult insertGoodsCompositionGoodsConnection(GodCpstGodCnnc godCpstGodCnnc) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			godCpstGodCnncRepository.insertGodCpstGodCnnc(godCpstGodCnnc);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();
		}
		return result;
		
	}
	
	/**
	 * 상품 판매몰 등록
	 * 
	 * @param godSaleMall
	 * @return
	 */
	public GoodsInfoResult insertGoodsSaleMall(GodSaleMall godSaleMall) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			godSaleMallRepository.insertGodSaleMall(godSaleMall);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();
		}
		return result;
	}
	
	/**
	 * 상품 아이콘 연결 등록 
	 * 
	 * @param godIconCnnc
	 * @return
	 */
	public GoodsInfoResult insertGoodsIconConnection(GodIconCnnc godIconCnnc) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			godIconCnncRepository.insertGodIconCnnc(godIconCnnc);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();
		}
		return result;
	}
	
	/**
	 * 상품 추가 상세 등록
	 * 
	 * @param godAditDetail
	 * @return
	 */
	public GoodsInfoResult insertGoodsAdditinalDetail(GodAditDetail godAditDetail) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			godAditDetailRepository.insertGodAditDetail(godAditDetail);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();
		}
		return result;
	}	
	
	/**
	 * 상품 모델 연결 등록
	 * 
	 * @param godModelImgCnnc
	 * @return
	 */
	public GoodsInfoResult insertGoodsModelImageConnection(GodModelImgCnnc godModelImgCnnc) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			godModelImgCnncRepository.insertGodModelImgCnnc(godModelImgCnnc);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();
		}
		return result;
	}
	
	/**
	 * 디자인 그룹 등록 여부 확인
	 * 
	 * @param dsgnGrpNo
	 * @return
	 */
	public boolean isGoodsDesignGroup(GodDsgnGrp godDsgnGrp) {
		boolean result = true;
		if(Utils.empty(godDsgnGrpRepository.selectGodDsgnGrp(godDsgnGrp))) {
			result = false;
		}
		return result; 
	}
	
	/**
	 * 언어별 상품명 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodLangbyGodNm> getGoodsLangByGoodsNameList(String godNo){		
		return goodsInfoRepository.getGoodsLangByGoodsNameList(godNo);
	}	
	
	/**
	 * 상품별 단품(옵션) 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodItmExtend> getGoodsItemList(String godNo) {
		return goodsInfoRepository.getGoodsItemList(godNo);
	}
	
	/**
	 * 상품별 매장 단품(옵션) 조회
	 * 
	 * @param godNo
	 * @param shopId
	 * @return
	 */
	public List<GodItmExtend> getGoodsItemListByShop(String godNo, String shopId) {
		List<GodItmExtend> itemList = new ArrayList<GodItmExtend>();
		
		if(StringService.isNotEmpty(shopId)) {
			GodShopItmInv shopItmInv = new GodShopItmInv();
			shopItmInv.setGodNo(godNo);
			shopItmInv.setShopId(shopId);			
			itemList = goodsInfoRepository.getGoodsItemListByShop(shopItmInv);			
		}else {
			itemList = this.getGoodsItemList(godNo);
		}		
		return itemList;
	}
	
	/**
	 * 언어별 고시정보 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodNtfcDetail> getGoodsNotificationDetailList(String godNo) {
		return goodsInfoRepository.getGoodsNotificationDetailList(godNo);
	}
	
	
	/**
	 * 구성품 연결 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodCpstGodCnncExtend> getGoodsCompositionGoodsConnectionList(String godNo) {
		return goodsInfoRepository.getGoodsCompositionGoodsConnectionList(godNo);
	}
	
	/**
	 * 구성상품 언어별 상품명 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodLangbyCpstGodNmExtend> getGoodsLangByCompositionGoodsNameList(String godNo) {
		return goodsInfoRepository.getGoodsLangByCompositionGoodsNameList(godNo);
	}
	
	/**
	 * 상품 구성품 연결의 단품 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodItmExtend> getGoodsCompositionGoodsConnectionItemList(String godNo) {
		return goodsInfoRepository.getGoodsCompositionGoodsConnectionItemList(godNo);
	}

	/**
	 * 상품 구성품 연결의 매장별 단품 조회
	 * 
	 * @param godNo
	 * @param shopId
	 * @return
	 */
	public List<GodItmExtend> getGoodsCompositionGoodsConnectionItemListByShop(String godNo, String shopId) {
		List<GodItmExtend> itemList = new ArrayList<GodItmExtend>();
		
		if(StringService.isNotEmpty(shopId)) {
			GodShopItmInv shopItmInv = new GodShopItmInv();
			shopItmInv.setGodNo(godNo);
			shopItmInv.setShopId(shopId);			
			itemList = goodsInfoRepository.getGoodsCompositionGoodsConnectionItemListByShop(shopItmInv);			
		}else {
			itemList = this.getGoodsCompositionGoodsConnectionItemList(godNo);
		}
		return itemList;
	}
	
	/**
	 * 판매몰 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public List<GodSaleMall> getGoodsSaleMallList(String godNo) {
		return goodsInfoRepository.getGoodsSaleMallList(godNo);
	}

	/**
	 * 디자인 그룹 목록 조회
	 * 
	 * @param godNo
	 * @param mallId
	 * @return
	 */
	public List<GodExtend> getDesignColorList(String godNo) {
		// 특정 상품 디자인 그룹 목록 조회
		List<GodExtend> designColorList = goodsInfoRepository.getSpecificationDesignColorList(godNo);
		
		if(Utils.empty(designColorList)){
			designColorList = goodsInfoRepository.getDesignColorList(godNo); 
		}				
		return designColorList;
	}
		
	/**
	 * 업체 국내 배송비 정책 목록 조회
	 * 
	 * @param comId
	 * @return
	 */
	public List<ComDmstcDlvCstPlc> getComDomesticDeliveryCostPolicy(Map<String, String> paramMap) {
		return goodsInfoRepository.getComDomesticDeliveryCostPolicy(paramMap);
	}	
	
	/**
	 * 업체 국내 배송비 정책 목록 언어별 목록 조회
	 * 
	 * @param comId
	 * @return
	 */
	public List<ComDmstcDlvCstPlcLang> getComDomesticDeliveryCostPolicyByLangList(String comId) {
		return goodsInfoRepository.getComDomesticDeliveryCostPolicyByLangList(comId);
	}

	/**
	 * 업체 국내 배송비 정책 목록 언어별 조회
	 * 
	 * @param comId
	 * @return
	 */
	public ComDmstcDlvCstPlcLang getComDomesticDeliveryCostPolicyByLang(Long dmstcDlvCstPlcSn, String langCd) {
		ComDmstcDlvCstPlcLang comDmstcDlvCstPlcLang = new ComDmstcDlvCstPlcLang();
		comDmstcDlvCstPlcLang.setDmstcDlvCstPlcSn(dmstcDlvCstPlcSn);
		comDmstcDlvCstPlcLang.setLangCd(langCd);
		return comDmstcDlvCstPlcLangRepository.selectComDmstcDlvCstPlcLang(comDmstcDlvCstPlcLang);
	}	
	
	/**
	 * 상품 컬러 그룹 목록 조회
	 * 
	 * @param brandGrpId
	 * @return
	 */
	public List<GodColorExtend> getGodColorList(String brndGrpId) {
		return goodsInfoRepository.getGoodsColorList(brndGrpId);
	}
	
	/**
	 * 상품 수정
	 * 
	 * @param god
	 * @return
	 */
	public GoodsInfoResult updateGoods(God god) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			if(goodsInfoRepository.updateGoods(god) > 0) {
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
	 * 상품 단품 수정
	 * 
	 * @param godItm
	 * @return
	 */
	public GoodsInfoResult updateGoodsItem(GodItm godItm) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			if(goodsInfoRepository.updateGoodsItem(godItm) > 0) {
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
	 * 상품 언어별 상품명 수정
	 * 
	 * @param godLangbyGodNm
	 * @return
	 */
	public GoodsInfoResult updateGoodsLangByGoodsName(GodLangbyGodNm godLangbyGodNm) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			if(goodsInfoRepository.updateGoodsLangByGoodsName(godLangbyGodNm) > 0) {
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
	 * 상품 판매몰 수정
	 * 
	 * @param godSaleMall
	 * @return
	 */
	public GoodsInfoResult updateGoodsSaleMall(GodSaleMall godSaleMall) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			godSaleMallRepository.updateGodSaleMall(godSaleMall);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();			
		}
		return result;
	}
	
	/**
	 * 상품 판매몰 삭제
	 * 
	 * @param godNo
	 */
	public void deleteGoodsSaleMall(String godNo) {
		goodsInfoRepository.deleteGoodsSaleMall(godNo);		
	}
	
	/**
	 * 상품 고시 상세 수정
	 * 
	 * @param godNtfcDetail
	 * @return
	 */
	public GoodsInfoResult updateGoodsNotificationDetail(GodNtfcDetail godNtfcDetail) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			if(goodsInfoRepository.updateGoodsNotificationDetail(godNtfcDetail) > 0) {
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
	 * 상품 구성 상품 연결 수정
	 * 
	 * @param godCpstGodCnnc
	 * @return
	 */
	public GoodsInfoResult updateGoodsCompositionGoodsConnection(GodCpstGodCnnc godCpstGodCnnc) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			if(godCpstGodCnncRepository.updateGodCpstGodCnnc(godCpstGodCnnc) > 0) {
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
	 * 일반상품이 세트의 구성품인 경우 세트의 판매상태 수정
	 * 
	 * @param godNo
	 */
	public void updateSetStatus(String cpstGodNo) {		
		String setGodNo = goodsInfoRepository.getSetGoodsNumber(cpstGodNo);
		
		//	상품상테 업데이트
		int count = goodsInfoRepository.updateSetGoodsStatus(cpstGodNo);

		//	단품 상테 업데이트
		if(count > 0) {
			goodsInfoRepository.updateGoodsItemAllStatusAccordingToGoodsStatus(setGodNo);
		}
	}
	
	/**
	 * 세트상품 전시 가능 체크
	 * 
	 * @param godNo
	 * @return
	 */
	public boolean checkSetDisplayValidate(String godNo) {
		boolean valid = true;
		if(goodsInfoRepository.checkSetDisplayValidate(godNo) > 0) {
			valid = false;
		}		
		return valid;
	}
	
	/**
	 * 적립금 조회
	 * 
	 * @param dspGodPrc
	 * @return
	 */
	public HashMap<String, BigDecimal> getGoodsPointSaveMoney(DspGodPrc dspGodPrc) {
		return goodsInfoRepository.getGoodsPointSaveMoney(dspGodPrc);
	}
	
	/**
	 * 상품 상세 URL 조회
	 * 
	 * @param godNo
	 * @return
	 */
	public String getViewGoodsUrl(String godNo) {
		return goodsInfoRepository.getViewGoodsUrl(godNo);
	}
	
	/**
	 * 상품 상세 redirect URL 조회
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public GoodsInfoResult getRedirectGoodsUrl(GoodsSearchDTO goodsSearchDTO) {
		return goodsInfoRepository.getRedirectGoodsUrl(goodsSearchDTO);
	}
	
	/**
	 * 연관상품 삭제
	 * 
	 * @param godNo
	 * @return
	 */
	public int deleteGoodsRelate(String godNo) {
		return goodsInfoRepository.deleteGoodsRelate(godNo);
	}
	
	/**
	 * 연관상품 등록
	 * 
	 * @param godRelateExtend
	 * @return
	 */
	public GoodsInfoResult insertGoodsRelate(GodRelateExtend godRelateExtend) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			if(godRelateExtend.getSortSeq() == null || godRelateExtend.getSortSeq() == 0) {
				godRelateExtend.setSortSeq(Integer.parseInt(DisplayEnum.DF_SORT_SEQ.toString()));
			}
			goodsInfoRepository.insertGoodsRelate(godRelateExtend);
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			super.stackTrace(e);
			throw new RuntimeException();
		}
		return result;
	}
	
	/**
	 * 상품 고시 정보 세팅
	 * - 엑셀로 상품정보 등록 및 수정시 사용
	 * 
	 * @param excelData
	 * @param sizeChart
	 * @param erpGodData
	 * @param langCd
	 * @param brndPrdlst
	 * @return GodNtfcDetail
	 */
	public GodNtfcDetail setGodNtfcDetailByExcelInfo(String crud, GodNtfcDetail notification, String langCd, GoodsExcelDTO excelData, GoodsErpSizeChartResult sizeChart, InfGodItmExtend erpGodData, SysBrndPrdlst brndPrdlst) {
		String mnfcturDate 			= excelData.getMnfcturDate();
		String mnfcturNationNm 		= excelData.getMnfcturNationNm();
		String mnfcturNationNmEng 	= excelData.getMnfcturNationNmEng();
		String mnfcturNationNmChi 	= excelData.getMnfcturNationNmChi();
		String matrDscr 			= excelData.getMatrDscr();
		String matrDscrEng 			= excelData.getMatrDscrEng();
		String matrDscrChi 			= excelData.getMatrDscrChi();
		String godDetailDscr1 		= excelData.getGodDetailDscr1();
		String godDetailDscrEng1 	= excelData.getGodDetailDscrEng1();
		String godDetailDscrChi1 	= excelData.getGodDetailDscrChi1();
		String godDetailDscr2 		= excelData.getGodDetailDscr2();
		String godDetailDscrEng2 	= excelData.getGodDetailDscrEng2();
		String godDetailDscrChi2	= excelData.getGodDetailDscrChi2();
		String sizeLktbHtml			= excelData.getSizeLktbHtml();
		String sizeLktbHtmlEng		= excelData.getSizeLktbHtmlEng();
		String sizeLktbHtmlChi		= excelData.getSizeLktbHtmlChi();
		String mobileSizeLktbHtml	= excelData.getMobileSizeLktbHtml();
		String mobileSizeLktbHtmlEng= excelData.getMobileSizeLktbHtmlEng();
		String mobileSizeLktbHtmlChi= excelData.getMobileSizeLktbHtmlChi();

		// 등록
		if("C".equals(crud)) {
			notification = new GodNtfcDetail();

			if(StringService.isEmpty(mnfcturDate)) {
				mnfcturDate = erpGodData.getMnfcturYm();
			}
			
			notification.setLangCd(langCd);
			notification.setPrdlstGrpCd(brndPrdlst.getPrdlstGrpCd());
			notification.setMnfcturDate(mnfcturDate);
			notification.setLndrCd(erpGodData.getLndrCd());

			if(String.valueOf(GoodsEnum.KOR).equals(langCd)) {
				if(StringService.isEmpty(mnfcturNationNm)) {
					mnfcturNationNm = erpGodData.getOrgPlNm();
				}
				if(StringService.isEmpty(matrDscr)) {
					matrDscr = erpGodData.getMatrDscr();
				}
				
				notification.setMnfcturNationNm(mnfcturNationNm);
				notification.setMatrDscr(matrDscr);
				if(!StringService.isEmpty(sizeLktbHtml.trim())) {
					notification.setSizeLktbHtml(sizeLktbHtml.trim());
				}
				else {
					notification.setSizeLktbHtml(sizeChart.getSizeLktb());
				}
				if(!StringService.isEmpty(mobileSizeLktbHtml.trim())) {
					notification.setMobileSizeLktbHtml(mobileSizeLktbHtml.trim());
				}
				else {
					notification.setMobileSizeLktbHtml(sizeChart.getMobileSizeLktb());
				}
				notification.setGodDetailAditDscr(godDetailDscr1);
				notification.setGodDetailDscr(godDetailDscr2);
			}
			else if(String.valueOf(GoodsEnum.ENG).equals(langCd)) {
				if(StringService.isEmpty(mnfcturNationNmEng)) {
					mnfcturNationNmEng = erpGodData.getOrgPlNm();
				}
				if(StringService.isEmpty(matrDscrEng)) {
					matrDscrEng = erpGodData.getMatrDscr();
				}
				
				notification.setMnfcturNationNm(mnfcturNationNmEng);
				notification.setMatrDscr(matrDscrEng);
				if(!StringService.isEmpty(sizeLktbHtmlEng.trim())) {
					notification.setSizeLktbHtml(sizeLktbHtmlEng.trim());
				}
				else {
					notification.setSizeLktbHtml(sizeChart.getSizeLktbEng());
				}
				if(!StringService.isEmpty(mobileSizeLktbHtmlEng.trim())) {
					notification.setMobileSizeLktbHtml(mobileSizeLktbHtmlEng.trim());
				}
				else {
					notification.setMobileSizeLktbHtml(sizeChart.getMobileSizeLktbEng());
				}
				notification.setGodDetailAditDscr(godDetailDscrEng1);
				notification.setGodDetailDscr(godDetailDscrEng2);
			}
			else if(String.valueOf(GoodsEnum.CHI).equals(langCd)) {
				if(StringService.isEmpty(mnfcturNationNmChi)) {
					mnfcturNationNmChi = erpGodData.getOrgPlNm();
				}
				if(StringService.isEmpty(matrDscrChi)) {
					matrDscrChi = erpGodData.getMatrDscr();
				}
				notification.setMnfcturNationNm(mnfcturNationNmChi);
				notification.setMatrDscr(matrDscrChi);
				if(!StringService.isEmpty(sizeLktbHtmlChi.trim())) {
					notification.setSizeLktbHtml(sizeLktbHtmlChi.trim());
				}
				else {
					notification.setSizeLktbHtml(sizeChart.getSizeLktbChi());
				}
				if(!StringService.isEmpty(mobileSizeLktbHtmlChi.trim())) {
					notification.setMobileSizeLktbHtml(mobileSizeLktbHtmlChi.trim());
				}
				else {
					notification.setMobileSizeLktbHtml(sizeChart.getMobileSizeLktbChi());
				}
				notification.setGodDetailAditDscr(godDetailDscrChi1);
				notification.setGodDetailDscr(godDetailDscrChi2);
			}
		}
		// 수정
		else if("U".equals(crud)) {
			notification.setLangCd(langCd);

			if(String.valueOf(GoodsEnum.KOR).equals(langCd)) {				
				if(!StringService.isEmpty(mnfcturNationNm)) {					
					notification.setMnfcturNationNm(mnfcturNationNm);
				}
				if(!StringService.isEmpty(sizeLktbHtml.trim())) {
					notification.setSizeLktbHtml(sizeLktbHtml.trim());
				}
				if(!StringService.isEmpty(mobileSizeLktbHtml.trim())) {
					notification.setMobileSizeLktbHtml(mobileSizeLktbHtml.trim());
				}
				if(!StringService.isEmpty(godDetailDscr1)) {
					notification.setGodDetailAditDscr(godDetailDscr1);
				}
				if(!StringService.isEmpty(godDetailDscr2)) {
					notification.setGodDetailDscr(godDetailDscr2);
				}
			}
			else if(String.valueOf(GoodsEnum.ENG).equals(langCd)) {
				if(!StringService.isEmpty(mnfcturNationNmEng)) {					
					notification.setMnfcturNationNm(mnfcturNationNmEng);
				}				
				if(!StringService.isEmpty(sizeLktbHtmlEng.trim())) {
					notification.setSizeLktbHtml(sizeLktbHtmlEng.trim());
				}
				if(!StringService.isEmpty(mobileSizeLktbHtmlEng.trim())) {
					notification.setMobileSizeLktbHtml(mobileSizeLktbHtmlEng.trim());
				}
				if(!StringService.isEmpty(godDetailDscrEng1)) {
					notification.setGodDetailAditDscr(godDetailDscrEng1);
				}
				if(!StringService.isEmpty(godDetailDscrEng2)) {
					notification.setGodDetailDscr(godDetailDscrEng2);
				}
			}
			else if(String.valueOf(GoodsEnum.CHI).equals(langCd)) {
				if(!StringService.isEmpty(mnfcturNationNmChi)) {					
					notification.setMnfcturNationNm(mnfcturNationNmChi);
				}				
				if(!StringService.isEmpty(sizeLktbHtmlChi.trim())) {
					notification.setSizeLktbHtml(sizeLktbHtmlChi.trim());
				}
				if(!StringService.isEmpty(mobileSizeLktbHtmlChi.trim())) {
					notification.setMobileSizeLktbHtml(mobileSizeLktbHtmlChi.trim());
				}
				if(!StringService.isEmpty(godDetailDscrChi1)) {
					notification.setGodDetailAditDscr(godDetailDscrChi1);
				}
				if(!StringService.isEmpty(godDetailDscrChi2)) {
					notification.setGodDetailDscr(godDetailDscrChi2);
				}
			}
		}

		return notification;
	}
	
	/**
	 * 언어별 상품명 설정
	 * - 엑셀로 상품정보 등록시 사용
	 *
	 * @param excelData
	 * @param erpGodData
	 * @return List<GodLangbyGodNm>
	 */
	public GodLangbyGodNm setLangbyGodNm(String crud, GodLangbyGodNm langbyGodNm, String langCd, GoodsExcelDTO excelData, InfGodItmExtend erpGodData) {
		String godNmEng 			= excelData.getGodNmEng();
		String godNmChi 			= excelData.getGodNmChi();
		String mobileGodNmEng 		= excelData.getMobileGodNmEng();
		String mobileGodNmChi 		= excelData.getMobileGodNmChi();

		// 등록
		if("C".equals(crud)) {
			langbyGodNm = new GodLangbyGodNm();
			langbyGodNm.setLangCd(langCd);

			if(String.valueOf(GoodsEnum.ENG).equals(langCd)) {
				// 영어 상품명 설정
				if(StringService.isEmpty(godNmEng)) {
					godNmEng = erpGodData.getEngGodNm();
				}
				if(StringService.isEmpty(mobileGodNmEng)) {
					mobileGodNmEng = erpGodData.getEngGodNm();
				}
				langbyGodNm.setGodNm(godNmEng);
				langbyGodNm.setMobileGodNm(mobileGodNmEng);
			}
			else if(String.valueOf(GoodsEnum.CHI).equals(langCd)) {
				// 중국어 상품명 설정
				if(StringService.isEmpty(godNmChi)) {
					godNmChi = erpGodData.getChiGodNm();
				}
				if(StringService.isEmpty(mobileGodNmChi)) {
					mobileGodNmChi = erpGodData.getChiGodNm();
				}
				langbyGodNm.setGodNm(godNmChi);
				langbyGodNm.setMobileGodNm(mobileGodNmChi);
			}
		}
		// 수정
		else if("U".equals(crud)) {
			if(String.valueOf(GoodsEnum.ENG).equals(langCd)) {
				// 영어 상품명 설정
				if(!StringService.isEmpty(godNmEng)) {
					langbyGodNm.setGodNm(godNmEng);
				}
				if(!StringService.isEmpty(mobileGodNmEng)) {
					langbyGodNm.setMobileGodNm(mobileGodNmEng);
				}
			}
			else if(String.valueOf(GoodsEnum.CHI).equals(langCd)) {
				// 중국어 상품명 설정
				if(!StringService.isEmpty(godNmChi)) {
					langbyGodNm.setGodNm(godNmChi);
				}
				if(!StringService.isEmpty(mobileGodNmChi)) {
					langbyGodNm.setMobileGodNm(mobileGodNmChi);
				}
			}
		}

		return langbyGodNm;
	}
	
	/**
	 * 상품 승인
	 * 
	 * @param god
	 * @return
	 */
	public GoodsInfoResult updateGoodsApproval(God god) {
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			if(goodsInfoRepository.updateGoodsApproval(god) > 0) {
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
	 * 상품 스타일 연결 조회(검색)
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public Page<GoodsColorResult> searchStyleConnectionList(GoodsSearchDTO goodsSearchDTO) {		
		try {			
			// 페이지 인덱스 셋팅
			PageParam pageParam = getPageService().buildPageParam(goodsSearchDTO.getGPageNo(), goodsSearchDTO.getGPageSize());
			RepositoryHelper.makePageEntityIndex(goodsSearchDTO, pageParam);
			
			// 목록 건수 조회	
			long totalRow = goodsInfoRepository.getStyleConnectionListCount(goodsSearchDTO);			
						
			// 목록 조회
			List<GoodsColorResult> results = new ArrayList<GoodsColorResult>();			
			
			if(totalRow > 0) {
				results = goodsInfoRepository.selectStyleConnectionList(goodsSearchDTO);
			}
			return new PageImpl<GoodsColorResult>(results, pageParam.getPageable(), totalRow);
		} catch (Exception e) {
			super.stackTrace(e);
			throw new RuntimeException();			
		}
	}	
	
	/**
	 * 상품 스타일 연결 삭제 
	 * 
	 * @param godCnncGrpGod
	 */
	public void deleteStyleConnection(GodCnncGrpGod godCnncGrpGod){
		try {			
			godCnncGrpGodRepository.deleteGodCnncGrpGod(godCnncGrpGod);
		} catch (Exception e) {
			super.stackTrace(e);
			throw new RuntimeException();			
		}
	}
	
	
	/**
	 * 상품 브랜드 수 조회
	 * 
	 * @param godNos
	 * @return
	 */
	public int getGoodsBrandCount(List<String> godNos) {		
		return goodsInfoRepository.getGoodsBrandCount(godNos);
	}
	
	/**
	 * 상품 스타일 연결 그룹단위 등록
	 * 
	 * @param godNos
	 * @param loginId
	 */
	public void insertStyleConnectionGroups(List<String> godNos, String loginId) {		
		
		try {					
			long dbSeq = getIdGenService().generateDBSequence(sqlSession1, "SQ_GOD_CNNC_GOD", DatabaseType.ORACLE); 			
			List<GodCnncGrpGodExtend> list = goodsInfoRepository.getStyleConnectionTargetList(godNos);
				
			for(GodCnncGrpGodExtend entity : list){
				String newGruopNo = "BP-" + entity.getBrndId() + "-" + String.format("%05d", dbSeq);
				
				entity.setErpGodCnncGrpNo(newGruopNo);
				entity.setRegtrId(loginId);
				entity.setUdterId(loginId);
							
				godCnncGrpGodRepository.insertGodCnncGrpGod(entity);
			}
			
		} catch (Exception e) {
			super.stackTrace(e);
			throw new RuntimeException();		
		}		
	}
	
	public GodCnncGrpGod selectGodCnncGrpGod(GodCnncGrpGod godCnncGrpGod) {		
		
		return godCnncGrpGodRepository.selectGodCnncGrpGod(godCnncGrpGod);
	}
	
	public void addtyleConnectionGroups(List<String> godNos, String loginId,String erpGodCnncGrpNo) {		
		
		try {								
			List<GodCnncGrpGodExtend> list = goodsInfoRepository.getStyleConnectionTargetList(godNos);		
			for(GodCnncGrpGodExtend entity : list){
		 
				entity.setErpGodCnncGrpNo(erpGodCnncGrpNo);
				entity.setRegtrId(loginId);
				entity.setUdterId(loginId);		
				godCnncGrpGodRepository.insertGodCnncGrpGod(entity);
			}
			
		} catch (Exception e) {
			super.stackTrace(e);
			throw new RuntimeException();		
		}		
	}

	public List<God> getTempGoods() {
		return goodsInfoRepository.getTempGoods();
	}
	
	public void updateTempNoti(GodNtfcDetail godNtfcDetail) {
		goodsInfoRepository.updateTempNoti(godNtfcDetail);
	}
	
	
	public List<GoodsColorResult> searchStyleCnncView(GoodsSearchDTO goodsSearchDTO) {		
		return  goodsInfoRepository.searchStyleCnncView(goodsSearchDTO);
	}	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */	
}
