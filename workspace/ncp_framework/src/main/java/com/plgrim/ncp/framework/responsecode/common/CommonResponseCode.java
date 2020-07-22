package com.plgrim.ncp.framework.responsecode.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.plgrim.ncp.framework.messages.MessageUtils;
import com.plgrim.ncp.framework.responsecode.ResponseCode;
import com.plgrim.ncp.framework.responsecode.ResponseException;
import com.plgrim.ncp.framework.responsecode.ResponseType;
import com.plgrim.ncp.framework.systems.BusinessType;

import lombok.Getter;

public enum CommonResponseCode implements ResponseCode { 
	//@formatter:off
	// 그룹명, 설명의 순서로 정렬됩니다. 코드가 일련번호로 추가 되어도 정렬된 상태로 보실수 있습니다. 



	//AF -- 제휴



	//CL -- 클레임

	CL_00014_교환_ERP_허브_생성_성공,
	CL_00013_교환_ERP_허브_생성_시도,
	CL_40010_교환_ERP_허브_생성_실패,
	CL_00016_교환_RETAIL_허브_생성_성공,
	CL_00015_교환_RETAIL_허브_생성_시도,
	CL_40011_교환_RETAIL_허브_생성_실패,
	CL_00006_교환_접수_성공,
	CL_00005_교환_접수_시도,
	CL_40006_교환_접수_실패,
	CL_00030_무료_수선_조회_성공,
	CL_00029_무료_수선_조회_시도,
	CL_40018_무료_수선_조회_실패,
	CL_00010_반품_ERP_허브_생성_성공,
	CL_00009_반품_ERP_허브_생성_시도,
	CL_40008_반품_ERP_허브_생성_실패,
	CL_00012_반품_RETAIL_허브_생성_성공,
	CL_00011_반품_RETAIL_허브_생성_시도,
	CL_40009_반품_RETAIL_허브_생성_실패,
	CL_00018_반품_교환_ERP_ERP_전송_성공,
	CL_00017_반품_교환_ERP_ERP_전송_시도,
	CL_40012_반품_교환_ERP_ERP_전송_실패,
	CL_00020_반품_교환_RETAIL_ERP_전송_성공,
	CL_00019_반품_교환_RETAIL_ERP_전송_시도,
	CL_40013_반품_교환_RETAIL_ERP_전송_실패,
	CL_00022_반품_교환_RETAIL_SOAP_허브_생성_성공,
	CL_00021_반품_교환_RETAIL_SOAP_허브_생성_시도,
	CL_40014_반품_교환_RETAIL_SOAP_허브_생성_실패,
	CL_00024_반품_교환_RETAIL_전송_결과_BO_반영_성공,
	CL_00023_반품_교환_RETAIL_전송_결과_BO_반영_시도,
	CL_40015_반품_교환_RETAIL_전송_결과_BO_반영_실패,
	CL_00004_반품_접수_성공,
	CL_00003_반품_접수_시도,
	CL_40005_반품_접수_실패,
	CL_00026_불량_수선_성공,
	CL_00025_불량_수선_시도,
	CL_40016_불량_수선_실패,
	CL_00028_불량_수선_조회_성공,
	CL_00027_불량_수선_조회_시도,
	CL_40017_불량_수선_조회_실패,
	CL_40004_취소_ERP_ERP_전송_실패,
	CL_40003_취소_ERP_허브_생성_실패,
	CL_00002_취소_접수_성공,
	CL_00001_취소_접수_시도,
	CL_40001_취소_접수_실패,
	CL_40002_취소_환불_실패,
	CL_00008_환불_성공,
	CL_00007_환불_시도,
	CL_40007_환불_실패,



	//DL -- 배송

	DL_00001_X0_배정_성공,
	DL_00002_X0_배정_성공_배정대기,
	DL_50001_X0_배정_수행_실패,
	DL_40001_X0_배정_실패_결품접수,
	DL_00901_X0_상품수령완료_성공,
	DL_40901_X0_상품수령완료_실패,



	//DP -- 전시

