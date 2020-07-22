package com.plgrim.ncp.cmp.member.bo.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoGodInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInq;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqOrdGod;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvl;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRfdBnkAcct;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.MbrDlvspPsnlInfoUdtSectCd;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.MbrPsnlInfoUdtSectCd;
import com.plgrim.ncp.biz.callcenter.result.CsoOthersMemberResult;
import com.plgrim.ncp.biz.callcenter.service.CsoOthersMemberService;
import com.plgrim.ncp.biz.delivery.data.DeliverySearchDTO;
import com.plgrim.ncp.biz.delivery.repository.DeliveryCommandRepository;
import com.plgrim.ncp.biz.delivery.service.DeliveryCommandService;
import com.plgrim.ncp.biz.delivery.service.DeliveryListService;
import com.plgrim.ncp.biz.delivery.service.DeliveryService;
import com.plgrim.ncp.biz.goods.result.GoodsReviewResult;
import com.plgrim.ncp.biz.goods.service.GoodsContentService;
import com.plgrim.ncp.biz.member.data.MemberBoDTO;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.MysizeDTO;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.member.service.MemberActivitySelectService;
import com.plgrim.ncp.biz.member.service.MemberBaseCommandService;
import com.plgrim.ncp.biz.member.service.MemberBaseSelectService;
import com.plgrim.ncp.biz.member.service.MemberOrderCommandService;
import com.plgrim.ncp.biz.member.service.MemberOrderSelectService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoCommandService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoSelectService;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.result.OrderBoResult;
import com.plgrim.ncp.biz.order.service.OrderCommandService;
import com.plgrim.ncp.biz.order.service.OrderOthersMemberService;
import com.plgrim.ncp.biz.order.service.OrderSelectService;
import com.plgrim.ncp.biz.promotion.result.EventOthersMemberResult;
import com.plgrim.ncp.biz.promotion.service.EventOthersMemberService;
import com.plgrim.ncp.cmp.member.bo.MemberActivityBOComponent;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.interfaces.delivery.adapter.DeliveryAdapter;
import com.plgrim.ncp.interfaces.delivery.data.DlivyDrctSDO;
import com.plgrim.ncp.interfaces.delivery.data.DlivyWmsSDO;
import com.plgrim.ncp.interfaces.order.adapter.OrderAdapter;
import com.plgrim.ncp.interfaces.order.data.OrderOfflinePurchaseSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

@Transactional(value = "transactionManager")
@Component
@Slf4j
public class MemberActivityBOComponentImpl extends AbstractComponent implements MemberActivityBOComponent {
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
 
	@Autowired
	MemberOrderSelectService memberOrderSelectService;

	@Autowired
	MemberOrderCommandService memberOrderCommandService;

	@Autowired
	MemberBaseCommandService memberBaseCommandService;

	@Autowired
	MemberBaseSelectService memberBaseSelectService;

	@Autowired
	MemberPersonalInfoCommandService memberPersonalInfoCommandService;

	@Autowired
	MemberPersonalInfoSelectService memberPersonalInfoSelectService;

	@Autowired
	OrderSelectService orderSelectService;

	@Autowired
	InterfaceApiCommon interfaceApiCommon;

	@Autowired
	OrderOthersMemberService orderOthersMemberService;

	@Autowired
	CsoOthersMemberService othersMemberService;

	/** 주문 서비스. */
	@Autowired
	OrderCommandService orderCommandService;

	
	/** 배송 서비스. */
	@Autowired
	DeliveryService deliveryService;

	/** 물류 서비스. */
	@Autowired
	DeliveryListService deliveryListService;

	/** 물류 서비스. */
	@Autowired
	DeliveryCommandService deliveryCommandService;

	@Autowired
	private CsoOthersMemberService csoOthersMemberService;

	@Autowired
	private MemberActivitySelectService memberActivitySelectService;

	@Autowired
	private EventOthersMemberService eventOthersMemberService;
	
	@Autowired
	private GoodsContentService goodsContentService;
	
	@Autowired
	private DeliveryCommandRepository deliveryCommandRepository;
	
	@Autowired
	private DeliveryAdapter deliveryAdapter;
	
	@Autowired
	private OrderAdapter orderAdapter;
	
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

