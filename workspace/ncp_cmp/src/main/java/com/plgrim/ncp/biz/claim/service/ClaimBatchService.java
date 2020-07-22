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

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdErpTrnsmis;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdErpTrnsmisHist;
import com.plgrim.ncp.base.entities.datasource1.pay.PayExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.PayRfd;
import com.plgrim.ncp.biz.claim.data.ClaimBatchDrtExchgRcptfrDTO;
import com.plgrim.ncp.biz.claim.data.ClaimBatchOrdGodErpClmErp;
import com.plgrim.ncp.biz.claim.data.ClaimBatchSearchDTO;
import com.plgrim.ncp.biz.claim.data.ClaimBatchTargetDTO;
import com.plgrim.ncp.biz.claim.data.ClmErpGodSnExchgDTO;
import com.plgrim.ncp.biz.claim.data.ClmErpTrnsmis;
import com.plgrim.ncp.biz.claim.data.ReleaseStockTargetDTO;
import com.plgrim.ncp.biz.claim.data.ReturnStoreExchgOriDTO;
import com.plgrim.ncp.biz.claim.data.ReturnStoreOrderDTO;
import com.plgrim.ncp.biz.claim.data.ReturnStoreOrderTargetDTO;
import com.plgrim.ncp.biz.claim.repository.ClaimBatchRepository;
import com.plgrim.ncp.biz.claim.repository.ClaimRepository;
import com.plgrim.ncp.biz.claim.repository.ClmEntityRepository;
import com.plgrim.ncp.biz.claim.repository.ClmErpPntTrnsmisEntityRepository;
import com.plgrim.ncp.biz.claim.repository.ClmWrhsGodAplPrmEntityRepository;
import com.plgrim.ncp.biz.claim.repository.ClmWrhsGodEntityRepository;
import com.plgrim.ncp.biz.claim.result.ClaimRefundVirtualCdResult;
import com.plgrim.ncp.biz.claim.result.ClaimReturnResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 클레임 배치 서비스
 * @author aether
 *
 */
@Slf4j
@Service
public class ClaimBatchService extends AbstractService {


	@Autowired
	ClmEntityRepository clmEntityRepository;
	
	@Autowired
	ClaimBatchRepository claimBatchRepository;
	
	@Autowired
	ClmErpPntTrnsmisEntityRepository clmErpPntTrnsmisEntityRepository;
	
	@Autowired
	ClmWrhsGodAplPrmEntityRepository clmWrhsGodAplPrmEntityRepository;
	
	@Autowired
	ClmWrhsGodEntityRepository clmWrhsGodEntityRepository;
	
	@Autowired
	ClaimRepository claimRepository;

    @Autowired
    @Qualifier("sessionTemplate1")
    SqlSession sqlSession1;
	
	
	
    /**
     * 클레임 번호로 클레임 정보를 들고온다.
     * @param clm
     * @return
     * @throws Exception
     */	
    public Clm selectClmByClmNo(Clm clm) throws Exception{
	    return clmEntityRepository.selectClm(clm);
    }


    /**
     * 클레임 원주문 결제 취소 누적금액 누적
     * @param payDto
     */
	public void updateOrdPayCancelAcclAmt(PayExtend payDto) {
		claimBatchRepository.updateOrdPayCancelAcclAmt(payDto);
    }

	/**
	 * 클레임 취소 결제정보 insert
	 * @param payDto
	 */
	public void insertClmPay(PayExtend payDto) {
		claimBatchRepository.insertClmPay(payDto);
    }

	/**
	 * 클레임 취소 결제 환불정보 insert
	 * @param payRfd
	 */
	public void insertClmPayRfd(PayRfd payRfd) {
		claimBatchRepository.insertClmPayRfd(payRfd);
    }

	/**
	 * 쿠폰복원 대상 여부 조회
	 * @param payDto
	 * @return
	 */
	public String getRepayGodCpn(PayExtend payDto) {
		return claimBatchRepository.getRepayGodCpn(payDto);
    }
	
	/**
	 * 클레임 대상 리스트
	 */
	public List<ClaimBatchTargetDTO> selectClaimTargetList(ClaimBatchSearchDTO claimBatchSearchDTO) {
		return claimBatchRepository.selectClaimTargetList(claimBatchSearchDTO);
    }


	public void updateClmErpTrnsmis(ClmErpTrnsmis totalCancelClmErpTrnsmis) {
		claimBatchRepository.updateClmErpTrnsmis(totalCancelClmErpTrnsmis);
    }


	public void updateOrdErpTrnsmis(ClmErpTrnsmis totalCancelClmErpTrnsmis) {
		claimBatchRepository.updateOrdErpTrnsmis(totalCancelClmErpTrnsmis);
    }


