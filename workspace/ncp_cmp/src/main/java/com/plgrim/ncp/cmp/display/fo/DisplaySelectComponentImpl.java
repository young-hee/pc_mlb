/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shsunhee.kim
 * @since       2015. 12. 1       
 */
package com.plgrim.ncp.cmp.display.fo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnr;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrContt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrSet;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspTmplat;
import com.plgrim.ncp.base.entities.datasource1.god.GodImg;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndImg;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPopupNoti;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplatHistExtends;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.SysInfoEnum;
import com.plgrim.ncp.biz.brand.data.BrandResultDTO;
import com.plgrim.ncp.biz.display.data.DisplayPageResultDTO;
import com.plgrim.ncp.biz.display.data.DspCnrScFrDTO;
import com.plgrim.ncp.biz.display.data.DspCnrSearchFoDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryResultFoDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryResultMbDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.data.DspCtgrySearchFoDTO;
import com.plgrim.ncp.biz.display.data.DspPlanDetailResultFoDTO;
import com.plgrim.ncp.biz.display.data.DspPlanResultFoDTO;
import com.plgrim.ncp.biz.display.data.DspPlanSearchFoDTO;
import com.plgrim.ncp.biz.display.data.DspPromtScFrDTO;
import com.plgrim.ncp.biz.display.data.DspStrendResultFoDTO;
import com.plgrim.ncp.biz.display.data.DspStrendSearchFoDTO;
import com.plgrim.ncp.biz.display.data.GnbResultFoDTO;
import com.plgrim.ncp.biz.display.data.ThemaPageResultFoDTO;
import com.plgrim.ncp.biz.display.result.DspBrndImgResult;
import com.plgrim.ncp.biz.display.result.DspCnrConttFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrConttFrResult;
import com.plgrim.ncp.biz.display.result.DspCnrConttGodFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrConttStrndFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrFrResult;
import com.plgrim.ncp.biz.display.result.DspCnrGodFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrSetFoResult;
import com.plgrim.ncp.biz.display.result.DspCnrSetFrResult;
import com.plgrim.ncp.biz.display.result.DspCommonGodFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryBrndFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryFoResult;
import com.plgrim.ncp.biz.display.result.DspCtgryFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryGodFoResult;
import com.plgrim.ncp.biz.display.result.DspPlanFoResult;
import com.plgrim.ncp.biz.display.result.DspPlanGodFoResult;
import com.plgrim.ncp.biz.display.result.DspPlanSprtrFoResult;
import com.plgrim.ncp.biz.display.result.DspPromtFrResult;
import com.plgrim.ncp.biz.display.result.DspStrendFoResult;
import com.plgrim.ncp.biz.display.result.DspTmplatFrResult;
import com.plgrim.ncp.biz.display.result.V2DspFoCtgryResult;
import com.plgrim.ncp.biz.display.service.DisplayCategoryFrService;
import com.plgrim.ncp.biz.display.service.DisplayCategoryService;
import com.plgrim.ncp.biz.display.service.DisplayCnrConttFrService;
import com.plgrim.ncp.biz.display.service.DisplayCornerContentsService;
import com.plgrim.ncp.biz.display.service.DisplayCornerService;
import com.plgrim.ncp.biz.display.service.DisplayPlanService;
import com.plgrim.ncp.biz.display.service.DisplayPromtFrService;
import com.plgrim.ncp.biz.display.service.DisplayStrendService;
import com.plgrim.ncp.biz.display.service.InformationPageService;
import com.plgrim.ncp.biz.goods.data.GoodsListMbDTO;
import com.plgrim.ncp.biz.goods.data.GoodsListMbResultDTO;
import com.plgrim.ncp.biz.goods.data.GoodsPriceSearchDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.vendor.service.VendorBrndService;
import com.plgrim.ncp.cmp.product.fo.GoodsPriceFOComponent;
import com.plgrim.ncp.commons.data.DspRevDTO;
import com.plgrim.ncp.commons.repository.DisplayRevRepository;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.page.PageService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DisplaySelectComponentImpl extends AbstractComponent implements DisplaySelectComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DisplayCategoryFrService displayCategoryFrService;
	
	@Autowired
	DisplayCnrConttFrService displayCnrConttFrService;
	
	@Autowired
	DisplayCornerService displayCornerService;
	
	@Autowired
	DisplayPromtFrService displayPromtFrService;
	
	@Autowired
	DisplayPlanService displayPlanService;
	
	@Autowired
	DisplayStrendService displayStrendService;
	
	@Autowired
    DisplayRevRepository displayRevRepository;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	GoodsPriceFOComponent goodsPriceFOComponent;

	@Autowired
	DisplayCategoryService displayCategorysService;

	@Autowired
	InformationPageService informationPageService;

	@Autowired
	DisplayCornerContentsService displayCornerContentsService;

	@Autowired
	VendorBrndService vendorBrndService;

	@Value("${ncp_base.display.revision.base}")
	Long baseRevSn;

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectDspCnrList(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.display.data.DspCnrScFrDTO)
	 */
	@Override
    public List<DspCnrFrResult> selectDspCnrList(DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
		
		//회원유형 정보 set
		setMbrInfo(dspCnrScFrDTO);
		
		//템플릿 유형별 기본정보 및 템플릿정보
		List<DspTmplatFrResult> tmplatList = displayCnrConttFrService.selectTmplatPageInfo(dspCnrScFrDTO);
		String tmplatUrl = null;
		if(tmplatList != null && tmplatList.size() > 0) {
			tmplatUrl = tmplatList.get(0).getDspTmplat().getTmplatCnncUrl();
			
			dspCnrScFrDTO.setTmplatSn(tmplatList.get(0).getDspTmplat().getTmplatSn());
			dspCnrScFrDTO.setTmplatCnrList(tmplatList);
			dspCnrScFrDTO.setTmplatCnncUrl(tmplatUrl);
		} 
//		else {
//			throw new Exception();
//		}
		
		//코너컨텐츠 목록 조회
		List<DspCnrFrResult> cnrList = displayCnrConttFrService.selectDspCnrList(dspCnrScFrDTO);
		
		//코너컨텐츠 자동상품 적용
		//setAltrtvGodList(cnrList, pk, dspCnrScFrDTO);
		
	    return cnrList;
    }
	
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectTmplatPageInfo(SystemPK, DspCnrScFrDTO)
	 */
	@Override
    public List<DspTmplatFrResult> selectTmplatPageInfo(DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
	    return displayCnrConttFrService.selectTmplatPageInfo(dspCnrScFrDTO);
    }
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectBrandAllList(com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO)
	 */
	@Override
    public List<DspCtgryBrndFrResult> selectBrandAllList(DspCtgryScFrDTO dto)
            throws Exception {
		setMbrInfo(dto);
	    return displayCategoryFrService.selectBrandAllList(dto);
    }
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectBrandAllListCount(com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO)
	 */
	@Override
    public List<Map<String, Object>> selectBrandAllListCount(DspCtgryScFrDTO dto)
            throws Exception {
		setMbrInfo(dto);
	    return displayCategoryFrService.selectBrandAllListCount(dto);
    }
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectBrandLnbCtgryList(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.display.data.DspCnrScFrDTO)
	 */
	@Override
    public List<DspCtgryFrResult> selectBrandLnbCtgryList(DspCtgryScFrDTO dto) throws Exception {
		List<DspCtgryFrResult> result = null;
		setMbrInfo(dto);
		
		result = displayCategoryFrService.selectBrandLnbCtgryList(dto);
		
	    return result;
    }

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectCtgryGodFrList(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO)
	 */
	@Override
    public List<DspCommonGodFrResult> selectCtgryGodFrList(DspCtgryScFrDTO dto) throws Exception {
		setMbrInfo(dto);
		
		List<DspCommonGodFrResult> result = null;
		
		result = displayCategoryFrService.selectCtgryGodFrList(dto);
		
	    return result;
    }


	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectCtgryGodFrTotCnt(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO)
	 */
	@Override
    public int selectCtgryGodFrTotCnt(DspCtgryScFrDTO dto) throws Exception {
		setMbrInfo(dto);
		
		int result = 0;
		
		result = displayCategoryFrService.selectCtgryGodFrTotCnt(dto);
		
	    return result;
    }
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectDisplayCategoryInfo(com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO)
	 */
	@Override
    public DspCtgryFrResult selectDisplayCategoryInfo(DspCtgryScFrDTO dto) throws Exception {
	    return displayCategoryFrService.selectDisplayCategoryInfo(dto);
    }
	
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#getLocationList(java.lang.String, com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO, com.plgrim.ncp.biz.display.result.DspCtgryFrResult)
	 */
	@Override
    public List<DspCtgryFrResult> getLocationList(String locType, DspCtgryScFrDTO dto, DspCtgryFrResult brandShopInfo) throws Exception {
		
		// location Home 
		List<DspCtgryFrResult> location = new ArrayList<DspCtgryFrResult>();
		DspCtgryFrResult home = new DspCtgryFrResult();
		home.setLocationUrl("/public/display/main/view");
		home.setLocationName("Home");
		location.add(home);
		
		if(locType.equals("L_BEAKER_C")) {
			//Beaker Brand Category
			//(Home > Beaker > Men )
			//(Home > Beaker > Men[대카] > (티셔츠)[중카] > .. )
			DspCtgryFrResult beaker = new DspCtgryFrResult();
			beaker.setLocationUrl("/public/display/beaker/view");
			beaker.setLocationName("BEAKER");
			location.add(beaker);
			
			//dspCtgryNo 로 상위 카테고리 목록 생성 후 생성
			List<DspCtgryFrResult> list = displayCategoryFrService.selectLocationList(dto);
			DspCtgryFrResult locL = list.get(1);
			DspCtgry locDspCtgoryL = locL.getDspCtgry();
			locL.setLocationName(locDspCtgoryL.getDspCtgryNm());
			locL.setLocationUrl("#");
			location.add(locL);
			
			
			//NEW
			if(StringService.isNotEmpty(dto.getNewBest()) && "new".equals(dto.getNewBest())) {
				DspCtgryFrResult newDspCtgry = new DspCtgryFrResult();
				newDspCtgry.setLocationUrl("#");
				newDspCtgry.setLocationName("NEW");
				location.add(newDspCtgry);
			}
			//BEST
			else if(StringService.isNotEmpty(dto.getNewBest()) && "best".equals(dto.getNewBest())) {
				DspCtgryFrResult bstDspCtgry = new DspCtgryFrResult();
				bstDspCtgry.setLocationUrl("#");
				bstDspCtgry.setLocationName("BEST");
				location.add(bstDspCtgry);
			}
			
			for(int i = 2; i < list.size(); i ++){
				DspCtgryFrResult loc = list.get(i);
				DspCtgry locDspCtgory = loc.getDspCtgry();
				loc.setLocationName(locDspCtgory.getDspCtgryNm());
				loc.setLocationUrl("#");
				location.add(loc);
			}
			
		}
		else if(locType.equals("L_BEAKER_I")) {
			//(Home > Beaker  )
			DspCtgryFrResult beaker = new DspCtgryFrResult();
			beaker.setLocationUrl("/public/display/beaker/view");
			beaker.setLocationName("BEAKER");
			location.add(beaker);
			
		}
		else if(locType.equals("L_BEAKER_B")) {
			//(Home > Beaker > BRAND > 해당Brandshop )
			DspCtgryFrResult beaker = new DspCtgryFrResult();
			beaker.setLocationUrl("/public/display/beaker/view");
			beaker.setLocationName("BEAKER");
			location.add(beaker);
			
			DspCtgryFrResult brand = new DspCtgryFrResult();
			brand.setLocationUrl("/public/display/beaker/index/view");
			brand.setLocationName("BRAND");
			location.add(brand);
			
			//brandShopNo name
			DspCtgryFrResult brnd = new DspCtgryFrResult();
			brnd.setLocationUrl("/public/display/brand/"+brandShopInfo.getDspCtgry().getDspCtgryNo()+"/nview");
			brnd.setLocationName(brandShopInfo.getDspCtgry().getDspCtgryNm());
			location.add(brnd);
			
		}
		else if(locType.equals("L_BEAKER_S")) {
			//Home > Beaker > 대> 중 > 소 > ...
			DspCtgryFrResult beaker = new DspCtgryFrResult();
			beaker.setLocationUrl("/public/display/beaker/view");
			beaker.setLocationName("BEAKER");
			location.add(beaker);
			
			//SALE
			DspCtgryFrResult saleDspCtgry = new DspCtgryFrResult();
			saleDspCtgry.setLocationUrl("#");
			saleDspCtgry.setLocationName("SALE");
			location.add(saleDspCtgry);
			
			List<DspCtgryFrResult> list = displayCategoryFrService.selectLocationList(dto);
			for(int i = 1; i < list.size(); i ++){
				DspCtgryFrResult loc = list.get(i);
				DspCtgry locDspCtgory = loc.getDspCtgry();
				loc.setLocationName(locDspCtgory.getDspCtgryNm());
				loc.setLocationUrl("/public/display/brand/sale/list?dspCtgryNo=DXMA01&turn=6");
				location.add(loc);
			}
		}
		else if(locType.equals("L_BRAND")) {
			//그외 브랜드
			//Home > 해당Brandshop > 대> 중 > 소 > ...
			DspCtgryFrResult brand = new DspCtgryFrResult();
			brand.setLocationUrl("/public/display/brand/"+brandShopInfo.getDspCtgry().getDspCtgryNo()+"/view");
			brand.setLocationName(brandShopInfo.getDspCtgry().getDspCtgryNm());
			location.add(brand);
			
			//NEW
			if("new".equals(StringService.isNotEmpty(dto.getNewBest()))) {
				DspCtgryFrResult newDspCtgry = new DspCtgryFrResult();
				newDspCtgry.setLocationUrl("#");
				newDspCtgry.setLocationName("NEW");
				location.add(newDspCtgry);
			}
			//BEST
			else if("new".equals(StringService.isNotEmpty(dto.getNewBest()))) {
				DspCtgryFrResult bstDspCtgry = new DspCtgryFrResult();
				bstDspCtgry.setLocationUrl("#");
				bstDspCtgry.setLocationName("BEST");
				location.add(bstDspCtgry);
			}
			
			List<DspCtgryFrResult> list = displayCategoryFrService.selectLocationList(dto);
			for(int i = 1; i < list.size(); i ++){
				DspCtgryFrResult loc = list.get(i);
				DspCtgry locDspCtgory = loc.getDspCtgry();
				loc.setLocationName(locDspCtgory.getDspCtgryNm());
				if(locDspCtgory.getCtgryDpthCd().equals("1")){
					String outputType = brandShopInfo.getDspCtgry().getExpsrLvlCd();
					if(outputType == null){
						outputType = "A_TP_LAG_MID_SML_DTL";
					}
					if(!outputType.equals("B_TP_MID_SML_DTL")){
						loc.setLocationUrl("/public/display/category/1d/view?dspCtgryNo="+locDspCtgory.getDspCtgryNo());
						loc.setDspCtgry(null);
						location.add(loc);
					}

				}else{
					loc.setLocationUrl("#");
					location.add(loc);
				}
			}
		}
		return location;
		
    }
	
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#getPromtLocation(java.lang.String, com.plgrim.ncp.biz.display.data.DspPromtScFrDTO)
	 */
	@Override
	public DspPlanDetailResultFoDTO getPromtLocation(String locType, DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		DspPlanDetailResultFoDTO detail = new DspPlanDetailResultFoDTO();
		
		DspPlanSearchFoDTO searchDTO = new DspPlanSearchFoDTO();
		
		searchDTO.setMallId(dspPromtScFrDTO.getMallId());
		searchDTO.setLang(dspPromtScFrDTO.getLang());
		searchDTO.setDevice(dspPromtScFrDTO.getDevice());
		
		searchDTO.setSpcPrmTp(dspPromtScFrDTO.getSpcPrmTp());
		searchDTO.setPrcSectCd(dspPromtScFrDTO.getPrcSectCd());
				
		searchDTO.setAplMbrTp(dspPromtScFrDTO.getAplMbrTp());
		searchDTO.setAplMbrAtrb(dspPromtScFrDTO.getAplMbrAtrb());
		searchDTO.setPsitnGrpcoId(dspPromtScFrDTO.getGrpcoId());
		
		searchDTO.setSectCd(dspPromtScFrDTO.getSectCd());
		searchDTO.setMode(dspPromtScFrDTO.getMode());
		
		if(ContextService.hasRole()){
        	SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
		}
		
		if(locType.equals("L_PROMT")) {
			//기획전
			if("brnd".equals(searchDTO.getMode())){
				//브랜드 리스트
				List<DspPlanFoResult> brndList = displayPlanService.selectbrndList(searchDTO);
				detail.setDspBrndList(brndList);
				searchDTO.setAplBrndId(searchDTO.getSectCd());
			}else{
				//카테고리 리스트
				List<DspPlanFoResult> ctgryList = displayPlanService.selectCtgryList(searchDTO);
				detail.setDspCtgryList(ctgryList);
				searchDTO.setCategoryNo(searchDTO.getSectCd());
			}
			
			//기획전 리스트
			List<DspPlanFoResult> dspPlanList = displayPlanService.selectPlanList(searchDTO);
			detail.setDspPlanList(dspPlanList);
		}
		
		return detail;
	}
	
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectCnncGodNos(com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO)
	 */
	@Override
    public List<String> selectCnncGodNos(DspCtgryScFrDTO dto) throws Exception {
		setMbrInfo(dto);
		
	    return displayCategoryFrService.selectCnncGodNos(dto);
    }
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectBrndCtgGodCount(com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO)
	 */
	@Override
    public Map<String, Integer> selectBrndCtgGodCount(List<DspCtgryFrResult> treeList, DspCtgryScFrDTO dto) throws Exception {
		setMbrInfo(dto);
		
		List<String> ctgList = new ArrayList<String>();
		for(DspCtgryFrResult dspCtgryFrResult: treeList) {
			ctgList.add(dspCtgryFrResult.getDspCtgry().getDspCtgryNo());
		}
		dto.setCateList(ctgList);
		
	    return displayCategoryFrService.selectBrndCtgGodCount(dto);
    }
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectStrndSnForBrnd(com.plgrim.ncp.biz.display.data.DspCnrScFrDTO)
	 */
	@Override
    public Long selectStrndSnForBrnd(DspCnrScFrDTO dspCnrScFrDTO)   throws Exception {
		return displayCnrConttFrService.selectStrndSnForBrnd(dspCnrScFrDTO);
    }
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectPromtGodList(com.plgrim.ncp.biz.display.data.DspPromtScFrDTO)
	 */
	@Override
    public DspPromtFrResult selectPromtGodList( DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		
		//회원유형 정보 set
		setMbrInfo(dspPromtScFrDTO);
		
		return displayPromtFrService.selectPromtGodList(dspPromtScFrDTO);
    }
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#getPromtBrandList(com.plgrim.ncp.biz.display.data.DspPlanSearchFoDTO)
	 */
	@Override
    public DspPlanResultFoDTO selectPromtBrandList(DspPlanSearchFoDTO searchDTO, DspCnrScFrDTO dspCnrScFrDTO, SystemPK pk) throws Exception {
		
		setMbrInfo(dspCnrScFrDTO);
		
		searchDTO.setMallId(dspCnrScFrDTO.getMallId());
		searchDTO.setLang(dspCnrScFrDTO.getLang());
		searchDTO.setDevice(dspCnrScFrDTO.getDevice());
		
		searchDTO.setSpcPrmTp(dspCnrScFrDTO.getSpcPrmTp());
		searchDTO.setPrcSectCd(dspCnrScFrDTO.getPrcSectCd());
				
		searchDTO.setAplMbrTp(dspCnrScFrDTO.getAplMbrTp());
		searchDTO.setAplMbrAtrb(dspCnrScFrDTO.getAplMbrAtrb());
		searchDTO.setPsitnGrpcoId(dspCnrScFrDTO.getGrpcoId());
		
		if(ContextService.hasRole()){
        	SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
		}
		
		searchDTO.setMode("brnd");
		searchDTO.setAplBrndId(DisplayEnum.BEKER_BRND_ID.toString());
		
		DspPlanResultFoDTO dspPlanFoResultDTO = new DspPlanResultFoDTO();
		
		//기획전 브랜드 기준 리스트
		List<DspPlanFoResult> dspPlanBrandList = displayPlanService.selectPlanBrandList(searchDTO, pk);
		dspPlanFoResultDTO.setDspPlanList(dspPlanBrandList);

		long totalCount = displayPlanService.selectPlanBrandyCount(searchDTO, pk);
		dspPlanFoResultDTO.setTotal(totalCount);
		
		return dspPlanFoResultDTO;
    }
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectStrndBrandList(com.plgrim.ncp.biz.display.data.DspStrendSearchFoDTO, com.plgrim.ncp.biz.display.data.DspCnrScFrDTO, com.plgrim.ncp.framework.data.SystemPK)
	 */
	@Override
	public DspStrendResultFoDTO selectStrndBrandList (DspStrendSearchFoDTO searchDTO, DspCnrScFrDTO dspCnrScFrDTO, SystemPK pk) throws Exception {
		setMbrInfo(dspCnrScFrDTO);
		
		searchDTO.setMallId(dspCnrScFrDTO.getMallId());
		searchDTO.setLang(dspCnrScFrDTO.getLang());
		searchDTO.setDevice(dspCnrScFrDTO.getDevice());		
				
		searchDTO.setAplMbrTp(dspCnrScFrDTO.getAplMbrTp());
		searchDTO.setAplMbrAtrb(dspCnrScFrDTO.getAplMbrAtrb());
		searchDTO.setGrpcoId(dspCnrScFrDTO.getGrpcoId());
		searchDTO.setEmpYn(dspCnrScFrDTO.getEmpYn());		
		
		DspStrendResultFoDTO dspStrendResultFoDTO = new DspStrendResultFoDTO();
		
		DspStrendSearchFoDTO brSearchDTO = new DspStrendSearchFoDTO();
		brSearchDTO.setMallId(pk.getMall());
		brSearchDTO.setDevice(pk.getDevice());
		brSearchDTO.setLang(pk.getLang());
		brSearchDTO.setStrndTpCd(searchDTO.getStrndTpCd());
		brSearchDTO.setBrndNotInYn("N");
		brSearchDTO.setUpBrndId(DisplayEnum.BEKER_BRND_ID.toString());
		
		List<DspStrendFoResult> brndList = displayStrendService.selectStrendBrand(brSearchDTO);
		
		PageService p = new PageService();
		PageParam pageParam = p.buildPageParam(searchDTO.getGPageNo(), searchDTO.getGPageSize());
		
		searchDTO.setUpBrndId(DisplayEnum.BEKER_BRND_ID.toString());
		if(StringService.isNotEmpty(searchDTO.getBrndId())){
			searchDTO.setUpBrndId(null);
		} 
		else {
			searchDTO.setBrndId(null);
		}
		List<DspStrendFoResult> strendList = displayStrendService.selectStrendList(pk,searchDTO, pageParam);
		
		long total = displayStrendService.selectStrendCount(searchDTO);
		
		dspStrendResultFoDTO.setTotal((int)total);
		dspStrendResultFoDTO.setStrendList(strendList);
		dspStrendResultFoDTO.setBrndList(brndList);
		
		return dspStrendResultFoDTO;
	}
	
	/**
	 * 상품컨텐츠 대체로직 적용 (NEW, BEST).
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param cnrList [설명]
	 * @param pk [설명]
	 * @param dspCnrScFrDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 23
	 */
	public void setAltrtvGodList(List<DspCnrFrResult> cnrList, SystemPK pk, DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
		
		dspCnrScFrDTO.setMallId(pk.getMall());
		dspCnrScFrDTO.setLang(pk.getLang());
		
		for(DspCnrFrResult dspCnrfrResult: cnrList) {
			
			List<DspCnrSetFrResult> setList = dspCnrfrResult.getDspCnrSetList();
			
			if(setList != null && setList.size() > 0) {
				for(DspCnrSetFrResult dspCnrSetFrResult: setList) {
					//대체상품전시로직 적용코드
					String altrtvDspGodTpCd = dspCnrSetFrResult.getDspCnrSet().getAltrtvDspGodTpCd();
					
					//상품컨텐츠 목록
					List<DspCnrConttFrResult> conttGodList = dspCnrSetFrResult.getDspCnrConttGodList();
					
					if(conttGodList != null && conttGodList.size() > 0) {
						//전시개수
//						int dspCnt = dspCnrSetFrResult.getDspCnrTpGrp().getDspCnt();
						int dspCnt = 0;
						//상품컨텐츠 수
						int godCnt = conttGodList.size();
						
						if(StringService.isNotEmpty(altrtvDspGodTpCd) && (godCnt < dspCnt)) {
							dspCnrScFrDTO.setCnrSn(dspCnrSetFrResult.getDspCnrSet().getCnrSn());
							dspCnrScFrDTO.setCnrSetSn(dspCnrSetFrResult.getDspCnrSet().getCnrSetSn());
							dspCnrScFrDTO.setLastIndex((dspCnt - godCnt));
							dspCnrScFrDTO.setNotIncludeGodNos(conttGodList);
							dspCnrScFrDTO.setAltScNm(dspCnrSetFrResult.getDspCnrSet().getSetNm());
														
							List<DspCnrConttFrResult> altGodList = null;
							if(altrtvDspGodTpCd.equals(DisplayEnum.ALTRTV_DSP_GOD_TP.CTGRY_BST.toString())) {
									
								altGodList = displayCnrConttFrService.selectCtgryBstGodList(dspCnrScFrDTO);								
							}
							else if(altrtvDspGodTpCd.equals(DisplayEnum.ALTRTV_DSP_GOD_TP.CTGRY_NEW_GOD.toString())) {
								
								altGodList = displayCnrConttFrService.selectCtgryNewGodList(dspCnrScFrDTO);
							}
							else if(altrtvDspGodTpCd.equals(DisplayEnum.ALTRTV_DSP_GOD_TP.BRND_BST.toString())) {
								//brndId
								if(dspCnrScFrDTO.getTmplatCnrList().get(0).getDspCtgry() != null) {
									List<String> brndId = new ArrayList<String>();
									brndId.add(dspCnrScFrDTO.getTmplatCnrList().get(0).getDspCtgry().getDspBrndId());
									
									dspCnrScFrDTO.setBrandIds(brndId);
								}
																
								altGodList = displayCnrConttFrService.selectBrndBstGodList(dspCnrScFrDTO);
							}
							else if(altrtvDspGodTpCd.equals(DisplayEnum.ALTRTV_DSP_GOD_TP.BRND_NEW_GOD.toString())) {
								//brndId
								if(dspCnrScFrDTO.getTmplatCnrList().get(0).getDspCtgry() != null) {
									List<String> brndId = new ArrayList<String>();
									brndId.add(dspCnrScFrDTO.getTmplatCnrList().get(0).getDspCtgry().getDspBrndId());
									
									dspCnrScFrDTO.setBrandIds(brndId);
								}
								
								altGodList = displayCnrConttFrService.selectBrndNewGodList(dspCnrScFrDTO);
							}
							else if(altrtvDspGodTpCd.equals(DisplayEnum.ALTRTV_DSP_GOD_TP.BRND_SESON_BST.toString())) {
								//brndId
								if(dspCnrScFrDTO.getTmplatCnrList().get(0).getDspCtgry() != null) {
									List<String> brndId = new ArrayList<String>();
									brndId.add(dspCnrScFrDTO.getTmplatCnrList().get(0).getDspCtgry().getDspBrndId());
									
									dspCnrScFrDTO.setBrandIds(brndId);
								}
								
								altGodList = displayCnrConttFrService.selectBrndSesonBstGodList(dspCnrScFrDTO);
							}
							else if(altrtvDspGodTpCd.equals(DisplayEnum.ALTRTV_DSP_GOD_TP.BRND_SESON_NEW_GOD.toString())) {
								//brndId
								if(dspCnrScFrDTO.getTmplatCnrList().get(0).getDspCtgry() != null) {
									List<String> brndId = new ArrayList<String>();
									brndId.add(dspCnrScFrDTO.getTmplatCnrList().get(0).getDspCtgry().getDspBrndId());
									
									dspCnrScFrDTO.setBrandIds(brndId);
								}
								
								altGodList = displayCnrConttFrService.selectBrndSesonNewGodList(dspCnrScFrDTO);
							}
							
							conttGodList.addAll(altGodList);

						}
					}
				}
			}
		}
		
	}	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	// 2016/07 (UX/UI) 
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectDspCnrListV2(com.plgrim.ncp.biz.display.data.DspCnrScFrDTO)
	 */
	///////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<DspCnrFrResult> selectDspCnrListV2(DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
		//회원유형 정보 set
		setMbrInfo(dspCnrScFrDTO);
		
		// 리비전번호(revSn)이 넘어오는 경우(=미리보기)에는 IP 체크하여 Admin 여부 확인
		if(dspCnrScFrDTO.getRevSn() == null || !displayRevRepository.isPsbPreviewIP()){
            // 개정순번 조회
            DspRevDTO dspRevDTO = new DspRevDTO();
            dspRevDTO.setTmplatScKey(dspCnrScFrDTO.getTmplatScKey());
            dspRevDTO.setTmplatKeyBrndId(dspCnrScFrDTO.getTmplatKeyBrndId());

            DspRevDTO revSnResult = displayRevRepository.getRevSnResult(dspRevDTO);

			dspCnrScFrDTO.setRevSn(Long.valueOf(1));
			dspCnrScFrDTO.setRevCpstTurn(1);
			dspCnrScFrDTO.setAbTestSn(Long.valueOf(1));
		}else{
            dspCnrScFrDTO.setIsPrevYn("Y");
        }

		//템플릿 유형별 기본정보 및 템플릿정보
		List<DspTmplatFrResult> tmplatList = displayCnrConttFrService.selectTmplatPageInfoV2(dspCnrScFrDTO);
		String tmplatUrl = null, tmplatDscr = null;
		if(tmplatList != null && tmplatList.size() > 0) {
			tmplatUrl = tmplatList.get(0).getDspTmplat().getTmplatCnncUrl();
			tmplatDscr = tmplatList.get(0).getDspTmplat().getTmplatDscr();
			
			dspCnrScFrDTO.setTmplatSn(tmplatList.get(0).getDspTmplat().getTmplatSn());
			dspCnrScFrDTO.setTmplatCnrList(tmplatList);
			dspCnrScFrDTO.setTmplatCnncUrl(tmplatUrl);
			dspCnrScFrDTO.setTmplatDscr(tmplatDscr);
		} 
		else {
			return null;
		}
		
		List<String> conttTpList = displayCnrConttFrService.selectDspCnrConttTp(dspCnrScFrDTO);
		dspCnrScFrDTO.setConttTpList(conttTpList);
		
		//코너컨텐츠 목록 조회
		List<DspCnrFrResult> cnrList = displayCnrConttFrService.selectDspCnrListV2(dspCnrScFrDTO);
		
		
	    return cnrList;
	}


	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectTmplatPageInfoV2(com.plgrim.ncp.biz.display.data.DspCnrScFrDTO)
	 */
	@Override
	public List<DspTmplatFrResult> selectTmplatPageInfoV2(DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
		return displayCnrConttFrService.selectTmplatPageInfoV2(dspCnrScFrDTO);
	}
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectBrndCtgryBrndId(java.lang.String)
	 */
	@Override
	public String selectBrndCtgryBrndId(String dspCtgryNo) throws Exception {
		return displayCnrConttFrService.selectBrndCtgryBrndId(dspCtgryNo);
	}
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectTopBrand(com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO)
	 */
	@Override
	public List<DspCtgryBrndFrResult> selectTopBrand(DspCtgryScFrDTO dto) throws Exception {
		//회원유형 정보 set
		setMbrInfo(dto);
		
		return displayCategoryFrService.selectTopBrand(dto);
	}
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectBrandImg(java.lang.String, java.lang.String)
	 */
	@Override
	public SysBrndImg selectBrandImg(SystemPK pk, String brandShopNo, String brndImgSectCd) throws Exception {
		String dspBrndId = displayCnrConttFrService.selectBrndCtgryBrndId(brandShopNo);
		
		SysBrndImg sysBrndImg = displayCategoryFrService.selectSysBrndImg(pk, dspBrndId, brndImgSectCd);
		
		return sysBrndImg;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectBrndCtgryList(com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO, com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.goods.data.GoodsPriceSearchFoDTO)
	 */
	@Override
	public List<DspCtgryFrResult> selectBrndCtgryList(DspCtgryScFrDTO dto, SystemPK pk, GoodsPriceSearchDTO goodsPriceSearchFoDTO)
	        throws Exception {
		setMbrInfo(dto);
		dto.setMallId(pk.getMall());
		dto.setLang(pk.getLang());
		dto.setDevice(pk.getDevice());
		dto.setSpcPrmTp(goodsPriceSearchFoDTO.getSpcPrmTp());
		dto.setPrcSectCd(goodsPriceSearchFoDTO.getPrcSectCd());
		
		return displayCategoryFrService.selectBrndCtgryList(dto);
	}
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectAllBrand(com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO)
	 */
	@Override
	public List<DspCtgryBrndFrResult> selectAllBrand(DspCtgryScFrDTO dto) throws Exception {
		//회원유형 정보 set
		setMbrInfo(dto);
		
		return displayCategoryFrService.selectAllBrand(dto);
	}
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectSpecialBrand(com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO, java.util.List)
	 */
	@Override
	public List<DspCtgryBrndFrResult> selectSpecialBrand(DspCtgryScFrDTO scDTO, List<DspCnrFrResult> cnrList) throws Exception {
		//회원유형 정보 set
		setMbrInfo(scDTO);
		
		if(cnrList != null) {
			List<String> bdmCtgNoList = new ArrayList<String>();
			
			for(DspCnrFrResult cnr: cnrList) {
				DspCnr cnrinfo = cnr.getDspCnr();
				if(cnrinfo != null) {
					if(StringService.isNotEmpty(cnrinfo.getDspCnrDscr()) && "SPECIAL".equals(cnrinfo.getDspCnrDscr())) {
						List<DspCnrSetFrResult> set = cnr.getDspCnrSetList();
						if(set != null && set.size() >0 ) {
							DspCnrSetFrResult setinfo = set.get(0);
							List<DspCnrConttFrResult> txtlist = setinfo.getDspCnrConttTextList();
							if(txtlist != null && txtlist.size() >0 ) {
								for(DspCnrConttFrResult txt: txtlist) {
									bdmCtgNoList.add(txt.getDspCnrContt().getConttCnncUrl());
								}
							}
						}
					}
				}
			}
			if(bdmCtgNoList.size() > 0) {
				List<String> brndIds = displayCategoryFrService.selectDspBrndIdList(bdmCtgNoList);
				
				scDTO.setBrandIds(brndIds);
			}
		}
		
		return displayCategoryFrService.selectAllBrand(scDTO);
	}

	@Override
	public DspBrndImgResult selectBrandStory(String brandId) throws Exception{
		return displayCategoryFrService.selectBrandStory(brandId);
	}
	
	@Override
	public DspBrndImgResult selectBrandStoryInfo(DspCtgryScFrDTO dto) throws Exception{
		return displayCategoryFrService.selectBrandStoryInfo(dto);
	}

	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspCnrScFrDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 10. 13
	 */
	public void setMbrInfo(DspCnrScFrDTO dspCnrScFrDTO) throws Exception {
		MemberSimpleInfo info = setupMrbInfo();
		dspCnrScFrDTO.setEmpYn(info.empYn);
        dspCnrScFrDTO.setAplMbrAtrb(info.mbr.getMbrAtrbCd());
        dspCnrScFrDTO.setAplMbrTp(info.mbr.getMbrTpCd());
        dspCnrScFrDTO.setGrpcoId(info.mbr.getPsitnGrpcoId());
	}

	public void setMbrInfo(DspPromtScFrDTO dspPromtScFrDTO) throws Exception {
		MemberSimpleInfo info = setupMrbInfo();
        dspPromtScFrDTO.setEmpYn(info.empYn);
        dspPromtScFrDTO.setAplMbrAtrb(info.mbr.getMbrAtrbCd());
        dspPromtScFrDTO.setAplMbrTp(info.mbr.getMbrTpCd());
        dspPromtScFrDTO.setGrpcoId(info.mbr.getPsitnGrpcoId());
	}
	
	public void setMbrInfo(DspCtgryScFrDTO dto) throws Exception {
		MemberSimpleInfo info = setupMrbInfo();
        dto.setEmpYn(info.empYn);
        dto.setAplMbrAtrb(info.mbr.getMbrAtrbCd());
        dto.setAplMbrTp(info.mbr.getMbrTpCd());
        dto.setGrpcoId(info.mbr.getPsitnGrpcoId());
	}


	private MemberSimpleInfo setupMrbInfo() {
		MemberSimpleInfo info = new MemberSimpleInfo();

		if(ContextService.hasRole()){
        	SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
        	info.mbr = userDetail.getMbr();
        	String mbrAtrb = info.mbr.getMbrAtrbCd();
        	
        	if(mbrAtrb.equals("plgrim") || 
        			mbrAtrb.equals("PLGRIM_FSHN") ||
        			mbrAtrb.equals("GRPCO")){
        		info.empYn = "Y";
        	}
        }else{
        	info.mbr = new Mbr();
        	info.mbr.setMbrTpCd("NMBR");
        }
		return info;
	}
	
	@Data
	class MemberSimpleInfo {
		Mbr mbr;
		String empYn = "N";
	}


	// TODO 기존 DisplaySelectComponentImpl

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#getDisplayCategoryList(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.display.data.DisplayCategorySearchDTO)
	 */
	@Override
	public DspCtgryResultFoDTO selectDisplayCategroyList(SystemPK pk, DspCtgrySearchFoDTO dto) throws Exception {
		// 임직원 검사
		String empYn = "N";
		Mbr mbr = null;
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();

			if(mbrAtrb.equals("plgrim") ||
					mbrAtrb.equals("PLGRIM_FSHN") ||
					mbrAtrb.equals("GRPCO")){
				empYn = "Y";
			}
		}
		dto.setEmpYn(empYn);
		DspCtgryResultFoDTO resultDTO = new DspCtgryResultFoDTO();
		List<DspCtgryFoResult> dspCtgryResultList = displayCategorysService.selectSubDisplayCategoryList(pk, dto);
		resultDTO.setDspCtgryResultList(dspCtgryResultList);
		return resultDTO;
	}

	/**
	 * CSS Club 테마페이지 컨텐츠 가져오기
	 *
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<DspCnrFoResult> selectDspCtgrContt(
			SystemPK pk,
			DspCtgrySearchFoDTO searchDTO,
			String ctgyNoMain) throws Exception {

		/** 전시카테고리 템플릿 정보 가져오기 start */
		DspCtgryResultFoDTO resultSpecialDTO = this.selectDspCtgryView(pk,searchDTO);
		List<DspCnrFoResult> cnrList = new ArrayList<DspCnrFoResult>();
		/** 전시카테고리 템플릿 정보 가져오기 end */

		/** 코너-콘텐츠 정보 가져오기 start */
		//전시카테고리 템플릿-
		DspTmplat dspTmplat = resultSpecialDTO.getDspCtgryResult().getDspTmplat();
		if(dspTmplat != null){
			this.selectDisplayPageView(pk, cnrList, ctgyNoMain, dspTmplat.getTmplatSn(), "THEMA_PGE", "", "");
		}
		/** 코너-콘텐츠 정보 가져오기 end */

		return cnrList;
	}

	@Override
	public DspCtgryResultFoDTO selectDspCtgryView(SystemPK pk,
												  DspCtgrySearchFoDTO dto) throws Exception {

		// 임직원 검사
		String empYn = "N";
		Mbr mbr = null;
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();

			if(mbrAtrb.equals("plgrim") ||
					mbrAtrb.equals("PLGRIM_FSHN") ||
					mbrAtrb.equals("GRPCO")){
				empYn = "Y";
			}
		}
		dto.setEmpYn(empYn);

		DspCtgryResultFoDTO dspCtgry2dResult = new DspCtgryResultFoDTO();
		try{
			/** 전시카테고리2D 기본정보 조회 */
			if(!dto.getHasCtgryInfo().equals("N")){
				DspCtgryFoResult dspCtgryInfo = displayCategorysService.selectDisplayCategoryInfo(pk, dto);
				dspCtgry2dResult.setDspCtgryResult(dspCtgryInfo);
			}

			/** 전시 카테고리 하부 목록 조회 */
//		if(dspCtgryInfo.getDspCtgry().getLeafCtgryYn().equals("N")){

			// 하부 전시카테고리
			if(!dto.getHasSubCtgry().equals("N")){
				List<DspCtgryFoResult> dspCtgryResultList = displayCategorysService.selectSubDisplayCategoryList(pk, dto);
				/** 전시카테고리 FILTER (SPECIAL) 적용 하부 목록 */
				String ctgryType = dto.getDspCtgryOutputType();
				if(ctgryType == null)
					ctgryType = "A_TP_LAG_MID_SML";
				String dspCtgrySpecial = dto.getSpecial();
				if(dspCtgrySpecial != null){
					if(dspCtgrySpecial.equals("OUTLET") || dspCtgrySpecial.equals("BRAND") || dspCtgrySpecial.equals("FILTER")){
						dspCtgryResultList = displayCategorysService.selectSubDisplayCategorySpecialList(dspCtgryResultList, pk,dto, ctgryType);
					}
				}
				dspCtgry2dResult.setDspCtgryResultList(dspCtgryResultList);
			}

			// 로케이션 바 정보
			if(!dto.getHasLocation().equals("N")){
				/** 전시카테고리 로케이션 바 조회 */
				List<DspCtgryFoResult> locations = new ArrayList<DspCtgryFoResult>();
				List<DspCtgryFoResult> dspCtgryResultList = dspCtgry2dResult.getDspCtgryResultList();
				if(dspCtgryResultList != null && dspCtgryResultList.size() > 0 ){
					String[] upperCtgryNos = dto.getUpperCategoryNos();
					if(upperCtgryNos != null){
						for(String ctgryNo:upperCtgryNos){
							for(DspCtgryFoResult dspCtgryResult:dspCtgryResultList){
								String dcNo = dspCtgryResult.getDspCtgry().getDspCtgryNo();
								if(dcNo.equals(ctgryNo)){
									locations.add(dspCtgryResult);
									break;
								}
							}
						}
					}
				}
				else{
					locations = displayCategorysService.selectLocationDsplatyCategoryList(pk, dto);
				}
//				List<DspCtgryFoResult> locations = displayCategorysService.selectLocationDsplatyCategoryList(pk, dto);
				dspCtgry2dResult.setDspCtgryLocationList(locations);
			}
//		}
			// 전시 카테고리 연결 상품목록
			String hasGodList = dto.getHasGodList();
			// 대카테고리 메인이 아닌 경우에 수행한다.
			if(!hasGodList.equals("N")){

				// 상품목록 데이터베이스 정렬값을 적용한다.
				if(dto.getSortColumn() != null && dto.getSortColumn().length() > 0){

				}else{
					dto.setSortColumn("NEW_GOD");
				}

				int totCnt = displayCategorysService.selectDisplayCategoryConnGoodTotCount(pk, dto);
				/** 전시 상품 리스트 */
				List<DspCtgryGodFoResult> godList = Lists.newArrayList();
				if ("PC".equalsIgnoreCase(pk.getDevice())) {
					godList = displayCategorysService.selectDisplayCategoryConnGoodList(pk, dto);
				} else {
					godList = displayCategorysService.selectDisplayCategoryConnGoodMobileAsList(pk, dto);
				}

				dspCtgry2dResult.setDspCtgryGodTotCount(totCnt);
				dspCtgry2dResult.setDspCtgryGodList(godList);
			}
		}catch(Exception e){
			log.error(e.getMessage(), e);
		}
		return dspCtgry2dResult;
	}

	/**
	 * V2 Location
	 *
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public List v2DspLocationSet(String tp1, String tp2) throws Exception {

		List<HashMap> locationSet = new ArrayList();
		HashMap<String, String> hm = new HashMap<>();
		hm.put("tp",tp1);
		hm.put("url",getLocationUrl(tp1));
		locationSet.add(hm);

		if(null != tp2 && !"".equals(tp2)){
			HashMap<String, String> hm1 = new HashMap<>();
			hm1.put("tp",tp2);
			hm1.put("url","#");
			locationSet.add(hm1);

		}

		return locationSet;

	}

	//로케이션 호출 URL 셋팅
	private String getLocationUrl(String tp){

		String url = null;

		if(tp.equals(DisplayEnum.LOCATION_TP.HELP_DESK.toString())){
			url = "/public/helpdesk/customerSupport";
		}else if(tp.equals(DisplayEnum.LOCATION_TP.MY_PAGE.toString())){
			url = "/secured/mypage/myPage";
		}else{
			url = "#";
		}

		return url;

	}

	/**
	 * V2 SiteMap
	 *
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public V2DspFoCtgryResult selectDspSisteMapView(SystemPK pk) throws Exception {

		//조회용 DTO
		DspCtgryScFrDTO dspCtgryFrDTO = new DspCtgryScFrDTO();
		//Result DTO
		V2DspFoCtgryResult v2DspFoCtgryResult = new V2DspFoCtgryResult();


		//회원 구분 코드조회
		dspCtgryFrDTO.setPrcSectCd(goodsPriceFOComponent.getMemberTypeForPriceForPC().getPrcSectCd());

		//조회 데이터 셋팅
		dspCtgryFrDTO.setMallId(pk.getMall());
		dspCtgryFrDTO.setLang(pk.getLang());
		dspCtgryFrDTO.setDevice(pk.getDevice());
		dspCtgryFrDTO.setDspCtgryNo("DXM");

		//전체 브랜드 리스트를 구한다
		v2DspFoCtgryResult.setBrndList(displayCategorysService.selectV2DspCtgryBrandAllList(dspCtgryFrDTO));

		//카테고리 트리 리스트를 구한다.
		v2DspFoCtgryResult.setCtgryList(displayCategorysService.selectV2DspCtgryList(dspCtgryFrDTO));


		return v2DspFoCtgryResult;

	}

	/**
	 * V2 상품 상세 location 조회
	 *
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public V2DspFoCtgryResult selectDspGodCtgryView(SystemPK pk, String dspCtgryNo, String brandShopNo, String brndShopId, String etcCtgryNo) throws Exception {


		//Result DTO
		V2DspFoCtgryResult v2DspFoCtgryResult = selectDspCtgryView(pk,dspCtgryNo,brandShopNo,brndShopId,etcCtgryNo);
		v2DspFoCtgryResult.setGodDetailYN("Y");

		return v2DspFoCtgryResult;
	}

	/**
	 * V2 LNB 데이터 조회
	 *
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public V2DspFoCtgryResult selectDspCtgryView(SystemPK pk, String dspCtgryNo, String brandShopNo, String brndShopId, String etcCtgryNo) throws Exception {

		//조회용 DTO
		DspCtgryScFrDTO dspCtgryFrDTO = new DspCtgryScFrDTO();
		//Result DTO
		V2DspFoCtgryResult v2DspFoCtgryResult = new V2DspFoCtgryResult();

		//LNB 배너 코너조회용
		String dspCtgryNoForType = dspCtgryNo;

		//회원 구분 코드조회
		dspCtgryFrDTO.setPrcSectCd(goodsPriceFOComponent.getMemberTypeForPriceForPC().getPrcSectCd());

		//조회 데이터 셋팅
		dspCtgryFrDTO.setMallId(pk.getMall());
		dspCtgryFrDTO.setLang(pk.getLang());
		dspCtgryFrDTO.setDevice(pk.getDevice());
		dspCtgryFrDTO.setBrandShopNo(brandShopNo);

		//Result 데이터 셋팅
		v2DspFoCtgryResult.setDspCtgryNo(dspCtgryNo);
		v2DspFoCtgryResult.setBrandShopNo(brandShopNo);
		v2DspFoCtgryResult.setEtcCtgryNo(etcCtgryNo);
		v2DspFoCtgryResult.setBrndShopId(brndShopId);


		//카테고리 관리 페이지 외 LNB 및 Location 처리
		DisplayEnum.LOCATION_TP[] a = DisplayEnum.LOCATION_TP.values();
		if((null != dspCtgryNo && !dspCtgryNo.equals("")) || (null != brandShopNo && !brandShopNo.equals(""))){
			for (DisplayEnum.LOCATION_TP tp : a){
				if(null != dspCtgryNo && !dspCtgryNo.equals("") && dspCtgryNo.equals(tp.toString())){
					dspCtgryNo = null;
					v2DspFoCtgryResult.setLocationTp(tp.toString());
					break;
				}
				if(null != brandShopNo && !brandShopNo.equals("") && brandShopNo.equals(tp.toString())){
					brandShopNo = null;
					v2DspFoCtgryResult.setLocationTp(tp.toString());
					break;
				}
			}
		}

		String pageChack = null;
		DspCtgry ctgryInfo = null;
		String ctgryNo = "DXM";

		List<DspCtgry> locationSet = new ArrayList<DspCtgry>();
		List<DspCtgry> locationSet1 = null;

		if(null != brndShopId && !brndShopId.equals("")){
			/*============================= 브랜드몰 page =============================*/
			pageChack = "BRAND";

		}else if(null == dspCtgryNo || dspCtgryNo.length() < 6){
			/*============================= MAIN page =============================*/
			pageChack = "MAIN";

		}else{
			/*============================= 통합몰 page =============================*/
			pageChack = "PLGRIM";
			ctgryNo = dspCtgryNo;
			//카테고리 정보 조회
			ctgryInfo = displayCategorysService.selectCtgryInfo(ctgryNo);

			//메인에서 New In, Top Sellers, On Sale로 접근시 메인화면에서 처리한다.
			if(null != ctgryInfo && ctgryNo.indexOf(DisplayEnum.WOMEN.toString()) < 0 && ctgryNo.indexOf(DisplayEnum.MEN.toString()) < 0 && ctgryNo.indexOf(DisplayEnum.KIDS.toString()) < 0
					&& (ctgryInfo.getCtgrySectCd().equals("NEW_GOD_CTGRY") || ctgryInfo.getCtgrySectCd().equals("BST_CTGRY") || ctgryInfo.getCtgrySectCd().equals("ON_SALE_CTGRY"))){
				/*============================= MAIN page =============================*/
				pageChack = "MAIN";
				dspCtgryFrDTO.setDspCtgryNo(dspCtgryNo);
				locationSet1 = displayCategorysService.selectV2DspLocationList(dspCtgryFrDTO);
				locationSet.addAll(locationSet1);
			}
			//전략 카테고리
		}



		if(pageChack.equals("BRAND")){
			v2DspFoCtgryResult.setMallGubun("BRAND");
			dspCtgryFrDTO.setMallGubun("BRAND");
			/*============================= 브랜드몰 데이터 조회 =============================*/


			//전체 브랜드 리스트를 구한다
			dspCtgryFrDTO.setDspCtgryNo(ctgryNo);
			v2DspFoCtgryResult.setBrndList(displayCategorysService.selectV2DspCtgryBrandAllList(dspCtgryFrDTO));

			dspCtgryFrDTO.setBrndId(brndShopId);

			if(null != dspCtgryNo && dspCtgryNo.length() > 5){
				ctgryNo = dspCtgryNo;
			}
			dspCtgryFrDTO.setDspCtgryNo(ctgryNo);

			//카테고리 정보 조회
			ctgryInfo = displayCategorysService.selectCtgryInfo(ctgryNo);

			//비이커 대카테고리 하위에서는 비이커 하위브랜드를 필터로 활용한다.
			if(brndShopId.equals("MCBR") && (null != dspCtgryNo && dspCtgryNo.length() > 5)){
				// Location set
				dspCtgryFrDTO.setBrandShopNo(null);
				dspCtgryFrDTO.setDspCtgryNo(dspCtgryNo);
				locationSet1 = displayCategorysService.selectV2DspLocationList(dspCtgryFrDTO);

				//대카테고리 CHACK
				v2DspFoCtgryResult.setCtgyChack(displayCategorysService.selectCategoryChack(dspCtgryFrDTO));

				dspCtgryFrDTO.setBrndId(displayCategorysService.selectBrandInfo(brandShopNo));

				//New In, Top Sellers, On Sale 카테고리 처리한다.
				if(null != ctgryInfo && ctgryNo.indexOf(DisplayEnum.WOMEN.toString()) < 0 && ctgryNo.indexOf(DisplayEnum.MEN.toString()) < 0 && ctgryNo.indexOf(DisplayEnum.KIDS.toString()) < 0 && (ctgryInfo.getCtgrySectCd().equals("NEW_GOD_CTGRY") || ctgryInfo.getCtgrySectCd().equals("BST_CTGRY") || ctgryInfo.getCtgrySectCd().equals("ON_SALE_CTGRY"))){
					dspCtgryFrDTO.setDspCtgryNo("DXM");
				}

				//카테고리 트리 리스트를 구한다.
				v2DspFoCtgryResult.setCtgryList(displayCategorysService.selectV2DspCtgryList(dspCtgryFrDTO));
			}else{
				// Location set
				dspCtgryFrDTO.setDspCtgryNo(ctgryNo);
				locationSet1 = displayCategorysService.selectV2DspLocationList(dspCtgryFrDTO);

				//대카테고리 CHACK
				v2DspFoCtgryResult.setCtgyChack(displayCategorysService.selectCategoryChack(dspCtgryFrDTO));

				//New In, Top Sellers, On Sale 카테고리 처리한다.
				if(null != ctgryInfo && ctgryNo.indexOf(DisplayEnum.WOMEN.toString()) < 0 && ctgryNo.indexOf(DisplayEnum.MEN.toString()) < 0 && ctgryNo.indexOf(DisplayEnum.KIDS.toString()) < 0 && (ctgryInfo.getCtgrySectCd().equals("NEW_GOD_CTGRY") || ctgryInfo.getCtgrySectCd().equals("BST_CTGRY") || ctgryInfo.getCtgrySectCd().equals("ON_SALE_CTGRY"))){
					dspCtgryFrDTO.setDspCtgryNo("DXM");
				}
				//카테고리 트리 리스트를 구한다.
				v2DspFoCtgryResult.setCtgryList(displayCategorysService.selectV2DspCtgryList(dspCtgryFrDTO));
			}

			locationSet.addAll(locationSet1);

			//Outlet 카테고리 리스트
			dspCtgryFrDTO.setDspCtgryNo("DXM");
			dspCtgryFrDTO.setCtgrySectCd("OTLT_CTGRY");
			v2DspFoCtgryResult.setMbOutletCtgryList(displayCategorysService.selectV2MBDspCtgryList(dspCtgryFrDTO));

			//전략 카테고리 리스트
			dspCtgryFrDTO.setCtgrySectCd("STRTGY_CTGRY");
			v2DspFoCtgryResult.setStrtgyCtgryList(displayCategorysService.selectV2DspCtgryList(dspCtgryFrDTO));

			//전략 카테고리 및 아울렛 카테고리 처리
			if(null != etcCtgryNo && !"".equals(etcCtgryNo)) {
				ctgryNo = etcCtgryNo;

				//카테고리 정보 조회
				ctgryInfo = displayCategorysService.selectCtgryInfo(ctgryNo);


				//전략 카테고리 리스트
							/*dspCtgryFrDTO.setCtgrySectCd("STRTGY_CTGRY");
							v2DspFoCtgryResult.setStrtgyCtgryList(displayCategorysService.selectV2DspCtgryList(dspCtgryFrDTO));*/

				dspCtgryFrDTO.setDspCtgryNo(ctgryNo);
				dspCtgryFrDTO.setBrndId(null);
				dspCtgryFrDTO.setCtgrySectCd(null);

				// Location set
				if(null != dspCtgryNo && dspCtgryNo.length() > 5) {
					dspCtgryFrDTO.setCtgrySectCd(ctgryInfo.getCtgrySectCd());

				}

				locationSet.addAll(displayCategorysService.selectV2DspLocationList(dspCtgryFrDTO));
			}/*else{
							if(null != dspCtgryNo && dspCtgryNo.length() > 5){
							//전략 카테고리 리스트
							dspCtgryFrDTO.setCtgrySectCd("STRTGY_CTGRY");
							v2DspFoCtgryResult.setStrtgyCtgryList(displayCategorysService.selectV2DspCtgryList(dspCtgryFrDTO));


							}
						}*/

			//카테고리 정보 조회
			v2DspFoCtgryResult.setCtgryInfo(ctgryInfo);

			// Location set
			v2DspFoCtgryResult.setLocationList(locationSet);

			/*============================= 브랜드몰 데이터 조회 END =============================*/

		}else if(pageChack.equals("MAIN")){
			v2DspFoCtgryResult.setMallGubun("MAIN");
			/*============================= MAIN 데이터 조회 =============================*/

			ctgryNo = "DXM";
			dspCtgryFrDTO.setDspCtgryNo(ctgryNo);

			//전체 브랜드 리스트를 구한다
			v2DspFoCtgryResult.setBrndList(displayCategorysService.selectV2DspCtgryBrandAllList(dspCtgryFrDTO));

			//카테고리 트리 리스트를 구한다.
			//v2DspFoCtgryResult.setCtgryList(displayCategorysService.selectV2DspCtgryList(dspCtgryFrDTO));

			//전략 카테고리 리스트
			dspCtgryFrDTO.setCtgrySectCd("STRTGY_CTGRY");
			v2DspFoCtgryResult.setStrtgyCtgryList(displayCategorysService.selectV2DspCtgryList(dspCtgryFrDTO));

			//Outlet 카테고리 리스트
			dspCtgryFrDTO.setCtgrySectCd("OTLT_CTGRY");
			v2DspFoCtgryResult.setMbOutletCtgryList(displayCategorysService.selectV2MBDspCtgryList(dspCtgryFrDTO));

			//New In, Top Sellers, On Sale 카테고리 리스트
			dspCtgryFrDTO.setCtgrySectCd("ETC_CTGRY");
			v2DspFoCtgryResult.setEtcCtgryList(displayCategorysService.selectV2MBDspCtgryList(dspCtgryFrDTO));

			//전략 카테고리 및 아울렛 카테고리 처리
			if(null != etcCtgryNo && !"".equals(etcCtgryNo)){
				ctgryNo = etcCtgryNo;

				//카테고리 정보 조회
				ctgryInfo = displayCategorysService.selectCtgryInfo(ctgryNo);

				dspCtgryFrDTO.setDspCtgryNo(ctgryNo);

				// Location set
				locationSet1 = displayCategorysService.selectV2DspLocationList(dspCtgryFrDTO);
				locationSet.addAll(locationSet1);
			}

			// Location set
			v2DspFoCtgryResult.setLocationList(locationSet);

			//카테고리 정보 조회
			v2DspFoCtgryResult.setCtgryInfo(ctgryInfo);

			/*============================= MAIN 데이터 조회 END =============================*/

		}else{
			v2DspFoCtgryResult.setMallGubun("PLGRIM");
			dspCtgryFrDTO.setMallGubun("PLGRIM");
			/*============================= 통합몰 데이터 조회 =============================*/

			dspCtgryFrDTO.setDspCtgryNo(ctgryNo);

			// Location set
			locationSet1 = displayCategorysService.selectV2DspLocationList(dspCtgryFrDTO);
			locationSet.addAll(locationSet1);

			if(null != brandShopNo && !"".equals(brandShopNo)){
				//브랜드 ID를 구한다.
				String brndId = displayCategorysService.selectBrandInfo(brandShopNo);
				dspCtgryFrDTO.setBrndId(brndId);
			}

			//카테고리 트리 리스트를 구한다.
			v2DspFoCtgryResult.setCtgryList(displayCategorysService.selectV2DspCtgryList(dspCtgryFrDTO));

			//New In, Top Sellers, On Sale 조회
			v2DspFoCtgryResult.setNewTopOnCtgryList(displayCategorysService.selectNewTopOnCtgryList(dspCtgryFrDTO));


			//브랜드 리스트를 구한다
			if(null != ctgryInfo && (ctgryInfo.getCtgrySectCd().equals("NEW_GOD_CTGRY") || ctgryInfo.getCtgrySectCd().equals("BST_CTGRY") || ctgryInfo.getCtgrySectCd().equals("ON_SALE_CTGRY"))){
				if(ctgryNo.indexOf(DisplayEnum.WOMEN.toString()) < 0 && ctgryNo.indexOf(DisplayEnum.MEN.toString()) < 0 && ctgryNo.indexOf(DisplayEnum.KIDS.toString()) < 0){
					dspCtgryFrDTO.setDspCtgryNo("DXM");
					dspCtgryFrDTO.setMallGubun(null);
				}else{
					dspCtgryFrDTO.setDspCtgryNo(dspCtgryNo.substring(0,6));
				}
			}
			v2DspFoCtgryResult.setBrndList(displayCategorysService.selectV2DspCtgryBrandAllList(dspCtgryFrDTO));

			//Outlet 카테고리 리스트
			dspCtgryFrDTO.setDspCtgryNo("DXM");
			dspCtgryFrDTO.setBrndId(null);
			dspCtgryFrDTO.setCtgrySectCd("OTLT_CTGRY");
			v2DspFoCtgryResult.setMbOutletCtgryList(displayCategorysService.selectV2MBDspCtgryList(dspCtgryFrDTO));

			//전략 카테고리 리스트
			dspCtgryFrDTO.setCtgrySectCd("STRTGY_CTGRY");
			v2DspFoCtgryResult.setStrtgyCtgryList(displayCategorysService.selectV2DspCtgryList(dspCtgryFrDTO));

			//전략 카테고리 및 아울렛 카테고리 처리
			if(null != etcCtgryNo && !"".equals(etcCtgryNo)) {
				ctgryNo = etcCtgryNo;

				//카테고리 정보 조회
				ctgryInfo = displayCategorysService.selectCtgryInfo(ctgryNo);

				//전략 카테고리 리스트
							/*dspCtgryFrDTO.setCtgrySectCd("STRTGY_CTGRY");
							v2DspFoCtgryResult.setStrtgyCtgryList(displayCategorysService.selectV2DspCtgryList(dspCtgryFrDTO));*/

							/*//Outlet 카테고리 리스트
							dspCtgryFrDTO.setCtgrySectCd("OTLT_CTGRY");
							v2DspFoCtgryResult.setOutletCtgryList(displayCategorysService.selectV2DspCtgryList(dspCtgryFrDTO));*/

				dspCtgryFrDTO.setDspCtgryNo(ctgryNo);

				// Location set
				dspCtgryFrDTO.setCtgrySectCd(ctgryInfo.getCtgrySectCd());
				locationSet.addAll(displayCategorysService.selectV2DspLocationList(dspCtgryFrDTO));
			}

			//카테고리 정보 조회
			v2DspFoCtgryResult.setCtgryInfo(ctgryInfo);

			// Location set
			v2DspFoCtgryResult.setLocationList(locationSet);

			/*============================= 통합몰 데이터 조회 END =============================*/
		}

		//LNB 배너 코너컨텐츠 조회
		if(!pageChack.equals("BRAND")){
			if((pageChack.equals("MAIN") && StringService.isEmpty(dspCtgryNoForType))
					|| (StringService.isNotEmpty(dspCtgryNoForType) && dspCtgryNoForType.startsWith("DXM") && dspCtgryNoForType.length() >= 6)) {
				String lnbThmaNo = getConfigService().getProperty("ncp_base.display.lnb.banner.thma.no");

				GoodsPriceSearchDTO goodsPriceSearchFoDTO = goodsPriceFOComponent.getMemberTypeForPriceForPC();
				DspCnrScFrDTO dto = new DspCnrScFrDTO();
				dto.setDevice(DisplayEnum.DVC.PC.toString());
				this.setDTOInfoForCnr(dto, pk, goodsPriceSearchFoDTO);
				this.setTmplatInfo(dto, DisplayEnum.TMPLAT_TP.THEMA_PGE.toString(), lnbThmaNo);
				List<DspCnrFrResult> cnrList = this.selectDspCnrListV2(dto);
				v2DspFoCtgryResult.setLnbCnr(cnrList);
			}
		}

		return v2DspFoCtgryResult;

	}

	/**
	 * V2 LNB 비이커 하위 브랜드 데이터 조회
	 *
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<DspCtgry> selectBeakerBrandList(SystemPK pk, String dspCtgryNo, String brandShopNo, String indexKey) throws Exception {

		DspCtgryScFrDTO dspCtgryFrDTO = new DspCtgryScFrDTO();

		//회원 구분 코드조회
		dspCtgryFrDTO.setPrcSectCd(goodsPriceFOComponent.getMemberTypeForPriceForPC().getPrcSectCd());

		//브랜드 ID를 구한다.
		String brndId = displayCategorysService.selectBrandInfo(brandShopNo);

		dspCtgryFrDTO.setMallId(pk.getMall());
		dspCtgryFrDTO.setLang(pk.getLang());
		dspCtgryFrDTO.setDevice(pk.getDevice());
		dspCtgryFrDTO.setBrandShopNo(brandShopNo);
		dspCtgryFrDTO.setBrndId(brndId);
		dspCtgryFrDTO.setIndexKey(indexKey);
		dspCtgryFrDTO.setDspCtgryNo(dspCtgryNo);

		return displayCategorysService.selectBeakerBrandList(dspCtgryFrDTO);
	}

	/**
	 * V2 모발일 GNB 조회
	 *
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public V2DspFoCtgryResult selectV2MBDspCtgryView(SystemPK pk, String dspCtgryNo, String brandShopNo, String brndShopId) throws Exception {

		DspCtgryScFrDTO dspCtgryFrDTO = new DspCtgryScFrDTO();
		V2DspFoCtgryResult v2DspFoCtgryResult = new V2DspFoCtgryResult();


		//회원 구분 코드조회
		dspCtgryFrDTO.setPrcSectCd(goodsPriceFOComponent.getMemberTypeForPriceForPC().getPrcSectCd());

		dspCtgryFrDTO.setMallId(pk.getMall());
		dspCtgryFrDTO.setLang(pk.getLang());
		dspCtgryFrDTO.setDevice(pk.getDevice());
		dspCtgryFrDTO.setBrandShopNo(brandShopNo);

		v2DspFoCtgryResult.setBrandShopNo(brandShopNo);
		v2DspFoCtgryResult.setBrndShopId(brndShopId);

		//카테고리 정보 조회
		DspCtgry ctgryInfo = displayCategorysService.selectCtgryInfo(dspCtgryNo);
		v2DspFoCtgryResult.setCtgryInfo(ctgryInfo);

		String brndId = null;
		String ctgryNo = "";
		if(null != brndShopId && !"".equals(brndShopId)){
			dspCtgryFrDTO.setMallGubun("BRAND");
			v2DspFoCtgryResult.setMallGubun("BRAND");
			brndId = brndShopId;

			if(StringService.isNotEmpty(brandShopNo)) {
				DspCtgry brndInfo = displayCategorysService.selectCtgryInfo(brandShopNo);
				if(brndInfo != null) {
					v2DspFoCtgryResult.setBrandShopNm(brndInfo.getDspCtgryNm());
				}
				else {
					v2DspFoCtgryResult.setBrandShopNm("");
				}
			}
		}else{
			brndId = displayCategorysService.selectBrandInfo(brandShopNo);
		}

		if(null != dspCtgryNo && !dspCtgryNo.equals("") && !dspCtgryNo.equals("MAIN")){
			ctgryNo = dspCtgryNo;
		}

		dspCtgryFrDTO.setBrndId(brndId);
		dspCtgryFrDTO.setDspCtgryNo(ctgryNo);
		v2DspFoCtgryResult.setDspCtgryNo(ctgryNo);

		// Location set
		if(ctgryNo.length() > 3){
			v2DspFoCtgryResult.setLocationList(displayCategorysService.selectV2DspMBLocationList(dspCtgryFrDTO));
		}

		//대카테고리 CHACK
		v2DspFoCtgryResult.setCtgyChack(displayCategorysService.selectCategoryChack(dspCtgryFrDTO));

		/*if(null != ctgryInfo && (ctgryInfo.getCtgrySectCd().equals("NEW_GOD_CTGRY") || ctgryInfo.getCtgrySectCd().equals("BST_CTGRY")  || ctgryInfo.getCtgrySectCd().equals("ON_SALE_CTGRY") )){
			dspCtgryFrDTO.setDspCtgryNo(ctgryNo.substring(0,6));
		}*/

		//카테고리 리스트를 조회한다.
		v2DspFoCtgryResult.setMbCtgryList(displayCategorysService.selectV2MBDspCtgryList(dspCtgryFrDTO));

		//전략 카테고리 리스트
		dspCtgryFrDTO.setCtgrySectCd("STRTGY_CTGRY");
		v2DspFoCtgryResult.setMbStrtgyCtgryList(displayCategorysService.selectV2MBDspCtgryList(dspCtgryFrDTO));

		//Outlet 카테고리 리스트
		dspCtgryFrDTO.setCtgrySectCd("OTLT_CTGRY");
		v2DspFoCtgryResult.setMbOutletCtgryList(displayCategorysService.selectV2MBDspCtgryList(dspCtgryFrDTO));

		//New In, Top Sellers, On Sale 카테고리 리스트
		dspCtgryFrDTO.setCtgrySectCd("ETC_CTGRY");
		v2DspFoCtgryResult.setEtcCtgryList(displayCategorysService.selectV2MBDspCtgryList(dspCtgryFrDTO));

		//브랜드 리스트를 구한다
		dspCtgryFrDTO.setDspCtgryNo("DXM");
		v2DspFoCtgryResult.setBrndList(displayCategorysService.selectV2DspCtgryBrandAllList(dspCtgryFrDTO));

		//LNB 배너 코너컨텐츠 조회
		if(StringService.isEmpty(brndShopId)){
			if((StringService.isNotEmpty(dspCtgryNo) && "MAIN".equals(dspCtgryNo))
					|| (StringService.isNotEmpty(dspCtgryNo) && dspCtgryNo.startsWith("DXM") && dspCtgryNo.length() >= 6)) {
				String lnbThmaNo = getConfigService().getProperty("ncp_base.display.lnb.banner.thma.no");

				GoodsPriceSearchDTO goodsPriceSearchFoDTO = goodsPriceFOComponent.getMemberTypeForPriceForPC();
				DspCnrScFrDTO dto = new DspCnrScFrDTO();
				dto.setDevice(DisplayEnum.DVC.MOBILE_WEB.toString());
				this.setDTOInfoForCnr(dto, pk, goodsPriceSearchFoDTO);
				this.setTmplatInfo(dto, DisplayEnum.TMPLAT_TP.THEMA_PGE.toString(), lnbThmaNo);
				List<DspCnrFrResult> cnrList = this.selectDspCnrListV2(dto);
				v2DspFoCtgryResult.setLnbCnr(cnrList);
			}
		}

		return v2DspFoCtgryResult;
	}

	/**
	 * V2 모발일 GNB 상품상세
	 *
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public V2DspFoCtgryResult selectV2MBDspGodCtgryView(SystemPK pk, String dspCtgryNo, String brandShopNo, String brndShopId) throws Exception {

		V2DspFoCtgryResult v2DspFoCtgryResult = selectV2MBDspCtgryView(pk,dspCtgryNo,brandShopNo,brndShopId);
		v2DspFoCtgryResult.setLocationList(null);

		return v2DspFoCtgryResult;

	}

	/**
	 * 모바일 V2 하위 카테고리 조회
	 *
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public V2DspFoCtgryResult selectDetailCategoryList(SystemPK pk, String dspCtgryNo, String brandShopNo, String brndShopId) throws Exception {

		DspCtgryScFrDTO dspCtgryFrDTO = new DspCtgryScFrDTO();

		V2DspFoCtgryResult v2DspFoCtgryResult = new V2DspFoCtgryResult();

		v2DspFoCtgryResult.setDspCtgryNo(dspCtgryNo);
		v2DspFoCtgryResult.setBrandShopNo(brandShopNo);
		v2DspFoCtgryResult.setBrndShopId(brndShopId);

		//카테고리 정보 조회
		DspCtgry ctgryInfo = displayCategorysService.selectCtgryInfo(dspCtgryNo);
		v2DspFoCtgryResult.setCtgryInfo(ctgryInfo);

		if(null != brndShopId && !"".equals(brndShopId)) {
			v2DspFoCtgryResult.setMallGubun("BRAND");
		}

		dspCtgryFrDTO.setMallId(pk.getMall());
		dspCtgryFrDTO.setLang(pk.getLang());
		dspCtgryFrDTO.setDspCtgryNo(dspCtgryNo);
		dspCtgryFrDTO.setBrandShopNo(brandShopNo);
		dspCtgryFrDTO.setBrndId(brndShopId);

		//회원 구분 코드조회
		dspCtgryFrDTO.setPrcSectCd(goodsPriceFOComponent.getMemberTypeForPriceForPC().getPrcSectCd());

		v2DspFoCtgryResult.setCtgryList(displayCategorysService.selectV2DspCtgryList(dspCtgryFrDTO));

		/*if(dspCtgryNo.length() == 6){
			//전략 카테고리 리스트
			dspCtgryFrDTO.setCtgrySectCd("STRTGY_CTGRY");
			v2DspFoCtgryResult.setStrtgyCtgryList(displayCategorysService.selectV2DspCtgryList(dspCtgryFrDTO));
		}*/

		return v2DspFoCtgryResult;
	}

	@Override
	public DspCtgryResultFoDTO selectDspCtgry1dNewView(SystemPK pk,
													   DspCtgrySearchFoDTO dto) throws Exception {

		// 임직원 검사
		String empYn = "N";
		Mbr mbr = null;
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();

			if(mbrAtrb.equals("plgrim") ||
					mbrAtrb.equals("PLGRIM_FSHN") ||
					mbrAtrb.equals("GRPCO")){
				empYn = "Y";
			}
		}
		dto.setEmpYn(empYn);

		DspCtgryResultFoDTO dspCtgryResult = new DspCtgryResultFoDTO();
		try{
			/** 전시카테고리 기본정보 조회 */
			DspCtgryFoResult dspCtgryInfo = displayCategorysService.selectDisplayCategoryInfo(pk, dto);
			/** 전시 카테고리 하부 목록 조회 */
			List<DspCtgryFoResult> dspCtgryResultList = displayCategorysService.selectSubDisplayCategoryList(pk, dto);
			/** 전시카테고리 NEW 하부 목록 */
			List<DspCtgryFoResult> dspCtgryNewResultList = displayCategorysService.selectSubDisplayCategoryNewList(dspCtgryResultList, pk,dto);
			/** 전시카테고리 로케이션 바 조회 */
			List<DspCtgryFoResult> locations = displayCategorysService.selectLocationDsplatyCategoryList(pk, dto);

			// 상품목록 데이터베이스 정렬값을 적용한다.
			if(dto.getSortColumn() == null || dto.getSortColumn().length() == 0){
				dto.setSortColumn(dspCtgryInfo.getDspCtgry().getDspGodSortStdrCd());
			}

			int totCnt = displayCategorysService.selectDisplayCategoryConnGoodTotCount(pk, dto);
			/** 전시 상품 리스트 */
			List<DspCtgryGodFoResult> godList = Lists.newArrayList();
			if ("PC".equalsIgnoreCase(pk.getDevice())) {
				godList = displayCategorysService.selectDisplayCategoryConnGoodList(pk, dto);
			}
			else {
				godList = displayCategorysService.selectDisplayCategoryConnGoodMobileAsList(pk, dto);
			}

//	    int totCnt = dto.getTotGodCnt();

			dspCtgryResult.setDspCtgryResult(dspCtgryInfo);
			dspCtgryResult.setDspCtgryLocationList(locations);
			dspCtgryResult.setDspCtgryResultList(dspCtgryResultList);
			dspCtgryResult.setDspCtgryResultNewList(dspCtgryNewResultList);
			dspCtgryResult.setDspCtgryGodTotCount(totCnt);
			dspCtgryResult.setDspCtgryGodList(godList);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		return dspCtgryResult;
	}

	@Override
	public DspCtgryResultFoDTO selectDspBrandNewView(SystemPK pk,
													 DspCtgrySearchFoDTO dto) throws Exception {

		// 임직원 검사
		String empYn = "N";
		Mbr mbr = null;
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();

			if(mbrAtrb.equals("plgrim") ||
					mbrAtrb.equals("PLGRIM_FSHN") ||
					mbrAtrb.equals("GRPCO")){
				empYn = "Y";
			}
		}
		dto.setEmpYn(empYn);

		DspCtgryResultFoDTO dspCtgryResult = new DspCtgryResultFoDTO();
		try{
			/** 전시카테고리 기본정보 조회 */
			DspCtgryFoResult dspCtgryInfo = displayCategorysService.selectDisplayCategoryInfo(pk, dto);
			/** 전시 카테고리 하부 목록 조회 */
			List<DspCtgryFoResult> dspCtgryResultList = displayCategorysService.selectSubDisplayCategoryList(pk, dto);
			/** 전시카테고리 BRAND 하부 목록 */
			String dspCtgrySpecial = dto.getSpecial();
			if(dspCtgrySpecial != null && dspCtgrySpecial.equals("BRAND")){
				String ctgryType = "A";
				dspCtgryResultList = displayCategorysService.selectSubDisplayCategorySpecialList(dspCtgryResultList, pk,dto,ctgryType);
			}
			/** 전시카테고리 NEW 하부 목록 */
			List<DspCtgryFoResult> dspCtgryNewResultList = displayCategorysService.selectSubDisplayCategorySpecialNewList(dspCtgryResultList, pk,dto);
			/** 전시카테고리 로케이션 바 조회 */
			List<DspCtgryFoResult> locations = displayCategorysService.selectLocationDsplatyCategoryList(pk, dto);

			// 상품목록 데이터베이스 정렬값을 적용한다.
			if(dto.getSortColumn() == null || dto.getSortColumn().length() == 0){
				dto.setSortColumn(dspCtgryInfo.getDspCtgry().getDspGodSortStdrCd());
			}


			int totCnt = displayCategorysService.selectDisplayCategoryConnGoodTotCount(pk, dto);

			/** 전시 상품 리스트 */
			List<DspCtgryGodFoResult> godList = Lists.newArrayList();
			if ("PC".equalsIgnoreCase(pk.getDevice())) {
				godList = displayCategorysService.selectDisplayCategoryConnGoodList(pk, dto);
			} else {
				godList = displayCategorysService.selectDisplayCategoryConnGoodMobileAsList(pk, dto);
			}

//	    int totCnt = dto.getTotGodCnt();

			dspCtgryResult.setDspCtgryResult(dspCtgryInfo);
			dspCtgryResult.setDspCtgryLocationList(locations);
			dspCtgryResult.setDspCtgryResultList(dspCtgryResultList);
			dspCtgryResult.setDspCtgryResultNewList(dspCtgryNewResultList);
			dspCtgryResult.setDspCtgryGodTotCount(totCnt);
			dspCtgryResult.setDspCtgryGodList(godList);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		return dspCtgryResult;
	}

	@Override
	public GnbResultFoDTO selectGnbView(SystemPK pk,
										DspCtgrySearchFoDTO dto) throws Exception {

		// 임직원 검사
		String empYn = "N";
		Mbr mbr = null;
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();

			if(mbrAtrb.equals("plgrim") ||
					mbrAtrb.equals("PLGRIM_FSHN") ||
					mbrAtrb.equals("GRPCO")){
				empYn = "Y";
			}
		}
		dto.setEmpYn(empYn);

		GnbResultFoDTO gnbDTO = new GnbResultFoDTO();
		dto.setSpecial("FILTER");
		List<DspCtgryFoResult> dspCtgryList = displayCategorysService.selectSubDisplayCategoryList(pk, dto);
		dto.setSpecial("OUTLET");
		List<DspCtgryFoResult> outletCtgryList = displayCategorysService.selectSubDisplayCategoryList(pk, dto);
		outletCtgryList = displayCategorysService.selectSubDisplayCategorySpecialList(outletCtgryList, pk,dto,"D1");
		dto.setSpecial("");
		gnbDTO.setDspCtgryList(dspCtgryList);
		gnbDTO.setOutLetCtgryList(outletCtgryList);

		return gnbDTO;
	}

	public List<DspCtgryGodFoResult> selectDisplayCategoryConnSpecialLimitGoodList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{
		// 임직원 검사
		String empYn = "N";
		Mbr mbr = null;
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();

			if(mbrAtrb.equals("plgrim") ||
					mbrAtrb.equals("PLGRIM_FSHN") ||
					mbrAtrb.equals("GRPCO")){
				empYn = "Y";
			}
		}
		searchDTO.setEmpYn(empYn);

		return displayCategorysService.selectDisplayCategoryConnSpecialLimitGoodList(pk, searchDTO);
	}

	public List<DspCtgryGodFoResult> selectNewBestGoodList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception{
		// 임직원 검사
		String empYn = "N";
		Mbr mbr = null;
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();

			if(mbrAtrb.equals("plgrim") ||
					mbrAtrb.equals("PLGRIM_FSHN") ||
					mbrAtrb.equals("GRPCO")){
				empYn = "Y";
			}
		}
		searchDTO.setEmpYn(empYn);
		return displayCategorysService.selectBestNewGodList(pk, searchDTO);
	}

	@Override
	public ThemaPageResultFoDTO selectThemaPageView(SystemPK pk,
													DspCtgrySearchFoDTO dto) throws Exception {
		ThemaPageResultFoDTO resultData = new ThemaPageResultFoDTO();
		// 임직원 검사
		String empYn = "N";
		Mbr mbr = null;
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();

			if(mbrAtrb.equals("plgrim") ||
					mbrAtrb.equals("PLGRIM_FSHN") ||
					mbrAtrb.equals("GRPCO")){
				empYn = "Y";
			}
		}else{
			mbr = new Mbr();
			mbr.setMbrTpCd("NMBR");
		}
		dto.setEmpYn(empYn);

		try{
			/** 메인몰 전시카테고리 기본정보 조회 */
			DspCtgryFoResult thema = displayCategorysService.selectDisplayCategoryInfo(pk, dto);
			List<DspCnrFoResult> cnrInfoList = new ArrayList<DspCnrFoResult>();
			resultData.setDspCtgryResult(thema);
			resultData.setDspCnrResultList(cnrInfoList);
		}catch(Exception e){
			log.error(e.getMessage(), e);
			throw e;
		}
		return resultData;
	}
	// 전시페이지의 전시코너정보
	@Override
	public DisplayPageResultDTO selectDisplayPageView(SystemPK pk, List<DspCnrFoResult> dspCnrResultList,
													  String serialNumber, Long tmplatSn, String  parentType, String prcSectCd, String spcPrmTp) throws Exception{
		DisplayPageResultDTO result = new DisplayPageResultDTO();
		// 해당페이지에서 사용하게될 전시코너목록을 받아서 설정한다.
		result.setDspCnrResultList(dspCnrResultList);

		// 임직원 검사
		String empYn = "N";
		Mbr mbr = null;
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();

			if(mbrAtrb.equals("plgrim") ||
					mbrAtrb.equals("PLGRIM_FSHN") ||
					mbrAtrb.equals("GRPCO")){
				empYn = "Y";
			}
		}else{
			mbr = new Mbr();
			mbr.setMbrTpCd("NMBR");
		}

		// 검색조건구성

		Map<String,Object> map = new HashMap<String,Object>();
		//parentType  ('THEMA_PGE', 'DSP_CTGRY','S_TRND', 'EVT','PROMT')
		map.put("parentType", parentType);
		if(parentType.equals("THEMA_PGE") || parentType.equals("DSP_CTGRY") || parentType.equals("EVT")){
			map.put("parentStringCd", serialNumber);
		}else{
			int cd = Integer.parseInt(serialNumber);
			map.put("parentIntCd", new Integer(cd));
		}
		map.put("tmplatSn", tmplatSn);
		map.put("lang", pk.getLang());
		map.put("device", pk.getDevice());
		map.put("aplMbrTp", mbr.getMbrTpCd());
		map.put("aplMbrAtrb", mbr.getMbrAtrbCd());
		map.put("grpcoId", mbr.getPsitnGrpcoId());
		map.put("prcSectCd", prcSectCd);
		map.put("spcPrmTp", spcPrmTp);
		map.put("empYn", empYn);
		map.put("mallId", pk.getMall());

		displayCornerService.selectDspCnrConttList(dspCnrResultList, map);

		return result;
	}

	@Override
	public DisplayPageResultDTO selectDisplayPageView(SystemPK pk,
													  List<DspCnrFoResult> dspCnrResultList, String serialNumber,
													  Long tmplatSn, String parentType, String prcSectCd,
													  String spcPrmTp, String screenNo) throws Exception {
		return selectDisplayPageView(pk, dspCnrResultList, serialNumber, tmplatSn, parentType, prcSectCd, spcPrmTp, screenNo, null);
	}

	@Override
	public DisplayPageResultDTO selectDisplayPageView(SystemPK pk,
													  List<DspCnrFoResult> dspCnrResultList, String serialNumber,
													  Long tmplatSn, String parentType, String prcSectCd,
													  String spcPrmTp, String screenNo, String[] brandIds) throws Exception {
		DisplayPageResultDTO result = new DisplayPageResultDTO();
		// 해당페이지에서 사용하게될 전시코너목록을 받아서 설정한다.
		result.setDspCnrResultList(dspCnrResultList);

		// 임직원 검사
		String empYn = "N";
		Mbr mbr = null;
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();

			if(mbrAtrb.equals("plgrim") ||
					mbrAtrb.equals("PLGRIM_FSHN") ||
					mbrAtrb.equals("GRPCO")){
				empYn = "Y";
			}
		}else{
			mbr = new Mbr();
			mbr.setMbrTpCd("NMBR");
		}

		// 검색조건구성
		Map<String,Object> map = new HashMap<String,Object>();
		//parentType  ('THEMA_PGE', 'DSP_CTGRY','S_TRND', 'EVT','PROMT')
		map.put("parentType", parentType);
		if(parentType.equals("THEMA_PGE") || parentType.equals("DSP_CTGRY") || parentType.equals("EVT")){
			map.put("parentStringCd", serialNumber);
		}else{
			int cd = Integer.parseInt(serialNumber);
			map.put("parentIntCd", new Integer(cd));
		}
		map.put("tmplatSn", tmplatSn);
		map.put("lang", pk.getLang());
		map.put("device", pk.getDevice());
		map.put("aplMbrTp", mbr.getMbrTpCd());
		map.put("aplMbrAtrb", mbr.getMbrAtrbCd());
		map.put("grpcoId", mbr.getPsitnGrpcoId());
		map.put("prcSectCd", prcSectCd);
		map.put("spcPrmTp", spcPrmTp);
		map.put("empYn", empYn);
		map.put("mallId", pk.getMall());
		map.put("brandIds", brandIds);
		// 전시컨텐츠 말기
		displayCornerService.selectDspCnrConttList(dspCnrResultList, map);

		// 자동상품채우기
		List<Long> cnrNoList = new ArrayList<Long>();
		List<Long> godCntList = new ArrayList<Long>();
		List<String> typeList = new ArrayList<String>();
		// 개별SCREEN_NO에 지정되는 설정값
		if(screenNo.equals("MALL_MAIN")){
			for(int i=0; i<dspCnrResultList.size(); i++) {	//IndexOutOfBoundsException 발생 방지 2016.05.01
				if (i == 3) {
					DspCnrFoResult cnrFoResult1 = dspCnrResultList.get(i);
					cnrNoList.add(cnrFoResult1.getDspCnr().getCnrSn());
					godCntList.add(new Long(10));
					typeList.add("BEST");
					break;
				}
			}
		}else if(screenNo.equals("CATEGORY_MAIN")){
			for(int i=0; i<dspCnrResultList.size(); i++){
				if(i==2){
					DspCnrFoResult cnrFoResult1 = dspCnrResultList.get(2);
					cnrNoList.add(cnrFoResult1.getDspCnr().getCnrSn());
					godCntList.add(new Long(10));
					typeList.add("BEST");
				}else if(i==4){
					DspCnrFoResult cnrFoResult2 = dspCnrResultList.get(4);
					cnrNoList.add(cnrFoResult2.getDspCnr().getCnrSn());
					godCntList.add(new Long(5));
					typeList.add("NEW");
					break;
				}
			}
		}else if(screenNo.equals("BRANDSHOP_A")){
			// CNR2:WHAT'S NEW (5)
			// CNR3:TOPSELLER (10)
			for(int i=0; i<dspCnrResultList.size(); i++) {
				if (i == 1) {
					DspCnrFoResult cnrFoResult1 = dspCnrResultList.get(1);
					cnrNoList.add(cnrFoResult1.getDspCnr().getCnrSn());
					godCntList.add(new Long(5));
					typeList.add("BRND-NEW-OTHERS");
				}else if(i==2){
					DspCnrFoResult cnrFoResult2 = dspCnrResultList.get(2);
					cnrNoList.add(cnrFoResult2.getDspCnr().getCnrSn());
					godCntList.add(new Long(10));
					typeList.add("BRND-BEST-OTHERS");
					break;
				}
			}
		}else if(screenNo.equals("BRANDSHOP_B")){
			// CNR2:WHAT'S NEW (5)
			// CNR4:TOPSELLER (5)
			for(int i=0; i<dspCnrResultList.size(); i++) {
				if (i == 1) {
					DspCnrFoResult cnrFoResult1 = dspCnrResultList.get(1);
					cnrNoList.add(cnrFoResult1.getDspCnr().getCnrSn());
					godCntList.add(new Long(5));
					typeList.add("BRND-NEW-OTHERS");
				}else if(i==3){
					DspCnrFoResult cnrFoResult2 = dspCnrResultList.get(3);
					cnrNoList.add(cnrFoResult2.getDspCnr().getCnrSn());
					godCntList.add(new Long(5));
					typeList.add("BRND-BEST-OTHERS");
					break;
				}
			}
		}else if(screenNo.equals("BRANDSHOP_C")){
			// CNR2:WHAT'S NEW(5)
			// CNR3:TOPSELLER (5)
			for(int i=0; i<dspCnrResultList.size(); i++) {
				if (i == 1) {
					DspCnrFoResult cnrFoResult1 = dspCnrResultList.get(1);
					cnrNoList.add(cnrFoResult1.getDspCnr().getCnrSn());
					godCntList.add(new Long(5));
					typeList.add("BRND-NEW-8S");
				}else if(i==2){
					DspCnrFoResult cnrFoResult2 = dspCnrResultList.get(2);
					cnrNoList.add(cnrFoResult2.getDspCnr().getCnrSn());
					godCntList.add(new Long(5));
					typeList.add("BRND-BEST-8S");
					break;
				}
			}
		}else if(screenNo.equals("BRANDSHOP_D")){
			// CNR3:TOPSELLER (10)
			// CNR4:WHAT'S NEW (10)
			for(int i=0; i<dspCnrResultList.size(); i++) {
				if (i == 2) {
					DspCnrFoResult cnrFoResult1 = dspCnrResultList.get(2);
					cnrNoList.add(cnrFoResult1.getDspCnr().getCnrSn());
					godCntList.add(new Long(10));
					typeList.add("BRND-BEST-OTHERS");
				} else if (i == 3) {
					DspCnrFoResult cnrFoResult2 = dspCnrResultList.get(3);
					cnrNoList.add(cnrFoResult2.getDspCnr().getCnrSn());
					godCntList.add(new Long(10));
					typeList.add("BRND-NEW-OTHERS");
					break;
				}
			}
		}else if(screenNo.equals("BRANDSHOP_E")){
			// CNR2:TOPSELLER (5)
			// CNR3:WHAT'S NEW (5)
			for(int i=0; i<dspCnrResultList.size(); i++) {
				if (i == 1) {
					DspCnrFoResult cnrFoResult1 = dspCnrResultList.get(1);
					cnrNoList.add(cnrFoResult1.getDspCnr().getCnrSn());
					godCntList.add(new Long(5));
					typeList.add("BRND-BEST-OTHERS");
				}else if(i==2){
					DspCnrFoResult cnrFoResult2 = dspCnrResultList.get(2);
					cnrNoList.add(cnrFoResult2.getDspCnr().getCnrSn());
					godCntList.add(new Long(5));
					typeList.add("BRND-NEW-OTHERS");
					break;
				}
			}
		}
		//해당페이지의 자동로직코너의 코너세트정보를 구한다.
		HashMap<String,Object> m = new HashMap<String,Object>();
		m.put("tmplatSn", tmplatSn);
		m.put("dspCtgryNo", serialNumber);
		m.put("cnrNoList", cnrNoList);
		List<DspCnrFoResult> dcResultList = null;
		if(cnrNoList.size() > 0) {
			dcResultList = displayCornerService.selectDspCnrSetByCnr(m);
		}
		// 실제데이터가 있는 코너의 코너세트정보와 비교한다.
		if(dcResultList != null) {
			for(int i = 0; i < dcResultList.size(); i ++){
				//전시코너
				DspCnrFoResult dspCnr = dcResultList.get(i);
				DspCnr cnrSrc = dspCnr.getDspCnr();
				//위전시코너-코너세트에 보여줄 상품 개 수
				Long godCnt = godCntList.get(i);
				//위전시코너-타입(NEW/BEST 등)
				String type = typeList.get(i);
				//BO에서 초기에 설정된 코너세트정보
				List<DspCnrSetFoResult> setSrcList = dspCnr.getDspCnrSetList();
				//실제로 운용되는 코너세트정보
				List<DspCnrSetFoResult> setTgtList = null;
				for(DspCnrFoResult dcfResult:dspCnrResultList){
					DspCnr cnrTgt = dcfResult.getDspCnr();
					if(cnrSrc.getCnrSn().equals(cnrTgt.getCnrSn())){
						setTgtList = dcfResult.getDspCnrSetList();
						if(setTgtList == null){
							setTgtList = new ArrayList<DspCnrSetFoResult>();
							dcfResult.setDspCnrSetList(setTgtList);
						}
						break;
					}
				}
				int index = 0;
				for(DspCnrSetFoResult setSrc:setSrcList){
					DspCnrSetFoResult setResult = null;
					for(DspCnrSetFoResult setTgt:setTgtList){
						if(setSrc.getDspCnrSet().getCnrSetSn().equals(setTgt.getDspCnrSet().getCnrSetSn())){
							setResult = setTgt;
							break;
						}
					}
					if(setResult == null){
						setResult = setSrc;
						setTgtList.add(index, setResult);
						//setTgtList.add(setResult);
					}
					setAutoDspCnrSetGodList(screenNo, setResult, godCnt, type, map);
					index ++;
				}
			}
		}
		return result;
	}

	@Override
	public List<SysPopupNoti> selectMainPopupList(SystemPK pk, String dspCtgryNo)
			throws Exception {
		Mbr mbr = null;
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
		}else{
			mbr = new Mbr();
			mbr.setMbrTpCd("NMBR");
		}

		HashMap<String,String> map = new HashMap<String,String>();

		map.put("lang", pk.getLang());
		map.put("device", pk.getDevice());
		map.put("aplMbrTp", mbr.getMbrTpCd());
		map.put("aplMbrAtrb", mbr.getMbrAtrbCd());
		map.put("grpcoId", mbr.getPsitnGrpcoId());
		map.put("mallId", pk.getMall());
		map.put("dspCtgryNo", dspCtgryNo);

		return displayCategorysService.selectMainPopupList(map);
