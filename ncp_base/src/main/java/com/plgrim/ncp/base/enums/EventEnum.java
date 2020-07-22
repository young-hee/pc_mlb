package com.plgrim.ncp.base.enums;


public enum EventEnum {

    // PROMOTION_CONSTANTS
    YES("Y"), NO("N");

    private final String value;

    private EventEnum(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
    
    /**
     * 경품 유형 : EVT_TP
     * 
     *   - ALL_DRWT  : 전체추첨
     *   - ALL_PRIZE : 전체당첨
     *   - PCH_DRWT  : 구매추첨
     *   - PCH_PRIZE : 구매당첨
     *   - TM_CPN    : 타임쿠폰
     *   - SNS_REPLY : SNS댓글
     *   - SNS_CNRS : SNS공유
     *   - ATEND_CHK : 출석체크
     *   - STMP      : 스탬프
     */
    public enum EventType {
          ALL_DRWT  
        , ALL_PRIZE 
        , PCH_DRWT  
        , PCH_PRIZE 
        , TM_CPN    
        , SNS_REPLY 
        , SNS_CNRS
        , ATEND_CHK
        , STMP;
    }
    

    /**
     * 경품 종류 : FREE_GIFT_KIND
     *   - ACTUAL THING : 현물
     *   - COUPON       : 쿠폰
     *   - POINT        : 포인트
     */
    public enum EventFreeGiftKind {
        ACTUAL_THING("ACTHNG"), COUPON("CPN"), POINT("PNT"), WEB_POINT("WEBPNT");

        private final String value;

        private EventFreeGiftKind(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    
    /**
     * 대표 하위 구분 : RPRST_LWPRT_SECT
     * 
     *   - LWPRT_EVT : 하위 이벤트
     *   - RPRST_EVT : 대표 이벤트
     * 
     */
    public enum EventRprstLwprtSect {
        LWPRT_EVT, RPRST_EVT;
    }
    
    /**
     * 이벤트 응모회원 구분
     */
    public enum EventApplcnMbrSect {
        MBR, NMBR, NMBR_EMP;
    }
    
    /**
     * 이벤트 결과 코드
     * 
     *   - NOT_LOGIN            : 비로그인
     *   - INVALID_DEVICE       : 유효하지 않은 디바이스
     *   - INVALID_MEMBER       : 유효하지 않은 회원
     *   - INVALID_APPLCN_DATE  : 유효하지 않은 응모일시
     *   - NO_MORE_WINNER       : 당첨가능 인원 없음
     *   - NO_MORE_CHANCE       : 응모기회 없음 
     *   - SUCCESS              : 성공
     *   - UNITY_MBR_ONLY       : 멤버쉽 회원만 가능
     *   - ONLINE_MBR_ONLY      : 온라인 회원만 가능
     *   - ERROR                : 에러
     *   - NOT_PARTCPTN_TGT     : 참여 대상자가 아님
     *   - CHG_GIFT_SUCCESS     : 경품변경성공
     *   - MULTI_TRY_PART_SUCCESS : 한번에 여러 건의 경품을 받는 도중 부분적으로 성공한 경우
     */
    public enum EventResultCode {
          NOT_LOGIN
        , INVALID_DEVICE
        , INVALID_MEMBER
        , INVALID_APPLCN_DATE
        , NO_MORE_WINNER
        , NO_MORE_CHANCE
        , SUCCESS
        , UNITY_MBR_ONLY
        , ONLINE_MBR_ONLY
        , ERROR
        , NOT_PARTCPTN_TGT
        , CHG_GIFT_SUCCESS
        , MULTI_TRY_PART_SUCCESS;
    }
    
    /**
     * 응모 횟수 구분 코드
     * 
     *   - EVT_STDR_1   : 이벤트 기준 1회
     *   - ONE_DAY_1BT  : 1일 1회
     *   - UNLMIT       : 무제한
     *
     */
    public enum EventApplcnCountSect {
          EVT_STDR_1BT("EVT_STDR_1BT")
        , ONE_DAY_1BT("1DAY_1BT")
        , UNLMIT("UNLMIT");

        private final String value;
        private EventApplcnCountSect(final String value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return value;
        }
    }
    
    /**
     *  이벤트 디바이스
     *  
     *   - PC
     *   - MOBILE_WEB
     *   - MOBILE_APP
     */
    public enum EventDevice {
        PC, MOBILE_WEB, MOBILE_APP;
    }
    
    
    /**
     *  SNS 유형 코드
     *  
     *   >. Facebook : FACEBUK
     *   >. Twitter : TWTR
     *   >. me2day : M2DY
     *   >. Pinterest : PNTRST
     *   >. Instagram : INSTGRM
     *   >. KakaoTalk : KKOTK
     *   >. KakaoStory : KKOST
     */
    public enum MbrSnsCd {
    	FACEBUK
    	, TWTR
    	, M2DY
    	, PNTRST
    	, INSTGRM
    	, KKOTK
    	, KKOST;
    }
}
