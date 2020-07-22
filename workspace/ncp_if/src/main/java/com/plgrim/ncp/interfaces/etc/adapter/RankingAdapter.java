package com.plgrim.ncp.interfaces.etc.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.interfaces.abstracts.AbstractAdapter;
import com.plgrim.ncp.interfaces.etc.data.CategoryRankingListSDO;
import com.plgrim.ncp.interfaces.etc.data.CategoryRankingSDO;
import com.plgrim.ncp.interfaces.etc.data.RankingListSDO;
import com.plgrim.ncp.interfaces.etc.data.RankingSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import com.plgrim.ncp.interfaces.util.InterfaceConstants;

@Service
public class RankingAdapter extends AbstractAdapter {
	/*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    @Autowired
    private InterfaceApiCommon interfaceApiCommon;

    /*
     * ---------------------------------------------------------------------
     * Constructors.
     * ---------------------------------------------------------------------
     */

    /*
     * ---------------------------------------------------------------------
     * public & interface method.
     * ---------------------------------------------------------------------
     */
    
    /**
     *  국가별 랭킹데이터를 요청한다.
     * 
     * @param sdo
     * @param adapterHeader
     * @return
     */
    public RankingListSDO getRankingOfCountry(RankingSDO sdo, final AdapterHeader adapterHeader) {
    	String inputJson = JsonService.marshalling(sdo);
    	String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.ETC_RANKING_OF_COUNTRY;
    	return this.sendInterface(inputJson, adapterHeader, serverUrl, RankingListSDO.class); 
    }
    
    /**
     *  카테고리별 랭킹데이터를 요청한다.
     * 
     * @param sdo
     * @param adapterHeader
     * @return
     */
    public CategoryRankingListSDO getRankingOfCategory(CategoryRankingSDO sdo, final AdapterHeader adapterHeader) {
    	String inputJson = JsonService.marshalling(sdo);
    	String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.ETC_RANKING_OF_CATEGORY;
    	return this.sendInterface(inputJson, adapterHeader, serverUrl, CategoryRankingListSDO.class); 
    }
}
