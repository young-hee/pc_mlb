package com.plgrim.ncp.commons.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;

/**
 * html 읽기
 * @author ka2
 *
 */
@Service
public class MailHtmlReaderService extends AbstractService {
    @Value("${ncp_base.interface.http.connection.timeout}")
    String connectionTimeout;
    @Value("${ncp_base.interface.http.read.timeout}")
    String readTimeout;


	public String readHTML(String sendurl,String paramString) {
        String line;
        StringBuilder htmlString = new StringBuilder();;
        try {
            // Get HTTPS URL connection
            URL url = new URL(sendurl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            initHttpsConnection(conn);

            // Connect to host
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(Integer.parseInt(this.connectionTimeout));
            conn.setReadTimeout(Integer.parseInt(this.readTimeout));
    
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(paramString);
            writer.flush();
            writer.close();
            os.close();
            
            conn.connect();
            conn.setInstanceFollowRedirects(true);
    
            // Print response from host  
            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            
            while ((line = reader.readLine()) != null) {
            	htmlString.append(line);
            }
            //log.debug(htmlString.toString());
            
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return htmlString.toString();
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
