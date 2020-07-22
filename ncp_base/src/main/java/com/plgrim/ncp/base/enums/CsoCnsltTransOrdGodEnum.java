package com.plgrim.ncp.base.enums;

/**
 * 
 * 업무 이관 Enum
 * 
 */
public enum CsoCnsltTransOrdGodEnum {


	// GOODS_CONSTANTS
		YES("Y"), NO("N");

		/**
		 * 이관 상태 코드
		 */	
		public enum ordGodSectCd {
			
			ORD, GOD;
			
		}
		
		private final String text;

		private CsoCnsltTransOrdGodEnum(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}

}
