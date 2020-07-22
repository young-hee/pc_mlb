package com.plgrim.ncp.framework.filter;


import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.Environment;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectFilter implements Filter {

    protected static final Log logger = LogFactory.getLog(RedirectFilter.class);
    private Environment env;

    String REDIRECT_URL = null;
    String device = null;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.env = ContextService.getBean(Environment.class);
        if (env == null) {
            throw new IllegalArgumentException("Environment must not be null");
        }

        this.device = env.getProperty("ncp.server.target");
        if (device == null) {
            throw new ServletException("Device must not be null");
        }

        if (device.equals("PC")) {
            this.REDIRECT_URL = env.getProperty("ncp.url.mb.root.secure");
        } else {
            this.REDIRECT_URL = env.getProperty("ncp.url.pc.root.secure");
        }

        if (REDIRECT_URL == null) {
            throw new ServletException("RedirectURL must not be null");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getServletPath() + httpRequest.getPathInfo();

        /**
         * /system/healthcheck URI PATH
         */
        if(path.contains("/system/healthcheck")) {
            chain.doFilter(httpRequest, response);
            return;
        }

        /**
         * Local 일 경우 PATH
         */
        if (ContextService.isLocalProfile()) {
            chain.doFilter(request, response);
            return;
        }

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        String userAgent = req.getHeader("User-Agent");
        String deviceType = userAgent.toLowerCase();
        String result = "PC";

        String[] deviceStr = StringUtils.split("iphone|ipod|ipad|blackberry|android|windows ce|lg|mot|samsung|sonyericsson", "|");

        for (String mobile : deviceStr) {
            if (deviceType.contains(mobile)) {
                result = "MOBILE";
                break;
            }
        }

        if (!device.equals(result)) {
            StringBuffer redirectURL = new StringBuffer();
            redirectURL.append(REDIRECT_URL);
            redirectURL.append(req.getRequestURI());

            String queryString = req.getQueryString();
            if (StringService.isNotEmpty(queryString)) {
                redirectURL.append("?");
                redirectURL.append(queryString);
            }

            res.sendRedirect(redirectURL.toString());
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}