package com.plgrim.ncp.biz.callcenter.repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.cso.*;
import com.plgrim.ncp.biz.callcenter.data.ChatInqSearchDTO;
import com.plgrim.ncp.biz.callcenter.result.*;
import com.plgrim.ncp.framework.page.PageParam;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ChatInqRepository extends AbstractRepository{

	public long selectCountPlgrimTalk(ChatInqSearchDTO searchDTO) throws Exception{
		long totalCount =0;
		totalCount = getSession1().selectOne("com.plgrim.ncp.biz.callcenter.chat.getListChatTotal", searchDTO);
		return  totalCount;
	}

	public List<ChatInquiryResult> getListPlgrimTalk(ChatInqSearchDTO searchDTO , PageParam pageParam ){
		List<ChatInquiryResult> results = new ArrayList<>() ;
		RepositoryHelper.makePageEntityIndex(searchDTO, pageParam);
		results = getSession1().selectList("com.plgrim.ncp.biz.callcenter.chat.getListChat",searchDTO);

		return results;
	}

	public MtmUserInfoResult selectChatUserInfo(long chatInqSn){
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.chat.selectChatUserInfo", chatInqSn);

	}

	public ChatInquiryResult selectChatInqInfo(long chatInqSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.chat.selectChatInqInfo", chatInqSn);

	}

	public String selectChatCnsltSn(long chatInqSn){
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.chat.selectChatCnsltSn", chatInqSn);

	}

	public String selectChatSn(long cnsltSn){
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.chat.selectChatSn", cnsltSn);
	}

	public List<DetailChatInqResult> getDetailChatInqList (CsoCnslt csoCnslt){
		List<DetailChatInqResult> results = new ArrayList<>() ;
		results = getSession1().selectList("com.plgrim.ncp.biz.callcenter.chat.getDetailChatInqList",csoCnslt);
		return results;
	}


	public ChatInquiryResult selectChatInqDetail(long chatInqSn){
		ChatInquiryResult chatInqResult = new ChatInquiryResult();
		chatInqResult = getSession1().selectOne("com.plgrim.ncp.biz.callcenter.chat.selectChatInqDetail", chatInqSn);
		return chatInqResult;
	}


}