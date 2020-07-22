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
package com.plgrim.ncp.cmp.callcenter.bo.counsel;

import com.plgrim.ncp.base.entities.datasource1.cso.*;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminStplatAgr;
import com.plgrim.ncp.base.entities.datasource1.sys.SysStplat;
import com.plgrim.ncp.base.enums.CounselTransEnum;
import com.plgrim.ncp.base.enums.CounselTransferBoardEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefJobCd;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefJobDetail;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefSectCd;
import com.plgrim.ncp.biz.callcenter.data.*;
import com.plgrim.ncp.biz.callcenter.repository.ChatInqRepository;
import com.plgrim.ncp.biz.callcenter.repository.CounselRepository;
import com.plgrim.ncp.biz.callcenter.result.ChatInquiryResult;
import com.plgrim.ncp.biz.callcenter.service.*;
import com.plgrim.ncp.biz.member.result.MbrExtendResult;
import com.plgrim.ncp.biz.member.service.MemberBaseSelectService;
import com.plgrim.ncp.biz.member.service.MemberBenefitSelectService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoCommandService;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.result.OrderBoResult;
import com.plgrim.ncp.biz.order.service.OrderBoSelectService;
import com.plgrim.ncp.cloudgateway.httpclientproxy.HttpClientProxyClient;
import com.plgrim.ncp.cloudgateway.httpclientproxy.HttpProxyRequest;
import com.plgrim.ncp.cloudgateway.httpclientproxy.HttpProxyResponse;
import com.plgrim.ncp.cmp.callcenter.bo.CallcenterCounselBOComponent;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.utils.NcpCryptoStandalone;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Transactional(value = "transactionManager")
@Component
public class CallcenterCounselBOComponentImpl implements CallcenterCounselBOComponent {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    @Autowired
    CounselService counselService;

    @Autowired
    CounselTransferService counselTransferService;

    @Autowired
    OrderBoSelectService orderBoSelectService;

    @Autowired
    CounselTransferBoardService counselTransferBoardService;

    @Autowired
    MemberBiasService memberBiasService;

    @Autowired
    CsTemplateService csTemplateService;

    @Autowired
    MemberGoodsQnaService memberGoodsQnaService;


    @Autowired
    MemberPersonalInfoCommandService memberPersonalInfoCommandService;

    @Autowired
    MemberBaseSelectService memberBaseSelectService;

    @Autowired
    MemberBenefitSelectService memberBenefitSelectService;

    @Autowired
    CallbackService callbackService;

    @Autowired
    CounselRepository counselRepository;

    /**
     * 관리자 약관 service.
     */
    @Autowired
    CsoAdminStplatService csoAdminStplatService;

    @Autowired
    MainCounselService mainCounselService;

    @Autowired
    AlarmService alarmService;

    @Autowired
    ChatInqRepository chatInqRepository;

    @Autowired
    private HttpClientProxyClient client;

    @Autowired
    MemoService memoService;


    // 암복호화 key
    @Value("${ncp_web_bo.cs.callcenter.plgrimtalk.key}")
    private String encryptKey;

    // 암복호화 salt
    @Value("${ncp_web_bo.cs.callcenter.plgrimtalk.salt}")
    private String encryptSalt;

    // 추가작업여부갱신 호출 url
    @Value("${ncp_web_bo.cs.callcenter.plgrimtalk.update.work}")
    private String updateUrl;

    // 추가작업여부갱신 호출 url
    @Value("${ncp_web_bo.cs.callcenter.plgrimtalk.update.work.test}")
    private String updateTestUrl;


    @Value("${ncp_web_bo.cs.callcenter.plgrimtalk.command}")
    private String command;

    @Value("${ncp_web_bo.cs.callcenter.plgrimtalk.serviceType}")
    private String serviceType;

    @Value("${ncp_web_bo.cs.callcenter.plgrimtalk.surveyType}")
    private String surveyType;

    @Value("${ncp_web_bo.cs.callcenter.plgrimtalk.option35}")
    private String option35;

    @Value("${ncp_base.plgrimtalk.test.mbrNo}")
    private String testMbrNoList;

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

