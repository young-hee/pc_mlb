package com.plgrim.ncp.framework.config.profiledcsv;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import com.plgrim.ncp.framework.systems.ApplicationType;

public class ProfiledCsvTest {

	@Test
	public void parseHeader() {

		String src =
				// 0 - Key header
				// 1 - Description header
				// upper 2 - stage header
				"key, desc, common, dev\n";
		ProfiledCsv csv = new ProfiledCsv(new StringReader(src));
		List<Stage> stages = csv.getStages();
		assertEquals("common", stages.get(0).getName());
		assertEquals("dev", stages.get(1).getName());
	}

	@Test
	public void parseValue() {
		String src = "key, desc, common, dev\n" + "key1, desc1, v1,";
		ProfiledCsv csv = new ProfiledCsv(new StringReader(src));
		assertEquals("v1", csv.getProperty("key1"));
	}

	@Test
	public void acvateProfile() {
		String src = "key, desc, common, local, dev\n" + "key1, desc1, v1 , v2 , v3";
		ProfiledCsv csv = new ProfiledCsv(new StringReader(src));

		csv.activate("local");
		assertEquals("v2", csv.getProperty("key1"));

		csv.activate("dev");
		assertEquals("v3", csv.getProperty("key1"));
	}

	@Test(expected = DuplicatedPropertyException.class)
	public void denyDuplicatedKey() {
		String src = "key, desc, common, local, dev\n" + "key1, desc1, v1 , v2 , v3\n" + "key1, desc1, v1 , v2 , v3";
		ProfiledCsv csv = new ProfiledCsv(new StringReader(src));

	}

	@Test
	public void readCommon() {
		String src = "key, desc, common, local, dev\n" + "key1, desc1, v1 , v2 , v3\n" + "key2, desc2, v4 , , ";
		ProfiledCsv csv = new ProfiledCsv(new StringReader(src));

		csv.activate("local");
		assertEquals("v4", csv.getProperty("key2"));

	}

	@Test
	public void readConfigNase() {
		ProfiledCsvPropertySource ps = new ProfiledCsvPropertySource("base");
		ps.activate(new String[] { "ncm", "local" });
		assertEquals("false", ps.getProperty("ncp_base.datasource1.auto.commit"));

		ps.activate(new String[] { "ncm", "stg" });
		assertNull(ps.getProperty("ncp_base.datasource1.auto.commit"));
	}

	@Test
	public void readConfigNCM() {
		ProfiledCsvPropertySource ps = new ProfiledCsvPropertySource(ApplicationType.NCP_FRONT_MOBILE.getSystemId());
		assertEquals("5555",ps.getProperty("ncp_web_mb_dx.search.port"));
	}
	
	@Test
	public void readeKorean() {
		ProfiledCsvPropertySource ps = new ProfiledCsvPropertySource("base");
		ps.activate(new String[] { "ncm", "local" });
		assertEquals("Common", "FASHIONPIA", ps.getProperty("ncp_if_server.erp.jco.erp.userid"));
		assertEquals("Local", "ERP 품질서버", ps.getProperty("ncp_if_server.erp.jco.erp.comments"));

	}



}
