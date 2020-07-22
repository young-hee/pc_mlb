package com.plgrim.ncp.biz.delivery.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.delivery.result.DeliveryComptMsgResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DeliveryAssignRepository extends AbstractRepository {

	/**
	 * 배정대상 주문번호 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @return List [설명]
	 * @since 2017. 11. 7
	 */
	public List<String> selectAssignOrdNoList() {		
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.assign.selectAssignOrdNoList");
	}
	
	
	/**
	 * 배정대상 출고정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDdg [설명]
	 * @return List<DeliveryOrderGoodResult> [설명]
	 * @throws Exception the exception
	 * @since 2017. 11. 7
	 */
	public List<DeliveryOrderGoodResult> selectAssignTargetList(DeliveryOrderGoodDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.assign.selectAssignTargetList", dto);
	}
	
	
	/**
	 * 배정대상 아이템 그룹핑 정보 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param lgsDdg [설명]
	 * @return List<DeliveryOrderGoodDTO> [설명]
	 * @throws Exception the exception
	 * @since 2017. 11. 7
	 */
	public List<DeliveryOrderGoodDTO> selectAssignTargetItemGrpList(DeliveryOrderGoodDTO dto) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.assign.selectAssignTargetItemGrpList", dto);
	}
	
	
	/**
	 * 배정 우선순위 배송매장 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param DeliveryOrderGoodDTO dto [설명]
	 * @return LgsAutoAsgnExtend [설명]
	 * @throws Exception the exception
	 * @since 2017. 11. 9
	 */
/*	
	public LgsAutoAsgnExtend selectDlvShop4Assign(DeliveryOrderGoodDTO dto) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.assign.selectDlvShop4Assign", dto);
	}
*/	
	
	/**
	 * 매장 아이템 배정 운영변수 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param DeliveryOrderGoodDTO dto [설명]
	 * @return LgsAutoAsgnExtend [설명]
	 * @throws Exception the exception
	 * @since 2017. 11. 9
	 */
/*
	public LgsAutoAsgnExtend selectShopItemOperVrblInfo(DeliveryOrderGoodDTO dto) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.assign.selectShopItemOperVrblInfo", dto);
	}
*/	
	
	/**
	 * 결품 알림 메시지 발송정보 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param ordNo [설명]
	 * @return DeliveryComptMsgResult [설명]
	 * @throws Exception the exception
	 * @since 2017. 11. 15
	 */
	public DeliveryComptMsgResult selectQuickShtgMsgInfo(String ordNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.assign.selectQuickShtgMsgInfo", ordNo);
	}
	
	
	/**
	 * 결품 알림 메시지 발송대상 확인 처리
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param ordNo [설명]
	 * @return Int [설명]
	 * @throws Exception the exception
	 * @since 2017. 11. 16
	 */
	public int updateShtgMsgTransYn(String ordNo) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.assign.updateShtgMsgTransYn", ordNo);
	}
	
}