	DP_00011_ABTEST_FO적용_성공,
	DP_00010_ABTEST_FO적용_시작,
	DP_40005_ABTEST_FO적용_실패,
	DP_00008_ABTEST_feature_저장_성공,
	DP_40004_ABTEST_feature_저장_실패,
	DP_00015_ABTEST_기간수정_성공,
	DP_40009_ABTEST_기간수정_실패,
	DP_00002_ABTEST_기본정보등록_성공,
	DP_00022_ABTEST_기본정보등록_시작,
	DP_40001_ABTEST_기본정보등록_실패,
	DP_00016_ABTEST_기본정보삭제_성공,
	DP_40010_ABTEST_기본정보삭제_실패,
	DP_00014_ABTEST_기본정보수정_성공,
	DP_40008_ABTEST_기본정보수정_실패,
	DP_00007_ABTEST_노출설정_저장_성공,
	DP_00006_ABTEST_노출설정_저장_시작,
	DP_40003_ABTEST_노출설정_저장_실패,
	DP_00005_ABTEST_대조군실험군_REV_등록,
	DP_00004_ABTEST_대조군실험군_REV_삭제,
	DP_00003_ABTEST_대조군실험군_REV_수정,
	DP_40002_ABTEST_대조군실험군_REV_저장_실패,
	DP_00009_ABTEST_세그먼트노출비율_조회,
	DP_00012_ABTEST_수정완료_성공,
	DP_40006_ABTEST_수정완료_실패,
	DP_00013_ABTEST_수정취소_성공,
	DP_40007_ABTEST_수정취소_실패,
	DP_00020_CONTT_ABTEST_미리보기_컨텐츠_복사_성공,
	DP_40015_CONTT_ABTEST_미리보기_컨텐츠_복사_실패,
	DP_00021_CONTT_미리보기_운영적용_성공,
	DP_40016_CONTT_미리보기_운영적용_실패,
	DP_00019_CONTT_임시저장_미리보기_컨텐츠_복사_성공,
	DP_40014_CONTT_임시저장_미리보기_컨텐츠_복사_실패,
	DP_00017_CTGRY_카테고리_등록_성공,
	DP_40011_CTGRY_카테고리_등록_실패,
	DP_00018_CTGRY_카테고리_수정_성공,
	DP_40012_CTGRY_카테고리_수정_실패,
	DP_40013_CTGRY_카테고리_수정_실패_상위카테고리전시여부,
	DP_00001_RECOPICK_조회결과,



	//GD -- 상품

	GD_00016_AFS사입사은품_판매출고내역_호출,
	GD_00019_SPLIT_재고_배치_실행,
	GD_00018_매장_가용재고_조회_호출,
	GD_00007_브랜드_태그_수신,
	GD_00005_브랜드그룹_코드_수신,
	GD_00006_브랜드코드_수신,
	GD_00008_상품마스터_정보_수신,
	GD_00009_상품마스터_정보_조회_호출,
	GD_00015_상품실측사이즈_조회,
	GD_00013_세탁코드수신,
	GD_00001_업체등록_수신,
	GD_00002_업체유형코드_변경_수신,
	GD_00017_오프라인_가격할인_조회_호출,
	GD_00014_원산지차수_수신,
	GD_00004_임직원_관리_브랜드_이력수신,
	GD_00011_품목그룹_코드정보_수신,
	GD_00010_품목그룹매핑_수신,
	GD_00012_품목코드_수신,



	//MB -- 회원

