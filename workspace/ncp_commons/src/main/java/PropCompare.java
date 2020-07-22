import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class PropCompare {
	
	public Map<String, String> compare(String profile) throws FileNotFoundException, IOException {
		Properties base = new Properties();
		base.load(new FileInputStream(new File("/Users/narusas/work/plgrim/workspace/ncp_config_OLD/src/main/resources/META-INF/config/"+profile+"/prod.properties")));
		Map<String,String> res = new HashMap<>();
		
		Iterator<Entry<Object, Object>> it = base.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<java.lang.Object, java.lang.Object> entry = (Map.Entry<java.lang.Object, java.lang.Object>) it.next();
			res.put((String)entry.getKey(), (String)entry.getValue());
		}
		
		return res;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties base = new Properties();
		base.load(new FileInputStream(new File("/Users/narusas/work/plgrim/workspace/ncp_config_OLD/src/main/resources/META-INF/config/ncp_base/prod.properties")));
//		System.out.println(base);
		
		Properties app = new Properties();
		app.load(new FileInputStream(new File("/Users/narusas/work/plgrim/workspace/ncp_config_OLD/src/main/resources/META-INF/config/ncp_batch_server/prod.properties")));
//		System.out.println(app);
		
//		base.putAll(app);
//		System.out.println(base);
		
		StringBuffer buf = new StringBuffer();
		CSVPrinter printer = new CSVPrinter(buf, CSVFormat.EXCEL );
		
		Iterator<Entry<Object, Object>> it = base.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<java.lang.Object, java.lang.Object> entry = (Map.Entry<java.lang.Object, java.lang.Object>) it.next();
			printer.printRecord(entry.getKey(), entry.getValue());
		}
		printer.flush();
		printer.close();
		System.out.println(buf.toString());
		
	}
}
