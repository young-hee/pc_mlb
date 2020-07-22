package com.plgrim.ncp.framework.config.profiledcsv;

import java.util.ArrayList;

public class Stages extends ArrayList<Stage> {
	private static final long serialVersionUID = 2274565211559928107L;

	public Stage find(String name, String alias) {
		for (Stage stage : this) {
			if (stage.match(name, alias)) {
				return stage;
			}
		}
		return null;
	}
	
	String[] names() {
		String[] names = new String[size()];
		for(int i=0; i< this.size(); i++){
			names[i] = get(i).getName();
		}
		return names;
	}

}