//		return null;
	}

	// 기획전 카테고리기준 메인
	@Override
	public DspPlanResultFoDTO getPlanCategoryView(DspPlanSearchFoDTO searchDTO,String specialCategoryNo, SystemPK pk) throws Exception {
		//기획전 정보
		DspPlanResultFoDTO dspPlanFoResultDTO = new DspPlanResultFoDTO();

		DspCtgrySearchFoDTO dto = new DspCtgrySearchFoDTO();

		DspCtgry dspCtgry = new DspCtgry();
		dspCtgry.setDspCtgryNo(specialCategoryNo);
		dto.setDspCtgry(dspCtgry);

		/** 메인몰 전시카테고리 기본정보 조회 */
		DspCtgryFoResult thema = displayCategorysService.selectDisplayCategoryInfo(pk, dto);
		List<DspCnrFoResult> cnrInfoList = new ArrayList<DspCnrFoResult>();

		//기획전 카테고리별 카운트
		List<DspPlanFoResult> dspPlanCtgryCntList = displayPlanService.selectPlanCategoryCnt(searchDTO,pk);

		//기획전 리스트 총갯수
		long total = displayPlanService.selectPlanMainTotalCount(searchDTO,pk);

		dspPlanFoResultDTO.setTotal(total);
		dspPlanFoResultDTO.setCtgryInfo(thema);
		dspPlanFoResultDTO.setCornerList(cnrInfoList);
		dspPlanFoResultDTO.setDspPlanCtgryCntList(dspPlanCtgryCntList);

		return dspPlanFoResultDTO;
	}

	// 기획전 브랜드기준 메인
	@Override
	public DspPlanResultFoDTO getPlanBrandyView(DspPlanSearchFoDTO searchDTO,String specialCategoryNo, SystemPK pk) throws Exception {
		//기획전 정보
		DspPlanResultFoDTO dspPlanFoResultDTO = new DspPlanResultFoDTO();

		DspCtgrySearchFoDTO dto = new DspCtgrySearchFoDTO();

		DspCtgry dspCtgry = new DspCtgry();
		dspCtgry.setDspCtgryNo(specialCategoryNo);
		dto.setDspCtgry(dspCtgry);

		/** 메인몰 전시카테고리 기본정보 조회 */
//		DspCtgryFoResult thema = displayCategorysService.selectDisplayCategoryInfo(pk, dto);
//		List<DspCnrFoResult> cnrInfoList = new ArrayList<DspCnrFoResult>();
//		if(thema != null) {
//			List<DspCnrTmplatInfo> list = thema.getDspCnrTmplatList();
//
//			if(list != null){
//				for(DspCnrTmplatInfo cnrTmplat:list){
//					DspCnrSearchFoDTO cnrSearchDTO = new DspCnrSearchFoDTO();
//					DspCnr dspCnr = new DspCnr();
//					dspCnr.setCnrSn(cnrTmplat.getCnrSn());
//					cnrSearchDTO.setDspCnr(dspCnr);
//					cnrSearchDTO.setParentType("THEMA_PGE");
//					cnrSearchDTO.setParentStringCd(dto.getDspCtgry().getDspCtgryNo());
//					cnrSearchDTO.setPrcSectCd(dto.getPrcSectCd());
//					cnrSearchDTO.setAplMbrTp(searchDTO.getAplMbrTp());
//					cnrSearchDTO.setAplMbrAtrb(searchDTO.getAplMbrAtrb());
//					cnrSearchDTO.setGrpcoId(searchDTO.getPsitnGrpcoId());
//
//					DspCnrFoResult dspCnrResult = displayCornerService.selectDspCnrDefaultInfo(pk, cnrSearchDTO);
//					cnrInfoList.add(dspCnrResult);
//				}
//			}
//		}

		//기획전 브랜드별 카운트
		List<DspPlanFoResult> dspPlanbrndyCntList = displayPlanService.selectPlanBrandyCnt(searchDTO,pk);
		long total = displayPlanService.selectPlanMainTotalCount(searchDTO,pk);

		dspPlanFoResultDTO.setTotal(total);
//		dspPlanFoResultDTO.setCtgryInfo(thema);
//		dspPlanFoResultDTO.setCornerList(cnrInfoList);
		dspPlanFoResultDTO.setDspPlanBrndCntList(dspPlanbrndyCntList);

		return dspPlanFoResultDTO;
	}
	//b2e 코드 조회
	@Override
	public String selectB2eCode(Integer promtSn, String mode,
								SystemPK pk) {

		DspPlanSearchFoDTO searchDTO = new DspPlanSearchFoDTO();

		searchDTO.setPromtSn((long)promtSn);
		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		searchDTO.setMode(mode);

		String b2eCode = "";

		String promtB2eCode = displayPlanService.selectB2eCode(searchDTO);

		if(promtB2eCode != null && promtB2eCode != ""){

			String promotionB2eCode = displayPlanService.selectPromotionB2eCode(promtB2eCode);

			if(promotionB2eCode != null && promotionB2eCode != ""){
				b2eCode = promotionB2eCode;
			}
		}

		return b2eCode;
	}

	//전체 상품 조회 모바일
	@Override
	public DspPlanDetailResultFoDTO selectPlan(DspPlanSearchFoDTO searchDTO, Integer promtSn,String mode, String prcSectCd,String pageNo,SystemPK pk) throws Exception {

		DspPlanDetailResultFoDTO dspPlanDetailResultFoDTO = new DspPlanDetailResultFoDTO();

		searchDTO.setPromtSn((long)promtSn);
		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		searchDTO.setMode(mode);
		searchDTO.setPrcSectCd(prcSectCd);
		searchDTO.setPageNo(pageNo);


		//기획전 상세정보
		List<DspPlanFoResult> detailCorner = displayPlanService.selectPlanCorner(searchDTO,promtSn, pk);

//		ArrayList<DspCnrFoResult> cornerList = new ArrayList<DspCnrFoResult>();

//		for (DspPlanFoResult dspPlanFoResult : detailCorner) {
//
//			DspCnrSearchFoDTO cnrSearch = new DspCnrSearchFoDTO();
//
//			cnrSearch.setParentType("PROMT");
//			cnrSearch.setParentIntCd(promtSn);
//			cnrSearch.setDspCnr(dspPlanFoResult.getDspCnr());
//			cnrSearch.setMallId(pk.getMall());
//			cnrSearch.setDevice(pk.getDevice());
//			cnrSearch.setLang(pk.getLang());
//			cnrSearch.setPrcSectCd(prcSectCd);
//			cnrSearch.setAplMbrTp(searchDTO.getAplMbrTp());
//			cnrSearch.setAplMbrAtrb(searchDTO.getAplMbrAtrb());
//			cnrSearch.setGrpcoId(searchDTO.getPsitnGrpcoId());

		//코너 기본정보
//			DspCnrFoResult dspCnrFoResult = displayCornerService.selectDspCnrDefaultInfo(pk, cnrSearch);
//
//			cornerList.add(dspCnrFoResult);
//		}

		if("brnd".equals(mode.toString())){
			//브랜드 리스트
			List<DspPlanFoResult> brndList = displayPlanService.selectbrndList(searchDTO);
			dspPlanDetailResultFoDTO.setDspBrndList(brndList);
		}else{
			//카테고리 리스트
//			List<DspPlanFoResult> ctgryList = displayPlanService.selectCtgryList(searchDTO);
//			dspPlanDetailResultFoDTO.setDspCtgryList(ctgryList);
		}

		//기획전 리스트
//		searchDTO.setAplBrndId("allBrnd");
//		List<DspPlanFoResult> dspPlanList = displayPlanService.selectPlanList(searchDTO);
//		dspPlanDetailResultFoDTO.setDspPlanList(dspPlanList);

		//구분자 정보 리스트
		//List<DspPlanSprtrFoResult> dspPlanSprtrList = displayPlanService.selectPlanSprtr(searchDTO);

//		if(detailCorner != null && detailCorner.size() > 0){
//			searchDTO.setSortValue(detailCorner.get(0).getDspPromt().getDspGodSortStdrCd().toString());
//			if(pk.getDevice().toString().equals("PC")){
//				searchDTO.setListExCnt(detailCorner.get(0).getDspPromt().getPcListExpsrCntCd().toString());
//			}else{
//				searchDTO.setListExCnt(detailCorner.get(0).getDspPromt().getMobileListExpsrCntCd().toString());
//			}
//		}
		//상품정보 리스트
		//List<DspPlanSprtrFoResult> detailGodList = displayPlanService.selectPlanGodList(searchDTO);

		//상품 총 갯수
//		long total = displayPlanService.selectPlanGodPageCount(searchDTO);

		//모바일 상품 리스트
		List<List<DspPlanGodFoResult>> detailMbGodList = displayPlanService.selectPlanMbGodList(searchDTO);

		//모바일 구분자별 상품리스트
		List<DspPlanSprtrFoResult> detailMbSprtrGodList = displayPlanService.selectPlanMbSprtrGodList(searchDTO);


		if(detailCorner != null && detailCorner.size() > 0){
			dspPlanDetailResultFoDTO.setDspPlanDetail(detailCorner.get(0));
		}

//		dspPlanDetailResultFoDTO.setTotalRow(total);

//		dspPlanDetailResultFoDTO.setCornerList(cornerList);
		//dspPlanDetailResultFoDTO.setDspPlanList(dspPlanList);
		//dspPlanDetailResultFoDTO.setDspPlanSprtrList(dspPlanSprtrList);
		//dspPlanDetailResultFoDTO.setDspPlanDetailGodList(detailGodList);

		dspPlanDetailResultFoDTO.setDetailMbGodList(detailMbGodList);
		dspPlanDetailResultFoDTO.setDetailMbSprtrGodList(detailMbSprtrGodList);


		return dspPlanDetailResultFoDTO;
	}

	//전체 상품 조회 PC
	@Override
	public DspPlanDetailResultFoDTO selectPlan(DspPlanSearchFoDTO searchDTO, Integer promtSn,String mode,SystemPK pk) throws Exception {
		DspPlanDetailResultFoDTO dspPlanDetailResultFoDTO = new DspPlanDetailResultFoDTO();

		searchDTO.setPromtSn((long)promtSn);
		searchDTO.setMallId(pk.getMall());
//		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		searchDTO.setMode(mode);

		// 기획전 전시대상 속성으로 기획전 진입여부 결정 - 미리보기 제외, 일단 국문만 #sr 48524, 170804
		/*
		 * 1. 국문이고 미리 보기가 아닌경우에만
		 * 2. 로그인이 되어 있는경우
		 *	2-1 회원 정보를 이용하여 전시대상 기획전이 있는지 확인
		 *		2-1-1 기획전이 있으면 계속 진행
		 *		2-1-2 없으면 메인으로 이동
		 * 3. 로그인이 되어 있지 않은경우
		 *	3-1 기획전 전시대상정보 조회
		 *		3-1-1 결과가 없으면 메인으로 이동
		 *		3-1-2 전시대상 멤버타입에 비회원이 있으면 원 로직 진행
		 *		3-1-3 전시대상 회원속성에 일반회원 속성이 있으면 로그인페이지 이동
		 * 		3-1-4 전시대상 회원속성에 일반회원 속성이 없으면 임직원 기획전임을 알리고 로그인 페이지 이동
		 * */
		dspPlanDetailResultFoDTO.setCheckResult(true);
		if(DisplayEnum.DF_LANG.toString().equals(pk.getLang())&&!"Y".equals(searchDTO.getPrev())){

			int checkCnt = 0;

			searchDTO.setPromtSn((long)promtSn);

			if(ContextService.hasRole()){
				checkCnt = displayPlanService.selectPlanTgtChkCnt(searchDTO);
				if(0==checkCnt){
					dspPlanDetailResultFoDTO.setResultCode(DisplayEnum.PlanResultCode.NOT_PARTCPTN_TGT.toString());
					dspPlanDetailResultFoDTO.setCheckResult(false);
				}else{
					dspPlanDetailResultFoDTO.setCheckResult(true);
				}
			}else{
				dspPlanDetailResultFoDTO = displayPlanService.selectPlanDspTgtInfo(searchDTO);
				if(dspPlanDetailResultFoDTO==null){
					dspPlanDetailResultFoDTO = new DspPlanDetailResultFoDTO();
					dspPlanDetailResultFoDTO.setResultCode(DisplayEnum.PlanResultCode.INVALID_PLAN.toString());
					dspPlanDetailResultFoDTO.setCheckResult(false);
				}else if(dspPlanDetailResultFoDTO.getTgtMbrTpCd().indexOf(MemberEnum.MemberTpCd.NMBR.toString())> -1){
					dspPlanDetailResultFoDTO.setCheckResult(true);
				}else{
					dspPlanDetailResultFoDTO.setCheckResult(false);
					if(dspPlanDetailResultFoDTO.getTgtMbrAtrbCd().indexOf(MemberEnum.MemberAtrbCd.GNRL_MBR.toString()) > -1){
						dspPlanDetailResultFoDTO.setResultCode(DisplayEnum.PlanResultCode.NOT_LOGIN.toString());
					}else{
						dspPlanDetailResultFoDTO.setResultCode(DisplayEnum.PlanResultCode.EMP_MBR_ONLY.toString());
					}
				}
			}

			if(!dspPlanDetailResultFoDTO.isCheckResult()){
				dspPlanDetailResultFoDTO.setFailJsp("display/specialDetailredirect");
				return dspPlanDetailResultFoDTO;
			}
		}

		//기획전 상세정보
		List<DspPlanFoResult> detailCorner = displayPlanService.selectPlanCorner(searchDTO,promtSn, pk);

		if("PC".equals(pk.getDevice())){
			if("3PCE".equals(detailCorner.get(0).getDspPromt().getPcListExpsrCntCd())){
				searchDTO.setImgSizeCd("346X457");
			}else{
				searchDTO.setImgSizeCd("228X301");
			}
		}else{
			if("1PCE".equals(detailCorner.get(0).getDspPromt().getPcListExpsrCntCd())){
				searchDTO.setImgSizeCd("1138X1500");
			}else{
				searchDTO.setImgSizeCd("346X457");
			}
		}
		//OLD/NEW 템플릿 구분
//		if(detailCorner!=null && detailCorner.size() > 0 && detailCorner.get(0).getDspTmplat() !=null
//				&& detailCorner.get(0).getDspTmplat().getTmplatSn() > Long.parseLong(getConfigService().getProperty("ncp_web_pc_dx.new.promt.tmplat.sn"))) {
//			DspPlanDetailResultFoDTO tmp = new DspPlanDetailResultFoDTO();
//			tmp.setTmplatSnTp("NEW");
//			return tmp;
//		}


//		ArrayList<DspCnrFoResult> cornerList = new ArrayList<DspCnrFoResult>();

//		for (DspPlanFoResult dspPlanFoResult : detailCorner) {
//
//			DspCnrSearchFoDTO cnrSearch = new DspCnrSearchFoDTO();
//
//			cnrSearch.setParentType("PROMT");
//			cnrSearch.setParentIntCd(promtSn);
//			cnrSearch.setDspCnr(dspPlanFoResult.getDspCnr());
//			cnrSearch.setMallId(pk.getMall());
//			cnrSearch.setDevice(pk.getDevice());
//			cnrSearch.setLang(pk.getLang());
//			cnrSearch.setPrcSectCd(searchDTO.getPrcSectCd());
//			cnrSearch.setAplMbrTp(searchDTO.getAplMbrTp());
//			cnrSearch.setAplMbrAtrb(searchDTO.getAplMbrAtrb());
//			cnrSearch.setGrpcoId(searchDTO.getPsitnGrpcoId());
//
//			//코너 기본정보
//			DspCnrFoResult dspCnrFoResult = displayCornerService.selectDspCnrDefaultInfo(pk, cnrSearch);
//
//			cornerList.add(dspCnrFoResult);
//		}

//		if("brnd".equals(mode.toString())){
		//브랜드 리스트
//			List<DspPlanFoResult> brndList = displayPlanService.selectbrndList(searchDTO);
//			dspPlanDetailResultFoDTO.setDspBrndList(brndList);
//			searchDTO.setAplBrndId(searchDTO.getSectCd());
//		}else{
		//카테고리 리스트
//			List<DspPlanFoResult> ctgryList = displayPlanService.selectCtgryList(searchDTO);
//			dspPlanDetailResultFoDTO.setDspCtgryList(ctgryList);
//			searchDTO.setCategoryNo(searchDTO.getSectCd());
//		}

		//기획전 리스트
//		List<DspPlanFoResult> dspPlanList = displayPlanService.selectPlanList(searchDTO);

		//구분자 정보 리스트
		List<DspPlanSprtrFoResult> dspPlanSprtrList = displayPlanService.selectPlanSprtr(searchDTO);

//		if(detailCorner != null && detailCorner.size() > 0){
//			searchDTO.setSortValue(detailCorner.get(0).getDspPromt().getDspGodSortStdrCd().toString());
//			if(pk.getDevice().toString().equals("PC")){
//				searchDTO.setListExCnt(detailCorner.get(0).getDspPromt().getPcListExpsrCntCd().toString());
//			}else{
//				searchDTO.setListExCnt(detailCorner.get(0).getDspPromt().getMobileListExpsrCntCd().toString());
//			}
//		}
		if(dspPlanSprtrList != null && dspPlanSprtrList.size() > 0 ){
			if(dspPlanSprtrList.get(0).getDspPromt() != null){
				searchDTO.setSortValue(dspPlanSprtrList.get(0).getDspPromt().getDspGodSortStdrCd());
			}
		}
		//상품정보 리스트
		List<DspPlanSprtrFoResult> detailGodList = displayPlanService.selectPlanGodList(pk, searchDTO);

		if(detailCorner != null && detailCorner.size() > 0){
			dspPlanDetailResultFoDTO.setDspPlanDetail(detailCorner.get(0));
		}

//		dspPlanDetailResultFoDTO.setCornerList(cornerList);
//		dspPlanDetailResultFoDTO.setDspPlanList(dspPlanList);
		dspPlanDetailResultFoDTO.setDspPlanSprtrList(dspPlanSprtrList);
		dspPlanDetailResultFoDTO.setDspPlanDetailGodList(detailGodList);

		return dspPlanDetailResultFoDTO;
	}


	//전체 상품 조회 미리보기
	@Override
	public DspPlanDetailResultFoDTO selectPlan(DspPlanSearchFoDTO searchDTO,
											   Integer promtSn, SystemPK pk) throws Exception {
		DspPlanDetailResultFoDTO dspPlanDetailResultFoDTO = new DspPlanDetailResultFoDTO();

		searchDTO.setPromtSn((long)promtSn);
		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());

		//기획전 상세정보
		List<DspPlanFoResult> detailCorner = displayPlanService.selectPrevPlanCorner(searchDTO,promtSn, pk);

		ArrayList<DspCnrFoResult> cornerList = new ArrayList<DspCnrFoResult>();

		for (DspPlanFoResult dspPlanFoResult : detailCorner) {

			DspCnrSearchFoDTO cnrSearch = new DspCnrSearchFoDTO();

			cnrSearch.setParentType("PROMT");
			cnrSearch.setParentIntCd(promtSn);
			cnrSearch.setDspCnr(dspPlanFoResult.getDspCnr());
			cnrSearch.setMallId(pk.getMall());
			cnrSearch.setDevice(pk.getDevice());
			cnrSearch.setLang(pk.getLang());
			cnrSearch.setAplMbrTp(searchDTO.getAplMbrTp());
			cnrSearch.setAplMbrAtrb(searchDTO.getAplMbrAtrb());
			cnrSearch.setGrpcoId(searchDTO.getPsitnGrpcoId());

			//코너 기본정보
			DspCnrFoResult dspCnrFoResult = displayCornerService.selectDspCnrDefaultInfo(pk, cnrSearch);

			cornerList.add(dspCnrFoResult);
		}

		//기획전 리스트
		List<DspPlanFoResult> dspPlanList = displayPlanService.selectPlanList(searchDTO);

		//구분자 정보 리스트
		List<DspPlanSprtrFoResult> dspPlanSprtrList = displayPlanService.selectPrevPlanSprtr(searchDTO);

		if(detailCorner != null && detailCorner.size() > 0){
			searchDTO.setSortValue(detailCorner.get(0).getDspPromt().getDspGodSortStdrCd().toString());
			if(pk.getDevice().toString().equals("PC")){
				searchDTO.setListExCnt(detailCorner.get(0).getDspPromt().getPcListExpsrCntCd().toString());
			}else{
				searchDTO.setListExCnt(detailCorner.get(0).getDspPromt().getMobileListExpsrCntCd().toString());
			}
		}
		//상품정보 리스트
		List<DspPlanSprtrFoResult> detailGodList = displayPlanService.selectPrevPlanGodList(searchDTO);

		if(detailCorner != null && detailCorner.size() > 0){
			dspPlanDetailResultFoDTO.setDspPlanDetail(detailCorner.get(0));
		}

		dspPlanDetailResultFoDTO.setCornerList(cornerList);
		dspPlanDetailResultFoDTO.setDspPlanList(dspPlanList);
		dspPlanDetailResultFoDTO.setDspPlanSprtrList(dspPlanSprtrList);
		dspPlanDetailResultFoDTO.setDspPlanDetailGodList(detailGodList);

		return dspPlanDetailResultFoDTO;
	}

	//기획전 selectbox조회
	@Override
	public DspPlanDetailResultFoDTO selectPlanList(DspPlanSearchFoDTO searchDTO,String sn, String mode,SystemPK pk)
			throws Exception {
		DspPlanDetailResultFoDTO dspPlanDetailResultFoDTO = new DspPlanDetailResultFoDTO();

		if("brnd".equals(mode.toString())){
			//브랜드 리스트
			searchDTO.setAplBrndId(sn);
		}else if("ctgry".equals(mode.toString())){
			//카테고리 리스트
			searchDTO.setCategoryNo(sn);
		}

		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		searchDTO.setMode(mode);
		//기획전 리스트
		List<DspPlanFoResult> dspPlanList = displayPlanService.selectPlanList(searchDTO);

		dspPlanDetailResultFoDTO.setDspPlanList(dspPlanList);

		return dspPlanDetailResultFoDTO;
	}

	//구분자별 상품리스트 조회시
	@Override
	public DspPlanDetailResultFoDTO selectGodList(DspPlanSearchFoDTO searchDTO,Integer promtSn, String mode,
												  String sortValue,String sprtr,String prcSectCd, SystemPK pk) throws Exception {
		DspPlanDetailResultFoDTO dspPlanDetailResultFoDTO = new DspPlanDetailResultFoDTO();

		searchDTO.setPromtSn((long)promtSn);
		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
//		searchDTO.setPrcSectCd(prcSectCd);
		searchDTO.setSortValue(sortValue);
		searchDTO.setSprtr(sprtr);

		//구분자 정보 리스트
		List<DspPlanSprtrFoResult> dspPlanSprtrList = displayPlanService.selectPlanSprtr(searchDTO);

		//상품정보 리스트
		List<DspPlanSprtrFoResult> detailGodList = displayPlanService.selectPlanGodList(pk, searchDTO);

		dspPlanDetailResultFoDTO.setDspPlanSprtrList(dspPlanSprtrList);
		dspPlanDetailResultFoDTO.setDspPlanDetailGodList(detailGodList);

		return dspPlanDetailResultFoDTO;
	}

	// 기획전 상품 조회 JSON 모바일
	@Override
	public DspPlanDetailResultFoDTO selectGodList(DspPlanSearchFoDTO searchDTO,
												  int promtSn, String sprtr, String prcSectCd,String pageNo, SystemPK pk) throws Exception{
		// TODO Auto-generated method stub
		DspPlanDetailResultFoDTO dspPlanDetailResultFoDTO = new DspPlanDetailResultFoDTO();

		searchDTO.setPromtSn((long)promtSn);
		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		searchDTO.setPrcSectCd(prcSectCd);
		searchDTO.setSprtr(sprtr);
		searchDTO.setPageNo(pageNo);

		//기획전 상세정보
		List<DspPlanFoResult> detailCorner = displayPlanService.selectPlanCorner(searchDTO,promtSn, pk);

		//상세정보가 있을경우 리스트 타입 세팅
		if(detailCorner != null && detailCorner.size() > 0){
			searchDTO.setSortValue(detailCorner.get(0).getDspPromt().getDspGodSortStdrCd().toString());
			if(pk.getDevice().toString().equals("PC")){
				searchDTO.setListExCnt(detailCorner.get(0).getDspPromt().getPcListExpsrCntCd().toString());
			}else{
				searchDTO.setListExCnt(detailCorner.get(0).getDspPromt().getMobileListExpsrCntCd().toString());
			}
		}


		long total = displayPlanService.selectPlanGodPageCount(searchDTO);

		List<List<DspPlanGodFoResult>> detailMbGodList = displayPlanService.selectPlanMbGodList(searchDTO);

		List<DspPlanSprtrFoResult> detailMbSprtrGodList = displayPlanService.selectPlanMbSprtrGodList(searchDTO);

		dspPlanDetailResultFoDTO.setTotalRow(total);
		dspPlanDetailResultFoDTO.setDetailMbGodList(detailMbGodList);
		dspPlanDetailResultFoDTO.setDetailMbSprtrGodList(detailMbSprtrGodList);

		return dspPlanDetailResultFoDTO;
	}

	//strend 전체 리스트
	@Override
	public DspStrendResultFoDTO selectStrend(
			DspStrendSearchFoDTO searchDTO, PageParam pageParam,
			SystemPK pk) throws Exception{
		DspStrendResultFoDTO resultDTO = new DspStrendResultFoDTO();
		//회원정보세션
		Mbr mbr = null;
		String empYn = "N";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();
			if ("plgrim".equals(mbrAtrb) || "PLGRIM_FSHN".equals(mbrAtrb) || "GRPCO".equals(mbrAtrb)) {
				empYn = "Y";
			}

		} else {
			mbr = new Mbr();
			mbr.setMbrTpCd("NMBR");
		}

		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());

		List<DspStrendFoResult> brndList = displayStrendService.selectStrendBrand(searchDTO);

		searchDTO.setAplMbrTp(mbr.getMbrTpCd());
		searchDTO.setAplMbrAtrb(mbr.getMbrAtrbCd());
		searchDTO.setGrpcoId(mbr.getPsitnGrpcoId());
		searchDTO.setEmpYn(empYn);

		List<DspStrendFoResult> strendList = displayStrendService.selectStrendList(pk,searchDTO, pageParam);

		long total = displayStrendService.selectStrendCount(searchDTO);

		resultDTO.setTotal((int) total);
		resultDTO.setStrendList(strendList);
		resultDTO.setBrndList(brndList);

		return resultDTO;
	}
	@Override
	public List<DspStrendFoResult> selectGetTheStylePreNextList(
			DspStrendSearchFoDTO searchDTO)  throws Exception{
		return displayStrendService.selectGetTheStylePreNextList(searchDTO);
	}
	@Override
	public List<DspStrendFoResult> selectGetTheStyleLNBList(
			DspStrendSearchFoDTO searchDTO)  throws Exception{
		return displayStrendService.selectGetTheStyleLNBList(searchDTO);
	}
	@Override
	public DspStrendFoResult selectGetTheStyleDetail(
			DspStrendSearchFoDTO searchDTO)  throws Exception{
		return displayStrendService.selectGetTheStyleDetail(searchDTO);
	}
	@Override
	public Integer selectLatestStrndSnOfGetTheStyle(
			DspStrendSearchFoDTO searchDTO)  throws Exception{
		return displayStrendService.selectLatestStrndSnOfGetTheStyle(searchDTO);
	}
	@Override
	public DspStrendResultFoDTO selectGetTheStyleList(
			DspStrendSearchFoDTO searchDTO,PageParam pageParam)  throws Exception{
		DspStrendResultFoDTO resultDTO = new DspStrendResultFoDTO();
		List<DspStrendFoResult> gtsList = displayStrendService.selectGetTheStyleList(searchDTO, pageParam);

		long total = displayStrendService.selectGetTheStyleCount(searchDTO);

		resultDTO.setTotal((int)total);
		resultDTO.setStrendList(gtsList);

		return resultDTO;
	}
	/** GET THE STYLE 추천상품 더보기
	 *  [BO에서 선행되는 작업]
	 *   DSP_STRND_CNR_RELATE_GOD 테이블에
	 *   1.한 코너 안 등록 상품과 중복되는 상품 없음
	 *   2.한 코너 안 추천 상품과 중복되는 상품 없음
	 *   3.상품 1개당 추천상품 10개 내외로 적재
	 *  [selectRecommendGodMoreOfGTS 에서 하는 작업]
	 *   1.전체 코너 안 등록상품과 추천상품 중복 없음
	 *   2.품절 상품 제외
	 *   3.각 등록상품당 우선순위 1,2위 추천상품 조회
	 *  ※전체 코너에 추천상품간 중복체크는 하지 않음
	 */
	@Override
	public List<DspStrendFoResult> selectRecommendGodMoreOfGTS(
			DspStrendSearchFoDTO searchDTO){
		return displayStrendService.selectRecommendGodMoreOfGTS(searchDTO);
	}
	//룩북 메인 리스트
	@Override
	public DspStrendResultFoDTO selectLookbook(
			DspStrendSearchFoDTO searchDTO, PageParam pageParam,
			SystemPK pk) throws Exception {
		DspStrendResultFoDTO resultDTO = new DspStrendResultFoDTO();
		//회원정보세션
		Mbr mbr = null;
		String empYn = "N";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();
			if ("plgrim".equals(mbrAtrb) || "PLGRIM_FSHN".equals(mbrAtrb) || "GRPCO".equals(mbrAtrb)) {
				empYn = "Y";
			}

		} else {
			mbr = new Mbr();
			mbr.setMbrTpCd("NMBR");
		}

		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		searchDTO.setStrndTpCd("LKBUK");

		DspStrendSearchFoDTO brSearchDTO = new DspStrendSearchFoDTO();
		brSearchDTO.setMallId(pk.getMall());
		brSearchDTO.setDevice(pk.getDevice());
		brSearchDTO.setLang(pk.getLang());
		brSearchDTO.setStrndTpCd("LKBUK");
		brSearchDTO.setBrndNotInYn("Y");
		brSearchDTO.setUpBrndId(DisplayEnum.BEKER_BRND_ID.toString());

		List<DspStrendFoResult> brndList = displayStrendService.selectStrendBrand(brSearchDTO);
		List<DspStrendFoResult> sesonList = displayStrendService.selectStrendSeason(brSearchDTO);

		searchDTO.setAplMbrTp(mbr.getMbrTpCd());
		searchDTO.setAplMbrAtrb(mbr.getMbrAtrbCd());
		searchDTO.setGrpcoId(mbr.getPsitnGrpcoId());
		searchDTO.setEmpYn(empYn);

		if(searchDTO.getBrndId() != null && searchDTO.getBrndId().equals(DisplayEnum.BEKER_BRND_ID.toString())) {
			searchDTO.setUpBrndId(searchDTO.getBrndId());
			searchDTO.setBrndId(null);
		}
		else {
			searchDTO.setUpBrndId(null);
		}

		if(searchDTO.getRevSn() == null){
			searchDTO.setRevSn(baseRevSn);
		}

		List<DspStrendFoResult> lookbookList = displayStrendService.selectStrendList(pk,searchDTO, pageParam);

		long total = displayStrendService.selectStrendCount(searchDTO);

		resultDTO.setTotal((int)total);
		resultDTO.setStrendList(lookbookList);
		resultDTO.setBrndList(brndList);
		resultDTO.setSesonList(sesonList);

		return resultDTO;
	}

	//무비 메인
	@Override
	public DspStrendResultFoDTO selectMovieList(DspStrendSearchFoDTO searchDTO,
												PageParam pageParam, SystemPK pk) throws Exception {
		DspStrendResultFoDTO resultDTO = new DspStrendResultFoDTO();
		//회원정보세션
		Mbr mbr = null;
		String empYn = "N";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();
			if ("plgrim".equals(mbrAtrb) || "PLGRIM_FSHN".equals(mbrAtrb) || "GRPCO".equals(mbrAtrb)) {
				empYn = "Y";
			}

		} else {
			mbr = new Mbr();
			mbr.setMbrTpCd("NMBR");
		}

		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		searchDTO.setStrndTpCd("MOV");

		DspStrendSearchFoDTO brSearchDTO = new DspStrendSearchFoDTO();
		brSearchDTO.setMallId(pk.getMall());
		brSearchDTO.setDevice(pk.getDevice());
		brSearchDTO.setLang(pk.getLang());
		brSearchDTO.setStrndTpCd("MOV");
		brSearchDTO.setBrndNotInYn("Y");
		brSearchDTO.setUpBrndId(DisplayEnum.BEKER_BRND_ID.toString());

		List<DspStrendFoResult> brndList = displayStrendService.selectStrendBrand(brSearchDTO);

		searchDTO.setAplMbrTp(mbr.getMbrTpCd());
		searchDTO.setAplMbrAtrb(mbr.getMbrAtrbCd());
		searchDTO.setGrpcoId(mbr.getPsitnGrpcoId());
		searchDTO.setEmpYn(empYn);

		if(searchDTO.getBrndId() != null && searchDTO.getBrndId().equals(DisplayEnum.BEKER_BRND_ID.toString())) {
			searchDTO.setUpBrndId(searchDTO.getBrndId());
			searchDTO.setBrndId(null);
		}
		else {
			searchDTO.setUpBrndId(null);
		}

		List<DspStrendFoResult> movieList = displayStrendService.selectStrendList(pk,searchDTO,pageParam);

		long total = displayStrendService.selectStrendCount(searchDTO);

		resultDTO.setTotal((int)total);
		resultDTO.setStrendList(movieList);
		resultDTO.setBrndList(brndList);

		return resultDTO;
	}

	//코디 리스트
	@Override
	public DspStrendResultFoDTO selectCoordiList( DspStrendSearchFoDTO searchDTO, PageParam pageParam,SystemPK pk) throws Exception {
		DspStrendResultFoDTO resultDTO = new DspStrendResultFoDTO();
		//회원정보세션
		Mbr mbr = null;
		String empYn = "N";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();
			if ("plgrim".equals(mbrAtrb) || "PLGRIM_FSHN".equals(mbrAtrb) || "GRPCO".equals(mbrAtrb)) {
				empYn = "Y";
			}

		} else {
			mbr = new Mbr();
			mbr.setMbrTpCd("NMBR");
		}

		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		searchDTO.setStrndTpCd("CODI");
		searchDTO.setBrndNotInYn("Y");
		searchDTO.setUpBrndId(DisplayEnum.BEKER_BRND_ID.toString());

		DspStrendSearchFoDTO brSearchDTO = new DspStrendSearchFoDTO();
		brSearchDTO.setMallId(pk.getMall());
		brSearchDTO.setDevice(pk.getDevice());
		brSearchDTO.setLang(pk.getLang());
		brSearchDTO.setStrndTpCd("CODI");
		brSearchDTO.setBrndNotInYn("Y");
		brSearchDTO.setUpBrndId(DisplayEnum.BEKER_BRND_ID.toString());

		List<DspStrendFoResult> brndList = displayStrendService.selectStrendBrand(brSearchDTO);

		searchDTO.setAplMbrTp(mbr.getMbrTpCd());
		searchDTO.setAplMbrAtrb(mbr.getMbrAtrbCd());
		searchDTO.setGrpcoId(mbr.getPsitnGrpcoId());
		searchDTO.setEmpYn(empYn);
		if(searchDTO.getBrndId() != null && searchDTO.getBrndId().equals(DisplayEnum.BEKER_BRND_ID.toString())) {
			searchDTO.setUpBrndId(searchDTO.getBrndId());
			searchDTO.setBrndId(null);
		}
		else {
			searchDTO.setUpBrndId(null);
		}

		List<DspStrendFoResult> coordiList = displayStrendService.selectStrendList(pk,searchDTO,pageParam);

		long total = displayStrendService.selectStrendCount(searchDTO);

		resultDTO.setTotal((int)total);
		resultDTO.setStrendList(coordiList);
		resultDTO.setBrndList(brndList);

		return resultDTO;
	}


	//매거진 리스트
	@Override
	public DspStrendResultFoDTO selectMagazineList(
			DspStrendSearchFoDTO searchDTO, PageParam pageParam, SystemPK pk)
			throws Exception {
		DspStrendResultFoDTO resultDTO = new DspStrendResultFoDTO();
		//회원정보세션
		Mbr mbr = null;
		String empYn = "N";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();
			if ("plgrim".equals(mbrAtrb) || "PLGRIM_FSHN".equals(mbrAtrb) || "GRPCO".equals(mbrAtrb)) {
				empYn = "Y";
			}

		} else {
			mbr = new Mbr();
			mbr.setMbrTpCd("NMBR");
		}

		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		searchDTO.setStrndTpCd("MGZ");
		searchDTO.setBrndNotInYn("Y");
		searchDTO.setUpBrndId(DisplayEnum.BEKER_BRND_ID.toString());

		DspStrendSearchFoDTO brSearchDTO = new DspStrendSearchFoDTO();
		brSearchDTO.setMallId(pk.getMall());
		brSearchDTO.setDevice(pk.getDevice());
		brSearchDTO.setLang(pk.getLang());
		brSearchDTO.setStrndTpCd("MGZ");
		brSearchDTO.setBrndNotInYn("Y");
		brSearchDTO.setUpBrndId(DisplayEnum.BEKER_BRND_ID.toString());

		List<DspStrendFoResult> brndList = displayStrendService.selectStrendBrand(brSearchDTO);

		searchDTO.setAplMbrTp(mbr.getMbrTpCd());
		searchDTO.setAplMbrAtrb(mbr.getMbrAtrbCd());
		searchDTO.setGrpcoId(mbr.getPsitnGrpcoId());
		searchDTO.setEmpYn(empYn);

		if(searchDTO.getBrndId() != null && searchDTO.getBrndId().equals(DisplayEnum.BEKER_BRND_ID.toString())) {
			searchDTO.setUpBrndId(searchDTO.getBrndId());
			searchDTO.setBrndId(null);
		}
		else {
			searchDTO.setUpBrndId(null);
		}
		List<DspStrendFoResult> magazineList = displayStrendService.selectStrendList(pk,searchDTO,pageParam);

		long total = displayStrendService.selectStrendCount(searchDTO);

		resultDTO.setTotal((int)total);
		resultDTO.setStrendList(magazineList);
		resultDTO.setBrndList(brndList);

		return resultDTO;
	}

	//strend 상세보기
	@Override
	public DspStrendResultFoDTO selectStrendDetail(String strndTp, String prcSectCd,String spcPrmTp,Integer sTrndSn, SystemPK pk)
			throws Exception {
		// TODO Auto-generated method stub
		DspStrendResultFoDTO resultDTO = new DspStrendResultFoDTO();
		DspStrendSearchFoDTO searchDTO = new DspStrendSearchFoDTO();

		//회원정보세션
		Mbr mbr = null;
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
		}else{
			mbr = new Mbr();
			mbr.setMbrTpCd("NMBR");
		}

		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		searchDTO.setStrndSn(sTrndSn);
		searchDTO.setStrndTpCd(strndTp);

