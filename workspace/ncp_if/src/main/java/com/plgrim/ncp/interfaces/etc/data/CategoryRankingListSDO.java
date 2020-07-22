package com.plgrim.ncp.interfaces.etc.data;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value=Include.NON_EMPTY)
@Alias("categoryRankingListSDO")
public class CategoryRankingListSDO extends InterfaceSDO {

    private static final long serialVersionUID = 1L;
    
    @JsonProperty("RESPONSE_DATA")
	private List<CategoryRankingSDO> categoryRankingList;
    
    @JsonSetter("RESPONSE_DATA")
    public void setResponseData(List<CategoryRankingSDO> data)
    {
    		this.categoryRankingList = data;
    }
    
    private String regtrId;
    
    private String category;
}