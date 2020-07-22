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
 * @since       2015. 7. 14       
 */
package com.plgrim.ncp.biz.delivery.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGod;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlivyDrctGodHist;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsRtrvlDrctGodHist;
import com.plgrim.ncp.biz.delivery.data.DeliveryInvoiceDTO;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.delivery.repository.DeliveryCommandRepository;
import com.plgrim.ncp.biz.delivery.repository.DeliverySelectRepository;
import com.plgrim.ncp.biz.delivery.result.DeliveryClaimGoodResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryInvoiceResult;
import com.plgrim.ncp.biz.delivery.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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
 * @author stdev10.kwon
 * @since 2015. 4. 25
 */
@Slf4j
@Service
public class DeliveryCommandService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	DeliveryCommandRepository deliveryCommandRepository;
	
	@Autowired
	DeliverySelectRepository deliverySelectRepository;
	
	@Autowired
    @Qualifier("sessionTemplate1")
    SqlSession sqlSession1;
	
	/** The interface api common. */
	@Autowired
	InterfaceApiCommon interfaceApiCommon;

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
	 * 운송장수정
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param deliveryInvoiceDTO [설명]
	 * @return int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 25
	 */
	public int updateInvoice(SystemPK systemPK, DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		//이력데이터 세팅
		deliveryInvoiceDTO.setDlvComCd(deliveryInvoiceDTO.getNewDlvComCd());
		deliveryInvoiceDTO.setDmstcWaybilNo(deliveryInvoiceDTO.getNewDmstcWaybilNo());
		
		if(deliveryCommandRepository.updateInvoice(deliveryInvoiceDTO) > 0) {
			return deliveryCommandRepository.insertInvoiceHistory(deliveryInvoiceDTO);
		} else {
			return 0;
		}
	}	
	
	/**
	 * 운송장번호 개별 생성
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param listDTO [설명]
	 * @param deliveryInvoiceDTO [설명]
	 * @return int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 25
	 */	
	public int updateDeliveryInvoiceChoice(SystemPK systemPK, List<DeliveryOrderGoodResult> listDTO, DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		int result = 0;
		List<DeliveryInvoiceDTO> chkList = new ArrayList<DeliveryInvoiceDTO>();
		int i=0;
		boolean chk = true;//중복된 업데이트 방지를 위한 변수
		if(listDTO != null && listDTO.size() > 0) {
			DeliveryInvoiceDTO invDTO = new DeliveryInvoiceDTO();//첫번째 데이터는 무조건 입력
			invDTO.setOrdNo(listDTO.get(0).getOrd().getOrdNo());
			invDTO.setDlvPcupspTurn(String.valueOf(listDTO.get(0).getLgsDsp().getDlvPcupspTurn()));
			invDTO.setDlvTurn(String.valueOf(listDTO.get(0).getLgsDlv().getDlvTurn()));
			chkList.add(invDTO);
			
			for (DeliveryOrderGoodResult dto : listDTO) {
				String ordNo = dto.getOrd().getOrdNo();
				String dlvPcupspTurn = String.valueOf(dto.getLgsDsp().getDlvPcupspTurn());
				String dlvTurn = String.valueOf(dto.getLgsDlv().getDlvTurn());
				
				if(i > 0) {//첫번째데이터는 비교제외
					for(DeliveryInvoiceDTO chkDTO : chkList) {
						//주문번호,배송수거지순번,배송순번이 중복된 데이터가 존재할수 있다.
						if(ordNo.equals(chkDTO.getOrdNo()) && dlvPcupspTurn.equals(chkDTO.getDlvPcupspTurn()) && dlvTurn.equals(chkDTO.getDlvTurn())) {
							chk = false;
							break;
						}
					}
					
					
					if(chk) {//조건을 통과할경우만 비교 데이터에 추가
						invDTO.setOrdNo(ordNo);
						invDTO.setDlvPcupspTurn(dlvPcupspTurn);
						invDTO.setDlvTurn(dlvTurn);
						chkList.add(invDTO);
					}
				}
			
				if(chk) {
					String wayBilSeq = getIdGenService().generateDBSequence(sqlSession1, "SQ_WAYBIL_NO", DatabaseType.ORACLE)+""; //운송장 SEQ
					String wayBilNo = wayBilSeq + (Long.parseLong(wayBilSeq) % 7);//운송장 번호
					
					deliveryInvoiceDTO.setOrdNo(ordNo);
					deliveryInvoiceDTO.setDlvPcupspTurn(dlvPcupspTurn);
					deliveryInvoiceDTO.setDlvTurn(dlvTurn);
					deliveryInvoiceDTO.setNewDmstcWaybilNo(wayBilNo);
					deliveryInvoiceDTO.setDmstcWaybilNo(wayBilNo);
					
					result++;
					if(deliveryCommandRepository.updateInvoice(deliveryInvoiceDTO) > 0) { //수정성공후 이력등록
						deliveryCommandRepository.insertInvoiceHistory(deliveryInvoiceDTO);
					} 
					
				} else {
					chk = true;
				}
				
				i++;
			}
			
		} 
		return result;
	}	
	
	/**
	 * 매장픽업 교환번호(운송장번호) 생성.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param deliveryInvoiceDTO [설명]
	 * @throws Exception the exception
	 * @since 2015. 7. 14
	 */
	public void updateWaybilInfo4Pickup(DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		String wayBilSeq = getIdGenService().generateDBSequence(sqlSession1, "SQ_WAYBIL_NO", DatabaseType.ORACLE)+""; //운송장 SEQ
		String wayBilNo = wayBilSeq + (Long.parseLong(wayBilSeq) % 7);//운송장 번호
		
		//운송장 등록
		deliveryInvoiceDTO.setNewDlvComCd(null);
		deliveryInvoiceDTO.setNewDmstcWaybilNo(wayBilNo);
		deliveryInvoiceDTO.setUdterId(BOSecurityUtil.getLoginId());
		deliveryCommandRepository.updateInvoice(deliveryInvoiceDTO);
		
		//이력생성
		deliveryInvoiceDTO.setDlvComCd(null);
		deliveryInvoiceDTO.setDmstcWaybilNo(wayBilNo);
		deliveryInvoiceDTO.setRegtrId(BOSecurityUtil.getLoginId());
		deliveryCommandRepository.insertInvoiceHistory(deliveryInvoiceDTO);
	}
	
	/**
	 * 배송상태변경
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param listDTO [설명]
	 * @param deliveryInvoiceDTO [설명]
	 * @return int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 25
	 */
	public boolean updateCompleteDelivery(SystemPK systemPK, List<DeliveryOrderGoodResult> listDTO, DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		boolean result = false;
		if(listDTO != null && listDTO.size() > 0) {
			List<DeliveryOrderGoodResult> packList = new ArrayList<DeliveryOrderGoodResult>();
			LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
			for (DeliveryOrderGoodResult dto : listDTO) {
				if(null != dto.getLgsDdg().getPckageGodTurn() && 0 != dto.getLgsDdg().getPckageGodTurn()) {
					packList.add(dto);
				} else {
					deliveryInvoiceDTO.setDlivyDrctGodNo(dto.getLgsDdg().getDlivyDrctGodNo());
					deliveryInvoiceDTO.setDlvStatCd(dto.getLgsDdg().getDlvStatCd());
	
					if(dto.getLgsDdg().getDlivyDrctQty() == null || "".equals(String.valueOf(dto.getLgsDdg().getDlivyDrctQty()))) {
						deliveryInvoiceDTO.setDlivyDrctQty(0);//출고지시수량
					} else {
						deliveryInvoiceDTO.setDlivyDrctQty(dto.getLgsDdg().getDlivyDrctQty().intValue());//출고지시수량
					}
					
					lgsDlivyDrctGodHist.setDlivyDrctGodNo(dto.getLgsDdg().getDlivyDrctGodNo());
					lgsDlivyDrctGodHist.setRegtrId(deliveryInvoiceDTO.getRegtrId());
					lgsDlivyDrctGodHist.setUdterId(deliveryInvoiceDTO.getUdterId());
					
					deliveryCommandRepository.updateCompleteDelivery(deliveryInvoiceDTO);
					deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);//이력저장

				}
			}
			
			if(packList.size() > 0) {//패키지 묶음 단일 처리
				
				List<DeliveryInvoiceDTO> chkList = new ArrayList<DeliveryInvoiceDTO>();//중복제거한 패키지
				
				
				deliveryInvoiceDTO.setOrdNo(packList.get(0).getOrd().getOrdNo());
				deliveryInvoiceDTO.setDlivyDrctGodNo(packList.get(0).getLgsDdg().getDlivyDrctGodNo());
				deliveryInvoiceDTO.setDlvStatCd(packList.get(0).getLgsDdg().getDlvStatCd());
				deliveryInvoiceDTO.setPckageGodTurn(packList.get(0).getLgsDdg().getPckageGodTurn());
				
				if(packList.get(0).getLgsDdg().getDlivyDrctQty() == null || "".equals(String.valueOf(packList.get(0).getLgsDdg().getDlivyDrctQty()))) {
					deliveryInvoiceDTO.setDlivyDrctQty(0);//출고지시수량
				} else {
					deliveryInvoiceDTO.setDlivyDrctQty(packList.get(0).getLgsDdg().getDlivyDrctQty().intValue());//출고지시수량
				}
				
				chkList.add(deliveryInvoiceDTO);
				int j = 0;
				for (DeliveryOrderGoodResult dto : packList) {
				
					boolean chk = true;
					
					String ordNo = dto.getOrd().getOrdNo();
					String dlivyDrctGodNo = dto.getLgsDdg().getDlivyDrctGodNo();
					String dlvStatCd = dto.getLgsDdg().getDlvStatCd();
					int pckageGodTurn = dto.getLgsDdg().getPckageGodTurn();

					
					if(j > 0) {//첫번째데이터는 비교제외
						for(DeliveryInvoiceDTO chkDTO : chkList) {
							//주문번호,배송수거지순번,배송순번이 중복된 데이터가 존재할수 있다.
							if(ordNo.equals(chkDTO.getOrdNo()) && pckageGodTurn == chkDTO.getPckageGodTurn()) {
								chk = false;
								break;
							}
						}
						
						if(chk) {//조건을 통과할경우만 비교 데이터에 추가
							deliveryInvoiceDTO.setOrdNo(ordNo);
							deliveryInvoiceDTO.setDlivyDrctGodNo(dlivyDrctGodNo);
							deliveryInvoiceDTO.setDlvStatCd(dlvStatCd);
							deliveryInvoiceDTO.setPckageGodTurn(pckageGodTurn);
							
							if(dto.getLgsDdg().getDlivyDrctQty() == null || "".equals(String.valueOf(dto.getLgsDdg().getDlivyDrctQty()))) {
								deliveryInvoiceDTO.setDlivyDrctQty(0);//출고지시수량
							} else {
								deliveryInvoiceDTO.setDlivyDrctQty(dto.getLgsDdg().getDlivyDrctQty().intValue());//출고지시수량
							}
							
							chkList.add(deliveryInvoiceDTO);
						}
					}
				    j++;
		        }
			
				for (DeliveryInvoiceDTO dto : chkList) {		
					lgsDlivyDrctGodHist.setDlivyDrctGodNo(dto.getDlivyDrctGodNo());
					lgsDlivyDrctGodHist.setRegtrId(dto.getRegtrId());
					lgsDlivyDrctGodHist.setUdterId(dto.getUdterId());
					
					deliveryCommandRepository.updateCompleteDeliveryPackList(deliveryInvoiceDTO);
					deliveryCommandRepository.insertLgsDlivyDrctGodHistPackList(lgsDlivyDrctGodHist);//이력저장
				}
			}
			result = true;
		} else {
			result = false;
		}
		return result;
	}		
	
	/**
	 * 송장,택배사수정(신규로들어온 송장,택배사와 수정되기전 데이터와 비교하여 수정된건만 저장)
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param listDTO [설명]
	 * @param deliveryInvoiceDTO [설명]
	 * @return int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 25
	 */
	public boolean updateInoviceCompare(SystemPK systemPK, List<DeliveryOrderGoodResult> listDTO, DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		boolean result = false;
		List<DeliveryInvoiceDTO> chkList = new ArrayList<DeliveryInvoiceDTO>();
		int i=0;
		boolean chk = true;//중복된 업데이트 방지를 위한 변수
		
		if(listDTO != null && listDTO.size() > 0) {
			DeliveryInvoiceDTO invDTO = new DeliveryInvoiceDTO();//첫번째 데이터는 무조건 입력
			invDTO.setOrdNo(listDTO.get(0).getOrd().getOrdNo());
			invDTO.setDlvPcupspTurn(String.valueOf(listDTO.get(0).getLgsDsp().getDlvPcupspTurn()));
			invDTO.setDlvTurn(String.valueOf(listDTO.get(0).getLgsDlv().getDlvTurn()));
			chkList.add(invDTO);
			
			for (DeliveryOrderGoodResult dto : listDTO) {
				String ordNo = dto.getOrd().getOrdNo();
				String dlvPcupspTurn = String.valueOf(dto.getLgsDsp().getDlvPcupspTurn());
				String dlvTurn = String.valueOf(dto.getLgsDlv().getDlvTurn());
				String wayBilNo = dto.getLgsDlv().getDmstcWaybilNo();
				String dlvComCd = dto.getLgsDlv().getDlvComCd();
				
				
				if(i > 0) {//첫번째데이터는 비교제외
					for(DeliveryInvoiceDTO chkDTO : chkList) {
						//주문번호,배송수거지순번,배송순번이 중복된 데이터가 존재할수 있다.
						if(ordNo.equals(chkDTO.getOrdNo()) && dlvPcupspTurn.equals(chkDTO.getDlvPcupspTurn()) && dlvTurn.equals(chkDTO.getDlvTurn())) {
							chk = false;
							break;
						}
					}
					
					if(chk) {//조건을 통과할경우만 비교 데이터에 추가
						invDTO.setOrdNo(ordNo);
						invDTO.setDlvPcupspTurn(dlvPcupspTurn);
						invDTO.setDlvTurn(dlvTurn);
						chkList.add(invDTO);
					}
				}
				
				if(chk) {
					deliveryInvoiceDTO.setOrdNo(ordNo);//주문번호
					deliveryInvoiceDTO.setDlvPcupspTurn(dlvPcupspTurn);//배송수거지순번
					deliveryInvoiceDTO.setDlvTurn(dlvTurn);//배송순번
					deliveryInvoiceDTO.setNewDmstcWaybilNo(wayBilNo);//변경된운송장번호
					deliveryInvoiceDTO.setNewDlvComCd(dlvComCd);//변경된택배사
					
					DeliveryInvoiceResult deliveryInvoiceResult = deliverySelectRepository.getInvoiceBasic(deliveryInvoiceDTO);//변경하기전 운송장번호,택배사
					deliveryInvoiceDTO.setDlvComCd(deliveryInvoiceResult.getDlvComCd());//변경전택배사
					deliveryInvoiceDTO.setDmstcWaybilNo(deliveryInvoiceResult.getDmstcWaybilNo());//변경전운송장번호
					
					if(wayBilNo.equals(deliveryInvoiceDTO.getDmstcWaybilNo()) && dlvComCd.equals(deliveryInvoiceDTO.getDlvComCd())) {
					} else {
						if(deliveryCommandRepository.updateInvoice(deliveryInvoiceDTO) > 0) { //수정성공후 이력등록
							deliveryInvoiceDTO.setDlvComCd(dlvComCd);		//택배사
							deliveryInvoiceDTO.setDmstcWaybilNo(wayBilNo);	//운송장번호
							deliveryCommandRepository.insertInvoiceHistory(deliveryInvoiceDTO);
						}	
					}
				}else {
					chk = true;
				}
				
				i++;
			}
			result = true;
		} else {
			result = false;
		}
		return result;
	}	
	
	/**
	 * 배송메모수정
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param listDTO [설명]
	 * @param deliveryInvoiceDTO [설명]
	 * @return int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 25
	 */
	public boolean updateDeliveryShopMemo(SystemPK systemPK, List<DeliveryOrderGoodResult> listDTO, DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		boolean result = false;
		if(listDTO != null && listDTO.size() > 0) {
			
			for (DeliveryOrderGoodResult dto : listDTO) {
				deliveryInvoiceDTO.setDlivyDrctGodMemoCont(dto.getDlivyDrctGodMemoCont());
				deliveryInvoiceDTO.setDlivyDrctGodNo(dto.getLgsDdg().getDlivyDrctGodNo());
				deliveryCommandRepository.updateDeliveryShopMemo(deliveryInvoiceDTO);
			}
			
			result = true;
		} else {
			result = false;
		}
		return result;
	}	
	
	/**
	 * 회수관리 택배사,송장,정산구분 배송비 저장
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param listDTO [설명]
	 * @param deliveryInvoiceDTO [설명]
	 * @return int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 25
	 */
	public boolean updateRtrvlSimple(SystemPK systemPK, List<DeliveryClaimGoodResult> listDTO, DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		boolean result = false;
		if(listDTO != null && listDTO.size() > 0) {
			LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist = new LgsRtrvlDrctGodHist();
			
			for (DeliveryClaimGoodResult dto : listDTO) {
				String ordNo = dto.getOrd().getOrdNo();
				String dlvPcupspTurn = String.valueOf(dto.getLgsDsp().getDlvPcupspTurn());
				String dlvTurn = String.valueOf(dto.getLgsDlv().getDlvTurn());
				String wayBilNo = dto.getLgsDlv().getDmstcWaybilNo();
				String dlvComCd = dto.getLgsDlv().getDlvComCd();
				String rtgodcstCalSectCd = dto.getLgsRdg().getRtgodcstCalSectCd();
				String rtgodcstCalAmt = String.valueOf(dto.getLgsRdg().getRtgodcstCalAmt());
				String rtrvlDrctGodNo = dto.getLgsRdg().getRtrvlDrctGodNo();
				
				deliveryInvoiceDTO.setOrdNo(ordNo);//주문번호
				deliveryInvoiceDTO.setDlvPcupspTurn(dlvPcupspTurn);//배송수거지순번
				deliveryInvoiceDTO.setDlvTurn(dlvTurn);//배송순번
				deliveryInvoiceDTO.setNewDmstcWaybilNo(wayBilNo);//변경된운송장번호
				deliveryInvoiceDTO.setNewDlvComCd(dlvComCd);//변경된택배사
				deliveryInvoiceDTO.setRtgodcstCalSectCd(rtgodcstCalSectCd);//정산구분
				deliveryInvoiceDTO.setRtgodcstCalAmt(rtgodcstCalAmt);//배송비
				deliveryInvoiceDTO.setRtrvlDrctGodNo(rtrvlDrctGodNo);//회수지시상품번호
				
				DeliveryInvoiceResult deliveryInvoiceResult = deliverySelectRepository.getInvoiceBasic(deliveryInvoiceDTO);//변경하기전 운송장번호,택배사
				deliveryInvoiceDTO.setDlvComCd(deliveryInvoiceResult.getDlvComCd());//변경전택배사
				deliveryInvoiceDTO.setDmstcWaybilNo(deliveryInvoiceResult.getDmstcWaybilNo());//변경전운송장번호
				
				if(wayBilNo.equals(deliveryInvoiceDTO.getDmstcWaybilNo()) && dlvComCd.equals(deliveryInvoiceDTO.getDlvComCd())) {
				} else {
					if(deliveryCommandRepository.updateInvoice(deliveryInvoiceDTO) > 0) { //수정성공후 이력등록
						deliveryCommandRepository.insertInvoiceHistory(deliveryInvoiceDTO);
					}	
				}
				
				lgsRtrvlDrctGodHist.setRtrvlDrctGodNo(rtrvlDrctGodNo);
				lgsRtrvlDrctGodHist.setRegtrId(deliveryInvoiceDTO.getRegtrId());
				lgsRtrvlDrctGodHist.setUdterId(deliveryInvoiceDTO.getUdterId());
				
				deliveryCommandRepository.insertLgsRtrvlDrctGodHist(lgsRtrvlDrctGodHist);//물류회수지시상품이력저장
				deliveryCommandRepository.updateRtrvlSimple(deliveryInvoiceDTO);//정산구분 배송비수정
			}
			
			result = true;
		} else {
			result = false;
		}
		return result;
	}	
	
	/**
	 * 회수상태수정
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param systemPK [설명]
	 * @param listDTO [설명]
	 * @param deliveryInvoiceDTO [설명]
	 * @return int [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 25
	 */
	public List<DeliveryClaimGoodResult> updateRtrvlStat(SystemPK systemPK, List<DeliveryClaimGoodResult> listDTO, DeliveryInvoiceDTO deliveryInvoiceDTO) throws Exception {
		List<DeliveryClaimGoodResult> returnRtrvlList = new ArrayList<DeliveryClaimGoodResult>();
		if(listDTO != null && listDTO.size() > 0) {
			LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist = new LgsRtrvlDrctGodHist();
			LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();

			for (DeliveryClaimGoodResult dto : listDTO) {

				String ordNo = dto.getOrd().getOrdNo();
				String clmNo = dto.getClm().getClmNo();
				String clmTpCd = dto.getClm().getClmTpCd();
				String dlvPcupspTurn = String.valueOf(dto.getLgsDsp().getDlvPcupspTurn());
				String dlvTurn = String.valueOf(dto.getLgsDlv().getDlvTurn());
				String wayBilNo = dto.getLgsDlv().getDmstcWaybilNo();
				String dlvComCd = dto.getLgsDlv().getDlvComCd();
				String rtgodcstCalSectCd = dto.getLgsRdg().getRtgodcstCalSectCd();
				String rtgodcstCalAmt = String.valueOf(dto.getLgsRdg().getRtgodcstCalAmt());
				String rtrvlDrctGodNo = dto.getLgsRdg().getRtrvlDrctGodNo();
				String newRtrvlStatCd = dto.getNewRtrvlStatCd();
				String rfdDelayCd = dto.getRfdDelayCd();
				String rfdDelayResnDscr = dto.getRfdDelayResnDscr();

				deliveryInvoiceDTO.setOrdNo(ordNo);//주문번호
				deliveryInvoiceDTO.setClmNo(clmNo);//클레임번호
				deliveryInvoiceDTO.setDlvPcupspTurn(dlvPcupspTurn);//배송수거지순번
				deliveryInvoiceDTO.setDlvTurn(dlvTurn);//배송순번
				deliveryInvoiceDTO.setNewDmstcWaybilNo(wayBilNo);//변경된운송장번호
				deliveryInvoiceDTO.setNewDlvComCd(dlvComCd);//변경된택배사
				deliveryInvoiceDTO.setRtgodcstCalSectCd(rtgodcstCalSectCd);//정산구분
				deliveryInvoiceDTO.setRtgodcstCalAmt(rtgodcstCalAmt);//배송비
				deliveryInvoiceDTO.setRtrvlDrctGodNo(rtrvlDrctGodNo);//회수지시상품번호

				deliveryCommandRepository.updateInvoice(deliveryInvoiceDTO);
				deliveryInvoiceDTO.setRfdDelayCd(rfdDelayCd);
				deliveryInvoiceDTO.setRfdDelayResnDscr(rfdDelayResnDscr);
				deliveryInvoiceDTO.setRtrvlStatCd(newRtrvlStatCd);
				deliveryCommandRepository.updateRtrvlStat(deliveryInvoiceDTO);

				List<DeliveryInvoiceResult> deliveryInvoiceResults = deliverySelectRepository.getInvoiceBasic2(deliveryInvoiceDTO);

				if("RTRVL_COMPT".equals(newRtrvlStatCd)) {
					//회수완료시 재고 정보용 데이터 셋팅
					DeliveryClaimGoodResult returnRtrvl = new DeliveryClaimGoodResult();
					returnRtrvl.setOrd(dto.getOrd());
					returnRtrvl.setClm(dto.getClm());
					returnRtrvl.setClmwg(dto.getClmwg());
					returnRtrvl.setLgsRdg(dto.getLgsRdg());
					if("GNRL_EXCHG".equals(clmTpCd)) {
						returnRtrvl.setParams("EXCHG_WRHS");	//출고 구분으로 사용(교환 입고)
					} else {
						returnRtrvl.setParams("");	//출고 구분으로 사용
					}
					returnRtrvlList.add(returnRtrvl);
					//반품 회수완료후 클레임상품이 하나인 클레임은 클레임 완료상태로 변경한다.
					//if("RTGOD".equals(clmTpCd) && deliveryInvoiceResult.getClmWrhsGodCnt() == 1) {
					//	deliveryCommandRepository.updateClmStatCd(deliveryInvoiceDTO);
					//}
					//교환일때 교환입고대기 상태인 출고지시 상품을 배정대기 상태로 변경한다.
					if("GNRL_EXCHG".equals(clmTpCd)) {
						for(DeliveryInvoiceResult deliveryInvoiceResult : deliveryInvoiceResults) {
							lgsDlivyDrctGodHist.setDlivyDrctGodNo(deliveryInvoiceResult.getExchgDlivyDrctGodNo());
							lgsDlivyDrctGodHist.setRegtrId(deliveryInvoiceDTO.getRegtrId());
							lgsDlivyDrctGodHist.setUdterId(deliveryInvoiceDTO.getUdterId());
							deliveryCommandRepository.updateLgsDlivyDrctGod(lgsDlivyDrctGodHist);
							deliveryCommandRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
							//교환시 출고 상품 재고 정보용 데이터 셋팅
							DeliveryClaimGoodResult returnRtrvl2 = new DeliveryClaimGoodResult();
							returnRtrvl2.setOrd(dto.getOrd());
							returnRtrvl2.setClm(dto.getClm());
							returnRtrvl2.setClmwg(dto.getClmwg());
							returnRtrvl2.setLgsRdg(dto.getLgsRdg());
							returnRtrvl2.setParams("EXCHG_DLIVY");	//출고 구분으로 사용(교환 출고)
							returnRtrvl2.setParams2(deliveryInvoiceResult.getExchgDlivyDrctQty()); //출고 수량으로 사용
							returnRtrvlList.add(returnRtrvl2);
						}
					}
				}

				lgsRtrvlDrctGodHist.setRtrvlDrctGodNo(dto.getLgsRdg().getRtrvlDrctGodNo());
				lgsRtrvlDrctGodHist.setRegtrId(deliveryInvoiceDTO.getRegtrId());
				lgsRtrvlDrctGodHist.setUdterId(deliveryInvoiceDTO.getUdterId());
				
				deliveryCommandRepository.insertLgsRtrvlDrctGodHist(lgsRtrvlDrctGodHist);//이력저장
			}
		}
		return returnRtrvlList;
	}

	/**
	 * [매장전용] #50212 픽업 재진열 대상 알림(매장 Dashboard) 기능 추가
	 * 	- '픽업매장 재진열 완료' 수정
	 *
	 * @param systemPk
	 * @param deliveryOrderGoodDTO
	 * @return
	 * @throws Exception
	 */
	public int updateRedispCompt(SystemPK systemPk, DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return this.deliveryCommandRepository.updateRedispCompt(deliveryOrderGoodDTO);
	}
}
