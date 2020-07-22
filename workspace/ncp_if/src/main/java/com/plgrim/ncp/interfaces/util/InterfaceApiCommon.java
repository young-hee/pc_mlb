package com.plgrim.ncp.interfaces.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONSerializer;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * interface 에서 공통으로 사용하는 기능들을 정의한 common class 이다.
 *
 * @author John_Kim
 */
@Component
@Slf4j
public class InterfaceApiCommon {

    @Value("${ncp_base.interface.internal.server.url}")
    private String internalInterfaceServerUrl;

    @Value("${ncp_base.interface.external.server.url}")
    private String externalInterfaceServerUrl;

    @Value("${ncp_base.interface.http.connection.timeout}")
    private int connectTimeout;
    @Value("${ncp_base.interface.http.read.timeout}")
    private int readTimeout;
    
    @Value("${ncp_base.interface.facebook.server.url}")
    private String facebookInterfaceServerUrl;
    
    @Value("${ncp_base.interface.facebook.access.key}")
    private String facebookInterfaceAccessKey;

    /**
     * internal interface server url 을 가져온다.
     *
     * @return
     */
    public String getInternalInterfaceServerUrl() {
        return this.internalInterfaceServerUrl;
    }

    /**
     * external interface server url 을 가져온다.
     *
     * @return
     */
    public String getExternalInterfaceServerUrl() {
        return this.externalInterfaceServerUrl;
    }
    
    /**
     * facebook interface server url 을 가져온다.
     *
     * @return
     */
    public String getFacebookInterfaceServerUrl() {
        return this.facebookInterfaceServerUrl;
    }
    public String getFacebookInterfaceAccessKey() {
        return this.facebookInterfaceAccessKey;
    }

    /**
     * RequestId 를 가져 온다.
     *
     * @return
     */
    public String getRequestId() {
        log.debug("{}", this.getClass().getName() + ".getRequestId");

        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 현재 일자와 시간을 string format 으로 가져 온다.
     *
     * @return
     */
    public String getCurrentDate() {
        log.debug("{}", this.getClass().getName() + ".getCurrentDate");

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        String currentDate = simpleDateFormat.format(date);

        return currentDate;
    }

    /**
     * 현재 일자를 string format 으로 가져 온다.
     *
     * @return
     */
    public String getShortCurrentDate() {
        log.debug("{}", this.getClass().getName() + ".getShortCurrentDate");

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        String currentDate = simpleDateFormat.format(date);

        return currentDate;
    }

    /**
     * '2010-10-22 15:20:46' 포멧의 string 을 '2010-10-22 15:20:46' 포멧의 date 로 변환해서 반환 한다.
     *
     * @param stringDate
     * @return
     * @throws ParseException
     */
    public Date getDateFromString(String stringDate) throws ParseException {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return transFormat.parse(stringDate);
    }

    /**
     * interface server 와 RESTful 통신한다.
     *
     * @param inputJson
     * @param serverUrl
     * @return
     */
    public String requestToServer(String inputJson, String serverUrl, AdapterHeader adapterHeader) throws Exception {

        log.debug("{}", this.getClass().getName() + ".requestToServer");
        log.debug("> inputJson : {}", inputJson);
        log.debug("> serverUrl : {}", serverUrl);

        String result = "";

        try {
            URL postUrl = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();

            /**
             * Default 외 AdapterHeader를 통해 명시적으로 timeout 설정을 할 경우 그 설정에 따름
             */
            if (adapterHeader.getConnectionTimeout() != -1 && adapterHeader.getReadTimeout() != -1) {
                connection.setConnectTimeout(adapterHeader.getConnectionTimeout());
                connection.setReadTimeout(adapterHeader.getReadTimeout());
            } else {
                connection.setConnectTimeout(connectTimeout);
                connection.setReadTimeout(readTimeout);
            }

            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            /**
             * Request Header setup.
             */

            // request id.
            connection.setRequestProperty("REQUEST_ID", adapterHeader.getRequestId());
            // mall id.
            connection.setRequestProperty("MALL_ID", adapterHeader.getMallId());
            // locale.
            connection.setRequestProperty("LANG_CD", adapterHeader.getLangCd());
            // channel.
            connection.setRequestProperty("DVC_CD", adapterHeader.getDvcCd());

            OutputStream os = connection.getOutputStream();
            os.write(inputJson.getBytes("UTF-8"));
            os.flush();

            if (connection.getHeaderField("INTERFACE_EXCEPTION_CODE") != null) {

                String errorMessage = URLDecoder.decode(connection.getHeaderField("INTERFACE_EXCEPTION_MESSAGE"), "UTF-8");

                log.info("> INTERFACE_EXCEPTION_CODE    : {}", connection.getHeaderField("INTERFACE_EXCEPTION_CODE"));
                log.info("> INTERFACE_EXCEPTION_MESSAGE : {}", errorMessage);
                
                JSONObject json = new JSONObject();
                
                json.put("RESPONSE_CD", connection.getHeaderField("INTERFACE_EXCEPTION_CODE"));
                json.put("RESPONSE_MSG", errorMessage);
                
                result = json.toString();
                
//                result = "EXCEPTION" + ":" + connection.getHeaderField("INTERFACE_EXCEPTION_CODE") + ":" + errorMessage;

            } else {
                log.info("> INTERFACE_EXCEPTION_CODE : {}", "INTERFACE_EXCEPTION_CODE is null. --> success to call interface server.");

                int responseCode = connection.getResponseCode();
                log.info("responseCode = " + responseCode);

                if (responseCode == 200 || responseCode == 201 || responseCode == 204) {
                	InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in), 8192);
                    StringBuilder builder = new StringBuilder();
                    String line = null;

                    try {
                        while ((line = reader.readLine()) != null) {
                            builder.append(line).append("\n");
                        }
                    } catch (IOException e) {
                        log.error("", e);
                    }

                    result = builder.toString().replace("\n", "");
                } else {
                    JSONObject json = new JSONObject();
                    json.put("RESPONSE_CD", responseCode);

                    result = json.toString();
                }
            }
        } catch (Exception e) {
            result = "FAIL";
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            throw e;
        }

