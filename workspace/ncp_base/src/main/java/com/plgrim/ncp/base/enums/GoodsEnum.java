package com.plgrim.ncp.base.enums; 

/**
 * 반드시 enum 속성은 상수이기 때문에 대문자로 선언 한다. 
 * 예) USER_ID_INPUT_NAME enum를 String값 비교 할때는
 * if (EnumSample.USER_ID_INPUT_NAME.equals("비교값"))
 */
public enum GoodsEnum {

	// GOODS_CONSTANTS
	YES("Y"), NO("N"),	
	MCOM("M00"),							// 자사업체코드	
	KOR("KOR"), ENG("ENG"), CHI("CHI"),
	GOD_TP_GRP("GOD_TP"),					// 상품유형그룹코드
	DEFAULT_TAX_SECT("TAXAT"),				// 과면세 기본값 과세
	DEFAULT_MIN_ORD_QTY("1"),				// 최소 주문수량 기본값
	DEFAULT_MAX_ORD_QTY("999"), 			// 최대 주문수량 기본값
	SUCCESS("success"), FAIL("fail"),
	SUCCESS_CODE("000"), FAIL_CODE("999"),
	RELATESECTCD("ERP_SET"),
	INSTA("insta"),
	NAVER("naver_sh");
	
	/**
	 * 상품 입점구분
	 */	
	public enum GoodsPartmal {
		MCOM, PARTMAL;
	}
	
	/**
	 * 상품 유형
	 * GNRL_GOD : 일반상품
	 * PCHS_GFT : 사입 사은품
	 * CNVRS_GFT : 전환 사은품
	 * SET_GOD : 셋트상품
	 * PCKAGE_GOD : 패키지 상품
	 * GFCT : 상품권
	 * MILE_GOD : 마일리지 상품
	 */
	public enum GoodsType {
		GNRL_GOD, PCHS_GFT, CNVRS_GFT, SET_GOD, PCKAGE_GOD, GFCT, MILE_GOD;
	}
	
	/**
	 * 상품 승인 상태코드
	 * APRV_WAIT : 승인대기
	 * APRV_COMPT : 승인완료
	 * APRV_REJ : 승인 거부
	 */
	public enum GoodsAprvState {
		APRV_WAIT, APRV_COMPT, APRV_REJ;
	}
	
	/**
	 * 번역 상태 코드
	 * TRSLT_WAIT : 번역 대기
     * TRSLT_REQUST : 번역 요청
     * TRSLT_COMPT : 번역 완료 
	 */
	public enum GoodsTransState {
		TRSLT_WAIT, TRSLT_REQUST, TRSLT_COMPT
	}
	
	/**
	 * 해외 전시 상태 코드
	 * DSP_WAIT : 전시 대기
	 * DSP_APRV : 전시 승인
	 * DSP_REJECT : 전시 거부
	 */
	public enum GoodsOvseaDspState {
		DSP_WAIT, DSP_APRV, DSP_REJECT
	}
	
	/**
	 * 시즌 그룹
	 * SS : Spring/Summer
	 * FW : Fall/Winter
	 */	
	public enum GoodsSeasonGroup {
		SS, FW
	}
	
	/**
	 * 상품 판매 상태코드
	 * SALE_PROGRS : 판매중
	 * SMTM_SLDOUT : 일시품절
	 * SLDOUT : 품절
	 * SALE_END : 판매 종료
	 */
	public enum GoodsSaleState {		
		SALE_PROGRS, SMTM_SLDOUT, SLDOUT, SALE_END;
	}
	
	/**
	 * 상품 이미지 코드
	 * THNAIL : 썸네일 이미지
	 * CRLB : 케어라벨 이미지
	 * DETAIL : 상세 이미지
	 */
	public enum GoodsImageType {		
		THNAIL, CRLB, DETAIL;
	}
	
