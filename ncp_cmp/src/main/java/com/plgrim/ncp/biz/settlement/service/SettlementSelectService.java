package com.plgrim.ncp.biz.settlement.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SettlementSelectService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;

	@Autowired
	InterfaceApiCommon interfaceApiCommon;


	/**카카오 알림톡 발송내역 건수조회
	 * @param noticeSDO
	 * @return
	 * @throws Exception
	 */
//	public HumusonHistListResult selectKakaoSendHist(NoticeSDO noticeSDO) throws Exception {
//
//		AdapterHeader adapterHeader = this.setAdapterHeader();
//		HumusonHistListResult list = new HumusonHistListResult();
//
//		try {
//			list = noticeAdapter.selectAlimTalkHist(noticeSDO, adapterHeader);
//		} catch(Exception e){
//			//log.info("selectAlimTalkListForMember Call Exception {}", e);
//		}
//
//		return list;
//	}

	/**
	 * AdapterHeader 값 설정
	 * @return
	 */
	private AdapterHeader setAdapterHeader(){
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId("DXM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");

		return adapterHeader;
	}


}
