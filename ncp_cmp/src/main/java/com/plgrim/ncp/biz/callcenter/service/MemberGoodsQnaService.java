/* Copyright (c) 2013 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 *                       
 */
package com.plgrim.ncp.biz.callcenter.service;

import com.plgrim.ncp.base.entities.datasource1.cso.*;
import com.plgrim.ncp.base.enums.*;
import com.plgrim.ncp.biz.callcenter.data.FastUrlGridDTO;
import com.plgrim.ncp.biz.callcenter.data.MemberGoodsQnaGridDTO;
import com.plgrim.ncp.biz.callcenter.data.MemberGoodsQnaSearchDTO;
import com.plgrim.ncp.biz.callcenter.repository.CounselRepository;
import com.plgrim.ncp.biz.callcenter.result.DetailMemberGoodsQnaResult;
import com.plgrim.ncp.biz.callcenter.result.SelectCnsltDetailResult;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.page.PageParam;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.biz.callcenter.data.MemberGoodsQnaDTO;
import com.plgrim.ncp.biz.callcenter.repository.MemberGoodsQnaRepository;
import com.plgrim.ncp.biz.callcenter.result.MemberGoodsQnaResult;
import com.plgrim.ncp.biz.callcenter.result.MtmResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * CodeViewService Implementation
 * @author Park
 *
 */
@Service
@Transactional
public class MemberGoodsQnaService {

	@Autowired
	private MemberGoodsQnaRepository memberGoodsQnaRepository;

	@Autowired
	private CounselRepository counselRepository;
	
	@Autowired
	CounselService counselService;

