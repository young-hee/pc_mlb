package com.plgrim.ncp.base.enums;

/**
 * 시스템 ENUM
 * <pre>
 * web.xml에서 선언된  mall 아이디 환경변수 명
 *     - MALL_ID_SYSTEM_VARIABLE_ID("mall.id")
 * 
 * 인증 회원에게 부여되는 권한
 *     -ROLE_USER_AUTHORIZE("ROLE_USER");
 * </pre>
 * @author Vito
 *
 */
public enum SysInfoEnum {

    /**
     * mall.id
     */
    MALL_ID_SYSTEM_VARIABLE_ID("mall.id");
    
    private final String value;

    private SysInfoEnum(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
    
    /**
     * <pre>
     * 몰 ID
     *    DXM_MALL_ID : DXM
     *    CSS_MALL_ID : CSS
     * </pre>
     * @author Vito
     *
     */
    public enum MallIdEnum {
        
        DXM_MALL_ID("DXM"),
        MLB_MALL_ID("MBM"),
        SA_MALL_ID("SAM"),
        CSS_MALL_ID("CSS");
        
        private final String v;

        private MallIdEnum(final String v) {
            this.v = v;
        }

        @Override
        public String toString() {
            return v;
        }
    }
    
    /**
     * 통합몰에서 노출되어야 하는 몰ID enum
     * 
     * (현재 plgrimshop mypage영역에서 노출 기준으로 사용하고 있으며, 이후 통합몰에 노출되어야 할 몰이 추가된다면 이곳에 값을 추가할 것)
     * @author seed
     *
     */
    public enum PlgrimshopMallEnum {
    	DXM_MALL_ID("DXM"),
    	EGTSNDS_MALL_ID("10001"),
    	BNPL_MALL_ID("10002"),
    	FSHNP_MALL_ID("10003"),
    	BKRSTR_MALL_ID("10004"),
    	TNCRSCM_MALL_ID("10005");
    	
    	private final String value;
    	
    	private PlgrimshopMallEnum(final String value) {
    		this.value = value;
    	}
    	
    	@Override
        public String toString() {
            return value;
        }
    }
    
    /**
     * 자사 브랜드 정보 enum
     *
     * (해당 카테고리에 적용되어 있는 상품의 브랜드 순서 정렬로직에서 사용함. 자사 브랜드의 순서를 property에서 가져옴.)
     */
    public enum OwnBrandEnum {
    	OwnBrandId("ncp_web_pc_dx.own.brand.id"),
    	OwnBrandFlag_Y("Y"),
    	OwnBrandFlag_N("N");
    	
    	private final String value;
    	
    	private OwnBrandEnum(final String value) {
    		this.value = value;
    	}
    	
    	@Override
    	public String toString() {
    		// TODO Auto-generated method stub
    		return value;
    	}
    }
    
}