	MB_00076_5회_이상_실패로_계정잠김,
	MB_40031_ERP_주소항목확인,
	MB_00077_ID_존재하지_않음,
	MB_00072_SMS_인증_불필요,
	MB_00079_SMS_인증_재전송_필요,
	MB_00071_SMS_인증_필요,
	MB_00080_SMS_인증번호_전송_성공,
	MB_00081_SMS_인증번호_전송_실패,
	MB_00111_SNS_네이버_로그인_성공,
	MB_00110_SNS_네이버_로그인_시도,
	MB_40045_SNS_네이버_로그인_실패,
	MB_00112_SNS_네이버_로그인_연동_정보_없음,
	MB_00109_SNS_네이버_연동_정보_조회_성공,
	MB_00108_SNS_네이버_연동_정보_조회_시도,
	MB_40044_SNS_네이버_연동_정보_조회_실패,
	MB_00106_SNS_페이스북_로그인_성공,
	MB_00105_SNS_페이스북_로그인_시도,
	MB_40043_SNS_페이스북_로그인_실패,
	MB_00107_SNS_페이스북_로그인_연동_정보_없음,
	MB_00104_SNS_페이스북_연동_결과_페이지_진입_성공,
	MB_00103_SNS_페이스북_연동_결과_페이지_진입_시도,
	MB_40042_SNS_페이스북_연동_결과_페이지_진입_실패,
	MB_00102_SNS_페이스북_연동_정보_조회_성공,
	MB_00101_SNS_페이스북_연동_정보_조회_시도,
	MB_40041_SNS_페이스북_연동_정보_조회_실패,
	MB_00001_로그인_성공,
	MB_40001_로그인_실패,
	MB_00004_오프라인_회원_가입유무_체크_호출,
	MB_40003_오프라인_회원_가입유무_체크_호출_실패,
	MB_00084_오프라인매장탈퇴_불가,
	MB_00083_오프라인매장탈퇴_성공,
	MB_00082_오프라인매장탈퇴_시도,
	MB_40032_오프라인매장탈퇴_실패,
	MB_00003_온라인회원가입_성공,
	MB_00002_온라인회원가입_시도,
	MB_40002_온라인회원가입_실패,
	MB_00075_인증번호_불일치,
	MB_00074_인증번호_일치,
	MB_00024_임직원_메일_발송_성공,
	MB_00023_임직원_메일_발송_시도,
	MB_40007_임직원_메일_발송_실패,
	MB_00070_임직원_사번_정보조회_성공,
	MB_00069_임직원_사번_정보조회_시도,
	MB_40030_임직원_사번_정보조회_실패,
	MB_00064_임직원_싱글인증_성공,
	MB_00063_임직원_싱글인증_시도,
	MB_40027_임직원_싱글인증_실패,
	MB_00068_임직원_인증코드_발송_성공,
	MB_00067_임직원_인증코드_발송_시도,
	MB_40029_임직원_인증코드_발송_실패,
	MB_00066_임직원_전환_성공,
	MB_00065_임직원_전환_시도,
	MB_40028_임직원_전환_실패,
	MB_00017_통합포인트_내역_조회_호출,
	MB_00018_통합포인트_조정사유_검색_호출,
	MB_00026_통합회원_메일_수신거부_성공,
	MB_00025_통합회원_메일_수신거부_시도,
	MB_40008_통합회원_메일_수신거부_실패,
	MB_00060_통합회원_멤버십_포인트_조회_성공_BO포인트상세,
	MB_00058_통합회원_멤버십_포인트_조회_성공_BO포인트탭,
	MB_00062_통합회원_멤버십_포인트_조회_성공_마이페이지,
	MB_00054_통합회원_멤버십_포인트_조회_성공_마일리지,
	MB_00048_통합회원_멤버십_포인트_조회_성공_멤버십_포인트_리스트,
	MB_00052_통합회원_멤버십_포인트_조회_성공_포인트,
	MB_00056_통합회원_멤버십_포인트_조회_성공_포인트_마일리지_잔액,
	MB_00059_통합회원_멤버십_포인트_조회_시도_BO포인트상세,
	MB_00057_통합회원_멤버십_포인트_조회_시도_BO포인트탭,
	MB_00061_통합회원_멤버십_포인트_조회_시도_마이페이지,
	MB_00053_통합회원_멤버십_포인트_조회_시도_마일리지,
	MB_00047_통합회원_멤버십_포인트_조회_시도_멤버십_포인트_리스트,
	MB_00051_통합회원_멤버십_포인트_조회_시도_포인트,
	MB_00055_통합회원_멤버십_포인트_조회_시도_포인트_마일리지_잔액,
	MB_40025_통합회원_멤버십_포인트_조회_실패_BO포인트상세,
	MB_40024_통합회원_멤버십_포인트_조회_실패_BO포인트탭,
	MB_40026_통합회원_멤버십_포인트_조회_실패_마이페이지,
	MB_40022_통합회원_멤버십_포인트_조회_실패_마일리지,
	MB_40019_통합회원_멤버십_포인트_조회_실패_멤버십_포인트_리스트,
	MB_40021_통합회원_멤버십_포인트_조회_실패_포인트,
	MB_40023_통합회원_멤버십_포인트_조회_실패_포인트_마일리지_잔액,
	MB_00020_통합회원_수선내역_조회_호출,
	MB_00019_통합회원_수선조회_호출,
	MB_00016_통합회원_오프라인_상세정보_조회_호출,
	MB_00022_통합회원_오프라인_최종구매일_조회_호출,
	MB_00015_통합회원_오프라인매장구매내역_조회_호출,
	MB_00014_통합회원_정보변경_결과_호출,
	MB_00008_통합회원_정보변경_성공,
	MB_00007_통합회원_정보변경_시도,
	MB_40005_통합회원_정보변경_실패,
	MB_00012_통합회원_정보변경_조회_호출,
	MB_00013_통합회원_정보변경_호출,
	MB_00010_통합회원_탈퇴_성공,
	MB_00009_통합회원_탈퇴_시도,
	MB_40006_통합회원_탈퇴_실패,
	MB_00006_통합회원가입_성공,
	MB_00005_통합회원가입_시도,
	MB_40004_통합회원가입_실패,
	MB_00011_통합회원이름_변경_호출,
	MB_00021_통합회원탈퇴처리_호출,
	MB_00034_회원_BO_정보변경_성공,
	MB_00033_회원_BO_정보변경_시도,
	MB_40012_회원_BO_정보변경_실패,
	MB_00036_회원_BO_탈퇴_성공,
	MB_00035_회원_BO_탈퇴_시도,
	MB_40013_회원_BO_탈퇴_실패,
	MB_00046_회원_마일리지_포인트_환원_성공,
	MB_00045_회원_마일리지_포인트_환원_시도,
	MB_40018_회원_마일리지_포인트_환원_실패,
	MB_00042_회원_수선이력_조회_성공,
	MB_00041_회원_수선이력_조회_시도,
	MB_40016_회원_수선이력_조회_실패,
	MB_00044_회원_수선이력엑셀_조회_성공,
	MB_00043_회원_수선이력엑셀_조회_시도,
	MB_40017_회원_수선이력엑셀_조회_실패,
	MB_00030_회원_이메일_SMS_발송_성공,
	MB_00029_회원_이메일_SMS_발송_시도,
	MB_40010_회원_이메일_SMS_발송_실패,
	MB_00032_회원_이메일_수신동의거부_성공,
	MB_00031_회원_이메일_수신동의거부_시도,
	MB_40011_회원_이메일_수신동의거부_실패,
	MB_00040_회원_탈퇴조건_조회_성공,
	MB_00039_회원_탈퇴조건_조회_시도,
	MB_40015_회원_탈퇴조건_조회_실패,
	MB_00038_회원_포인트정보_조회_성공,
	MB_00037_회원_포인트정보_조회_시도,
	MB_40014_회원_포인트정보_조회_실패,
	MB_00028_회원_휴대전화번호_수정_성공,
	MB_00027_회원_휴대전화번호_수정_시도,
	MB_40009_회원_휴대전화번호_수정_실패,
	MB_00073_휴대폰번호_인증_필요,
	MB_00078_휴대폰번호_존재하지_않음,



