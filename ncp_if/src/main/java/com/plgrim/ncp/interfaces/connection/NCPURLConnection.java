package com.plgrim.ncp.interfaces.connection;


import com.jcraft.jsch.Logger;
import com.plgrim.ncp.base.abstracts.AbstractSDO;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.responsecode.ResponseException;
import com.plgrim.ncp.interfaces.util.NCPURLConnectionParameters;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.BeanUtils;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Data
@Slf4j
public class NCPURLConnection <T extends AbstractSDO> {

    private String requestUrl;
    private String connectTimeout;
    private String readTimeout;
    private List<ImmutablePair<String, String>> keyPiar;


    /**
     * ConnectionParameter가 없을 경우 Default 값 설정
     *
     * @param inputJson
     * @return
     * @throws Exception
     */
    public String send(String requestUri, String inputJson) throws Exception {
        NCPURLConnectionParameters ncpurlConnectionParameters = getNcpurlConnectionParameters();

        return send(requestUri, inputJson, ncpurlConnectionParameters);
    }

    /**
     * Class 를 매개변수로 받아 json marshalling 처리
     *
     * @param inputData
     * @return
     * @throws Exception
     */
    public String send(String requestUri, T inputData) throws Exception {
        NCPURLConnectionParameters ncpurlConnectionParameters = getNcpurlConnectionParameters();

        return send(requestUri, JsonService.marshalling(inputData), ncpurlConnectionParameters);
    }

    /**
     * Default URLConnection parameter setting
     *
     * @return
     */
    private NCPURLConnectionParameters getNcpurlConnectionParameters() {
        NCPURLConnectionParameters ncpurlConnectionParameters = new NCPURLConnectionParameters();

        ncpurlConnectionParameters.setRequestMethod("POST");
        ncpurlConnectionParameters.setCharsetName("UTF-8");

        Map<String, String> requestPropertyMap = new HashMap<>();
        requestPropertyMap.put("Content-Type", "application/json");

        if (keyPiar != null) {
            for (ImmutablePair<String, String> pair : keyPiar) {
                requestPropertyMap.put(pair.getKey(), pair.getValue());
            }
        }

        ncpurlConnectionParameters.setRequestPropertyMap(requestPropertyMap);

        return ncpurlConnectionParameters;
    }


    /**
     * Class 를 매개변수로 받아 json marshalling 처리
     * Default 값이 아닌 별도의 parameter setting 으로 값 설정
     *
     * @param requestUri
     * @param inputData
     * @param ncpurlConnectionParameters
     * @return
     * @throws Exception
     */
    public String send(String requestUri, T inputData, NCPURLConnectionParameters ncpurlConnectionParameters) throws Exception {
        return this.send(requestUri, JsonService.marshalling(inputData), ncpurlConnectionParameters);
    }


    /**
     * Default 값이 아닌 별도의 parameter setting 으로 값 설정
     *
     * @param inputJson
     * @param ncpurlConnectionParameters
     * @return
     * @throws Exception
     */
    public String send(String requestUri, String inputJson, NCPURLConnectionParameters ncpurlConnectionParameters) throws Exception {
        InputStream in = null;
        HttpURLConnection connection = null;
        try {
        	log.debug("=====if url ==> " + this.requestUrl + StringService.nvl(requestUri, ""));
            URL url = new URL(this.requestUrl + StringService.nvl(requestUri, ""));
            if (this.requestUrl.contains("https://")) {
                // Get HTTPS URL connection
                connection = (HttpsURLConnection) url.openConnection();

                // URLConnection init
                initConnection(connection, ncpurlConnectionParameters);

                // HttpsURLConnection init
                initHttpsConnection((HttpsURLConnection) connection);

            } else {
                connection = (HttpURLConnection) url.openConnection();

                // URLConnection init
                initConnection(connection, ncpurlConnectionParameters);
            }

            OutputStream os = connection.getOutputStream();
            os.write(inputJson.getBytes(ncpurlConnectionParameters.getCharsetName()));
            os.flush();

            String result = "";
            int responseCode = connection.getResponseCode();
            if (responseCode == 200 || responseCode == 201 || responseCode == 204) {
                in = connection.getInputStream();

                result = getResultJson(in);

                // Response Result 에 대하여 항상 출력 하도록 설정
                log.debug("Response Result {}", result);

                return result;
            } else {
                /**
                 * 정상적인 response 를 받지 않으면 exception 을 명시적으로 발생시킨다.
                 * inputJson과 responseCode 를 항상 출력 하도록 설정
                 */
                in = connection.getErrorStream();

                result = getResultJson(in);

                log.error("Response Error InputJson : {} \nResponseCode : {} \nErrMessage : {}", inputJson, responseCode, result);
                throw new ResponseException("Response error code");
            }
        } finally {
            IOUtils.closeQuietly(in);
            try {
                connection.disconnect();
            } catch (Exception e) {
                log.warn("Connection Times Error");
            }
        }
    }

