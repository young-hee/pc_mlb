package com.plgrim.ncp.commons.service;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodDsgnGrp;
import com.plgrim.ncp.base.entities.datasource1.god.GodImgExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.god.GodMovi;
import com.plgrim.ncp.base.entities.datasource1.god.GodNtfcDetail;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.repository.god.GodRepository;
import com.plgrim.ncp.base.repository.sys.SysBrndRepository;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.commons.StringService;
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
 * Description	:	상품 정책 서비스
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 16.
 * @Version	:	
 *
 */

@Service
public class GodPlcService extends AbstractService {
	
	@Autowired
	GodRepository godRepository;
	
	@Autowired
	SysBrndRepository sysBrndRepository;

	/**
	 * Discovery ERP 성별코드에 따른 상품 추천성별 리턴
	 * 
	 * @param brndId
	 * @param sexCd
	 * @return
	 */
	public String getDiscoveryRecomendSexCd(String erpNo) {
		String recomendSexCd = "";
		String erpNoSexCd = StringService.mid(erpNo, 1, 1); 
				
		if("M".equals(erpNoSexCd)) {
			return recomendSexCd = "MEN";
		}
		if("W".equals(erpNoSexCd)) {
			return recomendSexCd = "WOMEN";
		}		
		if("X".equals(erpNoSexCd)) {
			return recomendSexCd = "CMNUSE";
		}		
		if("K".equals(erpNoSexCd)) {
			return recomendSexCd = "CHIL";
		}		
		return recomendSexCd;
	}
	
	/**
	 * 상품명 정책 가져오기
	 * 
	 * @param source
	 * @return
	 */
	public String getGoodsNamePolicy(String source) {
		if(StringService.isEmpty(source)) {
			return source;
		}
		String [] regExp = {"'", "\"", "<", ">", "=", ";"};
		for(int i=0; i<regExp.length; i++){
			if(StringService.contains(source, regExp[i])){
				source = source.replace(regExp[i], "");
			}
		}
		
		if(source.getBytes().length > 500) {			
			if(source.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
				source = source.substring(0, 150);
			}else {
				source = source.substring(0, 500);
			}			
		}		
		return source;
	}
	
