/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 3. 27       
 */
package com.plgrim.ncp.cmp.common.bo.operation.user;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.sys.*;
import com.plgrim.ncp.cmp.common.bo.operation.OperationUserBOComponent;
import com.plgrim.ncp.commons.data.FormSysAdmDTO;
import com.plgrim.ncp.commons.data.SysCmmnNotiDTO;
import com.plgrim.ncp.commons.result.*;
import com.plgrim.ncp.commons.service.SysAdmService;
import com.plgrim.ncp.commons.service.SysAdminBukmkService;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.commons.util.BOStringUtil;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.interfaces.email.adapter.EmailAdapter;
import com.plgrim.ncp.interfaces.email.data.EmailHtmlSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Transactional(value = "transactionManager")
@Component
public class OperationUserBOComponentImpl extends AbstractComponent implements OperationUserBOComponent {

	
	@Autowired
	SysAdmService sysAdmService;

	@Autowired
	SysAdminBukmkService sysAdminBukmkService;

	@Autowired
	InterfaceApiCommon interfaceApiCommon;

	@Autowired
	EmailAdapter emailAdapter;

	/**
	 * 운영자정보를 수정한다.
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public void insertAdminInfo ( FormSysAdmDTO paramData) throws Exception{
		
		sysAdmService.insertAdminInfo(paramData);
	}
	
	/**
	 * 운영자정보를 수정한다.
	 * @param paramData
	 * @throws Exception
	 */
	public void updateAdminInfo ( FormSysAdmDTO paramData) throws Exception{
		sysAdmService.updateAdminInfo(paramData);
	}
	
	/**
	 * 운영자 임시비밀번호 발급
	 * @param paramData
	 * @throws Exception
	 */
	public void updateAdminTempPassword( FormSysAdmDTO paramData) throws Exception{
		
		//임시비밀번호
		String tempPwd = sysAdmService.poUpdateAdminTempPassword(paramData.getSysAdmin());
		log.info( paramData.getSysAdmin().getAdminId() + ") 임시비밀번호 : "+tempPwd); 
		
		AdmResult adminInfo = sysAdmService.selectRowAdminInfo(paramData);

		try {
			
			if( StringService.isEmail(adminInfo.getSysAdmin().getEmail())){
				AdapterHeader adapterHeader = new AdapterHeader();
				adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
				adapterHeader.setMallId("DXM");
				adapterHeader.setLangCd("KOR");
				adapterHeader.setDvcCd("PC");
			   
				EmailHtmlSDO emailHtmlSDO = new EmailHtmlSDO();

				emailHtmlSDO.setMallEmail("DISCOVERY <discovery@fnf.co.kr>");
				emailHtmlSDO.setMbrEmail(adminInfo.getSysAdmin().getEmail());
				emailHtmlSDO.setSubject("[Discovery Expedition] 운영자 비밀번호 초기화.");
				emailHtmlSDO.setHtmlBody("임시비밀번호 : " + tempPwd);

				log.info(emailAdapter.sendHtmlEmail(emailHtmlSDO, adapterHeader));	
			}			
			
		} catch (Exception e) {
		  log.warn("ERROR !!! sending inquiry mail >>>", e);
		}
		
	}
	
	/**
	 * PO 비밀번호 분실 - 회원 리스트 조회 (존재여부)
	 * @param paramData
	 * @throws Exception
	 */
	public List<SysAdmin> poSelectLostPasswordList(SysAdmin paramData) throws Exception{
		return sysAdmService.poSelectLostPasswordList(paramData);
	}
	public List<SysAdmin> poSelectLostInfo(SysAdmin paramData) throws Exception{
		return sysAdmService.poSelectLostInfo(paramData);
	}