        log.info("> common result : {}", result);

        return result;
    }

    /**
     * interface server 와 RESTful 통신한다. inputstream 도 UTF-8 처리
     *
     * @param inputJson
     * @param serverUrl
     * @return
     */
    public String requestToServerUTF8(String inputJson, String serverUrl, AdapterHeader adapterHeader) throws Exception {

        log.debug("{}", this.getClass().getName() + ".requestToServer");
        log.debug("> inputJson : {}", inputJson);
        log.debug("> serverUrl : {}", serverUrl);

        String result = "";

        try {
            URL postUrl = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();

            connection.setConnectTimeout(3000);
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            /**
             * Request Header setup.
             */

            // request id.
            connection.setRequestProperty("REQUEST_ID", adapterHeader.getRequestId());
            // mall id.
            connection.setRequestProperty("MALL_ID", adapterHeader.getMallId());
            // locale.
            connection.setRequestProperty("LANG_CD", adapterHeader.getLangCd());
            // channel.
            connection.setRequestProperty("DVC_CD", adapterHeader.getDvcCd());

            OutputStream os = connection.getOutputStream();
            os.write(inputJson.getBytes("UTF-8"));
            os.flush();

            if (connection.getHeaderField("INTERFACE_EXCEPTION_CODE") != null) {

                String errorMessage = URLDecoder.decode(connection.getHeaderField("INTERFACE_EXCEPTION_MESSAGE"), "UTF-8");

                log.info("> INTERFACE_EXCEPTION_CODE    : {}", connection.getHeaderField("INTERFACE_EXCEPTION_CODE"));
                log.info("> INTERFACE_EXCEPTION_MESSAGE : {}", errorMessage);

                result = "EXCEPTION" + ":" + connection.getHeaderField("INTERFACE_EXCEPTION_CODE") + ":" + errorMessage;

            } else {
                log.info("> INTERFACE_EXCEPTION_CODE : {}", "INTERFACE_EXCEPTION_CODE is null. --> success to call interface server.");

                int responseCode = connection.getResponseCode();
                InputStream in = connection.getInputStream();

                if (responseCode == 200 || responseCode == 201 || responseCode == 204) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 8192);
                    StringBuilder builder = new StringBuilder();
                    String line = null;

                    try {
                        while ((line = reader.readLine()) != null) {
                            builder.append(line).append("\n");
                        }
                    } catch (IOException e) {
                        log.error("", e);
                    }

                    result = builder.toString().replace("\n", "");
                } else {
                    result = responseCode + "";
                }
            }
        } catch (Exception e) {
            result = "FAIL";
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            throw e;
        }

        log.info("> common result : {}", result);

        return result;
    }
}
