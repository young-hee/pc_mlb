package com.plgrim.ncp.biz.sample.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.test.TestAdmin;
import com.plgrim.ncp.biz.sample.result.TutorialSampleResult;

import lombok.Data;

@Data
public class TutorialSampleFormDTO extends AbstractDTO {

	String searchField;
	String searchText;
	
	
	String pageNo; //페이지 넘버
	
	
	TestAdmin testAdmin;
}
