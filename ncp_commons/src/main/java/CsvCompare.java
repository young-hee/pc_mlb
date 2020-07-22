import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.StringUtils;

import com.plgrim.ncp.framework.config.profiledcsv.ProfiledCsvPropertySource;

import scala.actors.threadpool.Arrays;

public class CsvCompare {
	public Map<String, String> compare(String app) throws IOException{
		ProfiledCsvPropertySource base = new ProfiledCsvPropertySource(app);
		base.activate(new String[] { "prod" });
		
		List<String> allNames = new ArrayList<>();
		allNames.addAll(Arrays.asList(base.getPropertyNames()));

		Map<String,String> res = new HashMap<>();
		for (String name : allNames) {
			String value = (String) base.getProperty(name);
			res.put(name, value);
		}
		return res;
		
	}
	
	public static void main(String[] args) throws IOException {
		ProfiledCsvPropertySource base = new ProfiledCsvPropertySource("ncp_base");
//		ProfiledCsvPropertySource app = new ProfiledCsvPropertySource("ncp_batch_server");

		base.activate(new String[] { "stg2" }); // 여기 부터 확인~~
//		app.activate(new String[] { "prod" });
		
		System.out.println(base.getProperty("ncp_base.datasource1.auto.commit"));

//		List<String> allNames = new ArrayList<>();
//		allNames.addAll(Arrays.asList(base.getPropertyNames()));
////		allNames.addAll(Arrays.asList(app.getPropertyNames()));
//
//		StringBuffer buf = new StringBuffer();
//		CSVPrinter printer = new CSVPrinter(buf, CSVFormat.EXCEL);
//		for (String name : allNames) {
////			String value = StringUtils.isNotEmpty((String) app.getProperty(name)) ? (String) app.getProperty(name): (String) base.getProperty(name);
//			String value = (String) base.getProperty(name);
//			printer.printRecord(name, value);
//		}
//		printer.flush();
//		printer.close();
//		System.out.println(buf.toString());

	}
}
