package com.plgrim.ncp.cmp.member.bo.customer;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum;
import com.plgrim.ncp.biz.member.data.MemberBoDTO;
import com.plgrim.ncp.biz.member.exception.MemberSecessionImpossibleException;
import com.plgrim.ncp.biz.member.exception.MemberStopImpossibleException;
import com.plgrim.ncp.biz.member.result.MbrExtendResult;
import com.plgrim.ncp.biz.member.service.MemberBaseCommandService;
import com.plgrim.ncp.biz.member.service.MemberBaseSelectService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoCommandService;
import com.plgrim.ncp.cmp.member.bo.MemberCustomerBOComponent;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional(value = "transactionManager")
@Component
@Slf4j
public class MemberCustomerBOComponentImpl extends AbstractComponent implements MemberCustomerBOComponent {

    @Autowired
    private MemberPersonalInfoCommandService memberPersonalInfoCommandService;

    @Autowired
    private MemberBaseSelectService memberBaseSelectService;

    @Autowired
    private MemberBaseCommandService memberBaseCommandService;

    /**
     * 회원 상태 변경.
     */
    @Override
    public void modifyMemberStatusBy(SystemPK systemPK, MemberBoDTO dto, String loginId) {
        Mbr mbr = dto.getMbr();
        MbrPsnlInfoModHist mpimh = dto.getMbrPsnlInfoModHist();

        String[] mbrNoArr = StringService.split(mbr.getMbrNo(), ",");

        for (String mbrNo : mbrNoArr) {
            mbr.setMbrNo(mbrNo);

            // step 1. 개인정보변경이력 등록
            MbrPsnlInfoModHist mpim = memberPersonalInfoCommandService.setMbrPsnlInfoModHist(mbrNo, mpimh.getModResnDscr(), loginId, false);
            String[] codeArr = {
                    MemberPersonalInfoEnum.MbrPsnlInfoUdtSectCd.MBR_STAT_CD.toString()                    // 회원 상태
            };
            mpim.setModLcSectCd(MemberPersonalInfoEnum.MemberModLcSectCd.ONLNE_MALL.toString()); // 수정이력 수정몰
            memberPersonalInfoCommandService.insertPersonalInfoForMbr(mbr, mpim, codeArr, false);

            //정지일 경우 주문상태 체크
            if (MemberEnum.MemberStatCd.STOP.toString().equals(mbr.getMbrStatCd())) {
                MbrExtendResult result = memberBaseSelectService.selectMemberForSecession(mbr);
                if (result.getOrdCnt() > 0 || result.getClmCnt() > 0) {
                    String[] param = {String.valueOf(result.getOrdCnt()), String.valueOf(result.getClmCnt())};
                    throw new MemberStopImpossibleException(param);
                }
            }

            // step 2. 회원 상태 수정
            memberBaseCommandService.updateMemberStatus(mbr, loginId);
        }
    }

    /**
     * 회원 탈퇴 (BO 에서는 ERP 탈퇴하지 않음.).
     *
     * @
     */
    @Override
    public void modifyMemberTerminateBy(SystemPK systemPK, Mbr mbr, String loginId) {

        // step 1. 탈퇴가능 조건 확인
        MbrExtendResult result = memberBaseSelectService.selectMemberForSecession(mbr);
        if (result.getOrdCnt() > 0 || result.getClmCnt() > 0) {
            String[] param = {String.valueOf(result.getOrdCnt()), String.valueOf(result.getClmCnt())};
            throw new MemberSecessionImpossibleException(param);
        }

        // step 2. 탈퇴정보 수정/삭제
        mbr.setSecsnSectCd(MemberEnum.MemberSecsnSectCd.ADMIN_SECSN.toString());
        mbr.setEnfrcsecsnDspsId(loginId);
        mbr.setUdterId(loginId);
        memberBaseCommandService.updateMemberSecession(mbr);

        // step 3. 개인정보이용이력 등록
        String[][] usefCdInfo = { // 구분, 업무, 업무상세
                {MemberPersonalInfoEnum.UsefSectCd.PSNL_INFO_STTUS.toString(), MemberPersonalInfoEnum.UsefJobCd.DELETE.toString(), MemberPersonalInfoEnum.UsefJobDetail.MBR_SECSN.toString()}
        };
        String[] target = {mbr.getMbrNo()};
        memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
                , usefCdInfo                // 개인정보 코드 정보(구분, 업무, 업무상세)
                , target                    // 이용대상 : 회원
                , null                        // 유입 일련번호
                , mbr.getAccessMenuSn()        // 메뉴 일련번호
                , loginId                    // 로그인 ID
        );
    }

}