	/**
	 * 상품 디자인 그룹 테이블 기본값 설정
	 * 		
	 * 	- DSGN_GRP_NM, RPRST_GOD_NO, RPRST_ERP_GOD_NO
	 * 
	 * 
	 * @param godDsgnGrp
	 * @return
	 */
	public GodDsgnGrp setGoodsDesignGroupDefaultPolicy(GodDsgnGrp godDsgnGrp, God god) {
		//	디자인 그룹명
		String dsgnGrpNm = godDsgnGrp.getDsgnGrpNm();
		godDsgnGrp.setDsgnGrpNm(getGoodsNamePolicy(dsgnGrpNm));
		//	대표 상품번호
		godDsgnGrp.setRprstGodNo(god.getGodNo());
		//	대표 ERP 상품번호
		godDsgnGrp.setRprstErpGodNo(god.getErpGodNo());		
		
		return godDsgnGrp;
	}
	
	
	/**
	 * 상품 테이블 기본값 설정
	 * 
	 * 	- 반드시 입력을 받아야 하는 항목은 제외
	 * 	   GOD_NO, ERP_GOD_NO, GOD_TP_CD, BRND_ID, BRND_GRP_ID, RTL_PRC, CSMR_PRC
	 * 
	 * @param god
	 * @return
	 */
	public God setGoodsDefaultPolicy(God god) {
		//	상품명		
		String godNm = god.getGodNm();
		god.setGodNm(getGoodsNamePolicy(godNm));
		//	모바일 상품명
		String mobileGodNm = god.getMobileGodNm();
		god.setMobileGodNm(getGoodsNamePolicy(mobileGodNm));
		//	유사검색어
		String godSrchSnm = god.getGodSrchSnm();
		god.setGodSrchSnm(getGoodsNamePolicy(godSrchSnm));
		// EP 상품명
		String epGodNm = god.getEpGodNm();
		god.setEpGodNm(getGoodsNamePolicy(epGodNm));
		//	승인상태
		if(StringService.isEmpty(god.getGodAprvSectCd())) {
			god.setGodAprvSectCd(String.valueOf(GoodsEnum.GoodsAprvState.APRV_WAIT));
		}		
		//	판매상태
		if(StringService.isEmpty(god.getGodSaleSectCd())) {
			god.setGodSaleSectCd(String.valueOf(GoodsEnum.GoodsSaleState.SMTM_SLDOUT));
		}
		//	과/면세 구분
		if(StringService.isEmpty(god.getTdfSect())) {
			god.setTdfSect(String.valueOf(GoodsEnum.DEFAULT_TAX_SECT));
		}
		//	시즌 그룹
		if(StringService.isNotEmpty(god.getSesonCd())) {

			if(StringService.contains(god.getSesonCd(), "S")) {
				god.setSesonGrpCd(String.valueOf(GoodsEnum.GoodsSeasonGroup.SS));
			}
			if(StringService.contains(god.getSesonCd(), "F")) {
				god.setSesonGrpCd(String.valueOf(GoodsEnum.GoodsSeasonGroup.FW));
			}			
		}
		//	입점구분
		if(StringService.isEmpty(god.getPartmalSectCd())) {
			god.setPartmalSectCd(String.valueOf(GoodsEnum.GoodsPartmal.MCOM));
		}
		//	임직원 전용 여부
		if(StringService.isEmpty(god.getEmpOnlyYn())) {
			god.setEmpOnlyYn(String.valueOf(GoodsEnum.NO));
		}
		//	주문 제작 상품 여부
		if(StringService.isEmpty(god.getOrdmkGodYn())) {
			god.setOrdmkGodYn(String.valueOf(GoodsEnum.NO));
		}
		//	온라인 전용 상품 여부
		if(StringService.isEmpty(god.getOnlneOnlyGodYn())) {
			god.setOnlneOnlyGodYn(String.valueOf(GoodsEnum.NO));
		}
		//	무료 수선 가능 여부
		if(StringService.isEmpty(god.getFreeRepairPsbYn())) {
			god.setFreeRepairPsbYn(String.valueOf(GoodsEnum.NO));
		}
		//	서비스 제공 여부
		if(StringService.isEmpty(god.getSvcOfferYn())) {
			god.setSvcOfferYn(String.valueOf(GoodsEnum.NO));
		}
		//	퀵 배송 가능 여부
		if(StringService.isEmpty(god.getQdlvPsbYn())) {
			god.setQdlvPsbYn(String.valueOf(GoodsEnum.NO));
		}
		//	예약 판매 상품 여부
		if(StringService.isEmpty(god.getResveSaleGodYn())) {
			god.setResveSaleGodYn(String.valueOf(GoodsEnum.NO));
		}
		//	상품 출고 대기 시간
		if(Utils.empty(god.getGodDlivyWaitTime())) {
			god.setGodDlivyWaitTime(new BigDecimal("48"));
		}
		//	국내 배송비 정책 일련번호
		if(Utils.empty(god.getDmstcDlvCstPlcSn())) {
			god.setDmstcDlvCstPlcSn(new Long(String.valueOf(GoodsEnum.Number.ONE)));
		}
		// 해외 배송 가능 여부 / 해외 판매 가능 여부
		if(StringService.isEmpty(god.getOvseaDlvPsbYn())) {
			god.setOvseaDlvPsbYn(String.valueOf(GoodsEnum.NO));
		}		
		// 매장 픽업 가능 여부
		if(Utils.empty(god.getShopPkupPsbYn())) {
			god.setShopPkupPsbYn(String.valueOf(GoodsEnum.NO));
		}
		//	편의점 택배 회수 가능 여부
		if(StringService.isEmpty(god.getCvnstorHdryRtrvlPsbYn())) {
			god.setCvnstorHdryRtrvlPsbYn(String.valueOf(GoodsEnum.NO));
		}
		//	단일 매장 출고 여부
		if(StringService.isEmpty(god.getSinglShopDlivyYn())) {
			god.setSinglShopDlivyYn(String.valueOf(GoodsEnum.NO));
		}
		//	전시 여부
		if(StringService.isEmpty(god.getDspYn())) {
			god.setDspYn(String.valueOf(GoodsEnum.NO));
		}
		//	특정 URL 전시 여부
		if(StringService.isEmpty(god.getSpcifyUrlDspYn())) {
			god.setSpcifyUrlDspYn(String.valueOf(GoodsEnum.NO));
		}
		//	가격 비교 사이트 노출 여부
		if(StringService.isEmpty(god.getPrcCmprSiteExpsrYn())) {
			god.setPrcCmprSiteExpsrYn(String.valueOf(GoodsEnum.YES));
		}
		//	모바일 상품 설명 노출 여부	
		if(StringService.isEmpty(god.getMobileGodDscrExpsrYn())) {
			god.setMobileGodDscrExpsrYn(String.valueOf(GoodsEnum.NO));
		}
		//	안전 인증 대상 여부
		if(StringService.isEmpty(god.getSafeCrtfcTgtYn())) {
			god.setSafeCrtfcTgtYn(String.valueOf(GoodsEnum.NO));
		}
		//	재고 관리 여부
		if(StringService.isEmpty(god.getInvManageYn())) {
			god.setInvManageYn(String.valueOf(GoodsEnum.YES));
		}
		//	한정 재고 여부
		if(StringService.isEmpty(god.getLmttInvYn())) {
			god.setLmttInvYn(String.valueOf(GoodsEnum.NO));
		}
		//	ERP 재고 연동 여부
		if(StringService.isEmpty(god.getErpInvIntrlckYn())) {
			god.setErpInvIntrlckYn(String.valueOf(GoodsEnum.YES));
		}
		//	CDC 재고 전용 여부
		if(StringService.isEmpty(god.getCdcInvOnlyYn())) {
			god.setCdcInvOnlyYn(String.valueOf(GoodsEnum.NO));
		}
		//	최소 주문 수량
		if(Utils.empty(god.getMinOrdQty())) {
			god.setMinOrdQty(new Long(String.valueOf(GoodsEnum.DEFAULT_MIN_ORD_QTY)));
		}
		//	최소 주문 수량
		if(Utils.empty(god.getMaxOrdQty())) {
			god.setMaxOrdQty(new Long(String.valueOf(GoodsEnum.DEFAULT_MAX_ORD_QTY)));
		}
		//	아울렛 여부
		if(StringService.isEmpty(god.getOtltYn())) {
			god.setOtltYn(String.valueOf(GoodsEnum.NO));
		}		
		//	MD 적용 즉시 할인 여부
		if(StringService.isEmpty(god.getMdAplImdtlDcYn())) {
			god.setMdAplImdtlDcYn(String.valueOf(GoodsEnum.NO));
		}
		//	즉시 할인 여부
		if(StringService.isEmpty(god.getImdtlDcYn())) {
			god.setImdtlDcYn(String.valueOf(GoodsEnum.NO));
		}
		//	제조 국가 변경 여부
		if(StringService.isEmpty(god.getMnfcturNationModYn())) {
			god.setMnfcturNationModYn(String.valueOf(GoodsEnum.NO));
		}
		
		String brndId = god.getBrndId();
		SysBrnd brndParam = new SysBrnd();
		brndParam.setBrndId(brndId);
		SysBrnd sysBrnd = sysBrndRepository.selectSysBrnd(brndParam);
				
		BigDecimal rtlPrc = god.getRtlPrc();
		
		//	실소가
		if(sysBrnd.getCsmrPrcDcRt().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal csmrPrc = rtlPrc.subtract(rtlPrc.multiply(sysBrnd.getCsmrPrcDcRt().divide(new BigDecimal(String.valueOf(GoodsEnum.Number.HUNDRED)))));	
			god.setCsmrPrc(csmrPrc.setScale(0, BigDecimal.ROUND_DOWN));
		}else {
			god.setCsmrPrc(rtlPrc);
		}		
		//	임직원가		
		if(sysBrnd.getEmpPrcDcRt().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal empPrc = rtlPrc.subtract(rtlPrc.multiply(sysBrnd.getEmpPrcDcRt().divide(new BigDecimal(String.valueOf(GoodsEnum.Number.HUNDRED)))));
			god.setEmpPrc(empPrc.setScale(0, BigDecimal.ROUND_DOWN));
		}else {
			god.setEmpPrc(rtlPrc);
		}		
		//	포인트 적립 여부
		if(StringService.isEmpty(god.getPntAccmlYn())) {			
			god.setPntAccmlYn(sysBrnd.getPntAccmlYn());
		}
		//	포인트 사용 여부
		if(StringService.isEmpty(god.getPntUseYn())) {
			god.setPntUseYn(sysBrnd.getPntUseYn());
		}
		//	포인트 적립율
		if(Utils.empty(god.getPntAccmlRt())) {			
			god.setPntAccmlRt(sysBrnd.getPntAccmlRt());
		}
		//	웹포인트 적립 여부
		if(StringService.isEmpty(god.getWebpntAccmlYn())) {			
			god.setWebpntAccmlYn(sysBrnd.getWebpntAccmlYn());
		}
		//	웹포인트 사용 여부
		if(StringService.isEmpty(god.getWebpntUseYn())) {			
			god.setWebpntUseYn(sysBrnd.getWebpntUseYn());
		}
		//	웹포인트 적립 율
		if(Utils.empty(god.getWebpntAccmlRt())) {			
			god.setWebpntAccmlRt(sysBrnd.getWebpntAccmlRt());
		}		
		if(String.valueOf(GoodsEnum.BrandGrpCode.DX).equals(sysBrnd.getUpperBrndId())) {
			//	추천 성별
			if(StringService.isEmpty(god.getRecomendSexCd())) {
				god.setRecomendSexCd(this.getDiscoveryRecomendSexCd(god.getErpGodNo()));
			}
			//	품목코드 설정			
			if(String.valueOf(GoodsEnum.GoodsType.SET_GOD).equals(god.getGodTpCd())) {
				god.setPrdlstCd("ZZ");				
			}
		}
		return god;
	}
	
