package com.plgrim.ncp.biz.sample.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.test.TestAdmin;
import com.plgrim.ncp.base.repository.test.TestAdminRepository;
import com.plgrim.ncp.biz.sample.data.TutorialSampleFormDTO;
import com.plgrim.ncp.biz.sample.repository.TutorialSampleRepository;
import com.plgrim.ncp.biz.sample.result.TutorialSampleResult;

@Service
public class TutorialSampleService extends AbstractService{

	
	@Autowired
	TutorialSampleRepository tutorialSampleRepository;
	@Autowired
	TestAdminRepository testAdminRepository;
	
	
	
	
	
	

	/**
	 * 테스트 관리자 목록 조회
	 * @param tutorialAdmFormDTO
	 * @return
	 * @throws Exception
	 */
	public List<TutorialSampleResult> selectTutorialSampleList(TutorialSampleFormDTO tutorialAdmFormDTO) throws Exception{
				
		return tutorialSampleRepository.selectTutorialSampleList(tutorialAdmFormDTO);
		
	}
	

	/**
	 * 테스트 관리자 목록 개수 조회
	 * @param tutorialAdmFormDTO
	 * @return
	 * @throws Exception
	 */
	public long selectTutorialSampleListCount(TutorialSampleFormDTO tutorialAdmFormDTO) throws Exception{
		
		
		return tutorialSampleRepository.selectTutorialSampleListCount(tutorialAdmFormDTO);
	}
	
	
	

	/**
	 * 테스트 관리자 아이디 중복체크를 위한 목록 개수 조회
	 * @param tutorialAdmFormDTO
	 * @return
	 * @throws Exception
	 */
	public boolean selectTutorialSampleIdCount(TutorialSampleFormDTO tutorialAdmFormDTO) throws Exception{
		
		boolean result = tutorialSampleRepository.selectTutorialSampleIdCount(tutorialAdmFormDTO)>0?true:false;
		return result;
		
	}
	
	

	
	/**
	 * 테스트 관리자 등록
	 * @param tutorialAdmFormDTO
	 * @throws Exception
	 */
	public void insertTutorialSampleInfo ( TutorialSampleFormDTO tutorialAdmFormDTO) throws Exception {
		if("APRV_COMPT".equals( tutorialAdmFormDTO.getTestAdmin().getAdminStatCd() )){
			tutorialAdmFormDTO.getTestAdmin().setAprvDt(Calendar.getInstance().getTime()); //승인일시
			
		}
		
		//비밀번호 암호화 SHA256
		tutorialAdmFormDTO.getTestAdmin().setPw( getIdGenService().generateSHA256(tutorialAdmFormDTO.getTestAdmin().getPw())); 		
		tutorialSampleRepository.insertTutorialSampleInfo(tutorialAdmFormDTO.getTestAdmin());
		
		
	}
	

	/**
	 * 테스트 관리자 삭제
	 * @param TestAdmin
	 * @throws Exception
	 */
	public void deleteTutorialSampleInfo ( TestAdmin TestAdmin) throws Exception {
		
		tutorialSampleRepository.deleteTutorialSampleInfo(TestAdmin.getAdminId());
	}
	
	
	/**
	 * 테스트 관리자 수정 팝업을위한 관리자 데이터 가져오기
	 * @param TestAdmin
	 * @return
	 * @throws Exception
	 */
	public TestAdmin selectTutorialSampleInfo ( TestAdmin TestAdmin) throws Exception {
		
		
		
		return testAdminRepository.selectTestAdmin(TestAdmin);
		
	}
	
	
	
	/**
	 * 테스트 관리자 수정
	 * @param testAdmin
	 * @throws Exception
	 */
	public void updateTutorialSampleInfo ( TutorialSampleFormDTO testAdmin) throws Exception {

		 
		


		if("APRV_COMPT".equals( testAdmin.getTestAdmin().getAdminStatCd() )){
			 SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			 Date currentTime = new Date ( );
			 Date dTime = formatter.parse(formatter.format(currentTime));
			testAdmin.getTestAdmin().setAprvDt(dTime); //승인일시
		}
		
		
				
		/*테스트 관리자 기본 정보 수정*/
		tutorialSampleRepository.updateTutorialSampleInfo(testAdmin.getTestAdmin());
		

		
	}

	



	
	
}
