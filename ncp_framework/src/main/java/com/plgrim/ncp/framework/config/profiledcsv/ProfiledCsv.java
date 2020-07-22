package com.plgrim.ncp.framework.config.profiledcsv;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;

/**
 * Profiled CSV 를 읽어 파싱하는 역활을 수행한다.
 * 
 * Profiled CSV 은 다음과 같은 구조를 가진다
 * 
 * 
 * Rows
 * 
 * - Row 0 : 헤더를 기술한다.
 * 
 * - ROW 1 이상: 데이터 를 기술한다.
 * 
 * Header Row 는 다음과 갈은 구성을 가진다
 * 
 * - Col 0: "Key"
 * 
 * - Col 1: "Description"
 * 
 * - Col 2: "Common"
 * 
 * - Col 3 이상 : 프로필명
 * 
 * 
 * Columns
 * 
 * - Col 0: Key 를 기술
 * 
 * - Col 1: Description 기술
 * 
 * - Col 2: 공통 Value
 * 
 * - Col 3이상: 각 프로필별 Value
 * 
 * 
 *
 */
@Slf4j
@Data
public class ProfiledCsv {
	public static final int COL_KEY = 0;
	public static final int COL_DESCRIPTION = 1;
	public static final int COL_VALUE_START = 2;

	public static final int ROW_HEADER = 0;
	public static final int ROW_DATA_START = 1;

	public static final int PROFILE_INDEX_COMMON = 0;

	Map<String, Stage> stageMap = new HashMap<>();
	Set<String> keys = new HashSet<>();
	Stages stages = new Stages();

	private Stage rootStage;

	public ProfiledCsv(InputStream in, String enc) {
		this(creatReader(in, enc));
	}

	private static InputStreamReader creatReader(InputStream in, String enc) {
		try {
			return new InputStreamReader(in, enc);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public ProfiledCsv(Reader reader) {
		parse(reader);
	}

	private void parse(Reader reader) {
		try {
			List<CSVRecord> records = readRecords(reader);
			parseHeaders(records);
			parseValues(records);
			bindStageGraph();
		} catch (DuplicatedPropertyException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void bindStageGraph() {
		// TODO Auto-generated method stub

	}

	private List<CSVRecord> readRecords(Reader reader) throws IOException {
		CSVParser csv = CSVFormat.EXCEL.parse(reader);

		List<CSVRecord> records = csv.getRecords();
		return records;
	}

	private void parseHeaders(List<CSVRecord> records) {
		CSVRecord headerLine = records.get(ROW_HEADER);
		for (int i = COL_VALUE_START; i < headerLine.size(); i++) {
			String stageName = headerLine.get(i).trim();
			addStage(stageName);
		}
	}

	private void addStage(String stageName) {
		String[] pair = Stage.parseStageName(stageName);
		Stage stage = new Stage(pair[0], pair[1]);
		stages.add(stage);
		stageMap.put(stageName, stage);
	}

	private void parseValues(List<CSVRecord> records) {
		for (int row = ROW_DATA_START; row < records.size(); row++) {
			CSVRecord csvRecord = records.get(row);
			String key = csvRecord.get(COL_KEY).trim();
			if (isEmpty(key) || isComment(key)) {
				continue;
			}
			if (keys.contains(key)) {
				throw new DuplicatedPropertyException(key);
			}
			keys.add(key);
			for (int col = COL_VALUE_START; col < csvRecord.size(); col++) {
				String value = csvRecord.get(col).trim();
				if (isEmpty(value)) {
					continue;
				}
				put(col - COL_VALUE_START, key, value);
			}
		}
	}

	private void put(int stageIndex, String key, String value) {
		stages.get(stageIndex).put(key, value);
	}

	private boolean isEmpty(String key) {
		return StringUtils.isEmpty(key);
	}

	private boolean isComment(String key) {
		return key.startsWith("#");
	}

	public String getProperty(String key) {
		return rootStage.get(key);
	}

	public void activate(String profile) {
		rootStage = Stage.activate(stages, profile);
	}

	public String[] getPropertyNames() {
		return keys.toArray(new String[keys.size()]);
	}
}
