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
import com.plgrim.ncp.biz.callcenter.data.CounselDTO;
import com.plgrim.ncp.biz.callcenter.data.CounselGridDTO;
import com.plgrim.ncp.biz.callcenter.data.FastUrlGridDTO;
import com.plgrim.ncp.biz.callcenter.data.PersonalInfoDTO;
import com.plgrim.ncp.biz.callcenter.repository.*;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class CounselService {

	@Autowired
	private CounselRepository counselRepository;

	public void insertCsoCnslt(CounselDTO counselDTO) throws Exception{

		CsoCnslt csoCnslt = counselDTO.getCsoCnslt();

		if(counselDTO.getPhoneNumber() != null && !"".equals(counselDTO.getPhoneNumber())){
			//String[] phoneNum = makePhoneNumber(counselDTO.getPhoneNumber());
			String[] phoneNum = null;
			csoCnslt.setCstmrmobilNationNo(phoneNum[0]);
			csoCnslt.setCstmrmobilAreaNo(phoneNum[1]);
			csoCnslt.setCstmrmobilTlofNo(phoneNum[2]);
			csoCnslt.setCstmrmobilTlofWthnNo(phoneNum[3]);
		}

		if("".equals(csoCnslt.getMallId()) || csoCnslt.getMallId() == null){
			csoCnslt.setMallId(CsoCnsltEnum.mallId.DXM.toString());
		}

		if("".equals(csoCnslt.getLangCd()) || csoCnslt.getLangCd() == null){
			csoCnslt.setLangCd(CsoCnsltEnum.langCd.KOR.toString());
		}

		csoCnslt.setRegtrId(BOSecurityUtil.getLoginId());
		csoCnslt.setUdterId(BOSecurityUtil.getLoginId());

		csoCnslt.setCnsltSn(counselRepository.insertCsoCnsltWithGenSn(csoCnslt)) ;
	}

	public void insertCsoCnsltOrdGod(CounselDTO counselDTO) throws Exception{
		if( !"".equals(counselDTO.getCsoCnsltOrdGod().getOrdNo()) && counselDTO.getCsoCnsltOrdGod().getOrdNo() != null){
			//고객서비스 상담 주문 상품 등록

			CsoCnsltOrdGod csoCnsltOrdGod = counselDTO.getCsoCnsltOrdGod();
			csoCnsltOrdGod.setCnsltSn(counselDTO.getCsoCnslt().getCnsltSn());
			csoCnsltOrdGod.setOrdGodSectCd(CsoCnsltOrdGodEnum.ordGodSect.ORD.toString());
			csoCnsltOrdGod.setOrdNo(counselDTO.getCsoCnsltOrdGod().getOrdNo());
			csoCnsltOrdGod.setOrdGodTurn(counselDTO.getCsoCnsltOrdGod().getOrdGodTurn());
			csoCnsltOrdGod.setRegtrId(BOSecurityUtil.getLoginId());
			csoCnsltOrdGod.setUdterId(BOSecurityUtil.getLoginId());

			counselRepository.insertCsoCnsltOrdGodWithGenTurn(csoCnsltOrdGod);

		}
	}

	public void insertCsoCnsltDetail(CounselDTO counselDTO) throws Exception{
		CsoCnslt csoCnslt = counselDTO.getCsoCnslt();
		CsoCnsltDetail csoCnsltDetail = counselDTO.getCsoCnsltDetail();

		//고객서비스 상담 상세 세팅
		csoCnsltDetail.setCnsltSn(csoCnslt.getCnsltSn());
		csoCnsltDetail.setCnsltDetailStatCd(csoCnslt.getCnsltStatCd());
		csoCnsltDetail.setCnsltKndCd(csoCnslt.getCnsltKndCd());

		//int maxTurn = counselRepository.getMaxCsoCnsltDetail(csoCnsltDetail.getCnsltSn()) + 1;
		//csoCnsltDetail.setCnsltDetailTurn(maxTurn);
		csoCnsltDetail.setCstmrClmCd(csoCnslt.getCstmrClmCd());
		csoCnsltDetail.setCnsltDetlTrnsmisRequstYn("N");
		csoCnsltDetail.setDeleteYn("N");
		csoCnsltDetail.setCnsltDetailCont(csoCnsltDetail.getCnsltDetailCont().replaceAll("(\r\n|\n)", "<br>"));
		csoCnsltDetail.setInqTpCd(csoCnslt.getInqTpCd());
		csoCnsltDetail.setCnsltChrgerId(BOSecurityUtil.getLoginId());
		csoCnsltDetail.setRegtrId(BOSecurityUtil.getLoginId());
		csoCnsltDetail.setUdterId(BOSecurityUtil.getLoginId());

		//고객서비스 상담상세 등록
		csoCnsltDetail.setCnsltDetailTurn(counselRepository.insertCounselDetailWithGenTurn(csoCnsltDetail));
	}

	public void insertCsoCnsltDetailOrdGod(CounselDTO counselDTO) throws Exception {
		// 고객서비스 상담 상세 주문 상품 등록
		CsoCnsltDetailOrdGod csoCnsltDetailOrdGod = new CsoCnsltDetailOrdGod();
		csoCnsltDetailOrdGod.setCnsltSn(counselDTO.getCsoCnslt().getCnsltSn());
		csoCnsltDetailOrdGod.setCnsltDetailTurn(counselDTO.getCsoCnsltDetail().getCnsltDetailTurn());
		csoCnsltDetailOrdGod.setOrdGodSectCd(CsoCnsltDetailOrdGodEnum.ordGodSect.ORD.toString());
		csoCnsltDetailOrdGod.setOrdGodTurn(counselDTO.getCsoCnsltDetailOrdGod().getOrdGodTurn());
		csoCnsltDetailOrdGod.setGodNm(counselDTO.getCsoCnsltDetailOrdGod().getGodNm());

		csoCnsltDetailOrdGod.setOrdNo(counselDTO.getCsoCnsltDetailOrdGod().getOrdNo());
		if(counselDTO.getCsoCnsltOrdGod() != null){
			if(counselDTO.getCsoCnsltOrdGod().getOrdNo() != null){
				csoCnsltDetailOrdGod.setOrdNo(counselDTO.getCsoCnsltOrdGod().getOrdNo());
			}
		}

		csoCnsltDetailOrdGod.setRegtrId(BOSecurityUtil.getLoginId());
		csoCnsltDetailOrdGod.setUdterId(BOSecurityUtil.getLoginId());

		counselRepository.insertCsoCnsltDetailOrdGodWithGenTurn(csoCnsltDetailOrdGod);

	}

	public void insertCsoCnsltDetailJobTp(CounselDTO counselDTO) throws  Exception{
		//고객서비스 상담 상세 업무 유형 등록
		CsoCnsltDetailJobTp csoCnsltDetailJobTp = new CsoCnsltDetailJobTp();

		csoCnsltDetailJobTp.setCnsltSn(counselDTO.getCsoCnslt().getCnsltSn());
		csoCnsltDetailJobTp.setCnsltDetailTurn(counselDTO.getCsoCnsltDetail().getCnsltDetailTurn());
		csoCnsltDetailJobTp.setRegtrId(BOSecurityUtil.getLoginId());
		csoCnsltDetailJobTp.setUdterId(BOSecurityUtil.getLoginId());

		for(int i = 0 ; i < counselDTO.getCnsltJobTpCdArr().length; i++){
			csoCnsltDetailJobTp.setCnsltJobTpCd(counselDTO.getCnsltJobTpCdArr()[i]);
			counselRepository.insertCsoCnsltDetailJobTp(csoCnsltDetailJobTp);
		}
	}

	public void insertCsoCnsltDetailHist(CounselDTO counselDTO) throws Exception{
		//고객서비스 상담 상세 이력 등록
		CsoCnsltDetailHist csoCnsltDetailHist = new CsoCnsltDetailHist();
		csoCnsltDetailHist.setCnsltSn(counselDTO.getCsoCnslt().getCnsltSn());
		csoCnsltDetailHist.setCnsltDetailTurn(counselDTO.getCsoCnsltDetail().getCnsltDetailTurn());

		csoCnsltDetailHist.setModInfoCd(CsoCnsltDetailHistEnum.modInfo.CNSLT_DETAIL_CONT.toString());
		csoCnsltDetailHist.setModCont(counselDTO.getCsoCnsltDetail().getCnsltDetailCont());

		csoCnsltDetailHist.setModTpCd(CsoCnsltDetailHistEnum.modTp.REG.toString());

		csoCnsltDetailHist.setRegtrId(BOSecurityUtil.getLoginId());
		csoCnsltDetailHist.setUdterId(BOSecurityUtil.getLoginId());

		counselRepository.insertCsoCnsltDetailHist(csoCnsltDetailHist);

		csoCnsltDetailHist.setModInfoCd(CsoCnsltDetailHistEnum.modInfo.CNSLT_DETAIL_STAT_CD.toString());
		counselRepository.insertCsoCnsltDetailHist(csoCnsltDetailHist);
	}



	public void insertCsoPromscl(CounselDTO counselDTO) throws Exception{

		CsoPromscl csoPromscl = counselDTO.getCsoPromscl();

		csoPromscl.setPromsclStatCd(CsoPromsclEnum.promsclStatCd.PROMS_WAIT.toString());
		csoPromscl.setPromsclConfigYn(CsoPromsclEnum.promsclConfigYn.Y.toString());
		csoPromscl.setChrgerCnfirmYn(CsoPromsclEnum.chrgerCnfirmYn.N.toString());

		//String[] phoneNum = makePhoneNumber(counselDTO.getCsoPromsclTel());
		String[] phoneNum = null;
		csoPromscl.setPromsclNationNo(phoneNum[0]);
		csoPromscl.setPromsclAreaNo(phoneNum[1]);
		csoPromscl.setPromsclTlofNo(phoneNum[2]);
		csoPromscl.setPromsclTlofWthnNo(phoneNum[3]);

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date promscDt = transFormat.parse(counselDTO.getCsoPromsclDate());
		csoPromscl.setPromsclDt(promscDt);

		csoPromscl.setCnsltSn(counselDTO.getCsoCnslt().getCnsltSn());
		csoPromscl.setCnsltDetailTurn(counselDTO.getCsoCnsltDetail().getCnsltDetailTurn());

		csoPromscl.setRegtrId(BOSecurityUtil.getLoginId());
		csoPromscl.setUdterId(BOSecurityUtil.getLoginId());

		counselRepository.insertCsoPromsclWithGenSn(csoPromscl);

	}

	public void insertCsoCnsltTrans(CounselDTO counselDTO) throws Exception{

		CsoCnsltTrans csoCnsltTrans = counselDTO.getCsoCnsltTrans();

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
			//csoCnsltTrans.setTransRecrId(null);
		}

		csoCnsltTrans.setCnsltSn(counselDTO.getCsoCnslt().getCnsltSn());
		csoCnsltTrans.setCnsltDetailTurn(counselDTO.getCsoCnsltDetail().getCnsltDetailTurn());
		csoCnsltTrans.setTransRqesterId(BOSecurityUtil.getLoginId());
		csoCnsltTrans.setTransRequstCont(counselDTO.getCsoCnsltDetail().getCnsltDetailCont());
		csoCnsltTrans.setTransRecrId(counselDTO.getCsoCnsltTrans().getTransRecrId());

		csoCnsltTrans.setRegtrId(BOSecurityUtil.getLoginId());
		csoCnsltTrans.setUdterId(BOSecurityUtil.getLoginId());
		csoCnsltTrans.setChrgerCnfirmYn(CounselTransEnum.chrgerCnfirmYn.N.toString());
		csoCnsltTrans.setTransStatCd(CounselTransEnum.transStat.TRANS_WAIT.toString());

		csoCnsltTrans.setTransRequstTurn(counselRepository.insertCsoCnsltTransWithGenTurn(csoCnsltTrans));

	}

	public void insertCsoCnsltTransOrdGod(CounselDTO counselDTO) throws Exception{

		// 상담이관 주문 상품 등록
		CsoCnsltTransOrdGod csoCnsltTransOrdGod = counselDTO.getCsoCnsltTransOrdGod();
		csoCnsltTransOrdGod.setCnsltSn(counselDTO.getCsoCnslt().getCnsltSn());
		csoCnsltTransOrdGod.setCnsltDetailTurn(counselDTO.getCsoCnsltDetail().getCnsltDetailTurn());
		csoCnsltTransOrdGod.setTransRequstTurn(counselDTO.getCsoCnsltTrans().getTransRequstTurn());
		csoCnsltTransOrdGod.setOrdGodSectCd(CsoCnsltDetailOrdGodEnum.ordGodSect.ORD.toString());

		csoCnsltTransOrdGod.setOrdNo(counselDTO.getCsoCnsltDetailOrdGod().getOrdNo());
		if(counselDTO.getCsoCnsltOrdGod() != null){
			if(counselDTO.getCsoCnsltOrdGod().getOrdNo() != null){
				csoCnsltTransOrdGod.setOrdNo(counselDTO.getCsoCnsltOrdGod().getOrdNo());
			}
		}

		csoCnsltTransOrdGod.setRegtrId(BOSecurityUtil.getLoginId());
		csoCnsltTransOrdGod.setUdterId(BOSecurityUtil.getLoginId());

		counselRepository.insertCsoCnsltTransOrdGodWithGenTurn(csoCnsltTransOrdGod);

	}

	public void insertCsoCnsltTransHist(CounselDTO counselDTO) {

		// 상담이관 이력 등록

		CsoCnsltTransHist csoCnsltTransHist = new CsoCnsltTransHist();

		csoCnsltTransHist.setCnsltSn(counselDTO.getCsoCnslt().getCnsltSn());
		csoCnsltTransHist.setCnsltDetailTurn(counselDTO.getCsoCnsltDetail().getCnsltDetailTurn());
		csoCnsltTransHist.setTransRequstTurn(counselDTO.getCsoCnsltTrans().getTransRequstTurn());
		csoCnsltTransHist.setTransModInfoCd(CsoCnsltTransHistEnum.TRANS_MOD_INFO_CD.TRANS_MOD_INFO.toString());
		csoCnsltTransHist.setModTpCd(CsoCnsltTransHistEnum.MOD_TP_CD.REG.toString());
		csoCnsltTransHist.setRegtrId(BOSecurityUtil.getLoginId());
		csoCnsltTransHist.setUdterId(BOSecurityUtil.getLoginId());

		counselRepository.insertCsoCnsltTransHist(csoCnsltTransHist);

		csoCnsltTransHist.setTransModInfoCd(CsoCnsltTransHistEnum.TRANS_MOD_INFO_CD.TRANS_RECR_ID.toString());

		counselRepository.insertCsoCnsltTransHist(csoCnsltTransHist);
	}

	public void inserCsoJobRequst(CounselDTO counselDTO) throws Exception{

		CsoJobRequst csoJobRequst = new CsoJobRequst();

		csoJobRequst.setTransStatCd(CsoJobRequstEnum.transStatCd.TRANS_WAIT.toString());
		csoJobRequst.setChrgJobTpCd(counselDTO.getCsoCnsltTrans().getChrgJobTpCd());
		csoJobRequst.setTransRequstTpCd(counselDTO.getCsoCnsltTrans().getTransRequstTpCd());
		csoJobRequst.setDoiCd(counselDTO.getCsoCnsltTrans().getDoiCd());
		csoJobRequst.setRegAdminId(counselDTO.getCsoCnsltTrans().getRegtrId());
		csoJobRequst.setRequstRceptAdminId(counselDTO.getCsoCnsltTrans().getTransRecrId()); // ?
		csoJobRequst.setRequstSj(counselDTO.getCsoCnsltTrans().getTransRequstSj());
		csoJobRequst.setRequstCont(counselDTO.getCsoCnsltTrans().getTransRequstCont());
		csoJobRequst.setCnsltSn(counselDTO.getCsoCnslt().getCnsltSn());
		csoJobRequst.setCnsltDetailTurn(counselDTO.getCsoCnsltDetail().getCnsltDetailTurn());
		csoJobRequst.setTransRequstTurn(counselDTO.getCsoCnsltTrans().getTransRequstTurn());
		csoJobRequst.setRegtrId(BOSecurityUtil.getLoginId());
		csoJobRequst.setUdterId(BOSecurityUtil.getLoginId());
		csoJobRequst.setMallId(counselDTO.getCsoCnslt().getMallId());
		csoJobRequst.setLangCd(counselDTO.getCsoCnslt().getLangCd());

		csoJobRequst.setRequstSn(counselRepository.insertCsoJobRequstWithGenSn(csoJobRequst));

		counselDTO.setCsoJobRequst(csoJobRequst);

	}

	public void insertCsoJobRequstOrdGod(CounselDTO counselDTO) throws Exception{

		CsoJobRequstOrdGod csoJobRequstOrdGod = new CsoJobRequstOrdGod();

		csoJobRequstOrdGod.setRequstSn(counselDTO.getCsoJobRequst().getRequstSn());
		csoJobRequstOrdGod.setOrdGodSectCd(CsoJobRequstOrdGodEnum.ordGodSectCd.ORD.toString());

		csoJobRequstOrdGod.setOrdNo(counselDTO.getCsoCnsltDetailOrdGod().getOrdNo());
		if(counselDTO.getCsoCnsltOrdGod() != null){
			if(counselDTO.getCsoCnsltOrdGod().getOrdNo() != null){
				csoJobRequstOrdGod.setOrdNo(counselDTO.getCsoCnsltOrdGod().getOrdNo());
			}
		}

		csoJobRequstOrdGod.setRegtrId(BOSecurityUtil.getLoginId());
		csoJobRequstOrdGod.setUdterId(BOSecurityUtil.getLoginId());

		counselRepository.insertCsoJobRequstOrdGodWithGenTurn(csoJobRequstOrdGod);
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

	public void insertFastUrl(List<FastUrlGridDTO> createList) {
		Iterator<FastUrlGridDTO> iterator = createList.iterator();
		while(iterator.hasNext()) {
			counselRepository.insertCsoGlan(iterator.next());
		}
	}

	public void updateFastUrl(List<FastUrlGridDTO> updateList) {
		Iterator<FastUrlGridDTO> iterator = updateList.iterator();
		while(iterator.hasNext()) {
			counselRepository.updateCsoGlan(iterator.next());
		}

	}

	public void deleteFastUrl(List<FastUrlGridDTO> deleteList) {
		Iterator<FastUrlGridDTO> iterator = deleteList.iterator();
		while(iterator.hasNext()) {
			counselRepository.deleteFastUrl(iterator.next());
		}
	}

	public void updatePersonalInfo(PersonalInfoDTO personalInfoDTO) {
		counselRepository.updatePersonalInfo(personalInfoDTO);
	    
    }


	public void updateCounselStatCompt(CsoCnslt csoCnslt) {

		csoCnslt.setCnsltStatCd(CsoCnsltEnum.cnsltStat.PROC_COMPT.toString());
		csoCnslt.setUdterId(BOSecurityUtil.getLoginId());

		counselRepository.updateCounselStatCompt(csoCnslt);
	}

	public void updateCounseldetailStatCompt(CsoCnsltDetail csoCnsltDetail) {
		counselRepository.updateCounselDetailStatCompt(csoCnsltDetail);
	}

	public void updateTransStatCompt(CsoCnsltTrans csoCnsltTrans) {
		csoCnsltTrans.setTransStatCd(CsoCnsltTransEnum.transStat.TRANS_COMPT.toString());
		csoCnsltTrans.setUdterId(BOSecurityUtil.getLoginId());

		counselRepository.updateTransStatCompt(csoCnsltTrans);
	}

	public void updatePromsclStatCompt(CsoPromscl csoPromscl) {
		csoPromscl.setPromsclStatCd(CsoPromsclEnum.promsclStatCd.PROMS_COMPT.toString());
		csoPromscl.setUdterId(BOSecurityUtil.getLoginId());
		counselRepository.updatePromsclStatCompt(csoPromscl);
	}

	public void deleteCounselGrid(List<CounselGridDTO> deleteList) {

		Iterator<CounselGridDTO> iterator = deleteList.iterator();
		while(iterator.hasNext()) {
			counselRepository.deleteCounselGrid(iterator.next());
		}

	}

	public void updateDisplayYn(CsoCnsltDetail csoCnsltDetail) {
		counselRepository.updateDisplayYn(csoCnsltDetail);
	}

	public void deleteTempMemo(CsoCnsltDetail csoCnsltDetail) {
		counselRepository.deleteTempMemo(csoCnsltDetail);
	}

	public void updateCounsel(CounselDTO counselDTO) throws Exception {

		CsoCnsltDetail csoCnsltDetail = counselDTO.getCsoCnsltDetail();
		csoCnsltDetail.setUdterId(BOSecurityUtil.getLoginId());

		counselRepository.updateCounselDetail(csoCnsltDetail);


		//고객서비스 상담 상세 이력 등록
		CsoCnsltDetailHist csoCnsltDetailHist = new CsoCnsltDetailHist();
		csoCnsltDetailHist.setCnsltSn(csoCnsltDetail.getCnsltSn());
		csoCnsltDetailHist.setCnsltDetailTurn(csoCnsltDetail.getCnsltDetailTurn());

		csoCnsltDetailHist.setModInfoCd(CsoCnsltDetailHistEnum.modInfo.CNSLT_DETAIL_CONT.toString());
		csoCnsltDetailHist.setModCont(csoCnsltDetail.getCnsltDetailCont());
		csoCnsltDetailHist.setUdterId(BOSecurityUtil.getLoginId());
		csoCnsltDetailHist.setModTpCd(CsoCnsltDetailHistEnum.modTp.UDT.toString());

		counselRepository.insertCsoCnsltDetailHist(csoCnsltDetailHist);

		csoCnsltDetailHist.setModInfoCd(CsoCnsltDetailHistEnum.modInfo.CNSLT_DETAIL_STAT_CD.toString());
		counselRepository.insertCsoCnsltDetailHist(csoCnsltDetailHist);


		// 상담 이관 등록

		if ("Y".equals(counselDTO.getTransSaveYn())) {
			CsoCnsltTrans csoCnsltTrans = counselDTO.getCsoCnsltTrans();

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

			CsoCnsltTransOrdGod csoCnsltTransOrdGod = counselDTO.getCsoCnsltTransOrdGod();

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
		if ("Y".equals(counselDTO.getPromiseCallSaveYn())){
			CsoPromscl csoPromscl = counselDTO.getCsoPromscl();

			csoPromscl.setPromsclStatCd(CsoPromsclEnum.promsclStatCd.PROMS_WAIT.toString());
			csoPromscl.setPromsclConfigYn(CsoPromsclEnum.promsclConfigYn.Y.toString());
			csoPromscl.setChrgerCnfirmYn(CsoPromsclEnum.chrgerCnfirmYn.N.toString());

			//String[] phoneNum = makePhoneNumber(counselDTO.getCsoPromsclTel());
			String[] phoneNum = null;
			csoPromscl.setPromsclNationNo(phoneNum[0]);
			csoPromscl.setPromsclAreaNo(phoneNum[1]);
			csoPromscl.setPromsclTlofNo(phoneNum[2]);
			csoPromscl.setPromsclTlofWthnNo(phoneNum[3]);

			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date promscDt = transFormat.parse(counselDTO.getCsoPromsclDate());
			csoPromscl.setPromsclDt(promscDt);

			csoPromscl.setCnsltSn(csoCnsltDetail.getCnsltSn());
			csoPromscl.setCnsltDetailTurn(csoCnsltDetail.getCnsltDetailTurn());

			csoPromscl.setRegtrId(BOSecurityUtil.getLoginId());
			csoPromscl.setUdterId(BOSecurityUtil.getLoginId());

			counselRepository.insertCsoPromsclWithGenSn(csoPromscl);
		}

	}

	public void deleteCsoMtmInq(Long mtmInqSn) {
		counselRepository.deleteCsoMtmInq(mtmInqSn);
	}

	public void updateCsoCnsltStat(CounselDTO counselDTO) {

		CsoCnsltDetail csoCnsltDetail = counselDTO.getCsoCnsltDetail();

		if(!CsoCnsltDetailEnum.cnsltDetailStat.TMPR_SAVE.toString().equals(csoCnsltDetail.getCnsltDetailStatCd())){
			csoCnsltDetail.setUdterId(BOSecurityUtil.getLoginId());
			counselRepository.updateCsoCnsltDetailStat(csoCnsltDetail);

			CsoCnslt csoCnslt = new CsoCnslt();
			csoCnslt.setCnsltSn(csoCnsltDetail.getCnsltSn());
			csoCnslt.setUdterId(BOSecurityUtil.getLoginId());
			csoCnslt.setCnsltStatCd(csoCnsltDetail.getCnsltDetailStatCd());

			counselRepository.updateCsoCnsltStat(csoCnslt);
		}

	}

	public void detailCsoGodInq(Long godInqSn) {
		counselRepository.deleteCsoGodInq(godInqSn);
	}
}
