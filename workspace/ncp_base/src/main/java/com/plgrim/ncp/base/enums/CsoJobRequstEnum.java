package com.plgrim.ncp.base.enums;

/**
 * 
 * 업무 이관 Enum
 * 
 */
public enum CsoJobRequstEnum {


	// GOODS_CONSTANTS
		YES("Y"), NO("N");

		/**
		 * 이관 상태 코드
			ㅁ. 이관 상태 : TRANS_STAT
			   >. 이관 대기 : TRANS_WAIT
			   >. 이관 취소 : TRANS_CNCL
			   >. 이관 완료 : TRANS_COMPT

		 */	
		public enum transStatCd {
			
			TRANS_WAIT, TRANS_CNCL, TRANS_COMPT;
			
		}
		
		
		/**
		 * 
		 * 중요도 코드
			ㅁ. 중요도 : DOI
			   >. 즉시 : IMDTL
			   >. 긴급 : EMERG
			   >. 일반 : GNRL
		 *
		 */

		public enum doi {
			DOI, IMDTL, EMERG, GNRL;
		}

		/**
		 * ㅁ. 언어 : LANG
		 >. 한국어 : KOR
		 >. 중국어 : CHI
		 >. 영어 : ENG
		 >. 일본어 : JPN
		 */
		public enum lang {
			KOR, CHI, ENG, JPN;
		}

		/**
		 * 몰 ID
		 */
		public enum mall{
			DXM
		}




		
		private final String text;

		private CsoJobRequstEnum(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}

}