	public void insertMemberGoodsQnaAns(MemberGoodsQnaDTO memberGoodsQnaDTO) throws Exception{
		
		long godInqSn = Long.valueOf(memberGoodsQnaDTO.getGodInqSn());

		// 상품 Q&A 상태 조회
		String mastsrAnsStatCd = memberGoodsQnaRepository.selectAnsStat(godInqSn);

		int godAnsTurn = 0;

		if (!CsoGodInqAnsEnum.detailAnsStatCd.ANS_COMPT.toString().equals(mastsrAnsStatCd)) {

			// 상품 Q&A 답변 등록
			memberGoodsQnaDTO.setGodInqSn(godInqSn);
			memberGoodsQnaDTO.setAnsAdminId(BOSecurityUtil.getLoginId());
			memberGoodsQnaDTO.setRegtrId(BOSecurityUtil.getLoginId());
			memberGoodsQnaDTO.setUdterId(BOSecurityUtil.getLoginId());

			if (memberGoodsQnaDTO.getCheckAnsWait() != null && memberGoodsQnaDTO.getCheckAnsWait().equals("Y")) {
				memberGoodsQnaDTO.setDetailAnsStatCd(CsoGodInqAnsEnum.detailAnsStatCd.ANS_WAIT.toString());
			} else if (CsoCnsltEnum.cnsltStat.TMPR_SAVE.toString().equals(memberGoodsQnaDTO.getCsoCnslt().getCnsltStatCd())) {
				// 임시 저장일 경우 답변 상태는 답변 전으로 설정한다
				memberGoodsQnaDTO.setDetailAnsStatCd(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_BF.toString());
			} else {
				memberGoodsQnaDTO.setDetailAnsStatCd(CsoGodInqAnsEnum.detailAnsStatCd.ANS_COMPT.toString());
			}

			godAnsTurn = memberGoodsQnaRepository.insertMemberGoodsQnaAnsWithGenTurn(memberGoodsQnaDTO);

			// 상품 Q&A 상담 마스터 테이블 답변 상태가 답변 전이고, 입력된 값이 답변 대기나 답변 완료일 때,
			// 상품 Q&A 상담 마스터 테이블 답변 상태가 답변 대기이고, 입력된 값이 답변 완료일때
			if (mastsrAnsStatCd.equals(CsoGodInqAnsEnum.detailAnsStatCd.ANS_BF.toString()) && memberGoodsQnaDTO.getDetailAnsStatCd().equals(CsoGodInqAnsEnum.detailAnsStatCd.ANS_WAIT.toString())
					|| mastsrAnsStatCd.equals(CsoGodInqAnsEnum.detailAnsStatCd.ANS_BF.toString()) && memberGoodsQnaDTO.getDetailAnsStatCd().equals(CsoGodInqAnsEnum.detailAnsStatCd.ANS_COMPT.toString())
					|| mastsrAnsStatCd.equals(CsoGodInqAnsEnum.detailAnsStatCd.ANS_WAIT.toString()) && memberGoodsQnaDTO.getDetailAnsStatCd().equals(CsoGodInqAnsEnum.detailAnsStatCd.ANS_COMPT.toString())) {

				// 상품 Q&A 답변 상태 업데이트
				CsoGodInq csoGodInq = new CsoGodInq();

				csoGodInq.setGodInqSn(godInqSn);
				csoGodInq.setAnsStatCd(memberGoodsQnaDTO.getDetailAnsStatCd());
				csoGodInq.setUdterId(BOSecurityUtil.getLoginId());
				memberGoodsQnaRepository.updateMemberGoodsQna(csoGodInq);
			}
		}


		// 상품 Q&A 조회
		MemberGoodsQnaResult memberGoodsQna = memberGoodsQnaRepository.selectMemberGoodsQna(godInqSn);


		//상담이 등록되어 있는지 안되어 있는지 체크
		String selectCnsltSn = memberGoodsQnaRepository.selectCounselCount(godInqSn);
		Long cnsltSn = null;

		if(selectCnsltSn != null){
			cnsltSn = Long.valueOf(selectCnsltSn);
		}else if(selectCnsltSn == null){
			// 상담 등록
			CsoCnslt csoCnslt = memberGoodsQnaDTO.getCsoCnslt();

			csoCnslt.setGodInqSn(godInqSn);
			csoCnslt.setCnsltReqstMbrNo(memberGoodsQna.getMbrNo());
			csoCnslt.setCstmrnm(memberGoodsQna.getInqCstmrNm());
			csoCnslt.setInqTpCd(memberGoodsQna.getInqTpCd());
			csoCnslt.setCstmrEmail(memberGoodsQna.getCstmrEmail());
			csoCnslt.setMallId(memberGoodsQna.getMallId());
			csoCnslt.setLangCd(memberGoodsQna.getLangCd());
			csoCnslt.setDvcCd(memberGoodsQna.getDvcCd());
			csoCnslt.setRegtrId(BOSecurityUtil.getLoginId());
			csoCnslt.setUdterId(BOSecurityUtil.getLoginId());


			cnsltSn = memberGoodsQnaRepository.insertCsoCnsltWithGenSn(csoCnslt);


			// 상담 주문 상품 등록
			CsoCnsltOrdGod csoCnsltOrdGod = new CsoCnsltOrdGod();
			csoCnsltOrdGod.setCnsltSn(cnsltSn);
			csoCnsltOrdGod.setGodNm(memberGoodsQna.getGodNm());
			csoCnsltOrdGod.setOrdGodSectCd(CsoCnsltOrdGodEnum.ordGodSect.GOD.toString());
			csoCnsltOrdGod.setGodNo(memberGoodsQna.getGodNo());

			memberGoodsQnaRepository.insertCsoCnsltOrdGodWithGenTurn(csoCnsltOrdGod);
		}

		
		// 상담 상세 등록
		CsoCnsltDetail csoCnsltDetail = memberGoodsQnaDTO.getCsoCnsltDetail();
		
		if(csoCnsltDetail ==null){
			csoCnsltDetail = new CsoCnsltDetail();
		}

		csoCnsltDetail.setCnsltSn(cnsltSn);
		csoCnsltDetail.setCnsltDetlTrnsmisRequstYn("N");
		csoCnsltDetail.setDeleteYn("N");
		csoCnsltDetail.setCnsltDetailStatCd(memberGoodsQnaDTO.getCsoCnslt().getCnsltStatCd());

		if(csoCnsltDetail.getCnsltDetailStatCd().equals(CsoCnsltDetailEnum.cnsltDetailStat.TMPR_SAVE.toString())){
			csoCnsltDetail.setCnsltDetailCont(csoCnsltDetail.getCnsltDetailCont());
		}else{
			if (memberGoodsQnaDTO.getAnsCont() != null && !"".equals(memberGoodsQnaDTO.getAnsCont())) {

				String cont = csoCnsltDetail.getCnsltDetailCont();
				if(StringService.isNotEmpty(cont)){
					cont.replaceAll("(\r\n|\n)", "<br />");
				}else{
					cont = "";
				}

				csoCnsltDetail.setCnsltDetailCont("답변 내용 : " + memberGoodsQnaDTO.getAnsCont() + " <br> -----------------------------------------------------------------------------------<br> 내용 : " + cont);
			}else{
				csoCnsltDetail.setCnsltDetailCont(csoCnsltDetail.getCnsltDetailCont().replaceAll("(\r\n|\n)", "<br />"));
			}
		}

		csoCnsltDetail.setGodInqSn(godInqSn);
		if (godAnsTurn > 0) {
			csoCnsltDetail.setGodAnsTurn(godAnsTurn);
		}
		csoCnsltDetail.setCnsltKndCd(memberGoodsQnaDTO.getCsoCnslt().getCnsltKndCd());
		csoCnsltDetail.setCstmrClmCd(memberGoodsQnaDTO.getCsoCnslt().getCstmrClmCd());
		csoCnsltDetail.setRegtrId(BOSecurityUtil.getLoginId());
		csoCnsltDetail.setUdterId(BOSecurityUtil.getLoginId());
		
		int cnsltDetailTurn = memberGoodsQnaRepository.insertCsoCnsltDetailWithGenTurn(csoCnsltDetail);

		//상담이력 상태 수정
		if (!CsoCnsltDetailEnum.cnsltDetailStat.TMPR_SAVE.toString().equals(csoCnsltDetail.getCnsltDetailStatCd())) {
		CsoCnslt csoCnslt = memberGoodsQnaDTO.getCsoCnslt();
		csoCnslt.setCnsltSn(csoCnsltDetail.getCnsltSn());
		csoCnslt.setCnsltStatCd(csoCnsltDetail.getCnsltDetailStatCd());
		csoCnslt.setUdterId(BOSecurityUtil.getLoginId());
		counselRepository.updateCounsel(csoCnslt);
		}
		
		//상담이 처리완료로 저장될 경우 임시저장 상태를 제외한 모든 상태를 처리 완료로 변경
		if (CsoCnsltDetailEnum.cnsltDetailStat.PROC_COMPT.toString().equals(csoCnsltDetail.getCnsltDetailStatCd())) {
			counselService.updateCounseldetailStatCompt(csoCnsltDetail);
		}
		

		// 상담 상세 업무 유형 등록
		CsoCnsltDetailJobTp csoCnsltDetailJobTp = new CsoCnsltDetailJobTp();
		
		if(memberGoodsQnaDTO.getCnsltJobTpCd() == null){
			String [] jobTpCd = {"INQ_ANS"};
			memberGoodsQnaDTO.setCnsltJobTpCd(jobTpCd);
		}

		for(int i = 0 ; i < memberGoodsQnaDTO.getCnsltJobTpCd().length; i++){
			
			csoCnsltDetailJobTp.setCnsltSn(cnsltSn);
			csoCnsltDetailJobTp.setCnsltDetailTurn(cnsltDetailTurn);
			csoCnsltDetailJobTp.setCnsltJobTpCd(memberGoodsQnaDTO.getCnsltJobTpCd()[i]);
			csoCnsltDetailJobTp.setRegtrId(BOSecurityUtil.getLoginId());
			csoCnsltDetailJobTp.setUdterId(BOSecurityUtil.getLoginId());
			
			memberGoodsQnaRepository.insertCsoCnsltDetailJopTp(csoCnsltDetailJobTp);
		}
		
		// 상담 상세 주문 상품
		CsoCnsltDetailOrdGod csoCnsltDetailOrdGod = new CsoCnsltDetailOrdGod();
		
		csoCnsltDetailOrdGod.setCnsltSn(cnsltSn);
		csoCnsltDetailOrdGod.setCnsltDetailTurn(cnsltDetailTurn);
		csoCnsltDetailOrdGod.setGodNm(memberGoodsQna.getGodNo());
		csoCnsltDetailOrdGod.setOrdGodSectCd(CsoCnsltDetailOrdGodEnum.ordGodSect.GOD.toString());
		csoCnsltDetailOrdGod.setGodNo(memberGoodsQna.getGodNo());

		memberGoodsQnaRepository.insertCsoCnsltDetailOrdGodWithGenTurn(csoCnsltDetailOrdGod);
		
		// 상담 상세 이력 등록
		CsoCnsltDetailHist csoCnsltDetailHist = new CsoCnsltDetailHist();
		csoCnsltDetailHist.setCnsltSn(cnsltSn);
		csoCnsltDetailHist.setCnsltDetailTurn(cnsltDetailTurn);
		csoCnsltDetailHist.setModInfoCd(CsoCnsltDetailHistEnum.modInfo.CNSLT_DETAIL_STAT_CD.toString());
		csoCnsltDetailHist.setModTpCd(CsoCnsltDetailHistEnum.modTp.REG.toString());

		memberGoodsQnaRepository.insertCsoCnsltDetailHist(csoCnsltDetailHist);
		
		csoCnsltDetailHist.setModInfoCd(CsoCnsltDetailHistEnum.modInfo.CNSLT_DETAIL_CONT.toString());
		csoCnsltDetailHist.setModTpCd(CsoCnsltDetailHistEnum.modTp.REG.toString());
		
		memberGoodsQnaRepository.insertCsoCnsltDetailHist(csoCnsltDetailHist);
		
		// 상담 이관 등록
		if("Y".equals(memberGoodsQnaDTO.getTransSaveYn())){
			CsoCnsltTrans csoCnsltTrans = memberGoodsQnaDTO.getCsoCnsltTrans();
			
			if(csoCnsltTrans.getTransTgtCd().equals(CounselTransEnum.transTgt.CNSLR_TRANS.toString())){
				csoCnsltTrans.setTransComId(null);
				csoCnsltTrans.setChrgJobTpCd(null);
				csoCnsltTrans.setTransRequstTpCd(null);
			}else if(csoCnsltTrans.getTransTgtCd().equals(CounselTransEnum.transTgt.COM_TRANS.toString())){
				csoCnsltTrans.setTransRecrId(null);
				csoCnsltTrans.setChrgJobTpCd(null);
				csoCnsltTrans.setTransRequstTpCd(null);
			}else if(csoCnsltTrans.getTransTgtCd().equals(CounselTransEnum.transTgt.JOB_TRANS.toString())){
				csoCnsltTrans.setTransComId(null);
				csoCnsltTrans.setTransRecrId(null);
			}
			
			csoCnsltTrans.setCnsltSn(cnsltSn);
			csoCnsltTrans.setCnsltDetailTurn(cnsltDetailTurn);
			csoCnsltTrans.setRegtrId(BOSecurityUtil.getLoginId());
			csoCnsltTrans.setUdterId(BOSecurityUtil.getLoginId());
			csoCnsltTrans.setChrgerCnfirmYn(CounselTransEnum.chrgerCnfirmYn.N.toString());
			csoCnsltTrans.setTransStatCd(CounselTransEnum.transStat.TRANS_WAIT.toString());
			
			int transRequstTurn = memberGoodsQnaRepository.insertCsoCnsltTransWithGenTurn(csoCnsltTrans);
			
			// 상담이관 주문 상품 등록
			CsoCnsltTransOrdGod csoCnsltTransOrdGod = new CsoCnsltTransOrdGod();
			csoCnsltTransOrdGod.setCnsltSn(cnsltSn);
			csoCnsltTransOrdGod.setCnsltDetailTurn(cnsltDetailTurn);
			csoCnsltTransOrdGod.setTransRequstTurn(transRequstTurn);
			csoCnsltTransOrdGod.setGodNm(memberGoodsQna.getGodNm());
			csoCnsltTransOrdGod.setOrdGodSectCd(CsoCnsltTransOrdGodEnum.ordGodSectCd.GOD.toString());
			csoCnsltTransOrdGod.setGodNo(memberGoodsQna.getGodNo());
			csoCnsltTransOrdGod.setRegtrId(BOSecurityUtil.getLoginId());
			csoCnsltTransOrdGod.setUdterId(BOSecurityUtil.getLoginId());
			
			
			memberGoodsQnaRepository.insertCsoCnsltTransOrdGodWithGenTurn(csoCnsltTransOrdGod);
			
			// 상담이관 이력 등록
			
			CsoCnsltTransHist csoCnsltTransHist = new CsoCnsltTransHist();
			
			csoCnsltTransHist.setCnsltSn(cnsltSn);
			csoCnsltTransHist.setCnsltDetailTurn(cnsltDetailTurn);
			csoCnsltTransHist.setTransRequstTurn(transRequstTurn);
			csoCnsltTransHist.setTransModInfoCd(CsoCnsltTransHistEnum.TRANS_MOD_INFO_CD.TRANS_MOD_INFO.toString());
			csoCnsltTransHist.setModTpCd(CsoCnsltTransHistEnum.MOD_TP_CD.REG.toString());
			csoCnsltTransHist.setRegtrId(BOSecurityUtil.getLoginId());
			csoCnsltTransHist.setUdterId(BOSecurityUtil.getLoginId());
			
			memberGoodsQnaRepository.insertCsoCnsltTransHist(csoCnsltTransHist);
			
			csoCnsltTransHist.setTransModInfoCd(CsoCnsltTransHistEnum.TRANS_MOD_INFO_CD.TRANS_RECR_ID.toString());
			
			memberGoodsQnaRepository.insertCsoCnsltTransHist(csoCnsltTransHist);
			
			// 업무 요청 등록
			if(csoCnsltTrans.getTransTgtCd().equals(CounselTransEnum.transTgt.JOB_TRANS.toString())){
				
				CsoJobRequst csoJobRequst = new CsoJobRequst();
				
				csoJobRequst.setTransStatCd(CsoJobRequstEnum.transStatCd.TRANS_WAIT.toString());
				csoJobRequst.setChrgJobTpCd(csoCnsltTrans.getChrgJobTpCd());
				csoJobRequst.setTransRequstTpCd(csoCnsltTrans.getTransRequstTpCd());
				csoJobRequst.setDoiCd(csoCnsltTrans.getDoiCd());
				csoJobRequst.setRegAdminId(csoCnsltTrans.getRegtrId());
				csoJobRequst.setRequstRceptAdminId(csoCnsltTrans.getTransRecrId()); // ?
				csoJobRequst.setRequstSj(csoCnsltTrans.getTransRequstSj());
				/*csoJobRequst.setRequstCont();*/
				csoJobRequst.setCnsltSn(cnsltSn);
				csoJobRequst.setCnsltDetailTurn(cnsltDetailTurn);
				csoJobRequst.setTransRequstTurn(transRequstTurn);
				
				long requstSn = memberGoodsQnaRepository.insertCsoJobRequstWithGenSn(csoJobRequst);
				
				// 업무 요청 주문 상품 등록
				
				CsoJobRequstOrdGod csoJobRequstOrdGod = new CsoJobRequstOrdGod();
				
				csoJobRequstOrdGod.setRequstSn(requstSn);
				csoJobRequstOrdGod.setGodNm(memberGoodsQna.getGodNm());
				csoJobRequstOrdGod.setOrdGodSectCd(CsoJobRequstOrdGodEnum.ordGodSectCd.GOD.toString());
				csoJobRequstOrdGod.setGodNo(memberGoodsQna.getGodNo());
				csoJobRequstOrdGod.setRegtrId(BOSecurityUtil.getLoginId());
				csoJobRequstOrdGod.setUdterId(BOSecurityUtil.getLoginId());
				
				memberGoodsQnaRepository.insertCsoJobRequstOrdGodWithGenTurn(csoJobRequstOrdGod);
				
			}
		}
		
	
		if("Y".equals(memberGoodsQnaDTO.getPromiseCallSaveYn())){
			// 약속콜 등록
			CsoPromscl csoPromscl = memberGoodsQnaDTO.getCsoPromscl();
			
			
			
			csoPromscl.setPromsclStatCd(CsoPromsclEnum.promsclStatCd.PROMS_WAIT.toString());
			csoPromscl.setPromsclConfigYn(CsoPromsclEnum.promsclConfigYn.Y.toString());
			csoPromscl.setChrgerCnfirmYn(CsoPromsclEnum.chrgerCnfirmYn.Y.toString());
			
			String[] phoneNum = makePhoneNumber(memberGoodsQnaDTO.getCsoPromsclTel());
			csoPromscl.setPromsclNationNo(phoneNum[0]);
			csoPromscl.setPromsclAreaNo(phoneNum[1]);
			csoPromscl.setPromsclTlofNo(phoneNum[2]);
			csoPromscl.setPromsclTlofWthnNo(phoneNum[3]);
			
			csoPromscl.setCnsltSn(cnsltSn);
			csoPromscl.setCnsltDetailTurn(cnsltDetailTurn);
			
			csoPromscl.setRegtrId(BOSecurityUtil.getLoginId());
			csoPromscl.setUdterId(BOSecurityUtil.getLoginId());
			
			
			memberGoodsQnaRepository.insertCsoPromsclWithGenSn(csoPromscl);
		}
		
		
		
		
		
		
	}
	
