package com.plgrim.ncp.biz.order.data;

import java.io.IOException;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class NaverPayOpenDTO extends AbstractDTO {

	String merchantUserKey;
	String merchantPayKey;
	String productName;
	Long totalPayAmount;
	Long taxScopeAmount;
	Long taxExScopeAmount;
	String returnUrl;
	String clientId;
	String mode;
	
	

    public static <T> T fromJSON(String jsonString, Class<T> type){
        ObjectMapper objectMapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);;

        try {
        	return objectMapper.readValue(jsonString, type);
        }catch(IOException ie) {
        	throw new RuntimeException(ie.getMessage());
        }
    }
	
	public String toJSON() {
		ObjectMapper objectMapper = new ObjectMapper();
        String jsonInString;
        try {
            jsonInString = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.warn("error occured during parse to json:" + e.getMessage(), e);
            return "{"+ToStringBuilder.reflectionToString(this)+"}";
        }

        return jsonInString;
	}
}
