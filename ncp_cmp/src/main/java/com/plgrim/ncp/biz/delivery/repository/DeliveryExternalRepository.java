/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 4. 22       
 */
package com.plgrim.ncp.biz.delivery.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInvExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGodHist;
import com.plgrim.ncp.biz.delivery.data.DeliveryFinishFoDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryInfErpDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryPayClaimDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.data.DlvOrdGodInfoDTO;
import com.plgrim.ncp.biz.delivery.data.LgsDlvspPkupDTO;

import lombok.extern.slf4j.Slf4j;

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
 * @author jwcale.kim
 * @since 2015. 4. 22
 */
@Slf4j
@Repository
public class DeliveryExternalRepository extends AbstractRepository {



	//////////////////////////////////////////CLAIM//////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 물류 배송지 update 후 물류 배송지 이력 테이블 insert
	 * @param deliveryPayClaimDTO
	 */
	public void updateClmDlvspCancelPay(DeliveryPayClaimDTO deliveryPayClaimDTO) {	    
		getSession1().update("com.plgrim.ncp.biz.delivery.external.updateClmDlvspCancelPay", deliveryPayClaimDTO);		
    }
	public void insertClmDlvspCancelPayHist(DeliveryPayClaimDTO deliveryPayClaimDTO) {	    		
		getSession1().insert("com.plgrim.ncp.biz.delivery.external.insertClmDlvspHistCancelPay", deliveryPayClaimDTO);
    }
	
	/**
	 * 물류 배송지 update 후 물류 배송지 이력 테이블 insert
	 * @param deliveryPayClaimDTO
	 */
	public void updateClmDlvCancelPay(DeliveryPayClaimDTO deliveryPayClaimDTO) {	    
		getSession1().update("com.plgrim.ncp.biz.delivery.external.updateClmDlvCancelPay", deliveryPayClaimDTO);
    }
	public void insertClmDlvCancelPayForGlobal(DeliveryPayClaimDTO deliveryPayClaimDTO) {	    
		getSession1().insert("com.plgrim.ncp.biz.delivery.external.insertClmDlvCancelPayForGlobal", deliveryPayClaimDTO);
    }
	public void insertClmDlvCancelPayHist(DeliveryPayClaimDTO deliveryPayClaimDTO) {	    		
		getSession1().insert("com.plgrim.ncp.biz.delivery.external.insertClmDlvHistCancelPay", deliveryPayClaimDTO);
    }


	public void updateClmDlvDrctGodCancel(DeliveryPayClaimDTO deliveryPayClaimDTO) {
		getSession1().update("com.plgrim.ncp.biz.delivery.external.updateClmDlvDrctGodCancel", deliveryPayClaimDTO);
		getSession1().insert("com.plgrim.ncp.biz.delivery.external.insertClmDlvDrctGodHistCancel", deliveryPayClaimDTO);
    }

	public List<GodShopItmInvExtend> selectOrdGodShopItmQty(DlvOrdGodInfoDTO dlvOrdGodInfoDTO) {
	    return getSession1().selectList("com.plgrim.ncp.biz.delivery.external.selectOrdGodShopItmQty", dlvOrdGodInfoDTO);
    }

	public List<DeliveryInfErpDTO> selectDlivyDrctInfoListByClm(DlvOrdGodInfoDTO dto) {
	    return getSession1().selectList("com.plgrim.ncp.biz.delivery.external.selectDlivyDrctInfoListByClm", dto);
    }
	
	public List<DeliveryInfErpDTO> selectErpResveRcptfrInfoListByClm(DeliverySearchDTO deliverySearchDTO) {
	    return getSession1().selectList("com.plgrim.ncp.biz.delivery.external.selectErpResveRcptfrInfoListByClm", deliverySearchDTO);
    }
	public void updateClmDlvDrctGodUnitCancel(DlvOrdGodInfoDTO dto) {
		getSession1().update("com.plgrim.ncp.biz.delivery.external.updateClmDlvDrctGodUnitCancel", dto);
		getSession1().insert("com.plgrim.ncp.biz.delivery.external.updateClmDlvDrctGodhHistUnitCancel", dto);
    }
	
