package com.plgrim.ncp.biz.sample.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.test.TestAdmin;
import com.plgrim.ncp.base.repository.test.TestAdminRepository;
import com.plgrim.ncp.biz.sample.data.TutorialAdmFormDTO;
import com.plgrim.ncp.biz.sample.repository.TutorialAdmSelectRepository;
import com.plgrim.ncp.biz.sample.result.TutorialAdmResult;

@Service
public class TutorialAdmSelectService extends AbstractService{

	
	@Autowired
	TutorialAdmSelectRepository tutorialAdmSelectRepository;
	@Autowired
	TestAdminRepository testAdminRepository;
	
	
	
	
	
	

	/**
	 * 테스트 관리자 목록 조회
	 * @param tutorialAdmFormDTO
	 * @return
	 * @throws Exception
	 */
	public List<TutorialAdmResult> selectTutorialAdminList(TutorialAdmFormDTO tutorialAdmFormDTO) throws Exception{
				
		return tutorialAdmSelectRepository.selectTutorialAdminList(tutorialAdmFormDTO);
		
	}
	

	/**
	 * 테스트 관리자 목록 개수 조회
	 * @param tutorialAdmFormDTO
	 * @return
	 * @throws Exception
	 */
	public long selectCountTutorialAdminList(TutorialAdmFormDTO tutorialAdmFormDTO) throws Exception{
		
		
		return tutorialAdmSelectRepository.selectCountTutorialAdminList(tutorialAdmFormDTO);
	}
	
	
	

	/**
	 * 테스트 관리자 아이디 중복체크를 위한 목록 개수 조회
	 * @param tutorialAdmFormDTO
	 * @return
	 * @throws Exception
	 */
	public boolean selectCountTutorialAdminId(TutorialAdmFormDTO tutorialAdmFormDTO) throws Exception{
		
		boolean result = tutorialAdmSelectRepository.selectCountTutorialAdminId(tutorialAdmFormDTO)>0?true:false;
		return result;
		
	}
	
	

	
	/**
	 * 테스트 관리자 등록
	 * @param tutorialAdmFormDTO
	 * @throws Exception
	 */
	public void insertTutorialAdminInfo ( TutorialAdmFormDTO tutorialAdmFormDTO) throws Exception {
		if("APRV_COMPT".equals( tutorialAdmFormDTO.getTestAdmin().getAdminStatCd() )){
			tutorialAdmFormDTO.getTestAdmin().setAprvDt(Calendar.getInstance().getTime()); //승인일시
		}
		
		//비밀번호 암호화 SHA256
		tutorialAdmFormDTO.getTestAdmin().setPw( getIdGenService().generateSHA256(tutorialAdmFormDTO.getTestAdmin().getPw())); 		
		tutorialAdmSelectRepository.insertTutorialAdminInfo(tutorialAdmFormDTO.getTestAdmin());
		
		
	}
	

	/**
	 * 테스트 관리자 삭제
	 * @param TestAdmin
	 * @throws Exception
	 */
	public void deleteTutorialAdminInfo ( TestAdmin TestAdmin) throws Exception {
		
		tutorialAdmSelectRepository.deleteTutorialAdminInfo(TestAdmin.getAdminId());
	}
	
	
	/**
	 * 테스트 관리자 수정 팝업을위한 관리자 데이터 가져오기
	 * @param TestAdmin
	 * @return
	 * @throws Exception
	 */
	public TestAdmin editTutorialAdminInfo ( TestAdmin TestAdmin) throws Exception {
		
		
		
		return testAdminRepository.selectTestAdmin(TestAdmin);
		
	}
	
	
	
	/**
	 * 테스트 관리자 수정
	 * @param testAdmin
	 * @throws Exception
	 */
	public void updateTutorialAdminInfo ( TutorialAdmFormDTO testAdmin) throws Exception {
		
		
		
		testAdmin.getTestAdmin().setUdtDt(Calendar.getInstance().getTime());//승인일시
		
		/*테스트 관리자 기본 정보 수정*/
		tutorialAdmSelectRepository.updateTutorialAdminInfo(testAdmin.getTestAdmin());
		

		
	}

	



	
	
}
