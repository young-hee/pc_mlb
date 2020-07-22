package com.plgrim.ncp.commons.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.commons.data.ZipcodeDTO;
import com.plgrim.ncp.commons.result.ZipcodeResult;
import com.plgrim.ncp.framework.page.PageParam;

@Repository
public class ZipcodeRepository extends AbstractRepository{

	public Page<ZipcodeResult> selectSearchZipcode(ZipcodeDTO zipcodeDTO) throws Exception {
		
		PageParam pageParam = getPageService().buildPageParam(zipcodeDTO.getGPageNo(), zipcodeDTO.getGPageSize());
		RepositoryHelper.makePageEntityIndex(zipcodeDTO, pageParam);
		
		List<ZipcodeResult> zipcodeList = getSession1().selectList("com.plgrim.ncp.commons.sysezipcode.selectSearchZipcode", zipcodeDTO);
		long totalRow =  getSession1().selectOne("com.plgrim.ncp.commons.sysezipcode.countSearchZipcode", zipcodeDTO);

		return new PageImpl<ZipcodeResult>(zipcodeList, pageParam.getPageable(), totalRow);
    }
	
public Page<ZipcodeResult> selectSearchZipcode(ZipcodeDTO zipcodeDTO, PageParam pageParam) throws Exception {
		
		//PageParam pageParam = getPageService().buildPageParam(zipcodeDTO.getGPageNo(), zipcodeDTO.getGPageSize());		
		RepositoryHelper.makePageEntityIndex(zipcodeDTO, pageParam);
		
		List<ZipcodeResult> zipcodeList = getSession1().selectList("com.plgrim.ncp.commons.sysezipcode.selectSearchZipcode", zipcodeDTO);
		long totalRow =  getSession1().selectOne("com.plgrim.ncp.commons.sysezipcode.countSearchZipcode", zipcodeDTO);

		return new PageImpl<ZipcodeResult>(zipcodeList, pageParam.getPageable(), totalRow);
    }

}
