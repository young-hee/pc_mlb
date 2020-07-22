package com.plgrim.ncp.interfaces.event.data;

import java.util.Date;
import java.util.Map;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtApplcn;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtFreeGiftInfo;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;

import lombok.Data;

@Alias("snsKakaoEventSDO")
@Data
public class SnsKakaoEventSDO {

	SnsKakaoEventInfResult snsKakaoEventInfResult;
	
	Evt evt;
	
	EvtApplcn evtApplcn;
	
	EvtFreeGiftInfo evtFreeGiftInfo;	
	
	Mbr mbr;
	
	private String lang;

	private String mallId;
	
	private String dvcCd;
	
//kakao 링크 공유 파라미터
	private String mbrNo; //회원 번호
	private Date snsCnrsDate; //공유일자
	private String cnrsSnsCd; // 공유 sns 코드
	private String evtNo; // 이벤트 넘버
	private String freeGiftInfo;
	private String multiDownloadYn; // Y/N
	private String prizeRank;
	private String cnrsUrl;
	
	
	
	
	
	
	private String Authorization;
	private String Resource_ID;
	private String CHAT_TYPE;
	private Number TEMPLATE_ID;
	private String content_id;
	private Map<String, String> serverCallbackArgs;
	
	
}
