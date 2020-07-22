package com.plgrim.ncp.framework.config.profiledcsv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.assertj.core.api.Assertions.*;

/**
 *
 */
@Data
@ToString(exclude="parent")
@AllArgsConstructor
@Slf4j
class Stage {

	String name;
	String alias;
	boolean common;
	boolean active;

	Map<String, String> properties = new HashMap<>();

	Stage parent;
	Stage child;
	Stage leafStage;

	public Stage(String name, String alias) {
		this.name = name;
		this.alias = alias;
		this.common = alias.equals("common");
	}

	public boolean isLeaf() {
		return !common;
	}

	public void setChild(Stage child) {
		child.parent = this;
		this.child = child;
	}

	public void put(String key, String value) {
		properties.put(key, value);
	}

	public String get(String key) {
		return leafStage.getHierachyValue(key);
	}

	private String getHierachyValue(String key) {
		String value = getInSelf(key);
		return value != null ? value : (parent == null ? null : parent.getHierachyValue(key));
	}

	String getInSelf(String key) {
		return properties.get(key);
	}

	public boolean containsKey(String key) {
		return properties.containsKey(key);
	}

	static Pattern ALIAS_PATTERN = Pattern.compile("(\\w+)(\\d+)");

	static String[] parseStageName(String str) {
		if ("common".equals(str)) {
			return new String[] { "common", "common" };
		}
		if (str.contains("_")) {
			return str.split("_");
		}

		Matcher m = ALIAS_PATTERN.matcher(str);
		if (m.find()) {
			return new String[] { m.group(1), m.group(2) };
		} else {
			return new String[] { str, "1" };
		}
	}

	static Stages parseNames(String[] stageList) {

		Stages res = new Stages();
		for (String stageName : stageList) {
			String[] pair = parseStageName(stageName);
			res.add(new Stage(pair[0], pair[1]));
		}
		return res;
	}

	public static Stage activate(Stages stages, String targetNameStr) {
		String[] targetPair = parseStageName(targetNameStr);
		String targetName = targetPair[0];
		String targetAlias = targetPair[1];

		Stage root = stages.find("common", "common");
		assertThat(root).isNotNull();

		Stage next = stages.find(targetName, "common");
		if (next != null) {
			root.setChild(next);
		} else {
			next = root;
		}

		Stage leaf = stages.find(targetName, targetAlias);
		assertThat(leaf).isNotNull();
		root.leafStage = leaf;
		next.setChild(leaf);

		return root;
	}

	public boolean match(String targetName, String targetAlias) {
		return name.equals(targetName) && alias.equals(targetAlias);
	}
}
