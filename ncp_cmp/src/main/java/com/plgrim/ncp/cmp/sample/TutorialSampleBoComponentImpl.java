package com.plgrim.ncp.cmp.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.test.TestAdmin;
import com.plgrim.ncp.biz.sample.data.TutorialSampleFormDTO;
import com.plgrim.ncp.biz.sample.result.TutorialSampleResult;
import com.plgrim.ncp.biz.sample.service.TutorialSampleService;

@Component
public class TutorialSampleBoComponentImpl extends AbstractComponent implements TutorialSampleBoComponent {

	@Autowired
	TutorialSampleService tutorialSampleService;

	
	
	@Override
	public List<TutorialSampleResult> selectTutorialSampleList(TutorialSampleFormDTO tutorialAdmFormDTO) throws Exception {

		return tutorialSampleService.selectTutorialSampleList(tutorialAdmFormDTO);

	}
	
	
	@Override
	public long selectTutorialSampleListCount(TutorialSampleFormDTO tutorialAdmFormDTO) throws Exception{
		
		return tutorialSampleService.selectTutorialSampleListCount(tutorialAdmFormDTO);
	}
	
	
	
	@Override
	public boolean selectTutorialSampleIdCount(TutorialSampleFormDTO tutorialAdmFormDTO) throws Exception{
		
		return tutorialSampleService.selectTutorialSampleIdCount(tutorialAdmFormDTO);
	
	}
	
	@Override
	public void insertTutorialSampleInfo ( TutorialSampleFormDTO tutorialAdmFormDTO) throws Exception {
		
		tutorialSampleService.insertTutorialSampleInfo(tutorialAdmFormDTO);
		
	}
	
	@Override
	public void deleteTutorialSampleInfo ( TestAdmin TestAdmin) throws Exception {
		
		tutorialSampleService.deleteTutorialSampleInfo(TestAdmin);
	}

	@Override
	public TestAdmin selectTutorialSampleInfo ( TestAdmin TestAdmin) throws Exception {
		
		return tutorialSampleService.selectTutorialSampleInfo(TestAdmin);
	}
	
	@Override
	public void updateTutorialSampleInfo ( TutorialSampleFormDTO testAdmin) throws Exception {
		
		tutorialSampleService.updateTutorialSampleInfo(testAdmin);
	}


	
	
}


