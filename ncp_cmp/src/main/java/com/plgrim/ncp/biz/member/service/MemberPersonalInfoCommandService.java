package com.plgrim.ncp.biz.member.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoUsef;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoUsefJobDetail;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoUsefMbd;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoUsefTgt;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRfdBnkAcct;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrStplatAgr;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.PsnlInfoUsefMbdCd;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefSectCd;
import com.plgrim.ncp.base.repository.mbr.MbrDlvspRepository;
import com.plgrim.ncp.base.repository.mbr.MbrPsnlInfoUsefJobDetailRepository;
import com.plgrim.ncp.base.repository.mbr.MbrPsnlInfoUsefMbdRepository;
import com.plgrim.ncp.base.repository.mbr.MbrPsnlInfoUsefRepository;
import com.plgrim.ncp.base.repository.mbr.MbrPsnlInfoUsefTgtRepository;
import com.plgrim.ncp.base.repository.mbr.MbrRepository;
import com.plgrim.ncp.base.repository.mbr.MbrRfdBnkAcctRepository;
import com.plgrim.ncp.biz.member.repository.MemberOrderSelectRepository;
import com.plgrim.ncp.biz.member.repository.MemberPersonalInfoCommandRepository;
import com.plgrim.ncp.biz.member.result.MemberFoResult;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.commons.util.CodeUtil.Code;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;

import lombok.extern.slf4j.Slf4j;

/**
 * 회원개인정보 command service
 */
@Service
@Slf4j
public class MemberPersonalInfoCommandService extends AbstractService {

    @Autowired
    @Qualifier("sessionTemplate1")
    SqlSession sqlSession1;

    @Autowired
    private MemberPersonalInfoCommandRepository memberPersonalInfoCommandRepository;

    @Autowired
    MemberOrderSelectRepository memberOrderSelectRepository;

    @Autowired
    private MbrPsnlInfoUsefRepository mbrPsnlInfoUsefRepository;

    @Autowired
    private MbrPsnlInfoUsefMbdRepository mbrPsnlInfoUsefMbdRepository;

    @Autowired
    private MbrPsnlInfoUsefJobDetailRepository mbrPsnlInfoUsefJobDetailRepository;

    @Autowired
    private MbrPsnlInfoUsefTgtRepository mbrPsnlInfoUsefTgtRepository;

    @Autowired
    private MbrRepository mbrRepository;

    @Autowired
    private MbrDlvspRepository mbrDlvspRepository;

    @Autowired
    private MbrRfdBnkAcctRepository mbrRfdBnkAcctRepository;

    /**
     * 개인정보변경이력 ERP 전송 여부 업데이트.
     *
     * @param mpim    [설명]
     * @param loginId [설명]
     * @ the exception
     * @since 2015. 6. 9
     */
    public void updatePersonalInfoErpTrnsmisYn(MbrPsnlInfoModHist mpim, String loginId) {
        mpim.setErpTrnsmisYn(MemberEnum.YES.toString()); // ERP 전송 여부
        mpim.setUdterId(loginId);

        memberPersonalInfoCommandRepository.updatePersonalInfoErpTrnsmisYn(mpim);
    }

    public void insertMbrStplatAgr(MbrStplatAgr agr) {
        memberPersonalInfoCommandRepository.insertMergeMbrStplatAgr(agr);
    }

