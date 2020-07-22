/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 6. 17       
 */
package com.plgrim.ncp.biz.callcenter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGodInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqOrdGod;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.biz.callcenter.repository.CsoOthersMemberRepository;
import com.plgrim.ncp.biz.callcenter.result.CsoOthersMemberResult;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

/**
 * [회원에서 사용하는 CS 정보 Service]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [환불]
 *   <li> [상품QNA]
 *   <li> [1:1문의]
 * </ul>.
 *
 * @author sy59.gim
 * @since 2015. 5. 15
 */
@Slf4j
@Service
public class CsoOthersMemberService {
	
	/** 회원에서 사용하는 CS 정보 repository. */
	@Autowired
	CsoOthersMemberRepository csoOthersMemberRepository;

	/**
	 * 회원 환불 목록 조회.
	 *
	 * @param mbrNo [설명]
	 * @param pageParam [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 4
	 */
    public Page<CsoOthersMemberResult> selectRefundListForMember(SystemPK systemPK, Mbr reqMbr, PageParam pageParam) throws Exception {
    	// 페이지 인덱스 셋팅
    	Mbr mbr = reqMbr;
    	mbr.setLang(systemPK.getLang());
    	RepositoryHelper.makePageEntityIndex(mbr, pageParam);
    	
    	// step 2. 목록 건수 조회
    	long listCount = csoOthersMemberRepository.selectRefundListCountForMember(mbr);
    	
    	// step 3. 목록 조회
		List<CsoOthersMemberResult> lists = new ArrayList<CsoOthersMemberResult>();
		if(listCount > 0){
			lists = csoOthersMemberRepository.selectRefundListForMember(mbr);
		}
		
		// TODO : 계좌 마스킹 처리 (기존 복호화시 마스킹 되는 부분 제거함)
		// step 4. 환불 계좌 복호화 및 마스킹 처리
//		String encAcct = "";
//		String viewAcct = "";
//		boolean isMasking = StringService.equalsIgnoreCase(mbr.getMaskingYn(), MemberEnum.YES.toString()) ? true : false;
//		RefundAccountUtil rau = new RefundAccountUtil();
//		for(CsoOthersMemberResult comResult : lists){
//			encAcct = comResult.getPayRfd().getRfdBnkAcctNo();
//			try{
//				viewAcct = rau.getDecryptRefundAccount(encAcct, isMasking, mbr.getMbrNo(), null);
//				if(StringService.isEmpty(viewAcct)) {continue;}
//			} catch (Exception e){
//				if(log.isInfoEnabled()) {log.info("> getDecryptRefundAccount FAIL : {}",  e);}
//				continue;
//			}
//			comResult.getPayRfd().setRfdBnkAcctNo(viewAcct);
//		}
		
    	return new PageImpl<CsoOthersMemberResult>(lists, pageParam.getPageable(), listCount);
	}
    
	/**
	 * 회원 환불 엑셀 조회.
	 *
	 * @param mbr [설명]
	 * @return List [설명]
	 * @since 2015. 5. 15
	 */
    public List<Map<String, Object>> selectRefundExcelForMember(SystemPK systemPK, Mbr reqMbr) {
    	Mbr mbr = reqMbr;
    	mbr.setLang(systemPK.getLang());
    	
    	List<Map<String, Object>> lists = csoOthersMemberRepository.selectRefundExcelForMember(mbr);
    	
		// TODO : 계좌 마스킹 처리 (기존 복호화시 마스킹 되는 부분 제거함)
		// step 4. 환불 계좌 복호화 및 마스킹 처리
//		String encAcct = "";
//		String viewAcct = "";
//		boolean isMasking = StringService.equalsIgnoreCase(mbr.getMaskingYn(), MemberEnum.YES.toString()) ? true : false;
//		RefundAccountUtil rau = new RefundAccountUtil();
//		for(Map<String, Object> comResult : lists){
//			encAcct = StringService.trimToEmpty((String) comResult.get("RFD_BNK_ACCT_NO"));
//			try{
//				viewAcct = rau.getDecryptRefundAccount(encAcct, isMasking, mbr.getMbrNo(), null);
//				if(StringService.isEmpty(viewAcct)) {continue;}
//			} catch (Exception e){
//				if(log.isInfoEnabled()) {log.info("> getDecryptRefundAccount FAIL : {}",  e);}
//				continue;
//			}
//			comResult.put("RFD_BNK_ACCT_NO", viewAcct);
//		}
//    	
		return csoOthersMemberRepository.selectRefundExcelForMember(mbr);
	}