	public List<ReturnStoreOrderTargetDTO> selectReturnStoreOrderTargetList(
            ClaimBatchTargetDTO dto) {
	    return claimBatchRepository.selectReturnStoreOrderTargetList(dto);
    }


	public ReturnStoreExchgOriDTO selectReturnStoreExchgOri(
            ReturnStoreOrderTargetDTO returnStore) {
	    return claimBatchRepository.selectReturnStoreExchgOri(returnStore);
    }


	public void updateOrdGodErpClmErpYn(
            ClaimBatchOrdGodErpClmErp claimBatchOrdGodErpClmErp) {
		claimBatchRepository.updateOrdGodErpClmErpYn(claimBatchOrdGodErpClmErp);
    }


	public void updateDrtExchgRcptfr(
            ClaimBatchDrtExchgRcptfrDTO claimBatchDrtExchgRcptfrDTO) {
		claimBatchRepository.updateDrtExchgRcptfr(claimBatchDrtExchgRcptfrDTO);
    }


	public void updateClmErpWthdraw(ClaimBatchTargetDTO dto) {
		claimBatchRepository.updateClmErpWthdraw(dto);
    }


	public List<ReleaseStockTargetDTO> selectReleaseStockTargetList(
            ClaimBatchTargetDTO dto) {
		return claimBatchRepository.selectReleaseStockTargetList(dto);
    }


	public void insertOrdErpTrnsmisHist(OrdErpTrnsmisHist ordErpTrnsmisHist) {
		claimBatchRepository.insertOrdErpTrnsmisHist(ordErpTrnsmisHist);
    }


	public void insertOrdErpTrnsmis(OrdErpTrnsmis ordErpTrnsmis) {
		claimBatchRepository.insertOrdErpTrnsmis(ordErpTrnsmis);
    }


	public void updateLgsRtrvlDrctGodErpTrnsmisYn(ReturnStoreOrderDTO returnStoreOrderDTO) {
		claimBatchRepository.updateLgsRtrvlDrctGodErpTrnsmisYn(returnStoreOrderDTO);
    }


	public ClaimRefundVirtualCdResult selectClaimRefundVirtualCd(String clmNo) {
		return claimBatchRepository.selectClaimRefundVirtualCd(clmNo);
	}

	public ClaimRefundVirtualCdResult selectClaimRefundVirtualCd(String clmNo, boolean isMask) throws Exception{
		//반품완료
		ClaimRefundVirtualCdResult result = this.selectClaimRefundVirtualCd(clmNo);

		if(result == null) {
			result = new ClaimRefundVirtualCdResult();
		}

		return result;
	}

	public String selectPcupsDlvCompNm(String clmNo){
		return claimBatchRepository.selectPcupsDlvCompNm(clmNo);
	}

	/**
	 * 반품처리를 위한 클레임 정보를 조회
	 * @param clmNo 클레임번호
	 * @return ClaimReturnResult
	 */
	public ClaimReturnResult selectClaim4Return( String clmNo ) {
		return claimBatchRepository.selectClaim4Return(clmNo);
	}

	/**
	 * 변경될 'ERP상품일련번호' 의 '수량순번' 조회
	 * @param clmErpGodSnExchgDTO
	 * @return '수량순번'
	 */
	public String selectQtyTurnByErpGodSn(ClmErpGodSnExchgDTO clmErpGodSnExchgDTO) {
		return claimBatchRepository.selectQtyTurnByErpGodSn(clmErpGodSnExchgDTO);
	}

	/**
	 * 기 등록되어 있는 클레임에 대해서 아직 회수완료되지 않은 'ERP상품일련번호' 로 변경
	 * @param clmErpGodSnExchgDTO
	 */
	public void modifyErpGodSn(ClmErpGodSnExchgDTO clmErpGodSnExchgDTO) {
		claimBatchRepository.modifyErpGodSn(clmErpGodSnExchgDTO);
	}

	/**
	 * '멤버쉽포인트 임시삭감 환원' 배치(ErpPntTrnsmisJob) 기능에 대해 확인
	 * 	- 멤버쉽포인트 부여 데이터 생성
	 * @return
	 */
	public void addErpPntTrnsmisHist(OrdErpTrnsmisHist erpPntTransmisHist) {
		claimBatchRepository.addErpPntTrnsmisHist(erpPntTransmisHist);
	}

	/**
	 * 주문사용쿠폰개수 조회
	 * 
	 * @param mbrIssuCpn
	 */
	public int getOrderUseCouponCount(MbrIssuCpn mbrIssuCpn) {
		return claimBatchRepository.getOrderUseCouponCount(mbrIssuCpn);  
    }
}
