/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 * beyondj2ee			2015.02.09
 */
package com.plgrim.ncp.base.abstracts;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 최상위 컨트롤러 클래스.
 */
public abstract class AbstractController extends AbstractBean {
    @InitBinder
    public void bindingPreparation(WebDataBinder binder) {
//        binder.registerCustomEditor(PropertyEditorSupport.class, new PropertyEditorSupport());
//	  DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//	  CustomDateEditor defaultDateEditor = new CustomDateEditor(dateFormat1, true);
//
//	  DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//	  CustomDateEditor anotherDateEditor = new CustomDateEditor(dateFormat2, true);

        //binder.registerCustomEditor(Date.class, defaultDateEditor);
        //binder.registerCustomEditor(Date.class, "timedate", anotherDateEditor);
    	binder.setAutoGrowCollectionLimit(2048);
    }

}