	//OD -- 주문

	OD_40015_PG_승인_결과_없음,
	OD_00008_PG_승인_성공,
	OD_00007_PG_승인_시작,
	OD_40039_X0_는_적용_불가한_프로모션,
	OD_40023_X0_승인_비정상_실패,
	OD_40022_X0_승인_정상_실패,
	OD_40024_X0_승인_취소_실패,
	OD_40013_X0_은_사용된_쿠폰,
	OD_40005_가용_P포인트_사용_초과,
	OD_40042_결제_인증실패,
	OD_40020_결제번호_이상,
	OD_40006_고객결제금액과_서버결제금액_상이,
	OD_40028_구매이력_HUB_연결_실패,
	OD_40030_구매이력_HUB_전송_결과_저장_실패,
	OD_00006_구매이력_HUB_전송_성공,
	OD_00005_구매이력_HUB_전송_시작,
	OD_40029_구매이력_HUB_전송_실패,
	OD_40025_구매이력_실시간_연결_실패,
	OD_40027_구매이력_실시간_전송_결과_저장_실패,
	OD_00004_구매이력_실시간_전송_성공,
	OD_00003_구매이력_실시간_전송_시작,
	OD_40026_구매이력_실시간_전송_실패,
	OD_40003_금액_100원_미만_결제_불가,
	OD_40016_멤버쉽_포인트_잔액_부족,
	OD_40008_멤버쉽포인트_사용금액과_분배금액_불일치,
	OD_40014_배송비_쿠폰_적용_불가,
	OD_40038_배송정책_정합성_에러,
	OD_40002_상품권_주문시_멤버십_포인트_사용_불가,
	OD_40004_실소가보다_P포인트_사용금액이_큼,
	OD_40021_PG_상점ID_크로스키_획득_실패,
	OD_40040_PG_결제번호_상이,
	OD_40041_PG_결제수단_상이,
	OD_00014_장바구니_등록_성공,
	OD_00013_장바구니_등록_시작,
	OD_40033_장바구니_등록_실패,
	OD_00016_장바구니_수량변경_성공,
	OD_00015_장바구니_수량변경_시작,
	OD_40034_장바구니_수량변경_실패,
	OD_00018_장바구니_옵션변경_성공,
	OD_00017_장바구니_옵션변경_시작,
	OD_40035_장바구니_옵션변경_실패,
	OD_00012_장바구니_조회_성공,
	OD_00011_장바구니_조회_시작,
	OD_40032_장바구니_조회_실패,
	OD_40012_장바구니_쿠폰_최소구매금액_조건_미달,
	OD_40037_주문_대상_상품이_미_존재,
	OD_40018_주문_상품정보_변경,
	OD_00002_주문_성공,
	OD_00001_주문_시작,
	OD_40011_주문_실명인증_진행_실패,
	OD_40001_주문_실패,
	OD_40017_주문_중복_진행,
	OD_00010_주문서_검증_성공,
	OD_00009_주문서_검증_시작,
	OD_40031_주문서_검증_실패,
	OD_00020_주문서_진입_성공,
	OD_00019_주문서_진입_시작,
	OD_40036_주문서_진입_실패,
	OD_40007_P포인트_사용금액과_분배금액_불일치,



