package com.plgrim.ncp.base.enums;

/**
 * 반드시 enum 속성은 상수이기 때문에 대문자로 선언 한다. 예) USER_ID_INPUT_NAME
 * enum를 String값 비교 할때는 if (EnumSample.USER_ID_INPUT_NAME.equals("비교값"))
 */
public enum JsonEnum {
	
	JSON_STATUS_KEY("result"),
    JSON_STATUS_SUCCESS("success"),
    JSON_STATUS_FAIL("fail");

    final String text;

    private JsonEnum(final String text) {
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
