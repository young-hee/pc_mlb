package com.plgrim.ncp.cmp.member.bo.benefit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;
import com.plgrim.ncp.base.enums.MemberBenefitEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum;
import com.plgrim.ncp.biz.member.data.MemberBenefitBoDTO;
import com.plgrim.ncp.biz.member.data.MemberBoDTO;
import com.plgrim.ncp.biz.member.data.MemberResultDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.result.MemberBenefitAplTgtGrpCdResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitBoResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitPymntHistResult;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.member.service.MemberBaseSelectService;
import com.plgrim.ncp.biz.member.service.MemberBenefitApiCommandService;
import com.plgrim.ncp.biz.member.service.MemberBenefitApiSelectService;
import com.plgrim.ncp.biz.member.service.MemberBenefitCommandService;
import com.plgrim.ncp.biz.member.service.MemberBenefitSelectService;
import com.plgrim.ncp.biz.member.service.MemberInterfaceService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoCommandService;
import com.plgrim.ncp.cmp.member.bo.MemberBenefitBOComponent;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.plgrim.ncp.interfaces.member.data.MemberMileageInfoSDO;
import com.plgrim.ncp.interfaces.member.data.MileageHistInofSDO;

import lombok.extern.slf4j.Slf4j;

@Transactional(value = "transactionManager")
@Component
@Slf4j
public class MemberBenefitBOComponentImpl extends AbstractComponent implements MemberBenefitBOComponent {

    @Autowired
    private MemberBenefitApiCommandService memberBenefitApiCommandService;

    @Autowired
    private MemberBenefitApiSelectService memberBenefitApiSelectService;

    @Autowired
    private MemberBaseSelectService memberBaseSelectService;

    @Autowired
    private MemberBenefitCommandService memberBenefitCommandService;

    @Autowired
    private MemberPersonalInfoCommandService memberPersonalInfoCommandService;

    @Autowired
    private MemberBenefitSelectService memberBenefitSelectService;

    @Autowired
    private MemberInterfaceService memberInterfaceService;
    
    /**
     * 회원혜택 / 적용대상 등록.
     */
    @Override
    public void insertMemberBenefit(MemberBenefitBoDTO dto) {

        // 회원혜택 등록
        memberBenefitApiCommandService.insertMemberBenefit(dto);

        // 적용대상 등록
        memberBenefitApiCommandService.insertMemberBenefitApplyTarget(dto);

    }

    /**
     * 회원혜택 수정.
     */
    @Override
    public void updateMemberBenefit(MemberBenefitBoDTO dto) {

        if (MemberBenefitEnum.MemberBenefitStat.APRV_WAIT.toString().equals(dto.getAprvSectCd())) {
            // 회원혜택 수정
            memberBenefitApiCommandService.updateMemberBenefit(dto);

            // 적용대상 삭제
            memberBenefitApiCommandService.deleteMemberBenefitApplyTarget(dto);

            // 적용대상 등록
            memberBenefitApiCommandService.insertMemberBenefitApplyTarget(dto);
        }
    }

    /**
     * 회원혜택 지급혜택 등록/수정/삭제.
     */
    public void insertMemberBenefitDetail(List<MemberBenefitBoDTO> insList, List<MemberBenefitBoDTO> updList,
                                          List<MemberBenefitBoDTO> delList) {
        memberBenefitApiCommandService.insertMemberBenefitDetail(insList);
        memberBenefitApiCommandService.updateMemberBenefitDetail(updList);
        memberBenefitApiCommandService.deleteMemberBenefitDetail(delList);

    }

    /**
     * 회원혜택 상태 수정.
     */
    @Override
    public void updateMemberBenefitStatus(MemberBenefitBoDTO dto) {

        // 회원혜택 상태 수정
        memberBenefitApiCommandService.updateMemberBenefitStatus(dto);
    }

    /**
     * 회원혜택 지급혜택 상태 수정.
     */
    @Override
    public void updateMemberBenefitDtlStatus(MemberBenefitBoDTO dto) {

        // 회원혜택 지급혜택 상태 수정
        memberBenefitApiCommandService.updateMemberBenefitDtlStatus(dto);
    }


