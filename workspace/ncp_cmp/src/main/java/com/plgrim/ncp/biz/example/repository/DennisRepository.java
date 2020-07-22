package com.plgrim.ncp.biz.example.repository;

import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;

@Repository
@Slf4j

public class DennisRepository extends AbstractRepository{

	public <TestExampleDTO> void mergeExampleGridData(TestExampleDTO testExampleDTO) {
		
		//설정 테스트 (실제개발 코드는 불필요함, 추후 Base XML설정으로 이동.)
		Configuration config = new Configuration();
		config.setLazyLoadingEnabled(true);
		config.setUseGeneratedKeys(false);
		config.setMultipleResultSetsEnabled(true);
		config.setUseColumnLabel(true);
		config.setDefaultExecutorType(ExecutorType.BATCH);
		//설정 테스트
		
		getSession1().update("com.plgrim.ncp.biz.example.mergeDennisGridData", testExampleDTO);
	}
}
