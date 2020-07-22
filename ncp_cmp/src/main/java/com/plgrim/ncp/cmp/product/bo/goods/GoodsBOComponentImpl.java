package com.plgrim.ncp.cmp.product.bo.goods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryCnncGod;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodCnncGrpGod;
import com.plgrim.ncp.base.entities.datasource1.god.GodColor;
import com.plgrim.ncp.base.entities.datasource1.god.GodColorExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodCpstGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodCpstGodCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodDsgnGrp;
import com.plgrim.ncp.base.entities.datasource1.god.GodDspCtgryExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodImgExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmOpt;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndrDscr;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndrImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodMovi;
import com.plgrim.ncp.base.entities.datasource1.god.GodNtfcDetail;
import com.plgrim.ncp.base.entities.datasource1.god.GodOpt;
import com.plgrim.ncp.base.entities.datasource1.god.GodOptVal;
import com.plgrim.ncp.base.entities.datasource1.god.GodRelateExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodSaleMall;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndPrdlst;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMall;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionProcedureSect;
import com.plgrim.ncp.biz.callcenter.service.MemoService;
import com.plgrim.ncp.biz.goods.data.GoodsDTO;
import com.plgrim.ncp.biz.goods.data.GoodsExcelDTO;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsColorResult;
import com.plgrim.ncp.biz.goods.result.GoodsErpSizeChartResult;
import com.plgrim.ncp.biz.goods.result.GoodsInfoResult;
import com.plgrim.ncp.biz.goods.service.GoodsCategoryService;
import com.plgrim.ncp.biz.goods.service.GoodsContentService;
import com.plgrim.ncp.biz.goods.service.GoodsErpService;
import com.plgrim.ncp.biz.goods.service.GoodsHistoryService;
import com.plgrim.ncp.biz.goods.service.GoodsInfoService;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.biz.promotion.service.PromotionService;
import com.plgrim.ncp.cmp.product.bo.GoodsBOComponent;
import com.plgrim.ncp.commons.auth.BOSecurityUserDetail;
import com.plgrim.ncp.commons.service.GodPlcService;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(value = "transactionManager")
@Component
public class GoodsBOComponentImpl extends AbstractComponent implements GoodsBOComponent {

	@Autowired
	private GodPlcService goodsPolicyService;
	
	@Autowired
	private GoodsInfoService goodsInfoService;
	
	@Autowired
	private GoodsErpService goodsErpService;
	
	@Autowired
	private GoodsHistoryService goodsHistoryService;
	
	@Autowired
	private GoodsCategoryService goodsCategoryService;
	
	@Autowired
	private GoodsContentService goodsContentService;
		
	@Autowired
	private MemoService memoService;
	
