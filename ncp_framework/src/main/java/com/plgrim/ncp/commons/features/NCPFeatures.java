package com.plgrim.ncp.commons.features;

import org.togglz.core.Feature;
import org.togglz.core.annotation.ActivationParameter;
import org.togglz.core.annotation.DefaultActivationStrategy;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;
import org.togglz.core.repository.FeatureState;

public enum NCPFeatures implements Feature {
	//@formatter:off
	/** 
	 * 이전 추천 파일럿시에 사용되었던 코드로 현재는 사용하지 않음. 
	 * 단지 ExclusiveGradualRolloutStrategyTest에서 테스트용도로만 사용됨. 
	 */
	RECOMMENDATION_4_A, 
	RECOMMENDATION_4_B,
	RECOMMENDATION_4_C,
	RECOMMENDATION_4_D,

	/**
	 * Best 로직: PC
	 * 
	 * labId: 10000 methodId: L10000_BESTNEW_01 (신규로직) methodId:
	 * L10000_BESTOLD_01 (기존로직)
	 */
	L10000_BESTNEW_01, // Best 신규로직
	L10000_BESTOLD_01, // Best 기존로직

	/**
	 * Best 로직: Mobile
	 * 
	 * labId: 10001 methodId: L10001_BESTNEW_01 (신규로직) methodId:
	 * L10001_BESTOLD_01 (기존로직)
	 */
	L10001_BESTNEW_01, // Best 신규로직
	L10001_BESTOLD_01, // Best 기존로직

	/**
	 * 추천 로직: PC - You May Also Like 영역
	 * 
	 * labId: 10002 methodId: L10002_RECO_RECOPICK_01 (레코픽) methodId:
	 * L10002_RECO_PLGRIM_01 (자사로직)
	 */
	L10002_RECO_RECOPICK_01, // 레코픽
	L10002_RECO_PLGRIM_02, // 자사 디폴트 대체 로직

	/**
	 * 추천 로직: Mobile - Main 섹션페이지의 Recommendation 영역
	 * 
	 * labId: 10003 methodId: L10003_RECO_RECOPICK_01 (레코픽) methodId:
	 * L10003_RECO_PLGRIM_01 (자사로직)
	 */
	L10003_RECO_RECOPICK_01, // 레코픽
	L10003_RECO_PLGRIM_01, // 자사 디폴트 대체 로직

	/**
	 * 모바일 메인: Mobile - FocusOn의 코너 영역
	 * 
	 * labId: 10004 methodId: L10004_CORNER_WHATSNEW_01 (What' New) methodId:
	 * L10004_CORNER_TOPSELLER_01 (Top Seller)
	 */
	L10004_CORNER_WHATSNEW_01, // What' New
	L10004_CORNER_TOPSELLER_01, // Top Seller
	
	
	/**
	 *  추천 로직: Mobile - You May Also Like 영역
	 *
	 *  labId: 10005
	 *  methodId: L10005_RECO_RECOPICK_01 (레코픽)
	 *  methodId: L10005_RECO_PLGRIM_01 (자사로직)
	 */
	L10005_RECO_RECOPICK_01,	// 레코픽
	L10005_RECO_PLGRIM_02,		// 자사 디폴트 대체 로직
	
	
	
	/**
	 *  추천 로직: Mobile - MEN 섹션페이지의 Recommendation 영역
	 *
	 *  labId: 10006
	 *  methodId: L10006_RECO_RECOPICK_01 (레코픽)
	 *  methodId: L10006_RECO_PLGRIM_01 (자사로직)
	 */
	L10006_RECO_RECOPICK_01,	// 레코픽
	L10006_RECO_PLGRIM_01,		// 자사 디폴트 대체 로직
	
	/**
	 *  추천 로직: Mobile - WOMEN 섹션페이지의 Recommendation 영역
	 *
	 *  labId: 10007
	 *  methodId: L10007_RECO_RECOPICK_01 (레코픽)
	 *  methodId: L10007_RECO_PLGRIM_01 (자사로직)
	 */
	L10007_RECO_RECOPICK_01,	// 레코픽
	L10007_RECO_PLGRIM_01,		// 자사 디폴트 대체 로직
	
