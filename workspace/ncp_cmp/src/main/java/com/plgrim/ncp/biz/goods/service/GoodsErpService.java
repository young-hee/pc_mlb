package com.plgrim.ncp.biz.goods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndr;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndrDscr;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndrImg;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodFitLktbExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodSizeLktb;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodSizeLktbPomExtend;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.repository.GoodsErpRepository;
import com.plgrim.ncp.biz.goods.result.GoodsErpResult;
import com.plgrim.ncp.biz.goods.result.GoodsErpSizeChartResult;
import com.plgrim.ncp.framework.commons.StringService;
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
 * Description	:	상품 ERP Service
 *
 * @Author 	:	neps
 * @Date   	:	2018. 5. 3.
 * @Version	:	
 *
 */
@Service
public class GoodsErpService extends GoodsService{

	@Autowired
	private GoodsErpRepository goodsErpRepository;
	
	/**
	 * ERP 상품 정보 조회
	 * 
	 * @param erpGodNo
	 * @return
	 */
	public InfGodItmExtend getErpGoods(String erpGodNo) {
		return goodsErpRepository.getErpGoods(erpGodNo);
	}
	
	/**
	 * ERP 상품 단품 조회
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public List<GodItmExtend> getErpGoodsItem(GoodsSearchDTO searchDTO) {
		return goodsErpRepository.getErpGoodsItem(searchDTO);
	}

	/**
	 * 세탁코드 정보 조회
	 * 
	 * @param lndrCd
	 * @return
	 */
	public GodLndr getGoodsLaundry(String lndrCd) {
		GodLndr godLndr = new GodLndr();
		godLndr.setLndrCd(lndrCd);
		return godLndrRepository.selectGodLndr(godLndr);
	}
	
	/**
	 * 세탁코드 설명 조회
	 * 
	 * @param lndrCd
	 * @param langCd
	 * @return
	 */
	public GodLndrDscr getGoodsLaundryDescription(String lndrCd, String langCd) {
		GodLndrDscr godLndrDscr = new GodLndrDscr();
		godLndrDscr.setLndrCd(lndrCd);
		godLndrDscr.setLangCd(langCd);
		return godLndrDscrRepository.selectGodLndrDscr(godLndrDscr);
	}

	/**
	 * 세탁코드 설명 목록 조회
	 * 
	 * @param lndrCd
	 * @return
	 */
	public List<GodLndrDscr> getGoodsLaundryDescriptionList(String lndrCd){
		return goodsErpRepository.getGoodsLaundryDescriptionList(lndrCd);
	}
	
	/**
	 * 세탁코드 이미지 조회
	 * 
	 * @param lndrCd
	 * @return
	 */
	public List<GodLndrImg> getGoodsLaundryImageList(GoodsSearchDTO searchDTO) {
		return goodsErpRepository.getGoodsLaundryImageList(searchDTO);
	}	
	
	/**
	 * ERP 상품 단품 목록 조회 (검색)
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public Page<GoodsErpResult> searchErpGoodsGridList(GoodsSearchDTO goodsSearchDTO)  {
		try {
			// 페이지 인덱스 셋팅
			PageParam pageParam = getPageService().buildPageParam(goodsSearchDTO.getGPageNo(), goodsSearchDTO.getGPageSize());
			RepositoryHelper.makePageEntityIndex(goodsSearchDTO, pageParam);
			
			// 목록 건수 조회	
			long totalRow = goodsErpRepository.getErpGoodsListCount(goodsSearchDTO);
			
			// 목록 조회
			List<GoodsErpResult> results = new ArrayList<GoodsErpResult>();
			
			if(totalRow > 0) {
				results = goodsErpRepository.selectErpGoodsList(goodsSearchDTO);	
			}
			
			return new PageImpl<GoodsErpResult>(results, pageParam.getPageable(), totalRow);
		} catch (Exception e) {
			super.stackTrace(e);
			throw new RuntimeException();
		}
	}
	
	/**
	 * ERP 상품정보 엑셀 템플릿 생성 데이터 조회
	 * 
	 * @param goodsSearchDTO
	 * @return
	 */
	public List<Map<String, String>> getErpGoodsUploadExcelTemplate(GoodsSearchDTO goodsSearchDTO)  {
		return goodsErpRepository.getErpGoodsUploadExcelTemplate(goodsSearchDTO);
	}
	
