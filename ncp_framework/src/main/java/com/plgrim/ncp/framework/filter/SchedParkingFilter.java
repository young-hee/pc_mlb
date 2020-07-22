package com.plgrim.ncp.framework.filter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SchedParkingFilter implements Filter {
	
	protected static final Log logger = LogFactory.getLog(SchedParkingFilter.class);

	private static final String REDIRECT_URL = "RedirectURL";

	private static final String START_DT = "StartDt";

	private static final String END_DT = "EndDt";

	private static final SimpleDateFormat sdf = new SimpleDateFormat(
	        "yyyy/MM/dd hh:mm:ss");

	String redirectUrl = null;

	Date startDt = null;

	Date endDt = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		this.redirectUrl = filterConfig.getInitParameter(REDIRECT_URL);

		if (redirectUrl == null) {
			throw new ServletException("RedirectURL must not be null");
		}

		final String start = filterConfig.getInitParameter(START_DT);
		final String end = filterConfig.getInitParameter(END_DT);

		if (start != null) {
			try {
				startDt = sdf.parse(start);
			}
			catch (ParseException e) {
				throw new ServletException("Invalid date form : "
				        + e.getMessage());
			}
		}

		if (end != null) {
			try {
				endDt = sdf.parse(end);
			}
			catch (ParseException e) {
				throw new ServletException("Invalid date form : "
				        + e.getMessage());
			}
		}
	}

	private boolean after(Date d) {
		if (startDt == null || startDt.before(d)) {
			return true;
		}

		return false;
	}

	private boolean before(Date d) {
		if (endDt == null || endDt.after(d)) {
			return true;
		}

		return false;
	}

	private boolean isIn() {
		final Date now = new Date();

		if (after(now) && before(now))
			return true;

		return false;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
	        FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse res = (HttpServletResponse) response;

		final String requestURI = req.getRequestURI();

		if (!redirectUrl.equals(requestURI) && isIn()) {
			logger.info("Redirect URL : " + redirectUrl);
			res.sendRedirect(redirectUrl);
			return;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}