	public List<DeliveryFinishFoDTO> selectDlivycomptTarget(DeliveryFinishFoDTO deliveryFinishFoDTO) {	    
	    return getSession1().selectList("com.plgrim.ncp.biz.delivery.external.selectDlivycomptTarget", deliveryFinishFoDTO);
    }	
	public void updateFoLgsDlivyDrctGod(LgsDlivyDrctGod lgsDlivyDrctGod) {	    
		getSession1().update("com.plgrim.ncp.biz.delivery.external.updateFoLgsDlivyDrctGod", lgsDlivyDrctGod);
	}	
	public void insertFoLgsDlivyDrctGodHist(LgsDlivyDrctGodHist lgsDlivyDrctGodHist) {	    
		getSession1().insert("com.plgrim.ncp.biz.delivery.external.insertFoLgsDlivyDrctGodHist", lgsDlivyDrctGodHist);
	}	
	public void updateFolgsDlivyDrctGod(LgsDlivyDrctGod lgsDlivyDrctGod) {	    
		getSession1().update("com.plgrim.ncp.biz.delivery.external.updateFolgsDlivyDrctGod", lgsDlivyDrctGod);
	}	
	public void updateDeliveryLocationChange(LgsDlvsp lgsDlvsp) {	    
		getSession1().update("com.plgrim.ncp.biz.delivery.external.updateDeliveryLocationChange", lgsDlvsp);		
    }
	public LgsDlvspPkupDTO selectLgsDlvspPkup(LgsDlvspExtend lgsDlvsp){
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.external.selectLgsDlvspPkup", lgsDlvsp);
	}
	public int insertFoLgsDlvspHist(LgsDlvspHist lgsDlvspHist) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.external.insertFoLgsDlvspHist", lgsDlvspHist);
	}	
	public int insertFoLgsDlvHist(LgsDlvHist lgsDlvHist) throws Exception {
		return getSession1().update("com.plgrim.ncp.biz.delivery.external.insertFoLgsDlvHist", lgsDlvHist);
	}	
	public List<DeliveryOrderGoodDTO> selectDrctGodRemainQty(
            DeliveryPayClaimDTO deliveryPayClaimDTO) {	    
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.external.selectDrctGodRemainQty", deliveryPayClaimDTO);
    }
	
	/**
	 * 물류회수지시상품 등록
	 * 클레임 - 반품/일반교환/맞교환
	 * @param lgsRtrvlDrctGod
	 * @throws Exception
	 */
	public void insertLgsRtrvlDrctGodForClm(LgsRtrvlDrctGodExtend lgsRtrvlDrctGod) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.delivery.external.insertLgsRtrvlDrctGodForClm", lgsRtrvlDrctGod);
	}
	
	/**
	 * 
	* <pre>
	*	물류회수지시상품 등록 <br/>
	*	티몰 전용
	* </pre>
	* @param lgsRtrvlDrctGod
	* @throws Exception
	* @since 2016. 1. 4.
	 */
	public void insertLgsRtrvlDrctGodForClmTmall(LgsRtrvlDrctGodExtend lgsRtrvlDrctGod) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.delivery.external.insertLgsRtrvlDrctGodForClmTmall", lgsRtrvlDrctGod);
	}
	
	
	
	/**
	 * 물류회수지시상품이력 등록
	 * 클레임 - 반품/일반교환/맞교환
	 * @param lgsRtrvlDrctGodHist
	 * @throws Exception
	 */
	public void insertLgsRtrvlDrctGodHistForClm(LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist) throws Exception {
		getSession1().insert("com.plgrim.ncp.biz.delivery.external.insertLgsRtrvlDrctGodHistForClm", lgsRtrvlDrctGodHist);
	}
	
	/**
	 * 클레임번호로 물류배송 테이블 조회
	 * @param clmNo
	 * @return
	 */
	public List<LgsDlv> selectLgsDlvByClmNo(String clmNo) {
	    return getSession1().selectList("com.plgrim.ncp.biz.delivery.external.selectLgsDlvByClmNo", clmNo);
    }

	/**
	 * 주문번호로 물류배송 테이블 조회
	 * @param ordNo
	 * @return
	 */
	public List<LgsDlv> selectLgsDlvByOrdNo(String ordNo) {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.external.selectLgsDlvByOrdNo", ordNo);
	}

	/**
	 * 주문배송지리스트
	 * @param lgsDlvspExtend
	 * @return
	 */
	public List<LgsDlvspExtend> selectDlvspListForOrd(LgsDlvspExtend lgsDlvspExtend) {
	    return getSession1().selectList("com.plgrim.ncp.biz.delivery.external.selectDlvspListForOrd", lgsDlvspExtend);
	}
	
	
	/**
	 * 편의점 택배시 상품명 조회
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param godNo [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 30
	 */
	public String getGoodsNm(String godNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.external.getGoodsNm", godNo);
	}
	
	
	public List<DeliveryInfErpDTO> selectDlivyDrctInfoListByPkup(DlvOrdGodInfoDTO dto) {
	    return getSession1().selectList("com.plgrim.ncp.biz.delivery.external.selectDlivyDrctInfoListByPkup", dto);
    }
