/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 3. 26       
 */
package com.plgrim.ncp.framework.mvc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.GridParam;
import com.plgrim.ncp.framework.data.GridResult;
import com.plgrim.ncp.framework.data.GridRow;
import com.plgrim.ncp.framework.page.PageService;
import com.plgrim.ncp.framework.utils.JsonUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Grid 전용 View
 * 
 * <p>
 * 
 *
 * @author tktaeki.kim
 * @since 2015. 3. 26
 */
@Slf4j
public class GridView extends MappingJackson2JsonView {

	static final String GRID_COMP_PREFIX = "$";
	
	static final String GRID_HEADER_TOKEN = ",";

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */

	@Override
	protected Object filterModel(Map<String, Object> model) {

		GridResult gResult = new GridResult();
		
		try{
			
			for (String key : model.keySet()) {
				
				Object modelObject = model.get(key);
				
				if (modelObject instanceof GridParam) {
					gResult = printGridJson((GridParam)modelObject);
				}
			}
			
		} catch(Exception e) {
			log.error(e.getLocalizedMessage(), e);
		} 
		
		return gResult;
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 실제 grid json을 생성 한다.
	 * 
	 * <p/>
	 * 
	 *
	 * @param gridParam [설명]
	 * @return Grid result [설명]
	 * @throws Exception the exception
	 * @since 2015. 3. 26
	 */
	private GridResult printGridJson (GridParam gridParam) throws Exception {
		
		GridResult gResult = new GridResult();
		
		//정보가 null일 경우
		if (gridParam == null || StringUtils.isEmpty(gridParam.getHeaders())) {
			log.warn("GridParam is null or grid header is null");
			return gResult;
		}
		
		String [] params = StringUtils.split(gridParam.getHeaders(), GRID_HEADER_TOKEN);
		List<String> paramList = Arrays.asList(params);
		
		Page<Map<String, Object>> results = gridParam.getResults();
		
		Model model = new ExtendedModelMap();
		PageService.makePageResult(results, model);
		Map<String, Object> map = model.asMap();
		gResult.setPage(map);

		// db 쿼리 result
		List<Map<String, Object>> queryList = results.getContent();
		// 각 Row에 담기는 데이터
		List<GridRow> rows = Lists.newArrayList();

		// 전체 로우의 key값을 카멜 표기법으로 변환 한다.
		List<Map<String, String>> convertQueryList = Lists.newArrayList();
		
		//db에 조회한 데이터를 변환 한다.
		for (Map<String, Object> qData : queryList) {

			Map<String, String> convetMap = Maps.newHashMap(); 
			
			for (String key : qData.keySet()) {

				String newKey = StringService.convertCamelCase(key);
				convetMap.put(newKey.toUpperCase(), qData.get(key).toString());
			}
			convertQueryList.add(convetMap);
		}

		int index = 1;
		for (Map<String, String> qData : convertQueryList) {

			// grid row number
			GridRow gRow = new GridRow();
			gRow.setId(String.valueOf(index));

			// UI 그리드 화면 과 1:1 맵핑 작업을 한다.
			List<String> datas = Lists.newArrayList();

			// 넘어온 파라미터를 기준으로 datas에 넣는다.
			for (String param : paramList) {

				// dhtmlx 컴포넌트 전용 헤더를 처리 한다.
				if (StringUtils.contains(param, GRID_COMP_PREFIX)) {
					datas.add(param);
				}
				else { // 일반 데이터일 경우
					
					String dbData = qData.get(param.toUpperCase());
					
					//값이 없을 경우 null값을 설정 한다.
					if (StringUtils.isEmpty(dbData)) {
						datas.add(null);
					} else { //db 값 저장
						datas.add(dbData);
					}
				}

			}

			gRow.setData(datas);
			rows.add(gRow);
			index++;

		}

		gResult.setRows(rows);
		
		return gResult;
	}

	
	
}
