/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shsunhee.kim
 * @since       2015. 10. 23
 */
package com.plgrim.ncp.base.enums;

/**
 * 반드시 enum 속성은 상수이기 때문에 대문자로 선언 한다. 예) USER_ID_INPUT_NAME
 * enum를 String값 비교 할때는 if (EnumSample.USER_ID_INPUT_NAME.equals("비교값"))
 */
public enum DisplayEnum {
	
	YES("Y"),NO("N")
	
	,MALL("DXM"),MALL_CSS("CSS")
	
	,DF_SORT_SEQ("999")
	
	,DSP_END_DATE("29991231235959")
	
	,DF_LANG("KOR")

	,BEKER_BRND_ID("MCBR")

	,WOMEN("DXMA41")

	,MEN("DXMA42")

	,KIDS("DXMA43")
	
	,NEWCTG("DXMA31")
	,BSTCTG("DXMA32")
	,SALECTG("DXMA33")
	,OTLTCTG("DXMA44")
    ,ONE("1")
    ,TWO("2")
    ,THREE("3")
    
    ,MAIN("MAIN")
    
    , GNB_BNR("120")
	, TOP_BNR("121")
	, MEN_LAYER_BNR("122")
	, WOMEN_LAYER_BNR("123")
	, CAP_LAYER_BNR("124")
	, ACC_LAYER_BNR("125")
	, KIDS_LAYER_BNR("126")
	, MLB_MO_TOP_BNR("226")
	, SA_TOP_BNR("188")
	, SA_MO_TOP_BNR("220")
	, SA_GNB_BNR("189")
	, SHOES_LAYER_BNR("20007")
	, DX_PC_TOP_BNR("20007")
	, DX_MO_TOP_BNR("20008")	
	, SA_LAYER_BNR("20009")
		
	, STG_GNB_BNR("10120")
	, STG_TOP_BNR("10121")
	, STG_MEN_LAYER_BNR("10122")
	, STG_WOMEN_LAYER_BNR("10123")
	, STG_CAP_LAYER_BNR("10124")
	, STG_ACC_LAYER_BNR("10125")
	, STG_KIDS_LAYER_BNR("10126")
	, STG_OUTLET_LAYER_BNR("20001")
	, STG_SA_TOP_BNR("10188")
	, STG_SA_MO_TOP_BNR("10220")
	, STG_SA_GNB_BNR("10189")
	, STG_SHOES_LAYER_BNR("20004")
	, STG_DX_PC_TOP_BNR("20007")
	, STG_DX_MO_TOP_BNR("20008")
	, STG_SA_LAYER_BNR("20009")
	, STG_MLB_MO_TOP_BNR("10223")
	
	,MOBILE_LOOKBOOK_MAIN_CTGRY("MBMA07A01")
	
	,MBM_MALL("MBM")
	,SAM_MALL("SAM")
	,DXM_MALL("DXM")
	;

	public enum UP_CTGRY_NO {
		DXM, BDM, THM, MTP, STP;
	}

	public enum DVC_SECT {
		PC, MOBILE, PC_MOBILE
	}
	
    public enum CTGRY_TP {

    	CTGRY_SHOP, ADHOC_SHOP;
	}
    
    	   
	public enum TMPLAT_TP {
		DSP_CTGRY, 
		THEMA_PGE,
		BRND,
		PROMT,
		STRND,
		EVT
	}
	
	public enum CONTT_TP {
		GOD,
		STK_GOD,
		HTML,
		IMG_BANNER,
		MOVI,
		PROMT,
		STRND,
		TEXT,
		VIMEO
	}
	
	
	public enum DSP_TGT_TP {
	   MALL_ID,
	   LANG,
	   DVC,
	   TGT_MBR_TP,
	   TGT_MBR_ATRB,
	   TGT_MBR_ATRB_DATA
	}
	
	//가격 구분 코드
	public enum PrcSectCd{
		GNRL, //일반
		ONLNE_CLUB,//온라인클럽
		SIGNL,//싱글
		EMP,//임직원
		ILMO//일모
	}
	
	//s-trend 유형코드
	public enum StrndTpCd{
		CODI, //코디
		LKBUK, //룩북
		MOV, //무비
		MGZ, //매거진
		GTS //겟더스타일
	}
	
