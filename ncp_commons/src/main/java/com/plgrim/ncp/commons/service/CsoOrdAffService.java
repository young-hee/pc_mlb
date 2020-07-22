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
 * @since       2015. 6. 10       
 */
package com.plgrim.ncp.commons.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoOrgztOrdAffInq;
import com.plgrim.ncp.commons.data.CsoOrdAffDTO;
import com.plgrim.ncp.commons.repository.CsoOrdAffRepository;
import com.plgrim.ncp.commons.result.CsoOrdAffResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 단체주문 / 제휴문의관리 Service
 * @author ed
 *
 */
@Service
public class CsoOrdAffService extends AbstractService {

	@Autowired
    CsoOrdAffRepository csoOrdAffRepository; // 단체주문 / 제휴문의관리 DAO
	
	@Autowired
	InterfaceApiCommon interfaceApiCommon;

	/**
	 * 단체주문 / 제휴문의 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<CsoOrdAffResult> selectCsoOrdAffList( CsoOrdAffDTO paramData) throws Exception {
		return csoOrdAffRepository.selectCsoOrdAffList(paramData); 
	}
	
	/**
	 * 단체주문 / 제휴문의 목록 조회 개수.
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectCsoOrdAffListCount( CsoOrdAffDTO paramData) throws Exception {
		return csoOrdAffRepository.selectCsoOrdAffListCount(paramData); 
	}
	
	/**
	 * 단체주문 / 제휴문의 목록 조회 엑셀.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectCsoOrdAffListExcel( CsoOrdAffDTO paramData) throws Exception {
		return csoOrdAffRepository.selectCsoOrdAffListExcel(paramData); 
	}
	
	/**
	 * 단체주문 / 제휴문의 답변 상태 수정.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void updateCsoOrdAffStat(CsoOrdAffDTO paramData) {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		CsoOrgztOrdAffInq csoOrgztOrdAffInq = new CsoOrgztOrdAffInq(); 
		
		csoOrgztOrdAffInq.setOrgztOrdAffInqSn(paramData.getSearch().getOrgztOrdAffInqSn()); // 일련번호
		csoOrgztOrdAffInq.setAnsStatCd("ANS_WAIT"); // 답변상태(답변대기)
		csoOrgztOrdAffInq.setUdterId(loginId); // 수정자 
		
		csoOrdAffRepository.updateCsoOrdAffStat(csoOrgztOrdAffInq); // 문의테이블 답변상태 수정
    }
	
	/**
	 * 단체주문 / 제휴문의 등록.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void insertCsoOrdAff( CsoOrdAffDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		
		if(paramData != null) {
			// 문의 테이블 데이터 설정
			paramData.getFormData().getCsoOrgztOrdAffInq().setAnsStatCd("ANS_COMPT"); // 답변상태 
			paramData.getFormData().getCsoOrgztOrdAffInq().setUdterId(loginId); // 수정자
			
			csoOrdAffRepository.updateCsoOrdAffStat(paramData.getFormData().getCsoOrgztOrdAffInq()); // 문의테이블 수정
			
			// 답변 테이블 데이터 설정
			paramData.getFormData().getCsoOrgztOrdAffInqAns().setOrgztOrdAffInqSn(paramData.getFormData().getCsoOrgztOrdAffInq().getOrgztOrdAffInqSn()); // 일련번호
			paramData.getFormData().getCsoOrgztOrdAffInqAns().setDetailAnsStatCd("ANS_COMPT");
			paramData.getFormData().getCsoOrgztOrdAffInqAns().setAnsAdminId(loginId); // 답변관리자
			paramData.getFormData().getCsoOrgztOrdAffInqAns().setRegtrId(loginId); // 등록자
			paramData.getFormData().getCsoOrgztOrdAffInqAns().setUdterId(loginId); // 수정자
			
			csoOrdAffRepository.insertCsoOrdAffAns(paramData.getFormData().getCsoOrgztOrdAffInqAns()); // 답변테이블 등록
			
			// SMS / EMAIL 발송 정보 가져옴
			paramData.getSearch().setOrgztOrdAffInqSn(paramData.getFormData().getCsoOrgztOrdAffInq().getOrgztOrdAffInqSn());
			paramData.getSearch().setPceodnlOrdAffInqAnsTurn(paramData.getFormData().getCsoOrgztOrdAffInqAns().getPceodnlOrdAffInqAnsTurn());
			paramData.getSearch().setMaskingYn("N"); // 마스킹 여부
			
			CsoOrdAffResult result = csoOrdAffRepository.selectCsoOrdAffDetail(paramData);
			
			if(result != null) {
				if("Y".equals(result.getCsoOrgztOrdAffInq().getInqerSmsRecptnYn())) {
					// Adapter Header 를 세팅한다.
					AdapterHeader adapterHeader = new AdapterHeader();
					adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
					adapterHeader.setMallId("DXM");
					adapterHeader.setLangCd("KOR");
					adapterHeader.setDvcCd("PC");

					String mobilNo = result.getCsoOrgztOrdAffInq().getInqerMobilAreaNo()
							+ result.getCsoOrgztOrdAffInq().getInqerMobilTlofNo() + result.getCsoOrgztOrdAffInq().getInqerMobilTlofWthnNo();

					ArrayList<String> params = new ArrayList<>();
					params.add(0, result.getCsoOrgztOrdAffInq().getInqerNm());	// 고객명

					String mallId = "DXM";
					String callerId = CsoOrdAffService.class.getName() + ".insertCsoOrdAff";
					String msgKey = "DXM_CS_02";
					String tranId = "NMBR";

					// TODO 문자 솔루션 적용
//					HumusonSDO humusonSDO = humusonCommonService.getAlimTalkData(tranId, mobilNo, mallId, callerId, msgKey, params);
//
//					humusonCommonService.sendAlimTalk(humusonSDO, adapterHeader);
				}
				
				if("Y".equals(result.getCsoOrgztOrdAffInq().getInqerEmailRecptnYn())) {
					// Adapter Header 를 세팅한다.
					AdapterHeader adapterHeader = new AdapterHeader();
					adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
					adapterHeader.setMallId("DXM");
					adapterHeader.setLangCd("KOR");
					adapterHeader.setDvcCd("PC");

					// TODO 이메일 솔루션 적용
//					// EMAIL 발송
//					List<EmailSDO> emailList = new ArrayList<EmailSDO>();
//
//					EmailSDO item = new EmailSDO();
//					String title = getDateService().parseString(result.getCsoOrgztOrdAffInq().getRegDt(), "yyyy년 MM월 dd일 HH시 mm분")
//							+ "에 문의하신 내용에 대한 답변입니다.";
//
//					item.setAutocode("");
//					item.setUserId(loginId);
//					item.setAutotype("O10"); // TODO : 템플릿 타입 수정요망
//					item.setEmail(result.getCsoOrgztOrdAffInq().getInqerEmail());
//					item.setName(result.getCsoOrgztOrdAffInq().getInqerNm());
//					item.setInsertdate("");
//					item.setSendtime("");
//					item.setSendyn("N");
//					item.setOpentime("");
//					item.setSenttime("");
//					item.setCmpncode("");
//					item.setFromaddress("webmaster@plgrimshop.com"); // TODO : 발신자 이메일 수정요망
//					item.setFromname("플그림"); // TODO : 발신자명 수정요망
//					item.setTitle(title);
//					item.setTag1(result.getCsoOrgztOrdAffInq().getOrgztOrdAffInqTpCd());	// 문의 유형
//					item.setTag2(getDateService().parseString(result.getCsoOrgztOrdAffInq().getRegDt(), "yyyy. MM. dd"));	// 문의 시간
//					item.setTag3("");
//					item.setTag4("");
//					item.setTag5(new SimpleDateFormat("yyyy.MM.dd").format(new Date())); // 답변 시간
//					item.setTag6("");
//					item.setTag7("");
//					item.setTag8("");
//					item.setTag9("");
//					item.setTag10("");
//					item.setTag11(result.getCsoOrgztOrdAffInq().getInqCont().replace("\n","<br />"));	// 문의 내용(data type : clob)
//					item.setTag12(result.getCsoOrgztOrdAffInqAns().getAnsCont().replace("\n", "<br />"));	// 답변 내용(data type : clob)
//					item.setCallerId(loginId);
//					item.setAdminId(loginId);
//
//					emailList.add(item); // 수신자 추가
//
//					emailAdapter.sendEmail(emailList, adapterHeader); // 발송
				}
			}
		}
	}
	
	/**
	 * 단체주문 / 제휴문의 상세 조회.
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public CsoOrdAffResult selectCsoOrdAffDetail( CsoOrdAffDTO paramData) throws Exception {

		if(BOSecurityUtil.getAccessSiteId().equals("BO")){
			if(StringUtils.isEmpty(paramData.getSearch().getMaskingYn())) {
				paramData.getSearch().setMaskingYn("Y");
			}
		}

		return csoOrdAffRepository.selectCsoOrdAffDetail(paramData); 
	}
}
