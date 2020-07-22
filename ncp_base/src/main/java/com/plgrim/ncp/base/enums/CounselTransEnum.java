package com.plgrim.ncp.base.enums;

/**
 * 
 * 업무 이관 Enum
 * 
 */
public enum CounselTransEnum {


	// GOODS_CONSTANTS
		YES("Y"), NO("N");

		/**
		 * 이관 상태 코드
		 */	
		public enum transTgt {
			
			CNSLR_TRANS, COM_TRANS, JOB_TRANS;
			
		}
		
		public enum transStat{
			TRANS_WAIT, TRANS_CNCL, TRANS_COMPT;
		}
		
		public enum chrgerCnfirmYn{
			Y, N;
		}
		
		private final String text;

		private CounselTransEnum(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}

}