	public String[] makePhoneNumber(String phoneNum) throws Exception{
		String[] totalArr = new String[4];
		totalArr[0] = "82";
		String[] tempNum = phoneNum.split("-");
		for(int i = 0 ; i < tempNum.length; i++){
			totalArr[i + 1] = tempNum[i];
		}
		return totalArr;
	}


	public List<MemberGoodsQnaResult> selectMemberGoodsQnaList(MemberGoodsQnaSearchDTO memberGoodsQnaSearchDTO, PageParam pageParam) throws Exception{
		return memberGoodsQnaRepository.selectMemberGoodsQnaList(memberGoodsQnaSearchDTO, pageParam);
	}

	public DetailMemberGoodsQnaResult detailMemberGoodsQnaUserInfo(MemberGoodsQnaSearchDTO memberGoodsQnaSearchDTO) throws Exception {
		return memberGoodsQnaRepository.detailMemberGoodsQnaUserInfo(memberGoodsQnaSearchDTO);
	}

	public DetailMemberGoodsQnaResult detailMemberGoodsQna(MemberGoodsQnaSearchDTO memberGoodsQnaSearchDTO) throws Exception {
		return memberGoodsQnaRepository.detailMemberGoodsQna(memberGoodsQnaSearchDTO);
	}

	public List<SelectCnsltDetailResult> selectCnsltDetail(String godInqSn) throws Exception{
		return memberGoodsQnaRepository.selectCnsltDetail(godInqSn);
	}

