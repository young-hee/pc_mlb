package com.plgrim.ncp.cmp.display.fo;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspUseGrp;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlExtend;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.biz.display.data.DspCnrConttExt;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.data.InterestSearchFoDTO;
import com.plgrim.ncp.biz.display.result.DspCnrFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryGodFoResult;
import com.plgrim.ncp.biz.display.result.GoodsInterestsGodFoResult;
import com.plgrim.ncp.biz.display.result.MallDspFoCtgryResult;
import com.plgrim.ncp.biz.display.service.MbmDisplayService;
import com.plgrim.ncp.biz.goods.service.GoodsContentService;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.page.PageService;

@Component
public class MbmDisplaySelectComponentImpl implements MbmDisplaySelectComponent {

	@Autowired
	MbmDisplayService mbmDisplayService;
	
	@Autowired
	GoodsContentService goodsContentService;
	
	@Autowired
	MessageSourceAccessor messageSourceAccessor;

	@Override
	public MallDspFoCtgryResult lnbCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		MallDspFoCtgryResult result = new MallDspFoCtgryResult();
		result.setMallDspFoCtgryResults(mbmDisplayService.lnbCtgryList(dspCtgryScFrDTO));

		return result;
	}
	
	@Override
	public MallDspFoCtgryResult lnbMbCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		MallDspFoCtgryResult result = new MallDspFoCtgryResult();
		result.setMallDspFoCtgryResults(mbmDisplayService.lnbMbCtgryList(dspCtgryScFrDTO));

		return result;
	}

	@Override
	public Page<DspCtgryGodFoResult> selectDisplayGodList(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam)
			throws Exception {
		
		//하위카테고리별 사이즈 선택했을 경우 
		if(dspCtgryScFrDTO.getSearchConditionDspCtgrySizes() != null && dspCtgryScFrDTO.getSearchConditionDspCtgrySizes().length > 0) {
			
			dspCtgryScFrDTO.setDspCtgryScFrDTOList(new ArrayList<>());
			
			for(String searchConditionDspCtgrySize : dspCtgryScFrDTO.getSearchConditionDspCtgrySizes()) {
				
				searchConditionDspCtgrySize = URLDecoder.decode(searchConditionDspCtgrySize, "UTF-8");
				
				ObjectMapper objectMapper = new ObjectMapper();
				DspCtgryScFrDTO searchDspCtgryScFrDTO = objectMapper.readValue(searchConditionDspCtgrySize, DspCtgryScFrDTO.class);
				dspCtgryScFrDTO.getDspCtgryScFrDTOList().add(searchDspCtgryScFrDTO);
				
			}
			
		}
	 
		if("S".equals(dspCtgryScFrDTO.getDspCtgryType())){
			return mbmDisplayService.searchDisplayGodList(dspCtgryScFrDTO, pageParam);
		}else{
			return mbmDisplayService.selectDisplayGodList(dspCtgryScFrDTO, pageParam);
		}
 
		
	}

	@Override
	public DspCtgryGodFoResult selectFilterList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		// TODO Auto-generated method stub
		return StringUtils.isEmpty(dspCtgryScFrDTO.getSearchText()) ? mbmDisplayService.selectFilterList(dspCtgryScFrDTO) : mbmDisplayService.selectSearchFilterList(dspCtgryScFrDTO);
	}

	@Override
	public List<DspCnrFrResult> selectMajorCategoryContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		return mbmDisplayService.selectMajorContt(dspCtgryScFrDTO);
	}

	@Override
	public List<DspCnrFrResult> selectMainContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		DspUseGrp dspUseGrp = mbmDisplayService.selectDspUseGrp(dspCtgryScFrDTO);
		dspCtgryScFrDTO.setDspCtgryNo(dspUseGrp.getDspCtgryNo());
		return mbmDisplayService.selectMainContt(dspCtgryScFrDTO);
	}

	@Override
	public DspCnrFrResult selectMbMainContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		DspUseGrp dspUseGrp = mbmDisplayService.selectDspUseGrp(dspCtgryScFrDTO);
		dspCtgryScFrDTO.setDspCtgryNo(dspUseGrp.getDspCtgryNo());
		return mbmDisplayService.selectMbMainContt(dspCtgryScFrDTO);
	}

	@Override
	public DspCnrFrResult conttAppend(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		DspUseGrp dspUseGrp = mbmDisplayService.selectDspUseGrp(dspCtgryScFrDTO);
		dspCtgryScFrDTO.setDspCtgryNo(dspUseGrp.getDspCtgryNo());
		return mbmDisplayService.conttAppend(dspCtgryScFrDTO);
	}

	@Override
	public void selectCommonCtgryList(Model model, DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam)
			throws Exception {
		String sortColumn = dspCtgryScFrDTO.getSortColumn();
		dspCtgryScFrDTO.setMallGubun("MBM");

		mbmDisplayService.selectCtgryList(dspCtgryScFrDTO);

		if (StringUtils.isNotEmpty(sortColumn)) {
			dspCtgryScFrDTO.setSortColumn(sortColumn);
		}

		if(DisplayEnum.TWO.toString().equals(dspCtgryScFrDTO.getCurrentCtgryDpthCd()) 
			|| DisplayEnum.CTGRY_SECT_CD.OTLT_CTGRY.name().equals(dspCtgryScFrDTO.getCtgrySectCd())){
			
			dspCtgryScFrDTO.setUpperCategory(dspCtgryScFrDTO.getCtgryNoDpth1());
			model.addAttribute("ctgryContt", mbmDisplayService.selectCategoryConttList(dspCtgryScFrDTO));
		
		}else{
			dspCtgryScFrDTO.setUpperCategory(dspCtgryScFrDTO.getCtgryNoDpth2());
		}
		
		if(DisplayEnum.CTGRY_SECT_CD.OTLT_CTGRY.name().equals(dspCtgryScFrDTO.getCtgrySectCd())){
			dspCtgryScFrDTO.setUpperCategory(dspCtgryScFrDTO.getCtgryNoDpth1());
		}

		List<MallDspFoCtgryResult> ctgList = mbmDisplayService.selectCategotyNavi(dspCtgryScFrDTO);
		
		List<MallDspFoCtgryResult> category1 = 
				ctgList.stream()
				.filter(t -> t.getDspCtgryNo().equals(dspCtgryScFrDTO.getCtgryNoDpth1()))				
				.collect(Collectors.toList());
		
		List<MallDspFoCtgryResult> category2 = 
				ctgList.stream()
				.filter(t -> t.getUpperDspCtgryNo().equals(dspCtgryScFrDTO.getCtgryNoDpth1()))				
				.collect(Collectors.toList());
		
		List<MallDspFoCtgryResult> category3 = 
				ctgList.stream()
				.filter(t -> t.getUpperDspCtgryNo().equals(dspCtgryScFrDTO.getCtgryNoDpth2()))				
				.collect(Collectors.toList());
		
		
//		Map<String, Integer> category3sum = new HashMap<>();
//		for(MallDspFoCtgryResult cate2List : category2){
//			int sum = 0;
//			if(DisplayEnum.YES.toString().equals(cate2List.getLeafCtgryYn())){
//				sum = cate2List.getGodCnt();
//			}else{
//				sum = 
//					ctgList.stream()
//					.filter(t -> t.getUpperDspCtgryNo().equals(cate2List.getDspCtgryNo()))				
//					.mapToInt(t -> t.getGodCnt())
//					.sum();
//			}
//			
//			
//			category3sum.put(cate2List.getDspCtgryNo(), sum);
//		}
		
		model.addAttribute("dpth1", category1);
		model.addAttribute("dpth2", category2);
		model.addAttribute("dpth3", category3);
//		model.addAttribute("dpth3sum", category3sum);
		
		List<MallDspFoCtgryResult> naviNmList = mbmDisplayService.selectCategotyNaviNm(dspCtgryScFrDTO);
		String sTitle = "";
		
		for(MallDspFoCtgryResult list : naviNmList){
			if(DisplayEnum.ONE.toString().equals(list.getCtgryDpthCd())){
				model.addAttribute("dpth1Nm", list.getDspCtgryNm());
				sTitle = list.getDspCtgryNm();
			}else if(DisplayEnum.TWO.toString().equals(list.getCtgryDpthCd())){
				model.addAttribute("dpth2Nm", list.getDspCtgryNm());
				sTitle = list.getDspCtgryNm() + " | " + sTitle;
			}else if(DisplayEnum.THREE.toString().equals(list.getCtgryDpthCd())){
				model.addAttribute("dpth3Nm", list.getDspCtgryNm());
				sTitle = list.getDspCtgryNm() + " | " + sTitle;
			}
		}
		
		model.addAttribute("seo_title", sTitle + " | MLB");
		model.addAttribute("seo_desc", sTitle + " | MLB");
		
		Page<DspCtgryGodFoResult> godList = selectDisplayGodList(dspCtgryScFrDTO, pageParam);
		displayCommon(model, dspCtgryScFrDTO, godList);

	}

	@Override
	public void selectMbCommonCtgryList(Model model, DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam)
			throws Exception {
		String sortColumn = dspCtgryScFrDTO.getSortColumn();
		dspCtgryScFrDTO.setMallGubun("MBM");
		dspCtgryScFrDTO.setUpperCategory("MBM");
		mbmDisplayService.selectMbCtgryList(model, dspCtgryScFrDTO);

		if (StringUtils.isNotEmpty(sortColumn)) {
			dspCtgryScFrDTO.setSortColumn(sortColumn);
		}
 
		Page<DspCtgryGodFoResult> godList = selectDisplayGodList(dspCtgryScFrDTO, pageParam);
		displayCommon(model, dspCtgryScFrDTO, godList);

	}

	@Override
	public void searchDisplayGodList(Model model, DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam)
			throws Exception {

		dspCtgryScFrDTO.setDspCtgryNo("MBM");
		
		if (StringUtils.isEmpty(dspCtgryScFrDTO.getSortColumn())) {
			dspCtgryScFrDTO.setSortColumn("MD_RECOMMEND_SEQ");
		}
		
		Page<DspCtgryGodFoResult> godList = mbmDisplayService.searchDisplayGodList(dspCtgryScFrDTO, pageParam);
		dspCtgryScFrDTO.setCtgryNoDpth1("");
		dspCtgryScFrDTO.setCtgryNoDpth2("");
		dspCtgryScFrDTO.setCtgryNoDpth3("");
		
		displayCommon(model, dspCtgryScFrDTO, godList);
	}

	@Override
	public Page<GodGodEvlExtend> selectDisplayReviewList(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam)
			throws Exception {

		return mbmDisplayService.selectDisplayReviewList(dspCtgryScFrDTO, pageParam);

	}
	@Override
	public List<MallDspFoCtgryResult> bestCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
 
		return mbmDisplayService.bestCtgryList(dspCtgryScFrDTO);
	}
	@Override
	public List<GodGodEvlExtend> selectDisplayBestReviewList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		return mbmDisplayService.selectDisplayBestReviewList(dspCtgryScFrDTO);

	}
	@Override
	public MallDspFoCtgryResult  selectLnbCategory(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		 
		return mbmDisplayService.selectLnbCategory(dspCtgryScFrDTO);
	}
	private void displayCommon(Model model, DspCtgryScFrDTO dspCtgryScFrDTO, Page<DspCtgryGodFoResult> godList)
			throws Exception {
		PageService.makePageResult(godList, model);
		model.addAttribute("godList", godList.getContent());
		model.addAttribute("totalRow", godList.getTotalElements());

		DspCtgryGodFoResult filters = selectFilterList(dspCtgryScFrDTO);
		List<List<DspCtgryGodFoResult>> sizeFilters = new ArrayList<>();
		
		if(dspCtgryScFrDTO.getLowerCtgryList() == null || dspCtgryScFrDTO.getLowerCtgryList().isEmpty()) {
			
			sizeFilterCommon(filters, sizeFilters);
		
		} else {
			
			for(MallDspFoCtgryResult lowerCtgry : dspCtgryScFrDTO.getLowerCtgryList()) {
			
				DspCtgryGodFoResult lowerFilters = lowerCtgry.getDspCtgryGodFoResult();
				List<List<DspCtgryGodFoResult>> lowerSizeFilters = new ArrayList<>();
				
				sizeFilterCommon(lowerFilters, lowerSizeFilters);
				
				lowerCtgry.setSizeFilters(lowerSizeFilters);
				
			}
			
		}
		
		model.addAttribute("sizeFilters", sizeFilters);
		model.addAttribute("searchSize", dspCtgryScFrDTO.getSearchConditionSizes());
		model.addAttribute("colorFilters", filters.getColorFilters());
		model.addAttribute("searchColor", dspCtgryScFrDTO.getSearchConditionColors());
		model.addAttribute("fLocation", dspCtgryScFrDTO.getFLocation());
		model.addAttribute("dspCtgryScFrDTO", dspCtgryScFrDTO);
		model.addAttribute("sortColumn", dspCtgryScFrDTO.getSortColumn());
		
	}
	
	private void sizeFilterCommon(DspCtgryGodFoResult filters, List<List<DspCtgryGodFoResult>> sizeFilters) {
		
		List<DspCtgryGodFoResult> number = new ArrayList<>();
		List<DspCtgryGodFoResult> str = new ArrayList<>();
		for (DspCtgryGodFoResult v : filters.getSizeFilters()) {
			if (v.getOptValCd1().matches("-?\\d+(\\.\\d+)?")) {
				number.add(v);
			} else {
				str.add(v);
			}
		}
		
		Collections.sort(number, new Comparator<DspCtgryGodFoResult>(){

			@Override
			public int compare(DspCtgryGodFoResult dspCtgryGodFoResult1, DspCtgryGodFoResult dspCtgryGodFoResult2) {
				
				return (Integer.parseInt(dspCtgryGodFoResult1.getOptValCd1()) < Integer.parseInt(dspCtgryGodFoResult2.getOptValCd1())) ? -1 : 1;
			
			}
			
		});

		sizeFilters.add(str);
		sizeFilters.add(number);
		
	}
	
	public List<GoodsInterestsGodFoResult> getTodayCookiesList(InterestSearchFoDTO interestSearchFoDTO) {
		return goodsContentService.getTodayCookiesList(interestSearchFoDTO);
	}

	@Override
	public List<DspCtgryGodFoResult> selectRecoPickGodList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
 
		return mbmDisplayService.selectRecoPickGodList(dspCtgryScFrDTO);
	}
	
	@Override
	public DspCtgryScFrDTO mypageLnbCurrentCtgryList(String currentUri) throws Exception {
		return mbmDisplayService.mypageLnbCurrentCtgryList(currentUri);
	}

	@Override
	public void selectCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		mbmDisplayService.selectCtgryList(dspCtgryScFrDTO);
		
	}
	
	@Override
	public List<DspCnrConttExt> selectCnrConttList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		return mbmDisplayService.selectCnrConttList(dspCtgryScFrDTO);
		
	}
	
	/**
	 * GNB카테고리 조회용
	 * @param dspCtgry
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object selectGNBCategotyList(DspCtgry dspCtgry) throws Exception {
		return mbmDisplayService.selectGNBCategotyList(dspCtgry);		
	}

	@Override
	public Object selectCommonBanner(DspCtgryScFrDTO dspCtgryScFrDTO, String env) throws Exception {
		return mbmDisplayService.selectCommonBanner(dspCtgryScFrDTO, env);
	}
	
	@Override
	public Object selectCategotyNavi(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		return mbmDisplayService.selectCategotyNavi(dspCtgryScFrDTO);
	}
	
	@Override
	public void selectGodTeamList(Model model, DspCtgryScFrDTO dspCtgryScFrDTO, Locale currentLocale) throws Exception {
		
		//내셔널 팀 정보
		List<DspCtgryGodFoResult> nationalTeamList = new ArrayList<>();
		//아메리칸 팀 정보
		List<DspCtgryGodFoResult> americanTeamList = new ArrayList<>();
		//팀코드 조회 정보
		dspCtgryScFrDTO.setFilterType( "TEAM" );
		List<DspCtgryGodFoResult> teamCdList = StringUtils.isEmpty( dspCtgryScFrDTO.getSearchText() ) ? new ArrayList<>() : selectFilterList( dspCtgryScFrDTO ).getTeamFilters();
		
		//리그별 팀정보 조회를 위해 프로퍼티 키값 및 팀정보 list 객체 초기화
		for( DisplayEnum.League league : DisplayEnum.League.values() ) {
			
			List<DspCtgryGodFoResult> teamList 	= null;
			
			switch( league ) {
				case NATIONAL :
					teamList 	= nationalTeamList;
					break;
				case AMERICAN :
					teamList	= americanTeamList;
					break;
				default 	  :
					teamList	= new ArrayList<>();
					break;
			}
			//팀 정보 셋팅
			if( teamCdList.isEmpty() && StringUtils.isEmpty( dspCtgryScFrDTO.getSearchText() ) ) {
				
				for( DisplayEnum.League.Team team : league.getTeams() ) {
					
					String messageKey = "display.god.team." + league.getLeagueCd() + "." + team.getTeamCd();
					
					DspCtgryGodFoResult teamInfo = new DspCtgryGodFoResult();
					teamInfo.setTeamCd( team.getTeamCd() );
					teamInfo.setTeamNm(	messageSourceAccessor.getMessage( messageKey, Locale.ENGLISH ) );
					teamInfo.setTeamAltNm( messageSourceAccessor.getMessage( messageKey, currentLocale ) );
					
					teamList.add( teamInfo );
					
				}
				
			} else {
				
				for( DspCtgryGodFoResult teamCd : teamCdList ) {
					
					if( "00".equals( teamCd.getOptValCd1() ) ) {
						
						continue;
						
					}
					
					for( DisplayEnum.League.Team team : league.getTeams() ) {
						
						if( teamCd.getOptValCd1().equals( team.getTeamCd() ) ) {
							
							String messageKey = "display.god.team." + league.getLeagueCd() + "." + team.getTeamCd();
							
							DspCtgryGodFoResult teamInfo = new DspCtgryGodFoResult();
							teamInfo.setTeamCd( team.getTeamCd() );
							teamInfo.setTeamNm(	messageSourceAccessor.getMessage( messageKey, Locale.ENGLISH ) );
							teamInfo.setTeamAltNm( messageSourceAccessor.getMessage( messageKey, currentLocale ) );
							
							teamList.add( teamInfo );
							
							break;
							
						}
						
					}
					
				}

			}
			
			//팀 영문명 오름차순으로 정렬
			Collections.sort( teamList, new Comparator<DspCtgryGodFoResult>() {
				
				@Override
				public int compare( DspCtgryGodFoResult dspCtgryGodFoResult1, DspCtgryGodFoResult dspCtgryGodFoResult12 ) {
					
					return dspCtgryGodFoResult1.getTeamNm().compareTo( dspCtgryGodFoResult12.getTeamNm() );
				
				}
				
			});
			
		}
		
		model.addAttribute( "nationalTeamFilters", nationalTeamList );								//내셔널리그 팀 목록
		model.addAttribute( "americanTeamFilters", americanTeamList );								//아메리칸리그 팀 목록
		model.addAttribute( "searchTeamCd"		 , dspCtgryScFrDTO.getSearchConditionTeamCds() );	//팀 검색 조건
		
	}
	
	@Override
	public MallDspFoCtgryResult selectLookBookCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		if(dspCtgryScFrDTO.getUpperCategory() == null) {
			dspCtgryScFrDTO.setUpperCategory(mbmDisplayService.selectLoobookCtgryNo(dspCtgryScFrDTO));
		}
		
		MallDspFoCtgryResult result = new MallDspFoCtgryResult();
		result.setMallDspFoCtgryResults(mbmDisplayService.selectLookbookCtgryList(dspCtgryScFrDTO));

		return result;
	}
	
	@Override
	public List<?> selectLookBookCtgryConttList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		if(DisplayEnum.TWO.toString().equals(dspCtgryScFrDTO.getCtgryDpthCd())) {
			
			return mbmDisplayService.selectCategoryConttList(dspCtgryScFrDTO);
			
		} else {
			
			return mbmDisplayService.selectLookbookCtgryConttList(dspCtgryScFrDTO);
			
		}
		
	}
	
	@Override
	public void selectMainRankingGod(Model model, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		List<DspCtgryGodFoResult> godList = mbmDisplayService.selectMainRankingGod(dspCtgryScFrDTO);
		
		List<DspCtgryGodFoResult> krList = 
				godList.stream()
				.filter(t -> t.getNationCd().equals("KR"))
				.sorted((t, t2) -> t.getGodRank().compareTo(t2.getGodRank()))
				.limit(20)
				.collect(Collectors.toList());

//		List<DspCtgryGodFoResult> hkList = 
//				godList.stream()
//				.filter(t -> t.getNationCd().equals("HK"))
//				.sorted((t, t2) -> t.getGodRank().compareTo(t2.getGodRank()))
//				.limit(10)
//				.collect(Collectors.toList());
//		
//		List<DspCtgryGodFoResult> twList = 
//				godList.stream()
//				.filter(t -> t.getNationCd().equals("TW"))
//				.sorted((t, t2) -> t.getGodRank().compareTo(t2.getGodRank()))
//				.limit(10)
//				.collect(Collectors.toList());
//		
//		List<DspCtgryGodFoResult> thList = 
//				godList.stream()
//				.filter(t -> t.getNationCd().equals("TH"))
//				.sorted((t, t2) -> t.getGodRank().compareTo(t2.getGodRank()))
//				.limit(10)
//				.collect(Collectors.toList());
		
		model.addAttribute("krList", krList);
//		model.addAttribute("hkList", hkList);
//		model.addAttribute("twList", twList);
//		model.addAttribute("thList", thList);
	}

	public List<?> selectLoobookCornerConttList(DspCtgryScFrDTO dspCtgryScFrDTO, Model model, String domain) throws Exception {

		List<DspCnrConttExt> lookbookConttList = mbmDisplayService.selectLoobookCornerConttList(dspCtgryScFrDTO);
		
		List<DspCnrConttExt> lookbookImgConttList = new ArrayList<>();
		List<DspCnrConttExt> lookbookGodConttList = new ArrayList<>();
		
		for(DspCnrConttExt lookbookContt : lookbookConttList) {
			
			if("IMG_BANNER".equals(lookbookContt.getConttTpCd())) {
				
				lookbookContt.setDspCnrConttExtList(new ArrayList<>());
				lookbookImgConttList.add(lookbookContt);
				
			} else if("GOD".equals(lookbookContt.getConttTpCd())) {
				
				lookbookGodConttList.add(lookbookContt);
				
			}
			
		}
		
		for(DspCnrConttExt lookbookGodContt : lookbookGodConttList) {
			
			String dspCtgryNo = lookbookGodContt.getDspCtgryNo();
			
			for(DspCnrConttExt lookbookImgContt : lookbookImgConttList) {
				
				if(lookbookImgContt.getDspCtgryNo().equals(dspCtgryNo)) {
					
					lookbookImgContt.getDspCnrConttExtList().add(lookbookGodContt);
					break;
					
				}
				
			}
			
		}
		
		if(dspCtgryScFrDTO.getDspCtgryNo() != null) {
			
			for(int i = 0; i < lookbookImgConttList.size(); i++) {
				
				if(lookbookImgConttList.get(i).getDspCtgryNo().equals(dspCtgryScFrDTO.getDspCtgryNo())) {

					List<DspCnrConttExt> subList = new ArrayList<>(lookbookImgConttList.subList(i, lookbookImgConttList.size()));
					lookbookImgConttList.removeAll(subList);
					lookbookImgConttList.addAll(0, subList);

					model.addAttribute("og_title", lookbookImgConttList.get(0).getDspCtgryNm());
					model.addAttribute("og_image", lookbookImgConttList.get(0).getImgFileUrl() + "/" + lookbookImgConttList.get(0).getImgFileNm());
					model.addAttribute("og_desc", StringUtils.isEmpty(lookbookImgConttList.get(0).getImgDscr()) ? lookbookImgConttList.get(0).getImgAltrtvCont() : lookbookImgConttList.get(0).getImgDscr());
					model.addAttribute("og_url", domain + "/lookbook/" + dspCtgryScFrDTO.getUpperCategory() + "/view?dspCtgryNo=" + lookbookImgConttList.get(0).getDspCtgryNo());
					break;

				}

			}

		} else {

			if (!lookbookImgConttList.isEmpty()) {

				model.addAttribute("og_title", lookbookImgConttList.get(0).getDspCtgryNm());
				model.addAttribute("og_image", lookbookImgConttList.get(0).getImgFileUrl() + "/" + lookbookImgConttList.get(0).getImgFileNm());
				model.addAttribute("og_desc", StringUtils.isEmpty(lookbookImgConttList.get(0).getImgDscr()) ? lookbookImgConttList.get(0).getImgAltrtvCont() : lookbookImgConttList.get(0).getImgDscr());
                model.addAttribute("og_url", domain + "/lookbook/" + dspCtgryScFrDTO.getUpperCategory() + "/view?dspCtgryNo=" + lookbookImgConttList.get(0).getDspCtgryNo());

			}
			
		}
		
		return lookbookImgConttList;
		
	}
	
	public DspCnrConttExt selectLoobookGodInfo(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		return mbmDisplayService.selectLoobookGodInfo(dspCtgryScFrDTO);
		
	}

	@Override
	public Page<DspCtgryGodFoResult> searchDisplayGodListPage(Model model, DspCtgryScFrDTO dspCtgryScFrDTO,
			PageParam pageParam) throws Exception {
		dspCtgryScFrDTO.setDspCtgryNo("MBM");
		
		if (StringUtils.isEmpty(dspCtgryScFrDTO.getSortColumn())) {
			dspCtgryScFrDTO.setSortColumn("MD_RECOMMEND_SEQ");
		}
		
		Page<DspCtgryGodFoResult> godList = mbmDisplayService.searchDisplayGodList(dspCtgryScFrDTO, pageParam);
		dspCtgryScFrDTO.setCtgryNoDpth1("");
		dspCtgryScFrDTO.setCtgryNoDpth2("");
		dspCtgryScFrDTO.setCtgryNoDpth3("");
		
		displayCommon(model, dspCtgryScFrDTO, godList);
		
		return godList;
	}

	@Override
	public void selectMobileLookBookCtgryList(Model model, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		dspCtgryScFrDTO.setMallGubun("MBM");
		
		if("".equals(dspCtgryScFrDTO.getDspCtgryNo()) || null == dspCtgryScFrDTO.getDspCtgryNo()){
 
			
			mbmDisplayService.selectLookbookCtgryList(dspCtgryScFrDTO);
			
		 	dspCtgryScFrDTO.setMallGubun(mbmDisplayService.selectLoobookCtgryNo(dspCtgryScFrDTO));
		 	dspCtgryScFrDTO.setSortSeq("1");
		 	dspCtgryScFrDTO.setDspCtgryNo(mbmDisplayService.selectLoobookCtgryNo(dspCtgryScFrDTO));
		 	dspCtgryScFrDTO.setMallGubun("MBM");
		}
		
		if("".equals(dspCtgryScFrDTO.getCurrentCtgryDpthCd()) || null == dspCtgryScFrDTO.getCurrentCtgryDpthCd()){
			dspCtgryScFrDTO.setCurrentCtgryDpthCd(DisplayEnum.TWO.toString());
		}
		
		mbmDisplayService.selectCtgryList(dspCtgryScFrDTO);
		
		List<MallDspFoCtgryResult> ctgList = mbmDisplayService.selectCategotyNavi(dspCtgryScFrDTO);
		
		List<MallDspFoCtgryResult> category2 = 
					ctgList.stream()
					.filter(t -> t.getCtgryDpthCd().equals("2"))				
					.collect(Collectors.toList());
		
		List<MallDspFoCtgryResult> category3 = null;
		if(DisplayEnum.THREE.toString().equals(dspCtgryScFrDTO.getCurrentCtgryDpthCd())){
			category3 = 
					ctgList.stream()
					.filter(t -> t.getUpperDspCtgryNo().equals(dspCtgryScFrDTO.getUpperCategory()))
					.filter(t -> t.getCtgryDpthCd().equals("3"))
					.collect(Collectors.toList());
		}else{
			category3 = 
					ctgList.stream()
					.filter(t -> t.getUpperDspCtgryNo().equals(dspCtgryScFrDTO.getDspCtgryNo()))
					.filter(t -> t.getCtgryDpthCd().equals("3"))
					.collect(Collectors.toList());
		}
						
		
		List<MallDspFoCtgryResult> category4 = 
						ctgList.stream()
						.filter(t -> t.getUpperDspCtgryNo().equals(dspCtgryScFrDTO.getDspCtgryNo()))
						.filter(t -> t.getCtgryDpthCd().equals("4"))
						.collect(Collectors.toList());
		
		model.addAttribute("dpth2", category2);
		model.addAttribute("dpth3", category3);
		model.addAttribute("dpth4", category4);
		
		List<DspCnrConttExt> ctgryConttList = new ArrayList<>();
		if(DisplayEnum.CTGRY_SECT_CD.LOOKBOOK_CTGRY.name().equals(dspCtgryScFrDTO.getCtgrySectCd())){
			dspCtgryScFrDTO.setUpperCategory(dspCtgryScFrDTO.getDspCtgryNo());
			
			ctgryConttList = mbmDisplayService.selectCategoryConttList(dspCtgryScFrDTO);
			
			model.addAttribute("ctgryContt", ctgryConttList);
		
		}
		
		List<MallDspFoCtgryResult> naviNmList = mbmDisplayService.selectCategotyNaviNm(dspCtgryScFrDTO);
		
		for(MallDspFoCtgryResult list : naviNmList){
			if(DisplayEnum.TWO.toString().equals(list.getCtgryDpthCd())){
				model.addAttribute("dpth2Nm", list.getDspCtgryNm());
				model.addAttribute("og_title", list.getDspCtgryNm());
				if(ctgryConttList.size() > 0) {
					model.addAttribute("og_image", ctgryConttList.get(0).getImgFileUrl() + "/" + ctgryConttList.get(0).getImgFileNm());
					model.addAttribute("og_desc", ctgryConttList.get(0).getImgAltrtvCont());
				}
			}else if(DisplayEnum.THREE.toString().equals(list.getCtgryDpthCd())){
				model.addAttribute("dpth3Nm", list.getDspCtgryNm());
				model.addAttribute("og_title", list.getDspCtgryNm());
				if(category4.size() > 0) {
					model.addAttribute("og_image", category4.get(0).getRprstImgFileUrl() + "/" + category4.get(0).getRprstImgFileNm());
					model.addAttribute("og_desc", category4.get(0).getRprstImgAltrtvCont());
				}
			}
		}
		
		model.addAttribute("dspCtgryScFrDTO", dspCtgryScFrDTO);
	}
	
	@Override
	public DspCnrFrResult selectUnexhibitedContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		DspUseGrp dspUseGrp = mbmDisplayService.selectDspUseGrp(dspCtgryScFrDTO);
		dspCtgryScFrDTO.setDspCtgryNo(dspUseGrp.getDspCtgryNo());
		return mbmDisplayService.selectUnexhibitedContt(dspCtgryScFrDTO);
	}
}
