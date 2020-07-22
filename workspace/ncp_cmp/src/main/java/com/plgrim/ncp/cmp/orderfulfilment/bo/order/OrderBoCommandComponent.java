package com.plgrim.ncp.cmp.orderfulfilment.bo.order;

import java.util.HashMap;
import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;
import com.plgrim.ncp.base.entities.datasource1.inf.InfAffOrd;
import com.plgrim.ncp.base.entities.datasource1.inf.InfAffOrdErr;
import com.plgrim.ncp.base.entities.datasource1.inf.InfTmprAffOrd;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdExtend;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.framework.data.SystemPK;


public interface OrderBoCommandComponent {
 
	/**
	 * 대량주문 생성
	 * 
	 * @param orderDTO
	 * @throws Exception
	 */
	public void bulkOrder(OrderBoDTO orderDTO) throws Exception;

	/**
	 * 전화주문 생성
	 * 
	 * @param orderDTO
	 * @throws Exception
	 */
	public void phoneOrder(OrderBoDTO orderDTO) throws Exception;
	
	/**
	 * 제휴주문 생성
	 * 
	 * @param extend
	 * @throws Exception
	 */
	public void affOrder(OrdExtend extend) throws Exception;
	
	/**
	 * 제휴주문등록 오류정보 생성
	 * 
	 * @param infAffOrdErr
	 * @throws Exception
	 */
	public void insertInfAffOrdErr(InfAffOrdErr infAffOrdErr) throws Exception;
	
	/**
	 * 제휴주문등록 오류정보 생성
	 * 
	 * @param infAffOrdErr
	 * @param infAffOrd
	 * @throws Exception
	 */
	public void insertInfAffOrdErr(InfAffOrdErr infAffOrdErr,InfAffOrd infAffOrd) throws Exception;
	
	/**
	 * 제휴주문 변경
	 * 
	 * @param infAffOrd
	 * @throws Exception
	 */
	public void updateAffOrd(InfAffOrd infAffOrd) throws Exception;
	
	/**
	 * 재휴주문 삭제
	 * 
	 * @param infAffOrd
	 * @throws Exception
	 */
	public void deleteAffOrd(InfAffOrd infAffOrd) throws Exception;
	
	/**
	 * 제휴주문 조회
	 * 
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 */
	public List<OrdExtend> selectAffOrd(OrderBoDTO orderDTO) throws Exception;

	/**
	 * 대량주문 확정 처리
	 * 
	 * @param orderDTO
	 * @throws Exception
	 */
	public void updatelagQtyOrdDcsn(OrderBoDTO orderDTO) throws Exception;

	/**
	 * 입금확정완료 처리
	 * 
	 * @param orderDTO
	 * @throws Exception
	 */
	public void confirmDeposit(OrderBoDTO orderDTO) throws Exception;

	/**
	 * 상담메모 생성
	 * 
	 * @param csoCnsltMemo
	 * @throws Exception
	 */
	public void insertCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) throws Exception;
	
	/**
	 * 임시 제휴주문데이터 생성
	 * 
	 * @param infTmprAffOrds
	 * @param loginId
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> insertInfTmprAffOrdTemp(List<InfTmprAffOrd> infTmprAffOrds, String loginId) throws Exception;
	
	/**
	 * 가상배송완료처리
	 * 
	 * @param ord
	 * @throws Exception
	 */
	public void virtlDlvCompt(Ord ord) throws Exception;
	
	/**
	 * 가상배송완료처리 (결제대기상태)
	 * virtlDlvCompt(ord) 와 동일함..
	 * 
	 * @param ord
	 * @throws Exception
	 */
	public void virtlDlvCompt4PayWait(Ord ord) throws Exception;
	
	/**
	 * 옵션변경 처리
	 * 
	 * @param orderDTO
	 * @param systemPk
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> optionChange(OrderBoDTO orderDTO, SystemPK systemPk) throws Exception;

}
