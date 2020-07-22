package com.plgrim.ncp.cloudgateway.recommend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RecommendCloudGatewayController {

	//private static final int SOCKET_TIMEOUT = 500;	
	private static final int SOCKET_TIMEOUT = 1000;	
	private static final int CONNECT_TIMEOUT = 500;
	public static final int REQUEST_TIMEOUT = 100;
	
	public static final int BATCH_SOCKET_TIMEOUT = 2000;	
	public static final int BATCH_CONNECT_TIMEOUT = 1000;
	public static final int BATCH_REQUEST_TIMEOUT = 500;
	
	private static ObjectMapper jsonMapper = new ObjectMapper();
	
	/**
	 * 레코픽의 상품기반 추천 상품리스트를 가져온다.
	 * 테스트: http://localhost:9999/recommend/recopick/item?serviceId=1612&pcId=14561267494942991950534&goodsId=GM0015082752627&limit=15
	 * http://112.106.29.71:9801/recommend/recopick/item?serviceId=1612&pcId=14561267494942991950534&goodsId=GM0015082752627&limit=15
	 * 
	 * @param serviceId
	 * @param pcId
	 * @param goodsId
	 * @param limit
	 * @return
	 */
	@RequestMapping("/recommend/recopick/item")
	RecommendResult recopickItemBasedRecommendResult(
			@RequestParam("serviceId") String serviceId, 
			@RequestParam("pcId") String pcId, 
			@RequestParam("goodsId") String goodsId, 
			@RequestParam("limit") int limit) {

		String url = "https://api.recopick.com/v1/recommendations/item/" + serviceId + "/" + pcId + "/" + goodsId + "?limit=" + limit;
		
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try {
			RequestConfig requestConfig = RequestConfig.custom()
	                .setSocketTimeout(SOCKET_TIMEOUT)
	                .setConnectTimeout(CONNECT_TIMEOUT)
	                .setConnectionRequestTimeout(REQUEST_TIMEOUT)
	                .build();
			
			httpClient = HttpClients.createDefault();
	        HttpGet httpGet = new HttpGet(url);
	        httpGet.setConfig(requestConfig);
	        httpResponse = httpClient.execute(httpGet);
		} catch (Exception e) {
			// SocketTimeoutException
			// ConnectTimeoutException
			
			try { 
				if (httpClient != null)	httpClient.close(); 
			} catch (Exception e1) {}
			
			return new RecommendResult("connectionTimeout", e.getMessage());
		}
		
		if (httpResponse == null) {
			return new RecommendResult("error", "httpResponse is null");
		}
	        
		BufferedReader reader = null;
	    try {   
			//log.debug("GET Response Status:: " + httpResponse.getStatusLine().getStatusCode());
	
	        reader = new BufferedReader(new InputStreamReader(
	                httpResponse.getEntity().getContent()));
	
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	
	        while ((inputLine = reader.readLine()) != null) {
	            response.append(inputLine);
	        }
	        reader.close();
	
	        // print result
	        //log.debug(response.toString());
	        
	        if (httpResponse.getStatusLine().getStatusCode() != 200) {
				return new RecommendResult("error", response.toString());
			}
	        
	        if (httpClient != null) {
	        	httpClient.close();
	        }	        
	        
	        Map<String, Object> mapResult = jsonMapper.readValue(response.toString(),
	                new TypeReference<HashMap<String, Object>>() {
	                });
	        
	        return new RecommendResult("success", mapResult);
	    } catch (Exception e) {
	    	return new RecommendResult("error", e.getMessage());
	    } finally {
	    	try {
	    		if (reader != null)	reader.close();
	    		if (httpClient != null)	httpClient.close(); 
			} catch (Exception e) {}
	    }
	}
	
	/**
	 * 기준 상품에 대한 추천 상품 리스트를 배치로 적재하기 위해 추천 상품리스트를 가져온다.
	 * 
	 * @param serviceId
	 * @param pcId
	 * @param goodsId
	 * @param limit
	 * @return
	 */
	@RequestMapping("/recommend/recopick/item/batch")
	RecommendResult recopickBatchItemBasedRecommendResult(
			@RequestParam("serviceId") String serviceId, 
			@RequestParam("pcId") String pcId, 
			@RequestParam("goodsId") String goodsId, 
			@RequestParam("limit") int limit) {

		String url = "https://api.recopick.com/v1/recommendations/item/" + serviceId + "/" + pcId + "/" + goodsId + "?limit=" + limit;
		
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try {
			RequestConfig requestConfig = RequestConfig.custom()
	                .setSocketTimeout(BATCH_SOCKET_TIMEOUT)
	                .setConnectTimeout(BATCH_CONNECT_TIMEOUT)
	                .setConnectionRequestTimeout(BATCH_REQUEST_TIMEOUT)
	                .build();
			
			httpClient = HttpClients.createDefault();
	        HttpGet httpGet = new HttpGet(url);
	        httpGet.setConfig(requestConfig);
	        httpResponse = httpClient.execute(httpGet);
		} catch (Exception e) {
			// SocketTimeoutException
			// ConnectTimeoutException
			
			try { 
				if (httpClient != null)	httpClient.close(); 
			} catch (Exception e1) {}
			
			return new RecommendResult("connectionTimeout", e.getMessage());
		}
		
		if (httpResponse == null) {
			return new RecommendResult("error", "httpResponse is null");
		}
	        
		BufferedReader reader = null;
	    try {   
			//log.debug("GET Response Status:: " + httpResponse.getStatusLine().getStatusCode());
	
	        reader = new BufferedReader(new InputStreamReader(
	                httpResponse.getEntity().getContent()));
	
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	
	        while ((inputLine = reader.readLine()) != null) {
	            response.append(inputLine);
	        }
	        reader.close();
	
	        // print result
	        //log.debug(response.toString());
	        
	        if (httpResponse.getStatusLine().getStatusCode() != 200) {
				return new RecommendResult("error", response.toString());
			}
	        
	        if (httpClient != null) {
	        	httpClient.close();
	        }	        
	        
	        Map<String, Object> mapResult = jsonMapper.readValue(response.toString(),
	                new TypeReference<HashMap<String, Object>>() {
	                });
	        
	        return new RecommendResult("success", mapResult);
	    } catch (Exception e) {
	    	return new RecommendResult("error", e.getMessage());
	    } finally {
	    	try {
	    		if (reader != null)	reader.close();
	    		if (httpClient != null)	httpClient.close(); 
			} catch (Exception e) {}
	    }
	}
	
	/**
	 * 레코픽의 유저기반 추천 상품리스트를 가져온다.
	 * 테스트: http://localhost:9999/recommend/recopick/user?serviceId=1612&pcId=14561267494942991950534&mbrNo=7&limit=15
	 * http://112.106.29.71:9801/recommend/recopick/user?serviceId=1612&pcId=14561267494942991950534&mbrNo=7&limit=15
	 *  
	 * @param serviceId
	 * @param pcId
	 * @param mbrNo
	 * @param limit
	 * @return
	 */
	@RequestMapping("/recommend/recopick/user")
	RecommendResult recopickUserBasedRecommendResult(
			@RequestParam("serviceId") String serviceId, 
			@RequestParam("pcId") String pcId, 
			@RequestParam("mbrNo") String mbrNo, 
			@RequestParam("limit") int limit) {
		
		if (mbrNo == null || mbrNo.equals("null")) {
			mbrNo = "";
		}
		
		// type(optional) : 개인화 추천의 종류입니다. 아래 목록을 참고하여 원하는 값을 지정해주시기 바랍니다. (기본값은 realtime 입니다)
		// - 사용자의 최근 행동 기록을 반영한 실시간 개인화 추천 : realtime
		// - 실시간 개인화 추천을 API로 호출하는 경우 행동로그가 없는 경우 차순위 추천 선택 함께 내보낼 수 있는 추천 : realtime_api
		//String url = "https://api.recopick.com/v1/recommendations/user/" + serviceId + "/" + pcId + "?limit=" + limit + "&mid=" + mbrNo;
		
		// mid가 있는 경우에만 mid 파라미터를 url에 포함해야 한다.
		String url = "https://api.recopick.com/v1/recommendations/user/" + serviceId + "/" + pcId + "?limit=" + limit + "&type=realtime_api";
		if (mbrNo != null && !"".equals(mbrNo)) {
			url = "https://api.recopick.com/v1/recommendations/user/" + serviceId + "/" + pcId + "?limit=" + limit + "&mid=" + mbrNo + "&type=realtime_api";
		}
		
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try {
			RequestConfig requestConfig = RequestConfig.custom()
	                .setSocketTimeout(SOCKET_TIMEOUT)
	                .setConnectTimeout(CONNECT_TIMEOUT)
	                .setConnectionRequestTimeout(REQUEST_TIMEOUT)
	                .build();
			
			httpClient = HttpClients.createDefault();
	        HttpGet httpGet = new HttpGet(url);
	        httpGet.setConfig(requestConfig);
	        httpResponse = httpClient.execute(httpGet);
		} catch (Exception e) {
			// SocketTimeoutException
			// ConnectTimeoutException
			
			try { 
				if (httpClient != null)	httpClient.close(); 
			} catch (Exception e1) {}
			
			return new RecommendResult("connectionTimeout", e.getMessage());
		}
		
		if (httpResponse == null) {
			return new RecommendResult("error", "httpResponse is null");
		}
	        
		BufferedReader reader = null;
	    try {   
			//log.debug("GET Response Status:: " + httpResponse.getStatusLine().getStatusCode());
	
	        reader = new BufferedReader(new InputStreamReader(
	                httpResponse.getEntity().getContent()));
	
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	
	        while ((inputLine = reader.readLine()) != null) {
	            response.append(inputLine);
	        }
	        reader.close();
	
	        // print result
	        //log.debug(response.toString());
	        
	        if (httpResponse.getStatusLine().getStatusCode() != 200) {
				return new RecommendResult("error", response.toString());
			}
	        
	        if (httpClient != null) {
	        	httpClient.close();
	        }

	        List<HashMap<String, Object>> goodsList = jsonMapper.readValue(response.toString(),
	                new TypeReference<List<HashMap<String, Object>>>() {
	                });
    
	        return new RecommendResult("success", goodsList);
	    } catch (Exception e) {
	    	return new RecommendResult("error", e.getMessage());
	    } finally {
	    	try {
	    		if (reader != null)	reader.close();
	    		if (httpClient != null)	httpClient.close(); 
			} catch (Exception e) {}
	    }
	}
	
	/**
	 * 레코벨의 상품기반 추천 상품리스트를 가져온다.
	 * 테스트: http://localhost:9999/recommend/recobell/item?cuid=fe39793b-5bd7-45ef-a2f7-d2d5548ecfee&size=15&goodsId=GM0015082752627
	 * http://112.106.29.71:9801/recommend/recobell/item?cuid=fe39793b-5bd7-45ef-a2f7-d2d5548ecfee&size=15&goodsId=GM0015082752627
	 * 
	 * @param cuid
	 * @param goodsId
	 * @param size
	 * @return
	 */
	@RequestMapping("/recommend/recobell/item")
	RecommendResult recobellItemBasedRecommendResult(
			@RequestParam("cuid") String cuid, 
			@RequestParam("goodsId") String goodsId, 
			@RequestParam("size") int size) {
		
		String url = "http://rb-rec-api-apne1-cf.recobell.io/rec/a002?cuid=" + cuid + "&format=json" + "&size=" + size + "&iids=" + goodsId;

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try {
			/* 
			 * TODO: 운영 배포시에는 proxy 정보 삭제할 것.
			 * 로컬에서 테스트할 경우 방화벽에 막혀 호출이 안되는 경우 아래 프록시 설정 추가)
			 */
//			HttpHost proxy = new HttpHost("71.100.103.80", 8080, "http");
			
			RequestConfig requestConfig = RequestConfig.custom()
	                .setSocketTimeout(SOCKET_TIMEOUT)
	                .setConnectTimeout(CONNECT_TIMEOUT)
	                .setConnectionRequestTimeout(REQUEST_TIMEOUT)
//	                .setProxy(proxy)	// TODO: 운영 배포시에는 proxy 정보 삭제할 것.
	                .build();
			
			httpClient = HttpClients.createDefault();
	        HttpGet httpGet = new HttpGet(url);
	        httpGet.setConfig(requestConfig);
	        httpResponse = httpClient.execute(httpGet);
		} catch (Exception e) {
			// SocketTimeoutException
			// ConnectTimeoutException
			
			try { 
				if (httpClient != null)	httpClient.close(); 
			} catch (Exception e1) {}
			
			return new RecommendResult("connectionTimeout", e.getMessage());
		}
		
		if (httpResponse == null) {
			return new RecommendResult("error", "httpResponse is null");
		}
	        
		BufferedReader reader = null;
	    try {   
			//log.debug("GET Response Status:: " + httpResponse.getStatusLine().getStatusCode());
	
	        reader = new BufferedReader(new InputStreamReader(
	                httpResponse.getEntity().getContent()));
	
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	
	        while ((inputLine = reader.readLine()) != null) {
	            response.append(inputLine);
	        }
	        reader.close();
	
	        // print result
	        //log.debug(response.toString());
	        
	        if (httpResponse.getStatusLine().getStatusCode() != 200) {
				return new RecommendResult("error", response.toString());
			}
	        
	        if (httpClient != null) {
	        	httpClient.close();
	        }
	        
	        Map<String, Object> mapResult = jsonMapper.readValue(response.toString(),
	                new TypeReference<HashMap<String, Object>>() {
	                });
	        
	        return new RecommendResult("success", mapResult);
	    } catch (Exception e) {
	    	return new RecommendResult("error", e.getMessage());
	    } finally {
	    	try {
	    		if (reader != null)	reader.close();
	    		if (httpClient != null)	httpClient.close(); 
			} catch (Exception e) {}
	    }
	}
	
	/**
	 * 레코픽의 카테고리기반 추천 상품리스트를 가져온다.
	 * 테스트: http://localhost:9999/recommend/recopick/ctgry?serviceId=1612&pcId=14561267494942991950534&type=most_interested_item_by_category&category=GM0015082752627&limit=15
	 * http://112.106.29.71:9801/recommend/recopick/ctgry?serviceId=1612&pcId=14561267494942991950534&type=most_interested_item_by_category&category=GM0015082752627&limit=15
	 * 
	 * @param serviceId
	 * @param pcId
	 * @param type
	 * @param dspCtgryNo
	 * @param limit
	 * @return
	 */
	@RequestMapping("/recommend/recopick/ctgry")
	RecommendResult recopickCtgryBasedRecommendResult(
			@RequestParam("serviceId") String serviceId, 
			@RequestParam("pcId") String pcId, 
			@RequestParam("type") String type, 
			@RequestParam("dspCtgryNo") String dspCtgryNo, 
			@RequestParam("limit") int limit) {

		String url = "https://api.recopick.com/v1/recommendations/seg/" + serviceId +"/" +pcId + "?type=" + type+ "&category=" + dspCtgryNo + "&limit=" + limit;

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try {
			RequestConfig requestConfig = RequestConfig.custom()
	                .setSocketTimeout(SOCKET_TIMEOUT)
	                .setConnectTimeout(CONNECT_TIMEOUT)
	                .setConnectionRequestTimeout(REQUEST_TIMEOUT)
	                .build();
			
			httpClient = HttpClients.createDefault();
	        HttpGet httpGet = new HttpGet(url);
	        httpGet.setConfig(requestConfig);
	        httpResponse = httpClient.execute(httpGet);
		} catch (Exception e) {
			// SocketTimeoutException
			// ConnectTimeoutException
			
			try { 
				if (httpClient != null)	httpClient.close(); 
			} catch (Exception e1) {}
			
			return new RecommendResult("connectionTimeout", e.getMessage());
		}
		
		if (httpResponse == null) {
			return new RecommendResult("error", "httpResponse is null");
		}
	        
		BufferedReader reader = null;
	    try {   
			//log.debug("GET Response Status:: " + httpResponse.getStatusLine().getStatusCode());
	
	        reader = new BufferedReader(new InputStreamReader(
	                httpResponse.getEntity().getContent()));
	
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	
	        while ((inputLine = reader.readLine()) != null) {
	            response.append(inputLine);
	        }
	        reader.close();
	
	        // print result
	        //log.debug(response.toString());
	        
	        if (httpResponse.getStatusLine().getStatusCode() != 200) {
				return new RecommendResult("error", response.toString());
			}
	        
	        if (httpClient != null) {
	        	httpClient.close();
	        }	        
	        
	        List<HashMap<String, Object>> goodsList = jsonMapper.readValue(response.toString(),
	                new TypeReference<List<HashMap<String, Object>>>() {
	                });
	        
	        return new RecommendResult("success", goodsList);
	    } catch (Exception e) {
	    	return new RecommendResult("error", e.getMessage());
	    } finally {
	    	try {
	    		if (reader != null)	reader.close();
	    		if (httpClient != null)	httpClient.close(); 
			} catch (Exception e) {}
	    }
	}

	/**
	 * 레코벨의 유저기반 추천 상품리스트를 가져온다.
	 * 테스트: http://localhost:9999/recommend/recobell/item?cuid=fe39793b-5bd7-45ef-a2f7-d2d5548ecfee&size=15&goodsId=GM0015082752627
	 * http://112.106.29.71:9801/recommend/recobell/item?cuid=fe39793b-5bd7-45ef-a2f7-d2d5548ecfee&size=15&goodsId=GM0015082752627
	 * 
	 * @param cuid
	 * @param goodsId
	 * @param size
	 * @return
	 */
	@RequestMapping("/recommend/recobell/user")
	RecommendResult recobellUserBasedRecommendResult(
			@RequestParam("cuid") String cuid, 
			@RequestParam("goodsId") String goodsId, 
			@RequestParam("size") int size) {
		
		String url = "http://rb-rec-api-apne1-cf.recobell.io/rec/a001?cuid=" + cuid + "&format=json" + "&size=" + size + "&iids=" + goodsId;
		
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try {
			/* 
			 * TODO: 운영 배포시에는 proxy 정보 삭제할 것.
			 * 로컬에서 테스트할 경우 방화벽에 막혀 호출이 안되는 경우 아래 프록시 설정 추가)
			 */
			//HttpHost proxy = new HttpHost("71.100.103.80", 8080, "http");
			
			RequestConfig requestConfig = RequestConfig.custom()
	                .setSocketTimeout(SOCKET_TIMEOUT)
	                .setConnectTimeout(CONNECT_TIMEOUT)
	                .setConnectionRequestTimeout(REQUEST_TIMEOUT)
//	                .setProxy(proxy)	// TODO: 운영 배포시에는 proxy 정보 삭제할 것.
	                .build();
			
			httpClient = HttpClients.createDefault();
	        HttpGet httpGet = new HttpGet(url);
	        httpGet.setConfig(requestConfig);
	        httpResponse = httpClient.execute(httpGet);
		} catch (Exception e) {
			// SocketTimeoutException
			// ConnectTimeoutException
			
			try { 
				if (httpClient != null)	httpClient.close(); 
			} catch (Exception e1) {}
			
			return new RecommendResult("connectionTimeout", e.getMessage());
		}
		
		if (httpResponse == null) {
			return new RecommendResult("error", "httpResponse is null");
		}
	        
		BufferedReader reader = null;
	    try {   
			//log.debug("GET Response Status:: " + httpResponse.getStatusLine().getStatusCode());
	
	        reader = new BufferedReader(new InputStreamReader(
	                httpResponse.getEntity().getContent()));
	
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	
	        while ((inputLine = reader.readLine()) != null) {
	            response.append(inputLine);
	        }
	        reader.close();
	
	        // print result
	        //log.debug(response.toString());
	        
	        if (httpResponse.getStatusLine().getStatusCode() != 200) {
				return new RecommendResult("error", response.toString());
			}
	        
	        if (httpClient != null) {
	        	httpClient.close();
	        }
	        
	        Map<String, Object> mapResult = jsonMapper.readValue(response.toString(),
	                new TypeReference<HashMap<String, Object>>() {
	                });
	        
	        return new RecommendResult("success", mapResult);
	    } catch (Exception e) {
	    	return new RecommendResult("error", e.getMessage());
	    } finally {
	    	try {
	    		if (reader != null)	reader.close();
	    		if (httpClient != null)	httpClient.close(); 
			} catch (Exception e) {}
	    }
	}

}
