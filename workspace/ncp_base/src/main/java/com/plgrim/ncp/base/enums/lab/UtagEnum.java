package com.plgrim.ncp.base.enums.lab;

public enum UtagEnum {

    /**
     * im(상품노출)
     */
    IMPRESSION("im"),
    /**
     * cl(클릭)
     */
    CLICK("cl"),
    /**
     * ca(장바구니담기)
     */
    CART("ca"),
    /**
     * or(주문완료) 
     */
    ORDER("or");

    private final String val;

    private UtagEnum(final String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }

    /////////////////////////////////
    /**
     * 판매지수 유형
     *  - PRMY : B 케이스
     *  - SCDY : C 케이스
     * 
     */
    public enum UtagSaleIdxTp {
        /**
         * 1차 케이스
         */
        PRMY,
        /**
         * 2차 케이스(Deprecated)
         */
        SCDY;
    }

    /**
     * Utag method id
     *  - SALE_IDX_PC : PC 판매지수
     *  - SALE_IDX_MB : MB 판매지수
     */
    public enum UtagLabelId {

        /**
         * SALE_IDX_PC : 10000
         */
        SALE_IDX_PC("10000"),
        SALE_IDX_MB("10001");

        private final String val;

        private UtagLabelId(final String val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val;
        }
    }

    /**
     * Utag method id
     *  - SALE_IDX : 판매지수
     */
    public static UtagMethodId UTAG_METHOD_ID;
    public enum UtagMethodId {

        /**
         * SALE_IDX_OLD : bestold01
         * SALE_IDX_NEW : bestnew01
         */
        SALE_IDX_OLD("bestold01"),
        SALE_IDX_NEW("bestnew01");

        private final String val;

        private UtagMethodId(final String val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val;
        }

        public void setValue(UtagMethodId utagMethodId) {
            UTAG_METHOD_ID = utagMethodId;
        }
        public void setValue() {
            UTAG_METHOD_ID = UtagMethodId.this;
        }
    }
}
