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
 * @since       2015. 8. 19       
 */
package com.plgrim.ncp.biz.display.repository;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspUseGrp;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlExtend;
import com.plgrim.ncp.biz.display.data.DspCnrConttExt;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.result.DspCnrFrResult;
import com.plgrim.ncp.biz.display.result.DspCnrSetFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryGodFoResult;
import com.plgrim.ncp.biz.display.result.MallDspFoCtgryResult;
import com.plgrim.ncp.biz.goods.data.GoodsReviewSearchDTO;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SamDisplayRepository extends AbstractRepository {
	@Cacheable(value="samDisplayRepository.lnbCtgryList",
			key="{" + "#p0.lang,"
					+ "#p0.upperCategory,"
					+ "#p0.ctgryDpthCd,"
					+ "#p0.bstGodEvlYn"
					+ "}")
	public List<MallDspFoCtgryResult> lnbCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> lnbCtgryList");

		List<MallDspFoCtgryResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.lnbCtgryList", dspCtgryScFrDTO);
		
		return result;
	}
	
	@Cacheable(value="samDisplayRepository.selectLeafCtgryList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.upperCategory"
					+ "}")
	public List<MallDspFoCtgryResult> selectLeafCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectLeafCtgryList");

		List<MallDspFoCtgryResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectLeafCtgryList", dspCtgryScFrDTO);
		
		return result;
	}
	
	@Cacheable(value="samDisplayRepository.selectCtgryList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.dspCtgryNo"
					+ "}")
	public MallDspFoCtgryResult selectCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectCtgryList");

		MallDspFoCtgryResult result= getSession1().selectOne("com.plgrim.ncp.biz.mall.display.selectCtgryList", dspCtgryScFrDTO);
		
		return result;
	}
	
	@Cacheable(value="samDisplayRepository.selectDisplayGodList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.prcSectCd,"
					+ "#p0.ctgrySectCd,"
					+ "#p0.searchConditionColors?.toString(),"										
					+ "#p0.searchConditionSizes?.toString(),"										
					+ "#p0.searchConditionTeamCds?.toString(),"
					+ "#p0.prcStart,"
					+ "#p0.prcEnd,"
					+ "#p0.sortColumn,"
					+ "#p0.endIndex,"
					+ "#p0.beginIndex"
					+ "}")
	public Page<DspCtgryGodFoResult> selectDisplayGodList(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectDisplayGodList");
	

		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectDisplayGodList", dspCtgryScFrDTO);
	
		int totalCnt = getSession1().selectOne("com.plgrim.ncp.biz.mall.display.selectDisplayGodListTotCnt", dspCtgryScFrDTO);
		return new PageImpl<DspCtgryGodFoResult>(result, pageParam.getPageable(), totalCnt);
	}
 
	
	@Cacheable(value="samDisplayRepository.selectFilterList",
			key="{" + "#p0.lang,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.filterType"
					+ "}")
	public List<DspCtgryGodFoResult> selectFilterList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectFilterList ");

		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectFilterList", dspCtgryScFrDTO);
		
		return result;
	}
	
	public List<DspCtgryGodFoResult> selectSearchFilterList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectSearchFilterList ");

		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectSearchFilterList", dspCtgryScFrDTO);
		
		return result;
	}
	
	@Cacheable(value="samDisplayRepository.selectCategoryCornerList",
			key="{"+ "#p0.lang,"
					+ "#p0.device,"
					+ "#p0.dspCtgryNo"
					+ "}")
	public List<DspCnrFrResult> selectCategoryCornerList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectCategoryCornerList ");
		
		DspCtgryScFrDTO copy = new DspCtgryScFrDTO();
		
		BeanUtils.copyProperties(dspCtgryScFrDTO, copy);
    	if("MOBILE_WEB".equals(copy.getDevice()) ||"MOBILE_APP".equals(copy.getDevice()) ) {
    		copy.setDevice("MOBILE");
    	};
		List<DspCnrFrResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectCategoryCornerList", copy);
		
		return result;
	}
	
	@Cacheable(value="samDisplayRepository.selectCategoryCornerGrpList",
			key="{"+ "#p0.lang,"
					+ "#p0.device,"
					+ "#p0.dspCtgryNo"
					+ "}")
	public List<DspCnrFrResult> selectCategoryCornerGrpList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectCategoryCornerGrpList ");
		
		DspCtgryScFrDTO copy = new DspCtgryScFrDTO();
		
		BeanUtils.copyProperties(dspCtgryScFrDTO, copy);
    	if("MOBILE_WEB".equals(copy.getDevice()) ||"MOBILE_APP".equals(copy.getDevice()) ) {
    		copy.setDevice("MOBILE");
    	};
		List<DspCnrFrResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectCategoryCornerGrpList", copy);
		
		return result;
	}
	
	@Cacheable(value="samDisplayRepository.selectCategoryCornerCnrList",
			key="{"+ "#p0.lang,"
					+ "#p0.device,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.cnrSn"
					+ "}")
	public List<DspCnrFrResult> selectCategoryCornerCnrList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectCategoryCornerGrpList ");
		
		DspCtgryScFrDTO copy = new DspCtgryScFrDTO();
		
		BeanUtils.copyProperties(dspCtgryScFrDTO, copy);
    	if("MOBILE_WEB".equals(copy.getDevice()) ||"MOBILE_APP".equals(copy.getDevice()) ) {
    		copy.setDevice("MOBILE");
    	};
		List<DspCnrFrResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectCategoryCornerCnrList", copy);
		
		return result;
	}
	
	
	@Cacheable(value="samDisplayRepository.selectCategoryCornerSetList",
			key="{" + "#p0.lang,"
					+ "#p0.cnrSn,"
					+ "#p0.cnrTpGrpSn,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.cnrSetBaseDspCntEnd,"
					+ "#p0.cnrSetBaseDspCnt"
					+ "}")
	public List<DspCnrSetFrResult> selectCategoryCornerSetList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectCategoryCornerSetList ");

		
		List<DspCnrSetFrResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectCategoryCornerSetList", dspCtgryScFrDTO);
		
		return result;
	}
 
	
	@Cacheable(value="samDisplayRepository.selectCategoryCornerConttList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.cnrSn,"
					+ "#p0.cnrSetSn,"
					+ "#p0.cnrTpGrpSn,"
					+ "#p0.mallId,"
					+ "#p0.device,"
					+ "#p0.tgtMbrTpCd"
					+ "}")
	public List<DspCnrConttExt> selectCategoryCornerConttList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectCategoryCornerConttList ");

		List<DspCnrConttExt> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectCategoryCornerConttList", dspCtgryScFrDTO);
		
		return result;
	}
	

	@Cacheable(value="samDisplayRepository.selectCategoryCornerGodConttList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.cnrSn,"
					+ "#p0.cnrSetSn,"
					+ "#p0.cnrTpGrpSn,"
					+ "#p0.mallId,"
					+ "#p0.device,"
					+ "#p0.tgtMbrTpCd"
					+ "}")
	public List<DspCnrConttExt> selectCategoryCornerGodConttList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectCategoryCornerGodConttList");
	 
		List<DspCnrConttExt> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectCategoryCornerGodConttList", dspCtgryScFrDTO);
 
		return result;
	}
 
	@Cacheable(value="samDisplayRepository.selectMainCornerConttList",
	key="{"
			+ "#p0.lang,"
			+ "#p0.cnrSn,"
			+ "#p0.cnrSetSn,"
			+ "#p0.cnrTpGrpSn,"
			+ "#p0.mallGubun,"
			+ "#p0.device,"
			+ "#p0.tgtMbrTpCd"
			+ "}")
	public List<DspCnrConttExt> selectMainCornerConttList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectMainCornerConttList ");

		List<DspCnrConttExt> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectMainCornerConttList", dspCtgryScFrDTO);
		
		return result;
	}
	
