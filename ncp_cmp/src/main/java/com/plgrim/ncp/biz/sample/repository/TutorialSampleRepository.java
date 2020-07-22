package com.plgrim.ncp.biz.sample.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.test.TestAdmin;
import com.plgrim.ncp.biz.sample.data.TutorialSampleFormDTO;
import com.plgrim.ncp.biz.sample.result.TutorialSampleResult;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Repository
public class TutorialSampleRepository extends AbstractRepository {

	
	
	
	

/**
 * 테스트 관리자 목록 조회
 * @param tutorialAdmFormDTO
 * @return
 * @throws Exception
 */
public List<TutorialSampleResult> selectTutorialSampleList(TutorialSampleFormDTO tutorialAdmFormDTO) throws Exception{
		
	
	
		return getSession1().selectList("com.plgrim.ncp.tutorial.sample.selectTutorialSampleList",tutorialAdmFormDTO);
	}
	



/**
 * 테스트 관리자 목록 개수 조회
 * @param tutorialAdmFormDTO
 * @return
 * @throws Exception
 */
public long selectTutorialSampleListCount(TutorialSampleFormDTO tutorialAdmFormDTO) throws Exception{
	
	
	return getSession1().selectOne("com.plgrim.ncp.tutorial.sample.selectCountTutorialSampleList",tutorialAdmFormDTO);
}




/**
 * 테스트 관리자 아이디 중복체크를 위한 목록 개수 조회
 * @param tutorialAdmFormDTO
 * @return
 * @throws Exception
 */
public long selectTutorialSampleIdCount(TutorialSampleFormDTO tutorialAdmFormDTO) throws Exception{

	String adminId = tutorialAdmFormDTO.getTestAdmin().getAdminId();
	Map<String,String> map = new HashMap<String, String>();
	
	map.put("adminId", adminId);
	log.info("관리자 id check 를위한 목록개수"+tutorialAdmFormDTO);
	log.info("관리자 id check 파라미터"+adminId);

	return getSession1().selectOne("com.plgrim.ncp.tutorial.sample.selectCountTutorialSampleId", adminId);
		
}




/**
 * 테스트 관리자 등록
 * @param testAdmin
 * @throws Exception
 */
public void insertTutorialSampleInfo ( TestAdmin testAdmin) throws Exception {

	log.info("등록 testAdmin info test(repository)"+testAdmin.toString());
	getSession1().insert( "com.plgrim.ncp.tutorial.sample.insertTutorialSampleInfo", testAdmin);
}




/**
 * 테스트 관리자 삭제
 * @param testAdmin
 * @throws Exception
 */
public void deleteTutorialSampleInfo ( String testAdmin) throws Exception {
	
	log.info("삭제 testAdmin info test(repository)"+testAdmin.toString());
	getSession1().delete( "com.plgrim.ncp.tutorial.sample.deleteTutorialSampleInfo", testAdmin);
}




/**
 * 테스트 관리자 수정
 * @param testAdmin
 * @throws Exception
 */
public void updateTutorialSampleInfo ( TestAdmin testAdmin) throws Exception {
	
	log.info("수정 testAdmin info test(repository)"+testAdmin.toString());
	getSession1().insert( "com.plgrim.ncp.tutorial.sample.updateTutorialSampleInfo", testAdmin);
}




}
