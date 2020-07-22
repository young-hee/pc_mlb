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

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.cso.*;
import com.plgrim.ncp.base.enums.*;
import com.plgrim.ncp.biz.callcenter.data.MtmAddDTO;
import com.plgrim.ncp.biz.callcenter.data.MtmListSearchDTO;
import com.plgrim.ncp.biz.callcenter.repository.*;
import com.plgrim.ncp.biz.callcenter.result.MtmListResult;
import com.plgrim.ncp.commons.auth.BOAccessDecisionVoter;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.data.SystemPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * CodeViewService Implementation
 * @author Park
 *
 */
@Service
public class MtmService extends AbstractService {
	

	@Autowired
	private MtmRepository mtmRepository;

	@Autowired
	private CounselRepository counselRepository;
	

	/**
	 * 1:1 문의 테이블
	 */	
	@Autowired
	private CsoMtmInqEntityRepository csoMtmInqRepository;
	
	/**
	 * 1:1 문의 답변 테이블
	 */
	@Autowired
	private CsoMtmInqRsponsEntityRepository csoMtmInqRsponsRepository;

	/**
	 * 고객서비스 상담 테이블
	 */
	@Autowired
	private CsoCnsltEntityRepository csoCnsltRepository;
		
	/**
	 * 고객서비스 상세 상담 테이블
	 *//*
	@Autowired
	private CsoCnsltDetailRepository csoCnsltDetailRepository;*/

	/**
	 * 고객서비스 상세 상담 이력 테이블
	 */
	@Autowired
	private CsoCnsltDetailHistEntityRepository csoCnsltDetailHistRepository;
	
	/**
	 * 고객서비스 상세 업무 유형 테이블
	 */
	@Autowired
	CsoCnsltDetailJobTpEntityRepository csoCnsltDetailJobTpRepository;
	
	/**
	 * 약속콜 테이블
	 */
	@Autowired
	CsoPromsclEntityRepository csoPromsclRepository;
	
	
	
	
	
	public List<MtmListResult> getListMtm(SystemPK systemPK, MtmListSearchDTO searchDTO) {
	    
	    return null;//mtmRepository.getListMtm(systemPK,searchDTO);
    }

	public int insertCsoMtmInqAns(CsoMtmInqAns csoMtmInqAns) throws Exception {
		return mtmRepository.insertCsoMtmInqAnsWithGenTurn(csoMtmInqAns);
	}

	public void updateCsoMtmInq(CsoMtmInq csoMtmInq) throws Exception {
		mtmRepository.updateCsoMtmInq(csoMtmInq);
	}
	
	public void updateCsoMtmInqAnsEvlAdminAns(CsoMtmInq csoMtmInq) throws Exception {
		mtmRepository.updateCsoMtmInqAnsEvlAdminAns(csoMtmInq);
	}
	public void updateAnsDscnttTp(CsoMtmInq csoMtmInq) throws Exception {
		mtmRepository.updateAnsDscnttTp(csoMtmInq);
	}

	public String mtmInqStatCheck(Long mtmInqSn) {
		return mtmRepository.mtmInqStatCheck(mtmInqSn);
	}


