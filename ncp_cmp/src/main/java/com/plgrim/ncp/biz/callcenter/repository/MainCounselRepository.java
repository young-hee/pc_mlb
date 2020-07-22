package com.plgrim.ncp.biz.callcenter.repository;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdmin;
import com.plgrim.ncp.biz.callcenter.data.*;
import com.plgrim.ncp.biz.callcenter.result.*;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;


@Repository
public class MainCounselRepository extends AbstractRepository {

	public MemberBiasResult selectMemberBias(String mbrNo) throws Exception{
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectMemberBias", mbrNo);
	}

	public RefundBankAccountResult selectRefundBankAccount(String mbrNo) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectRefundBankAccount", mbrNo);
	}

	public List<SelectNewOrderResult> seletNewOrderList(CounselNewOrderSearchDTO counselNewOrderSearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.maincounsel.selectNewOrderList", counselNewOrderSearchDTO);
	}

	public long seletNewOrderListTotal(CounselNewOrderSearchDTO counselNewOrderSearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectNewOrderListTotal", counselNewOrderSearchDTO);
	}
	
	public List<MainCounselResult> selectMainCounselList(CounselNewOrderSearchDTO counselNewOrderSearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.maincounsel.selectMainCounselList", counselNewOrderSearchDTO);
	}
	
	public long selectMainCounselListTotal(CounselNewOrderSearchDTO counselNewOrderSearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectMainCounselListTotal", counselNewOrderSearchDTO);
	}
	
	
	public List<MainCounselResult> selectWorkConditionList(CounselNewOrderSearchDTO counselNewOrderSearchDTO) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.maincounsel.selectWorkConditionList", counselNewOrderSearchDTO);
	}
	
	
	public MainCounselResult selectCallListCount(CounselNewOrderSearchDTO counselNewOrderSearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectCallListCount", counselNewOrderSearchDTO);
	}

	public List<SelectMemberResult> selectMemberNo(MainCounselDTO mainCounselDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.maincounsel.selectMemberNo", mainCounselDTO);
	    
    }

	public List<SelectNotMemberResult> selectNotMemberSearchList(MemberSearchDTO memberSearchDTO) throws Exception {
		
		List<SelectNotMemberResult> list = getSession1().selectList("com.plgrim.ncp.biz.callcenter.maincounsel.selectNotMemberSearchList", memberSearchDTO);
		
		/*for (int i = 0 ; i < list.size(); i++){
			
			if(list.get(i).getMobilNationNo() != null && list.get(i).getMobilTlofNo() != null && list.get(i).getMobilTlofWithNo() != null){
				list.get(i).setTel(list.get(i).getMobilNationNo() + "-" + list.get(i).getMobilTlofNo() + "-" + list.get(i).getMobilTlofWithNo());
			}else{
				list.get(i).setTel(list.get(i).getOtelNationNo() + "-" + list.get(i).getOtelTlofNo() + "-" + list.get(i).getOtelTlofWithNo());
			}
		}*/
		
	    return list; 
    }
	
	public long selectNotMemberSearchListTotal(MemberSearchDTO memberSearchDTO) throws Exception {
	    return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectNotMemberSearchListTotal", memberSearchDTO);
    }


	public SysNotiResult selectNotiOne() {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectNotiOne");
	}

	public int  selectPromsclOne(String loginId) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectPromsclOne", loginId);
	}

	public MainCounselResult selectPromsclListCount(String loginId) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectPromsclListCount", loginId);
	}

	public MainCounselResult selectCounselListCount(String loginId) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectCounselListCount", loginId);
	}

	public MainCounselResult selectTransferListCount(String loginId) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectTransferListCount", loginId);
	}

	public List<CounselResult> selectCounselList(CounselSearchDTO counselSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.maincounsel.selectCounselList", counselSearchDTO);
	}

	public long selectCounselListTotal(CounselSearchDTO counselSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectCounselListTotal", counselSearchDTO);
	}

	public InsertCounselUserInfoResult selectCounselUserInfo(CounselSearchDTO counselSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectCounselUserInfo", counselSearchDTO);
	}

	public List<CounselResult> selectInquiryList(CounselSearchDTO counselSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.maincounsel.selectInquiryList", counselSearchDTO);
	}

	public long selectInquiryListTotal(CounselSearchDTO counselSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectInquiryListTotal", counselSearchDTO);
	}

	public List<ChatInquiryResult> selectChatList(ChatInqSearchDTO searchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.maincounsel.selectChatList", searchDTO);
	}

	public long selectChatListTotal(ChatInqSearchDTO searchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectChatListTotal", searchDTO);
	}

	public List<MemberGoodsQnaResult> selectQnaList(MemberGoodsQnaSearchDTO memberGoodsQnaSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.maincounsel.selectQnaList", memberGoodsQnaSearchDTO);
	}

	public long selectQnaListTotal(MemberGoodsQnaSearchDTO memberGoodsQnaSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectQnaListTotal", memberGoodsQnaSearchDTO);
	}

	public InsertCounselUserInfoResult selectCounselUserAndOrdInfo(CounselSearchDTO counselSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectCounselUserAndOrdInfo", counselSearchDTO);
	}


	public SysAdmin selectCtiInfo(String loginId) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectCtiInfo", loginId);
	}

	public void insertCtiCallInfo(CsoCnsltClHistDTO csoCnsltClHistDTO) {
		getSession1().insert("com.plgrim.ncp.biz.callcenter.maincounsel.insertCtiCallInfo", csoCnsltClHistDTO);
	}

	public List<SmsEmailResult> selectSmsList(String tel) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.maincounsel.selectSmsList", tel);
	}

	public long selectSmsListTotal(String tel) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectSmsListTotal", tel);
	}

	public List<SmsEmailResult> selectEmailList(MemberGoodsQnaSearchDTO memberGoodsQnaSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.maincounsel.selectEmailList", memberGoodsQnaSearchDTO);
	}

	public long selectEmailListTotal(MemberGoodsQnaSearchDTO memberGoodsQnaSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectEmailListTotal", memberGoodsQnaSearchDTO);
	}

	public MainCounselOrdResult selectOrdNo(String ordNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectOrdNo", ordNo);
	}

	public long selectGodNo(String godNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectGodNo", godNo);
	}

	public MainCounselNotMemberInfoResult selectNotMemberInfo(Map<String, String> map) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectNotMemberInfo", map);
	}

	public AlarmResult selectPromsclFiveMinutesAgo(String regtrId) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectPromsclFiveMinutesAgo", regtrId);
	}
	
	public AlarmResult selectPromiseCallWait(String regtrId) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectPromiseCallWait", regtrId);
	}
	public List<AlarmResult> selectTrans(String userId) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.maincounsel.selectTrans", userId);
	}

	public int selectNotMemberInfoCount(Map<String, String> map) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectNotMemberInfoCount", map);
	}

	public BaseAddrResult selectUserBaseAddress(String ordNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectUserBaseAddress", ordNo);
	}

	public MainCounselNotMemberInfoResult selectCounselNotMemeberInfo(Map<String, String> map) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectCounselNotMemeberInfo", map);
	}

	public InsertCounselUserInfoResult selectCounselOrdInfo(CounselSearchDTO counselSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectCounselOrdInfo", counselSearchDTO);
	}

	public String selectMbrId(String mbrNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectMbrId", mbrNo);
	}

	public int selectRejoinCntByMbrNo(String mbrNo) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectRejoinCntByMbrNo", mbrNo);
	}

	public SysAdminResult selectAdminInfo(String loginId) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.maincounsel.selectAdminInfo", loginId);
	}
}