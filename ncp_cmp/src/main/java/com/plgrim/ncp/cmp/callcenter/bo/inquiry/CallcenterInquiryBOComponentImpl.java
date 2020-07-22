/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 6. 26       
 */
package com.plgrim.ncp.cmp.callcenter.bo.inquiry;

import com.plgrim.ncp.base.entities.datasource1.cso.*;
import com.plgrim.ncp.base.enums.*;
import com.plgrim.ncp.biz.callcenter.data.*;
import com.plgrim.ncp.biz.callcenter.repository.MailTemplateRepository;
import com.plgrim.ncp.biz.callcenter.repository.MemberGoodsQnaRepository;
import com.plgrim.ncp.biz.callcenter.repository.MtmRepository;
import com.plgrim.ncp.biz.callcenter.result.*;
import com.plgrim.ncp.biz.callcenter.service.CounselService;
import com.plgrim.ncp.biz.callcenter.service.FaqService;
import com.plgrim.ncp.biz.callcenter.service.MemberGoodsQnaService;
import com.plgrim.ncp.biz.callcenter.service.MtmService;
import com.plgrim.ncp.cmp.callcenter.bo.CallcenterInquiryBOComponent;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.page.PageParam;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Transactional(value = "transactionManager")
@Component
public class CallcenterInquiryBOComponentImpl implements CallcenterInquiryBOComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	@Autowired
	MtmService mtmService;

	@Autowired
	MemberGoodsQnaService memberGoodsQnaService;

	@Autowired
	FaqService faqService;

	@Autowired
	MtmRepository mtmRepository;
	
	@Autowired
	MemberGoodsQnaRepository memberGoodsQnaRepository;

	@Autowired
	CounselService counselService;

	@Autowired
	MailTemplateRepository mailTemplateRepository;


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

	public String[] makePhoneNumber(String phoneNum) throws Exception{
		String[] totalArr = new String[4];
		totalArr[0] = "82";
		String[] tempNum = phoneNum.split("-");
		for(int i = 0 ; i < tempNum.length; i++){
			totalArr[i + 1] = tempNum[i];
		}
		return totalArr;
	}

	@Override
	public void insertMemberGoodsQnaAns(MemberGoodsQnaDTO memberGoodsQnaDTO) throws Exception {
		memberGoodsQnaService.insertMemberGoodsQnaAns(memberGoodsQnaDTO);
	}
	@Override
	public void insertQnaAnsEvlAdminAns(MemberGoodsQnaDTO memberGoodsQnaDTO) throws Exception {
		memberGoodsQnaService.insertQnaAnsEvlAdminAns(memberGoodsQnaDTO);
	}
	
	@Override
	public void updateFaqGrid(List<FaqGridDTO> updateList) throws Exception {
		faqService.updateFaqGrid(updateList);
	}

	@Override
	public void insertFaq(FaqDTO faqDTO, List<MultipartFile> files) throws Exception {
		faqService.insertFaq(faqDTO,files);

		// FAQ 게시판 첨부파일 등록
		if( files.size() > 0){
			faqService.insertFaqAtchmnfl(faqDTO, files);
		}
	}

	@Override
	public void updateFaq(FaqDTO faqDTO, List<MultipartFile> files) throws Exception {
		faqService.updateFaq(faqDTO);

		// FAQ 게시판 첨부파일 등록
		if( files.size() > 0){
			faqService.insertFaqAtchmnfl(faqDTO, files);
		}
	}

	@Override
	public void deleteFaqGrid(List<FaqGridDTO> deleteList) throws Exception {
		faqService.deleteFaqGrid(deleteList);
	}

	@Override
	public void insertMtmInquiryAns(MtmAddDTO mtmAddDTO) throws Exception {

		long mtmInqSn = Long.valueOf(mtmAddDTO.getMtmInqSn());

		// 1:1 문의 답변 상태 조회
		String mastsrAnsStatCd = mtmRepository.selectAnsStat(mtmInqSn);

		int mtmInqAnsTurn = 0;

		if(!CsoMtmInqAnsEnum.detailAnsStatCd.ANS_COMPT.toString().equals(mastsrAnsStatCd)){

			// 1:1 문의 답변 등록
			CsoMtmInqAns csoMtmInqAns = new CsoMtmInqAns();
			csoMtmInqAns.setMtmInqSn(mtmInqSn);
			csoMtmInqAns.setAnsSj("1:1문의 답변이 등록되었습니다.");
			csoMtmInqAns.setAnsCont(mtmAddDTO.getAnsCont());

			csoMtmInqAns.setAnsCnfirmYn("N");
			csoMtmInqAns.setAnsAdminId(BOSecurityUtil.getLoginId());
			csoMtmInqAns.setRegtrId(BOSecurityUtil.getLoginId());
			csoMtmInqAns.setUdterId(BOSecurityUtil.getLoginId());

			// 답변대기 체크일경우 답변상태는 답변대기 아니면 답변 완료
			if(mtmAddDTO.getCheckAnsWait() != null && mtmAddDTO.getCheckAnsWait().equals("Y")){
				csoMtmInqAns.setDetailAnsStatCd(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_WAIT.toString());
			}else if(CsoCnsltEnum.cnsltStat.PRCS_WAIT.toString().equals(mtmAddDTO.getCsoCnslt().getCnsltStatCd())){
				//  처리대기 클릭시 답변대기상태 확인중 일 경우 답변상태는 처리대기   확인중이 아닐경우 답변 상태는 답변 전으로 설정한다
				csoMtmInqAns.setDetailAnsStatCd(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_BF.toString());
			}else if(CsoCnsltEnum.cnsltStat.TMPR_SAVE.toString().equals(mtmAddDTO.getCsoCnslt().getCnsltStatCd())){
				// 임시 저장일 경우 답변 상태는 답변 전으로 설정한다
				csoMtmInqAns.setDetailAnsStatCd(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_BF.toString());
			}else{
				csoMtmInqAns.setDetailAnsStatCd(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_COMPT.toString());
			}

			mtmInqAnsTurn = mtmService.insertCsoMtmInqAns(csoMtmInqAns);

			// 1:1 문의 답변 상태 조회
			//String mastsrAnsStatCd = mtmRepository.selectAnsStat(mtmInqSn);

			// 1:1 상담 마스터 테이블 답변 상태가 답변 전이고, 입력된 값이 답변 대기나 답변 완료일 때,
			// 1:1 상담 마스터 테이블 답변 상태가 답변 대기이고, 입력된 값이 답변 완료일때
			if(mastsrAnsStatCd.equals(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_BF.toString()) && csoMtmInqAns.getDetailAnsStatCd().equals(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_WAIT.toString())
					|| mastsrAnsStatCd.equals(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_BF.toString()) && csoMtmInqAns.getDetailAnsStatCd().equals(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_COMPT.toString())
					|| mastsrAnsStatCd.equals(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_WAIT.toString()) && csoMtmInqAns.getDetailAnsStatCd().equals(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_COMPT.toString())){

				// 1:1 문의 답변 상태 업데이트
				CsoMtmInq csoMtmInq = new CsoMtmInq();
				csoMtmInq.setMtmInqSn(mtmInqSn);
				csoMtmInq.setAnsStatCd(csoMtmInqAns.getDetailAnsStatCd());
				csoMtmInq.setUdterId(BOSecurityUtil.getLoginId());

				mtmService.updateCsoMtmInq(csoMtmInq);
			}
		}



		// 1:1 문의 조회
		MtmResult mtmInq = mtmRepository.selectCsoMtmInq(mtmInqSn);

		// 상담이 등록되어 있는지 안되어 있는지 체크
		String selectCnsltSn = mtmRepository.selectCounselCount(mtmInqSn);
		Long cnsltSn = null;

		// 1:1 문의 주문 상품 조회
		List<MtmOrdGodResult> mtmOrdGod = mtmRepository.selectCsoMtmInqOrdGod(mtmInqSn);

		if(selectCnsltSn != null){

			cnsltSn = Long.valueOf(selectCnsltSn);

			if(!CsoCnsltEnum.cnsltStat.TMPR_SAVE.toString().equals(mtmAddDTO.getCsoCnslt().getCnsltStatCd())){
				CsoCnslt csoCnslt = mtmAddDTO.getCsoCnslt();
				csoCnslt.setCnsltSn(cnsltSn);
				csoCnslt.setUdterId(BOSecurityUtil.getLoginId());
				memberGoodsQnaRepository.updateCsoCnsltStat(csoCnslt);
			}

		}else if(selectCnsltSn == null){
			// 상담 등록
			CsoCnslt csoCnslt = mtmAddDTO.getCsoCnslt();
			csoCnslt.setMtmInqSn(mtmInqSn);
			csoCnslt.setCnsltReqstMbrNo(mtmInq.getInqMbrNo());
			csoCnslt.setCstmrnm(mtmInq.getInqCstmrNm());
			csoCnslt.setInqTpCd(mtmInq.getInqTpCd());
			csoCnslt.setCstmrEmail(mtmInq.getCstmrEmail());
			csoCnslt.setMallId(mtmInq.getMallId());
			csoCnslt.setLangCd(mtmInq.getLangCd());
			csoCnslt.setDvcCd(mtmInq.getDvcCd());
			csoCnslt.setRegtrId(BOSecurityUtil.getLoginId());
			csoCnslt.setUdterId(BOSecurityUtil.getLoginId());

			cnsltSn = memberGoodsQnaRepository.insertCsoCnsltWithGenSn(csoCnslt);



			// 주문 정보 삭제를 안했을 경우 상담테이블에 주문 상품 등록
			if("N".equals(mtmAddDTO.getOrderDeleteYn())){
				// 상담 주문 상품 등록
				for(int i = 0 ; i < mtmOrdGod.size(); i++){
					CsoCnsltOrdGod csoCnsltOrdGod = new CsoCnsltOrdGod();

					csoCnsltOrdGod.setCnsltSn(cnsltSn);
					csoCnsltOrdGod.setGodNm(mtmOrdGod.get(i).getGodNm());
					csoCnsltOrdGod.setOrdGodSectCd(CsoCnsltOrdGodEnum.ordGodSect.ORD.toString());
					csoCnsltOrdGod.setOrdNo(mtmOrdGod.get(i).getOrdNo());
					csoCnsltOrdGod.setGodNo(mtmOrdGod.get(i).getGodNo());
					csoCnsltOrdGod.setOrdGodTurn(mtmOrdGod.get(i).getOrdGodTurn());

					memberGoodsQnaRepository.insertCsoCnsltOrdGodWithGenTurn(csoCnsltOrdGod);
				}
			}
		}

		// 상담 상세 등록
		CsoCnsltDetail csoCnsltDetail = mtmAddDTO.getCsoCnsltDetail();

		csoCnsltDetail.setCnsltSn(cnsltSn);
		csoCnsltDetail.setCnsltDetlTrnsmisRequstYn("N");
		csoCnsltDetail.setCstmrClmCd(mtmAddDTO.getCsoCnslt().getCstmrClmCd());
		csoCnsltDetail.setDeleteYn("N");
		csoCnsltDetail.setCnsltDetailStatCd(mtmAddDTO.getCsoCnslt().getCnsltStatCd());
		csoCnsltDetail.setCnsltKndCd(mtmAddDTO.getCsoCnslt().getCnsltKndCd());

		if (csoCnsltDetail.getCnsltDetailStatCd().equals(CsoCnsltDetailEnum.cnsltDetailStat.TMPR_SAVE.toString())) {
			csoCnsltDetail.setCnsltDetailCont(csoCnsltDetail.getCnsltDetailCont());
		}else{
			if (mtmAddDTO.getAnsCont() != null && !"".equals(mtmAddDTO.getAnsCont())) {
				csoCnsltDetail.setCnsltDetailCont(mtmAddDTO.getAnsCont());
			} else {
				csoCnsltDetail.setCnsltDetailCont(csoCnsltDetail.getCnsltDetailCont().replaceAll("(\r\n|\n)", "<br />"));
			}
		}

		csoCnsltDetail.setMtmInqSn(mtmInqSn);
		if(mtmInqAnsTurn > 0){
			csoCnsltDetail.setMtmInqAnsTurn(mtmInqAnsTurn);
		}
		csoCnsltDetail.setRegtrId(BOSecurityUtil.getLoginId());

		csoCnsltDetail.setUdterId(BOSecurityUtil.getLoginId());

		int cnsltDetailTurn = memberGoodsQnaRepository.insertCsoCnsltDetailWithGenTurn(csoCnsltDetail);

		//상담이 처리완료로 저장될 경우 임시저장 상태를 제외한 모든 상태를 처리 완료로 변경
		if (CsoCnsltDetailEnum.cnsltDetailStat.PROC_COMPT.toString().equals(csoCnsltDetail.getCnsltDetailStatCd())) {
			counselService.updateCounseldetailStatCompt(csoCnsltDetail);
		}

		// 상담 상세 업무 유형 등록
		CsoCnsltDetailJobTp csoCnsltDetailJobTp = new CsoCnsltDetailJobTp();

		for(int i = 0 ; i < mtmAddDTO.getCnsltJobTpCd().length; i++){

			csoCnsltDetailJobTp.setCnsltSn(cnsltSn);
			csoCnsltDetailJobTp.setCnsltDetailTurn(cnsltDetailTurn);
			csoCnsltDetailJobTp.setCnsltJobTpCd(mtmAddDTO.getCnsltJobTpCd()[i]);
			csoCnsltDetailJobTp.setRegtrId(BOSecurityUtil.getLoginId());
			csoCnsltDetailJobTp.setUdterId(BOSecurityUtil.getLoginId());

			memberGoodsQnaRepository.insertCsoCnsltDetailJopTp(csoCnsltDetailJobTp);
		}



		// 주문 정보 삭제를 안했을 경우 상담 상세 테이블에 주문 상품 등록
		if("N".equals(mtmAddDTO.getOrderDeleteYn())){
			for(int i = 0 ; i < mtmOrdGod.size(); i++){
				// 상담 상세 주문 상품 등록
				CsoCnsltDetailOrdGod csoCnsltDetailOrdGod = new CsoCnsltDetailOrdGod();

				csoCnsltDetailOrdGod.setCnsltSn(cnsltSn);
				csoCnsltDetailOrdGod.setCnsltDetailTurn(cnsltDetailTurn);
				csoCnsltDetailOrdGod.setGodNm(mtmOrdGod.get(i).getGodNm());
				csoCnsltDetailOrdGod.setOrdGodSectCd(CsoCnsltOrdGodEnum.ordGodSect.ORD.toString());
				csoCnsltDetailOrdGod.setOrdNo(mtmOrdGod.get(i).getOrdNo());
				csoCnsltDetailOrdGod.setGodNo(mtmOrdGod.get(i).getGodNo());
				csoCnsltDetailOrdGod.setOrdGodTurn(mtmOrdGod.get(i).getOrdGodTurn());

				memberGoodsQnaRepository.insertCsoCnsltDetailOrdGodWithGenTurn(csoCnsltDetailOrdGod);
			}
		}


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
		if("Y".equals(mtmAddDTO.getTransSaveYn())){
			CsoCnsltTrans csoCnsltTrans = mtmAddDTO.getCsoCnsltTrans();

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

			// 주문 정보 삭제를 안했을 경우 상담 상세 테이블에 주문 상품 등록
			if("N".equals(mtmAddDTO.getOrderDeleteYn())){
				// 상담이관 주문 상품 등록
				for(int i = 0 ; i < mtmOrdGod.size(); i++){
					CsoCnsltTransOrdGod csoCnsltTransOrdGod = new CsoCnsltTransOrdGod();

					csoCnsltTransOrdGod.setCnsltSn(cnsltSn);
					csoCnsltTransOrdGod.setCnsltDetailTurn(cnsltDetailTurn);
					csoCnsltTransOrdGod.setTransRequstTurn(transRequstTurn);
					csoCnsltTransOrdGod.setGodNm(mtmOrdGod.get(i).getGodNm());
					csoCnsltTransOrdGod.setOrdGodSectCd(CsoCnsltOrdGodEnum.ordGodSect.ORD.toString());
					csoCnsltTransOrdGod.setOrdNo(mtmOrdGod.get(i).getOrdNo());
					csoCnsltTransOrdGod.setOrdGodTurn(mtmOrdGod.get(i).getOrdGodTurn());
					csoCnsltTransOrdGod.setRegtrId(BOSecurityUtil.getLoginId());
					csoCnsltTransOrdGod.setUdterId(BOSecurityUtil.getLoginId());

					memberGoodsQnaRepository.insertCsoCnsltTransOrdGodWithGenTurn(csoCnsltTransOrdGod);
				}
			}


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



				// 주문 정보 삭제를 안했을 경우 업무 이관 테이블에 주문 상품 등록
				if("N".equals(mtmAddDTO.getOrderDeleteYn())){

					// 업무 이관 주문 상품 등록
					for(int i = 0 ; i < mtmOrdGod.size(); i++){
						CsoJobRequstOrdGod csoJobRequstOrdGod = new CsoJobRequstOrdGod();

						csoJobRequstOrdGod.setRequstSn(requstSn);
						csoJobRequstOrdGod.setGodNm(mtmOrdGod.get(i).getGodNm());
						csoJobRequstOrdGod.setOrdGodSectCd(CsoCnsltOrdGodEnum.ordGodSect.ORD.toString());
						csoJobRequstOrdGod.setOrdNo(mtmOrdGod.get(i).getOrdNo());
						csoJobRequstOrdGod.setOrdGodTurn(mtmOrdGod.get(i).getOrdGodTurn());
						csoJobRequstOrdGod.setRegtrId(BOSecurityUtil.getLoginId());
						csoJobRequstOrdGod.setUdterId(BOSecurityUtil.getLoginId());

						memberGoodsQnaRepository.insertCsoJobRequstOrdGodWithGenTurn(csoJobRequstOrdGod);
					}
				}

			}
		}


		if("Y".equals(mtmAddDTO.getPromiseCallSaveYn())){
			// 약속콜 등록
			CsoPromscl csoPromscl = mtmAddDTO.getCsoPromscl();



			csoPromscl.setPromsclStatCd(CsoPromsclEnum.promsclStatCd.PROMS_WAIT.toString());
			csoPromscl.setPromsclConfigYn(CsoPromsclEnum.promsclConfigYn.Y.toString());
			csoPromscl.setChrgerCnfirmYn(CsoPromsclEnum.chrgerCnfirmYn.Y.toString());

			String[] phoneNum = makePhoneNumber(mtmAddDTO.getCsoPromsclTel());
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
	@Override
	public void insertInquiryAnsEvlAdminAns(MtmAddDTO mtmAddDTO) throws Exception {
		
		long mtmInqSn = Long.valueOf(mtmAddDTO.getMtmInqSn());
		
		// 1:1 문의 조회
		MtmResult mtmInq = mtmRepository.selectCsoMtmInq(mtmInqSn);
		//답변 완료 된  상태 && 만족도 조사를 한 상태 && 추가 답변을 하지 않은 상태.
		if(mtmInq != null 
				&& CsoMtmInqAnsEnum.detailAnsStatCd.ANS_COMPT.toString().equals(mtmInq.getAnsStatCd())
				&& (("VDSTF").equals(mtmInq.getAnsEvlCd()) || ("DSTF").equals(mtmInq.getAnsEvlCd()))
				&& StringUtils.isEmpty(mtmInq.getAnsEvlAdminAnsCont())
				){
			
			// 1:1 문의 만족도평가 추가 답변 업데이트
			CsoMtmInq csoMtmInq = new CsoMtmInq();
			csoMtmInq.setMtmInqSn(mtmInqSn);
			csoMtmInq.setAnsEvlAdminAnsCont(mtmAddDTO.getCsoMtmInq().getAnsEvlAdminAnsCont().replaceAll("(\r\n|\n)", "<br />"));
			csoMtmInq.setUdterId(BOSecurityUtil.getLoginId());
			
			mtmService.updateCsoMtmInqAnsEvlAdminAns(csoMtmInq);
			
		}else{
			// 만족도평가에 대한 추가 답변은 불가능.
			// 에러 처리해야함
			throw new Exception("만족도 평가에 대한 답변을 할수 없는 글입니다.");
		}
		
	}
	@Override
	public void updateAnsDscnttTp(CsoMtmInq csoMtmInq) throws Exception {
		
		long mtmInqSn = Long.valueOf(csoMtmInq.getMtmInqSn());
		
		// 1:1 문의 조회
		MtmResult mtmInq = mtmRepository.selectCsoMtmInq(mtmInqSn);
		//답변 완료 된  상태 && 만족도 조사를 한 상태 && 추가 답변을 하지 않은 상태.
		if(mtmInq != null 
				&& CsoMtmInqAnsEnum.detailAnsStatCd.ANS_COMPT.toString().equals(mtmInq.getAnsStatCd())
				&& (("VDSTF").equals(mtmInq.getAnsEvlCd()) || ("DSTF").equals(mtmInq.getAnsEvlCd()))
				&& StringUtils.isEmpty(mtmInq.getAnsEvlAdminAnsCont())
				){
			
			// 1:1 문의 만족도 불만유형 업데이트
			csoMtmInq.setAnsDscnttDetailCont(csoMtmInq.getAnsDscnttDetailCont().replaceAll("(\r\n|\n)", "<br />"));
			csoMtmInq.setUdterId(BOSecurityUtil.getLoginId());
			
			mtmService.updateAnsDscnttTp(csoMtmInq);
			
		}else{
			// 만족도평가에 대한 추가 답변은 불가능.
			// 에러 처리해야함
			throw new Exception("만족도 평가 결과에 대한 불만 유형 입력을 할수 없는 글입니다.");
		}
		
	}

	@Override
	public void deleteFaqAtchmnfl(FaqDTO faqDTO) throws Exception {
		faqService.deleteFaqAtchmnfl(faqDTO);
	}

	@Override
	public void updateInquiry(MtmAddDTO mtmAddDTO) throws Exception {
		mtmService.updateInquiry(mtmAddDTO);
	}

	@Override
	public void updateQna(MemberGoodsQnaDTO memberGoodsQnaDTO) throws Exception {
		memberGoodsQnaService.updateQna(memberGoodsQnaDTO);
	}

	@Override
	public void updateComptAns(MemberGoodsQnaDTO memberGoodsQnaDTO) throws Exception {
		memberGoodsQnaService.updateComptAns(memberGoodsQnaDTO);
	}

	@Override
	public List<MemberGoodsQnaResult> selectMemberGoodsQnaList(MemberGoodsQnaSearchDTO memberGoodsQnaSearchDTO, PageParam pageParam) throws Exception {
		return memberGoodsQnaService.selectMemberGoodsQnaList(memberGoodsQnaSearchDTO, pageParam);
	}

	@Override
	public DetailMemberGoodsQnaResult detailMemberGoodsQnaUserInfo(MemberGoodsQnaSearchDTO memberGoodsQnaSearchDTO) throws Exception {
		return memberGoodsQnaService.detailMemberGoodsQnaUserInfo(memberGoodsQnaSearchDTO);
	}

	@Override
	public DetailMemberGoodsQnaResult detailMemberGoodsQna(MemberGoodsQnaSearchDTO memberGoodsQnaSearchDTO) throws Exception {
		return memberGoodsQnaService.detailMemberGoodsQna(memberGoodsQnaSearchDTO);
	}

	@Override
	public List<SelectCnsltDetailResult> selectCnsltDetail(String godInqSn) throws Exception {
		return memberGoodsQnaService.selectCnsltDetail(godInqSn);
	}

	@Override
	public String mtmInqStatCheck(Long mtmInqSn) throws Exception {
		return mtmService.mtmInqStatCheck(mtmInqSn);
	}

	@Override
	public MailTemplateResult selectInqryInfo(String mtmInqSn) throws Exception {
		return mailTemplateRepository.selectInqryInfo(mtmInqSn);
	}

	@Override
	public MailTemplateResult selectMemberGoodsQnaInfo(CsoGodInq csoGodInq) throws Exception {
		return mailTemplateRepository.selectMemberGoodsQnaInfo(csoGodInq);
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */


}
