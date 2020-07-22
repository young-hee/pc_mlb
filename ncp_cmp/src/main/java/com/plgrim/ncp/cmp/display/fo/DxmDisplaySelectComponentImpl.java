package com.plgrim.ncp.cmp.display.fo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspUseGrp;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlExtend;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.biz.display.data.DspCnrConttExt;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.data.InterestSearchFoDTO;
import com.plgrim.ncp.biz.display.repository.DxmDisplayRepository;
import com.plgrim.ncp.biz.display.result.DspCnrFrResult;
import com.plgrim.ncp.biz.display.result.DspCnrSetFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryGodFoResult;
import com.plgrim.ncp.biz.display.result.DxmDspFoCtgryResult;
import com.plgrim.ncp.biz.display.result.GoodsInterestsGodFoResult;
import com.plgrim.ncp.biz.display.result.MallDspFoCtgryResult;
import com.plgrim.ncp.biz.display.service.DxmDisplayService;
import com.plgrim.ncp.biz.goods.service.GoodsContentService;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.page.PageService;

@Component
public class DxmDisplaySelectComponentImpl implements DxmDisplaySelectComponent {

	@Autowired
	DxmDisplayService dxmDisplayService;
	
	@Autowired
	GoodsContentService goodsContentService;
	
	@Autowired
	DxmDisplayRepository dxmDisplayRepository;

	@Override
	public DxmDspFoCtgryResult lnbCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		DxmDspFoCtgryResult result = new DxmDspFoCtgryResult();
		result.setDxmDspFoCtgryResults(dxmDisplayService.lnbCtgryList(dspCtgryScFrDTO));