	@Autowired
	private PromotionService promotionService;
	
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsBOComponent#searchGoodsList(com.plgrim.ncp.biz.goods.data.GoodsSearchDTO)
	 */
	@Override
	public Page<GoodsInfoResult> searchGoodsList(GoodsSearchDTO goodsSearchDTO) {
		return goodsInfoService.searchGoodsList(goodsSearchDTO);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsBOComponent#updateGoodsList(com.plgrim.ncp.biz.goods.data.GoodsDTO)
	 */
	@Override
	public GoodsInfoResult updateGoodsList(GoodsDTO goodsDTO) {
		GoodsInfoResult result = new GoodsInfoResult();		
		try {			
			int cnt = goodsDTO.getGodList().size();			
			for(God god : goodsDTO.getGodList()) {
				String godNo = god.getGodNo();				
				goodsInfoService.updateGoodsApproval(god);
				
				//	상품의 상태 변경 시 단품 상태도 싱크
				if(StringService.isNotEmpty(god.getGodSaleSectCd())) {
					GodItm godItm = new GodItm();
					godItm.setGodNo(godNo);
					godItm.setItmStatCd(god.getGodSaleSectCd());
					godItm.setUdterId(god.getUdterId());
					goodsInfoService.updateGoodsItem(godItm);
				}
								
				//	세트상품 상태 싱크
				goodsInfoService.updateSetStatus(godNo);
				
				if(cnt < 10) {
					//	전시가격 생성
					PromotionBoDTO promotionBoDTO = new PromotionBoDTO();
					promotionBoDTO.setIcSect(String.valueOf(PromotionProcedureSect.GOD));
					promotionBoDTO.setGodNo(godNo);
					promotionService.updatePromotionGoodsPriceProcedure(promotionBoDTO);					
				}
			}
			
			for(GodLangbyGodNm langbyGodNm : goodsDTO.getGodLangbyGodNmList()) {
				GodLangbyGodNm langbyGodNmOrg = goodsInfoService.getGoodsLangByGoodsName(langbyGodNm.getGodNo(), langbyGodNm.getLangCd());
				if(!Utils.empty(langbyGodNmOrg)) {
					langbyGodNmOrg.setLangCd(langbyGodNm.getLangCd());
					langbyGodNmOrg.setOvseaDspStatCd(langbyGodNm.getOvseaDspStatCd());					
					goodsInfoService.updateGoodsLangByGoodsName(langbyGodNmOrg);
				}else {				
					goodsPolicyService.setGoodsLangByGoodsNameDefaultPolicy(langbyGodNm);
					goodsInfoService.insertGoodsLangByGoodsName(langbyGodNm);
				}
				
				//	글로벌 영문/중문 상품명 이력 등록
				goodsHistoryService.insertGoodsLangByGoodsNameHistory(langbyGodNm);
			}
			
			if(cnt >= 10) {				
				//	전시가격 생성
				PromotionBoDTO promotionBoDTO = new PromotionBoDTO();
				promotionBoDTO.setIcSect(String.valueOf(PromotionProcedureSect.ALL));
				promotionService.updatePromotionGoodsPriceProcedure(promotionBoDTO);
			}
			
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			throw new RuntimeException();
		}		
		return result;
	}	
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsBOComponent#insertGoods(com.plgrim.ncp.biz.goods.data.GoodsDTO)
	 */
	@Override
	public GoodsInfoResult insertGoods(GoodsDTO goodsDTO) {
		
		/**
		 * 상품 등록 FLOW
		 * ERP_GOD_NO 생성
		 * 
		 * 등록 유무 확인
		 * 
		 * 상품 번호 생성
		 * 	> 디자인 그룹번호 설정
		 * 	> 상위 브랜드 그룹 설정(이미지 경로 룰 정보)
		 * 
		 * 상품 디자인 그룹 등록
		 * 
		 * 상품 테이블 등록
		 * 	> 이력은 trigger 로 쌓임
		 * 
		 * 글로벌 영문 상품명 등록
		 * 	> 글로벌 영문 상품명 이력 등록
		 * 
		 * 상품 단품 등록
		 * 	> 이력은 trigger 로 쌓임
		 * 
		 * 상품 단품 매장 등록
		 * 상품 고시 상세 등록
		 * 전시카테고리 연결 상품 등록 
		 * 상품 판매몰 등록
		 * 상품 이미지 등록
		 * 
		 * 상품 구성 상품 연결 등록
		 * 
		 * 상품 옵션 등록
		 * 상품 옵션 값 등록		
		 * 상품 단품 옵션 등록
		 * 
		 * ERP SET(연관상품) 등록
		 *  
		 */		
		
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			//	ERP_GOD_NO 생성
			String erpGodNo = goodsInfoService.createErpGodNo(goodsDTO.getGod());
			goodsDTO.getGod().setErpGodNo(erpGodNo);
			
			//	등록 유무 확인
			GoodsSearchDTO erpGodNoSearchDTO = new GoodsSearchDTO();
			erpGodNoSearchDTO.setErpGodNo(StringService.trimToEmpty(erpGodNo));
			erpGodNoSearchDTO.setGodTpCd(StringService.trimToEmpty(goodsDTO.getGod().getGodTpCd()));
			
			if(goodsInfoService.isExistErpGodNo(erpGodNoSearchDTO) > 0) {			
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
				return result;			
			}
			
			//	상품 번호 생성
			String godNo = goodsInfoService.createGodNo(goodsDTO.getGod().getGodTpCd(), goodsDTO.getGod().getComId());			
			goodsDTO.getGod().setGodNo(godNo);			
			
			//	디자인 그룹 번호가 없을 시 ERP번호로 대체
			if(StringService.isEmpty(StringService.trimToEmpty(goodsDTO.getGodDsgnGrp().getDsgnGrpNo()))){
				goodsDTO.getGodDsgnGrp().setDsgnGrpNo(goodsDTO.getGod().getErpGodNo());
				goodsDTO.getGod().setDsgnGrpNo(goodsDTO.getGod().getErpGodNo());
			}			
			
			//	상위 브랜드 그룹 처리
			if(StringService.isEmpty(StringService.trimToEmpty(goodsDTO.getGod().getBrndGrpId()))){					
				SysBrnd sysBrnd = goodsInfoService.getSystemBrand(goodsDTO.getGod().getBrndId());
				goodsDTO.getGod().setBrndGrpId(sysBrnd.getUpperBrndId());
			}
			
			//	상품 디자인 그룹 등록			
			if(!goodsInfoService.isGoodsDesignGroup(goodsDTO.getGodDsgnGrp())) {
				GodDsgnGrp godDsgnGrp = new GodDsgnGrp();
				BeanUtils.copyProperties(goodsDTO.getGodDsgnGrp(), godDsgnGrp);

				goodsPolicyService.setGoodsDesignGroupDefaultPolicy(godDsgnGrp, goodsDTO.getGod());
				goodsInfoService.insertGoodsDesignGroup(godDsgnGrp);
			}
			
			//	상품 등록
			God god = new God();
			BeanUtils.copyProperties(goodsDTO.getGod(), god);	
			// MLB 일 경우 컬러코드를 => 팀 / 컬러로 쪼개 _ ds
			if("ML".equals(goodsDTO.getGod().getBrndGrpId()) && String.valueOf(GoodsEnum.GoodsType.GNRL_GOD).equals(goodsDTO.getGod().getGodTpCd())) {
				String mlbColorCd = erpGodNo.substring(erpGodNo.length()-3, erpGodNo.length());
				if(mlbColorCd.length() >= 3) {
					// TeamCd Setting
					god.setTeamCd(mlbColorCd.substring(0,2));
				}
			}
			
			goodsPolicyService.setGoodsDefaultPolicy(god);			
			goodsInfoService.insertGoods(god);
			
			//	글로벌 영문/중문 상품명 등록
			if(!Utils.empty(goodsDTO.getGodLangbyGodNmList())) {
				for(GodLangbyGodNm langbyGodNm : goodsDTO.getGodLangbyGodNmList()) {
					langbyGodNm.setGodNo(godNo);					
					goodsPolicyService.setGoodsLangByGoodsNameDefaultPolicy(langbyGodNm);
					
					goodsInfoService.insertGoodsLangByGoodsName(langbyGodNm);										
					//	글로벌 영문/중문 상품명 이력 등록
					goodsHistoryService.insertGoodsLangByGoodsNameHistory(langbyGodNm);					
				}
			}

			//	상품 단품 테이블 등록				
			for(GodItmExtend itm : goodsDTO.getGodItmExtendList()) {
				GodItm godItm = new GodItm();
				BeanUtils.copyProperties(itm, godItm);
				godItm.setGodNo(godNo);				

				goodsPolicyService.setGoodsItemDefaultPolicy(god, godItm);				
				goodsInfoService.insertGoodsItem(godItm);
			}			

			//	상품 옵션 등록			
			GodOpt opt = new GodOpt();
			opt.setGodNo(godNo);
			goodsInfoService.insertGoodsOption(opt);
			
			//	상품 옵션 값 등록
			GodOptVal optVal = new GodOptVal();
			optVal.setGodNo(godNo);
			goodsInfoService.insertGoodsOptionValue(optVal);
			
			//	상품 단품 옵션 등록
			GodItmOpt itmOpt = new GodItmOpt(); 
			itmOpt.setGodNo(godNo);
			goodsInfoService.insertGoodsItemOption(itmOpt);				
			
			// 상품 매장 단품 재고 등록
			goodsInfoService.insertGoodsShopItemInventory(erpGodNo);
			
			//	상품 고시상세 등록
			if(!Utils.empty(goodsDTO.getGodNtfcDetailList())) {
				for(GodNtfcDetail detail : goodsDTO.getGodNtfcDetailList()) {
					detail.setGodNo(godNo);
					goodsPolicyService.setGoodsNotificationDetailDefaultPolicy(god, detail);
					goodsInfoService.insertGoodsNotificationDetail(detail);
				}
			}
			
			//	전시카테고리 연결 상품 등록
			if(!Utils.empty(goodsDTO.getDspCtgryCnncGodList())) {
				for(DspCtgryCnncGod dspCtgryCnncGod : goodsDTO.getDspCtgryCnncGodList()) {
					dspCtgryCnncGod.setGodNo(godNo);
					goodsCategoryService.insertDisplayCategoryConnectionGoods(dspCtgryCnncGod);
				}
			}
			
			//	상품 판매몰 연결 등록
			if(!Utils.empty(goodsDTO.getGodSaleMallList())) {				
				for(GodSaleMall saleMall : goodsDTO.getGodSaleMallList()) {
					saleMall.setGodNo(godNo);
					goodsInfoService.insertGoodsSaleMall(saleMall);
				}
			}
			
			//	상품 이미지 등록
			if(!Utils.empty(goodsDTO.getGodImgExtendList())) {
				for(GodImgExtend imgEx : goodsDTO.getGodImgExtendList()) {
					goodsPolicyService.setGoodsImageDefaultPolicy(god, imgEx);					
					goodsContentService.insertGoodsImage(imgEx);					
				}
			}
			
			//	상품 동영상 등록
			if(!Utils.empty(goodsDTO.getGodMovi())) {				
				GodMovi movi = goodsDTO.getGodMovi();
				movi.setGodNo(godNo);
				goodsPolicyService.setGoodsMovieDefaultPolicy(movi);
				goodsContentService.insertGoodsMovie(goodsDTO.getGodMovi());
			}
			
			//	상품 구성 상품 연결 등록
			if(String.valueOf(GoodsEnum.GoodsType.SET_GOD).equals(god.getGodTpCd()) && !Utils.empty(goodsDTO.getGodCpstGodCnncList())) {
				for(GodCpstGodCnnc cpstCnnc : goodsDTO.getGodCpstGodCnncList()) {												
					cpstCnnc.setGodNo(godNo);
					goodsInfoService.insertGoodsCompositionGoodsConnection(cpstCnnc);
				}
			}
			
			// ERP SET(연관상품) 등록
			if(!Utils.empty(goodsDTO.getGodRelateExtendList())) {
				for(GodRelateExtend godRelateEx : goodsDTO.getGodRelateExtendList()) {
					godRelateEx.setGodNo(godNo);
					goodsInfoService.insertGoodsRelate(godRelateEx);
				}
			}
			
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
			result.setRstMsg("등록 되었습니다.");
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			throw new RuntimeException();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsBOComponent#getGoods(com.plgrim.ncp.biz.goods.data.GoodsSearchDTO)
	 */
	@Override
	public GoodsInfoResult getGoods(GoodsSearchDTO goodsSearchDTO) {		
		GoodsInfoResult result = new GoodsInfoResult();
		
		/**
		 * 상품 조회
		 * 상품 언어별 상품명 조회
		 * 업체 조회
		 * 업체 국내 배송비 정책 조회
		 * 브랜드 조회
		 * 컬러 조회
		 * 전시카테고리 조회
		 * 판매몰 정보 조회
		 * 단품 조회
		 * 고시정보 조회
		 * 이미지 조회
		 * 동영상 조회
		 * 구성정보 조회
		 * CS MEMO 조회
		 * 
		 */
		
		try {			
			String godNo = goodsSearchDTO.getGodNo();
			
			//	상품 조회
			GodExtend godEx = new GodExtend();			
			God god = goodsInfoService.getGoods(godNo);
			
			if(Utils.empty(god)) {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));				
				return result;
			}
			BeanUtils.copyProperties(god, godEx);
			result.setGodEx(godEx);
			
			//	상품 언어별 상품명 조회
			List<GodLangbyGodNm> langbyGodNmList = goodsInfoService.getGoodsLangByGoodsNameList(godNo);
			result.setGodLangbyGodNmList(langbyGodNmList);
			
			if(!Utils.empty(langbyGodNmList)) {				
				for(GodLangbyGodNm langByGodNm : langbyGodNmList) {
					if(String.valueOf(GoodsEnum.ENG).equals(langByGodNm.getLangCd())) {
						result.setGodNmEng(langByGodNm);
					}
					if(String.valueOf(GoodsEnum.CHI).equals(langByGodNm.getLangCd())) {
						result.setGodNmChi(langByGodNm);
					}
				}			
			}
			
			//	업체 조회
			String comId = god.getComId();
			Com com = goodsInfoService.getCom(comId);
			result.setCom(com);
			
			//	브랜드 조회
			SysBrnd sysBrnd = goodsInfoService.getSystemBrand(god.getBrndId());
			result.setSysBrnd(sysBrnd);

			//	컬러 조회
			String colorCd = god.getColorCd();
			String brndGrpId = sysBrnd.getUpperBrndId();
			GodColor godColor = goodsInfoService.getGoodsColor(colorCd, brndGrpId);
			result.setGodColor(godColor);			
			
			//	전시카테고리 조회
			List<GodDspCtgryExtend> dspCateList = goodsCategoryService.getDisplayCategoryList(godNo);
			result.setGodDspCtgryExList(dspCateList);

			//	판매몰 정보 조회
			List<GodSaleMall> saleMallList = goodsInfoService.getGoodsSaleMallList(godNo);
			result.setGodSaleMallList(saleMallList);		
			
			//	단품 조회
			List<GodItmExtend> itemList = goodsInfoService.getGoodsItemList(godNo);
			result.setGodItmExList(itemList);
			
			//	고시정보 조회
			List<GodNtfcDetail> ntfcDetailList = goodsInfoService.getGoodsNotificationDetailList(godNo);
			result.setGodNtfcDetailList(ntfcDetailList);

			if(!Utils.empty(ntfcDetailList)) {				
				for(GodNtfcDetail ntfcDetail : ntfcDetailList) {
					if(String.valueOf(GoodsEnum.KOR).equals(ntfcDetail.getLangCd())) {
						result.setGodNtfcDetail(ntfcDetail);
					}
					if(String.valueOf(GoodsEnum.ENG).equals(ntfcDetail.getLangCd())) {
						result.setGodNtfcDetailEng(ntfcDetail);
					}
					if(String.valueOf(GoodsEnum.CHI).equals(ntfcDetail.getLangCd())) {
						result.setGodNtfcDetailChi(ntfcDetail);
					}
				}
				
				String lndrCd = ntfcDetailList.get(Integer.parseInt(String.valueOf(GoodsEnum.Number.ZERO))).getLndrCd();
				//	세탁설명 조회
				List<GodLndrDscr> godLndrDscrList = goodsErpService.getGoodsLaundryDescriptionList(lndrCd);
				result.setGodLndrDscrList(godLndrDscrList);
				
				//	세탁설명 이미지 조회
				goodsSearchDTO.setLndrCd(lndrCd);
				goodsSearchDTO.setBrndGrpId(brndGrpId);
				List<GodLndrImg> godLndrImgList = goodsErpService.getGoodsLaundryImageList(goodsSearchDTO);
				result.setGodLndrImgList(godLndrImgList);				
				
			}
			
			// 이미지 조회
			List<GodImg> imageList = goodsContentService.getGoodsImageList(godNo);
			if(!Utils.empty(imageList)) {
				for(GodImg img : imageList) {
					if(String.valueOf(GoodsEnum.GoodsImageType.THNAIL).equals(img.getImgTpCd())) {
						result.setGodImg(img);
					}
				}
				result.setGodImgList(imageList);
			}
			
			//	동영상 조회
			GodMovi movi =  goodsContentService.getGoodsMovie(godNo);
			if(!Utils.empty(movi)) {				
				result.setGodMovi(movi);
			}
			
			//	구성정보 조회
			if(String.valueOf(GoodsEnum.GoodsType.SET_GOD).equals(god.getGodTpCd())) {				
				List<GodCpstGodCnncExtend> cpstList = goodsInfoService.getGoodsCompositionGoodsConnectionList(godNo);
				result.setGodCpstGodCnncExList(cpstList);
			}
			
			//	CS Memo 조회
			CsoCnsltMemo csoCnsltMemo = new CsoCnsltMemo();
			csoCnsltMemo.setGodNo(godNo);
			csoCnsltMemo = memoService.getCsoCnsltMemo(csoCnsltMemo);
			result.setCsoCnsltMemo(csoCnsltMemo);
			
			//	전시가격 생성
			PromotionBoDTO promotionBoDTO = new PromotionBoDTO();
			promotionBoDTO.setIcSect(String.valueOf(PromotionProcedureSect.GOD));
			promotionBoDTO.setGodNo(godNo);
			promotionService.updatePromotionGoodsPriceProcedure(promotionBoDTO);			
			
			// 연관상품 조회
			if("MBM".equals(BOSecurityUtil.getAuthMall().get("mallId").toString())) {
				List<GodRelateExtend> godRelateExtendList = goodsContentService.getGoodsRelateList(godNo);
				result.setGodRelateExList(godRelateExtendList);
			}
			
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			throw new RuntimeException();			
		}
		return result;
	}

