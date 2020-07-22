package com.plgrim.ncp.biz.callcenter.repository;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.biz.callcenter.data.MemberBiasDTO;
import com.plgrim.ncp.biz.callcenter.data.MemberBiasGridDTO;
import com.plgrim.ncp.biz.callcenter.data.MemberBiasSearchDTO;
import com.plgrim.ncp.biz.callcenter.result.MemberBiasResult;
import com.plgrim.ncp.biz.callcenter.result.MemberBiasResultExtend;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;


@Slf4j
@Repository
public class MemberBiasRepository extends AbstractRepository {

	@Autowired
	IdGenService idGenService;

	public void insertMemberBias(MemberBiasDTO memberBiasDTO) throws Exception {
		Long maxSn = Long.parseLong(idGenService.generateDBSequence(getSession1(), "SQ_CSO_CNSLT_MEMO", DatabaseType.ORACLE)+"");
		memberBiasDTO.setMemoSn(maxSn);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.memberbias.insertMemberBias", memberBiasDTO);
		
	}
	
	public List<MemberBiasResult> selectMemberBias(MemberBiasSearchDTO memberBiasSearchDTO) throws Exception {
		
		List<MemberBiasResult> list = getSession1().selectList("com.plgrim.ncp.biz.callcenter.memberbias.selectMemberBias", memberBiasSearchDTO);
		return list;
		
	}

	public long selectMemberBiasTotal(MemberBiasSearchDTO memberBiasSearchDTO) {

		long totalCount = getSession1().selectOne("com.plgrim.ncp.biz.callcenter.memberbias.selectMemberBiasTotal", memberBiasSearchDTO);
		return totalCount;

	}
	
	public List<MemberBiasResult> selectMemberBiasPop(MemberBiasSearchDTO memberBiasSearchDTO) throws Exception {
		List<MemberBiasResult> list = getSession1().selectList("com.plgrim.ncp.biz.callcenter.memberbias.selectMemberBiasPop", memberBiasSearchDTO);
		return list;
	}
	
	public void updateMemberBias(MemberBiasGridDTO memberBiasGridDTO) {
		memberBiasGridDTO.setUdterId(BOSecurityUtil.getLoginId());
		getSession1().update("com.plgrim.ncp.biz.callcenter.memberbias.updateMemberBias", memberBiasGridDTO);
	}


	public long selectMemberBiasPopTotal(MemberBiasSearchDTO memberBiasSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.memberbias.selectMemberBiasPopTotal", memberBiasSearchDTO);
	}

	public void updateMemberBiasPop(MemberBiasResultExtend dto) {				
		getSession1().update("com.plgrim.ncp.biz.callcenter.memberbias.updateMemberBiasPop", dto);
	}


}