    /**
     * InputStream 을 통해 Json 형태로 marshalling
     * @param in
     * @return
     * @throws IOException
     */
    private String getResultJson(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in), 8192);
        StringBuilder builder = new StringBuilder();
        String line = null;

        while ((line = reader.readLine()) != null) {
            builder.append(line).append("\n");
        }

        return builder.toString().replace("\n", "");
    }

    /**
     * URLConnection init
     * connection parameter setting
     *
     * @param connection
     * @param ncpurlConnectionParameters
     * @throws ProtocolException
     */
    private void initConnection(HttpURLConnection connection, NCPURLConnectionParameters ncpurlConnectionParameters) throws ProtocolException {
        connection.setConnectTimeout(Integer.parseInt(this.connectTimeout));
        connection.setReadTimeout(Integer.parseInt(this.readTimeout));
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod(ncpurlConnectionParameters.getRequestMethod());

        Map<String, String> requestPropertyMap = ncpurlConnectionParameters.getRequestPropertyMap();
        Iterator<String> keys = requestPropertyMap.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            connection.setRequestProperty(key, ncpurlConnectionParameters.getRequestPropertyMap().get(key));
        }
    }

    /**
     * Default Value 이외 Config 설정이 변경이 필요 할 경우 현재 Bean을 copy 하여 사용
     *
     * @param target the target bean(NCPURLConnection)
     */
    public void connectionBeanClone(NCPURLConnection target) {
        BeanUtils.copyProperties(this, target);
    }

    /**
     * HttpsConnection Init
     *
     * @param connection
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws CertificateException
     */
    private void initHttpsConnection(HttpsURLConnection connection) throws NoSuchAlgorithmException, KeyManagementException, CertificateException {
        // Set Hostname verification
        connection.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                // Ignore host name verification. It always returns true.
                return true;
            }
        });

        // SSL setting
        SSLContext context = SSLContext.getInstance("TLSv1.2");
        context.init(null, getTrustManager(), new SecureRandom());
        connection.setSSLSocketFactory(context.getSocketFactory());
    }

    /**
     * GET TrustManger Class
     * jdk 공인 인증서를 사용
     *
     * @return
     * @throws CertificateException
     */
    private TrustManager[] getTrustManager() throws CertificateException {
        return new TrustManager[]{new X509TrustManager() {

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // client certification check
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // Server certification check
                try {
                    // Get trust store
                    KeyStore trustStore = KeyStore.getInstance("JKS");
                    String cacertPath = System.getProperty("java.home") + "/lib/security/cacerts"; // Trust store path should be different by system platform.
                    trustStore.load(new FileInputStream(cacertPath), "changeit".toCharArray()); // Use default certification validation

                    // Get Trust Manager
                    TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    tmf.init(trustStore);
                    TrustManager[] tms = tmf.getTrustManagers();
                    ((X509TrustManager) tms[0]).checkServerTrusted(chain, authType);
                } catch (KeyStoreException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }};
    }
}