	@Override
    public Map<String, String> issueReceipt(HttpServletRequest request, SystemPK pk, MypageOrderInfoDTO mypageOrderInfoDTO)  {

		try {
			Map<String, String> returnMap = new HashMap<String, String>();
			MypageOrderInfoDTO orderInfoDTO = orderSelectService.selectPayMethodChange(mypageOrderInfoDTO);
			
			if(orderInfoDTO != null){
				Map<String, String> paramMap = new HashMap<String, String>();
				
				String payMnCd = "";
				if("RLTM_BNK_ACCT_PAY".equals(orderInfoDTO.getPayMnCd())){ // 실시간 계좌 이체 
					payMnCd = "ABANK";
				}else if("NON_BNKBOK_PAY".equals(orderInfoDTO.getPayMnCd()) || "VIRTL_BNK_ACCT_PAY".equals(orderInfoDTO.getPayMnCd())) { //무통장 입금
					payMnCd = "NBANK";		
				}
				
				
				paramMap.put("mbr_id", mypageOrderInfoDTO.getMbrNo());
				paramMap.put("number", mypageOrderInfoDTO.getAllatCertNo());
				paramMap.put("main_pay_amt", orderInfoDTO.getPayExchgRtCrncySumAmt().toString());
				paramMap.put("product_nm", orderInfoDTO.getGodNm());
			    paramMap.put("receipt_type", payMnCd);
			    paramMap.put("seq_no", orderInfoDTO.getPgAprvNo());
			    // 07.10 추가 이영호
			    paramMap.put("pgStoreId", orderInfoDTO.getPgStoreId());
			    
				log.debug("@@@@@@@@@@@ : "+returnMap.toString());
				log.debug("@@@@@@@@@@@ : "+mypageOrderInfoDTO.toString());
				if("0000".equals(returnMap.get("reply_cd"))){
					Ord ord = new Ord();
					ord.setOrdNo(mypageOrderInfoDTO.getOrdNo());
					ord.setRcptfrAprvNo(returnMap.get("approval_no"));
					ord.setRcptfrReqstCd("CASH_RCPTFR_PUBLI");
					ord.setRcptfrPrcsCd("CASH_RCPTFR_PUBLI_COMPT");
					ord.setRcptfrPrposCd(mypageOrderInfoDTO.getRcptfrPrposCd());
					orderCommandService.updateReceipt(ord);
				}
			}
			return returnMap;
		} catch (Exception e) {
    		throw new RuntimeException(e);
    	}
	}

	/**
	 * 대표 환불 계좌 변경.
	 */
	@Override
	public void modifyRprstRefundAccountBy(SystemPK systemPK, List<MbrRfdBnkAcct> listMrba, String loginId)  {
    	// step 1. 대표 설정 확인
		MbrRfdBnkAcct mrParam = new MbrRfdBnkAcct();
    	for(MbrRfdBnkAcct mrba : listMrba){
    		if(!StringService.equalsIgnoreCase(mrba.getRprstRfdBnkAcctYn(), "1") ){ continue; }
    		
    		mrParam = mrba;
    		mrParam.setRprstRfdBnkAcctYn(MemberEnum.YES.toString());
    	}
    	
    	// step 2. 기존 대표 환불 계좌 설정 해제
    	memberOrderCommandService.updateRprstRefundAccountRevome(mrParam, loginId);
    	
    	// step 3. 대표 환불 계좌 설정
    	memberOrderCommandService.updateRprstRefundAccountSet(mrParam, loginId);
	}


