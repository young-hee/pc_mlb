package com.plgrim.ncp.cmp.common.bo.system.post;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.plgrim.ncp.cmp.common.bo.system.SystemPostBOComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.entities.datasource1.sys.SysFileUpload;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPlc;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPlcVal;
import com.plgrim.ncp.biz.zipcode.data.SysFileUploadBoDTO;
import com.plgrim.ncp.biz.zipcode.data.SysFileUploadExtend;
import com.plgrim.ncp.biz.zipcode.service.SysZipcodeService;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;

@Transactional(value = "transactionManager")
@Component
public class SystemPostBOComponentImpl implements SystemPostBOComponent {


    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */
    @Autowired
    private SysZipcodeService sysZipcodeService;

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
    /**
     * 우편번호방식 변경
     *
     * @param plcVal [설명]
     * @return the vendor list
     * @throws Exception the exception
     * @since 2016. 11. 29
     */
    @Override
    public void mergePostNoIntrlckMenmthd(String plcVal) throws Exception {
        String userId = BOSecurityUtil.getLoginId();        // 세션ID

        SysPlc sysPlc = new SysPlc();
        sysPlc.setMallId("DXM");
        sysPlc.setPlcCd("POST_NO_INTRLCK_MENMTHD");

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        sysPlc.setAplBegDt(simpleDateFormat.parse(simpleDateFormat.format(date)));
        sysPlc.setAplEndDt(simpleDateFormat.parse("9999-12-31 23:59:59"));
        sysPlc.setUseYn("Y");
        sysPlc.setRegtrId(userId);
        sysPlc.setUdterId(userId);
        sysZipcodeService.mergeSysPlcPostNoIntrlckMenmthd(sysPlc);

        SysPlc selectSysPlcPostNo = sysZipcodeService.selectSysPlcPostNo(sysPlc);
        if (selectSysPlcPostNo != null) {
            SysPlcVal sysPlcVal = sysZipcodeService.selectSysPlcValPostNo(sysPlc);

            if (sysPlcVal == null) {
                sysPlcVal = new SysPlcVal();
                sysPlcVal.setPlcValTurn(1);
            }

            sysPlcVal.setMallId(selectSysPlcPostNo.getMallId());
            sysPlcVal.setPlcCd(selectSysPlcPostNo.getPlcCd());
            sysPlcVal.setAplBegDt(selectSysPlcPostNo.getAplBegDt());
            sysPlcVal.setPlcValBegDt(simpleDateFormat.parse(simpleDateFormat.format(date)));
            sysPlcVal.setPlcValEndDt(simpleDateFormat.parse("9999-12-31 23:59:59"));
            sysPlcVal.setPlcVal(plcVal);
            sysPlcVal.setPlcValTpCd("CHAR");
            sysPlcVal.setUseYn("Y");
            sysPlcVal.setRegtrId(userId);
            sysPlcVal.setUdterId(userId);

            sysZipcodeService.mergeSysPlcValPostNo(sysPlcVal);
        }
    }

	/**
	 * 시스템 파일 업로드 이력 조회.
	 *
	 * @param sysFileUpload
	 * @return SysFileUploadExtend
	 * @throws Exception
	 */
	public SysFileUploadExtend selectSysFileUploadInfo(SysFileUpload sysFileUpload) throws Exception {
		SysFileUploadExtend result = sysZipcodeService.selectSysFileUploadInfo(sysFileUpload);
		return result;
	}
    
	/**
	 * 시스템 파일 업로드 등록.
	 *
	 * @param sysFileUpload
	 * @return sysFileUpload
	 * @throws Exception
	 */
	public SysFileUpload insertSysFileUpload(SysFileUpload sysFileUpload) throws Exception {
		String userId = BOSecurityUtil.getLoginId();        // 세션ID
		sysFileUpload.setRegtrId(userId);
		sysFileUpload.setUdterId(userId);
		sysZipcodeService.insertSysFileUpload(sysFileUpload);
		
		return sysFileUpload;
	}
	
