package com.plgrim.ncp.cloudgateway.httpclientproxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class HttpProxyRequest {
	String method;
	String url;
	String ip;
	String responseEncoding;
	Integer socketTimeout;
	NameValuePair[] headers = {};
	NameValuePair[] params = {}; 
	String body;

	public void addParameter(String name, String value) {
		List<NameValuePair> temp = new ArrayList<>(Arrays.asList(params));
		temp.add(new NameValuePair(name, value));
		params = temp.toArray(new NameValuePair[temp.size()]);
	}

	public void addHeader(String name, String value) {
		List<NameValuePair> temp = new ArrayList<>(Arrays.asList(headers));
		temp.add(new NameValuePair(name, value));
		headers = temp.toArray(new NameValuePair[temp.size()]);
	}
}
