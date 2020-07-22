package com.plgrim.ncp.commons.service;

import java.net.InetAddress;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kcp.J_PP_CLI_N;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.enums.OrderClaimEnum;
import com.plgrim.ncp.base.enums.OrderEnum;
import com.plgrim.ncp.commons.data.order.KcpCancelParamDTO;
import com.plgrim.ncp.commons.data.order.KcpCommonReceiveDTO;
import com.plgrim.ncp.commons.data.order.KcpParamDTO;
import com.plgrim.ncp.commons.data.order.KcpReturnDTO;
import com.plgrim.ncp.framework.commons.ContextService;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * <p>
 * 
 * <ul>
 * <li>[기능1]
 * <li>[기능2]
 * </ul>.
 *
 * @author syvictor.kim
 * @since 2015. 5. 6
 */
@Slf4j
@Service
public class KcpPayService extends AbstractService {
    
	public KcpReturnDTO approve(KcpParamDTO kcpParamDTO, Long realTotoalAmount, String mallId, boolean vip ) {
		
		KcpReturnDTO kcpReturnDTO = new KcpReturnDTO();
		/* ============================================================================== */
	    /* =   02. 인스턴스 생성 및 초기화(변경 불가)                                   = */
	    /* = -------------------------------------------------------------------------- = */
	    /* =   결제에 필요한 인스턴스를 생성하고 초기화 합니다.                         = */
	    /* =   ※ 주의 ※ 이 부분은 변경하지 마십시오                                   = */
	    /* = -------------------------------------------------------------------------- = */
	    J_PP_CLI_N c_PayPlus = new J_PP_CLI_N();

	    //c_PayPlus.mf_init( "", g_conf_gw_url, g_conf_gw_port, g_conf_tx_mode, g_conf_log_dir );
	    c_PayPlus.mf_init( "", getConfigService().getProperty("ncp_base.order.kcp.gw.url")
	    		, getConfigService().getProperty("ncp_base.order.kcp.gw.port")
	    		, Integer.valueOf(getConfigService().getProperty("ncp_base.order.kcp.tx.mode"))
	    		, getConfigService().getProperty("ncp_base.order.kcp.log.dir") ); // 설정행
	    c_PayPlus.mf_init_set();   

	    /* = -------------------------------------------------------------------------- = */
	    /* =   02. 인스턴스 생성 및 초기화 END                                          = */
	    /* ============================================================================== */


	    /* ============================================================================== */
	    /* =   03. 처리 요청 정보 설정                                                  = */
	    /* = -------------------------------------------------------------------------- = */
	    /* = -------------------------------------------------------------------------- = */
	    /* =   03-1. 승인 요청 정보 설정                                                = */
	    /* = -------------------------------------------------------------------------- = */

	    if ( ("pay").equals(kcpParamDTO.getReq_tx()) )
	    {
	            c_PayPlus.mf_set_enc_data( kcpParamDTO.getEnc_data() ,
	            		kcpParamDTO.getEnc_info() );

	            int ordr_data_set_no;

	            ordr_data_set_no = c_PayPlus.mf_add_set( "ordr_data" );

	            c_PayPlus.mf_set_us( ordr_data_set_no, "ordr_mony", realTotoalAmount.toString() );      // 결제금액 검증           
	            
	    }
	    
	    
	    /* ============================================================================== */
	    /* =   04. 실행                                                                 = */
	    /* = -------------------------------------------------------------------------- = */
	    //mall 분리
	    String siteCd = getConfigService().getProperty("ncp_base.order.kcp.site.cd");
	    String siteKey = getConfigService().getProperty("ncp_base.order.kcp.site.key");
	    if("MBM".equals(mallId)) {
	    	siteCd = getConfigService().getProperty("ncp_base.order.kcp.mlb.site.cd");
	    	siteKey = getConfigService().getProperty("ncp_base.order.kcp.mlb.site.key");
	    	if(vip){
		    	siteCd = getConfigService().getProperty("ncp_base.order.kcp.mlb.site.vip.cd");
		    	siteKey = getConfigService().getProperty("ncp_base.order.kcp.mlb.site.vip.key");
	    	}
	    	
	    } else if("SAM".equals(mallId)) {
	    	siteCd = getConfigService().getProperty("ncp_base.order.kcp.sa.site.cd");
	    	siteKey = getConfigService().getProperty("ncp_base.order.kcp.sa.site.key");
	    }
	    
	    if ( kcpParamDTO.getTran_cd().length() > 0 )
	    {
	        //c_PayPlus.mf_do_tx( g_conf_site_cd, g_conf_site_key, tran_cd, "", ordr_idxx, g_conf_log_level, "0" );
	    	c_PayPlus.mf_do_tx( siteCd
	    			, siteKey
	    			, kcpParamDTO.getTran_cd()
	    			, ""
	    			, kcpParamDTO.getOrdr_idxx()
	    			, getConfigService().getProperty("ncp_base.order.kcp.log.level")
	    			, "0" ); // 설정행
	    	

	    	kcpReturnDTO.setResCd(c_PayPlus.m_res_cd);  // 결과 코드
	    	kcpReturnDTO.setResMsg(c_PayPlus.m_res_msg); // 결과 메시지
	    }
	    else
	    {
	        c_PayPlus.m_res_cd  = "9562";
	        c_PayPlus.m_res_msg = "연동 오류| tran_cd값이 설정되지 않았습니다.";
	    }

	    log.debug("res_cd {}",  kcpReturnDTO.getResCd());
	    log.debug("res_msg {}",  kcpReturnDTO.getResMsg());
	    
	    log.debug("c_PayPlus {}",  c_PayPlus);
	    
	    /* = -------------------------------------------------------------------------- = */
	    /* =   04. 실행 END                                                             = */
	    /* ============================================================================== */

	    /* ============================================================================== */
	    /* =   06. 승인 결과 값 추출                                                    = */
	    /* = -------------------------------------------------------------------------- = */
	    if ( ("pay").equals(kcpParamDTO.getReq_tx()) )
	    {
	        if ( ( "0000" ).equals(kcpReturnDTO.getResCd()) )
	        {
	        	kcpReturnDTO.setTno(c_PayPlus.mf_get_res( "tno"    )); // KCP 거래 고유 번호
	        	kcpReturnDTO.setAmount(c_PayPlus.mf_get_res( "amount" )); // KCP 실제 거래 금액
	        	kcpReturnDTO.setPntIssue(c_PayPlus.mf_get_res( "pnt_issue" )); // 결제 포인트사 코드
	        	kcpReturnDTO.setCouponMny(c_PayPlus.mf_get_res( "coupon_mny" )); // 쿠폰금액

	    /* = -------------------------------------------------------------------------- = */
	    /* =   06-1. 신용카드 승인 결과 처리                                            = */
	    /* = -------------------------------------------------------------------------- = */
	            if ( OrderEnum.kcpPayComp.CREDT_CARD_PAY.getKcpValue().equals(kcpParamDTO.getUse_pay_method()) ){
	            	kcpReturnDTO.setCardCd(c_PayPlus.mf_get_res( "card_cd"   )); // 카드사 코드
	            	kcpReturnDTO.setCardName(c_PayPlus.mf_get_res( "card_name" )); // 카드사 명
	            	kcpReturnDTO.setAppTime(c_PayPlus.mf_get_res( "app_time"  )); // 승인시간
	            	kcpReturnDTO.setAppNo(c_PayPlus.mf_get_res( "app_no"    )); // 승인번호
	            	kcpReturnDTO.setNoinf(c_PayPlus.mf_get_res( "noinf"     )); // 무이자 여부
	            	kcpReturnDTO.setQuota(c_PayPlus.mf_get_res( "quota"     )); // 할부 개월 수
	            	kcpReturnDTO.setPartcancYn(c_PayPlus.mf_get_res( "partcanc_yn" )); // 부분취소 가능유무
	            	kcpReturnDTO.setCardBinType_01(c_PayPlus.mf_get_res( "card_bin_type_01" )); // 카드구분1
	            	kcpReturnDTO.setCardBinType_02(c_PayPlus.mf_get_res( "card_bin_type_02" )); // 카드구분2
	            	kcpReturnDTO.setCardMny(c_PayPlus.mf_get_res( "card_mny" )); // 카드결제금액

	                /* = -------------------------------------------------------------- = */
	                /* =   06-1.1. 복합결제(포인트+신용카드) 승인 결과 처리             = */
	                /* = -------------------------------------------------------------- = */
	                if ( ( "SCSK" ).equals(kcpReturnDTO.getPntIssue()) || ( "SCWB" ).equals(kcpReturnDTO.getPntIssue()) )
	                {
	                	kcpReturnDTO.setPntAmount(c_PayPlus.mf_get_res( "pnt_amount"   )); // 적립금액 or 사용금액
	                	kcpReturnDTO.setPntAppTime(c_PayPlus.mf_get_res( "pnt_app_time" )); // 승인시간
	                	kcpReturnDTO.setPntAppNo(c_PayPlus.mf_get_res( "pnt_app_no"   )); // 승인번호
	                	kcpReturnDTO.setAddPnt(c_PayPlus.mf_get_res( "add_pnt"      )); // 발생 포인트
	                	kcpReturnDTO.setUsePnt(c_PayPlus.mf_get_res( "use_pnt"      )); // 사용가능 포인트
	                	kcpReturnDTO.setRsvPnt(c_PayPlus.mf_get_res( "rsv_pnt"      )); // 총 누적 포인트
	                	kcpReturnDTO.setTotal_amount(Long.valueOf(kcpReturnDTO.getAmount())+Long.valueOf(kcpReturnDTO.getPntAmount()));                    // 복합결제시 총 거래금액
	                }
	            }

	    /* = -------------------------------------------------------------------------- = */
	    /* =   06-2. 계좌이체 승인 결과 처리                                            = */
	    /* = -------------------------------------------------------------------------- = */
	            if ( OrderEnum.kcpPayComp.RLTM_BNK_ACCT_PAY.getKcpValue().equals(kcpParamDTO.getUse_pay_method()) ){
	            	kcpReturnDTO.setAppTime(c_PayPlus.mf_get_res( "app_time"  )); // 승인시간
	            	kcpReturnDTO.setBankName(c_PayPlus.mf_get_res( "bank_name" )); // 은행명
	            	kcpReturnDTO.setBankCode(c_PayPlus.mf_get_res( "bank_code" )); // 은행코드
	            	kcpReturnDTO.setBkMny(c_PayPlus.mf_get_res( "bk_mny"    )); // 계좌이체결제금액
	            }

	    /* = -------------------------------------------------------------------------- = */
	    /* =   06-3. 가상계좌 승인 결과 처리                                            = */
	    /* = -------------------------------------------------------------------------- = */
	            if ( OrderEnum.kcpPayComp.VIRTL_BNK_ACCT_PAY.getKcpValue().equals(kcpParamDTO.getUse_pay_method()) ){
	            	kcpReturnDTO.setBankName(c_PayPlus.mf_get_res( "bankname"  )); // 입금할 은행 이름
	            	kcpReturnDTO.setDepositor(c_PayPlus.mf_get_res( "depositor" )); // 입금할 계좌 예금주
	            	kcpReturnDTO.setAccount(c_PayPlus.mf_get_res( "account"   )); // 입금할 계좌 번호
	            	kcpReturnDTO.setVaDate(c_PayPlus.mf_get_res( "va_date"   )); // 가상계좌 입금마감시간
	            }

	    /* = -------------------------------------------------------------------------- = */
	    /* =   06-4. 포인트 승인 결과 처리                                              = */
	    /* = -------------------------------------------------------------------------- = */
	            
//	                pnt_amount   = c_PayPlus.mf_get_res( "pnt_amount"   ); // 적립금액 or 사용금액
//	                pnt_app_time = c_PayPlus.mf_get_res( "pnt_app_time" ); // 승인시간
//	                pnt_app_no   = c_PayPlus.mf_get_res( "pnt_app_no"   ); // 승인번호
//	                add_pnt      = c_PayPlus.mf_get_res( "add_pnt"      ); // 발생 포인트
//	                use_pnt      = c_PayPlus.mf_get_res( "use_pnt"      ); // 사용가능 포인트
//	                rsv_pnt      = c_PayPlus.mf_get_res( "rsv_pnt"      ); // 총 누적 포인트
	            

	    /* = -------------------------------------------------------------------------- = */
	    /* =   06-5. 휴대폰 승인 결과 처리                                              = */
	    /* = -------------------------------------------------------------------------- = */
	            if ( OrderEnum.kcpPayComp.MOBIL_PON_PAY.getKcpValue().equals(kcpParamDTO.getUse_pay_method()) ){
	            	kcpReturnDTO.setAppTime(c_PayPlus.mf_get_res( "hp_app_time" )); // 승인 시간
	            	kcpReturnDTO.setCommid(c_PayPlus.mf_get_res( "commid"      )); // 통신사 코드
	            	kcpReturnDTO.setMobileNo(c_PayPlus.mf_get_res( "mobile_no"   )); // 휴대폰 번호
	            }

	    /* = -------------------------------------------------------------------------- = */
	    /* =   06-6. 상품권 승인 결과 처리                                              = */
	    /* = -------------------------------------------------------------------------- = */

//	                app_time    = c_PayPlus.mf_get_res( "tk_app_time" ); // 승인 시간
//	                tk_van_code = c_PayPlus.mf_get_res( "tk_van_code" ); // 발급사 코드
//	                tk_app_no   = c_PayPlus.mf_get_res( "tk_app_no"   ); // 승인 번호


	    /* = -------------------------------------------------------------------------- = */
	    /* =   06-7. 현금영수증 승인 결과 처리                                          = */
	    /* = -------------------------------------------------------------------------- = */
	            kcpReturnDTO.setCashAuthno(c_PayPlus.mf_get_res( "cash_authno" )); // 현금영수증 승인번호
	            kcpReturnDTO.setCashNo(c_PayPlus.mf_get_res( "cash_no"     )); // 현금영수증 거래번호
	            
	            
	            /* = -------------------------------------------------------------------------- = */
	    	    /* =   06-8. 에스크로 여부 결과 처리                                            = */
	    	    /* = -------------------------------------------------------------------------- = */
	    	    kcpReturnDTO.setEscwYn(c_PayPlus.mf_get_res( "escw_yn" )); // 에스크로 여부
	        }
	    }
	    
	    /* = -------------------------------------------------------------------------- = */
	    /* =   06. 승인 결과 처리 END                                                   = */
	    /* ============================================================================== */


	    /* = ========================================================================== = */
	    /* =   07. 승인 및 실패 결과 DB 처리                                            = */
	    /* = -------------------------------------------------------------------------- = */
	    /* =      결과를 업체 자체적으로 DB 처리 작업하시는 부분입니다.                 = */
	    /* = -------------------------------------------------------------------------- = */


//	        /* = -------------------------------------------------------------------------- = */
//	        /* =   07-2. 승인 실패 DB 처리(res_cd != "0000")                                = */
//	        /* = -------------------------------------------------------------------------- = */
//	            if( !"0000".equals ( res_cd ) )
//	            {
//
//	            }
//	        }
//	    }
//	 
	    
	    return kcpReturnDTO;
	}
	
