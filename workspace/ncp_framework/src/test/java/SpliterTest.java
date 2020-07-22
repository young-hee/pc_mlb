import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import com.google.common.base.Splitter;

public class SpliterTest {

	@Test
	public void test() {
		String src = "abc | def |||";

		String[] expected = { "abc", "def" };

		Iterable<String> result = Splitter.on('|')
				.trimResults()
				.split(src);

		Iterator<String> it = result.iterator();
		assertEquals(expected[0], it.next());
		assertEquals(expected[1], it.next());
		assertEquals("", it.next());
		assertEquals("", it.next());
		assertEquals("", it.next());
		assertFalse(it.hasNext());

	}

}
