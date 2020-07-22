package com.plgrim.ncp.biz.callcenter.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;
import com.plgrim.ncp.biz.callcenter.data.CsoCnsltMemoExtendDTO;

@Repository
public class MemoRepository extends AbstractRepository {

	public void insertCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) throws Exception{
		getSession1().insert("com.plgrim.ncp.biz.callcenter.memo.insertCsoCnsltMemo",csoCnsltMemo);
    }

	public List<CsoCnsltMemoExtendDTO> selectCsoCnsltMemoList(CsoCnsltMemo csoCnsltMemo)  throws Exception{	    
	    return getSession1().selectList("com.plgrim.ncp.biz.callcenter.memo.selectCsoCnsltMemoList",csoCnsltMemo);
    }

	public CsoCnsltMemo getCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) {	    
	    return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.memo.getCsoCnsltMemo",csoCnsltMemo);
    }

	public void updateCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) {
	    // TODO Auto-generated method stub
		getSession1().update("com.plgrim.ncp.biz.callcenter.memo.updateCsoCnsltMemo",csoCnsltMemo);
    }

}
