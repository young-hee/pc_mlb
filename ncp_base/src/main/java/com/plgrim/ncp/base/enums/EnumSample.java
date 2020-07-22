package com.plgrim.ncp.base.enums;

/**
 * 반드시 enum 속성은 상수이기 때문에 대문자로 선언 한다. 예) USER_ID_INPUT_NAME
 * enum를 String값 비교 할때는 if (EnumSample.USER_ID_INPUT_NAME.equals("비교값"))
 */
public enum EnumSample {

    /* 로그인 페이지로 넘길때 현재 URL 정보를 넘길때 파라미터 이름 */
    ENUM("loginTarget"),
    /* 로그인 사용자 파라미터 (input type=test)*/
    USER_ID_INPUT_NAME("userId"),
    /* 로그인 사용자 패스워드 파라미터 (input type=test)*/
    PASSWORD_INPUT_NAME("password"),
    /* 로그인 ajax 사용 유무 헤더 파라미터 */
    HEADER_AJAX_LOGIN("ajax-login");

    final String text;

    private EnumSample(final String text) {
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
