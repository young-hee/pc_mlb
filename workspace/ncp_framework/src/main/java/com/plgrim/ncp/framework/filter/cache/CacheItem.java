package com.plgrim.ncp.framework.filter.cache;

import lombok.Data;

@Data
public class CacheItem {
	byte[] data;
	String contentType;

	public static CacheItem from(byte[] data, CacheResponseWrapper cacheResponse) {
		CacheItem item = new CacheItem();
		item.setData(data);
		item.setContentType(cacheResponse.getContentType());
		return item;
	}

	public boolean isEmpty() {
		return data == null || data.length == 0;
	}

	public boolean isNotEmpty() {
		return !isEmpty();
	}

	public int getContentLength() {
		return isEmpty() ? 0 : data.length;
	}

}