	/**
	 * 상품 이미지 사이즈 코드
	 * 
	 */
	public enum GoodsImageReSizeCd {
		IMG_ORIGIN("ORIGIN"),		// 원본 이미지
		IMG_940x1253("940x1253"),	// PC : 상품상세 모델컷,제품컷
		IMG_828x1104("828x1104"),	// MOBILE : 상품상세 모델컷,제품컷, 추천상품
		IMG_800x1067("800x1067"),	// PC : 상품상세 모델컷,제품컷
		IMG_790x1054("790x1054"),	// PC : 상품상세 모델컷,제품컷
		IMG_750x1000("750x1000"),	// MOBILE : Main-new arrival
		IMG_828x828("828x828"),		// MOBILE : 상품상세 메인컷
		IMG_750x750("750x750"),		// MOBILE : 상품상세 메인컷
		IMG_738x982("738x982"),		// PC : 위시, 최근 본 상품
		IMG_660x660("660x660"),		// MOBILE : 상품상세 룩이 완성되는 상품, 많이 본 상품
		IMG_634x634("634x634"),		// PC MLB : 상품상세 모델컷,제품컷
		IMG_465x600("465x600"),		// MOBILE : 상품상세 모델컷,제품컷, 추천상품
		IMG_450x600("450x600"),		// MOBILE : 대/중/소, 스페셜(대) 카테고리, 위시, 최근 본 상품
		IMG_70x70("70x70"),			// PC MLB : 상품상세 슬라이드
		IMG_371x594("371x594"),		// PC : 스페셜(중) 카테고리
		IMG_371x494("371x494"),		// PC : 대/중/소, 스페셜(대) 카테고리
		IMG_365x487("365x487"),		// MOBILE : 스페셜(중) 카테고리
		IMG_319x426("319x426"),		// PC : 위시, 최근 본 상품
		IMG_311x513("311x513"),		// PC : 스페셜(중) 카테고리
		IMG_311x413("311x413"),		// PC : 대/중/소, 스페셜(대) 카테고리
		IMG_296x465("296x465"),		// PC : 스페셜(중) 카테고리
		IMG_296x365("296x365"),		// PC : 대/중/소, 스페셜(대) 카테고리
		IMG_290x388("290x388"),		// MOBILE : Main-MD`s Choice, Disco 하단
		IMG_290x387("290x387"),		// MOBILE : Main-MD`s Choice, Disco 하단	
		IMG_234x313("234x313"),		// PC : 상품상세 모델컷,제품컷, 추천상품
		IMG_220x293("220x293"),		// PC : Main-new arrival
		IMG_201x270("201x270"),		// PC : 상품상세 모델컷,제품컷, 추천상품
		IMG_200x268("200x268"),		// MOBILE : 장바구니
		IMG_200x267("200x267"),		// MOBILE : 장바구니
		IMG_196x263("196x263"),		// PC : 상품상세 모델컷,제품컷, 추천상품
		IMG_183x244("183x244"),		// PC : Main-new arrival
		IMG_173x231("173x231"),		// PC : Main-new arrival
		IMG_116x154("116x154"),		// PC : Main-MD`s Choice, Disco 하단
		IMG_122x162("122x162"),		// PC : Main-MD`s Choice, Disco 하단
		IMG_107x143("107x143"),		// PC : Main-MD`s Choice, Disco 하단
		IMG_75x100("75x100"),		// PC : 장바구니
		IMG_53x71("53x71"),			// PC : 오늘 본 상품
		IMG_38x51("38x51"),		    // PC : 색상 썸네일
		IMG_35x36("35x36"),		    // PC : 색상 썸네일
		IMG_64x85("64x85"),			// MOBILE : 색상 썸네일
		IMG_126x168("126x168"),		// MOBILE : 색상 썸네일2
		IMG_180x180("180x180");		// MOBILE : 팀컬러
        private final String value;

        private GoodsImageReSizeCd(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
		
	}
	
	/**
	 * 상품 업로드 파일 유형
	 * EXCEL : 엑셀파일
	 * ZIP : zip 파일
	 */
	public enum GoodsUploadFileType {
		EXCEL, ZIP;
	}

	/**
	 * 상품 이미지 등록 유형
	 * ONE : 화면 개별등록
	 * MASS : ZIP 대량 등록
	 * 
	 */
	public enum GoodsUploadImageType {
		ONE, MASS;
	}
	