	/**
	 * 상품 언어별 상품명 기본값 설정
	 * 
	 * 	- GOD_NM, MOBILE_GOD_NM, TRSLT_STAT_CD, OVSEA_DSP_STAT_CD
	 * 
	 * @param langByGodNm
	 * @return
	 */
	public GodLangbyGodNm setGoodsLangByGoodsNameDefaultPolicy(GodLangbyGodNm langByGodNm) {		
		//	상품명
		String godNm = langByGodNm.getGodNm();
		langByGodNm.setGodNm(getGoodsNamePolicy(godNm));
		//	모바일 상품명
		String mobileGodNm = langByGodNm.getMobileGodNm();
		langByGodNm.setMobileGodNm(getGoodsNamePolicy(mobileGodNm));
		// 전시승인여부
		if(StringService.isEmpty(langByGodNm.getOvseaDspStatCd())) {			
			langByGodNm.setOvseaDspStatCd(String.valueOf(GoodsEnum.GoodsOvseaDspState.DSP_WAIT));
		}	
		//	번역여부
		langByGodNm.setTrsltStatCd(String.valueOf(GoodsEnum.GoodsTransState.TRSLT_COMPT));
		return langByGodNm;
	}
	
	
	/**
	 * 상품 단품 테이블 기본값 설정
	 * 	SAFE_INV_USE_YN, PARTMAL_COM_INV_NTCN_YN
	 * 
	 * @param godItm
	 * @return
	 */
	public GodItm setGoodsItemDefaultPolicy(God god, GodItm godItm) {		
		//	SKU NO 설정
		if(StringService.isEmpty(godItm.getSkuNo())) {
			godItm.setSkuNo(god.getErpGodNo() + godItm.getItmNm());
		}
		//	판매상태 설정
		if(StringService.isEmpty(godItm.getItmStatCd())) {
			godItm.setItmStatCd(String.valueOf(GoodsEnum.GoodsSaleState.SMTM_SLDOUT));
		}
		//	안전 재고 사용 여부
		if(StringService.isEmpty(godItm.getSafeInvUseYn())) {
			godItm.setSafeInvUseYn(String.valueOf(GoodsEnum.NO));	
		}
		// 업체 재고 알림 기본		
		if(StringService.isEmpty(godItm.getPartmalComInvNtcnYn())) {			
			godItm.setPartmalComInvNtcnYn(String.valueOf(GoodsEnum.NO));
		}		
		//	Discovery
		if(String.valueOf(GoodsEnum.BrandGrpCode.DX).equals(god.getBrndGrpId())) {
			godItm.setUsefulInvQtyShopId(String.valueOf(GoodsEnum.ShopCode.DXM_ONLINE));
		}
		// MLB
		else if(String.valueOf(GoodsEnum.BrandGrpCode.ML).equals(god.getBrndGrpId())) {
			// MLB
			if("M".equals(god.getBrndId())) {
				godItm.setUsefulInvQtyShopId(String.valueOf(GoodsEnum.ShopCode.MLB_ONLINE));
			}
			// MLB KIDS
			else {
				godItm.setUsefulInvQtyShopId(String.valueOf(GoodsEnum.ShopCode.MLB_KIDS_ONLINE));
			}
		}
		// SA
		else if(String.valueOf(GoodsEnum.BrandGrpCode.SA).equals(god.getBrndGrpId())) {
			godItm.setUsefulInvQtyShopId(String.valueOf(GoodsEnum.ShopCode.SAM_ONLINE));
		}
		
		return godItm; 
	}
	
	
	/**
	 * 상품 고시상세 테이블 기본값 설정
	 * 	KFDA_JDGMN_YN, FNCL_CSMT_YN
	 * 
	 * @param godNtfcDetail
	 * @return
	 */
	public GodNtfcDetail setGoodsNotificationDetailDefaultPolicy(God god, GodNtfcDetail godNtfcDetail) {
		//	식품의약품안전처 심사 여부
		if(StringService.isEmpty(godNtfcDetail.getKfdaJdgmnYn())) {
			godNtfcDetail.setKfdaJdgmnYn(String.valueOf(GoodsEnum.NO));
		}
		//	기능성 화장품 여부
		if(StringService.isEmpty(godNtfcDetail.getFnclCsmtYn())) {
			godNtfcDetail.setFnclCsmtYn(String.valueOf(GoodsEnum.NO));
		}		
		if(String.valueOf(GoodsEnum.BrandGrpCode.DX).equals(god.getBrndGrpId())) {			
			//	품목그룹코드 설정			
			if(String.valueOf(GoodsEnum.GoodsType.SET_GOD).equals(god.getGodTpCd())) {
				godNtfcDetail.setPrdlstGrpCd("PDL_ETC");				
			}
		}		
		return godNtfcDetail;
	}
	