	/**
	 * PO 비밀번호 분실, 임시비밀번호 발급 (로그인 정보가 없는 사용자 요청임)
	 * @param paramData
	 * @throws Exception
	 */
	public String poRequestAdminLostPassword(SysAdmin sysAdmin) throws Exception{
		FormSysAdmDTO paramData = new FormSysAdmDTO();
		paramData.setSysAdmin(sysAdmin);

		//임시비밀번호
		String tempPwd = sysAdmService.poUpdateAdminTempPassword(paramData.getSysAdmin());
		log.info( paramData.getSysAdmin().getAdminId() + ") 임시비밀번호 : "+tempPwd);

		AdmResult adminInfo = sysAdmService.poSelectRowAdminInfo(paramData);

		// TODO 이메일 솔루션 적용

		//이메일발송...
//		if( getStringService().isEmail(  adminInfo.getSysAdmin().getEmail() ) ){
//
//			List<EmailSDO> parameterList = new ArrayList<EmailSDO>();
//
//			EmailSDO parameter1 = new EmailSDO();
//			parameter1.setAutocode("");
//			parameter1.setUserId( adminInfo.getSysAdmin().getAdminId()  );
//			parameter1.setAutotype("A02");
//			parameter1.setEmail( adminInfo.getSysAdmin().getEmail());
//			parameter1.setName( adminInfo.getSysAdmin().getAdminNm());
//
////			parameter1.setInsertdate( Calendar.getInstance().getTime());
//			parameter1.setInsertdate( this.interfaceApiCommon.getCurrentDate() );
//			parameter1.setSendtime(this.interfaceApiCommon.getCurrentDate());
//			parameter1.setSendyn("N");
//			parameter1.setOpentime("");
//			parameter1.setSenttime("");
//			parameter1.setCmpncode("");
//			parameter1.setFromaddress("");
//			parameter1.setFromname("");
//			parameter1.setTitle("운영자 비밀번호 초기화");
//			parameter1.setTag1(getDateService().parseString(Calendar.getInstance().getTime(), "yyyy-MM-dd HH:mm:ss"));
//			parameter1.setTag2(tempPwd);
//			parameter1.setTag3("");
//			parameter1.setTag4("");
//			parameter1.setTag5("");
//			parameter1.setTag6("");
//			parameter1.setTag7("");
//			parameter1.setTag8("");
//			parameter1.setTag9("");
//			parameter1.setTag10("");
//			parameter1.setTag11("");
//			parameter1.setTag12("");
//			parameter1.setCallerId(InterfaceAdapterEnum.INTERFACE_ADAPTER_UPDATE_ADMIN_TEMP_PASSWORD.toString());
//			parameter1.setAdminId(adminInfo.getSysAdmin().getAdminId());
//
//			parameterList.add(parameter1);
//
//			// Adapter Header 를 세팅한다.
//			AdapterHeader adapterHeader = new AdapterHeader();
//			adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
//			adapterHeader.setMallId("DXM");
//			adapterHeader.setLangCd("KOR");
//			adapterHeader.setDvcCd("PC");
//
//			log.info("> junit test : {}", emailAdapter.sendEmail(parameterList, adapterHeader));
//		}

		return tempPwd;
	}

	/**
	 * 비밀번호 변경
	 * @param paramData
	 * @throws Exception
	 */
	public void updateAdminChangePassword( SysAdmin paramData) throws Exception{	
		sysAdmService.updateAdminChangePassword(paramData);
	}
	
	/**
	 * 운영자정보 비밀번호 초기화 날짜 변경
	 * @param paramData
	 * @throws Exception
	 */
	public void updateAdminPwIntzDt( SysAdmin paramData) throws Exception{	
		sysAdmService.updateAdminPwIntzDt(paramData);
	}
	
	
	
	/**
	 * 나의정보 수정 처리
	 * @param paramData
	 * @throws Exception
	 */
	public void updateMyInfo ( FormSysAdmDTO paramData) throws Exception{
		sysAdmService.updateMyInfo(paramData.getSysAdmin());
	}


	/**
	 * 즐겨찾기 목록를 가지고 온다.
	 */
	public List<SysAdminBukmkResult> selectAdminBookmarkList() throws Exception{
		return sysAdminBukmkService.selectAdminBookmarkList( BOSecurityUtil.getLoginId(), BOSecurityUtil.getAccessSiteId());
	}


	/**
	 * 즐겨찾기 등록.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void insertSysAdminBukmk(SysAdminBukmk paramData) throws Exception {
		sysAdminBukmkService.insertSysAdminBukmk(paramData);
	}

	/**
	 * 즐겨찾기 삭제.
	 *
	 * @param systemPK [설명]
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public void deleteSysAdminBukmk(SysAdminBukmk paramData) throws Exception {
		sysAdminBukmkService.deleteSysAdminBukmk(paramData);
	}


	/**
	 * 운영관리 운영자 목록 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AdmResult> selectListAdm( FormSysAdmDTO paramData) throws Exception {

		return sysAdmService.selectListAdm(paramData);
	}

	/*public Page<AdmResult> selectListAdm( FormSysAdmDTO paramData, PageParam pageParam) throws Exception {

		RepositoryHelper.makePageEntityIndex(paramData, pageParam);

		List<AdmResult> admResultList = sysAdmService.selectListAdm(paramData);

		long totalRow = sysAdmService.selectCountAdmList(paramData);

		return new PageImpl<AdmResult>(admResultList, pageParam.getPageable(), totalRow);

	}*/


