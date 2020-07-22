package com.plgrim.ncp.base.entities.sample;

import java.util.Collection;

import lombok.Data;

@Data
public class Category {
	private Collection<Category> childs;
	
	private String name;
	
	private String link;
}
