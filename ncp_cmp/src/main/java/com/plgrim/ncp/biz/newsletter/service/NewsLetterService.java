package com.plgrim.ncp.biz.newsletter.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.biz.newsletter.data.NewsLetterDTO;
import com.plgrim.ncp.biz.newsletter.repository.NewsLetterRepository;
import com.plgrim.ncp.biz.newsletter.result.NewsLetterResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 뉴스레터 service..
 */
@Service
@Slf4j
public class NewsLetterService extends AbstractService {
	
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;

	@Autowired
	private NewsLetterRepository newsLetterRepository;		

	public long selectNewsLetterListCount(NewsLetterDTO dto) {
		return newsLetterRepository.selectNewsLetterListCount(dto);
	}
	
	public List<NewsLetterResult> selectNewsLetterList(NewsLetterDTO dto) {
		RepositoryHelper.makePageEntityIndex(dto, dto.getPageParam());	// 페이지 설정
		return newsLetterRepository.selectNewsLetterList(dto);
	}
	
	public List<Map<String, Object>> selectNewsLetterListExcel(NewsLetterDTO dto) {
		return newsLetterRepository.selectNewsLetterListExcel(dto);
	}
	
	public void deleteNewsLetter(NewsLetterDTO dto) {
		newsLetterRepository.deleteNewsLetter(dto);
	}
	
	public void insertNewsLetter(NewsLetterDTO dto) {
		newsLetterRepository.insertNewsLetter(dto);
	}
	
}
