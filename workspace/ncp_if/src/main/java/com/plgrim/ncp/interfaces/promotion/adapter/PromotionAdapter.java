package com.plgrim.ncp.interfaces.promotion.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.framework.commons.ConfigService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.interfaces.abstracts.AbstractAdapter;
import com.plgrim.ncp.interfaces.abstracts.InterfaceSDO;
import com.plgrim.ncp.interfaces.promotion.data.OnOffCouponIssueSDO;
import com.plgrim.ncp.interfaces.promotion.data.OnOffCouponUseSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import com.plgrim.ncp.interfaces.util.InterfaceConstants;

import lombok.extern.slf4j.Slf4j;


/**
 * 프로모션 IF adapter
 *
 * <p>
 *
 * <ul>
 * <li> [기능1]
 * <li> [기능2]
 * </ul>.
 *
 * @author tktaeki.kim
 */
@Service
@Slf4j
public class PromotionAdapter extends AbstractAdapter{


    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    @Autowired
    private InterfaceApiCommon interfaceApiCommon;

	@Autowired
	private ConfigService configService;
    
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
     * 온오프라인쿠폰 발행정보 수신
     * 
     * @param stdDt
     * @param brand
     * @param adapterHeader
     * @return
     */
    public OnOffCouponIssueSDO getOnOffCouponIssueList(String stdDt, String brand, String cid, final AdapterHeader adapterHeader) {
    	
        String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.PROMOTION_GET_ONOFFCOUPON_ISSUELIST;
        OnOffCouponIssueSDO sdo = new OnOffCouponIssueSDO();
        
        if(StringService.isNotEmpty(stdDt)) {
        	sdo.setStdDt(stdDt);
        }
        sdo.setBrand(brand);
        if(StringService.isNotEmpty(cid)) {
        	sdo.setCid(cid);
        }
        
        OnOffCouponIssueSDO result = (OnOffCouponIssueSDO)this.sendInterface(sdo, adapterHeader, serverUrl, OnOffCouponIssueSDO.class);
        return result;
    }
    
    /**
     * 온오프라인 검증
     * 
     * @param cid
     * @param issueNo
     * @param adapterHeader
     * @return
     */
    public OnOffCouponUseSDO validOnOffCoupon(String cid, String issueNo, final AdapterHeader adapterHeader) {
    	
    	String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.PROMOTION_VALID_ONOFFCOUPON_USE;
    	OnOffCouponUseSDO sdo = new OnOffCouponUseSDO();
    	
    	sdo.setCid(cid);
    	sdo.setIssueNo(issueNo);
    	
    	OnOffCouponUseSDO result = (OnOffCouponUseSDO) this.sendInterface(sdo, adapterHeader, serverUrl, OnOffCouponUseSDO.class);
    	return result; 
    }
    
    /**
     * 온오프라인 사용
     * 
     * @param cid
     * @param issueNo
     * @param adapterHeader
     * @return
     */
    public InterfaceSDO useOnOffCoupon(String cid, String issueNo, String ordNo, final AdapterHeader adapterHeader) {
    	
    	String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.PROMOTION_USE_ONOFFCOUPON;
    	OnOffCouponUseSDO sdo = new OnOffCouponUseSDO();
    	
    	sdo.setCid(cid);
    	sdo.setIssueNo(issueNo);
    	sdo.setOrdNo(ordNo);
    	sdo.setShopcode(configService.getProperty("ncp_base.shop.code.online"));
    	
    	InterfaceSDO result = (InterfaceSDO) this.sendInterface(sdo, adapterHeader, serverUrl, InterfaceSDO.class);
    	return result; 
    }
    
    /**
     * 온오프라인 사용
     * 
     * @param onOffCouponUseSDO
     * @param adapterHeader
     * @return
     */
    public InterfaceSDO useOnOffCoupon(OnOffCouponUseSDO onOffCouponUseSDO, final AdapterHeader adapterHeader) {    	
    	String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.PROMOTION_USE_ONOFFCOUPON;    	
    	InterfaceSDO result = (InterfaceSDO) this.sendInterface(onOffCouponUseSDO, adapterHeader, serverUrl, InterfaceSDO.class);
    	return result; 
    }    
    
    
    /**
     * 온오프라인 복원
     * 
     * @param cid
     * @param issueNo
     * @param adapterHeader
     * @return
     */
    public InterfaceSDO restoreOnOffCoupon(String cid, String issueNo, String ordNo, String clmNo, final AdapterHeader adapterHeader) {
    	
    	String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.PROMOTION_RESTORE_ONOFFCOUPON;
    	OnOffCouponUseSDO sdo = new OnOffCouponUseSDO();
    	
    	sdo.setCid(cid);
    	sdo.setIssueNo(issueNo);
    	sdo.setOrdNo(ordNo);
    	sdo.setClmNo(clmNo);
    	sdo.setShopcode(configService.getProperty("ncp_base.shop.code.online"));
    	
    	InterfaceSDO result = (InterfaceSDO) this.sendInterface(sdo, adapterHeader, serverUrl, InterfaceSDO.class);
    	return result; 
    }
    
    /**
     * 온오프라인 복원
     * 
     * @param onOffCouponUseSDO
     * @param adapterHeader
     * @return
     */
    public InterfaceSDO restoreOnOffCoupon(OnOffCouponUseSDO onOffCouponUseSDO, final AdapterHeader adapterHeader) {    	
    	String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.PROMOTION_RESTORE_ONOFFCOUPON;  
    	InterfaceSDO result = (InterfaceSDO) this.sendInterface(onOffCouponUseSDO, adapterHeader, serverUrl, InterfaceSDO.class);
    	return result; 
    }    
    
    /**
     * 온오프라인쿠폰 쿠폰 발급 및 발급가능 검증
     * 
     * @param brand
     * @param cid
     * @param issueno
     * @param requestTypeCd
     * @param adapterHeader
     * @return OnOffCouponIssueSDO
     */
    public OnOffCouponIssueSDO issueOnOffCoupon(String brand, String cid, String issueno, String requestTypeCd, final AdapterHeader adapterHeader) {
    	
        String serverUrl = interfaceApiCommon.getInternalInterfaceServerUrl() + InterfaceConstants.PROMOTION_ISSUE_ONOFFCOUPON;
        OnOffCouponIssueSDO sdo = new OnOffCouponIssueSDO();
        
        sdo.setBrand(brand);
       	sdo.setCid(cid);
       	sdo.setIssueno(issueno);
        sdo.setRequestTypeCd(requestTypeCd);

        OnOffCouponIssueSDO result = (OnOffCouponIssueSDO)this.sendInterface(sdo, adapterHeader, serverUrl, OnOffCouponIssueSDO.class);
        return result;
    }
}