	/**
	 * 최저가 구분 코드
	 * CSMR_PRC : 실소가
	 * B2E_PRC : B2E가
	 * EMP_PRC : 임직원가
	 * 
	 */
	public enum LwetPrcSect{
		CSMR_PRC, B2E_PRC, EMP_PRC
	}
	
	/**
	 * 상품 포인트 적립 코드
	 * MBSH_PNT : 맴버쉽 포인트
	 * EVT_PNT : 이벤트 포인트
	 * WEB_PNT : 웹 포인트
	 * MILE : 마일리지
	 * NO_ACCML : 적립없음
	 */
	public enum GoodsPntAccmlCd {
		MBSH_PNT, EVT_PNT, WEB_PNT, NO_ACCML;		
	}
	
	/**
	 * 비회원, 등록/수정자id
	 */
	public enum RegtrId{
		GUEST
	}

	/**
	 * 재고연동구분
	 * 
	 * DSP : 판매중 품절 상품 실시간 가용재고
	 * ALL : 오프라인 매장재고
	 * NON_DSP : 임시판매중지 상품 재고연동
	 * DSP_ALL : DSP 전체 대상 재고연동
	 */
	public enum StockQuantitySyncInvIntrlckTpCd{
		DSP, ALL, NON_DSP, DSP_ALL
	}
	
	/**
	 * 추천성별코드
	 */
	public enum RecommendSexCd{
		MEN, WOMEN, CMNUSE, INFNT, CHIL
	}
	
	/**
	 * 상품 공지 구분 코드
	 * 브랜드 공지 : BRND_NOTI
	 * 상품 공지 : GOD_NOTI
	 * 카테고리 공지 : DSP_CTGRY_NOTI
	 */
	public enum GodNotSectCd{
		BRND_NOTI, GOD_NOTI, DSP_CTGRY_NOTI
	}
	
	private final String text;

