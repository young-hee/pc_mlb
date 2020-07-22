package com.plgrim.ncp.commons.grid;

import java.util.List;

public interface GridCommandAwareCallback<T> {

	void commandHandler(List<T> createList, List<T> updateList, List<T> deleteList, List<T> dataList) throws Exception;
	
}
	