	/**
	 * 운영관리 운영자 목록 엑셀 다운로드
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectListAdmExcel(FormSysAdmDTO paramData) throws Exception {

		List<Map<String, String>> admResultList = new ArrayList();

		Iterator<AdmResult> iterator = sysAdmService.selectListAdm(paramData).iterator();

		int excelNo = 1;
		while(iterator.hasNext()) {
			AdmResult admResult = iterator.next();

			LinkedHashMap<String,String> dataMap = new LinkedHashMap<String,String>();

			dataMap.put("excelNo", String.valueOf(excelNo));

			dataMap.put("adminNm", admResult.getSysAdmin().getAdminNm() );
			dataMap.put("adminId", admResult.getMakAdminId() );
			dataMap.put("boSiteIds", admResult.getBoSiteIds() );
			dataMap.put("authorGrpNms", admResult.getSysAuthorGrp().getAuthorGrpNm());
			dataMap.put("comNm", admResult.getComNm() );
			dataMap.put("shopNm", admResult.getShopNm() );
			dataMap.put("email", admResult.getMakEmail() );
			dataMap.put("mobil", admResult.getMobil() );
			dataMap.put("tel", admResult.getTel() );
			dataMap.put("adminTpNm", admResult.getAdminTpNm() );
			dataMap.put("adminStatNm", admResult.getAdminStatNm() );
			dataMap.put("aprvDt", getDateService().parseString( admResult.getSysAdmin().getAprvDt() , "yyyy-MM-dd HH:mm") );
			dataMap.put("lastLoginDt", getDateService().parseString( admResult.getSysAdmin().getLastLoginDt(), "yyyy-MM-dd HH:mm") );

			admResultList.add(dataMap);
			excelNo++;
		}

		return admResultList;

	}

	/**
	 * 운영목록 총 수
	 */
	public long selectCountAdmList ( FormSysAdmDTO paramData) throws Exception {
		return sysAdmService.selectCountAdmList(paramData);
	}

	/**
	 * 권한그룹 목록 조회
	 * @return
	 * @throws Exception
	 */
	public List<SysAuthorGrp>  selectListAuthorGrp() throws Exception {
		return sysAdmService.selectListAuthorGrp();
	}

	/**
	 * 운영자 ID 중복 체크
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public boolean checkAdminId ( String adminId ) throws Exception {
		return sysAdmService.checkAdminId( adminId );
	}

	/**
	 * 운영자 정보 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public AdmResult selectRowAdminInfo( FormSysAdmDTO paramData ) throws Exception {
		return sysAdmService.selectRowAdminInfo(paramData);
	}

	/**
	 * 권한그룹 메뉴목록 조회.
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AuthResult> selectListAuthMenu (FormSysAdmDTO paramData ) throws Exception {

		return sysAdmService.selectListAuthMenu( paramData);

	}

	/**
	 * 권한그룹 FILE 목록 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<AuthResult> selectListAuthFile (  FormSysAdmDTO paramData) throws Exception {

		//String authorPath; //권한경로 ( 운영관리 운영권한 팝업창에서 사용 )

		//String authorActNm ; //권한기능명 ( 운영관리 운영권한 팝업창에서 사용 )

		List<AuthResult> authReslutList = sysAdmService.selectListAuthFile( paramData);

		Iterator<AuthResult> iterator = authReslutList.iterator();
		while( iterator.hasNext()){

			AuthResult authResult = iterator.next();

			authResult.setAuthorPath( authResult.getNaMenuNm() +" &#47; "+authResult.getSysMenu().getMenuNm() );

			String authorResrcNm = authResult.getSysAuthor().getAuthorResrcNm();

			String authorActNm = (" "+getStringService().nvl( authorResrcNm,"")).indexOf("C") > 0?"/등록":"";
			authorActNm += (" "+getStringService().nvl( authorResrcNm,"")).indexOf("C") > 0?"/조회":"";
			authorActNm += (" "+getStringService().nvl( authorResrcNm,"")).indexOf("C") > 0?"/수정":"";
			authorActNm += (" "+getStringService().nvl( authorResrcNm,"")).indexOf("C") > 0?"/삭제":"";
			authorActNm += (" "+getStringService().nvl( authorResrcNm,"")).indexOf("C") > 0?"/엑셀다운로드":"";
			authorActNm += (" "+getStringService().nvl( authorResrcNm,"")).indexOf("C") > 0?"/마스킹해제":"";

			authorActNm = authorActNm.trim();

			authResult.setAuthorActNm(authorActNm);

		}


		return authReslutList;
	}

	/**
	 * 브랜드 선택 목록 조회
	 * @param paramData
	 * * @return
	 * @throws Exception
	 */
	public List<SysBrndExtend> getComBrndList(SysAdmin paramData) throws Exception {
		return sysAdmService.getComBrndList( paramData);
	}
	public List<SysBrndExtend> getUserComBrndList(FormSysAdmDTO paramData) throws Exception {
		return sysAdmService.getUserComBrndList( paramData);
	}

