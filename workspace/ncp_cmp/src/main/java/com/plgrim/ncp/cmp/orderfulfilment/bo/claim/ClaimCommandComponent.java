/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jhkhan.cha
 * @since       2015. 4. 22       
 */
package com.plgrim.ncp.cmp.orderfulfilment.bo.claim;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.biz.claim.data.ClaimBoDTO;
import com.plgrim.ncp.biz.claim.data.ClaimSearchDTO;
import com.plgrim.ncp.biz.claim.data.ClmDlvOrdGodInfoDTO;
import com.plgrim.ncp.biz.claim.data.ClmOrdGodAplPrm;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.data.OrderDTO;
import com.plgrim.ncp.framework.data.SystemPK;

/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author jhkhan.cha
 * @since 2015. 4. 17
 */
public interface ClaimCommandComponent {

	/**
	 * [메서드 설명].
	 * 클레임접수 주문전체취소
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param claimSearchDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 17
	 */
	public String updateClaimTotalCancel(ClaimSearchDTO claimSearchDTO,SystemPK systemPK) throws Exception;
	
	/**
	 * [메서드 설명].
	 * 클레임접수 주문부분취소
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param claimSearchDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 17
	 */
	public String updateClaimUnitCancel(HttpServletRequest request, ClaimSearchDTO claimSearchDTO ,SystemPK systemPK) throws Exception;
	
	
	
	
	/***************************************************************************************************
	 * Khan
	 * *************************************************************************************************/
	
	/**
	 * [주문 - 반품접수].
	 * 
	 * @param claimBoDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public String addReturnClaim(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception;
	
	
	/**
	 * [주문 - 반품메모저장].
	 * 
	 * @param clmNo [설명]
	 * @param memoCont [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 17
	 */
	public void updateCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) throws Exception;
	
	
	/**
	 * [주문 - 교환접수].
	 * 
	 * @param claimBoDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public String addExchangeClaim(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception;

	/**
	 * [클레임관리 - 입고완료].
	 * 
	 * @param claimBoDTO [설명]
	 * @return 
	 * @throws Exception the exception
	 * @since 2015. 4. 27
	 */
	public HashMap<String, Object> updateRtrvlStat(ClaimBoDTO claimBoDTO) throws Exception;

	/**
	 * 클레임 접수 처리
	 * @param claimBoDTO
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> updateClmRcept(ClaimBoDTO claimBoDTO) throws Exception;
	
	/**
	 * 클레임 접수 처리
	 * @param claimBoDTO
	 * @param systemPK
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> updateClmRcept(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception;

	/**
	 * [클레임관리 - 반품/교환상세 부분철회]
	 * @param claimBoDTO
	 * @param systemPK
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object>  updateClaimWthdraw(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception;
	
	/**
	 * 교환클레임 반품 전환
	 * @param claimBoDTO
	 * @param systemPK
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object>  switchReturn(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception;

	/**
	 * 환불계좌 변경처리
	 * @param claimBoDTO
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object>  updateRfdBnk(ClaimBoDTO claimBoDTO) throws Exception;

	/**
	 * PG 미연동 클레임 완료처리 버튼
	 * @param clmNo
	 * @throws Exception
	 */
	public void updateClmStatCmpl(String clmNo) throws Exception ;
	
	/**
	 * 취소 클레임 초도 배송비 결제
	 *
	 * @param orderDTO
	 * @param request
	 * @throws Exception
	 */
	public void updateCancelClmDlvAmtPayMethod(OrderDTO orderDTO, HttpServletRequest request) throws Exception;
	
	/**
	 * 교환/반품 배송비 결제
	 * 
	 * @param orderDTO
	 * @param request
	 * @throws Exception
	 */
	public void updateClmDlvAmtPayMethod(OrderDTO orderDTO, HttpServletRequest request) throws Exception;
	
	/**
	 * [개발] 불량상품 고객에 대한 교환/반품 Process 개선 요청
	 * @param claimBoDTO
	 * @param systemPK
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> updateClmChangeRcept(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception;

	/**
	 * 주문사은품 취소 상품 목록 조회
	 * 
	 * @param claimSearchDTO
	 * @param ordNo
	 * @return
	 * @throws Exception
	 */
	public List<ClmDlvOrdGodInfoDTO> getOrderGiftCancelGoodsList(ClaimSearchDTO claimSearchDTO, String ordNo) throws Exception;
	
	/**
	 * 주문사은품 취소 목록 조회
	 * 
	 * @param claimSearchDTO
	 * @param ordNo
	 * @return
	 * @throws Exception
	 */
	public List<ClmOrdGodAplPrm> getOrderGiftCancelPromotionList(ClaimSearchDTO claimSearchDTO, String ordNo) throws Exception;

	public String updateClaimPartCancel(ClaimBoDTO claimBoDTO, OrderBoDTO orderBoDTO, SystemPK systemPK) throws Exception;

	public void sendSalesInfoToErp(String ordNo, String clmNo, String mallId, boolean isDepstWait, boolean hasTempMileuse);
	
	public boolean refundPayForClaim(Clm clm, Ord ord, boolean isDepstWait, boolean hasTempMileuse, boolean isRefundReprocess) throws Exception;
	
	
	/**
	 * 클레임 환불오류 건 PG사 전송 대상 여부 N 변경
	 * 
	 * @param claimBoDTO
	 * @param systemPK
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> updateClmRfdErrPgTrnsmisTgtYn(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception;
}
