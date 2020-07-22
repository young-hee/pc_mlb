package com.plgrim.ncp.base.enums;

/**
 * 
 * 업무 이관 Enum
 * 
 */
public enum CsoCnsltDetailEnum {


	// GOODS_CONSTANTS
		YES("Y"), NO("N");

		/**
		 * 삭제 여부
		 */
		public enum deleteYn {
			Y, N;
		}

		/**
		 * 상담 상태 코드
		 ㅁ. 상담의 처리여부는 처리상태로 관리한다
		 >. 처리 대기 : 상담 내용 입력 후 ‘처리대기’ 버튼으로 저장
		 >. 처리 완료 : 상담 내용 입력 후 ‘처리완료’ 버튼으로 저장

		 ㅁ. 상담 상태 : CNSLT_STAT
		 >. 처리 대기 : PRCS_WAIT
		 >. 임시 저장 : TMPR_SAVE
		 >. 처리 완료 : PROC_COMPT
		 */
		public enum cnsltDetailStat {

			PRCS_WAIT, TMPR_SAVE, PROC_COMPT;

		}


		private final String text;

		private CsoCnsltDetailEnum(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}

}
