package com.plgrim.ncp.biz.sample.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.test.TestAdmin;

import lombok.Data;

@Data
public class TutorialSampleResult extends AbstractResult{
	
		
	/**
	 * 베이스 엔티티
	 */
		TestAdmin testAdmin;
		
		
}
