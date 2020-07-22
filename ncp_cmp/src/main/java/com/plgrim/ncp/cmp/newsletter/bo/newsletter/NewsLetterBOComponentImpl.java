package com.plgrim.ncp.cmp.newsletter.bo.newsletter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefJobCd;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefJobDetail;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefSectCd;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoCommandService;
import com.plgrim.ncp.biz.newsletter.data.NewsLetterDTO;
import com.plgrim.ncp.biz.newsletter.data.NewsLetterResultDTO;
import com.plgrim.ncp.biz.newsletter.result.NewsLetterResult;
import com.plgrim.ncp.biz.newsletter.service.NewsLetterService;
import com.plgrim.ncp.cmp.newsletter.bo.NewsLetterBOComponent;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.systems.SystemContext;

import lombok.extern.slf4j.Slf4j;

@Transactional(value = "transactionManager")
@Component
@Slf4j
public class NewsLetterBOComponentImpl extends AbstractComponent implements NewsLetterBOComponent {
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	@Autowired
	private NewsLetterService newsLetterService;
	
	@Autowired
	private SystemContext systemContext;
	
	@Autowired
	private MemberPersonalInfoCommandService memberPersonalInfoCommandService;
	
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
	

	/**
	 * newsletter 목록 조회.
	 */
	@Override
	public NewsLetterResultDTO getNewsLetterList(SystemPK systemPK, NewsLetterDTO dto, String loginId)  {
		// step 1. 페이지 인덱스 및 변수 설정
		NewsLetterResultDTO resultDTO = new NewsLetterResultDTO();
		dto.setLang(systemPK.getLang());

		// step 2. 목록 건수 조회
		long listCount = newsLetterService.selectNewsLetterListCount(dto);
		resultDTO.setListCount(listCount);

		// step 3. 목록 조회
		List<NewsLetterResult> lists = new ArrayList<NewsLetterResult>();
		if(listCount > 0){
			lists = newsLetterService.selectNewsLetterList(dto);
		}
		resultDTO.setLists(lists);
		
		// step 4. 개인 정보 이용 이력 등록
		if(listCount > 0){
			String[][] usefCdInfo = { // 구분, 업무, 업무상세
					{UsefSectCd.PSNL_INFO_STTUS.toString(), UsefJobCd.INQIRE.toString(), UsefJobDetail.LIST.toString()}
			};
			String[] target = {};
			memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
					, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
					, target					// 이용대상 : 회원
					, null						// 유입 일련번호
					, dto.getAccessMenuSn()			// 메뉴 일련번호
					, loginId					// 로그인 ID
			);
		}

		return resultDTO;
	}

	/**
	 * newsletter 엑셀 다운로드 목록 조회.
	 */
	@Override
	public List<Map<String, Object>> getNewsLetterListExcel(SystemPK systemPK, NewsLetterDTO dto, String loginId)  {
		
		// step 1. 회원 엑셀 조회
		dto.setLang(systemPK.getLang());
		List<Map<String, Object>> lists = newsLetterService.selectNewsLetterListExcel(dto);

		// step 2. 개인 정보 이용 이력 등록
		if(lists != null && lists.size() > 0){
			String[][] usefCdInfo = { // 구분, 업무, 업무상세
					{UsefSectCd.PSNL_INFO_STTUS.toString(), UsefJobCd.SAVE.toString(), UsefJobDetail.EXCEL_DOWN.toString()}
			};
			String[] target = null;
			memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
					, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
					, target					// 이용대상 : 회원
					, null						// 유입 일련번호
					, dto.getAccessMenuSn()			// 메뉴 일련번호
					, loginId					// 로그인 ID
			);
		}
		return lists;
		
	}
	
	/**
	 * newsletter delete.
	 */
	@Override
	public void deleteNewsLetter(NewsLetterDTO dto)  {		
		newsLetterService.deleteNewsLetter(dto);
	}




}