	//대체전시상품
	public enum ALTRTV_DSP_GOD_TP {
		/** 카테고리 베스트 */
		CTGRY_BST,
		/** 카테고리 신상품 */
		CTGRY_NEW_GOD,
		/** 브랜드 베스트 */
		BRND_BST,
		/** 브랜드 신상품 */
		BRND_NEW_GOD,
		/** 브랜드 시즌 베스트 */
		BRND_SESON_BST,
		/** 브랜드 시즌 신상품 */
		BRND_SESON_NEW_GOD
	}

	//로케이션 구분값
	public enum LOCATION_TP {

		/** 비이커 Brands in Beaker */
		BRANDS_IN_BEAKER,
		/** 비이커 Top Brands 20 */
		TOP_BRAND,
		/** 비이커 Look Book */
		LOOK_BOOK,
		/** 검색 */
		SEARCH,
		/** Outlet */
		OUTLET,
		/** 기획전 */
		PLAN,
		/** 이벤트 */
		EVENT,
		/** Get The Style */
		GET_THE_STYLE,
		/** 장바구니 */
		BASKET,
		/** 주문서 */
		ORDER,
		/** 주문서완료 */
		ORDER_COMPT,
		/** 마이페이지 */
		MY_PAGE,
		/** 고객센터 */
		HELP_DESK,
		/** 회원가입 */
		MBR_JOIN,
		/** 로그인 */
		LOGIN,
		/** 비회원주문조회 */
		NMBR_ORDER,
		/** 매장찾기 */
		SHOP_SEARCH,
		/** 사이트맵 */
		SITE_MAP,
		/** 이용약관 */
		STPLAT,
		/** 브랜드스토리 */
		BRAND_STORY,
		/** 개인정보취급방침 */
		PSNL_INFO_TRTMNT_POLCY,
		/** 멤버십안내 */
		MBR_INFO,
		/** 회사소개 */
		COMPANY_INFO,
		/** 입점신청 */
		PARTMAL_REQST,
		/** 멤버십 포인트 */
		MBSH_PNT,
		/** P포인트 */
		WEB_PNT,
		/** 쿠폰 */
		CPN,
		/** 일모카드 잔액조회 */
		ILMO_CARD_INQIRE,
		/** 최근 본 상품 */
		USE_GOD,
		/** 위시리스트 */
		WISHLST,
		/** 이벤트 참여내역 */
		JOIN_EVT,
		/** 재입고 알림 현황 */
		RE_WHSG_NTCN,
		/** 1:1문의 */
		MTM_INQ,
		/** 상품리뷰 */
		GOD_REV,
		/** 상품Q&A */
		GOD_INQ,
		/** 공지사항 목록 */
		NOTI_LIST,
		/** 공지사항 상세 */
		NOTI_DETAIL,
		/** 1:1상담 */
		MTM_CNSLT,
		/** 이용안내 */
		USE_INFO,
		/** A/S 현황조회 */
		AS,
		/** 나의 브랜드 */
		MY_BRAND,
		/** 쇼핑백 */
		ORD_SHOPBAG,
		/** 단체주문/제휴문의 */
		ORGZT_ORD_AFF_INQ,
		/** 주문/배송조회 */
		LIST_ORDER,
		/** 취소/교환/반품 조회 */
		CLM_LIST,
		/** 취소/교환/반품 조회 */
		REPAIR_LIST,
		/** 매장구매내역 */
		MY_PURCHASED_LIST_IN_STORE,
		/** 현금영수증 발행내역 */
		LIST_RECEIPT,

		/** 현금영수증 발행내역 (임시)*/
		LIST_RECEIPT2,
		/** 배송지관리 */
		LIST_DELIVERY_LOCATION,
		/** 멤버쉽정보 [#31806][16.12.29] 멤버십 제도 개선 (MBSH_INFO 추가) */
		MBSH_INFO
	}

	//노출 수준 코드
	public enum EXPSR_LVL {
		A_TP_LAG_MID_SML_DTL,
		B_TP_MID_SML_DTL,
		C_TP_LAG_MID_SML,
		D_TP_LAG_SML_DTL,
		D1,
		D2,
		D3,
		D4
	}
	
	public enum DVC {
		PC,
		MOBILE_WEB,
		MOBILE_APP
	}

