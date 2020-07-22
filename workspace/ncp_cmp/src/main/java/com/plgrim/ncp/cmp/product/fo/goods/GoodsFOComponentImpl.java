package com.plgrim.ncp.cmp.product.fo.goods;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.com.ComChrger;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspGodPrc;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodColorLang;
import com.plgrim.ncp.base.entities.datasource1.god.GodCpstGodCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodLangbyGodNm;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndrDscr;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndrImg;
import com.plgrim.ncp.base.entities.datasource1.god.GodMovi;
import com.plgrim.ncp.base.entities.datasource1.god.GodNtfcDetail;
import com.plgrim.ncp.base.entities.datasource1.god.GodReWhsgNtcn;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodFitLktbExtend;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.enums.PromotionEnum.CouponIssueLimitReason;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsInfoResult;
import com.plgrim.ncp.biz.goods.service.GoodsContentService;
import com.plgrim.ncp.biz.goods.service.GoodsErpService;
import com.plgrim.ncp.biz.goods.service.GoodsInfoService;
import com.plgrim.ncp.biz.goods.service.GoodsPriceService;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.biz.promotion.result.MbrIssuCpnResult;
import com.plgrim.ncp.biz.promotion.result.PromotionBoResult;
import com.plgrim.ncp.biz.promotion.service.PromotionService;
import com.plgrim.ncp.cmp.product.fo.GoodsFOComponent;
import com.plgrim.ncp.commons.service.GodPlcService;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GoodsFOComponentImpl extends AbstractComponent implements GoodsFOComponent {
	
	@Autowired
	private GodPlcService goodsPolicyService;
	
	@Autowired
	private GoodsErpService goodsErpService;
	
	@Autowired
	private GoodsInfoService goodsInfoService;
	
	@Autowired
	private GoodsContentService goodsContentService;
		
	@Autowired
	private GoodsPriceService goodsPriceService;

	@Autowired
	private PromotionService promotionService;

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.fo.GoodsFOComponent#getGoods(com.plgrim.ncp.biz.goods.data.GoodsSearchDTO)
	 */
	@Override
	public GoodsInfoResult getGoods(GoodsSearchDTO goodsSearchDTO) {
		
		/**
		 * 상품 조회
		 * 상품 언어별 상품명 조회
		 * 가격정보 조회
		 * 적립금 조회
		 * 업체조회
		 * 업체 배송정책 조회
		 * 업체 AS 담당자 조회
		 * 브랜드 조회
		 * 색상 조회
		 * 단품 조회
		 * 이미지 조회
		 * 동영상 조회
		 * 고시정보 조회
		 * 세탁설명 조회
		 * 세탁설명 이미지 조회
		 * 핏 정보 조회
		 * - 세트 -
		 * 구성정보 조회
		 * 구성상품 언어별 상품명 조회
		 * 구성상품 단품 조회
		 * 구성상품 고시정보 조회
		 */
		
		GoodsInfoResult result = new GoodsInfoResult();
		try {
			String godNo = "";
			String langCd = goodsSearchDTO.getLangCd();
			String mallId = goodsSearchDTO.getMallTpCd();
			
			//	상품 조회
			GodExtend godEx = new GodExtend();
			God god = goodsInfoService.getGoods(goodsSearchDTO);					
			godNo = god.getGodNo();						
			
			String recomendSexNm = CodeUtil.getCode(langCd, god.getRecomendSexCd()).getCdNm();
			godEx.setRecomendSexNm(recomendSexNm);			
			BeanUtils.copyProperties(god, godEx);
			
			//	상품 언어별 상품명 조회
			GodLangbyGodNm langByGodNm = goodsInfoService.getGoodsLangByGoodsName(godNo, langCd);
			if(!Utils.empty(langByGodNm)) {
				godEx.setGodNm(langByGodNm.getGodNm());
				godEx.setMobileGodNm(langByGodNm.getMobileGodNm());
				godEx.setGodSrchSnm(langByGodNm.getGodSrchSnm());
			}

			//	상품 언어별 컬러명 조회
			GodColorLang godColorLang = goodsInfoService.getGoodsColorLang(god.getColorCd(), langCd, god.getBrndGrpId());
			if(!Utils.empty(godColorLang)) {
				godEx.setColorNm(godColorLang.getColorNm());
			}
			
			//	가격 조회
			DspGodPrc priceDTO = new DspGodPrc();
			priceDTO.setGodNo(godNo);
			priceDTO.setPrcSectCd(String.valueOf(DisplayEnum.PrcSectCd.GNRL));
			priceDTO.setLangCd(langCd);
			priceDTO.setMallId(mallId);
			DspGodPrc dspGodPrc = goodsPriceService.getDspGodPrc(priceDTO);
			
			//	언어별 가격이 없을 경우 원화 가격
			if(!String.valueOf(GoodsEnum.KOR).equals(langCd) && Utils.empty(dspGodPrc)) {
				priceDTO.setLangCd(String.valueOf(GoodsEnum.KOR));
				dspGodPrc = goodsPriceService.getDspGodPrc(priceDTO);
			}

			//	적립금 조회	
			HashMap<String, BigDecimal> point = goodsInfoService.getGoodsPointSaveMoney(priceDTO); 
			if(!Utils.empty(point)){
				godEx.setPntAccmlRt(point.get("APL_PNT_ACCML_RT"));
				godEx.setPntSavMny(point.get("PNT_SAV_MNY"));				
			}
			result.setGodEx(godEx);
			result.setDspGodPrc(dspGodPrc);				
			
			//	상품 전시 유효성 체크 - BO에서 미리보기는 패스
			if(!String.valueOf(GoodsEnum.YES).equals(goodsSearchDTO.getIsBoPreview())) {
				result.setUtm_source(goodsSearchDTO.getUtm_source());
				result.setUtm_medium(goodsSearchDTO.getUtm_medium());
				goodsDisplayValidate(result, god, langByGodNm, dspGodPrc);
				
				if(String.valueOf(GoodsEnum.FAIL_CODE).equals(result.getRstCd())) {
					return result;
				} 
			}
			
			//	업체 조회
			Com com = goodsInfoService.getCom(god.getComId());
			result.setCom(com);
			
			//	업채 배송정책 조회
			String comId = god.getComId();
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("comId", comId);
			paramMap.put("mallId", mallId);
			List<ComDmstcDlvCstPlc> dmstcDlvCstPlcList = goodsInfoService.getComDomesticDeliveryCostPolicy(paramMap);
			dlvLoop : for(ComDmstcDlvCstPlc dlvCstPlc : dmstcDlvCstPlcList) {
//				if(String.valueOf(GoodsEnum.YES).equals(dlvCstPlc.getBaseDlvCstPlcYn()) && String.valueOf(GoodsEnum.YES).equals(dlvCstPlc.getUseYn())) {
				if(String.valueOf(GoodsEnum.YES).equals(dlvCstPlc.getUseYn())) {
					//	언어별 정책 조회
					ComDmstcDlvCstPlcLang comDmstcDlvCstPlcLang = goodsInfoService.getComDomesticDeliveryCostPolicyByLang(dlvCstPlc.getDmstcDlvCstPlcSn(), langCd);
					if(!Utils.empty(comDmstcDlvCstPlcLang)) {
						dlvCstPlc.setDlvMthdDscr(comDmstcDlvCstPlcLang.getDlvMthdDscr());
						dlvCstPlc.setMobileDlvMthdDscr(comDmstcDlvCstPlcLang.getMobileDlvMthdDscr());
					}		
					result.setComDmstcDlvCstPlc(dlvCstPlc);					
					break dlvLoop;
				}
			}
			
			//	업체 AS 담당자 조회
			ComChrger comChrger = goodsInfoService.getComChrger(comId, "AS");
			result.setComChrger(comChrger);
			
			//	브랜드 조회
			SysBrnd sysBrnd = goodsInfoService.getSystemBrand(god.getBrndId());
			result.setSysBrnd(sysBrnd);

			//	색상조회
			List<GodExtend> designColorList = goodsInfoService.getDesignColorList(godNo);
			result.setDesignColorList(designColorList);
			
			//	단품 조회
			List<GodItmExtend> itemList = goodsInfoService.getGoodsItemList(godNo);
			result.setGodItmExList(itemList);	
			
			// 단품 일시품절 여부 : 입고알림서비스 때매
			String optSoldYn = "N";
			for(GodItmExtend itm : itemList) {
				if(String.valueOf(GoodsEnum.GoodsSaleState.SMTM_SLDOUT).equals(itm.getItmStatCd())) {
					optSoldYn = "Y";
				}
			}
			result.setOptSoldYn(optSoldYn);
			
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
			result.setGodMovi(movi);			
			
			//	고시정보 조회
			GodNtfcDetail ntfcDetail;
			GodLndrDscr godLndr;
			String lndrCd;
			String brndGrpId = sysBrnd.getUpperBrndId();
			
			//	일반 상품
			if(String.valueOf(GoodsEnum.GoodsType.GNRL_GOD).equals(god.getGodTpCd())) {
				ntfcDetail = goodsInfoService.getGoodsNotificationDetail(godNo, langCd);
				
				if(!Utils.empty(ntfcDetail)) {
					//	세탁설명 조회
					lndrCd = ntfcDetail.getLndrCd();					
					godLndr = goodsErpService.getGoodsLaundryDescription(lndrCd, langCd);
					if(!Utils.empty(godLndr)) {
						ntfcDetail.setLndrMthdDscr(godLndr.getLndrMthdDscr());
					
						//	세탁설명 이미지 조회
						goodsSearchDTO.setLndrCd(lndrCd);
						goodsSearchDTO.setBrndGrpId(brndGrpId);
						goodsSearchDTO.setLangCd(langCd);
						List<GodLndrImg> godLndrImgList = goodsErpService.getGoodsLaundryImageList(goodsSearchDTO);
						result.setGodLndrImgList(godLndrImgList);
					}
					result.setGodNtfcDetail(ntfcDetail);
				}
								
				//	핏 정보 조회
				String checkPomCd = goodsPolicyService.getBrandFitGuideCheckPomCd(god);
				InfGodFitLktbExtend godFitLktbEx = goodsErpService.getGoodsErpFitGuide(godNo, langCd, checkPomCd);
				result.setGodFitLktbEx(godFitLktbEx);
			}
			
			//	세트 상품
			if(String.valueOf(GoodsEnum.GoodsType.SET_GOD).equals(god.getGodTpCd())) {
				ntfcDetail = goodsInfoService.getGoodsNotificationDetail(godNo, langCd);
				
				if(!Utils.empty(ntfcDetail)) {
					result.setGodNtfcDetail(ntfcDetail);
				}
				
				//	구성정보 조회
				List<GodCpstGodCnncExtend> codCpstGodCnncList = new ArrayList<GodCpstGodCnncExtend>();
				List<GodNtfcDetail> ntfcDetailList = new ArrayList<GodNtfcDetail>();
				
				List<GodCpstGodCnncExtend> cpstList = goodsInfoService.getGoodsCompositionGoodsConnectionList(godNo);				
				
				if(Utils.empty(cpstList)) {
					result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
					return result;					
				}
				
				for(GodCpstGodCnncExtend cpst : cpstList) {
					
					String cpstGodNo = cpst.getCpstGodNo();
					
					//	언어별 구성 상품 상품명 조회
					GodLangbyGodNm cpstLangByGodNm = goodsInfoService.getGoodsLangByGoodsName(cpstGodNo, langCd);					
					if(!Utils.empty(cpstLangByGodNm) && StringService.isNotEmpty(cpstLangByGodNm.getGodNm())) {
						cpst.setCpstGodNm(cpstLangByGodNm.getGodNm());
						cpst.setCpstMobileGodNm(cpstLangByGodNm.getMobileGodNm());
					}
					
					//	구성품별 판매 가격 조회
					priceDTO.setGodNo(cpstGodNo);
					DspGodPrc cpstDspGodPrc = goodsPriceService.getDspGodPrc(priceDTO);
					cpst.setCsmrPrc(cpstDspGodPrc.getLastSalePrc());
					
					//	언어별 구성 상품 고시정보 조회
					ntfcDetail = goodsInfoService.getGoodsNotificationDetail(cpstGodNo, langCd);					
					
					if(!Utils.empty(ntfcDetail)) {
						//	사이즈 조견표
						cpst.setCpstSizeLktbHtml(ntfcDetail.getSizeLktbHtml());
						cpst.setCpstMobileSizeLktbHtml(ntfcDetail.getMobileSizeLktbHtml());
						
						//	세탁설명 조회
						lndrCd = ntfcDetail.getLndrCd();						
						godLndr = goodsErpService.getGoodsLaundryDescription(lndrCd, langCd);
						if(!Utils.empty(godLndr)) {
							ntfcDetail.setLndrMthdDscr(godLndr.getLndrMthdDscr());
							
							//	세탁설명 이미지 조회							
							goodsSearchDTO.setLndrCd(lndrCd);
							goodsSearchDTO.setBrndGrpId(brndGrpId);
							goodsSearchDTO.setLangCd(langCd);
							List<GodLndrImg> cpstLndrImgList = goodsErpService.getGoodsLaundryImageList(goodsSearchDTO);
							cpst.setCpstLndrImgList(cpstLndrImgList);
							
						}					
					}	
					ntfcDetailList.add(ntfcDetail);
					codCpstGodCnncList.add(cpst);
				}				
				result.setGodCpstGodCnncExList(codCpstGodCnncList);
				result.setGodNtfcDetailList(ntfcDetailList);
				
				
				//	구성정보 단품 조회
				List<GodItmExtend> cpstGodItmList = goodsInfoService.getGoodsCompositionGoodsConnectionItemList(godNo);
				result.setCpstGodCnncItmExList(cpstGodItmList);
			}

			if(String.valueOf(GoodsEnum.FAIL_CODE).equals(result.getRstCd())) {
				return result;
			} else {				
				result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
			}
			
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			//throw new RuntimeException();
		}
		
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.fo.GoodsFOComponent#addCouponDownMemberIssue(java.lang.String)
	 */
	@Override
	public String addCouponDownMemberIssue(String prmNo) throws Exception {

		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
		String mbrNo = userDetail.getMbr().getMbrNo();
		String resultMsg = "";

		// 쿠폰 발급
		PromotionBoDTO promotionBoDTO = new PromotionBoDTO();
		List<String> listMbrNo = new ArrayList<String>();
		listMbrNo.add(mbrNo);
		promotionBoDTO.setListMbrNo(listMbrNo);
		promotionBoDTO.getMbrIssuCpn().setPrmNo(prmNo);
		promotionBoDTO.getMbrIssuCpn().setRegtrId(mbrNo);
		promotionBoDTO.getMbrIssuCpn().setUdterId(mbrNo);
		promotionBoDTO.getPrmCpn().setPrmNo(prmNo);
		promotionBoDTO.getPrmCpn().setUdterId(mbrNo);
		promotionBoDTO.getPrmCpn().setRegtrId(mbrNo);

		Prm prm = new Prm();
		prm.setPrmNo(prmNo);
		promotionBoDTO.setPrm(prm);
		//쿠폰 회원 발급 가능 체크
		PromotionBoResult couponResult = promotionService.selectCouponIssueUseCheck(promotionBoDTO);

		if(CouponIssueLimitReason.SUCCES.toString().equals(couponResult.getChkMsg())){
			//회원별 쿠폰 발급 처리 후 mbrIssuCpn결과 값 return
			List<MbrIssuCpnResult> couponIssueResult = promotionService.selectCouponIssueCheckResult(promotionBoDTO, couponResult.getPrmCpn());
			for(MbrIssuCpnResult info : couponIssueResult){
				if(CouponIssueLimitReason.IDBY_ISSU_QTY_EXCESS.toString().equals(info.getRstCd())){
					resultMsg = CouponIssueLimitReason.IDBY_ISSU_QTY_EXCESS.toString();
				} else {
					resultMsg = info.getRstCd();
				}
			}
		} else {
			resultMsg = couponResult.getChkMsg();
		}

		return resultMsg;
	}

	private GoodsInfoResult goodsDisplayValidate(GoodsInfoResult result, God god, GodLangbyGodNm langByGodNm, DspGodPrc dspGodPrc) {
		//	상품 등록 여부
		if(Utils.empty(god)) {		
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			return result;
		}			
		//	미전시 상품 노출 X
		if(String.valueOf(GoodsEnum.NO).equals(god.getDspYn())) {
			// 인스타 유입일경우 체크 안함
			if( !(GoodsEnum.INSTA.toString().equals(result.getUtm_source()) && GoodsEnum.INSTA.toString().equals(result.getUtm_medium())) 
					&& !(GoodsEnum.NAVER.toString().equals(result.getUtm_source()) && GoodsEnum.NAVER.toString().equals(result.getUtm_medium())) ) {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
				return result;
			}
		}
		//	승인상태
		if(!String.valueOf(GoodsEnum.GoodsAprvState.APRV_COMPT).equals(god.getGodAprvSectCd())) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			return result;
		}
		//	상품 유형 - 사은품 X
		if(String.valueOf(GoodsEnum.GoodsType.CNVRS_GFT).equals(god.getGodTpCd())) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			return result;
		}
		//	해외 전시 확인
		if(!Utils.empty(langByGodNm)) {
			//	번역 상태 확인
			if(!String.valueOf(GoodsEnum.GoodsTransState.TRSLT_COMPT).equals(langByGodNm.getTrsltStatCd())) {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
				return result;				
			}
			//	전시 상태 확인
			if(!String.valueOf(GoodsEnum.GoodsOvseaDspState.DSP_APRV).equals(langByGodNm.getOvseaDspStatCd())) {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
				return result;
			}
		}
		//	전시 가격 확인
		if(Utils.empty(dspGodPrc)) {		
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			return result;
		}		
		
		//	세트 상품일 경우 구성품의 승인완료/전시 상태 확인
		if(String.valueOf(GoodsEnum.GoodsType.SET_GOD).equals(god.getGodTpCd())) {
			// 인스타 유입일경우 체크 안함
			if( !(GoodsEnum.INSTA.toString().equals(result.getUtm_source()) && GoodsEnum.INSTA.toString().equals(result.getUtm_medium())) 
					&& !(GoodsEnum.NAVER.toString().equals(result.getUtm_source()) && GoodsEnum.NAVER.toString().equals(result.getUtm_medium())) ) {
				boolean setValid = goodsInfoService.checkSetDisplayValidate(god.getGodNo());
				if(!setValid) {
					result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
					return result;
				}
			}
		}		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.fo.GoodsFOComponent#getViewGoodsUrl(java.lang.String, boolean)
	 */
	@Override
	public String getViewGoodsUrl(String godNo, boolean encode) {
		StringBuffer url = new StringBuffer();				
		try {
			String goodsUrl = goodsInfoService.getViewGoodsUrl(godNo);			
			if(encode) {				
				String[] paths = StringService.split(goodsUrl, "/");  				
				for (int i = 0; i < paths.length; i++) {
					url.append("/");
					//	한글 SEO URL Encoding 처리
					if(paths[i].matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
						url.append(URLEncoder.encode(paths[i], "UTF-8"));
					}else {
						url.append(paths[i]);
					}
				}								
			} else {
				url.append(goodsUrl);
			}
		} catch (Exception e) {
			log.error("getViewGoodsUrl {}", e.getMessage());
			url.append("/goods/" + godNo + "/view");
		}		
		return url.toString();
	}
	
	@Override
	public GoodsInfoResult getRedirectGoodsUrl(GoodsSearchDTO goodsSearchDTO) {
	
		return goodsInfoService.getRedirectGoodsUrl(goodsSearchDTO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.fo.GoodsFOComponent#addGoodsInform(GodReWhsgNtcn godReWhsgNtcn)
	 */
	@Override
	public String addGoodsInform(GodReWhsgNtcn godReWhsgNtcn) throws Exception {
		return goodsContentService.addGoodsInform(godReWhsgNtcn);
	}

}
