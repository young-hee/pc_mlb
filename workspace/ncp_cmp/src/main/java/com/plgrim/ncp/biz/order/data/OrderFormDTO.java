package com.plgrim.ncp.biz.order.data;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopExtends;
import com.plgrim.ncp.biz.cart.result.CartGiftResult;
import com.plgrim.ncp.biz.cart.result.CartGodPrmResult;
import com.plgrim.ncp.biz.cart.result.CartResult;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderFormDTO extends AbstractDTO {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -7700364403685499711L;

	List<CartResult> cartResultList;

	// List<OrderGiftDTO> orderGiftList;
	List<CartGiftResult> ordGiftPrmList;

	List<Prm> cardBenefitList;

	// default 배송지
	MbrDlvspExtend mbrDlvsp;

	// 픽업매장 정보
	SysShopExtends sysShop;

	String searchDelv;
	
	//배송비즉시할인쿠폰 : by cannon (2016.06.07)
	List<CartGodPrmResult> dlvFeeImdtlCpnResultList;
	
	//선호결제수단
	String mbrPreferPayMn;

	public static <T> T fromJSON(String jsonString, Class<T> type){
        ObjectMapper objectMapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

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