	/**
	 * 상품 최초 전시일 설정 조건
	 * 
	 * @param god
	 * @return
	 */
	public God setFirstDisplayDate(God god) {
		if(StringService.isEmpty(god.getGodNo())) {
			God bGod = godRepository.selectGod(god);
			
			if(Utils.empty(bGod.getFirstDspDt()) 
				&& String.valueOf(GoodsEnum.YES).equals(bGod.getDspYn())	
				&& String.valueOf(GoodsEnum.GoodsAprvState.APRV_COMPT).equals(bGod.getGodAprvSectCd())
				&& !(String.valueOf(GoodsEnum.GoodsSaleState.SALE_END).equals(bGod.getGodSaleSectCd()))
			) {
				Timestamp toDay = new Timestamp(System.currentTimeMillis());
				god.setFirstDspDt(toDay);
			}
		}
		return god;
	}
	
	/**
	 * 상품 이미지 등록을 위한 기본 파라미터 설정
	 * 
	 * @param god
	 * @param imgEx
	 * @return
	 */
	public GodImgExtend setGoodsImageDefaultPolicy(God god, GodImgExtend imgEx) {
		imgEx.setGodNo(god.getGodNo());
		imgEx.setErpGodNo(god.getErpGodNo());
		imgEx.setBrndId(god.getBrndId());
		
		//	브랜드에 따른 S3 위치 설정	
		if(String.valueOf(GoodsEnum.BrandGrpCode.DX).equals(god.getBrndGrpId())) {
			imgEx.setCloudFrontType("DXM");			
			if(StringService.containsIgnoreCase("l", imgEx.getImgTpCd()) || StringService.containsIgnoreCase("m", imgEx.getImgTpCd())) {
				imgEx.setImgTpCd(String.valueOf(GoodsEnum.GoodsImageType.THNAIL));
			}
			
			if(StringService.containsIgnoreCase("d", imgEx.getImgTpCd())) {
				imgEx.setImgTpCd(String.valueOf(GoodsEnum.GoodsImageType.DETAIL));
			}			
		}	
		// MLB, SA 추가 _ ds
		else if(String.valueOf(GoodsEnum.BrandGrpCode.ML).equals(god.getBrndGrpId())) {
			imgEx.setCloudFrontType("MBM");			
			if(StringService.containsIgnoreCase("l", imgEx.getImgTpCd()) || StringService.containsIgnoreCase("m", imgEx.getImgTpCd())) {
				imgEx.setImgTpCd(String.valueOf(GoodsEnum.GoodsImageType.THNAIL));
			}
			
			if(StringService.containsIgnoreCase("d", imgEx.getImgTpCd())) {
				imgEx.setImgTpCd(String.valueOf(GoodsEnum.GoodsImageType.DETAIL));
			}	
		} else if(String.valueOf(GoodsEnum.BrandGrpCode.SA).equals(god.getBrndGrpId())) {
			imgEx.setCloudFrontType("SAM");			
			if(StringService.containsIgnoreCase("l", imgEx.getImgTpCd()) || StringService.containsIgnoreCase("m", imgEx.getImgTpCd())) {
				imgEx.setImgTpCd(String.valueOf(GoodsEnum.GoodsImageType.THNAIL));
			}
			
			if(StringService.containsIgnoreCase("d", imgEx.getImgTpCd())) {
				imgEx.setImgTpCd(String.valueOf(GoodsEnum.GoodsImageType.DETAIL));
			}	
		}
		//	사이즈코드
		imgEx.setImgSizeCd(String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_ORIGIN));
		//	대표이미지 여부
		if(String.valueOf(GoodsEnum.GoodsImageType.THNAIL).equals(imgEx.getImgTpCd())) {
			imgEx.setRprstImgYn(String.valueOf(GoodsEnum.YES));
		}else {
			imgEx.setRprstImgYn(String.valueOf(GoodsEnum.NO));
		}
		//	승인여부
		if(StringService.isEmpty(imgEx.getImgAprvYn())) {
			imgEx.setImgAprvYn(String.valueOf(GoodsEnum.YES));
		}
		//	사용여부
		if(StringService.isEmpty(imgEx.getImgUseYn())) {
			imgEx.setImgUseYn(String.valueOf(GoodsEnum.YES));
		}
		//	이미지 등록 방식 여부
		if(StringService.isEmpty(imgEx.getImageUploadType())) {
			imgEx.setImageUploadType(String.valueOf(GoodsEnum.GoodsUploadImageType.ONE));
		}
		//	Image path rule
		String imgTy = imgEx.getImgTpCd().toLowerCase();
		String brndId = imgEx.getBrndId().toLowerCase();
		String saveImagePath = "/goods/"+imgTy+"/"+brndId+"/"+DateService.getStringCurrentToday()+"/";		
		imgEx.setSaveImagePath(saveImagePath);
		
