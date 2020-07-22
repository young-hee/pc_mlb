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

public class OracleTest {
	public static void main(String[] args) throws Exception {
		String type = args[0];
		String size = args[1];
		System.out.println("Type:" + type + " size:" + size);
		BufferedReader r = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File("oracleslowquery.sql")), "UTF-8"));
		StringBuffer buf = new StringBuffer();
		while (true) {
			String line = r.readLine();
			if (line == null) {
				break;
			}
			buf.append(line + "\n");
		}
		r.close();

		String sql = buf.toString();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@71.52.2.40:3007:orastg", "ncp", "sncp2015!");
		DatabaseMetaData dbmd = conn.getMetaData();
		System.out.println("Connected to : " + dbmd.getDatabaseProductVersion());
		System.out.println("JDBC Driver: " + dbmd.getDriverVersion());
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

		System.out.println("Type:" + args[0]);
		if ("s".equals(type)) {
			stmt.setString(10, size);
		} else {
			stmt.setInt(10, Integer.parseInt(size));
		}
		stmt.setInt(11, 1);
		System.out.println("Start");
		long start = System.currentTimeMillis();
		ResultSet rs = stmt.executeQuery();
		int count = 0;
		while (rs.next()) {
			count++;
		}
		;
		long end = System.currentTimeMillis();
		System.out.println("Count: " + count);
		System.out.println("Elapsed: " + (end - start) + "ms");
		stmt.close();
		conn.close();
	}
}
