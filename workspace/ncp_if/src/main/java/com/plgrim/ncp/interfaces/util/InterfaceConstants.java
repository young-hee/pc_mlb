package com.plgrim.ncp.interfaces.util;

import org.springframework.stereotype.Component;


/**
 * Interface 에서 사용하는 상수들을 정의하는 class 이다.
 * 
 * @author
 *
 */
@Component
public class InterfaceConstants {

	public static final String SEND_SAMPLE = "/sample/sendSample";

	public static final String SEND_SAMPLE_LIST = "/sample/sendSampleList";

	/**
	 * 내부 Interface Constants
	 */
	public static final String IF_RESULT_CD_SUCESS 		= "200";
	public static final String IF_RESULT_CD_FAIL 		= "500";

	/* 문자/알림톡 발송 */
	public static final String SEND_SMS_MMS = "/mpush/sendSmsMms";
	public static final String SEND_ALIMTALK = "/mpush/sendAlimTalk";


	/* 물류 */
	public static final String DELIVERY_SEND_WMS_DLIVY_DRCT = "/delivery/sendWmsDlivyDrct";
	public static final String DELIVERY_SEND_WMS_RETRIEVAL = "/delivery/sendWmsRetrievalOrder";
	public static final String DELIVERY_SEND_EPOST_RETRIEVAL = "/delivery/sendEpostRetrievalOrder";
	public static final String DELIVERY_SEND_WMS_RETRIEVAL_WITHDRAW = "/delivery/sendWmsRetrievalWithdrawOrder";
	public static final String DELIVERY_SEND_CJ_LIST = "/delivery/sendCjList";
	public static final String DELIVERY_SEND_CJ_RDC_LIST = "/delivery/sendCjRdcList";
	public static final String DELIVERY_GET_CJ_DLV_STATUS = "/delivery/getCjDlvStatus";
	public static final String DELIVERY_GET_CJ_RDC_DMSTC_WAYBIL_NO = "/delivery/getCjDmstcWaybilNo";

	/* 회원 */
	public static final String MEMBER_GET_MEMBER_INFORMATION = "/member/getMemberInformation";
	public static final String MEMBER_SEND_MEMBER_INFORMATION = "/member/sendMemberInformation";
	public static final String MEMBER_GET_MEMBERSHIP_YN = "/member/getMembershipYn";
	public static final String MEMBER_SEND_DROP_MEMBER = "/member/sendDropMemberInformation";
	public static final String MEMBER_SEND_MEMBER_LASTEST_LOGIN_INFO = "/member/sendMemberLatestLogInInfo";
	public static final String MEMBER_ADD_MEMBERSHIP_CARD = "/member/addMembershipCard";
	public static final String MEMBER_GET_MILEAGE_INFORMATION = "/member/getMemberMileage";
	public static final String MEMBER_EMPLOYEE_INFO = "/member/getMemberEmployeeInfo";
	
	public static final String GOODS_TARGET_COUNT = "/goods/getGoodsInterfasceTargetCount";
	public static final String BASIC_GOODS_INFORMATION = "/goods/basicGoodsInformation";
	public static final String GOODS_MATERIAL_INFORMATION = "/goods/goodsMaterialInformation";
	public static final String GOODS_SIZELKTBN_INFORMATION = "/goods/goodsSizeLktbnIformation";
	public static final String GOODS_PRC_INFORMATION = "/goods/getGoodsPrcInformation";
	public static final String GOODS_ITMINV_INFORMATION = "/goods/getGoodsItemInventoryInformation";
	public static final String RLTM_GODINVL_INFORMATION = "/goods/getRealtimeGoodsInventoryInformation";
	public static final String GOODS_SIZELKTBPOM_INFORMATION = "/goods/goodsSizeLktbPomIformation";
	public static final String SEND_GOODS_IMAGE_URL = "/goods/sendGoodsImageURL";

 	/* 프로모션 */
	public static final String PROMOTION_GET_ONOFFCOUPON_ISSUELIST = "/promotion/getOnOffCouponIssueList";
	public static final String PROMOTION_VALID_ONOFFCOUPON_USE = "/promotion/validOnOffCoupon";
	public static final String PROMOTION_USE_ONOFFCOUPON = "/promotion/useOnOffCoupon";
	public static final String PROMOTION_RESTORE_ONOFFCOUPON = "/promotion/restoreOnOffCoupon";
	public static final String PROMOTION_ISSUE_ONOFFCOUPON = "/promotion/issueOnOffCoupon";
	