	@Override
	public GoodsInfoResult updateGoods(GoodsDTO goodsDTO) {
		GoodsInfoResult result = new GoodsInfoResult();
		
		/**
		 * 상품 테이블 수정
		 * 
		 * 상품 언어별 상품명 테이블 수정
		 * 
		 * 상품 언어별 상품명 이력 테이블 등록
		 * 
		 * 상품 단품 테이블 수정
		 * 
		 * 상품 판매몰 테이블 수정
		 * 
		 * 전시카테고리 연결 상품 테이블 수정
		 * 
		 * 상품 고시정보 테이블 수정
		 * 
		 * 상품 구성상품 연결 테이블 수정
		 * 
		 * 상품 이미지 수정
		 * 
		 * 상품 동영상 수정
		 */
		
		try {
			God god = goodsDTO.getGod();
			String godNo = god.getGodNo();
			String godTpCd = god.getGodTpCd();
			
			if(StringService.isEmail(godNo)) {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));				
				return result;				
			}
			//	상품 테이블 수정			
			goodsInfoService.updateGoods(god);
			
			//	상품 언어별 상품명 테이블 수정
			if(!Utils.empty(goodsDTO.getGodLangbyGodNmList())) {
				for(GodLangbyGodNm langbyGodNm : goodsDTO.getGodLangbyGodNmList()) {
					langbyGodNm.setGodNo(godNo);				
					if(!Utils.empty(goodsInfoService.getGoodsLangByGoodsName(langbyGodNm.getGodNo(), langbyGodNm.getLangCd()))) {
						//	상품 언어별 상품명 테이블 수정
						goodsInfoService.updateGoodsLangByGoodsName(langbyGodNm);						
					}else {
						//	상품 언어별 상품명 테이블 등록						
						goodsPolicyService.setGoodsLangByGoodsNameDefaultPolicy(langbyGodNm);						
						goodsInfoService.insertGoodsLangByGoodsName(langbyGodNm);																
					}

					//	글로벌 영문/중문 상품명 이력 등록
					goodsHistoryService.insertGoodsLangByGoodsNameHistory(langbyGodNm);					
				}				
			}
			
