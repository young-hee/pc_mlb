package com.plgrim.ncp.biz.newsletter.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.biz.newsletter.data.NewsLetterDTO;
import com.plgrim.ncp.biz.newsletter.result.NewsLetterResult;

/**
 * 뉴스레터 repository
 */
@Repository
public class NewsLetterRepository extends AbstractRepository { 	

	/**
	 * @param dto
	 * @return
	 */
	public long selectNewsLetterListCount(NewsLetterDTO dto) {
		return getSession1().selectOne("com.plgrim.ncp.biz.newsletter.selectNewsLetterListCount", dto);
	}
	
	/**
	 * @param dto
	 * @return
	 */
	public List<NewsLetterResult> selectNewsLetterList(NewsLetterDTO dto) {
		return  getSession1().selectList("com.plgrim.ncp.biz.newsletter.selectNewsLetterList", dto);		
	}
	
	/**
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> selectNewsLetterListExcel(NewsLetterDTO dto) {
		return  getSession1().selectList("com.plgrim.ncp.biz.newsletter.selectNewsLetterListExcel", dto);		
	}
	
	/**
	 * @param dto
	 * @return
	 */
	public void deleteNewsLetter(NewsLetterDTO dto) {
		getSession1().delete("com.plgrim.ncp.biz.newsletter.deleteNewsLetter", dto);		
	}
	
	/**
	 * @param dto
	 * @return
	 */
	public void insertNewsLetter(NewsLetterDTO dto) {
		getSession1().insert("com.plgrim.ncp.biz.newsletter.insertNewsLetter", dto);		
	}

}