	/**
     * 회원 주문 배송지 변경.
     */
    @Override
    public void updateDeliveryLocationChange(SystemPK systemPK, LgsDlvsp lgsDlvsp)  {
    	// 주문 배송지 업데이트
    	deliveryService.updateDeliveryLocationChange(lgsDlvsp);
    	
    	// 배송지 변경 이력테이블 업데이트
    	LgsDlvspHist lgsDlvspHist = new LgsDlvspHist();
    	lgsDlvspHist.setOrdNo(lgsDlvsp.getOrdNo());
    	lgsDlvspHist.setDlvPcupspTurn(lgsDlvsp.getDlvPcupspTurn());
    	
    	try {
			deliveryService.insertFoLgsDlvspHist(lgsDlvspHist);
    	} catch (Exception e) {
    		throw new RuntimeException(e);
    	}
    	
    	// 재 출고지시
    	List<DeliverySearchDTO> dlvDrctTgtList = deliveryService.selectReDlvDrctTargetList(lgsDlvsp);
    	
    	if (dlvDrctTgtList == null || dlvDrctTgtList.isEmpty()) {
    		return;
    	}
    	
    	try {
    		
    		List<DlivyDrctSDO> dlvDrctList = new ArrayList<DlivyDrctSDO>();
	    	
    		for(DeliverySearchDTO dlvDrctTgt : dlvDrctTgtList) {
	    		DlivyDrctSDO dlvDrct = deliveryCommandRepository.selectWmsDlivyDrctTargetList(dlvDrctTgt);
	    		dlvDrctList.add(dlvDrct);
	    	}
	    	
    		if (dlvDrctList.isEmpty() == false) {
				
					
				DlivyWmsSDO dlivyWmsSDO = new DlivyWmsSDO();
				dlivyWmsSDO.setReleaseList(dlvDrctList);
				
				AdapterHeader header = new AdapterHeader();
				header.setRequestId(interfaceApiCommon.getRequestId());
				header.setMallId("DXM");
				header.setLangCd("KOR");
				header.setDvcCd("PC");
				
				dlivyWmsSDO.setCallerId(this.getClass().getSimpleName() + this.getClass().getDeclaredMethods()[0].getName());
				
				String returnData = deliveryAdapter.sendWMSDlivyDrct(dlivyWmsSDO, header);
				
				DlivyWmsSDO dlivyWmsSDOReturn = (DlivyWmsSDO) JsonService.unmarshalling(returnData, DlivyWmsSDO.class);
				
				if("200".equals(dlivyWmsSDOReturn.getResponseCd()) == false) {
					throw new RuntimeException("출고지시 실패");
				}
			}
    		
    	} catch (Exception e) {
    		throw new RuntimeException(e);
    	}
    }	


	/**
	 * 대표배송지 변경.
	 */
	@Override
	public void modifyBaseDeliveryLocationBy(SystemPK systemPK, List<MemberBoDTO> listDTO, String loginId)  {
    	// step 1. 대표배송지로 설정된 배송지 확인
		MbrDlvsp mdParam = new MbrDlvsp();
    	for(MemberBoDTO bDTO : listDTO){
    		if(!StringService.equalsIgnoreCase(bDTO.getMbrDlvsp().getBaseDlvspYn(), "1") ){ continue; }
    		
    		mdParam = bDTO.getMbrDlvsp();
    		mdParam.setBaseDlvspYn(MemberEnum.YES.toString());
    	}
    	
    	// step 2. 기존 대표 배송지 설정 해제
    	memberOrderCommandService.updateBaseDeliveryLocationRevomeBy(mdParam, loginId);
    	
    	// step 3. 대표배송지 설정
		memberOrderCommandService.updateBaseDeliveryLocationSetBy(mdParam, loginId);
	}