	public enum PROMT_IMG_SECT {
		RPRST_IMG_1PCE,
		RPRST_IMG_2PCE,
		PROMT_GOD_IMG
	}

	public enum BO_CONTT_VIEW_TYPE {
		BASE,
		PRVW,
		AB_TEST
	}

	   /**
     * 기획전대상조회 결과 코드
     * 
     *   - NOT_LOGIN            : 비로그인
     *   - INVALID_MEMBER       : 유효하지 않은 회원   
     *   - SUCCESS              : 성공
     *   - NOT_PARTCPTN_TGT     : 참여 대상자가 아님
     *   - UNITY_MBR_ONLY		: 통합회원 전용
     *   - ONLINE_MBR_ONLY      : 온라인회원 전용
     *   - EMP_MBR_ONLY			: 임직원 회원 전용
     *   - INVALID_PLAN         : 유효하지 않은 기획전
     */
    public enum PlanResultCode {
          NOT_LOGIN
        , INVALID_MEMBER
        , SUCCESS
        , NOT_PARTCPTN_TGT        
        , UNITY_MBR_ONLY
        , ONLINE_MBR_ONLY
        , EMP_MBR_ONLY
        , INVALID_PLAN
    }
    
    /**
     * 카테고리구분코드
     */
    public enum CTGRY_SECT_CD {
    	  GNRL_CTGRY			/** 일반 카테고리			*/
    	, STRTGY_CTGRY			/** 전략 카테고리			*/
    	, NEW_GOD_CTGRY			/** 신상품 카테고리			*/
    	, ON_SALE_CTGRY			/** ON SALE 카테고리		*/
    	, OTLT_CTGRY			/** 아울렛 카테고리			*/
    	, BST_CTGRY				/** 베스트 카테고리			*/
    	, LOOKBOOK_CTGRY		/** 룩북 카테고리			*/
    }
    
    /**
     * 리그별 팀코드 
     */
    public enum League {
    	
    	  NATIONAL(NationalTeam.values(), "national")		/** 내셔널리그	팀	, 리그 코드	*/
    	, AMERICAN(AmericanTeam.values(), "american");		/** 아메리칸리그 팀	, 리그 코드	*/
    	
    	private final Team[] teams;
    	private final String leagueCd;
    	
    	private League(Team[] teams, String leagueCd) {
    		
    		this.teams = teams;
    		this.leagueCd = leagueCd;
    	
    	}
    	
    	public Team[] getTeams() {
    		
    		return teams;
    		
    	}
    	
    	public String getLeagueCd() {
    		
    		return leagueCd;
    		
    	}
    	
    	/**
    	 * 팀 interface
    	 * 	- getTeamCd            : 팀코드 반환
    	 */
    	public interface Team {
    		
    		String getTeamCd();
    		
    	}
    	/** 내셔널리그팀			*/
    	private enum NationalTeam implements Team {
    	
    		  ATLANTA_BRAVES("01")     			/** 팀명: Atlanta Braves				, 팀코드: 01			*/      
    		, CHICAGO_CUBS("02")             	/** 팀명: Chicago Cubs				, 팀코드: 02			*/
    		, CINCINNATI_REDS("03")          	/** 팀명: Cincinnati Reds				, 팀코드: 03			*/
    		, COLORADO_ROCKIES("04")         	/** 팀명: Colorado Rockies			, 팀코드: 04			*/
    		, MIAMI_MARLINS("05")            	/** 팀명: Miami Marlins				, 팀코드: 05			*/
    		, LOS_ANGELES_DODGERS("07")      	/** 팀명: Los Angeles Dodgers			, 팀코드: 07			*/
    		, NEW_YORK_METS("09")            	/** 팀명: New York Mets				, 팀코드: 09			*/
    		, PHILADELPHIA_PHILLIES("10")    	/** 팀명: Philadelphia Phillies		, 팀코드: 10			*/
    		, PITTSBURGH_PIRATES("11")       	/** 팀명: Pittsburgh Pirates			, 팀코드: 11			*/
    		, ST_LOUIS_CARDINALS("12")       	/** 팀명: ST.Louis Cardinals			, 팀코드: 12			*/
    		, SAN_DIEGO_PADRES("13")         	/** 팀명: San Diego Padres			, 팀코드: 13			*/
    		, SAN_FRANCISCO_GIANTS("14")     	/** 팀명: San Francisco Giants		, 팀코드: 14			*/
    		, ARIZONA_DIAMONDBACKS("15")     	/** 팀명: Arizona Diamondbacks		, 팀코드: 15			*/
    		, MILWAUKEE_BREWERS("48")        	/** 팀명: Milwaukee Brewers			, 팀코드: 48			*/
    		, WASHINGTON_NATIONALS("56");		/** 팀명: Washington Nationals		, 팀코드: 56			*/
    		
