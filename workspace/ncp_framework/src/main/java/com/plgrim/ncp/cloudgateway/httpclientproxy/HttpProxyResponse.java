package com.plgrim.ncp.cloudgateway.httpclientproxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpProxyResponse {
	String errorMessage;
	Integer status;
	NameValuePair[] headers = {};
	String body;

	public void addHeader(String name, String value) {
		List<NameValuePair> temp = new ArrayList<>(Arrays.asList(headers));
		temp.add(new NameValuePair(name, value));
		headers = temp.toArray(new NameValuePair[temp.size()]);
	}
}