	/**
	 * 배송지 목록 저장.
	 */
	@Override
	public void saveDeliveryLocationListBy(SystemPK systemPK, List<MemberBoDTO> listDTO, String loginId)  {
		MemberBoDTO mDTO = new MemberBoDTO();
    	MbrDlvsp mdParam = new MbrDlvsp();
    	Iterator<MemberBoDTO> iterator = listDTO.iterator();
    	
    	// 개인정보변경이력 SEQ 생성
    	MbrPsnlInfoModHist mpim = null;
    	
    	// 배송지 다건 저장
    	while(iterator.hasNext()) {
    		mDTO = iterator.next();
    		mdParam = mDTO.getMbrDlvsp(); 
    		mdParam.setUdterId(loginId);
    		boolean isReg = mdParam.getDlvAdbukTurn() == null ? true : false;
    		// 휴대폰번호, 전화번호 처리
    		List<String> mobileNo = makeGlobalPhoneNo(mDTO.getMobilNo());
    		mdParam.setMobilNationNo(mobileNo.get(0));
    		mdParam.setMobilAreaNo(mobileNo.get(1));
    		mdParam.setMobilTlofNo(mobileNo.get(2));
    		mdParam.setMobilTlofWthnNo(mobileNo.get(3));

			/* 해외 회원의 경우 전화번호 입력을 안받음 */
			if(!"OVSEA_ADDR".equalsIgnoreCase(mdParam.getDlvAddrSectCd())){
				List<String> telNo = makeGlobalPhoneNo(mDTO.getTelNo());
				mdParam.setTelNationNo(telNo.get(0));
				mdParam.setTelAreaNo(telNo.get(1));
				mdParam.setTelTlofNo(telNo.get(2));
				mdParam.setTelTlofWthnNo(telNo.get(3));
				mdParam.setNationCd(mdParam.getNationCd());
			}
    		// step 1. 개인정보변경이력 등록
    		if(mpim == null){
    			mpim = memberPersonalInfoCommandService.setMbrPsnlInfoModHist(mdParam.getMbrNo(), "", loginId, false);
    		}
    		
			if("Y".equalsIgnoreCase(mDTO.getMbrBaseAddrYn())){
				String[] codeArr = { MbrPsnlInfoUdtSectCd.MBR_ADDR.toString()					// 주소
									, MbrPsnlInfoUdtSectCd.MBR_POST_NO.toString()				// 회원 우편번호
									, MbrPsnlInfoUdtSectCd.MOBIL_NO.toString()					// 휴대전화번호
									, MbrDlvspPsnlInfoUdtSectCd.DLVSP_ADDR.toString()			// 배송지 주소
    							, MbrDlvspPsnlInfoUdtSectCd.DLVSP_ADDRSE.toString()				// 배송지 수취인
    							, MbrDlvspPsnlInfoUdtSectCd.DLVSP_TEL_NO.toString()				// 배송지 전화 번호
    							, MbrDlvspPsnlInfoUdtSectCd.DLVSP_MOBIL_NO.toString()			// 배송지 휴대전화 번호
    			   			   };
    		memberPersonalInfoCommandService.insertPersonalInfoForMbrDlvsp(mdParam, mpim, codeArr, isReg);    		
        	
				Mbr mbr = new Mbr();
				mbr.setMbrNo(mdParam.getMbrNo());
				mbr.setMbrAddrNationCd(mdParam.getNationCd());
				mbr.setMbrAddrSectCd(mdParam.getDlvAddrSectCd());
				mbr.setMbrPostNo(mdParam.getPostNo());
				mbr.setMbrBaseAddr(mdParam.getBaseAddr());
				mbr.setMbrDetailAddr(mdParam.getDetailAddr());
				mbr.setMbrCtyNm(mdParam.getCtyNm());
				mbr.setMbrLcltyNm(mdParam.getLcltyNm());

				mbr.setMobilNationNo(mobileNo.get(0));
				mbr.setMobilAreaNo(mobileNo.get(1));
				mbr.setMobilTlofNo(mobileNo.get(2));
				mbr.setMobilTlofWthnNo(mobileNo.get(3));

				MemberFoDTO dto = new MemberFoDTO();
				dto.setMbr(mbr);
				memberBaseCommandService.updateMemberChoiceInfo(dto);
			} else {
				String[] codeArr = { MbrDlvspPsnlInfoUdtSectCd.DLVSP_ADDR.toString()				// 배송지 주소
									, MbrDlvspPsnlInfoUdtSectCd.DLVSP_ADDRSE.toString()				// 배송지 수취인
									, MbrDlvspPsnlInfoUdtSectCd.DLVSP_TEL_NO.toString()				// 배송지 전화 번호
									, MbrDlvspPsnlInfoUdtSectCd.DLVSP_MOBIL_NO.toString()			// 배송지 휴대전화 번호
				};
				memberPersonalInfoCommandService.insertPersonalInfoForMbrDlvsp(mdParam, mpim, codeArr, isReg);
			}

        	// step 2. 대표배송지 설정값 확인
        	if(StringService.equalsIgnoreCase(mdParam.getBaseDlvspYn(), "1") || StringService.equalsIgnoreCase(mdParam.getBaseDlvspYn(), MemberEnum.YES.toString())){
        		mdParam.setBaseDlvspYn(MemberEnum.YES.toString());
        	} else {
        		mdParam.setBaseDlvspYn(MemberEnum.NO.toString());
        	}

			//동일 회원배송지 존재여부 체크
			//존재하면 수정, 없으면 수정
			mdParam.setDlvAdbukTurn(memberOrderSelectService.getMbrDeliveryAdbukTurn(mdParam));


			// step 3. 추가 배송지가 대표배송지인 경우에 기존 대표배송지 설정 제거
        	if(StringService.equalsIgnoreCase(mdParam.getBaseDlvspYn(), MemberEnum.YES.toString())){
        		memberOrderCommandService.updateBaseDeliveryLocationRevomeBy(mdParam, loginId);
        	}
        	
        	// step 3. 배송지 저장 
        	memberOrderCommandService.mergeDeliveryLocationBy(mdParam, loginId);
    	}


	}

