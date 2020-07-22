package com.plgrim.ncp.framework.asyncJob.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.batch.core.JobParameters;

import com.plgrim.ncp.framework.commons.JsonService;
import com.google.common.collect.ImmutableMap;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParameterMap extends HashMap<String, Object> {
	private static final long serialVersionUID = 6623458854893688140L;

	public ParameterMap(Map<String, Object> data) {
		super(data);
	}

	public String getString(String key) {
		return (String) get(key);
	}

	public String getString(String key, String defaultValue) {
		String value = getString(key);
		return StringUtils.isEmpty(value) ? defaultValue : value;
	}

	public boolean getBool(String key, boolean defaultValue) {
		return get(key) == null ? defaultValue : (Boolean) get(key);
	}

	public static ParameterMap of(String key1, Object value1) {
		ParameterMap map = new ParameterMap();
		map.put(key1, value1);
		return map;
	}

	public static ParameterMap of(String key1, Object value1, String key2, Object value2) {
		ParameterMap map = new ParameterMap();
		map.put(key1, value1);
		map.put(key2, value2);
		return map;
	}

	public static ParameterMap of(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
		ParameterMap map = new ParameterMap();
		map.put(key1, value1);
		map.put(key2, value2);
		map.put(key3, value3);
		return map;
	}

	public static ParameterMap of(String key1, Object value1, String key2, Object value2, String key3, Object value3, String key4,
			Object value4) {
		ParameterMap map = new ParameterMap();
		map.put(key1, value1);
		map.put(key2, value2);
		map.put(key3, value3);
		map.put(key4, value4);
		return map;
	}

	public static ParameterMap fromStringArrayMap(Map<String, String[]> overwriteParams) {
		ParameterMap res = new ParameterMap();
		if (overwriteParams == null) {
			return res;
		}
		for (String key : overwriteParams.keySet()) {
			String[] value = overwriteParams.get(key);
			res.put(key, value[0]);
		}
		return res;
	}

	public static ParameterMap fromStringMap(Map<String, String> overwriteParams) {
		ParameterMap res = new ParameterMap();
		if (overwriteParams == null) {
			return res;
		}
		for (String key : overwriteParams.keySet()) {
			String value = overwriteParams.get(key);
			res.put(key, value);
		}
		return res;
	}

	public void overwrite(ParameterMap overwriteParams) {
		for (String key : overwriteParams.keySet()) {
			Object value = overwriteParams.get(key);
			put(key, value);
		}
	}
}