	/**
	 * ERP 상품 사이즈 조견표 생성
	 * 
	 * @param dsgnGrpNo
	 * @param prdlstCd
	 * @param brndId
	 * @param imageUrl
	 * @param itemList
	 * @return
	 */
	public GoodsErpSizeChartResult getErpGoodsSizeChart(String dsgnGrpNo, String prdlstCd, String brndId, String imageUrl, List<GodItmExtend> itemList, String brndGrpId, InfGodItmExtend erpGodData) {
		GoodsErpSizeChartResult result = new GoodsErpSizeChartResult();
		// MLB, MLBK 신발(SH)/모자(CP) 일 경우 신규 추가 테이블을 이용하여 사이즈조견표HTML 생성 (20181114_ds)
		if(String.valueOf(GoodsEnum.BrandGrpCode.ML).equals(brndGrpId) && ("SH".equals(prdlstCd) || "CP".equals(prdlstCd))) {
			Map<String, String> paramMap = new HashMap<String, String>(); 
			paramMap.put("brndId", brndId);
			paramMap.put("prdlstCd", prdlstCd);
			if(erpGodData.getFitSectCd() != null) {
				paramMap.put("fitDetailSectCd", erpGodData.getFitDetailSectCd());
			}
			List<Map<String, String>> mlbSizeLktbList = goodsErpRepository.getMlbGoodsSizeLktbExList(paramMap);
			if(mlbSizeLktbList!=null && mlbSizeLktbList.size()>0) {
				for(Map<String, String> mlbSizeLktb : mlbSizeLktbList) {
					if(String.valueOf(GoodsEnum.KOR).equals(mlbSizeLktb.get("LANG_CD"))) {
						result.setSizeLktb(mlbSizeLktb.get("SIZE_LKTB_HTML"));
						result.setMobileSizeLktb(mlbSizeLktb.get("MOBILE_SIZE_LKTB_HTML"));
					}
					else if(String.valueOf(GoodsEnum.ENG).equals(mlbSizeLktb.get("LANG_CD"))) {
						result.setSizeLktbEng(mlbSizeLktb.get("SIZE_LKTB_HTML"));
						result.setMobileSizeLktbEng(mlbSizeLktb.get("MOBILE_SIZE_LKTB_HTML"));
					}
					else if(String.valueOf(GoodsEnum.CHI).equals(mlbSizeLktb.get("LANG_CD"))) {
						result.setSizeLktbChi(mlbSizeLktb.get("SIZE_LKTB_HTML"));
						result.setMobileSizeLktbChi(mlbSizeLktb.get("MOBILE_SIZE_LKTB_HTML"));
					}
				}
			}
		} 
		// 그외 (DX, MLB/MLBK 일반, SA)
		else {
			InfGodSizeLktb sizeParam = new InfGodSizeLktb();
			sizeParam.setDsgnGrpNo(dsgnGrpNo);
			sizeParam.setBrndId(brndId);
			
			List<InfGodSizeLktbPomExtend> pomExList = goodsErpRepository.getErpGoodsSizeLktbPomExList(sizeParam);
			List<InfGodSizeLktb> sizeList = goodsErpRepository.getErpGoodsSizeLktbList(sizeParam);
			
			//	PC
			result.setSizeLktb(getSizeChartByPC(String.valueOf(GoodsEnum.KOR), itemList, imageUrl, pomExList, sizeList));
			result.setSizeLktbEng(getSizeChartByPC(String.valueOf(GoodsEnum.ENG), itemList, imageUrl, pomExList, sizeList));
			result.setSizeLktbChi(getSizeChartByPC(String.valueOf(GoodsEnum.CHI), itemList, imageUrl, pomExList, sizeList));
			
			//	MOBILE
			result.setMobileSizeLktb(getSizeChartByMobile(String.valueOf(GoodsEnum.KOR), itemList, imageUrl, pomExList, sizeList));
			result.setMobileSizeLktbEng(getSizeChartByMobile(String.valueOf(GoodsEnum.ENG), itemList, imageUrl, pomExList, sizeList));
			result.setMobileSizeLktbChi(getSizeChartByMobile(String.valueOf(GoodsEnum.CHI), itemList, imageUrl, pomExList, sizeList));
			
		}
		return result;		
	}