	/**
	 * 배송지 목록 삭제.
	 */
	@Override
	public void removeDeliveryLocationListBy(SystemPK systemPK, List<MemberBoDTO> listDTO, String loginId)  {
		MemberBoDTO mDTO = new MemberBoDTO();
    	MbrDlvsp mdParam = new MbrDlvsp();
    	Iterator<MemberBoDTO> iterator = listDTO.iterator();
    	
    	// 개인정보변경이력 SEQ 생성
    	MbrPsnlInfoModHist mpim = null;
    	
    	// 배송지 다건 삭제
    	while(iterator.hasNext()) {
    		mDTO = iterator.next();
    		mdParam = new MbrDlvsp();
    		mdParam.setMbrNo(mDTO.getMbrDlvsp().getMbrNo());
    		mdParam.setDlvAdbukTurn(mDTO.getMbrDlvsp().getDlvAdbukTurn());
    		boolean isReg = mdParam.getDlvAdbukTurn() == null ? true : false;
    		
    		// step 1. 개인정보변경이력 등록
    		if(mpim == null){
    			mpim = memberPersonalInfoCommandService.setMbrPsnlInfoModHist(mdParam.getMbrNo(), "", loginId, false);
    		}
    		String[] codeArr = {
    							  MbrDlvspPsnlInfoUdtSectCd.DLVSP_ADDR.toString()				// 배송지 주소
    							, MbrDlvspPsnlInfoUdtSectCd.DLVSP_ADDRSE.toString()				// 배송지 수취인
    							, MbrDlvspPsnlInfoUdtSectCd.DLVSP_TEL_NO.toString()				// 배송지 전화 번호
    							, MbrDlvspPsnlInfoUdtSectCd.DLVSP_MOBIL_NO.toString()			// 배송지 휴대전화 번호
    			   			   };
    		memberPersonalInfoCommandService.insertPersonalInfoForMbrDlvsp(mdParam, mpim, codeArr, isReg);    
        	
        	// step 2. 대표배송지 설정값 확인 - 대표배송지는 삭제 불가능
        	if(StringService.equalsIgnoreCase(mdParam.getBaseDlvspYn(), "1") || StringService.equalsIgnoreCase(mdParam.getBaseDlvspYn(), MemberEnum.YES.toString())){
        		continue;
        	}

        	// step 3. 배송지 삭제
        	memberOrderCommandService.deleteDeliveryLocationBy(mdParam, loginId);
    	}
	}

