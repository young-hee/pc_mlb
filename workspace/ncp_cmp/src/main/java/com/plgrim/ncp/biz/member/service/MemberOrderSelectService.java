package com.plgrim.ncp.biz.member.service;

import com.cvs.dcaIche.util.utilCommon;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRfdBnkAcct;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.biz.member.bank.DCATranCheck;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;
import com.plgrim.ncp.biz.member.repository.MemberOrderSelectRepository;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.member.result.MypageMtmFoResult;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.interfaces.enums.InterfaceAdapterEnum;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
 
/**
 * 회원주문정보 select service
 */
@Service
@Slf4j
public class MemberOrderSelectService extends AbstractService {

	
	@Autowired
	InterfaceApiCommon interfaceApiCommon;
	
	@Autowired
	JsonService jsonService;
	

	@Autowired
	private MemberOrderSelectRepository memberOrderSelectRepository;
	
	@Autowired
	private MemberBenefitSelectService memberBenefitSelectService;
	
	
	/** 1:1문의 주문리스트	*/
	public List<MypageMtmFoResult> selectOrdGodList(MypageMtmFoDTO mypageMtmFoDTO) {
	    
		List<MypageMtmFoResult> ordList = memberOrderSelectRepository.selectOrdList(mypageMtmFoDTO);
		
		for (MypageMtmFoResult mypageMtmFoResult : ordList) {
			
			mypageMtmFoDTO.setOrd(mypageMtmFoResult.getOrd());
			List<MypageMtmFoResult> godList = memberOrderSelectRepository.selectGodList(mypageMtmFoDTO);
			mypageMtmFoResult.setOrdGodList(godList);
		}
		
		return ordList;
    }
	
	/** 1:1문의 주문상품리스트 페이징	*/
	public Page<MypageMtmFoResult> selectInquiryOrdGodList(MypageMtmFoDTO mypageMtmFoDTO,PageParam pageParam) {
	    
		Page<MypageMtmFoResult> ordList = memberOrderSelectRepository.selectInquiryOrdGodList(mypageMtmFoDTO,pageParam);
		
//		for (MypageMtmFoResult mypageMtmFoResult : ordList) {			
//			mypageMtmFoDTO.setOrd(mypageMtmFoResult.getOrd());
//			List<MypageMtmFoResult> godList = memberOrderSelectRepository.selectGodList(mypageMtmFoDTO);
//			mypageMtmFoResult.setOrdGodList(godList);
//		}
		
		return ordList;
    }
	
	public Page<MypageMtmFoResult> selectInquiryOrdGodMOList(MypageMtmFoDTO mypageMtmFoDTO,PageParam pageParam){
		
		Page<MypageMtmFoResult> ordList = memberOrderSelectRepository.selectInquiryOrdGodMOList(mypageMtmFoDTO,pageParam);
		return ordList;
	}
	
	

	
	/**
	 * [메서드 설명].
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dto [설명]
	 * @return List [설명]
	 * @since 2015. 4. 27
	 */
	public String selectHanjinTracking(MypageFoDTO mypageFoDTO) {
		String result = "";

//
//		HanjinSDO hanjinSDO = new HanjinSDO();
//		// 송장번호를 세팅한다.
//		hanjinSDO.setWblnum(mypageFoDTO.getWaybillNumber());
//		// 요청 아이디를 세팅한다.
//		hanjinSDO.setCallerId(InterfaceAdapterEnum.INTERFACE_ADAPTER_SELECT_HANJIN_TRACKING.toString());
//
//		// Adapter Header 를 세팅한다.
//		AdapterHeader adapterHeader = new AdapterHeader();
//		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
//		adapterHeader.setMallId("DXM");
//		adapterHeader.setLangCd("KOR");
//		adapterHeader.setDvcCd("WEB");
//
//		try {
//			result = hanjinAdapter.getHanjinTracking(hanjinSDO, adapterHeader);
//		log.info("> selectHanjinTracking : {}", result);
//        } catch (Exception e) {
//    	log.info("> selectHanjinTracking Exception : {}", e.getMessage());
//	        log.error("",e);
//        }
	    return result;
    }	
	
	/** 매장 상세 내역	 */
//	public List<BpSalesDisplaySDO> selectBpSalesDisplay(BpSalesDisplaySDO bpSalesDisplaySDO, AdapterHeader adapterHeader) {
//
//			List<BpSalesDisplaySDO> purchasedList = new ArrayList<BpSalesDisplaySDO>();
//		    return purchasedList;
//    }
//
	/**
	 * 상품명 조회
	 *
	 * @param mbrSavMnyHist [설명]
	 * @return List [설명]
	 * @ the exception
	 * @since 2015. 5. 7
	 */
    public String  selectErpGoodNm(String matNo)  {
    	MypageFoDTO dto = new MypageFoDTO();
    	dto.setMatNo(matNo);
    	return memberOrderSelectRepository.selectErpGoodNm(dto);
	}
    
