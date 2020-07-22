package com.plgrim.ncp.base.enums;

/**
 * 
 * 업무 이관 Enum
 * 
 */
public enum CsoPromsclEnum {


	// GOODS_CONSTANTS
		YES("Y"), NO("N");

		/**
		 * 약속콜 상태 코드
			ㅁ. 약속콜 상태 : PROMSCL_STAT
			   >. 약속 대기 : PROMS_WAIT
			   >. 약속 지연 : PROMS_DELAY
			   >. 약속 완료 : PROMS_COMPT
			   >. 약속 취소 : PROMS_CNCL
			
			ㅁ. 약속 지연의 경우 약속콜 일시로 판단 해야 하는 부분임.
		 */	
		public enum promsclStatCd {
		
			PROMSCL_STAT, PROMS_WAIT,PROMS_DELAY, PROMS_COMPT, PROMS_CNCL;
			
		}
		

		/**
		 * 
		 * 약속콜 설정 여부
			ㅁ. 상담을 진행 하면서 고객에게 다시 전화를 걸기로 한 경우 설정 함
			
			ㅁ. 약속 전화 설정 여부
			   >. 약속콜 : Y
			   >. 일반콜 : N
			
			ㅁ. 예약콜이 설정되면 반드시 예약콜이 존재하도록 해야 한다.
		 *
		 */
		public enum promsclConfigYn{
			Y, N;
		}
		
		/**
		 * 담당자 확인 여부
		 */
		public enum chrgerCnfirmYn{
			Y, N;
		}
		
		private final String text;

		private CsoPromsclEnum(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}

}