	/* 주문 */
	public static final String ORDER_ONLINE_RT_SEND = "/order/onlineRTSend";
	public static final String ORDER_ONLINE_RT_CANCLE = "/order/cancelSendRT";
	public static final String ORDER_ONLINE_RT_RECEIVE = "/order/storeDeliConfirm";
	public static final String ORDER_SHOP_RETURN_RT_SEND = "/order/shopReturnRT";
	public static final String ORDER_PAYMEMT_INFO_SEND = "/order/orderPaymentInfo";
	public static final String ORDER_USE_TEMP_MEMBER_MILEAGE = "/order/useTempMemberMileage";
	public static final String ORDER_OFFlINT_PURCHASE_LIST = "/order/offlinePurchaseList";
	public static final String ORDER_FORMERLY_PURCHASE_LIST = "/order/formerlyPurchaseList";
	public static final String ORDER_SEND_ERP_INV_STOCK = "/order/sendOrderErpInvStock";
	public static final String ORDER_FORMERLY_PURCHASE_CONFIRM_INFO = "/order/formerlyPurchaseConfirmInfo";
	
	/* 기타 */
	public static final String ETC_RANKING_OF_COUNTRY = "/etc/getRankingOfCountry";
	public static final String ETC_RANKING_OF_CATEGORY = "/etc/getRankingOfCategory";
	/**
	 * 외부 Interface Constants
	 */
	public static final String IF_GOD_01 = "/inf/online/GOD/ERPProductIFTargetCount";
	public static final String IF_GOD_02 = "/inf/online/GOD/ERPProductInfon";
	public static final String IF_GOD_03 = "/inf/online/GOD/ERPMaterial";
	public static final String IF_GOD_04 = "/inf/online/GOD/ERPProductSizeInfo";
	public static final String IF_GOD_05 = "/inf/online/GOD/ERPProductPriceInfo";
	public static final String IF_GOD_06 = "/inf/online/GOD/StockConcilationInfo";
	public static final String IF_GOD_07 = "/inf/online/GOD/IndivisalStockConcilationInfo";
	public static final String IF_GOD_08 = "/inf/online/GOD/ERPProductSizePomInfo";
	public static final String IF_GOD_09 = "/inf/online/GOD/SendGoodsImageURL";
	
	/* 매장정보 */
	public static final String IF_SST_01 = "/shop/getInterfaceShopList";

    public static final String SEND_HTML_EMAIL = "/aws/ses/sendHtmlEmail";
    public static final String SEND_TEMPLATE_EMAIL = "/aws/ses/sendTemplateEmail";

    public static final String NAVER_ACCESS_TOKEN_VALIDATOR = "/naver/naverAccessTokenValidator";
    public static final String OLAPIC_TRANS = "/olapic/trans";
    
    public static final String FACEBOOK_SEND_GOD = "/facebook/send";
    public static final String FACEBOOK_UPDATE_GOD = "/facebook/update";
    
    public static final String SEND_CERT_SMS = "/sms/sendMobileCertSms";
    public static final String SEND_CERT_SMS_CHECK = "/sms/sendMobileCertSmsCheck";
    
    public static final String MLB_MONOLABS_LICENSE = "/monolabs/sendLicense";

    /* 크리마 관련 */
    public static final String CREMA_SEND_CATEGORY = "/crema/sendCremaCategoryList";
    public static final String CREMA_SEND_CARTITEM = "/crema/sendCremaCartItemList";
    public static final String CREMA_SEND_PRODUCT = "/crema/sendCremaProductList";
    public static final String CREMA_SEND_ORDER = "/crema/sendCremaOrderList";
    public static final String CREMA_SEND_USER = "/crema/sendCremaUserList";
    public static final String CREMA_UPDATE_USER = "/crema/updateCremaUserList";

    public static final String CREMA_UPDATE_RECPTN_AGR = "/crema/updateCremaRecptnAgr";
    public static final String IF_GET_MEMBER_RECPTN_AGR = "/member/getMemberRecvYn";

    public static final String CREMA_SEND_OFF_ORDER = "/crema/sendCremaOfflineOrderList";

    public static final String IF_GET_OFF_ORDER = "/order/getOfflineOrderList";
    public static final String IF_GET_OFF_SUBORDER = "/order/getOfflineSubOrderList";

}
