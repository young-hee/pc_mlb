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
 * @since       2015. 5. 26       
 */
package com.plgrim.ncp.biz.callcenter.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGodInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqOrdGod;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.biz.callcenter.result.CsoOthersMemberResult;

/**
 * [회원에서 사용하는 CS 정보 Repository]
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
@Repository
public class CsoOthersMemberRepository extends AbstractRepository{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 회원 환불 건수 조회.
	 *
	 * @param mbr [설명]
	 * @return Long [설명]
	 * @since 2015. 5. 15
	 */
    public long selectRefundListCountForMember(Mbr mbr) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.others.member.selectRefundListCountForMember", mbr);
	}
	
	/**
	 * 회원 환불 목록 조회.
	 *
	 * @param mbr [설명]
	 * @return List [설명]
	 * @since 2015. 5. 15
	 */
    public List<CsoOthersMemberResult> selectRefundListForMember(Mbr mbr) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.others.member.selectRefundListForMember", mbr);
	}
    
	/**
	 * 회원 환불 엑셀 조회.
	 *
	 * @param mbr [설명]
	 * @return List [설명]
	 * @since 2015. 5. 15
	 */
    public List<Map<String, Object>> selectRefundExcelForMember(Mbr mbr) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.others.member.selectRefundExcelForMember", mbr);
	}
    
	/**
	 * 회원 상품문의 건수 조회.
	 *
	 * @param mbr [설명]
	 * @return Long [설명]
	 * @since 2015. 5. 15
	 */
    public long selectGoodsInquiryListCountForMember(CsoGodInq cgi) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.others.member.selectGoodsInquiryListCountForMember", cgi);
	}
	
	/**
	 * 회원 상품문의 목록 조회.
	 *
	 * @param mbr [설명]
	 * @return List [설명]
	 * @since 2015. 5. 15
	 */
    public List<CsoOthersMemberResult> selectGoodsInquiryListForMember(CsoGodInq cgi) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.others.member.selectGoodsInquiryListForMember", cgi);
	}
    
	/**
	 * 회원 상품문의 엑셀 조회.
	 *
	 * @param mbr [설명]
	 * @return List [설명]
	 * @since 2015. 5. 15
	 */
    public List<Map<String, Object>> selectGoodsInquiryExcelForMember(CsoGodInq cgi) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.others.member.selectGoodsInquiryExcelForMember", cgi);
	}
    
	/**
	 * 상품문의 삭제여부 변경.
	 *
	 * @param cgi [설명]
	 * @since 2015. 5. 19
	 */
    public void updateGoodsInquiryForDeleteYn(CsoGodInq cgi) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.others.member.updateGoodsInquiryForDeleteYn", cgi);
	}
    
	/**
	 * 회원 일대일문의 건수 조회.
	 *
	 * @param mbr [설명]
	 * @return Long [설명]
	 * @since 2015. 5. 15
	 */
    public long selectMtmInquiryListCountForMember(CsoMtmInq cmi) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.others.member.selectMtmInquiryListCountForMember", cmi);
	}
	
	/**
	 * 회원 일대일문의 목록 조회.
	 *
	 * @param mbr [설명]
	 * @return List [설명]
	 * @since 2015. 5. 15
	 */
    public List<CsoOthersMemberResult> selectMtmInquiryListForMember(CsoMtmInq cmi) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.others.member.selectMtmInquiryListForMember", cmi);
	}
    
	/**
	 * 회원 일대일문의 엑셀 조회.
	 *
	 * @param mbr [설명]
	 * @return List [설명]
	 * @since 2015. 5. 15
	 */
    public List<Map<String, Object>> selectMtmInquiryExcelForMember(CsoMtmInq cmi) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.others.member.selectMtmInquiryExcelForMember", cmi);
	}
    
	/**
	 * 회원 일대일문의 주문상품 목록 조회.
	 *
	 * @param cmi [설명]
	 * @return List [설명]
	 * @since 2015. 5. 15
	 */
    public List<OrdGod> selectMtmInquiryOrdGodList(CsoMtmInqOrdGod cmiog) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.others.member.selectMtmInquiryOrdGodList", cmiog);
	}
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