//		List<DspStrendFoResult> corner = displayStrendService.selectStrendCorner(searchDTO);

//		ArrayList<DspCnrFoResult> cornerList = new ArrayList<DspCnrFoResult>();

//		for (DspStrendFoResult dspStrendFoResult : corner) {
//			DspCnrSearchFoDTO cnrSearch = new DspCnrSearchFoDTO();
//			DspCnr dspCnr = new DspCnr();
//
//			dspCnr.setCnrSn(dspStrendFoResult.getDspCnrTmplatInfo().getCnrSn());
//			cnrSearch.setParentType("S_TRND");
//			cnrSearch.setParentIntCd(sTrndSn);
//			cnrSearch.setDspCnr(dspCnr);
//			cnrSearch.setMallId(pk.getMall());
//			cnrSearch.setDevice(pk.getDevice());
//			cnrSearch.setLang(pk.getLang());
//			cnrSearch.setAplMbrTp(mbr.getMbrTpCd());
//			cnrSearch.setAplMbrAtrb(mbr.getMbrAtrbCd());
//			cnrSearch.setGrpcoId(mbr.getPsitnGrpcoId());
//			cnrSearch.setPrcSectCd(prcSectCd);
//			cnrSearch.setSpcPrmTp(spcPrmTp);
//
//			//코너 기본정보
//			DspCnrFoResult dspCnrFoResult = displayCornerService.selectDspCnrDefaultInfo(pk, cnrSearch);
//
//			cornerList.add(dspCnrFoResult);
//        }

