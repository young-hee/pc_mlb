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
import com.plgrim.ncp.base.entities.datasource1.dsp.DspUseGrp;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlExtend;
import com.plgrim.ncp.biz.display.data.DspCnrConttExt;
import com.plgrim.ncp.biz.display.data.DspCnrConttExtResult;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.result.DspCnrFrResult;
import com.plgrim.ncp.biz.display.result.DspCnrSetFrResult;
import com.plgrim.ncp.biz.display.result.DspCtgryGodFoResult;
import com.plgrim.ncp.biz.display.result.DxmDspFoCtgryResult;
import com.plgrim.ncp.biz.display.result.MallDspFoCtgryResult;
import com.plgrim.ncp.biz.goods.data.GoodsReviewSearchDTO;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DxmDisplayRepository extends AbstractRepository {
	@Cacheable(value="dxmDisplayRepository.lnbCtgryList",
			key="{" + "#p0.lang,"
					+ "#p0.upperCategory,"
					+ "#p0.ctgryDpthCd,"
					+ "#p0.bstGodEvlYn"
					+ "}")
	public List<DxmDspFoCtgryResult> lnbCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> lnbCtgryList");

		List<DxmDspFoCtgryResult> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.lnbCtgryList", dspCtgryScFrDTO);
		
		return result;
	}
	
	@Cacheable(value="dxmDisplayRepository.selectLeafCtgryList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.upperCategory"
					+ "}")
	public List<DxmDspFoCtgryResult> selectLeafCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectLeafCtgryList");

		List<DxmDspFoCtgryResult> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectLeafCtgryList", dspCtgryScFrDTO);
		
		return result;
	}
	
	@Cacheable(value="dxmDisplayRepository.selectCtgryList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.dspCtgryNo"
					+ "}")
	public DxmDspFoCtgryResult selectCtgryList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectCtgryList");

		DxmDspFoCtgryResult result= getSession1().selectOne("com.plgrim.ncp.biz.dxm.display.selectCtgryList", dspCtgryScFrDTO);
		
		return result;
	}
	
	@Cacheable(value="dxmDisplayRepository.selectDisplayGodList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.prcSectCd,"
					+ "#p0.ctgrySectCd,"
					+ "#p0.searchConditionColors?.toString(),"										
					+ "#p0.searchConditionSizes?.toString(),"										
					+ "#p0.prcStart,"
					+ "#p0.prcEnd,"
					+ "#p0.sortColumn,"
					+ "#p0.endIndex,"
					+ "#p0.beginIndex"
					+ "}")
	public Page<DspCtgryGodFoResult> selectDisplayGodList(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectDisplayGodList");
	

		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectDisplayGodList", dspCtgryScFrDTO);
	
		int totalCnt = getSession1().selectOne("com.plgrim.ncp.biz.dxm.display.selectDisplayGodListTotCnt", dspCtgryScFrDTO);
		return new PageImpl<DspCtgryGodFoResult>(result, pageParam.getPageable(), totalCnt);
	}
 
	
	@Cacheable(value="dxmDisplayRepository.selectFilterList",
			key="{" + "#p0.lang,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.filterType"
					+ "}")
	public List<DspCtgryGodFoResult> selectFilterList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectFilterList ");

		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectFilterList", dspCtgryScFrDTO);
		
		return result;
	}
 
	public List<DspCtgryGodFoResult> selectFilterListByGod(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectFilterListByGod ");

		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectFilterListByGod", dspCtgryScFrDTO);
		
		return result;
	}

	@Cacheable(value="dxmDisplayRepository.selectCategoryCornerList",
			key="{" + "#p0.lang,"
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
		List<DspCnrFrResult> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectCategoryCornerList", copy);
		
		return result;
	}
	
	@Cacheable(value="dxmDisplayRepository.selectCategoryCornerSetList",
			key="{" + "#p0.lang,"
					+ "#p0.cnrSn,"
					+ "#p0.cnrTpGrpSn,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.cnrSetBaseDspCntEnd,"
					+ "#p0.cnrSetBaseDspCnt"
					+ "}")
	public List<DspCnrSetFrResult> selectCategoryCornerSetList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectCategoryCornerSetList ");
		
		List<DspCnrSetFrResult> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectCategoryCornerSetList", dspCtgryScFrDTO);
		
		return result;
	}

	@Cacheable(value="dxmDisplayRepository.selectCategoryCornerConttList",
			key="{" + "#p0.lang,"
					+ "#p0.cnrSn,"
					+ "#p0.cnrSetSn,"
					+ "#p0.cnrTpGrpSn,"
					+ "#p0.mallId,"
					+ "#p0.device,"
					+ "#p0.tgtMbrTpCd"
					+ "}")
	public List<DspCnrConttExt> selectCategoryCornerConttList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectCategoryCornerConttList ");
		List<DspCnrConttExt> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectCategoryCornerConttList", dspCtgryScFrDTO);
		return result;
	}

	@Cacheable(value="dxmDisplayRepository.selectCategoryCornerConttListByPagingTotCnt",
			key="{" + "#p0.lang,"
					+ "#p0.cnrSn,"
					+ "#p0.cnrSetSn,"
					+ "#p0.cnrTpGrpSn,"
					+ "#p0.mallId,"
					+ "#p0.device,"
					+ "#p0.tgtMbrTpCd"
					+ "}")
	public long selectCategoryCornerConttListByPagingTotCnt(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectCategoryCornerConttListByPagingTotCnt ");
		return getSession1().selectOne("com.plgrim.ncp.biz.dxm.display.selectCategoryCornerConttListByPagingTotCnt", dspCtgryScFrDTO);
	}

	@Cacheable(value="dxmDisplayRepository.selectCategoryCornerConttListByPaging",
			key="{" + "#p0.lang,"
					+ "#p0.cnrSn,"
					+ "#p0.cnrSetSn,"
					+ "#p0.cnrTpGrpSn,"
					+ "#p0.mallId,"
					+ "#p0.device,"
					+ "#p0.tgtMbrTpCd"
					+ "}")
	public List<DspCnrConttExtResult> selectCategoryCornerConttListByPaging(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectCategoryCornerConttListByPaging ");
		return getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectCategoryCornerConttListByPaging", dspCtgryScFrDTO);
	}

	@Cacheable(value="dxmDisplayRepository.selectCategoryCornerGodConttList",
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
	 
		List<DspCnrConttExt> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectCategoryCornerGodConttList", dspCtgryScFrDTO);
 
		return result;
	}
 
	@Cacheable(value="dxmDisplayRepository.selectMainCornerConttList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.cnrSn,"
					+ "#p0.cnrSetSn,"
					+ "#p0.cnrTpGrpSn,"
					+ "#p0.mallId,"
					+ "#p0.device,"
					+ "#p0.tgtMbrTpCd"
					+ "}")
	public List<DspCnrConttExt> selectMainCornerConttList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectMainCornerConttList ");

		List<DspCnrConttExt> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectMainCornerConttList", dspCtgryScFrDTO);
		
		return result;
	}
	
	@Cacheable(value="dxmDisplayRepository.searchDisplayGodList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.prcSectCd,"
					+ "#p0.ctgrySectCd,"
					+ "#p0.searchConditionColors?.toString(),"										
					+ "#p0.searchConditionSizes?.toString(),"										
					+ "#p0.prcStart,"
					+ "#p0.prcEnd,"
					+ "#p0.sortColumn,"
					+ "#p0.searchText,"
					+ "#p0.endIndex,"
					+ "#p0.beginIndex"
					+ "}")
	public Page<DspCtgryGodFoResult> searchDisplayGodList(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> searchDisplayGodList");

		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.searchDisplayGodList", dspCtgryScFrDTO);
		int totalCnt = getSession1().selectOne("com.plgrim.ncp.biz.dxm.display.searchDisplayGodListTotCnt", dspCtgryScFrDTO);
		return new PageImpl<DspCtgryGodFoResult>(result, pageParam.getPageable(), totalCnt);
	}
	
	
	@Cacheable(value="dxmDisplayRepository.selectRecoPickGodList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.recoPickSDOs?.toString(),"																		
					+ "#p0.prcSectCd"
					+ "}")
	public List<DspCtgryGodFoResult> selectRecoPickGodList(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectRecoPickGodList ");

		List<DspCtgryGodFoResult> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectRecoPickGodList", dspCtgryScFrDTO);
		
		return result;
	}
	
	
	@Cacheable(value="dxmDisplayRepository.selectDisplayReviewList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.bstGodEvlYn,"
					+ "#p0.endIndex,"																	
					+ "#p0.beginIndex"
					+ "}")
	public Page<GodGodEvlExtend> selectDisplayReviewList(DspCtgryScFrDTO dspCtgryScFrDTO, PageParam pageParam) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> searchDisplayGodList");

		List<GodGodEvlExtend> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectDisplayReviewList", dspCtgryScFrDTO);
	
		GoodsReviewSearchDTO goodsReviewSearchDTO= new GoodsReviewSearchDTO();
		
		for (GodGodEvlExtend godGodEvlExtend : result) {
			goodsReviewSearchDTO.setGodNo(godGodEvlExtend.getGodNo());
			goodsReviewSearchDTO.setGodEvlTurn(godGodEvlExtend.getGodEvlTurn());
			godGodEvlExtend.setGodGodEvlAtchFileList(getSession1().selectList("com.plgrim.ncp.biz.goods.content.getGodGodEvlAtchFileList", goodsReviewSearchDTO));
		}
		int totalCnt = getSession1().selectOne("com.plgrim.ncp.biz.dxm.display.getDisplayReviewListCount", dspCtgryScFrDTO);
		return new PageImpl<GodGodEvlExtend>(result, pageParam.getPageable(), totalCnt);
	}
	
	
	@Cacheable(value="dxmDisplayRepository.selectDisplayBestReviewList",
			key="{"
					+ "#p0.lang,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.bstGodEvlYn"
					+ "}")
	public List<GodGodEvlExtend> selectDisplayBestReviewList(DspCtgryScFrDTO dspCtgryScFrDTO ) throws Exception {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> selectDisplayBestReviewList");
		GoodsReviewSearchDTO goodsReviewSearchDTO= new GoodsReviewSearchDTO();
		List<GodGodEvlExtend> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectDisplayReviewList", dspCtgryScFrDTO);
		for (GodGodEvlExtend godGodEvlExtend : result) {
			goodsReviewSearchDTO.setGodNo(godGodEvlExtend.getGodNo());
			goodsReviewSearchDTO.setGodEvlTurn(godGodEvlExtend.getGodEvlTurn());
			godGodEvlExtend.setGodGodEvlAtchFileList(getSession1().selectList("com.plgrim.ncp.biz.goods.content.getGodGodEvlAtchFileList", goodsReviewSearchDTO));
		}
		return result;
	}
	
	
	@Cacheable(value="dxmDisplayRepository.selectDisplayBestReviewList",
			key="{"
					+ "#p0.godNos"
					+ "}")
	public DxmDspFoCtgryResult  selectLnbCategory(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {
 
		return getSession1().selectOne("com.plgrim.ncp.biz.dxm.display.selectLnbCategory", dspCtgryScFrDTO);
	}
	
	@Cacheable(value="dxmDisplayRepository.selectDspUseGrpTpCd",
			key="{"
					+ "#p0.dspUseGrpTpCd"
					+ "}")
	public DspUseGrp selectDspUseGrpTpCd(DspUseGrp dspUseGrp) {
		return getSession1().selectOne("com.plgrim.ncp.biz.dxm.display.selectDspUseGrpTpCd", dspUseGrp);
	}
	
	@Cacheable(value="dxmDisplayRepository.mypageLnbCurrentCtgryList",
			key="{" 
					+ "#currentUri"
					+ "}")
	public DspCtgryScFrDTO mypageLnbCurrentCtgryList(String currentUri) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.dxm.display.mypageLnbCurrentCtgryList", currentUri);
	}
	
	@Cacheable(value="dxmDisplayRepository.selectCategotyNaviNm",
			key="{"
					+ "#p0.lang,"
					+ "#p0.dspCtgryNo,"
					+ "#p0.mallGubun,"
					+ "#p0.ctgrySectCd"
					+ "}")
	public List<MallDspFoCtgryResult> selectCategotyNaviNm(DspCtgryScFrDTO dspCtgryScFrDTO) throws Exception {		
		List<MallDspFoCtgryResult> result = getSession1().selectList("com.plgrim.ncp.biz.dxm.display.selectCategotyNaviNm", dspCtgryScFrDTO);		
		return result;
	}
}
