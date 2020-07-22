package com.plgrim.ncp.cmp.product.fo.content;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlAtchFile;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrTodayGod;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnGodExtend;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmLang;
import com.plgrim.ncp.base.enums.BskEnum.MbrSect;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.enums.GoodsEnum.TodayGodSectCd;
import com.plgrim.ncp.biz.goods.data.GoodsContentSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsReviewSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsContentResult;
import com.plgrim.ncp.biz.goods.result.GoodsRelatedGodResult;
import com.plgrim.ncp.biz.goods.result.GoodsReviewResult;
import com.plgrim.ncp.biz.goods.service.GoodsContentService;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.cmp.product.fo.GoodsContentFOComponent;
import com.plgrim.ncp.commons.taglib.Functions;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.utils.Utils;

@Transactional(value = "transactionManager")
@Component
public class GoodsContentFOComponentImpl extends AbstractComponent implements GoodsContentFOComponent {
	
	@Autowired
	private GoodsContentService goodsContentService;
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.fo.GoodsContentFOComponent#getGoodsContent(com.plgrim.ncp.biz.goods.data.GoodsContentSearchDTO, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public GoodsContentResult getGoodsContent(GoodsContentSearchDTO goodsContentSearchDTO, HttpServletResponse response, HttpServletRequest request) {
		
		/**
		 * 1. 상품 공지사항 조회
		 * 2. 최대 할인 상품 쿠폰 프로모션 조회
		 * 3. 이벤트 조회(카드1 그외 2)
		 * 4. 사은품 프로모션 적용 여부 확인
		 */
		
		GoodsContentResult result = new GoodsContentResult();
		