	//PM -- 프로모션

	PM_40004_마케팅_수신동의쿠폰_미지급,
	PM_00007_마케팅_수신동의쿠폰_지급완료,
	PM_00006_마케팅_수신동의쿠폰_혜택대상,
	PM_40005_멤버쉽_전환쿠폰_미지급,
	PM_00009_멤버쉽_전환쿠폰_지급완료,
	PM_00008_멤버쉽_전환쿠폰_혜택대상,
	PM_40002_모바일_앱다운로드쿠폰_미지급,
	PM_00003_모바일_앱다운로드쿠폰_지급완료,
	PM_00002_모바일_앱다운로드쿠폰_혜택대상,
	PM_00014_비로그인_회원,
	PM_40011_사이즈등록혜택_미지급,
	PM_00028_사이즈등록혜택_지급완료,
	PM_00027_사이즈등록혜택_혜택대상,
	PM_40006_상품리뷰쿠폰_미지급,
	PM_00011_상품리뷰쿠폰_지급완료,
	PM_00010_상품리뷰쿠폰_혜택대상,
	PM_40003_온라인_신규가입쿠폰_미지급,
	PM_00005_온라인_신규가입쿠폰_지급완료,
	PM_00004_온라인_신규가입쿠폰_혜택대상,
	PM_40001_온라인_쿠폰_발급_오류,
	PM_00001_온오프라인_쿠폰사용_호출,
	PM_00000_온오프라인_쿠폰정보_조회_호출,
	PM_00019_유효하지_않은_APP,
	PM_00021_유효하지_않은_ERP_조건,
	PM_00020_유효하지_않은_LANG,
	PM_00016_유효하지_않은_디바이스,
	PM_00018_유효하지_않은_몰,
	PM_00017_유효하지_않은_회원,
	PM_00022_이전에_해당_혜택을_받음,
	PM_40009_추천인혜택_미지급,
	PM_00024_추천인혜택_지급완료,
	PM_00023_추천인혜택_혜택대상,
	PM_40010_피추천인혜택_미지급,
	PM_00026_피추천인혜택_지급완료,
	PM_00025_피추천인혜택_혜택대상,
	PM_00012_회원_혜택_API_성공,
	PM_40007_회원_혜택_API_오류,
	PM_00013_회원_혜택_없음,
	PM_00015_회원_혜택_지급_성공,
	PM_40008_회원_혜택_지급_오류,



