package com.plgrim.ncp.biz.claim.repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.clm.*;
import com.plgrim.ncp.base.entities.datasource1.com.ComChrger;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbRepair;
import com.plgrim.ncp.base.entities.datasource1.lgs.*;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdClmGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrm;
import com.plgrim.ncp.base.entities.datasource1.pay.PayExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.PayRfd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysExcpCd;
import com.plgrim.ncp.base.enums.OrderClaimEnum;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.biz.claim.data.*;
import com.plgrim.ncp.biz.claim.data.ClmErpTrnsmis;
import com.plgrim.ncp.biz.claim.result.*;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.goods.data.GoodsInventoryDTO;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.biz.promotion.data.MbrIssuCpnExtend;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chakhan
 *
 */
@Repository
@Slf4j
public class ClaimRepository extends AbstractRepository {
	
	@Autowired
	IdGenService idGenService;

	public long selectCountMtm(ClaimListSearchDTO searchDTO) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.getListClaimTotalCnt",searchDTO);
	}
	
	public List<ClaimListResult> getListClaim(ClaimListSearchDTO searchDTO, PageParam pageParam) throws Exception {
		

		
		List<ClaimListResult> results = new ArrayList<ClaimListResult>();
		

		results = getSession1().selectList("com.plgrim.ncp.biz.claim.manage.getListClaim", searchDTO);			



				
		return results;
    }

	public ClaimUserInfoDetailResult getClaimUserInfo(String clmNo) {
		ClaimUserInfoDetailResult result = getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.getClaimUserInfo", clmNo);
	    return result;
    }

	public ClaimInfoDetailResult getClaimInfo(String clmNo) {
		ClaimInfoDetailResult result = getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.getClaimInfo", clmNo);
	    return result;
	}

	public int getClmRceptCompt(ClaimSearchDTO claimSearchDTO) {
		int cnt = getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.getClmRceptCompt", claimSearchDTO);
	    return cnt;
    }

	public int checkExistClm(ClaimSearchDTO claimSearchDTO) {
		int cnt = getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.checkExistClm", claimSearchDTO);
	    return cnt;
    }

	public String insertClmByOrdGenClmNo(ClaimReceiptDTO claimTotalCancelDTO) throws NumberFormatException, Exception {
		String clmNo = claimTotalCancelDTO.getClmNo();		
		getSession1().insert("com.plgrim.ncp.biz.claim.manage.insertClmByOrd", claimTotalCancelDTO);
		return clmNo;
    }

	public void insertClmWrhsGodByOrdGod(ClaimReceiptDTO claimTotalCancelDTO) throws NumberFormatException, Exception {
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("CLM_NO", claimTotalCancelDTO.getClmNo());		
		Long clmWrhsGodTurn = Long.parseLong(idGenService.generateDBOrder(getSession1(),"CLM_WRHS_GOD","CLM_WRHS_GOD_TURN",map, DatabaseType.ORACLE)+"");
		claimTotalCancelDTO.setClmWrhsGodTurn(clmWrhsGodTurn);
		getSession1().insert("com.plgrim.ncp.biz.claim.manage.insertClmWrhsGodByOrdGod", claimTotalCancelDTO);		
    }
	
	public void insertUnitClmWrhsGodByOrdGod(ClaimReceiptDTO claimReceiptDTO) throws NumberFormatException, Exception {
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("CLM_NO", claimReceiptDTO.getClmNo());		
		Long clmWrhsGodTurn = Long.parseLong(idGenService.generateDBOrder(getSession1(),"CLM_WRHS_GOD","CLM_WRHS_GOD_TURN",map, DatabaseType.ORACLE)+"");
		claimReceiptDTO.setClmWrhsGodTurn(clmWrhsGodTurn);
		getSession1().insert("com.plgrim.ncp.biz.claim.manage.insertUnitClmWrhsGodByOrdGod", claimReceiptDTO);		   
    }

	public void insertOrdClmGodCnnc(
			ClmWrhsGodExtend clmWrhsGodExtend)  throws Exception{
		getSession1().insert("com.plgrim.ncp.biz.claim.manage.insertOrdClmGodCnnc", clmWrhsGodExtend);	    
    }
	
	public void updateClmStatCd(Clm clm) {
		getSession1().update("com.plgrim.ncp.biz.claim.manage.updateClmStatCd", clm);
    }	
	
	public void updateClmForAllWthdraw(Clm clm) {
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateClmForAllWthdraw", clm);		
	}
	
	public void insertOrdClmAplPrmByOrd(ClaimReceiptDTO claimReceiptDTO) {
		getSession1().insert("com.plgrim.ncp.biz.claim.manage.insertOrdClmAplPrmByOrd", claimReceiptDTO);
    }
	
	
	public void insertClmWrhsGodAplPrmByOrd(ClaimReceiptDTO claimReceiptDTO) {	    
		getSession1().insert("com.plgrim.ncp.biz.claim.manage.insertClmWrhsGodAplPrmByOrd", claimReceiptDTO);
    }
	
	public void updateClmWrhsGodToPrcByErp(ClaimReceiptDTO claimReceiptDTO) {
		getSession1().update("com.plgrim.ncp.biz.claim.manage.updateClmWrhsGodToPrcByErp", claimReceiptDTO);
    }
	
	public void updateClmToPrcByClmWrhsGod(ClaimReceiptDTO claimReceiptDTO) {
		getSession1().update("com.plgrim.ncp.biz.claim.manage.updateClmToPrcByClmWrhsGod", claimReceiptDTO);	    
    }
	

	public void updateRepairClmToPrcByClmWrhsGod(ClaimReceiptDTO claimReceiptDTO) {
		getSession1().update("com.plgrim.ncp.biz.claim.manage.updateRepairClmToPrcByClmWrhsGod", claimReceiptDTO);
    }

	public RefundResultDTO getClmPaySumAmt(ClmPaySumAmtSearchDTO clmPaySumAmtSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.getClmPaySumAmt", clmPaySumAmtSearchDTO);	     
    }
	
	
	

	
	/***************************************************************************************************
	 * Khan
	 * *************************************************************************************************/

	/**
	 * 클레임 등록
	 * 주문을 클레임에 조회후 저장한다. 
	 * @param claimBoDTO
	 * @throws Exception
	 */
	public void insertClmForRtrvl(ClaimBoDTO claimBoDTO) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.claim.return.insertClmForRtrvl", claimBoDTO);
    }
	
	/**
	 * 클레임입고상품 등록
	 * @param claimDTO
	 * @return
	 * @throws Exception
	 */
	public void insertClmWrhsGodForRtrvl(ClmWrhsGodExtend clmWrhsGodExtendDTO) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.claim.return.insertClmWrhsGodForRtrvl", clmWrhsGodExtendDTO);
    }

	/**
	 * 주문클레임상품연결 등록
	 * @param claimBoDTO
	 * @return
	 * @throws Exception
	 */
	public void insertOrdClmGodCnncForRtrvl(ClmWrhsGodExtend clmWrhsGodExtendDTO) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.claim.return.insertOrdClmGodCnncForRtrvl", clmWrhsGodExtendDTO);
    }

	/**
	 * 주문클레임적용프로모션 등록
	 * @param claimDTO
	 * @return
	 * @throws Exception
	 */
	public void insertOrdClmAplPrmForRtrvl(ClaimBoDTO claimDTO) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.claim.return.insertOrdClmAplPrmForRtrvl", claimDTO);
    }
	
	/**
	 * 클레임입고상품적용프로모션 등록
	 * @param claimDTO
	 * @return
	 * @throws Exception
	 */
	public void insertClmWrhsGodAplPrmForRtrvl(ClaimBoDTO claimDTO) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.claim.return.insertClmWrhsGodAplPrmForRtrvl", claimDTO);
    }
		
	/**
	 * 클레임번호 채번
	 * @param claimReturnSearchDTO
	 * @return
	 * @throws Exception
	 */
	public String selectClmNo() throws Exception {
		
		String clmNo = Long.toString(idGenService.generateDBSequence(getSession1(), "SQ_CLM", DatabaseType.ORACLE));
		return clmNo;
	}
	
	/**
	 * 클레임관리 - 입고완료/사은품가입고
	 * @param lgsRtrvlDrctGodExtend
	 * @throws Exception
	 */
	public void updateRtrvlStat(LgsRtrvlDrctGodExtend lgsRtrvlDrctGodExtend) throws Exception {
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateRtrvlStat", lgsRtrvlDrctGodExtend);
	}
	
	/**
	 * 클레임 메모저장
	 * @param csoCnsltMemo
	 * @throws Exception
	 */
	public void updateCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) throws Exception {

		Long memoSn = Long.parseLong(idGenService.generateDBSequence(getSession1(), "SQ_CSO_CNSLT_MEMO", DatabaseType.ORACLE)+"");
		csoCnsltMemo.setMemoSn(memoSn);
		
/*		String clmNo 		= claimDTO.getClmNo();
		String memoCont 	= claimDTO.getMemoCont();
		
		String udterId		= claimDTO.getUdterId();
        String memoRegtrId = claimDTO.getMemoRegtrId();
        String memoTpCd   	= claimDTO.getMemoTpCd();
        String expsrYn    	= claimDTO.getExpsrYn();
        String cstmrTpCd  	= claimDTO.getCstmrTpCd();
        String ordNo      	= claimDTO.getOrdNo();
        String mbrNo     	= claimDTO.getMbrNo();
        String regtrId   	= claimDTO.getRegtrId();
		
        log.info(">>>>>>>>>> [ClaimRepository.updateClaimMemo] clmNo        : " + clmNo 		+ "<<<<<<<<<<");
        log.info(">>>>>>>>>> [ClaimRepository.updateClaimMemo] memoCont 	: " + memoCont 		+ "<<<<<<<<<<");
		log.info(">>>>>>>>>> [ClaimRepository.updateClaimMemo] udterId	  	: " + udterId		+ "<<<<<<<<<<");
		log.info(">>>>>>>>>> [ClaimRepository.updateClaimMemo] memoRegtrId  : " + memoRegtrId 	+ "<<<<<<<<<<");
		log.info(">>>>>>>>>> [ClaimRepository.updateClaimMemo] memoTpCd     : " + memoTpCd   	+ "<<<<<<<<<<");
		log.info(">>>>>>>>>> [ClaimRepository.updateClaimMemo] expsrYn      : " + expsrYn    	+ "<<<<<<<<<<");
		log.info(">>>>>>>>>> [ClaimRepository.updateClaimMemo] cstmrTpCd    : " + cstmrTpCd  	+ "<<<<<<<<<<");
		log.info(">>>>>>>>>> [ClaimRepository.updateClaimMemo] ordNo        : " + ordNo      	+ "<<<<<<<<<<");
		log.info(">>>>>>>>>> [ClaimRepository.updateClaimMemo] mbrNo     	: " + mbrNo     	+ "<<<<<<<<<<");
		log.info(">>>>>>>>>> [ClaimRepository.updateClaimMemo] regtrId   	: " + regtrId   	+ "<<<<<<<<<<");*/
		getSession1().insert("com.plgrim.ncp.biz.claim.return.updateCsoCnsltMemo", csoCnsltMemo);
    }	

		
	/**
	 * 클레임 철회 기본정보조회
	 * @param claimReturnSearchDTO
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> selectClaimCheckInfo(ClaimReturnSearchDTO claimReturnSearchDTO) throws Exception {
		
		Map<String, String>  clmChkInfo = getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectClaimCheckInfo", claimReturnSearchDTO);
		
/*		String ordNo           	= clmChkInfo.get("ordNo"); 		
		String clmTpCd         	= clmChkInfo.get("clmTpCd");        
		String clmStatCd       	= clmChkInfo.get("clmStatCd");       
		String stdrCrncySumAmt	= clmChkInfo.get("stdrCrncySumAmt");  
		String chkCnt			= clmChkInfo.get("chkCnt");         
		String chkRtVal			= clmChkInfo.get("chkRtVal");				         
		String chkExVal			= clmChkInfo.get("chkExVal");				         
 
		log.info(">>>>>>>>>> [ClaimRepository.selectClaimCheckInfo] ordNo           	: " + ordNo             + "<<<<<<<<<<");
		log.info(">>>>>>>>>> [ClaimRepository.selectClaimCheckInfo] clmTpCd         	: " + clmTpCd           + "<<<<<<<<<<");
		log.info(">>>>>>>>>> [ClaimRepository.selectClaimCheckInfo] clmStatCd        	: " + clmStatCd         + "<<<<<<<<<<");
		log.info(">>>>>>>>>> [ClaimRepository.selectClaimCheckInfo] stdrCrncySumAmt	    : " + stdrCrncySumAmt   + "<<<<<<<<<<");
		log.info(">>>>>>>>>> [ClaimRepository.selectClaimCheckInfo] chkCnt			 	: " + chkCnt			+ "<<<<<<<<<<");
		log.info(">>>>>>>>>> [ClaimRepository.selectClaimCheckInfo] chkRtVal		 	: " + chkRtVal			+ "<<<<<<<<<<");
		log.info(">>>>>>>>>> [ClaimRepository.selectClaimCheckInfo] chkExVal		 	: " + chkExVal			+ "<<<<<<<<<<");
*/				
		return clmChkInfo;
	}

	/**
	 * 클레임 접수 가능 수량조회
	 * @param claimReturnSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectCheckClmQty(ClaimBoDTO claimDTO) throws Exception {
		
		List<Map<String, String>>  clmChkInfo = getSession1().selectList("com.plgrim.ncp.biz.claim.return.selectCheckClmQty", claimDTO);

		return clmChkInfo;
	}
	
//	public CancelClaimInfoResult selectClaimCheckInfo(ClaimReturnSearchDTO claimReturnSearchDTO) throws Exception {
//		
//		CancelClaimInfoResult cancelClaimInfoResult = getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectClaimCheckInfo", claimReturnSearchDTO);
//		
//		String ordNo           	= cancelClaimInfoResult.getOrdNo(); 
//		String clmTpCd         	= cancelClaimInfoResult.getClmTpCd();
//		String clmStatCd       = cancelClaimInfoResult.getClmStatCd();
//		String stdrCrncySumAmt= cancelClaimInfoResult.getStdrCrncySumAmt();  
//		String chkCnt			= cancelClaimInfoResult.getChkCnt();
//		String chkVal			= cancelClaimInfoResult.getChkVal();
// 
//		log.info(">>>>>>>>>> [ClaimRepository.selectClaimCheckInfo] ordNo           	: " + ordNo             + "<<<<<<<<<<");
//		log.info(">>>>>>>>>> [ClaimRepository.selectClaimCheckInfo] clmTpCd         	: " + clmTpCd           + "<<<<<<<<<<");
//		log.info(">>>>>>>>>> [ClaimRepository.selectClaimCheckInfo] clmStatCd        	: " + clmStatCd         + "<<<<<<<<<<");
//		log.info(">>>>>>>>>> [ClaimRepository.selectClaimCheckInfo] stdrCrncySumAmt	    : " + stdrCrncySumAmt   + "<<<<<<<<<<");
//		log.info(">>>>>>>>>> [ClaimRepository.selectClaimCheckInfo] chkCnt			 	: " + chkCnt			+ "<<<<<<<<<<");
//		log.info(">>>>>>>>>> [ClaimRepository.selectClaimCheckInfo] chkVal			 	: " + chkVal			+ "<<<<<<<<<<");
//				
//		return cancelClaimInfoResult;
//	}
	
	
	
	public List<rtnClmDetailResult> selectRtnClmDetail(ClaimBoDTO claimDTO) {
		
	    return getSession1().selectList("com.plgrim.ncp.biz.claim.return.selectRtnClmDetail", claimDTO);
    }

	public ClmWrhsGod selectClmWrhsGod(ClmWrhsGodExtend clmWrhsGodExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectClmWrhsGod", clmWrhsGodExtend);
    }
	
    public ClmWrhsGod selectClmWrhsGodRt(ClmWrhsGodExtend clmWrhsGodExtend) {
        return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectClmWrhsGodRt", clmWrhsGodExtend);
    }

	public void updateClmWrhsGod(ClmWrhsGodExtend clmWrhsGodExtend) {	    
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateClmWrhsGod", clmWrhsGodExtend);
    }
	
	public OrdClmGodCnnc selectOrdClmGodCnnc(ClmWrhsGodExtend clmWrhsGodExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectOrdClmGodCnnc", clmWrhsGodExtend);
    }

	public void updateOrdClmGodCnnc(ClmWrhsGodExtend clmWrhsGodExtend) {	    
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateOrdClmGodCnnc", clmWrhsGodExtend);
    }

    public List<LgsRtrvlDrctGod> selectLgsRtrvlDrctGodSwList(LgsRtrvlDrctGod lgsRtrvlDrctGod) {
        
        return getSession1().selectList("com.plgrim.ncp.biz.claim.return.selectLgsRtrvlDrctGodSwList", lgsRtrvlDrctGod);
    }
	
    
    public void updateInfOrdGodErpDstbSw(InfOrdGodErpDstb infOrdGodErpDstb) {
        getSession1().update("com.plgrim.ncp.biz.claim.return.updateInfOrdGodErpDstbSw", infOrdGodErpDstb);
    }

    public List<InfOrdGodErpDstb> selectInfOrdGodErpDstbSwList(InfOrdGodErpDstb infOrdGodErpDstb) {
        
        return getSession1().selectList("com.plgrim.ncp.biz.claim.return.selectInfOrdGodErpDstbSwList", infOrdGodErpDstb);
    }
    
    
	
	/***************************************************************************************************
	 * Khan
	 * *************************************************************************************************/

	public ClmWrhsGod selectClmWrhsGodForSet(ClmWrhsGodExtend clmWrhsGodExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectClmWrhsGodForSet", clmWrhsGodExtend);
    }

	public void insertClmWrhsCpstGodCnncForRtrvl(ClmWrhsCpstGodCnnc clmWrhsCpstGodCnnc) throws Exception {
		getSession1().insert("com.plgrim.ncp.base.insertClmWrhsCpstGodCnnc", clmWrhsCpstGodCnnc);
	}

	
	public List<OrdDetailClmInfoResult> selectClaimInfo(ClaimSearchDTO claimSearchDTO){
		
		return getSession1().selectList("com.plgrim.ncp.biz.claim.manage.selectClaimInfo", claimSearchDTO);
		//totalRow = getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.selectOrdClmListCnt",claimSearchDTO);
		//return new PageImpl<OrdDetailClmInfoResult>(results, pageParam.getPageable(), totalRow);
	}

	public long selectClaimInfoCnt(ClaimSearchDTO claimSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.selectClaimInfoCnt", claimSearchDTO);
	}

	/**
	 * 클레임 관리페이지 부분취소 상세팝업 배송지별 리스트
	 * @param clmNo
	 * @return
	 */
	public List<ClmDetailDlvSpGodInfoDTO> selectClmDetailGodInfo(String clmNo) {
	    return getSession1().selectList("com.plgrim.ncp.biz.claim.manage.selectClmDetailGodInfo", clmNo);
    }


	/**
	 * 클레임 관리페이지 반품 상세팝업 배송지별 리스트
	 * @param clmNo
	 * @return
	 */
	public List<ClmDetailResult> selectReturnClaimDetail(String clmNo) {
	    return getSession1().selectList("com.plgrim.ncp.biz.claim.return.selectReturnClaimDetail", clmNo);
    }
	
	/**
	 * 클레임 관리페이지 교환 상세팝업 배송지별 리스트
	 * @param clmNo
	 * @return
	 */
	public List<ClmDetailResult> selectExchangeClaimDetail(String clmNo) {
	    return getSession1().selectList("com.plgrim.ncp.biz.claim.return.selectExchangeClaimDetail", clmNo);
    }

	/**
	 * 클레임관리 > 클레임상세 >  수정 후 접수 반품
	 * 클레임입고상품에 등록되어 있는 상품의 주문상품순번을 조회
	 * @param claimDTO
	 * @return
	 */
	public List<String> selectOrdGodTurn(ClmWrhsGodExtend clmWrhsGodExtend) {
	    return getSession1().selectList("com.plgrim.ncp.biz.claim.return.selectOrdGodTurn", clmWrhsGodExtend);
    }

	public String selectSeqExchFpkey(String clmNo) {
	    return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectSeqExchFpkey", clmNo);
    }
	
	
	
	
	
	
	

	/* 반품/교환 삭제 관련 ***************************************************************************************** */
	
	/* 인터페이스주문상품ERP분배 삭제 */
	public void deleteInfOrdGodErpDstb(ClmWrhsGodExtend clmWrhsGod) {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteInfOrdGodErpDstb", clmWrhsGod);
	}
	
	/* 물류 출고지시상품 이력 삭제 */
	public void deleteLgsDlivyDrctGodHist(ClmWrhsGodExtend clmWrhsGod) {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteLgsDlivyDrctGodHist", clmWrhsGod);
	}
	
	/* 물류 출고지시상품 삭제 */
	public void deleteLgsDlivyDrctGod(ClmWrhsGodExtend clmWrhsGod) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteLgsDlivyDrctGod", clmWrhsGod);
	}
	
	/* 주문상품적용프로모션 삭제 */
	public void deleteOrdGodAplPrm(ClmWrhsGodExtend clmWrhsGod) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteOrdGodAplPrm", clmWrhsGod);
	}

	/**
	 * 교환 수정후 접수 관련 삭제대상상품 조회
	 * @param claimSearchDTO
	 * @param pageParam
	 * @return
	 */
	public List<ClmWrhsGodExtend> selectOrdGodListForClm(ClmWrhsGodExtend clmWrhsGod){
	    return getSession1().selectList("com.plgrim.ncp.biz.claim.return.selectOrdGodListForClm", clmWrhsGod);
	}

	/* 주문구성상품연결 삭제 */
	public void deleteOrdCpstGodCnnc(ClmWrhsGodExtend clmWrhsGod) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteOrdCpstGodCnnc", clmWrhsGod);
	}
	
	/* 주문 클레임 상품연결 삭제 - 출고 상품 연결 */
	public void deleteOrdClmGodCnncDlivy(OrdClmGodCnnc ordClmGodCnnc) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteOrdClmGodCnncDlivy", ordClmGodCnnc);
	}
	
	/* 주문상품 삭제 */
	public void deleteOrdGod(ClmWrhsGodExtend clmWrhsGod) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteOrdGod", clmWrhsGod);
	}

	/* 물류 회수지시상품 이력 삭제 */
	public void deleteLgsRtrvlDrctGodHist(LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist) {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteLgsRtrvlDrctGodHist", lgsRtrvlDrctGodHist);
	}

	/* 물류 회수지시상품 삭제 */
	public void deleteLgsRtrvlDrctGod(LgsRtrvlDrctGod lgsRtrvlDrctGod) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteLgsRtrvlDrctGod", lgsRtrvlDrctGod);
	}
	
	/* 클레임 입고상품 적용 프로모션 삭제 */
	public void deleteClmWrhsGodAplPrm(ClmWrhsGodAplPrm clmWrhsGodAplPrm) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteClmWrhsGodAplPrm", clmWrhsGodAplPrm);
	}

	/* 주문 클레임 상품연결 삭제 - 입고 상품 연결 */
	public void deleteOrdClmGodCnncWrhs(OrdClmGodCnnc ordClmGodCnnc) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteOrdClmGodCnncWrhs", ordClmGodCnnc);
	}
	
	/* 클레임 입고 구성 상품 연결 삭제 */
	public void deleteClmWrhsCpstGodCnnc(ClmWrhsGodExtend clmWrhsGod) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteClmWrhsCpstGodCnnc", clmWrhsGod);
	}
	
	/* 클레임 입고상품 삭제 */
	public void deleteClmWrhsGod(ClmWrhsGodExtend clmWrhsGod) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteClmWrhsGod", clmWrhsGod);
	}
	
	/* 물류 배송 이력 삭제 */
	public void deleteLgsDlvspHist(LgsDlvspHist lgsDlvspHist) {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteLgsDlvspHist", lgsDlvspHist);
	}

	/* 물류 배송 삭제 */
	public void deleteLgsDlvsp(LgsDlvsp lgsDlvsp) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteLgsDlvsp", lgsDlvsp);
	}

	/* 물류 배송지 이력 삭제 */
	public void deleteLgsDlvHist(LgsDlvHist lgsDlvHist) {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteLgsDlvHist", lgsDlvHist);
	}

	/* 물류 배송지 삭제 */
	public void deleteLgsDlv(LgsDlv lgsDlv) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteLgsDlv", lgsDlv);
	}
	

	/* 클레임 ERP 전송 삭제 */
	public void deleteClmErpTrnsmis(Clm clm) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteClmErpTrnsmis", clm);
	}

	/* 클레임 마스터 삭제 */
	public void deleteClm(Clm clm) throws Exception {
		getSession1().delete("com.plgrim.ncp.biz.claim.return.deleteClm", clm);
	}

	/* 반품/교환 삭제 관련 ***************************************************************************************** */
	
	
	
	
	/**
	 *
	 * @param clm
	 * @return
	 */
	public void updateClm(Clm clm) {
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateClm", clm);
	}

	/**
	 *
	 * @param ordGod
	 * @return
	 */
	public void updateOrdGod(OrdGod ordGod) {
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateOrdGod", ordGod);
	}

	/**
	 *
	 * @param ordGodAplPrm
	 * @return
	 */
	public void updateOrdGodAplPrm(OrdGodAplPrm ordGodAplPrm) {
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateOrdGodAplPrm", ordGodAplPrm);
	}

	/**
	 *
	 * @param lgsDlvsp
	 * @return
	 */
	public void updateLgsDlvsp(LgsDlvsp lgsDlvsp) {
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateLgsDlvsp", lgsDlvsp);
	}

	/**
	 *
	 * @param lgsDlv
	 * @return
	 */
	public void updateLgsDlv(LgsDlv lgsDlv) {
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateLgsDlv", lgsDlv);
	}

	/**
	 *
	 * @param lgsDlivyDrctGod
	 * @return
	 */
	public void updateLgsDlivyDrctGod(LgsDlivyDrctGod lgsDlivyDrctGod) {
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateLgsDlivyDrctGod", lgsDlivyDrctGod);
	}

	/**
	 *
	 * @param ordClmGodCnnc
	 * @return
	 *//*
	public void updateOrdClmGodCnnc(OrdClmGodCnnc ordClmGodCnnc) {
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateOrdClmGodCnnc", ordClmGodCnnc);
	}*/

	/**
	 *
	 * @param clmWrhsGod
	 * @return
	 */
	/*public void updateClmWrhsGod(ClmWrhsGod clmWrhsGod) {
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateClmWrhsGod", clmWrhsGod);
	}*/

	/**
	 *
	 * @param clmWrhsGodAplPrm
	 * @return
	 */
	public void updateClmWrhsGodAplPrm(ClmWrhsGodAplPrm clmWrhsGodAplPrm) {
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateClmWrhsGodAplPrm", clmWrhsGodAplPrm);
	}

	/**
	 *
	 * @param lgsRtrvlDrctGod
	 * @return
	 */
	public void updateLgsRtrvlDrctGod(LgsRtrvlDrctGod lgsRtrvlDrctGod) {
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateLgsRtrvlDrctGod", lgsRtrvlDrctGod);
	}
	
		
	public void updateClmRcept(Clm clm) {	    
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateClmRcept", clm);
    }
	
	public void updateClmWrhsGodWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {	    
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateClmWrhsGodWthdraw", clmWrhsGodExtend);
    }
	
	public void updateRepairClmWrhsGodWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateRepairClmWrhsGodWthdraw", clmWrhsGodExtend);
    }

	public void updateClmErpWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateClmErpWthdraw", clmWrhsGodExtend);
    }
	
	public void updateRtrvlStatWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {	    
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateRtrvlStatWthdraw", clmWrhsGodExtend);
    }

	public void updateRepairRtrvlStatWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateRepairRtrvlStatWthdraw", clmWrhsGodExtend);
    }

	public void updateRtrvlDstbWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {	    
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateRtrvlDstbWthdraw", clmWrhsGodExtend);
    }
	
	public int selectOrdErpErpCnt(ClmWrhsGodExtend clmWrhsGodExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectOrdErpErpCnt", clmWrhsGodExtend);
    }

	public int selectClmWrhsGodWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectClmWrhsGodWthdraw", clmWrhsGodExtend);
    }

	public int selectRepairClmWrhsGodWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectRepairClmWrhsGodWthdraw", clmWrhsGodExtend);
    }

	public int selectClmRtrvDrctWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectClmRtrvDrctWthdraw", clmWrhsGodExtend);
    }
	
	public int selectClmRtrvDrctWthdrawSendErp(ClmWrhsGodExtend clmWrhsGodExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectClmRtrvDrctWthdrawSendErp", clmWrhsGodExtend);
    }
	
	public int selectClmRtrvDrct(ClmWrhsGodExtend clmWrhsGodExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectClmRtrvDrct", clmWrhsGodExtend);
    }

    public int selectClmRtrvDrctWthdrawSwitch(ClmWrhsGodExtend clmWrhsGodExtend) {
        return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectClmRtrvDrctWthdrawSwitch", clmWrhsGodExtend);
    }
	
	public int selectClmDrctWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectClmDrctWthdraw", clmWrhsGodExtend);
    }
	public int selectSwitchClmDrctWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectSwitchClmDrctWthdraw", clmWrhsGodExtend);
    }
	
	public void insertLgsDlvspHist(LgsDlvspHist lgsDlvspHist) {
		getSession1().insert("com.plgrim.ncp.biz.claim.return.insertLgsDlvspHist", lgsDlvspHist);
	}

	public void insertLgsDlvHist(LgsDlvHist lgsDlvHist) {
		getSession1().insert("com.plgrim.ncp.biz.claim.return.insertLgsDlvHist", lgsDlvHist);
	}

	public void insertLgsRtrvlDrctGodHist(LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist) {
		getSession1().insert("com.plgrim.ncp.biz.claim.return.insertLgsRtrvlDrctGodHist", lgsRtrvlDrctGodHist);
	}

	public void insertLgsDlivyDrctGodHist(LgsDlivyDrctGodHist lgsDlivyDrctGodHist) {
		getSession1().insert("com.plgrim.ncp.biz.claim.return.insertLgsDlivyDrctGodHist", lgsDlivyDrctGodHist);
	}

	public void updateOrdGodErpForClmWthdraw(String clmNo) {
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateOrdGodErpForClmWthdraw", clmNo);
    }
	
	//환불계좌저장	
	public void updateRfdBnk(ClmExtend clmExtend) {
		getSession1().update("com.plgrim.ncp.biz.claim.return.updateRfdBnk", clmExtend);
    }

	public int checkResveOrdDate(Ord ord) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.checkResveOrdDate", ord);
    }

	public List<OrdGodAplPrm> selectOrdGodAplPrm(ClaimBoDTO claimBoDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.manage.selectOrdGodAplPrm", claimBoDTO);
    }

	public List<ClmDlvOrdGodInfoDTO> selectCnclTgtGodGftPrm(ClmDlvOrdGodInfoDTO dto) {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.manage.selectCnclTgtGodGftPrm", dto);
    }
	
	public List<ClmWrhsGodExtend> selectRtnTgtGodGftPrm(ClmWrhsGodExtend dto) {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.manage.selectRtnTgtGodGftPrm", dto);
    }
	
	public List<OrdGod> selectOrdGodByOrdGft(OrdGodAplPrm ordGodAplPrmDto) {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.manage.selectOrdGodByOrdGft", ordGodAplPrmDto);
    }

	public List<ClmWrhsGod> selectClmWrhsGodForOrdGft(ClaimOrdGftSearchDTO claimOrdGftSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.manage.selectClmWrhsGodForOrdGft", claimOrdGftSearchDTO);
    }
	
   public OrdClmGodCnnc selectOrdClmGodCnncSw(OrdClmGodCnnc ordClmGodCnnc) {
        return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectOrdClmGodCnncSw", ordClmGodCnnc);
    }
   
   public void updateClmSumry(String clmNo) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.claim.return.updateClmSumry", clmNo);
   }
   
   /**
    * Update clm sumry for repair.
    *
    * @param clmNo the clm no
    * @throws Exception the exception
    */
   public void updateClmSumryForRepair(String clmNo) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.claim.return.updateClmSumryForRepair", clmNo);
   }   

   public void updateLgsDlvCst(LgsDlv lgsDlv) {
		getSession1().update("com.plgrim.ncp.biz.claim.manage.updateLgsDlvCst", lgsDlv);
   }
   
   public void insertLgsDlvCstForGlovalCancel(LgsDlv lgsDlv) {
	   getSession1().insert("com.plgrim.ncp.biz.claim.manage.insertLgsDlvCstForGlovalCancel", lgsDlv);
   }
   
   /**
    * 주문 상품 수량 합계 조회
    * @param ordNo
    * @return
    */
   public int getTotalOrdQty(String ordNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.getTotalOrdQty", ordNo);
   }
   
      //글로벌 남은 상품 무게를 구한다
   public List<OrdGod> selectRemainWt(String ordNo) {
	   return getSession1().selectList("com.plgrim.ncp.biz.interfaces.selectRemainWt", ordNo);
   }

   public LgsDlv selectOvseaLgsDlvByClmNo(String clmNo) {
	   return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.selectOvseaLgsDlvByClmNo", clmNo);
   }
   
   public LgsDlv selectDmstcLgsDlvByClmNo(String clmNo) {
	   return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.selectDmstcLgsDlvByClmNo", clmNo);
   }

	public void updateOvseaWaybilNoPop(LgsDlv lgsDlv) {
		getSession1().update("com.plgrim.ncp.biz.claim.manage.updateOvseaWaybilNoPop", lgsDlv);
	}
	
	public List<OrdGod> selectOrdGodListForAllCancel(String ordNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.manage.selectOrdGodListForAllCancel", ordNo);
	}

	public List<PayExtend> selectAddPayDlvAmtByPartCncl(Clm clm) {
        return getSession1().selectList("com.plgrim.ncp.biz.claim.manage.selectAddPayDlvAmtByPartCncl", clm);
    }

	public void updateAddPayByPartCncl(RefundPayDTO refundPayDTO) {
		getSession1().update("com.plgrim.ncp.biz.claim.manage.updateAddPayByPartCncl", refundPayDTO);
   }

	public int selectPayWaitPartCnclCount(String ordNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.selectPayWaitPartCnclCount", ordNo);
   }
	
	public int selectClmWrhsGodCountForTempDel(String clmNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectClmWrhsGodCountForTempDel", clmNo);
    }
	
	public int selectRepairClmWrhsGodCount(ClmWrhsGodExtend clmWrhsGodExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectRepairClmWrhsGodCount", clmWrhsGodExtend);
    }

	public int selectClmWrhsGodCount(String clmNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectClmWrhsGodCount", clmNo);
    }

	public void selectOrdGodErpForUpdate(String ordNo) {
		getSession1().selectList("com.plgrim.ncp.biz.claim.manage.selectOrdGodErpForUpdate", ordNo);
    }
	
	public List<ComChrger> selectClmComChrger(Clm clm) {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.manage.selectClmComChrger", clm);
	}

	public boolean selectOrdStatDlvComptYn(String ordNo) {
		boolean result = false;
		String resultYn = getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.selectOrdStatDlvComptYn", ordNo);
		if("Y".equalsIgnoreCase(resultYn)){
			result = true;
		}
		return result;
	}
	
	public String selectBaseDlvComCd(String mallId) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectBaseDlvComCd", mallId);
	}

	public Map<String, String> selectBaseDlvCom(LgsDlv dlv) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectBaseDlvCom", dlv);
	}

	public ClmErpTrnsmis selectclmErpTrnsmis(ClmErpTrnsmis cst) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectclmErpTrnsmis", cst);
	}