	public void updateInquiry(MtmAddDTO mtmAddDTO) throws Exception{

		// 1:1 문의 상태 수정
		CsoMtmInq csoMtmInq = new CsoMtmInq();
		CsoMtmInqAns csoMtmInqAns = mtmAddDTO.getCsoMtmInqAns();
		csoMtmInq.setMtmInqSn(Long.valueOf(mtmAddDTO.getMtmInqSn()));

		// 답변대기 체크일경우 답변상태는 답변대기 아니면 답변 완료
		if(mtmAddDTO.getCheckAnsWait() != null && mtmAddDTO.getCheckAnsWait().equals("Y")){
			csoMtmInq.setAnsStatCd(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_WAIT.toString());
		}else if(CsoCnsltEnum.cnsltStat.TMPR_SAVE.toString().equals(mtmAddDTO.getCsoCnslt().getCnsltStatCd())){
			// 임시 저장일 경우 답변 상태는 답변 전으로 설정한다
			csoMtmInq.setAnsStatCd(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_BF.toString());
		}else if(CsoCnsltEnum.cnsltStat.PRCS_WAIT.toString().equals(mtmAddDTO.getCsoCnslt().getCnsltStatCd())){
			// 처리대기일 경우 답변 상태는 답변 전으로 설정한다
			csoMtmInq.setAnsStatCd(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_BF.toString());
		}else{
			csoMtmInq.setAnsStatCd(CsoMtmInqAnsEnum.detailAnsStatCd.ANS_COMPT.toString());
		}

		csoMtmInq.setUdterId(BOSecurityUtil.getLoginId());

		mtmRepository.updateCsoMtmInq(csoMtmInq);

		// 1:1 문의 답변 수정
		csoMtmInqAns.setMtmInqSn(csoMtmInq.getMtmInqSn());
		csoMtmInqAns.setDetailAnsStatCd(csoMtmInq.getAnsStatCd());
		csoMtmInqAns.setAnsAdminId(BOSecurityUtil.getLoginId());
		csoMtmInqAns.setUdterId(BOSecurityUtil.getLoginId());
		mtmRepository.upddateCsoMtmInqAns(csoMtmInqAns);

		//상담이력 상세 수정
		CsoCnsltDetail csoCnsltDetail = mtmAddDTO.getCsoCnsltDetail();
		csoCnsltDetail.setCnsltDetailStatCd(mtmAddDTO.getCsoCnslt().getCnsltStatCd());
		csoCnsltDetail.setUdterId(BOSecurityUtil.getLoginId());

		if(!csoCnsltDetail.getCnsltDetailStatCd().equals(CsoCnsltDetailEnum.cnsltDetailStat.TMPR_SAVE.toString())){

			if(csoMtmInqAns.getAnsCont() != null && !"".equals(csoMtmInqAns.getAnsCont())){
				csoCnsltDetail.setCnsltDetailCont(csoMtmInqAns.getAnsCont());
			}else{
				csoCnsltDetail.setCnsltDetailCont(csoCnsltDetail.getCnsltDetailCont().replaceAll("(\r\n|\n)", "<br />"));
			}

		}

		counselRepository.updateCounselDetail(csoCnsltDetail);

		//상담이력 상태 수정
		CsoCnslt csoCnslt = mtmAddDTO.getCsoCnslt();
		csoCnslt.setCnsltSn(csoCnsltDetail.getCnsltSn());
		csoCnslt.setCnsltStatCd(csoCnsltDetail.getCnsltDetailStatCd());
		csoCnslt.setUdterId(BOSecurityUtil.getLoginId());
		counselRepository.updateCounsel(csoCnslt);

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

		if ("Y".equals(mtmAddDTO.getTransSaveYn())) {
			CsoCnsltTrans csoCnsltTrans = mtmAddDTO.getCsoCnsltTrans();

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

			CsoCnsltTransOrdGod csoCnsltTransOrdGod = mtmAddDTO.getCsoCnsltTransOrdGod();

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
		if ("Y".equals(mtmAddDTO.getPromiseCallSaveYn())){
			CsoPromscl csoPromscl = mtmAddDTO.getCsoPromscl();

			csoPromscl.setPromsclStatCd(CsoPromsclEnum.promsclStatCd.PROMS_WAIT.toString());
			csoPromscl.setPromsclConfigYn(CsoPromsclEnum.promsclConfigYn.Y.toString());
			csoPromscl.setChrgerCnfirmYn(CsoPromsclEnum.chrgerCnfirmYn.N.toString());

			String[] phoneNum = makePhoneNumber(mtmAddDTO.getCsoPromsclTel());
			csoPromscl.setPromsclNationNo(phoneNum[0]);
			csoPromscl.setPromsclAreaNo(phoneNum[1]);
			csoPromscl.setPromsclTlofNo(phoneNum[2]);
			csoPromscl.setPromsclTlofWthnNo(phoneNum[3]);

			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date promscDt = transFormat.parse(mtmAddDTO.getCsoPromsclDate());
			csoPromscl.setPromsclDt(promscDt);

			csoPromscl.setCnsltSn(csoCnsltDetail.getCnsltSn());
			csoPromscl.setCnsltDetailTurn(csoCnsltDetail.getCnsltDetailTurn());

			csoPromscl.setRegtrId(BOSecurityUtil.getLoginId());
			csoPromscl.setUdterId(BOSecurityUtil.getLoginId());

			counselRepository.insertCsoPromsclWithGenSn(csoPromscl);
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
}
