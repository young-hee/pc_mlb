package com.plgrim.ncp.base.entities.sample;

import java.util.Collection;

import lombok.Data;

@Data
public class SampleDTO {
	String title;
	boolean isLastPage;
	
	Collection<SampleProductDTO> sampleList;
}
