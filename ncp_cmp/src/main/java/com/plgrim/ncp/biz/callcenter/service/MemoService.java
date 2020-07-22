package com.plgrim.ncp.biz.callcenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;
import com.plgrim.ncp.biz.callcenter.data.CsoCnsltMemoExtendDTO;
import com.plgrim.ncp.biz.callcenter.repository.MemoRepository;

@Service
public class MemoService extends AbstractService {


	@Autowired
	MemoRepository memoRepository;
	
	
	public void insertCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) throws Exception{
		memoRepository.insertCsoCnsltMemo(csoCnsltMemo);
	}
	
	public List<CsoCnsltMemoExtendDTO> selectCsoCnsltMemoList(CsoCnsltMemo csoCnsltMemo) throws Exception{
		return memoRepository.selectCsoCnsltMemoList(csoCnsltMemo);
	}

	public CsoCnsltMemo getCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) {
		return memoRepository.getCsoCnsltMemo(csoCnsltMemo);
    }

	public void updateCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) {
	    // TODO Auto-generated method stub
		memoRepository.updateCsoCnsltMemo(csoCnsltMemo);
    }

}
