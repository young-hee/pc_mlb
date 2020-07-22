package com.plgrim.ncp.framework.enums;

/**
 * 시큐리티에서 사용하는 상수들을 정의하는 enum.
 */
public enum  SecurityParam {

    /* 로그인 페이지로 넘길때 현재 URL 정보를 넘길때 파라미터 이름 */
    TARGET_PARAM_NAME("loginTarget"),
    /* 로그인 사용자 파라미터 (input type=text)*/
    USER_ID_INPUT_NAME("userId"),
    /* 로그인 사용자 패스워드 파라미터 (input type=password)*/
    PASSWORD_INPUT_NAME("password"),
    /* 로그인 ajax 사용 유무 헤더 파라미터 */
    HEADER_AJAX_LOGIN("ajax-login"),
    /* 로그인시 휴면 해제 여부 */
    RELEASE_DRMNCY("releaseDrmncy"),
    LOGIN_POPUP_ENALBED("pageMode"),
    DEFAULT_ROLE("ROLE_USER"),
    ADMIN_ROLE("ROLE_ADM"),
    
    /**
     * ROLE_GUEST는 비회원 인증 절차를 거친 사용자만 가질수 있음
     */
    GUEST_ROLE("ROLE_GUEST"),
    FAIL_MESSAGE("FAIL_MESSAGE");


    final String text;

    private SecurityParam(final String text) {
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
