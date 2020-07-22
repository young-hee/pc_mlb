package com.plgrim.ncp.biz.claim.repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdErpTrnsmis;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdErpTrnsmisHist;
import com.plgrim.ncp.base.entities.datasource1.pay.PayExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.PayRfd;
import com.plgrim.ncp.biz.claim.data.*;
import com.plgrim.ncp.biz.claim.result.ClaimRefundVirtualCdResult;
import com.plgrim.ncp.biz.claim.result.ClaimReturnResult;
import com.plgrim.ncp.framework.commons.IdGenService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chakhan
 *
 */
@Repository
@Slf4j
public class ClaimBatchRepository extends AbstractRepository {
	
	@Autowired
	IdGenService idGenService;

	public void updateOrdPayCancelAcclAmt(PayExtend payDto) {
		getSession1().update("com.plgrim.ncp.biz.claim.batch.updateOrdPayCancelAcclAmt", payDto);
    }

	public void insertClmPay(PayExtend payDto) {
		getSession1().insert("com.plgrim.ncp.biz.claim.batch.insertClmPay", payDto);	    
    }

	public void insertClmPayRfd(PayRfd payRfd) {
		/*getSession1().insert("com.plgrim.ncp.biz.claim.batch.insertClmPayRfd", payRfd);*/
    }

	public String getRepayGodCpn(PayExtend payDto) {	    
	    return getSession1().selectOne("com.plgrim.ncp.biz.claim.batch.getRepayGodCpn", payDto);
    }
	
	public List<ClaimBatchTargetDTO> selectClaimTargetList(ClaimBatchSearchDTO claimBatchSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.batch.selectClaimTargetList", claimBatchSearchDTO);
    }

	public void updateClmErpTrnsmis(ClmErpTrnsmis totalCancelClmErpTrnsmis) {
		getSession1().update("com.plgrim.ncp.biz.claim.batch.updateClmErpTrnsmis", totalCancelClmErpTrnsmis);
    }

	public void updateOrdErpTrnsmis(ClmErpTrnsmis totalCancelClmErpTrnsmis) {
		getSession1().update("com.plgrim.ncp.biz.claim.batch.updateOrdErpTrnsmis", totalCancelClmErpTrnsmis);
    }

	public List<ReturnStoreOrderTargetDTO> selectReturnStoreOrderTargetList(
            ClaimBatchTargetDTO dto) {
	    return getSession1().selectList("com.plgrim.ncp.biz.claim.batch.selectReturnStoreOrderTargetList", dto);
    }

	public ReturnStoreExchgOriDTO selectReturnStoreExchgOri(
            ReturnStoreOrderTargetDTO returnStore) {
	    return getSession1().selectOne("com.plgrim.ncp.biz.claim.batch.selectReturnStoreExchgOri", returnStore);
    }

	public void updateOrdGodErpClmErpYn(
            ClaimBatchOrdGodErpClmErp claimBatchOrdGodErpClmErp) {
		getSession1().update("com.plgrim.ncp.biz.claim.batch.updateOrdGodErpClmErpYn", claimBatchOrdGodErpClmErp);
    }

	public void updateDrtExchgRcptfr(
            ClaimBatchDrtExchgRcptfrDTO claimBatchDrtExchgRcptfrDTO) {
		getSession1().update("com.plgrim.ncp.biz.claim.batch.updateDrtExchgRcptfr", claimBatchDrtExchgRcptfrDTO);
    }

	public void updateClmErpWthdraw(ClaimBatchTargetDTO dto) {
		getSession1().update("com.plgrim.ncp.biz.claim.batch.updateClmErpWthdraw", dto);
    }

	public List<ReleaseStockTargetDTO> selectReleaseStockTargetList(ClaimBatchTargetDTO dto) {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.batch.selectReleaseStockTargetList", dto);
    }

