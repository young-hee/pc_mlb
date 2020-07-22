package com.plgrim.ncp.biz.example.service;

import java.util.Iterator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.biz.example.repository.DennisRepository;

@Service
@Slf4j

public class DennisCommandService<TestExampleDTO> extends AbstractService {

		@Autowired
		DennisRepository dennisRepository;
	
		/**
		 * 샘플 그리드 저장/수정
		 * @param <TestExampleDTO>
		 * @param testExampleList
		 * @return
		 */
		public <TestExampleDTO> void mergeExampleGridData(List<TestExampleDTO> testDennisList) {
		Iterator<TestExampleDTO> iterator = testDennisList.iterator();
		while(iterator.hasNext()) {
			dennisRepository.mergeExampleGridData(iterator.next());
			
		}
		
	}
}
