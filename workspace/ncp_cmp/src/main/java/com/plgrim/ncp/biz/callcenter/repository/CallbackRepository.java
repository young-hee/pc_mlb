package com.plgrim.ncp.biz.callcenter.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.biz.callcenter.data.CallbackGridDTO;
import com.plgrim.ncp.biz.callcenter.data.CallbackSearchDTO;
import com.plgrim.ncp.biz.callcenter.data.InfCallBackDTO;
import com.plgrim.ncp.biz.callcenter.result.CallbackResult;
import com.plgrim.ncp.framework.commons.IdGenService;


@Repository
public class CallbackRepository extends AbstractRepository {

	@Autowired
	IdGenService idGenService;


	public List<CallbackResult> selectCallbackList(CallbackSearchDTO callbackSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.callback.selectCallbackList", callbackSearchDTO);
	}

	public long selectCallbackListTotal(CallbackSearchDTO callbackSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.callback.selectCallbackListTotal", callbackSearchDTO);
	}

	public void updateCallbackGrid(CallbackGridDTO callbackGridDTO) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.callback.updateCallbackGrid", callbackGridDTO);
	}

	public void insertCallback(InfCallBackDTO infCallBackDTO) throws Exception{

		//long clbcSn = idGenService.generateDBSequence(getSession1(), "SQ_CSO_CLBC", DatabaseType.ORACLE);
		//csoCallback.setClbcSn(clbcSn);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.callback.insertCallback", infCallBackDTO);
	}
}