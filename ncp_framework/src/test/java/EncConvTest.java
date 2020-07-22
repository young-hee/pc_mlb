import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

public class EncConvTest {

	@Test
	public void test() throws UnsupportedEncodingException {
		String src = "한글";
		System.out.println(src);
		byte[]  d = src.getBytes("EUC-KR");
		
		for (byte b : d) {
			System.out.print(Integer.toHexString(b & 0xFF)+' ');
		}
		System.out.println("");
		String dest = new String(d, "UTF-8");
		System.out.println(dest);
		
		byte[] d2 = dest.getBytes("UTF-8");
		
		for (byte b : d2) {
			System.out.print(Integer.toHexString(b & 0xFF)+' ');
		}
	}
}
