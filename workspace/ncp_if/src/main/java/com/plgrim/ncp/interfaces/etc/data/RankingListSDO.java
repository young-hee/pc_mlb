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
@Alias("rankingListSDO")
public class RankingListSDO extends InterfaceSDO {

    private static final long serialVersionUID = 1L;
    
    @JsonProperty("RESPONSE_DATA")
	private List<RankingSDO> rankingList;
    
    @JsonSetter("RESPONSE_DATA")
    public void setResponseData(List<RankingSDO> data)
    {
    		this.rankingList = data;
    }
    
    private String regtrId;
}