	//SC -- 시스템공통

	SC_00009_X0건이_등록_되었습니다,
	SC_00013_X0건이_변경이_완료되었습니다,
	SC_00017_X0건이_삭제_되었습니다,
	SC_00007_X0건이_저장_되었습니다,
	SC_00003_X0건이_정상처리_되었습니다,
	SC_00011_X0건이_조회_되었습니다,
	SC_00005_X0건이_추가_되었습니다,
	SC_00015_X0건이_추가_되었습니다,
	SC_40003_대상_정보가_존재하지_않습니다,
	SC_00008_등록_되었습니다,
	SC_00022_변경_사항을_모두_초기화_합니다,
	SC_00012_변경이_완료되었습니다,
	SC_00016_삭제_되었습니다,
	SC_00001_성공적으로_처리_되었습니다,
	SC_50000_시스템_오류가_발생하였습니다,
	SC_50001_시스템_점검중입니다,
	SC_50002_시스템이_과부하_상태입니다,
	SC_00020_업로드_되었습니다,
	SC_45000_요청한_입력값_X0_이_유효하지_않습니다,
	SC_40000_요청한_입력값이_유효하지_않습니다,
	SC_40002_이미_등록된_정보입니다,
	SC_00026_인증_메일이_전송_되었습니다,
	SC_00025_인증번호가_전송_되었습니다,
	SC_00006_저장_되었습니다,
	SC_00023_전송_되었습니다,
	SC_00024_전송예약_되었습니다,
	SC_00002_정상_처리_되었습니다,
	SC_00010_조회_되었습니다,
	SC_00021_조회된_데이터가_없습니다,
	SC_00019_초기화_되었습니다,
	SC_00004_추가_되었습니다,
	SC_00014_추가_되었습니다,
	SC_00018_취소_되었습니다,
	SC_40001_필수_입력값이_누락되었거나_값이_없습니다,
	SC_40004_해당_정보에_대한_권한이_없습니다,



	//SH -- 검색

	SH_00001_검색로그기록,
	SH_00002_검색엔진_응답시간,



	//SM -- 매출및정산
	SM_00019_PG_승인내역_PG_연동_건수,
	SM_00002_PG_승인내역_PG_연동_성공,
	SM_00001_PG_승인내역_PG_연동_시도,
	SM_40001_PG_승인내역_PG_연동_실패,
	SM_00012_PG_통합내역_매출테이블_이관_성공,
	SM_00011_PG_통합내역_매출테이블_이관_시도,
	SM_40006_PG_통합내역_매출테이블_이관_실패,
	SM_00014_ERP_매출정보_조회_성공,
	SM_00013_ERP_매출정보_조회_시도,
	SM_40007_ERP_매출정보_조회_실패,
	SM_00016_정산_주문클레임_이체_성공,
	SM_00015_정산_주문클레임_이체_시도,
	SM_40008_정산_주문클레임_이체_실패,
	SM_00018_P포인트_충당금_ERP_전송_성공,
	SM_00017_P포인트_충당금_ERP_전송_시도,
	SM_40009_P포인트_충당금_ERP_전송_실패;


	//@formatter:on
	
	
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// 개발자 수정 가능 부분 종료. 이 아래 부분은 수정하시면 않됩니다 
	//
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Getter
	private ResponseType type;

	@Getter
	private String messageCode;
	
	private BusinessType businessType;
	
	private String codeNo;

	@Getter
	private String defaultMessage;

	private CommonResponseCode() {
		parse(this);
	}

	Pattern pattern = Pattern.compile("([A-Z]{2})_(\\d{5})_(.*)");


