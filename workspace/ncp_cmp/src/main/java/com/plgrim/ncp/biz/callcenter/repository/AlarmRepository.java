package com.plgrim.ncp.biz.callcenter.repository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.biz.callcenter.data.AlarmMessageSearchDTO;
import com.plgrim.ncp.biz.callcenter.result.AlarmResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.IdGenService;


@Repository
public class AlarmRepository extends AbstractRepository {

	@Autowired
	IdGenService idGenService;

	public List<AlarmResult> selectAlarmMessage(AlarmMessageSearchDTO alarmMessageSearchDTO) {
	    return getSession1().selectList("com.plgrim.ncp.biz.callcenter.alarmmessage.selectAlarmMessage", alarmMessageSearchDTO);
    }

	public long selectAlarmMessageTotal(AlarmMessageSearchDTO alarmMessageSearchDTO) {
	    return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.alarmmessage.selectAlarmMessageTotal", alarmMessageSearchDTO);
    }

	public List<AlarmResult> selectCounselTransferAlarmMessageList(AlarmMessageSearchDTO alarmMessageSearchDTO) {
		
		alarmMessageSearchDTO.setRegtrId(BOSecurityUtil.getLoginId());
		//alarmMessageSearchDTO.setTransRecrId(BOSecurityUtil.getLoginId());
		
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.alarmmessage.selectCounselTransferAlarmMessageList", alarmMessageSearchDTO);
    }

	public long selectCounselTransferAlarmMessageListTotal(AlarmMessageSearchDTO alarmMessageSearchDTO) {
	    return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.alarmmessage.selectCounselTransferAlarmMessageListTotal", alarmMessageSearchDTO);
    }

	public int selectTransferAlarmCount(String loginId) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.alarmmessage.selectTransferAlarmCount", loginId);
	}

	public int selectPromiseCallCount(String loginId) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.alarmmessage.selectPromiseCallCount", loginId);
	}

	public int selectNotiCount() {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.alarmmessage.selectNotiCount");
	}

	public void updateConfirmInPromiseCall(Map param) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.alarmmessage.updateConfirmInPromiseCall", param);
	}

	public void updateConfirmInTransfer(Map param) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.alarmmessage.updateConfirmInTransfer", param);
	}
}