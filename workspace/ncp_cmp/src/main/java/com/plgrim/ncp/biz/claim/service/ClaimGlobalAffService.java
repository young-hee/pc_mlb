/* Copyright (c) 2013 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 *                       
 */
package com.plgrim.ncp.biz.claim.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspHist;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdClmGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrm;
import com.plgrim.ncp.base.repository.lgs.LgsDlvHistRepository;
import com.plgrim.ncp.base.repository.lgs.LgsDlvspHistRepository;
import com.plgrim.ncp.biz.claim.data.InfAffClmDTO;
import com.plgrim.ncp.biz.claim.data.LgsDlvExtendForClm;
import com.plgrim.ncp.biz.claim.repository.ClaimGlobalAffLgsDlvRepository;
import com.plgrim.ncp.biz.claim.repository.ClaimGlobalAffRepository;
import com.plgrim.ncp.biz.claim.repository.ClaimGlobalAffLgsDlvspRepository;

/**
 * 클레임 글로벌 제휴사 서비스
 * @author aether
 *
 */
@Slf4j
@Service
public class ClaimGlobalAffService extends AbstractService {
	
	
	@Autowired
	private ClaimGlobalAffRepository claimGlobalAffRepository;
	
	@Autowired
	private ClaimGlobalAffLgsDlvRepository claimGlobalAffLgsDlvRepository;
	
	@Autowired
	private ClaimGlobalAffLgsDlvspRepository claimGlobalAffLgsDlvspRepository;
	
	@Autowired
	LgsDlvspHistRepository lgsDlvspHistRepository;// 물류배송지 이력
	
	@Autowired
	LgsDlvHistRepository lgsDlvHistRepository;// 물류배송 이력

	
	/**
	 * 
	* <pre>
	*	제휴사 클레임정보를 select 한다.
	* </pre>
	* @param infAffClmSearch
	* @return
	* @since 2015. 11. 13.
	 */
	public List<InfAffClmDTO> selectInfAffClm(InfAffClmDTO infAffClmSearch) {		
		return claimGlobalAffRepository.selectInfAffClm(infAffClmSearch);
	}


	/**
	 * 
	* <pre>
	* 	검색 조건으로 OrdGodErp 단위의 list 를 구한다.
	* </pre>
	* @param infAff
	* @return
	* @since 2015. 11. 16.
	 */
	public List<InfOrdGodErpDstbExtend> selectOrdGodErpBaseList(
            InfAffClmDTO infAff) {
		return claimGlobalAffRepository.selectOrdGodErpBaseList(infAff);
    }


	/**
	 * 
	* <pre>
	*	검색조건으로 주문상품 적용 프로모션 정보의 리스트를 구한다.
	* </pre>
	* @param searchOrdGodAplPrm
	* @return
	* @since 2015. 11. 16.
	 */
	public List<OrdGodAplPrm> selectOrdGodAplPrm(OrdGodAplPrm searchOrdGodAplPrm) {
		return claimGlobalAffRepository.selectOrdGodAplPrm(searchOrdGodAplPrm);
    }


	/**
	 * 
	* <pre>
	*	주문클레임 상품 연결정보를 insert 한다.
	* </pre>
	* @param ordClmGodCnnc
	* @since 2015. 11. 16.
	 */
	public void insertOrdClmGodCnnc(OrdClmGodCnnc ordClmGodCnnc) {
		claimGlobalAffRepository.insertOrdClmGodCnnc(ordClmGodCnnc);
    }


	public void updateInfOrdGodErpDstbByClmUnit(InfOrdGodErpDstb infOrdGodErpDstb) {
		claimGlobalAffRepository.updateInfOrdGodErpDstbByClmUnit(infOrdGodErpDstb);
    }


	/////////////////////////////////////////    반품  	/////////////////////////////////////////////////////
	
	
	
	public List<LgsDlvsp> selectOrdLgsDlvsp(LgsDlvsp ordLgsDlvSpSearch) {
		return claimGlobalAffRepository.selectOrdLgsDlvsp(ordLgsDlvSpSearch);
    }

	

	public void insertLgsDlvSp(LgsDlvsp ordLgsDlvSpResult) throws Exception {
		claimGlobalAffLgsDlvspRepository.insertLgsDlvsp(ordLgsDlvSpResult);
    }


	public void insertLgsDlvByOrdLgsDlv(LgsDlvExtendForClm lgsDlvSearch) {
		claimGlobalAffRepository.insertLgsDlvByOrdLgsDlv(lgsDlvSearch);
    }


	public LgsDlvsp selectLgsDlvsp(LgsDlvsp ordLgsDlvSpSearch) throws Exception {
		return claimGlobalAffLgsDlvspRepository.selectLgsDlvsp(ordLgsDlvSpSearch);
    }


	public void insertLgsDlvspHist(LgsDlvspHist lgsDlvspHist) throws Exception { 
		lgsDlvspHistRepository.insertLgsDlvspHist(lgsDlvspHist);
    }


	public void updateInfAffClmForRegClmNo(InfAffClmDTO infAffClmDTO) {
		claimGlobalAffRepository.updateInfAffClmForRegClmNo(infAffClmDTO);
    }

	

}
