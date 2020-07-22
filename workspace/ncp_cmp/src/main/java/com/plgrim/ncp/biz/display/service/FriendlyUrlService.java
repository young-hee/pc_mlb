package com.plgrim.ncp.biz.display.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.biz.display.data.DspCtgryScFrDTO;
import com.plgrim.ncp.biz.display.repository.FriendlyUrlRepository;
import com.plgrim.ncp.biz.display.result.DspCtgryFrResult;

@Service
public class FriendlyUrlService extends AbstractService {
	
	@Autowired
	FriendlyUrlRepository friendlyUrlRepository;
	
	/**
	 * 상품 브랜드정보 조회
	 */
	public Map<String,Object> selectGodBrandInfo(HashMap<String,Object> map) throws Exception {
		return friendlyUrlRepository.selectGodBrandInfo(map);
	}

	/**
	 * 전시카테고리 기본정보 조회.
	 */
	public DspCtgryFrResult selectDspCtgryFrInfo(DspCtgryScFrDTO dto) throws Exception {
		return friendlyUrlRepository.selectDspCtgryFrInfo(dto);
	}
	
	/**
	 * 전시 카테고리별 매타 data 조회
	 */
	public List<DspCtgry> selectMetaDspCtgryList(DspCtgry dspCtgry) throws Exception {
		return friendlyUrlRepository.selectMetaDspCtgryList(dspCtgry);
	}
}