	/*public void deleteGridQna(List<MemberGoodsQnaGridDTO> deleteList) throws Exception {
		Iterator<MemberGoodsQnaGridDTO> iterator = deleteList.iterator();
		while(iterator.hasNext()) {
			memberGoodsQnaRepository.deleteGridQna(iterator.next());
		}
	}*/

	public void updateQna(MemberGoodsQnaDTO memberGoodsQnaDTO) throws Exception {
		// 상품 Q&A 상태 수정
		CsoGodInq csoGodInq = new CsoGodInq();
		CsoGodInqAns csoGodInqAns = memberGoodsQnaDTO.getCsoGodInqAns();
		csoGodInq.setGodInqSn(memberGoodsQnaDTO.getGodInqSn());

		// 답변대기 체크일경우 답변상태는 답변대기 아니면 답변 완료
		if(memberGoodsQnaDTO.getCheckAnsWait() != null && memberGoodsQnaDTO.getCheckAnsWait().equals("Y")){
			csoGodInq.setAnsStatCd(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_WAIT.toString());
		}else if(CsoCnsltEnum.cnsltStat.TMPR_SAVE.toString().equals(memberGoodsQnaDTO.getCsoCnslt().getCnsltStatCd())){
			// 임시 저장일 경우 답변 상태는 답변 전으로 설정한다
			csoGodInq.setAnsStatCd(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_BF.toString());
		}else{
			csoGodInq.setAnsStatCd(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_COMPT.toString());
		}

		csoGodInq.setUdterId(BOSecurityUtil.getLoginId());

		// 상품 Q&A 상태 수정
		memberGoodsQnaRepository.updateMemberGoodsQna(csoGodInq);

		// 상품 Q&A 답변 수정
		csoGodInqAns.setGodInqSn(csoGodInq.getGodInqSn());
		csoGodInqAns.setDetailAnsStatCd(csoGodInq.getAnsStatCd());
		csoGodInqAns.setAnsAdminId(BOSecurityUtil.getLoginId());
		csoGodInqAns.setUdterId(BOSecurityUtil.getLoginId());

		memberGoodsQnaRepository.upddateCsoGodInqAns(csoGodInqAns);


		// 상담이력 수정
		CsoCnslt csoCnslt = memberGoodsQnaDTO.getCsoCnslt();
		csoCnslt.setCnsltSn(memberGoodsQnaDTO.getCsoCnsltDetail().getCnsltSn());
		csoCnslt.setUdterId(BOSecurityUtil.getLoginId());

		counselRepository.updateCounsel(csoCnslt);


		//상담이력 상세 수정
		CsoCnsltDetail csoCnsltDetail = memberGoodsQnaDTO.getCsoCnsltDetail();
		csoCnsltDetail.setCnsltDetailStatCd(memberGoodsQnaDTO.getCsoCnslt().getCnsltStatCd());
		csoCnsltDetail.setUdterId(BOSecurityUtil.getLoginId());

		if(!csoCnsltDetail.getCnsltDetailStatCd().equals(CsoCnsltDetailEnum.cnsltDetailStat.TMPR_SAVE.toString())){

			if(csoGodInqAns.getAnsCont() != null && !"".equals(csoGodInqAns.getAnsCont())){
				csoCnsltDetail.setCnsltDetailCont("답변 내용 : " + csoGodInqAns.getAnsCont() + " <br> -----------------------------------------------------------------------------------<br> 상담 내용 : " + csoCnsltDetail.getCnsltDetailCont().replaceAll("(\r\n|\n)", "<br />") + "<br>");
			}else{
				csoCnsltDetail.setCnsltDetailCont(csoCnsltDetail.getCnsltDetailCont().replaceAll("(\r\n|\n)", "<br />"));
			}

		}

		counselRepository.updateCounselDetail(csoCnsltDetail);

		//고객서비스 상담 상세 이력 등록
		CsoCnsltDetailHist csoCnsltDetailHist = new CsoCnsltDetailHist();
		csoCnsltDetailHist.setCnsltSn(csoCnsltDetail.getCnsltSn());
		csoCnsltDetailHist.setCnsltDetailTurn(csoCnsltDetail.getCnsltDetailTurn());

		csoCnsltDetailHist.setModInfoCd(CsoCnsltDetailHistEnum.modInfo.CNSLT_DETAIL_CONT.toString());
		csoCnsltDetailHist.setModTpCd(CsoCnsltDetailHistEnum.modTp.UDT.toString());
		csoCnsltDetailHist.setModCont(csoCnsltDetail.getCnsltDetailCont());
		csoCnsltDetailHist.setRegtrId(BOSecurityUtil.getLoginId());
		csoCnsltDetailHist.setUdterId(BOSecurityUtil.getLoginId());
		counselRepository.insertCsoCnsltDetailHist(csoCnsltDetailHist);

		csoCnsltDetailHist.setModInfoCd(CsoCnsltDetailHistEnum.modInfo.CNSLT_DETAIL_STAT_CD.toString());
		counselRepository.insertCsoCnsltDetailHist(csoCnsltDetailHist);

		// 상담 이관 등록

		if ("Y".equals(memberGoodsQnaDTO.getTransSaveYn())) {
			CsoCnsltTrans csoCnsltTrans = memberGoodsQnaDTO.getCsoCnsltTrans();

			if (csoCnsltTrans.getTransTgtCd().equals(CounselTransEnum.transTgt.CNSLR_TRANS.toString())) {
				csoCnsltTrans.setTransComId(null);
				csoCnsltTrans.setChrgJobTpCd(null);
				csoCnsltTrans.setTransRequstTpCd(null);
			} else if (csoCnsltTrans.getTransTgtCd().equals(CounselTransEnum.transTgt.COM_TRANS.toString())) {
				csoCnsltTrans.setTransRecrId(null);
				csoCnsltTrans.setChrgJobTpCd(null);
				csoCnsltTrans.setTransRequstTpCd(null);
			} else if (csoCnsltTrans.getTransTgtCd().equals(CounselTransEnum.transTgt.JOB_TRANS.toString())) {
				csoCnsltTrans.setTransComId(null);
			}

			csoCnsltTrans.setCnsltSn(csoCnsltDetail.getCnsltSn());
			csoCnsltTrans.setCnsltDetailTurn(csoCnsltDetail.getCnsltDetailTurn());
			csoCnsltTrans.setTransRqesterId(BOSecurityUtil.getLoginId());
			csoCnsltTrans.setTransRequstCont(csoCnsltDetail.getCnsltDetailCont());
			csoCnsltTrans.setTransRecrId(csoCnsltTrans.getTransRecrId());

			csoCnsltTrans.setRegtrId(BOSecurityUtil.getLoginId());
			csoCnsltTrans.setUdterId(BOSecurityUtil.getLoginId());
			csoCnsltTrans.setChrgerCnfirmYn(CounselTransEnum.chrgerCnfirmYn.N.toString());
			csoCnsltTrans.setTransStatCd(CounselTransEnum.transStat.TRANS_WAIT.toString());

			csoCnsltTrans.setTransRequstTurn(counselRepository.insertCsoCnsltTransWithGenTurn(csoCnsltTrans));

			// 상담 이관 주문 상품 등록

			CsoCnsltTransOrdGod csoCnsltTransOrdGod = memberGoodsQnaDTO.getCsoCnsltTransOrdGod();

			if (csoCnsltTransOrdGod != null && !"".equals(csoCnsltTransOrdGod.getOrdNo())) {

				csoCnsltTransOrdGod.setCnsltSn(csoCnsltDetail.getCnsltSn());
				csoCnsltTransOrdGod.setCnsltDetailTurn(csoCnsltDetail.getCnsltDetailTurn());
				csoCnsltTransOrdGod.setTransRequstTurn(csoCnsltTrans.getTransRequstTurn());
				csoCnsltTransOrdGod.setOrdGodSectCd(CsoCnsltDetailOrdGodEnum.ordGodSect.ORD.toString());

				csoCnsltTransOrdGod.setOrdNo(csoCnsltTransOrdGod.getOrdNo());

				csoCnsltTransOrdGod.setRegtrId(BOSecurityUtil.getLoginId());
				csoCnsltTransOrdGod.setUdterId(BOSecurityUtil.getLoginId());

				counselRepository.insertCsoCnsltTransOrdGodWithGenTurn(csoCnsltTransOrdGod);
			}


			// 상담이관 이력 등록

			CsoCnsltTransHist csoCnsltTransHist = new CsoCnsltTransHist();

			csoCnsltTransHist.setCnsltSn(csoCnsltDetail.getCnsltSn());
			csoCnsltTransHist.setCnsltDetailTurn(csoCnsltDetail.getCnsltDetailTurn());
			csoCnsltTransHist.setTransRequstTurn(csoCnsltTrans.getTransRequstTurn());
			csoCnsltTransHist.setTransModInfoCd(CsoCnsltTransHistEnum.TRANS_MOD_INFO_CD.TRANS_MOD_INFO.toString());
			csoCnsltTransHist.setModTpCd(CsoCnsltTransHistEnum.MOD_TP_CD.REG.toString());
			csoCnsltTransHist.setRegtrId(BOSecurityUtil.getLoginId());
			csoCnsltTransHist.setUdterId(BOSecurityUtil.getLoginId());

			counselRepository.insertCsoCnsltTransHist(csoCnsltTransHist);

			csoCnsltTransHist.setTransModInfoCd(CsoCnsltTransHistEnum.TRANS_MOD_INFO_CD.TRANS_RECR_ID.toString());

			counselRepository.insertCsoCnsltTransHist(csoCnsltTransHist);

			// 상담 이관 유형이 업무 이관일 때
			if (csoCnsltTrans.getTransTgtCd().equals(CounselTransEnum.transTgt.JOB_TRANS.toString())) {


				CsoJobRequst csoJobRequst = new CsoJobRequst();

				csoJobRequst.setTransStatCd(CsoJobRequstEnum.transStatCd.TRANS_WAIT.toString());
				csoJobRequst.setChrgJobTpCd(csoCnsltTrans.getChrgJobTpCd());
				csoJobRequst.setTransRequstTpCd(csoCnsltTrans.getTransRequstTpCd());
				csoJobRequst.setDoiCd(csoCnsltTrans.getDoiCd());
				csoJobRequst.setRegAdminId(csoCnsltTrans.getRegtrId());
				csoJobRequst.setRequstRceptAdminId(csoCnsltTrans.getTransRecrId());
				csoJobRequst.setRequstSj(csoCnsltTrans.getTransRequstSj());
				csoJobRequst.setRequstCont(csoCnsltTrans.getTransRequstCont());
				csoJobRequst.setCnsltSn(csoCnsltTrans.getCnsltSn());
				csoJobRequst.setCnsltDetailTurn(csoCnsltTrans.getCnsltDetailTurn());
				csoJobRequst.setTransRequstTurn(csoCnsltTrans.getTransRequstTurn());

				csoJobRequst.setRequstSn(counselRepository.insertCsoJobRequstWithGenSn(csoJobRequst));

				//counselDTO.setCsoJobRequst(csoJobRequst);

				// 업무 이관 주문 상품 등록
				if (csoCnsltTransOrdGod != null && !"".equals(csoCnsltTransOrdGod.getOrdNo())) {
					CsoJobRequstOrdGod csoJobRequstOrdGod = new CsoJobRequstOrdGod();

					csoJobRequstOrdGod.setRequstSn(csoJobRequst.getRequstSn());
					csoJobRequstOrdGod.setOrdGodSectCd(CsoJobRequstOrdGodEnum.ordGodSectCd.ORD.toString());

					csoJobRequstOrdGod.setOrdNo(csoCnsltTransOrdGod.getOrdNo());

					csoJobRequstOrdGod.setOrdNo(csoCnsltTransOrdGod.getOrdNo());

					csoJobRequstOrdGod.setRegtrId(BOSecurityUtil.getLoginId());
					csoJobRequstOrdGod.setUdterId(BOSecurityUtil.getLoginId());

					counselRepository.insertCsoJobRequstOrdGodWithGenTurn(csoJobRequstOrdGod);
				}
			}

		}


		// 약속콜 등록
		if ("Y".equals(memberGoodsQnaDTO.getPromiseCallSaveYn())){
			CsoPromscl csoPromscl = memberGoodsQnaDTO.getCsoPromscl();

			csoPromscl.setPromsclStatCd(CsoPromsclEnum.promsclStatCd.PROMS_WAIT.toString());
			csoPromscl.setPromsclConfigYn(CsoPromsclEnum.promsclConfigYn.Y.toString());
			csoPromscl.setChrgerCnfirmYn(CsoPromsclEnum.chrgerCnfirmYn.N.toString());

			String[] phoneNum = makePhoneNumber(memberGoodsQnaDTO.getCsoPromsclTel());
			csoPromscl.setPromsclNationNo(phoneNum[0]);
			csoPromscl.setPromsclAreaNo(phoneNum[1]);
			csoPromscl.setPromsclTlofNo(phoneNum[2]);
			csoPromscl.setPromsclTlofWthnNo(phoneNum[3]);

			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date promscDt = transFormat.parse(memberGoodsQnaDTO.getCsoPromsclDate());
			csoPromscl.setPromsclDt(promscDt);

			csoPromscl.setCnsltSn(csoCnsltDetail.getCnsltSn());
			csoPromscl.setCnsltDetailTurn(csoCnsltDetail.getCnsltDetailTurn());

			csoPromscl.setRegtrId(BOSecurityUtil.getLoginId());
			csoPromscl.setUdterId(BOSecurityUtil.getLoginId());

			counselRepository.insertCsoPromsclWithGenSn(csoPromscl);
		}

	}