	/**
	 * 결제 후 망취소
	 * 
	 * @param kcpParamDTO
	 * @return
	 */
	public KcpReturnDTO approveCancel(KcpParamDTO kcpParamDTO, String mallId,boolean vip ) {
		
		KcpCancelParamDTO cancelDTO = new KcpCancelParamDTO();
		
		cancelDTO.setTno(kcpParamDTO.getTno());
		cancelDTO.setOrdr_idxx(kcpParamDTO.getOrdr_idxx());
		
		cancelDTO.setNet_cancel(true);
		cancelDTO.setMod_desc("가맹점 결과 처리 오류");
		
		/* ============================================================================== */
	    /* =   07-1.자동취소시 에스크로 거래인 경우                                     = */
	    /* = -------------------------------------------------------------------------- = */
		if ( ("Y").equals(kcpParamDTO.getEscw_yn()) && OrderEnum.kcpPayComp.VIRTL_BNK_ACCT_PAY.getKcpValue().equals(kcpParamDTO.getUse_pay_method()) ){
			cancelDTO.setMod_type(OrderClaimEnum.KcpCancel.ESCROW_CANCEL_VIRTLBNKACCT.getModType()); // 에스크로 가상계좌 건의 경우 가상계좌 발급취소(STE5)
        }
        else if ( ("Y").equals(kcpParamDTO.getEscw_yn()) )
        {
        	cancelDTO.setMod_type(OrderClaimEnum.KcpCancel.ESCROW_CANCEL_BEFORE_DLV.getModType()); // 에스크로 가상계좌 이외 건은 즉시취소(STE2)
        }
        else
        {
        	cancelDTO.setMod_type(OrderClaimEnum.KcpCancel.STANDARD_CANCEL_PAY.getModType()); // 에스크로 거래 건이 아닌 경우(일반건)(STSC)
        }
		/* = ---------------------------------------------------------------------------- = */
	    /* =   07-1. 자동취소시 에스크로 거래인 경우 처리 END                             = */
	    /* = ============================================================================== */
		
		//mall 분리
		String siteCd = getConfigService().getProperty("ncp_base.order.kcp.site.cd");
		String siteKey = getConfigService().getProperty("ncp_base.order.kcp.site.key");
		if("MBM".equals(mallId)) {
			siteCd = getConfigService().getProperty("ncp_base.order.kcp.mlb.site.cd");
			siteKey = getConfigService().getProperty("ncp_base.order.kcp.mlb.site.key");
	    	if(vip){
		    	siteCd = getConfigService().getProperty("ncp_base.order.kcp.mlb.site.vip.cd");
		    	siteKey = getConfigService().getProperty("ncp_base.order.kcp.mlb.site.vip.key");
	    	}
		} else if("SAM".equals(mallId)) {
			siteCd = getConfigService().getProperty("ncp_base.order.kcp.sa.site.cd");
			siteKey = getConfigService().getProperty("ncp_base.order.kcp.sa.site.key");
		}
		
		cancelDTO.setSiteCd(siteCd);
		cancelDTO.setSiteKey(siteKey);
		
		return this.approveCancel(cancelDTO);
	}
	
