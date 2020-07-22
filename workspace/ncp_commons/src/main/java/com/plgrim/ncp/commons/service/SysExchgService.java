package com.plgrim.ncp.commons.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysExchgRt;
import com.plgrim.ncp.commons.data.FormSysExchgDTO;
import com.plgrim.ncp.commons.data.SysExchgDTO;
import com.plgrim.ncp.commons.repository.SysExchgRepository;
import com.plgrim.ncp.commons.result.SysExchgResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
public class SysExchgService extends AbstractService {

	@Autowired
	SysExchgRepository sysExchgRepository;
	
	public List<SysExchgResult> selectSysExchgList ( FormSysExchgDTO paramData) throws Exception {
			return sysExchgRepository.selectSysExchgList(paramData);
	}

	/**
	 * Mall Site 저장 처리
	 * @param paramData
	 */
	
	public void mergeExchg ( SysExchgDTO paramData ) throws Exception {
		sysExchgRepository.mergeExchg(paramData);
		
	}
	
	/**
	 * 환율 목록 엑셀.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectSysExchgListExcel(FormSysExchgDTO paramData) throws Exception {
	    return sysExchgRepository.selectSysExchgListExcel(paramData);
    }
	
	/**
	 * 환율 목록 개수.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysExchgListCount(FormSysExchgDTO paramData) throws Exception {
	    return sysExchgRepository.selectSysExchgListCount(paramData);
    }
	
	/**
	 * 환율 등록.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void insertExchg(SysExchgDTO paramData) throws Exception {
		String loginId = BOSecurityUtil.getLoginId(); // 로그인ID
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		String aplBegDt = paramData.getExchgRtAplBegDtDay() + " " + paramData.getExchgRtAplBegDtHour();
		
		//paramData.getSysExchgRt().setAplBegDt(getDateService().createDate(aplBegDt, "yyyy-MM-dd HH:mm")); // 적용시작일
		//paramData.getSysExchgRt().setAplEndDt(getDateService().createDate("9999-12-31" , "yyyy-MM-dd")); // 적용종료일
		paramData.getSysExchgRt().setExchgRtAplBegDt(sdf.parse(aplBegDt)); // 적용시작일
		paramData.getSysExchgRt().setExchgRtAplEndDt(sdf.parse("9999-12-31 23:59:59")); // 적용종료일
		paramData.getSysExchgRt().setRegtrId(loginId); // 등록자
		paramData.getSysExchgRt().setUdterId(loginId); // 수정자

		SysExchgRt sysExchgRt = sysExchgRepository.selectSysExchgCrncyCd(paramData.getSysExchgRt().getCrncyCd());
		if(sysExchgRt != null){
			sysExchgRt.setExchgRtAplEndDt(sdf.parse(aplBegDt));
			sysExchgRt.setUdterId(loginId); // 수정자
			sysExchgRepository.updateAplEndDt(sysExchgRt); // 기존에 적용되던 환율 종료처리			
		}
		
		sysExchgRepository.insertSysExchgRt(paramData.getSysExchgRt()); // 신규등록

		/**
		 * 환율 정보 등록 후 전시상품가격 프로시저 즉시 실행
		 * */
		sysExchgRepository.updateDspGoodsPriceProcedure();
    }
	
	/**
	 * 환율 목록 개수.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectSysExchgDupCount(SysExchgDTO paramData) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		String aplBegDt = paramData.getExchgRtAplBegDtDay() + " " + paramData.getExchgRtAplBegDtHour();
		
		paramData.getSysExchgRt().setExchgRtAplBegDt(sdf.parse(aplBegDt)); // 적용시작일
		
	    return sysExchgRepository.selectSysExchgDupCount(paramData.getSysExchgRt());
    }
}
