import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//@Slf4j
public class OracleJdbcSlowTest {
//	@Test
	public static void main(String[] args) throws Exception{
		
//	}
//	public void rawTest() throws IOException, ClassNotFoundException, SQLException {
		BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/oracleslowquery.sql")), "UTF-8"));
		StringBuffer buf = new StringBuffer();
		while(true) {
			String line = r.readLine();
			if (line == null) {
				break;
			}
			buf.append(line+"\n");
		}
		
		String sql = buf.toString();
				//Files.toString(new File("src/test/resources/oracleslowquery.sql"), Charset.forName("UTF-8"));
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@71.52.2.41:3005:ORAPLGRIM", "ncp", "sncp2015!");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@71.52.2.40:3007:orastg", "ncp", "sncp2015!");
		DatabaseMetaData dbmd = conn.getMetaData(); 
		System.out.println("Connected to : " + dbmd.getDatabaseProductVersion()); 
		System.out.println("JDBC Driver: " + dbmd.getDriverVersion());

//		BasicDataSource ds = new org.apache.commons.dbcp.BasicDataSource();
//		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//		ds.setUrl("jdbc:oracle:thin:@71.52.2.40:3007:orastg");
//		ds.setUsername("ncp");
//		ds.setPassword("sncp2015!");
//		ds.setInitialSize(1);
//		ds.setMinIdle(1);
//		ds.setMaxIdle(1);
//		ds.setMaxActive(3);
//
//		ds.setDefaultAutoCommit(false);
		
//		net.sf.log4jdbc.Log4jdbcProxyDataSource ds2 = new net.sf.log4jdbc.Log4jdbcProxyDataSource(ds);
//		Log4JdbcCustomFormatter logFormatter = new net.sf.log4jdbc.tools.Log4JdbcCustomFormatter();
//		logFormatter.setLoggingType(LoggingType.MULTI_LINE);
//		logFormatter.setSqlPrefix("SQL:::");
//
//		ds2.setLogFormatter(logFormatter);
		
//		for(int type : new int[]{ResultSet.TYPE_FORWARD_ONLY , ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.TYPE_SCROLL_SENSITIVE}){
//			for(int read:new int[]{ ResultSet.CONCUR_READ_ONLY,  ResultSet.CONCUR_UPDATABLE} ){
//				for(int rsSet: new int[]{ResultSet.HOLD_CURSORS_OVER_COMMIT, ResultSet.CLOSE_CURSORS_AT_COMMIT}){
//					Connection conn = ds.getConnection();
					conn.setAutoCommit(false);
					Statement altStmt = conn.createStatement();
					altStmt.execute("alter session set events '10046 trace name context forever, level 12'"); 
		
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setString(1, "Y");
					stmt.setString(2, "Y");
					stmt.setString(3, "Y");
					stmt.setString(4, "Y");
					stmt.setString(5, "Y");
					stmt.setString(6, "TRY");
					stmt.setString(7, "KOR");
					stmt.setString(8, "2016-07-08");
					stmt.setString(9, "2016-07-08");
//					stmt.setString(10, "10");
					stmt.setInt(10, 10);
					stmt.setInt(11, 1);
					System.out.println("Start");
					long start = System.currentTimeMillis();
					ResultSet rs = stmt.executeQuery();
					int count = 0;
					while(rs.next()){
						count ++;
					};
					long end = System.currentTimeMillis();
					System.out.println("Count: "+ count);
					System.out.println("Elapsed: "+ (end-start));
					stmt.close();
					conn.close();
//				}
//				
//			}
//			
//		}
		
		
	}

//	@Ignore
//	@Test
//	public void test() throws Exception {
//		String sql = Files.toString(new File("src/test/resources/oracleslowquery.sql"), Charset.forName("UTF-8"));
//		// System.out.println(sql);
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		// Connection conn =
//		// DriverManager.getConnection("jdbc:oracle:thin:@71.52.2.40:3007:orastg",
//		// "ncp", "sncp2015!");
//		
//		SingleConnectionDataSource ds = new SingleConnectionDataSource();
////		BasicDataSource ds = new org.apache.commons.dbcp.BasicDataSource();
//		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//		ds.setUrl("jdbc:oracle:thin:@71.52.2.40:3007:orastg");
//		ds.setUsername("ncp");
//		ds.setPassword("sncp2015!");
////		ds.setInitialSize(1);
////		ds.setMinIdle(1);
////		ds.setMaxIdle(1);
////		ds.setMaxActive(3);
//////		ds.setDefaultAutoCommit(false);
////		
////		net.sf.log4jdbc.Log4jdbcProxyDataSource ds2 = new net.sf.log4jdbc.Log4jdbcProxyDataSource(ds);
////		Log4JdbcCustomFormatter logFormatter = new net.sf.log4jdbc.tools.Log4JdbcCustomFormatter();
////		logFormatter.setLoggingType(LoggingType.MULTI_LINE);
////		logFormatter.setSqlPrefix("SQL:::");
////
////		ds2.setLogFormatter(logFormatter);
////		
//		
//		SqlSessionFactoryBean sessionFactory1 = new org.mybatis.spring.SqlSessionFactoryBean();
//		sessionFactory1.setDataSource(ds);
//		sessionFactory1.setTypeAliasesPackage("com.plgrim.ncp.base.entities.datasource1");
//		sessionFactory1.setConfigLocation(new ClassPathResource("META-INF/mybatis/datasource1.mybatis.config.xml"));
//
//		ApplicationContext ctx = new FileSystemXmlApplicationContext();
//		List<Resource> resources = new ArrayList<>();
//
//		for (Resource res : ctx.getResources("classpath*:META-INF/mybatis/datasource1/**/*.xml")) {
//			resources.add(res);
//		}
//		resources.add(new ClassPathResource("META-INF/mybatis/base.common.xml"));
//
//		sessionFactory1.setMapperLocations(resources.toArray(new Resource[resources.size()]));
//
//		SqlSessionTemplate sessionTemplate1 = new org.mybatis.spring.SqlSessionTemplate(sessionFactory1.getObject(),
//				ExecutorType.SIMPLE);
//
//		log.info("Start");
//		long start = System.currentTimeMillis();
//		OrderBoDTO dto = new OrderBoDTO();
//		dto.setBeginIndex(1);
//		
//		dto.setEndIndexStr("50");
////		dto.setEndIndex(50);
//		
//		dto.setMaskingYn("Y");
//		dto.setMallId("TRY");
//		dto.setLangCd("KOR");
//		dto.setStartOrdDt("2016-06-28");
//		dto.setEndOrdDt("2016-07-04");
//
//		List<Object> rs = sessionTemplate1.selectList("com.plgrim.ncp.biz.order.selectBoOrdGodList", dto);
//		log.info("Size:" + rs.size());
//		long end = System.currentTimeMillis();
//		log.info("query Elapsed:" + (end - start));
//		sessionTemplate1.close();
//
//	}

}