    /**
     * 회원 상세 정보 변경 이력 등록.
     *
     * @param afterMbr [설명]
     * @param mpim     [설명]
     * @param codeArr  [설명]
     * @param isReg    [설명]
     * @ the exception
     * @since 2015. 4. 16
     */
    public void insertPersonalInfoForMbr(Mbr afterMbr, MbrPsnlInfoModHist mpim, String[] codeArr, boolean isReg) {

        Mbr beforeMbr = new Mbr();

        try {
            // 기존 정보 조회
            if (!isReg) {
                beforeMbr = mbrRepository.selectMbr(afterMbr);
            }

            // AFTER
            Map<String, Object> afterMap = makeAfterMap(afterMbr);

            // BEFORE
            Map<String, Object> beforeMap = makeAfterMap(beforeMbr);

            insertPersonalInfo(afterMap, beforeMap, mpim, codeArr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 회원 개인정보 이용 이력 등록
     * <p>
     * <p/>
     * <p>
     * [사용 방법 설명].
     *
     * @param systemPK   [설명]
     * @param usefCdInfo [업무구분 코드 {이용 구분 코드, 이용 업무 코드, 이용 업무 상세 코드}]
     * @param target     [개인정보 이용 대상 회원 목록]
     * @param inflowSn   [유입경로]
     * @param menuSn     [개인정보 이용 메뉴 일련번호 (FRONT - NULL)]
     * @param loginId    시스템 접근자 ID (FRONT - mbr_no, BO/CS/PO - loginId(admin_id))
     * @ the exception
     * @since 2015. 6. 9
     */
    public void insertMemberPersonalInfo(SystemPK systemPK, String[][] usefCdInfo, String[] target, Long inflowSn,
                                         Long menuSn, String loginId) {
        for (String[] usefCd : usefCdInfo) {
            // 회원 개인정보 이용
            MbrPsnlInfoUsef mpiu = insertMbrPsnlInfoUsef(systemPK, usefCd[0], usefCd[1], inflowSn, menuSn, loginId);

            // 회원 개인정보 이용 업무 상세
            insertMbrPsnlInfoUsefJobDetail(mpiu, usefCd[2]);

            // 회원 개인정보 이용 주체
            if (StringService.isEmpty(mpiu.getBoSiteId())
                    && StringService.equalsIgnoreCase(mpiu.getUsefSectCd(), UsefSectCd.PSNL_INFO_STTUS.toString())) {
                insertMbrPsnlInfoUsefMbd(mpiu, PsnlInfoUsefMbdCd.CSTMR.toString(), null);
            } else if (!StringService.isEmpty(mpiu.getBoSiteId())
                    && StringService.equalsIgnoreCase(mpiu.getUsefSectCd(), UsefSectCd.PSNL_INFO_STTUS.toString())) {
                insertMbrPsnlInfoUsefMbd(mpiu, PsnlInfoUsefMbdCd.ADMIN.toString(), null);
            } else {
                String[] comList = StringService.split(MemberPersonalInfoEnum.valueOf(mpiu.getUsefJobCd()).toString(),
                        "|");
                for (String comCd : comList) {
                    insertMbrPsnlInfoUsefMbd(mpiu, PsnlInfoUsefMbdCd.PSNL_INFO_COM.toString(), comCd);
                }
            }

            // 회원 개인정보 이용 대상
            if (target == null || target.length == 0)
                continue;
            for (String mbrNo : target) {
                if (StringUtils.isEmpty(mbrNo)) {
                    continue;
                }
                if (!"NMBR".equals(mbrNo)) {
                    // 회원 대상
                    if (mbrNo.contains("MB")) {
                        insertMbrPsnlInfoUsefTgt(mpiu, mbrNo);
                    }
                }
            }
        }
    }

    /**
     * 1:1 문의 이력 등록.
     *
     * @param mpiu
     * @param usefSectCdInfo
     * @param usefJobCdInfo
     * @param usefCdInfoDetail
     * @throws Exception
     */
    public void insertPersonalInfoUsef(MbrPsnlInfoUsef mpiu, String usefSectCdInfo, String usefJobCdInfo,
                                       String[] usefCdInfoDetail) {

        MbrPsnlInfoUsefJobDetail mpiujd = new MbrPsnlInfoUsefJobDetail();
        mpiu.setUsefSectCd(usefSectCdInfo); // 이용 구분 코드
        mpiu.setUsefJobCd(usefJobCdInfo); // 이용 업무 코드
        mbrPsnlInfoUsefRepository.insertMbrPsnlInfoUsef(mpiu);
        for (String codeName : usefCdInfoDetail) {
            mpiujd.setPsnlInfoUsefSn(mpiu.getPsnlInfoUsefSn());
            mpiujd.setUsefJobDetailCd(codeName); // 이용 업무 상세 코드
            mpiujd.setRegtrId(mpiu.getRegtrId());
            mpiujd.setUdterId(mpiu.getUdterId());

            mbrPsnlInfoUsefJobDetailRepository.insertMbrPsnlInfoUsefJobDetail(mpiujd);
        }
        insertMbrPsnlInfoUsefMbd(mpiu, PsnlInfoUsefMbdCd.CSTMR.toString(), null);
    }

    /**
     * 회원 개인정보 변경 이력 등록 정보 설정.
     *
     * @param mbrNo       개인정보 변경 회원번호
     * @param modResnDscr 개인정보 변경 사유
     * @param loginId     시스템 접근자 ID (FRONT - mbr_no, BO/CS/PO - loginId(admin_id))
     * @param isMember    변경주체의 회원 여부
     * @return Mbr psnl info mod hist [설명]
     * @ the exception
     * @since 2015. 4. 16
     */
    public MbrPsnlInfoModHist setMbrPsnlInfoModHist(String mbrNo, String modResnDscr, String loginId, boolean isMember) {

        long psnlInfoModHistSn = getIdGenService().generateDBSequence(sqlSession1, "sq_mbr_psnl_info_mod_hist",
                DatabaseType.ORACLE);

        MbrPsnlInfoModHist mpim = new MbrPsnlInfoModHist();
        mpim.setPsnlInfoModHistSn(psnlInfoModHistSn); // 개인정보 변경 이력 일련번호
        mpim.setMbrNo(mbrNo); // 회원번호
        mpim.setModResnDscr(modResnDscr); // 변경 사유 설명
        mpim.setErpTrnsmisYn("N"); // ERP 전송 여부
        mpim.setRegtrId(loginId);
        mpim.setUdterId(loginId);

        if (isMember) {
            mpim.setModMbrNo(loginId); // 변경 회원 번호
        } else {
            mpim.setModAdminId(loginId); // 변경 관리자 ID
        }

        return mpim;
    }


    /**
     * 회원 정보 변경 이력 등록.
     *
     * @param afterMap  [설명]
     * @param beforeMap [설명]
     * @param mpim      [설명]
     * @param codeArr   [설명]
     * @ the exception
     * @since 2015. 4. 16
     */
    private void insertPersonalInfo(Map<String, Object> afterMap, Map<String, Object> beforeMap,
                                    MbrPsnlInfoModHist mpim, String[] codeArr) {
        String modBfVal = "";
        String modAfVal = "";

        Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("psnl_info_mod_hist_sn", mpim.getPsnlInfoModHistSn());
        int modTurn = 0;

        for (String codeName : codeArr) {
            modBfVal = this.makeCheckString(codeName, beforeMap);
            modAfVal = this.makeCheckString(codeName, afterMap);

            if (StringService.contains(codeName, "_NO") || StringService.contains(codeName, "_DATE")
                    || StringService.contains(codeName, "MBR_BRTHDY")) {
                modBfVal = StringService.removeHyphen(modBfVal);
                modAfVal = StringService.removeHyphen(modAfVal);

            }

            if (MemberPersonalInfoEnum.MbrPsnlInfoUdtSectCd.EMP_MAIL_CRTFC.toString().equals(codeName)) {
                modAfVal = "EMP_CRTFC";
            } else if (modBfVal.equals(modAfVal)) {
                continue;
            }

            // 변경이력 값 설정
            modTurn = getIdGenService().generateDBOrder(sqlSession1, "mbr_psnl_info_mod_hist",
                    "psnl_info_mod_hist_turn", conditions, DatabaseType.ORACLE);
            mpim.setPsnlInfoModHistTurn(modTurn); // 개인정보 변경 이력 순번

            mpim.setPsnlInfoUdtSectCd(codeName); // 개인정보 수정 구분 코드
            mpim.setModBfVal(modBfVal); // 변경 이전 값
            mpim.setModAfVal(modAfVal); // 변경 이후 값

            memberPersonalInfoCommandRepository.insertPersonalInfoModHistory(mpim);
        }
    }

    private String makeCheckString(String codeName, Map<String, Object> paramMap) {
        String returnStr = "";

        String[] colunms = StringService.split(MemberPersonalInfoEnum.valueOf(codeName).toString(), "|");

        for (String colunm : colunms) {
            returnStr += StringService.trimToEmpty((String) paramMap.get(colunm));
        }

        return returnStr;
    }

    /**
     * [회원 개인정보 이용 주체 등록].
     * <p>
     * <p/>
     * <p>
     * [사용 방법 설명].
     *
     * @param mpiu  [설명]
     * @param mbdCd [설명]
     * @param comCd [설명]
     * @ the exception
     * @since 2015. 5. 13
     */
    private void insertMbrPsnlInfoUsefMbd(MbrPsnlInfoUsef mpiu, String mbdCd, String comCd) {
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("PSNL_INFO_USEF_SN", mpiu.getPsnlInfoUsefSn());
        Integer piumTurn = getIdGenService().generateDBOrder(sqlSession1, "mbr_psnl_info_usef_mbd",
                "psnl_info_usef_mbd_turn", conditions, DatabaseType.ORACLE);

        MbrPsnlInfoUsefMbd mpium = new MbrPsnlInfoUsefMbd();
        mpium.setPsnlInfoUsefSn(mpiu.getPsnlInfoUsefSn()); // 개인정보 이용 일련번호
        mpium.setPsnlInfoUsefMbdTurn(piumTurn); // 개인정보 이용 주체 순번
        mpium.setPsnlInfoUsefMbdCd(mbdCd); // 개인정보 이용 주체 코드

        if (StringService.equalsIgnoreCase(mbdCd, PsnlInfoUsefMbdCd.CSTMR.toString())) {
            mpium.setMbrNo(mpiu.getRegtrId()); // 회원 번호
        } else if (StringService.equalsIgnoreCase(mbdCd, PsnlInfoUsefMbdCd.ADMIN.toString())) {
            mpium.setAdminId(mpiu.getRegtrId()); // 관리자 ID
        } else if (StringService.equalsIgnoreCase(mbdCd, PsnlInfoUsefMbdCd.PSNL_INFO_COM.toString())) {
            mpium.setPsnlInfoUsefComCd(comCd); // 개인정보 이용 업체 코드
        }

        mpium.setRegtrId(mpiu.getRegtrId()); // 등록자 ID
        mpium.setUdterId(mpiu.getRegtrId()); // 수정자 ID

        mbrPsnlInfoUsefMbdRepository.insertMbrPsnlInfoUsefMbd(mpium);
    }

    /**
     * [회원 개인정보 이용 등록].
     * <p>
     * <p/>
     * <p>
     * [사용 방법 설명].
     *
     * @param systemPK   [설명]
     * @param usefSectCd [설명]
     * @param usefJobCd  [설명]
     * @param inflowSn   [설명]
     * @param menuSn     [설명]
     * @param loginId    [설명]
     * @return Mbr psnl info usef [설명]
     * @ the exception
     * @since 2015. 6. 9
     */
    private MbrPsnlInfoUsef insertMbrPsnlInfoUsef(SystemPK systemPK, String usefSectCd, String usefJobCd, Long inflowSn,
                                                  Long menuSn, String loginId) {
        long piuSn = getIdGenService().generateDBSequence(sqlSession1, "sq_mbr_psnl_info_usef", DatabaseType.ORACLE);
        Date usefDt = new Date();

        MbrPsnlInfoUsef mpiu = new MbrPsnlInfoUsef();
        mpiu.setPsnlInfoUsefSn(piuSn); // 개인정보 이용 일련번호
        mpiu.setUsefDt(usefDt); // 이용 일자
        mpiu.setUsefSectCd(usefSectCd); // 이용 구분 코드
        mpiu.setUsefJobCd(usefJobCd); // 이용 업무 코드
        if(systemPK != null) {
	        mpiu.setLangCd(systemPK.getLang()); // 언어 코드
	        mpiu.setMallId(systemPK.getMall()); // 몰 ID
	        mpiu.setDvcCd(systemPK.getDevice()); // 디바이스 코드
	        mpiu.setBoSiteId(systemPK.getSite()); // BO 사이트 ID
        }
        mpiu.setInflowSn(inflowSn); // 유입 일련번호
        mpiu.setMenuSn(menuSn); // 메뉴 일련번호

        if (loginId != null && loginId.length() > 50) {
            loginId = loginId.substring(0, 49);
        }

        mpiu.setRegtrId(loginId); // 등록자 ID
        mpiu.setUdterId(loginId); // 수정자 ID

        mbrPsnlInfoUsefRepository.insertMbrPsnlInfoUsef(mpiu);

        return mpiu;
    }

    /**
     * [회원 개인정보 이용 업무 상세 등록].
     * <p>
     * <p/>
     * <p>
     * [사용 방법 설명].
     *
     * @param mpiu            [설명]
     * @param usefJobDetailCd [설명]
     * @ the exception
     * @since 2015. 6. 9
     */
    private void insertMbrPsnlInfoUsefJobDetail(MbrPsnlInfoUsef mpiu, String usefJobDetailCd) {
        MbrPsnlInfoUsefJobDetail mpiujd = new MbrPsnlInfoUsefJobDetail();
        mpiujd.setPsnlInfoUsefSn(mpiu.getPsnlInfoUsefSn()); // 개인정보 이용 일련번호
        mpiujd.setUsefJobDetailCd(usefJobDetailCd); // 이용 업무 상세 코드
        mpiujd.setRegtrId(mpiu.getRegtrId()); // 등록자 ID
        mpiujd.setUdterId(mpiu.getRegtrId()); // 수정자 ID

        mbrPsnlInfoUsefJobDetailRepository.insertMbrPsnlInfoUsefJobDetail(mpiujd);
    }

    /**
     * [개인정보 이용 대상 등록].
     *
     * @param mpiu  [설명]
     * @param mbrNo [설명]
     * @return Mbr psnl info usef tgt [설명]
     * @ the exception
     * @since 2015. 5. 13
     */
    private void insertMbrPsnlInfoUsefTgt(MbrPsnlInfoUsef mpiu, String mbrNo) {
        MbrPsnlInfoUsefTgt mpiut = new MbrPsnlInfoUsefTgt();
        mpiut.setPsnlInfoUsefSn(mpiu.getPsnlInfoUsefSn()); // 개인정보 이용 일련번호
        mpiut.setMbrNo(mbrNo); // 회원 번호
        mpiut.setUsefDt(mpiu.getUsefDt()); // 이용 일자
        mpiut.setRegtrId(mpiu.getRegtrId()); // 등록자 ID
        mpiut.setUdterId(mpiu.getRegtrId()); // 수정자 ID

        mbrPsnlInfoUsefTgtRepository.insertMbrPsnlInfoUsefTgt(mpiut);
    }

    /**
     * 개인정보변경이력 - 마이사이즈 변경이력 저장
     *
     * @param mbrPsnlInfoModHist
     * @
     */
    public void insertPersonalInfoForMbrSize(MbrPsnlInfoModHist mbrPsnlInfoModHist) {
        Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("psnl_info_mod_hist_sn", mbrPsnlInfoModHist.getPsnlInfoModHistSn());
        int modTurn = 0;
        modTurn = getIdGenService().generateDBOrder(sqlSession1, "mbr_psnl_info_mod_hist", "psnl_info_mod_hist_turn",
                conditions, DatabaseType.ORACLE);
        mbrPsnlInfoModHist.setPsnlInfoModHistTurn(modTurn);
        memberPersonalInfoCommandRepository.insertPersonalInfoModHistory(mbrPsnlInfoModHist);
    }

    public void insertMergeMbrStplatAgr(MbrStplatAgr agr) {
        memberPersonalInfoCommandRepository.insertMergeMbrStplatAgr(agr);
    }

    /**
     * 선택동의 약관의 동의여부 update
     *
     * @param mbrNoList
     * @param dto
     * @param ssoGrpCd
     * @
     */
    public void updateMktAgrYn(List<Mbr> mbrNoList, MemberFoResult dto, String ssoGrpCd, String agrYn) {
        for (Mbr member : mbrNoList) {
            MbrStplatAgr stplatAgr = new MbrStplatAgr();
            for (MbrStplatAgr mbrStplatAgr : dto.getMbrStplatAgrs()) {
                String stplatCd = mbrStplatAgr.getStplatCd();
                if (stplatCd.contains(MemberEnum.MbrStplatMarktAgr.MARKT_PSNL_INFO_COLCT_USEF_AGR.toString())) {

                    // PLGRIM
                    stplatCd = MemberEnum.MbrStplatMarktAgr.MARKT_PSNL_INFO_COLCT_USEF_AGR.toString();
                    stplatAgr.setMbrNo(member.getMbrNo());
                    stplatAgr.setStplatCd(stplatCd);
                    stplatAgr.setStplatIemAgrYn(agrYn);
                    // step3. mbr stplat
                    memberPersonalInfoCommandRepository.insertMergeMbrStplatAgr(stplatAgr);

                }
            }
        }
    }

    /**
     * 회원 상세 정보 변경 이력 등록.
     *
     * @param afterMbr [설명]
     * @param mpim     [설명]
     * @param codeArr  [설명]
     * @param isReg    [설명]
     * @ the exception
     * @since 2015. 4. 16
     */
    public void insertPersonalInfoForMbr(MbrPsnlInfoModHist mpim, String[] codeArr, Mbr beforeMbr, Mbr afterMbr) {
        try {
            // AFTER
            Map<String, Object> afterMap = makeAfterMap(afterMbr);

            // BEFORE
            Map<String, Object> beforeMap = makeAfterMap(beforeMbr);

            insertPersonalInfo(afterMap, beforeMap, mpim, codeArr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void psnlInfoModHistory(Mbr mbr) {
        mbr.setMbrNo("11111111111111111111111111111");
        mbr.setMbrNm("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Method[] methods = mbr.getClass().getMethods();
        List<Code> cdList = CodeUtil.getCodes("PSNL_INFO_UDT_SECT", "ENG");
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().indexOf("get") > -1) {
                for (Code code : cdList) {
                    String cd = code.getCd();
                    cd = cd.replaceAll("_", "");
                    String methodName = methods[i].getName().substring(3);
                    if (cd.toLowerCase().indexOf(methodName.toLowerCase()) > -1) {
                        log.info(" ============================================= ");
                        log.info("method name :  " + methods[i].getName());
                        log.info("code   :   " + code.getCd());
                        log.info(" ============================================= ");
                        try {
                            log.info("methods[i].invoke(mbr, null)   :   " + methods[i].invoke(mbr, null));
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            log.error(e.getMessage(), e);
                        }
                        log.info(" +++++++++++++++++++++++++++++++++++++++++++++++++ ");
                        break;
                    }
                }

            }
        }

    }

    /**
     * 회원 환불계좌 정보 변경 이력 등록.
     *
     * @param afterRfdBnkAcct [설명]
     * @param mpim            [설명]
     * @param codeArr         [설명]
     * @param isReg           [설명]
     * @ the exception
     * @since 2015. 6. 9
     */
    public void insertPersonalInfoForMbrRfdBakAcct(MbrRfdBnkAcct afterRfdBnkAcct, MbrPsnlInfoModHist mpim,
                                                   String[] codeArr, boolean isReg) {
        try {
            MbrRfdBnkAcct beforeRfdBnkAcct = new MbrRfdBnkAcct();

            // 기존 정보 조회
            if (!isReg) {
                beforeRfdBnkAcct = mbrRfdBnkAcctRepository.selectMbrRfdBnkAcct(afterRfdBnkAcct);
            }

            // AFTER
            Map<String, Object> afterMap = makeAfterMap(afterRfdBnkAcct);

            // BEFORE
            Map<String, Object> beforeMap = makeAfterMap(beforeRfdBnkAcct);

            insertPersonalInfo(afterMap, beforeMap, mpim, codeArr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setEntity(Mbr mbr) {
        mbr.setMbrNo("11111111111111111111111111111");
        mbr.setMbrNm("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Method[] methods = mbr.getClass().getMethods();
        List<Code> cdList = CodeUtil.getCodes("PSNL_INFO_UDT_SECT", "ENG");
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().indexOf("get") > -1) {
                for (Code code : cdList) {
                    String cd = code.getCd();
                    cd = cd.replaceAll("_", "");
                    String methodName = methods[i].getName().substring(3);
                    if (cd.toLowerCase().indexOf(methodName.toLowerCase()) > -1) {
                        log.info(" ============================================= ");
                        log.info("method name :  " + methods[i].getName());
                        log.info("code   :   " + code.getCd());
                        log.info(" ============================================= ");
                        try {
                            log.info("methods[i].invoke(mbr, null)   :   " + methods[i].invoke(mbr, null));
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                        }
                        log.info(" +++++++++++++++++++++++++++++++++++++++++++++++++ ");
                        break;
                    }
                }

            }
        }

    }

    public void insertPersonalInfoForMbrDlvsp(MbrDlvsp afterDlvsp, MbrPsnlInfoModHist mpim, String[] codeArr,
                                              boolean isReg) {
        try {
            MbrDlvsp beforeDlvsp = new MbrDlvsp();

            // 기존 정보 조회
            if (!isReg) {
                beforeDlvsp = mbrDlvspRepository.selectMbrDlvsp(afterDlvsp);
            }

            // AFTER
            Map<String, Object> afterMap = makeAfterMap(afterDlvsp);

            // BEFORE
            Map<String, Object> beforeMap = new HashMap<String, Object>();
            if (beforeDlvsp != null) {
                beforeMap = makeAfterMap(beforeDlvsp);
            }

            insertPersonalInfo(afterMap, beforeMap, mpim, codeArr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