	void parse(CommonResponseCode code) {

		Matcher m = pattern.matcher(code.name());
		Assert.isTrue(m.find(), "ErrorCode  형식에 어긋나는  enum 입니다. :" + code.name());
		
		String businessType = m.group(1);
		String codeNo = m.group(2);
		String msg = m.group(3);
		
		if (BusinessType.contains(businessType) == false){
			throw new IllegalStateException("Invalid business type:"+code.name());
		}

		if (codeNo.startsWith("5")) {
			code.type = ResponseType.ERROR;
		} else if (codeNo.startsWith("4")) {
			code.type = ResponseType.FAIL;
		} else if (codeNo.startsWith("0")) {
			code.type = ResponseType.SUCCESS;
		}
		else {
			throw new IllegalStateException("Invalid reponse type:"+code.name());
		}

		this.businessType = BusinessType.codeOf(businessType);
		this.codeNo = codeNo;
		
		String messageCode =businessType+"_"+codeNo;
		code.messageCode = messageCode;
		
		

		code.defaultMessage =msg.replaceAll("_", " ").replaceAll("X(\\d+)", "{$1}");
		Assert.isTrue(StringUtils.isNotEmpty(code.defaultMessage), "기본 메시지 가 있어야 합니다.");
	}

	/**
	 * 
	 * @param httpStatus
	 *            웹 클라이언트에게 반환할 에러 상태
	 * @param messageCode
	 *            메시지 코드
	 * @param defaultMessage
	 *            메시지 코드에 매칭되는 메시지를 찾지 못했을때 사용할 기본 메시지
	 */
	private CommonResponseCode(ResponseType responseType, String messageCode, String defaultMessage) {

		this.type = responseType;
		this.messageCode = messageCode;
		this.defaultMessage = defaultMessage;
	}

	@Override
	public String toMessage(Object... args) {
		return "["+messageCode+"] "+toRawMessage(args);
	}

	@Override
	public String toMessage() {

		return toMessage((Object[]) null);
	}

	@Override
	public ResponseException toException() {

		return toExceptionImpl(toMessage(), (Throwable) null, (Object[]) null);
	}

	@Override
	public ResponseException toException(Throwable throwable) {

		return toExceptionImpl(toMessage(), throwable);
	}

	@Override
	public ResponseException toException(Throwable throwable, Object... args) {

		return toExceptionImpl(toMessage(args), throwable, args);
	}

	@Override
	public ResponseException toException(Object... args) {

		return toExceptionImpl(toMessage(args), (Throwable) null, args);
	}

	ResponseException toExceptionImpl(String message, Throwable throwable, Object... args) {

		Assert.notNull(type);
		switch (type) {
		case SUCCESS:
			throw new ResponseException("성공 메시지를 예외로 만들려고 시도하였습니다.", throwable);
		case ERROR:
			return new ResponseException(toMessage(args), this, throwable);
		case FAIL:
		case MSG:
			return new ResponseException(toMessage(args), this, throwable);
		default:
			throw new IllegalStateException();
		}
	}
	
	@Override
	public String getCode() {
		return messageCode;
	}

	/**
	 * UTF-8 URL Encoding 된 메시지를 반환한다.
	 */
	@Override
	public String toUrlEncodedMessage() {

		return toUrlEncodedMessage((Object[]) null);
	}

	@Override
	public String toUrlEncodedMessage(Object... args) {

		String msg = toMessage(args);
		return encodeURIComponent(msg);
	}

	/**
	 * 자바스크립트에서 만들어내는 Url Encoded String과  동일한 형태를 만들어 낸다.
	 * 
	 * @param s
	 * @return
	 */
	public static String encodeURIComponent(String s) {

		String encodeString = null;
		try {
			// 자바스크립
			encodeString = URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\%21", "!")
					.replaceAll("\\%27", "'").replaceAll("\\%28", "(").replaceAll("\\%29", ")")
					.replaceAll("\\%7E", "~");

		} catch (UnsupportedEncodingException e) {
		}

		return encodeString;
	}

	@Override
	public BusinessType getBusinessType() {
		return businessType;
	}

	@Override
	public String getCodeNo() {
		return codeNo;
	}

	@Override
	public String toString() {
		return toMessage();
	}

	@Override
	public String toRawMessage() {
		return toRawMessage((Object[]) null);
	}

	@Override
	public String toRawMessage(Object... args) {
		String message = MessageUtils.getMessage(this.messageCode, this.defaultMessage, args);
		if (StringUtils.isEmpty(message)) {
			message = MessageUtils.getMessage(this.type + "_" + this.messageCode, this.defaultMessage, args);
		}
		return message;
	}

	
}
