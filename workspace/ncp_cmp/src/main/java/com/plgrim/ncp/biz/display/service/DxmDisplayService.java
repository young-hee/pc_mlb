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
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspUseGrp;
import com.plgrim.ncp.base.entities.datasource1.dsp.RecoPick;
import com.plgrim.ncp.base.entities.datasource1.dsp.RecoPickSDO;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.biz.display.data.DspCnrConttExt;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.repository.DxmDisplayRepository;
import com.plgrim.ncp.biz.display.result.DspCnrFrResult;
import com.plgrim.ncp.biz.display.result.DspCnrSetFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryGodFoResult;
import com.plgrim.ncp.biz.display.result.DxmDspFoCtgryResult;
import com.plgrim.ncp.biz.display.result.MallDspFoCtgryResult;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.repository.MemberActivitySelectRepository;
import com.plgrim.ncp.biz.member.result.MypageWishFoResult;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.page.PageParam;

@Service
public class DxmDisplayService extends AbstractService {
	@Autowired
	private DxmDisplayRepository dxmDisplayRepository;

	@Autowired
	private MemberActivitySelectRepository memberActivitySelectRepository;

	public DspUseGrp selectDspUseGrp(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		DspUseGrp dspUseGrp = new DspUseGrp();
		dspUseGrp.setDspUseGrpTpCd(dspCtgryScFrDTO.getDspUseGrpTpCd());
		return dxmDisplayRepository.selectDspUseGrpTpCd(dspUseGrp);

	}

	public List<DxmDspFoCtgryResult> lnbCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		dspCtgryScFrDTO.setCtgryDpthCd(DisplayEnum.ONE.toString());
		List<DxmDspFoCtgryResult> results = dxmDisplayRepository.lnbCtgryList(dspCtgryScFrDTO);