	/**
	 *  추천 로직: PC - 메인페이지의 Recommendation 영역
	 *
	 *  labId: 10008
	 *  methodId: L10008_RECO_RECOPICK_01 (레코픽)
	 *  methodId: L10008_RECO_PLGRIM_01 (자사로직)
	 */
	L10008_RECO_RECOPICK_01,	// 레코픽
	L10008_RECO_PLGRIM_01,		// 자사 디폴트 대체 로직
	
	@Label("남성")
	@DefaultActivationStrategy(id="gender",parameters={@ActivationParameter(name="gender",value="MALE")})
	@EnabledByDefault
	GENDER_MALE,
	
	@Label("여성")
	@DefaultActivationStrategy(id="gender",parameters={@ActivationParameter(name="gender",value="FEMALE")})
	@EnabledByDefault
	GENDER_FEMALE,
	
	@Label("어린이")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="0"),@ActivationParameter(name="to",value="9")})
	@EnabledByDefault
	AGE_UNDER_10,
	
	@Label("10대")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="10"),@ActivationParameter(name="to",value="19")})
	@EnabledByDefault
	AGE_FROM_10_TO_19,
	
	@Label("20대")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="20"),@ActivationParameter(name="to",value="29")})
	@EnabledByDefault
	AGE_FROM_20_TO_29,
	
	@Label("30대")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="30"),@ActivationParameter(name="to",value="39")})
	@EnabledByDefault
	AGE_FROM_30_TO_39,
	
	@Label("40대")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="40"),@ActivationParameter(name="to",value="49")})
	@EnabledByDefault
	AGE_FROM_40_TO_49,
	
	@Label("50대")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="50"),@ActivationParameter(name="to",value="59")})
	@EnabledByDefault
	AGE_FROM_50_TO_59,
	
	@Label("60대")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="60"),@ActivationParameter(name="to",value="69")})
	@EnabledByDefault
	AGE_FROM_60_TO_69,
	
	@Label("고연령자")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="70"),@ActivationParameter(name="to",value="999")})
	@EnabledByDefault
	AGE_OVER_70,
	
	
	@Label("10세 이상")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="10"),@ActivationParameter(name="to",value="999")})
	@EnabledByDefault
	AGE_OVER_10,
	
	@Label("20세 미만")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="0"),@ActivationParameter(name="to",value="19")})
	@EnabledByDefault
	AGE_UNDER_20,
	
	@Label("20세 이상")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="20"),@ActivationParameter(name="to",value="999")})
	@EnabledByDefault
	AGE_OVER_20,
	
	@Label("30세 미만")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="0"),@ActivationParameter(name="to",value="29")})
	@EnabledByDefault
	AGE_UNDER_30,
	
	@Label("30세 이상")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="30"),@ActivationParameter(name="to",value="999")})
	@EnabledByDefault
	AGE_OVER_30,
	
	@Label("40세 미만")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="0"),@ActivationParameter(name="to",value="39")})
	@EnabledByDefault
	AGE_UNDER_40,
	
	@Label("40세 이상")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="40"),@ActivationParameter(name="to",value="999")})
	@EnabledByDefault
	AGE_OVER_40,
	
	@Label("50세 미만")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="0"),@ActivationParameter(name="to",value="49")})
	@EnabledByDefault
	AGE_UNDER_50,
	
	@Label("50세 이상")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="50"),@ActivationParameter(name="to",value="999")})
	@EnabledByDefault
	AGE_OVER_50,
	
	@Label("60세 미만")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="0"),@ActivationParameter(name="to",value="59")})
	@EnabledByDefault
	AGE_UNDER_60,
	
	@Label("60세 이상")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="60"),@ActivationParameter(name="to",value="999")})
	@EnabledByDefault
	AGE_OVER_60,
	
	@Label("70세 미만")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="0"),@ActivationParameter(name="to",value="69")})
	@EnabledByDefault
	AGE_UNDER_70,
	
	@Label("70세 이상")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="70"),@ActivationParameter(name="to",value="999")})
	@EnabledByDefault
	AGE_OVER_670,
	
	@Label("유치원")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="3"),@ActivationParameter(name="to",value="5")})
	@EnabledByDefault
	AGE_KINDERGARTEN,
	
	@Label("초등학교")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="6"),@ActivationParameter(name="to",value="11")})
	@EnabledByDefault
	AGE_ELEMENTARY_SCHOOL,
	
	@Label("중학교")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="12"),@ActivationParameter(name="to",value="14")})
	@EnabledByDefault
	AGE_MODDLE_SCHOOL,
	
	@Label("고등학교")
	@DefaultActivationStrategy(id="age",parameters={@ActivationParameter(name="from",value="15"),@ActivationParameter(name="to",value="17")})
	@EnabledByDefault
	AGE_HIGH_SCHOOL,
	
	
	
	
	
	
	@Label("10%")
	@DefaultActivationStrategy(id="probability",parameters={@ActivationParameter(name="probability",value="10")})
	@EnabledByDefault
	PROBABILITY_10,
	
	@Label("20%")
	@DefaultActivationStrategy(id="probability",parameters={@ActivationParameter(name="probability",value="20")})
	@EnabledByDefault
	PROBABILITY_20,
	
	@Label("30%")
	@DefaultActivationStrategy(id="probability",parameters={@ActivationParameter(name="probability",value="30")})
	@EnabledByDefault
	PROBABILITY_30,
	
	@Label("40%")
	@DefaultActivationStrategy(id="probability",parameters={@ActivationParameter(name="probability",value="40")})
	@EnabledByDefault
	PROBABILITY_40,
	
	@Label("50%")
	@DefaultActivationStrategy(id="probability",parameters={@ActivationParameter(name="probability",value="50")})
	@EnabledByDefault
	PROBABILITY_50,
	
	@Label("60%")
	@DefaultActivationStrategy(id="probability",parameters={@ActivationParameter(name="probability",value="60")})
	@EnabledByDefault
	PROBABILITY_60,
	
	@Label("70%")
	@DefaultActivationStrategy(id="probability",parameters={@ActivationParameter(name="probability",value="70")})
	@EnabledByDefault
	PROBABILITY_70,
	
	@Label("80%")
	@DefaultActivationStrategy(id="probability",parameters={@ActivationParameter(name="probability",value="80")})
	@EnabledByDefault
	PROBABILITY_80,
	
	@Label("90%")
	@DefaultActivationStrategy(id="probability",parameters={@ActivationParameter(name="probability",value="90")})
	@EnabledByDefault
	PROBABILITY_90,
	
	@Label("100%")
	@DefaultActivationStrategy(id="probability",parameters={@ActivationParameter(name="probability",value="100")})
	@EnabledByDefault
	PROBABILITY_100,
	
	@Label("25%")
	@DefaultActivationStrategy(id="probability",parameters={@ActivationParameter(name="probability",value="25")})
	@EnabledByDefault
	PROBABILITY_25,
	
	@Label("75%")
	@DefaultActivationStrategy(id="probability",parameters={@ActivationParameter(name="probability",value="75")})
	@EnabledByDefault
	PROBABILITY_75,
	
	@Label("33%")
	@DefaultActivationStrategy(id="probability",parameters={@ActivationParameter(name="probability",value="33")})
	@EnabledByDefault
	PROBABILITY_33,
	
	@Label("66%")
	@DefaultActivationStrategy(id="probability",parameters={@ActivationParameter(name="probability",value="66")})
	@EnabledByDefault
	PROBABILITY_66,
	
	
	@Label("강원도")	 		@DefaultActivationStrategy(id="residence",parameters={@ActivationParameter(name="residence",value="42000")}) @EnabledByDefault RESIDENCE_강원도,
	@Label("경기도") 			@DefaultActivationStrategy(id="residence",parameters={@ActivationParameter(name="residence",value="41000")}) @EnabledByDefault RESIDENCE_경기도,
	@Label("경상남도") 		@DefaultActivationStrategy(id="residence",parameters={@ActivationParameter(name="residence",value="48000")}) @EnabledByDefault RESIDENCE_경상남도,
	@Label("경상북도") 		@DefaultActivationStrategy(id="residence",parameters={@ActivationParameter(name="residence",value="47000")}) @EnabledByDefault RESIDENCE_경상북도,
	@Label("광주광역시") 		@DefaultActivationStrategy(id="residence",parameters={@ActivationParameter(name="residence",value="29000")}) @EnabledByDefault RESIDENCE_광주광역시,
	@Label("대구광역시") 		@DefaultActivationStrategy(id="residence",parameters={@ActivationParameter(name="residence",value="27000")}) @EnabledByDefault RESIDENCE_대구광역시,
	@Label("대전광역시") 		@DefaultActivationStrategy(id="residence",parameters={@ActivationParameter(name="residence",value="30000")}) @EnabledByDefault RESIDENCE_대전광역시,
	@Label("부산광역시") 		@DefaultActivationStrategy(id="residence",parameters={@ActivationParameter(name="residence",value="26000")}) @EnabledByDefault RESIDENCE_부산광역시,
	@Label("서울특별시") 		@DefaultActivationStrategy(id="residence",parameters={@ActivationParameter(name="residence",value="11000")}) @EnabledByDefault RESIDENCE_서울특별시,
	@Label("울산광역시") 		@DefaultActivationStrategy(id="residence",parameters={@ActivationParameter(name="residence",value="31000")}) @EnabledByDefault RESIDENCE_울산광역시,
	@Label("인천광역시") 		@DefaultActivationStrategy(id="residence",parameters={@ActivationParameter(name="residence",value="28000")}) @EnabledByDefault RESIDENCE_인천광역시,
	@Label("전라남도") 		@DefaultActivationStrategy(id="residence",parameters={@ActivationParameter(name="residence",value="46000")}) @EnabledByDefault RESIDENCE_전라남도,
	@Label("전라북도") 		@DefaultActivationStrategy(id="residence",parameters={@ActivationParameter(name="residence",value="45000")}) @EnabledByDefault RESIDENCE_전라북도,
	@Label("제주특별자치도") 	@DefaultActivationStrategy(id="residence",parameters={@ActivationParameter(name="residence",value="50000")}) @EnabledByDefault RESIDENCE_제주특별자치도,
	@Label("충청남도") 		@DefaultActivationStrategy(id="residence",parameters={@ActivationParameter(name="residence",value="44000")}) @EnabledByDefault RESIDENCE_충청남도,
	@Label("충청북도") 		@DefaultActivationStrategy(id="residence",parameters={@ActivationParameter(name="residence",value="43000")}) @EnabledByDefault RESIDENCE_충청북도,

	
	
	
	@Label("Composite Sample")
	@DefaultActivationStrategy(id="composite",parameters={@ActivationParameter(name="features",value="GENDER_MALE, COMPOSITE_SAMPLE_2")})
	@EnabledByDefault
	COMPOSITE_SAMPLE_1,
	
	@Label("Composite Sample")
	@DefaultActivationStrategy(id="composite",parameters={@ActivationParameter(name="features",value="AGE_FROM_30_TO_39, AGE_FROM_40_TO_49"),@ActivationParameter(name="condition",value="or") })
	@EnabledByDefault
	COMPOSITE_SAMPLE_2,
	
	//@formatter:on
	;

	public boolean isActive() {
		return FeatureContext.getFeatureManager().isActive(this);
	}

	public String getGroup() {
		return getState().getParameter("group");
	}

	public FeatureState getState() {
		return FeatureContext.getFeatureManager().getFeatureState(this);
	}

}
