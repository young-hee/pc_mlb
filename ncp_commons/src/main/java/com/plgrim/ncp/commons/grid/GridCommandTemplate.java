package com.plgrim.ncp.commons.grid;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.Predicate;
import org.springframework.ui.Model;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.framework.commons.CollectionService;

@Slf4j
public class GridCommandTemplate {

	public <T> void execute(List<T> dataList, GridCommandAwareCallback<T> gridCommandCallback) throws Exception {
		
		//predicate 구현
	  	Predicate createPredicate = new Predicate() {
	  		@Override
	        public boolean evaluate(Object object) {
	  			AbstractDTO element = (AbstractDTO) object;
	  			String value = (String) element.getGridCudTag();
	  				 
	  			if (value.equals("C")) {
	  				return true;
	  			}
	  				return false;
	            }
	  	};
	  	
	  	List<T> createList = CollectionService.selectList(dataList, createPredicate);
	  	
	    //predicate 구현
	  	Predicate updatePredicate = new Predicate() {
	  		@Override
            public boolean evaluate(Object object) {
  				AbstractDTO element = (AbstractDTO) object;
  				String value = (String) element.getGridCudTag();
  				
  				 if (value.equals("U")) {
  					 return true;
  				 }
  				 	return false;
            }
	  	};
		
	  	List<T> updateList = CollectionService.selectList(dataList, updatePredicate);
	  	
	  	//predicate 구현
	  	Predicate deletePredicate = new Predicate() {
	  		@Override
            public boolean evaluate(Object object) {
  				AbstractDTO element = (AbstractDTO) object;
  				String value = (String) element.getGridCudTag();
  				
  				 if (value.equals("D")) {
  					 return true;
  				 }
  				 	return false;
            }
	  	};
		
	  	List<T> deleteList = CollectionService.selectList(dataList, deletePredicate);
	  	
	  	gridCommandCallback.commandHandler(createList, updateList, deleteList, dataList);
    }

	public <O> void select(Model model, List<O> selectGridDataList, int selectTotalCount) {
	    
    }
	
//	public <T extends AbstractResult> void select(Model model, PageParam pageParam, List<T> selectGridDataList, long selectTotalCount) {
//		GridBuilder gridBuilder = new GridBuilder(); 
//		gridBuilder.buildGridData(model, selectGridDataList);
//		
//		Page<T> pagedList = new PageImpl<T>(selectGridDataList, pageParam.getPageable(), selectTotalCount);
//		
//    }
}
