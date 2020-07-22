package com.plgrim.ncp.commons.builder;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.data.GridResult;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.framework.page.PageService;
import com.google.common.primitives.UnsignedInts;

/**
 * @author Louis
 *
 */
/**
 * @author Louis
 *
 */
@Component
public class GridBuilder {

	private static final String GRID_ATTRIBUTE_NAME = "rows";

	/**
	 * 그리드 데이타 저장 
	 *   그리드 컴포넌트가 읽을 수 있는 변수명으로 저장한다.
	 * @param model
	 * @param Collection result
	 * @return
	 */
	public <T extends AbstractResult>  Model buildGridData(Model model, Collection<T> result) {

	    return model.addAttribute(GRID_ATTRIBUTE_NAME, result); 
    }
	
	/**
	 * 그리드 데이타 저장 
	 *   그리드 컴포넌트가 읽을 수 있는 변수명으로 저장한다.
	 * @param model
	 * @param Collection result
	 * @return
	 */
	public <T extends AbstractResult>  Model buildGridData(Model model, List<T> result) {

	    return model.addAttribute(GRID_ATTRIBUTE_NAME, result); 
    }
	
	/**
	 * 그리드 데이타 저장 
	 *   그리드 컴포넌트가 읽을 수 있는 변수명으로 저장한다.
	 * @param model
	 * @param Collection result
	 * @return
	 */
	public <T extends AbstractResult>  Model buildGridData(Model model, Collection<T> result, UnsignedInts totalCount) {

	    return model.addAttribute(GRID_ATTRIBUTE_NAME, result); 
    }

	public GridResult buildGridMessage(Model model, String successMessage, String exception) {
		
		GridResult gridResult = new GridResult();
		
	    return gridResult;
    }
	
	
	/**
	 * 그리드 CUD 결과 빌더
	 * @param model
	 * @param successMessage
	 * @param exception
	 * @return
	 */
	public GridResult buildReadable(Model model, String successMessage, String exception) {
		
		return null;
	}
	
	/**
	 * 그리드 Read 오브젝트 빌더
	 * @param model
	 * @param result
	 * @return
	 */
	public <T extends AbstractResult> GridResult build(Model model, T result) {
		
		return null;
	}
	
	/**
	 * 그리드 Read 컬렉션 빌더
	 * @param model
	 * @param result
	 * @return
	 */
	public <T extends AbstractResult> GridResult build(Model model, Collection<T> result) {
		
		GridResult gridResult = new GridResult();
		
		gridResult.setRows(result);
		
		return gridResult;
	}
	
	/**
	 * 그리드 Read 컬렉션 빌더
	 * @param model
	 * @param result
	 * @return
	 */
	public <T extends AbstractResult> GridResult build(Model model, Collection<T> result, int totalCount) {
		
		GridResult gridResult = new GridResult();
		
		gridResult.setRows(result);
		
		return gridResult;
	}

	public <T extends AbstractResult> Model build(Model model, PageParam pageParam, List<T> selectedGridDataList, long selectedTotalCount) throws Exception {
	    
		Page<T> pagedList = new PageImpl<T>(selectedGridDataList, pageParam.getPageable(), selectedTotalCount);
		
		PageService.makePageResult(pagedList, model);
		
		return model.addAttribute(GRID_ATTRIBUTE_NAME, selectedGridDataList);
    }
	
	/**
	 * 그리드 직접 바인딩을 위한 Json 마셜링
	 * @param model
	 * @param returnList
	 * @return
	 * @throws Exception
	 */
	public <T extends AbstractResult> Model buildGridJson(Model model, List<T> returnList) throws Exception {
		
		return buildGridJson(model, returnList, GRID_ATTRIBUTE_NAME);
    }

	/**
	 * 그리드 직접 바인딩을 위한 Json 마셜링
	 * @param model
	 * @param returnList 데이터 리스트 
	 * @param keyName 저장될 변수명
	 * @return
	 * @throws Exception
	 */
	public <T extends AbstractResult> Model buildGridJson(Model model, List<T> returnList, String keyName) throws Exception {
		
		return model.addAttribute(keyName, JsonService.marshalling(returnList));
    }
	
	
	/**
	 * 그리드 직접 바인딩으로 위한 Json 마셜링(페이징 처리 데이터 포함) 
	 * @param model
	 * @param returnList
	 * @param selectedTotalCount
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public <T extends AbstractResult> Model buildGridPagedJson(Model model, List<T> returnList, long selectedTotalCount, PageParam pageParam ) throws Exception {
		
		return buildGridPagedJson(model, returnList, GRID_ATTRIBUTE_NAME, selectedTotalCount, pageParam );
    }
	
	 
	/**
	 * 그리드 직접 바인딩으로 위한 Json 마셜링(페이징 처리 데이터 포함) 
	 * @param model
	 * @param returnList
	 * @param keyName
	 * @param selectedTotalCount
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public <T extends AbstractResult> Model buildGridPagedJson(Model model, List<T> returnList, String keyName, long selectedTotalCount, PageParam pageParam ) throws Exception {
		
		Page<T> pagedList = new PageImpl<T>(returnList, pageParam.getPageable(), selectedTotalCount);
		PageService.makePageResult(pagedList, model);
		
		return model.addAttribute(keyName, JsonService.marshalling(returnList));
    }

}
