package com.plgrim.ncp.commons.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysPlcValExtend;
import com.plgrim.ncp.base.enums.JsonEnum;
import com.plgrim.ncp.commons.data.FormSysPolicyDTO;
import com.plgrim.ncp.commons.repository.SysPolicyRepository;
import com.plgrim.ncp.commons.result.SysPolicyResult;

@Service
public class SysPlcService extends AbstractService {

	@Autowired
	SysPolicyRepository sysPolicyRepository;
	
	/**
	 * 정책 리스트 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<SysPolicyResult> selectListPlc( FormSysPolicyDTO paramData) throws Exception {
		
		return sysPolicyRepository.selectListPlc(paramData);
	}
	
	/**
	 * 정책 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public SysPolicyResult selectPlcOne( FormSysPolicyDTO paramData) throws Exception {
		
		return sysPolicyRepository.selectPlcOne(paramData);
	}
	
	/**
	 * 정책 값 리스트 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<SysPolicyResult> selectListPlcVal( FormSysPolicyDTO paramData) throws Exception {
		
		return sysPolicyRepository.selectListPlcVal(paramData);
	}
	
	/**
	 * 정책 값 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public SysPolicyResult selectPlcValOne( FormSysPolicyDTO paramData) throws Exception {
		
		return sysPolicyRepository.selectPlcValOne(paramData);
	}
	
	/**
	 * 정책 값 등록
	 * @param paramData
	 * @throws Exception
	 */
	public int insertSysPlcVal ( SysPlcValExtend paramData) throws Exception{
		
		return sysPolicyRepository.insertSysPlcVal(paramData);
	}
	
	/**
	 * 정책 값 수정
	 * @param paramData
	 * @throws Exception
	 */
	public int updateSysPlcVal ( SysPlcValExtend paramData) throws Exception{
		
		return sysPolicyRepository.updateSysPlcVal(paramData);
	}
	
	public void refreshMviewPolicy() throws Exception{
		// 수정된 데이터 적용을 위한 MView 갱신
		sysPolicyRepository.refreshMviewPolicy();
	}
	
	/**
	 * 정책 값 시작일시, 종료일시등의 VALIDATION 체크
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> checkValidation( FormSysPolicyDTO paramData) throws Exception{
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String msg = "";
		String result = JsonEnum.JSON_STATUS_FAIL.toString();
		
		Date plcValEndDt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paramData.getSelPlcValEndDt());	//종료일시
		
		if(plcValEndDt.compareTo(Calendar.getInstance().getTime()) < 0){	//현시점보다 과거 종료일시 삽입, 수정 금지
			msg = "정책 값 종료일시는 현재보다 이전으로 설정할 수 없습니다.";
		}else if(paramData.getSelPlcValTurn() != 0 && sysPolicyRepository.isPastPlcVal( paramData)){	// 과거 이력인 경우
			msg = "종료된 정책은 수정할 수 없습니다.";
		}else if(!sysPolicyRepository.isValidPlcValBegDt( paramData)){	// 정책 값 시작일시 유효여부 확인
			msg = "정책 값의 시작일시가 정책의 적용기간과 맞지 않습니다.";
		}else if(!sysPolicyRepository.isValidPlcValEndDt( paramData)){	//정책 값 종료일시 유효여부 확인
			msg = "정책 값의 종료일시가 정책의 적용기간과 맞지 않습니다.";
		}else if("Y".equals(paramData.getSelUseYn()) && sysPolicyRepository.isDuplicatePeriod( paramData)){	// 같은 정책값과의 중복여부 확인
			if("Y".equals(paramData.getSelCmpndValPsbYn())){	// 다중 값 허용하는 경우
				msg = "같은 값을 가지는 정책과 기간이 중복됩니다.";	
			}else{
				msg = "기간이 중복되는 정책이 있습니다.";
			}
		}else{
			result = JsonEnum.JSON_STATUS_SUCCESS.toString();
		}
		
		resultMap.put(JsonEnum.JSON_STATUS_KEY.toString(), result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}
	
	/**
	 * 과거 이력여부 체크
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public boolean isPastPlcVal( FormSysPolicyDTO paramData) throws Exception{
		
		return sysPolicyRepository.isPastPlcVal( paramData);
	}
	
	
}
