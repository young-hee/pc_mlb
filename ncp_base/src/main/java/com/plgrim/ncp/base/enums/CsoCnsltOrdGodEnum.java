package com.plgrim.ncp.base.enums;

/**
 * 
 * 업무 이관 Enum
 * 
 */
public enum CsoCnsltOrdGodEnum {


	// GOODS_CONSTANTS
		YES("Y"), NO("N");

		/**
		 * 주문, 상품 구분 코드
		 */	
		public enum ordGodSect {
		
			ORD,GOD;
			
		}
		
		private final String text;

		private CsoCnsltOrdGodEnum(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}

}