		return result;
	}

	@Override
	public DxmDspFoCtgryResult lnbMbCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		DxmDspFoCtgryResult result = new DxmDspFoCtgryResult();
		result.setDxmDspFoCtgryResults(dxmDisplayService.lnbMbCtgryList(dspCtgryScFrDTO));

		return result;
	}

	@Override
	public Page<DspCtgryGodFoResult> selectDisplayGodList(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam)
			throws Exception {
	 
		if("S".equals(dspCtgryScFrDTO.getDspCtgryType())){
			return dxmDisplayService.searchDisplayGodList(dspCtgryScFrDTO, pageParam);
		}else{
			return dxmDisplayService.selectDisplayGodList(dspCtgryScFrDTO, pageParam);
		}
 
		
	}

	@Override
	public DspCtgryGodFoResult selectFilterList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		// TODO Auto-generated method stub
		return dxmDisplayService.selectFilterList(dspCtgryScFrDTO);
	}

	@Override
	public DspCnrFrResult selectMajorCategoryContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		return dxmDisplayService.selectMajorCategoryContt(dspCtgryScFrDTO);
	}

	@Override
	public DspCnrFrResult selectMainContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		DspUseGrp dspUseGrp = dxmDisplayService.selectDspUseGrp(dspCtgryScFrDTO);
		dspCtgryScFrDTO.setDspCtgryNo(dspUseGrp.getDspCtgryNo());
		return dxmDisplayService.selectMainContt(dspCtgryScFrDTO);
	}

	@Override
	public DspCnrFrResult selectMbMainContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		DspUseGrp dspUseGrp = dxmDisplayService.selectDspUseGrp(dspCtgryScFrDTO);
		dspCtgryScFrDTO.setDspCtgryNo(dspUseGrp.getDspCtgryNo());
		return dxmDisplayService.selectMbMainContt(dspCtgryScFrDTO);
	}

	@Override
	public DspCnrFrResult conttAppend(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		DspUseGrp dspUseGrp = dxmDisplayService.selectDspUseGrp(dspCtgryScFrDTO);
		dspCtgryScFrDTO.setDspCtgryNo(dspUseGrp.getDspCtgryNo());
		return dxmDisplayService.conttAppend(dspCtgryScFrDTO);
	}

	@Override
	public void selectCommonCtgryList(Model model, DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam)
			throws Exception {
		String sortColumn = dspCtgryScFrDTO.getSortColumn();
		dspCtgryScFrDTO.setUpperCategory("DXM");

		dxmDisplayService.selectCtgryList(dspCtgryScFrDTO);

		if (StringUtils.isNotEmpty(sortColumn)) {
			dspCtgryScFrDTO.setSortColumn(sortColumn);
		}

		dspCtgryScFrDTO.setMallGubun("DXM");
		List<MallDspFoCtgryResult> naviNmList = dxmDisplayService.selectCategotyNaviNm(dspCtgryScFrDTO);
		String sTitle = "";
		
		for(MallDspFoCtgryResult list : naviNmList){
			if(DisplayEnum.ONE.toString().equals(list.getCtgryDpthCd())){
				sTitle = list.getDspCtgryNm();
			}else if(DisplayEnum.TWO.toString().equals(list.getCtgryDpthCd())){
				sTitle = list.getDspCtgryNm() + " | " + sTitle;
			}else if(DisplayEnum.THREE.toString().equals(list.getCtgryDpthCd())){
				sTitle = list.getDspCtgryNm() + " | " + sTitle;
			}
		}
		
		model.addAttribute("seo_title", sTitle + " | Discovery Expedition");
		model.addAttribute("seo_desc", sTitle + " | Discovery Expedition");
		
		Page<DspCtgryGodFoResult> godList = selectDisplayGodList(dspCtgryScFrDTO, pageParam);
		displayCommon(model, dspCtgryScFrDTO, godList);
		
	}

	@Override
	public void selectMbCommonCtgryList(Model model, DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam)
			throws Exception {
		String sortColumn = dspCtgryScFrDTO.getSortColumn();
		dspCtgryScFrDTO.setUpperCategory("DXM");
		dxmDisplayService.selectMbCtgryList(model, dspCtgryScFrDTO);

		dspCtgryScFrDTO.setMallGubun("DXM");
		List<MallDspFoCtgryResult> naviNmList = dxmDisplayService.selectCategotyNaviNm(dspCtgryScFrDTO);
		String sTitle = "";
		
		for(MallDspFoCtgryResult list : naviNmList){
			if(DisplayEnum.ONE.toString().equals(list.getCtgryDpthCd())){
				sTitle = list.getDspCtgryNm();
			}else if(DisplayEnum.TWO.toString().equals(list.getCtgryDpthCd())){
				sTitle = list.getDspCtgryNm() + " | " + sTitle;
			}else if(DisplayEnum.THREE.toString().equals(list.getCtgryDpthCd())){
				sTitle = list.getDspCtgryNm() + " | " + sTitle;
			}
		}
		
		model.addAttribute("seo_title", sTitle + " | Discovery Expedition");
		model.addAttribute("seo_desc", sTitle + " | Discovery Expedition");

		if (StringUtils.isNotEmpty(sortColumn)) {
			dspCtgryScFrDTO.setSortColumn(sortColumn);
		}
 
		Page<DspCtgryGodFoResult> godList = selectDisplayGodList(dspCtgryScFrDTO, pageParam);
		displayCommon(model, dspCtgryScFrDTO, godList);

	}

	@Override
	public void searchDisplayGodList(Model model, DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam) throws Exception {

		dspCtgryScFrDTO.setDspCtgryNo("DXM");
		Page<DspCtgryGodFoResult> godList = dxmDisplayService.searchDisplayGodList(dspCtgryScFrDTO, pageParam);
		dspCtgryScFrDTO.setCtgryNoDpth1("");
		dspCtgryScFrDTO.setCtgryNoDpth2("");
		dspCtgryScFrDTO.setCtgryNoDpth3("");
		
		HttpServletRequest request = ContextService.getCurrentRequest();
		
		displayCommonForSearch(model, dspCtgryScFrDTO, godList, request);
	}

	@Override
	public Page<GodGodEvlExtend> selectDisplayReviewList(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam)
			throws Exception {

		return dxmDisplayService.selectDisplayReviewList(dspCtgryScFrDTO, pageParam);

	}
	@Override
	public List<DxmDspFoCtgryResult> bestCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
 
		return dxmDisplayService.bestCtgryList(dspCtgryScFrDTO);
	}
	@Override
	public List<GodGodEvlExtend> selectDisplayBestReviewList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		return dxmDisplayService.selectDisplayBestReviewList(dspCtgryScFrDTO);

	}
	@Override
	public DxmDspFoCtgryResult  selectLnbCategory(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		 
		return dxmDisplayService.selectLnbCategory(dspCtgryScFrDTO);
	}

	private void displayCommon(Model model, DspCtgryScFrDTO dspCtgryScFrDTO, Page<DspCtgryGodFoResult> godList)
			throws Exception {
		PageService.makePageResult(godList, model);
		model.addAttribute("godList", godList.getContent());
		model.addAttribute("totalRow", godList.getTotalElements());

		DspCtgryGodFoResult filters = selectFilterList(dspCtgryScFrDTO);
		
		makeFilters(model, filters);
		
		model.addAttribute("searchSize", dspCtgryScFrDTO.getSearchConditionSizes());
		model.addAttribute("searchColor", dspCtgryScFrDTO.getSearchConditionColors());
		model.addAttribute("fLocation", dspCtgryScFrDTO.getFLocation());
		model.addAttribute("dspCtgryScFrDTO", dspCtgryScFrDTO);
		model.addAttribute("sortColumn", dspCtgryScFrDTO.getSortColumn());
	}
	
	private void displayCommonForSearch(Model model, DspCtgryScFrDTO dspCtgryScFrDTO, Page<DspCtgryGodFoResult> godList, HttpServletRequest request)
			throws Exception {
		PageService.makePageResult(godList, model);
		model.addAttribute("godList", godList.getContent());
		model.addAttribute("totalRow", godList.getTotalElements());

		DspCtgryGodFoResult filters = new DspCtgryGodFoResult();
		
		// 검색 버튼을 눌렀을시에는 필터 조회.
		// 필터에서 체크하고 검색 버튼을 눌렀을시에는 세션에서 조회하지만 혹시나 없으면 그냥 필터 조회.
		if(dspCtgryScFrDTO.isSearchFilterInitFlag()) {
			filters = dxmDisplayService.selectFilterListByGod(dspCtgryScFrDTO);
			request.getSession().setAttribute("DSP_CTG_CTGRY_GOD_FO_RESULT", filters);
		}
		else {
			if(request.getSession().getAttribute("DSP_CTG_CTGRY_GOD_FO_RESULT") != null) {
				filters = (DspCtgryGodFoResult) request.getSession().getAttribute("DSP_CTG_CTGRY_GOD_FO_RESULT");
			}
			else {
				filters = dxmDisplayService.selectFilterListByGod(dspCtgryScFrDTO);
				request.getSession().setAttribute("DSP_CTG_CTGRY_GOD_FO_RESULT", filters);
			}
		}
		
		makeFilters(model, filters);
		
		model.addAttribute("searchSize", dspCtgryScFrDTO.getSearchConditionSizes());
		model.addAttribute("searchColor", dspCtgryScFrDTO.getSearchConditionColors());
		model.addAttribute("fLocation", dspCtgryScFrDTO.getFLocation());
		model.addAttribute("dspCtgryScFrDTO", dspCtgryScFrDTO);
		model.addAttribute("sortColumn", dspCtgryScFrDTO.getSortColumn());
	}
	
	private void makeFilters(Model model, DspCtgryGodFoResult filters) {
		List<List<DspCtgryGodFoResult>> sizeFilters = new ArrayList<>();
		List<DspCtgryGodFoResult> number = new ArrayList<>();
		List<DspCtgryGodFoResult> str = new ArrayList<>();
		if(filters.getSizeFilters() != null && !filters.getSizeFilters().isEmpty()) {
			for (DspCtgryGodFoResult v : filters.getSizeFilters()) {
				if (v.getOptValCd1().matches("-?\\d+(\\.\\d+)?")) {
					number.add(v);
				} else {
					str.add(v);
				}
			}
		}

		List<DspCtgryGodFoResult> number1 = new ArrayList<>();
		List<DspCtgryGodFoResult> number2 = new ArrayList<>();
		List<DspCtgryGodFoResult> number3 = new ArrayList<>();
		if(!number.isEmpty()) {
			for (DspCtgryGodFoResult v : number) {
				if (Integer.valueOf(v.getOptValCd1()) < 39) {
					number1.add(v);
				} else if (Integer.valueOf(v.getOptValCd1()) < 121) {
					number2.add(v);
				} else {
					number3.add(v);
				}
			}
		}

		sizeFilters.add(str);
		sizeFilters.add(number1);
		sizeFilters.add(number2);
		sizeFilters.add(number3);
		model.addAttribute("sizeFilters", sizeFilters);
		model.addAttribute("colorFilters", filters.getColorFilters());
	}
	
	public List<GoodsInterestsGodFoResult> getTodayCookiesList(InterestSearchFoDTO interestSearchFoDTO) {
		return goodsContentService.getTodayCookiesList(interestSearchFoDTO);
	}

	@Override
	public List<DspCtgryGodFoResult> selectRecoPickGodList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
 
		return dxmDisplayService.selectRecoPickGodList(dspCtgryScFrDTO);
	}
	
	@Override
	public DspCtgryScFrDTO mypageLnbCurrentCtgryList(String currentUri) throws Exception {
		return dxmDisplayService.mypageLnbCurrentCtgryList(currentUri);
	}

	@Override
	public void selectCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		dxmDisplayService.selectCtgryList(dspCtgryScFrDTO);
		
	}
	
	/**
	 * @param dspCtgryScFrDTO
	 * @return
	 * @throws Exception
	 */
	public Object selectCommonBanner(DspCtgryScFrDTO dspCtgryScFrDTO, String env) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		
		dspCtgryScFrDTO.setUpperCategory("DXM");
		dspCtgryScFrDTO.setPrcSectCd("GNRL");
		dspCtgryScFrDTO.setDspUseGrpTpCd("PC".equals(dspCtgryScFrDTO.getDevice()) ? "MAIN_PC_UPEND_TMPLAT" : "MAIN_MOBILE_UPEND_BANNER"); // THMA01A02
		DspUseGrp dspUseGrp = dxmDisplayService.selectDspUseGrp(dspCtgryScFrDTO);
		dspCtgryScFrDTO.setDspCtgryNo(dspUseGrp.getDspCtgryNo());
		dspCtgryScFrDTO.setCnrSetBaseDspCntEnd(null);

		List<DspCnrFrResult> list = dxmDisplayRepository.selectCategoryCornerList(dspCtgryScFrDTO);

		for (DspCnrFrResult dspCnrFrResult : list) {
			dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
			dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
			List<DspCnrSetFrResult> sets = dxmDisplayRepository.selectCategoryCornerSetList(dspCtgryScFrDTO);

			for (DspCnrSetFrResult dspCnrSetFrResult : sets) {

				dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
				List<DspCnrConttExt> bnrList = dxmDisplayRepository.selectCategoryCornerConttList(dspCtgryScFrDTO);
				
				if("local".equals(env) || "dev".equals(env) || "stg".equals(env)) {
					if(DisplayEnum.DVC_SECT.PC.name().equals(dspCtgryScFrDTO.getDevice())) {
						if(DisplayEnum.STG_DX_PC_TOP_BNR.toString().equals(dspCtgryScFrDTO.getCnrSn())){
							result.put("topBnr", bnrList);
						}
					}else{
						if(DisplayEnum.STG_DX_MO_TOP_BNR.toString().equals(dspCtgryScFrDTO.getCnrSn())){
							result.put("topBnr", bnrList);
						}
					}
				}else {
					if(DisplayEnum.DVC_SECT.PC.name().equals(dspCtgryScFrDTO.getDevice())) {
						if(DisplayEnum.DX_PC_TOP_BNR.toString().equals(dspCtgryScFrDTO.getCnrSn())){
							result.put("topBnr", bnrList);
						}
					}else{
						if(DisplayEnum.DX_MO_TOP_BNR.toString().equals(dspCtgryScFrDTO.getCnrSn())){
							result.put("topBnr", bnrList);
						}
					}
				}
			}
		}
		
		return result;
	}
}
