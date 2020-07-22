package com.plgrim.ncp.commons.testing;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plgrim.ncp.framework.json.JSendResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/testing")
public class CommonTestingController {
	@Autowired
	@Qualifier("jdbcTemplate1")
	JdbcTemplate template1;

	@Autowired
	@Qualifier("sessionTemplate1")
	private SqlSession sqlSession1;

	/**
	 * JSON Web Response Test
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/case001")
	public ResponseEntity<? extends JSendResponse> case001() throws Exception {
		return JSendResponse.success("JSON OK");
	}

	/**
	 * DataSource 1 connection testing
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/case002")
	public ResponseEntity<? extends JSendResponse> case002() throws Exception {
		Integer fromDual = template1.queryForObject("select 99 from dual", Integer.class);
		return JSendResponse.success("JdbcTemplate1 SELECT OK :" + fromDual);
	}

	@RequestMapping("/case003")
	public ResponseEntity<? extends JSendResponse> case003() throws Exception {
		List<String> cdName = sqlSession1.selectList("com.plgrim.ncp.commons.testing.selectSingleCode");
		return JSendResponse.success("SqlSession1 SELECT OK :" + cdName);
	}

}
