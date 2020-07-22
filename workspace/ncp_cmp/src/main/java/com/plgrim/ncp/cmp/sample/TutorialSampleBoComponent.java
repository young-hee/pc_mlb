package com.plgrim.ncp.cmp.sample;

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.test.TestAdmin;
import com.plgrim.ncp.biz.sample.data.TutorialSampleFormDTO;
import com.plgrim.ncp.biz.sample.result.TutorialSampleResult;

public interface TutorialSampleBoComponent {
	
	
	/**
	 * 테스트관리자 관리 테스트  목록 조회
	 * @param formAdmDTO
	 * @return
	 * @throws Exception
	 */
	public List<TutorialSampleResult> selectTutorialSampleList(TutorialSampleFormDTO tutorialAdmFormDTO) throws Exception;
	

	/**
	 * 테스트 관리자 목록 개수 조회
	 * @param tutorialAdmFormDTO
	 * @return
	 * @throws Exception
	 */
	public long selectTutorialSampleListCount(TutorialSampleFormDTO tutorialAdmFormDTO) throws Exception;
	
	
	/**
	 * 테스트 관리자 아이디 중복체크를 위한 목록 개수 조회
	 * @param tutorialAdmFormDTO
	 * @return
	 * @throws Exception
	 */
	public boolean selectTutorialSampleIdCount(TutorialSampleFormDTO tutorialAdmFormDTO) throws Exception;

	
	/**
	 * 테스트 관리자 등록
	 * @param tutorialAdmFormDTO
	 * @throws Exception
	 */
	public void insertTutorialSampleInfo( TutorialSampleFormDTO tutorialAdmFormDTO) throws Exception;

	
	/**
	 * 테스트 관리자 삭제
	 * @param TestAdmin
	 * @throws Exception
	 */
	public void deleteTutorialSampleInfo ( TestAdmin TestAdmin) throws Exception;
	
	
	/**
	 * 테스트 관리자 수정
	 * @param TestAdmin
	 * @return
	 * @throws Exception
	 */
	public TestAdmin selectTutorialSampleInfo ( TestAdmin TestAdmin) throws Exception;
	
	
	
	/**
	 * 테스트 관리자 기본정보 수정처리
	 * @param testAdmin
	 * @throws Exception
	 */
	public void updateTutorialSampleInfo ( TutorialSampleFormDTO testAdmin) throws Exception;


}