	private GoodsEnum(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
	
	/**
	 * 상품 이미지 PATH
	 * BO_S3_HTTPS_PATH : BO cloud HTTP 경로
	 * BO_S3_TEMP_UPLOAD_PATH : BO 파일 업로드 임시 저장 경로
	 */
    public enum S3FilePath {
        BO_S3_HTTPS_PATH("ncp_web_bo.cloud.bucket.url"),
        BO_S3_TEMP_UPLOAD_PATH("ncp_web_bo.cloud.bucket.pec.temp.folder.path");

        private final String value;

        private S3FilePath(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    
	/**
	 * 고시정보 카테고리
	 * 
	 * 의류 : PDL_CLTHS
	 * 구두/신발 : PDL_SHOE
	 * 가방 : PDL_BAG
	 * 패션잡화(모자/벨트/액세서리) : PDL_ACSR
	 * 침구류/커튼 : PDL_BEDD
	 * 가구(침대/소파/싱크대/DIY제품) : PDL_FNTR
	 * 주방용품 : PDL_KTCH_SUPLI
	 * 화장품 : PDL_CSMT
	 * 귀금속/보석시계류 : PDL_JWL
	 * 서적 : PDL_BUK
	 * 디지털콘텐츠(음원,게임,인터넷강의등) : PDL_DGT_CONTT
	 * 영유아용품 : PDL_INFNT
	 * 악기 : PDL_ISTM
	 * 스포츠용품 : PDL_SPORTS_SUPLI
	 * 기타 : PDL_ETC
	 */
    public enum PrdNtfcCd {    	
        
    	PDL_CLTHS("의류"),    
    	PDL_SHOE("구두/신발"),
    	PDL_BAG("가방"),
    	PDL_ACSR("패션잡화(모자/벨트/액세서리)"),
    	PDL_BEDD("침구류/커튼"),
    	PDL_FNTR("가구(침대/소파/싱크대/DIY제품)"),
    	PDL_KTCH_SUPLI("주방용품"),
    	PDL_CSMT("화장품"),
    	PDL_JWL("귀금속/보석/시계류"),
    	PDL_BUK("서적"),
    	PDL_DGT_CONTT("디지털콘텐츠(음원,게임,인터넷강의등)"),
    	PDL_ETC("기타재화");

        private final String value;

        private PrdNtfcCd(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    
    //	Magic Number
    public enum Number{
    	ZERO("0"), ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"),
    	SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), HUNDRED("100");
    	
        private final String value;

        private Number(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }    	   
    }
    
    /**
     * OPT_TY_NM : ERP 옵션 유형 명
     */
    public enum DummySize {    	
    	OPT_CD(""), OPT_TY_NM(""), OPT_VAL_CD(""), OPT_VAL_NM("");
    	
    	private final String value;
    	
    	private DummySize(final String value) {
    		this.value = value;
    	}
        @Override
        public String toString() {
            return value;
        }    	
    	
    }
    
    /**
     * 세트/패키지 단품 사이즈 
     */
    public enum PackageSize {
    	OPT_CD("DXP01"), OPT_TY_NM("SIZE"), OPT_VAL_CD("OS"), OPT_VAL_NM("ONE SIZE");

    	private final String value;
    	
    	private PackageSize(final String value) {
    		this.value = value;
    	}
        @Override
        public String toString() {
            return value;
        }    	
    }
    
    /**
     * 재고 동기화 유형
     * 
     * IRDS : 증감
     * DDCT : 차감
     */
    public enum InvSyncTp {
    	IRDS, DDCT;	
    }
    
    /**
     * 재고 동기화 요청 파트
     * 
     * ORD : 주문
     * CLM : 클레임
     * DLV : 배송
     * 	SHOP_DLV : 매장 처리
     * 	WMS_DLV : 센터(WMS) 처리
     */
    public enum InvSyncReqTp {
    	ORD, CLM, DLV, SHOP_DLV, WMS_DLV;
    }
    
    /**
     * 상품 판매몰 ID
     * 
     * DXM : (Discovery-expedition)
     * 
     */    
    public enum SaleMall {
    	DXM("DXM"), MBM("MBM"), SAM("SAM");

    	private final String value;
    	
    	private SaleMall(final String value) {
    		this.value = value;
    	}
        @Override
        public String toString() {
            return value;
        }    	
    }
    
	/**
	 * 오늘본상품 구분
	 * GOD : 상품
	 * EVT : 이벤트
	 * PROMT : 기획전
	 * 
	 */
	public enum TodayGodSectCd{
		GOD, EVT, PROMT ,_TODAYALLLIST;
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
	
	/**
	 * ERP Shop Code;
	 * 
	 * X30004 : Discovery WMS
	 * 
	 */	
    public enum ShopCode {
    	DXM_ONLINE("X30004")
    	, MLB_ONLINE("M510")
    	, MLB_KIDS_ONLINE("I50002")
    	, SAM_ONLINE("A30003");

    	private final String value;
    	
    	private ShopCode(final String value) {
    		this.value = value;
    	}
        @Override
        public String toString() {
            return value;
        }    	
    }
    
	/**
	 * Brand Group Code;
	 * 
	 * DX : Discovery Expedition
	 * ML : MLB
	 * 
	 */	    
	public enum BrandGrpCode{
		DX, ML, SA;
	}
	
	/**
	 * origin
	 * 
	 * KOR
	 * CHI
	 * VIE
	 * IND
	 * 
	 */
	public enum OrgCountryNm {		
		ORG_KOR("한국", "Korea", "韩国")
		, ORG_CHI("중국", "China", "中国")
		, ORG_VIE("베트남", "Vietnam", "越南")
		, ORG_IND("인도네시아", "Indonesia", "印度尼西亚")
		, ORG_CAM("캄보디아", "Cambodia", "柬埔寨")
		, ORG_THA("태국", "Thailand", "泰国")
		, ORG_MAL("말레이시아", "Malaysia", "马来西亚");
		
		private final String kor;
    	private final String eng;
    	private final String chi;
    	
    	private OrgCountryNm(final String kor, final String eng, final String chi) {
    		this.kor = kor;
    		this.eng = eng;
    		this.chi = chi;
    	}
    	public String getKorOrg() {
    		return kor;
    	}
    	public String getEngOrg() {
    		return eng;
    	}
    	public String getChiOrg() {
    		return chi;
    	}
       
	}
}