    /**
     * 계좌확인 API
     */
	public Map<String, String> refundSendCheck(MemberFoDTO dto)  {
		Map<String, String> returnMap = new HashMap<String, String>();
		  //-----------------------------------------
		  //  전문 길이를 맞추기 위한 변수
		  //-----------------------------------------
		  String  filler         = utilCommon.FILLER;
		  int     filler_option  = utilCommon.FILLER_OPTION;
		  String  zero           = utilCommon.ZERP;
		  int     zero_option    = utilCommon.ZERP_OPTION;
		  StringBuffer resp 	 = new StringBuffer();

		  /*-----------------------------------------
		   *  전문 길이에 맞게 파라메터 받기
		   *  메소드 : getFillStr(String parameter, String str, int length, int option)
		   *  설  명 : parameter : 변경 할 데이터
		   *           str       : filler ==> 공백문자
		   *                       zero   ==> 0
		   *           length    : 전문 길이
		   *           option    : filler_option ==> parameter의 뒤에서 부터 채운다.
		   *                       zero_option   ==> parameter의 앞에서 부터 채운다.
		   *-----------------------------------------*/

		  String outBankCode  = utilCommon.getFillStr("020"                  								, filler,  2, filler_option);	//출금은행코드 우리은행 020
		  String inBankCode   = utilCommon.getFillStr(dto.getAccount_bank()                 	, filler,  2, filler_option);	//입금은행코드
		  String inAccount    = utilCommon.getFillStr(dto.getAccount_number()               		, filler, 16, filler_option);	//입금계자번호
		  String sendAmount   = utilCommon.getFillStr("10"                   								,   zero, 13, zero_option);		//입금금액 10원
		  String senderName;
		  try {
				senderName = utilCommon.Uni2Ksc(utilCommon.getFillStr(dto.getAccount_holder()	, filler,  5, filler_option));
			} catch (UnsupportedEncodingException e1) {
				throw new RuntimeException(e1);
			}	//입금주명
		  
		  if(getConfigService().getProperty("ncp_base.env").matches("dev|local")){
				// 인증이 안되서 개발은 할수없고 하드코딩하면 안되니 로컬 개발은 인증통과하게 설정
				returnMap.put("respCode", "0000");
				returnMap.put("errMsg", "");
				returnMap.put("receiverName", senderName);
				return returnMap;
		  }

		  //-------------------------------------
		  //  properties 파일 경로 (full)
		  //-------------------------------------
		  String prop = getConfigService().getProperty("ncp_base.external.config")+"/mall.properties";
//		  String prop = "/cont02/ocpp/mall/fashion/Refund/configs/mall.properties"; //운영 주소
//		  String prop = getConfigService().getProperty("ncp_base.external.config")+"\\mall.properties";
		  log.debug("Prop : " + prop);
		  //-------------------------------------
		  //  Hashtable 에 값 넣기
		  //-------------------------------------
		  Hashtable  htArg  = new Hashtable();

		  htArg.put( "outBankCode",   outBankCode );
		  htArg.put( "inBankCode",    inBankCode  );
		  htArg.put( "inAccount",     inAccount   );
		  htArg.put( "sendAmount",    sendAmount  );
		  htArg.put( "senderName",    senderName  );

		  //-------------------------------------
		  //  전문 송수신
		  //-------------------------------------
		  DCATranCheck dca = null;

		  try
		  {
		      dca  = new DCATranCheck();
		      dca.doTransService(htArg, prop);
		  }
		  catch(Exception e)
		  {}

		  //-------------------------------------
		  //  전문 수신 후 받은 값
		  //-------------------------------------
		  String respCode     = utilCommon.getNullToBlank((String)htArg.get( "respCode" ));
		  String errMsg       = utilCommon.getNullToBlank((String)htArg.get( "errMsg" ));

		  outBankCode = utilCommon.getNullToBlank((String)htArg.get( "outBankCode" ));
		  inBankCode  = utilCommon.getNullToBlank((String)htArg.get( "inBankCode" ));
		  inAccount   = utilCommon.getNullToBlank((String)htArg.get( "inAccount"));
		  sendAmount  = utilCommon.getNullToBlank((String)htArg.get( "sendAmount" ));
		  senderName  = utilCommon.getNullToBlank((String)htArg.get( "senderName" ));

		  String receiverName = utilCommon.getNullToBlank((String)htArg.get( "receiverName" ));
		  String plusOrMinus  = utilCommon.getNullToBlank((String)htArg.get( "plusOrMinus" ));
		  String balance      = utilCommon.getNullToBlank((String)htArg.get( "balance" ));

		  if(respCode.equals("0000")){
			/*if(!(receiverName.trim().equals(dto.getAccount_holder()))){
				respCode = "0589";
				//errMsg = "예금주 성명이 다릅니다.\n예금주("+receiverName.trim()+") 성명을 확인해주십시오.";
				errMsg = "예금주 성명이 다릅니다.\n예금주 성명을 확인해주십시오.\n[Error code : "+respCode+"]";
			}*/
		  }else if(respCode.equals("0204")||respCode.equals("0703")||respCode.equals("1486")||respCode.equals("2044")||respCode.equals("2075")
					 ||respCode.equals("2076")||respCode.equals("2084")||respCode.equals("A005")||respCode.equals("A067")||respCode.equals("A098")
					 ||respCode.equals("A920")||respCode.equals("O059")
				   ){
				
			  errMsg = "해지계좌입니다.\n다른 계좌를 입력해 주십시오.\n[Error code : "+respCode+"]";
				  
		  }else {
			  errMsg = "알수없는 계좌입니다.\n담당자에게 문의하세요.\n[Error code : "+respCode+"]";
		  }
		  returnMap.put("respCode", respCode);
		  returnMap.put("errMsg", errMsg);
		  returnMap.put("receiverName", receiverName);
		 
		  return returnMap;
		  
		  
	}
	
