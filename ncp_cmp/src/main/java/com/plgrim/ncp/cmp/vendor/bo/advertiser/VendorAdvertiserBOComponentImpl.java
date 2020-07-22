package com.plgrim.ncp.cmp.vendor.bo.advertiser;

import com.plgrim.ncp.cmp.vendor.bo.VendorAdvertiserBOComponent;
import com.plgrim.ncp.cmp.vendor.bo.common.VendorBOComponentImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * [클래스 설명]
 *
 * <p>
 *
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author yoon.eun
 * @since 2015. 11. 10
 */
@Component
@Transactional(value = "transactionManager")
public class VendorAdvertiserBOComponentImpl extends VendorBOComponentImpl implements VendorAdvertiserBOComponent {


}