	private String getSizeChartByPC(String langCd, List<GodItmExtend> itemList, String imageUrl, List<InfGodSizeLktbPomExtend> pomExList, List<InfGodSizeLktb> sizeList) {		
		if(Utils.empty(sizeList)) {
			return null;
		}
		
		StringBuffer sizeHtml = new StringBuffer();
		if(StringService.isNotEmpty(imageUrl)) {			
			sizeHtml.append("<div class=\"sizeGuideImg\"><img src=\""+imageUrl+"\" /></div>");
			sizeHtml.append(System.getProperty("line.separator"));
		}
//		sizeHtml.append("<div class=\"data-tbl02 sizeguide-tbl\">");
//		sizeHtml.append(System.getProperty("line.separator"));
		sizeHtml.append("<table class=\"board-list st01\">");
		sizeHtml.append(System.getProperty("line.separator"));
		sizeHtml.append("<caption>SIZE GUIDE</caption>");
		sizeHtml.append(System.getProperty("line.separator"));
		sizeHtml.append("<colgroup>");
		sizeHtml.append(System.getProperty("line.separator"));
		sizeHtml.append("<col>");
		sizeHtml.append(System.getProperty("line.separator"));
				
		for(int i = 0; i<itemList.size(); i++) {
			sizeHtml.append("<col style=\"width:60px;\">");
			sizeHtml.append(System.getProperty("line.separator"));
		}
		sizeHtml.append("</colgroup>");
		sizeHtml.append(System.getProperty("line.separator"));
		sizeHtml.append("<thead>");
		sizeHtml.append(System.getProperty("line.separator"));
		sizeHtml.append("<tr>");
		sizeHtml.append(System.getProperty("line.separator"));
		
		if(String.valueOf(GoodsEnum.KOR).equals(langCd)) {			
			sizeHtml.append("<th scope=\"col\" class=\"gray\">치수 항목</th>");
		}
		if(String.valueOf(GoodsEnum.ENG).equals(langCd)) {
			sizeHtml.append("<th scope=\"col\" class=\"gray\">SIZE</th>");
		}
		// 중국어 추가, 영어로 해도 되나요? (201811001_ds)
		if(String.valueOf(GoodsEnum.CHI).equals(langCd)) {
			sizeHtml.append("<th scope=\"col\" class=\"gray\">SIZE</th>");
		}
		sizeHtml.append(System.getProperty("line.separator"));		
		
		for(GodItmExtend item : itemList) {
			sizeHtml.append("<th scope=\"col\">"+item.getItmNm()+"</th>");
			sizeHtml.append(System.getProperty("line.separator"));
		}
		sizeHtml.append("</tr>");
		sizeHtml.append(System.getProperty("line.separator"));
		sizeHtml.append("</thead>");
		sizeHtml.append(System.getProperty("line.separator"));

		sizeHtml.append("<tbody>");
		sizeHtml.append(System.getProperty("line.separator"));		
				
		for(InfGodSizeLktbPomExtend pom : pomExList) {
			sizeHtml.append("<tr>");
			sizeHtml.append(System.getProperty("line.separator"));

			if(String.valueOf(GoodsEnum.KOR).equals(langCd)) {
				sizeHtml.append("<th scope=\"row\" class=\"gray\">"+pom.getPomNm()+"("+pom.getPomClfcCd()+")</th>");				
			}
			if(String.valueOf(GoodsEnum.ENG).equals(langCd)) {
				sizeHtml.append("<th scope=\"row\" class=\"gray\">"+pom.getEngPomNm()+"("+pom.getPomClfcCd()+")</th>");
			}
			if(String.valueOf(GoodsEnum.CHI).equals(langCd)) {
				sizeHtml.append("<th scope=\"row\" class=\"gray\">"+pom.getChiPomNm()+"("+pom.getPomClfcCd()+")</th>");
			}			
			sizeHtml.append(System.getProperty("line.separator"));
			
			for(GodItmExtend item : itemList) {
				for(InfGodSizeLktb size : sizeList) {
					if(pom.getPomCd().equals(size.getPomCd()) && item.getOptValCd1().equals(size.getOptValCd())) {						
						sizeHtml.append("<td>"+size.getSizeVal()+"</td>");
						sizeHtml.append(System.getProperty("line.separator"));											
					}
				}
			}

			sizeHtml.append("</tr>");
			sizeHtml.append(System.getProperty("line.separator"));			
		}
		
		sizeHtml.append("</tbody>");
		sizeHtml.append(System.getProperty("line.separator"));

		sizeHtml.append("</table>");
//		sizeHtml.append(System.getProperty("line.separator"));
//		sizeHtml.append("</div>");

		return sizeHtml.toString();
	}
	
	
	private String getSizeChartByMobile(String langCd, List<GodItmExtend> itemList, String imageUrl, List<InfGodSizeLktbPomExtend> pomExList, List<InfGodSizeLktb> sizeList) {
		if(Utils.empty(sizeList)) {
			return null;
		}
		
		StringBuffer sizeHtml = new StringBuffer();		
		if(StringService.isNotEmpty(imageUrl)) {			
			sizeHtml.append("<div class=\"sizeGuideImg\"><img src=\""+imageUrl+"\" /></div>");
			sizeHtml.append(System.getProperty("line.separator"));
		}				
		if(String.valueOf(GoodsEnum.KOR).equals(langCd)) {
			sizeHtml.append("<p class=\"txt-sub-info01 \">아래의 표를 좌/우로 밀어서 확인하세요.</p>");
		}
		if(String.valueOf(GoodsEnum.ENG).equals(langCd)) {
			sizeHtml.append("<p class=\"txt-sub-info01 \">Check the table below by pushing left/right.</p>");
		}	
		// 중국어 추가, 영어로 해도 되나요? (201811001_ds)
		if(String.valueOf(GoodsEnum.CHI).equals(langCd)) {
			sizeHtml.append("<p class=\"txt-sub-info01 \">Check the table below by pushing left/right.</p>");
		}		
		sizeHtml.append(System.getProperty("line.separator"));
		
		sizeHtml.append("<div class=\"table_scroll\">");
		sizeHtml.append(System.getProperty("line.separator"));

		sizeHtml.append("<table class=\"board-list st01\">");
		sizeHtml.append(System.getProperty("line.separator"));
		sizeHtml.append("<colgroup>");
		sizeHtml.append(System.getProperty("line.separator"));
		sizeHtml.append("<col style=\"width:100px\">");
		sizeHtml.append(System.getProperty("line.separator"));
		sizeHtml.append("</colgroup>");
		sizeHtml.append(System.getProperty("line.separator"));			
		sizeHtml.append("<tbody>");
		sizeHtml.append(System.getProperty("line.separator"));
		sizeHtml.append("<tr>");
		sizeHtml.append(System.getProperty("line.separator"));
		
		if(String.valueOf(GoodsEnum.KOR).equals(langCd)) {			
			sizeHtml.append("<th scope=\"row\">치수 항목</th>");
		}
		if(String.valueOf(GoodsEnum.ENG).equals(langCd)) {
			sizeHtml.append("<th scope=\"row\">SIZE</th>");
		}
		// 중국어 추가, 영어로 해도 되나요? (201811001_ds)
		if(String.valueOf(GoodsEnum.CHI).equals(langCd)) {
			sizeHtml.append("<th scope=\"row\">SIZE</th>");
		}
		sizeHtml.append(System.getProperty("line.separator"));		
		
		for(GodItmExtend item : itemList) {
			sizeHtml.append("<td>"+item.getItmNm()+"</td>");
			sizeHtml.append(System.getProperty("line.separator"));
		}
		sizeHtml.append("</tr>");		
		
		for(InfGodSizeLktbPomExtend pom : pomExList) {
			sizeHtml.append("<tr>");
			sizeHtml.append(System.getProperty("line.separator"));

			if(String.valueOf(GoodsEnum.KOR).equals(langCd)) {
				sizeHtml.append("<th scope=\"row\">"+pom.getPomNm()+"("+pom.getPomClfcCd()+")</th>");				
			}
			if(String.valueOf(GoodsEnum.ENG).equals(langCd)) {
				sizeHtml.append("<th scope=\"row\">"+pom.getEngPomNm()+"("+pom.getPomClfcCd()+")</th>");
			}
			if(String.valueOf(GoodsEnum.CHI).equals(langCd)) {
				sizeHtml.append("<th scope=\"row\">"+pom.getChiPomNm()+"("+pom.getPomClfcCd()+")</th>");
			}	
			
			sizeHtml.append(System.getProperty("line.separator"));

			for(GodItmExtend item : itemList) {
				for(InfGodSizeLktb size : sizeList) {
					if(pom.getPomCd().equals(size.getPomCd()) && item.getOptValCd1().equals(size.getOptValCd())) {						
						sizeHtml.append("<td>"+size.getSizeVal()+"</td>");
						sizeHtml.append(System.getProperty("line.separator"));											
					}
				}
			}

			sizeHtml.append("</tr>");
			sizeHtml.append(System.getProperty("line.separator"));
		}

		sizeHtml.append(System.getProperty("line.separator"));
		sizeHtml.append("</tbody>");
		sizeHtml.append(System.getProperty("line.separator"));
		sizeHtml.append("</table>");

		sizeHtml.append(System.getProperty("line.separator"));
		sizeHtml.append("</div>");	
		
		
		return sizeHtml.toString();
	}
	