		return imgEx;
	}
	
	/**
	 * 상품 동영상 테이블 기본값 설정
	 * 
	 * 	- MOVI_TP_CD, MOVI_SIZE_CD, MOVI_TURN, RPRST_MOVI_YN, MOVI_APRV_YN, MOVI_USE_YN
	 * 
	 * @param godMovi
	 * @return
	 */
	public GodMovi setGoodsMovieDefaultPolicy(GodMovi godMovi) {
		//	동영상 유형
		godMovi.setMoviTpCd(String.valueOf(GoodsEnum.GoodsImageType.THNAIL));
		//	동영상 사이즈
		godMovi.setMoviSizeCd(String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_ORIGIN));
		//	동영상 순번
		godMovi.setMoviTurn(Integer.parseInt(String.valueOf(GoodsEnum.Number.ONE)));
		//	대표 동영상 여부
		godMovi.setRprstMoviYn(String.valueOf(GoodsEnum.YES));
		//	동영상 승인 여부
		godMovi.setMoviAprvYn(String.valueOf(GoodsEnum.YES));
		//	동영상 사용	여부
		godMovi.setMoviUseYn(String.valueOf(GoodsEnum.YES));		
		return godMovi;
		
	}
	
	/**
	 * 공지 사항 이미지 업로드시 Mall에 따른 업로드 경로 조회
	 * 
	 * @param mallId
	 * @return
	 */
	public String getNoticeImageUploadPath(String mallId) {
		String webResourcePath = "";
		String s3BuketName = IOService.getBucketName();
		
		if(StringService.isNotEmpty(mallId)) {
			webResourcePath = s3BuketName + ":" +getConfigService().getProperty("ncp_web_bo.cloud."+mallId+".bucket.image.folder.path") + "/goods/notice/";
		}
		
		return webResourcePath;
	}
	
	/**
	 * 브랜드에 따른 AWS Cloud Front URL 조회
	 * 
	 * @param brndId
	 * @return
	 */
	public String getBrandCloudFrontUrl(String brndId) {
		String url = "";
		SysBrnd brndParam = new SysBrnd();
		brndParam.setBrndId(brndId);
		SysBrnd sysBrnd = sysBrndRepository.selectSysBrnd(brndParam);
		
		// 디스커버리
		if(String.valueOf(GoodsEnum.BrandGrpCode.DX).equals(sysBrnd.getUpperBrndId())) {
			url = getConfigService().getProperty("ncp_web_bo.cdn.DXM.image.url");
		}
		// MLB
		else if(String.valueOf(GoodsEnum.BrandGrpCode.ML).equals(sysBrnd.getUpperBrndId())) {
			url = getConfigService().getProperty("ncp_web_bo.cdn.MBM.image.url");
		}
		// SA
		else if(String.valueOf(GoodsEnum.BrandGrpCode.SA).equals(sysBrnd.getUpperBrndId())) {
			url = getConfigService().getProperty("ncp_web_bo.cdn.SAM.image.url");
		}
		
		return url;
	}
	
	
	/**
	 * 브랜드별 핏 조견표 정보 조회 시 체크해야 할 POM 코드 가져오기
	 * 
	 * @param god
	 * @return
	 */
	public String getBrandFitGuideCheckPomCd(God god) {
		String pomCd = "";		
		/*
		 * MLB JP/JD 품목일 경우 소매에 따른 핏 가이드 구분
		 * 
		 * 	일반소매용 : SH34 있음 
		 * 	나글란 소매용 : SH34없음
		 * 		
		 */
		if(String.valueOf(GoodsEnum.BrandGrpCode.ML).equals(god.getBrndGrpId())) {		
			if("JP".equals(god.getPrdlstCd()) || "DJ".equals(god.getPrdlstCd())){
				pomCd = "SH34";
			}
		}		
		return pomCd;
	}
}
