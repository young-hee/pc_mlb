package com.plgrim.ncp.base.enums;

/**
 * 
 * 업무 이관 Enum
 * 
 */
public enum CsoCnsltDetailHistEnum {


	// GOODS_CONSTANTS
		YES("Y"), NO("N");

		/**
		 * 변경 정보 코드
			ㅁ. 변경 정보 : MOD_INFO
			   >. 상담 상세 상태 코드 : CNSLT_DETAIL_STAT_CD
			   >. 상담 상세 내용 : CNSLT_DETAIL_CONT
		 */	
		public enum modInfo {
			
			CNSLT_DETAIL_STAT_CD, CNSLT_DETAIL_CONT;
			
		}
		
		
		/**
		 * 
		 * 변경 유형 코드
			ㅁ. 변경 유형 : MOD_TP
			   >. 등록 : REG
			   >. 조회 : INQIRE
			   >. 수정 : UDT
			   >. 삭제 : DELETE

		 *
		 */
		public enum modTp {
			REG, INQIRE, UDT, DELETE;
		}
		
		private final String text;

		private CsoCnsltDetailHistEnum(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}

}
