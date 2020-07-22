package com.plgrim.ncp.base.enums;

/**
 * 
 * 업무 이관 Enum
 * 
 */
public enum MemberBiasEnum {


	// GOODS_CONSTANTS
		YES("Y"), NO("N");

		/**
		 * 메모 유형
		 */	
		public enum memoTp {
		
			ORD, CLM, MBR, CSTMR_INCLN;
			
		}
		
		
		
		private final String text;

		private MemberBiasEnum(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}

}