//		if(corner != null && corner.size() > 0){
//			resultDTO.setStrendDetail(corner.get(0));
//			resultDTO.setTrndNm(corner.get(0).getDspStrnd().getStrndNm());
//		}
//		resultDTO.setCornerList(cornerList);

		if(searchDTO.getRevSn() == null){
			searchDTO.setRevSn(baseRevSn);
		}

		List<DspStrendFoResult> result = displayStrendService.selectStrendCorner(searchDTO);

		if(result != null && result.size() > 0){
			resultDTO.setStrendDetail(result.get(0));
			resultDTO.setTrndNm(result.get(0).getDspStrnd().getStrndNm());
		}

		return resultDTO;
	}

	//strend 상세보기
	@Override
	public DspStrendResultFoDTO selectStrendDetail(String strndTp, String prcSectCd,Integer sTrndSn, SystemPK pk)
			throws Exception {
		// TODO Auto-generated method stub
		DspStrendResultFoDTO resultDTO = new DspStrendResultFoDTO();
		DspStrendSearchFoDTO searchDTO = new DspStrendSearchFoDTO();

		//회원정보세션
		Mbr mbr = null;

		String empYn = "N";

		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb =  mbr.getMbrAtrbCd();
			if("plgrim".equals(mbrAtrb) || "PLGRIM_FSHN".equals(mbrAtrb) || "GRPCO".equals(mbrAtrb)){
				empYn = "Y";
			}

		}else{
			mbr = new Mbr();
			mbr.setMbrTpCd("NMBR");
		}

		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		searchDTO.setStrndSn(sTrndSn);
		searchDTO.setStrndTpCd(strndTp);

		List<DspStrendFoResult> corner = displayStrendService.selectStrendCorner(searchDTO);

		if(!corner.isEmpty()){
			searchDTO.setBrndId(corner.get(0).getDspStrnd().getBrndId());
		}

		// 다음 룩북을 가져오는 로직 변경
		/*int nextStrnd = displayStrendService.selectNextStrend(searchDTO);


		if(nextStrnd < 1){
			nextStrnd = 0;
		}*/

		ArrayList<DspCnrFoResult> cornerList = new ArrayList<DspCnrFoResult>();

		if(corner != null && corner.size() > 0){
			resultDTO.setStrendDetail(corner.get(0));
			resultDTO.setTrndNm(corner.get(0).getDspStrnd().getStrndNm());
		}
		resultDTO.setCornerList(cornerList);
		//resultDTO.setNextStrnd(nextStrnd);

		return resultDTO;
	}



	@Override
	public DspCnrFoResult selectDisplayCornerView(SystemPK pk, DspCnrSearchFoDTO dto) throws Exception {
		Mbr mbr = null;
		// 로그인 되었는지 ROLE 검사
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
		} else {
			mbr = new Mbr();
			mbr.setMbrTpCd("NMBR");
		}
		dto.setAplMbrTp(mbr.getMbrTpCd());
		dto.setAplMbrAtrb(mbr.getMbrAtrbCd());
		dto.setGrpcoId(mbr.getPsitnGrpcoId());

		return displayCornerService.selectDspCnrDefaultInfo(pk, dto);
	}


	//멤버십 안내 및 약관
	@Override
	public SysStplat selectMembershipInfo(SystemPK pk) throws Exception{
		// TODO Auto-generated method stub
		SysStplat search = new SysStplat();
		search.setMallId(pk.getMall());
		search.setDevice(pk.getDevice());
		search.setLang(pk.getLang());
		search.setStplatCd("MBSH_STPLAT");

		return informationPageService.selectSysStplat(search);
	}

	@Override
	public SysStplat selectSysStplatCont(SystemPK pk, String stplatCd) throws Exception{
		// TODO Auto-generated method stub
		SysStplat search = new SysStplat();
		search.setMallId(pk.getMall());
		search.setDevice(pk.getDevice());
		search.setLang(pk.getLang());
		search.setStplatCd(stplatCd);
		if(search.getLang().equals("KOR")){
			return informationPageService.selectSysStplat(search);	
		}else{
			return informationPageService.selectSysStplatByLang(search);
		}		
	}

	@Override
	public List<SysStplatHistExtends> selectSysStplatHist(SystemPK pk, String stplatCd) throws Exception {
		// TODO Auto-generated method stub
		SysStplat search = new SysStplat();
		search.setMallId(pk.getMall());
		search.setDevice(pk.getDevice());
		search.setLang(pk.getLang());
		search.setStplatCd(stplatCd);
		if(search.getLang().equals("KOR")){
			return informationPageService.selectSysStplatHist(search);	
		}else{
			return null; //informationPageService.selectSysStplatHistByLang(search);
		}
	}	
	
	//개인정보취급방침
	@Override
	public SysStplat selectPrivacyPolicy(SystemPK pk)  throws Exception{
		// TODO Auto-generated method stub
		SysStplat search = new SysStplat();
		search.setMallId(pk.getMall());
		search.setDevice(pk.getDevice());
		search.setLang(pk.getLang());
		search.setStplatCd("PSNL_INFO_TRTMNT_POLCY");

		return informationPageService.selectSysStplat(search);
	}

	//과거 개인정보취급방침
	@Override
	public SysStplat selectOldPrivacyPolicy(SystemPK pk, String dataVersion)  throws Exception{
		// TODO Auto-generated method stub
		SysStplatHistExtends search = new SysStplatHistExtends();
		search.setMallId(pk.getMall());
		search.setDevice(pk.getDevice());
		search.setLang(pk.getLang());
		search.setStplatCd("PSNL_INFO_TRTMNT_POLCY");
		search.setHistStrDt(dataVersion);

		return informationPageService.selectOldSysStplat(search);
	}

	/*
	 * 개인정보처리방침 히스토리 생성
	 */
	@Override
	public List<SysStplatHistExtends> selectPrivacyPolicyHistDt(SystemPK pk) throws Exception{

		SysStplat search = new SysStplat();
		search.setLang(pk.getLang());
		search.setStplatCd(MemberEnum.StplatCd.PSNL_INFO_TRTMNT_POLCY.toString());

		return informationPageService.selectPrivacyPolicyHistDt(search);
	}

	// P포인트 이용약관
	@Override
	public SysStplat selectPurpleCoinTermsAndConditions(SystemPK pk)  throws Exception{
		// TODO Auto-generated method stub
		SysStplat search = new SysStplat();
		search.setMallId(pk.getMall());
		search.setDevice(pk.getDevice());
		search.setLang(pk.getLang());
		search.setStplatCd("WEBPNT_USEF_STPLAT");

		return informationPageService.selectSysStplat(search);
	}



	//이메일 무단수집거부
	@Override
	public SysStplat selectNoEmailPolicy(SystemPK pk) throws Exception {
		// TODO Auto-generated method stub
		SysStplat search = new SysStplat();
		search.setMallId(pk.getMall());
		search.setDevice(pk.getDevice());
		search.setLang(pk.getLang());
		search.setStplatCd("EMAIL_WONTICE_COLCT_REJECT");

		return informationPageService.selectSysStplat(search);
	}

	//사이트맵 카테고리 리스트 조회
	@Override
	public GnbResultFoDTO selectSiteView(SystemPK pk,
										 DspCtgrySearchFoDTO searchDTO) throws Exception {
		GnbResultFoDTO siteDTO = new GnbResultFoDTO();

		List<DspCtgryFoResult> dspCtgryList = displayCategorysService.selectSubDisplayCategoryList(pk, searchDTO);
		searchDTO.setSpecial("OUTLET");
		List<DspCtgryFoResult> outletCtgryList = displayCategorysService.selectSubDisplayCategorySpecialList(dspCtgryList, pk,searchDTO,"A");
		searchDTO.setSpecial("");

		siteDTO.setDspCtgryList(dspCtgryList);
		siteDTO.setOutLetCtgryList(outletCtgryList);

		return siteDTO;
	}

	@Override
	public DspCtgryFoResult selectDisplayCategoryInfo(SystemPK pk, DspCtgrySearchFoDTO dto) throws Exception {
		DspCtgryFoResult resultData = null;

		// 회원정보세션
		Mbr mbr = null;

		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
		}
		else {
			mbr = new Mbr();
			mbr.setMbrTpCd("NMBR");
		}

		resultData = displayCategorysService.selectDisplayCategoryInfo(pk, dto);

		return resultData;
	}

	@Override
	public DspCnrFoResult selectDspCnrDefaultInfo(SystemPK pk, DspCnrSearchFoDTO dto) throws Exception {
		DspCnrFoResult resultData = null;

		Mbr mbr = null;
		// 로그인 되었는지 ROLE 검사
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
		} else {
			mbr = new Mbr();
			mbr.setMbrTpCd("NMBR");
		}
		dto.setAplMbrTp(mbr.getMbrTpCd());
		dto.setAplMbrAtrb(mbr.getMbrAtrbCd());
		dto.setGrpcoId(mbr.getPsitnGrpcoId());

		resultData = displayCornerService.selectDspCnrDefaultInfo(pk, dto);

		return resultData;
	}

	/**
	 * 브랜드 베스트셀러 상품 목록 조회
	 * @param brndBstGod
	 * @return
	 * @throws Exception
	 */
