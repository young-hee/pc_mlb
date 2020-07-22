package com.plgrim.ncp.base.enums.member;

public enum RecommendSizeApplyEnum {
   
	WOMEN("1"),     // 여성 기본 사이즈
    MEN("2"),       // 남성 기본 사이즈
    KIDS("3"),      // 키즈 기본 사이즈
	ALL("4"),      // 전체 기본 사이즈
	DIRECT("5");   // 쿠키 정보
  
	//	제휴 대행 업체 코드(SMT)
    public enum purchase {    	
        
        UP("UP"),
		DOWN("DOWN"),
		SET("SET");

        private final String value;

        private purchase(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
	
   final String text;

    private RecommendSizeApplyEnum(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}