		for (DxmDspFoCtgryResult dxm : results) {

			if (dxm.getDspCtgryNo().equals(dspCtgryScFrDTO.getCtgryNoDpth1())) {
				dspCtgryScFrDTO.setCtgryDpthCd(DisplayEnum.TWO.toString());
				addCtgryList(dxm, dspCtgryScFrDTO);
				for (DxmDspFoCtgryResult v : dxm.getDxmDspFoCtgryResults()) {
					if (v.getDspCtgryNo().equals(dspCtgryScFrDTO.getCtgryNoDpth2())) {
						dspCtgryScFrDTO.setCtgryDpthCd(DisplayEnum.THREE.toString());
						addCtgryList(v, dspCtgryScFrDTO);

						for (DxmDspFoCtgryResult d : v.getDxmDspFoCtgryResults()) {
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

	public List<DxmDspFoCtgryResult> lnbMbCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		dspCtgryScFrDTO.setCtgryDpthCd(DisplayEnum.ONE.toString());
		List<DxmDspFoCtgryResult> results = dxmDisplayRepository.lnbCtgryList(dspCtgryScFrDTO);

		for (DxmDspFoCtgryResult dxm : results) {
			dspCtgryScFrDTO.setCtgryDpthCd(DisplayEnum.TWO.toString());
			addCtgryList(dxm, dspCtgryScFrDTO);
			for (DxmDspFoCtgryResult v : dxm.getDxmDspFoCtgryResults()) {
				dspCtgryScFrDTO.setCtgryDpthCd(DisplayEnum.THREE.toString());
				addCtgryList(v, dspCtgryScFrDTO);
				for (DxmDspFoCtgryResult d : v.getDxmDspFoCtgryResults()) {
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

		DxmDspFoCtgryResult dspCtgry = dxmDisplayRepository.selectCtgryList(dspCtgryScFrDTO);

		if (dspCtgry != null) {
			dspCtgryScFrDTO.setSortColumn(dspCtgry.getSortColumn());
			dspCtgryScFrDTO.setFLocation(dspCtgry.getDspCtgryNm());
			dspCtgryScFrDTO.setMetaSjNm(dspCtgry.getMetaSjNm());
			dspCtgryScFrDTO.setMetaDscrCont(dspCtgry.getMetaDscrCont());
			dspCtgryScFrDTO.setMetaKwd(dspCtgry.getMetaKwd());
		}
	}

	public void selectMbCtgryList(Model model, DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		DxmDspFoCtgryResult dspCtgry = dxmDisplayRepository.selectCtgryList(dspCtgryScFrDTO);
		List<DxmDspFoCtgryResult> dxmDspFoCtgryResults = new ArrayList<>();
		dspCtgryScFrDTO.setSortColumn(dspCtgry.getSortColumn());
		dspCtgryScFrDTO.setFLocation(dspCtgry.getDspCtgryNm());
		dspCtgryScFrDTO.setMetaSjNm(dspCtgry.getMetaSjNm());
		dspCtgryScFrDTO.setMetaDscrCont(dspCtgry.getMetaDscrCont());
		dspCtgryScFrDTO.setMetaKwd(dspCtgry.getMetaKwd());

		if ("Y".equals(dspCtgry.getLeafCtgryYn())) {
			dspCtgryScFrDTO.setUpperCategory(dspCtgry.getUpperDspCtgryNo());
			dxmDspFoCtgryResults.add(dspCtgry);
			dxmDspFoCtgryResults.addAll(dxmDisplayRepository.selectLeafCtgryList(dspCtgryScFrDTO));
		} else {
			dspCtgryScFrDTO.setUpperCategory(dspCtgry.getDspCtgryNo());
			dxmDspFoCtgryResults.addAll(dxmDisplayRepository.lnbCtgryList(dspCtgryScFrDTO));
		}
		model.addAttribute("upperDspCtgryNo", dspCtgry.getUpperDspCtgryNo());
		model.addAttribute("category", dxmDspFoCtgryResults);

	}

	private void addCtgryList(DxmDspFoCtgryResult dxmDspFoCtgryResult, DspCtgryScFrDTO dspCtgryScFrDTO)
			throws Exception {
		dspCtgryScFrDTO.setUpperCategory(dxmDspFoCtgryResult.getDspCtgryNo());
		dspCtgryScFrDTO.setSortColumn(dxmDspFoCtgryResult.getSortColumn());
		dspCtgryScFrDTO.setFLocation(dxmDspFoCtgryResult.getDspCtgryNm());
		dxmDspFoCtgryResult.setDxmDspFoCtgryResults(dxmDisplayRepository.lnbCtgryList(dspCtgryScFrDTO));
	}

	public Page<DspCtgryGodFoResult> selectDisplayGodList(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam)
			throws Exception {
		RepositoryHelper.makePageEntityIndex(dspCtgryScFrDTO, pageParam);
		return dxmDisplayRepository.selectDisplayGodList(dspCtgryScFrDTO, pageParam);
	}

	public DspCtgryGodFoResult selectFilterList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		DspCtgryGodFoResult dspCtgryGodFoResult = new DspCtgryGodFoResult();

		dspCtgryScFrDTO.setFilterType("SIZE");
		dspCtgryGodFoResult.setSizeFilters(dxmDisplayRepository.selectFilterList(dspCtgryScFrDTO));

		dspCtgryScFrDTO.setFilterType("COLOR");
		dspCtgryGodFoResult.setColorFilters(dxmDisplayRepository.selectFilterList(dspCtgryScFrDTO));

		return dspCtgryGodFoResult;
	}

	public DspCtgryGodFoResult selectFilterListByGod(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		DspCtgryGodFoResult dspCtgryGodFoResult = new DspCtgryGodFoResult();

		dspCtgryScFrDTO.setFilterType("SIZE");
		dspCtgryGodFoResult.setSizeFilters(dxmDisplayRepository.selectFilterListByGod(dspCtgryScFrDTO));

		dspCtgryScFrDTO.setFilterType("COLOR");
		dspCtgryGodFoResult.setColorFilters(dxmDisplayRepository.selectFilterListByGod(dspCtgryScFrDTO));

		return dspCtgryGodFoResult;
	}

	public DspCnrFrResult selectMajorCategoryContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		List<DspCnrFrResult> list = dxmDisplayRepository.selectCategoryCornerList(dspCtgryScFrDTO);
		List<DspCnrConttExt> cnrConttList = new ArrayList<>();
		List<DspCnrConttExt> cnrGodConttList = new ArrayList<>();

		for (DspCnrFrResult dspCnrFrResult : list) {
			dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
			dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
			List<DspCnrSetFrResult> sets = dxmDisplayRepository.selectCategoryCornerSetList(dspCtgryScFrDTO);

			for (DspCnrSetFrResult dspCnrSetFrResult : sets) {

				dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
				cnrConttList.addAll(dxmDisplayRepository.selectCategoryCornerConttList(dspCtgryScFrDTO));
				cnrGodConttList.addAll(dxmDisplayRepository.selectCategoryCornerGodConttList(dspCtgryScFrDTO));
			}
		}
		DspCnrFrResult result = new DspCnrFrResult();

		result.setCnrConttList(cnrConttList);
		result.setCnrGodConttList(cnrGodConttList);
		return result;

	}

	public DspCnrFrResult selectMainContt(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		DspCnrFrResult result = selectMainCommonContt(dspCtgryScFrDTO);

		dspCtgryScFrDTO.setDspUseGrpTpCd("MAIN_PC_UPEND_TMPLAT"); // THMA01A02
		DspUseGrp dspUseGrp = selectDspUseGrp(dspCtgryScFrDTO);
		dspCtgryScFrDTO.setDspCtgryNo(dspUseGrp.getDspCtgryNo());
		dspCtgryScFrDTO.setCnrSetBaseDspCntEnd(null);
		result.setCnrBnrConttList(selectCnrBnrConttList(dspCtgryScFrDTO));

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

		List<DspCnrFrResult> list = dxmDisplayRepository.selectCategoryCornerList(dspCtgryScFrDTO);
		List<DspCnrFrResult> newArrivalList = new ArrayList<>();
		List<DspCnrFrResult> cnrMainConttList = new ArrayList<>();
		int count = 0;
		for (DspCnrFrResult dspCnrFrResult : list) {
			if (count > 0) {
				dspCtgryScFrDTO.setCnrSetBaseDspCntEnd(dspCnrFrResult.getCnrSetBaseDspCnt());
			}
			dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
			dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
			List<DspCnrSetFrResult> sets = dxmDisplayRepository.selectCategoryCornerSetList(dspCtgryScFrDTO);

			for (DspCnrSetFrResult dspCnrSetFrResult : sets) {
				dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
				dspCnrSetFrResult.setCnrConttExList(dxmDisplayRepository.selectMainCornerConttList(dspCtgryScFrDTO));
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

		if (!"PC".equals(dspCtgryScFrDTO.getDevice())) {

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

		List<DspCnrFrResult> list = dxmDisplayRepository.selectCategoryCornerList(dspCtgryScFrDTO);
		List<DspCnrFrResult> cnrMainConttList = new ArrayList<>();
		int count = 0;
		for (DspCnrFrResult dspCnrFrResult : list) {
			if (count > 0) {

				dspCtgryScFrDTO.setCnrSetBaseDspCnt(dspCnrFrResult.getCnrSetBaseDspCnt());
				dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
				dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
				List<DspCnrSetFrResult> sets = dxmDisplayRepository.selectCategoryCornerSetList(dspCtgryScFrDTO);

				for (DspCnrSetFrResult dspCnrSetFrResult : sets) {
					dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
					dspCnrSetFrResult
							.setCnrConttExList(dxmDisplayRepository.selectMainCornerConttList(dspCtgryScFrDTO));
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
		return dxmDisplayRepository.searchDisplayGodList(dspCtgryScFrDTO, pageParam);
	}

	public List<DspCtgryGodFoResult> selectRecoPickGodList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		List<RecoPickSDO> recoPickSDOs = new ArrayList<>();
		if (dspCtgryScFrDTO.getRecoPicks() != null && dspCtgryScFrDTO.getRecoPicks().size() > 0) {

			for (RecoPick p : dspCtgryScFrDTO.getRecoPicks()) {
				RecoPickSDO sdo = new RecoPickSDO();
				BeanUtils.copyProperties(p, sdo);
				recoPickSDOs.add(sdo);
			}
		}
		dspCtgryScFrDTO.setRecoPickSDOs(recoPickSDOs);
		return dxmDisplayRepository.selectRecoPickGodList(dspCtgryScFrDTO);
	}

	private List<DspCnrConttExt> selectCnrBnrConttList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		List<DspCnrFrResult> list = dxmDisplayRepository.selectCategoryCornerList(dspCtgryScFrDTO);
		List<DspCnrConttExt> cnrBnrConttList = new ArrayList<>();

		for (DspCnrFrResult dspCnrFrResult : list) {
			if (!DisplayEnum.STG_DX_PC_TOP_BNR.toString().equals(String.valueOf(dspCnrFrResult.getCnrSn()))
					&& !DisplayEnum.STG_DX_MO_TOP_BNR.toString().equals(String.valueOf(dspCnrFrResult.getCnrSn()))
					&& !DisplayEnum.DX_PC_TOP_BNR.toString().equals(String.valueOf(dspCnrFrResult.getCnrSn()))
					&& !DisplayEnum.DX_MO_TOP_BNR.toString().equals(String.valueOf(dspCnrFrResult.getCnrSn()))) {
				dspCtgryScFrDTO.setCnrSn(String.valueOf(dspCnrFrResult.getCnrSn()));
				dspCtgryScFrDTO.setCnrTpGrpSn(dspCnrFrResult.getCnrTpGrpSn());
				List<DspCnrSetFrResult> sets = dxmDisplayRepository.selectCategoryCornerSetList(dspCtgryScFrDTO);

				for (DspCnrSetFrResult dspCnrSetFrResult : sets) {
					dspCtgryScFrDTO.setCnrSetSn(dspCnrSetFrResult.getCnrSetSn());
					cnrBnrConttList.addAll(dxmDisplayRepository.selectMainCornerConttList(dspCtgryScFrDTO));
				}
			}
		}
		return cnrBnrConttList;

	}

	public Page<GodGodEvlExtend> selectDisplayReviewList(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam)
			throws Exception {
		RepositoryHelper.makePageEntityIndex(dspCtgryScFrDTO, pageParam);

		return dxmDisplayRepository.selectDisplayReviewList(dspCtgryScFrDTO, pageParam);
	}

	public List<GodGodEvlExtend> selectDisplayBestReviewList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		return dxmDisplayRepository.selectDisplayBestReviewList(dspCtgryScFrDTO);
	}

	public List<DxmDspFoCtgryResult> bestCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		dspCtgryScFrDTO.setCtgryDpthCd(DisplayEnum.ONE.toString());
		List<DxmDspFoCtgryResult> results = dxmDisplayRepository.lnbCtgryList(dspCtgryScFrDTO);

		return results;
	}

	public DxmDspFoCtgryResult selectLnbCategory(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		return dxmDisplayRepository.selectLnbCategory(dspCtgryScFrDTO);
	}

	/**
	 * 마이페이지 LNB 조회를 위한 현재 URI에 대한 마이페이지 LNB 정보 조회
	 * 
	 * @param currentUri
	 * @return DspCtgryScFrDTO
	 * @throws Exception
	 */
	public DspCtgryScFrDTO mypageLnbCurrentCtgryList(String currentUri) throws Exception {
		return dxmDisplayRepository.mypageLnbCurrentCtgryList(currentUri);
	}

	public List<MallDspFoCtgryResult> selectCategotyNaviNm(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		return dxmDisplayRepository.selectCategotyNaviNm(dspCtgryScFrDTO);

	}
}
