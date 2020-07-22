/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      syvictor.kim 
 * @since       2015. 4. 3       
 */
package com.plgrim.ncp.web.pc.mlb.order;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plgrim.ncp.biz.order.data.OrderDTO;
import com.plgrim.ncp.cmp.orderfulfilment.fo.order.OrderCommandComponent;
import com.plgrim.ncp.cmp.orderfulfilment.fo.sale.SaleComponent;
import com.plgrim.ncp.commons.data.order.KcpCommonReceiveDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OrderVirtualAccountController extends OrderController {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	@Qualifier("orderCommandComponentImpl")
	protected OrderCommandComponent orderCommandComponent;

	@Autowired
	protected SaleComponent saleComponent;


    @RequestMapping(value = "/common/get")
	public String setVirtualAccountDeposit(@ModelAttribute("kcpCommonReceiveDTO") KcpCommonReceiveDTO kcpCommonReceiveDTO,HttpServletRequest request, Model model) throws Exception {
    	log.info("kcp first get param {}",kcpCommonReceiveDTO.toJSON());
    	
        /* ============================================================================== */
        /* =   PAGE : 공통 통보 PAGE                                                    = */
        /* = -------------------------------------------------------------------------- = */
        /* =   연동시 오류가 발생하는 경우 아래의 주소로 접속하셔서 확인하시기 바랍니다.= */
        /* =   접속 주소 : http://kcp.co.kr/technique.requestcode.do			        = */
        /* = -------------------------------------------------------------------------- = */
        /* =   Copyright (c)  2016   NHN KCP Inc.   All Rights Reserverd.               = */
        /* ============================================================================== */
    	
        /* ============================================================================== */
        /* =   01. 공통 통보 페이지 설명(필독!!)                                        = */
        /* = -------------------------------------------------------------------------- = */
        /* =   공통 통보 페이지에서는,                                                  = */
        /* =   가상계좌 입금 통보 데이터와 모바일안심결제 통보 데이터 등을 KCP를 통해   = */
        /* =   실시간으로 통보 받을 수 있습니다.                                        = */
        /* =                                                                            = */
        /* =   common_return 페이지는 이러한 통보 데이터를 받기 위한 샘플 페이지        = */
        /* =   입니다. 현재의 페이지를 업체에 맞게 수정하신 후, 아래 사항을 참고하셔서  = */
        /* =   KCP 관리자 페이지에 등록해 주시기 바랍니다.                              = */
        /* =                                                                            = */
        /* =   등록 방법은 다음과 같습니다.                                             = */
        /* =  - KCP 관리자페이지(admin.kcp.co.kr)에 로그인 합니다.                      = */
        /* =  - [쇼핑몰 관리] -> [정보변경] -> [공통 URL 정보] -> 공통 URL 변경 후에    = */
        /* =    가맹점 URL을 입력합니다.                                                = */
        /* ============================================================================== */
    	
		String remoteHost = request.getRemoteHost();
		String remoteAddr = request.getRemoteAddr();
		try{
		    /* = -------------------------------------------------------------------------- = */
		    /* =   02-1. 가상계좌 입금 통보 데이터 받기                                     = */
		    /* = -------------------------------------------------------------------------- = */
			if ( "TX00".equals(kcpCommonReceiveDTO.getTx_cd()) ) {
				OrderDTO orderDTO = new OrderDTO();
				String resultCode = orderCommandComponent.setVirtualAccountDeposit(kcpCommonReceiveDTO,orderDTO, remoteHost, remoteAddr);
				if("0000".equals(resultCode)){
					orderCommandComponent.sendVirOrderMail(orderDTO, request);
				}
				model.addAttribute("resultCode", resultCode);
				
			}
		    /* = -------------------------------------------------------------------------- = */
		    /* =   02-2. 가상계좌 환불 통보 데이터 받기                                      = */
		    /* = -------------------------------------------------------------------------- = */
		    else if ( ("TX01").equals(kcpCommonReceiveDTO.getTx_cd()) )
		    {
//		        refund_nm  = f_get_parm( request.getParameter( "refund_nm"  ) );         // 환불계좌주명
//		        refund_mny = f_get_parm( request.getParameter( "refund_mny" ) );         // 환불금액
//		        bank_code  = f_get_parm( request.getParameter( "bank_code"  ) );         // 은행코드
		    }
		    /* = -------------------------------------------------------------------------- = */
		    /* =   02-3. 구매확인/구매취소 통보 데이터 받기                                 = */
		    /* = -------------------------------------------------------------------------- = */
		    else if ( ("TX02").equals(kcpCommonReceiveDTO.getTx_cd()) )
		    {
//		        st_cd = f_get_parm( request.getParameter( "st_cd" ) );                   // 구매확인 코드
//
//		        if ( st_cd.equals("N") )                                                 // 구매확인 상태가 구매취소인 경우
//		        {
//		            can_msg = f_get_parm( request.getParameter( "can_msg" ) );           // 구매취소 사유
//		        }
		    }
		    /* = -------------------------------------------------------------------------- = */
		    /* =   02-4. 배송시작 통보 데이터 받기                                          = */
		    /* = -------------------------------------------------------------------------- = */
		    else if ( ("TX03").equals(kcpCommonReceiveDTO.getTx_cd()) )
		    {
//		        waybill_no   = f_get_parm( request.getParameter( "waybill_no"   ) );     // 운송장 번호
//		        waybill_corp = f_get_parm( request.getParameter( "waybill_corp" ) );     // 택배 업체명
		    }
		    /* ============================================================================== */

		    /* ============================================================================== */
		    /* =   03. 공통 통보 결과를 업체 자체적으로 DB 처리 작업하시는 부분입니다.      = */
		    /* = -------------------------------------------------------------------------- = */
		    /* =   통보 결과를 DB 작업 하는 과정에서 정상적으로 통보된 건에 대해 DB 작업을  = */
		    /* =   실패하여 DB update 가 완료되지 않은 경우, 결과를 재통보 받을 수 있는     = */
		    /* =   프로세스가 구성되어 있습니다. 소스에서 result 라는 Form 값을 생성 하신   = */
		    /* =   후, DB 작업이 성공 한 경우, result 의 값을 "0000" 로 세팅해 주시고,      = */
		    /* =   DB 작업이 실패 한 경우, result 의 값을 "0000" 이외의 값으로 세팅해 주시  = */
		    /* =   기 바랍니다. result 값이 "0000" 이 아닌 경우에는 재통보를 받게 됩니다.   = */
		    /* = -------------------------------------------------------------------------- = */

		    /* = -------------------------------------------------------------------------- = */
		    /* =   03-1. 가상계좌 입금 통보 데이터 DB 처리 작업 부분                        = */
		    /* = -------------------------------------------------------------------------- = */
		    if ( ("TX00").equals(kcpCommonReceiveDTO.getTx_cd()) )
		    {
		    }
		    /* = -------------------------------------------------------------------------- = */
		    /* =   03-2. 가상계좌 환불 통보 데이터 DB 처리 작업 부분                        = */
		    /* = -------------------------------------------------------------------------- = */
		    else if ( ("TX01").equals(kcpCommonReceiveDTO.getTx_cd()) )
		    {
		    }
		    /* = -------------------------------------------------------------------------- = */
		    /* =   03-3. 구매확인/구매취소 통보 데이터 DB 처리 작업 부분                    = */
		    /* = -------------------------------------------------------------------------- = */
		    else if ( ("TX02").equals(kcpCommonReceiveDTO.getTx_cd()) )
		    {
		    	model.addAttribute("resultCode", saleComponent.saleKcpConfirmReceive(kcpCommonReceiveDTO));
		    }
		    /* = -------------------------------------------------------------------------- = */
		    /* =   03-4. 배송시작 통보 데이터 DB 처리 작업 부분                             = */
		    /* = -------------------------------------------------------------------------- = */
		    else if ( ("TX03").equals(kcpCommonReceiveDTO.getTx_cd()) )
		    {
		    }
		    /* = -------------------------------------------------------------------------- = */
		    /* =   03-5. 정산보류 통보 데이터 DB 처리 작업 부분                             = */
		    /* = -------------------------------------------------------------------------- = */
		    else if ( ("TX04").equals(kcpCommonReceiveDTO.getTx_cd()) )
		    {
		    }
		    /* = -------------------------------------------------------------------------- = */
		    /* =   03-6. 즉시취소 통보 데이터 DB 처리 작업 부분                             = */
		    /* = -------------------------------------------------------------------------- = */
		    else if ( ("TX05").equals(kcpCommonReceiveDTO.getTx_cd()) )
		    {
		    }
		    /* = -------------------------------------------------------------------------- = */
		    /* =   03-7. 취소 통보 데이터 DB 처리 작업 부분                                 = */
		    /* = -------------------------------------------------------------------------- = */
		    else if ( ("TX06").equals(kcpCommonReceiveDTO.getTx_cd()) )
		    {
		    }
		    /* = -------------------------------------------------------------------------- = */
		    /* =   03-8. 발급계좌해지 통보 데이터 DB 처리 작업 부분                         = */
		    /* = -------------------------------------------------------------------------- = */
		    else if ( ("TX07").equals(kcpCommonReceiveDTO.getTx_cd()) )
		    {
		    }
		    /* = -------------------------------------------------------------------------- = */
		    /* =   03-9. 모바일계좌이체 통보 데이터 DB 처리 작업 부분                       = */
		    /* = -------------------------------------------------------------------------- = */
		    else if ( ("TX08").equals(kcpCommonReceiveDTO.getTx_cd()) )
		    {
		    }else{
				model.addAttribute("resultCode", "-1");
			}
		    /* ============================================================================== */

		    /* ============================================================================== */
		    /* =   04. result 값 세팅 하기                                                  = */
		    /* ============================================================================== */
		    
		}catch(Exception e){
			log.error(e.getMessage(),e);
			model.addAttribute("resultCode", "-99");
		}
		return "tiles:order/kcpOrderCommonReturn";
	}
}