		try {
			//	1. 상품 공지사항 조회
			List<GodNotiExtend> noticeList = goodsContentService.getGoodsNoticeList(goodsContentSearchDTO);
			result.setGodNotiExList(noticeList);	
			
			//	2. 상품 쿠폰 프로모션 조회
			GoodsContentSearchDTO priceDTO = new GoodsContentSearchDTO();
			BeanUtils.copyProperties(goodsContentSearchDTO, priceDTO);
						
			PrmCpnGodExtend promotionCoupon = goodsContentService.getGoodsMostDiscountCouponPromotion(priceDTO);			
			
			//	언어별 가격이 없을 경우 원화 가격
			if(Utils.empty(promotionCoupon)) {
				priceDTO.setLang(String.valueOf(GoodsEnum.KOR));
				promotionCoupon = goodsContentService.getGoodsMostDiscountCouponPromotion(priceDTO);
			}
			result.setPrmCpnGod(promotionCoupon);
			
			if(!Utils.empty(promotionCoupon)) {
				PrmLang prmLang = goodsContentService.getPromotionLanguage(promotionCoupon.getPrmNo(), goodsContentSearchDTO.getLang());
				result.setPrmCpnLang(prmLang);
			}
			
			//	3. 상품 쿠폰 목록 조회
			List<PrmCpnGodExtend> prmCpnList = goodsContentService.getGoodsCouponPromotionList(goodsContentSearchDTO);
			result.setPrmCpnList(prmCpnList);			
			
			//	4. 이벤트 조회
			Evt cardEvent = goodsContentService.getGoodsCardEvent(goodsContentSearchDTO);
			result.setCardEvent(cardEvent);
			List<Evt> eventList = goodsContentService.getGoodsEventList(goodsContentSearchDTO);			
			result.setEventList(eventList);
			
			//	5. 사은품 프로모션 적용 여부 확인
			if(goodsContentService.isGiftPromotion(goodsContentSearchDTO) > 0) {
				result.setGiftPromoAplYn(String.valueOf(GoodsEnum.YES));
			}else {
				result.setGiftPromoAplYn(String.valueOf(GoodsEnum.NO));
			}			
			
			// 6. 최근 본 상품 등록			
			MbrTodayGod mbrTodayGod = new MbrTodayGod();
			mbrTodayGod.setGodNo(goodsContentSearchDTO.getGodNo());
			mbrTodayGod.setMallId(goodsContentSearchDTO.getMallId());
			mbrTodayGod.setLangCd(goodsContentSearchDTO.getLang());
			boolean loginYn = false;
			
			if(ContextService.hasRole()){
				SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
				Mbr mbr = userDetail.getMbr();
				mbrTodayGod.setMbrSectCd(MbrSect.MBR.toString());
				mbrTodayGod.setMbrNo(mbr.getMbrNo());
				mbrTodayGod.setRegtrId(mbr.getMbrNo());
				mbrTodayGod.setUdterId(mbr.getMbrNo());
				loginYn = true;
			}
			
			mbrTodayGod.setTodayGodSectCd(TodayGodSectCd.GOD.toString());
			mbrTodayGod.setMallId(goodsContentSearchDTO.getMallId());
			if (ContextService.getCurrentRequest().getCookies() != null && !loginYn) {					
				Functions.toDayCookies("godNo", goodsContentSearchDTO.getGodNo(), TodayGodSectCd.GOD.toString(), TodayGodSectCd._TODAYALLLIST.toString(),"", response);
			}else{
				// 최근 본 상품 중복조회
				goodsContentService.isDuplicateTodayGod(mbrTodayGod);
			}
			
			// 7. 상품 기획전 조회
			List<DspPromt> promotionList = goodsContentService.getGoodsPromotionList(goodsContentSearchDTO);
			result.setPromotionList(promotionList);
			
			
			
			if("DXM".equals(goodsContentSearchDTO.getMallId())){
				
				List<Long> sort = new ArrayList<>();
				 List<Evt> event  = new ArrayList<>();
				
				for (Evt evt : eventList) {
					if(evt.getEvtDetailTpCd().equals("HTML")){
						String url = "/event/"+evt.getEvtNo()+"/"+evt.getEvtTpCd();
						evt.setPcUrl(url);
						evt.setMobileUrl(url);
					}
					sort.add(evt.getRegDt().getTime());					
				}
	
				for (DspPromt dspPromt : promotionList) {
					
					sort.add(dspPromt.getRegDt().getTime());
					
				}
				
				if(sort.size() >0){
					Collections.sort(sort);
					Collections.reverse(sort);
					int count = 0;
					for (Long v : sort) {
						
						if(count < 2){
							for (Evt evt : eventList) {
								if(v == evt.getRegDt().getTime()){
									event.add(evt);
								}			
							}
				
							for (DspPromt dspPromt : promotionList) {
								if(v == dspPromt.getRegDt().getTime()){
									Evt evt = new Evt();
									String url = "/special/"+dspPromt.getPromtSn() ;
									evt.setPcUrl(url);
									evt.setMobileUrl(url);
									evt.setEvtNm(dspPromt.getPromtNm());
									event.add(evt);
								}
							 			
							}
						 
						} 
						count++;
					}
					
				}
				result.setEventList(event);
 
			}else{
				result.setEventList(eventList);
			}
			
			// 8. 신규가입회원 쿠폰 조회
			String prmNo = getConfigService().getProperty("ncp_base."+goodsContentSearchDTO.getMallId()+".new.join.promotion.no");
			goodsContentSearchDTO.setPrmNo(prmNo);
			result.setPrmCpnDspCtgry(goodsContentService.getGoodsCouponNewMember(goodsContentSearchDTO));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			throw new RuntimeException();			
		}
		
		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.fo.GoodsContentFOComponent#searchReviewList(com.plgrim.ncp.biz.goods.data.GoodsReviewSearchDTO)
	 */
	@Override
	public GoodsReviewResult searchReviewList(GoodsReviewSearchDTO goodsReviewSearchDTO) {
		GoodsReviewResult result = new GoodsReviewResult(); 		
		Page<GodGodEvlExtend> reviewList = goodsContentService.searchReviewList(goodsReviewSearchDTO);		
		result.setGodGodEvlExList(reviewList);
		
		String godNo = goodsReviewSearchDTO.getGodNo();
		
		if(!reviewList.getContent().isEmpty()) {		
			for(GodGodEvlExtend review : reviewList) {
				goodsReviewSearchDTO.setGodEvlTurn(review.getGodEvlTurn());
				goodsReviewSearchDTO.setGodNo(review.getGodNo());
				List<GodGodEvlAtchFile> atchFileList = goodsContentService.getGodGodEvlAtchFileList(goodsReviewSearchDTO);			
				review.setGodGodEvlAtchFileList(atchFileList);
			 
			}
			goodsReviewSearchDTO.setGodNo(godNo);
			if(StringService.isEmpty(goodsReviewSearchDTO.getSortFlag()) && StringService.isEmpty(goodsReviewSearchDTO.getPhotoReviewYn())){
				GodGodEvlExtend reviewAverageScore = goodsContentService.getReviewAverageScore(goodsReviewSearchDTO);
				result.setReviewAverageScore(reviewAverageScore);			
			}
		}
		return result;
	}
	
	@Override
    public long getReviewListCount(GoodsReviewSearchDTO goodsReviewSearchDTO) {
    	return  goodsContentService.getReviewListCount(goodsReviewSearchDTO);
    }
	
	@Override
    public List<GoodsRelatedGodResult> getRelatedGoods(GoodsContentSearchDTO contentSearchDTO) {
    	return  goodsContentService.getRelatedGoods(contentSearchDTO);
    }
    
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	
}
