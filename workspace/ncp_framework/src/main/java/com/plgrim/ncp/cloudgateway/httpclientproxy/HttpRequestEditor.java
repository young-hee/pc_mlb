package com.plgrim.ncp.cloudgateway.httpclientproxy;

import java.beans.PropertyEditorSupport;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpRequestEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		ObjectMapper mapper = new ObjectMapper();

		HttpProxyRequest value = null;

		try {
			value = new HttpProxyRequest();
			setValue(mapper.readValue(text, HttpProxyRequest.class));
		} catch (IOException e) {
			// handle error
		}

		setValue(value);
	}
}