    /**
     * 회원혜택 목록 조회.
     */
    @Override
    public MemberBenefitBoResult getMemberBenefitList(SystemPK systemPK, MemberBenefitBoDTO dto, String loginId) {
        // step 1. 페이지 인덱스 및 변수 설정

        MemberBenefitBoResult resultDTO = new MemberBenefitBoResult();
        dto.setLang(systemPK.getLang());

        // step 2. 목록 건수 조회
        long listCount = memberBenefitApiSelectService.selectMemberBenefitListCount(dto);
        resultDTO.setListCount(listCount);

        // step 3. 목록 조회
        List<MemberBenefitBoResult> lists = new ArrayList<MemberBenefitBoResult>();
        if (listCount > 0) {
            lists = memberBenefitApiSelectService.selectMemberBenefitList(dto);
        }
        resultDTO.setLists(lists);


        return resultDTO;
    }

    /**
     * 회원혜택 상세조회
     */
    @Override
    public MemberBenefitBoResult getMemberBenefitDetail(SystemPK systemPK, MemberBenefitBoDTO dto, String loginId) {
        // 회원혜택 정보 조회
        MemberBenefitBoResult resultDTO = memberBenefitApiSelectService.selectMemberBenefitDetail(dto);

        return resultDTO;
    }

    /**
     * 회원혜택 지급 혜택 목록 조회.
     */
    @Override
    public Page<MemberBenefitBoResult> getMemberBenefitDetailList(SystemPK systemPK, MemberBenefitBoDTO dto, PageParam pageParam) {
        dto.setLang(systemPK.getLang());
        return memberBenefitApiSelectService.selectMemberBenefitDetailList(dto, pageParam);
    }

    /**
     * 회원혜택 상세 수정이력 목록 조회.
     */
    @Override
    public Page<MemberBenefitBoResult> getMemberBenefitDetailHistList(SystemPK systemPK, MemberBenefitBoDTO dto, PageParam pageParam) {
        dto.setLang(systemPK.getLang());
        return memberBenefitApiSelectService.selectMemberBenefitDetailHistList(dto, pageParam);
    }

    /**
     * 회원혜택 상세 발급내역 목록 조회.
     */
    @Override
    public Page<MemberBenefitPymntHistResult> getMemberBenefitPymntHistList(SystemPK systemPK, MemberBenefitBoDTO dto, PageParam pageParam) {
        dto.setLang(systemPK.getLang());
        return memberBenefitApiSelectService.selectMemberBenefitPymntHistList(dto, pageParam);
    }

    /**
     * 회원혜택 상세 그룹사 목록 조회.
     */
    @Override
    public Page<MemberBenefitAplTgtGrpCdResult> selectMbrBnefAplTgtGrpCdList(SystemPK systemPK, MemberBenefitBoDTO dto, PageParam pageParam) {
        dto.setLang(systemPK.getLang());
        return memberBenefitApiSelectService.selectMbrBnefAplTgtGrpCdList(dto, pageParam);
    }

    /**
     * 회원혜택 중복체크
     */
    @Override
    public int checkMemberBenefitKey(SystemPK systemPK, MemberBenefitBoDTO dto) {
        dto.setLang(systemPK.getLang());
        int resultDTO = memberBenefitApiSelectService.checkMemberBenefitKey(dto);

        return resultDTO;
    }

    /**
     * 회원혜택 엑셀 목록 조회
     */
    @Override
    public List<Map<String, Object>> selectMemberBenefitExcelList(SystemPK systemPK, MemberBenefitBoDTO dto) {
        dto.setLang(systemPK.getLang());
        return memberBenefitApiSelectService.selectMemberBenefitExcelList(dto);
    }

    /**
     * 회원혜택 엑셀 수정이력 조회
     */
    @Override
    public List<Map<String, Object>> selectMemberBenefitDetailHistExcelList(SystemPK systemPK, MemberBenefitBoDTO dto) {
        dto.setLang(systemPK.getLang());
        return memberBenefitApiSelectService.selectMemberBenefitDetailHistExcelList(dto);
    }

    /**
     * 회원혜택 상세 엑셀 발급내역 조회
     */
    @Override
    public List<Map<String, Object>> selectMemberBenefitPymntHistExcelList(SystemPK systemPK, MemberBenefitBoDTO dto) {
        dto.setLang(systemPK.getLang());
        return memberBenefitApiSelectService.selectMemberBenefitPymntHistExcelList(dto);
    }

    /**
     * 회원혜택 지급혜택 중복체크.
     */
    @Override
    public int checkMemberBenefitDetailDup(List<MemberBenefitBoDTO> list) {

        int result_cnt = 0;
        if (list != null && !list.isEmpty()) {
            for (MemberBenefitBoDTO dto : list) {
                log.debug("checkMemberBenefitDetailDup  :::::::::::::::::::: " + dto.toString());
                result_cnt = memberBenefitApiSelectService.checkMemberBenefitDetailDup(dto);
            }
        }

        return result_cnt;
    }