//
//	public List<RepairReceptionSDO> selectReapirTargetList(ClmWrhsGodExtend clmWrhsGodExtend) {
//		return getSession1().selectList("com.plgrim.ncp.biz.claim.return.selectReapirTargetList", clmWrhsGodExtend);
//	}
//
//	/**
//	 * Select repair reception sdo list.
//	 * REPAIR : 무료수선 접수 RFC 호출을 위한 조회
//	 *
//	 * @param clm the clm
//	 * @return the list
//	 */
//	public List<RepairReceptionSDO> selectRepairReceptionSDOList(Clm clm) {
//		return getSession1().selectList("com.plgrim.ncp.biz.claim.manage.selectRepairReceptionSDOList", clm);
//	}


	//무료수선 목록 COUNT
	public long selectRepairRegCount(ClaimListSearchDTO searchDTO) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.selectFreeRepairRegListCnt",searchDTO);
	}

	//무료수선 목록 조회
	public List<ClaimListResult> selectRepairRegList(ClaimListSearchDTO searchDTO, PageParam pageParam) throws Exception {
		//마스킹 권한 기본값셋팅추가
		if( searchDTO.getMaskingYn() == null ||
			(searchDTO.getMaskingYn() != null && "".equals(searchDTO.getMaskingYn()))
		){
			searchDTO.setMaskingYn(SessionEnum.YES.toString()); // Y일 경우 마스킹처리
		}
		List<ClaimListResult> results = new ArrayList<ClaimListResult>();
		results = getSession1().selectList("com.plgrim.ncp.biz.claim.manage.selectFreeRepairRegList", searchDTO);
		return results;
	}

	/**
	 * 자동취소 클레임 철회 상품
	 * @return
	 */
	public RepairResult selectRepairGod(RepairDTO repairDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectRepairGod", repairDTO);
	}

	/**
	 * 클레임 상태정보 UPDATE
	 * @return
	 */
	public int updateRepairClm(Clm clm) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.claim.return.updateRepairClm", clm);
	}

	/**
	 * 인터페이스 주문 상품 ERP 분배 테이블 업데이트
	 */
	public int updateInfOrdGodErpDstb(InfOrdGodErpDstb infOrdGodErpDstb) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.claim.return.updateInfOrdGodErpDstb", infOrdGodErpDstb);
	}

	/**
	 * 수선 완료 / 입고 완료 수량 조회
	 * @param repairDTO
	 */
	public RepairResult selectRepairCnt(RepairDTO repairDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectRepairCnt", repairDTO);
	}

	/**
	 * 수선회수지시 업데이트
	 *
	 * @param lgsRtrvlDrctGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 */
	public int updateRepairLgsRdg(LgsRtrvlDrctGod lgsRtrvlDrctGod) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.claim.return.updateRepairLgsRdg", lgsRtrvlDrctGod);
	}
	/**
	 * 회수지시 상품 HIST 등록
	 *
	 * @param lgsRtrvlDrctGod [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 */
	public int insertRepairLgsRdgHist(LgsRtrvlDrctGod lgsRtrvlDrctGod) throws Exception {
		return getSession1().insert("com.plgrim.ncp.biz.claim.return.insertRepairLgsRdgHist", lgsRtrvlDrctGod);
	}

	/**
	 * 수선 출고지시 업데이트
	 *
	 * @param deliveryOrderGoodDTO [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 */
	public int updateRepairLgsDdgInfo4DivWayBil(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.claim.return.updateRepairLgsDdgInfo4DivWayBil", deliveryOrderGoodDTO);
	}

	/**
	 * 수선상품 INF_ORD_GOD_ERP_DSTB_REPAIR 철회 일자 UPDATE
	 *
	 * @param infOrdGodErpDstbRepair [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 */
	public int updateInfOrdGodErpRepair(InfOrdGodErpDstbRepair infOrdGodErpDstbRepair) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.claim.return.updateInfOrdGodErpRepair", infOrdGodErpDstbRepair);
	}

	public int selectRcptnoNullCntByClmNo(String clmNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectRcptnoNullCntByClmNo", clmNo);
	}

	
	/**
	 * Select clm by mbr.
	 *
	 * @param claimBoDTO the claim bo dto
	 * @return the long
	 */
	public long selectClmByMbr(ClaimBoDTO claimBoDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.selectClmByMbr", claimBoDTO);
	}

	/**
	 * 수선 완료(발송) 안내 SMS 대상 조회
	 */
	public RepairResult selectRepairDlvComptSmsTarget(RepairDTO parameter) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.selectRepairDlvComptSmsTarget", parameter);
	}

	/**
	 * 수선 완료(발송) 안내 SMS 대상 조회
	 */
	public InfOrdGodErpDstbRepair selectInfOrdGodErpDstbRepair(InfOrdGodErpDstbRepair infOrdGodErpDstbRepair) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.return.selectInfOrdGodErpDstbRepair", infOrdGodErpDstbRepair);
	}
	
	/**
	 * 회원 클레임 쿠폰갱신
	 * @param clmWrhsGodAplPrm
	 */
	public void updateMbrIssuCoupon(ClmWrhsGodAplPrm clmWrhsGodAplPrm) {
		getSession1().update("com.plgrim.ncp.biz.claim.manage.updateMbrIssuCoupon", clmWrhsGodAplPrm);
	}
	
	/**
	 * 클레임 프로모션 정보조회
	 * @param clmCouponSearchDTO
	 */
	public ClmGoodsCouponResult selectClmCouponInfo(ClmCouponSearchDTO clmCouponSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.selectClmCouponInfo", clmCouponSearchDTO);
	}
	
	/**
	 * 클레임 프로모션(무료 반품/교환 배송비쿠폰) 사용된 상품갯수 조회
	 * @param clmCouponSearchDTO
	 */
	public long selectUsedClaimCouponGodCnt(ClmCouponSearchDTO clmCouponSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.selectUsedClaimCouponGodCnt", clmCouponSearchDTO);
	}
	
	/**
     * 클레임 사용 쿠폰 복원
     *
     * @param mbrIssuCpn
     * @throws Exception
     */
    public void updateCouponRevertStatus(MbrIssuCpn mbrIssuCpn) throws Exception {
    	getSession1().update("com.plgrim.ncp.biz.claim.manage.updateCouponRevertStatus", mbrIssuCpn);
    }
    
    /**
     * 세트구성상품 교환시 세트대표상품 여부 조회
     *
     * @param Map<String, Object> extOrdGod
     * @throws Exception
     */
    public List<OrdGod> selectOrdCpstGodCnncList(Map<String, Object> extOrdGod ) throws Exception {
    	return getSession1().selectList("com.plgrim.ncp.biz.claim.manage.selectOrdCpstGodCnncList", extOrdGod);
    }

    /**
     * 세트구성상품 교환시 출고지시 테이블의 세트대표상품 pckage_god_turn 업데이트
     *
     * @param Map<String, Object> extDlivyDrctGod
     * @throws Exception
     */
    public void updateSetPckageGodTurn(Map<String, Object> extDlivyDrctGod) throws Exception {
    	getSession1().update("com.plgrim.ncp.biz.claim.manage.updateSetPckageGodTurn", extDlivyDrctGod);
    }
    
    /** [#48750][개발] 불량상품 고객에 대한 교환/반품 Process 개선 요청 
     *  집하상태코드목록 - 클레임지연조회
     */
	public List<SysExcpCd> selectSysExcpCdList(SysExcpCd sysExcpCd) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.claim.manage.selectSysExcpCdList", sysExcpCd);
	}
	
	/**
	 * [#48750][개발] 불량상품 고객에 대한 교환/반품 Process 개선 요청
	 * 선진행 구분코드 update
	 */
	public void updateClmPreprogrsSectCd(ClaimBoDTO claimBoDTO) throws Exception  {
		getSession1().update("com.plgrim.ncp.biz.claim.manage.updateClmPreprogrsSectCd", claimBoDTO);
    }
	
	/**
     * 클레임 사용쿠폰 상태 보정
     *
     * @param mbrIssuCpn
     * @throws Exception
     */
    public void updateCouponCorrectStatus(MbrIssuCpn mbrIssuCpn) throws Exception {
    	getSession1().update("com.plgrim.ncp.biz.claim.manage.updateCouponCorrectStatus", mbrIssuCpn);
    }
    
    /**
     * 모바일재결재 정보조회
     * 
     * @param payNo
     * @return
     */
    public RefundPayDTO getMobileRePayInfo(String payNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.getMobileRePayInfo", payNo);
    }
    
    /**
     * 환불처리 여부 확인
     * 
     * @param payNo
     * @return
     */
    public String getRefundPayYn(String payNo) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.getRefundPayYn", payNo);
    }
    
    /**
     * 거래번호 변경 (휴대폰 재 승인)
     *
     * @param mbrIssuCpn
     * @throws Exception
     */
    public void updateTradeReauthNo(RefundPayDTO refundPayDTO) {
    	getSession1().update("com.plgrim.ncp.biz.claim.manage.updateTradeReauthNo", refundPayDTO);
    }
    
    public String getPartClaimPossibleYn(String ordNo, String clmTpCd) {
    	return this.getPartClaimPossibleYn(ordNo, clmTpCd, "");
    }

    public String getPartClaimPossibleYn(String ordNo, String clmTpCd, String role) {
    	
    	Map<String, String> param = new HashMap<String, String>();
    	param.put("ordNo", ordNo);
    	param.put("clmTpCd", clmTpCd);
    	param.put("role", role);
    	
    	return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.getPartClaimPossibleYn", param);
    }
    
    public int getNoClaimGodCount(String ordNo) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.getNoClaimGodCount", ordNo);
    }
    
    public RefundPayDTO selectPayInfoForCalculateReserve(String ordNo) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.selectPayInfoForCalculateReserve", ordNo);
    }
    
    public void updateEscrStatCd(RefundPayDTO refundPayDTO) {
    	getSession1().update("com.plgrim.ncp.biz.claim.manage.updateEscrStatCd", refundPayDTO);
    }
    
    public void insertErpTrnsmisForClaim(String ordNo, String clmNo, String clmErpTrnsmisTpCd, String erpTrnsmisCd) {
    	
    	ClmWrhsGodErpTrnsmisDTO dto = new ClmWrhsGodErpTrnsmisDTO();
    	dto.setOrdNo(ordNo);
    	dto.setClmNo(clmNo);
    	dto.setClmErpTrnsmisTpCd(clmErpTrnsmisTpCd);
    	dto.setErpTrnsmisCd(erpTrnsmisCd);
		
    	this.insertErpTrnsmisForClaim(dto);
    }
    
    public void insertErpTrnsmisForClaim(ClmWrhsGodErpTrnsmisDTO dto) {
    	getSession1().insert("com.plgrim.ncp.biz.claim.erptrnsmis.insertErpTrnsmisForClaim", dto);
    }
    
    public MbrIssuCpnExtend selectRestoreOnoffCpnInfo(String mbrCpnNo) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.selectRestoreOnoffCpnInfo", mbrCpnNo);
    }
    
    public int getWMSAvailableInvQty(GoodsInventoryDTO gDTO) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.getWMSAvailableInvQty", gDTO);
    }
    
    public List<PayExtend> selectOrdClmRefundPayReprocess(Clm clm) {
    	return getSession1().selectList("com.plgrim.ncp.pay.selectOrdClmRefundPayReprocess", clm);
    }
    
    public void updatePayRfdStat(PayRfd payRfd) {
    	getSession1().update("com.plgrim.ncp.biz.claim.manage.updatePayRfdStat", payRfd);
    }
    
    public String getKcpRefundBnkCd(String cd) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.getKcpRefundBnkCd", cd);
    }
    
    public void updateCancelClaimTpCd(ClaimSearchDTO dto) {
    	getSession1().update("com.plgrim.ncp.biz.claim.manage.updateCancelClaimTpCd", dto);
    }
    
    public void updatePayRfdBnkInfo(Clm clm) {
    	getSession1().update("com.plgrim.ncp.biz.claim.manage.updatePayRfdBnkInfo", clm);
    }
    
    public int getEscrCalResrveCnt(String ordNo) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.claim.manage.getEscrCalResrveCnt", ordNo);
    }
    
    public int updateClmRfdErrPgTrnsmisTgtYn(ClaimBoDTO claimBoDTO) throws Exception {    	
    	return getSession1().update("com.plgrim.ncp.biz.claim.manage.updateClmRfdErrPgTrnsmisTgtYn", claimBoDTO);
    }
}
