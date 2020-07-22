package com.plgrim.ncp.cmp.orderfulfilment.batch.claim;

import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdErpTrnsmis;
import com.plgrim.ncp.base.entities.datasource1.pay.PayRfd;
import com.plgrim.ncp.biz.claim.data.ClaimBatchSearchDTO;
import com.plgrim.ncp.biz.claim.data.ClaimBatchTargetDTO;
import com.plgrim.ncp.biz.claim.data.ClmErpTrnsmis;

import java.util.List;

public interface ClaimBatchComponent {

    /**
     * 반품/교환 클레임 완료배치 > 반품 환불로직
     * @param clmNo
     * @return
     * @throws Exception
     */
	public String returnCmplBatchRepayToDB(String clmNo) throws Exception;
	
	/**
	 * 클레임 대상 리스트
	 * @throws Exception
	 */	
	public List<ClaimBatchTargetDTO> selectClaimTargetList(ClaimBatchSearchDTO claimDTO) throws Exception;
	
	/**
	 * 클레임ERP전송상태 변경
	 * @param totalCancelClmErpTrnsmis
	 */
	public void updateClmErpTrnsmis(ClmErpTrnsmis totalCancelClmErpTrnsmis);
	
	/**
	 * 주문ERP전송상태 변경
	 * @param totalCancelClmErpTrnsmis
	 */
	public void updateOrdErpTrnsmis(ClmErpTrnsmis totalCancelClmErpTrnsmis);

	/**
	 * 클레임 철회시 인터페이스주문ERP분배 데이터의 클레임번호를 삭제한다.
	 * @param dto
	 */
	public void updateClmErpWthdraw(ClaimBatchTargetDTO dto);

	/**
	 * 클레임 산태를 변경한다.
	 * @param clm
	 * @throws Exception
	 */
	void updateClmStatCd(Clm clm) throws Exception;

	/**
	 * 결제데이터 생성 및 환불처리
	 * @param clmNo
	 * @return
	 * @throws Exception
	 */
	String createPayAndRfd(String clmNo) throws Exception;
	
	/**
	 * 클레임  P포인트 처리
	 * @param ord
	 * @param clm
	 */
	public void insertWebpoint(Ord ord, Clm clm);

	/**
	 * <pre>
	 * [주문모듈]Off-line 매장 온라인 주문 반품 BO 개발
	 * 	- '픽업프로세스' 개선 - 반품 배치프로세스 통합
	 * </pre>
	 *
	 * @param clmNo 신규 반품 처리될 클레임번호
	 * @throws Exception
	 */
	void execShopRtnClmProc( String clmNo ) throws Exception;

	/**
	 * <pre>
	 * '클레임철회' 시 이후 프로세스 처리
	 * </pre>
	 *
	 * @param clmNo 클레임번호
	 * @param clmTpCd 클레임 유형 - 반품('RTGOD'), 교환('GNRL_EXCHG')
	 * @throws Exception
	 */
	void execWthdrawProc( String clmNo, String clmTpCd ) throws Exception;

	/**
	 * 온오프쿠폰 임시사용 복원
	 * 
	 * @param ordNo
	 * @param orderType
	 * @param cpnStatCd
	 * @return
	 */
	public List<OrdErpTrnsmis> sendFgifOfferUseTemp(String ordNo, String orderType, String cpnStatCd);

	/**
	 * 클레임번호로 판매영수증이 없는 카운트를 구한다
	 * 
	 * @param clmNo
	 * @return
	 * @throws Exception
	 */
	public int selectRcptnoNullCntByClmNo(String clmNo) throws Exception ;
}
