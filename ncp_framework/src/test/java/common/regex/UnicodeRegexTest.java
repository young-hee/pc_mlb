package common.regex;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class UnicodeRegexTest {
	

	void match(String patternStr, String src) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher m = pattern.matcher(src);
		assertTrue(m.find());
	}

	void notMatch(String patternStr, String src) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher m = pattern.matcher(src);
		assertFalse(m.find());
	}

	@Test
	public void test() {

		String pattern = "[\\p{InHangul_Jamo}\\p{InHangul_Compatibility_Jamo}\\p{InHangul_Syllables}]+";
		match(pattern, "한글");
		match(pattern, "ㄱㄴㄷ");
		match(pattern, "ㅏㅣㅜ");

		// 고어( compatibility)
		match(pattern, "ᄚᅇᇲ");

		// 완성형: InHangul_Syllables
		match(pattern, "걡");
	}

	@Test
	public void 한자() {
		String pattern = "^[\\p{IsHan}：\\s]+$";
		match(pattern, "北美微博广场");
		match(pattern, "全部都好有用架");

		// 간체차 번체자
		match(pattern, "户戶见見讠訁贝貝龟龜丰豐");
	}

	@Test
	public void 한자_구두점() {
		String pattern = "^[\\p{InCJK_SYMBOLS_AND_PUNCTUATION}\\p{InHALFWIDTH_AND_FULLWIDTH_FORMS}\\p{InCJK_COMPATIBILITY_FORMS}]+$";
		match(pattern, "，");
		match(pattern, "。");
		match(pattern, "：");
		match(pattern, "；");
		match(pattern, "！");
		match(pattern, "？");
		match(pattern, "（）［］【】");
		match(pattern, "、");
		match(pattern, "《》〈〉");
		match(pattern, "～");
		match(pattern, "「」");
		match(pattern, "﹁﹂");
	}

	@Test(expected = AssertionError.class)
	public void 한자실패() {
		String pattern = "^[\\p{IsHan}]+$";
		match(pattern, "abc 这个");
	}

	@Test
	public void 숫자() {
		String pattern = "[\\p{Digit}]+";
		match(pattern, "123");
		match(pattern, "1");
	}

	@Test
	public void 영문자() {
		String pattern = "[\\p{Alpha}]+";
		match(pattern, "A");
		match(pattern, "abc");
	}

	@Test
	public void 영숫자() {
		String pattern = "[\\p{Alnum}]+";
		match(pattern, "A1");
		match(pattern, "abc123");
	}

	@Test
	public void 키보드입력가능특수문자() {
		String pattern = "^[~@#$%^&*()_+`\\-={}|\\[\\]\\\\:;\"'<>,.?/]+$";
		// 일반 키보드에서 입력 가능한 특수 문자
		match(pattern, "~");
		match(pattern, "@");
		match(pattern, "#");
		match(pattern, "$");
		match(pattern, "%");
		match(pattern, "^");
		match(pattern, "&");
		match(pattern, "*");
		match(pattern, "(");
		match(pattern, ")");
		match(pattern, "_");
		match(pattern, "+");
		match(pattern, "`");
		match(pattern, "\\-");
		match(pattern, "=");
		match(pattern, "{");
		match(pattern, "}");
		match(pattern, "|");
		match(pattern, "[");
		match(pattern, "]");
		match(pattern, "\\");
		match(pattern, ":");
		match(pattern, ";");
		match(pattern, "\"");
		match(pattern, "'");
		match(pattern, "<");
		match(pattern, ">");
		match(pattern, ",");
		match(pattern, ".");
		match(pattern, "?");
		match(pattern, "/");
	}

	@Test
	public void Xss특수문자배제() {
		// / \ : < >
		String pattern = "^[~@#$%^&*()_+`\\-={}|\\[\\];\"',.?]+$";
		notMatch(pattern, "/");
		notMatch(pattern, "\\");
		notMatch(pattern, ":");
		notMatch(pattern, "<");
		notMatch(pattern, ">");
	}
	
	String XSS_FILTER_FULL = "^[\\p{Alnum}\\p{IsHan}\\p{InHangul_Jamo}\\p{InHangul_Compatibility_Jamo}\\p{InHangul_Syllables}\\p{InCJK_SYMBOLS_AND_PUNCTUATION}\\p{InHALFWIDTH_AND_FULLWIDTH_FORMS}\\p{InCJK_COMPATIBILITY_FORMS}~@#$%^&*()_+`\\-={}|\\[\\];\"',.?\\s]+$"; 


	@Test
	public void 문자종합() {
		String pattern = XSS_FILTER_FULL;
		match(pattern, "한글 100 Year 見貝");
		match(pattern,
				"这个测试对我们来说是一个全局PLGRIM测试：第一个接触的考验。\n这件事是测试的目的，而不是实际操作的内容。\n这个测试对我们来说是一个全局PLGRIM测试 第一个接触的考验\n这件事是测试的目的，而不是实际操作的内容");

	}
	
	
	@Test
	public void xss방지() {
		notMatch(XSS_FILTER_FULL, "<script>");
		notMatch(XSS_FILTER_FULL, "/>");
		
	}

}
