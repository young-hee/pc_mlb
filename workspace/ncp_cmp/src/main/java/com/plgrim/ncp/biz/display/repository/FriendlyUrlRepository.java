package com.plgrim.ncp.biz.display.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.result.DspCtgryFrResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class FriendlyUrlRepository extends AbstractRepository{

	/**
	 * 상품 브랜드정보 조회
	 */
	@Cacheable(
			value="friendlyUrlRepository.selectGodBrandInfo", 
			key="{"
					+ "#p0['langCd'],"
					+ "#p0['godNo']"
					+ "}")
	public Map<String,Object> selectGodBrandInfo(HashMap<String,Object> map) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.goods.selectGodBrandInfo", map);
	}
	
	/**
	 * FriendlyUrl 전시카테고리 기본정보조회
	 */
	@Cacheable(
			value="friendlyUrlRepository.selectDspCtgryFrInfo", 
			key="{"
					+ "#p0.lang,"
					+ "#p0.mallId,"
					+ "#p0.dspCtgry?.dspCtgryNo"
					+ "}")
	public DspCtgryFrResult selectDspCtgryFrInfo(DspCtgryScFrDTO dto) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectFriendlyUrlDspCtgryInfo", dto);
	}
	
	/**
	 * 전시 카테고리별 매타 data 조회
	 */
	@Cacheable(
			value="friendlyUrlRepository.selectMetaDspCtgryList", 
			key="{"
					+ "#p0.aplMallId,"
					+ "#p0.lang"
					+ "}")
	public List<DspCtgry> selectMetaDspCtgryList(DspCtgry dspCtgry) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectMetaDspCtgryList", dspCtgry);
	}
}
