package com.plgrim.ncp.commons.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.commons.data.order.NaverCancelReturnDTO;
import com.plgrim.ncp.commons.data.order.NaverConfirmReturnDTO;
import com.plgrim.ncp.commons.data.order.NaverReturnDTO;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * <p>
 * 
 * <ul>
 * <li>[기능1]
 * <li>[기능2]
 * </ul>.
 *
 * @author syvictor.kim
 * @since 2015. 5. 6
 */
@Slf4j
@Service
public class NaverPayService extends AbstractService {
    
	public NaverReturnDTO nPayPayment(String paymentId, String mallId) {
        String line;
        String jsonString = "";
        NaverReturnDTO naverReturnDTO = null;
        try {
            // Get HTTPS URL connection
            URL url = new URL(getConfigService().getProperty("ncp_base.order.naverPay.paymentUrl")); // 설정행

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
    
            conn.setRequestMethod("POST");
            // SSL setting  
            SSLContext context = SSLContext.getInstance("TLSv1.2");
            context.init(null, null, null); // No validation for now  
            conn.setSSLSocketFactory(context.getSocketFactory());
            
            //mall 분리
            String clientId = getConfigService().getProperty("ncp_base.order.naverPay.clientId");
            String clientSecret = getConfigService().getProperty("ncp_base.order.naverPay.clientSecret");
            if("MBM".equals(mallId)) {
            	clientId = getConfigService().getProperty("ncp_base.order.naverPay.mlb.clientId");
            	clientSecret = getConfigService().getProperty("ncp_base.order.naverPay.mlb.clientSecret");
            }
            if("SAM".equals(mallId)) {
            	clientId = getConfigService().getProperty("ncp_base.order.naverPay.sa.clientId");
            	clientSecret = getConfigService().getProperty("ncp_base.order.naverPay.sa.clientSecret");
            }
            
            // Connect to host
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(50000);
            conn.setReadTimeout(50000);
            conn.setRequestProperty("X-Naver-Client-Id", clientId); // 설정행
            conn.setRequestProperty("X-Naver-Client-Secret", clientSecret); // 설정행
            
    
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write("paymentId="+paymentId);
            writer.flush();
            writer.close();
            os.close();
            
            conn.connect();
            conn.setInstanceFollowRedirects(true);
    
            // Print response from host  
            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            
            while ((line = reader.readLine()) != null) {
                if("".equals(jsonString)){
                    jsonString = line;
                }
                log.debug(line);
            }
            naverReturnDTO = NaverReturnDTO.fromJSON(jsonString, NaverReturnDTO.class);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return naverReturnDTO;
    }
	
	public NaverCancelReturnDTO nPayCancel(String paymentId,long cancelAmount,String reason, String mallId) {
		//mall 분리
		String clientId = getConfigService().getProperty("ncp_base.order.naverPay.clientId");
		String clientSecret = getConfigService().getProperty("ncp_base.order.naverPay.clientSecret");
		if("MBM".equals(mallId)) {
			clientId = getConfigService().getProperty("ncp_base.order.naverPay.mlb.clientId");
			clientSecret = getConfigService().getProperty("ncp_base.order.naverPay.mlb.clientSecret");
		}
		if("SAM".equals(mallId)) {
			clientId = getConfigService().getProperty("ncp_base.order.naverPay.sa.clientId");
			clientSecret = getConfigService().getProperty("ncp_base.order.naverPay.sa.clientSecret");
		}
		
		return this.nPayCancel(
				paymentId
				, cancelAmount
				, reason
				, clientId
				, clientSecret
				);
       
    }
	
	
	
	public NaverCancelReturnDTO nPayCancel(String paymentId,long cancelAmount,String reason, String clientId, String clientSecret) {
        String line;
        String jsonString = "";
        NaverCancelReturnDTO naverCancelReturnDTO = null;
        try {
            // Get HTTPS URL connection
            URL url = new URL(getConfigService().getProperty("ncp_base.order.naverPay.cancelUrl")); // 설정행

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
    
            conn.setRequestMethod("POST");
            // SSL setting  
            SSLContext context = SSLContext.getInstance("TLSv1.2");
            context.init(null, null, null); // No validation for now  
            conn.setSSLSocketFactory(context.getSocketFactory());
    
            // Connect to host
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(50000);
            conn.setReadTimeout(50000);
            conn.setRequestProperty("X-Naver-Client-Id", clientId); // 설정행
            conn.setRequestProperty("X-Naver-Client-Secret", clientSecret); // 설정행
            
            
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write("paymentId="+paymentId+"&cancelAmount="+cancelAmount+"&taxScopeAmount="+cancelAmount+"&taxExScopeAmount=0&cancelReason="+reason+"&cancelRequester=" + this.getCancelRequester());
            writer.flush();
            writer.close();
            os.close();
            
            conn.connect();
            conn.setInstanceFollowRedirects(true);
    
            // Print response from host  
            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            
            while ((line = reader.readLine()) != null) {
                if("".equals(jsonString)){
                    jsonString = line;
                }
                log.debug(line);
            }
            naverCancelReturnDTO = NaverCancelReturnDTO.fromJSON(jsonString, NaverCancelReturnDTO.class);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return naverCancelReturnDTO;
    }
	
	/**
	 * 취소요청자값 조회
	 * 
	 * 1 : 구매자취소, 2: 가맹점 관리자 취소
	 * 
	 * @return
	 */
	private String getCancelRequester() {
		try {
			String systemId = getIdGenService().getEnvValue("system.id");
			if ("ncp_web_pc_dx".equals(systemId) || "ncp_web_mb_dx".equals(systemId)
					|| "ncp_web_pc_mlb".equals(systemId) || "ncp_web_mb_mlb".equals(systemId)
					|| "ncp_web_pc_sa".equals(systemId) || "ncp_web_mb_sa".equals(systemId) ) {
				log.info("getCancelRequester info 1");
				return "1";
			}
			log.info("getCancelRequester info 2");
			return "2";
        } catch (Exception e) {
        	log.error("getCancelRequester error");
        	log.error(e.getMessage(),e);
        	return "2";
        }
	}
	
	public NaverConfirmReturnDTO nPayPurchaseConfirm(String paymentId, String clientId, String clientSecret) {
		String line;
        String jsonString = "";
        NaverConfirmReturnDTO naverConfirmReturnDTO = null;
        try {
            // Get HTTPS URL connection
            URL url = new URL(getConfigService().getProperty("ncp_base.order.naverPay.confirmUrl")); // 설정행

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
    
            conn.setRequestMethod("POST");
            // SSL setting  
            SSLContext context = SSLContext.getInstance("TLSv1.2");
            context.init(null, null, null); // No validation for now  
            conn.setSSLSocketFactory(context.getSocketFactory());
    
            // Connect to host
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(50000);
            conn.setReadTimeout(50000);
            conn.setRequestProperty("X-Naver-Client-Id", clientId); // 설정행
            conn.setRequestProperty("X-Naver-Client-Secret", clientSecret); // 설정행
            
    
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write("paymentId="+paymentId+"&requester=1");
            writer.flush();
            writer.close();
            os.close();
            
            conn.connect();
            conn.setInstanceFollowRedirects(true);
    
            // Print response from host  
            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            
            while ((line = reader.readLine()) != null) {
                if("".equals(jsonString)){
                    jsonString = line;
                }
                log.debug(line);
            }
            naverConfirmReturnDTO = NaverConfirmReturnDTO.fromJSON(jsonString, NaverConfirmReturnDTO.class);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return naverConfirmReturnDTO;
	}

}