			//	상품 단품 테이블 수정				
			if(!Utils.empty(goodsDTO.getGodItmExtendList())) {				
				for(GodItmExtend itm : goodsDTO.getGodItmExtendList()) {
					GodItm godItm = new GodItm();
					BeanUtils.copyProperties(itm, godItm);
					godItm.setGodNo(godNo);
					goodsPolicyService.setGoodsItemDefaultPolicy(god, godItm);
					goodsInfoService.updateGoodsItem(godItm);
				}
			}
			
			//	상품 판매몰 테이블 수정
			if(!Utils.empty(goodsDTO.getGodSaleMallList())) {
				goodsInfoService.deleteGoodsSaleMall(godNo);
				for(GodSaleMall saleMall : goodsDTO.getGodSaleMallList()) {
					saleMall.setGodNo(godNo);
					goodsInfoService.insertGoodsSaleMall(saleMall);
				}
			}
			
			//	전시카테고리 연결 상품 테이블 수정
			if(!Utils.empty(goodsDTO.getDspCtgryCnncGodList())) {
				goodsCategoryService.deleteDisplayCategoryConnectionGoods(godNo);
				for(DspCtgryCnncGod dspCtgryCnncGod : goodsDTO.getDspCtgryCnncGodList()) {
					dspCtgryCnncGod.setGodNo(godNo);
					goodsCategoryService.updateDisplayCategoryConnectionGoods(dspCtgryCnncGod);
				}				
			}
			
			//	상품 고시정보 테이블 수정
			if(!Utils.empty(goodsDTO.getGodNtfcDetailList())) {
				for(GodNtfcDetail detail : goodsDTO.getGodNtfcDetailList()) {
					detail.setGodNo(godNo);
					if(!Utils.empty(goodsInfoService.getGoodsNotificationDetail(detail.getGodNo(), detail.getLangCd()))) {						
						goodsInfoService.updateGoodsNotificationDetail(detail);
					}else {
						goodsPolicyService.setGoodsNotificationDetailDefaultPolicy(god, detail);
						goodsInfoService.insertGoodsNotificationDetail(detail);						
					}
				}
			}
			
			// 상품 이미지 수정
			if(!Utils.empty(goodsDTO.getGodImgExtendList())) {
				goodsContentService.deleteGodImg(godNo);
				for(GodImgExtend imgEx : goodsDTO.getGodImgExtendList()) {
					goodsPolicyService.setGoodsImageDefaultPolicy(god, imgEx);
					goodsContentService.insertGoodsImage(imgEx);					
				}
			}			
			
			//	상품 동영상 수정
			GodMovi movi = goodsDTO.getGodMovi();
			if(movi!=null){
				movi.setGodNo(godNo);
				goodsPolicyService.setGoodsMovieDefaultPolicy(movi);
				goodsContentService.insertGoodsMovie(movi);			
			}
			
			//	세트상품 상태 싱크
			if(String.valueOf(GoodsEnum.GoodsType.GNRL_GOD).equals(godTpCd)) {				
				goodsInfoService.updateSetStatus(godNo);
			}
			
			//	상품 구성 상품 연결 수정
			if(String.valueOf(GoodsEnum.GoodsType.SET_GOD).equals(godTpCd) && !Utils.empty(goodsDTO.getGodCpstGodCnncList())) {
				for(GodCpstGodCnnc cpstCnnc : goodsDTO.getGodCpstGodCnncList()) {												
					cpstCnnc.setGodNo(godNo);
					goodsInfoService.updateGoodsCompositionGoodsConnection(cpstCnnc);
				}
			}			
			