    /* (non-Javadoc)
     * @see com.plgrim.ncp.cmp.callcenter.bo.CallcenterCounselBOComponent#insertCounsel(com.plgrim.ncp.biz.callcenter.data.CounselDTO)
     */
    @Override
    public void insertCounsel(CounselDTO counselDTO, SystemPK systemPK) throws Exception {


        List<OrderBoResult> godList = new ArrayList<OrderBoResult>();
        String[] ogArr;
        List<OrderBoResult> selectedGodList = new ArrayList<OrderBoResult>();

        // 고객서비스 상담 주문 상품 정보 조회
        boolean hasOrdGod = counselDTO.getCsoCnsltOrdGod() != null ? true : false;

        if (hasOrdGod) {
            if (!"".equals(counselDTO.getCsoCnsltOrdGod().getOrdNo()) && counselDTO.getCsoCnsltOrdGod().getOrdNo() != null) {// 주문번호가 있을 때
                OrderBoDTO orderBoDTO = new OrderBoDTO();
                orderBoDTO.setOrdNo(counselDTO.getCsoCnsltOrdGod().getOrdNo());
                godList = orderBoSelectService.selectBOGodList(orderBoDTO);
            }

            if (counselDTO.getOrdGodTurns() != null && !"".equals(counselDTO.getOrdGodTurns())) {
                ogArr = counselDTO.getOrdGodTurns().split("-");

                for (int i = 0; i < godList.size(); i++) {
                    for (int j = 0; j < ogArr.length; j++) {
                        if (godList.get(i).getOrdGodTurn().equals(Integer.valueOf(ogArr[j]))) {
                            selectedGodList.add(godList.get(i));
                        }
                    }
                }

                godList.clear();
                godList = selectedGodList;
            }
        }

        // 고객 서비스 상담 등록
        counselService.insertCsoCnslt(counselDTO);

        // 고객서비스 상담 주문 상품 등록
        if (hasOrdGod) {
            if (!"".equals(counselDTO.getCsoCnsltOrdGod().getOrdNo()) && counselDTO.getCsoCnsltOrdGod().getOrdNo() != null) {// 주문번호가 있을 때

                CsoCnsltOrdGod csoCnsltOrdGod = counselDTO.getCsoCnsltOrdGod();

                for (int i = 0; i < godList.size(); i++) {
                    csoCnsltOrdGod.setOrdGodTurn(godList.get(i).getOrdGodTurn());
                    csoCnsltOrdGod.setGodNm(godList.get(i).getGodNm());
                    counselService.insertCsoCnsltOrdGod(counselDTO);
                }
            }
        }


        //고객 서비스 상세 등록
        counselService.insertCsoCnsltDetail(counselDTO);


        // 고객서비스 상담 상세 주문 상품 등록
        if (hasOrdGod) {
            if (!"".equals(counselDTO.getCsoCnsltOrdGod().getOrdNo()) && counselDTO.getCsoCnsltOrdGod().getOrdNo() != null) { // 주문번호가 있을 때

                for (int i = 0; i < godList.size(); i++) {
                    CsoCnsltDetailOrdGod csoCnsltDetailOrdGod = new CsoCnsltDetailOrdGod();
                    csoCnsltDetailOrdGod.setOrdGodTurn(godList.get(i).getOrdGodTurn());
                    csoCnsltDetailOrdGod.setGodNm(godList.get(i).getGodNm());

                    counselDTO.setCsoCnsltDetailOrdGod(csoCnsltDetailOrdGod);

                    counselService.insertCsoCnsltDetailOrdGod(counselDTO);
                }
            }
        }


        // 고객서비스 상담 상세 업무 유형 등록
        counselService.insertCsoCnsltDetailJobTp(counselDTO);

        // 고객서비스 상담 상세 이력 등록
        counselService.insertCsoCnsltDetailHist(counselDTO);

        // 상담이관 등록
		/*if(!"".equals(counselDTO.getCsoCnsltTrans().getTransTgtCd()) && counselDTO.getCsoCnsltTrans().getTransTgtCd() != null){
			counselService.insertCsoCnsltTrans(counselDTO);
		}*/

        // 상담 이관을 저장 했을 때
        if ("Y".equals(counselDTO.getTransSaveYn())) {

            // 상담 이관 등록
            counselService.insertCsoCnsltTrans(counselDTO);

            // 상담 이관 주문 상품 등록
            for (int i = 0; i < godList.size(); i++) {

                CsoCnsltTransOrdGod csoCnsltTransOrdGod = new CsoCnsltTransOrdGod();
                csoCnsltTransOrdGod.setOrdGodTurn(godList.get(i).getOrdGodTurn());
                csoCnsltTransOrdGod.setGodNm(godList.get(i).getGodNm());

                counselDTO.setCsoCnsltTransOrdGod(csoCnsltTransOrdGod);
                counselService.insertCsoCnsltTransOrdGod(counselDTO);
            }

            // 상담 이관 이력 등록
            counselService.insertCsoCnsltTransHist(counselDTO);

            // 업무 요청 등록
            if (counselDTO.getCsoCnsltTrans().getTransTgtCd().equals(CounselTransEnum.transTgt.JOB_TRANS.toString())) {
                counselService.inserCsoJobRequst(counselDTO);

                for (int i = 0; i < godList.size(); i++) {

                    CsoJobRequstOrdGod csoJobRequstOrdGod = new CsoJobRequstOrdGod();
                    csoJobRequstOrdGod.setOrdGodTurn(godList.get(i).getOrdGodTurn());
                    csoJobRequstOrdGod.setGodNm(godList.get(i).getGodNm());

                    counselDTO.setCsoJobRequstOrdGod(csoJobRequstOrdGod);
                    counselService.insertCsoJobRequstOrdGod(counselDTO);
                }
            }
        }


        if ("Y".equals(counselDTO.getPromiseCallSaveYn())) {
            // 약속콜 등록
            counselService.insertCsoPromscl(counselDTO);
        }

        String[][] usefCdInfo = { // 구분, 업무, 업무상세
                {UsefSectCd.PSNL_INFO_STTUS.toString(), UsefJobCd.CSTMR_CNSL_REPAIR.toString(), UsefJobDetail.CSTMR_CNSL_REPAIR.toString()}
        };


        String[] target = {counselDTO.getMbrNo()};
        memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK, usefCdInfo, target, null, null, BOSecurityUtil.getLoginId());


    }

    public String[] makePhoneNumber(String phoneNum) throws Exception {
        String[] totalArr = new String[4];
        totalArr[0] = "82";
        String[] tempNum = phoneNum.split("-");
        for (int i = 0; i < tempNum.length; i++) {
            totalArr[i + 1] = tempNum[i];
        }
        return totalArr;
    }

    @Override
    public void insertCounselTransfer(CounselTransferDTO counselTransferDTO) throws Exception {
        counselTransferService.insertCounselTransfer(counselTransferDTO);
    }

    @Override
    public void insertFastUrl(List<FastUrlGridDTO> createList, List<FastUrlGridDTO> updateList) throws Exception {

        counselService.insertFastUrl(createList);

        counselService.updateFastUrl(updateList);


    }

    @Override
    public void deleteFastUrl(List<FastUrlGridDTO> deleteList) throws Exception {

        counselService.deleteFastUrl(deleteList);
    }

	/*@Override
    public int addInqryResponseCnslt(MtmAddDTO addDTO) {
	    // TODO Auto-generated method stub
		int resultCnt = mtmService.addInqryResponseCnslt(addDTO);
	    return resultCnt;
    }*/

    @Override
    public void insertCounselTransferBoard(CounselTransferBoardDTO counselTransferBoardDTO, List<MultipartFile> files) throws Exception {


        //업무 이관 게시판 등록
        counselTransferBoardService.insertCounselTransferBoard(counselTransferBoardDTO);

        // 업무 이관 게시판 주문 상품 등록
        counselTransferBoardService.insertCounselTransferBoardOrdGod(counselTransferBoardDTO);

        //업무 이관 게시판 첨부파일 등록
        if (files.size() > 0) {
            counselTransferBoardService.insertCounselTransferBoardAtchmnfl(counselTransferBoardDTO, files);
        }
		
		/*
		업무 이관 게시판 답변 등록
		counselTransferBoardService.insertCounselTransferBoardAns(counselTransferBoardDTO);
		 */

    }

    @Override
    public void insertCounselTransferBoardAns(CounselTransferBoardDTO counselTransferBoardDTO, List<MultipartFile> files) throws Exception {


        // 업무 이관 게시판 답변 등록
        counselTransferBoardService.insertCounselTransferBoardAns(counselTransferBoardDTO);

        // 업무 이관 게시판 첨부 파일 등록
        if (files.size() > 0) {
            counselTransferBoardService.insertCounselTransferBoardAnsAtch(counselTransferBoardDTO, files);

        }

        // 업무 이관 게시판 상태 변경 및 담당자 변경시

        CsoJobRequst csoJobRequst = counselTransferBoardDTO.getCsoJobRequst();

        if ((CounselTransferBoardEnum.transStat.TRANS_COMPT.toString()).equals(csoJobRequst.getTransStatCd()) || (!"".equals(csoJobRequst.getRequstRceptAdminId()) && csoJobRequst.getRequstRceptAdminId() != null)) {

            counselTransferBoardService.updateCounselTransferBoard(csoJobRequst);

            // 해당 업무이관에 상담번호가 있을 경우 상담 이관 게시판에 상태를 변경해준다.
            if ((csoJobRequst.getCnsltSn() != null && "".equals(csoJobRequst.getCnsltSn()))
                    && (csoJobRequst.getCnsltDetailTurn() != null && "".equals(csoJobRequst.getCnsltDetailTurn()))
                    && (csoJobRequst.getTransRequstTurn() != null && "".equals(csoJobRequst.getTransRequstTurn()))) {

                CsoCnsltTrans csoCnsltTrans = new CsoCnsltTrans();
                csoCnsltTrans.setCnsltSn(csoJobRequst.getCnsltSn());
                csoCnsltTrans.setCnsltDetailTurn(csoJobRequst.getCnsltDetailTurn());
                csoCnsltTrans.setTransRequstTurn(csoJobRequst.getTransRequstTurn());
                csoCnsltTrans.setTransStatCd(csoJobRequst.getTransStatCd());
                csoCnsltTrans.setTransRecrId(csoJobRequst.getRequstRceptAdminId());
                csoCnsltTrans.setUdterId(BOSecurityUtil.getLoginId());
                counselTransferService.updateCounselTransfer(csoCnsltTrans);

                //상담이관 게시판 변경 이력
				/*CsoCnsltTransHist csoCnsltTransHist = new CsoCnsltTransHist();
				csoCnsltTransHist.setCnsltSn(csoJobRequst.getCnsltSn());
				csoCnsltTransHist.setCnsltDetailTurn(csoJobRequst.getCnsltDetailTurn());
				csoCnsltTransHist.setTransRequstTurn(csoJobRequst.getTransRequstTurn());
				counselTransferService.insertCounselTransferHist();*/

            }


        }

    }

    @Override
    public void updateCounselTransferBoardAns(CounselTransferBoardDTO counselTransferBoardDTO, List<MultipartFile> files) throws Exception {

        // 업무 이관 게시판 답변 수정
        counselTransferBoardService.updateCounselTransferBoardAns(counselTransferBoardDTO);

        // 업무 이관 게시판 첨부 파일 등록
        if (files.size() > 0) {
            counselTransferBoardService.insertCounselTransferBoardAnsAtch(counselTransferBoardDTO, files);
        }

        // 업무 이관 게시판 상태 변경 및 담당자 변경시
        CsoJobRequst csoJobRequst = counselTransferBoardDTO.getCsoJobRequst();

        if (((CounselTransferBoardEnum.transStat.TRANS_COMPT.toString()).equals(csoJobRequst.getTransStatCd())) || (!"".equals(csoJobRequst.getRequstRceptAdminId()) && csoJobRequst.getRequstRceptAdminId() != null)) {

            counselTransferBoardService.updateCounselTransferBoard(csoJobRequst);

            // 해당 업무이관에 상담번호가 있을 경우 상담 이관 게시판에 상태를 변경해준다.
            if ((csoJobRequst.getCnsltSn() != null && "".equals(csoJobRequst.getCnsltSn()))
                    && (csoJobRequst.getCnsltDetailTurn() != null && "".equals(csoJobRequst.getCnsltDetailTurn()))
                    && (csoJobRequst.getTransRequstTurn() != null && "".equals(csoJobRequst.getTransRequstTurn()))) {

                CsoCnsltTrans csoCnsltTrans = new CsoCnsltTrans();
                csoCnsltTrans.setCnsltSn(csoJobRequst.getCnsltSn());
                csoCnsltTrans.setCnsltDetailTurn(csoJobRequst.getCnsltDetailTurn());
                csoCnsltTrans.setTransRequstTurn(csoJobRequst.getTransRequstTurn());
                csoCnsltTrans.setTransStatCd(csoJobRequst.getTransStatCd());
                csoCnsltTrans.setTransRecrId(csoJobRequst.getRequstRceptAdminId());
                csoCnsltTrans.setUdterId(BOSecurityUtil.getLoginId());
                counselTransferService.updateCounselTransfer(csoCnsltTrans);

            }

        }
    }

    @Override
    public MbrExtendResult selectMemberInfo(String loginId, String memberId, boolean b, String mbrNo) throws Exception {
        return memberBaseSelectService.selectMemberForCS(loginId, memberId, false, mbrNo);
    }

    @Override
    public long getMemberReserveRemainAmount(String erpNo) throws Exception {
        return memberBenefitSelectService.getMemberReserveRemainAmount(erpNo);
    }

    @Override
    public void insertMemberBias(MemberBiasDTO memberBiasDTO) throws Exception {
        memberBiasService.insertMemberBias(memberBiasDTO);

    }

    @Override
    public void updateMemberBias(List<MemberBiasGridDTO> createList, List<MemberBiasGridDTO> updateList) throws Exception {
        memberBiasService.updateMemberBias(updateList);
    }

    @Override
    public void insertCsTemplate(CsTemplateDTO csTemplateDTO) throws Exception {

        csTemplateService.insertCsTemplate(csTemplateDTO);

    }

    @Override
    public void updateCsTemplateGrid(List<CsTemplateGridDTO> updateList) throws Exception {
        csTemplateService.updateCsTemplateGrid(updateList);
    }

    @Override
    public void updateCsTemplate(CsTemplateDTO csTemplateDTO) throws Exception {
        csTemplateService.updateCsTemplate(csTemplateDTO);
    }

    @Override
    public void insertEmailSmsTemplate(EmailSmsTemplateDTO emailSmsTemplateDTO) throws Exception {
        csTemplateService.insertEmailSmsTemplate(emailSmsTemplateDTO);
    }

    @Override
    public void updateEmailsmsTemplate(EmailSmsTemplateDTO emailSmsTemplateDTO) throws Exception {
        csTemplateService.updateEmailsmsTemplate(emailSmsTemplateDTO);
    }

    @Override
    public void updateEmailSmsTemplateGrid(List<EmailSmsTemplateGridDTO> updateList) throws Exception {
        csTemplateService.updateEmailSmsTemplateGrid(updateList);
    }

    /**
     * 개인정보 수정
     */
    @Override
    public void updatePersonalInfo(PersonalInfoDTO personalInfoDTO) throws Exception {

        // 비밀번호 암호화

        String pw = personalInfoDTO.getMbrPw();
        if (pw != null && !"".equals(pw)) {
            String encPw = IdGenService.generateSHA256(pw);
            personalInfoDTO.setMbrPw(encPw);
        }

        if (personalInfoDTO.getPhoneNumber() != null && !"".equals(personalInfoDTO.getPhoneNumber())) {
            String phone[] = personalInfoDTO.getPhoneNumber().split("-");
            personalInfoDTO.setMobilNationNo("82");
            personalInfoDTO.setMobilAreaNo(phone[0]);
            personalInfoDTO.setMobilTlofNo(phone[1]);
            personalInfoDTO.setMobilTlofWthnNo(phone[2]);
        }

        // 개인정보 수정
        counselService.updatePersonalInfo(personalInfoDTO);

    }

    @Override
    public void updateCallbackGrid(List<CallbackGridDTO> updateList) throws Exception {
        callbackService.updateCallbackGrid(updateList);


    }

    @Override
    public void updateCounselStatCompt(CsoCnslt csoCnslt) throws Exception {
        counselService.updateCounselStatCompt(csoCnslt);

        CsoCnsltDetail csoCnsltDetail = new CsoCnsltDetail();
        csoCnsltDetail.setCnsltSn(csoCnslt.getCnsltSn());
        csoCnsltDetail.setCnsltDetailStatCd(csoCnslt.getCnsltStatCd());
        csoCnsltDetail.setUdterId(BOSecurityUtil.getLoginId());
        counselService.updateCounseldetailStatCompt(csoCnsltDetail);

    }


    @Override
    public void updateTransStatCompt(CsoCnsltTrans csoCnsltTrans) throws Exception {
        counselService.updateTransStatCompt(csoCnsltTrans);
    }

    @Override
    public void updatePromsclStatCompt(CsoPromscl csoPromscl) throws Exception {
        counselService.updatePromsclStatCompt(csoPromscl);
    }

    @Override
    public void insertCounselInDetail(CounselDTO counselDTO) throws Exception {

        String cnsltSn = counselDTO.getCnsltSn();
        List<CsoCnsltDetailOrdGod> csoCnsltDetailOrdGod = counselRepository.selectCsoCnsltDetailOrdGod(Long.parseLong(cnsltSn));

        counselDTO.getCsoCnslt().setCnsltSn(Long.parseLong(cnsltSn));

        //채팅번호가 존재하면 채팅상담
        long sn = Long.valueOf(cnsltSn).longValue();
        String chatInqSn = chatInqRepository.selectChatSn(sn);
        //고객 서비스 상세 등록

        counselService.insertCsoCnsltDetail(counselDTO);

        // 상담 전체 상태 업데이트
        counselService.updateCsoCnsltStat(counselDTO);

        for (int i = 0; i < csoCnsltDetailOrdGod.size(); i++) {
            counselDTO.setCsoCnsltDetailOrdGod(csoCnsltDetailOrdGod.get(i));
            counselService.insertCsoCnsltDetailOrdGod(counselDTO);
        }

        // 고객서비스 상담 상세 업무 유형 등록
        counselService.insertCsoCnsltDetailJobTp(counselDTO);

        // 고객서비스 상담 상세 이력 등록
        counselService.insertCsoCnsltDetailHist(counselDTO);

        // 상담 이관을 저장 했을 때
        if ("Y".equals(counselDTO.getTransSaveYn())) {

            // 상담 이관 등록
            counselService.insertCsoCnsltTrans(counselDTO);

            // 상담 이관 주문 상품 등록
            for (int i = 0; i < csoCnsltDetailOrdGod.size(); i++) {

                CsoCnsltTransOrdGod csoCnsltTransOrdGod = new CsoCnsltTransOrdGod();
                csoCnsltTransOrdGod.setOrdGodTurn(csoCnsltDetailOrdGod.get(i).getOrdGodTurn());
                csoCnsltTransOrdGod.setGodNm(csoCnsltDetailOrdGod.get(i).getGodNm());

                counselDTO.setCsoCnsltTransOrdGod(csoCnsltTransOrdGod);
                counselService.insertCsoCnsltTransOrdGod(counselDTO);
            }

            // 상담 이관 이력 등록
            counselService.insertCsoCnsltTransHist(counselDTO);

            // 업무 요청 등록
            if (counselDTO.getCsoCnsltTrans().getTransTgtCd().equals(CounselTransEnum.transTgt.JOB_TRANS.toString())) {
                counselService.inserCsoJobRequst(counselDTO);

                for (int i = 0; i < csoCnsltDetailOrdGod.size(); i++) {

                    CsoJobRequstOrdGod csoJobRequstOrdGod = new CsoJobRequstOrdGod();
                    csoJobRequstOrdGod.setOrdGodTurn(csoCnsltDetailOrdGod.get(i).getOrdGodTurn());
                    csoJobRequstOrdGod.setGodNm(csoCnsltDetailOrdGod.get(i).getGodNm());

                    counselDTO.setCsoJobRequstOrdGod(csoJobRequstOrdGod);
                    counselService.insertCsoJobRequstOrdGod(counselDTO);
                }
            }
        }

        if ("Y".equals(counselDTO.getPromiseCallSaveYn())) {
            // 약속콜 등록
            counselService.insertCsoPromscl(counselDTO);
        }

    }

    public String encryptData(String data, boolean replaceFlag) {
        NcpCryptoStandalone ncp = new NcpCryptoStandalone(encryptKey);
        String eData = ncp.encryptBase64(data, encryptSalt, replaceFlag);
        return eData;
    }

    @Override
    public void deleteCounselGrid(List<CounselGridDTO> deleteList) throws Exception {
        counselService.deleteCounselGrid(deleteList);
    }

    /**
     * 관리자 개인정보 동의 이력 조회
     *
     * @param systemPK [설명]
     * @param sasa     [설명]
     * @throws Exception the exception
     * @since 2018. 7. 25
     */
    @Override
    public SysAdminStplatAgr selectSysAdminStplatAgr(SystemPK systemPK, SysAdminStplatAgr sasa) throws Exception {
    	return csoAdminStplatService.selectSysAdminStplatAgr(sasa);
    }
    
    /**
     * 관리자 개인정보 동의 이력 등록
     *
     * @param systemPK [설명]
     * @param sasa     [설명]
     * @throws Exception the exception
     * @since 2015. 6. 26
     */
    @Override
    public void insertSysAdminStplatAgr(SystemPK systemPK, SysAdminStplatAgr sasa) throws Exception {
        csoAdminStplatService.insertSysAdminStplatAgr(sasa);
    }

    @Override
    public void insertCtiCallInfo(CsoCnsltClHistDTO csoCnsltClHistDTO) {
        mainCounselService.insertCtiCallInfo(csoCnsltClHistDTO);
    }

    @Override
    public void updateDisplayYn(CsoCnsltDetail csoCnsltDetail) {
        counselService.updateDisplayYn(csoCnsltDetail);
    }

    @Override
    public void deleteTempMemo(CsoCnsltDetail csoCnsltDetail) {
        counselService.deleteTempMemo(csoCnsltDetail);

		/*if(csoCnsltDetail.getMtmInqSn() != null){
			counselService.deleteCsoMtmInq(csoCnsltDetail.getMtmInqSn());
		}else if(csoCnsltDetail.getGodInqSn() != null){
			counselService.detailCsoGodInq(csoCnsltDetail.getGodInqSn());
		}*/
    }

    @Override
    public void updateCounsel(CounselDTO counselDTO) throws Exception {
        counselService.updateCounsel(counselDTO);
    }

    @Override
    public void updateConfirmInPromiseCall(Map param) throws Exception {
        alarmService.updateConfirmInPromiseCall(param);
    }

    @Override
    public void updateConfirmInTransfer(Map param) throws Exception {
        alarmService.updateConfirmInTransfer(param);
    }

    @Override
    public void insertCallback(InfCallBackDTO infCallBackDTO) throws Exception {
        callbackService.insertCallback(infCallBackDTO);
    }

    @Override
    public void updateCsoCnsltDetailCont(CsoCnsltDetail csoCnsltDetail) throws Exception {
        memberGoodsQnaService.updateCsoCnsltDetailCont(csoCnsltDetail);
    }

    @Override
    public MbrWebpntHistExtend getPurpleCoin(MbrWebpntHist mbrWebpntHist) throws Exception {
        return memberBenefitSelectService.selectWebPointInfo(mbrWebpntHist);
    }

    @Override
    public CsoCnsltMemo getCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) {
        // TODO Auto-generated method stub
        return memoService.getCsoCnsltMemo(csoCnsltMemo);
    }

    @Override
    public void updateCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) {
        // TODO Auto-generated method stub
        memoService.updateCsoCnsltMemo(csoCnsltMemo);
    }

    @Override
    public void insertCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) throws Exception {
        // TODO Auto-generated method stub
        memoService.insertCsoCnsltMemo(csoCnsltMemo);
    }

    /**
     * 시스템 약관 조회
     *
     * @param ss [설명]
     * @return Sys stplat [설명]
     * @throws Exception the exception
     * @since 2015. 6. 26
     */
    @Override
    public SysStplat selectSysStplat(SystemPK systemPK, SysStplat ss) throws Exception {
        //return csoAdminStplatService.selectSysStplat(ss);
    	return null;
    }
    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */


}
