package com.plgrim.ncp.framework.interceptor;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CountryResponse;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Client Ip를 통해 로케일 변경
 * https://en.wikipedia.org/wiki/ISO_3166-1
 */
@Slf4j
public class GeoIpLocaleInterceptor implements HandlerInterceptor {

    @Autowired
    ApplicationContext ctx;

    @Value("${ncp_base.locale.cookie.name}")
    String cookieName;

    @Value("${ncp_base.locale.iso.code.name}")
    String isoCodeName;

    @Value("${ncp_base.locale.supported}")
    private String supportedLocale;
    
    // Request 요청시마다 File Reading 메모리 효율 문제 개선 
    private static DatabaseReader reader;
    static {
    	Resource resource = new ClassPathResource("GeoLite2-Country.mmdb");
        DatabaseReader tmp = null;
        try {
        	tmp = new DatabaseReader.Builder(resource.getInputStream()).build();
        } catch (Exception e) {
          // Handle exception.
        	log.error("GeoIpLocaleInterceptor.GeoLite2-Country.mmdb.Reading Error");
        }
        reader = tmp;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String reqUri = request.getRequestURI();

        /**
         * healthcheck interceptor 제외
         */
        if(reqUri.contains("/system/healthcheck")) {
            log.debug("{} request Uri : {}", this.getClass(), reqUri);
            return true;
        }

        /**
         * Local 일 경우 Path
         * Local의 경우 database 에 국가정보가 없으므로
         * Local Test 시에는 아래 구문을 주석 처리 후 client ip를 지정해야한다.
         */
        if (ContextService.isLocalProfile()) {
            log.debug("{} local Path", this.getClass().getName());
            return true;
        }

        HttpSession session = request.getSession();
        String clientIsoCode = String.valueOf(session.getAttribute(isoCodeName));
        String isoCode = clientIsoCode;

        /**
         * Session 에 client PC의 ISO_CODE 가 없을 경우 IP를 사용하여 재설정
         * GeoLieIP 의 경우 Country 정보만 가져올 수 있다.
         * 사용되는 국가 / 언어 코드
         * 한국/중국(홍콩포함)/그외
         * 국가코드: KR/CN/EN
         * 언어코드: ko/zh/en
         */
        log.debug("{} clientIsoCode : {}", this.getClass(), clientIsoCode);
        if (StringService.isEmpty(clientIsoCode) || "null".equals(clientIsoCode)) {
            /**
             * GeoIP Database file Read
             */
            //Resource resource = new ClassPathResource("GeoLite2-Country.mmdb");
//            try (DatabaseReader reader = new DatabaseReader.Builder(GeoIpLocaleInterceptor.resource.getInputStream()).build()) {
        	try {
//              InetAddress ipAddress = InetAddress.getByName("206.190.36.45");   // 미국
//              InetAddress ipAddress = InetAddress.getByName("45.112.188.55");   // 중국
//              InetAddress ipAddress = InetAddress.getByName("14.198.224.44");   // 홍콩
//              InetAddress ipAddress = InetAddress.getByName("43.224.248.66");   // 대만
//              InetAddress ipAddress = InetAddress.getByName("14.32.15.193");   // 한국
                InetAddress ipAddress = InetAddress.getByName(getClientIp(request));
                CountryResponse cityResponse = reader.country(ipAddress);
                isoCode = cityResponse.getCountry().getIsoCode();

                /**
                 * 홍콩의 경우 ISO_CODE 및 언어코드 모두 중국으로 설정
                 */
                if ("HK".equals(isoCode)) {
                    isoCode = "CN";
                }

                session.setAttribute(isoCodeName, isoCode);
            } catch (Exception e) {
                session.setAttribute(isoCodeName, isoCode);
                log.error("{} request URI : {} {}", this.getClass().getName(), getClientIp(request), reqUri);
                log.error("{} getCountry Fail {} {}", this.getClass().getName(), e.getMessage(), e.getStackTrace());
            }
        }

        /**
         * language 파라미터 우선 적용
         */
        String cookie = CookieUtil.getCookieValue(request, cookieName);
        String queryString = request.getQueryString();

        if (StringService.isNotEmpty(queryString) && queryString.lastIndexOf("language") > -1) {
            String language = queryString.substring(queryString.lastIndexOf("language"));
            Pattern p = Pattern.compile("language(\\=|\\%3D|%253D)(..)");
            Matcher m = p.matcher(language);

            String localeCode = "";
            if (m.find() && m.groupCount() == 2) {
                localeCode = m.group(2);
            }

            /**
             * 파라미터와 쿠키와 동일 할 경우 Path
             */
            if (cookie != null && localeCode.equals(cookie)) {
                return true;
            }

            /**
             * 지원하지 않는 언어의 경우 영어로 설정
             */
            if (!supportedLocale.contains(localeCode)) {
                localeCode = "en";
            }
            Locale locale = StringUtils.parseLocaleString(localeCode);
            log.debug("{} parameter locale change  : {}", this.getClass(), locale);

            RequestContextUtils.getLocaleResolver(request).setLocale(request, response, locale);
        } else {
            /**
             * Cookie에 설정된 로케일에 따름 없을 경우 접속 IP를 체크하여 로케일 설정
             * 한국(KR) - 한국어(ko)
             * 중국(CN) - 중국어(zh)
             * 그외 - 영어(en)
             */
            if (StringService.isEmpty(cookie)) {
                Locale locale = Locale.ENGLISH;
                if (Locale.KOREA.getCountry().equals(isoCode)) {
                    locale = Locale.KOREAN;
                } else if (Locale.CHINA.getCountry().equals(isoCode)
                        || Locale.TAIWAN.getCountry().equals(isoCode)) {
                    locale = Locale.CHINESE;
                }

                log.debug("{} locale change  : {}", this.getClass(), locale);
                RequestContextUtils.getLocaleResolver(request).setLocale(request, response, locale);
            }
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * request 를 이용해서 Client IP 를 가져오기
     *
     * @param request
     * @return
     */
    private String getClientIp(HttpServletRequest request) {
        String clientIp = StringService.trimToEmpty(request.getHeader("X-Forwarded-For"));

        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getRemoteAddr();
        }

        /**
         * X-Forwarded-For의 경우 1.1.1.1, 2.2.2.2 의 형식의 String 이므로
         * client ip 를 check 하기 위하여 첫번재 인덱스 사용
         */
        if(StringService.indexOfAny(clientIp, ",") > -1){
            String[] clientIpArr = clientIp.split(",");
            clientIp = clientIpArr[0];
        }

        return clientIp;
    }

}
