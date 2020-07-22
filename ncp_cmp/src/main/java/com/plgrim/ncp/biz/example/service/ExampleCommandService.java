package com.plgrim.ncp.biz.example.service;

import java.util.Iterator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.biz.example.repository.ExampleRepository;

@Service
@Slf4j
public class ExampleCommandService<TestExampleDTO> extends AbstractService {

	@Autowired
	ExampleRepository  exampleRepository;
	
	/**
	 * 샘플 그리드 저장/수정
	 * @param <TestExampleDTO>
	 * @param testExampleList
	 * @return
	 */
	public <TestExampleDTO> void mergeExampleGridData(List<TestExampleDTO> testExampleList) {
	
		Iterator<TestExampleDTO> iterator = testExampleList.iterator();
		while(iterator.hasNext()) {
			exampleRepository.mergeExampleGridData(iterator.next());
		}
    }
	
	/**
	 * 샘플 그리드 데이타 삭제 
	 * @param <TestExampleDTO>
	 * @param testExampleList
	 * @return
	 */
	public <TestExampleDTO> void deleteExampleGridData(List<TestExampleDTO> testExampleList) {
		
		Iterator<TestExampleDTO> iterator = testExampleList.iterator();
		while(iterator.hasNext()) {
			exampleRepository.deleteExampleGridData(iterator.next());
		}
    }
	
	
	public <TestExampleDTO> void createExampleGridData(List<TestExampleDTO> createList) {
		
		Iterator<TestExampleDTO> iterator = createList.iterator();
		while(iterator.hasNext()) {
			exampleRepository.createExampleGridData(iterator.next());
		}
    }
	
	public <TestExampleDTO> void updateExampleGridData(List<TestExampleDTO> updateList) {
		
		Iterator<TestExampleDTO> iterator = updateList.iterator();
		while(iterator.hasNext()) {
			exampleRepository.updateExampleGridData(iterator.next());
		}
    }
	
	

}