	/**
	 * 매장 구매 내역
	 */
	@Override
	public OrderOfflinePurchaseSDO getOfflinePurchaseList(OrderOfflinePurchaseSDO orderOfflinePurchaseSDO, SystemPK systemPK)  {
		OrderOfflinePurchaseSDO result = new OrderOfflinePurchaseSDO();
		
		try {
			
			AdapterHeader header = new AdapterHeader();
			header.setRequestId(interfaceApiCommon.getRequestId());
			header.setLangCd(systemPK.getLang());
			header.setMallId(systemPK.getMall());
			header.setDvcCd(systemPK.getDevice());
			
			String jsonResult = orderAdapter.offlinePurchaseList(orderOfflinePurchaseSDO, header);
			result = JsonService.unmarshalling(jsonResult, OrderOfflinePurchaseSDO.class);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Map<String, String> refundSendCheck(MemberFoDTO dto)  {
		return memberOrderSelectService.refundSendCheck(dto);
	}


	/**
	 * AdapterHeader 값 설정
	 * @return
	 */
	private AdapterHeader setAdapterHeader(){
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId("DXM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");
		adapterHeader.setLocale("Input locale code.");
		adapterHeader.setChannel("Input channel.");
		adapterHeader.setUserId("Input security user id.");
		adapterHeader.setPassword("Input security uesr password.");

		return adapterHeader;
	}

	/**
	 * 회원 주문 목록 조회.
	 */
	@Override
	public Page<OrderBoResult> getOrderListForMember(SystemPK systemPK, OrderBoDTO orderDTO, PageParam pageParam, String loginId)  {
		try {
			return orderOthersMemberService.selectOrderListForMember(systemPK, orderDTO, pageParam);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 회원 주문 엑셀 조회.
	 */
	@Override
	public List<Map<String, Object>> getOrderExcelForMember(SystemPK systemPK, OrderBoDTO orderDTO, String loginId) {
		return orderOthersMemberService.selectOrderExcelForMember(systemPK, orderDTO);
	}


	/**
	 * 회원 환불 계좌 조회.
	 */
	@Override
	public List<MbrRfdBnkAcct> getRefundAccountList(SystemPK systemPK, MbrRfdBnkAcct mbrRfdBnkAcct, String loginId)  {
		mbrRfdBnkAcct.setLang(systemPK.getLang());
		return memberOrderSelectService.selectMemberRefundAccountList(mbrRfdBnkAcct, loginId);
	}

	/**
	 * 회원 환불 목록 조회.
	 */
	@Override
	public Page<CsoOthersMemberResult> getRefundListForMember(SystemPK systemPK, Mbr mbr, PageParam pageParam)  {
		try {
			return othersMemberService.selectRefundListForMember(systemPK, mbr, pageParam);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 회원 환불 엑셀 조회.
	 */
	@Override
	public List<Map<String, Object>> getRefundExcelForMember(SystemPK systemPK, Mbr mbr, String loginId) {
		return othersMemberService.selectRefundExcelForMember(systemPK, mbr);
	}


	/**
	 * 회원 배송지 조회.
	 */
	@Override
	public List<MemberBoResult> getDeliveryLocationList(SystemPK systemPK, MbrDlvsp mbrDlvsp, String loginId)  {

		//ncp 3차 ********************************************************************************************
		if(StringService.isEmpty(mbrDlvsp.getLang())){
			mbrDlvsp.setLang(systemPK.getLang());
		}
		//ncp 3차 ********************************************************************************************

		return memberOrderSelectService.selectDeliveryLocationList(mbrDlvsp);

	}

	/**
	 * 상품평 노출 여부 변경 처리
	 */
	@Override
	public void modifyGoodsReviewForNtceYn(SystemPK systemPK, List<GodGodEvl> ggeList, String loginId)  {
		GodGodEvl gge = null;
		Iterator<GodGodEvl> iterator = ggeList.iterator();

		// 상품평 다건 비노출 처리
		while(iterator.hasNext()) {
			gge = iterator.next();
			gge.setUdterId(loginId);

			// TODO	상품재작업필요 : goodsContentCommandService.updateGoodsReviewForNtceYn(gge);
		}
	}

	/**
	 * 상품문의 삭제 여부 변경 처리
	 */
	@Override
	public void modifyGoodsInquiryForDeleteYn(SystemPK systemPK, List<CsoGodInq> cgiList, String loginId)  {
		CsoGodInq cgi = null;
		Iterator<CsoGodInq> iterator = cgiList.iterator();

		// 상품문의 다건 삭제 처리
		while(iterator.hasNext()) {
			cgi = iterator.next();
			cgi.setUdterId(loginId);

			csoOthersMemberService.updateGoodsInquiryForDeleteYn(cgi);
		}
	}


	@Override
	public List<MysizeDTO> selectMyBasicSize(String mbrNo)  {
		return memberActivitySelectService.selectMyBasicSize(mbrNo);
	}

	/**
	 * 회원 이벤트 응모 내역 조회.
	 */
	@Override
	public Page<EventOthersMemberResult> getEventApplyListForMember(SystemPK systemPK, EvtApplcn ea, PageParam pageParam, String loginId)  {
		try {
			return eventOthersMemberService.selectEventApplyListForMember(systemPK, ea, pageParam);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 회원 이벤트 응모내역 엑셀 조회.
	 */
	@Override
	public List<Map<String, Object>> getEventApplyExcelForMember(SystemPK systemPK, EvtApplcn ea, String loginId) {
		return eventOthersMemberService.selectEventApplyExcelForMember(systemPK, ea);
	}

	/**
	 * 회원 상품평 목록 조회.
	 */
	@Override
	public Page<GoodsReviewResult> getGoodsReviewListForMember(SystemPK systemPK, GodGodEvl gge, PageParam pageParam, String loginId)  {
		return goodsContentService.selectGoodsReviewListForMember(systemPK, gge, pageParam);
	}

	/**
	 * 회원 상품평 엑셀 조회.
	 */
	@Override
	public List<Map<String, Object>> getGoodsReviewExcelForMember(SystemPK systemPK, GodGodEvl gge, String loginId) {
		return goodsContentService.selectReviewListForExcel(systemPK, gge);
	}

	/**
	 * 회원 상품문의 목록 조회.
	 */
	@Override
	public Page<CsoOthersMemberResult> getGoodsInquiryListForMember(SystemPK systemPK, CsoGodInq cgi, PageParam pageParam, String loginId)  {
		try {
			return othersMemberService.selectGoodsInquiryListForMember(systemPK, cgi, pageParam);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 회원 상품문의 엑셀 조회.
	 */
	@Override
	public List<Map<String, Object>> getGoodsInquiryExcelForMember(SystemPK systemPK, CsoGodInq cgi, String loginId) {
		return othersMemberService.selectGoodsInquiryExcelForMember(systemPK, cgi);
	}

	/**
	 * 회원 일대일문의 목록 조회.
	 */
	@Override
	public Page<CsoOthersMemberResult> getMtmInquiryListForMember(SystemPK systemPK, CsoMtmInq cmi, PageParam pageParam, String loginId)  {
		try {
			return othersMemberService.selectMtmInquiryListForMember(systemPK, cmi, pageParam);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 회원 일대일문의 엑셀 조회.
	 */
	@Override
	public List<Map<String, Object>> getMtmInquiryExcelForMember(SystemPK systemPK, CsoMtmInq cmi, String loginId) {
		return othersMemberService.selectMtmInquiryExcelForMember(systemPK, cmi);
	}

	/**
	 * 회원 일대일문의 주문상품 목록 조회.
	 */
	@Override
	public List<OrdGod> getMtmInquiryOrdGodList(CsoMtmInqOrdGod cmiog) {
		return othersMemberService.selectMtmInquiryOrdGodList(cmiog);
	}

	/**
	 * 전화번호 설정(국가번호 포함)
	 * @param phoneNo
	 * @return
	 */
	private List<String> makeGlobalPhoneNo(String phoneNo){
		
		String checkNo = StringService.substring(StringService.removeHyphen(StringService.nvl(phoneNo, "")), 0, 13);
		String regEx = "(\\d{0})(\\d{0})(\\d{0})(\\d{0})";
		String phoneForm = "$1-$2-$3-$4";
		if (checkNo.length() < 9){
			regEx = "(\\d{0})(\\d{0})(\\d{0,4})(\\d{4})";
		} else if (checkNo.length() > 11){
			regEx = "(\\d{2})(\\d{2,3})(\\d{3,4})(\\d{4})";
		} else if (StringService.substring(checkNo, 0, 2).equals("02")){
			regEx = "(\\d{0})(\\d{2})(\\d{3,4})(\\d{4})";
		} else {
			regEx = "(\\d{0})(\\d{2,3})(\\d{3,4})(\\d{4})";
		}
		
		String[] orgArr =  StringService.split(checkNo.replaceAll(regEx, phoneForm), "-");
		String[] spNo = phoneNo.split("-");
		if(spNo.length == 3 || spNo.length == 4){
			orgArr = spNo;
		}
		
		List<String> returnList = new ArrayList<String>();
		
		for(int i = 0; i < 4-orgArr.length; i++){
			returnList.add("");
		}
		int aIndex = 0;
		for(int i = returnList.size(); i < 4; i++){
			returnList.add(orgArr[aIndex++]);
		}		
		return returnList;
	}

}
