package com.plgrim.ncp.base.enums;

/**
 * 
 * 업무 이관 Enum
 * 
 */
public enum CsoCnsltTransEnum {


	// GOODS_CONSTANTS
		YES("Y"), NO("N");

		/**
		 * 이관 상태 코드
		 ㅁ. 이관 상태 : TRANS_STAT
		 >. 이관 대기 : TRANS_WAIT
		 >. 이관 취소 : TRANS_CNCL
		 >. 이관 완료 : TRANS_COMPT

		 */
		public enum transStat {

			TRANS_STAT, TRANS_WAIT, TRANS_CNCL, TRANS_COMPT

		}

		/**
		 *이관 대상 코드
		 ㅁ. 상담 이관 대상 : 상담을 타 업체, 업무협업, 상담사 에게 상담을 이관 하는 행위의 구분

		 ㅁ. 이관 대상 : TRANS_TGT
		 >. 상담원 이관 : CNSLR_TRANS
		 >. 업체 이관 : COM_TRANS
		 >. 업무 이관 : JOB_TRANS
		 */
		public enum transTgt{
			CNSLR_TRANS, COM_TRANS, JOB_TRANS
		}

		private final String text;

		private CsoCnsltTransEnum(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}

}