	/**
	 * 회원 상품문의 목록 조회.
	 *
	 * @param mbrNo [설명]
	 * @param pageParam [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 4
	 */
    public Page<CsoOthersMemberResult> selectGoodsInquiryListForMember(SystemPK systemPK, CsoGodInq cgi, PageParam pageParam) throws Exception {
    	// 페이지 인덱스 셋팅
    	cgi.setLang(systemPK.getLang());
    	RepositoryHelper.makePageEntityIndex(cgi, pageParam);
    	
    	// step 2. 목록 건수 조회
    	long listCount = csoOthersMemberRepository.selectGoodsInquiryListCountForMember(cgi);
    	
    	// 목록 조회
		List<CsoOthersMemberResult> lists = new ArrayList<CsoOthersMemberResult>();
		if(listCount > 0){
			lists = csoOthersMemberRepository.selectGoodsInquiryListForMember(cgi);
		}
    	
    	return new PageImpl<CsoOthersMemberResult>(lists, pageParam.getPageable(), listCount);
	}
    
	/**
	 * 회원 상품문의 엑셀 조회.
	 *
	 * @param mbr [설명]
	 * @return List [설명]
	 * @since 2015. 5. 15
	 */
    public List<Map<String, Object>> selectGoodsInquiryExcelForMember(SystemPK systemPK, CsoGodInq cgi) {
    	cgi.setLang(systemPK.getLang());
    	
		return csoOthersMemberRepository.selectGoodsInquiryExcelForMember(cgi);
	}
    
	/**
	 * 상품문의 삭제여부 변경.
	 *
	 * @param cgi [설명]
	 * @since 2015. 5. 19
	 */
    public void updateGoodsInquiryForDeleteYn(CsoGodInq cgi) {
    	csoOthersMemberRepository.updateGoodsInquiryForDeleteYn(cgi);
	}


	/**
	 * 회원 1:1 문의 목록 조회.
	 *
	 * @param mbrNo [설명]
	 * @param pageParam [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 4
	 */
    public Page<CsoOthersMemberResult> selectMtmInquiryListForMember(SystemPK systemPK, CsoMtmInq cmi, PageParam pageParam) throws Exception {
    	// 페이지 인덱스 셋팅
    	cmi.setLang(systemPK.getLang());
    	RepositoryHelper.makePageEntityIndex(cmi, pageParam);
    	
    	// step 2. 목록 건수 조회
    	long listCount = csoOthersMemberRepository.selectMtmInquiryListCountForMember(cmi);
    	
    	// 목록 조회
		List<CsoOthersMemberResult> lists = new ArrayList<CsoOthersMemberResult>();
		if(listCount > 0){
			lists = csoOthersMemberRepository.selectMtmInquiryListForMember(cmi);
		}
    	
    	return new PageImpl<CsoOthersMemberResult>(lists, pageParam.getPageable(), listCount);
	}
    
	/**
	 * 회원 일대일문의 엑셀 조회.
	 *
	 * @param mbr [설명]
	 * @return List [설명]
	 * @since 2015. 5. 15
	 */
    public List<Map<String, Object>> selectMtmInquiryExcelForMember(SystemPK systemPK, CsoMtmInq cmi) {
    	cmi.setLang(systemPK.getLang());
    	
		return csoOthersMemberRepository.selectMtmInquiryExcelForMember(cmi);
	}
    
	/**
	 * 회원 일대일문의 주문상품 목록 조회.
	 *
	 * @param mtmInqNo [설명]
	 * @param ordNo [설명]
	 * @return List [설명]
	 * @since 2015. 5. 15
	 */
    public List<OrdGod> selectMtmInquiryOrdGodList(CsoMtmInqOrdGod cmiog) {
		return csoOthersMemberRepository.selectMtmInquiryOrdGodList(cmiog);
	}
	
}
