package com.plgrim.ncp.base.enums;

/**
 * 
 * 업무 이관 Enum
 * 
 */
public enum CsoCnsltTransHistEnum {


	// GOODS_CONSTANTS
		YES("Y"), NO("N");

		/**
		 * 이관 상태 코드
		 */	
		public enum TRANS_MOD_INFO_CD {
			
			TRANS_MOD_INFO, TRANS_RECR_ID;
			
		}
		
		public enum MOD_TP_CD{
			MOD_TP, REG, INQIRE, UDT, DELETE;
		}
		
		
		private final String text;

		private CsoCnsltTransHistEnum(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}

}
