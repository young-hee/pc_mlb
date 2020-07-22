package com.plgrim.ncp.cmp.vendor.bo.photographer;

import com.plgrim.ncp.cmp.vendor.bo.common.VendorBOComponentImpl;
import com.plgrim.ncp.cmp.vendor.bo.VendorPhotographerBOComponent;
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
public class VendorPhotographerBOComponentImpl extends VendorBOComponentImpl implements VendorPhotographerBOComponent {

}
