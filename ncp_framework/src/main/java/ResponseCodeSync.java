import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ResponseCodeSync {
	static Map<String, String> groupMap;
	static {
		groupMap = new HashMap<>();
		groupMap.put("AF",  "제휴");
		groupMap.put("BR",	"브랜드");
		groupMap.put("CC",	"고객센터");
		groupMap.put("CT",	"장바구니");
		groupMap.put("CL",	"클레임");
		groupMap.put("SC",	"시스템공통");
		groupMap.put("CP",	"쿠폰");
		groupMap.put("CS",	"상담관리");
		groupMap.put("DL",	"배송");
		groupMap.put("DP",	"전시");
		groupMap.put("ET",	"이벤트");
		groupMap.put("FQ",	"FAQ");
		groupMap.put("GD",	"상품");
		groupMap.put("GT",	"비회원");
		groupMap.put("MI",	"메인");
		groupMap.put("MZ",	"매거진");
		groupMap.put("MB",	"회원");
		groupMap.put("MP",	"마이페이지");
		groupMap.put("OD",	"주문");
		groupMap.put("PY",	"결제");
		groupMap.put("PM",	"프로모션");
		groupMap.put("SH",	"검색");
		groupMap.put("SM",	"매출및정산");
		groupMap.put("ST",	"임직원");
		groupMap.put("VD",	"업체");		
	}
	public static void main(String[] args) throws IOException {
		
		

		
		Document doc = Jsoup.connect("http://devops.plgrimshop.com/confluence/pages/viewpage.action?pageId=4030772").get();
		Elements table = doc.select(".confluenceTable");
		Elements tbody = table.select("tbody");
		Elements childs = tbody.first().children();

		List<Code> codes = new ArrayList<>();
		for (Element tr : childs) {
			Elements tds = tr.children();
			
			// ASCII 160 non-breaking space를 trim 하기 위한 정규표현식 추가
			String group = tds.eq(0).text().trim().replaceAll("(^\\h*)|(\\h*$)","");
			String code = tds.eq(1).text().trim().replaceAll("(^\\h*)|(\\h*$)","");
			String desc = convertDesc(tds.eq(2).text()).trim().replaceAll("(^\\h*)|(\\h*$)","");
			String confirmed = tds.eq(3).text().trim().replaceAll("(^\\h*)|(\\h*$)","");
			String front = tds.eq(4).text().trim().replaceAll("(^\\h*)|(\\h*$)","");
			
			System.out.print(group+":"+code+":"+confirmed+":"+desc);
			if ("Y".equals(confirmed.trim().toUpperCase()) == false) {
				System.out.println("..REJECT");
				for(char ch: confirmed.toCharArray()){
					System.out.println(ch+":"+((int) ch));
				}
				continue;
			}
			System.out.println("..ACCEPT");
			codes.add(new Code(group,code ,desc, front.contains("Y") ));
		}
		Collections.sort(codes, new Comparator<Code>(){

			@Override
			public int compare(Code o1, Code o2) {
				int comp = o1.getGroup().compareTo(o2.getGroup());
				if (comp != 0){
					return comp;
				}
				return o1.getDesc().compareTo(o2.getDesc());
			}});
		writeCsv(codes);
		writeJavaSource(codes);
		writeJavascriptSource(codes);
		

	}

	

	private static void writeCsv(List<Code> codes) {
		System.out.println("#############-CSV");
		for (Code code : codes) {
			System.out.println(code.getGroup()+","+code.getCode()+","+code.getDesc()+",Y");
		}
		System.out.println("#############-CSV");
	}



	private static void writeJavaSource(List<Code> codes) throws IOException {
		StringBuffer buf = new StringBuffer("\t// 그룹명, 설명의 순서로 정렬됩니다. 코드가 일련번호로 추가 되어도 정렬된 상태로 보실수 있습니다. ");
		String group = null;
		for (Code code : codes) {
			if (code.getGroup().equals(group) == false){
				group = code.getGroup();
				buf.append("\n\n\n\n\t//"+group +" -- "+ groupMap.get(group)+"\n");
			}
			buf.append("\n\t"+code.toEnum()+",");
			
		}
		buf.deleteCharAt(buf.length()-1).append(";\n\n\n");
		String codeStr = buf.toString();
		
		File f= new File("src/main/java/com/plgrim/ncp/framework/responsecode/common/CommonResponseCode.java");
		
		updateCode(f, f, codeStr);
	}
	
	public static void updateCode(File from, File to, String codeStr) throws IOException {
		String raw = FileUtils.readFileToString(from, "UTF-8");
		System.out.println(raw);
		int start = raw.indexOf("//@formatter:off");
		start = raw.indexOf("\n", start)+1;
		int end = raw.indexOf("//@formatter:on")-1;
		System.out.println(start);
		System.out.println(end);
		String top = raw.substring(0,  start);
		
		String bottom = raw.substring(end);
		
		System.out.println(top);
		System.out.println(codeStr);
		System.out.println(bottom);
		String newRaw = top+codeStr+bottom;
		FileUtils.write(to, newRaw, "UTF-8");
	}

	private static String convertDesc(String text) {
		text = text.replaceAll(" ", "_");
		text = text.replace("{0}", "X0");
		text = text.replace("{1}", "X1");
		text = text.replace("{2}", "X2");
		text = text.replace("{3}", "X3");
		text = text.replace("{4}", "X4");
		return text.trim();
	}
	
	private static void writeJavascriptSource(List<Code> codes) throws IOException {
		StringBuffer buf = new StringBuffer("\t// 그룹명, 설명의 순서로 정렬됩니다. 코드가 일련번호로 추가 되어도 정렬된 상태로 보실수 있습니다. ");
		String group = null;
		List<String> temp = new ArrayList<>();
		for (Code code : codes) {
			if (code.getGroup().equals(group) == false){
				group = code.getGroup();
				temp.add("\n\n\n\n\t\t//"+group +" -- "+ groupMap.get(group)+"\n"+"\n\t\t"+code.toFunction());
			}
			else {
				temp.add("\n\t\t"+code.toFunction());	
			}
			
			
		}
		
		buf.append(StringUtils.join(temp));
		buf.deleteCharAt(buf.length()-1).append("\n\n\n");
		String codeStr = buf.toString();
		System.out.println(codeStr);
		File fromFile = new File("src/main/resources/responsecode.js");
		File[] toFiles = new File[]{
				new File("../ncp_web_bo/src/main/webapp/js/responsecode.js")
				,new File("../ncp_web_pc_dx/src/main/webapp/common/js/responsecode.js")
				,new File("../ncp_web_mb_dx/src/main/webapp/common/js/responsecode.js")
		};
		for (File toFile : toFiles) {
			updateCode(fromFile, toFile, codeStr);	
		}
		
		
	}
}

@Data
@AllArgsConstructor
class Code {
	public String toEnum() {
		return group+"_"+code+"_"+convert(desc);
	}
	public String toFunction() {
		return toEnum()+" : new ResponseCode('"+group+"','"+code+"','"+desc+"')";
	}
	private String convert(String text) {
		return text.replaceAll("\n", "__").replaceAll(" ", "_").replaceAll("\\{0}", "X0").replaceAll("\\{1}", "X1").replaceAll("\\{2}", "X2").replaceAll("\\{3}", "X3").replaceAll("\\{4}", "X4").replaceAll("\\{5}", "X5").replaceAll("\\{6}", "X6");
	}
	String group;
	String code;
	String desc;
	boolean front;
}