    		private final String teamCd;
    		
    		private NationalTeam(String teamCd) {
    			this.teamCd = teamCd;
    		}
    		
    		@Override
    		public String getTeamCd() {
    			return this.teamCd;
    		}
    		
    	}
    	/** 아메리칸리그팀			*/
    	private enum AmericanTeam implements Team {
    		
    		  HOUSTON_ASTROS("06")              /** 팀명: Houston Astros				, 팀코드: 06			*/
    		, LA_ANGELS("41")                   /** 팀명: LA Angels					, 팀코드: 41			*/
    		, BALTIMORE_ORIOLES("42")           /** 팀명: Baltimore Orioles			, 팀코드: 42			*/
    		, BOSTON_REDSOX("43")               /** 팀명: Boston Redsox				, 팀코드: 43			*/
    		, CHICAGO_WHITE_SOX("44")           /** 팀명: Chicago White Sox			, 팀코드: 44			*/
    		, CLEVELAND_INDIANS("45")           /** 팀명: Cleveland Indians			, 팀코드: 45			*/
    		, DETROIT_TIGER("46")               /** 팀명: Detroit Tiger				, 팀코드: 46			*/
    		, KANSAS_CITY_ROYALS("47")          /** 팀명: Kansas City Royals			, 팀코드: 47			*/
    		, MINNESOTA_TWINS("49")             /** 팀명: Minnesota Twins				, 팀코드: 49			*/
    		, NEW_YORK_YANKEES("50")            /** 팀명: New York Yankees			, 팀코드: 50			*/
    		, OAKLAND_ATHLETICS("51")           /** 팀명: Oakland Athletics			, 팀코드: 51			*/
    		, SEATTLE_MARINES("52")             /** 팀명: Seattle Marines				, 팀코드: 52			*/
    		, TEXAS_RANGERS("53")               /** 팀명: Texas Rangers				, 팀코드: 53			*/
    		, TORONTO_BLUE_JAYS("54")           /** 팀명: Toronto Blue Jays			, 팀코드: 54			*/
    		, TAMPA_BAY_RAYS("55");             /** 팀명: Tampa Bay Rays				, 팀코드: 55			*/
    		
    		private final String teamCd;
    		
    		private AmericanTeam(String teamCd) {
    			this.teamCd = teamCd;
    		}
    		
    		@Override
    		public String getTeamCd() {
    			return this.teamCd;
    		}
    		
    	}
    	
    }
	
    final String text;
   
    private DisplayEnum(final String text) {
        this.text = text;
    }
    
    public enum NumberEnum{
        ONE(1);

        final int value;
        private   NumberEnum(final int value) {
            this.value = value;
        }
  }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
    
    /**
	 * Interface Result Code;
	 * 
	 * 200 : SUCCESS
	 * 500 : FAIL
	 * 
	 */
	public enum InfRstCd {		
		SUCCESS_CODE("200"), FAIL_CODE("500");
		
    	private final String value;
    	
    	private InfRstCd(final String value) {
    		this.value = value;
    	}
        @Override
        public String toString() {
            return value;
        }   		
	}
	
	public enum MbmMainCtgry {
		
		MEN("MBMA01")
		, WOMEN("MBMA02")
		, CAP("MBMA03")
		, ACC("MBMA04")
		, KIDS("MBMA05")
		, ACC_NEW_ARRIVAL("MBMA04A01")
		, ACC_BIGBALL("MBMA04A08")
		, ACC_SALE("MBMA04A03")
		, ACC_SHOES("MBMA04A04")
		, ACC_BAG("MBMA04A05");
		
		private final String value;
		
		private MbmMainCtgry(final String value) {
    		this.value = value;
    	}
		
        public String getCtgryNo() {
            return value;
        }
        
	}
}