	/**
	 * 승인 취소처리
	 * 
	 * @param cancelDTO
	 * @return
	 */
	public KcpReturnDTO approveCancel(KcpCancelParamDTO cancelDTO) {
		
		 /* = -------------------------------------------------------------------------- = */
//	    /* =   07. 승인 및 실패 결과 DB 처리 END                                        = */
//	    /* = ========================================================================== = */
//
//	    /* = ========================================================================== = */
//	    /* =   08. 승인 결과 DB 처리 실패시 : 자동취소                                  = */
//	    /* = -------------------------------------------------------------------------- = */
//	    /* =      승인 결과를 DB 작업 하는 과정에서 정상적으로 승인된 건에 대해         = */
//	    /* =      DB 작업을 실패하여 DB update 가 완료되지 않은 경우, 자동으로          = */
//	    /* =      승인 취소 요청을 하는 프로세스가 구성되어 있습니다.                   = */
//	    /* =                                                                            = */
//	    /* =      DB 작업이 실패 한 경우, bSucc 라는 변수(String)의 값을 "false"        = */
//	    /* =      로 설정해 주시기 바랍니다. (DB 작업 성공의 경우에는 "false" 이외의    = */
//	    /* =      값을 설정하시면 됩니다.)                                              = */
//	    /* = -------------------------------------------------------------------------- = */
		
		KcpReturnDTO kcpReturnDTO = new KcpReturnDTO();
		J_PP_CLI_N c_PayPlus = new J_PP_CLI_N();

	    //c_PayPlus.mf_init( "", g_conf_gw_url, g_conf_gw_port, g_conf_tx_mode, g_conf_log_dir );
	    c_PayPlus.mf_init( "", getConfigService().getProperty("ncp_base.order.kcp.gw.url")
	    		, getConfigService().getProperty("ncp_base.order.kcp.gw.port")
	    		, Integer.valueOf(getConfigService().getProperty("ncp_base.order.kcp.tx.mode"))
	    		, getConfigService().getProperty("ncp_base.order.kcp.log.dir") ); // 설정행
	    c_PayPlus.mf_init_set();   
	    
	    int mod_data_set_no;

        String cancel_tran_cd = "00200000";
	    

        mod_data_set_no = c_PayPlus.mf_add_set( "mod_data" );
        c_PayPlus.mf_set_us( mod_data_set_no, "tno",      cancelDTO.getTno()         ); // KCP 원거래 거래번호
        c_PayPlus.mf_set_us( mod_data_set_no, "mod_type", cancelDTO.getMod_type()               ); // 원거래 변경 요청 종류
        c_PayPlus.mf_set_us( mod_data_set_no, "mod_ip",   this.getModIp()  ); // 변경 요청자 IP
        c_PayPlus.mf_set_us( mod_data_set_no, "mod_desc", cancelDTO.getMod_desc()  ); // 변경 사유

        /****************************************************************************************************************
         * 부분취소
         ****************************************************************************************************************/
        if (OrderClaimEnum.KcpCancel.STANDARD_PARTCANCEL_PAY.getModType().equals(cancelDTO.getMod_type())) { // 부분취소
        	
        	c_PayPlus.mf_set_us( mod_data_set_no, "mod_mny", 		cancelDTO.getMod_mny() 			);     // 취소요청금액
        	c_PayPlus.mf_set_us( mod_data_set_no, "rem_mny", 		cancelDTO.getRem_mny() 			);     // 취소가능잔액
        	
        /****************************************************************************************************************
         * 전체환불 
         ****************************************************************************************************************/
        } else if (OrderClaimEnum.KcpCancel.STANDARD_REFUND_PAY.getModType().equals(cancelDTO.getMod_type())) { // 전체환불
        	
        	c_PayPlus.mf_set_us( mod_data_set_no, "mod_comp_type",  "MDCP01"        				);     // 변경 유형 : 계좌인증 + 환불등록
        	c_PayPlus.mf_set_us( mod_data_set_no, "mod_sub_type",   "MDSC00"        				);     // 변경 유형 : 전체환불
        	
        	c_PayPlus.mf_set_us( mod_data_set_no, "mod_bankcode",   cancelDTO.getMod_bankcode()     );     // 환불 요청 은행 코드
            c_PayPlus.mf_set_us( mod_data_set_no, "mod_account",    cancelDTO.getMod_account()      );     // 환불 요청 계좌
            c_PayPlus.mf_set_us( mod_data_set_no, "mod_depositor",  cancelDTO.getMod_depositor()    );     // 환불 요청 계좌주명
            
        /****************************************************************************************************************
         * 부분환불 
         ****************************************************************************************************************/
        } else if (OrderClaimEnum.KcpCancel.STANDARD_PARTREFUND_PAY.getModType().equals(cancelDTO.getMod_type())) { // 부분환불
        	
        	c_PayPlus.mf_set_us( mod_data_set_no, "mod_comp_type",  "MDCP01"        				);     // 변경 유형 : 계좌인증 + 환불등록
        	c_PayPlus.mf_set_us( mod_data_set_no, "mod_sub_type",   "MDSC03"        				);     // 변경 유형 : 부분환불
        	
        	c_PayPlus.mf_set_us( mod_data_set_no, "mod_bankcode",   cancelDTO.getMod_bankcode()     );     // 환불 요청 은행 코드
            c_PayPlus.mf_set_us( mod_data_set_no, "mod_account",    cancelDTO.getMod_account()      );     // 환불 요청 계좌
            c_PayPlus.mf_set_us( mod_data_set_no, "mod_depositor",  cancelDTO.getMod_depositor()    );     // 환불 요청 계좌주명
            
            c_PayPlus.mf_set_us( mod_data_set_no, "mod_mny", 		cancelDTO.getMod_mny() 			); 	   // 취소요청금액
        	c_PayPlus.mf_set_us( mod_data_set_no, "rem_mny", 		cancelDTO.getRem_mny() 			);     // 취소가능잔액
            
        /****************************************************************************************************************
         * 휴대폰 재승인
         ****************************************************************************************************************/
        } else if (OrderClaimEnum.KcpCancel.MOBILPON_REPAY.getModType().equals(cancelDTO.getMod_type())) {
        	
        	c_PayPlus.mf_set_us( mod_data_set_no, "mod_mny", 		cancelDTO.getMod_mny() 			);     // 재승인 요청 금액
        	
    	/****************************************************************************************************************
         * 에스크로 - 배송 전 취소 / 배송 후 취소
         ****************************************************************************************************************/
        } else if (OrderClaimEnum.KcpCancel.ESCROW_CANCEL_BEFORE_DLV.getModType().equals(cancelDTO.getMod_type())
        		|| OrderClaimEnum.KcpCancel.ESCROW_CANCEL_AFTER_DLV.getModType().equals(cancelDTO.getMod_type())) {
        	
        	if ("Y".equals(cancelDTO.getRefund_yn())) {
        		c_PayPlus.mf_set_us( mod_data_set_no, "bank_code",   cancelDTO.getMod_bankcode()     );     // 환불 요청 은행 코드
                c_PayPlus.mf_set_us( mod_data_set_no, "refund_account",  cancelDTO.getMod_account()  );     // 환불 요청 계좌
                c_PayPlus.mf_set_us( mod_data_set_no, "refund_nm",  cancelDTO.getMod_depositor()     );     // 환불 요청 계좌주명
        	}
        
    	/****************************************************************************************************************
         * 에스크로 - 구매 확인 후 취소
         ****************************************************************************************************************/
        } else if (OrderClaimEnum.KcpCancel.ESCROW_CANCEL_AFTER_CONFIRM.getModType().equals(cancelDTO.getMod_type())) {
        	
        	cancel_tran_cd = "70200200";
        	
        	c_PayPlus.mf_set_us( mod_data_set_no, "mod_desc_cd" , "CA06" 								);		// 변경사유코드 (고정값)
        	
        	if (OrderClaimEnum.KcpCancel.ESCROW_CANCEL_RLTMBNKACCT_AFTER_CONFIRM.getModType().equals(cancelDTO.getEscr_cncl_cd())) {
        		
        		c_PayPlus.mf_set_us( mod_data_set_no, "sub_mod_type"    , "STSC"            			);
                c_PayPlus.mf_set_us( mod_data_set_no, "mod_sub_type"    , "MDSC03"          			);
        		
        	} else if (OrderClaimEnum.KcpCancel.ESCROW_PARTCANCEL_RLTMBNKACCT_AFTER_CONFIRM.getModType().equals(cancelDTO.getEscr_cncl_cd())) {
        		
        		c_PayPlus.mf_set_us( mod_data_set_no, "sub_mod_type"    , "STPC"            			);
                c_PayPlus.mf_set_us( mod_data_set_no, "part_canc_yn"    , "Y"               			);
                c_PayPlus.mf_set_us( mod_data_set_no, "rem_mny"         , cancelDTO.getRem_mny()        );
                c_PayPlus.mf_set_us( mod_data_set_no, "amount"          , cancelDTO.getMod_mny()        );
                c_PayPlus.mf_set_us( mod_data_set_no, "mod_mny"         , cancelDTO.getMod_mny()        );
                c_PayPlus.mf_set_us( mod_data_set_no, "mod_sub_type"    , "MDSC04"          			);
                
        	} else if (OrderClaimEnum.KcpCancel.ESCROW_CANCEL_VIRTLBNKACCT_AFTER_CONFIRM.getModType().equals(cancelDTO.getEscr_cncl_cd())) {
        		
        		c_PayPlus.mf_set_us( mod_data_set_no, "sub_mod_type"    , "STHD"            			);
                c_PayPlus.mf_set_us( mod_data_set_no, "mod_mny"         , cancelDTO.getMod_mny()        );
                c_PayPlus.mf_set_us( mod_data_set_no, "mod_sub_type"    , "MDSC00"          			);
                c_PayPlus.mf_set_us( mod_data_set_no, "mod_bankcode"    , cancelDTO.getMod_bankcode()   );
                c_PayPlus.mf_set_us( mod_data_set_no, "mod_account"     , cancelDTO.getMod_account()  	);
                c_PayPlus.mf_set_us( mod_data_set_no, "mod_depositor"   , cancelDTO.getMod_depositor()  );
                
        	} else if (OrderClaimEnum.KcpCancel.ESCROW_PARTCANCEL_VIRTLBNKACCT_AFTER_CONFIRM.getModType().equals(cancelDTO.getEscr_cncl_cd())) {
        		
        		c_PayPlus.mf_set_us( mod_data_set_no, "sub_mod_type"    , "STPD"            			);
                c_PayPlus.mf_set_us( mod_data_set_no, "mod_mny"         , cancelDTO.getMod_mny()        );
				c_PayPlus.mf_set_us( mod_data_set_no, "rem_mny"         , cancelDTO.getRem_mny()        ); 
                c_PayPlus.mf_set_us( mod_data_set_no, "mod_sub_type"    , "MDSC04"          			);
                c_PayPlus.mf_set_us( mod_data_set_no, "mod_bankcode"    , cancelDTO.getMod_bankcode()   );
                c_PayPlus.mf_set_us( mod_data_set_no, "mod_account"     , cancelDTO.getMod_account()  	);
                c_PayPlus.mf_set_us( mod_data_set_no, "mod_depositor"   , cancelDTO.getMod_depositor()  );
                c_PayPlus.mf_set_us( mod_data_set_no, "part_canc_yn"    , "Y"               			);
                
        	}
    	
        }


    	c_PayPlus.mf_do_tx( cancelDTO.getSiteCd()
    			, cancelDTO.getSiteKey()
    			, cancel_tran_cd
    			, ""
    			, cancelDTO.getOrdr_idxx()
    			, getConfigService().getProperty("ncp_base.order.kcp.log.level")
    			, "1" ); // 설정행
	    
    	log.debug("###### SEND MSG #######" + c_PayPlus.getLogSendMsg());
    	log.debug("###### RECV MSG #######" + c_PayPlus.getLogRecvMsg());
    	
    	kcpReturnDTO.setTno(cancelDTO.getTno());
    	kcpReturnDTO.setResCd(c_PayPlus.m_res_cd);
    	kcpReturnDTO.setResMsg(c_PayPlus.m_res_msg);
    	
    	log.debug("cancel-res_cd {}",  kcpReturnDTO.getResCd());
	    log.debug("cancel-res_msg {}",  kcpReturnDTO.getResMsg());
	    log.debug("cancel-c_PayPlus {}",  c_PayPlus);
	    
	    if (OrderClaimEnum.KcpCancel.MOBILPON_REPAY.getModType().equals(cancelDTO.getMod_type())) { // 휴대폰 재결제
	    	kcpReturnDTO.setTradeReauthNo(c_PayPlus.mf_get_res("trade_reauth_no"));
	    }
	    
	    return kcpReturnDTO;
	}
	