	public void updateComptAns(MemberGoodsQnaDTO memberGoodsQnaDTO) {
		memberGoodsQnaRepository.updateComptAns(memberGoodsQnaDTO);
	}

	public void updateCsoCnsltDetailCont(CsoCnsltDetail csoCnsltDetail) {
		memberGoodsQnaRepository.updateCsoCnsltDetailCont(csoCnsltDetail);
	}
	
	public void insertQnaAnsEvlAdminAns(MemberGoodsQnaDTO memberGoodsQnaDTO) throws Exception{
		
		long godInqSn = Long.valueOf(memberGoodsQnaDTO.getGodInqSn());

		// 상품 Q&A 조회
		MemberGoodsQnaResult memberGoodsQna = memberGoodsQnaRepository.selectMemberGoodsQna(godInqSn);
		//답변 완료 된  상태 && 만족도 조사를 한 상태 && 추가 답변을 하지 않은 상태.
		if (memberGoodsQna != null
				&& CsoMtmInqAnsEnum.detailAnsStatCd.ANS_COMPT.toString().equals(memberGoodsQna.getAnsStatCd())
				&& (("VDSTF").equals(memberGoodsQna.getAnsEvlCd()) || ("DSTF").equals(memberGoodsQna.getAnsEvlCd()))
				&& StringUtils.isEmpty(memberGoodsQna.getAnsEvlAdminAnsCont())
				){

			// 상품 Q&A 만족도평가 추가 답변 업데이트
			CsoGodInq csoGodInq = new CsoGodInq();

			csoGodInq.setGodInqSn(godInqSn);
			csoGodInq.setAnsEvlAdminAnsCont(memberGoodsQnaDTO.getCsoGodInq().getAnsEvlAdminAnsCont().replaceAll("(\r\n|\n)", "<br />"));
			csoGodInq.setUdterId(BOSecurityUtil.getLoginId());
			memberGoodsQnaRepository.updateMemberGoodsQnaAnsEvlAdminAns(csoGodInq);
		}else{
			// 만족도평가에 대한 추가 답변은 불가능.
			// 에러 처리해야함
			throw new Exception("만족도 평가에 대한 답변을 할수 없는 글입니다.");
		}
		
	}
}
