package com.plgrim.ncp.base.enums;

/**
 * 
 * 업무 이관 Enum
 * 
 */
public enum CounselTransferBoardEnum {


	// GOODS_CONSTANTS
		YES("Y"), NO("N");

		/**
		 * 이관 상태 코드
		 */	
		public enum transStat {
		
			TRANS_WAIT, TRANS_CNCL, TRANS_COMPT;
			
		}
		
		/**
		 * 주문, 상품 구분 코드
		 */	
		public enum ordGodSect {
		
			ORD,GOD;
			
		}
		
		private final String text;

		private CounselTransferBoardEnum(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}

}
