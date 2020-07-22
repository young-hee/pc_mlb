package com.plgrim.ncp.biz.callcenter.repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTrans;
import com.plgrim.ncp.biz.callcenter.data.CounselTransferDTO;
import com.plgrim.ncp.biz.callcenter.data.CounselTransferSearchDTO;
import com.plgrim.ncp.biz.callcenter.result.CounselTransferlResult;
import com.plgrim.ncp.framework.page.PageParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class CounselTransferRepository extends AbstractRepository{

	public List<CounselTransferlResult> selectListCounselTransfer(CounselTransferSearchDTO counselTransferSearchDTO){
		List<CounselTransferlResult> list = getSession1().selectList("com.plgrim.ncp.biz.callcenter.counseltransfer.getListCounselTransfer", counselTransferSearchDTO);
		return list;
	}

	public List<String> getCounselTransferOrdGodsNo(String cnsltSn) {
		List<String> result = getSession1().selectList("com.plgrim.ncp.biz.callcenter.counseltransfer.getCounselTransferOrdGodsNo", cnsltSn);
	    return result;
    }
	
	public long selectCountCounselTransfer(CounselTransferSearchDTO dto) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.counseltransfer.getListCounselTransferTotal", dto);
	}

	public void updateCounselTransfer(CsoCnsltTrans csoCnsltTrans) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.counseltransfer.updateCounselTransfer", csoCnsltTrans);
	}

/*	public void addCounselTransfer(CounselTransferDTO counselTransferDTO) throws Exception{
		getSession1().insert("com.plgrim.ncp.biz.callcenter.counseltransfer.addCounselTransfer", counselTransferDTO);
	}*/

	
}