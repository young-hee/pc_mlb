package com.plgrim.ncp.framework.interceptor;

import com.plgrim.ncp.framework.systems.Stage;
import com.plgrim.ncp.framework.systems.SystemContext;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RedirectInterceptor extends HandlerInterceptorAdapter {

    @Getter
    @Setter
    String tokenCookieName = "UIUX_REDRIECT_COOKIE";

    @Autowired
    SystemContext systemContext;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (!Stage.LOCAL.equals(systemContext.getStage()) && !Stage.STAGING.equals(systemContext.getStage())) {

            boolean chk = false;
            Cookie[] cookies = request.getCookies();

            if(cookies != null){
                for (Cookie cookie : cookies) {
                    try {
                        if (!tokenCookieName.equals(cookie.getName())) {
                            continue;
                        }

                        String redirectCookie = cookie.getValue();

                        if ("Y".equals(redirectCookie)) {
                            chk = true;
                        }
                    } catch (Exception ex) {
                        log.error("UIUX_REDRIECT ERROR: {}", ex);
                        throw ex;
                    }

                }
            }

            String userAgent = request.getHeader("User-Agent");
            String appVersion = "0";
            if(userAgent.indexOf("app_version_") > 0){
                int appVersionIndex = userAgent.indexOf("app_version_");
                appVersion = userAgent.substring(appVersionIndex+12, appVersionIndex+16);
            }

            if (!chk && !(((userAgent.contains("Android") && userAgent.contains("plgrimshop_app") && Double.parseDouble(appVersion) >= 1.20)
                    || ((userAgent.contains("iPhone") || userAgent.contains("iPod") || userAgent.contains("iPad"))
                    && userAgent.contains("plgrimshop_app") && Double.parseDouble(appVersion) >= 1.10))
                    || (userAgent.contains("beanpole_app") && Double.parseDouble(appVersion) >= 2.10)
                    || (userAgent.contains("8seconds_app") && Double.parseDouble(appVersion) >= 4.10)) ) {
                log.info("request URI : {}", request.getServerName()+request.getRequestURI());

                String url = request.getServerName();

                response.sendRedirect("http://" + url + "/park.html");

                return false;
            }

        }

        return true;
    }

}