	public GoodsErpSizeChartResult getTempSizeChart(String dsgnGrpNo, String prdlstCd, String brndId, String imageUrl, List<GodItmExtend> itemList, String brndGrpId) {
		GoodsErpSizeChartResult result = new GoodsErpSizeChartResult();
		// MLB, MLBK 신발(SH)/모자(CP) 일 경우 신규 추가 테이블을 이용하여 사이즈조견표HTML 생성 (20181114_ds)
		if(String.valueOf(GoodsEnum.BrandGrpCode.ML).equals(brndGrpId) && ("SH".equals(prdlstCd) || "CP".equals(prdlstCd))) {
			Map<String, String> paramMap = new HashMap<String, String>(); 
			paramMap.put("brndId", brndId);
			paramMap.put("prdlstCd", prdlstCd);
			/*if(erpGodData.getFitSectCd() != null) {
				paramMap.put("fitDetailSectCd", erpGodData.getFitDetailSectCd());
			}*/
			List<Map<String, String>> mlbSizeLktbList = goodsErpRepository.getMlbGoodsSizeLktbExList(paramMap);
			if(mlbSizeLktbList!=null && mlbSizeLktbList.size()>0) {
				for(Map<String, String> mlbSizeLktb : mlbSizeLktbList) {
					if(String.valueOf(GoodsEnum.KOR).equals(mlbSizeLktb.get("LANG_CD"))) {
						result.setSizeLktb(mlbSizeLktb.get("SIZE_LKTB_HTML"));
						result.setMobileSizeLktb(mlbSizeLktb.get("MOBILE_SIZE_LKTB_HTML"));
					}
					else if(String.valueOf(GoodsEnum.ENG).equals(mlbSizeLktb.get("LANG_CD"))) {
						result.setSizeLktbEng(mlbSizeLktb.get("SIZE_LKTB_HTML"));
						result.setMobileSizeLktbEng(mlbSizeLktb.get("MOBILE_SIZE_LKTB_HTML"));
					}
					else if(String.valueOf(GoodsEnum.CHI).equals(mlbSizeLktb.get("LANG_CD"))) {
						result.setSizeLktbChi(mlbSizeLktb.get("SIZE_LKTB_HTML"));
						result.setMobileSizeLktbChi(mlbSizeLktb.get("MOBILE_SIZE_LKTB_HTML"));
					}
				}
			}
		} 
		else {
			InfGodSizeLktb sizeParam = new InfGodSizeLktb();
			sizeParam.setDsgnGrpNo(dsgnGrpNo);
			sizeParam.setBrndId(brndId);
			
			List<InfGodSizeLktbPomExtend> pomExList = goodsErpRepository.getErpGoodsSizeLktbPomExList(sizeParam);
			List<InfGodSizeLktb> sizeList = goodsErpRepository.getErpGoodsSizeLktbList(sizeParam);
			
			//	PC
			result.setSizeLktb(getSizeChartByPC(String.valueOf(GoodsEnum.KOR), itemList, imageUrl, pomExList, sizeList));
			result.setSizeLktbEng(getSizeChartByPC(String.valueOf(GoodsEnum.ENG), itemList, imageUrl, pomExList, sizeList));
			result.setSizeLktbChi(getSizeChartByPC(String.valueOf(GoodsEnum.CHI), itemList, imageUrl, pomExList, sizeList));
			
			//	MOBILE
			result.setMobileSizeLktb(getSizeChartByMobile(String.valueOf(GoodsEnum.KOR), itemList, imageUrl, pomExList, sizeList));
			result.setMobileSizeLktbEng(getSizeChartByMobile(String.valueOf(GoodsEnum.ENG), itemList, imageUrl, pomExList, sizeList));
			result.setMobileSizeLktbChi(getSizeChartByMobile(String.valueOf(GoodsEnum.CHI), itemList, imageUrl, pomExList, sizeList));
		}
		return result;		
	}

	
	/**
	 * ERP 상품 핏조견표 정보 조회
	 * 
	 * @param godNo
	 * @param langCd
	 * @param pomCd
	 * @return
	 */
	public InfGodFitLktbExtend getGoodsErpFitGuide(String godNo, String langCd, String pomCd) {		
		InfGodFitLktbExtend fitParam = new InfGodFitLktbExtend();
		fitParam.setGodNo(godNo);
		fitParam.setLangCd(langCd);
				
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("godNo", godNo);
		paramMap.put("pomCd", pomCd);
		
		if(goodsErpRepository.isExistFitGuideCheckSizePomCd(paramMap) > 0){
			fitParam.setPomCd(pomCd);			
		}
		
		return goodsErpRepository.getGoodsErpFitGuide(fitParam);		
	}
}
