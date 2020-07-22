package com.plgrim.ncp.framework.filter.cache;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CacheResponseWrapper extends HttpServletResponseWrapper {
	protected HttpServletResponse origResponse = null;
	protected ServletOutputStream stream = null;
	protected PrintWriter writer = null;
	protected OutputStream cache = null;
	String encoding;

	public CacheResponseWrapper(HttpServletResponse response, OutputStream cache, String enc) {
		super(response);
		origResponse = response;
		this.cache = cache;
		this.encoding = enc;
	}

	public CacheResponseWrapper(HttpServletResponse response, OutputStream cache) {
		this(response, cache, "UTF-8");
	}

	public ServletOutputStream createOutputStream() throws IOException {
		return (new CacheResponseStream(origResponse, cache));
	}

	public void flushBuffer() throws IOException {
		stream.flush();
	}

	public ServletOutputStream getOutputStream() throws IOException {
		if (writer != null) {
			throw new IllegalStateException("getWriter() has already been called!");
		}

		if (stream == null)
			stream = createOutputStream();
		return (stream);
	}

	public PrintWriter getWriter() throws IOException {
		if (writer != null) {
			return (writer);
		}

		if (stream != null) {
			throw new IllegalStateException("getOutputStream() has already been called!");
		}

		stream = createOutputStream();
		writer = new PrintWriter(new OutputStreamWriter(stream, encoding));
		return (writer);
	}
}