    @Override
    public void insertWebPoint(MbrWebpntHist mbrWebpntHist) {
        memberBenefitCommandService.insertWebPoint(mbrWebpntHist);
    }

    @Override
    public void webPntImdtlRtrvl(MbrWebpntHist mbrWebpntHist) {
        memberBenefitCommandService.webPntImdtlRtrvl(mbrWebpntHist);
    }

    /**
     * 멤버쉽 포인트 내역 조회.
     */
    @Override
    public MemberResultDTO getMemberReserveList(SystemPK systemPK, String mbrNo, String loginId, String startReserveDt, String endReserveDt) {
        try {
            log.info(CommonResponseCode.MB_00057_통합회원_멤버십_포인트_조회_시도_BO포인트탭.toMessage());

            // step 1. 페이지 인덱스 및 변수 설정
            MemberResultDTO resultDTO = new MemberResultDTO();

            // step 2. 회원의 ERP 정보 조회
            String erpCstmrNo = memberBaseSelectService.selectMemberErpNo(mbrNo);

            Mbr mbr = new Mbr();
            mbr.setMbrNo(mbrNo);
            mbr.setErpCstmrNo(erpCstmrNo);
            MypageFoDTO mypageFoDTO = new MypageFoDTO();
    		mypageFoDTO.setMbr(mbr);
    		mypageFoDTO.setHistoryYn("Y");
    		mypageFoDTO.setDateStart(startReserveDt);
    		mypageFoDTO.setDateEnd(endReserveDt);
    		
    		// 마일리지 내역 조회
    		MemberMileageInfoSDO memberMileageInfoSDO = memberInterfaceService.getMemberMileage(systemPK, mypageFoDTO);
    		
    		if(memberMileageInfoSDO.getResponseCd().equals("200")){
            
	            // 잔여 마일리지
	            resultDTO.setReserveAmount(Long.parseLong(memberMileageInfoSDO.getNowPoint()));
	            // 소멸예정 마일리지
	            resultDTO.setExpiringAmount(Long.parseLong(memberMileageInfoSDO.getEliminatePoint()));
	            // 마일리지 내역
	            List<MileageHistInofSDO> mileageHistList = memberMileageInfoSDO.getMileageHistList();
	            
	            // 그리드에 노출하기 위해 형태 변환
	            List<Map<String, String>> reserveList = new ArrayList<Map<String, String>>();
	            
	            if(mileageHistList != null && !mileageHistList.isEmpty()) {
		            for(MileageHistInofSDO sdo : mileageHistList) {
		            	Map<String, String> map = new HashMap<String, String>();
		            	map.put("mileageDate", sdo.getMileageDate());
		            	map.put("mileageNm", sdo.getMileageNm());
		            	map.put("brandName", sdo.getBrandName());
		            	map.put("ordNo", sdo.getOrdNo());
		            	map.put("mileageUsePoint", sdo.getMileageUsePoint());
		            	map.put("mileageRestorePoint", sdo.getMileageRestorePoint());
		            	reserveList.add(map);
		            }
	            }
	            
	            resultDTO.setReserveList(reserveList);
    		}

            log.info(CommonResponseCode.MB_00058_통합회원_멤버십_포인트_조회_성공_BO포인트탭.toMessage());
            return resultDTO;
        } catch (Exception e) {
            log.info(CommonResponseCode.MB_40024_통합회원_멤버십_포인트_조회_실패_BO포인트탭.toMessage() + " : " + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 멤버쉽 포인트 잔액 조회.
     */
    @Override
    public MemberResultDTO getMemberReserve(SystemPK systemPK, String mbrNo, String loginId) {

        try {
            log.info(CommonResponseCode.MB_00059_통합회원_멤버십_포인트_조회_시도_BO포인트상세.toMessage());

            // step 1. 페이지 인덱스 및 변수 설정
            MemberResultDTO resultDTO = new MemberResultDTO();

            // step 2. 회원의 ERP 정보 조회
            String erpNo = memberBaseSelectService.selectMemberErpNo(mbrNo);

            // step 3. 포인트 잔액 조회
            long reserveAmount = memberBenefitSelectService.getMemberReserveRemainAmount(erpNo);
            resultDTO.setReserveAmount(reserveAmount);

            log.info(CommonResponseCode.MB_00060_통합회원_멤버십_포인트_조회_성공_BO포인트상세.toMessage());
            return resultDTO;
        } catch (Exception e) {
            log.info(CommonResponseCode.MB_40025_통합회원_멤버십_포인트_조회_실패_BO포인트상세.toMessage() + " : " + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 임시삭감 포인트 내역 조회.
     */
    @Override
    public MemberResultDTO getMemberTempDelReserveList(SystemPK systemPK, String mbrNo, String loginId) {

        // step 1. 페이지 인덱스 및 변수 설정
        MemberResultDTO resultDTO = new MemberResultDTO();

        // step 2. 회원의 ERP 정보 조회
        String erpNo = memberBaseSelectService.selectMemberErpNo(mbrNo);

        // step 3. 임시삭감 포인트 내역 조회
        List<Map<String, String>> reserveList = memberBenefitSelectService.getMemberTempDelReserveHistory(systemPK, erpNo);
        resultDTO.setReserveList(reserveList);

        return resultDTO;
    }


    /**
     * 멤버쉽 포인트 내역 엑셀 조회.
     */
    @Override
    public List<Map<String, Object>> getMemberReserveExcel(SystemPK systemPK, String mbrNo, String loginId, String startReserveDt, String endReserveDt) {
        try {
	    	// step 1. 회원의 ERP 정보 조회
	        String erpCstmrNo = memberBaseSelectService.selectMemberErpNo(mbrNo);
	
	        // step 2. 멤버쉽 포인트 내역 조회
	        Mbr mbr = new Mbr();
	        mbr.setMbrNo(mbrNo);
	        mbr.setErpCstmrNo(erpCstmrNo);
	        MypageFoDTO mypageFoDTO = new MypageFoDTO();
			mypageFoDTO.setMbr(mbr);
			mypageFoDTO.setHistoryYn("Y");
    		mypageFoDTO.setDateStart(startReserveDt);
    		mypageFoDTO.setDateEnd(endReserveDt);
			
			// 마일리지 내역 조회
			MemberMileageInfoSDO memberMileageInfoSDO = memberInterfaceService.getMemberMileage(systemPK, mypageFoDTO);
	        
	        // 마일리지 내역
	        List<MileageHistInofSDO> mileageHistList = memberMileageInfoSDO.getMileageHistList();
	        
	        // 엑셀에 노출하기 위해 형태 변환
	        List<Map<String, Object>> reserveList = new ArrayList<Map<String, Object>>();
	        
	        if(mileageHistList != null && !mileageHistList.isEmpty()) {
		        for(MileageHistInofSDO sdo : mileageHistList) {
		        	Map<String, Object> map = new HashMap<String, Object>();
		        	map.put("마일리지 생성/사용 일자", sdo.getMileageDate());
		        	map.put("마일리지 유형", sdo.getMileageNm());
		        	map.put("마일리지 몰", sdo.getBrandName());
		        	map.put("주문번호", sdo.getOrdNo());
		        	map.put("마일리지 사용 금액", sdo.getMileageUsePoint());
		        	map.put("마일리지 적립/복원 금액", sdo.getMileageRestorePoint());
		        	reserveList.add(map);
		        }
	        }
	        
	        return reserveList;
	    } catch (Exception e) {
	        log.info(CommonResponseCode.MB_40024_통합회원_멤버십_포인트_조회_실패_BO포인트탭.toMessage() + " : " + e);
	        throw new RuntimeException(e);
	    }
    }

    /**
     * 회원 웹포인트 조회.
     */
    @Override
    public MemberResultDTO getMemberPurpleCoinList(SystemPK systemPK, MbrWebpntHist mbrWebpntHist, String loginId) {
        // step 1. 페이지 인덱스 및 변수 설정
        MemberResultDTO resultDTO = new MemberResultDTO();
        mbrWebpntHist.setLang(systemPK.getLang());

        // step 2. 목록 건수 조회
        long listCount = memberBenefitSelectService.selectMemberPurpleCoinListCount(mbrWebpntHist);
        resultDTO.setListCount(listCount);

        // step 3. 목록 조회
        List<MemberBoResult> lists = new ArrayList<MemberBoResult>();
        if (listCount > 0) {
            lists = memberBenefitSelectService.selectMemberPurpleCoinList(mbrWebpntHist);
        }
        resultDTO.setLists(lists);

        // step 4. 웹포인트 총액 조회
        if (listCount > 0) {
            MbrWebpntHistExtend total = memberBenefitSelectService.selectWebPointInfo(mbrWebpntHist);
            resultDTO.setMbrWebpntAmt(total);
        }

        return resultDTO;
    }


    /**
     * 회원 웹포인트 엑셀 조회.
     */
    @Override
    public List<Map<String, Object>> getMemberPurpleCoinExcel(SystemPK systemPK, MbrWebpntHist mbrWebpntHist, String loginId) {
        mbrWebpntHist.setLang(systemPK.getLang());
        return memberBenefitSelectService.selectMemberPurpleCoinExcel(mbrWebpntHist);
    }


    /**
     * 앱다운로드 포인트 적립 목록 조회.
     */
    @Override
    public MemberResultDTO getAppdownloadPointList(SystemPK systemPK, MemberBoDTO dto, String loginId) {
        // step 1. 페이지 인덱스 및 변수 설정

        MemberResultDTO resultDTO = new MemberResultDTO();
        dto.setLang(systemPK.getLang());

        // step 2. 목록 건수 조회
        long listCount = memberBaseSelectService.selectAppDownloadPntListCount(dto);
        resultDTO.setListCount(listCount);

        // step 3. 목록 조회
        List<MemberBoResult> lists = new ArrayList<MemberBoResult>();
        if (listCount > 0) {
            lists = memberBaseSelectService.selectAppDownloadPntList(dto);
        }
        resultDTO.setLists(lists);


        return resultDTO;
    }

    /**
     * 앱다운로드 포인트 적립 내역 다운로드 목록 액셀 조회.
     */
    @Override
    public List<Map<String, Object>> getAppdownloadPointListExcel(SystemPK systemPK, MemberBoDTO dto, String loginId) {
        // step 1. 회원 엑셀 조회
        dto.setLang(systemPK.getLang());
        List<Map<String, Object>> lists = memberBaseSelectService.selectAppDownloadPntListExcel(dto);

        // step 2. 개인 정보 이용 이력 등록
        if (lists != null && lists.size() > 0) {
            String[][] usefCdInfo = { // 구분, 업무, 업무상세
                    {MemberPersonalInfoEnum.UsefSectCd.PSNL_INFO_STTUS.toString(), MemberPersonalInfoEnum.UsefJobCd.SAVE.toString(), MemberPersonalInfoEnum.UsefJobDetail.EXCEL_DOWN.toString()}
            };
            String[] target = null;
            memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
                    , usefCdInfo                // 개인정보 코드 정보(구분, 업무, 업무상세)
                    , target                    // 이용대상 : 회원
                    , null                        // 유입 일련번호
                    , dto.getAccessMenuSn()            // 메뉴 일련번호
                    , loginId                    // 로그인 ID
            );
        }
        return lists;
    }


    /**
     * 회원 발급 쿠폰 목록 조회.
     */
    @Override
    public MemberResultDTO getCouponListCountForMember(SystemPK systemPK, MemberBoDTO dto, String loginId) {
        // step 1. 페이지 인덱스 및 변수 설정
        MemberResultDTO resultDTO = new MemberResultDTO();
        dto.setLang(systemPK.getLang());

        // step 2. 목록 건수 조회
        long listCount = memberBenefitSelectService.selectCouponListCountForMember(dto);
        resultDTO.setListCount(listCount);

        // step 3. 목록 조회
        List<MemberBoResult> lists = new ArrayList<MemberBoResult>();
        if (listCount > 0) {
            lists = memberBenefitSelectService.selectCouponListForMember(dto);
        }

        resultDTO.setLists(lists);

        return resultDTO;
    }

    /**
     * 회원 발행 쿠폰 엑셀 조회.
     */
    @Override
    public List<Map<String, Object>> getCouponListExcelForMember(SystemPK systemPK, MemberBoDTO dto, String loginId) {
        dto.setLang(systemPK.getLang());
        return memberBenefitSelectService.selectCouponListExcelForMember(dto);
    }

    @Override
    public Map<String, String> selectWebPointInfoMap(String mbrNo) {
        return memberBenefitSelectService.selectWebPointInfoMap(mbrNo);
    }

    @Override
    public MbrWebpntHistExtend selectWebPointInfo(MbrWebpntHist mbrWebpntHist) {
        return memberBenefitSelectService.selectWebPointInfo(mbrWebpntHist);
    }

    /**
     * 멤버쉽 포인트 조회
     */
    @Override
    public long getMemberPoint(String erpNo) {
        return memberBenefitSelectService.getMemberReserveRemainAmount(erpNo);
    }

}
