package com.plgrim.ncp.commons.features;

import static com.plgrim.ncp.commons.features.ExclusiveGradualRolloutStrategy.EXCLUSIVE_KEY;
import static com.plgrim.ncp.commons.features.ExclusiveGradualRolloutStrategy.PARAM_PERCENTAGE;
import static com.plgrim.ncp.commons.features.NCPFeatures.RECOMMENDATION_4_A;
import static com.plgrim.ncp.commons.features.NCPFeatures.RECOMMENDATION_4_B;
import static com.plgrim.ncp.commons.features.NCPFeatures.RECOMMENDATION_4_C;
import static com.plgrim.ncp.commons.features.NCPFeatures.RECOMMENDATION_4_D;
import static net.narusas.test.assertk.AssertK.그렇다면;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.togglz.core.Feature;
import org.togglz.core.repository.FeatureState;

@SuppressWarnings("static-access")
public class ExclusiveGradualRolloutStrategyTest {
	private static final String EXCLUSIVE_GROUP_KEY = "recommendationPrototype";

	private ExclusiveGradualRolloutStrategy 배타적_점진적_출시전략;

	private FeatureState stateA;

	private FeatureState stateB;

	private FeatureState stateC;
	
	private FeatureState stateD;

	private HttpSession session;

	private Random randomGenerator;

	private ExclusiveState exclusiveState;

	int 임계치 = 30;

	@Before
	public void setUp() {
		배타적_점진적_출시전략 = new ExclusiveGradualRolloutStrategy();
		randomGenerator = mock(Random.class);
		배타적_점진적_출시전략.setRandomSeed(randomGenerator);

		stateA = createState(RECOMMENDATION_4_A);
		stateB = createState(RECOMMENDATION_4_B);
		stateC = createState(RECOMMENDATION_4_C);
		stateD = createState(RECOMMENDATION_4_D);

		session = new MockHttpSession();

		MockHttpServletRequest req = new MockHttpServletRequest();
		req.setSession(session);
		
		ServletRequestAttributes attr = new ServletRequestAttributes(req);
		RequestContextHolder.setRequestAttributes(attr);

		exclusiveState = new ExclusiveState();
	}

	@After
	public void after() {
		RequestContextHolder.resetRequestAttributes();
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void 퍼센트_임계치를_초과했을때() {
		랜덤값_기능_비활성화_수치로_설정();
		그렇다면().다음_(배타적_점진적_출시전략.출시_대상인가(stateA)).은.거짓.이여야한다();
	}

	@Test
	public void 퍼센트_임계치에_포함되었을때() {
		랜덤값_기능_활성화_수치로_설정();
		그렇다면().다음_(배타적_점진적_출시전략.출시_대상인가(stateA)).은.참.이여야한다();
	}

	@Test
	public void 퍼센트_임계치일때() {
		랜덤값_임계치로_설정();
		그렇다면().다음_(배타적_점진적_출시전략.출시_대상인가(stateA)).은.참.이여야한다();
	}

	@Test
	public void 독점처리() {
		// Given
		랜덤값_기능_활성화_수치로_설정();

		// When
		boolean 활성화여부 = 배타적_점진적_출시전략.activate(stateA);

		// Then
		그렇다면().다음_(활성화여부).는.참.이여야한다();
		그렇다면().다음_(독점상태().is처리됨(RECOMMENDATION_4_A)).는.참.이여야한다();
		//그렇다면().다음_(독점상태().is독점되었는가()).는.참.이여야한다();
		그렇다면().다음_(독점상태().다음_기능이_독점했는가(RECOMMENDATION_4_A)).는.참.이여야한다();
	}

	@Test
	public void 비독점처리() {
		// Given
		랜덤값_기능_비활성화_수치로_설정();

		// When
		boolean 활성화여부 = 배타적_점진적_출시전략.activate(stateA);

		// Then
		그렇다면().다음_(활성화여부).는.거짓.이여야한다();
		그렇다면().다음_(독점상태().is처리됨(RECOMMENDATION_4_A)).는.참.이여야한다();
		그렇다면("임계치 초과값은 기능 출시대상이 아님").으로_(독점상태().다음_기능이_독점했는가(RECOMMENDATION_4_A)).는.거짓.이여야한다();
	}

	@Test
	public void 이미_독점되었을때는_비독점처리하기() {
		// Given
		랜덤값_기능_활성화_수치로_설정();

		// When
		boolean A_활성화여부 = 배타적_점진적_출시전략.activate(stateA);

		// Then
		그렇다면().다음_(A_활성화여부).는.참.이여야한다();
		그렇다면("출시 대상이지만 이미 A가 독점하였음").으로_(배타적_점진적_출시전략.activate(stateB)).은.거짓.이여야한다();
		그렇다면("출시 대상이지만 이미 A가 독점하였음").으로_(배타적_점진적_출시전략.activate(stateC)).은.거짓.이여야한다();
	}

	@Test
	public void 독점처리시_중복처리방지() {
		// Given
		A가_이미_독점함();
		랜덤값_기능_비활성화_수치로_설정();

		// When
		boolean A_활성화여부 = 배타적_점진적_출시전략.isActive(stateA, null);

		// Then
		그렇다면("이미 A가 독점했기 때문에 랜덤값과는 상관없이").으로_(A_활성화여부).는.참.이여야한다();
	}

	@Test
	public void 비독점처리시_중복처리방지() {
		// Given
		A가_독점하지_못했음();
		랜덤값_기능_활성화_수치로_설정();

		// When
		boolean A_활성화여부 = 배타적_점진적_출시전략.isActive(stateA, null);

		// Then
		그렇다면("이미 처리되어서 중복으로 판정하지 않음").으로_(A_활성화여부).는.거짓.이여야한다();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private FeatureState createState(Feature feature) {
		FeatureState state = new FeatureState(feature);

		// 30%의 사용자에게 활성화 됨
		state.setParameter(PARAM_PERCENTAGE, String.valueOf(임계치));
		state.setParameter(EXCLUSIVE_KEY, EXCLUSIVE_GROUP_KEY);
		return state;
	}

	private ExclusiveState 독점상태() {
		return (ExclusiveState) session.getAttribute("exclusive_"+EXCLUSIVE_GROUP_KEY);
	}

	private void 랜덤값_설정(int r) {
		when(randomGenerator.nextInt(100)).thenReturn(r);
	}

	private void 랜덤값_기능_활성화_수치로_설정() {
		랜덤값_설정(임계치 - 1);
	}

	private void 랜덤값_임계치로_설정() {
		랜덤값_설정(임계치);
	}

	private void 랜덤값_기능_비활성화_수치로_설정() {
		랜덤값_설정(임계치 + 1);
	}

	private void A가_이미_독점함() {
		세션에_Feature가_독점으로_기록됨(RECOMMENDATION_4_A);
	}

	private void A가_독점하지_못했음() {
		세션에_Feature가_비독점으로_기록됨(RECOMMENDATION_4_A);
	}

	private void 세션에_Feature가_독점으로_기록됨(Feature feature) {
		exclusiveState.독점하기_기록(feature, EXCLUSIVE_GROUP_KEY);
		session.setAttribute(배타적_점진적_출시전략.sessionKey(stateA), exclusiveState);
	}

	private void 세션에_Feature가_비독점으로_기록됨(Feature feature) {
		exclusiveState.독점하지않기_기록(feature);
		session.setAttribute(배타적_점진적_출시전략.sessionKey(stateA), exclusiveState);
	}
}
