package com.plgrim.ncp.base.enums;

/**
 * 
 * 업무 이관 Enum
 * 
 */
public enum CsoJobRequstOrdGodEnum {


	// GOODS_CONSTANTS
		YES("Y"), NO("N");

			
		public enum ordGodSectCd {
			ORD, GOD;
		}
		
		
		private final String text;

		private CsoJobRequstOrdGodEnum(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}

}