	/**
	 * PO Dashboard 현황 정보
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public DashboardSumResult selectDashboardSumInfo(FormSysAdmDTO paramData ) throws Exception {
		return sysAdmService.selectDashboardSumInfo(paramData);
	}

	/**
	 * PO Main Notice list
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<SysCmmnNotiResult> selectPoMainNoticeList(SystemPK systemPK, SysCmmnNotiDTO paramData ) throws Exception {
		List<SysCmmnNotiResult> lists = sysAdmService.selectPoMainNoticeList( systemPK, paramData);
		List<SysCmmnNotiResult> newLists = new ArrayList<SysCmmnNotiResult>();
		SysCmmnNotiResult commNoti = null;
		int noticeSn = 0;
		String title = "";
		String lineHtml = "";
		String day7InYn = "";
		for(int i=0; i<lists.size(); i++){
			commNoti = (SysCmmnNotiResult)lists.get(i);
			lineHtml = "";
			noticeSn = BOStringUtil.strToInt(commNoti.getSysNoti().getNotiSn()+"");
			title = commNoti.getSysNoti().getNotiSj();
			title = BOStringUtil.getTitleLimit(title, 150); //50자 축약 표현 처리
			lineHtml += "<a href=\"javascript:noticeView('"+noticeSn+"')\">"+title+"</a>";
			day7InYn = commNoti.getDay7InYn();
			if(day7InYn.equals("Y")){ //7일 이내
				lineHtml += "<span class=\"new\">NEW</span>";
			}
			lineHtml += "<span class=\"date\">"+commNoti.getRegDtNm()+"</span>";
			if(i<5){//top 5건 처리
				//log.info( "공지사항 라인 :: "+lineHtml);
				commNoti.setNoticeLineHtml(lineHtml);
				newLists.add(commNoti);
			}
		}
		return newLists;
	}

	/**
	 * #33927 BO 배송매장 전용 DashBoard 생성
	 *	- 배송매장 공지사항
	 *
	 * @return
	 * @throws Exception
	 */
	public List<SysCmmnNotiResult> selectDlvShopMainNoticeList() throws Exception {
		List<SysCmmnNotiResult> lists = sysAdmService.selectDlvShopMainNoticeList();
		List<SysCmmnNotiResult> newLists = new ArrayList<SysCmmnNotiResult>();

		for (SysCmmnNotiResult notiResult : lists ) {
			String lineHtml = "<td><p class='title'><a href=\"javascript:noticeView('"+ notiResult.getSysNoti().getNotiSn() +"')\">"+ BOStringUtil.getTitleLimit(notiResult.getSysNoti().getNotiSj(), 150) +"</a>";

			// 7일 이내
			String day7InYn = notiResult.getDay7InYn();
			if (StringService.equalsIgnoreCase("Y", day7InYn)) {
				lineHtml += "<span class=\"new-icon\">N</span>";
			}
			lineHtml += "</p></td>";

			lineHtml += "<td><p class=\"date\">"+notiResult.getRegDtNm()+"</p></td>";
			//log.info( "공지사항 라인 :: "+lineHtml);
			notiResult.setNoticeLineHtml(lineHtml);
			newLists.add(notiResult);
		}

		return newLists;
	}

	/**
	 * 운영자 담당 몰 조회
	 *
	 * @param paramData
	 * @throws Exception
	 */
	@Override
	public List<SysAdminMallExtend> selectAdminMall(FormSysAdmDTO paramData) throws Exception{
		return sysAdmService.selectAdminMall(paramData);
	}
}