			// ERP SET(연관상품) 수정
			if(!Utils.empty(goodsDTO.getGodRelateExtendList())) {
				goodsInfoService.deleteGoodsRelate(godNo);
				for(GodRelateExtend godRelateEx : goodsDTO.getGodRelateExtendList()) {
					godRelateEx.setGodNo(godNo);
					goodsInfoService.insertGoodsRelate(godRelateEx);
				}
			}
			
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
			result.setRstMsg("저장 되었습니다.");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			throw new RuntimeException();			
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsBOComponent#insertMassGoods(java.util.List)
	 */
	@Override
	public List<GoodsExcelDTO> insertMassGoods(List<GoodsExcelDTO> list) {
		List<GoodsExcelDTO> resultList = null;

		BOSecurityUserDetail user = BOSecurityUtil.getCurrentUserDetail();

		if(!Utils.empty(list)) {
			resultList = new ArrayList<GoodsExcelDTO>();

			//	상품 유형 순으로 정렬(일반상품 -> 패키지 -> 세트)
			Collections.sort(list, new Comparator<GoodsExcelDTO>(){
				@Override
                public int compare(GoodsExcelDTO o1, GoodsExcelDTO o2) {
	                return o1.getGodTpCd().compareTo(o2.getGodTpCd());
                }
			});
			GoodsSearchDTO searchDTO;
			excelLoop : for(GoodsExcelDTO excelData : list) {
				Class<?> validClass = GoodsExcelDTO.GoodsInfoChecks.class;

				//	Excel Formet validation
				goodsInfoService.excelFormatValidation(excelData, validClass);

				if(String.valueOf(GoodsEnum.SUCCESS_CODE).equals(excelData.getValidCode())) {

					//	Insert GoodsDTO
					GoodsDTO goodsData = new GoodsDTO();

					String godTpCd 				= excelData.getGodTpCd();
					String erpGodNo 			= excelData.getErpGodNo();
					String shopPkupPsbYn		= excelData.getShopPkupPsbYn();
					String godNm 				= excelData.getGodNm();
					String mobileGodNm 			= excelData.getMobileGodNm();
					String epGodNm				= excelData.getEpGodNm();
					String malls 				= excelData.getMallId();
					String categoryNos 			= excelData.getDspCtgryNo();
					String godSrchSnm 			= excelData.getGodSrchSnm();

					InfGodItmExtend erpGodData = goodsErpService.getErpGoods(erpGodNo);

					if(Utils.empty(erpGodData)) {
						excelData.setValidText("ERP 상품 정보가 없습니다.");
						resultList.add(excelData);
						continue excelLoop;
					}
					searchDTO = new GoodsSearchDTO();
					searchDTO.setErpGodNo(erpGodNo);

					God god = goodsInfoService.getGoods(searchDTO);
					if(!Utils.empty(god)) {
						excelData.setValidText("등록된 상품입니다.");
						resultList.add(excelData);
						continue excelLoop;
					}

					String dsgnGrpNo = erpGodData.getDsgnGrpNo();
					String brndId = erpGodData.getBrndId();
					String prdlstCd = erpGodData.getPrdlstCd();

					if(!"SYS_OPRTR".equals(user.getSysAdmin().getAdminTpCd())) {
						String adminId = user.getSysAdmin().getAdminId();
						SysAdminBrnd adminBrnd = goodsErpService.getSysAdminBrnd(adminId, brndId);

						if(Utils.empty(adminBrnd)) {
							excelData.setValidText("권한이 없는 브랜드 상품입니다.");
							resultList.add(excelData);
							continue excelLoop;
						}
					}

					//	디자인 그룹 정보 설정
					GodDsgnGrp dsgnGrpData = new GodDsgnGrp();
					dsgnGrpData.setDsgnGrpNo(dsgnGrpNo);
					dsgnGrpData.setDsgnGrpNm(godNm);
					goodsData.setGodDsgnGrp(dsgnGrpData);

					//	상품 정보 설정
					god = new God();
					god.setErpGodNo(erpGodNo);
					god.setGodTpCd(godTpCd);
					god.setDsgnGrpNo(dsgnGrpNo);
					god.setGodNm(godNm);
					god.setMobileGodNm(mobileGodNm);
					god.setSesonCd(erpGodData.getSesonCd());
					god.setKcCrtfcNo(erpGodData.getKcCrtfcNo());
					god.setRtlPrc(erpGodData.getRtlPrc());
					god.setCsmrPrcTpCd(erpGodData.getCsmrPrcTpCd());
					// 추천성별 코드 추가 (20181023_ds)
					god.setRecomendSexCd(erpGodData.getRecomendSexCd());

					// 매장 픽업 가능 여부
					if(!Utils.empty(shopPkupPsbYn)) {
						shopPkupPsbYn = shopPkupPsbYn.toUpperCase();
						if(!String.valueOf(GoodsEnum.YES).equals(shopPkupPsbYn) && !String.valueOf(GoodsEnum.NO).equals(shopPkupPsbYn)) {
							excelData.setValidText("매장 픽업 가능 여부는 N 또는 Y만 입력이 가능합니다.");
							resultList.add(excelData);
							continue excelLoop;
						}
						god.setShopPkupPsbYn(shopPkupPsbYn);
					}
					
					//	SYS_BRND 조회
					SysBrnd sysBrnd = goodsErpService.getSystemBrand(brndId);
					if(Utils.empty(sysBrnd)) {
						excelData.setValidText("브랜드 정보가 없습니다.");
						resultList.add(excelData);
						continue excelLoop;
					}
					god.setBrndId(brndId);
					String brndGrpId = sysBrnd.getUpperBrndId();
					god.setBrndGrpId(brndGrpId);

					//	COM 조회
					String comId = user.getCom().getComId();
					god.setComId(comId);

					Map<String, String> paramMap = new HashMap<String, String>();
					paramMap.put("comId", comId);
					paramMap.put("mallId", malls);
					
					//	COM DeliveryPolicy 조회
					List<ComDmstcDlvCstPlc> dlvCstPlcList = goodsInfoService.getComDomesticDeliveryCostPolicy(paramMap);
					if(Utils.empty(dlvCstPlcList)) {
						excelData.setValidText("배송비 정책 정보가 없습니다.");
						resultList.add(excelData);
						continue excelLoop;
					}
					dlvLoop : for(ComDmstcDlvCstPlc plc : dlvCstPlcList) {
//						if(String.valueOf(GoodsEnum.YES).equals(plc.getBaseDlvCstPlcYn())) {
						if(String.valueOf(GoodsEnum.YES).equals(plc.getUseYn())) {
							god.setDmstcDlvCstPlcSn(plc.getDmstcDlvCstPlcSn());
							break dlvLoop;
						}
					}
					if(Utils.empty(god.getDmstcDlvCstPlcSn())) {
						excelData.setValidText("배송비 정책 정보가 없습니다.");
						resultList.add(excelData);
						continue excelLoop;
					}

					// COLOR 조회
					// MLB 일 경우 컬러코드를 => 팀 / 컬러로 쪼개 _ ds
					if("ML".equals(sysBrnd.getUpperBrndId())) {
						String mlbColorCd = StringService.trimToEmpty(erpGodData.getColorCd());
						// 일반상품
						if(String.valueOf(GoodsEnum.GoodsType.GNRL_GOD).equals(godTpCd)) {
							if(mlbColorCd.length() >= 3) {
								erpGodData.setTeamCd(mlbColorCd.substring(0,2));
								erpGodData.setColorCd(mlbColorCd.substring(mlbColorCd.length()-1, mlbColorCd.length()));
								// TeamCd Setting
								god.setTeamCd(erpGodData.getTeamCd());
							} else {
								erpGodData.setColorCd(mlbColorCd);
							}
						}
						// 세트상품 : 팀없음
						else if(String.valueOf(GoodsEnum.GoodsType.SET_GOD).equals(godTpCd)) {
							erpGodData.setColorCd(mlbColorCd);
						}
						// 그외 (사은품) : 팀없음, 끝자리하나 컬러 -> [수정] erp 컬러코드 그대로
						else {
							erpGodData.setColorCd(mlbColorCd);
						}
					}
					String colorCd = erpGodData.getColorCd();
					GodColor color = goodsErpService.getGoodsColor(colorCd, brndGrpId);
					if(Utils.empty(color)) {
						excelData.setValidText("컬러 정보가 없습니다.["+colorCd+"]");
						resultList.add(excelData);
						continue excelLoop;
					}
					god.setColorCd(colorCd);
					god.setColorNm(color.getColorNm());

					god.setMnfcturYearCd(StringService.mid(erpGodData.getMnfcturYm(), 0, 4));
					god.setGodSrchSnm(godSrchSnm);
					god.setPrdlstCd(prdlstCd);
					god.setEpGodNm(epGodNm);
					goodsData.setGod(god);

					//	언어별 상품명 설정
					List<GodLangbyGodNm> langbyGodNmList = new ArrayList<GodLangbyGodNm>();
					GodLangbyGodNm godLangbyGodNm = null;
					// 영문 상품명
					godLangbyGodNm = goodsInfoService.setLangbyGodNm("C", null, String.valueOf(GoodsEnum.ENG), excelData, erpGodData);
					langbyGodNmList.add(godLangbyGodNm);
					
					// 중문 상품명
					godLangbyGodNm = goodsInfoService.setLangbyGodNm("C", null, String.valueOf(GoodsEnum.CHI), excelData, erpGodData);
					langbyGodNmList.add(godLangbyGodNm);
					
					goodsData.setGodLangbyGodNmList(langbyGodNmList);

					//	단품정보 설정
					searchDTO = new GoodsSearchDTO();
					searchDTO.setErpGodNo(erpGodNo);
					searchDTO.setComId(comId);
					List<GodItmExtend> itemList = goodsErpService.getErpGoodsItem(searchDTO);
					if(Utils.empty(itemList)) {
						excelData.setValidText("옵션 정보가 없습니다.");
						resultList.add(excelData);
						continue excelLoop;
					}
					goodsData.setGodItmExtendList(itemList);

					// 연관 상품 리스트 설정 (20181204_ds)
					String erpDsgnGrpSetCont = erpGodData.getDsgnGrpSetCont();
					if(StringService.isNotEmpty(erpDsgnGrpSetCont)) {
						List<GodRelateExtend> godRelateExtendList = new ArrayList<GodRelateExtend>();
						String[] dsgnGrpSetConts = StringService.split(erpDsgnGrpSetCont, "|");
						for(int i=0; i<dsgnGrpSetConts.length; i++) {
							String dsgnGrpSetCont = dsgnGrpSetConts[i];
							GodRelateExtend godRelateExtend = new GodRelateExtend();
							godRelateExtend.setRelateGodNo(dsgnGrpSetCont);
							godRelateExtend.setRelateSectCd(String.valueOf(GoodsEnum.RELATESECTCD));
							godRelateExtend.setSortSeq(i+1);
							godRelateExtendList.add(godRelateExtend);
						}
						goodsData.setGodRelateExtendList(godRelateExtendList);
					}

					//	전시 카테고리 정보 설정
					if(String.valueOf(GoodsEnum.GoodsType.GNRL_GOD).equals(godTpCd)) {
						if(StringService.isEmpty(categoryNos)) {
							excelData.setValidText("전시카테고리는 필수입력항목입니다.");
							resultList.add(excelData);
							continue excelLoop;
						}

						int sortSeq = 1;
						String[] dspCtgryNos = StringService.split(categoryNos, "|");
						List<DspCtgryCnncGod> ctgryList = new ArrayList<DspCtgryCnncGod>();
						for(String vo : dspCtgryNos) {
							DspCtgry dspCtgry = goodsCategoryService.getDisplayCategory(vo);
							if(Utils.empty(dspCtgry)) {
								excelData.setValidText("잘못된 카테고리번호["+vo+"] 입니다.");
								resultList.add(excelData);
								continue excelLoop;
							}

							if(String.valueOf(GoodsEnum.NO).equals(dspCtgry.getLeafCtgryYn())) {
								excelData.setValidText("최하위 카테고리번호["+vo+"]가 아닙니다.");
								resultList.add(excelData);
								continue excelLoop;
							}

							DspCtgryCnncGod ctgry = new DspCtgryCnncGod();
							ctgry.setDspCtgryNo(vo);
							ctgry.setDspYn(String.valueOf(GoodsEnum.YES));
							ctgry.setRprstGodYn(String.valueOf(GoodsEnum.NO));
							ctgry.setBrndCtgryDspYn(String.valueOf(GoodsEnum.NO));
							ctgry.setSortSeq(sortSeq);
							ctgryList.add(ctgry);

							sortSeq++;
						}
						goodsData.setDspCtgryCnncGodList(ctgryList);
					}

					//	판매몰 정보 설정
					GodSaleMall mall;
					List<GodSaleMall> mallList = new ArrayList<GodSaleMall>();
					if(StringService.isEmpty(malls)) {

						mall = new GodSaleMall();

						if(String.valueOf(GoodsEnum.BrandGrpCode.DX).equals(brndGrpId)) {
							mall.setMallId(String.valueOf(GoodsEnum.SaleMall.DXM));
						}
						// 판매몰 추가에 따른 수정 필요 (20181101_ds)
						else if(String.valueOf(GoodsEnum.BrandGrpCode.ML).equals(brndGrpId)) {
							mall.setMallId(String.valueOf(GoodsEnum.SaleMall.MBM));
						}
						else if(String.valueOf(GoodsEnum.BrandGrpCode.SA).equals(brndGrpId)) {
							mall.setMallId(String.valueOf(GoodsEnum.SaleMall.SAM));
						}

						mall.setUseYn(String.valueOf(GoodsEnum.YES));
						mallList.add(mall);

						goodsData.setGodSaleMallList(mallList);

					} else {
						String[] mallIds = StringService.split(malls, "|");
						for(String vo : mallIds) {
							SysMall sysMall = goodsInfoService.getSystemMall(vo);
							if(Utils.empty(sysMall)) {
								excelData.setValidText("잘못된 전시몰ID["+vo+"] 입니다.");
								resultList.add(excelData);
								continue excelLoop;
							}

							mall = new GodSaleMall();
							mall.setMallId(vo);
							mall.setUseYn(String.valueOf(GoodsEnum.YES));
							mallList.add(mall);
						}
						goodsData.setGodSaleMallList(mallList);
					}

					//	고시정보 설정
					List<GodNtfcDetail> notificationList = new ArrayList<GodNtfcDetail>();
					GodNtfcDetail notification = new GodNtfcDetail();

					//	PRDLST_GRP_CD 조회
					SysBrndPrdlst brndPrdlst = goodsCategoryService.getSystemBrandProductlist(brndId, prdlstCd);

					if(Utils.empty(brndPrdlst)) {
						excelData.setValidText("잘못된 복종코드["+prdlstCd+"] 입니다.");
						resultList.add(excelData);
						continue excelLoop;
					}

					//	SIZE GUIDE 조회(조견표) 조회
					String sizeGdImgUrl = "";

					String sizeGdImgFileUrl = "";
					String sizeImg = excelData.getSizeImg();
					if(StringService.isNotEmpty(sizeImg)) {
						sizeGdImgFileUrl = "/goods/sizeguide/"+sizeImg+".jpg";
					}else {
						sizeGdImgFileUrl = brndPrdlst.getSizeGdImgFileUrl();
					}
					sizeGdImgUrl = goodsPolicyService.getBrandCloudFrontUrl(brndId)+sizeGdImgFileUrl;

					GoodsErpSizeChartResult sizeChart = goodsErpService.getErpGoodsSizeChart(dsgnGrpNo, prdlstCd, brndId, sizeGdImgUrl, itemList, brndGrpId, erpGodData);

					// 국문 세팅
					notification = goodsInfoService.setGodNtfcDetailByExcelInfo("C", null, String.valueOf(GoodsEnum.KOR), excelData, sizeChart, erpGodData, brndPrdlst);
					notificationList.add(notification);

					// 영문 세팅
					notification = goodsInfoService.setGodNtfcDetailByExcelInfo("C", null, String.valueOf(GoodsEnum.ENG), excelData, sizeChart, erpGodData, brndPrdlst);
					notificationList.add(notification);

					// 중문 세팅
					notification = goodsInfoService.setGodNtfcDetailByExcelInfo("C", null, String.valueOf(GoodsEnum.CHI), excelData, sizeChart, erpGodData, brndPrdlst);
					notificationList.add(notification);

					goodsData.setGodNtfcDetailList(notificationList);

					goodsData.setLoginId(user.getSysAdmin().getAdminId());
					GoodsInfoResult result = this.insertGoods(goodsData);

					if(String.valueOf(GoodsEnum.FAIL_CODE).equals(result.getRstCd())) {
						excelData.setValidText("상품정보 등록에 실패했습니다. 관리자에게 문의하세요.");
						resultList.add(excelData);
					}
				}
				else {
					resultList.add(excelData);
				}
			}
		}
		return resultList;
	}

