import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class Compare {
	public static void main(String[] args) throws IOException {
		
		CsvCompare csv = new CsvCompare();
		PropCompare prop = new PropCompare();
		
		String app = "ncp_base";
		Map<String, String> csvMap = csv.compare(app);
		Map<String, String> propMap = prop.compare(app);
		
		Set<String> names = new HashSet<>();
		names.addAll(csvMap.keySet());
		names.addAll(propMap.keySet());
		
		Map<String,String[]> diffMap = new HashMap<>();
		
		for (String key : names) {
			String csvValue = csvMap.get(key);
			String propValue = propMap.get(key);
			if (StringUtils.equals(csvValue, propValue)==false) {
				diffMap.put(key, new String[]{csvValue, propValue});
			}
		}
		
		Set<Entry<String, String[]>> it3 = diffMap.entrySet();
		for (Entry<String, String[]> entry : it3) {
			System.out.println(entry.getKey()+","+entry.getValue()[0]+","+entry.getValue()[1] );
		}
	}
}
