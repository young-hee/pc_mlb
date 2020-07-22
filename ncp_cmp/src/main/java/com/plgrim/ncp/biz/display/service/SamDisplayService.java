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
 * @since       2015. 7. 24       
 */
package com.plgrim.ncp.biz.display.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspUseGrp;
import com.plgrim.ncp.base.entities.datasource1.dsp.RecoPick;
import com.plgrim.ncp.base.entities.datasource1.dsp.RecoPickSDO;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.biz.display.data.DspCnrConttExt;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.repository.SamDisplayRepository;
import com.plgrim.ncp.biz.display.result.DspCnrFrResult;
import com.plgrim.ncp.biz.display.result.DspCnrSetFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryGodFoResult;
import com.plgrim.ncp.biz.display.result.MallDspFoCtgryResult;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.repository.MemberActivitySelectRepository;
import com.plgrim.ncp.biz.member.result.MypageWishFoResult;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SamDisplayService extends AbstractService {
	@Autowired
	private SamDisplayRepository samDisplayRepository;
 
	@Autowired
	private MemberActivitySelectRepository memberActivitySelectRepository;
	public DspUseGrp selectDspUseGrp(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		DspUseGrp dspUseGrp = new DspUseGrp();
		dspUseGrp.setDspUseGrpTpCd(dspCtgryScFrDTO.getDspUseGrpTpCd());
		return samDisplayRepository.selectDspUseGrpTpCd(dspUseGrp);

	}

	public List<MallDspFoCtgryResult> lnbCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		dspCtgryScFrDTO.setCtgryDpthCd(DisplayEnum.ONE.toString());
		List<MallDspFoCtgryResult> results = samDisplayRepository.lnbCtgryList(dspCtgryScFrDTO);

		for (MallDspFoCtgryResult mbm : results) {

			if (mbm.getDspCtgryNo().equals(dspCtgryScFrDTO.getCtgryNoDpth1())) {
				dspCtgryScFrDTO.setCtgryDpthCd(DisplayEnum.TWO.toString());
				addCtgryList(mbm, dspCtgryScFrDTO);
				for (MallDspFoCtgryResult v : mbm.getMallDspFoCtgryResults()) {
					if (v.getDspCtgryNo().equals(dspCtgryScFrDTO.getCtgryNoDpth2())) {
						dspCtgryScFrDTO.setCtgryDpthCd(DisplayEnum.THREE.toString());
						addCtgryList(v, dspCtgryScFrDTO);

						for (MallDspFoCtgryResult d : v.getMallDspFoCtgryResults()) {
							if (d.getDspCtgryNo().equals(dspCtgryScFrDTO.getCtgryNoDpth3())) {
								dspCtgryScFrDTO.setFLocation(d.getDspCtgryNm());
								dspCtgryScFrDTO.setSortColumn(d.getSortColumn());
							}

						}

					}

				}
			}
		}
		return results;
	}

	public List<MallDspFoCtgryResult> lnbMbCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		dspCtgryScFrDTO.setCtgryDpthCd(DisplayEnum.ONE.toString());
		List<MallDspFoCtgryResult> results = samDisplayRepository.lnbCtgryList(dspCtgryScFrDTO);

		for (MallDspFoCtgryResult mbm : results) {
			dspCtgryScFrDTO.setCtgryDpthCd(DisplayEnum.TWO.toString());
			addCtgryList(mbm, dspCtgryScFrDTO);
			for (MallDspFoCtgryResult v : mbm.getMallDspFoCtgryResults()) {
				dspCtgryScFrDTO.setCtgryDpthCd(DisplayEnum.THREE.toString());
				addCtgryList(v, dspCtgryScFrDTO);
				for (MallDspFoCtgryResult d : v.getMallDspFoCtgryResults()) {
					if (d.getDspCtgryNo().equals(dspCtgryScFrDTO.getCtgryNoDpth3())) {
						dspCtgryScFrDTO.setFLocation(d.getDspCtgryNm());
						dspCtgryScFrDTO.setSortColumn(d.getSortColumn());
					}
				}
			}

		}
		return results;
	}

	public void selectCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		MallDspFoCtgryResult dspCtgry = samDisplayRepository.selectCtgryList(dspCtgryScFrDTO);

		if (dspCtgry != null) {
			dspCtgryScFrDTO.setSortColumn(dspCtgry.getSortColumn());
			dspCtgryScFrDTO.setFLocation(dspCtgry.getDspCtgryNm());
			dspCtgryScFrDTO.setMetaSjNm(dspCtgry.getMetaSjNm());
			dspCtgryScFrDTO.setMetaDscrCont(dspCtgry.getMetaDscrCont());
			dspCtgryScFrDTO.setMetaKwd(dspCtgry.getMetaKwd());
			dspCtgryScFrDTO.setCtgrySectCd(dspCtgry.getCtgrySectCd());
			dspCtgryScFrDTO.setUpperCategory(dspCtgry.getUpperDspCtgryNo());
			
			DspCtgryScFrDTO subCtgryScFrDTO = new DspCtgryScFrDTO();
			subCtgryScFrDTO.setUpperCategory(dspCtgry.getDspCtgryNo());
			subCtgryScFrDTO.setLang(dspCtgryScFrDTO.getLang());
			
			dspCtgryScFrDTO.setLowerCtgryList(samDisplayRepository.lnbCtgryList(subCtgryScFrDTO));
		}
	}

	public void selectMbCtgryList(Model model, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		MallDspFoCtgryResult dspCtgry = samDisplayRepository.selectCtgryList(dspCtgryScFrDTO);
		List<MallDspFoCtgryResult> mbmDspFoCtgryResults = new ArrayList<>();
		dspCtgryScFrDTO.setSortColumn(dspCtgry.getSortColumn());
		dspCtgryScFrDTO.setFLocation(dspCtgry.getDspCtgryNm());
		dspCtgryScFrDTO.setMetaSjNm(dspCtgry.getMetaSjNm());
		dspCtgryScFrDTO.setMetaDscrCont(dspCtgry.getMetaDscrCont());
		dspCtgryScFrDTO.setMetaKwd(dspCtgry.getMetaKwd());
		
		if ("Y".equals(dspCtgry.getLeafCtgryYn())) {
			dspCtgryScFrDTO.setUpperCategory(dspCtgry.getUpperDspCtgryNo());
			mbmDspFoCtgryResults.add(dspCtgry);
			mbmDspFoCtgryResults.addAll(samDisplayRepository.selectLeafCtgryList(dspCtgryScFrDTO));
		} else {
			dspCtgryScFrDTO.setUpperCategory(dspCtgry.getDspCtgryNo());
			mbmDspFoCtgryResults.addAll(samDisplayRepository.lnbCtgryList(dspCtgryScFrDTO));
		}
		model.addAttribute("upperDspCtgryNo", dspCtgry.getUpperDspCtgryNo());
		model.addAttribute("category", mbmDspFoCtgryResults);

	}

	private void addCtgryList(MallDspFoCtgryResult mallDspFoCtgryResult, DspCtgryScFrDTO dspCtgryScFrDTO)
			throws Exception {
		dspCtgryScFrDTO.setUpperCategory(mallDspFoCtgryResult.getDspCtgryNo());
		dspCtgryScFrDTO.setSortColumn(mallDspFoCtgryResult.getSortColumn());
		dspCtgryScFrDTO.setFLocation(mallDspFoCtgryResult.getDspCtgryNm());
		mallDspFoCtgryResult.setMallDspFoCtgryResults(samDisplayRepository.lnbCtgryList(dspCtgryScFrDTO));
	}

	public Page<DspCtgryGodFoResult> selectDisplayGodList(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam)
			throws Exception {
		RepositoryHelper.makePageEntityIndex(dspCtgryScFrDTO, pageParam);
		return samDisplayRepository.selectDisplayGodList(dspCtgryScFrDTO, pageParam);
	}

	public DspCtgryGodFoResult selectFilterList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		DspCtgryGodFoResult dspCtgryGodFoResult = new DspCtgryGodFoResult();

		dspCtgryScFrDTO.setFilterType("SIZE");
		
		if(dspCtgryScFrDTO.getLowerCtgryList() == null || dspCtgryScFrDTO.getLowerCtgryList().isEmpty()) {
			
			dspCtgryGodFoResult.setSizeFilters(samDisplayRepository.selectFilterList(dspCtgryScFrDTO));

		} else {
			
			for(MallDspFoCtgryResult mallDspFoCtgryResult : dspCtgryScFrDTO.getLowerCtgryList()) {
				
				DspCtgryGodFoResult lowerCtgryGodFoResult = new DspCtgryGodFoResult();
				
				mallDspFoCtgryResult.setDspCtgryGodFoResult(lowerCtgryGodFoResult);
				
				DspCtgryScFrDTO lowerCtgryScFrDTO = new DspCtgryScFrDTO();
				lowerCtgryScFrDTO.setFilterType("SIZE");
				lowerCtgryScFrDTO.setDspCtgryNo(mallDspFoCtgryResult.getDspCtgryNo());
				
				lowerCtgryGodFoResult.setSizeFilters(samDisplayRepository.selectFilterList(lowerCtgryScFrDTO));
				
			}
			
		}

		dspCtgryScFrDTO.setFilterType("COLOR");
		dspCtgryGodFoResult.setColorFilters(samDisplayRepository.selectFilterList(dspCtgryScFrDTO));

		return dspCtgryGodFoResult;
	}
	
	public DspCtgryGodFoResult selectSearchFilterList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		DspCtgryGodFoResult dspCtgryGodFoResult = new DspCtgryGodFoResult();
		
		if(StringUtils.isEmpty(dspCtgryScFrDTO.getFilterType())) {
			
			dspCtgryScFrDTO.setFilterType("SIZE");
			dspCtgryGodFoResult.setSizeFilters(samDisplayRepository.selectSearchFilterList(dspCtgryScFrDTO));
			
			dspCtgryScFrDTO.setFilterType("COLOR");
			dspCtgryGodFoResult.setColorFilters(samDisplayRepository.selectSearchFilterList(dspCtgryScFrDTO));
			
		} else {
			
			dspCtgryGodFoResult.setTeamFilters(samDisplayRepository.selectSearchFilterList(dspCtgryScFrDTO));
		
		}
		
		return dspCtgryGodFoResult;
	}

	public DspCnrFrResult selectMajorCategoryContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		List<DspCnrFrResult> list = samDisplayRepository.selectCategoryCornerList(dspCtgryScFrDTO);
		List<DspCnrConttExt> cnrConttList = new ArrayList<>();
		List<DspCnrConttExt> cnrGodConttList = new ArrayList<>();

		for (DspCnrFrResult dspCnrFrResult : list) {
			dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
			dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
			List<DspCnrSetFrResult> sets = samDisplayRepository.selectCategoryCornerSetList(dspCtgryScFrDTO);

			for (DspCnrSetFrResult dspCnrSetFrResult : sets) {

				dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
				cnrConttList.addAll(samDisplayRepository.selectCategoryCornerConttList(dspCtgryScFrDTO));
				cnrGodConttList.addAll(samDisplayRepository.selectCategoryCornerGodConttList(dspCtgryScFrDTO));
			}
		}
		DspCnrFrResult result = new DspCnrFrResult();

		result.setCnrConttList(cnrConttList);
		result.setCnrGodConttList(cnrGodConttList);
		return result;

	}

	public List<DspCnrFrResult> selectMainContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