/*
	public List<God> selectBrndBestGod(DspBrndBstGod brndBstGod) throws Exception {
		return displayCategorysService.selectBrndBestGod(brndBstGod);
	}
*/
	
	@Override
	public DspPlanResultFoDTO getPlanBrandyViewForMB(DspPlanSearchFoDTO searchDTO, SystemPK pk, boolean isFilter) throws Exception {
		//기획전 정보
		DspPlanResultFoDTO dspPlanFoResultDTO = new DspPlanResultFoDTO();
		//기획전 브랜드 기준 리스트
		List<DspPlanFoResult> dspPlanBrandList = displayPlanService.selectPlanBrandList(searchDTO, pk);
		dspPlanFoResultDTO.setDspPlanList(dspPlanBrandList);

		if (isFilter) {
			// 기획전 브랜드별 카운트
			List<DspPlanFoResult> dspPlanBrndCntList = displayPlanService.selectPlanBrandyCnt(searchDTO, pk);
			dspPlanFoResultDTO.setDspPlanBrndCntList(dspPlanBrndCntList);
			// 기획전 카테고리별 카운트
			List<DspPlanFoResult> dspPlanCtgryCntList = displayPlanService.selectPlanCategoryCnt(searchDTO, pk);
			dspPlanFoResultDTO.setDspPlanCtgryCntList(dspPlanCtgryCntList);
		}

		return dspPlanFoResultDTO;
	}

	@Override
	public List<DspCtgryResultMbDTO> selectMobileDisplayCategoryList(
			SystemPK pk, DspCtgrySearchFoDTO dto) throws Exception {

		List<DspCtgryResultMbDTO> dspCtgry2dResultList = new ArrayList<DspCtgryResultMbDTO>();
		List<DspCtgryGodFoResult> godList = new ArrayList<DspCtgryGodFoResult>();
		DspCtgryFoResult dspCtgryInfo = new DspCtgryFoResult();
		String[] brandIds = new String[1];
		// 임직원 검사
		String empYn = "N";
		Mbr mbr = null;
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();

			if(mbrAtrb.equals("plgrim") ||
					mbrAtrb.equals("PLGRIM_FSHN") ||
					mbrAtrb.equals("GRPCO")){
				empYn = "Y";
			}
		}
		dto.setEmpYn(empYn);

		try{
			List<DspCtgryFoResult> dspCtgryResultList = null;

			/** 전시카테고리2D 기본정보 조회 */
			dspCtgryInfo = displayCategorysService.selectDisplayCategoryInfo(pk, dto);

			if ("new".equals(dto.getDspCtgryType())) {
				/** 전시 카테고리 하부 목록 조회 */
				dspCtgryResultList = displayCategorysService.selectSubDisplayCategoryList(pk, dto);
				String godNos = displayCategorysService.getDspCtgrygodNoList(pk, dto);

				DspCtgryResultMbDTO dspCtgry2dResult = new DspCtgryResultMbDTO();
				dspCtgry2dResult.setCtgNo(dto.getDspCtgry().getDspCtgryNo());
				dspCtgry2dResult.setCateType("new");
				dspCtgry2dResult.setCtgNm("NEW");
				dspCtgry2dResult.setCtgDepthCd("2");
				dspCtgry2dResult.setGodNos(godNos);
				dspCtgry2dResultList.add(dspCtgry2dResult);

			} else if ("best".equals(dto.getDspCtgryType())) {
				/** 전시 카테고리 하부 목록 조회 */
				dspCtgryResultList = displayCategorysService.selectSubDisplayCategoryList(pk, dto);

				String godNos = displayCategorysService.getDspCtgrygodNoList(pk, dto);

				DspCtgryResultMbDTO dspCtgry2dResult = new DspCtgryResultMbDTO();
				dspCtgry2dResult.setCtgNo(dto.getDspCtgry().getDspCtgryNo());
				dspCtgry2dResult.setCateType("best");
				dspCtgry2dResult.setCtgNm("BEST");
				dspCtgry2dResult.setCtgDepthCd("2");
				dspCtgry2dResult.setGodNos(godNos);
				dspCtgry2dResultList.add(dspCtgry2dResult);


			} else if ("brand".equals(dto.getDspCtgryType())) {
				DspCtgrySearchFoDTO searchDTO = new DspCtgrySearchFoDTO();
				DspCtgry dspCtgry = new DspCtgry();
				dspCtgry.setDspCtgryNo(dto.getDspCtgryTreeRootDspCtgryNo());
				searchDTO.setDspCtgry(dspCtgry);
				searchDTO.setDspCtgryOutputType(dspCtgryInfo.getDspCtgry().getExpsrLvlCd());
				searchDTO.setHasGodList("N");
				searchDTO.setHasLocation("N");
				searchDTO.setHasSubCtgry("Y");
				searchDTO.setDspCtgryTreeRootDspCtgryNo(dto.getDspCtgryTreeRootDspCtgryNo());
				searchDTO.setSpcPrmTp(dto.getSpcPrmTp());
				searchDTO.setSpecial("BRAND");
				List<DspCtgryFoResult> brandList = this.selectDspCtgryViewForMB(pk, searchDTO).getDspCtgryResultList();

				if (brandList != null && 0 < brandList.size()) {
					DspCtgryFoResult row = brandList.get(0);
					DspCtgrySearchFoDTO searchDTO1 = new DspCtgrySearchFoDTO();
					DspCtgry dspCtgry1 = new DspCtgry();
					if ("Y".equals(dto.getMbCtgryListYn())) {
						dspCtgry1.setDspCtgryNo(dto.getDspCtgry().getDspCtgryNo());
						searchDTO1.setDspCtgryTreeRootDspCtgryNo(dto.getDspCtgry().getDspCtgryNo());
					} else {
						dspCtgry1.setDspCtgryNo("DXM");
						searchDTO1.setDspCtgryTreeRootDspCtgryNo("DXM");
					}
					searchDTO1.setDspCtgry(dspCtgry1);
					searchDTO1.setHasGodList("N");
					searchDTO1.setHasLocation("N");
					searchDTO1.setHasSubCtgry("Y");
//					searchDTO1.setSpecial("BRAND");
					brandIds[0] = row.getDspCtgry().getDspBrndId();
					searchDTO1.setSearchConditionBrandIds(brandIds);
					if(dto.getGrpBrndId() != null && !"".equals(dto.getGrpBrndId())) {
						searchDTO1.setGrpBrndId(dto.getGrpBrndId());
						searchDTO1.setSearchConditionBrandIds(null);
					}
					searchDTO1.setDspCtgryOutputType(dspCtgryInfo.getDspCtgry().getExpsrLvlCd());
					searchDTO1.setSpcPrmTp(dto.getSpcPrmTp());
					dspCtgryResultList = displayCategorysService.selectSubDisplayCategoryList(pk, searchDTO1);

				}
			} else {
				DspCtgryResultMbDTO dspCtgry2dResult = new DspCtgryResultMbDTO();
				dspCtgry2dResult.setCtgNo(dspCtgryInfo.getDspCtgry().getDspCtgryNo());
				dspCtgry2dResult.setCtgNm(dspCtgryInfo.getDspCtgry().getDspCtgryNm());
				dspCtgry2dResult.setCtgDepthCd(dspCtgryInfo.getDspCtgry().getCtgryDpthCd());
				dspCtgry2dResultList.add(dspCtgry2dResult);

				if (!"OUTLET".equals(dto.getSpecial()) && !dto.getHasAllCtgry().equals("Y") && ("0".equals(dspCtgryInfo.getDspCtgry().getCtgryDpthCd())
						|| ("1".equals(dspCtgryInfo.getDspCtgry().getCtgryDpthCd())))) {
					// New 카테고리
					DspCtgryResultMbDTO dspCtgry2dResultNew = new DspCtgryResultMbDTO();
					dspCtgry2dResultNew.setCtgNo(dspCtgryInfo.getDspCtgry().getDspCtgryNo() + "_new");
					dspCtgry2dResultNew.setCtgNm("New");
					dspCtgry2dResultNew.setCateType("new");
					dspCtgry2dResultNew.setNewBestType("new");
					dspCtgry2dResultNew.setCtgDepthCd(dspCtgryInfo.getDspCtgry().getCtgryDpthCd());
					dspCtgry2dResultList.add(dspCtgry2dResultNew);

					// Best 카테고리
					DspCtgryResultMbDTO dspCtgry2dResultBest = new DspCtgryResultMbDTO();
					dspCtgry2dResultBest.setCtgNo(dspCtgryInfo.getDspCtgry().getDspCtgryNo() + "_best");
					dspCtgry2dResultBest.setCtgNm("Best");
					dspCtgry2dResultBest.setCateType("best");
					dspCtgry2dResultBest.setNewBestType("best");
					dspCtgry2dResultBest.setCtgDepthCd(dspCtgryInfo.getDspCtgry().getCtgryDpthCd());
					dspCtgry2dResultList.add(dspCtgry2dResultBest);

				}

				// 하부 전시카테고리
				if (!dto.getHasSubCtgry().equals("N")) {
					/** 전시 카테고리 하부 목록 조회 */
					dspCtgryResultList = displayCategorysService.selectSubDisplayCategoryList(pk, dto);
					/** 전시카테고리 FILTER (SPECIAL) 적용 하부 목록 */
					String ctgryType = dto.getDspCtgryOutputType();
					if(ctgryType == null)
						ctgryType = "A_TP_LAG_MID_SML";
					String dspCtgrySpecial = dto.getSpecial();
//					if(dspCtgrySpecial != null){
//						if(dspCtgrySpecial.equals("OUTLET") || dspCtgrySpecial.equals("BRAND") || dspCtgrySpecial.equals("FILTER")){
//							dspCtgryResultList = displayCategorysService.selectSubDisplayCategorySpecialList(dspCtgryResultList, pk,dto, ctgryType);
//						}
//					}
				}
			}

			if (dspCtgryResultList != null) {
				String oneDepthDspYn = "";
				String oneDepthDspCtgNo = "";


				if ("brand".equals(dto.getDspCtgryType()) && "2".equals(dspCtgryResultList.get(0).getDspCtgry().getCtgryDpthCd())) {
					for (DspCtgryFoResult dspCtgryFoResult : dspCtgryResultList) {
						if (dspCtgryFoResult.getDspCtgry().getCtgryDpthCd().equals("2")) {
							if (!dspCtgryFoResult.getDspCtgry().getDspCtgryNm().contains("상품권")
									&& !dspCtgryFoResult.getDspCtgry().getDspCtgryNm().toLowerCase().contains("gift card")
									&& !dspCtgryFoResult.getDspCtgry().getDspCtgryNm().toLowerCase().contains("gift cards")
									&& !dspCtgryFoResult.getDspCtgry().getDspCtgryNm().toLowerCase().contains("giftcard")
									&& !dspCtgryFoResult.getDspCtgry().getDspCtgryNm().toLowerCase().contains("giftcards")
									&& !"Y".equals(dspCtgryFoResult.getDspCtgry().getCtgryDivLneYn())) {

								DspCtgryResultMbDTO dspCtgry2dResult2 = new DspCtgryResultMbDTO();
								dspCtgry2dResult2.setCtgNo(dspCtgryFoResult.getDspCtgry().getDspCtgryNo());
								dspCtgry2dResult2.setCtgNm(dspCtgryFoResult.getDspCtgry().getDspCtgryNm());
								dspCtgry2dResult2.setCtgDepthCd(dspCtgryFoResult.getDspCtgry().getCtgryDpthCd());
								dspCtgry2dResult2.setCateType(dto.getDspCtgryType());
								dspCtgry2dResult2.setBrndId(brandIds);
								dspCtgry2dResultList.add(dspCtgry2dResult2);
							}
						} else {
							if (dspCtgryFoResult.getDspCtgry().getCtgryDpthCd().equals("3")) {
								DspCtgryResultMbDTO dspCtgry2dResult2 = new DspCtgryResultMbDTO();
								dspCtgry2dResult2.setCtgNo(dspCtgryFoResult.getDspCtgry().getDspCtgryNo());
								dspCtgry2dResult2.setCtgNm(dspCtgryFoResult.getDspCtgry().getDspCtgryNm());
								dspCtgry2dResult2.setCtgDepthCd(dspCtgryFoResult.getDspCtgry().getCtgryDpthCd());
								dspCtgry2dResult2.setCateType(dto.getDspCtgryType());
								dspCtgry2dResult2.setBrndId(brandIds);
								dspCtgry2dResultList.add(dspCtgry2dResult2);
							}
						}
					}
				} else if ("brand".equals(dto.getDspCtgryType()) && "B_TP_MID_SML_DTL".equals(dspCtgryInfo.getDspCtgry().getExpsrLvlCd())) {
					// NEW 전시카테고리 생성
					DspCtgrySearchFoDTO specialSearchNewBest = new DspCtgrySearchFoDTO();
					DspCtgry dspCtgry = new DspCtgry();
					dspCtgry.setDspCtgryNo("DXM");
					specialSearchNewBest.setDspCtgry(dspCtgry);
					specialSearchNewBest.setHasAllCtgry("Y");
					specialSearchNewBest.setHasLocation("N");
					specialSearchNewBest.setHasGodList("N");
					specialSearchNewBest.setHasSubCtgry("Y");
					specialSearchNewBest.setUseNew("USED");
					specialSearchNewBest.setDspCtgryOutputType("A_TP_LAG_MID_SML_DTL");
					specialSearchNewBest.setDspCtgryTreeRootDspCtgryNo(dto.getDspCtgry().getDspCtgryNo());
					specialSearchNewBest.setSpcPrmTp(dto.getSpcPrmTp());
					specialSearchNewBest.setSttSectCd(null);
//					specialSearchNewBest.setSpecial("BRAND");
					specialSearchNewBest.setDspCtgryType("brand");
					specialSearchNewBest.setSearchConditionBrandIds(brandIds);
					List<DspCtgryResultMbDTO> dspCtgryResultNew = this.selectMobileDisplayCategoryList(pk, specialSearchNewBest);


					for (int i=0; i < dspCtgryResultNew.size(); i++) {
						if (i == 0) {
							DspCtgryResultMbDTO dspCtgrydResult = new DspCtgryResultMbDTO();
							dspCtgrydResult.setCtgNo("DXM_new");
							dspCtgrydResult.setCtgNm("NEW");
							dspCtgrydResult.setCtgDepthCd("2");
							dspCtgrydResult.setCateType("new");
							dspCtgrydResult.setBrndId(brandIds);
							dspCtgry2dResultList.add(dspCtgrydResult);
						} else {
							// 대카 제외
							if (!"1".equals(dspCtgryResultNew.get(i).getCtgDepthCd())) {
								DspCtgryResultMbDTO dspCtgrydResult = new DspCtgryResultMbDTO();
								dspCtgrydResult.setCtgNo(dspCtgryResultNew.get(i).getCtgNo());
								dspCtgrydResult.setCtgNm(dspCtgryResultNew.get(i).getCtgNm());
								dspCtgrydResult.setCtgDepthCd("3");
								dspCtgrydResult.setCateType("new");
								dspCtgrydResult.setBrndId(brandIds);
								dspCtgry2dResultList.add(dspCtgrydResult);
							}
						}
					}


					// BEST 전시카테고리 생성.
					specialSearchNewBest.setUseNew(null);
					specialSearchNewBest.setSttSectCd("RECENT_14_DAY");
					List<DspCtgryResultMbDTO> dspCtgryResultBest = this.selectMobileDisplayCategoryList(pk, specialSearchNewBest);

					for (int i=0; i < dspCtgryResultBest.size(); i++) {
						if (i == 0) {
							DspCtgryResultMbDTO dspCtgrydResult = new DspCtgryResultMbDTO();
							dspCtgrydResult.setCtgNo("DXM_best");
							dspCtgrydResult.setCtgNm("BEST");
							dspCtgrydResult.setCtgDepthCd("2");
							dspCtgrydResult.setCateType("best");
							dspCtgrydResult.setBrndId(brandIds);
							dspCtgry2dResultList.add(dspCtgrydResult);
						} else {
							// 대카 제외
							if (!"1".equals(dspCtgryResultBest.get(i).getCtgDepthCd())) {
								DspCtgryResultMbDTO dspCtgrydResult = new DspCtgryResultMbDTO();
								dspCtgrydResult.setCtgNo(dspCtgryResultBest.get(i).getCtgNo());
								dspCtgrydResult.setCtgNm(dspCtgryResultBest.get(i).getCtgNm());
								dspCtgrydResult.setCtgDepthCd("3");
								dspCtgrydResult.setCateType("best");
								dspCtgrydResult.setBrndId(brandIds);
								dspCtgry2dResultList.add(dspCtgrydResult);
							}
						}
					}


					for (DspCtgryFoResult dspCtgryFoResult : dspCtgryResultList) {
						if (dspCtgryFoResult.getDspCtgry().getCtgryDpthCd().equals("2") || dspCtgryFoResult.getDspCtgry().getCtgryDpthCd().equals("3")) {
							if (!dspCtgryFoResult.getDspCtgry().getDspCtgryNm().contains("상품권")
									&& !dspCtgryFoResult.getDspCtgry().getDspCtgryNm().contains("구분선")) {

								DspCtgryResultMbDTO dspCtgry2dResult2 = new DspCtgryResultMbDTO();
								dspCtgry2dResult2.setCtgNo(dspCtgryFoResult.getDspCtgry().getDspCtgryNo());
								dspCtgry2dResult2.setCtgNm(dspCtgryFoResult.getDspCtgry().getDspCtgryNm());
								dspCtgry2dResult2.setCtgDepthCd(dspCtgryFoResult.getDspCtgry().getCtgryDpthCd());
								dspCtgry2dResult2.setCateType(dto.getDspCtgryType());
								dspCtgry2dResult2.setBrndId(brandIds);
								dspCtgry2dResultList.add(dspCtgry2dResult2);
							}

						}
					}

				} else if ("brand".equals(dto.getDspCtgryType()) && "3".equals(dspCtgryResultList.get(0).getDspCtgry().getCtgryDpthCd())) {
					for (DspCtgryFoResult dspCtgryFoResult : dspCtgryResultList) {
						if (dspCtgryFoResult.getDspCtgry().getCtgryDpthCd().equals("3") || dspCtgryFoResult.getDspCtgry().getCtgryDpthCd().equals("4")) {
							if (!dspCtgryFoResult.getDspCtgry().getDspCtgryNm().contains("상품권")
									&& !dspCtgryFoResult.getDspCtgry().getDspCtgryNm().contains("구분선")) {

								DspCtgryResultMbDTO dspCtgry2dResult2 = new DspCtgryResultMbDTO();
								dspCtgry2dResult2.setCtgNo(dspCtgryFoResult.getDspCtgry().getDspCtgryNo());
								dspCtgry2dResult2.setCtgNm(dspCtgryFoResult.getDspCtgry().getDspCtgryNm());
								dspCtgry2dResult2.setCtgDepthCd(dspCtgryFoResult.getDspCtgry().getCtgryDpthCd());
								dspCtgry2dResult2.setCateType(dto.getDspCtgryType());
								dspCtgry2dResult2.setBrndId(brandIds);
								dspCtgry2dResultList.add(dspCtgry2dResult2);
							}
						}
					}


				} else {
					for (DspCtgryFoResult dspCtgryFoResult : dspCtgryResultList) {

						if (dto.getHasAllCtgry().equals("Y")) {
							if ("1".equals(dspCtgryFoResult.getDspCtgry().getCtgryDpthCd())) {
								oneDepthDspYn = dspCtgryFoResult.getDspCtgry().getDspYn();
								oneDepthDspCtgNo = dspCtgryFoResult.getDspCtgry().getDspCtgryNo();
							}

							if (("1".equals(dspCtgryFoResult.getDspCtgry().getCtgryDpthCd()) && "Y".equals(oneDepthDspYn))
									|| ("2".equals(dspCtgryFoResult.getDspCtgry().getCtgryDpthCd()) && "Y".equals(dspCtgryFoResult.getDspCtgry().getDspYn())
									&& "Y".equals(oneDepthDspYn) && dspCtgryFoResult.getDspCtgry().getUpperDspCtgryNo().equals(oneDepthDspCtgNo))) {
								if (!dspCtgryFoResult.getDspCtgry().getDspCtgryNm().contains("상품권")
										&& !dspCtgryFoResult.getDspCtgry().getDspCtgryNm().toLowerCase().contains("gift card")
										&& !dspCtgryFoResult.getDspCtgry().getDspCtgryNm().toLowerCase().contains("gift cards")
										&& !dspCtgryFoResult.getDspCtgry().getDspCtgryNm().toLowerCase().contains("giftcard")
										&& !dspCtgryFoResult.getDspCtgry().getDspCtgryNm().toLowerCase().contains("giftcards")
										&& !"Y".equals(dspCtgryFoResult.getDspCtgry().getCtgryDivLneYn())) {
									DspCtgryResultMbDTO dspCtgry2dResult2 = new DspCtgryResultMbDTO();
									dspCtgry2dResult2.setCtgNo(dspCtgryFoResult.getDspCtgry().getDspCtgryNo());
									dspCtgry2dResult2.setCtgNm(dspCtgryFoResult.getDspCtgry().getDspCtgryNm());
									dspCtgry2dResult2.setCtgDepthCd(dspCtgryFoResult.getDspCtgry().getCtgryDpthCd());
									dspCtgry2dResult2.setCateType(dto.getDspCtgryType());
									dspCtgry2dResult2.setBrndId(brandIds);
									dspCtgry2dResult2.setDspCtgryNm(dspCtgryInfo.getDspCtgry().getDspCtgryNm());
									dspCtgry2dResultList.add(dspCtgry2dResult2);

								}
							}
						} else {
							if (dspCtgryInfo.getDspCtgry().getCtgryDpthCd().equals("1")) {
								if (dspCtgryFoResult.getDspCtgry().getCtgryDpthCd().equals("2")) {
									if (!dspCtgryFoResult.getDspCtgry().getDspCtgryNm().contains("상품권")
											&& !dspCtgryFoResult.getDspCtgry().getDspCtgryNm().toLowerCase().contains("gift card")
											&& !dspCtgryFoResult.getDspCtgry().getDspCtgryNm().toLowerCase().contains("gift cards")
											&& !dspCtgryFoResult.getDspCtgry().getDspCtgryNm().toLowerCase().contains("giftcard")
											&& !dspCtgryFoResult.getDspCtgry().getDspCtgryNm().toLowerCase().contains("giftcards")
											&& !"Y".equals(dspCtgryFoResult.getDspCtgry().getCtgryDivLneYn())) {

										DspCtgryResultMbDTO dspCtgry2dResult2 = new DspCtgryResultMbDTO();
										dspCtgry2dResult2.setCtgNo(dspCtgryFoResult.getDspCtgry().getDspCtgryNo());
										dspCtgry2dResult2.setCtgNm(dspCtgryFoResult.getDspCtgry().getDspCtgryNm());
										dspCtgry2dResult2.setCtgDepthCd(dspCtgryFoResult.getDspCtgry().getCtgryDpthCd());
										dspCtgry2dResult2.setCateType(dto.getDspCtgryType());
										dspCtgry2dResultList.add(dspCtgry2dResult2);

									}
								}
							} else {
								if (dspCtgryFoResult.getDspCtgry().getCtgryDpthCd().equals("3")) {
									DspCtgryResultMbDTO dspCtgry2dResult2 = new DspCtgryResultMbDTO();
									dspCtgry2dResult2.setCtgNo(dspCtgryFoResult.getDspCtgry().getDspCtgryNo());
									dspCtgry2dResult2.setCtgNm(dspCtgryFoResult.getDspCtgry().getDspCtgryNm());
									dspCtgry2dResult2.setCtgDepthCd(dspCtgryFoResult.getDspCtgry().getCtgryDpthCd());
									dspCtgry2dResult2.setCateType(dto.getDspCtgryType());
									dspCtgry2dResultList.add(dspCtgry2dResult2);
								}
							}
						}
					}
				}
			}

		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}

		// 서브 카테고리 리스트에 New, Best 노출 위해 처리
		if (!"OUTLET".equals(dto.getSpecial()) && "Y".equals(dto.getMbCtgryListYn()) && StringService.isEmpty(dto.getUseNew()) && StringService.isEmpty(dto.getSttSectCd())) {
			if (dspCtgry2dResultList != null && dspCtgry2dResultList.size() > 0) {
				if (!"2".equals(dspCtgry2dResultList.get(0).getCtgDepthCd()) && !"3".equals(dspCtgry2dResultList.get(0).getCtgDepthCd())) {
					// New 카테고리
					DspCtgryResultMbDTO dspCtgry2dResultNew = new DspCtgryResultMbDTO();
					dspCtgry2dResultNew.setCtgNo(dspCtgry2dResultList.get(0).getCtgNo() + "_new");
					dspCtgry2dResultNew.setCtgNm("New");
					dspCtgry2dResultNew.setCateType("new");
					dspCtgry2dResultNew.setNewBestType("new");
					dspCtgry2dResultNew.setCtgDepthCd(dspCtgry2dResultList.get(0).getCtgDepthCd());
					dspCtgry2dResultList.add(1, dspCtgry2dResultNew);

					// Best 카테고리
					DspCtgryResultMbDTO dspCtgry2dResultBest = new DspCtgryResultMbDTO();
					dspCtgry2dResultBest.setCtgNo(dspCtgry2dResultList.get(0).getCtgNo() + "_best");
					dspCtgry2dResultBest.setCtgNm("Best");
					dspCtgry2dResultBest.setCateType("best");
					dspCtgry2dResultBest.setNewBestType("best");
					dspCtgry2dResultBest.setCtgDepthCd(dspCtgry2dResultList.get(0).getCtgDepthCd());
					dspCtgry2dResultList.add(2, dspCtgry2dResultBest);
				}
			}
		}


		return dspCtgry2dResultList;
	}

	public GoodsListMbResultDTO selectMobileGoodsList(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception {

		// 임직원 검사
		String empYn = "N";
		Mbr mbr = null;
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();

			if (mbrAtrb.equals("plgrim") || mbrAtrb.equals("PLGRIM_FSHN") || mbrAtrb.equals("GRPCO")){
				empYn = "Y";
			}
		}
		searchDTO.setEmpYn(empYn);


		DspCtgryResultFoDTO dspCtgry2dResult = new DspCtgryResultFoDTO();

		int totCnt = displayCategorysService.selectDisplayCategoryConnGoodTotCount(pk, searchDTO);
		/** 전시 상품 리스트 */
		List<DspCtgryGodFoResult> godList = null;
		if(searchDTO.getSaleIdxTp()!=null&&!"".equals(searchDTO.getSaleIdxTp())){
			godList = displayCategorysService.selectDisplayCategoryConnGoodMobileNewBestList(pk,searchDTO);
		}else{
			godList = displayCategorysService.selectDisplayCategoryConnGoodMobileAsList(pk, searchDTO);
		}


		List<String> godNos = new ArrayList<String>();
		for (DspCtgryGodFoResult dspCtgryGodFoResult : godList) {
			godNos.add(dspCtgryGodFoResult.getGod().getGodNo());
		}

		List<GodImg> godImgList = null;
		if (godNos != null && godNos.size() > 0) {
			godImgList = displayCategorysService.selectMbGodImgList(godNos);
		}


		GoodsListMbResultDTO result = new GoodsListMbResultDTO();
		List<GoodsListMbDTO> dtoList = new ArrayList<GoodsListMbDTO>();

		DecimalFormat df = new DecimalFormat("#,###");			// 원화

		// 국내가 아니면 USD 로 세팅
		if(!"KOR".equals(pk.getLang())) {
			df = new DecimalFormat("#,###.##");	// USD
		}

		for (DspCtgryGodFoResult dspCtgryGodFoResult : godList) {
			GoodsListMbDTO dto = new GoodsListMbDTO();
			dto.setGodNo(dspCtgryGodFoResult.getGod().getGodNo());
			dto.setTitle(dspCtgryGodFoResult.getGod().getGodNm());
			dto.setCouponRate(dspCtgryGodFoResult.getDspGodPrc().getCpnDcRt());
			dto.setDiscountRate(dspCtgryGodFoResult.getDspGodPrc().getGodDcRt());
			dto.setPrice(df.format(dspCtgryGodFoResult.getDspGodPrc().getLastSalePrc()));
			// 정소가 추가 2015.09.18
			dto.setPrePrice(df.format(dspCtgryGodFoResult.getDspGodPrc().getRtlPrc()));
			dto.setGodSaleSectCd(dspCtgryGodFoResult.getGod().getGodSaleSectCd());

			dto.setColorStyleCds(dspCtgryGodFoResult.getColorStyleCds());

			if (godImgList != null) {
				for(int i=0; i < godImgList.size(); i++) {
					if (dspCtgryGodFoResult.getGod().getGodNo().equals(godImgList.get(i).getGodNo())) {
						dto.setImage(godImgList.get(i).getImgUrl());
						break;
					}
				}
			}
			dto.setBrndNm(dspCtgryGodFoResult.getSysBrnd().getBrndNm());
			dto.setTagNm(dspCtgryGodFoResult.getGod().getTagNm());

			int iconCnt = 0;

			// 상품 아이콘 최대 3개 설정 (시즌 -> Seasonoff -> 할인율 -> New -> Best 순)
			if (!StringService.isEmpty(dspCtgryGodFoResult.getMnfcturDate()) && dspCtgryGodFoResult.getMnfcturDate().length() > 4) {
				Calendar cal = Calendar.getInstance();
				String year  = String.valueOf(cal.get(Calendar.YEAR)).substring(3, 4);
				String mnfcturYear =  dspCtgryGodFoResult.getGod().getErpGodNo().substring(2, 3);

				if (year.equals(mnfcturYear)) {
					if (!StringService.isEmpty(dspCtgryGodFoResult.getGod().getSesonGrpCd())) {
						String twoDigitYear =  String.valueOf(cal.get(Calendar.YEAR)).substring(2, 4);
						String seasonGroupCd = dspCtgryGodFoResult.getGod().getSesonGrpCd();

						//	2015.10.01	세트 상품 경우 시즌 아이콘 안보이도록 WEB기준으로 변경
						if( dspCtgryGodFoResult.getGod().getPartmalSectCd().equals("MCOM") && ( dspCtgryGodFoResult.getGod().getGodTpCd().equals("GNRL_GOD") || (dspCtgryGodFoResult.getGod().getGodTpCd().equals("CNVRS_GFT")) )){
							dto.setSeasonInfo(twoDigitYear+seasonGroupCd);
						}
						iconCnt++;
					}
				}
			}

			if (dspCtgryGodFoResult.getCpnDcRt() != null || dspCtgryGodFoResult.getGodDcRt() != null) {
				iconCnt++;
			}

			// 텍스트 아이콘 존재시 SEASONOFF, NEW, BEST 제외
			if (!StringService.isEmpty(dspCtgryGodFoResult.getGod().getTextIconCont())) {
				dto.setTextIcon(dspCtgryGodFoResult.getGod().getTextIconCont());
			} else {
				if (dspCtgryGodFoResult.getGodIconList() != null) {
					for(int j=0; j < dspCtgryGodFoResult.getGodIconList().size(); j++) {
						// seasonoff
						if (dspCtgryGodFoResult.getGodIconList().get(j).getGodIcon().getIconTurn() == 1) {
							dto.setSeasonoff(true);
							iconCnt++;
							// new
						} else if (dspCtgryGodFoResult.getGodIconList().get(j).getGodIcon().getIconTurn() == 2) {
							if (iconCnt < 3) {
								dto.setBrandNew(true);
								iconCnt++;
							}
							// best
						} else if (dspCtgryGodFoResult.getGodIconList().get(j).getGodIcon().getIconTurn() == 3) {
							if (iconCnt < 3) {
								dto.setBest(true);
								iconCnt++;
							}
						}
					}
				}
			}


			dtoList.add(dto);
		}

		result.setProducts(dtoList);
		result.setProductTotCnt(totCnt);


		return result;
	}



	public int selectGoodsCount(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception {
		return displayCategorysService.selectDisplayCategoryConnGoodTotCount(pk, searchDTO);
	}

	/**
	 *
	 *  카테고리 상품의 컬러, 사이즈, 소재, 스타일을 위해 godNo 조회
	 * @param pk
	 * @param searchDTO
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectGoodsCSMS(SystemPK pk, DspCtgrySearchFoDTO searchDTO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap = displayCategorysService.getMbDspCtgrygodNoList(pk, searchDTO);

		if (!"brand".equals(searchDTO.getDspCtgryType())) {
			if (resultMap != null && resultMap.size() > 0) {
				List<String> brndIdList = (List)resultMap.get("brndIdList");
				List<BrandResultDTO> brndInfoList = vendorBrndService.selectSysBrndNm(brndIdList);

				List<BrandResultDTO> sortedBrandList = getSortedBrandList(brndInfoList);
				resultMap.put("brndInfoList", sortedBrandList);

			}
		}

		return resultMap;
	}

	/**
	 * 알파벳 순으로 정렬하되 자사 브랜드를 앞으로 정렬한 브랜드 리스트 조회
	 *
	 * @param brndInfoList
	 * @return List<SysBrnd>
	 */
	private List<BrandResultDTO> getSortedBrandList(List<BrandResultDTO> brndInfoList) {

		getSortedListByAlphaNumeric(brndInfoList);

		List<BrandResultDTO> sortedBrandList = makeOwnBrandList(brndInfoList);

		return sortedBrandList;

	}

	/**
	 * 자사 브랜드를 앞으로 정렬
	 * @param brndInfoList
	 * @return
	 */
	private List<BrandResultDTO> makeOwnBrandList(List<BrandResultDTO> brndInfoList) {

		List<BrandResultDTO> ownBrandList = new ArrayList<BrandResultDTO>();
		List<BrandResultDTO> sortedBrandList = new ArrayList<BrandResultDTO>();

		// 자사 브랜드 순서가 프로퍼티에 정의되어 있음.
		// ", " 으로 split 하여 배열로 사용함.
		if (StringUtils.isNotEmpty(getConfigService().getProperty(SysInfoEnum.OwnBrandEnum.OwnBrandId.toString()))
				&& getConfigService().getProperty(SysInfoEnum.OwnBrandEnum.OwnBrandId.toString()).indexOf(",") > 0) {

			String[] ownBrandIds = getConfigService().getProperty(SysInfoEnum.OwnBrandEnum.OwnBrandId.toString()).replaceAll(" ", "").split(",");

			// 자사 브랜드와 그 외 브랜드를 분리한다.
			for (int i = 0; i < ownBrandIds.length; i++) {
				for (int j = 0; j < brndInfoList.size(); j++) {

					if (brndInfoList.get(j) != null) {
						BrandResultDTO dto = brndInfoList.get(j);

						if (StringUtils.equals(ownBrandIds[i].toLowerCase(), dto.getBrndId().toLowerCase())) {
							dto.setOwnBrndYN(SysInfoEnum.OwnBrandEnum.OwnBrandFlag_Y.toString());
							ownBrandList.add(dto);

							brndInfoList.set(j, null);

						} else {
							dto.setOwnBrndYN(SysInfoEnum.OwnBrandEnum.OwnBrandFlag_N.toString());

							brndInfoList.set(j, dto);
						}
					}
				}
			}

			// 자사 브랜드를 앞으로 정렬한다.
			sortedBrandList.addAll(ownBrandList);
			for (BrandResultDTO brandInfo : brndInfoList) {
				if (brandInfo != null) {
					sortedBrandList.add(brandInfo);
				}
			}

			int count = 0;
			for (int i = 0; i < sortedBrandList.size(); i++) {
				if (StringUtils.equals(sortedBrandList.get(i).getOwnBrndYN(), SysInfoEnum.OwnBrandEnum.OwnBrandFlag_Y.toString())) {
					count++;
				}
			}


		}

		return sortedBrandList;

	}

	/**
	 * 0-9 / a-z정렬
	 *
	 * @param brndInfoList
	 */
	private void getSortedListByAlphaNumeric(List<BrandResultDTO> brndInfoList) {

		Collections.sort(brndInfoList, new Comparator<SysBrnd>() {

			@Override
			public int compare(SysBrnd o1, SysBrnd o2) {
				return o1.getBrndNm().toLowerCase().compareTo(o2.getBrndNm().toLowerCase());
			}

		});
	}

	@Override
	public DspCtgryResultFoDTO selectDspCtgryViewForMB(SystemPK pk,
													   DspCtgrySearchFoDTO dto) throws Exception {

		// 임직원 검사
		String empYn = "N";
		Mbr mbr = null;
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();

			if(mbrAtrb.equals("plgrim") ||
					mbrAtrb.equals("PLGRIM_FSHN") ||
					mbrAtrb.equals("GRPCO")){
				empYn = "Y";
			}
		}
		dto.setEmpYn(empYn);

		DspCtgryResultFoDTO dspCtgry2dResult = new DspCtgryResultFoDTO();
		try{
			/** 전시카테고리2D 기본정보 조회 */
			if(!dto.getHasCtgryInfo().equals("N")){
				DspCtgryFoResult dspCtgryInfo = displayCategorysService.selectDisplayCategoryInfo(pk, dto);
				dspCtgry2dResult.setDspCtgryResult(dspCtgryInfo);
			}

			/** 전시 카테고리 하부 목록 조회 */
//		if(dspCtgryInfo.getDspCtgry().getLeafCtgryYn().equals("N")){

			// 하부 전시카테고리
			if(!dto.getHasSubCtgry().equals("N")){
				List<DspCtgryFoResult> dspCtgryResultList = displayCategorysService.selectSubDisplayCategoryList(pk, dto);
				/** 전시카테고리 FILTER (SPECIAL) 적용 하부 목록 */
				String ctgryType = dto.getDspCtgryOutputType();
				if(ctgryType == null)
					ctgryType = "A_TP_LAG_MID_SML";
				String dspCtgrySpecial = dto.getSpecial();
				if(dspCtgrySpecial != null){
					if(dspCtgrySpecial.equals("OUTLET") || dspCtgrySpecial.equals("BRAND") || dspCtgrySpecial.equals("FILTER")){
						dspCtgryResultList = displayCategorysService.selectSubDisplayCategorySpecialList(dspCtgryResultList, pk,dto, ctgryType);
					}
				}
				dspCtgry2dResult.setDspCtgryResultList(dspCtgryResultList);
			}

			// 로케이션 바 정보
			if(!dto.getHasLocation().equals("N")){
				/** 전시카테고리 로케이션 바 조회 */
				List<DspCtgryFoResult> locations = new ArrayList<DspCtgryFoResult>();
				List<DspCtgryFoResult> dspCtgryResultList = dspCtgry2dResult.getDspCtgryResultList();
				if(dspCtgryResultList != null && dspCtgryResultList.size() > 0 ){
					String[] upperCtgryNos = dto.getUpperCategoryNos();
					if(upperCtgryNos != null){
						for(String ctgryNo:upperCtgryNos){
							for(DspCtgryFoResult dspCtgryResult:dspCtgryResultList){
								String dcNo = dspCtgryResult.getDspCtgry().getDspCtgryNo();
								if(dcNo.equals(ctgryNo)){
									locations.add(dspCtgryResult);
									break;
								}
							}
						}
					}
				}
				else{
					locations = displayCategorysService.selectLocationDsplatyCategoryList(pk, dto);
				}
//				List<DspCtgryFoResult> locations = displayCategorysService.selectLocationDsplatyCategoryList(pk, dto);
				dspCtgry2dResult.setDspCtgryLocationList(locations);
			}
//		}
			// 전시 카테고리 연결 상품목록
			String hasGodList = dto.getHasGodList();
			// 대카테고리 메인이 아닌 경우에 수행한다.
			if(!hasGodList.equals("N")){

				// 상품목록 데이터베이스 정렬값을 적용한다.
				if(dto.getSortColumn() != null && dto.getSortColumn().length() > 0){

				}else{
					dto.setSortColumn("NEW_GOD");
				}

				int totCnt = displayCategorysService.selectDisplayCategoryConnGoodTotCount(pk, dto);
				/** 전시 상품 리스트 */
				List<DspCtgryGodFoResult> godList = displayCategorysService.selectDisplayCategoryConnGoodMobileAsList(pk, dto);

				dspCtgry2dResult.setDspCtgryGodTotCount(totCnt);
				dspCtgry2dResult.setDspCtgryGodList(godList);
			}
		}catch(Exception e){
			log.error(e.getMessage(), e);
		}
		return dspCtgry2dResult;
	}

	@Override
	public List<DspCnrConttStrndFoResult> selectDspCnrConttStndList(SystemPK pk)
			throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		map.put("lang", pk.getLang());
		return displayCornerService.selectDspCnrConttStndList(map);
	}

	@Override
	public List<DspCnrConttStrndFoResult> selectBeanpoleDspCnrConttStndList(SystemPK pk)
			throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		map.put("lang", pk.getLang());
		return displayCornerService.selectBeanpoleDspCnrConttStndList(map);
	}

	@Override
	public DspStrendResultFoDTO selectStrendDetailForMB(String strndTp, String prcSectCd,Integer sTrndSn, SystemPK pk)
			throws Exception {
		DspStrendResultFoDTO resultDTO = new DspStrendResultFoDTO();
		DspStrendSearchFoDTO searchDTO = new DspStrendSearchFoDTO();

		//회원정보세션
		Mbr mbr = null;
		String empYn = "N";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();
			if ("plgrim".equals(mbrAtrb) || "PLGRIM_FSHN".equals(mbrAtrb) || "GRPCO".equals(mbrAtrb)) {
				empYn = "Y";
			}

		} else {
			mbr = new Mbr();
			mbr.setMbrTpCd("NMBR");
		}

		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		searchDTO.setStrndSn(sTrndSn);
		searchDTO.setStrndTpCd(strndTp);

		List<DspStrendFoResult> corner = displayStrendService.selectStrendCorner(searchDTO);

		if(!corner.isEmpty()){
			searchDTO.setBrndId(corner.get(0).getDspStrnd().getBrndId());
		}

		// 다음 룩북 정보 조회 제거
		/*int nextStrnd = 0;


		if(displayStrendService.selectNextStrend(searchDTO) > 0 ){
			nextStrnd = displayStrendService.selectNextStrend(searchDTO);
		}*/

		ArrayList<DspCnrFoResult> cornerList = new ArrayList<DspCnrFoResult>();

		if(corner != null && corner.size() > 0){
			resultDTO.setStrendDetail(corner.get(0));
			resultDTO.setTrndNm(corner.get(0).getDspStrnd().getStrndNm());
		}
		resultDTO.setCornerList(cornerList);
		//resultDTO.setNextStrnd(nextStrnd);

		return resultDTO;
	}

	@Override
	public DspStrendResultFoDTO selectStrendForMB(
			DspStrendSearchFoDTO searchDTO, PageParam pageParam,
			SystemPK pk) throws Exception{
		DspStrendResultFoDTO resultDTO = new DspStrendResultFoDTO();
		//회원정보세션
		Mbr mbr = null;
		String empYn = "N";
		if (ContextService.hasRole()) {
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();
			if ("plgrim".equals(mbrAtrb) || "PLGRIM_FSHN".equals(mbrAtrb) || "GRPCO".equals(mbrAtrb)) {
				empYn = "Y";
			}

		} else {
			mbr = new Mbr();
			mbr.setMbrTpCd("NMBR");
		}

		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());

		DspStrendSearchFoDTO brSearchDTO = new DspStrendSearchFoDTO();
		brSearchDTO.setMallId(pk.getMall());
		brSearchDTO.setDevice(pk.getDevice());
		brSearchDTO.setLang(pk.getLang());
		brSearchDTO.setBrndNotInYn("Y");
		brSearchDTO.setUpBrndId(DisplayEnum.BEKER_BRND_ID.toString());

		List<DspStrendFoResult> brndList = displayStrendService.selectStrendBrand(brSearchDTO);

		searchDTO.setAplMbrTp(mbr.getMbrTpCd());
		searchDTO.setAplMbrAtrb(mbr.getMbrAtrbCd());
		searchDTO.setGrpcoId(mbr.getPsitnGrpcoId());
		searchDTO.setEmpYn(empYn);
		if(searchDTO.getBrndId() != null && searchDTO.getBrndId().equals(DisplayEnum.BEKER_BRND_ID.toString())) {
			searchDTO.setUpBrndId(searchDTO.getBrndId());
			searchDTO.setBrndId(null);
		}
		else {
			searchDTO.setUpBrndId(null);
		}
		List<DspStrendFoResult> strendList = displayStrendService.selectStrendList(pk,searchDTO, pageParam);

		long total = displayStrendService.selectStrendCount(searchDTO);

		resultDTO.setTotal((int)total);
		resultDTO.setStrendList(strendList);
		resultDTO.setBrndList(brndList);

		return resultDTO;
	}

	@Override
	public GnbResultFoDTO selectGnbViewForMB(SystemPK pk,
											 DspCtgrySearchFoDTO dto) throws Exception {

		// 임직원 검사
		String empYn = "N";
		Mbr mbr = null;
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();

			if(mbrAtrb.equals("plgrim") ||
					mbrAtrb.equals("PLGRIM_FSHN") ||
					mbrAtrb.equals("GRPCO")){
				empYn = "Y";
			}
		}
		dto.setEmpYn(empYn);

		GnbResultFoDTO gnbDTO = new GnbResultFoDTO();
		dto.setSpecial("FILTER");
		List<DspCtgryFoResult> dspCtgryList = displayCategorysService.selectSubDisplayCategoryList(pk, dto);
		dto.setSpecial("OUTLET");
		List<DspCtgryFoResult> outletCtgryList = displayCategorysService.selectSubDisplayCategoryList(pk, dto);
		outletCtgryList = displayCategorysService.selectSubDisplayCategorySpecialList(outletCtgryList, pk,dto,"D1");
		dto.setSpecial("");
		gnbDTO.setDspCtgryList(dspCtgryList);
		gnbDTO.setOutLetCtgryList(outletCtgryList);

		return gnbDTO;
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	public void setAutoDspCnrSetGodList (String screenNo, DspCnrSetFoResult dspCnrSetResult, Long godCnt, String type, Map<String,Object> map) throws Exception{
//		if(screenNo.equals("MALL_MAIN") || screenNo.equals("CATEGORY_MAIN")){
		Map<String,List<DspCnrConttFoResult>> dspCnrConttMap = dspCnrSetResult.getDspCnrConttMap();
		if(dspCnrConttMap == null){
			dspCnrConttMap = new HashMap<String,List<DspCnrConttFoResult>>();
			dspCnrSetResult.setDspCnrConttMap(dspCnrConttMap);
		}

		String setType = "GOD";
		if(screenNo.equals("MALL_MAIN") && type.equals("BEST")){
			setType = "STK_GOD";
		}

		List<DspCnrConttFoResult> conttGodList = dspCnrConttMap.get(setType);
		if(conttGodList == null){
			conttGodList = new ArrayList<DspCnrConttFoResult>();
			dspCnrConttMap.put(setType, conttGodList);
		}
		DspCnrSet set = dspCnrSetResult.getDspCnrSet();
		int listSize = conttGodList.size();
		int intGodCnt = godCnt.intValue();
		if(listSize < intGodCnt){
			HashMap<String,Object> map2 = new HashMap<String,Object>();
			map2.put("dspCtgryNm", set.getSetNm());
			map2.put("lastIndex", intGodCnt * 2);
			map2.put("brandIds", map.get("brandIds"));
			map2.put("lang", map.get("lang"));
			List<String> godNos = null;
			if(type.equals("BEST")){
				godNos = displayCornerService.selectTopSellerGodNoList1(map2);
			}
			else if(type.equals("NEW")){
				godNos = displayCornerService.selectNewArrivalGodNoList1(map2);
			}
			else if(type.equals("BRND-BEST-8S")){
				//당시즌 계산
				Calendar now = Calendar.getInstance();
				int year = now.get(Calendar.YEAR);
				int month = now.get(Calendar.MONTH) + 1;
				String[] sesonList = null;
				if(month >= 1 && month <=2){
					sesonList = new String[7];
					year = year - 1;
					String strYear = String.valueOf(year);
					String strSesion = strYear.substring(2);
					sesonList[0] = strSesion + "08";
					sesonList[1] = strSesion + "09";
					sesonList[2] = strSesion + "10";
					sesonList[3] = strSesion + "11";
					sesonList[4] = strSesion + "12";
					year = year + 1;
					strYear = String.valueOf(year);
					strSesion = strYear.substring(2);
					sesonList[5] = strSesion + "01";
					sesonList[6] = strSesion + "02";
				}else if(month >= 3 && month <= 7){
					sesonList = new String[5];
					String strYear = String.valueOf(year);
					String strSesion = strYear.substring(2);
					sesonList[0] = strSesion + "03";
					sesonList[1] = strSesion + "04";
					sesonList[2] = strSesion + "05";
					sesonList[3] = strSesion + "06";
					sesonList[4] = strSesion + "07";
				}else if(month >= 8 && month <= 12){
					sesonList = new String[7];
					String strYear = String.valueOf(year);
					String strSesion = strYear.substring(2);
					sesonList[0] = strSesion + "08";
					sesonList[1] = strSesion + "09";
					sesonList[2] = strSesion + "10";
					sesonList[3] = strSesion + "11";
					sesonList[4] = strSesion + "12";
					year = year + 1;
					strYear = String.valueOf(year);
					strSesion = strYear.substring(2);
					sesonList[5] = strSesion + "01";
					sesonList[6] = strSesion + "02";
				}
				map2.put("sesonList", sesonList);
				godNos = displayCornerService.selectBrandTopSellerGodNoList8s(map2);
			}
			else if(type.equals("BRND-NEW-8S")){
				godNos = displayCornerService.selectBrandNewArrivalGodNoList8s(map2);
			}
			else if(type.equals("BRND-BEST-OTHERS")){
				godNos = displayCornerService.selectBrandTopSellerGodNoListOthers(map2);
			}
			else if(type.equals("BRND-NEW-OTHERS")){
				godNos = displayCornerService.selectBrandNewArrivalGodNoListOthers(map2);
				if(godNos == null || godNos.size() < intGodCnt ){
					godNos = displayCornerService.selectBrandNewArrivalGodNoList8s(map2);
				}
			}
			if(godNos != null && godNos.size() > 0){
				List<String> godNoList = new ArrayList<String>();
				for(String gNo:godNos){
					boolean isEquals = false;
					for(int i = 0; i < conttGodList.size(); i ++){
						DspCnrConttFoResult cnrResult = conttGodList.get(i);
						if(cnrResult != null){
							DspCnrContt ct = cnrResult.getDspCnrContt();
							if(ct != null){
								if(gNo.equals(ct.getGodNo())){
									isEquals = true;
									break;
								}
							}

						}
					}
					if(!isEquals)
						godNoList.add(gNo);
				}
				if(godNoList.size() > 0){
					map.put("godNoList", godNoList);
					List<DspCnrConttGodFoResult> list = displayCornerService.selectDspCnrConttGod2(map);
					if(list != null && list.size() > 0){
						for(DspCnrConttGodFoResult gResult:list){
							DspCnrConttFoResult dccf = new DspCnrConttFoResult();
							dccf.setDspCnrGod(gResult);
							conttGodList.add(dccf);
						}
					}
				}
			}
		}

//		}
	}

	@Override
	public DisplayPageResultDTO selectDisplayPageViewForMB(SystemPK pk,
														   List<DspCnrFoResult> dspCnrResultList, String serialNumber,
														   Long tmplatSn, String parentType, String prcSectCd,
														   String spcPrmTp, String screenNo, String[] brandIds) throws Exception {
		DisplayPageResultDTO result = new DisplayPageResultDTO();
		// 해당페이지에서 사용하게될 전시코너목록을 받아서 설정한다.
		result.setDspCnrResultList(dspCnrResultList);

		// 임직원 검사
		String empYn = "N";
		Mbr mbr = null;
		if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			mbr = userDetail.getMbr();
			String mbrAtrb = mbr.getMbrAtrbCd();

			if(mbrAtrb.equals("plgrim") ||
					mbrAtrb.equals("PLGRIM_FSHN") ||
					mbrAtrb.equals("GRPCO")){
				empYn = "Y";
			}
		}else{
			mbr = new Mbr();
			mbr.setMbrTpCd("NMBR");
		}

		// 검색조건구성
		Map<String,Object> map = new HashMap<String,Object>();
		//parentType  ('THEMA_PGE', 'DSP_CTGRY','S_TRND', 'EVT','PROMT')
		map.put("parentType", parentType);
		if(parentType.equals("THEMA_PGE") || parentType.equals("DSP_CTGRY") || parentType.equals("EVT")){
			map.put("parentStringCd", serialNumber);
		}else{
			int cd = Integer.parseInt(serialNumber);
			map.put("parentIntCd", new Integer(cd));
		}
		map.put("tmplatSn", tmplatSn);
		map.put("lang", pk.getLang());
		map.put("device", pk.getDevice());
		map.put("aplMbrTp", mbr.getMbrTpCd());
		map.put("aplMbrAtrb", mbr.getMbrAtrbCd());
		map.put("grpcoId", mbr.getPsitnGrpcoId());
		map.put("prcSectCd", prcSectCd);
		map.put("spcPrmTp", spcPrmTp);
		map.put("empYn", empYn);
		map.put("mallId", pk.getMall());
		map.put("brandIds", brandIds);
		// 전시컨텐츠 말기
		displayCornerService.selectDspCnrConttList(dspCnrResultList, map);

		// 자동상품채우기
		List<Long> cnrNoList = new ArrayList<Long>();
		List<Long> godCntList = new ArrayList<Long>();
		List<String> typeList = new ArrayList<String>();
		// 개별SCREEN_NO에 지정되는 설정값
		if(screenNo.equals("MALL_MAIN")){
			DspCnrFoResult cnrFoResult1 = dspCnrResultList.get(2);
			DspCnrFoResult cnrFoResult2 = dspCnrResultList.get(4);
			cnrNoList.add(cnrFoResult1.getDspCnr().getCnrSn());
			cnrNoList.add(cnrFoResult2.getDspCnr().getCnrSn());
			godCntList.add(new Long(10));
			godCntList.add(new Long(5));
			typeList.add("BEST");
			typeList.add("NEW");
		}else if(screenNo.equals("CATEGORY_MAIN")){
			DspCnrFoResult cnrFoResult1 = dspCnrResultList.get(2);
			DspCnrFoResult cnrFoResult2 = dspCnrResultList.get(4);
			cnrNoList.add(cnrFoResult1.getDspCnr().getCnrSn());
			cnrNoList.add(cnrFoResult2.getDspCnr().getCnrSn());
			godCntList.add(new Long(10));
			godCntList.add(new Long(5));
			typeList.add("BEST");
			typeList.add("NEW");
		}else if(screenNo.equals("BRANDSHOP_A")){
			// CNR2:WHAT'S NEW (5)
			// CNR3:TOPSELLER (10)
			DspCnrFoResult cnrFoResult1 = dspCnrResultList.get(1);
			DspCnrFoResult cnrFoResult2 = dspCnrResultList.get(2);
			cnrNoList.add(cnrFoResult1.getDspCnr().getCnrSn());
			cnrNoList.add(cnrFoResult2.getDspCnr().getCnrSn());
			godCntList.add(new Long(5));
			godCntList.add(new Long(10));
			typeList.add("BRND-NEW-OTHERS");
			typeList.add("BRND-BEST-OTHERS");
		}else if(screenNo.equals("BRANDSHOP_B")){
			// CNR2:WHAT'S NEW (5)
			// CNR4:TOPSELLER (5)
			DspCnrFoResult cnrFoResult1 = dspCnrResultList.get(1);
			DspCnrFoResult cnrFoResult2 = dspCnrResultList.get(3);
			cnrNoList.add(cnrFoResult1.getDspCnr().getCnrSn());
			cnrNoList.add(cnrFoResult2.getDspCnr().getCnrSn());
			godCntList.add(new Long(5));
			godCntList.add(new Long(5));
			typeList.add("BRND-NEW-OTHERS");
			typeList.add("BRND-BEST-OTHERS");
		}else if(screenNo.equals("BRANDSHOP_C")){
			// CNR2:WHAT'S NEW(5)
			// CNR3:TOPSELLER (5)
			DspCnrFoResult cnrFoResult1 = dspCnrResultList.get(1);
			DspCnrFoResult cnrFoResult2 = dspCnrResultList.get(2);
			cnrNoList.add(cnrFoResult1.getDspCnr().getCnrSn());
			cnrNoList.add(cnrFoResult2.getDspCnr().getCnrSn());
			godCntList.add(new Long(5));
			godCntList.add(new Long(5));
			typeList.add("BRND-NEW-8S");
			typeList.add("BRND-BEST-8S");

		}else if(screenNo.equals("BRANDSHOP_D")){
			// CNR3:TOPSELLER (10)
			// CNR4:WHAT'S NEW (10)
			DspCnrFoResult cnrFoResult1 = dspCnrResultList.get(2);
			DspCnrFoResult cnrFoResult2 = dspCnrResultList.get(3);
			cnrNoList.add(cnrFoResult1.getDspCnr().getCnrSn());
			cnrNoList.add(cnrFoResult2.getDspCnr().getCnrSn());
			godCntList.add(new Long(10));
			godCntList.add(new Long(10));
			typeList.add("BRND-BEST-OTHERS");
			typeList.add("BRND-NEW-OTHERS");
		}else if(screenNo.equals("BRANDSHOP_E")){
			// CNR2:TOPSELLER (5)
			// CNR3:WHAT'S NEW (5)
			DspCnrFoResult cnrFoResult1 = dspCnrResultList.get(1);
			DspCnrFoResult cnrFoResult2 = dspCnrResultList.get(2);
			cnrNoList.add(cnrFoResult1.getDspCnr().getCnrSn());
			cnrNoList.add(cnrFoResult2.getDspCnr().getCnrSn());
			godCntList.add(new Long(5));
			godCntList.add(new Long(5));
			typeList.add("BRND-BEST-OTHERS");
			typeList.add("BRND-NEW-OTHERS");
		}
		//해당페이지의 자동로직코너의 코너세트정보를 구한다.
		HashMap<String,Object> m = new HashMap<String,Object>();
		m.put("tmplatSn", tmplatSn);
		m.put("dspCtgryNo", serialNumber);
		m.put("cnrNoList", cnrNoList);
		List<DspCnrFoResult> dcResultList = null;
		if(cnrNoList.size() > 0) {
			dcResultList = displayCornerService.selectDspCnrSetByCnr(m);
		}
		if(dcResultList != null) {
			// 실제데이터가 있는 코너의 코너세트정보와 비교한다.
			for(int i = 0; i < dcResultList.size(); i ++){
				//전시코너
				DspCnrFoResult dspCnr = dcResultList.get(i);
				DspCnr cnrSrc = dspCnr.getDspCnr();
				//위전시코너-코너세트에 보여줄 상품 개 수
				Long godCnt = godCntList.get(i);
				//위전시코너-타입(NEW/BEST 등)
				String type = typeList.get(i);
				//BO에서 초기에 설정된 코너세트정보
				List<DspCnrSetFoResult> setSrcList = dspCnr.getDspCnrSetList();
				//실제로 운용되는 코너세트정보
				List<DspCnrSetFoResult> setTgtList = null;
				for(DspCnrFoResult dcfResult:dspCnrResultList){
					DspCnr cnrTgt = dcfResult.getDspCnr();
					if(cnrSrc.getCnrSn().equals(cnrTgt.getCnrSn())){
						setTgtList = dcfResult.getDspCnrSetList();
						if(setTgtList == null){
							setTgtList = new ArrayList<DspCnrSetFoResult>();
							dcfResult.setDspCnrSetList(setTgtList);
						}
						break;
					}
				}
				for(DspCnrSetFoResult setSrc:setSrcList){
					DspCnrSetFoResult setResult = null;
					for(DspCnrSetFoResult setTgt:setTgtList){
						if(setSrc.getDspCnrSet().getCnrSetSn().equals(setTgt.getDspCnrSet().getCnrSetSn())){
							setResult = setTgt;
							break;
						}
					}
					if(setResult == null){
						setResult = setSrc;
						setTgtList.add(setResult);
					}
					setAutoDspCnrSetGodList(screenNo, setResult, godCnt, type, map);
				}
			}
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectLeftMenuCtgryForMB(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.display.data.DspCtgrySearchFoDTO)
	 */
	@Override
	public List<DspCtgryFoResult> selectLeftMenuCtgryForMB(SystemPK pk,DspCtgrySearchFoDTO dto) throws Exception {
		return displayCategorysService.selectLeftMenuCtgry(pk,dto);
	}



	//이벤트 기획전
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectPlanEvent(com.plgrim.ncp.biz.display.data.DspPlanSearchFoDTO, java.lang.Integer, java.lang.String, com.plgrim.ncp.framework.data.SystemPK)
	 */
	@Override
	public DspPlanDetailResultFoDTO selectPlanEvent (DspPlanSearchFoDTO searchDTO,Integer promtSn,String mode,SystemPK pk) throws Exception {

		DspPlanDetailResultFoDTO dspPlanDetailResultFoDTO = new DspPlanDetailResultFoDTO();

		searchDTO.setPromtSn((long)promtSn);
		searchDTO.setMallId(pk.getMall());
		searchDTO.setDevice(pk.getDevice());
		searchDTO.setLang(pk.getLang());
		searchDTO.setMode(mode);

		//기획전 상세정보
		List<DspPlanFoResult> detailCorner = displayPlanService.selectPlanCorner(searchDTO,promtSn, pk);

		ArrayList<DspCnrFoResult> cornerList = new ArrayList<DspCnrFoResult>();

		for (DspPlanFoResult dspPlanFoResult : detailCorner) {

			DspCnrSearchFoDTO cnrSearch = new DspCnrSearchFoDTO();

			cnrSearch.setParentType("PROMT");
			cnrSearch.setParentIntCd(promtSn);
			cnrSearch.setDspCnr(dspPlanFoResult.getDspCnr());
			cnrSearch.setMallId(pk.getMall());
			cnrSearch.setDevice(pk.getDevice());
			cnrSearch.setLang(pk.getLang());
			cnrSearch.setPrcSectCd(searchDTO.getPrcSectCd());
			cnrSearch.setAplMbrTp(searchDTO.getAplMbrTp());
			cnrSearch.setAplMbrAtrb(searchDTO.getAplMbrAtrb());
			cnrSearch.setGrpcoId(searchDTO.getPsitnGrpcoId());

			if("Y".equals(searchDTO.getSoldDspYn())){
				cnrSearch.setSoldDspYn(searchDTO.getSoldDspYn());
			}

			//코너 기본정보
			DspCnrFoResult dspCnrFoResult = displayCornerService.selectDspCnrDefaultInfo(pk, cnrSearch);

			//주문수량
			//컬러코드
			List<DspCnrSetFoResult> dspCnrSetList = dspCnrFoResult.getDspCnrSetList();

			for(DspCnrSetFoResult dspCnrSetFoResult: dspCnrSetList) {
				Map<String,List<DspCnrConttFoResult>> dspCnrConttMap = dspCnrSetFoResult.getDspCnrConttMap();

				List<DspCnrConttFoResult> cnrConttGodList = dspCnrConttMap.get("GOD");

				if(cnrConttGodList != null) {
					for(DspCnrConttFoResult cnrGod: cnrConttGodList) {

						log.debug("############ cnrGod ::: " + cnrGod);
						DspCnrConttGodFoResult dspCnrGod = cnrGod.getDspCnrGod();
						String godNo = dspCnrGod.getGod().getGodNo();
//						String dsgnGrpNo = dspCnrGod.getGod().getDsgnGrpNo();

//						Map<String,String> searchMap = new HashMap<String,String>();
//						searchMap.put("dsgnGrpNo",dsgnGrpNo);
//
//						/* 상품의 컬러 목록 조회 ( 동일 디자인그룹에 속한 상품 목록 ) */
//						if(Lang.KOR.toString().equals(pk.getLang())){
//							searchMap.put("language",Language.KO.toString());
//						}else if(Lang.ENG.toString().equals(pk.getLang())){
//							searchMap.put("language",Language.EN.toString());
//						}else{
//							searchMap.put("language",Language.ZH.toString());
//						}
//
//						List<GodColorFoResult> colorList = displayCornerService.getGoodsColorList(searchMap);
//						dspCnrGod.setGodColorList(colorList);

						Map<String, Object> map = new HashMap<String, Object>();
						map.put("godNo",godNo);
						map.put("begDt",dspPlanFoResult.getDspPromt().getDspBegDt());
						map.put("endDt",dspPlanFoResult.getDspPromt().getDspEndDt());
						Integer ordCnt = displayCornerService.getGoodsOrdCnt(map);
						dspCnrGod.setOrdCnt(ordCnt);

						List<String> godNoList = new ArrayList<String>();
						godNoList.add(godNo);
						dspCnrGod.setGodIconList(displayPlanService.getGodIconList(godNoList));

					}
				}

			}


			cornerList.add(dspCnrFoResult);

		}

		if("brnd".equals(mode.toString())){
			//브랜드 리스트
			List<DspPlanFoResult> brndList = displayPlanService.selectbrndList(searchDTO);
			dspPlanDetailResultFoDTO.setDspBrndList(brndList);
			searchDTO.setAplBrndId(searchDTO.getSectCd());
		}else{
			//카테고리 리스트
			List<DspPlanFoResult> ctgryList = displayPlanService.selectCtgryList(searchDTO);
			dspPlanDetailResultFoDTO.setDspCtgryList(ctgryList);
			searchDTO.setCategoryNo(searchDTO.getSectCd());
		}

		//기획전 리스트
		List<DspPlanFoResult> dspPlanList = displayPlanService.selectPlanList(searchDTO);

		//구분자 정보 리스트
		//List<DspPlanSprtrFoResult> dspPlanSprtrList = displayPlanService.selectPlanSprtr(searchDTO);

		if(detailCorner != null && detailCorner.size() > 0){
			searchDTO.setSortValue(detailCorner.get(0).getDspPromt().getDspGodSortStdrCd().toString());
			if(pk.getDevice().toString().equals("PC")){
				searchDTO.setListExCnt(detailCorner.get(0).getDspPromt().getPcListExpsrCntCd().toString());
			}else{
				searchDTO.setListExCnt(detailCorner.get(0).getDspPromt().getMobileListExpsrCntCd().toString());
			}
		}
		//상품정보 리스트
		//List<DspPlanSprtrFoResult> detailGodList = displayPlanService.selectPlanGodList(pk, searchDTO);

		if(detailCorner != null && detailCorner.size() > 0){
			dspPlanDetailResultFoDTO.setDspPlanDetail(detailCorner.get(0));
		}

		dspPlanDetailResultFoDTO.setCornerList(cornerList);
		dspPlanDetailResultFoDTO.setDspPlanList(dspPlanList);
		//dspPlanDetailResultFoDTO.setDspPlanSprtrList(dspPlanSprtrList);
		//dspPlanDetailResultFoDTO.setDspPlanDetailGodList(detailGodList);

		return dspPlanDetailResultFoDTO;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectPickPromtSn(java.util.List)
	 */
	@Override
	public Integer selectPickPromtSn(List<Integer> listPromtSn) throws Exception {
		return displayPlanService.selectPickPromtSn(listPromtSn);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectPromtMdBrnd(java.lang.Long, com.plgrim.ncp.framework.data.SystemPK)
	 */
	@Override
	public String selectPromtMdBrnd(Integer promtSn, SystemPK pk) throws Exception {
		return displayPlanService.selectPromtMdBrnd(promtSn, pk);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.display.fo.DisplaySelectComponent#selectMyBrand(java.util.List)
	 */
	@Override
	public List<DspCtgry> selectMyBrand(List<String> dspCtgryNos) throws Exception {
		return displayCategorysService.selectMyBrand(dspCtgryNos);
	}

	@Override
	public List<BrandResultDTO> selectSortedBrandInfoList(List<SysBrnd> brandInfoResult) throws Exception {
		List<BrandResultDTO> sortedBrandInfoResult = new ArrayList<BrandResultDTO>();

		for (SysBrnd sysBrnd : brandInfoResult) {
			BrandResultDTO dto = new BrandResultDTO();
			if (sysBrnd != null && StringUtils.isNotEmpty(sysBrnd.getBrndId()) && StringUtils.isNotEmpty(sysBrnd.getBrndNm())) {
				dto.setBrndId(sysBrnd.getBrndId());
				dto.setBrndNm(sysBrnd.getBrndNm());

				sortedBrandInfoResult.add(dto);
			}

		}

		sortedBrandInfoResult = getSortedBrandList(sortedBrandInfoResult);

		sortedBrandInfoResult = checkBrandCountByAlpha(sortedBrandInfoResult);

		return sortedBrandInfoResult;

	}

	/**
	 * 브랜드 카운팅 (자사브랜드 / 알파펫 별로)
	 *
	 * @param sortedBrandInfoResult
	 * @return
	 */
	private List<BrandResultDTO> checkBrandCountByAlpha(List<BrandResultDTO> sortedBrandInfoResult) {

		int ownBrndCnt = 0;
		int otherBrndCnt = 0;

		TreeMap<String, Integer> checkedMap = new TreeMap<String, Integer>();

		for (BrandResultDTO brandResultDTO : sortedBrandInfoResult) {
			// 자사 브랜드 카운팅
			if (StringUtils.equals(SysInfoEnum.OwnBrandEnum.OwnBrandFlag_Y.toString(), brandResultDTO.getOwnBrndYN())) {
				ownBrndCnt++;

				// 그외 브랜드 알파벳별로 카운팅
			} else {

				// Map에 넣기전에 같은것이 있는지 체크
				if (!checkedMap.isEmpty()
						&& StringUtils.isNotEmpty(brandResultDTO.getBrndNm())
						&& !checkedMap.containsKey(brandResultDTO.getBrndNm().substring(0, 1).toLowerCase()) ) {

					otherBrndCnt = 0;
				}

				otherBrndCnt++;

				checkedMap.put(brandResultDTO.getBrndNm().substring(0, 1).toLowerCase(), otherBrndCnt);

			}
		}

		for (BrandResultDTO brandResultDTO : sortedBrandInfoResult) {
			// 자사 브랜드 카운팅
			if (StringUtils.equals(SysInfoEnum.OwnBrandEnum.OwnBrandFlag_Y.toString(), brandResultDTO.getOwnBrndYN())) {
				brandResultDTO.setOwnBrndCnt(ownBrndCnt);

				// 그외 브랜드 알파벳별로 카운팅
			} else {

				if (checkedMap.containsKey(brandResultDTO.getBrndNm().substring(0, 1).toLowerCase()) ) {
					brandResultDTO.setTotCnt(checkedMap.get(brandResultDTO.getBrndNm().substring(0, 1).toLowerCase()));
					brandResultDTO.setFirstBrndChar(brandResultDTO.getBrndNm().substring(0, 1).toUpperCase());
				}
			}
		}

		return sortedBrandInfoResult;

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	// 2016/07 (UX/UI)
	///////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void setDTOInfoForCnr(DspCnrScFrDTO dspCnrScFrDTO, SystemPK pk, GoodsPriceSearchDTO goodsPriceSearchFoDTO) throws Exception {

		dspCnrScFrDTO.setMallId(pk.getMall());
		dspCnrScFrDTO.setLang(pk.getLang());
		dspCnrScFrDTO.setSpcPrmTp(goodsPriceSearchFoDTO.getSpcPrmTp());
		dspCnrScFrDTO.setPrcSectCd(goodsPriceSearchFoDTO.getPrcSectCd());
	}

	@Override
	public void setTmplatInfo(DspCnrScFrDTO dspCnrScFrDTO, String tmplatTp, String tmplatKey) throws Exception {
		dspCnrScFrDTO.setTmplatTp(tmplatTp);
		dspCnrScFrDTO.setTmplatScKey(tmplatKey);
	}

	@Override
	public void setDTOInfoForCtgry(DspCtgrySearchFoDTO dspCtgrySearchFoDTO, SystemPK pk, String dspCtgryNo ) throws Exception {
		DspCtgry dc = new DspCtgry();
		dc.setDspCtgryNo(dspCtgryNo);
		dspCtgrySearchFoDTO.setDspCtgry(dc);
		dspCtgrySearchFoDTO.setHasCtgryInfo("Y");
		dspCtgrySearchFoDTO.setHasGodList("N");
		dspCtgrySearchFoDTO.setHasLocation("N");
		dspCtgrySearchFoDTO.setHasSubCtgry("N");
	}

	@Override
	public void setDTOInfoForGodSearch(DspCtgrySearchFoDTO dspCtgrySearchFoDTO, DspCtgryResultFoDTO dspCtgryInfo, HttpServletRequest request, GoodsPriceSearchDTO priceDTO, DspCtgryScFrDTO dspCtgryFrDTO) throws Exception {
		setMbrInfo(dspCtgrySearchFoDTO);

		String sortColumn = request.getParameter("sortColumn");
		if(sortColumn == null){
			if(!"NEW_GOD_CTGRY".equals(dspCtgryInfo.getDspCtgryResult().getDspCtgry().getCtgrySectCd())
					&& !"BST_CTGRY".equals(dspCtgryInfo.getDspCtgryResult().getDspCtgry().getCtgrySectCd())
					&& !"ON_SALE_CTGRY".equals(dspCtgryInfo.getDspCtgryResult().getDspCtgry().getCtgrySectCd())){
				sortColumn = dspCtgryInfo.getDspCtgryResult().getDspCtgry().getDspGodSortStdrCd();
				if(StringService.isEmpty(sortColumn)) {
					sortColumn = "NEW_GOD_SEQ";
				}
			}else{
				sortColumn = "MD_RECOMMEND_SEQ";
			}
		}

		int defaultImgTurn = 0;

		if(dspCtgryInfo.getDspCtgryResult().getDspCtgry().getListExpsrImgTurn() != null){
			defaultImgTurn = dspCtgryInfo.getDspCtgryResult().getDspCtgry().getListExpsrImgTurn();
		}
		dspCtgrySearchFoDTO.setDefaultImgTurn(defaultImgTurn); // default 이미지 순번은 PC, 모바일 공통
		dspCtgrySearchFoDTO.setHasGodList("Y");
		dspCtgrySearchFoDTO.setSortColumn(sortColumn);
		dspCtgrySearchFoDTO.setSpcPrmTp(priceDTO.getSpcPrmTp());
		dspCtgrySearchFoDTO.setPrcSectCd(priceDTO.getPrcSectCd());

		dspCtgrySearchFoDTO.setSearchConditionBrandAtoZs(dspCtgryFrDTO.getBrand());
		dspCtgrySearchFoDTO.setSearchConditionSizes(dspCtgryFrDTO.getSize());
		dspCtgrySearchFoDTO.setSearchConditionColors(dspCtgryFrDTO.getColor());
		dspCtgrySearchFoDTO.setSearchConditionColors2(dspCtgryFrDTO.getColor2());
		dspCtgrySearchFoDTO.setSearchConditionMaterials(dspCtgryFrDTO.getMeterial());
	}

	@Override
	public int getGodCnrConttCnt(List<DspCnrFrResult> cnrList) throws  Exception {
		int conttCnt = 0;
		if(cnrList != null){
			for(DspCnrFrResult cnr : cnrList) {
				if(cnr.getDspCnr() == null){
					DspCnr dspCnr = new DspCnr();
					cnr.setDspCnr(dspCnr);
				}
				if("GOD_BANNER".equals(cnr.getDspCnr().getDspCnrDscr())){
					for(DspCnrSetFrResult set : cnr.getDspCnrSetList()){
						if(StringService.isNotEmpty(set.getDspCnrSet().getSetDscr())){
							conttCnt += Integer.parseInt(set.getDspCnrSet().getSetDscr());
						}
					}
				}
			}
		}

		return conttCnt;
	}


	@Override
	public int getGodCnrConttCntMobile(List<DspCnrFrResult> cnrList) throws  Exception {
		int conttCnt = 0;
		if(cnrList != null){
			for(DspCnrFrResult cnr : cnrList) {
				if(cnr.getDspCnr() == null){
					DspCnr dspCnr = new DspCnr();
					cnr.setDspCnr(dspCnr);
				}
				if("GOD_BANNER".equals(cnr.getDspCnr().getDspCnrDscr())){
					for(DspCnrSetFrResult set : cnr.getDspCnrSetList()){
						// 모바일에서는 1단 배너 노출 안함
						if("2".equals(set.getDspCnrSet().getSetDscr())){
							conttCnt += Integer.parseInt(set.getDspCnrSet().getSetDscr());
						}
					}
				}
			}
		}

		return conttCnt;
	}

	@Override
	public DspCtgryResultFoDTO selectCtgryListV2(String dspCtgryNo, String ctgrySectCd) throws Exception {
		DspCtgryResultFoDTO ctgryNoList = displayCategorysService.selectV2DspCtgryNo(dspCtgryNo, ctgrySectCd);
		return ctgryNoList;
	}

	@Override
	public DspCtgryResultFoDTO selectDspCtgryInfo(SystemPK pk, GoodsPriceSearchDTO priceSearchFoDTO, String brandShopNo) throws Exception{
		DspCtgrySearchFoDTO dspCtgrySearchFoDTO = new DspCtgrySearchFoDTO();
		DspCtgry dc = new DspCtgry();
		dc.setDspCtgryNo(brandShopNo);

		dspCtgrySearchFoDTO.setSpcPrmTp(priceSearchFoDTO.getSpcPrmTp());
		dspCtgrySearchFoDTO.setPrcSectCd(priceSearchFoDTO.getPrcSectCd());
		dspCtgrySearchFoDTO.setDspCtgryTreeRootDspCtgryNo(brandShopNo);
		dspCtgrySearchFoDTO.setDspCtgry(dc);
		dspCtgrySearchFoDTO.setHasCtgryInfo("Y");
		dspCtgrySearchFoDTO.setHasGodList("N");
		dspCtgrySearchFoDTO.setHasLocation("N");
		dspCtgrySearchFoDTO.setHasSubCtgry("N");

		DspCtgryResultFoDTO dspCtgryResult = new DspCtgryResultFoDTO();
		try{
			if(!dspCtgrySearchFoDTO.getHasCtgryInfo().equals("N")){
				DspCtgryFoResult dspCtgryInfo = displayCategorysService.selectDisplayCategoryInfo(pk, dspCtgrySearchFoDTO);
				dspCtgryResult.setDspCtgryResult(dspCtgryInfo);
			}
			if(!dspCtgrySearchFoDTO.getHasSubCtgry().equals("N")){
				List<DspCtgryFoResult> dspCtgryResultList = displayCategorysService.selectSubDisplayCategoryList(pk, dspCtgrySearchFoDTO);

				String ctgryType = dspCtgrySearchFoDTO.getDspCtgryOutputType();
				if(ctgryType == null){
					ctgryType = "A_TP_LAG_MID_SML";
				}
				String dspCtgrySpecial = dspCtgrySearchFoDTO.getSpecial();
				if(dspCtgrySpecial != null){
					if(dspCtgrySpecial.equals("OUTLET") || dspCtgrySpecial.equals("BRAND") || dspCtgrySpecial.equals("FILTER")){
						dspCtgryResultList = displayCategorysService.selectSubDisplayCategorySpecialList(dspCtgryResultList, pk, dspCtgrySearchFoDTO, ctgryType);
					}
				}
				dspCtgryResult.setDspCtgryResultList(dspCtgryResultList);
			}
		}catch(Exception e){
			log.error(e.getMessage(), e);
		}
		return dspCtgryResult;
	}

	@Override
	public DspCtgryResultFoDTO selectDspCtgryGodList(DspCtgrySearchFoDTO dspCtgrySearchFoDTO, SystemPK pk) throws Exception {
		dspCtgrySearchFoDTO.setMallId(pk.getMall());
		dspCtgrySearchFoDTO.setLang(pk.getLang());
		dspCtgrySearchFoDTO.setDevice(pk.getDevice());
		DspCtgryResultFoDTO dspCtgryResult = new DspCtgryResultFoDTO();

		// 전시 카테고리 연결 상품목록
		String hasGodList = dspCtgrySearchFoDTO.getHasGodList();
		if(!hasGodList.equals("N")){

			if(!"CORNER".equals(dspCtgrySearchFoDTO.getSelectSectCd())){
				int totCnt = displayCategorysService.selectDspCtgryGodsListCnt(dspCtgrySearchFoDTO);
				dspCtgryResult.setDspCtgryGodTotCount(totCnt);
			}

			/** 전시 상품 리스트 */
			List<DspCtgryGodFoResult> godList = displayCategorysService.selectDspCtgryGodsList(pk, dspCtgrySearchFoDTO);

			dspCtgryResult.setDspCtgryGodList(godList);
		}
		return dspCtgryResult;
	}

	@Override
	public DspCnrGodFoResult selectTopSellers(String dspCtgryNo, String brndId, GoodsPriceSearchDTO priceDTO, SystemPK pk, String cnrDscr, int imgTurn) throws Exception {

		// STEP 1 : 호출한 화면 카테고리 번호로 하위 TOP SELLERS 카테고리 번호 조회
		DspCtgryResultFoDTO ctgryNoList = displayCategorysService.selectV2DspCtgryNo(dspCtgryNo, "BST_CTGRY");

		if(ctgryNoList.getDspCtgryResultList() == null || ctgryNoList.getDspCtgryResultList().size() < 1){
			DspCnrGodFoResult result = new DspCnrGodFoResult();

			return result;
		}else{
			String ctgryNo = ctgryNoList.getDspCtgryResultList().get(0).getDspCtgry().getDspCtgryNo();

			// STEP 2 : Top Sellers 카테고리 상품 조회를 위한 조건 설정
			DspCtgrySearchFoDTO dspCtgrySearchFoDTO = new DspCtgrySearchFoDTO();
			DspCtgry dspCtgry = new DspCtgry();

			dspCtgry.setDspCtgryNo(ctgryNo);
			if("PC".equals(pk.getDevice())){
				if("NEW_TOPSELLER".equals(cnrDscr)){
					dspCtgrySearchFoDTO.setImgSizeCd("346X457");
				}else{
					dspCtgrySearchFoDTO.setImgSizeCd("228X301");
				}
			}else{
				dspCtgrySearchFoDTO.setImgSizeCd("346X457");
			}
			dspCtgrySearchFoDTO.setSelectSectCd("CORNER");
			dspCtgrySearchFoDTO.setDspCtgry(dspCtgry);
			dspCtgrySearchFoDTO.setSearchConditionBrandId(brndId);
			dspCtgrySearchFoDTO.setHasGodList("Y");
			dspCtgrySearchFoDTO.setSpcPrmTp(priceDTO.getSpcPrmTp());
			dspCtgrySearchFoDTO.setPrcSectCd(priceDTO.getPrcSectCd());
			dspCtgrySearchFoDTO.setSortColumn("MD_RECOMMEND_SEQ");
			dspCtgrySearchFoDTO.setDefaultImgTurn(imgTurn-1);
			if(StringService.isEmpty(brndId)){
				dspCtgrySearchFoDTO.setMallGubun("DXM");
			}

			dspCtgrySearchFoDTO.setBeginIndex(1);
			dspCtgrySearchFoDTO.setEndIndex(10);

			// STEP 3 : Top Sellers 카테고리 상품 조회
			DspCtgryResultFoDTO godList = selectDspCtgryGodList(dspCtgrySearchFoDTO, pk);

			// STEP 4 : Top Sellers 컨텐츠 조회를 위한 조건 설정
			DspCnrScFrDTO dto = new DspCnrScFrDTO();
			dto.setDevice(DisplayEnum.DVC.PC.toString());
			dto.setTmplatKeyBrndId(brndId);
			setDTOInfoForCnr(dto, pk, priceDTO);
			setTmplatInfo(dto, DisplayEnum.TMPLAT_TP.DSP_CTGRY.toString(), ctgryNo);

			// STEP 5 : Top Sellers 컨텐츠 조회
			List<DspCnrFrResult> cnrList = this.selectDspCnrListV2(dto);

			DspCnrGodFoResult result = new DspCnrGodFoResult();
			result.setDspCtgryGod(godList);
			result.setDspCtgryCnrList(cnrList);

			// STEP 6 : Top Sellers 카테고리 번호 세팅(화면 컨트롤 요소)
			result.setDspCtgryNo(ctgryNo);

			return result;
		}

	}

	@Override
	public DspCtgryResultFoDTO selectNewIn(String dspCtgryNo, String brndId, GoodsPriceSearchDTO priceDTO, SystemPK pk, int imgTurn) throws Exception {

		// STEP 1 : 호출한 화면 카테고리 번호로 하위 New In 카테고리 번호 조회
		DspCtgryResultFoDTO ctgryNoList = displayCategorysService.selectV2DspCtgryNo(dspCtgryNo, "NEW_GOD_CTGRY");
		if(ctgryNoList.getDspCtgryResultList() == null || ctgryNoList.getDspCtgryResultList().size() < 1){
			DspCtgryResultFoDTO result = new DspCtgryResultFoDTO();

			return result;
		}else{
			String ctgryNo = ctgryNoList.getDspCtgryResultList().get(0).getDspCtgry().getDspCtgryNo();

			// STEP 2 : New In 카테고리 상품 조회를 위한 조건 설정
			DspCtgrySearchFoDTO dspCtgrySearchFoDTO = new DspCtgrySearchFoDTO();
			DspCtgry dspCtgry = new DspCtgry();

			dspCtgry.setDspCtgryNo(ctgryNo);
			if("PC".equals(pk.getDevice())){
				dspCtgrySearchFoDTO.setImgSizeCd("228X301");
			}else{
				dspCtgrySearchFoDTO.setImgSizeCd("346X457");
			}
			dspCtgrySearchFoDTO.setSelectSectCd("CORNER");
			dspCtgrySearchFoDTO.setDspCtgry(dspCtgry);
			dspCtgrySearchFoDTO.setSearchConditionBrandId(brndId);
			dspCtgrySearchFoDTO.setHasGodList("Y");
			dspCtgrySearchFoDTO.setSpcPrmTp(priceDTO.getSpcPrmTp());
			dspCtgrySearchFoDTO.setPrcSectCd(priceDTO.getPrcSectCd());
			dspCtgrySearchFoDTO.setSortColumn("MD_RECOMMEND_SEQ");
			dspCtgrySearchFoDTO.setDefaultImgTurn(imgTurn-1);
			if(StringService.isEmpty(brndId)){
				dspCtgrySearchFoDTO.setMallGubun("DXM");
			}

			dspCtgrySearchFoDTO.setBeginIndex(1);
			dspCtgrySearchFoDTO.setEndIndex(40);

			// STEP 3 : New In 카테고리 상품 조회
			DspCtgryResultFoDTO result = selectDspCtgryGodList(dspCtgrySearchFoDTO, pk);

			// STEP 4 : New In 카테고리 번호 세팅(화면 컨트롤 요소)
			DspCtgryFoResult dspCtgryResult = new DspCtgryFoResult();
			dspCtgryResult.setDspCtgry(dspCtgry);
			result.setDspCtgryResult(dspCtgryResult);

			return result;
		}
	}

	@Override
	public DspCtgryResultFoDTO selectOnSale(String dspCtgryNo, String brndId, GoodsPriceSearchDTO priceDTO, SystemPK pk, int imgTurn) throws Exception {

		// STEP 1 : 호출한 화면 카테고리 번호로 하위 On Sale 카테고리 번호 조회
		DspCtgryResultFoDTO ctgryNoList = displayCategorysService.selectV2DspCtgryNo(dspCtgryNo, "ON_SALE_CTGRY");
		if(ctgryNoList.getDspCtgryResultList() == null || ctgryNoList.getDspCtgryResultList().size() < 1){
			DspCtgryResultFoDTO result = new DspCtgryResultFoDTO();

			return result;
		}else{
			String ctgryNo = ctgryNoList.getDspCtgryResultList().get(0).getDspCtgry().getDspCtgryNo();

			// STEP 2 : On Sale 카테고리 상품 조회를 위한 조건 설정
			DspCtgrySearchFoDTO dspCtgrySearchFoDTO = new DspCtgrySearchFoDTO();
			DspCtgry dspCtgry = new DspCtgry();

			dspCtgry.setDspCtgryNo(ctgryNo);
			if("PC".equals(pk.getDevice())){
				dspCtgrySearchFoDTO.setImgSizeCd("228X301");
			}else{
				dspCtgrySearchFoDTO.setImgSizeCd("346X457");
			}
			dspCtgrySearchFoDTO.setSelectSectCd("CORNER");
			dspCtgrySearchFoDTO.setDspCtgry(dspCtgry);
			dspCtgrySearchFoDTO.setSearchConditionBrandId(brndId);
			dspCtgrySearchFoDTO.setHasGodList("Y");
			dspCtgrySearchFoDTO.setSpcPrmTp(priceDTO.getSpcPrmTp());
			dspCtgrySearchFoDTO.setPrcSectCd(priceDTO.getPrcSectCd());
			dspCtgrySearchFoDTO.setSortColumn("MD_RECOMMEND_SEQ");
			dspCtgrySearchFoDTO.setDefaultImgTurn(imgTurn-1);
			if(StringService.isEmpty(brndId)){
				dspCtgrySearchFoDTO.setMallGubun("DXM");
			}

			dspCtgrySearchFoDTO.setBeginIndex(1);
			dspCtgrySearchFoDTO.setEndIndex(40);

			// STEP 3 : On Sale 카테고리 상품 조회
			DspCtgryResultFoDTO result = selectDspCtgryGodList(dspCtgrySearchFoDTO, pk);

			// STEP 4 : On Sale 카테고리 번호 세팅(화면 컨트롤 요소)
			DspCtgryFoResult dspCtgryResult = new DspCtgryFoResult();
			dspCtgryResult.setDspCtgry(dspCtgry);
			result.setDspCtgryResult(dspCtgryResult);

			return result;
		}

	}

	@Override
	public DspCtgryResultFoDTO selectNewSaleTopLifestyle(String menCtgryNo, String womenCtgryNo, String brndId, GoodsPriceSearchDTO priceDTO, SystemPK pk) throws Exception{
		// STEP 1 : 상품 조회를 위한 조건 설정
		DspCtgrySearchFoDTO dspCtgrySearchFoDTO = new DspCtgrySearchFoDTO();
		dspCtgrySearchFoDTO.setSearchConditionBrandId(brndId);
		dspCtgrySearchFoDTO.setHasGodList("Y");
		dspCtgrySearchFoDTO.setSpcPrmTp(priceDTO.getSpcPrmTp());
		dspCtgrySearchFoDTO.setPrcSectCd(priceDTO.getPrcSectCd());
		dspCtgrySearchFoDTO.setMenCtgryNo(menCtgryNo);
		dspCtgrySearchFoDTO.setWomenCtgryNo(womenCtgryNo);
		dspCtgrySearchFoDTO.setSortColumn("MD_RECOMMEND_SEQ");
		if("PC".equals(pk.getDevice())){
			dspCtgrySearchFoDTO.setImgSizeCd("228X301");
		}else{
			dspCtgrySearchFoDTO.setImgSizeCd("346X457");
		}

		dspCtgrySearchFoDTO.setBeginIndex(1);
		dspCtgrySearchFoDTO.setEndIndex(40);

		// STEP 2 : 상품 조회
		dspCtgrySearchFoDTO.setMallId(pk.getMall());
		dspCtgrySearchFoDTO.setLang(pk.getLang());
		dspCtgrySearchFoDTO.setDevice(pk.getDevice());
		DspCtgryResultFoDTO dspCtgryResult = new DspCtgryResultFoDTO();

		// 전시 카테고리 연결 상품목록
		String hasGodList = dspCtgrySearchFoDTO.getHasGodList();
		if(!hasGodList.equals("N")){

			int totCnt = displayCategorysService.selectNewSaleTopLifestyleCnt(dspCtgrySearchFoDTO);
			/** 전시 상품 리스트 */
			List<DspCtgryGodFoResult> godList = displayCategorysService.selectNewSaleTopLifestyleList(pk, dspCtgrySearchFoDTO);


			dspCtgryResult.setDspCtgryGodTotCount(totCnt);
			dspCtgryResult.setDspCtgryGodList(godList);
		}
		return dspCtgryResult;
	}



	@Override
	public List<String> selectCtgryCnncGodNo(DspCtgryScFrDTO dspCtgryFrDTO) throws Exception{
		setMbrInfo(dspCtgryFrDTO);

		return displayCategorysService.selectCtgryCnncGodNo(dspCtgryFrDTO);
	}

	@Override
	public List<String> selectTopNewSaleGodNo(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception{
		setMbrInfo(dspCtgryScFrDTO);

		return displayCategorysService.selectTopNewSaleGodNo(dspCtgryScFrDTO);
	}

	@Override
	public List<DspCtgryFoResult> selectLowerCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		return displayCategorysService.selectLowerCtgryList(dspCtgryScFrDTO);
	}

	@Override
	public List<String> selectCtgryNmListPC(DspCtgryScFrDTO dspCtgryFrDTO) throws Exception{
		return displayCategorysService.selectCtgryNmListPC(dspCtgryFrDTO);
	}

	@Override
	public List<String> selectCtgryNmList(DspCtgryScFrDTO dspCtgryFrDTO) throws Exception{
		setMbrInfo(dspCtgryFrDTO);

		return displayCategorysService.selectCtgryNmList(dspCtgryFrDTO);
	}


	@Override
	public DspCtgryResultFoDTO selectRecommendationList(String godNoList, GoodsPriceSearchDTO priceSearchFoDTO, SystemPK pk) throws Exception{
		DspCtgrySearchFoDTO dto = new DspCtgrySearchFoDTO();
		dto.setMallGubun("DXM");
		dto.setPrcSectCd(priceSearchFoDTO.getPrcSectCd());
		dto.setSpcPrmTp(priceSearchFoDTO.getSpcPrmTp());
		dto.setLang(pk.getLang());
		dto.setMallId(pk.getMall());
		dto.setDevice(pk.getDevice());
		dto.setRecoGodNoList(godNoList.split(","));

		List<DspCtgryGodFoResult> result = displayCategorysService.selectRecommendationList(dto);

		DspCtgryResultFoDTO godList = new DspCtgryResultFoDTO();
		godList.setDspCtgryGodList(result);
		return godList;
	}

	public void setMbrInfo(DspCtgrySearchFoDTO dto) throws Exception {
		MemberSimpleInfo info = setupMrbInfo();
		dto.setEmpYn(info.getEmpYn());
		dto.setAplMbrAtrb(info.getMbr().getMbrAtrbCd());
		dto.setAplMbrTp(info.getMbr().getMbrTpCd());
		dto.setGrpcoId(info.getMbr().getPsitnGrpcoId());
	}

	/**
	 * 검색 전시카테고리의 상품정렬코드 조회
	 *
	 * @param SystemPK
	 * @param DspCtgrySearchFoDTO
	 * @return getDspCtgryGodSortCdInfo
	 * @author sy1985.kim
	 * @throws Exception
	 */
	public String getDspCtgryGodSortCdInfo(SystemPK pk, DspCtgrySearchFoDTO dspCtgrySearchFoDTO) throws Exception {
		DspCtgryFoResult dspCtgryInfo =	displayCategorysService.selectDisplayCategoryGodSortInfo(pk, dspCtgrySearchFoDTO);
		String result = "SORT_NEW_GOD_IDEX";

		if(dspCtgryInfo != null){
			if(dspCtgryInfo.getDspCtgry().getDspGodSortStdrCd() != null){
				result = dspCtgryInfo.getDspCtgry().getDspGodSortStdrCd();
			}
		}
		log.debug("DspGodSortStdrCd : {}",result);
		return result;
	}

	@Override
	public DspCtgryResultFoDTO selectDisplayCategoryFoInfo(SystemPK pk, String dspCtgryNo, String dspBrndId, String mallGubun) throws Exception {
		DspCtgry dc = new DspCtgry();
		dc.setDspCtgryNo(dspCtgryNo);
		dc.setDspBrndId(dspBrndId);

		DspCtgrySearchFoDTO dspCtgrySearchFoDTO = new DspCtgrySearchFoDTO();
		dspCtgrySearchFoDTO.setDspCtgry(dc);
		dspCtgrySearchFoDTO.setMallGubun(mallGubun);

		DspCtgryResultFoDTO dspCtgryResult = new DspCtgryResultFoDTO();
		DspCtgryFoResult dspCtgryInfo = displayCategorysService.selectDisplayCategoryFoInfo(pk, dspCtgrySearchFoDTO);
		dspCtgryResult.setDspCtgryResult(dspCtgryInfo);

		return dspCtgryResult;
	}

	@Override
	public int getCornerImgTurn(List<DspCnrFrResult> cnrList, String cnrDscr) throws Exception {
		String imgTurn = "0";
		if(cnrList != null && cnrList.size() > 0){
			for(DspCnrFrResult list : cnrList){
				if(list.getDspCnr() != null){
					if(cnrDscr.equals(list.getDspCnr().getDspCnrDscr())){
						if(list.getDspCnrSetList() != null && list.getDspCnrSetList().size() > 0){
							if(StringService.isNotEmpty(list.getDspCnrSetList().get(0).getDspCnrSet().getSetDscr())){
								imgTurn = list.getDspCnrSetList().get(0).getDspCnrSet().getSetDscr();
							}

						}
					}
				}
			}
		}
		return Integer.parseInt(imgTurn);
	}
	
}

