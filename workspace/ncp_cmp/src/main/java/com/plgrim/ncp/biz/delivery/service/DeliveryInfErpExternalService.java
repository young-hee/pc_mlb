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
 * @since       2015. 4. 28
 */
package com.plgrim.ncp.biz.delivery.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.biz.delivery.data.DeliveryInfErpDTO;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.data.DlvOrdGodInfoDTO;
import com.plgrim.ncp.biz.delivery.repository.DeliveryExternalRepository;

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
 * @since 2015. 4. 25
 */
@Slf4j
@Service
public class DeliveryInfErpExternalService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DeliveryExternalRepository deliveryExternalRepository;


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
	 * 출고지시건 정보 조회. 클레임에서 사용
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 25
	 */
	public List<DeliveryInfErpDTO> selectDlivyDrctInfoListByClm(DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<DeliveryInfErpDTO> resultList = new ArrayList<DeliveryInfErpDTO>();
		if(deliverySearchDTO.getClmDlvOrdGodInfoList()!=null && deliverySearchDTO.getClmDlvOrdGodInfoList().size()!=0){
			for(DlvOrdGodInfoDTO dto : deliverySearchDTO.getClmDlvOrdGodInfoList()){
				dto.setClmNo(deliverySearchDTO.getClmNo());
				dto.setClmStatCd(deliverySearchDTO.getClmStatCd());
				resultList.addAll(deliveryExternalRepository.selectDlivyDrctInfoListByClm(dto));
			}
		}else{
			DlvOrdGodInfoDTO dto = new DlvOrdGodInfoDTO();
			dto.setOrdNo(deliverySearchDTO.getOrdNo());
			dto.setClmNo(deliverySearchDTO.getClmNo());
			dto.setClmStatCd(deliverySearchDTO.getClmStatCd());
			resultList = deliveryExternalRepository.selectDlivyDrctInfoListByClm(dto);
		}
		return resultList;
	}

	/**
	 * 예약영수증생성건 정보 조회. 클레임용
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param deliverySearchDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 25
	 */
	public List<DeliveryInfErpDTO> selectErpResveRcptfrInfoListByClm(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryExternalRepository.selectErpResveRcptfrInfoListByClm(deliverySearchDTO);
	}

	
	public List<DeliveryInfErpDTO> selectDlivyDrctInfoListByPkup(DeliverySearchDTO deliverySearchDTO) throws Exception {
		List<DeliveryInfErpDTO> resultList = new ArrayList<DeliveryInfErpDTO>();
		
		DlvOrdGodInfoDTO dto = new DlvOrdGodInfoDTO();
		dto.setOrdNo(deliverySearchDTO.getOrdNo());
		resultList = deliveryExternalRepository.selectDlivyDrctInfoListByPkup(dto);
	
		return resultList;
	}
	/*
	public List<InfOrdGodErpDstbExtends> selectPickupErpResveRcptfrInfoList(DeliverySearchDTO deliverySearchDTO) throws Exception {
		return deliveryExternalRepository.selectPickupErpResveRcptfrInfoList(deliverySearchDTO);
	}
	*/
}