	/**
	 * IP 조회
	 * Batch와 같이 request가 없을 경우는 서버 IP를 리턴
	 * @return
	 */
	private String getModIp() {
		try {
			if (ContextService.getCurrentRequest() != null) {
				return ContextService.getCurrentRequest().getRemoteAddr();
			} else {
				return InetAddress.getLocalHost().getHostAddress();
			}
		} catch (Exception e) {
			return "0.0.0.0";
		}
	}
	
	public Map<String, String> setVirtualAccountDeposit(KcpCommonReceiveDTO kcpCommonReceiveDTO, Map<String, String> resultMap, String host, String addr) throws Exception {
		
			if (log.isInfoEnabled()) {
				log.info("*************************************************************");
				log.info("kcp get param {}",kcpCommonReceiveDTO.toJSON());

				log.info("**.getRemoteHost         	={}", host);
				log.info("**.getRemoteAddr         	={}", addr);
				log.info("*************************************************************");
			}
	        
        	// 상점 ID 확인
//        	if ( !(resultMap.get("pgStoreId")).equals(kcpCommonReceiveDTO.getSite_cd())) {
//        		resultMap.put("code", "9999 : site_cd is wrong");
//        	} 
//        	else 
        	if ( (resultMap.get("approval_no")).equals(kcpCommonReceiveDTO.getTno())) {		// 거래일련번호 일치
        		if ( (resultMap.get("bnkAcctNo")).equals(kcpCommonReceiveDTO.getAccount())) {		// 계좌번호 일치
        			
        				if ((resultMap.get("amt")).equals(kcpCommonReceiveDTO.getIpgm_mnyx())) {		// 입금 금액 일치

        						
								resultMap.put("code", "0000");
								resultMap.put("approvalYmdhms", kcpCommonReceiveDTO.getTx_tm());
								resultMap.put("udterId", "KCP");
							
        				}
        				else {
                			resultMap.put("code", "9999 : Apply_amt is mismatch");        			
        				}
        			
        		}
        		else {
        			resultMap.put("code", "9999 : bnkAcctNo is mismatch");        			
        		}
        	} 
        	else {
        		resultMap.put("code", "9999 : approval_no is mismatch");
        	}
	       

	        return resultMap;
		}
}