	@Override
	public List<GoodsExcelDTO> updateMassGoods(List<GoodsExcelDTO> list) {
		List<GoodsExcelDTO> resultList = null;

		BOSecurityUserDetail user = BOSecurityUtil.getCurrentUserDetail();

		if(!Utils.empty(list)) {
			resultList = new ArrayList<GoodsExcelDTO>();

			//	상품 유형 순으로 정렬(일반상품 -> 패키지 -> 세트)
			Collections.sort(list, new Comparator<GoodsExcelDTO>(){
				@Override
                public int compare(GoodsExcelDTO o1, GoodsExcelDTO o2) {
	                return o1.getGodTpCd().compareTo(o2.getGodTpCd());
                }
			});
			GoodsSearchDTO searchDTO;
			excelLoop : for(GoodsExcelDTO excelData : list) {

				//	Update GoodsDTO
				GoodsDTO goodsData = new GoodsDTO();

				String godTpCd 				= excelData.getGodTpCd();
				String erpGodNo 			= excelData.getErpGodNo();
				String shopPkupPsbYn		= excelData.getShopPkupPsbYn();
				String godNm 				= excelData.getGodNm();
				String mobileGodNm 			= excelData.getMobileGodNm();
				String categoryNos 			= excelData.getDspCtgryNo();
				String godSrchSnm 			= excelData.getGodSrchSnm();
				String epGodNm				= excelData.getEpGodNm();

				if(Utils.empty(godTpCd)) {
					excelData.setValidText("상품유형은 필수입력항목입니다.");
					resultList.add(excelData);
					continue excelLoop;
				}
				
				if(Utils.empty(erpGodNo)) {
					excelData.setValidText("ERP품번은 필수입력항목입니다.");
					resultList.add(excelData);
					continue excelLoop;
				}
				
				if(!Utils.empty(godSrchSnm) && !StringService.byteCheck(godSrchSnm, 500)) {
					excelData.setValidText("검색키워드는 최대 500자(한글 포함 150자) 입니다.");
					resultList.add(excelData);
					continue excelLoop;
				}
				
				InfGodItmExtend erpGodData = goodsErpService.getErpGoods(erpGodNo);

				if(Utils.empty(erpGodData)) {
					excelData.setValidText("ERP 상품 정보가 없습니다.");
					resultList.add(excelData);
					continue excelLoop;
				}
				searchDTO = new GoodsSearchDTO();
				searchDTO.setErpGodNo(erpGodNo);

				God god = goodsInfoService.getGoods(searchDTO);
				if(Utils.empty(god)) {
					excelData.setValidText("등록되지 않은 상품입니다.");
					resultList.add(excelData);
					continue excelLoop;
				}

				String brndId = erpGodData.getBrndId();

				if(!"SYS_OPRTR".equals(user.getSysAdmin().getAdminTpCd())) {
					String adminId = user.getSysAdmin().getAdminId();
					SysAdminBrnd adminBrnd = goodsErpService.getSysAdminBrnd(adminId, brndId);

					if(Utils.empty(adminBrnd)) {
						excelData.setValidText("권한이 없는 브랜드 상품입니다.");
						resultList.add(excelData);
						continue excelLoop;
					}
				}

				//	상품명
				if(!Utils.empty(godNm)) {
					god.setGodNm(godNm);
				}
				if(!Utils.empty(mobileGodNm)) {
					god.setMobileGodNm(mobileGodNm);
				}

				//	매장 픽업 가능 여부
				if(!Utils.empty(shopPkupPsbYn)) {
					shopPkupPsbYn = shopPkupPsbYn.toUpperCase();
					if(!String.valueOf(GoodsEnum.YES).equals(shopPkupPsbYn) && !String.valueOf(GoodsEnum.NO).equals(shopPkupPsbYn)) {
						excelData.setValidText("매장 픽업 가능 여부는 N 또는 Y만 입력이 가능합니다.");
						resultList.add(excelData);
						continue excelLoop;
					}
					god.setShopPkupPsbYn(shopPkupPsbYn);
				}
				
				if(!Utils.empty(godSrchSnm)) {
					god.setGodSrchSnm(godSrchSnm);
				}

				if(!Utils.empty(epGodNm)) {
					god.setEpGodNm(epGodNm);
				}
				
				goodsData.setGod(god);

				// 각 언어별 상품명이 1개라도 있으면 상품명 설정
				if(!Utils.empty(excelData.getGodNmEng()) || !Utils.empty(excelData.getGodNmChi())
						|| !Utils.empty(excelData.getMobileGodNmEng()) || !Utils.empty(excelData.getMobileGodNmChi())
						) {
					// 언어별 상품명 설정
					List<GodLangbyGodNm> langbyGodNmList = goodsInfoService.getGoodsLangByGoodsNameList(god.getGodNo());
					List<GodLangbyGodNm> langbyGodNmListNew = new ArrayList<GodLangbyGodNm>();
					
					// 이미 등록되어 있는 언어별 상품명 정보를 조회하여 변경 세팅.
					if(!Utils.empty(langbyGodNmList)) {
						for(GodLangbyGodNm godLangbyGodNm : langbyGodNmList) {
							godLangbyGodNm = goodsInfoService.setLangbyGodNm("U", godLangbyGodNm, godLangbyGodNm.getLangCd(), excelData, erpGodData);
							langbyGodNmListNew.add(godLangbyGodNm);
						}
					}
					
					goodsData.setGodLangbyGodNmList(langbyGodNmListNew);
				}

				//	전시 카테고리 정보 설정
				if(String.valueOf(GoodsEnum.GoodsType.GNRL_GOD).equals(godTpCd)) {
					if(!StringService.isEmpty(categoryNos)) {
						int sortSeq = 1;
						String[] dspCtgryNos = StringService.split(categoryNos, "|");
						List<DspCtgryCnncGod> ctgryList = new ArrayList<DspCtgryCnncGod>();
						for(String vo : dspCtgryNos) {
							DspCtgry dspCtgry = goodsCategoryService.getDisplayCategory(vo);
							if(Utils.empty(dspCtgry)) {
								excelData.setValidText("잘못된 카테고리번호["+vo+"] 입니다.");
								resultList.add(excelData);
								continue excelLoop;
							}

							if(String.valueOf(GoodsEnum.NO).equals(dspCtgry.getLeafCtgryYn())) {
								excelData.setValidText("최하위 카테고리번호["+vo+"]가 아닙니다.");
								resultList.add(excelData);
								continue excelLoop;
							}

							DspCtgryCnncGod ctgry = new DspCtgryCnncGod();
							ctgry.setDspCtgryNo(vo);
							ctgry.setDspYn(String.valueOf(GoodsEnum.YES));
							ctgry.setRprstGodYn(String.valueOf(GoodsEnum.NO));
							ctgry.setBrndCtgryDspYn(String.valueOf(GoodsEnum.NO));
							ctgry.setSortSeq(sortSeq);
							ctgryList.add(ctgry);

							sortSeq++;
						}
						goodsData.setDspCtgryCnncGodList(ctgryList);
					}
				}

				// 각 언어별 제조국, 상품 상세 설명, 상품 상세 추가 설명, 사이즈 조견표 HTML, 모바일 사이즈 조견표 HTML 중 1개라도 있으면 고시정보 세팅
				if(!Utils.empty(excelData.getGodDetailDscr1()) || !Utils.empty(excelData.getGodDetailDscrEng1()) || !Utils.empty(excelData.getGodDetailDscrChi1())
						|| !Utils.empty(excelData.getGodDetailDscr2()) || !Utils.empty(excelData.getGodDetailDscrEng2()) || !Utils.empty(excelData.getGodDetailDscrChi2())
						|| !Utils.empty(excelData.getSizeLktbHtml()) || !Utils.empty(excelData.getSizeLktbHtmlEng()) || !Utils.empty(excelData.getSizeLktbHtmlChi())
						|| !Utils.empty(excelData.getMobileSizeLktbHtml()) || !Utils.empty(excelData.getMobileSizeLktbHtmlEng()) || !Utils.empty(excelData.getMobileSizeLktbHtmlChi())
						|| !Utils.empty(excelData.getMnfcturNationNm()) || !Utils.empty(excelData.getMnfcturNationNmEng()) || !Utils.empty(excelData.getMnfcturNationNmChi())
						) {
					// 고시정보 설정
					List<GodNtfcDetail> notificationList = goodsInfoService.getGoodsNotificationDetailList(god.getGodNo());
					List<GodNtfcDetail> notificationListNew = new ArrayList<GodNtfcDetail>();
					
					// 이미 등록되어 있는 언어별 고시정보를 조회하여 변경 세팅.
					if(!Utils.empty(notificationList)) {
						for(GodNtfcDetail detail : notificationList) {
							detail = goodsInfoService.setGodNtfcDetailByExcelInfo("U", detail, detail.getLangCd(), excelData, null, erpGodData, null);
							notificationListNew.add(detail);
						}
					}

					goodsData.setGodNtfcDetailList(notificationListNew);
				}
				goodsData.setLoginId(user.getSysAdmin().getAdminId());
				GoodsInfoResult result = this.updateGoods(goodsData);

				if(String.valueOf(GoodsEnum.FAIL_CODE).equals(result.getRstCd())) {
					excelData.setValidText("상품정보 수정에 실패했습니다. 관리자에게 문의하세요.");
					resultList.add(excelData);
				}
			}
		}
		return resultList;
	}
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsBOComponent#getGodColorList(java.lang.String)
	 */
	@Override
	public List<GodColorExtend> getGodColorList(String brndGrpId) {
		return goodsInfoService.getGodColorList(brndGrpId);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsBOComponent#getComDomesticDeliveryCostPolicy(java.lang.String)
	 */
	@Override
	public List<ComDmstcDlvCstPlc> getComDomesticDeliveryCostPolicy(String comId, String mallId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("comId", comId);
		if(mallId != null) {
			paramMap.put("mallId", mallId);
		}
		return goodsInfoService.getComDomesticDeliveryCostPolicy(paramMap);
	}
	
	public void tempUpdateSize() {
		List<God> gods = goodsInfoService.getTempGoods();
		System.out.println("gods.size() : ======================= "+gods.size()+" =======================");
		
		for(God god : gods) {
			// 단품정보 설정
			GoodsSearchDTO searchDTO = new GoodsSearchDTO();
			searchDTO.setErpGodNo(god.getErpGodNo());					
			searchDTO.setComId("M00");
			List<GodItmExtend> itemList = goodsErpService.getErpGoodsItem(searchDTO);
			if(Utils.empty(itemList)) {
				System.out.println("======================= 옵션 정보가 없습니다 =======================");
				continue;
			}		
			
//			고시정보 설정
			List<GodNtfcDetail> notificationList = new ArrayList<GodNtfcDetail>();
			GodNtfcDetail notification = new GodNtfcDetail();
			String godNo = god.getGodNo();
			String brndId = god.getBrndId();
			String prdlstCd = god.getPrdlstCd();
			String dsgnGrpNo = god.getDsgnGrpNo();
			String brndGrpId = god.getBrndGrpId();

			//	PRDLST_GRP_CD 조회
			SysBrndPrdlst brndPrdlst = goodsCategoryService.getSystemBrandProductlist(brndId, prdlstCd);
			
			if(Utils.empty(brndPrdlst)) {
				System.out.println("======================= 잘못된 복종코드 입니다 =======================");
				continue ;						
			}
			
			//	SIZE GUIDE 조회(조견표) 조회
			String sizeGdImgFileUrl = brndPrdlst.getSizeGdImgFileUrl();								
			String sizeGdImgUrl = "https://static.mlb-korea.com/images"+sizeGdImgFileUrl;
			
			GoodsErpSizeChartResult sizeChart = goodsErpService.getTempSizeChart(dsgnGrpNo, prdlstCd, brndId, sizeGdImgUrl, itemList, brndGrpId);
			
			notification.setGodNo(godNo);
			notification.setLangCd(String.valueOf(GoodsEnum.KOR));					
			notification.setSizeLktbHtml(sizeChart.getSizeLktb());
			notification.setMobileSizeLktbHtml(sizeChart.getSizeLktb());
			notificationList.add(notification);
			
			notification = new GodNtfcDetail();
			notification.setGodNo(godNo);
			notification.setLangCd(String.valueOf(GoodsEnum.ENG));
			notification.setSizeLktbHtml(sizeChart.getSizeLktbEng());
			notification.setMobileSizeLktbHtml(sizeChart.getSizeLktbEng());					
			notificationList.add(notification);
			
			notification = new GodNtfcDetail();
			notification.setGodNo(godNo);
			notification.setLangCd(String.valueOf(GoodsEnum.CHI));
			notification.setSizeLktbHtml(sizeChart.getSizeLktbChi());
			notification.setMobileSizeLktbHtml(sizeChart.getSizeLktbChi());					
			notificationList.add(notification);
			
			for(GodNtfcDetail detail : notificationList) {
				goodsInfoService.updateTempNoti(detail);
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsBOComponent#searchStyleConnectionList(com.plgrim.ncp.biz.goods.data.GoodsSearchDTO)
	 */
	@Override
	public Page<GoodsColorResult> searchStyleConnectionList(GoodsSearchDTO goodsSearchDTO) {
		return goodsInfoService.searchStyleConnectionList(goodsSearchDTO);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsBOComponent#deleteStyleConnectionList(java.util.List)
	 */
	@Override
	public void deleteStyleConnectionList(List<GoodsDTO> gridList) {
		for(GoodsDTO dto : gridList){		
			if(!Utils.empty(dto.getGodCnncGrpGod())){
				goodsInfoService.deleteStyleConnection(dto.getGodCnncGrpGod());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsBOComponent#insertMassGoodsStyleConnection(java.util.List)
	 */
	@Override
	public List<GoodsExcelDTO> insertMassGoodsStyleConnection(List<GoodsExcelDTO> list) {
		//	Validation Check
		boolean valid = true;
		List<GoodsExcelDTO> rtnList = null;
		
		BOSecurityUserDetail user = BOSecurityUtil.getCurrentUserDetail();
		
		if(!(list.isEmpty())){		
			List<String> godNos = new ArrayList<String>();			
			rtnList = new ArrayList<GoodsExcelDTO>();
						
			for(GoodsExcelDTO excelData : list){
				Class<?> validClass = GoodsExcelDTO.GoodsStyleConnctionChecks.class;
				//	Excel Formet validation
				goodsInfoService.excelFormatValidation(excelData, validClass);
								
				if(String.valueOf(GoodsEnum.SUCCESS_CODE).equals(excelData.getValidCode())) {
					String erpGodNo = StringService.trimToEmpty(excelData.getErpGodNo());
					
					if(!StringService.isNumeric(excelData.getGroup())){
						excelData.setValidText("연결번호는 숫자만 입력 가능합니다.");
						valid = false;
						break;						
					}
					
					GoodsSearchDTO goodsSearchDTO = new GoodsSearchDTO();
					goodsSearchDTO.setErpGodNo(erpGodNo);
					God god = goodsInfoService.getGoods(goodsSearchDTO);
					
					if(Utils.empty(god)){
						excelData.setValidText("ERP 상품 정보가 없습니다.");
						valid = false;
						break;
					}
				
					godNos.add(god.getGodNo());
					int brandCount = goodsInfoService.getGoodsBrandCount(godNos);
					
					if(brandCount > 1) {
						excelData.setValidText("그룹에 다른 브랜드 상품이 포함 되어 있습니다.");
						valid = false;
						break;						
					}
					
				}
			}
			
			if(valid){
				// insert
				goodsInfoService.insertStyleConnectionGroups(godNos, user.getSysAdmin().getAdminId());				
			}else{
				rtnList.addAll(list);						
			}			

		}		
		return rtnList;
	}

	@Override
	public List<GoodsColorResult> searchStyleCnncView(GoodsSearchDTO goodsSearchDTO) {
		return goodsInfoService.searchStyleCnncView(goodsSearchDTO);
	}
	
	@Override
	public List<GoodsExcelDTO> addStyleCnncs(GoodsSearchDTO goodsSearchDTO) {
		BOSecurityUserDetail user = BOSecurityUtil.getCurrentUserDetail();
		boolean valid = true;
		List<GoodsExcelDTO> resultList = new ArrayList<GoodsExcelDTO>();
		List<GoodsExcelDTO> resultAllList = new ArrayList<GoodsExcelDTO>();
		String erpGodCnncGrpNo = goodsSearchDTO.getErpGodCnncGrpNo();
		List<String> godNos = new ArrayList<String>();	
		excelLoop :for (int i = 0; i < goodsSearchDTO.getErpGodNos().length; i++) {
			
			String erpGodNo = goodsSearchDTO.getErpGodNos()[i];
			GoodsExcelDTO excelData = new GoodsExcelDTO();
			excelData.setErpGodNo(erpGodNo);
			GoodsSearchDTO searchDTO = new GoodsSearchDTO();
			searchDTO.setErpGodNo(erpGodNo);
			God god = goodsInfoService.getGoods(searchDTO);
			
			if(Utils.empty(god)){
				excelData.setValidText("ERP 상품 정보가 없습니다.");
				resultList.add(excelData);
				resultAllList.add(excelData);
				continue excelLoop;
			}
		
			GodCnncGrpGod	godCnncGrpGod = new GodCnncGrpGod();
			godCnncGrpGod.setErpGodCnncGrpNo(erpGodCnncGrpNo);
			godCnncGrpGod.setErpGodNo(erpGodNo);
			godCnncGrpGod =	goodsInfoService.selectGodCnncGrpGod(godCnncGrpGod);
			
			if(godCnncGrpGod != null){
				excelData.setValidText("이미 등록되어 있습니다.");
				resultList.add(excelData);
				resultAllList.add(excelData);
				continue excelLoop;
			}
			
			godNos.add(god.getGodNo());
			int brandCount = goodsInfoService.getGoodsBrandCount(godNos);
			
			if(brandCount > 1) {
				excelData.setValidText("그룹에 다른 브랜드 상품이 포함 되어 있습니다.");
				resultList.add(excelData);
				resultAllList.add(excelData);
				valid = false;
				continue excelLoop;					
			}
			resultAllList.add(excelData);
 		}
		
		if(valid && godNos.size() >0){
			// insert
			goodsInfoService.addtyleConnectionGroups(godNos, user.getSysAdmin().getAdminId(),erpGodCnncGrpNo);				
		}else{
			return resultAllList;					
		}
		return resultList;	
	 
	}

	
}