//	@Cacheable(value="samDisplayRepository.searchDisplayGodList",
//			key="{"
//					+ "#p0.lang,"
//					+ "#p0.dspCtgryNo,"
//					+ "#p0.prcSectCd,"
//					+ "#p0.ctgrySectCd,"
//					+ "#p0.searchConditionColors?.toString(),"										
//					+ "#p0.searchConditionSizes?.toString(),"										
//					+ "#p0.prcStart,"
//					+ "#p0.prcEnd,"
//					+ "#p0.sortColumn,"
//					+ "#p0.searchText,"
//					+ "#p0.endIndex,"
//					+ "#p0.beginIndex"
//					+ "}")
	public Page<DspCtgryGodFoResult> searchDisplayGodList(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> searchDisplayGodList");

		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.searchDisplayGodList", dspCtgryScFrDTO);
		int totalCnt = getSession1().selectOne("com.plgrim.ncp.biz.mall.display.searchDisplayGodListTotCnt", dspCtgryScFrDTO);
		return new PageImpl<DspCtgryGodFoResult>(result, pageParam.getPageable(), totalCnt);
	}
	
	
	@Cacheable(value="samDisplayRepository.selectRecoPickGodList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.mallGubun,"
					+ "#p0.recoPickSDOs?.toString(),"																		
					+ "#p0.prcSectCd"
					+ "}")
	public List<DspCtgryGodFoResult> selectRecoPickGodList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectRecoPickGodList ");

		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectRecoPickGodList", dspCtgryScFrDTO);
		
		return result;
	}
	
	
	@Cacheable(value="samDisplayRepository.selectDisplayReviewList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.bstGodEvlYn,"
					+ "#p0.endIndex,"																	
					+ "#p0.beginIndex"
					+ "}")
	public Page<GodGodEvlExtend> selectDisplayReviewList(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> searchDisplayGodList");

		List<GodGodEvlExtend> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectDisplayReviewList", dspCtgryScFrDTO);
	
		GoodsReviewSearchDTO goodsReviewSearchDTO= new GoodsReviewSearchDTO();
		
		for (GodGodEvlExtend godGodEvlExtend : result) {
			goodsReviewSearchDTO.setGodNo(godGodEvlExtend.getGodNo());
			goodsReviewSearchDTO.setGodEvlTurn(godGodEvlExtend.getGodEvlTurn());
			godGodEvlExtend.setGodGodEvlAtchFileList(getSession1().selectList("com.plgrim.ncp.biz.goods.content.getGodGodEvlAtchFileList", goodsReviewSearchDTO));
		}
		int totalCnt = getSession1().selectOne("com.plgrim.ncp.biz.mall.display.getDisplayReviewListCount", dspCtgryScFrDTO);
		return new PageImpl<GodGodEvlExtend>(result, pageParam.getPageable(), totalCnt);
	}
	
	
	@Cacheable(value="samDisplayRepository.selectDisplayBestReviewList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.bstGodEvlYn"
					+ "}")
	public List<GodGodEvlExtend> selectDisplayBestReviewList(DspCtgryScFrDTO dspCtgryScFrDTO ) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectDisplayBestReviewList");
		GoodsReviewSearchDTO goodsReviewSearchDTO= new GoodsReviewSearchDTO();
		List<GodGodEvlExtend> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectDisplayReviewList", dspCtgryScFrDTO);
		for (GodGodEvlExtend godGodEvlExtend : result) {
			goodsReviewSearchDTO.setGodNo(godGodEvlExtend.getGodNo());
			goodsReviewSearchDTO.setGodEvlTurn(godGodEvlExtend.getGodEvlTurn());
			godGodEvlExtend.setGodGodEvlAtchFileList(getSession1().selectList("com.plgrim.ncp.biz.goods.content.getGodGodEvlAtchFileList", goodsReviewSearchDTO));
		}
		return result;
	}
	
	
	@Cacheable(value="samDisplayRepository.selectDisplayBestReviewList",
			key="{"
					+ "#p0.godNos"
					+ "}")
	public MallDspFoCtgryResult  selectLnbCategory(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
 
		return getSession1().selectOne("com.plgrim.ncp.biz.mall.display.selectLnbCategory", dspCtgryScFrDTO);
	}
	
	@Cacheable(value="samDisplayRepository.selectDspUseGrpTpCd",
			key="{"
					+ "#p0.dspUseGrpTpCd"
					+ "}")
	public DspUseGrp selectDspUseGrpTpCd(DspUseGrp dspUseGrp) {
		return getSession1().selectOne("com.plgrim.ncp.biz.mall.display.selectDspUseGrpTpCd", dspUseGrp);
	}
	
	@Cacheable(value="samDisplayRepository.mypageLnbCurrentCtgryList",
			key="{" 
					+ "#currentUri"
					+ "}")
	public DspCtgryScFrDTO mypageLnbCurrentCtgryList(String currentUri) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.mall.display.mypageLnbCurrentCtgryList", currentUri);
	}
	
	@Cacheable(value="samDisplayRepository.selectGNBCategotyList",
			key="{" + "#p0.lang"
					+ "}")
	public List<MallDspFoCtgryResult> selectGNBCategotyList(DspCtgry dspCtgry) throws Exception {		
		List<MallDspFoCtgryResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectGNBCategotyList", dspCtgry);		
		return result;
	}
	
	@Cacheable(value="samDisplayRepository.selectCategotyNavi",
			key="{"
					+ "#p0.lang,"
					+ "#p0.mallGubun,"
					+ "#p0.ctgrySectCd"
					+ "}")
	public List<MallDspFoCtgryResult> selectCategotyNavi(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {		
		List<MallDspFoCtgryResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectCategotyNavi", dspCtgryScFrDTO);		
		return result;
	}
	
	@Cacheable(value="samDisplayRepository.selectCategotyNaviNm",
			key="{"
					+ "#p0.lang,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.mallGubun,"
					+ "#p0.ctgrySectCd"
					+ "}")
	public List<MallDspFoCtgryResult> selectCategotyNaviNm(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {		
		List<MallDspFoCtgryResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectCategotyNaviNm", dspCtgryScFrDTO);		
		return result;
	}
	
	public List<DspCtgryGodFoResult> selectMainRankingGod(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {		
		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectMainRankingGod", dspCtgryScFrDTO);		
		return result;
	}
	
	public String selectLoobookCtgryNo(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		
		return getSession1().selectOne("com.plgrim.ncp.biz.mall.display.selectLoobookCtgryNo", dspCtgryScFrDTO);
		
	}

	public List<DspCnrConttExt> selectLoobookCornerConttList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		List<DspCnrConttExt> result = getSession1().selectList("com.plgrim.ncp.biz.mall.display.selectLoobookCornerConttList", dspCtgryScFrDTO);
		
		return result;
	}
	
	public DspCnrConttExt selectLoobookGodInfo(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {

		DspCnrConttExt result = getSession1().selectOne("com.plgrim.ncp.biz.mall.display.selectLoobookGodInfo", dspCtgryScFrDTO);
		
		return result;
	}
	
}