//		DspCnrFrResult result = selectMainCommonContt(dspCtgryScFrDTO);
		
		List<DspCnrFrResult> result = samDisplayRepository.selectCategoryCornerGrpList(dspCtgryScFrDTO);

//		dspCtgryScFrDTO.setDspUseGrpTpCd("MAIN_PC_UPEND_TMPLAT"); // THMA01A02
//		DspUseGrp dspUseGrp = selectDspUseGrp(dspCtgryScFrDTO);
//		dspCtgryScFrDTO.setDspCtgryNo(dspUseGrp.getDspCtgryNo());
//		dspCtgryScFrDTO.setCnrSetBaseDspCntEnd(null);
//		result.setCnrBnrConttList(selectCnrBnrConttList(dspCtgryScFrDTO));

		return result;

	}

	public DspCnrFrResult selectMbMainContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		DspCnrFrResult result = selectMainCommonContt(dspCtgryScFrDTO);
		dspCtgryScFrDTO.setDspUseGrpTpCd("MAIN_MOBILE_UPEND_BANNER"); // THMA01A02
		DspUseGrp dspUseGrp = selectDspUseGrp(dspCtgryScFrDTO);
		dspCtgryScFrDTO.setDspCtgryNo(dspUseGrp.getDspCtgryNo());
		dspCtgryScFrDTO.setCnrSetBaseDspCntEnd(null);
		result.setCnrBnrConttList(selectCnrBnrConttList(dspCtgryScFrDTO));

		return result;

	}

	private DspCnrFrResult selectMainCommonContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		DspCnrFrResult result = new DspCnrFrResult();

		List<DspCnrFrResult> list = samDisplayRepository.selectCategoryCornerList(dspCtgryScFrDTO);
		List<DspCnrFrResult> newArrivalList = new ArrayList<>();
		List<DspCnrFrResult> cnrMainConttList = new ArrayList<>();
		int count = 0;
		for (DspCnrFrResult dspCnrFrResult : list) {
			if (count > 0) {
				dspCtgryScFrDTO.setCnrSetBaseDspCntEnd(dspCnrFrResult.getCnrSetBaseDspCnt());
			}
			dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
			dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
			List<DspCnrSetFrResult> sets = samDisplayRepository.selectCategoryCornerSetList(dspCtgryScFrDTO);

			for (DspCnrSetFrResult dspCnrSetFrResult : sets) {
				dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
				dspCnrSetFrResult.setCnrConttExList(samDisplayRepository.selectMainCornerConttList(dspCtgryScFrDTO));
			}
			if (!sets.isEmpty()) {
				dspCnrFrResult.setDspCnrSetList(sets);
				if (count == 0) {
					newArrivalList.add(dspCnrFrResult);
				} else if (count > 0) {
					cnrMainConttList.add(dspCnrFrResult);
				}
			}
			count++;
		}
		
		if(!"PC".equals(dspCtgryScFrDTO.getDevice())){
		
			MypageWishFoResult mypageWishFoResult = new MypageWishFoResult();
			MypageFoDTO dto = new MypageFoDTO();
			if (ContextService.hasRole()) { // 회원
				Mbr mbr = ((SecurityUserDetail) ContextService.getCurrentUserDetail()).getMbr();
				dto.setMbrNo(mbr.getMbrNo());

			}
			for (DspCnrFrResult dspCnrFrResult : newArrivalList) {

				for (DspCnrSetFrResult sets : dspCnrFrResult.getDspCnrSetList()) {
					for (DspCnrConttExt con : sets.getCnrConttExList()) {

						dto.setGodNo(con.getGodNo());
						mypageWishFoResult = memberActivitySelectRepository.selectGodWishListCount(dto);
						con.setWishListYn(mypageWishFoResult.getWishListYn());
						con.setWishListCount(mypageWishFoResult.getWishListCount());
						
					}
				}

			}
		}

		result.setCnrMainConttList(cnrMainConttList);
		result.setNewArrivalList(newArrivalList);
		return result;

	}

	public DspCnrFrResult conttAppend(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		DspCnrFrResult result = new DspCnrFrResult();

		List<DspCnrFrResult> list = samDisplayRepository.selectCategoryCornerList(dspCtgryScFrDTO);
		List<DspCnrFrResult> cnrMainConttList = new ArrayList<>();
		int count = 0;
		for (DspCnrFrResult dspCnrFrResult : list) {
			if (count > 0) {

				dspCtgryScFrDTO.setCnrSetBaseDspCnt(dspCnrFrResult.getCnrSetBaseDspCnt());
				dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
				dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
				List<DspCnrSetFrResult> sets = samDisplayRepository.selectCategoryCornerSetList(dspCtgryScFrDTO);

				for (DspCnrSetFrResult dspCnrSetFrResult : sets) {
					dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
					dspCnrSetFrResult
							.setCnrConttExList(samDisplayRepository.selectMainCornerConttList(dspCtgryScFrDTO));
				}
				if (!sets.isEmpty()) {
					dspCnrFrResult.setDspCnrSetList(sets);
					cnrMainConttList.add(dspCnrFrResult);
				}
			}

			count++;
		}

		result.setCnrMainConttList(cnrMainConttList);

		return result;

	}

	public Page<DspCtgryGodFoResult> searchDisplayGodList(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam)
			throws Exception {
		RepositoryHelper.makePageEntityIndex(dspCtgryScFrDTO, pageParam);
		return samDisplayRepository.searchDisplayGodList(dspCtgryScFrDTO, pageParam);
	}
	public List<DspCtgryGodFoResult> selectRecoPickGodList(DspCtgryScFrDTO dspCtgryScFrDTO)
			throws Exception {
		List<RecoPickSDO> recoPickSDOs = new ArrayList<>();
		if (dspCtgryScFrDTO.getRecoPicks() != null && dspCtgryScFrDTO.getRecoPicks().size() > 0) {

			for (RecoPick p : dspCtgryScFrDTO.getRecoPicks()) {
				RecoPickSDO sdo = new RecoPickSDO();
				BeanUtils.copyProperties(p, sdo);
				recoPickSDOs.add(sdo);
			}
		}
		dspCtgryScFrDTO.setRecoPickSDOs(recoPickSDOs);
		return samDisplayRepository.selectRecoPickGodList(dspCtgryScFrDTO);
	}

	public List<DspCnrConttExt> selectCnrConttList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		List<DspCnrFrResult> list = samDisplayRepository.selectCategoryCornerCnrList(dspCtgryScFrDTO);
		List<DspCnrConttExt> cnrConttList = new ArrayList<>();
		List<DspCnrSetFrResult> sets = new ArrayList<>();

		for (DspCnrFrResult dspCnrFrResult : list) {

			dspCtgryScFrDTO.setCnrSetBaseDspCnt(dspCnrFrResult.getCnrSetBaseDspCnt());
			dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
			dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
			
			sets.addAll(samDisplayRepository.selectCategoryCornerSetList(dspCtgryScFrDTO));
			
		}
		
		Collections.sort(sets, new Comparator<DspCnrSetFrResult>(){
			@Override
			public int compare(DspCnrSetFrResult dspCnrSetFrResult1, DspCnrSetFrResult dspCnrSetFrResult2) {
				return (dspCnrSetFrResult1.getSortSeq() < dspCnrSetFrResult2.getSortSeq()) ? -1 : 1;
			}
		});
		
		for (DspCnrSetFrResult dspCnrSetFrResult : sets) {
			dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrSetFrResult.getCnrSn()));
			dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
			dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrSetFrResult.getCnrTpGrpSn());
			cnrConttList.addAll(samDisplayRepository.selectMainCornerConttList(dspCtgryScFrDTO));
		}
		
		return cnrConttList;
	}
	
	public List<DspCnrConttExt> selectCnrBnrConttList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		List<DspCnrFrResult> list = samDisplayRepository.selectCategoryCornerList(dspCtgryScFrDTO);
		List<DspCnrConttExt> cnrBnrConttList = new ArrayList<>();

		for (DspCnrFrResult dspCnrFrResult : list) {

			dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
			dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
			List<DspCnrSetFrResult> sets = samDisplayRepository.selectCategoryCornerSetList(dspCtgryScFrDTO);

			for (DspCnrSetFrResult dspCnrSetFrResult : sets) {
				dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
				cnrBnrConttList.addAll(samDisplayRepository.selectMainCornerConttList(dspCtgryScFrDTO));
			}
		}
		return cnrBnrConttList;

	}

	public Page<GodGodEvlExtend> selectDisplayReviewList(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam)
			throws Exception {
		RepositoryHelper.makePageEntityIndex(dspCtgryScFrDTO, pageParam);

		return samDisplayRepository.selectDisplayReviewList(dspCtgryScFrDTO, pageParam);
	}

	public List<GodGodEvlExtend> selectDisplayBestReviewList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		return samDisplayRepository.selectDisplayBestReviewList(dspCtgryScFrDTO);
	}

	public List<MallDspFoCtgryResult> bestCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		dspCtgryScFrDTO.setCtgryDpthCd(DisplayEnum.ONE.toString());
		List<MallDspFoCtgryResult> results = samDisplayRepository.lnbCtgryList(dspCtgryScFrDTO);

		return results;
	}
	
	public MallDspFoCtgryResult  selectLnbCategory(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		 
		return samDisplayRepository.selectLnbCategory(dspCtgryScFrDTO);
	}
	
	/**
	 * 카테고리 2depth 코너  컨텐츠 조회
	 * @param dspCtgryScFrDTO
	 * @return
	 * @throws Exception
	 */
	public List<DspCnrConttExt> selectCategoryConttList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		List<DspCnrFrResult> list = samDisplayRepository.selectCategoryCornerCnrList(dspCtgryScFrDTO);
		List<DspCnrConttExt> cnrConttList = new ArrayList<>();

		for (DspCnrFrResult dspCnrFrResult : list) {

			dspCtgryScFrDTO.setCnrSetBaseDspCnt(dspCnrFrResult.getCnrSetBaseDspCnt());
			dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
			dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
			List<DspCnrSetFrResult> sets = samDisplayRepository.selectCategoryCornerSetList(dspCtgryScFrDTO);

			for (DspCnrSetFrResult dspCnrSetFrResult : sets) {
				dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
				cnrConttList.addAll(samDisplayRepository.selectCategoryCornerConttList(dspCtgryScFrDTO));
				
			}
			
		}
		return cnrConttList;
	}
	
	/**
	 * 마이페이지 LNB 조회를 위한 현재 URI에 대한 마이페이지 LNB 정보 조회
	 * 
	 * @param currentUri
	 * @return DspCtgryScFrDTO
	 * @throws Exception
	 */
	public DspCtgryScFrDTO mypageLnbCurrentCtgryList(String currentUri) throws Exception {
		return samDisplayRepository.mypageLnbCurrentCtgryList(currentUri);
	}
	
	/**
	 * GNB카테고리 조회용
	 * @param dspCtgry
	 * @return
	 * @throws Exception
	 */
	public Object selectGNBCategotyList(DspCtgry dspCtgry) throws Exception {
		Map<String, Object> category = new HashMap<String, Object>();
		
		List<MallDspFoCtgryResult> ctgList = samDisplayRepository.selectGNBCategotyList(dspCtgry);

		String test = "test";
		log.info("{} selectGNBCategotyList {}", this.getClass() , test);
		List<MallDspFoCtgryResult> category1 =
				ctgList.stream()
				.filter(t -> t.getCtgryInfo().getCtgryDpthCd().equals("1"))				
				.collect(Collectors.toList());
		
		List<MallDspFoCtgryResult> category2 = 
				ctgList.stream()
				.filter(t -> t.getCtgryInfo().getCtgryDpthCd().equals("2"))				
				.collect(Collectors.toList());
		
		List<MallDspFoCtgryResult> category3 = 
				ctgList.stream()
				.filter(t -> t.getCtgryInfo().getCtgryDpthCd().equals("3"))				
				.collect(Collectors.toList());
		
		category.put("category1", category1);
		category.put("category2", category2);
		category.put("category3", category3);
	
		return category;
	}
	
	/**
	 * @param dspCtgryScFrDTO
	 * @return
	 * @throws Exception
	 */
	public Object selectCommonBanner(DspCtgryScFrDTO dspCtgryScFrDTO, String env) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		
		dspCtgryScFrDTO.setUpperCategory("SAM");
		dspCtgryScFrDTO.setPrcSectCd("GNRL");
		dspCtgryScFrDTO.setDspUseGrpTpCd("PC".equals(dspCtgryScFrDTO.getDevice()) ? "SA_PC_CMN_BANNER" : "SA_MO_CMN_BANNER"); // STPA01A02
		DspUseGrp dspUseGrp = selectDspUseGrp(dspCtgryScFrDTO);
		dspCtgryScFrDTO.setDspCtgryNo(dspUseGrp.getDspCtgryNo());
		dspCtgryScFrDTO.setCnrSetBaseDspCntEnd(null);
		
		List<DspCnrFrResult> list = samDisplayRepository.selectCategoryCornerList(dspCtgryScFrDTO);

		for (DspCnrFrResult dspCnrFrResult : list) {
			dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
			dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
			List<DspCnrSetFrResult> sets = samDisplayRepository.selectCategoryCornerSetList(dspCtgryScFrDTO);

			for (DspCnrSetFrResult dspCnrSetFrResult : sets) {

				dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
				List<DspCnrConttExt> bnrList = samDisplayRepository.selectCategoryCornerConttList(dspCtgryScFrDTO);
				
				if(DisplayEnum.DVC_SECT.PC.name().equals(dspCtgryScFrDTO.getDevice())) {
					if(DisplayEnum.SA_GNB_BNR.toString().equals(dspCtgryScFrDTO.getCnrSn())
							|| DisplayEnum.STG_SA_GNB_BNR.toString().equals(dspCtgryScFrDTO.getCnrSn())) {
						result.put("gnbBnr", bnrList);
					}else if(DisplayEnum.SA_TOP_BNR.toString().equals(dspCtgryScFrDTO.getCnrSn())
							|| DisplayEnum.STG_SA_TOP_BNR.toString().equals(dspCtgryScFrDTO.getCnrSn())) {
						result.put("topBnr", bnrList);
					}else if(DisplayEnum.SA_LAYER_BNR.toString().equals(dspCtgryScFrDTO.getCnrSn())
							|| DisplayEnum.STG_SA_LAYER_BNR.toString().equals(dspCtgryScFrDTO.getCnrSn())) {
						result.put("LayerBnr", bnrList);
					}
					
				} else {
					if(DisplayEnum.SA_MO_TOP_BNR.toString().equals(dspCtgryScFrDTO.getCnrSn())
							|| DisplayEnum.STG_SA_MO_TOP_BNR.toString().equals(dspCtgryScFrDTO.getCnrSn())) {
						result.put("topBnr", bnrList);
					}
				}
			}
		}
		
		return result;
	}
	
	public List<DspCnrFrResult> selectMajorContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		return samDisplayRepository.selectCategoryCornerGrpList(dspCtgryScFrDTO);

	}
	
	public List<MallDspFoCtgryResult> selectCategotyNavi(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		return samDisplayRepository.selectCategotyNavi(dspCtgryScFrDTO);

	}
	
	public List<MallDspFoCtgryResult> selectCategotyNaviNm(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		return samDisplayRepository.selectCategotyNaviNm(dspCtgryScFrDTO);
		
	}
	
	public List<MallDspFoCtgryResult> selectLookbookCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		dspCtgryScFrDTO.setCtgryDpthCd(DisplayEnum.TWO.toString());
		List<MallDspFoCtgryResult> results = samDisplayRepository.lnbCtgryList(dspCtgryScFrDTO);

		for (MallDspFoCtgryResult mall : results) {
				
			dspCtgryScFrDTO.setCtgryDpthCd(DisplayEnum.THREE.toString());
			addCtgryList(mall, dspCtgryScFrDTO);
				
		}
		
		return results;
	}
	
	public List<MallDspFoCtgryResult> selectLookbookCtgryConttList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		MallDspFoCtgryResult dspCtgryResult = samDisplayRepository.selectCtgryList(dspCtgryScFrDTO);
		
		dspCtgryScFrDTO.setDspCtgryNm(dspCtgryResult.getDspCtgryNm());
		
		dspCtgryScFrDTO.setLowerCtgryList(samDisplayRepository.lnbCtgryList(dspCtgryScFrDTO));
		
		DspCtgryScFrDTO dspCtgryScFrConttDTO = new DspCtgryScFrDTO();
		dspCtgryScFrConttDTO.setLang(dspCtgryScFrDTO.getLang());
		dspCtgryScFrConttDTO.setUpperCategory(dspCtgryScFrDTO.getDspCtgryNo());
		
		return samDisplayRepository.lnbCtgryList(dspCtgryScFrConttDTO);
		
	}
	
	public List<DspCtgryGodFoResult> selectMainRankingGod(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		return samDisplayRepository.selectMainRankingGod(dspCtgryScFrDTO);
	}
	
	public String selectLoobookCtgryNo(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		return samDisplayRepository.selectLoobookCtgryNo(dspCtgryScFrDTO);
		
	}

	public List<DspCnrConttExt> selectLoobookCornerConttList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		return samDisplayRepository.selectLoobookCornerConttList(dspCtgryScFrDTO);
	}
	
	public DspCnrConttExt selectLoobookGodInfo(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		return samDisplayRepository.selectLoobookGodInfo(dspCtgryScFrDTO);
	}
	
}