/*	
	public List<InfOrdGodErpDstbExtends> selectPickupErpResveRcptfrInfoList(DeliverySearchDTO deliverySearchDTO) {
	    return getSession1().selectList("com.plgrim.ncp.biz.delivery.external.selectPickupErpResveRcptfrInfoList", deliverySearchDTO);
    }
*/
	public List<LgsDlivyDrctGod> selectDlvDrctGodByOrdNo(DeliveryPayClaimDTO deliveryPayClaimDTO) {
	    return getSession1().selectList("com.plgrim.ncp.biz.delivery.external.selectDlvDrctGodByOrdNo", deliveryPayClaimDTO);
    }
	public int checkMinusDlvCnt(LgsDlvspExtend dto) {
	    return getSession1().selectOne("com.plgrim.ncp.biz.delivery.external.checkMinusDlvCnt", dto);
    }
	public int checkItmDlvCnt(LgsDlvspExtend dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.external.checkItmDlvCnt", dto);
    }
	public void insertLgsDlvForCnclClm(LgsDlv lgsDlv) {
		getSession1().insert("com.plgrim.ncp.biz.delivery.external.insertLgsDlvForCnclClm", lgsDlv);
    }
	public void insertLgsDlvForCnclClmHist(LgsDlv lgsDlv) {
		getSession1().insert("com.plgrim.ncp.biz.delivery.external.insertLgsDlvForCnclClmHist", lgsDlv);
    }

	//ncp2 해당 국내배송비정책번호가 등록되어 있는지 확인
	public int checkDlvTurnCnt(LgsDlvspExtend dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.external.checkDlvTurnCnt", dto);
    }

	public int selectDlvTurn(DlvOrdGodInfoDTO dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.external.selectDlvTurn", dto);
	}
	
	//제휴사 주문 자사 회수 가능 여부 조회
	public String selectAffOrdMcomRtrvlPsbYn(String ordNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.delivery.external.selectAffOrdMcomRtrvlPsbYn", ordNo);
	}

	//////////////////////////////////////////CLAIM//////////////////////////////////////////////////////////////////////////////////////

	// 재출고지시 대상 조회
	public List<DeliverySearchDTO> selectReDlvDrctTargetList(LgsDlvsp dlvsp) {
		return getSession1().selectList("com.plgrim.ncp.biz.delivery.external.selectReDlvDrctTargetList", dlvsp);
	}
}