	/**
	 * 시스템 파일 업로드 진행 상태 변경
	 * 
	 * @param sysFileUploadBoDTO
	 * @return String result
	 * @throws Exception the exception
	 * @since 2017. 12. 11
	 */
    @Override
    public String updateProgressStatusCode(SysFileUploadBoDTO sysFileUploadBoDTO) throws Exception {
        String userId = BOSecurityUtil.getLoginId();        // 세션ID
        String resultCode = "SUCCESS";

		sysFileUploadBoDTO.getSysFileUpload().setUdterId(userId);
		// 이력 update
		int result = sysZipcodeService.updateSysFileUpload(sysFileUploadBoDTO.getSysFileUpload());
        
		if(result == 0) {
			resultCode = "FAIL";
		}
		
        return resultCode;
    }
	
	/**
	 * 변경 승인 처리
	 * 
	 * @param sysFileUploadBoDTO
	 * @return String result
	 * @throws Exception the exception
	 * @since 2017. 12. 11
	 */
    @Override
    public String addrChangeApprove(SysFileUploadBoDTO sysFileUploadBoDTO) throws Exception {
        String userId = BOSecurityUtil.getLoginId();        // 세션ID
        String resultCode = "SUCCESS";

        // 이력 조회
        SysFileUploadExtend sysFileUploadExtend = sysZipcodeService.selectSysFileUploadInfo(sysFileUploadBoDTO.getSysFileUpload());
        
        // 정책 조회하여 제한건수 조회
		SysPlc sysPlc = new SysPlc();
		sysPlc.setMallId("DXM");
		sysPlc.setPlcCd("생성되는 정책 코드");
		SysPlcVal sysPlcVal = sysZipcodeService.selectSysPlcValPostNo(sysPlc);

		java.lang.Integer cnt = 0;
		
		try {
			cnt = Integer.parseInt(sysPlcVal.getPlcVal());
		}
		catch(Exception e) {
			// 정책에서 가져오지 못하면 초기값 설정
			cnt = 1000000;
		}
		
        // 검증
        if(sysFileUploadExtend == null) {
        	resultCode = "NO_DATA_FOUND";
        }
        else if(!"APRV_WAIT".equals(sysFileUploadExtend.getProgrsStatCd())) {
    		resultCode = "INCORRECT_STATUS";
    	}
        else {
    		java.lang.Integer modBfCnt = sysFileUploadExtend.getModBfCnt();
    		java.lang.Integer modAfCnt = sysFileUploadExtend.getModAfCnt();
    		java.lang.Integer modCnt = modAfCnt - modBfCnt;
    		if(modCnt < 0 && Math.abs(modCnt) > cnt) {
    			resultCode = "LIMITED_COUNT";
    		}
        }
        
        if("SUCCESS".equals(resultCode)) {
	        // 테이블 변경
	        resultCode = sysZipcodeService.tableRename();
        }
        
        if("SUCCESS".equals(resultCode)) {
        	// 인덱스 변경
        	resultCode = sysZipcodeService.indexRename();
        }

        String progrsStatCd = "APRV_COMPT";
        if("LIMITED_COUNT".equals(resultCode)) {
        	progrsStatCd = "APRV_WAIT";
        	String failrResnCont = "변경후의 건수가 변경전의 건수보다 " + StringService.integerToString(cnt) + " 건 이상 적어서 승인 처리가 되지 않았습니다.";
        	failrResnCont += "\n정책관리에서 제한건수 변경 후 승인이 가능합니다.";
        	sysFileUploadBoDTO.getSysFileUpload().setFailrResnCont(failrResnCont);
        }
        else if(!"SUCCESS".equals(resultCode)) {
        	progrsStatCd = "APRV_FAILR";
        }
        sysFileUploadBoDTO.getSysFileUpload().setProgrsStatCd(progrsStatCd);
		sysFileUploadBoDTO.getSysFileUpload().setUdterId(userId);
		// 이력 update
		sysZipcodeService.updateSysFileUpload(sysFileUploadBoDTO.getSysFileUpload());
        
        return resultCode;
    }
    
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