	public void insertOrdErpTrnsmisHist(OrdErpTrnsmisHist ordErpTrnsmisHist) {
		getSession1().insert("com.plgrim.ncp.biz.claim.batch.insertOrdErpTrnsmisHist", ordErpTrnsmisHist);
    }

	public void insertOrdErpTrnsmis(OrdErpTrnsmis ordErpTrnsmis) {
		getSession1().insert("com.plgrim.ncp.biz.claim.batch.insertOrdErpTrnsmis", ordErpTrnsmis);
    }
	
	public void updateLgsRtrvlDrctGodErpTrnsmisYn(ReturnStoreOrderDTO returnStoreOrderDTO) {
		getSession1().update("com.plgrim.ncp.biz.claim.batch.updateLgsRtrvlDrctGodErpTrnsmisYn", returnStoreOrderDTO);
    }

	public ClaimRefundVirtualCdResult selectClaimRefundVirtualCd(String clmNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.batch.selectClaimRefundVirtualCd", clmNo);
	}

	public String selectPcupsDlvCompNm(String clmNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.batch.selectPcupsDlvCompNm", clmNo);
	}

	/**
	 * 반품처리를 위한 클레임 정보를 조회
	 * @param clmNo 클레임번호
	 * @return ClaimReturnResult
	 */
	public ClaimReturnResult selectClaim4Return(String clmNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.batch.selectClaim4Return", clmNo);
	}

	/**
	 * 변경될 'ERP상품일련번호' 의 '수량순번' 조회
	 * @param clmErpGodSnExchgDTO
	 * @return '수량순번'
	 */
	public String selectQtyTurnByErpGodSn(ClmErpGodSnExchgDTO clmErpGodSnExchgDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.batch.selectQtyTurnByErpGodSn", clmErpGodSnExchgDTO);
	}

	/**
	 * 기 등록되어 있는 클레임에 대해서 아직 회수완료되지 않은 'ERP상품일련번호' 로 변경
	 * @param clmErpGodSnExchgDTO
	 */
	public void modifyErpGodSn(ClmErpGodSnExchgDTO clmErpGodSnExchgDTO) {
		getSession1().update("com.plgrim.ncp.biz.claim.batch.modifyErpGodSn", clmErpGodSnExchgDTO);
	}

	/**
	 * '멤버쉽포인트 임시삭감 환원' 배치(ErpPntTrnsmisJob) 기능에 대해 확인
	 * 	- 멤버쉽포인트 부여 데이터 생성
	 * @return
	 */
/*
	public void addErpPntTrnsmis(OrdErpPntTrnsmis erpPntTransmis) {
		getSession1().insert("com.plgrim.ncp.biz.claim.batch.addErpPntTrnsmis", erpPntTransmis);
	}
*/
	/**
	 * '멤버쉽포인트 임시삭감 환원' 배치(ErpPntTrnsmisJob) 기능에 대해 확인
	 * 	- 멤버쉽포인트 부여 데이터 생성
	 * @return
	 */
	public void addErpPntTrnsmisHist(OrdErpTrnsmisHist erpPntTransmisHist) {
		getSession1().insert("com.plgrim.ncp.biz.claim.batch.addErpPntTrnsmisHist", erpPntTransmisHist);
	}

	/**
	 * '멤버쉽포인트 임시삭감 환원' 배치(ErpPntTrnsmisJob) 기능에 대해 확인
	 * 	- 멤버쉽포인트 부여 데이터 생성
	 * @return
	 */
/*	
	public void addErpPntTrnsmis4Fail(OrdErpPntTrnsmis erpPntTransmis) {
		getSession1().insert("com.plgrim.ncp.biz.claim.batch.addErpPntTrnsmis4Fail", erpPntTransmis);
	}
*/	
	/**
	 * 주문사용쿠폰개수 조회
	 * 
	 * @param mbrIssuCpn
	 */
	public int getOrderUseCouponCount(MbrIssuCpn mbrIssuCpn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.batch.getOrderUseCouponCount", mbrIssuCpn);	    
    }
}
