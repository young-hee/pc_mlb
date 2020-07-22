package com.plgrim.ncp.commons.grid;

import java.util.List;

import org.apache.commons.collections4.Predicate;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.framework.commons.CollectionService;

@Component
public class GridCommand {
	
	private Predicate getPredicate(final String type) {
		
		//predicate 구현
	  	Predicate dataPredicate = new Predicate() {
	  		@Override
            public boolean evaluate(Object object) {
  				AbstractDTO element = (AbstractDTO) object;
  				String value = (String) element.getGridCudTag();
  				
  				 if (value.equals(type)) {
  					 return true;
  				 }
  				 	return false;
            }
	  	};
		
		return dataPredicate;
	}
	
	public <T> List getDeleteList(List<T> dataList) throws Exception {
		
		return CollectionService.selectList(dataList, getPredicate("D"));
	}
	
	public <T> List getCreateList(List<T> dataList) throws Exception {
		
		return CollectionService.selectList(dataList, getPredicate("C"));
	}
	
	public <T> List getUpdateList(List<T> dataList) throws Exception {
		
		return CollectionService.selectList(dataList, getPredicate("U"));
	}

}
