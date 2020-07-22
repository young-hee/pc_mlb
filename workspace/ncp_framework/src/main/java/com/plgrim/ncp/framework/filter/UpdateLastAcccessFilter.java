package com.plgrim.ncp.framework.filter;

import com.plgrim.ncp.framework.data.CrossSiteToken;
import com.plgrim.ncp.framework.exception.CryptoException;
import com.plgrim.ncp.framework.utils.CryptoUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class UpdateLastAcccessFilter implements Filter {
    public static final String CROSS_SITE_TOKEN_KEY = "crossSite";
    private static final String COOKIE_DOMAIN = "plgrimshop.com";

    @Getter
    @Setter
    String tokenCookieName = "GF_RM";

    @Getter
    @Setter
    long expireMilis = 1000 * 60 * 30;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        try {
            preHandle(req, res);
        } catch (Exception ex) {
            log.error("{}", ex);
        }

        try {
            chain.doFilter(request, response);
        } finally {
            postHandle(req, res);
        }

    }

    private void preHandle(HttpServletRequest req, HttpServletResponse res) {
        Cookie[] cookies = req.getCookies();
        if (ArrayUtils.isEmpty(cookies)) {
            return;
        }
        for (Cookie cookie : cookies) {
            if (tokenCookieName.equals(cookie.getName()) == false) {
                continue;
            }
            try {
                log.debug("CrossSiteToken cookie: {}", cookie.getValue());
                CrossSiteToken token = decrypt(cookie.getValue());
                long now = System.currentTimeMillis();
                log.info("CrossSiteToken: {}", token);

                if (token.isExpired(now, expireMilis)) {
                    log.info("CrossSiteToken is expired");

                    String contextPath = req.getContextPath();
                    Cookie gfCookie = new Cookie(tokenCookieName, null);
                    gfCookie.setDomain("plgrimshop.com");
                    gfCookie.setMaxAge(0);
                    gfCookie.setPath(contextPath.length() > 0 ? contextPath : "/");
                    res.addCookie(gfCookie);

                    return;
                }
                token.setLastAccessTimestamp(now);
                req.setAttribute(CROSS_SITE_TOKEN_KEY, token);
            } catch (Exception ex) {
                log.error("Can not decrypt crosssite tokne cookie. {}", cookie.getValue(), ex);
                return;
            }
        }
    }

    private void postHandle(HttpServletRequest req, HttpServletResponse res) {
        CrossSiteToken token = (CrossSiteToken) req.getAttribute(CROSS_SITE_TOKEN_KEY);
        if (token == null) {
            return;
        }

        String encryptedData = encrypt(token);

        log.debug("Handle crossSiteToken:{} encryptedStr: {}", token, encryptedData);
        addCookie(tokenCookieName, encryptedData, req, res);
    }

    private void addCookie(String tokenCookieName, String encryptedData, HttpServletRequest req, HttpServletResponse res) {
        String contextPath = req.getContextPath();
        Cookie cookie = new Cookie(tokenCookieName, encryptedData);
        cookie.setDomain(COOKIE_DOMAIN);
        cookie.setMaxAge(-1);
        cookie.setPath(contextPath.length() > 0 ? contextPath : "/");

        res.addCookie(cookie);
    }

    private String encrypt(CrossSiteToken token) {
        try {
            String src = token.getMbrId() + "," + token.getLastAccessTimestamp();
            return CryptoUtil.encryptBase64(src);
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    private CrossSiteToken decrypt(String value) {
        try {
            String decryptedStr = CryptoUtil.decryptBase64(value);

            if (StringUtils.isEmpty(decryptedStr)) {
                return null;
            }
            String[] tokens = decryptedStr.split(",");
            if (tokens.length != 2) {
                return null;
            }
            return new CrossSiteToken(tokens[0], Long.parseLong(tokens[1]));
        } catch (Exception e) {
            log.error("{}", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