	/**
	 * 환불 계좌 목록 조회.
	 */
	public List<MbrRfdBnkAcct> selectMemberRefundAccountList(MbrRfdBnkAcct mbrRfdBnkAcct, String loginId)  {
		
		// step 1. 환불 계좌 조회
		List<MbrRfdBnkAcct> lists = memberOrderSelectRepository.selectMemberRefundAccountList(mbrRfdBnkAcct);
		
		// TODO : 기존 복호화 하는곳의 마스킹 처리 제거됨. 필요시 마스킹 추가.. 
		// step 2. 환불 계좌 복호화 및 마스킹 처리
//		String encAcct = "";
//		String viewAcct = "";
//		boolean isMasking = StringService.equalsIgnoreCase(MemberEnum.YES.toString(), mbrRfdBnkAcct.getMaskingYn()) ? true : false;
//		RefundAccountUtil rau = new RefundAccountUtil();
//		for(MbrRfdBnkAcct mrba : lists){
//			encAcct = mrba.getRfdBnkAcctNo();
//			try{
//				viewAcct = rau.getDecryptRefundAccount(encAcct, isMasking, loginId, null);	// 복호화  // TODO 마스킹 처리 필요
//				if(StringService.isEmpty(viewAcct)) continue;
//			} catch (Exception e){
//				if(log.isInfoEnabled()) {log.info("> getDecryptRefundAccount FAIL : {}",  e.getMessage());}
//				continue;
//			}
//			mrba.setRfdBnkAcctNo(viewAcct);
//		}
		return lists;
	}
	
	/**
	 * 픽업매장 -> 일반배송 전환 가능여부 조회
	 * 
	 * @param mypageOrderInfoDTO
	 * @return
	 * @
	 */
	public MypageOrderInfoDTO getPickupDeliveryChangeYn(MypageOrderInfoDTO mypageOrderInfoDTO)  {
		return memberOrderSelectRepository.getPickupDeliveryChangeYn( mypageOrderInfoDTO);
	}

	/**
	 * 픽업매장 -> 일반배송 전환 가능여부 조회 ( 옵션 판매상태 기준 : #47710 픽업판매중인 상품이 있는 경우 일반배송으로 전환 불가 )
	 */
	public String getPickupDeliveryChangeYnByItmStat(String ordNo) {
		return memberOrderSelectRepository.getPickupDeliveryChangeYnByItmStat(ordNo);
	}

	/**
	 * 배송지 목록 조회.
	 */
	public List<MemberBoResult> selectDeliveryLocationList(MbrDlvsp mbrDlvsp) {
    	return  memberOrderSelectRepository.selectDeliveryLocationList(mbrDlvsp);
	}
	
	/**
	 * 배송지 목록 조회 Page.
	 */
	public Page<MemberBoResult> selectDeliveryLocationPageList(MbrDlvsp mbrDlvsp, PageParam pageParam)  {
		return memberOrderSelectRepository.selectDeliveryLocationPageList(mbrDlvsp, pageParam);
	}
	
	/**
	 * 회원배송지순번조회
	 */
    public Integer getMbrDeliveryAdbukTurn(MbrDlvsp mbrDlvsp) {
    	return memberOrderSelectRepository.getMbrDeliveryAdbukTurn(mbrDlvsp);
	}
    /**
	 * 1:1주문상품 리스트MO  8월20일 이전 내용 확인
	 */
    public boolean selectInquiryOrdMOListBefor0822(MypageMtmFoDTO mypageMtmFoDTO) {
	   int test = memberOrderSelectRepository.selectInquiryOrdMOListBefor0822(mypageMtmFoDTO);
	   if(test>0){
		   return true;// 데이터가 있음
	   }else{
		   return false;//데이터가 없음
	   }
	}
    
    
}
